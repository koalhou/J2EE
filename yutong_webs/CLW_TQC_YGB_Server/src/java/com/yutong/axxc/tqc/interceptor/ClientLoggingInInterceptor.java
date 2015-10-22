package com.yutong.axxc.tqc.interceptor;


import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.tools.RandomNumberUtil;

/**
 
 * @version $Revision 1.0 $ 2012-4-11 下午4:08:20
 */
public class ClientLoggingInInterceptor extends AbstractPhaseInterceptor<Message> {
    private boolean isInitialized;
	protected int limit = 100 * 1024;
	protected PrintWriter writer;
	protected boolean prettyLogging;
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

    public ClientLoggingInInterceptor() {
        this(Phase.PRE_PROTOCOL);
    }

    public ClientLoggingInInterceptor(String s) {
        super(Phase.PRE_PROTOCOL);
        isInitialized = true;
    } 

    @Override
	public void handleMessage(Message message) throws Fault {
    	if (writer != null || logger.isInfoEnabled()) {
			logging(logger, message);
		}
    }
    protected void logging(final Logger logger, final Message message)
			throws Fault {

		final String seqNum = RandomNumberUtil.randomString(ModCommonConstant.RANDOM_NUMBER_LENGTH);
		MDC.put("module", "[" + "CLW-API" + "]");
		MDC.put("processType", "[" + seqNum + "]");

		if (message.containsKey(LoggingMessage.ID_KEY)) {
			return;
		}
		String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
		if (id == null) {
			id = LoggingMessage.nextId();
			message.getExchange().put(LoggingMessage.ID_KEY, id);
		}
		message.put(LoggingMessage.ID_KEY, id);
		final LoggingMessage buffer = new LoggingMessage("Inbound Message\n----------------------------", id);

		final Integer responseCode = (Integer) message.get(Message.RESPONSE_CODE);
		if (responseCode != null) {
			buffer.getResponseCode().append(responseCode);
		}

		final String encoding = (String) message.get(Message.ENCODING);

		if (encoding != null) {
			buffer.getEncoding().append(encoding);
		}
		final String httpMethod = (String) message.get(Message.HTTP_REQUEST_METHOD);
		if (httpMethod != null) {
			buffer.getHttpMethod().append(httpMethod);
		}
		final String ct = (String) message.get(Message.CONTENT_TYPE);
		if (ct != null) {
			buffer.getContentType().append(ct);
		}
		final Object headers = message.get(Message.PROTOCOL_HEADERS);

		if (headers != null) {
			buffer.getHeader().append(headers);
		}
		final String uri = (String) message.get(Message.REQUEST_URL);
		if (uri != null) {
			buffer.getAddress().append(uri);
			final String query = (String) message.get(Message.QUERY_STRING);
			if (query != null) {
				buffer.getAddress().append("?").append(query);
			}
		}

		final InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			final CachedOutputStream bos = new CachedOutputStream();
			try {
				IOUtils.copy(is, bos);

				bos.flush();
				is.close();

				message.setContent(InputStream.class, bos.getInputStream());
				if (bos.getTempFile() != null) {
					// large thing on disk...
					buffer.getMessage().append("\nMessage (saved to tmp file):\n");
					buffer.getMessage().append("Filename: " + bos.getTempFile().getAbsolutePath() + "\n");
				}
				if (bos.size() > limit) {
					buffer.getMessage().append("(message truncated to " + limit + " bytes)\n");
				}
				writePayload(buffer.getPayload(), bos, encoding, ct);

				bos.close();
			} catch (final Exception e) {
				throw new Fault(e);
			}
		}

		// log(logger, buffer.toString());
		logger.info(buffer.toString());
	}
	public void setPrettyLogging(final boolean flag) {
		prettyLogging = flag;
	}

	public boolean isPrettyLogging() {
		return prettyLogging;
	}

	protected void writePayload(final StringBuilder builder,
			final CachedOutputStream cos, final String encoding,
			final String contentType) throws Exception {
		// Just transform the XML message when the cos has content
		if (isPrettyLogging()
				&& (contentType != null && contentType.indexOf("xml") >= 0)
				&& cos.size() > 0) {
			final Transformer serializer = XMLUtils.newTransformer(2);
			// Setup indenting to "pretty print"
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			final StringWriter swriter = new StringWriter();
			serializer.transform(new StreamSource(cos.getInputStream()), new StreamResult(swriter));
			final String result = swriter.toString();
			if (result.length() < limit || limit == -1) {
				builder.append(swriter.toString());
			} else {
				builder.append(swriter.toString().substring(0, limit));
			}

		} else {
			if (StringUtils.isEmpty(encoding)) {
				cos.writeCacheTo(builder, limit);
			} else {
				cos.writeCacheTo(builder, encoding, limit);
			}

		}
	}
    protected boolean isInitialized() {
        return isInitialized;
    }

}

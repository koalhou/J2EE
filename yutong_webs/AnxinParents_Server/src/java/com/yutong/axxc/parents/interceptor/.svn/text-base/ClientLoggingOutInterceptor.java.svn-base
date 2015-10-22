package com.yutong.axxc.parents.interceptor;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.axxc.parents.common.ModCommonConstant;

/**
 
 * @version $Revision 1.0 $ 2012-4-11 下午4:08:20
 */
public class ClientLoggingOutInterceptor extends AbstractPhaseInterceptor<Message> {
    private boolean isInitialized;
	private static final String LOG_SETUP = ClientLoggingOutInterceptor.class
			.getName() + ".log-setup";

	protected int limit = 100 * 1024;
	protected PrintWriter writer;
	protected boolean prettyLogging;
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

    public ClientLoggingOutInterceptor() {
        this(Phase.PRE_STREAM);
    }

    public ClientLoggingOutInterceptor(String s) {
        super(Phase.PRE_STREAM);
        isInitialized = true;
    } 

    @Override
	public void handleMessage(Message message) throws Fault {
		final OutputStream os = message.getContent(OutputStream.class);
		if (os == null) {
			return;
		}
		if (logger.isInfoEnabled() || writer != null) {
			// Write the output while caching it for the log message
			final boolean hasLogged = message.containsKey(LOG_SETUP);
			if (!hasLogged) {
				message.put(LOG_SETUP, Boolean.TRUE);
				final CacheAndWriteOutputStream newOut = new CacheAndWriteOutputStream(
						os);
				message.setContent(OutputStream.class, newOut);
				newOut.registerCallback(new LoggingCallback(logger, message, os));
			}
		}
    }

    protected boolean isInitialized() {
        return isInitialized;
    }

	class LoggingCallback implements CachedOutputStreamCallback {

		private final Message message;
		private final OutputStream origStream;
		private final Logger logger; // NOPMD

		public LoggingCallback(final Logger logger, final Message msg,
				final OutputStream os) {
			this.logger = logger;
			this.message = msg;
			this.origStream = os;
		}

		@Override
		public void onFlush(final CachedOutputStream cos) {

		}

		@Override
		public void onClose(final CachedOutputStream cos) {

			String id = (String) message.getExchange().get(
					LoggingMessage.ID_KEY);
			if (id == null) {
				id = LoggingMessage.nextId();
				message.getExchange().put(LoggingMessage.ID_KEY, id);
			}
			final LoggingMessage buffer = new LoggingMessage(
					"Outbound Message\n---------------------------", id);

			final Integer responseCode = (Integer) message
					.get(Message.RESPONSE_CODE);
			if (responseCode != null) {
				buffer.getResponseCode().append(responseCode);
			}

			final String encoding = (String) message.get(Message.ENCODING);

			if (encoding != null) {
				buffer.getEncoding().append(encoding);
			}
			final String httpMethod = (String) message
					.get(Message.HTTP_REQUEST_METHOD);
			if (httpMethod != null) {
				buffer.getHttpMethod().append(httpMethod);
			}
			final String address = (String) message
					.get(Message.ENDPOINT_ADDRESS);
			if (address != null) {
				buffer.getAddress().append(address);
			}
			final String ct = (String) message.get(Message.CONTENT_TYPE);
			if (ct != null) {
				buffer.getContentType().append(ct);
			}
			final Object headers = message.get(Message.PROTOCOL_HEADERS);
			if (headers != null) {
				buffer.getHeader().append(headers);
			}

			if (cos.getTempFile() == null) {
				// buffer.append("Outbound Message:\n");
				if (cos.size() > limit) {
					buffer.getMessage().append(
							"(message truncated to " + limit + " bytes)\n");
				}
			} else {
				buffer.getMessage().append(
						"Outbound Message (saved to tmp file):\n");
				buffer.getMessage().append(
						"Filename: " + cos.getTempFile().getAbsolutePath()
								+ "\n");
				if (cos.size() > limit) {
					buffer.getMessage().append(
							"(message truncated to " + limit + " bytes)\n");
				}
			}
			try {
				writePayload(buffer.getPayload(), cos, encoding, ct);
			} catch (final Exception ex) {
				// ignore
			}

			// log(logger, buffer.toString());
			logger.info(buffer.toString());
			try {
				// empty out the cache
				cos.lockOutputStream();
				cos.resetOut(null, false);
			} catch (final Exception ex) {
				// ignore
			}
			message.setContent(OutputStream.class, origStream);
		}
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
			serializer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");

			final StringWriter swriter = new StringWriter();
			serializer.transform(new StreamSource(cos.getInputStream()),
					new StreamResult(swriter));
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
}

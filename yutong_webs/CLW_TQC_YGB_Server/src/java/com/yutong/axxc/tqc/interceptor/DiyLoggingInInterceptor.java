package com.yutong.axxc.tqc.interceptor;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.LoggingMessage;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;
import org.slf4j.MDC;

import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.tools.RandomNumberUtil;

/**
 
 * @version $Revision 1.0 $ 2012-4-11 下午4:08:20
 */
public class DiyLoggingInInterceptor extends DiyAbstractLoggingInterceptor {
	private static final Logger LOG = LogUtils
			.getLogger(DiyLoggingInInterceptor.class);

	public DiyLoggingInInterceptor() {
		super(Phase.RECEIVE);
	}

	public DiyLoggingInInterceptor(final String phase) {
		super(phase);
	}

	public DiyLoggingInInterceptor(final String id, final String phase) {
		super(id, phase);
	}

	public DiyLoggingInInterceptor(final int lim) {
		this();
		limit = lim;
	}

	public DiyLoggingInInterceptor(final String id, final int lim) {
		this(id, Phase.RECEIVE);
		limit = lim;
	}

	public DiyLoggingInInterceptor(final PrintWriter w) {
		this();
		this.writer = w;
	}

	public DiyLoggingInInterceptor(final String id, final PrintWriter w) {
		this(id, Phase.RECEIVE);
		this.writer = w;
	}

	@Override
	public void handleMessage(final Message message) throws Fault {
		final Logger logger = getMessageLogger(message);
		if (writer != null || logger.isLoggable(Level.INFO)) {
			logging(logger, message);
		}
	}

	protected void logging(final Logger logger, final Message message)
			throws Fault {

		final String reqId = RandomNumberUtil
				.randomString(ModCommonConstant.RANDOM_NUMBER_LENGTH);
		MDC.put("REQ_ID", "[" + reqId + "]");

		if (message.containsKey(LoggingMessage.ID_KEY)) {
			return;
		}
		String id = (String) message.getExchange().get(LoggingMessage.ID_KEY);
		if (id == null) {
			id = LoggingMessage.nextId();
			message.getExchange().put(LoggingMessage.ID_KEY, id);
		}
		message.put(LoggingMessage.ID_KEY, id);
		final LoggingMessage buffer = new LoggingMessage(
				"Inbound Message\n----------------------------", id);

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
					buffer.getMessage().append(
							"\nMessage (saved to tmp file):\n");
					buffer.getMessage().append(
							"Filename: " + bos.getTempFile().getAbsolutePath()
									+ "\n");
				}
				if (bos.size() > limit) {
					buffer.getMessage().append(
							"(message truncated to " + limit + " bytes)\n");
				}
				writePayload(buffer.getPayload(), bos, encoding, ct);

				bos.close();
			} catch (final Exception e) {
				throw new Fault(e);
			}
		}

		// log(logger, buffer.toString());
		LOG.info(buffer.toString());
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}
}

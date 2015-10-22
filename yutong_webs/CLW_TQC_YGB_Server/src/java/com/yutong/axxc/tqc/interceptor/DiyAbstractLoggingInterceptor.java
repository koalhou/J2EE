package com.yutong.axxc.tqc.interceptor;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.helpers.XMLUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.InterfaceInfo;

/**
 
 * @version $Revision 1.0 $ 2012-4-11 下午4:20:14
 */
public abstract class DiyAbstractLoggingInterceptor extends
		AbstractPhaseInterceptor<Message> {

	protected int limit = 100 * 1024;
	protected PrintWriter writer;
	protected boolean prettyLogging;

	public DiyAbstractLoggingInterceptor(final String phase) {
		super(phase);
	}

	public DiyAbstractLoggingInterceptor(final String id, final String phase) {
		super(id, phase);
	}

	protected abstract Logger getLogger();

	Logger getMessageLogger(final Message message) {
		final EndpointInfo endpoint = message.getExchange().getEndpoint()
				.getEndpointInfo();
		if (endpoint.getService() == null) {
			return getLogger();
		}
		Logger logger = endpoint.getProperty("MessageLogger", Logger.class);
		if (logger == null) {
			final String serviceName = endpoint.getService().getName()
					.getLocalPart();
			final InterfaceInfo iface = endpoint.getService().getInterface();
			final String portName = endpoint.getName().getLocalPart();
			final String portTypeName = iface.getName().getLocalPart();
			final String logName = "org.apache.cxf.services." + serviceName
					+ "." + portName + "." + portTypeName;
			logger = LogUtils.getL7dLogger(this.getClass(), null, logName);
			endpoint.setProperty("MessageLogger", logger);
		}
		return logger;
	}

	public void setOutputLocation(final String s) {
		if (s == null || "<logger>".equals(s)) {
			writer = null;
		} else if ("<stdout>".equals(s)) {
			writer = new PrintWriter(System.out, true);
		} else if ("<stderr>".equals(s)) {
			writer = new PrintWriter(System.err, true);
		} else {
			try {
				final URI uri = new URI(s);
				final File file = new File(uri);
				writer = new PrintWriter(new FileWriter(file, true), true);
			} catch (final Exception ex) {
				getLogger().log(Level.WARNING,
						"Error configuring log location " + s, ex);
			}
		}
	}

	public void setPrintWriter(final PrintWriter w) {
		writer = w;
	}

	public PrintWriter getPrintWriter() {
		return writer;
	}

	public void setLimit(final int lim) {
		limit = lim;
	}

	public int getLimit() {
		return limit;
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

	/**
	 * Transform the string before display. The implementation in this class
	 * does nothing. Override this method if you wish to change the contents of
	 * the logged message before it is delivered to the output. For example, you
	 * can use this to mask out sensitive information.
	 * 
	 * @param originalLogString
	 *            the raw log message.
	 * @return transformed data
	 */
	protected String transform(final String originalLogString) {
		return originalLogString;
	}

	protected void log(final Logger logger, String message) {
		message = transform(message);
		if (writer != null) {
			writer.println(message);
			// Flushing the writer to make sure the message is written
			writer.flush();
		} else if (logger.isLoggable(Level.INFO)) {
			final LogRecord lr = new LogRecord(Level.INFO, message);
			lr.setSourceClassName(logger.getName());
			lr.setSourceMethodName(null);
			lr.setLoggerName(logger.getName());
			logger.log(lr);
		}
	}

}

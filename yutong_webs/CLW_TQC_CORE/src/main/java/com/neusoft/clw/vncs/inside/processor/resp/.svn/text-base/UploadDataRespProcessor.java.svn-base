package com.neusoft.clw.vncs.inside.processor.resp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.nio.client.ICommunicateService;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;
import com.neusoft.clw.vncs.inside.processor.AbstractInsideProcessor;

public class UploadDataRespProcessor extends
		AbstractInsideProcessor<InsideMsg, ICommunicateService> {

	private static Logger log = LoggerFactory
			.getLogger(UploadDataRespProcessor.class);
	

	public static final UploadDataRespProcessor PROCESSOR = new UploadDataRespProcessor();

	private UploadDataRespProcessor() {
	}

	public static UploadDataRespProcessor getInstance() {
		return PROCESSOR;
	}

	public void handle(InsideMsg msg, ICommunicateService communicateService)
			throws HandleException {
		try {
			communicateService.send(InsideMsgFactory.createUpLoadDataResp(
					msg.getSeq()).getBytes());
			log.info(LogFormatter.formatMsg("UploadDataRespProcessor",
					"send a uploaddate response."));
			msg = null;
		} catch (Throwable t) {
			throw new HandleException("handle active test request failed.", t);
		}
	}

	public InsideMsg parse(byte[] buf) throws ParseException {
		return null;		
//		super.parseHeader(buf, msg);
		
	}

	public void valid(InsideMsg msg) throws InvalidMessageException {
		super.validHeader(msg);
	}
}

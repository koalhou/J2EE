package com.neusoft.smsplatform.message.inside.processor.resp;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageBindResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;



/**
 * 登陆应答processor
 * @author Chenqiong
 *
 */
public final class MessageBindRespProcessor extends MessageAbstractInsideProcessor<MessageBindResp, SmsCommunicateService> {

    private static final MessageBindRespProcessor PROCESSOR = new MessageBindRespProcessor();

    private MessageBindRespProcessor() {
    }

    public static MessageBindRespProcessor getInstance() {
        return PROCESSOR;
    }

	public MessageBindResp parse(byte[] buf) throws ParseException {
        try {
            MessageBindResp resp = new MessageBindResp();
            int location = super.parseHeader(buf, resp);
            String result = new String(buf,location,MessageBindResp.RESULTSIZE);
            location+=MessageBindResp.RESULTSIZE;
            resp.setResult(result);
            return resp;
        } catch (Throwable t) {
            throw new ParseException("parse bind response failed.", t);
        }
    }

    public void valid(MessageBindResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(MessageBindResp msg, SmsCommunicateService communicateService) throws HandleException {
    	
    }
}

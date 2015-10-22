package com.neusoft.clw.vncs.inside.processor.resp;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import com.neusoft.log.Logformater;
//import com.neusoft.tag.app.inside.msg.InsideMsgStatusCode;
//import com.neusoft.tag.app.inside.msg.resp.SendDataResp;
//import com.neusoft.tag.app.inside.processor.AbstractInsideProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.inside.msg.resp.SendDataResp;
import com.neusoft.clw.vncs.inside.processor.AbstractInsideProcessor;
import com.neusoft.clw.vncs.nio.CommunicateService;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2009-2-20 上午09:34:11
 */
public final class SendDataRespProcessor extends
        AbstractInsideProcessor<SendDataResp, CommunicateService> {

    private Logger log = LoggerFactory.getLogger(SendDataRespProcessor.class);

    public static final SendDataRespProcessor PROCESSOR = new SendDataRespProcessor();
    
    private SendCommandDAO sendCommandDAO;

    private SendDataRespProcessor() {
    	sendCommandDAO = (SendCommandDAO) SpringBootStrap.getInstance().getBean("sendCommandDAO");
    }

    public static SendDataRespProcessor getInstance() {
        return PROCESSOR;
    }

    public SendDataResp parse(byte[] buf) throws ParseException {
        SendDataResp resp = new SendDataResp();
        super.parseHeader(buf, resp);
        return resp;
    }

    public void valid(SendDataResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(SendDataResp msg, CommunicateService nioService) {
    	String msg_id = (String) Constant.respMap.get(msg.getSeq());
    	if (msg_id == null) {
    		log.info(LogFormatter.formatMsg("SendDataRespProcessor", "can not find the transaction matched seq "+msg.getSeq()));
    		Constant.respMap.remove(msg.getSeq());
    	}else{
    		sendCommandDAO.UpdateTwoTimes(msg_id);
    		log.info(LogFormatter.formatMsg("SendDataRespProcessor", msg.getSeq()+" already send to the platform"));
    		Constant.respMap.remove(msg.getSeq());			
    	}
    	msg = null;
    }
    
}

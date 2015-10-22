
package com.neusoft.smsplatform.message.inside.processor.req;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.req.ChangePhoneReq;
import com.neusoft.smsplatform.message.inside.msg.resp.ChangePhoneResp;
import com.neusoft.smsplatform.message.inside.msg.resp.OrderRelationsResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;

/**
 * 修改手机解析类
 * @author chenqiong
 *
 */
public final class ChangePhoneReqProcessor extends
        MessageAbstractInsideProcessor<ChangePhoneReq, SmsCommunicateService> {

    private Logger log = LoggerFactory.getLogger(ChangePhoneReqProcessor.class);

	private static final String NAME = "<OrderRelationsReqProcessor>";

    public static final ChangePhoneReqProcessor PROCESSOR = new ChangePhoneReqProcessor();
    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    private ChangePhoneReqProcessor() {
    }

    public static ChangePhoneReqProcessor getInstance() {
        return PROCESSOR;
    }

    public ChangePhoneReq parse(byte[] buf) throws ParseException {
        try {
        	log.info("ChangePhoneReq:变更手机号处理开始");
        	ChangePhoneReq req = new ChangePhoneReq();
            int location = super.parseHeader(buf, req);
            String stu_id = new String(buf,location,ChangePhoneReq.STUIDSIZE).trim();
            location+=ChangePhoneReq.STUIDSIZE;
            String phone1 = new String(buf,location,ChangePhoneReq.PHONE1SIZE).trim();
            location+=ChangePhoneReq.PHONE1SIZE;
            String phone2 = new String(buf,location,ChangePhoneReq.PHONE2SIZE).trim();
            location+=ChangePhoneReq.PHONE2SIZE;
            log.info("ChangePhoneReq:stu_id="+stu_id+",phone1="+phone1+",phone2="+phone2);
            req.setStu_id(stu_id);
            req.setPhone1(phone1);
            req.setPhone2(phone2);
            log.info("ChangePhoneReq:变更手机号处理结束");
            return req;
        } catch (Throwable t) {
            throw new ParseException("parse active test requset failed.", t);
        }
    }

    public void valid(ChangePhoneReq msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(ChangePhoneReq msg, SmsCommunicateService nioService) throws HandleException {
    	ChangePhoneResp resp = new ChangePhoneResp();
    	try{
	    	if(msg.getPhone1()==null||msg.getPhone1().equals("")){
	    		if(msg.getPhone2()!=null&&!msg.getPhone2().equals("")){
	    			dao.insertChangePhone(msg.getStu_id(), msg.getPhone2());
	    			resp.setResult("0");
	    		}else{
	    			resp.setResult("1");
	    		}
	    	}else{
	    		if(msg.getPhone2()!=null&&!msg.getPhone2().equals("")){
	    			dao.updateChangePhoneValid(msg.getStu_id(),msg.getPhone1());
	    			dao.insertNewChangePhone(msg.getStu_id(), msg.getPhone1(), msg.getPhone2());
	    			resp.setResult("0");
	    		}else{
	    			dao.deleteChangePhone(msg.getStu_id(), msg.getPhone1());
	    			resp.setResult("0");
	    		}
	    	}
    	}catch(DataAccessException e){
    		log.error(NAME +"在入库时发生异常："+e);
    		resp.setResult("1");
    	}
    	resp.setCommand(msg.getCommand());
    	resp.setMsgLength(String.valueOf(OrderRelationsResp.total));
    	resp.setSeqLength(msg.getSeqLength());
		try {
			nioService.send(resp.getBytes());
		} catch (Exception e) {
			log.error(NAME+"变更手机号返回处理结果时发生错误："+e);
			e.printStackTrace();
		}
    }
}

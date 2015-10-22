
package com.neusoft.smsplatform.message.inside.processor.req;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.req.OrderRelationsReq;
import com.neusoft.smsplatform.message.inside.msg.resp.OrderRelationsResp;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;

/**
 * 订购关系解析类
 * @author chenqiong
 *
 */
public final class OrderRelationsReqProcessor extends
        MessageAbstractInsideProcessor<OrderRelationsReq, SmsCommunicateService> {

    private Logger log = LoggerFactory.getLogger(OrderRelationsReqProcessor.class);

	private static final String NAME = "<OrderRelationsReqProcessor>";

    public static final OrderRelationsReqProcessor PROCESSOR = new OrderRelationsReqProcessor();
    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    private OrderRelationsReqProcessor() {
    }

    public static OrderRelationsReqProcessor getInstance() {
        return PROCESSOR;
    }

    public OrderRelationsReq parse(byte[] buf) throws ParseException {
        try {
        	log.info("OrderRelationsReq:手机订购或退订处理开始");
        	OrderRelationsReq req = new OrderRelationsReq();
            int location = super.parseHeader(buf, req);
            String stu_id = new String(buf,location,OrderRelationsReq.STUIDSIZE).trim();
            location+=OrderRelationsReq.STUIDSIZE;
            String tag = new String(buf,location,OrderRelationsReq.TAGSIZE).trim();
            location+=OrderRelationsReq.TAGSIZE;
            req.setStu_id(stu_id);
            req.setTag(tag);
            if(tag.equals("0")){
            	String phone1 = new String(buf,location,OrderRelationsReq.PHONE1SIZE).trim();
                location+=OrderRelationsReq.PHONE1SIZE;
                String relative = new String(buf,location,OrderRelationsReq.RELATIVETYPE1SIZE).trim();
                location+=OrderRelationsReq.RELATIVETYPE1SIZE;
            	req.setPhone1(phone1);
                req.setRelative_type1(relative);
                if(location<Integer.parseInt(req.getMsgLength())){
    	            String phone2 = new String(buf,location,OrderRelationsReq.PHONE2SIZE).trim();
    	            location+=OrderRelationsReq.PHONE2SIZE;
    	            String relative2 = new String(buf,location,OrderRelationsReq.RELATIVETYPE2SIZE).trim();
    	            location+=OrderRelationsReq.RELATIVETYPE2SIZE;
    	            req.setPhone2(phone2);
    	            req.setRelative_type2(relative2);
    	            if(location<Integer.parseInt(req.getMsgLength())){
    		            String phone3 = new String(buf,location,OrderRelationsReq.PHONE3SIZE).trim();
    		            location+=OrderRelationsReq.PHONE2SIZE;
    		            String relative3 = new String(buf,location,OrderRelationsReq.RELATIVETYPE3SIZE).trim();
    		            location+=OrderRelationsReq.RELATIVETYPE3SIZE;
    		            req.setPhone3(phone3);
    		            req.setRelative_type3(relative3);
    	            }
                }
            }
            
            
            if(location>Integer.parseInt(req.getMsgLength())){
            	log.error(NAME+"手机订购或退订协议上报错误");
            	throw new Exception("手机订购或退订协议上报错误");
            }            
//            log.info("OrderRelationsReq:stu_id="+stu_id+",tag="+tag+",phone1="+phone1+",type1="+relative+"," +
//            		",phone2="+phone2+"type2="+relative2+",phone3="+phone3+"type3="+relative3);
            log.info("OrderRelationsReq:手机订购或退订处理结束");
            return req;
        } catch (Throwable t) {
            throw new ParseException("parse active test requset failed.", t);
        }
    }

    public void valid(OrderRelationsReq msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(OrderRelationsReq msg, SmsCommunicateService nioService) throws HandleException {
    	OrderRelationsResp resp = new OrderRelationsResp();
    	try{
	    	if(msg.getTag().equals("0")){
	    		if(msg.getPhone1()!=null&&!msg.getPhone1().equals("")){
	    			dao.insertOrder(msg.getStu_id(), msg.getPhone1(), msg.getRelative_type1(),"0");
	    		}
	    		if(msg.getPhone2()!=null&&!msg.getPhone2().equals("")){
	    			if(msg.getRelative_type2()!=null&&!msg.getRelative_type2().equals("")){
	    				dao.insertOrder(msg.getStu_id(), msg.getPhone2(), msg.getRelative_type2(),"1");
	    			}else{
	    				dao.insertOrder(msg.getStu_id(), msg.getPhone2(), "6","1");
	    			}
	    		}
	    		if(msg.getPhone3()!=null&&!msg.getPhone3().equals("")){
	    			if(msg.getRelative_type3()!=null&&!msg.getRelative_type3().equals("")){
	    				dao.insertOrder(msg.getStu_id(), msg.getPhone3(), msg.getRelative_type3(),"1");
	    			}else{
	    				dao.insertOrder(msg.getStu_id(), msg.getPhone3(), "6","1");
	    			}
	    			
	    		}
	    	}else{
	    		dao.updateUnsubscribe(msg.getStu_id());
	    	}
	    	resp.setResult("0");
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
			log.error(NAME+"订购关系返回处理结果时发生错误："+e);
			e.printStackTrace();
		}
    }
}

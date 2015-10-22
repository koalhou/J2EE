package com.neusoft.smsplatform.message.inside.processor.resp;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.smsplatform.message.dao.UpdateDAO;
import com.neusoft.smsplatform.message.inside.msg.req.SendMessageReq;
import com.neusoft.smsplatform.message.inside.msg.resp.SendMessageResp;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;
import com.neusoft.smsplatform.message.util.SendMethod;


/**
 * 短信发送 应答processor
 * @author chenqiong
 *
 */

public final class SendMessageRespProcessor extends MessageAbstractInsideProcessor<SendMessageResp, SmsCommunicateService> {

    private static final SendMessageRespProcessor PROCESSOR = new SendMessageRespProcessor();
    private Logger log = LoggerFactory.getLogger(SendMessageRespProcessor.class);
	private static final String NAME = "<SendMessageRespProcessor>";
    private static UpdateDAO dao = (UpdateDAO) SpringBootStrap.getInstance().getBean("updatedao");
    private SendMessageRespProcessor() {
    }

    public static SendMessageRespProcessor getInstance() {
        return PROCESSOR;
    }

	@SuppressWarnings("unchecked")
	public SendMessageResp parse(byte[] buf) throws ParseException {
        try {
        	log.info("SendMessageResp:发送短信接收回应处理开始");
        	SendMessageResp resp = new SendMessageResp();
        	int location = super.parseHeader(buf, resp);
            String result = new String(buf,location,SendMessageResp.RESULTSIZE);
            location += SendMessageResp.RESULTSIZE;
            Map map = null;
            if(location!=Integer.parseInt(resp.getMsgLength())){
        		throw new Exception("协议上报错误！");
        	}
        	if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
        		Object obj = Constant.getMemcachedClient().getObject(resp.getSeqLength());
        		if(obj!=null&&!obj.equals("")){
        			map = (Map) obj;
        		}
        	}else{
        		map = (Map) Constant.ytbsendcmdMap.get(resp.getSeqLength());
        	}
        	if(map!=null&&map.size()>0){
	            if(result.equals("0")){
	            	if(location!=Integer.parseInt(resp.getMsgLength())){
	            		throw new Exception("全部成功时协议上报错误！");
	            	}
	            	String recordid = (String) map.get("recordid");
	            	if(recordid!=null&&!recordid.equals("")){
	            		DBBuffer.getInstance().add(dao.updateCheckRecord(recordid, "1","短信发送成功"));
	            	}
	            	String msgid1 = (String) map.get("msgid1");
	            	if(msgid1!=null&&!msgid1.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid1, "0","短信发送成功"));
	            	}
	            	String msgid2 = (String) map.get("msgid2");
	            	if(msgid2!=null&&!msgid2.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid2, "0","短信发送成功"));
	            	}
	            	String msgid3 = (String) map.get("msgid3");
	            	if(msgid3!=null&&!msgid3.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid3, "0","短信发送成功"));
	            	}
	            }else if(result.equals("1")){
	            	if(location!=Integer.parseInt(resp.getMsgLength())){
	            		throw new Exception("全部失败时协议上报错误！");
	            	}
	            	String recordid = (String) map.get("recordid");
	            	if(recordid!=null&&!recordid.equals("")){
	            		DBBuffer.getInstance().add(dao.updateCheckRecord(recordid, "2","短信发送失败"));
	            	}
	            	String msgid1 = (String) map.get("msgid1");
	            	if(msgid1!=null&&!msgid1.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid1, "1","短信发送失败"));
	            	}
	            	String msgid2 = (String) map.get("msgid2");
	            	if(msgid2!=null&&!msgid2.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid2, "1","短信发送失败"));
	            	}
	            	String msgid3 = (String) map.get("msgid3");
	            	if(msgid3!=null&&!msgid3.equals("")){
	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid3, "1","短信发送失败"));
	            	}
	            	
	            	Integer count = (Integer)map.get("count");
	            	if(count <=3){
		            	SendMessageReq req = new SendMessageReq();
		            	String seq = SmsCommonMsgUtils.getSeq();
		            	req.setSeqLength(seq);
		            	req.setCommand(SendMessageReq.COMMAND);
		            	req.setMsgLength(String.valueOf(SendMessageReq.HEADERSIZE));
		            	req.setPhone1((String) map.get("phone1"));
		            	req.setPhone2((String) map.get("phone2"));
		            	req.setPhone3((String) map.get("phone3"));
		            	req.setMessage((String) map.get("message"));
		            	SendMethod.SendCommand(req.getBytes());
		            	log.info(NAME +"该短信第"+(count+1)+"次发送成功");
		            	map.put("count", count+1);
		            	if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
		    				Constant.getMemcachedClient().insert(req.getSeqLength(), map);
		    			}
		    			Constant.ytbsendcmdMap.put(req.getSeqLength(), map);
	            	}
	            }else if(result.equals("3")){
	            	String phone1 = new String(buf,location,SendMessageResp.PHONE1SIZE);
	            	location+=SendMessageResp.PHONE1SIZE;
	            	String phone2 = null;
	            	if(location<Integer.parseInt(resp.getMsgLength())){
	            		phone2 = new String(buf,location,SendMessageResp.PHONE2SIZE);
	            		location+=SendMessageResp.PHONE2SIZE;
	            	}else if(location>Integer.parseInt(resp.getMsgLength())){
	            		throw new Exception("部分成功时协议上报错误！");
	            	}
	            	String recordid = (String) map.get("recordid");
	            	if(recordid!=null&&!recordid.equals("")){
	            		DBBuffer.getInstance().add(dao.updateCheckRecord(recordid, "3","短信发送部分成功"));
	            	}
	            	String cache_phone1 = (String) map.get("phone1");
	            	String cache_phone2 = (String) map.get("phone2");
	            	String cache_phone3 = (String) map.get("phone3");
	            	if(cache_phone1!=null&&!cache_phone1.equals("")){
	            		String msgid1 = (String) map.get("msgid1");
	            		if(msgid1!=null&&!msgid1.equals("")){
		            		if(cache_phone1.equals(phone1)
		            				||(phone2!=null&&!phone2.equals("")&&cache_phone1.equals(phone2))){			
		            			DBBuffer.getInstance().add(dao.updateSmsRecord(msgid1, "0","短信发送成功"));
		            		}else{
		            			DBBuffer.getInstance().add(dao.updateSmsRecord(msgid1, "1","短信发送失败"));
		            		}
	            		}
	            	}
	            	if(cache_phone2!=null&&!cache_phone2.equals("")){
	            		String msgid2 = (String) map.get("msgid2");
    	            	if(msgid2!=null&&!msgid2.equals("")){
	            			if(cache_phone2.equals(phone1)
	            				||(phone2!=null&&!phone2.equals("")&&cache_phone2.equals(phone2))){
	            				DBBuffer.getInstance().add(dao.updateSmsRecord(msgid2, "0","短信发送成功"));
	    	            	}else{
	    	            		DBBuffer.getInstance().add(dao.updateSmsRecord(msgid2, "1","短信发送失败"));
		            		}
	            		}
	            	}
	            	if(cache_phone3!=null&&!cache_phone3.equals("")){
	            		String msgid3 = (String) map.get("msgid3");
    	            	if(msgid3!=null&&!msgid3.equals("")){
		            		if(cache_phone3.equals(phone1)
		            				||(phone2!=null&&!phone2.equals("")&&cache_phone3.equals(phone2))){
		            			DBBuffer.getInstance().add(dao.updateSmsRecord(msgid3, "0","短信发送成功"));
		    	            }else{
		    	            	DBBuffer.getInstance().add(dao.updateSmsRecord(msgid3, "1","短信发送失败"));
		            		}
	            		}
	            	}          	
	            }
	            if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
	        		Constant.getMemcachedClient().delete(resp.getSeqLength());
	        	}
	            Constant.ytbsendcmdMap.remove(resp.getSeqLength());
        	}
        	log.info("SendMessageResp:发送短信接收回应处理结束");
            return resp;
        } catch (Throwable t) {
            throw new ParseException("parse send message response failed.", t);
        }
    }

    public void valid(SendMessageResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(SendMessageResp msg, SmsCommunicateService communicateService) throws HandleException {
    }
}

package com.neusoft.clw.info.business;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.SendCmdService;
 

public class SendCommandBean {
	private static String NAME = "";
	private static Logger log = LoggerFactory.getLogger(SendCommandBean.class);
	@SuppressWarnings({ "static-access", "unchecked" })
	public static void sendCommand(RequestBean reqBean) throws SendInfoException, UnsupportedEncodingException {
		
		  SendReq req = new SendReq();
	        req.setCommand(reqBean.getSend_command());
	        req.setStatusCode(SendReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        req.setPacket_content(reqBean.getPacket_content());
	        req.setPacket_con_length(String.valueOf(req.getPacket_content().getBytes("GBK").length));
	        req.setTerminal_id(reqBean.getTerminal_id());
	        req.setMsgLength(String.valueOf(SendReq.MSGHEADERSIZE+req.TIMIDESIZE+req.PACKETLENG+req.getPacket_content().getBytes("GBK").length));
//	        System.out.println("输出："+new String(bytes));
	        log.info(NAME+":::"+new String(req.getBytes()));
			String url = SendCmdService.cycleSendCommand(req.getBytes());
	        if(url!=null&&!url.equals("")){
	        	log.info(NAME +"向"+url+"下发命令成功！");
	        	Constant.respMap.put(req.getSeq(),reqBean.getMsg_id());
	        	log.debug("Constant.respMap"+Constant.respMap);
	        	if(reqBean.getSend_type().equals("2B00")){
	        		Constant.getMemcachedClient().insert(reqBean.getTerminal_id()+reqBean.getSend_type(), reqBean.getMsg_id());
	        		Constant.ytbsendcmdMap.put(reqBean.getTerminal_id()+reqBean.getSend_type(), reqBean.getMsg_id());
	        	}
	        }else{
	        	log.info(NAME+"平台url未配置,下发命令失败");
	        	throw new SendInfoException("下发命令失败");
	        }
	}
	
	public static void main(String[] args){
		System.out.println("00042002012031a3d10ea0934e8a8c8378d1898f5bfc0201103000c这是程的测试".length());
		System.out.println("00042002012031a3d10ea0934e8a8c8378d1898f5bfc0201103000c这是程".length());
	}
}

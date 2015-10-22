package com.neusoft.clw.info.business;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.SendExceptionSettingReq;
import com.neusoft.clw.info.bean.SendLineReq;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.SendCmdService;
 

public class SendExceptionSettingCommandBean {
	private static String NAME = "";
	private static Logger log = LoggerFactory.getLogger(SendExceptionSettingCommandBean.class);
	@SuppressWarnings({ "unchecked" })
	public static void sendCommand(RequestBean reqBean, SendCommandDAO sendCommandDAO) throws SendInfoException, ParseException, DBErrorException, UnsupportedEncodingException {
		
		  	SendExceptionSettingReq req = new SendExceptionSettingReq();
		  	req.setCommand(reqBean.getSend_command());
	        req.setStatusCode(SendReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        req.setMsg_id(reqBean.getMsg_id());
	        req.setTerminal_id(reqBean.getTerminal_id());
	        req.setBytetype("F3");
	        req.setPacket_content(req.getTcToString());
	        sendCommandDAO.saveExceptionSettingCommand(reqBean,req);
			String url = SendCmdService.cycleSendCommand(req.getBytes());
	        if(url!=null&&!url.equals("")){
	        	log.info(NAME +"向"+url+"下发命令成功！");
	        	Constant.respMap.put(req.getSeq(),reqBean.getMsg_id());
	        	log.debug("Constant.respMap"+Constant.respMap);
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

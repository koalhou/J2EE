package com.neusoft.clw.info.business;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.SendLineReq;
import com.neusoft.clw.info.bean.SendLockReq;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.SendCmdService;
import com.neusoft.clw.vncs.util.Converser;
 

public class SendLineCommandBean {
	private static String NAME = "";
	private static Logger log = LoggerFactory.getLogger(SendLineCommandBean.class);
	@SuppressWarnings({ "unchecked" })
	public static void sendCommand(RequestBean reqBean, SendCommandDAO sendCommandDAO) throws SendInfoException, ParseException, DBErrorException, UnsupportedEncodingException {
		
		  	SendLineReq req = new SendLineReq();
		  	req.setCommand(reqBean.getSend_command());
	        req.setStatusCode(SendReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        req.setMsg_id(reqBean.getMsg_id());
	        req.setTerminal_id(reqBean.getTerminal_id());
	        req.setIplength(Integer.toHexString(Integer.parseInt(reqBean.getIplength())));
	        req.setIp(reqBean.getIp());
	        req.setPort(Integer.toHexString(Integer.parseInt(reqBean.getPort())));
	        req.setUserlength(Integer.toHexString(Integer.parseInt(reqBean.getUserlength())));
	        req.setUsername(reqBean.getUsername());
	        req.setPasslength(Integer.toHexString(Integer.parseInt(reqBean.getPasslength())));
	        req.setUserpass(reqBean.getUserpass());
	        req.setFilelength(Integer.toHexString(Integer.parseInt(reqBean.getFilelength())));
	        req.setFilename(reqBean.getFilename());
	        req.setCrc(Long.toHexString(Long.parseLong(reqBean.getCrc())));
	        req.setBytetype("80");
	        req.setPacket_content(req.getTcToString());
	        sendCommandDAO.saveLineCommand(reqBean,req);
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
	
	@SuppressWarnings({ "unchecked" })
	public static void sendCommand_lock(RequestBean reqBean, SendCommandDAO sendCommandDAO) throws SendInfoException, ParseException, DBErrorException, UnsupportedEncodingException {
		
		  	SendLockReq req = new SendLockReq();
		  	req.setCommand(reqBean.getSend_command());
	        req.setStatusCode(SendReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        req.setMsg_id(reqBean.getMsg_id());
	        req.setTerminal_id(reqBean.getTerminal_id());
	        req.setBytetype("80");
	        
	        req.setPacket_content(reqBean.getPacket_content());
	        
	        //下发命令入库
	        sendCommandDAO.saveLockCommand(reqBean,req);
	        
	        byte[] bb = req.getBytes();
			String url = SendCmdService.cycleSendCommand(bb);
			
	        if(url!=null&&!url.equals("")){
	        	log.info("远程锁车向前置机下发成功！");
	        	Constant.respMap.put(req.getSeq(),reqBean.getMsg_id());
	        	log.debug("Constant.respMap"+Constant.respMap);
	        }else{
	        	log.info(NAME+"平台url未配置,下发命令失败");
	        	throw new SendInfoException("下发命令失败");
	        }
	}
	
	public static void main(String[] args){
		
	}
}

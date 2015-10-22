package com.neusoft.clw.info.business;

import java.text.ParseException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.SendHolesReq;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.SendCmdService;
 

public class SendHolesCommandBean {
	private static String NAME = "";
	private static Logger log = LoggerFactory.getLogger(SendHolesCommandBean.class);
	@SuppressWarnings({ "unchecked" })
	public static void sendCommand(RequestBean reqBean, SendCommandDAO sendCommandDAO) throws SendInfoException, ParseException, DBErrorException {
		
		  	SendHolesReq req = new SendHolesReq();
		  	String routeid = Integer.toHexString(Integer.parseInt(reqBean.getRoute_id()));
		  	String stu_id = Integer.toHexString(Integer.parseInt(reqBean.getStu_id()));
		  	log.info(NAME+"线路编号："+routeid+"学生编号："+stu_id);
	        req.setCommand(reqBean.getSend_command());
	        req.setStatusCode(SendReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        req.setBegintime(DateUtil.changeTime14To12Format(reqBean.getBeginTime()));
	        req.setEndtime(DateUtil.changeTime14To12Format(reqBean.getEndTime()));
//	        req.setRoute_id(routeid);
	        req.setStu_id(stu_id);
	        req.setMsg_id(reqBean.getMsg_id());
	        req.setSend_type(reqBean.getSend_type());
	        req.setTerminal_id(reqBean.getTerminal_id());
	        req.setBytetype("80");
	        req.setPacket_content(req.getTcToString());
	        sendCommandDAO.saveHolesCommand(reqBean,req);
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

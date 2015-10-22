package com.neusoft.pushmessage.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.mobile.bean.PhotoBean;
import com.neusoft.pushmessage.buffer.PushBean;
import com.neusoft.pushmessage.buffer.PushBuffer;

public class UpPhotoThread extends Thread implements Runnable{
	private static Logger log = LoggerFactory.getLogger(UpPhotoThread.class);
	private Up_InfoContent urt;
	public UpPhotoThread(Up_InfoContent urt){
		this.urt = urt;
	}
	
	public void run(){
		log.info("接收到上行照片信息，上行照片推送处理开始");
		PhotoBean bean = null;
		Object obj = null;
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			obj = Constant.getMemcachedClient().getObject(urt.getTerminalId()+urt.getMsg_id());
			if(obj!=null&&!obj.equals("")){
				bean = (PhotoBean) obj;
			}else{
				bean = (PhotoBean) Constant.ytbsendcmdMap.get(urt.getTerminalId()+urt.getMsg_id());
			}
		}else{
			bean = (PhotoBean) Constant.ytbsendcmdMap.get(urt.getTerminalId()+urt.getMsg_id());
		}
		Constant.getMemcachedClient().delete(urt.getTerminalId()+urt.getMsg_id());
		Constant.ytbsendcmdMap.remove(urt.getTerminalId()+urt.getMsg_id());
		if(bean!=null&&!bean.equals("")){
			if(bean.getUser_id()!=null && !bean.getUser_id().equals("")
			   && bean.getClient_id()!=null && !bean.getClient_id().equals("")){
				bean.setPhoto_id(urt.getMsg_id());
				bean.setGrant_type("1");
				bean.setVersion_id(Constant.VERSION_ID);
				bean.setP_s(Config.props.getProperty("push_switch"));
				PushBean p = new PushBean();
				p.setType("1");
				p.setPhotoBean(bean);
				PushBuffer.getInstance().add(p);
			}else{
				log.info("UpPhotoThread 用户id或者客户端id为空，无法进行推送");
			}
		}else{
			log.info("UpPhotoThread 该车照片指令缓存不存在！");
		}
		bean = null;
		
		log.info("照片信息已存入推送队列,等待推送,上行照片推送处理结束");
	}
}

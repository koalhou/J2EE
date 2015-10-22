package com.neusoft.clw.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.smsplatform.message.buffer.send.SendBuffer;
import com.neusoft.smsplatform.message.buffer.sync.SyncBuffer;

public class XdThread extends Thread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(XdThread.class);
	private String path;
	
	public XdThread(String path){
		this.path = path;
	}
	
	public void run() {
		new ClassPathXmlApplicationContext(path);
		
		SendBuffer sendBuffer = SendBuffer.getInstance();
		Thread worker3 = new Thread(sendBuffer);
		worker3.start();
		
		SyncBuffer syncBuffer = SyncBuffer.getInstance();
		Thread worker4 = new Thread(syncBuffer);
		worker4.start();
		
		
//		 try {
//			while(true){
////         	log.info("短信准备发送");
//			 	SendMessageReq req = sendmessage();	
//			        List<SendMessageReq> list = new ArrayList<SendMessageReq>();
//			        if(req!=null){
//			        	list.add(req);	
//			        }else{
////	            	System.out.println("本次处理结束1");
//			        	continue;
//			        }
//			        if(list == null||list.size()==0){
////	            	System.out.println("本次处理结束2");
//			        	continue;
//			        }
////	            if(list!=null&&list.size()<20){
////	            	continue;
////	            }
//			       
//			        SendBuffer.getInstance().add(list);
////	            log.info("放入队列SendBuffer，等待发送");
////			        Thread.sleep(1000);
////	            System.out.println("本次处理结束");
//			 }
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (java.text.ParseException e) {
//			e.printStackTrace();
//		} 
	}

//	@SuppressWarnings("unused")
//	private SendMessageReq sendmessage() throws ParseException, java.text.ParseException{
//		Up_InfoContent uhc = new Up_InfoContent();
//		uhc.setStu_id("421");
//		uhc.setSms_eventtype("5");
//		uhc.setTerminalId("LZYTCTC20C1011716");
//		uhc.setTerminal_time(DateUtil.changeTime12ToFormat(new Date()));
////		System.out.println(uhc.getStu_id()+uhc.getSms_eventtype());
//		List<XcStuSmsVTBean> smslistbean = SmsOrderManager.getInstance().getXcSmsVTValue(uhc.getStu_id()+uhc.getSms_eventtype());
////		System.out.println(smslistbean);''
////		System.out.println(SmsOrderManager.getInstance().getXcSmsVTValue(uhc.getStu_id()+uhc.getSms_eventtype()));
//		if(smslistbean!=null){//如果缓存存在该学生信息
//			//短信消息内容
//			String message = XCVWBuildSQL.getInstance().isSuccessSendMsg(smslistbean, uhc, uhc.getSms_eventtype());
//			//将所需信息存入SendMessageReq
//			SendMessageReq sendMessageReq =new SendMessageReq();
//			sendMessageReq.setStu_id(uhc.getStu_id());
//			sendMessageReq.setEvent_type(uhc.getSms_eventtype());
//			sendMessageReq.setMessage(message);
//			sendMessageReq.setVehicle_vin(uhc.getTerminalId());
//			sendMessageReq.setRecordid(IdCreater.getUUid());
//			return sendMessageReq;
//			//调用短信发送接口
////			SendBuffer.getInstance().add(sendMessageReq);
//		}else{
//			log.info("<BuildSQL>该学生" + uhc.getStu_id() + "翔东短信业务未开通，不下发短信！");
//			return null;
//		}
//	}
}

package com.neusoft.clw.vncs.manage;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.CommandBean;
import com.neusoft.clw.info.bean.SendNextHolesReq;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.service.SendCmdService;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.tqcpt.service.OilWearSQL;
import com.neusoft.tqcpt.util.CDate;

public class SendCommandManager {

	private Logger log = LoggerFactory.getLogger(SendCommandManager.class);

	private static final String NAME = "<SendCommandManager>";
	
	private static final SendCommandManager sendCommandManager = new SendCommandManager();
	
	// 目前统一计算为前一天日期
	private String currentDateNoSplit;

	private SendCommandDAO sendCommandDAO;
	public static boolean sendflag=true;
	public SendCommandDAO getSendCommandDAO() {
		return sendCommandDAO;
	}
	public void setSendCommandDAO(SendCommandDAO sendCommandDAO) {
		this.sendCommandDAO = sendCommandDAO;
	}
	private List<CommandBean> commandList;
	private SendCommandManager() {	 
	}
	public static SendCommandManager getInstance() {
		return sendCommandManager;
	}
	private OilWearSQL oilWearSQL;
	
	public static boolean runflag = true;
	private TransactionDao transactionDao;
	
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public void init(int startDate,int endDate ) {
		//String systime = sendCommandDAO.getSysTime();
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[sendCommandInit]");
		 try {
			 boolean bCore = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, Integer.parseInt(Config.props.getProperty("core.active.time")));
			//判断是否是主核心
			if(bCore){
			 if(sendflag){
				 sendflag=false;//设置独占运行模式 
				/* 从数据库中查询出待发送命令信息，并保存入缓存中 */
					commandList = sendCommandDAO.getRecord();
					if (commandList != null && commandList.size() > 0) {
						log.info(NAME+"开始处理待发送命令");
						Iterator it = commandList.iterator();
//						Back back = BackMap.getInstance().get(Config.props.getProperty("backAddress"));	  
							
						while (it.hasNext()) {
							CommandBean combean = (CommandBean) it.next();
							log.info(NAME+"Send_type==="+combean.getSend_type());
							if(combean.getSend_type().equals("86")||combean.getSend_type().equals("87")||combean.getSend_type().equalsIgnoreCase("88")){
								SendNextHolesReq req = new SendNextHolesReq();
								req.setCommand(combean.getSend_command());
						        req.setStatusCode(SendReq.STATUS);
						        req.setSeq(InsideMsgUtils.getSeq());
						        req.setTerminal_id(combean.getVehicle_vin());
						        req.setPacket_content(combean.getPacket_content());
						        req.setMsg_id(combean.getMsg_id());
						        String url = SendCmdService.cycleSendCommand(req.getBytes());
						        if(url!=null&&!url.equals("")){
						        	log.info(NAME+"向"+url+"下发命令成功！");
						        	Constant.respMap.put(req.getSeq(),combean.getMsg_id());
						        	log.debug("Constant.respMap"+Constant.respMap);
						        	//更新数据库
						        	sendCommandDAO.UpdateOneTimes(combean.getMsg_id());
						        }else{
						        	log.info(NAME+"平台url未配置,下发命令失败");
						        }
							}else if(combean.getSend_type().equals("2009")){
								SendReq req = new SendReq();
								req.setCommand(combean.getSend_command());
						        req.setStatusCode(SendReq.STATUS);
						        req.setSeq(InsideMsgUtils.getSeq());
						        req.setTerminal_id(combean.getVehicle_vin());
						        String content=combean.getPacket_content();
						        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();						        
						        req.setPacket_content2(decoder.decodeBuffer(content));
						        req.setPacket_con_length(String.valueOf(req.getPacket_content2().length)); 
						        req.setMsgLength(String.valueOf(SendReq.MSGHEADERSIZE+req.TIMIDESIZE+req.PACKETLENG+req.getPacket_content2().length));
						        byte[] bytes = req.getBytesTc();
						        log.info(NAME+"透传输出："+new String(bytes));	
						        String url = SendCmdService.cycleSendCommand(req.getBytesTc());
						        if(url!=null&&!url.equals("")){
						        	log.info(NAME+"向"+url+"下发透传命令成功！");
						        	Constant.respMap.put(req.getSeq(),combean.getMsg_id());
						        	log.debug("Constant.respMap"+Constant.respMap);
						        	//更新数据库
						        	sendCommandDAO.UpdateOneTimes(combean.getMsg_id());
						        }else{
						        	log.info(NAME+"平台url未配置,下发命令失败");
						        }
							}else{
								SendReq req = new SendReq();
								req.setCommand(combean.getSend_command());
						        req.setStatusCode(SendReq.STATUS);
						        req.setSeq(InsideMsgUtils.getSeq());
						        req.setTerminal_id(combean.getVehicle_vin());
						        req.setPacket_content(combean.getPacket_content());
						        req.setPacket_con_length(String.valueOf(req.getPacket_content().getBytes("GBK").length));    
						        req.setMsgLength(String.valueOf(SendReq.MSGHEADERSIZE+req.TIMIDESIZE+req.PACKETLENG+req.getPacket_content().getBytes("GBK").length));
						        byte[] bytes = req.getBytes();
						        log.info(NAME+"输出："+new String(bytes));			       
	//							back.getCommunicateService().send(bytes);
						        String url = SendCmdService.cycleSendCommand(req.getBytes());
						        if(url!=null&&!url.equals("")){
						        	log.info(NAME+"向"+url+"下发命令成功！");
						        	Constant.respMap.put(req.getSeq(),combean.getMsg_id());
						        	log.debug("Constant.respMap"+Constant.respMap);
						        	//更新数据库
						        	sendCommandDAO.UpdateOneTimes(combean.getMsg_id());
						        }else{
						        	log.info(NAME+"平台url未配置,下发命令失败");
						        }
							}								
						}
					}
			     sendflag =true; 
				 }else{//独占结束
					 log.info(" ！！！！！！！！！！命令发送进行中！！！！！！！！！！！");
					 return;
				 } 		
			}else{
				log.info(NAME+"当前核心不为主核心，不进行命令下发");
			}
		 } catch (Exception e) {
			 sendflag =true; 
			 log.error(NAME+"SendCommandManager发送命令错误："+e.getMessage());		
			e.printStackTrace();
		}        
		 
         //-----------偷油/加油提醒
	     transactionDao = (TransactionDao) SpringBootStrap.getInstance().getBean("transactionDao"); 
	     boolean b = false;
	     try {		
				int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));			
				String reportServer = transactionDao.getReportServer();
				if (reportServer.equals(Constant.CORE_ID)) {// 是指定的报表计算服务器
					b = true;
				} else {
					// 是否为主核心，为了保证主核心业务的正常，核心数量大于1时主核心不参与报表计算。
					ActiveCoreService acs = ActiveCoreService.getInstance();
					b = acs.getMainCore(Constant.CORE_ID, coreActive);
					// 获取活跃核心数量
					int corenum = transactionDao.queryReportServer(coreActive);
					if (corenum == 1) {
						b = true;
					} else {
						int rcore = transactionDao.queryLiveReportS(coreActive,reportServer);
						if (rcore == 0 && !b) {// 非主核心设置自己为报表服务器
							transactionDao.setReportServer(Constant.CORE_ID);
							b = true;
						}
						b = false;
					}
				}
				
				if (!b) {
					log.info(NAME+"本机不是【通勤车】同步加油/偷油记录 服务器。");
					return;
				}				
				log.info(NAME + "【通勤车】同步加油/偷油记录  是统计服务器：" + runflag);					
				if (runflag) {
					runflag = false;
					// 设置独占运行模式			
					//currentDateNoSplit=CDate.getCurrentDateNoSplit();
					//传入当天日期
					currentDateNoSplit=CDate.getCurrentDate();
					oilWearSQL.synFromInfoToAlarmNew(currentDateNoSplit,startDate,endDate); 
				    log.info(NAME + "【通勤车】同步加油/偷油记录  结束");
					runflag = true;
				}
			}catch(Exception e) {		
				runflag = true;
				log.error(NAME+"【通勤车】同步加油/偷油记录出现异常："+e.getMessage());	
				e.printStackTrace();
			}
		
	}
	public OilWearSQL getOilWearSQL() {
		return oilWearSQL;
	}
	public void setOilWearSQL(OilWearSQL oilWearSQL) {
		this.oilWearSQL = oilWearSQL;
	}
	
	
	public static void main(String[] args){		
		System.out.println("----------->>:"+CDate.getCurrentDate());		
		System.out.println("----------->>:"+CDate.getNextDate(-1));		
		System.out.println("----------->>:"+CDate.getNextDateByDate("2013-12-01",-1));
	}
	 
}

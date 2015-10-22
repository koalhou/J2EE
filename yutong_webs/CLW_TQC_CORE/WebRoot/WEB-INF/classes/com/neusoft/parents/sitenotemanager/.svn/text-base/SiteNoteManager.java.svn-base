package com.neusoft.parents.sitenotemanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.bean.CacheVinTripList;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.eventBuffer.BasicObject;
import com.neusoft.parents.eventBuffer.EventBuffer;
import com.neusoft.parents.utils.CacheUtils;

public class SiteNoteManager //implements Runnable 
{
    private Logger log = LoggerFactory.getLogger(SiteNoteManager.class);
    private static final String NAME = "<SiteNoteManager>";
	public static boolean runflag = true;
	private TransactionDao transactionDao;
    private IParentsDAO parentsDAO;
    /**
     * 厂外推送开始    10秒进行一次
     * 步骤 1：先获取有今天行程的所有车辆；2：根据车辆轮询，查找该车辆的行程； 3：车辆和行程作为KEY，从缓存中读取对应的员工订购信息
     * 4：异步进行   根据员工的订购信息  获取那些订购信息已经满足推送条件，将满足推送条件的推送出去，不满足的依然保存到缓存中，以待下次轮询。
     */
    public void init()
    {
    	MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[SiteNoteManager]");
		String reportServer = null; 
		transactionDao = (TransactionDao) SpringBootStrap.getInstance().getBean("transactionDao");
		boolean b = false;
    	try
        {
    		int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
    		reportServer = transactionDao.getReportServer();    		
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
    		
    		log.info(NAME+"判断是本服务器是否为【员工版】消息推送服务器"+b);
			if (!b) {
				log.info(NAME+"本机不是【员工版】消息推送服务器。");
				return;
			}
			
			log.info(NAME+"本次站点提醒、推送开始！runflag:" + runflag);
			if (runflag) {			
				runflag = false; 
	        	List<VehicleReal> list = parentsDAO.getVehicleVin();     //获取当天有发车安排的车辆  VIN
	        	//根据获取到的VIN轮询
	        	for(VehicleReal vinList : list)
	        	{
	        		VehicleReal vr = CacheUtils.getVehicleRealCache(vinList.getVehicle_vin());  //获取该车的缓存  包括车辆的经纬度、车辆的行程
	
	                if (vr != null)
	                {
	                	if(vr.getTrip_id()==null)
	                 	{
	                 		//log.info("车辆不在行程中");
	                 	}
	                	else 
	                	{
	                		//根据车辆的VIN和行程，获得员工订购的提醒信息：VIN和行程是缓存的key
	                		CacheVinTripList dd = CacheUtils.getVinTripCache(vr.getVehicle_vin(),vr.getTrip_id()); 	                		
	                		if (dd != null && dd.getStuSiteNoteList().size() > 0)
	                        {
	                			//为了满足10秒轮询一次，选择异步    判断当前的订购的信息是否满足推送条件
	                			BasicObject boSendCommand=new BasicObject();
	    						boSendCommand.setObjectName("com.neusoft.parents.service.SendMsgCommand");										
	    						boSendCommand.setFunctionName("shouldSendMsg");
	    						List<Object> paramsSendCommand=new ArrayList<Object>();
	    						paramsSendCommand.add(dd);
	    						paramsSendCommand.add(vr);
	    						//paramsSendCommand.add(parentsDAO);
	    						boSendCommand.setParamsList(paramsSendCommand);
	    						//将BasicObject加入事件处理缓存
	    						EventBuffer.getInstance().add(boSendCommand);
	                        }
	                	}
	                }
	        	}
	          runflag = true;
		   }  
		 log.info(NAME+"本次站点提醒、推送结束！ runflag:" + runflag);
        }    	
	    catch (Exception e)
	    {   
	    	runflag = true;
	        log.error(NAME+"站点提醒、推送异常：", e);
	    }

    }

    public void setParentsDAO(IParentsDAO parentsDAO)
    {
        this.parentsDAO = parentsDAO;
    }
    
}
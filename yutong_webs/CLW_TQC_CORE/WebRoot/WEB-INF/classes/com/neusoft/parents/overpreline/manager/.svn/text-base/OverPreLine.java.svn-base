package com.neusoft.parents.overpreline.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.service.BuildSQL;
import com.neusoft.parents.algorithm.manager.AlgorithmUtil;
import com.neusoft.parents.bean.News;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.cachemanager.VehicleRealCacheManager;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.overpreline.dao.IOverPreLineDAO;
import com.neusoft.parents.pushmessage.domain.BaseNotification;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.pushmessage.manager.PushMsgBuffer;
import com.neusoft.parents.utils.CacheUtils;


public class OverPreLine {
	
	private Logger log = LoggerFactory.getLogger(OverPreLine.class);
	
	private IOverPreLineDAO overPreLineDao;
	
	private IParentsDAO parentsDAO;
	
	

	//判断偏航
	public synchronized void init(){
		
		log.info("<OverPreLine>判断偏航告警开始");
		
		List<VehicleReal> list = parentsDAO.getVehicleVin();     //获取当天有发车安排的车辆  VIN
		
		
    	//根据获取到的VIN轮询
    	for(VehicleReal vinList : list)
    	{
    		VehicleReal vr = CacheUtils.getVehicleInfoCache(vinList.getVehicle_vin());  //获取该车的缓存  包括车辆的经纬度、车辆的行程

            if (vr != null)
            {
            	if(vr.getTrip_id()==null)
             	{
             		//log.info("车辆不在行程中");
            		//车辆不在行程中，清空缓存中的偏航标识
            		vr.setFlag("0");
            		VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.LIU+vr.getVehicle_vin(), vr); 
             	}
            	else 
            	{
            		//车辆在行程中，开始判断是否偏航
            		List<NoticeBean> list1 = overPreLineDao.getPreLine(vr.getRoute_id());
            		int timespanInMinute = -1;

            		for(NoticeBean noTi:list1){
            			double disTemp = AlgorithmUtil.getDistance(Double.parseDouble(vr.getLongitude()), 
            					Double.parseDouble(vr.getLatitude()), 
            					Double.parseDouble(noTi.getLongitude()), 
            					Double.parseDouble(noTi.getLatitude()));
            			if(disTemp <= 5){
            				timespanInMinute = 1;
            				//不偏航
            				//timespanInMinute = 1;
            				if(vr.getFlag()!= null && "1".equals(vr.getFlag())){
            					
            					vr.setEnd_overtime(vr.getTerminal_time());
            					
            					//偏航结束处理
            					pushMsg("偏航告警结束",vr);
            					//此处需要将偏航告警添加到数据库中
            					//String uuid = "";
            					DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertOverPreLineAlarmSql(vr, IdCreater.getUUid()));
            					vr.setFlag("0");
            					vr.setStart_overtime(null);
            					vr.setEnd_overtime(null);
            					VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.LIU+vr.getVehicle_vin(), vr); 
            				}
            				break;
            			}else{
            				//不做处理
            			}
            		}
            		if(timespanInMinute < 0 && !"1".equals(vr.getFlag())){
            			vr.setFlag("1");
            			vr.setStart_overtime(vr.getTerminal_time());
    					VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.LIU+vr.getVehicle_vin(), vr);
            			//开始偏航处理
            			pushMsg("偏航告警开始",vr);
            		}
            		
            	}
            	
           }
    	}
		
    	log.info("<OverPreLine>判断偏航告警结束");
	}
	
	
	
	/**
	 * 假借新闻推送来验证偏航告警
	 */
	public void pushMsg(String name,VehicleReal vr){       
		PushMessageCls pushMessageBean = new PushMessageCls();
		//int id = parentsDAO.getPushCheckRecordID();    
		//pushMessageBean.setId(id);
		pushMessageBean.setRule_id("01");    //02厂内推送 03厂外推送 01新闻推送
		pushMessageBean.setUser_code("90067233");
		
		BaseNotification content = new BaseNotification();
		//content.setNews_type("1");
		//content.setNews_id(eachnewsList.getNews_id());
		content.setNews_title(name);
		content.setNews_summary(name);
		content.setNews_time(DateUtil.date2string(vr.getTerminal_time(),"yyyyMMddHHmmss"));
		/*try
		{
			String msgJson = JacksonUtils.toJson(content);
			pushMessageBean.setContent(msgJson);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
		pushMessageBean.setContent(content);
		// 将推送内容放入到推送缓冲池中
		PushMsgBuffer.getInstance().add(pushMessageBean);
		// 将推送信息存储到数据库中
		//DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertPushInfo(pushMessageBean));//liuja先删除掉，之后要加上。推送记录
		/*try
		{
			log.info("上行推送的json...."+JacksonUtils.toJson(pushMessageBean));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
	}
	
	
	
	

	public void setOverPreLineDao(IOverPreLineDAO overPreLineDao) {
		this.overPreLineDao = overPreLineDao;
	}

	public void setParentsDAO(IParentsDAO parentsDAO) {
		this.parentsDAO = parentsDAO;
	}
	
	
	

}

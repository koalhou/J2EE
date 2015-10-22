package com.neusoft.tqcpt.dao.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.info.bean.RealTimeRecord;
import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.tqcpt.bean.VehicleBean;
import com.neusoft.tqcpt.bean.VehiclwBeanList;
import com.neusoft.tqcpt.dao.TqcStatisticDao;
import com.neusoft.tqcpt.pojo.tqc.TqcStatistic;
import com.neusoft.tqcpt.service.TqcStatisticSQL;
import com.neusoft.tqcpt.util.CDate;

public class TqcStatisticDaoImpl extends AbstractDaoManager implements TqcStatisticDao {

	private static Logger logger = LoggerFactory.getLogger(TqcStatisticDaoImpl.class);    
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//目前统一计算为前一天日期
	//private static String currentDate=CDate.getNextDate(0);
	private String currentDate;
	
	// 获得TqcStatisticSQL对象
	private TqcStatisticSQL tqcStatisticSQL;

	public TqcStatisticSQL getTqcStatisticSQL() {
		return tqcStatisticSQL;
	}
	public void setTqcStatisticSQL(TqcStatisticSQL tqcStatisticSQL) {
		this.tqcStatisticSQL = tqcStatisticSQL;
	}
	
	/**
	 * 查询所有车辆，定时执行任务,
	 * 
	 * @param 
	 * @return  0：成功   1:失败
	 */
	public int selectAllVehicleStatistic() {
		int hour = Integer.parseInt(CDate.getHour());
		//add by liuja  当天如果是在三点之前统计，统计前一天，否则统计当天的发车统计
		if(hour <= 2){
			currentDate = CDate.getPreDate();//计算前一天
		}else{
			currentDate = CDate.getCurrentDate();//计算当天
		}
		
		Map<String,String> mapData=new HashMap<String,String>();
		String vinCode="";
		//先删除前一天记录  返回删除几条
		int j=tqcStatisticSQL.delSendPassengerStatByDay(currentDate);		
		//获得所有车辆列表
		List<Map<String,String>> vehicleList=tqcStatisticSQL.getVehicleListInfo(currentDate);
		for(int i=0;i<vehicleList.size();i++){
			mapData=(Map<String,String>)vehicleList.get(i);			
			vinCode=StringUtil.objToStr(mapData.get("VEHICLE_VIN"));
			logger.info("--车辆VIN-->:"+vinCode+" --执行日期-->:"+currentDate);			
			//执行前一天的统计
			//sendPassengerStatistic(vinCode,currentDate);
			newsendPassengerStatistic(vinCode,currentDate);//二期使用新的算法
		}		
		return 0;
	}
	
	
	/**
	 * 单个车辆执行发车与客流统计
	 * 
	 * @param strVin,sDate
	 * @return  
	 */
	public void sendPassengerStatistic(String strVin, String sDate) {
		//获得起点站时间集合
		List siteTimeData=tqcStatisticSQL.getSiteTimeRecord(strVin,sDate);
		Map siteMap=null;	
		//暂定为每辆车每天的  发动机打火时间 为00:00:01，熄火时间为晚上23:59:59		
		String dayStartTime=sDate+" 00:00:00";
		String dayEndTime=sDate+" 23:59:59";		
		//输入变量
		String vehicleVin="",vehicleNo,enterpriseId,organizationId,routeClass,driverName,
		       startTime="",endTime=""; //单个行程起点站时间、单个行程终点站时间		
		int routeId=0,tripId=0,loadNumber=0,
		    startSite=0,endSite=0,  //单个行程个起点站  、终点站
		    startOrder=0,endOrder=0,terminalTripid=0;//单个行程起点站顺序、终点站顺序
        float idleOil=0;//指定时间内  怠速油耗(L)
        float totalOil=0;//指定时间内  总油耗(L)
        float emptyMilage=0;//指定时间内  空驶里程(KM)
        float loadMileage=0;//指定时间内  载重里程(KM)
        float totalMileage=0;//指定时间内   总里程(KM)
		
		//循环起站点、末站点所有时间   
		for(int i=0;i<siteTimeData.size();i++){
			siteMap=(Map)siteTimeData.get(i);	
			logger.info("---------------第 "+(i+1)+" 个行程【开始】------------------");
			vehicleVin=StringUtil.objToStr(siteMap.get("VEHICLE_VIN"));
			vehicleNo=StringUtil.objToStr(siteMap.get("VEHICLE_LN"));
			driverName=StringUtil.objToStr(siteMap.get("DRIVER_NAME"));
			enterpriseId=StringUtil.objToStr(siteMap.get("ENTERPRISE_ID"));
			organizationId=StringUtil.objToStr(siteMap.get("ORGANIZATION_ID"));			
			
			routeId=StringUtil.objToZero(siteMap.get("ROUTE_ID"));
			routeClass=StringUtil.objToStr(siteMap.get("ROUTE_CLASS"));
			tripId=StringUtil.objToZero(siteMap.get("TRIP_ID"));
			terminalTripid=StringUtil.objToZero(siteMap.get("TERMINAL_TRIPID"));
			loadNumber=StringUtil.objToZero(siteMap.get("LOAD_NUMBER"));//载重人数
			
			startSite=StringUtil.objToZero(siteMap.get("START_SITE"));			
			endSite=StringUtil.objToZero(siteMap.get("END_SITE"));
			
			startOrder=StringUtil.objToZero(siteMap.get("START_ORDER"));
		    endOrder=StringUtil.objToZero(siteMap.get("END_ORDER"));
		    
		    startTime=StringUtil.objToStr(siteMap.get("START_TIME"));
		    endTime=StringUtil.objToStr(siteMap.get("END_TIME"));
			
			logger.info("vehicleVin----->:"+vehicleVin);
			logger.info("vehicleNo------>:"+vehicleNo);
			logger.info("driver_name------>:"+driverName);
			logger.info("enterpriseId--->:"+enterpriseId);
			logger.info("organizationId->:"+organizationId);
			logger.info("routeId-------->:"+routeId);
			logger.info("routeClass----->:"+routeClass);
			logger.info("tripId--------->:"+tripId);
			logger.info("terminalTripid->:"+terminalTripid);
			logger.info("loadNumber----->:"+loadNumber);			
			logger.info("起站点顺序------->:"+startOrder+" 终点站顺序----->:"+endOrder);
			logger.info("起站点到站时间---->:"+startTime+ " 终点站到站时间-->:"+endTime);
			logger.info("起站点---------->:"+startSite+ " 终点站-------->:"+endSite);
			
			/* 			 
			       【计算逻辑】
			        一、如果是一天中的第一条:
				   1、根据dayStart [发动机打火时间 为00:00:01] 时间到第一个行程的终点站出站时间，计算出总里程、总油耗；
				   2、用当前行程起点站出站时间和终点站出站时间 ，计算出载重里程。
				   3、空驶里程=总里程-载重里程
				   
			        二、否则
			       1、根据上一行程的终点站时间 到 当前行程的终点站时间，计算出总里程、总油耗；
			       2、用当前行程起点站点时间和终点站时间 ，计算出载重里程。
				   3、空驶里程=总里程-载重里程
				   
			        三、如果是一天中最后一条行程
			       1、根据上一行程的终点站时间 到 当前行程的终点站时间，计算出总里程A、总油耗A；
			       2、根据当前行程的终点站时间 到 dayEnd [熄火时间为晚上23:59:59]，计算出总里程B、总油耗B；			       
			       3、用当前行程起点站点时间和终点站时间 ，计算出载重里程。			       
			       4、总里程=总里程A+总里程B;   总油耗=总油耗A+总油耗B			       
				   5、空驶里程=总里程-载重里程 
			  */	
		   		
			//结果集
			Map mapResult=null;
			if(startSite>0 && endSite >0){		    
				//第一个行程  【对应 一】 
				if(i==0){
					logger.info("---------此行程为当天【第一条】行程---------");
					//获得总里程、总油耗                                             //点火时间   
					mapResult=mergeMileageOil(vehicleVin,dayStartTime,endTime);			
					totalMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					totalOil=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
					  logger.info("计算总里程、总油耗时间 startTime-->:"+dayStartTime+" endTime-->:"+endTime);
					  logger.info("totalMileage-->:"+totalMileage+" totalOil-->:"+totalOil);
					mapResult.clear();
					  logger.info("计算载重里程 startTime-->:"+startTime+" endTime-->:"+endTime);
					//获得载重里程
					mapResult=mergeMileageOil(vehicleVin,startTime,endTime);
					loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					  logger.info("loadMileage-->:"+loadMileage);
					//获得空驶里程
					emptyMilage=totalMileage-loadMileage;
				}
				// 最后一条行程  【对应三】
				else if(i==siteTimeData.size()-1){
					logger.info("---------此行程为当天【最后一条】行程-------");
					//----获得上一个行程的终点站时间，如果终点站时间为空，则使用起点站时间					
					String upSiteStartTime=((Map)siteTimeData.get(i-1)).get("END_TIME")==null?((Map)siteTimeData.get(i-1)).get("START_TIME").toString():((Map)siteTimeData.get(i-1)).get("END_TIME").toString();
					
					logger.info("计算总里程A、总油耗A时间 startTime-->:"+upSiteStartTime+" endTime-->:"+endTime);
					//计算总里程A、总油耗A                                   //点火时间   
					mapResult=mergeMileageOil(vehicleVin,upSiteStartTime,endTime);
					float totalMileageA=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					float totalOilA=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
					logger.info("计算总里程A、总油耗A totalMileageA-->:"+totalMileageA+" totalOilA-->:"+totalOilA);
					
					//因为是最后一条  计算总里程B、总油耗B 
					mapResult.clear();	
					logger.info("最后一条 计算总里程B、总油耗B 时间 endTime-->:"+endTime+" dayEndTime-->:"+dayEndTime);
					mapResult=mergeMileageOil(vehicleVin,endTime,dayEndTime);
					float totalMileageB=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					float totalOilB=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
					logger.info("最后一条  计算总里程B、总油耗B totalMileageB-->:"+totalMileageB+" totalOilB-->:"+totalOilB);
					
					mapResult.clear();
					//获得当前行程载重里程
					logger.info("当前行程载重里程 时间 startTime-->:"+startTime+" endTime-->:"+endTime);
					mapResult=mergeMileageOil(vehicleVin,startTime,endTime);	
					loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					
					//计算总里程、总油耗
					totalMileage=totalMileageA+totalMileageB;
					totalOil=totalOilA+totalOilB;				
					//获得空驶里程
					emptyMilage=totalMileage-loadMileage;	
					
				}//中间的行程数据  【对应二】
				else {
					logger.info("-----------此行程为当天【中间一条】行程------------");
					//----获得上一个行程的终点站时间，如果终点站时间为空，则使用起点站时间					
					String upSiteStartTime=((Map)siteTimeData.get(i-1)).get("END_TIME")==null?((Map)siteTimeData.get(i-1)).get("START_TIME").toString():((Map)siteTimeData.get(i-1)).get("END_TIME").toString();						
					//获得总里程、总油耗                                             //点火时间   
					logger.info("总里程、总油耗 时间 startTime-->:"+upSiteStartTime+" endTime-->:"+endTime);
					mapResult=mergeMileageOil(vehicleVin,upSiteStartTime,endTime);
					totalMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					totalOil=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
					logger.info("总里程、总油耗 totalMileage-->:"+totalMileage+" totalOil-->:"+totalOil);
					
					mapResult.clear();
					//获得载重里程
					logger.info("载重里程 时间 startTime-->:"+startTime+" endTime-->:"+endTime);
					mapResult=mergeMileageOil(vehicleVin,startTime,endTime);	
					loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					//获得空驶里程
					emptyMilage=totalMileage-loadMileage;						
				} 
	
				logger.info("-------totalMileage-->:"+totalMileage);
				logger.info("-------loadMileage--->:"+loadMileage);				
				logger.info("-------emptyMilage--->:"+emptyMilage);
				logger.info("-------totalOil------>:"+totalOil);		
				//赋值对象
				TqcStatistic tqcStatistic=new TqcStatistic();
				tqcStatistic.setVinCode(vehicleVin);
			    tqcStatistic.setVehicleNo(vehicleNo);
			    tqcStatistic.setDriverName(driverName);
				tqcStatistic.setRouteId(routeId+"");
				tqcStatistic.setRouteClass(routeClass);
				tqcStatistic.setTripId(tripId+"");
				tqcStatistic.setLoadNumber(loadNumber+"");
				tqcStatistic.setStartTime(startTime);
				tqcStatistic.setEndTime(endTime);
				tqcStatistic.setIdleOil(idleOil+"");
				tqcStatistic.setTotalOil(totalOil+"");
				tqcStatistic.setEmptyMileage(emptyMilage+"");
				tqcStatistic.setLoadMileage(loadMileage+"");
				tqcStatistic.setTotalMileage(totalMileage+"");
				tqcStatistic.setEnterpriseId(enterpriseId);
				tqcStatistic.setOrganizationId(organizationId);	
				tqcStatistic.setTerminalTripId(terminalTripid+"");				
				//插入到数据库
				tqcStatisticSQL.addTqcStatisticData(tqcStatistic);
		  }else{
			  logger.info("-------------->:起点站 或 终点站 为 0，不满足计算条件！");
		  }
	      logger.info("---------------第 "+(i+1)+" 个行程【结束】------------------");
		}
	}
	
	private static int int_totle = 0;
	/**
	 * 单个车辆执行发车与客流统计
	 * 
	 * @param strVin,sDate
	 * @return  
	 */
	public void newsendPassengerStatistic(String strVin, String sDate) {
		//1、获得进出站集合
		List<VehicleBean> siteList = tqcStatisticSQL.getInoutSiteRecord(strVin,sDate);
		//2、根据trip_id ，如果一直相同，且terminal_tripid也是相同的，表示在跑的是同一个行程 否则表示换了新的行程
		//如果行程中只有一个站点，不进行统计
		List<VehiclwBeanList> tripList = new LinkedList<VehiclwBeanList>();
		
		String dayStartTime=sDate+" 00:00:00";
		String dayEndTime=sDate+" 23:59:59";	
		
		float idleOil=0;//指定时间内  怠速油耗(L)
        float totalOil=0;//指定时间内  总油耗(L)
        float emptyMilage=0;//指定时间内  空驶里程(KM)
        float loadMileage=0;//指定时间内  载重里程(KM)
        float totalMileage=0;//指定时间内   总里程(KM)
		
		VehicleBean next_site = null ;
		VehicleBean first_site = null ;
		VehicleBean end_site = null ;
		int i = 0;
		int j = 0;//生成几条记录
		for(VehicleBean vb:siteList){
			
			next_site = vb;
			if(first_site == null){
				first_site = vb;
			}
			if(next_site != null && first_site != null && next_site.getTrip_id() != null && next_site.getTrip_id().equals(first_site.getTrip_id())){
				//1、同一个行程，terminal_tripid也相同
				if(next_site.getTerminal_tripid().equals(first_site.getTerminal_tripid())){
					if(!next_site.getSite_id().equals(first_site.getSite_id())){
						i++;
						end_site = next_site;
					}
				}
				//2、同一个行程，但是terminal_tripid不相同
				else{
					if(i > 1){  //
						i = 1;
						//此处需要生成一条发车与客流统计 起点站first_site 终点站next_site;
						//***************
						j++;
						VehiclwBeanList tripLine = new VehiclwBeanList() ;
						tripLine.setFirst_site(first_site);
						tripLine.setEnd_site(end_site);
						tripList.add(tripLine);
					}
					first_site = next_site;
				}
				
			}else{
				if(i > 1){
					i = 1;
					//此处需要生成一条发车与客流统计 起点站first_site 终点站next_site;
					//***************
					j++;
					VehiclwBeanList tripLine = new VehiclwBeanList() ;
					tripLine.setFirst_site(first_site);
					tripLine.setEnd_site(end_site);
					tripList.add(tripLine);
					//tripList.add(null);

				}
				first_site = next_site;
			}
		}
		if(i > 1){
			i = 1;
			//此处需要生成一条发车与客流统计 起点站first_site 终点站next_site;
			//***************
			j++;
			VehiclwBeanList tripLine = new VehiclwBeanList() ;
			tripLine.setFirst_site(first_site);
			tripLine.setEnd_site(end_site);
			tripList.add(tripLine);
			//tripList.add(null);

		}
		
		
		
		Map mapResult=null;
		if(tripList != null && tripList.size()>0){
			for(int m=0; m < tripList.size();m++){
				
				String vin = tripList.get(m).getFirst_site().getVehicle_vin();
				//载重人数（需求要求）每站上车人数的累加
				String num = tqcStatisticSQL.getLoadNum(tripList.get(m).getFirst_site().getTerminal_time(),tripList.get(m).getEnd_site().getTerminal_time(),strVin);
				//String vehicleNo = tripList.get(m).getFirst_site().getVehicle_ln();
				tripList.get(m).getFirst_site().setLoad_number(num);
				logger.info("vehicleVin----->:"+tripList.get(m).getFirst_site().getVehicle_vin());
				logger.info("vehicleln------>:"+tripList.get(m).getFirst_site().getVehicle_ln());
				logger.info("driver_name------>:"+tripList.get(m).getFirst_site().getDriver_name());
				logger.info("enterpriseId--->:"+tripList.get(m).getFirst_site().getEnterprise_id());
				logger.info("organizationId->:"+tripList.get(m).getFirst_site().getOrganization_id());
				logger.info("routeId-------->:"+tripList.get(m).getFirst_site().getRoute_id());
				logger.info("routeClass----->:"+tripList.get(m).getFirst_site().getRoute_class());
				logger.info("tripId--------->:"+tripList.get(m).getFirst_site().getTrip_id());
				logger.info("terminalTripid->:"+tripList.get(m).getFirst_site().getTerminal_tripid());
				logger.info("loadNumber----->:"+tripList.get(m).getFirst_site().getLoad_number());
				//logger.info("loadNumber----->:"+num);
				//logger.info("起站点顺序------->:"+startOrder+" 终点站顺序----->:"+endOrder);
				logger.info("起站点到站时间---->:"+tripList.get(m).getFirst_site().getTerminal_time()+ " 终点站到站时间-->:"+tripList.get(m).getEnd_site().getTerminal_time());
				logger.info("起站点---------->:"+tripList.get(m).getFirst_site().getSite_id()+ " 终点站-------->:"+tripList.get(m).getEnd_site().getSite_id());
				if(j==1){
					//就一个行程 直接全天的
					
					logger.info("-----------此行程为当天【中间一条】行程------------");
					
					
					String start = tripList.get(m).getFirst_site().getTerminal_time();
					String end = tripList.get(m).getEnd_site().getTerminal_time();
					
					//----获得上一个行程的终点站时间，如果终点站时间为空，则使用起点站时间					
					//String upSiteStartTime=((Map)siteTimeData.get(i-1)).get("END_TIME")==null?((Map)siteTimeData.get(i-1)).get("START_TIME").toString():((Map)siteTimeData.get(i-1)).get("END_TIME").toString();						
					//获得总里程、总油耗                                             //点火时间   
					logger.info("总里程、总油耗 时间 startTime-->:"+dayStartTime+" endTime-->:"+dayEndTime);
					mapResult=mergeMileageOil(vin,dayStartTime,dayEndTime);
					totalMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					totalOil=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
					logger.info("总里程、总油耗 totalMileage-->:"+totalMileage+" totalOil-->:"+totalOil);
					
					mapResult.clear();
					//获得载重里程
					logger.info("载重里程 时间 startTime-->:"+start+" endTime-->:"+end);
					mapResult=mergeMileageOil(vin,start,end);	
					loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
					//获得空驶里程
					emptyMilage=totalMileage-loadMileage;
					

				}else{
					if(m==0){
						//第一个行程
						logger.info("---------此行程为当天【第一条】行程---------");
						
						
						String start = tripList.get(m).getFirst_site().getTerminal_time();
						String end = tripList.get(m).getEnd_site().getTerminal_time();
						
						//获得总里程、总油耗                                             //点火时间   
						mapResult=mergeMileageOil(vin,dayStartTime,end);			
						totalMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						totalOil=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
						logger.info("计算总里程、总油耗时间 startTime-->:"+tripList.get(m).getFirst_site().getTerminal_time()+" endTime-->:"+tripList.get(m).getEnd_site().getTerminal_time());
						logger.info("totalMileage-->:"+totalMileage+" totalOil-->:"+totalOil);
						mapResult.clear();
						
						logger.info("计算载重里程 startTime-->:"+tripList.get(m).getFirst_site().getTerminal_time()+" endTime-->:"+tripList.get(m).getEnd_site().getTerminal_time());
						//获得载重里程
						mapResult=mergeMileageOil(vin,start,end);
						loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						logger.info("loadMileage-->:"+loadMileage);
						//获得空驶里程
						emptyMilage=totalMileage-loadMileage;
					}else if(m == tripList.size()-1){
						//最后一个行程
						logger.info("---------此行程为当天【最后一条】行程-------");
						
						
						String start = tripList.get(m-1).getEnd_site().getTerminal_time();
						String end = tripList.get(m).getEnd_site().getTerminal_time();
						
						//----获得上一个行程的终点站时间，如果终点站时间为空，则使用起点站时间					

						logger.info("计算总里程A、总油耗A时间 startTime-->:"+tripList.get(m).getFirst_site().getTerminal_time()+" endTime-->:"+tripList.get(m).getEnd_site().getTerminal_time());
						//计算总里程A、总油耗A                                   //点火时间   
						mapResult=mergeMileageOil(vin,start,end);
						float totalMileageA=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						float totalOilA=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
						logger.info("计算总里程A、总油耗A totalMileageA-->:"+totalMileageA+" totalOilA-->:"+totalOilA);
						
						//因为是最后一条  计算总里程B、总油耗B 
						mapResult.clear();	
						logger.info("最后一条 计算总里程B、总油耗B 时间 endTime-->:"+tripList.get(m).getEnd_site().getTerminal_time()+" dayEndTime-->:"+dayEndTime);
						mapResult=mergeMileageOil(vin,end,dayEndTime);
						float totalMileageB=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						float totalOilB=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
						logger.info("最后一条  计算总里程B、总油耗B totalMileageB-->:"+totalMileageB+" totalOilB-->:"+totalOilB);
						
						mapResult.clear();
						//获得当前行程载重里程
						logger.info("当前行程载重里程 时间 startTime-->:"+tripList.get(m).getFirst_site().getTerminal_time()+" endTime-->:"+tripList.get(m).getEnd_site().getTerminal_time());
						mapResult=mergeMileageOil(vin,tripList.get(m).getFirst_site().getTerminal_time(),end);	
						loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						
						//计算总里程、总油耗
						totalMileage=totalMileageA+totalMileageB;
						totalOil=totalOilA+totalOilB;				
						//获得空驶里程
						emptyMilage=totalMileage-loadMileage;
					}else{
						//中间的行程
						logger.info("-----------此行程为当天【中间一条】行程------------");
						
						
						String start = tripList.get(m-1).getEnd_site().getTerminal_time();
						String end = tripList.get(m).getEnd_site().getTerminal_time();
						
						//----获得上一个行程的终点站时间，如果终点站时间为空，则使用起点站时间					
						//String upSiteStartTime=((Map)siteTimeData.get(i-1)).get("END_TIME")==null?((Map)siteTimeData.get(i-1)).get("START_TIME").toString():((Map)siteTimeData.get(i-1)).get("END_TIME").toString();						
						//获得总里程、总油耗                                             //点火时间   
						logger.info("总里程、总油耗 时间 startTime-->:"+start+" endTime-->:"+end);
						mapResult=mergeMileageOil(vin,start,end);
						totalMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						totalOil=StringUtil.objToMaxZero(mapResult.get("TOTAL_OIL"));
						logger.info("总里程、总油耗 totalMileage-->:"+totalMileage+" totalOil-->:"+totalOil);
						
						mapResult.clear();
						//获得载重里程
						logger.info("载重里程 时间 startTime-->:"+tripList.get(m).getFirst_site().getTerminal_time()+" endTime-->:"+end);
						mapResult=mergeMileageOil(vin,tripList.get(m).getFirst_site().getTerminal_time(),end);	
						loadMileage=StringUtil.objToMaxZero(mapResult.get("MILEAGE"));
						//获得空驶里程
						emptyMilage=totalMileage-loadMileage;
					}
				}
					
					
					
					
				logger.info("-------totalMileage-->:"+totalMileage);
				logger.info("-------loadMileage--->:"+loadMileage);				
				logger.info("-------emptyMilage--->:"+emptyMilage);
				logger.info("-------totalOil------>:"+totalOil);		
				//赋值对象
				TqcStatistic tqcStatistic=new TqcStatistic();
				tqcStatistic.setVinCode(vin);
			    tqcStatistic.setVehicleNo(tripList.get(m).getFirst_site().getVehicle_ln());
			    if(null == tripList.get(m).getFirst_site().getDriver_name() || "".equals(tripList.get(m).getFirst_site().getDriver_name())){
			    	String drivename = tqcStatisticSQL.getDriveName(sDate,strVin,tripList.get(m).getFirst_site().getTrip_id());
			    	tqcStatistic.setDriverName(drivename);
			    }else {
			    	tqcStatistic.setDriverName(tripList.get(m).getFirst_site().getDriver_name());
				}
				tqcStatistic.setRouteId(tripList.get(m).getFirst_site().getRoute_id()+"");
				tqcStatistic.setRouteClass(tripList.get(m).getFirst_site().getRoute_class());
				tqcStatistic.setTripId(tripList.get(m).getFirst_site().getTrip_id()+"");
				tqcStatistic.setLoadNumber(tripList.get(m).getFirst_site().getLoad_number()+"");
				tqcStatistic.setStartTime(tripList.get(m).getFirst_site().getTerminal_time());
				tqcStatistic.setEndTime(tripList.get(m).getEnd_site().getTerminal_time());
				tqcStatistic.setIdleOil(idleOil+"");
				tqcStatistic.setTotalOil(totalOil+"");
				tqcStatistic.setEmptyMileage(emptyMilage+"");
				tqcStatistic.setLoadMileage(loadMileage+"");
				tqcStatistic.setTotalMileage(totalMileage+"");
				tqcStatistic.setEnterpriseId(tripList.get(m).getFirst_site().getEnterprise_id());
				tqcStatistic.setOrganizationId(tripList.get(m).getFirst_site().getOrganization_id());	
				tqcStatistic.setTerminalTripId(tripList.get(m).getFirst_site().getTerminal_tripid()+"");	
				int_totle++;
				logger.info("-------总数量为：------>:"+int_totle);
				
				//插入到数据库
				tqcStatisticSQL.addTqcStatisticData(tqcStatistic);
				
			}
		}

	}
	
	
	/**
	 * 合并里程、油耗
	 * 
	 * @param strVin,startDate,endDate
	 * @return  Map
	 */
	public Map mergeMileageOil(String strVin, String startDate,String endDate){
		//从终端记录表里面获得里程 CLW_YW_TERMINAL_RECORD_T
		Map mileageMap=analyseTerminalRecord(strVin, startDate,endDate);	
		//从防偷油记录表里面获得 油耗  ZSPT_FTLY_INFO
		Map oilMap=analyseZsptFtlyRecord(strVin, startDate,endDate);		
		Map mapData=new HashMap();
		mapData.put("MILEAGE", mileageMap.get("MILEAGE"));
		mapData.put("TOTAL_OIL", oilMap.get("TOTAL_OIL"));
		return mapData;
	}
	
	/**
	 * 根据车辆vin、开始时间、结束时间  分析终端记录表数据，返回指定时间段的 油耗、里程    [CLW_YW_TERMINAL_RECORD_T]
	 * 
	 * @param strVin,startDate,endDate
	 * @return  Map
	 */
	public  Map analyseTerminalRecord(String strVin, String startDate,String endDate){
		Map mapResult = new HashMap();
		try {
			List<RealTimeRecord> rows = tqcStatisticSQL.getTerminalRecord(strVin,startDate,endDate);			
			// 总里程、总油耗等
			float COUNT_MILEAGE_START = 0;// 总行车里程开始
			float COUNT_MILEAGE = 0;// 总行车里程
			float MILEAGE = 0;// 行车里程( 指定时间段差值 )
			float COUNT_OIL_TOTAL_START = 0; // 总燃油消耗开始
			float COUNT_OIL_TOTAL = 0; // 总燃油消耗
			float OIL = 0; // 燃油消耗( 指定时间段差值)
			String OIL_INSTANT = "0";
			DecimalFormat def = new DecimalFormat(".#####");
			RealTimeRecord cSPerv = null, CsNonce = null, perv = null, nonce = null;
			float diff = 0;
			// reportTimeSpace 90 流水表数据间隔，判断是否连续			
			//long reportTimeSpace = Long.valueOf(Config.props.getProperty("reportTimeSpace"));
			long reportTimeSpace = Long.valueOf(90);
			boolean continuous;
			long reltime = 10;// 单条记录的持续时间
			int po = 0; // 连续的时候赋值为0，断开开的时候加1，如果大于等于2则说明前一点为为单点

			if (null == rows || 0 == rows.size()) {
				// 如果没有实时记录将不计算
				return mapResult;
			}
			if (rows != null & rows.size() > 0) {
				// 先赋值总里程、总油耗、及瞬时油耗
				if (rows.size() == 1) {// 只有一条记录时
					if (rows.get(0).getMILEAGE() != null
							&& !rows.get(0).getMILEAGE().equals("FFFF")) {
						COUNT_MILEAGE = Float.parseFloat(rows.get(0)
								.getMILEAGE());
					} else {
						COUNT_MILEAGE = 0;
					}
					if (rows.get(0).getOIL_TOTAL() != null
							&& !rows.get(0).getOIL_TOTAL().equals("FFFF")) {
						COUNT_OIL_TOTAL = Float.parseFloat(rows.get(0)
								.getOIL_TOTAL());
					} else {
						COUNT_OIL_TOTAL = 0;
					}
					if (rows.get(0).getOIL_INSTANT().equals("FFFF")) {
						OIL_INSTANT = "0";
					} else {
						OIL_INSTANT = rows.get(0).getOIL_INSTANT();
					}
				}
				// 再赋值总行车里程开始
				if (rows.get(0).getMILEAGE() != null
						&& !rows.get(0).getMILEAGE().equals("FFFF")) {
					COUNT_MILEAGE_START = Float.parseFloat(rows.get(0)
							.getMILEAGE());
				}
				// 总燃油消耗开始
				if (rows.get(0).getOIL_TOTAL() != null
						&& !rows.get(0).getOIL_TOTAL().equals("FFFF")) {
					COUNT_OIL_TOTAL_START = Float.parseFloat(rows.get(0)
							.getOIL_TOTAL());
				}
				perv = rows.get(0);
				// 如果是多条记录,继续计算 ---------开始
				for (int i = 1; i < rows.size(); i++) {
					// 总里程，总油耗
					if (rows.get(0).getMILEAGE() != null
							&& !rows.get(i).getMILEAGE().equals("FFFF")) {
						if (COUNT_MILEAGE_START == 0) {
							//只记录第一条里程开始值，
							COUNT_MILEAGE_START = Float.parseFloat(rows.get(i)
									.getMILEAGE());
						} else {
							//如果不是第一条，将总里程赋值，目的是计算指定时间段的值，COUNT_MILEAGE-COUNT_MILEAGE_START
							COUNT_MILEAGE = Float.parseFloat(rows.get(i)
									.getMILEAGE());
						}
					}
					if (rows.get(0).getOIL_TOTAL() != null
							&& !rows.get(i).getOIL_TOTAL().equals("FFFF")) {
						if (COUNT_OIL_TOTAL_START == 0) {
							COUNT_OIL_TOTAL_START = Float.parseFloat(rows
									.get(i).getOIL_TOTAL());
						} else {
							COUNT_OIL_TOTAL = Float.parseFloat(rows.get(i)
									.getOIL_TOTAL());
						}
					}
					if (rows.get(i).getOIL_INSTANT() != null
							&& !rows.get(i).getOIL_INSTANT().equals("FFFF")) {
						OIL_INSTANT = rows.get(i).getOIL_INSTANT();
					}
					nonce = rows.get(i);

					diff = (df.parse(nonce.getTERMINAL_TIME()).getTime() - df
							.parse(perv.getTERMINAL_TIME()).getTime()) / 1000;

					if (diff >= 0 && diff < reportTimeSpace) {
						continuous = true;// 两条记录连续
					} else {
						continuous = false;// 两条记录不连续
					}
					if (diff == 0) {
						diff = reltime;// 如果为零付给时间。
					}
					// 计算各种持续时间
					if (continuous) {// 记录连续的时候累计时间
						po = 0;

					} else {// 不连续
						po = po + 1;
						if (po >= 2) {// 前一点为孤立点
						}
					}
					perv = nonce;
				}
				// --------------------- 结束
				// 总里程，总油耗 计算及入库
				MILEAGE = COUNT_MILEAGE - COUNT_MILEAGE_START;
				OIL = COUNT_OIL_TOTAL - COUNT_OIL_TOTAL_START;
				// 增加对负数的修正2011-08-18
				if (MILEAGE < 0) {
					MILEAGE = 0;
				}
				if (OIL < 0) {
					OIL = 0;
				}
                //赋值总里程
				mapResult.put("MILEAGE", def.format(MILEAGE));
				//赋值总油耗   暂时先注释  通勤车目前用的是 流量传感器   
				//mapResult.put("TOTAL_OIL", def.format(OIL));	
				
				logger.info("--范围里程-->:"+def.format(MILEAGE));
				logger.info("--范围油耗-->:"+def.format(OIL));
			}
		} catch (Exception e) {			
			e.printStackTrace();
			logger.debug("实时数据异常" + e.toString());			
		}
		return mapResult;
	}
	
	
	/**
	 * 根据车辆vin、开始时间、结束时间  分析终端记录表数据，返回指定时间段的 油耗、里程   [ZSPT_FTLY_INFO]
	 * 计算逻辑：实际耗油量=总油量+加油油量-偷油油量    即：OIL=COUNT_OIL_TOTAL_START - COUNT_OIL_TOTAL+addOilMass-reduceOilMass
	 * @param strVin,startDate,endDate
	 * @return  Map
	 */
	public Map analyseZsptFtlyRecord(String strVin, String startDate,String endDate) {
		Map mapResult = new HashMap();
		try {
			List<Map> dataList = tqcStatisticSQL.getFtlyInfoRecord(strVin,startDate,endDate);
			float COUNT_OIL_TOTAL_START = 0; // 总燃油消耗开始
			float COUNT_OIL_TOTAL = 0; // 总燃油消耗
			float OIL = 0; // 燃油消耗( 指定时间段差值)	
			float addOilMass=0; //定义加油量
			float reduceOilMass=0; //定义偷油量
			DecimalFormat def = new DecimalFormat(".#####");	
			String oil_total_0="",oil_total_i="",oilBoxState="";		
			if (null == dataList || 0 == dataList.size()) {
				// 如果没有实时记录将不计算
				return mapResult;
			}
			if (dataList != null & dataList.size() > 0) {				
				oil_total_0=dataList.get(0).get("OILBOX_MASS").toString();				
				// 先赋值总里程、总油耗、及瞬时油耗
				if (dataList.size() == 1) {
					// 只有一条记录时					
					if (oil_total_0 != null && !oil_total_0.equals("FFFF")) {
						COUNT_OIL_TOTAL = Float.parseFloat(oil_total_0);
					} else {
						COUNT_OIL_TOTAL = 0;
					}					
					//增加对加油偷油的判断  油箱油位异常标志  00:油位正常; 01:偷油告警; 10:加油告警; 11:保留
					oilBoxState=dataList.get(0).get("OILBOX_STATE").toString();	
					//如果是偷油 ，赋值偷油值
					if(oilBoxState.endsWith("001")){
						reduceOilMass=Float.parseFloat(dataList.get(0).get("ADD_OILL").toString());
					}else if(oilBoxState.endsWith("010")){
						addOilMass=Float.parseFloat(dataList.get(0).get("ADD_OILL").toString());
					}
				}				
				// 总燃油消耗开始
				if (oil_total_0 != null && !oil_total_0.equals("FFFF")) {
					COUNT_OIL_TOTAL_START = Float.parseFloat(oil_total_0);
				}			
				// 如果是多条记录,继续计算 ---------开始
				for (int i = 1; i < dataList.size(); i++) {					
					oil_total_0=dataList.get(0).get("OILBOX_MASS").toString();
					oil_total_i=dataList.get(i).get("OILBOX_MASS").toString();
					
					if (oil_total_0 != null && !oil_total_i.equals("FFFF")) {
						if (COUNT_OIL_TOTAL_START == 0) {
							//如果开始油耗为空的话，再重新赋值开始油耗
							COUNT_OIL_TOTAL_START = Float.parseFloat(oil_total_i);
						} else {
							//如果不是则只赋值总油耗，目的是得到最后一条数据
							COUNT_OIL_TOTAL = Float.parseFloat(oil_total_i);
						}
					}					
					//增加对加油偷油的判断  油箱油位异常标志  00:油位正常; 01:偷油告警; 10:加油告警; 11:保留
					oilBoxState=dataList.get(i).get("OILBOX_STATE").toString();	
					//如果是偷油 ，赋值偷油值 ，将值累加
					if(oilBoxState.endsWith("001")){
						reduceOilMass+=Float.parseFloat(dataList.get(i).get("ADD_OILL").toString());
					}else if(oilBoxState.endsWith("010")){
						addOilMass+=Float.parseFloat(dataList.get(i).get("ADD_OILL").toString());
					}
				}
				// --------------------- 结束			
				//OIL = COUNT_OIL_TOTAL - COUNT_OIL_TOTAL_START;//ECU上报的油耗是随时间  逐步增加的
				OIL = COUNT_OIL_TOTAL_START - COUNT_OIL_TOTAL+addOilMass-reduceOilMass;	//油量监控是随时间逐步减少的
				if (OIL < 0) {
					OIL = 0;
				}              
				//赋值总油耗   暂时先注释  通勤车目前用的是 流量传感器   
				mapResult.put("TOTAL_OIL", def.format(OIL));
				logger.info("--开始油耗-->:"+COUNT_OIL_TOTAL_START+" --结束油耗-->:"+COUNT_OIL_TOTAL);
				logger.info("--加油油量-->:"+addOilMass+" --偷油油耗-->:"+reduceOilMass);
				logger.info("--范围油耗-->:"+def.format(OIL));
			}
		} catch (Exception e) {			
			e.printStackTrace();
			logger.debug("实时数据异常" + e.toString());			
		}
		return mapResult;
	}
	
	public static void main(String[] args){
	   SimpleDateFormat dfDay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dfDay.format(new Date()));
		
		System.out.println(CDate.getCurrentDate());
	}

}

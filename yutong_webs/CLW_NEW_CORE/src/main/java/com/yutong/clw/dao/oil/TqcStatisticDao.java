package com.yutong.clw.dao.oil;

import java.util.Map;

public interface TqcStatisticDao { 
	
	/**
	 * 查询所有车辆，定时执行任务,
	 * 
	 * @param 
	 * @return  0：成功   1:失败
	 */
	public int selectAllVehicleStatistic();	
	
	
	/**
	 * 根据单个车辆、当天匹配每个行程的起站时间、结束时间
	 * 
	 * @param strVin,sDate
	 * @return  
	 */
	public void sendPassengerStatistic(String strVin, String sDate);
	
	
	/**
	 * 根据车辆vin、开始时间、结束时间  分析终端记录表数据，返回指定时间段的 油耗、里程    [CLW_YW_TERMINAL_RECORD_T]
	 * 
	 * @param strVin,startDate,endDate
	 * @return  
	 */
	public  Map analyseTerminalRecord(String strVin, String startDate,String endDate);
	
	
	/**
	 * 根据车辆vin、开始时间、结束时间  分析终端记录表数据，返回指定时间段的 油耗、里程   [ZSPT_FTLY_INFO]
	 * 
	 * @param strVin,startDate,endDate
	 * @return  
	 */
	public  Map analyseZsptFtlyRecord(String strVin, String startDate,String endDate);
	
	
}

package com.neusoft.tqcpt.dao;

public interface TqcOilWearDao {
   
	/**
	 * 查询所有车辆，定时执行任务,
	 * 
	 * @param   dateSplit(2013-09-07), dateNoSplit(20130907)
	 * @return  0：成功   1:失败
	 */
	public void selectAllVehicleOilWear(String dateSplit,String dateNoSplit);	
	
	
	/**
	 * 根据单个车辆、当天时间，计算里程
	 * 
	 * @param  strVin,sDate
	 * @return void
	 */
	public String executeOilWearStatistic(String strVin, String sDate);
	
	
	
	
	
}

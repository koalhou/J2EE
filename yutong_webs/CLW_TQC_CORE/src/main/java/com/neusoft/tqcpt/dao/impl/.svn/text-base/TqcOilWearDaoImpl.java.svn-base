package com.neusoft.tqcpt.dao.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.tqcpt.dao.TqcOilWearDao;
import com.neusoft.tqcpt.service.OilWearSQL;
import com.neusoft.tqcpt.util.CDate;

public class TqcOilWearDaoImpl extends AbstractDaoManager implements
		TqcOilWearDao {

	private static Logger logger = LoggerFactory.getLogger(TqcOilWearDaoImpl.class);
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 目前统一计算为前一天日期
	private String currentDate;
	// 定义一个标准常量 作为加油,骤减的标准
	private static float standardOil = 10;

	private OilWearSQL oilWearSQL;

	public OilWearSQL getOilWearSQL() {
		return oilWearSQL;
	}

	public void setOilWearSQL(OilWearSQL oilWearSQL) {
		this.oilWearSQL = oilWearSQL;
	}

	/**
	 * 查询所有车辆，定时执行任务,
	 * 
	 * @param   dateSplit(2013-09-07), dateNoSplit(20130907)
	 * @return  0：成功   1:失败
	 */
	public void selectAllVehicleOilWear(String dateSplit,String dateNoSplit){	
		// 先删除前一天记录 返回删除几条
	    int j=oilWearSQL.delZsptOilAnalysInfoByDay(dateSplit);
		Map<String, String> mapData = new HashMap<String, String>();
		String vinCode = "",vinLn="";		
		try {
			// 获得所有车辆列表
			List<Map<String, String>> vehicleList = oilWearSQL.getVehicleListInfo();
			for (int i = 0; i < vehicleList.size(); i++) {
				mapData = (Map<String, String>) vehicleList.get(i);
				vinCode = StringUtil.objToStr(mapData.get("VEHICLE_VIN"));
				vinLn=StringUtil.objToStr(mapData.get("VEHICLE_LN"));
				logger.info("--车牌号-->:" + vinLn +"--车辆VIN-->:" + vinCode + " --执行日期-->:"+ dateSplit);	
				//计算当天日油耗
				String dayOilSum=executeOilWearStatistic(vinCode, dateNoSplit);
				//插入数据库
				//DBBuffer.getInstance().add(OilWearSQL.getInstance().buildInsertOilAnalysInfoSql(vinCode,dayOilSum,dateNoSplit));	
				oilWearSQL.buildInsertOilAnalysInfoSql(vinCode,dayOilSum,dateNoSplit);
			}
			//执行成功，插入日志
			oilWearSQL.executeInsertTqcProcLog("0");			
		} catch (Exception e) {			
			e.printStackTrace();
			oilWearSQL.executeInsertTqcProcLog("1");
			logger.debug("定时执行油耗统计任务 发生异常：" + e.toString());			
		}
	}

	
	
	// ------------数据列表变量
	private float addOilSum = 0, stealOilSum = 0, oilSum = 0;
	
	/**
	 * 油量计算中，过滤错误值算法
	 * @param j
	 * @param oilWearList
	 * @param mapOilWearLast
	 * @param mapOilWearCurrent
	 * @return
	 */
	 public int oilDateCalculate(int j,List<Map<String, String>> oilWearList,Map<String, String> mapOilWearLast,Map<String, String> mapOilWearCurrent){
		 int count = 0;
		 // ------------临时比对变量
		 float compOil = 0, absCompOil = 0;
		 float addOil = 0, stealOil = 0, oilBoxMassCurrent = 0, oilBoxMassLast = 0;
		 String flag = "";    //加油偷油标识 010：加油 001偷油
		 // 上条油量
		 oilBoxMassLast = Float.parseFloat(mapOilWearLast.get("OILBOX_MASS"));
		 // 当条油量
		 oilBoxMassCurrent = Float.parseFloat(mapOilWearCurrent.get("OILBOX_MASS"));
		 
		 flag = mapOilWearCurrent.get("OILBOX_STATE");
	//		 if("010".equals(flag)){
	//			 //log.info("上一条" + df.format(oilBoxMassLast) + " 当前油量->:"+ df.format(oilBoxMassCurrent) );	
	//		 }
	//		 else if("001".equals(flag)){
	//			 //log.info("上一条" + df.format(oilBoxMassLast) + " 当前油量->:"+ df.format(oilBoxMassCurrent) );
	//		 }
		 // 上一条减去下一条
		 compOil = oilBoxMassLast - oilBoxMassCurrent;
		 // 求绝对值
		 absCompOil = Math.abs(compOil);
	
		 // 【加油累计】 上一条:100L 当前：200L
		 if ((absCompOil >= standardOil) && (compOil < 0) ) {    //加油事件存在漏报的情况，所以不能简单的根据加油标识来认定
			 addOilSum += absCompOil;
		 }
		 // 【偷油累计】 上一条:100L 当前:50L
		 else if ((absCompOil >= standardOil) && (compOil > 0) && "001".equals(flag)) {
			 stealOilSum += absCompOil;
		}
	//		else{ //将错误的值也计算进内，因为会将该错误值计算两次，两次会相互抵消掉错误值带来的影响
	//			oilSum += compOil;
	//		}
		// 【正常消耗累计】 上一条：100L 当前:97L
		else if ((compOil > 0) && "000".equals(flag)) {//(absCompOil < standardOil) && 
			oilSum += absCompOil;
			//oilSum += compOil;
		}
		else{
			//过滤上报错误的值
			count++;
			if(j+1 < oilWearList.size()){
				mapOilWearCurrent = (Map<String, String>) oilWearList.get(j+1);
				count += oilDateCalculate(j+1,oilWearList,mapOilWearLast,mapOilWearCurrent);
			}
		}
			
		return count;
	 }
	
	
	/**
	 * 执行油耗分析统计
	 * 
	 * @param strVin,sDate
	 * @return String 
	 */
	public String executeOilWearStatistic(String vinCode, String sDate){
		//将初始值全部清空
		addOilSum = 0;
		stealOilSum = 0;
		oilSum = 0;
		DecimalFormat df = new DecimalFormat(".###");
		// 查询 oilWearList
		List<Map<String, String>> oilWearList = oilWearSQL.getVehicleZsptFtlyInfo(vinCode, sDate);
		
		// 当前数据集合
		Map<String, String> mapOilWearCurrent = new HashMap<String, String>();
		// 上一条数据集合
		Map<String, String> mapOilWearLast = new HashMap<String, String>();
		
		for (int i = 1; i < oilWearList.size(); i++) {
			//上一条记录
		 	mapOilWearLast = (Map<String, String>) oilWearList.get(i - 1);
			// 当前记录
			mapOilWearCurrent = (Map<String, String>) oilWearList.get(i);

			i += oilDateCalculate(i,oilWearList,mapOilWearLast,mapOilWearCurrent);
		}
		
		logger.info("-总加油量->:" + df.format(addOilSum) + " -总偷油量->:"+ df.format(stealOilSum) + " -总耗油量->:" + df.format(oilSum));	
		return df.format(oilSum);
	}
	/*public String executeOilWearStatistic(String vinCode, String sDate){
		// ------------定义总和变量
		float addOilSum = 0, stealOilSum = 0, oilSum = 0;
		// ------------数据列表变量
		float addOil = 0, stealOil = 0, oilBoxMassCurrent = 0, oilBoxMassLast = 0;
		// ------------临时比对变量
		float compOil = 0, absCompOil = 0;
		DecimalFormat df = new DecimalFormat(".###");
		// 查询 oilWearList
		List<Map<String, String>> oilWearList = oilWearSQL.getVehicleZsptFtlyInfo(vinCode, sDate);
		// 当前数据集合
		Map<String, String> mapOilWearCurrent = new HashMap<String, String>();
		// 上一条数据集合
		Map<String, String> mapOilWearLast = new HashMap<String, String>();
		// 从第一条开始执行
		for (int i = 1; i < oilWearList.size(); i++) {
			// 上一条记录
			mapOilWearLast = (Map<String, String>) oilWearList.get(i - 1);
			// 当前记录
			mapOilWearCurrent = (Map<String, String>) oilWearList.get(i);
			// 上条油量
			oilBoxMassLast = Float.parseFloat(mapOilWearLast.get("OILBOX_MASS"));
			// 当条油量
			oilBoxMassCurrent = Float.parseFloat(mapOilWearCurrent.get("OILBOX_MASS"));
			// 上一条减去下一条
			compOil = oilBoxMassLast - oilBoxMassCurrent;
			// 求绝对值
			absCompOil = Math.abs(compOil);

			// 【加油累计】 上一条:100L 当前：200L
			if ((absCompOil >= standardOil) && (compOil < 0)) {
				addOilSum += absCompOil;
			}
			// 【偷油累计】 上一条:100L 当前:50L
			else if ((absCompOil >= standardOil) && (compOil > 0)) {
				stealOilSum += absCompOil;
			}
			// 【正常消耗累计】 上一条：100L 当前:97L
			else if ((compOil > 0)) {//(absCompOil < standardOil) && 
				oilSum += absCompOil;
			}

			// 【异常增加累计】 上一条：100L 当前:102L
			
//			 else if((absCompOil < standardOil) && (compOil < 0)){			
			 
			// logger.info(oilBoxMassCurrent + " " + oilBoxMassLast);
			// logger.info(mapOilWearCurrent.get("REPORT_TIME") + " "+mapOilWearLast.get("REPORT_TIME"));
		}
		logger.info("-总加油量->:" + df.format(addOilSum) + " -总偷油量->:"+ df.format(stealOilSum) + " -总耗油量->:" + df.format(oilSum));	
		return df.format(oilSum);
	}*/
	
}

/*******************************************************************************
 * @FileName: helper.java 2013-7-12 下午4:13:15
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.login.helper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.yw.ub.login.ds.LoginStaticsDataInfo;

/**
 * @author zhangzhia 2013-7-12 下午4:13:15
 * 
 */
public class Helper {

	public static Map getJson(List pageList, Boolean isTotal) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();

		Integer allLoginCount = null;
		Integer allActiveCount = null;
		Double allActivePercent = null;
		String allActivePercentstr = null;
		for (int i = 0; i < pageList.size(); i++) {

			LoginStaticsDataInfo data = (LoginStaticsDataInfo) pageList.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("cell",
					new Object[] { data.getSystemname(), data.getEpCount(),
							data.getActiveCount(), data.getActivePercent(), data.getActivePercentStr() });
			mapList.add(cellMap);
		}

		mapData.put("rows", mapList);

		return mapData;
	}

	/**
	 * 
	 * @author zhangzhia 2013-7-15 下午4:38:34
	 * 
	 * @param val
	 * @param fix
	 *            小数点后确定位数
	 * @return
	 */
	public static double fix(double val, int fix) {
		if (val == 0)
			return val;
		int p = (int) Math.pow(10, fix);
		return (double) ((int) (val * p)) / p;
	}

	/**
	 * 输出百分比值字符串
	 *@author zhangzhia 2013-7-31 下午5:19:18
	 *
	 * @param value  双精度值
	 * @return  值百分比字符串
	 */
	public static String getPercent(Double value) {
		if (value != null && value > 0) {
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(1);
			df.setMinimumFractionDigits(1);
			if(1 == value)
			{
				return "100%";
			}
			else
			{
				return df.format(value*100) + "%";
			}
		} 
		else {
			return "--";
		}
	}

	public static String getSetDate(Date dt, int dayNum) {
	   StringBuffer str = new StringBuffer();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Calendar lastDate = Calendar.getInstance();
       lastDate.add(Calendar.DATE, dayNum);
       str.append(sdf.format(lastDate.getTime()));
       return str.toString();
	}
}

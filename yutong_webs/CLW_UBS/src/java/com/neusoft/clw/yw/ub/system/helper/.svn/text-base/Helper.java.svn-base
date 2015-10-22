/*******************************************************************************
 * @FileName: helper.java 2013-7-12 下午4:13:15
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.system.helper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.yw.ub.system.ds.StaticsDataInfo;
import com.neusoft.clw.yw.ub.system.ds.VisitStaticsDataInfo;

/**
 * @author zhangzhia 2013-7-12 下午4:13:15
 *
 */
public class Helper {

    public static Map getPagination(List pageList) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        for (int i = 0; i < pageList.size(); i++) {

        	StaticsDataInfo data = (StaticsDataInfo) pageList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("cell", new Object[] {
            		data.getSystemname(),
                    data.getVisitCount(),
                    data.getAllVisitCount(),
            		data.getVisitPercent(),
                    data.getVisitEpCount(), 
                    data.getAllVisitEpCount(),
            		data.getVisitEpPercent(),
                    data.getVisitUserCount(),
                    data.getAllVisitUserCount(),
            		data.getVisitUserPercent()});

            mapList.add(cellMap);
        }

        mapData.put("rows", mapList);

        return mapData;
    }
    
    public static Map getPaginationPage(List pageList, int totalCount, int pageIndex,
            int rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        for (int i = 0; i < pageList.size(); i++) {

        	StaticsDataInfo data = (StaticsDataInfo) pageList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("cell", new Object[] {
            		data.getSystemname(),
                    data.getVisitCount(),
                    data.getVisitEpCount(), 
                    data.getVisitUserCount() });

            mapList.add(cellMap);

//        	private String systemname = "";
//        	private Integer visitCount = null;
//        	private Integer allVisitCount = null;
//        	private Float visitPercent = null;
//        	private Integer visitEpCount = null;
//        	private Integer allVisitEpCount = null;
//        	private Float visitEpPercent = null;
//        	private Integer visitUserCount = null;
//        	private Integer allVisitUserCount = null;
//        	private Float visitUserPercent = null;
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    
    public static Map getZoneJson(List list) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        int j = 0;
        for (int i = 0; i < list.size(); i++) {

        	VisitStaticsDataInfo data = (VisitStaticsDataInfo) list.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("cell", new Object[] {
            		data.getSystemname(),
                    data.getVisitCount(),
                    data.getVisitEpCount(), 
                    data.getVisitUserCount()});

            mapList.add(cellMap);
        }

        mapData.put("rows", mapList);

        return mapData;
    }
}

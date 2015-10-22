package com.neusoft.clw.common.util.servlet.ds;

import java.util.List;

/**
 * 内存数据bean
 * @author JinPeng
 */
public class MemData {
    /** 核心应用列表 **/
    private static List < Appcfg > appcfgList;

    /** 数据同步地址列表 **/
    private static List<Appcfg> realNoticList;
    
    public static List < Appcfg > getAppcfgList() {
        return appcfgList;
    }

    public static void setAppcfgList(List < Appcfg > appcfgList) {
        MemData.appcfgList = appcfgList;
    }

    public static List < Appcfg > getRealNoticList() {
        return realNoticList;
    }

    public static void setRealNoticList(List < Appcfg > realNoticList) {
        MemData.realNoticList = realNoticList;
    }
}

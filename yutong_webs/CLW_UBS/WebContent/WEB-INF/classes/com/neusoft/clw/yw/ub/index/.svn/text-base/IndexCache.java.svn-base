package com.neusoft.clw.yw.ub.index;

import java.util.concurrent.ConcurrentHashMap;

public class IndexCache
{
    
    public static String ENT_AND_BUS="ENT_&_BUS";//车辆数和开通企业
    public static String CA_RANK_LASTMONTH="CA_Rank_LastMonth";//企业活跃度的上月排名
    public static String CA_INFO_LASTMONTH="CA_Info_LastMonth";//企业活跃度的上月汇总
    public static String CA_INFO_LAST6MONTH="CA_Info_Last6Month";//企业活跃度的上6个月汇总
    public static String SA_INFO_LASTMONTH="SA_Info_LastMonth";//服务活跃度的上月汇总
    public static String SA_RANK_LASTMONTH="SA_Rank_LastMonth";//服务活跃度的上月汇总
    public static String PA_INFO_LASTMONTH="PA_Info_LastMonth";//推送活跃度的上月汇总
    ConcurrentHashMap<String,Object> cache;
    
    
    public IndexCache()
    {
        cache = new ConcurrentHashMap<String,Object>();
    }


    public ConcurrentHashMap<String, Object> getCache()
    {
        return cache;
    }


    
}

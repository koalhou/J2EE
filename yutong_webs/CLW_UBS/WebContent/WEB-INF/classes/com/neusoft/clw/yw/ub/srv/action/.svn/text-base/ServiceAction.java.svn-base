package com.neusoft.clw.yw.ub.srv.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.custom.ds.Meta;
import com.neusoft.clw.yw.ub.srv.CommonUtil;
import com.neusoft.clw.yw.ub.srv.ComparatorMeta;
import com.neusoft.clw.yw.ub.srv.ds.ServiceInfo;
import com.neusoft.clw.yw.ub.srv.ds.Summary;
import com.opensymphony.xwork2.ActionContext;

/**
 * 服务活跃度
 */
public class ServiceAction extends PaginationAction
{
    protected Logger logger = Logger.getLogger(ServiceAction.class);

    private transient Service service;

    private String plat="web";//web app  all
    private int serviceID=0;//0:全部服务   其他为服务的ID 
    private int vis=1;//vis-访问次数   visEntCnt-访问企业数  openEntCnt-开通企业数    visActivity-活跃度
    private int visEntCnt=0;//
    private int openEntCnt=0;//
    private int visActivity=0;//
    private int areaOrProvince=0;//0为大区  1为省份
    private  List<ServiceInfo> serviceList;//服务列表

    private String dateType;//查询时间类型  day week  month 
    private String startDay;//查询开始时间 yyyy-MM-dd
    private String endDay;//查询结束时间
    
    private int rp=20;
    private int page=1;
    private String sortname;
    private String sortorder;

    private Map<String, Object> returnMap=new HashMap<String, Object>();
    
    private boolean checkInput() 
    {
        if(!StringUtils.hasText(this.plat)){
            returnMap.put("error", "请选择平台");
            return false;
        }
        Date start=DateUtil.parseStringToDate(this.startDay, CommonUtil.YYYY_MM_DD);
        Date end=DateUtil.parseStringToDate(this.endDay, CommonUtil.YYYY_MM_DD);
        Map<String,Object> paraMap=new HashMap<String,Object>();
        
        if(start==null||end==null){
            returnMap.put("error", "查询时间为空或日期格式错误");
            return false;
        }
        if(start.compareTo(end)>0){
            returnMap.put("error", "开始时间不能大于结束时间");
            return false;
        }
        
        return true;
    }

    /**
     * 函数介绍：获取查询基本信息 参数： 返回值：
     * @throws BusinessException 
     */
    public String init() throws BusinessException
    {
        ActionContext
        .getContext()
        .getSession()
        .put(Constants.CURRENT_LOCATION, getText("ub.service.location"));
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：获取服务(ID、name、type)列表
      * 参数：
      * 返回值：
     */
    public String getSrvList() throws BusinessException
    {
        Map<String, Object> paraMap=new HashMap<String, Object>();
        if("web".equals(this.plat)){
            paraMap.put("srvType", "1");
        }else if("app".equals(this.plat)){
            paraMap.put("srvType", "2");
        }
        serviceList = service.getObjects("ServiceActivity.getServiceList",paraMap);
        this.returnMap.put("srvList", serviceList);
        return SUCCESS;
    }

    /**
     * 
      * 函数介绍：获取所有服务的汇总信息
      * 参数：
      * 返回值：
     */
    public String getSummary() throws BusinessException{
        if(!checkInput()){
            return SUCCESS;
        }
            Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("plat", plat);
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            paraMap.put("id", serviceID);
            Summary summary = (Summary)service.getObject("ServiceActivity.getSummary", paraMap);
            Integer entOpen = (Integer)service.getObject("ServiceActivity.getOpenEnt", paraMap);
            Integer entVis = (Integer)service.getObject("ServiceActivity.getVisEnt", paraMap);
            summary.setOpenEntCnt(entOpen==null?0:entOpen);
            summary.setVisEntCnt(entVis==null?0:entVis);
            
            getServiceQuality(summary);
            this.returnMap.put("summary", summary);
        return SUCCESS;
    }
    
    /**
     * 
      * 函数介绍：查询所有服务的信息
      * 参数：
      * 返回值：
     */
    @SuppressWarnings("unchecked")
    private void getServiceQuality(Summary summary) throws BusinessException{
        int i=0;
        int j=0;
        Map<String, Object> paraMap=new HashMap<String, Object>();
        if("web".equals(this.plat)){
            paraMap.put("srvType", "1");
        }else if("app".equals(this.plat)){
            paraMap.put("srvType", "2");
        }
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        serviceList = service.getObjects("ServiceActivity.getServiceList", paraMap);
        int a=serviceList.size();
        for (ServiceInfo serviceinfo : serviceList)
        {
            paraMap.put("id", serviceinfo.getId());
            paraMap.put("plat", plat);
            
            Date start = DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
            Date end = DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
            long days= (end.getTime()-serviceinfo.getReleaseTime().getTime())/(1000 * 60 * 60 * 24)+1;
            logger.debug("ID："+serviceinfo.getId());
            logger.debug("已发布天数："+days);
            if(days>=100){
                //发布以来总访问天次超过100
                int vis100=(Integer)service.getObject("ServiceActivity.get100Vis", paraMap);
                logger.debug("已发布以来的访问总天次："+vis100);
                if(vis100>=100){
                    i++;
                    days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)+1;
                    logger.debug("选择时间区间（天数）："+days);
                    if (days >=90 )
                    {
                        List<Integer> vis90=(List<Integer>)service.getObjects("ServiceActivity.get90Vis", paraMap);
                        logger.debug("选择时间区间的访问总天次："+vis90 +"-"+vis90.size());
                        if(vis90.size()>=3){
                            //连续3个月的满足都满足50%
                            for(int t=1;t<vis90.size()-1;t++){
                                if(vis90.get(t-1)>50&&vis90.get(t)>50&&vis90.get(t+1)>50){
                                    j++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            
        }
        double basePercent=(new BigDecimal(i)).divide((new BigDecimal(a)),3,BigDecimal.ROUND_HALF_UP).movePointRight(2).doubleValue();
        double qualityPercent=(new BigDecimal(j)).divide((new BigDecimal(a)),3,BigDecimal.ROUND_HALF_UP).movePointRight(2).doubleValue();
        
        summary.setEntBaseReqStr(""+i+"/"+basePercent);
        summary.setEntQualityReqStr(""+j+"/"+qualityPercent);
    }
    
    /**
     * 
      * 函数介绍：单个服务的汇总
      * 参数：
      * 返回值：
     */
    public String getOneSummary() throws BusinessException{
        if(StringUtils.hasText(plat)&&StringUtils.hasText(startDay)&&StringUtils.hasText(endDay)){
            Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("plat", plat);
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            paraMap.put("id", serviceID);
            Summary summary = (Summary)service.getObject("ServiceActivity.getSummary", paraMap);
            Integer entOpen = (Integer)service.getObject("ServiceActivity.getOpenEnt", paraMap);
            Integer entVis = (Integer)service.getObject("ServiceActivity.getVisEnt", paraMap);
            summary.setOpenEntCnt(entOpen==null?0:entOpen);
            summary.setVisEntCnt(entVis==null?0:entVis);
            
            getSingleServiceQuality(summary);
            this.returnMap.put("summary", summary);
        }else{
            this.returnMap.put("error", "平台、时间不能为空");
        }
        return SUCCESS;
    }
    
    /**
     * 
      * 函数介绍：单个服务的质量结果
      * 参数：
      * 返回值：
     */
    private void getSingleServiceQuality(Summary summary)
            throws BusinessException
    {
        summary.setEntBaseReqStr("否");
        summary.setEntQualityReqStr("否");
        Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("srvID", this.serviceID);
        ServiceInfo serviceinfo = (ServiceInfo) service.getObject(
                "ServiceActivity.getServiceList", paraMap);
        if(serviceinfo==null){
            throw new BusinessException("没有该服务ID");
        }

        paraMap.put("id", serviceinfo.getId());
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("plat", plat);
        summary.setReportDate(DateUtil.formatDateToString(serviceinfo.getReleaseTime(), "yyyy/MM/dd"));
        
        Date start = DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
        Date end = DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
        long days = (end.getTime() - serviceinfo.getReleaseTime().getTime())
                / (1000 * 60 * 60 * 24) + 1;
        logger.debug("ID：" + serviceinfo.getId());
        logger.debug("已发布天数：" + days);
        if (days >= 100)
        {
            // 发布以来总访问天次超过100
            int vis100 = (Integer) service.getObject(
                    "ServiceActivity.get100Vis", paraMap);
            logger.debug("已发布以来的访问总天次：" + vis100);
            if (vis100 >= 100)
            {
                summary.setEntBaseReqStr("是");
                days = (end.getTime() - start.getTime())
                        / (1000 * 60 * 60 * 24) + 1;
                logger.debug("选择时间区间（天数）：" + days);
                if (days > 90)
                {
                    List<Integer> vis90 = (List<Integer>) service.getObjects(
                            "ServiceActivity.get90Vis", paraMap);
                    logger.debug("选择时间区间的访问总天次：" + vis90 + "-" + vis90.size());
                    if (vis90.size() >= 3)
                    {
                        // 连续3个月的满足都满足50%
                        for (int t = 1; t < vis90.size() - 1; t++)
                        {
                            if (vis90.get(t - 1) > 50 && vis90.get(t) > 50
                                    && vis90.get(t + 1) > 50)
                            {
                                summary.setEntQualityReqStr("是");
                                break;
                            }
                        }
                    }
                }
            }
        }

    }
    
    
    /**
     * 
      * 函数介绍：根据指标获取数据
      * 参数：vis-访问次数   visEntCnt-访问企业数  openEntCnt-开通企业数    visActivity-活跃度
      * 返回值： 折线图
     * @throws BusinessException 
     */
    public String getChartByIndex() throws BusinessException{
        if(!checkInput()){
            return SUCCESS;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", plat);
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("id", this.serviceID);
        
        int s=vis+visActivity+visEntCnt+openEntCnt;
        if(s>2){
            this.returnMap.put("error", "选择指标超过2个了！");
            return SUCCESS;
        }
        
        List<Summary> list=getData(paraMap);
        
        //计算占比
        int visSum=0;
        int visEnpSum=0;
        int openEnpSum=0;
        for (Summary summary : list)
        {
            visSum+=summary.getVis();
            visEnpSum+=summary.getVisEntCnt();
            openEnpSum+=summary.getOpenEntCnt();
        }
       
        for (Summary summary : list)
        {
            summary.setVisPercent(CommonUtil.getPercent(summary.getVis(),visSum)+"%");
            summary.setVisEntCntPercent(CommonUtil.getPercent(summary.getVisEntCnt(),visEnpSum)+"%");
            summary.setOpenEntCntPercent(CommonUtil.getPercent(summary.getOpenEntCnt(),openEnpSum)+"%");
        }
        
        this.returnMap.put("lines", list);
        
        return SUCCESS;
    }

    /**
     * 
      * 函数介绍：获取服务访问情况，区分日月周
      * 参数：
      * 返回值：生成表格
     * @throws BusinessException 
     */
    public String getVisList() throws BusinessException
    {
        if(!checkInput()){
            return SUCCESS;
        }
        
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", plat);
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("id", this.serviceID);
        
        List<Summary> visList=new ArrayList<Summary>();
        Integer total=0;
        if("day".equals(this.dateType)){
            paraMap.put("rowStart", (page-1)*rp);
            paraMap.put("rowEnd", page*rp);
            paraMap.put("sortOrder", "desc");
            total = (Integer)service.getObject("ServiceActivity.getDayVisListCnt", paraMap);
            visList = (List<Summary>)service.getObjects("ServiceActivity.getDayVisList", paraMap);
        }else if("week".equals(this.dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(monday, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("周前："+visList);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            visList.addAll((List<Summary>)service.getObjects("ServiceActivity.getWeekVisList", paraMap));
            logger.debug("周前+周："+visList);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(monday, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("周所有："+visList);   
        }else{
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(lastDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("月前："+visList); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            visList.addAll((List<Summary>)service.getObjects("ServiceActivity.getMonthVisList", paraMap));
            logger.debug("月前+月："+visList); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(firstDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("月所有："+visList); 
        }
        this.returnMap.put("page", page);
        List<Object> grid=new ArrayList<Object>();
        
        if("day".equals(dateType)){
            this.returnMap.put("total", total);// 从数据库获取总记录数
            for (Summary sum : visList)
            {
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                        new Object[] 
                                {
                        sum.getReportDate(),
                        sum.getVis(),
                        sum.getVisEntCnt(),
                        sum.getOpenEntCnt(),
                        sum.getVisActivity()
                                });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
                
        }else{
            this.returnMap.put("total", visList.size());// 从数据库获取总记录数
            int max=(page*rp>visList.size()?visList.size():page*rp);
            for (int i=max;i>0;i--)
            {
                    Summary sum=visList.get(i-1);
                    Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                    
                    cellMap.put("id", sum.getReportDate());
                    cellMap.put("cell", 
                        new Object[] 
                        {
                            sum.getReportDate(),
                            sum.getVis(),
                            sum.getVisEntCnt(),
                            sum.getOpenEntCnt(),
                            sum.getVisActivity()
                        });
                    logger.debug(cellMap);
                    grid.add(cellMap);
                
            }
        }
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }
    private List<Summary> getData(Map<String, Object> paraMap) throws BusinessException
    {
        List<Summary> visList=new ArrayList<Summary>();
        if("day".equals(this.dateType)){
            paraMap.put("sortOrder", "asc");
            visList = (List<Summary>)service.getObjects("ServiceActivity.getDayVisList", paraMap);
        }else if("week".equals(this.dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(monday, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("周前："+visList);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            visList.addAll((List<Summary>)service.getObjects("ServiceActivity.getWeekVisList", paraMap));
            logger.debug("周前+周："+visList);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(monday, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("周所有："+visList);   
        }else{
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(lastDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("月前："+visList); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            visList.addAll((List<Summary>)service.getObjects("ServiceActivity.getMonthVisList", paraMap));
            logger.debug("月前+月："+visList); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Summary  tmp = (Summary)service.getObject("ServiceActivity.getDayVisSum", paraMap);
                if(tmp!=null){
                    tmp.setReportDate(DateUtil.formatDateToString(DateUtil.parseStringToDate(firstDay, CommonUtil.YYYY_MM_DD), "yyyy/MM/dd")+"-"
                            +DateUtil.formatDateToString(DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD),"yyyy/MM/dd"));
                    getVisAndOpenEntRange(paraMap, tmp);
                    visList.add(tmp);
                }
            }
            logger.debug("月所有："+visList); 
        }
        return visList;
    }

    private void getVisAndOpenEntRange(Map<String, Object> paraMap,
            Summary tmp) throws BusinessException
    {
        Summary summary=(Summary)service.getObject("ServiceActivity.getServiceVisGrid", paraMap);
        tmp.setOpenEntCnt(summary.getOpenEntCnt());
        tmp.setVisEntCnt(summary.getVisEntCnt());
        tmp.setVisActivity(CommonUtil.getPercent(summary.getVisEntCnt(), summary.getOpenEntCnt()));
    }

    /**
     * 
      * 函数介绍：获取各个服务的访次  
      * 参数：
      * 返回值：柱状图
     */
    public String getServiceVisChart() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("plat", plat);
        List<ServiceInfo> serviceVisList=(List<ServiceInfo>)service.getObjects("ServiceActivity.getServiceVisChart", paraMap);
        logger.debug(serviceVisList);
        this.returnMap.put("bars", serviceVisList);
       
        for (ServiceInfo info : serviceVisList)
        {
            paraMap.put("id", info.getId());
            paraMap.put("releaseTime", info.getReleaseTime());
            
            Date start = DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
            Date end = DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
            long days= (end.getTime()-info.getReleaseTime().getTime())/(1000 * 60 * 60 * 24);
            
            //计算发布时长
            info.setReleaseRange("");
            double years=Math.ceil(days/365);
            if(years>0){
                info.setReleaseRange((int)years+"年");
            }
            if(days-years*365>0){
                info.setReleaseRange( info.getReleaseRange()+(int)(days-years*365)+"天");
            }
            //计算基本和质量要求
            if(days>=100){
                //查询
                int vis100=(Integer)service.getObject("ServiceActivity.get100Vis", paraMap);
                if(vis100>=100){
                    info.setEntBaseReq(true);
                    days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
                    if (days >90 )
                    {
                        //查询
                        List<Integer> vis90=(List<Integer>)service.getObjects("ServiceActivity.get90Vis", paraMap);
                        if(vis90.size()>=3){
                            for(int i=1;i<vis90.size()-1;i++){
                                if((vis90.get(i-1)>50)&&(vis90.get(i)>50)&&(vis90.get(i+1)>50)){
                                    info.setEntQualityReq(true);
                                }
                            }
                        }
                    }
                }
            }
            //获取最后日期的数据
            Summary summary=(Summary)service.getObject("ServiceActivity.getServiceVisGrid", paraMap);
            if(summary!=null){
                info.setOpenEntCnt(summary.getOpenEntCnt());
                info.setVisEntCnt(summary.getVisEntCnt());
                info.setVisActivity(CommonUtil.getPercent(summary.getVisEntCnt(), summary.getOpenEntCnt()));
            }
        }
      //生成表格 
        List<Object> grid=new ArrayList<Object>();
        for (int i=(page-1)*rp;i<(page*rp>serviceVisList.size()?serviceVisList.size():page*rp);i++)
        {
                ServiceInfo sum=serviceVisList.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getId());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getName(),
                        sum.getVis(),
                        sum.getVisEntCnt(),
                        sum.getOpenEntCnt(),
                        sum.getVisActivity(),
                        DateUtil.formatDateToString(sum.getReleaseTime(), "yyyy.MM")  ,
                        sum.getReleaseRange(),
                        sum.isEntBaseReq(),
                        sum.isEntQualityReq(),
                        sum.getId()
                    });
                logger.debug(cellMap);
                grid.add(cellMap);
            
        }
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", serviceVisList.size());// 从数据库获取总记录数
        this.returnMap.put("rows", grid);
        
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：单个服务的月度活跃度图
      * 参数：
      * 返回值：柱状图
     */
    public String getSingleServiceActive() throws BusinessException
    {
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("plat", plat);
        paraMap.put("id", this.serviceID);
        List<Summary> acList=(List<Summary>)service.getObjects("ServiceActivity.getSingleServiceAc", paraMap);
        
        logger.debug(acList);
        this.returnMap.put("singleBar", acList);
        return SUCCESS;
    }
    
    /**
     * 
      * 函数介绍：获取大区或省份的访问数据
      * 参数：
      * 返回值：饼图
     */
    public String getVis4Area() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("plat", plat);
        paraMap.put("id", this.serviceID);
        List<Summary> areaSVList=(List<Summary>)service.getObjects("ServiceActivity.getSingleServicePieGrid", paraMap);
        
        //省份pie
        List<Meta> ppList=new ArrayList<Meta>();
        Meta other=new Meta();
        other.setName("其他");
        int otherSum=0;
        for (int i=0;i<areaSVList.size();i++)
        {
            Summary summary=areaSVList.get(i);
            if(i<9){
                Meta m=new Meta();
                m.setName(summary.getProvince());
                m.setCode(summary.getVis()+"");
                ppList.add(m);
            }else{
                otherSum+=summary.getVis();
            }
        }
        if(areaSVList.size()>0)
        {   
            other.setCode(otherSum+"");
            ppList.add(other);
        }
        logger.debug(ppList);
        this.returnMap.put("ppList", ppList);
        
        //大区pie
        Map<String,Integer> apMap=new HashMap<String,Integer>();
        List<Meta> apList=new ArrayList<Meta>();
        for (int i=0;i<areaSVList.size();i++)
        {
            Summary summary=areaSVList.get(i);
            if(apMap.get(summary.getArea())==null){
                apMap.put(summary.getArea(), summary.getVis());
                
            }else{
                apMap.put(summary.getArea(), apMap.get(summary.getArea())+summary.getVis());
            }
        }
        Set<String> keys = apMap.keySet();
        for (String key : keys)
        {
            Meta m=new Meta();
            m.setName(key);
            m.setCode( apMap.get(key)+"");
            apList.add(m);
        }
        logger.debug(apList);
        this.returnMap.put("apList", apList);
        
        //表格
        Map<String,List<Meta>> apgMap=new HashMap<String,List<Meta>>();
        
        for (int i=0;i<areaSVList.size();i++)
        {
            Summary summary=areaSVList.get(i);
            List<Meta> unionList;
            if(apgMap.get(summary.getArea())==null){
                unionList=new ArrayList<Meta>();
                Meta m=new Meta();
                m.setName(summary.getProvince());
                m.setCode(summary.getVis()+"");
                unionList.add(m);
                apgMap.put(summary.getArea(), unionList);
                
            }else{
                unionList=apgMap.get(summary.getArea());
                Meta m=new Meta();
                m.setName(summary.getProvince());
                m.setCode(summary.getVis()+"");
                unionList.add(m);
                apgMap.put(summary.getArea(), unionList);
            }
        }
        Set<String>  as=apgMap.keySet();
        List<Meta> apgList=new ArrayList<Meta>();
        ComparatorMeta cm=new ComparatorMeta();
        for (String key : as)
        {
            Meta m=new Meta();
            m.setName(key);
            m.setCode( apMap.get(key)+"");
            List<Meta> ll=apgMap.get(key);
            Collections.sort(ll,cm);
            m.setList(ll);
            apgList.add(m);
        }
        
        logger.debug(apgList);
        this.returnMap.put("apgList", apgList);
        
        
        return SUCCESS;
    }
    
    private void sort( List<Meta> apgList){
        
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public Map<String, Object> getReturnMap()
    {
        return returnMap;
    }

    public void setReturnMap(Map<String, Object> returnMap)
    {
        this.returnMap = returnMap;
    }

    public List<ServiceInfo> getServiceList()
    {
        return serviceList;
    }

    public void setServiceList(List<ServiceInfo> serviceList)
    {
        this.serviceList = serviceList;
    }

    public String getPlat()
    {
        return plat;
    }

    public void setPlat(String plat)
    {
        this.plat = plat;
    }

    public int getServiceID()
    {
        return serviceID;
    }

    public void setServiceID(int serviceID)
    {
        this.serviceID = serviceID;
    }

  
    public int getVis()
    {
        return vis;
    }

    public void setVis(int vis)
    {
        this.vis = vis;
    }

    public int getVisEntCnt()
    {
        return visEntCnt;
    }

    public void setVisEntCnt(int visEntCnt)
    {
        this.visEntCnt = visEntCnt;
    }

    public int getOpenEntCnt()
    {
        return openEntCnt;
    }

    public void setOpenEntCnt(int openEntCnt)
    {
        this.openEntCnt = openEntCnt;
    }

    public int getVisActivity()
    {
        return visActivity;
    }

    public void setVisActivity(int visActivity)
    {
        this.visActivity = visActivity;
    }

    public String getDateType()
    {
        return dateType;
    }

    public void setDateType(String dateType)
    {
        this.dateType = dateType;
    }

    public String getStartDay()
    {
        return startDay;
    }

    public void setStartDay(String startDay)
    {
        this.startDay = startDay;
    }

    public String getEndDay()
    {
        return endDay;
    }

    public void setEndDay(String endDay)
    {
        this.endDay = endDay;
    }

    public int getRp()
    {
        return rp;
    }

    public void setRp(int rp)
    {
        this.rp = rp;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public String getSortname()
    {
        return sortname;
    }

    public void setSortname(String sortname)
    {
        this.sortname = sortname;
    }

    public String getSortorder()
    {
        return sortorder;
    }

    public void setSortorder(String sortorder)
    {
        this.sortorder = sortorder;
    }

    public int getAreaOrProvince()
    {
        return areaOrProvince;
    }

    public void setAreaOrProvince(int areaOrProvince)
    {
        this.areaOrProvince = areaOrProvince;
    }

 

}

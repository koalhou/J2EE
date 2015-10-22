package com.neusoft.clw.yw.ub.passivity.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.passivity.ds.Gather;
import com.neusoft.clw.yw.ub.passivity.ds.Question;
import com.neusoft.clw.yw.ub.srv.CommonUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 被动活跃度
 * 
 * @author ZhangYong
 */
public class PassivityAction extends PaginationAction
{
    protected Logger logger = Logger.getLogger(PassivityAction.class);
    private Map<String, Object> returnMap=new HashMap<String, Object>();
    private transient Service service;
    private String dateType="day";//查询时间类型  day week  month 
    private String startDay;//查询开始时间 yyyy-MM-dd
    private String endDay;//查询结束时间
    private int questionID=0;//推送ID
    
    private int rp=20;
    private int page=1;
    private int vis=1;
    private int  visEp=0;

    /**
     * 页面初始化
     * @return
     */
    public String query()
    {
       ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION, getText("ub.passivity.location"));

        return SUCCESS;
    }
    
    public String getpushlist()
    {
        return SUCCESS;
    }
    
    private boolean checkDate() 
    {
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
     * 
      * 函数介绍：统计区域
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */
    public String gather() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("id", this.questionID);
        Gather gather=(Gather)service.getObject("PassivityActivity.gather", paraMap);
        
        logger.debug(gather);
        this.returnMap.put("gather", gather);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：趋势图
      * 参数：
      * 返回值：
     */
    public String lines() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("id", this.questionID);
        
        List<Gather> list=new ArrayList<Gather>();
        Date start= DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
        Date end= DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        if("day".equals(dateType)){
           list=(List<Gather>)service.getObjects("PassivityActivity.linesDay", paraMap);  
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("PassivityActivity.linesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("PassivityActivity.linesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
        //计算占比
        int visSum=0;//访问次数
        int visEpSum=0;//访问企业
        for (Gather ga : list)
        {
            visSum+=ga.getVis();
            visEpSum+=ga.getVisEp();
        }
       
        for (Gather ga : list)
        {
            ga.setVisCntPercent(CommonUtil.getPercent(ga.getVis(),visSum)+"%");
            ga.setVisEpPercent(CommonUtil.getPercent(ga.getVisEp(),visEpSum)+"%");
        }
        this.returnMap.put("lines", list);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：表格
      * 参数：
      * 返回值：
     */
    public String grid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("id", this.questionID);
        
        List<Gather> list=new ArrayList<Gather>();
        Date start= DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
        Date end= DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        int total=0;
        if("day".equals(dateType)){
            paraMap.put("rowStart", (page-1)*rp);
            paraMap.put("rowEnd", page*rp);
            total=service.getCount("PassivityActivity.linesDayCnt", paraMap);  
            list=(List<Gather>)service.getObjects("PassivityActivity.linesDayPage", paraMap);  
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("PassivityActivity.linesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("PassivityActivity.linesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("PassivityActivity.linesRang", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
      //生成表格格式数据
        this.returnMap.put("page", page);// 从前台获取当前第page页
        List<Object> grid=new ArrayList<Object>();
        int max=(page*rp>list.size()?list.size():page*rp);
        if("day".equals(dateType)){
            this.returnMap.put("total", total);// 从数据库获取总记录数
            page=1;
            for (Gather sum : list)
            {
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                        new Object[] 
                                {
                        sum.getReportDate(),
                        sum.getVisEp(),
                        sum.getVis()
                                });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
        }else{
            this.returnMap.put("total", list.size());// 从数据库获取总记录数
            for (int i=(page*rp>list.size()?list.size():page*rp)-1;i>max-rp&&i>=0;i--)
            {
                Gather sum=list.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                        new Object[] 
                                {
                        sum.getReportDate(),
                        sum.getVisEp(),
                        sum.getVis()
                                });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
        }
        
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：各个推送的访问情况
      * 参数：
      * 返回值：
     */
    public String questtionGrid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        
        paraMap.put("rowStart", (page-1)*rp);
        paraMap.put("rowEnd", page*rp);
        int total=service.getCount("PassivityActivity.questionCnt", paraMap);  
        List<Question> list=(List<Question>)service.getObjects("PassivityActivity.questionPage", paraMap);  
      
        
      //生成表格格式数据
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", total);// 从数据库获取总记录数
        List<Object> grid=new ArrayList<Object>();
        for (Question question : list)
        {
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            
            cellMap.put("id", question.getId());
            cellMap.put("cell", 
                new Object[] 
                {
                    question.getName(),
                    question.getPubTime(),
                    question.getEndTime(),
                    question.getOpenEp(),
                    question.getVisEp(),
                    question.getVis(),
                    question.getVisPercent()
                });
            logger.debug(cellMap);
            grid.add(cellMap);
        }
        
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }
    /**
     * 
     * 函数介绍：根据ID获取name
     * 参数：
     * 返回值：
     */
    public String getQuestion() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("id", this.questionID);
        Question question=(Question)service.getObject("PassivityActivity.getQuestion", paraMap);  
        
        //生成表格格式数据
        this.returnMap.put("question", question);
        return SUCCESS;
    }
    public Service getService()
    {
        return service;
    }
    public void setService(Service service)
    {
        this.service = service;
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

    public int getQuestionID()
    {
        return questionID;
    }

    public void setQuestionID(int questionID)
    {
        this.questionID = questionID;
    }

    public Map<String, Object> getReturnMap()
    {
        return returnMap;
    }

    public void setReturnMap(Map<String, Object> returnMap)
    {
        this.returnMap = returnMap;
    }
    

}

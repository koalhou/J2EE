package com.neusoft.clw.yw.ub.market.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.ub.market.ds.AccountManangerTask;
import com.neusoft.clw.yw.ub.market.service.MarketService;
import com.neusoft.clw.yw.ub.srv.CommonUtil;
import com.opensymphony.xwork2.ActionContext;

public class MarketAction extends PaginationAction
{
    protected Logger logger = Logger.getLogger(MarketAction.class);
    private Map<String, Object> returnMap=new HashMap<String, Object>();
    private transient Service service;
    
    private int rp=20;
    private int page=1;
    
    private transient MarketService marketService;
    private String queryDate;//查询开始时间 yyyy-MM-dd
    private int acPercent;//企业活跃度
    private int taskPercent;//任务完成率
    private int id;//客戶經理的ID
    private int plan;//客戶經理的计划量
    private AccountManangerTask task;//客戶經理的计划量
    
    private boolean checkMonth(){
        if(StringUtils.hasText(this.queryDate)){
           Date s=DateUtil.parseStringToDate(this.queryDate+"-01", CommonUtil.YYYY_MM_DD); 
           if(s!=null){
               return true;
           }
        }
        this.returnMap.put("error", "时间格式不正确");
        return false;
    }
    private boolean checkYear(){
        if(StringUtils.hasText(this.queryDate)){
            Date s=DateUtil.parseStringToDate(this.queryDate+"-01-01", CommonUtil.YYYY_MM_DD); 
            if(s!=null){
                return true;
            }
        }
        this.returnMap.put("error", "时间格式不正确");
        return false;
    }
    
    /**
     * 页面初始化
     * @return
     */
    public String taskInit()
    {
       ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION, "-> 系统设置 -> 客户经理指标设置");
        return SUCCESS;
    }
    /**
     * 页面初始化
     * @return
     */
    public String rankListInit()
    {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION, "-> 行为分析 -> 市场看板");
        return SUCCESS;
    }
    public String linesInit()
    {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION, "-> 行为分析 -> 市场看板");
        return SUCCESS;
    }
    
    /**
     * 
      * 函数介绍：任务列表
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */    
    public String listTask() throws BusinessException{
        if(!checkMonth()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("month", this.queryDate);
        paraMap.put("rowStart", (page-1)*rp);
        paraMap.put("rowEnd", page*rp);
        List<AccountManangerTask> list= (List<AccountManangerTask>)service.getObjects("MarketBoard.getTaskList", paraMap);
        List<Object> grid=new ArrayList<Object>();
        for (int i=0;i<list.size();i++)
        {
            AccountManangerTask trask =list.get(i);
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            
            cellMap.put("id", trask.getId()+"|"+trask.getMonth());
            cellMap.put("cell", 
                new Object[] 
                {
                    i+1,
                    trask.getName(),
                    trask.getArea(),
                    trask.getPlan()
                });
            logger.debug(cellMap);
            grid.add(cellMap);
        }
        
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", list.size());// 从数据库获取总记录数
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }
    
    public String preEditTask() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("month", this.queryDate);
        paraMap.put("id", this.id);
        AccountManangerTask tmp= (AccountManangerTask)service.getObject("MarketBoard.getTask", paraMap);
        this.task=tmp;
        return SUCCESS;
        
    }
    /**
     * 
     * 函数介绍：设置任务
     * 参数：
     * 返回值：
     * @throws BusinessException 
     * @throws DataAccessException 
     */    
    public String editTask() throws BusinessException{
        //TODO 删除测试用户
        if(!checkMonth()){
            return ERROR;
        }
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("month", this.queryDate);
        paraMap.put("id", this.id);
        AccountManangerTask task= (AccountManangerTask)service.getObject("MarketBoard.getTask", paraMap);
        task.setCreater(currentUser.getUserID());
//        task.setUser("test");
        task.setPlan(plan);
        task.setMonth(queryDate);
        try
        {
            marketService.save(task);
            this.returnMap.put("ret", "ok");
        }
        catch (BusinessException e)
        {
            this.returnMap.put("ret", "fail");
        }
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：当年的折线图
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */    
    public String lines() throws BusinessException{
        if(!checkYear()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startMonth", this.queryDate+"-03");
        paraMap.put("endMonth", (Integer.valueOf(this.queryDate)+1)+"-02");
        List<AccountManangerTask> list= (List<AccountManangerTask>)service.getObjects("MarketBoard.getAcAndTask", paraMap);
        List<AccountManangerTask> hjlist=new ArrayList<AccountManangerTask>();
        List<AccountManangerTask> zblist=new ArrayList<AccountManangerTask>();
        List<AccountManangerTask> mzlist=new ArrayList<AccountManangerTask>();
        List<AccountManangerTask> sdlist=new ArrayList<AccountManangerTask>();
        List<AccountManangerTask> hnlist=new ArrayList<AccountManangerTask>();
        for (AccountManangerTask task : list)
        {
        	if(task.getMonth().indexOf("0")==0){
        		task.setMonth(task.getMonth().substring(1)+"月");
        	}
            if("中部区域".equals(task.getArea())){
                zblist.add(task);
            }else if("黑吉区域".equals(task.getArea())){
                hjlist.add(task);
            }else if("山东区域".equals(task.getArea())){
                sdlist.add(task);
            }else if("华南区域".equals(task.getArea())){
                hnlist.add(task);
            }else{
                mzlist.add(task);
            }
        }
        
        this.returnMap.put("zblist", zblist);
        this.returnMap.put("hjlist", hjlist);
        this.returnMap.put("sdlist", sdlist);
        this.returnMap.put("hnlist", hnlist);
        this.returnMap.put("mzlist", mzlist);
        return SUCCESS;
    }
    
    /**
     * 
      * 函数介绍：查看排名
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */
    public String amRank() throws BusinessException{
        if(!checkMonth()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("month", this.queryDate);
        List<AccountManangerTask> list= (List<AccountManangerTask>)service.getObjects("MarketBoard.getAMList", paraMap);
        List<Object> grid=new ArrayList<Object>();
        for (int i=0;i<list.size();i++)
        {
            AccountManangerTask trask =list.get(i);
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            
            cellMap.put("id", trask.getId()+"|"+trask.getMonth());
            cellMap.put("cell", 
                new Object[] 
                {
                    i+1,
                    trask.getName(),
                    trask.getArea(),
                    trask.getPlan(),
                    trask.getCompleted(),
                    trask.getCompletedPercent()+"%"
                });
            logger.debug(cellMap);
            grid.add(cellMap);
        }
        
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", list.size());// 从数据库获取总记录数
        this.returnMap.put("rows", grid);
        
        return SUCCESS;
    }
    /**
     * 
     * 函数介绍：查看排名
     * 参数：
     * 返回值：
     * @throws BusinessException 
     */
    public String acRank() throws BusinessException{
        if(!checkMonth()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("month", this.queryDate+"-01");
        List<AccountManangerTask> list= (List<AccountManangerTask>)service.getObjects("MarketBoard.getTMList", paraMap);
        List<Object> grid=new ArrayList<Object>();
        for (int i=0;i<list.size();i++)
        {
            AccountManangerTask trask =list.get(i);
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            
            cellMap.put("id", trask.getId()+"|"+trask.getMonth());
            cellMap.put("cell", 
                    new Object[] 
                            {
                    i+1,
                    trask.getName(),
                    trask.getArea(),
                    trask.getCompletedPercent()+"%"
                            });
            logger.debug(cellMap);
            grid.add(cellMap);
        }
        
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", list.size());// 从数据库获取总记录数
        this.returnMap.put("rows", grid);
        
        return SUCCESS;
    }
    
    
    
    public Map<String, Object> getReturnMap()
    {
        return returnMap;
    }
    public void setReturnMap(Map<String, Object> returnMap)
    {
        this.returnMap = returnMap;
    }
    public Service getService()
    {
        return service;
    }
    public void setService(Service service)
    {
        this.service = service;
    }
    public String getQueryDate()
    {
        return queryDate;
    }
    public void setQueryDate(String queryDate)
    {
        this.queryDate = queryDate;
    }
    public int getAcPercent()
    {
        return acPercent;
    }
    public void setAcPercent(int acPercent)
    {
        this.acPercent = acPercent;
    }
    public int getTaskPercent()
    {
        return taskPercent;
    }
    public void setTaskPercent(int taskPercent)
    {
        this.taskPercent = taskPercent;
    }
    public MarketService getMarketService()
    {
        return marketService;
    }
    public void setMarketService(MarketService marketService)
    {
        this.marketService = marketService;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getPlan()
    {
        return plan;
    }
    public void setPlan(int plan)
    {
        this.plan = plan;
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

    public AccountManangerTask getTask()
    {
        return task;
    }

    public void setTask(AccountManangerTask task)
    {
        this.task = task;
    }


}

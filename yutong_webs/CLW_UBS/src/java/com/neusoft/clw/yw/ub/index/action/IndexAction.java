package com.neusoft.clw.yw.ub.index.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.custom.ds.Gather;
import com.neusoft.clw.yw.ub.index.IndexCache;
import com.neusoft.clw.yw.ub.index.ds.CAStatistics;
import com.neusoft.clw.yw.ub.index.ds.SAStatistics;
import com.neusoft.clw.yw.ub.passivity.ds.Question;
import com.neusoft.clw.yw.ub.srv.CommonUtil;
import com.neusoft.clw.yw.ub.srv.ds.ServiceInfo;

/**
 * 用户行为分析首页
 * 
 * @author 
 */
public class IndexAction extends PaginationAction
{
    protected Logger logger = Logger.getLogger(IndexAction.class);

    private transient Service service;
    private IndexCache cache;

    
    private String curDate;//查询开始时间


    private String firstDayInMonth(int month,String dateString){
        Calendar cal= Calendar.getInstance();
        if(StringUtils.hasText(this.curDate)){
            Date tmp=DateUtil.parseStringToDate(dateString, CommonUtil.YYYY_MM_DD);
            cal.setTime(tmp);
        }
        cal.add(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.formatDateToString(cal.getTime(), CommonUtil.YYYY_MM_DD);
    }
    /**
     * 
      * 函数介绍： 获取月的最后一天
      * 参数：
      * 返回值：
     */
    private String lastDayInMonth(String dateString){
    	Calendar cal= Calendar.getInstance();
    	Date tmp=DateUtil.parseStringToDate(dateString, CommonUtil.YYYY_MM_DD);
    	cal.setTime(tmp);
    	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
    	return DateUtil.formatDateToString(cal.getTime(), CommonUtil.YYYY_MM_DD);
    }


    private Map<String, Object> returnMap=new HashMap<String, Object>();

	private String ret;
    /**
     * 函数介绍：获取车辆数和开通企业
     * @throws BusinessException 
     */
    public String getEntAndBus() throws BusinessException
    {
        Gather ga=(Gather)cache.getCache().get(cache.ENT_AND_BUS);
        if(ga==null){
            Map<String, Object> paraMap=new HashMap<String, Object>();
            Calendar cal= Calendar.getInstance();
            if(StringUtils.hasText(this.curDate)){
                cal.setTime(DateUtil.parseStringToDate(this.curDate, CommonUtil.YYYY_MM_DD));
            }
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            String tmpDate=DateUtil.formatDateToString(cal.getTime(), CommonUtil.YYYY_MM_DD);
            paraMap.put("endDay", tmpDate);
            
            ga=(Gather)service.getObject("UBIndex.getEntAndBus", paraMap);  
            cache.getCache().put(cache.ENT_AND_BUS, ga);
        }
        
        
        logger.debug(ga);
        this.returnMap.put("ga", ga); 

        return SUCCESS;
    }

    /**
     * 函数介绍：获取企业活跃度的上月排名
     * @throws BusinessException 
     */
    public String getCARankLastMonth() throws BusinessException
    {
        List<ServiceInfo> rankList=(List<ServiceInfo>)cache.getCache().get(cache.CA_RANK_LASTMONTH);
        if(rankList==null){
            Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("startDay", firstDayInMonth(-1,this.curDate));
            rankList=(List<ServiceInfo>)service.getObjects("UBIndex.getEntRank", paraMap);  
            cache.getCache().put(cache.CA_RANK_LASTMONTH, rankList);
        }
        logger.debug(rankList);
        this.returnMap.put("caRankList", rankList); 
        return SUCCESS;
    }
    /**
     * 函数介绍：获取企业活跃度的上月汇总
     * @throws BusinessException 
     */
    public String getCAInfoLastMonth() throws BusinessException
    {
        CAStatistics info=(CAStatistics)cache.getCache().get(cache.CA_INFO_LASTMONTH);
        if(info==null){
            Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("startDay", firstDayInMonth(-1,this.curDate));
            info=(CAStatistics)service.getObject("UBIndex.getCALastMonth", paraMap);  
            if(info==null){
                info=new CAStatistics();
            }
            cache.getCache().put(cache.CA_INFO_LASTMONTH, info);
        }
        this.returnMap.put("lastMonth", info); 
        return SUCCESS;
    }
    /**
     * 函数介绍：获取企业活跃度的上6个月汇总
     * @throws BusinessException 
     */
    public String getCAInfoLast6Month() throws BusinessException
    {
        List<CAStatistics> caList=(List<CAStatistics>)cache.getCache().get(cache.CA_INFO_LAST6MONTH);
        if(caList==null){
                Map<String, Object> paraMap=new HashMap<String, Object>();
                
                caList=new ArrayList<CAStatistics>();
                for (int i = 0; i <6; i++)
                {
                    paraMap.put("startDay",firstDayInMonth(i-6,this.curDate));
                    CAStatistics info=(CAStatistics)service.getObject("UBIndex.getCABar", paraMap);  
                    if(info==null){
                        info=new CAStatistics();
                        info.setReportDate(firstDayInMonth(i-6,this.curDate).substring(0,7));
                    }
                    logger.debug(info);
                    caList.add(info);
                }
            cache.getCache().put(cache.CA_INFO_LAST6MONTH, caList);
        }
        this.returnMap.put("caBars", caList); 
        
        
        return SUCCESS;
    }
    /**
     * 函数介绍：获取服务活跃度的上月汇总
     * @throws BusinessException 
     */
    public String getSAInfoLastMonth() throws BusinessException
    {
        List<ServiceInfo> list=(List<ServiceInfo>)cache.getCache().get(cache.SA_RANK_LASTMONTH);
        Map<String, String> paraMap=new HashMap<String, String>();
        if(list==null){
            paraMap.put("startDay", firstDayInMonth(-1,this.curDate));
            list=(List<ServiceInfo>)service.getObjects("UBIndex.getServiceRank", paraMap);  
            cache.getCache().put(cache.SA_RANK_LASTMONTH, list);
        }
        logger.debug(list);
        this.returnMap.put("saRankList", list); 
        
        
        List<SAStatistics> bars=(List<SAStatistics>)cache.getCache().get(cache.SA_INFO_LASTMONTH);
        if(bars==null){
            bars=new ArrayList<SAStatistics>();
            logger.info(diffMonth());
            int minus = diffMonth();
            for(int i=0;i<minus;i++){
            	logger.debug( diffMonth()+":"+ firstDayInMonth(i-minus,this.curDate));
                paraMap.put("startDay",  firstDayInMonth(i-minus,this.curDate));
                List<String> srvIDs=(List<String>)service.getObjects("UBIndex.getServicePerMonth", paraMap);
                
                SAStatistics sa=new SAStatistics();
                sa.setReportDate(firstDayInMonth(i-minus,this.curDate).substring(0,7));
                sa.setTotal(srvIDs.size());
                logger.debug("reportDate:"+firstDayInMonth(i-minus,this.curDate).substring(0,7));
                int baseReq=0;
                int qulityReq=0;
                for (String id : srvIDs)
                {
                    paraMap.put("id", id);
                    List<ServiceInfo> srvList=(List<ServiceInfo>)service.getObjects("UBIndex.getServiceInfoBySrvID", paraMap); 
                    paraMap.put("endDay", lastDayInMonth(paraMap.get("startDay")));
                    Integer vis100=service.getCount("UBIndex.get100Vis", paraMap);
                    if(vis100>=100){
                    	sa.setBaseReq(sa.getBaseReq()+1);
                        baseReq++;
                    }
                    
                    if(srvList!=null&&srvList.size()>0){
                        ServiceInfo ser=srvList.get(srvList.size()-1);
                        logger.debug(ser);
                        if(vis100>=100){
                            for(int t=1;t<srvList.size()-1;t++){
                                    if(srvList.get(t-1).getVisActivity()>50&&srvList.get(t).getVisActivity()>50&&srvList.get(t+1).getVisActivity()>50){
                                        sa.setQulityReq(sa.getQulityReq()+1);
                                        qulityReq++;
                                        break;
                                }
                            }
                        }
                    }
                }
                sa.setBaseReqPercent(CommonUtil.getPercent(baseReq, sa.getTotal()));
                sa.setQulityReqPercent(CommonUtil.getPercent(qulityReq, sa.getTotal()));
                bars.add(sa);
                logger.debug(sa);
            }
            cache.getCache().put(cache.SA_INFO_LASTMONTH, bars);
        }
        
        this.returnMap.put("saBars", bars); 
               
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：获取当月与今年一月的差值，大约6为6
      * 参数：
      * 返回值：
     */
    private int diffMonth()
    {
        Calendar lastMonth= Calendar.getInstance();
        if(StringUtils.hasText(this.curDate)){
            Date tmp=DateUtil.parseStringToDate(this.curDate, CommonUtil.YYYY_MM_DD);
            lastMonth.setTime(tmp);
        }
        lastMonth.add(Calendar.MONTH, -1);
        lastMonth.set(Calendar.DAY_OF_MONTH, 1);
        
        Calendar firstMonth= Calendar.getInstance();
        firstMonth.set(Calendar.DAY_OF_MONTH, 1);
        firstMonth.set(Calendar.MONTH, 0);
        
        int diff=lastMonth.get(Calendar.MONTH)-firstMonth.get(Calendar.MONTH)+1;
        int minus=(diff<6?diff:6);
        return minus;
    }
    /**
     * 函数介绍：获取推送活跃度的上月汇总
     * @throws BusinessException 
     */
    public String getPAInfoLastMonth() throws BusinessException
    {
        List<Question> list=( List<Question>)cache.getCache().get(cache.PA_INFO_LASTMONTH);
        if(list==null){
            Map<String, Object> paraMap=new HashMap<String, Object>();
            paraMap.put("startDay", firstDayInMonth(-1,this.curDate));
            list=(List<Question>)service.getObjects("UBIndex.questionList", paraMap);  
            cache.getCache().put(cache.PA_INFO_LASTMONTH, list);
        }
      
        logger.debug(list);
        this.returnMap.put("paList", list);
        return SUCCESS;
    }
    /**
     * 函数介绍：清楚缓存
     * @throws BusinessException 
     */
    public String clearCache() throws BusinessException
    {
       logger.info("clearing cache data......");
       cache.getCache().clear();
       this.ret="ok";
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
    public Map<String, Object> getReturnMap()
    {
        return returnMap;
    }
    
    public String getCurDate()
    {
        return curDate;
    }

    public void setCurDate(String curDate)
    {
        this.curDate = curDate;
    }
    public void setReturnMap(Map<String, Object> returnMap)
    {
        this.returnMap = returnMap;
    }

    public void setCache(IndexCache cache)
    {
        this.cache = cache;
    }

	public String getRet() {
		return ret;
	}

 
}

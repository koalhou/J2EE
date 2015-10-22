package com.neusoft.clw.yw.ub.custom.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.custom.ds.AccountGather;
import com.neusoft.clw.yw.ub.custom.ds.Gather;
import com.neusoft.clw.yw.ub.custom.ds.Meta;
import com.neusoft.clw.yw.ub.srv.CommonUtil;
import com.neusoft.clw.yw.ub.srv.ds.ServiceInfo;
import com.neusoft.clw.yw.ub.srv.ds.Summary;
import com.opensymphony.xwork2.ActionContext;

/**
 * 企业活跃度
 * 
 * @author 
 */
public class CustomerAction extends PaginationAction
{
    protected Logger logger = Logger.getLogger(CustomerAction.class);

    private transient Service service;
    private  List<Meta> regions;//大区列表

    private String plat="web";//web app all
    private String area;//大区id
    private String province;//省份id
    private String city;//地市id

    private int rp=20;
    private int page=1;
    
    private String startDay;//查询开始时间
    private String endDay;//查询结束时间
    private String dateType;//查询时间类型  day week  month 
    private String customerType;//企业类别  A B C
    private String customerID;//企业名称或编码
    private String accountID;//账号ID
    private String srvID;//服务ID
    private String ac;// 是否活跃  1 活跃  0  不活跃
    
    private int vis=1;//访问次数
    private int amvis=0;//上午访问次数
    private int pmvis=0;//下午访问次数
    private int nivis=0;//晚上访问次数
    private int addvis=0;//追加访问次数
    private int openEnt=0;//开通企业
    private int epEnt=0;//活跃企业
    private int ep=0;  //活跃度
    private int oVis=0;  //原始访问次数

    private Map<String, Object> returnMap=new HashMap<String, Object>();

    /**
     * 函数介绍：获取查询基本信息 参数： 返回值：
     * @throws BusinessException 
     */
    public String init() throws BusinessException
    {
        ActionContext
        .getContext()
        .getSession()
        .put(Constants.CURRENT_LOCATION, getText("ub.custom.location"));
        regions = service.getObjects("CustomActivity.getRegionList", null);
        return SUCCESS;
    }

    /**
      * 函数介绍：根据大区获取省份
      * 参数：
      * 返回值：
     */
    public String getProvinceByArea() throws BusinessException
    {
        List<Meta> list = service.getObjects("CustomActivity.getProvinceList", this.area);
        returnMap.put("provinces", list);
        return SUCCESS;
    }

    /**
     * 根据省份获取地市
      * 函数介绍：
      * 参数：
      * 返回值：
     */
    public String getCityByProvince() throws BusinessException
    {
        List<Meta> list = service.getObjects("CustomActivity.getCityList", this.province);
        returnMap.put("citys", list);
        return SUCCESS;
    }
    

    /**
     * 
      * 函数介绍：获取所有企业客户的汇总统计
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */
    public String gatherAll() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
        Gather  gahter=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
        Gather  tmp=(Gather)service.getObject("CustomActivity.getEntSum", paraMap);
        gahter.setVis(tmp.getVis());
        gahter.setDayVis(tmp.getDayVis());
        logger.debug(gahter);
       
        this.returnMap.put("gather", gahter);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：折线图
      * 参数：
      * 返回值：
     */
    public String getLineChart() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        
        int s=vis+amvis+pmvis+nivis+addvis+openEnt+epEnt+ep+oVis;
        if(s>2){
            this.returnMap.put("ret", "error");
            this.returnMap.put("msg", "选择指标超过2个了！");
            return ERROR;
        }
        Map<String, Object> paraMap = new HashMap<String, Object>();
        
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        
        List<Gather> list=new ArrayList<Gather>();
        Date start= DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
        Date end= DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        if("day".equals(dateType)){
            paraMap.put("sortOrder", "asc");
           list=(List<Gather>)service.getObjects("CustomActivity.getLinesDay", paraMap);  
           
        	   List<Gather> ovisList=(List<Gather>)service.getObjects("CustomActivity.getOvis4LinesDay", paraMap);  
        	   for (Gather gather : list) {
				for (Gather tmp : ovisList) {
					if(gather.getReportDate().equals(tmp.getReportDate())){
						gather.setoVis(tmp.getoVis());
					}
				}
			}
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getLinesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getLinesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
        //计算占比
        int visSum=0;//访问次数
        int amvisSum=0;//上午访问次数
        int pmvisSum=0;//下午访问次数
        int nivisSum=0;//晚上访问次数
        int addvisSum=0;//追加访问次数
        int openEntSum=0;//开通企业
        int epEntSum=0;//活跃企业
        int oVisSum=0;//活跃企业
        for (Gather ga : list)
        {
            visSum+=ga.getVis();
            amvisSum+=ga.getAmVis();
            pmvisSum+=ga.getPmVis();
            nivisSum+=ga.getNiVis();
            addvisSum+=ga.getAddVis();
            openEntSum+=ga.getOpenEntCnt();
            epEntSum+=ga.getEmEntCnt();
            oVisSum+=ga.getoVis();
        }
       
        for (Gather ga : list)
        {
            ga.setVisPercent(CommonUtil.getPercent(ga.getVis(),visSum)+"%");
            ga.setAmVisPercent(CommonUtil.getPercent(ga.getAmVis(),amvisSum)+"%");
            ga.setPmVisPercent(CommonUtil.getPercent(ga.getPmVis(),pmvisSum)+"%");
            ga.setNiVisPercent(CommonUtil.getPercent(ga.getNiVis(),nivisSum)+"%");
            ga.setAddVisPercent(CommonUtil.getPercent(ga.getAddVis(),addvisSum)+"%");
            ga.setOpenEntCntPercent(CommonUtil.getPercent(ga.getOpenEntCnt(),openEntSum)+"%");
            ga.setEmEntCntPercent(CommonUtil.getPercent(ga.getEmEntCnt(),epEntSum)+"%");
            ga.setEmEntCntPercent(CommonUtil.getPercent(ga.getEmEntCnt(),epEntSum)+"%");
           ga.setoVisPercent(CommonUtil.getPercent(ga.getoVis(),oVisSum)+"%");
        }
        this.returnMap.put("lines", list);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：汇总表格
      * 参数：
      * 返回值：
     */
    public String getGrid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }

        Map<String, Object> paraMap = new HashMap<String, Object>();
        
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        
        List<Gather> list=new ArrayList<Gather>();
        Date start= DateUtil.parseStringToDate(startDay, CommonUtil.YYYY_MM_DD);
        Date end= DateUtil.parseStringToDate(endDay, CommonUtil.YYYY_MM_DD);
        Calendar cal=Calendar.getInstance();
        cal.setTime(start);
        Integer total=0;
        if("day".equals(dateType)){
            paraMap.put("rowStart", (page-1)*rp);
            paraMap.put("rowEnd", page*rp);
            paraMap.put("sortOrder", "desc");
           total=(Integer)service.getObject("CustomActivity.getLinesDayCnt", paraMap);  
           list=(List<Gather>)service.getObjects("CustomActivity.getLinesDay", paraMap);  
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getLinesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getLinesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getLinesRange", paraMap);
                if(tmp!=null){
                    Gather  ga=(Gather)service.getObject("CustomActivity.getEntAndBus", paraMap);
                    tmp.setBusCnt(ga.getBusCnt());
                    tmp.setOpenEntCnt(ga.getOpenEntCnt());
                    tmp.setEmEntCnt(ga.getEmEntCnt());
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
        //生成表格格式数据
        this.returnMap.put("page", page);
        List<Object> grid=new ArrayList<Object>();
        
        if("day".equals(dateType)){
            this.returnMap.put("total", total);// 从数据库获取总记录数
            for (Gather sum : list)
            {
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getReportDate(),
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getOpenEntCnt(),
                        sum.getEmEntCnt(),
                        sum.getEp()
                    });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
                
        }else{
            this.returnMap.put("total", list.size());// 从数据库获取总记录数
            int max=(page*rp>list.size()?list.size():page*rp);
            for (int i=max;i>0;i--)
            {
                    Gather sum=list.get(i-1);
                    Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                    
                    cellMap.put("id", sum.getReportDate());
                    cellMap.put("cell", 
                        new Object[] 
                        {
                            sum.getReportDate(),
                            sum.getVis(),
                            sum.getAmVis(),
                            sum.getPmVis(),
                            sum.getNiVis(),
                            sum.getAddVis(),
                            sum.getOpenEntCnt(),
                            sum.getEmEntCnt(),
                            sum.getEp()
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
      * 函数介绍：查询各个企业的信息列表
      * 参数：
      * 返回值：
     * @throws BusinessException 
     */
    public String getEveryEntInfo() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        List<Gather> list=(List<Gather>)service.getObjects("CustomActivity.getEveryEntRang", paraMap);
        logger.debug(list);
        //表格
        List<Object> grid=new ArrayList<Object>();
        for (int i=(page-1)*rp;i<(page*rp>list.size()?list.size():page*rp);i++)
        {
                Gather sum=list.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getEntID());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getEntName(),
                        sum.getEntCode(),
                        sum.getEntType(),
                        sum.getEntOpenTime(),
                        sum.getAc()==0?"否":"是",
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getEntID()
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
      * 函数介绍：获取各个服务的访问柱状图
      * 参数：
      * 返回值：柱状图
     */
    public String getServiceVisBars() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", plat);
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        List<ServiceInfo> serviceVisList=(List<ServiceInfo>)service.getObjects("CustomActivity.getServiceVisBars", paraMap);
        
        logger.debug(serviceVisList);
        this.returnMap.put("bars", serviceVisList);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：获取各个服务的访问表格
      * 参数：
      * 返回值：柱状图
     */
    public String getServiceVisGrid() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", plat);
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        List<ServiceInfo> list=(List<ServiceInfo>)service.getObjects("CustomActivity.getServiceVisGrid", paraMap);
        
        logger.debug(list);
        List<Object> grid=new ArrayList<Object>();
        for (int i=(page-1)*rp;i<(page*rp>list.size()?list.size():page*rp);i++)
        {
            ServiceInfo sum=list.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getId());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getName(),
                        sum.getVis(),
                        sum.getVisEntCnt(),
                        sum.getOpenEntCnt(),
                        sum.getVisActivity()
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
      * 函数介绍：查询
      * 参数：
      * 返回值：
     */
    public String search() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("customerType", this.customerType);
        paraMap.put("ac", this.ac);
        paraMap.put("id", this.customerID);
       
        if(StringUtils.hasText(this.city)){
            paraMap.put("city", this.city);
        }else if(StringUtils.hasText(this.province)) {
            paraMap.put("province", this.province);
        }else if(StringUtils.hasText(this.area)) {
            paraMap.put("area", this.area);
        }
        List<Gather> list=(List<Gather>)service.getObjects("CustomActivity.getEveryEntRang", paraMap);
        //表格
        List<Object> grid=new ArrayList<Object>();
        for (int i=(page-1)*rp;i<(page*rp>list.size()?list.size():page*rp);i++)
        {
                Gather sum=list.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getEntID());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getEntName(),
                        sum.getEntCode(),
                        sum.getEntType(),
                        sum.getEntOpenTime(),
                        sum.getAc(),
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getEntID()
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
      * 函数介绍：获取单个企业的统计信息
      * 参数：
      * 返回值：
     */
    public String getOneGather() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.customerID);
        Gather list=(Gather)service.getObject("CustomActivity.getOneGather", paraMap);
        this.returnMap.put("gather", list);
        return SUCCESS;
    }
    
    public String getOneLines() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        int s=vis+amvis+pmvis+nivis+addvis;
        if(s>2){
            this.returnMap.put("ret", "error");
            this.returnMap.put("msg", "选择指标超过2个了！");
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.customerID);
        List<Gather> list=new ArrayList<Gather>();
        if("day".equals(dateType)){
            paraMap.put("sortOrder", "asc");
            list=(List<Gather>)service.getObjects("CustomActivity.getOneLinesDay", paraMap);
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getOneLinesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getOneLinesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
        //占比
        int visSum=0;//访问次数
        int amvisSum=0;//上午访问次数
        int pmvisSum=0;//下午访问次数
        int nivisSum=0;//晚上访问次数
        int addvisSum=0;//追加访问次数
        for (Gather ga : list)
        {
            visSum+=ga.getVis();
            amvisSum+=ga.getAmVis();
            pmvisSum+=ga.getPmVis();
            nivisSum+=ga.getNiVis();
            addvisSum+=ga.getAddVis();
        }
       
        for (Gather ga : list)
        {
            ga.setVisPercent(CommonUtil.getPercent(ga.getVis(),visSum)+"%");
            ga.setAmVisPercent(CommonUtil.getPercent(ga.getAmVis(),amvisSum)+"%");
            ga.setPmVisPercent(CommonUtil.getPercent(ga.getPmVis(),pmvisSum)+"%");
            ga.setNiVisPercent(CommonUtil.getPercent(ga.getNiVis(),nivisSum)+"%");
            ga.setAddVisPercent(CommonUtil.getPercent(ga.getAddVis(),addvisSum)+"%");
           
        }
        this.returnMap.put("lines", list);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：单个企业的表格
      * 参数：
      * 返回值：
     */
    public String getOneGrid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }

        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.customerID);
        List<Gather> list=new ArrayList<Gather>();
        Integer total=0;
        if("day".equals(dateType)){
            paraMap.put("rowStart", (page-1)*rp);
            paraMap.put("rowEnd", page*rp);
            paraMap.put("sortOrder", "desc");
            total=(Integer)service.getObject("CustomActivity.getOneLinesDayCnt", paraMap);
            list=(List<Gather>)service.getObjects("CustomActivity.getOneLinesDay", paraMap);
        }else  if("week".equals(dateType)){
            String monday=CommonUtil.getMOnday4Week(startDay);
            if(monday!=null){
                paraMap.put("endDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周前："+list);    
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getOneLinesWeek", paraMap));  
            logger.debug("周前+周："+list);
            monday=CommonUtil.getMonday4Day(endDay);
            if(monday!=null){
                paraMap.put("startDay", monday);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("周所有："+list);  
            
        }else if("month".equals(dateType)){
            String lastDay=CommonUtil.getLastDay(startDay);
            if(lastDay!=null){
                paraMap.put("endDay", lastDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月前："+list); 
            paraMap.put("startDay", startDay);
            paraMap.put("endDay", endDay);
            list.addAll((List<Gather>)service.getObjects("CustomActivity.getOneLinesMonth", paraMap)); 
            logger.debug("月前+月："+list); 
            String firstDay=CommonUtil.getFirstDay(endDay);
            if(firstDay!=null){
                paraMap.put("startDay", firstDay);
                Gather tmp=(Gather)service.getObject("CustomActivity.getOneLinesRange", paraMap);
                if(tmp!=null){
                    list.add(tmp);
                }
            }
            logger.debug("月所有："+list); 
        }
        
        //表格
        List<Object> grid=new ArrayList<Object>();
        if("day".equals(dateType)){
            this.returnMap.put("total", total);
            for (Gather sum : list)
            {
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                        new Object[] 
                                {
                        sum.getReportDate(),
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getLoginCnt(),
                        sum.getEntID()
                                });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
        }else{
            this.returnMap.put("total", list.size());// 从数据库获取总记录数
            int max=(page*rp>list.size()?list.size():page*rp);
            for (int i=max;i>0;i--)
            {
                Gather sum=list.get(i-1);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getReportDate());
                cellMap.put("cell", 
                        new Object[] 
                                {
                        sum.getReportDate(),
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getLoginCnt(),
                        sum.getEntID()
                                });
                logger.debug(cellMap);
                grid.add(cellMap);
            }
        }
        
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：账户的统计
      * 参数：
      * 返回值：
     */
    public String getAccountGrid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.customerID);
        List<AccountGather> list=service.getObjects("CustomActivity.getAccountGrid", paraMap);
        logger.debug(list);
        //表格
        List<Object> grid=new ArrayList<Object>();
        for (int i=(page-1)*rp;i<(page*rp>list.size()?list.size():page*rp);i++)
        {
                AccountGather sum=list.get(i);
                Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
                
                cellMap.put("id", sum.getId());
                cellMap.put("cell", 
                    new Object[] 
                    {
                        sum.getName(),
                        sum.getRoleName(),
                        sum.getCreatedTime(),
                        sum.getLoginCnt(),
                        sum.getVis(),
                        sum.getAmVis(),
                        sum.getPmVis(),
                        sum.getNiVis(),
                        sum.getAddVis(),
                        sum.getDayVis(),
                        sum.getId()
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
      * 函数介绍：单个账号的柱状图
      * 参数：
      * 返回值：
     */
    public String getOneAccountBars() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.accountID);
        
        List<Meta> list=service.getObjects("CustomActivity.getOneAccountBars", paraMap);
        logger.debug(list);
       this.returnMap.put("bars", list);
        return SUCCESS;
    }
    /**
     * 
      * 函数介绍：单个账号的服务访问表格
      * 参数：时间区间    服务ID
      * 返回值：
     */
    public String getOneAccountGrid() throws BusinessException{
        if(!checkDate()){
            return ERROR;
        }
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("plat", this.plat);
        paraMap.put("startDay", this.startDay);
        paraMap.put("endDay", this.endDay);
        paraMap.put("id", this.accountID);
        paraMap.put("srvID", this.srvID);
        paraMap.put("rowStart", rp*(page-1));
        paraMap.put("rowEnd", rp*page);
        Integer total=service.getCount("CustomActivity.getOneAccountGridCnt", paraMap);
        List<AccountGather> list=service.getObjects("CustomActivity.getOneAccountGrid", paraMap);
        List<Object> grid=new ArrayList<Object>();
        for (AccountGather sum : list)
        {
            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
            
            cellMap.put("id", sum.getId());
            cellMap.put("cell", 
                    new Object[] 
                            {
                    sum.getSrvName(),
                    sum.getOpDesc(),
                    sum.getOpTime(),
                    sum.getIp(),
                    sum.getAddr()
                            });
            logger.debug(cellMap);
            grid.add(cellMap);
        }
        logger.debug(list);
       
        this.returnMap.put("page", page);// 从前台获取当前第page页
        this.returnMap.put("total", total);// 从数据库获取总记录数
        this.returnMap.put("rows", grid);
        return SUCCESS;
    }

    /**
     * 
      * 函数介绍：单个企业的各个服务的访问柱状图
      * 参数：
      * 返回值：柱状图
     */
    public String getServiceVisBars4One() throws BusinessException{
        Map<String, Object> paraMap=new HashMap<String, Object>();
        paraMap.put("startDay", startDay);
        paraMap.put("endDay", endDay);
        paraMap.put("plat", plat);
        paraMap.put("id", this.customerID);
        List<ServiceInfo> serviceVisList=(List<ServiceInfo>)service.getObjects("CustomActivity.getServiceVisChart4One", paraMap);
        
        logger.debug(serviceVisList);
        this.returnMap.put("bars", serviceVisList);
        return SUCCESS;
    }
    
    private boolean checkDate() throws BusinessException
    {
        Date start=DateUtil.parseStringToDate(this.startDay, CommonUtil.YYYY_MM_DD);
        Date end=DateUtil.parseStringToDate(this.endDay, CommonUtil.YYYY_MM_DD);
        Map<String,Object> paraMap=new HashMap<String,Object>();
        
        if(start==null||end==null){
            returnMap.put("error", "查询时间不能为空");
            return false;
        }
        if(start.compareTo(end)>0){
            returnMap.put("error", "开始时间不能大于结束时间");
            return false;
        }
        
        if(!("all".equals(this.plat)||"web".equals(this.plat)||"app".equals(this.plat))){
            returnMap.put("error", "请选择平台");
            return false;            
        }
        
        return true;
    }

    public Service getService()
    {
        return service;
    }

    public void setService(Service service)
    {
        this.service = service;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public Map<String, Object> getReturnMap()
    {
        return returnMap;
    }

    public void setReturnMap(Map<String, Object> returnMap)
    {
        this.returnMap = returnMap;
    }

    public List<Meta> getRegions()
    {
        return regions;
    }

    public void setRegions(List<Meta> regions)
    {
        this.regions = regions;
    }

    public String getPlat()
    {
        return plat;
    }

    public void setPlat(String plat)
    {
        this.plat = plat;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
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

    public String getCustomerType()
    {
        return customerType;
    }

    public void setCustomerType(String customerType)
    {
        this.customerType = customerType;
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

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }

    public String getAc()
    {
        return ac;
    }

    public void setAc(String ac)
    {
        this.ac = ac;
    }

    public int getVis()
    {
        return vis;
    }

    public void setVis(int vis)
    {
        this.vis = vis;
    }

    public int getAmvis()
    {
        return amvis;
    }

    public void setAmvis(int amvis)
    {
        this.amvis = amvis;
    }

    public int getPmvis()
    {
        return pmvis;
    }

    public void setPmvis(int pmvis)
    {
        this.pmvis = pmvis;
    }

    public int getNivis()
    {
        return nivis;
    }

    public void setNivis(int nivis)
    {
        this.nivis = nivis;
    }

    public int getAddvis()
    {
        return addvis;
    }

    public void setAddvis(int addvis)
    {
        this.addvis = addvis;
    }

    public int getOpenEnt()
    {
        return openEnt;
    }

    public void setOpenEnt(int openEnt)
    {
        this.openEnt = openEnt;
    }

    public int getEpEnt()
    {
        return epEnt;
    }

    public void setEpEnt(int epEnt)
    {
        this.epEnt = epEnt;
    }

    public int getEp()
    {
        return ep;
    }

    public void setEp(int ep)
    {
        this.ep = ep;
    }

    public String getSrvID()
    {
        return srvID;
    }

    public void setSrvID(String srvID)
    {
        this.srvID = srvID;
    }

    public void setAccountID(String accountID)
    {
        this.accountID = accountID;
    }

    public String getAccountID()
    {
        return accountID;
    }

}

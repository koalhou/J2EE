package com.neusoft.clw.itsmanage.oilmanage.oilused.action;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilManage;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilUsed;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilUsedExport;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.sysset.ocktimeset.domain.OckTimeSet;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class OilUsedNewAction extends PaginationAction {
    private OilUsed oilused;

    private HashMap Params = new HashMap();

    private VehcileInfo vehcileInfo;

    private String vehicle_ln;

    private List < OilUsed > oilusedList;

    private List < OilUsed > sumList;

    private String check_time_code;

    private OckTimeSet ocktimeset;

    private OilSet oilset;

    private String chooseorgid;

    private Map map = new HashMap();

    private String code_name;

    private String enterprise_name;

    private String organization_name;

    private String vehicle_vin;
    
    private String vehicle_code;

    /** service共通类 */
    private transient Service service;
    
    
    /**
     * 油耗分析页面浏览
     * @return
     */
    public String oilUsedList() {
        final String browseTitle = getText("oilused.browseCar.title");
    	MDC.put("modulename", "[fuelanalysis]");
        UserInfo user = getCurrentUser();
        int totalCountDay = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == oilused) {
                oilused = new OilUsed();
            }
            oilused.setOrganization_id(user.getOrganizationID());
            oilused.setVehicle_ln(SearchUtil.formatSpecialChar(getVehicle_ln()));
            oilused.setVehicle_code(SearchUtil.formatSpecialChar(getVehicle_code()));
            if (null != check_time_code && !"".equals(check_time_code)) {
                oilused.setCheck_time_code(check_time_code);
            } else {
                oilused.setCheck_time_code(DateUtil.getMonth());
            }

            if (null != chooseorgid && !"".equals(chooseorgid)) {
                oilused.setOrganization_id(chooseorgid);
            } else {
                oilused.setOrganization_id(user.getOrganizationID());
            }

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            oilused.setEnterprise_id(user.getEntiID());
            oilused.setSortname(sortName);
            oilused.setSortorder(sortOrder);

            log.info("[Organization_id:" + oilused.getOrganization_id() + ",Time_list:" + oilused.getCheck_time_code()
        			+ ",Vehicle_ln:" + getVehicle_ln() +"]:"+browseTitle+"开始");
            
            totalCountDay = service.getCount("OilUsed.getOilUsedCount", oilused);

            oilusedList = service.getObjectsByPage(
                    "OilUsed.getOilUsedList", oilused, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));

            sumList = service.getObjects("OilUsed.getOilUsedSumList", oilused);

            this.map = getPagination(oilusedList, sumList, totalCountDay,
                    pageIndex);// 转换map

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId("111_3_4_6");
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        } 
        return SUCCESS;
    }

    /**
     * 导出油耗分析
     */
    public String exportOilbyCar() throws UnsupportedEncodingException {
        String modelTitle = getText("oilused.export.title");
    	MDC.put("modulename", "[fuelanalysis]");
        UserInfo user = getCurrentUser();
        if (null == oilused) {
            oilused = new OilUsed();
        }

        oilused.setOrganization_id(user.getOrganizationID());
        oilused.setEnterprise_id(user.getEntiID());
        oilused.setVehicle_ln(getVehicle_ln());
        oilused.setVehicle_code(SearchUtil.formatSpecialChar(getVehicle_code()));
        //oilused.setSortname("SAVE_STANDARD desc,over_standard");
        //oilused.setSortorder("desc");
        
        String timeStr = "";
        if (null != check_time_code && !"".equals(check_time_code)) {
            if (check_time_code.indexOf(",") >= 0) {
                if (check_time_code.substring(check_time_code.indexOf(",") + 2)
                        .equals("")) {
                    oilused.setCheck_time_code(DateUtil.getMonth());
                } else {
                    oilused.setCheck_time_code(check_time_code
                            .substring(check_time_code.indexOf(",") + 2));
                }
            } else {
                oilused.setCheck_time_code(check_time_code);
            }
        } else {
            if(null==oilused.getCheck_time_code()||"".equals(oilused.getCheck_time_code())){
                oilused.setCheck_time_code(DateUtil.getMonth());  
            }   
        }
        timeStr = oilused.getCheck_time_code();

        if (null != chooseorgid && !"".equals(chooseorgid)) {
            if (chooseorgid.indexOf(",") >= 0) {
                if (chooseorgid.substring(chooseorgid.indexOf(",") + 2).equals(
                        "")) {
                    oilused.setOrganization_id(user.getOrganizationID());
                } else {
                    oilused.setOrganization_id(chooseorgid
                            .substring(chooseorgid.indexOf(",") + 2));
                }

            } else {
                oilused.setOrganization_id(chooseorgid);
            }
        } else {
            oilused.setOrganization_id(user.getOrganizationID());
        }
        
        log.info("[Organization_id:" + oilused.getOrganization_id() + ",Time_list:" +oilused.getCheck_time_code()
    			+ ",Vehicle_ln:" + getVehicle_ln() +"]:"+modelTitle+"开始");
        try {

            oilusedList = service.getObjects("OilUsed.getOilUsedList", oilused);

            sumList = service.getObjects("OilUsed.getOilUsedSumList", oilused);
        } catch (BusinessException e) {
            log.error("导出油耗分析报告查询列表时出错:", e);
            return ERROR;
        } catch (Exception e) {
            log.error("导出油耗分析报告查询列表时出错:", e);
            return ERROR;
        }

        List < OilUsedExport > oilexportlist = new ArrayList < OilUsedExport >();
        DecimalFormat decimalformat = new DecimalFormat("0.00");
        for (int i = 0; i < oilusedList.size(); i++) {
            OilUsedExport oilexport = new OilUsedExport();
            OilUsed oilused = oilusedList.get(i);
            //oilexport.setRn(oilused.getRn());
            oilexport.setVehicle_code(oilused.getVehicle_code());
            oilexport.setVehicle_ln(oilused.getVehicle_ln());
            //oilexport.setVehicle_vin(oilused.getVehicle_vin());            
            //oilexport.setDriverName(oilused.getDriver_name());
            oilexport.setCheckValue(decimalformat.format(oilused.getCheck_value()));
            oilexport.setHkmOilUsed(decimalformat.format(oilused.getHkm_oilused()==null?0.00:oilused.getHkm_oilused()));
            oilexport.setMinusOil(decimalformat.format(oilused.getMinusoil()));
            oilexport.setCountOilTotal(decimalformat.format(oilused.getCOUNT_OIL_TOTAL()));
            //oilexport.setCheckValueMile(decimalformat.format(oilused.getCheck_value_mile()));
            //oilexport.setMaxOil(decimalformat.format(oilused.getMax_oil()));
//            String save_sta=decimalformat.format(oilused.getSave_standard());
//            if("0.00".equals(save_sta)){
//            	save_sta="--";
//            }
//            oilexport.setSaveStandard(save_sta);  
            
//            String over_sta=decimalformat.format(oilused.getOver_standard());
//            if("0.00".equals(over_sta)){
//            	over_sta="--";
//            }
//            oilexport.setOverStandard(over_sta);
            oilexport.setCountMileage(decimalformat.format(oilused.getCOUNT_MILEAGE()));
            
            
            //oilexport.setTotal_oil(decimalformat.format(oilused.getTotal_oil()));
            
            oilexportlist.add(oilexport);
        }

        if (oilexportlist.size() > 0) {
            for (int j = 0; j < sumList.size(); j++) {
                OilUsedExport oilexport = new OilUsedExport();
                OilUsed oilused = sumList.get(j);
                oilexport.setVehicle_code(getText("common.sum"));
                oilexport.setCountOilTotal(decimalformat.format(oilused.getCOUNT_OIL_TOTAL()==null?0.00:oilused.getCOUNT_OIL_TOTAL()));
                //oilexport.setTotal_oil(decimalformat.format(totalList.get(0).getTotal_oil()));
                oilexport.setCountMileage(decimalformat.format(oilused.getCOUNT_MILEAGE()==null?0.00:oilused.getCOUNT_MILEAGE()));
                oilexportlist.add(oilexport);
            }
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "tmp/" + UUIDGenerator.getUUID() + "OilUsed.xls";

            // add by jinp start
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // addd by jinp stop

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("油耗与里程统计(" + timeStr + ")");

            if(oilexportlist == null || oilexportlist.size()<1){
            	oilexportlist.add(new OilUsedExport());
            }
            
            excelExporter.putAutoExtendSheets("exportoilUsedbyCarNew", 0,
                    oilexportlist);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error("生成油耗分析导出文件时出错:", e);
            return ERROR;
        } catch (Exception e) {
            log.error("生成油耗分析导出文件时出错:", e);
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
        
        SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String datetimestr=pathDf.format(calendar.getTime());


        // 设置下载文件属性
		String fileName=URLEncoder.encode("油耗与里程统计","UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();	
        response.setHeader("Content-disposition",
                "attachment;filename="+fileName+"-"+datetimestr+".xls");
        
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            log.error("下载油耗分析导出文件时出错:", e);
            return ERROR;
        } catch (Exception e) {
            log.error("下载油耗分析导出文件时出错:", e);
            return ERROR;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            this.addOperationLog(formatLog(modelTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_OILUSED_EXPORT);
        }
        log.info(modelTitle+"结束");
        // 导出文件成功
        return null;
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, OilManage om) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != om) {
            if (null != om.getRefuel_id()) {
                OperateLogFormator.format(sb, "refuel_id", om.getRefuel_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 油耗分析页面
     * @return
     */
    public String readyPage() {
    	MDC.put("modulename", "[fuelanalysis]");
    	log.info("进入油耗分析页面");
        UserInfo user = getCurrentUser();
        if (null == oilused) {
            oilused = new OilUsed();
        }
        oilused.setOrganization_id(user.getOrganizationID());
        if(DateUtil.getMonthFirstDay1().equals(DateUtil.getCurrentDay()))
        //if(DateUtil.getMonthFirstDay1().equals("2013-08-01"))
        {
        	oilused.setCheck_time_code(DateUtil.getPreMonth());
        }
        else
        {
        	oilused.setCheck_time_code(DateUtil.getMonth());
        }
        //oilused.setCheck_time_code(DateUtil.getMonth());
        
        if(chooseorgid == null || chooseorgid.length() == 0) {
        	chooseorgid = user.getOrganizationID();
        }
        return SUCCESS;
    }

    /**
     * 转换Map
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List oilusedList, List sumList, int totalCountDay,
            String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        Map sumMap = new LinkedHashMap();
        DecimalFormat decimalformat = new DecimalFormat("0.00");

        for (int i = 0; i < oilusedList.size(); i++) {

            OilUsed s = (OilUsed) oilusedList.get(i);
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            cellMap.put("cell", new Object[] {
            		//(i + 1) + (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(pageIndex),//节油排名
            		//s.getRn(),
            		s.getVehicle_code(),
            		s.getVehicle_ln(),   //车牌号
            		s.getVehicle_vin(),//车辆VIN
//            		s.getDriver_name(),  //司机
            		decimalformat.format(s.getCheck_value()), //考核油耗
            		decimalformat.format(s.getHkm_oilused()==null?0.00:s.getHkm_oilused()), //百公里油耗
            		decimalformat.format(s.getMinusoil()),    //燃油差距   
            		decimalformat.format(s.getCOUNT_OIL_TOTAL()),//累计耗油量
//            		decimalformat.format(s.getCheck_value_mile()),//标准耗油量 
//            		decimalformat.format(s.getMax_oil()),//允许最高耗油量
//            		decimalformat.format(s.getSave_standard()),//节标准
//            		decimalformat.format(s.getOver_standard()),//超标准
            		 decimalformat.format(s.getCOUNT_MILEAGE()),//实际里程             		
                    s.getShort_allname(),
                    s.getOil_total(),
                    s.getMileage()
                    });

            mapList.add(cellMap);

        }
        OilUsed s = (OilUsed) sumList.get(0);
        sumMap.put("id", "sumid");
        sumMap.put("cell", new Object[] {
                getText("common.sum"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                decimalformat.format(s.getRefuel_amount() == null ? 0 : s
                        .getRefuel_amount()),
                decimalformat.format(s.getCOUNT_OIL_TOTAL() == null ? 0 : s
                        .getCOUNT_OIL_TOTAL()),
                decimalformat.format(s.getMinusamount() == null ? 0 : s
                        .getMinusamount()),
                decimalformat.format(s.getCOUNT_MILEAGE() == null ? 0 : s
                        .getCOUNT_MILEAGE()) });

        mapList.add(sumMap);

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /*
     * 油耗分析曲线图
     */
    public String getOilLineChart(String chooseorgid, String vehicle_vin,
            String check_time_code) {
        String xmlStr = "";
        log.debug("chooseorgid:" + chooseorgid);
        log.debug("vehicle_vin:" + vehicle_vin);
        log.debug("check_time_code:" + check_time_code);
        
        try {
        	HttpServletRequest request = (HttpServletRequest) ServletActionContext
            .getRequest();
            if (StringUtils.isNotEmpty(vehicle_vin)) {
                vehicle_vin = StringUtils.trim(vehicle_vin);
            }
            WebContext ctx = WebContextFactory.get();
            request = ctx.getHttpServletRequest();
        	MDC.put("modulename", "[fuelanalysis]");
                    
            // 取得session对象实例
            UserInfo user = (UserInfo) request.getSession().getAttribute(
                    Constants.USER_SESSION_KEY);
            OilUsed oilused = new OilUsed();
            oilused.setOrganization_id(chooseorgid);
            oilused.setVehicle_vin(vehicle_vin);
            oilused.setCheck_time_code(check_time_code);
            oilused.setEnterprise_id(user.getEntiID());
            log.info("[Organization_id:" + oilused.getOrganization_id() + ",Time_list:" + oilused.getCheck_time_code()
        			+ ",vehicle_vin:" + vehicle_vin +"]:油耗分析曲线图开始");
            List < OilUsed > countList = service.getObjects(
                    "OilUsed.getChartDate", oilused);
            xmlStr = "<chart xAxisName='时间(天)' rotateYAxisName='0'  yAxisName='百公里油耗&#13;(L/100km)' yAxisValuesStep='2' yAxisValuesPadding='15'"
                    + "baseFontSize='10' outCnvBaseFontSize='10' labelDisplay='ROTATE' slantLabels='1' rotateLabels='1' rotateValues ='0' valuePosition ='AUTO'"
                    + "alternateHGridColor='FCB541' alternateHGridAlpha='20' bgColor='FFFFFF' borderColor='FFFFFF' divLineColor='FCB541' "
                    + "divLineAlpha='50' canvasBorderColor='666666' baseFontColor='666666' lineColor='FCB541' chartRightMargin='50'>";

            int i = 0;
            int xpage = 0;
            int listSize = countList.size();
            if(listSize != 0) {
            	xpage = (int) (listSize / (14.1));
                if (xpage == 0) {
                    xpage = 1;
                }
                String nullFlag="0";
                for (OilUsed info : countList) {
                    // 横轴坐标只显示15个点
                    if (i % xpage == 0) {
                        xmlStr = xmlStr
                                + "<set showName='1' showValues='1' label='"
                                + info.getAlarm_day().replaceAll("-", "/")
                                + "' value='" + info.getCOUNT_OIL_TOTAL()
                                + "' isSliced='1' />";
                    } else {
                        xmlStr = xmlStr + "<set showName='0' showValue='1' label='"
                                + info.getAlarm_day().replaceAll("-", "/")
                                + "' value='" + info.getCOUNT_OIL_TOTAL()
                                + "' isSliced='1' />";
                    }
                    i++;
                    if(!info.getCOUNT_OIL_TOTAL().equals("0")){
                    	nullFlag="1";
                    }
                }
                List < OilUsed > infoList = service.getObjects(
                        "OilUsed.getChartInfo", oilused);
                String infoStr = infoList.get(0).getVehicle_ln() + "$"
                        + infoList.get(0).getVehicle_code() + "$"
                        + infoList.get(0).getShort_allname();
                
                String style="<styles>"+
        		"<definition>"+
        			"<style name='MyCaptionFontStyle' type='font' face='Verdana' size='12'/>"+
        		"</definition>"+
        		"<application>"+
        			"<apply toObject='ToolTip' styles='MyCaptionFontStyle' />"+
        		"</application>"+
        		"</styles>";
        		xmlStr = xmlStr +style;
                
                xmlStr = xmlStr + "</chart>";

                xmlStr = xmlStr + "|" + infoStr;
                
                xmlStr = xmlStr + "||" +nullFlag;
            } else {
            	xmlStr = null;
            }
            
            log.info("油耗分析曲线图结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("油耗分析曲线图异常",e);
            return null;
        }
        return xmlStr;
    }

    /*
     * 取随即颜色RGB值
     */
    public String generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        red = (red + Color.white.getRed()) / 2;
        green = (green + Color.white.getGreen()) / 2;
        blue = (blue + Color.white.getBlue()) / 2;
        String color = Integer.toHexString(red) + Integer.toHexString(green)
                + Integer.toHexString(blue);
        return color;
    }
    
    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public List < OilUsed > getSumList() {
        return sumList;
    }

    public void setSumList(List < OilUsed > sumList) {
        this.sumList = sumList;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getCheck_time_code() {
        return check_time_code;
    }

    public void setCheck_time_code(String check_time_code) {
        this.check_time_code = check_time_code;
    }

    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
    }

    public void setParams(HashMap params) {
        Params = params;
    }

    public HashMap getParams() {
        return Params;
    }

    public OilSet getOilset() {
        return oilset;
    }

    public void setOilset(OilSet oilset) {
        this.oilset = oilset;
    }

    public OilUsed getOilused() {
        return oilused;
    }

    public void setOilused(OilUsed oilused) {
        this.oilused = oilused;
    }

    public List < OilUsed > getOilusedList() {
        return oilusedList;
    }

    public void setOilusedList(List < OilUsed > oilusedList) {
        this.oilusedList = oilusedList;
    }

    public OckTimeSet getOcktimeset() {
        return ocktimeset;
    }

    public void setOcktimeset(OckTimeSet ocktimeset) {
        this.ocktimeset = ocktimeset;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public VehcileInfo getVehcileInfo() {
        return vehcileInfo;
    }

    public void setVehcileInfo(VehcileInfo vehcileInfo) {
        this.vehcileInfo = vehcileInfo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

	public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
}

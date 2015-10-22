package com.neusoft.clw.yunxing.stu.stride.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.rankingExport;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2012-3-14 3:21:42 PM
 */
public class StuRidingAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示消息 * */
    private String message = null;
    
    private String user_org_id;
    
    private RouteChart queryObj;
    
    private RouteChart exportObj;
    
    private List < RouteChart > strideResult;
    
    private Map map = new HashMap();

    public Service getService() {
        return service;
    }


    public void setService(Service service) {
        this.service = service;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getUser_org_id() {
        return user_org_id;
    }


    public void setUser_org_id(String user_org_id) {
        this.user_org_id = user_org_id;
    }


    public RouteChart getQueryObj() {
        return queryObj;
    }


    public void setQueryObj(RouteChart queryObj) {
        this.queryObj = queryObj;
    }


    public Map getMap() {
        return map;
    }


    public void setMap(Map map) {
        this.map = map;
    }


    public RouteChart getExportObj() {
        return exportObj;
    }


    public void setExportObj(RouteChart exportObj) {
        this.exportObj = exportObj;
    }


    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        
        user_org_id = getCurrentUser().getOrganizationID();
        queryObj = new RouteChart();
        //当天时间 update by fanxy 修改为默认 本周第一天到现在的时间范围
        //queryObj.setBegTime(DateUtil.getCurrentDay()+" 00:00:00");
        //queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");
        queryObj.setBegTime(DateUtil.getCurrentBeforeWeek());
        queryObj.setEndTime(DateUtil.getCurrentDay());
        return SUCCESS;
    }

    public String getRideInfo(){
        final String browseTitle = getText("menu2.xsccjl");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == queryObj) {
                queryObj = new RouteChart();
                //queryObj.setBegTime(DateUtil.getCurrentDay()+" 00:00:00");
                //queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");
                queryObj.setBegTime(DateUtil.getCurrentBeforeWeek()+" 00:00:00");
                queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");
                queryObj.setOrganization_id(user.getOrganizationID());
            }
            if("".equals(queryObj.getOrganization_id())){
            	queryObj.setOrganization_id(user.getOrganizationID());
            }
            //queryObj.setUser_organization_id(user.getOrganizationID());
            
            //重新设置时间的格式 0-23:59:59
            String beg_time_s = queryObj.getBegTime() + " 00:00:00";
            String end_time_s = queryObj.getEndTime() + " 23:59:59";          
            queryObj.setBegTime(beg_time_s);
            queryObj.setEndTime(end_time_s);
            
            /*log.info("查询条件如下：");
            log.info("车牌VIN->"+queryObj.getVIN());
            log.info("状态->"+queryObj.getSt_ride_flag());
            log.info("stu_name->"+queryObj.getStu_name());
            log.info("stu_shool->"+queryObj.getStu_school());
            log.info("stu_class->"+queryObj.getStu_class());
            log.info("site_id->"+queryObj.getSite_id());
            log.info("strideState->"+queryObj.getStrideState());
            log.info("开始时间->"+queryObj.getBegTime());
            log.info("结束时间->"+queryObj.getEndTime());*/

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            totalCount = service.getCount("StuRide.getCountStuRideInfoList", queryObj);

            strideResult = (List < RouteChart >) service.getObjectsByPage(
                    "StuRide.getStuRideInfoList1", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(strideResult, totalCount, pageIndex);// 转换map

            if (0 == strideResult.size()) {
                this.addActionMessage(getText("nodata.list"));
            }
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(browseTitle);
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STU_RIDE_QUR);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    public Map getPagination(List list, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RouteChart s = (RouteChart) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getId());
            /*cellMap.put("cell", new Object[] {
                    s.getStu_name(),
                    s.getSt_ride_flag(),
                    s.getStu_code(),
                    s.getStu_school(),
                    s.getStu_class(),
                    s.getRoute_name(),
                    s.getVehicle_ln(),
                    s.getDriver_name(),
                    s.getSichen_name(),
                    s.getTerminal_time(),
                    s.getMesg_flag()
                    });*/
           /* if(s.getPlanVeh() == null || "".equals(s.getPlanVeh())){
            	s.setPlanVeh("未绑定车辆");
            	s.setStrideState("-");
            }else{
            	RouteChart planVin = (RouteChart)service.getObject("StuRide.getVehicleByVin",s.getPlanVeh());
            	if(planVin != null)
            		s.setPlanVeh(planVin.getVehicle_ln());
            	else
            		s.setPlanVeh("");
	            if(s.getStrideState().equals("1"))
                	s.setStrideState("乘车正常");
                else if(s.getStrideState().equals("0"))
                	s.setStrideState("乘车异常");
            }*/
            cellMap.put("cell", new Object[] {
                    s.getStu_name(),
                    //s.getStu_school(),
                    //s.getStu_class(),
                    s.getStu_code(),
                    s.getStu_card_id(),
                    s.getShort_name(),
                    //s.getSite_name(),
                    //s.getRoute_name(),
                    //s.getSt_ride_flag(),
                    s.getVehicle_code(),
                    s.getVehicle_ln(),
                    (s.getDriver_name()==null?"未分配":s.getDriver_name()),
                    s.getPlanVeh(),
                    s.getStrideState(),
                    s.getTerminal_time(),
                    (s.getZonename()==null || "".equals( s.getZonename())) ? "定位无效":s.getZonename() 
                    //(s.getSichen_name()==null?"未登录":(s.getSichen_name()==""?"未登录":s.getSichen_name()))
                    });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 导出学生乘车记录
     * @return
     */
    public String exportStRide() throws UnsupportedEncodingException {
        String exportTitle = getText("员工刷卡记录");
        List < RouteChart > list = new ArrayList < RouteChart >();
        UserInfo user = getCurrentUser();
        if("".equals(queryObj.getOrganization_id())){
        	queryObj.setOrganization_id(user.getOrganizationID());
        }
        String beg_time_s = queryObj.getBegTime() + " 00:00:00";
        String end_time_s = queryObj.getEndTime() + " 23:59:59";
        queryObj.setBegTime(beg_time_s);
        queryObj.setEndTime(end_time_s);
//        queryObj.setSortname("TERMINAL_TIME");
//        queryObj.setSortorder("desc");
        try {
        	//queryObj.setUser_organization_id(getCurrentUser().getOrganizationID());
            list = (List < RouteChart >) service.getObjects(
                    "StuRide.getStuRideInfoList1", queryObj);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }
        try{
	        for (int i = 0; i < list.size(); i++) {
	            RouteChart s = (RouteChart) list.get(i);
	            if(s.getPlanVeh() == null || "".equals(s.getPlanVeh())){
	            	s.setPlanVeh("未绑定车辆");
	            	s.setStrideState("-");
	            }else{
	            	RouteChart planVin = (RouteChart)service.getObject("StuRide.getVehicleByVin",s.getPlanVeh());
	            	if(planVin != null)
	            		s.setPlanVeh(planVin.getVehicle_ln());
	            	else
	            		s.setPlanVeh("");
		            if(s.getStrideState().equals("1"))
	                	s.setStrideState("乘车正常");
	                else if(s.getStrideState().equals("0"))
	                	s.setStrideState("乘车异常");
	            }
	            if(s.getZonename()==null || "".equals( s.getZonename())){
	            	s.setZonename("定位无效");
	            }
	        }
        }catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }
        DecimalFormat decimalformat = new DecimalFormat("0");
        if (list.size() > 0) {
        	RouteChart oilexport = new RouteChart();
                //CarRunHistory oilused = totalList.get(0);
                oilexport.setStu_name(getText("总计刷卡:"));
                oilexport.setStu_code(decimalformat.format(list.size())+"次");	
                oilexport.setStu_card_id("");
                oilexport.setShort_name("");
                oilexport.setVehicle_code("");
                oilexport.setVehicle_ln("");
                oilexport.setDriver_name("");
                oilexport.setTerminal_time("");
                oilexport.setZonename("");
               list.add(oilexport);
            }
           
        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "StRide.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(exportTitle+"("+queryObj.getBegTime()+"——"+queryObj.getEndTime()+")");

            excelExporter.putAutoExtendSheets("exportStRide", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
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
	
        // 设置下载文件属性
		String fileName=URLEncoder.encode("员工刷卡记录","UTF-8");
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename="+fileName+"-"+DateUtil.getCurrentDayTime()+".xls");
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
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return null;
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
            this.addOperationLog("员工刷卡记录导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STU_RIDE_EXP);
        }
        // 导出文件成功
        return null;
    }


    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
}

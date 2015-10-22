package com.neusoft.clw.infomanage.holidaymanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.holidaymanage.domain.HolidayInfo;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class HolidayManageAction extends PaginationAction{
    /** service共通类 */
    private transient Service service;

    /** 显示数据list **/
    private List < HolidayInfo > holidayList;

    private String holidayId = "";
    
    /** 修改数据用 **/
    private HolidayInfo holidayInfo;

    private StudentInfo studentInfo = new StudentInfo();
    
    private String studentName = "";
    
    /** 显示消息 **/
    private String message = null;

    private final String FORBID = "forbid";

    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    private Map map = new HashMap();

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List < HolidayInfo > getHolidayList() {
        return holidayList;
    }

    public void setHolidayList(List < HolidayInfo > holidayList) {
        this.holidayList = holidayList;
    }

    public HolidayInfo getHolidayInfo() {
        return holidayInfo;
    }

    public void setHolidayInfo(HolidayInfo holidayInfo) {
        this.holidayInfo = holidayInfo;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 浏览假期信息
     * @return
     */
    public String holidayBrowse() {
        final String browseTitle = "浏览假期信息";
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == holidayInfo) {
                holidayInfo = new HolidayInfo();
            }
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            UserInfo user = getCurrentUser();
            
            holidayInfo.setEnterprise_id(user.getEntiID());
            holidayInfo.setOrganization_id(user.getOrganizationID());
            holidayInfo.setSortname(sortName);
            holidayInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("HolidayManage.getCount", holidayInfo);
            holidayList = (List < HolidayInfo >) service.getObjectsByPage(
                    "HolidayManage.getInfos", holidayInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(holidayList, totalCount, pageIndex, rpNum);// 转换map

            if (holidayList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_HOLIDAYMANAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String addBefore() {
        holidayInfo = new HolidayInfo();
        holidayInfo.setHoliday_start_time(DateUtil.getCurrentDay()+" 00:00:00");
        holidayInfo.setHoliday_end_time(DateUtil.getCurrentDay()+" 23:59:59");
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    
    public String stuListReady() {
        return SUCCESS;
    }
    
    public Map getPaginationForStu(List list, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            StudentInfo s = (StudentInfo) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getStudent_id());
            cellMap.put("cell", new Object[] {
                    s.getStudent_code(),
                    s.getStudent_name(),
                    s.getStudent_school(),
                    s.getStudent_class()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public String getStudentList() {
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if(null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setOrganization_id(user.getOrganizationID());

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            studentInfo.setSortname(sortName);
            studentInfo.setSortorder(sortOrder);

            totalCount = service.getCount("HolidayManage.getCountStuInfos", studentInfo);

            List < StudentInfo > stuResult = (List < StudentInfo >) service.getObjectsByPage(
                    "HolidayManage.getStudentInfos", studentInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPaginationForStu(stuResult, totalCount, pageIndex);// 转换map

            if (0 == stuResult.size()) {
                this.addActionMessage(getText("nodata.list"));
            }
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.info("query student list error:", e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 添加假期
     */
    public String add() {
        if (null == holidayInfo) {
            return addBefore();
        }
        final String addTitle = "填加假期信息";
        log.info(addTitle);
        try {
            UserInfo user = getCurrentUser();
            holidayInfo.setCreater(user.getUserID());
            holidayInfo.setModifier(user.getUserID());
            // 请假
            holidayInfo.setHoliday_status("86");
            
            List<HolidayInfo> retList = new ArrayList<HolidayInfo>();
            // 初始化参数表
            try {
                retList = (List < HolidayInfo >) service.getObjects("HolidayManage.getVehicleInfo", holidayInfo.getStudent_id().trim());
            } catch (BusinessException e) {
                log.error("query vehicle info error:" + e.getMessage());
            }
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            int i = 0;
            for(HolidayInfo tmp : retList) {
                if(tmp.getVehicleVin() != null && tmp.getVehicleVin().length() >0 
                        && tmp.getSimCardNumber() != null && tmp.getSimCardNumber().length() >0
                        && tmp.getRoute_id() != null && tmp.getRoute_id().length() > 0) {
                    holidayInfo.setVehicleVin(tmp.getVehicleVin());
                    holidayInfo.setSimCardNumber(tmp.getSimCardNumber());
                    holidayInfo.setRoute_id(tmp.getRoute_id());
                    String resultCode = sendCommandUtils.noticeCoreHoliday(holidayInfo);
                    if("0".equals(resultCode)) {
                        // 下发成功
                        i++;
                    }
                }
            }
            
            if(i > 0) {
                service.insert("HolidayManage.insertHolidayInfo", holidayInfo);
            } else {
                setMessage("holiday.noticecore.error");
                return ERROR;
            }
            
        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("add holiday error:" + e.getMessage());
            setMessage("holiday.noticecore.error");
            return ERROR;
        }
        setMessage("holiday.addsuccess.message");
        
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HOLIDAYMANAGE_ADD);
        return SUCCESS;
    }

    /**
     * 修改假期页面
     */
    public String editBefore() {
        final String editBefTitle = getText("holiday.editbefore.title");
        log.info(editBefTitle);
        try {
            if (null == holidayInfo) {
                holidayInfo = new HolidayInfo();
                holidayInfo.setHoliday_id(holidayId);
            }
            holidayInfo = (HolidayInfo) service.getObject(
                    "HolidayManage.getHolidayInfo", holidayInfo);
            if (null != message) {
                addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 修改假期
     */
    public String updateHoliday() {
        if (null == holidayInfo) {
            return editBefore();
        }
        final String editTitle = getText("holiday.update");
        try {
            UserInfo user = getCurrentUser();
            holidayInfo.setCreater(user.getUserID());
            holidayInfo.setModifier(user.getUserID());
            // 请假
            holidayInfo.setHoliday_status("86");

            List<HolidayInfo> retList = new ArrayList<HolidayInfo>();
            // 初始化参数表
            try {
                retList = (List < HolidayInfo >) service.getObjects("HolidayManage.getVehicleInfo", holidayInfo.getStudent_id().trim());
            } catch (BusinessException e) {
                log.error("query vehicle info error:" + e.getMessage());
            }
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            int i = 0;
            for(HolidayInfo tmp : retList) {
                if(tmp.getVehicleVin() != null && tmp.getVehicleVin().length() >0 
                        && tmp.getSimCardNumber() != null && tmp.getSimCardNumber().length() >0
                        && tmp.getRoute_id() != null && tmp.getRoute_id().length() > 0) {
                    holidayInfo.setVehicleVin(tmp.getVehicleVin());
                    holidayInfo.setSimCardNumber(tmp.getSimCardNumber());
                    holidayInfo.setRoute_id(tmp.getRoute_id());
                    String resultCode = sendCommandUtils.noticeCoreHoliday(holidayInfo);
                    if("0".equals(resultCode)) {
                        // 下发成功
                        i++;
                    }
                }
            }
            
            if(i > 0) {
                service.update("HolidayManage.updateHolidayTimebyHolidayId", holidayInfo);
            } else {
                setMessage("holiday.noticecore.error");
                return ERROR;
            }
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("holiday.editsuccess.message");
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HOLIDAYMANAGE_MODIFY);
        return SUCCESS;
    }

    /**
     * 注销假期
     */
    public String cancelHoliday() {
        if (null == holidayInfo) {
            return editBefore();
        }
        try {
            UserInfo user = getCurrentUser();

            holidayInfo.setCreater(user.getUserID());
            holidayInfo.setModifier(user.getUserID());
            // 销假
            holidayInfo.setHoliday_status("87");

            List<HolidayInfo> retList = new ArrayList<HolidayInfo>();
            // 初始化参数表
            try {
                retList = (List < HolidayInfo >) service.getObjects("HolidayManage.getVehicleInfo", holidayInfo.getStudent_id().trim());
            } catch (BusinessException e) {
                log.error("query vehicle info error:" + e.getMessage());
            }
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            int i = 0;
            for(HolidayInfo tmp : retList) {
                if(tmp.getVehicleVin() != null && tmp.getVehicleVin().length() >0 
                        && tmp.getSimCardNumber() != null && tmp.getSimCardNumber().length() >0
                        && tmp.getRoute_id() != null && tmp.getRoute_id().length() > 0) {
                    holidayInfo.setVehicleVin(tmp.getVehicleVin());
                    holidayInfo.setSimCardNumber(tmp.getSimCardNumber());
                    holidayInfo.setRoute_id(tmp.getRoute_id());
                    String resultCode = sendCommandUtils.noticeCoreHoliday(holidayInfo);
                    if("0".equals(resultCode)) {
                        // 下发成功
                        i++;
                    }
                }
            }
            
            if(i > 0) {
                service.update("HolidayManage.cancelByHolidayId", holidayInfo);
            } else {
                setMessage("holiday.noticecore.error");
                return ERROR;
            }
        } catch (BusinessException e) {
            log.error("cancel holiday error:", e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("holiday.delete.success");
        // 设置操作描述
        this.addOperationLog(formatLog("销假", null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_HOLIDAYMANAGE_DELETE);

        return SUCCESS;

    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, HolidayInfo holidayObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != holidayObj) {
            if (null != holidayObj.getHoliday_id()) {
                OperateLogFormator.format(sb, "holidayid", holidayObj
                        .getHoliday_id());
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
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex, String rpNum) {
//        Map<String, String> statusMap = new HashMap<String,String>();
//        statusMap.put("0", "请假中");
//        statusMap.put("1", "已销假");
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            HolidayInfo s = (HolidayInfo) list.get(i);
//            s.setHoliday_status(statusMap.get(s.getHoliday_flag()));
            
            if("0".equals(s.getHoliday_flag())) {
                // 请假状态无销假时间
                s.setModify_time("请假中");
                s.setHoliday_status("请假中");
            } else if("CLW_XC".equals(s.getModifier())) {
                // 终端销假
                s.setHoliday_status("终端销假");
            } else {
                // 管理员销假
                s.setHoliday_status("管理员销假");
            }
            
            if(null == s.getVehicleLn() || s.getVehicleLn().length() == 0 ) {
                s.setVehicleLn("未乘坐");
            }
            if(null == s.getRouteName() || s.getRouteName().length() == 0) {
                s.setRouteName("未规划");
            }
            
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getHoliday_id());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getStudent_name(),
                    s.getStudent_code(),
                    s.getVehicleLn(),
                    s.getRouteName(),
                    s.getHoliday_start_time(),
                    s.getHoliday_end_time(), 
                    s.getModify_time(),
                    s.getHoliday_reason(),
                    s.getHoliday_status()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public String getStudentNameById() {
        try {
            studentName = (String) service.getObject("HolidayManage.getStunameById", studentInfo.getStudent_id());
        } catch (Exception e) {
            log.error("get student name error：", e);
            return ERROR;
        }
        return ERROR;
    }
    
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

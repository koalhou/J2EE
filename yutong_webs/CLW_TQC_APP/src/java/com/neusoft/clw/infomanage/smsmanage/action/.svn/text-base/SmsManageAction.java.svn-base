/*******************************************************************************
 * @(#)SmsManageAction.java 2012-3-14
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.smsmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.smsmanageservice.SmsManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.smsmanage.domain.MessageInfo;
import com.neusoft.clw.infomanage.smsmanage.domain.SmsInfo;
import com.neusoft.clw.infomanage.studentmanage.action.DateFormatUtil;
import com.neusoft.clw.infomanage.studentmanage.action.PasswordConfirmAction;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 短信提醒Action类
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-14 下午04:05:15
 */
public class SmsManageAction extends PaginationAction{
    /** service共通类 */
    private transient Service service;
    
    private transient SmsManageService smsManageService;

    private static final String FORBID_ACTION = "forbid";
    
    /** 学生信息 **/
    private StudentInfo studentInfo;
    
    /** 显示消息 **/
    private String message = null;
    
    /** 家长短信配置list **/
    private List<MessageInfo> parentsList = new ArrayList<MessageInfo>();
    
    /** 其他联系人短信配置list **/
    private List<MessageInfo> othersList = new ArrayList<MessageInfo>();
    
    /** 删除家长ID值 **/
    private String delParentId = "";
    
    /** 删除其他联系人ID值 **/
    private String otherPersonId = "";
    
    /** 短信配置列表list **/
    private List < StudentInfo > messageBrowseList;
    
    private Map map = new HashMap();
    
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    
    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        UserInfo user = getCurrentUser();
        if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return FORBID_ACTION;
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            StudentInfo s = (StudentInfo) list.get(i);
            if(null == s.getVehicleLn() || s.getVehicleLn().length() == 0 ) {
                s.setVehicleLn("未乘坐");
            }
            if(null == s.getRouteName() || s.getRouteName().length() == 0) {
                s.setRouteName("未规划");
            }
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getStudent_id());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getStudent_name(),
                    s.getStudent_code(),
                    s.getStudent_school(), 
                    s.getStudent_class(),
                    s.getVehicleLn(),
                    s.getRouteName(),
                    s.getCreate_time()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 浏览信息
     * @return
     */
    public String messageBrowse() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            UserInfo user = getCurrentUser();
            
            if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
                // 未经过认证
                return SUCCESS;
            }
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setOrganization_id(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            studentInfo.setSortname(sortName);
            studentInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("SmsManage.getCount", studentInfo);
            messageBrowseList = (List < StudentInfo >) service.getObjectsByPage(
                    "SmsManage.getInfos", studentInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(messageBrowseList, totalCount, pageIndex, rpNum);// 转换map

            if (messageBrowseList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog("查询短信提醒");
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMESSAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("query message error:" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 添加短信提醒初始化
     * @return
     */
    public String addBefore() {
        UserInfo user = getCurrentUser();
        
        if (!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return ERROR;
        }
        
        if (null != message) {
            addActionMessage(message);
        }

        return SUCCESS;
    }
    
    /**
     * 填加家长联系人
     * @return
     */
    public String addParent() {
        MessageInfo messageInfo = new MessageInfo();
        // 设置ID
        messageInfo.setMessageId(UUIDGenerator.getUUID());
        // 设置截止日期
        messageInfo.setEndTime(DateFormatUtil.getYYYYMMDD(1, 0, 0));
        parentsList.add(messageInfo);

        return SUCCESS;
    }

    /**
     * 删除家长联系人
     * @return
     */
    public String deleteParent() {
        for (int i = 0; i < parentsList.size(); i++) {
            MessageInfo messageInfo = parentsList.get(i);
            if (messageInfo.getMessageId().equals(delParentId)) {
                parentsList.remove(i);
                break;
            }
        }
        return SUCCESS;
    }
    
    /**
     * 验证联系人是否配置提醒类型
     */
    private boolean checkParentsList() {
        boolean ret = false;
        for (int i = 0; i < parentsList.size(); i++) {
            MessageInfo messageInfo = parentsList.get(i);
            if(messageInfo.isAnXinWarnChoiceFlag() || messageInfo.isDownNormalChoiceFlag() ||
               messageInfo.isDownNotChargeChoiceFlag() || messageInfo.isDownNotSetSiteChoiceFlag() ||
               messageInfo.isUpNormalChoiceFlag() || messageInfo.isUpNotChargeChoiceFlag() ||
               messageInfo.isUpNotSetSiteChoiceFlag()) {
                continue;
            } else {
                ret = true;
                break;
            }
        }
        return ret;
    }
    
    /**
     * 添加短信提醒配置信息
     * @return
     */
    public String addSms() {
        if(checkParentsList()) {
            addActionError("请确认各联系人是否至少选择一种短信提醒类型！");
            return ERROR;
        }
        
        try {
            UserInfo user = getCurrentUser();
            SmsInfo smsInfo = new SmsInfo();
            // 设置学生ID
            smsInfo.setStudent_id(studentInfo.getStudent_id());
            smsInfo.setCreator(user.getUserID());
            // 设置联系人list
            smsInfo.setParentsList(parentsList);
            // 添加短信提醒配置
            smsManageService.addMsgConfig(smsInfo);
        } catch (BusinessException e) {
            log.error("add sms error:", e);
            return ERROR;
        } finally {
            setMessage("sms.add.success");
            
            this.addOperationLog("添加短信提醒信息");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMESSAGE_ADD);
        }
        return SUCCESS;
    }
    
    /**
     * 选择学生
     * @return
     */
    public String stuListReady() {
        return SUCCESS;
    }
    
    /**
     * 拼装学生信息
     * @param list
     * @param totalCount
     * @param pageIndex
     * @return
     */
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
    
    /**
     * 获取学生信息
     * @return
     */
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

            totalCount = service.getCount("SmsManage.getCountStuInfos", studentInfo);

            List < StudentInfo > stuResult = (List < StudentInfo >) service.getObjectsByPage(
                    "SmsManage.getStudentInfos", studentInfo, (Integer
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
     * 分析联系人
     */
    private void analyseParents(){
        for(MessageInfo messageInfo : parentsList) {
            messageInfo.setMessageId(UUIDGenerator.getUUID());
            String[] eventTypes = messageInfo.getEventType().split(",");
            
            // 初始化默认配置
            messageInfo.setUpNotChargeChoiceFlag(false);
            messageInfo.setDownNotChargeChoiceFlag(false);
            messageInfo.setUpNotSetSiteChoiceFlag(false);
            messageInfo.setDownNotSetSiteChoiceFlag(false);
            messageInfo.setUpNormalChoiceFlag(false);
            messageInfo.setDownNormalChoiceFlag(false);
            messageInfo.setAnXinWarnChoiceFlag(false);
            
            for(int i = 0; i < eventTypes.length; i++ ) {
                if("0".equals(eventTypes[i])) {
                    // 未刷卡上车
                    messageInfo.setUpNotChargeChoiceFlag(true);
                } else if("1".equals(eventTypes[i])) {
                    // 未刷卡下车
                    messageInfo.setDownNotChargeChoiceFlag(true);
                } else if("2".equals(eventTypes[i])) {
                    // 未在规定站点上车
                    messageInfo.setUpNotSetSiteChoiceFlag(true);
                } else if("3".equals(eventTypes[i])) {
                    // 未在规定站点上车
                    messageInfo.setDownNotSetSiteChoiceFlag(true);
                } else if("4".equals(eventTypes[i])) {
                    // 正常上车
                    messageInfo.setUpNormalChoiceFlag(true);
                } else if("5".equals(eventTypes[i])) {
                    // 正常下车
                    messageInfo.setDownNormalChoiceFlag(true);
                } else if("6".equals(eventTypes[i])) {
                    // 安芯提醒
                    messageInfo.setAnXinWarnChoiceFlag(true);
                }
            }
        }
    }
    
    /**
     * 修改短信提醒
     */
    public String editBefore() {
        UserInfo user = getCurrentUser();
        
        if (!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return ERROR;
        }
        
        if (null != message) {
            addActionMessage(message);
        }
        
        try {
            if (null != studentInfo) {
                // 根据学生编号获取学生信息
                studentInfo = (StudentInfo) service.getObject("StudentManage.getStudentInfo", studentInfo);
                // 根据学生编号获取联系人信息
                parentsList = (List<MessageInfo>) service.getObjects("SmsManage.getSmsInfoById", studentInfo.getStudent_id());
                // 分析联系人信息
                analyseParents();
            } else {
                setMessage("info.data.notexsist");
                return ERROR;
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("query sms by student id error:" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 添加短信提醒配置信息
     * @return
     */
    public String editSms() {
        if(checkParentsList()) {
            addActionError("请确认各联系人是否至少选择一种短信提醒类型！");
            return ERROR;
        }
        
        try {
            UserInfo user = getCurrentUser();
            SmsInfo smsInfo = new SmsInfo();
            // 设置学生ID
            smsInfo.setStudent_id(studentInfo.getStudent_id());
            smsInfo.setCreator(user.getUserID());
            // 设置联系人list
            smsInfo.setParentsList(parentsList);
            // 添加短信提醒配置
            smsManageService.addMsgConfig(smsInfo);
            setMessage("sms.edit.success");
        } catch (BusinessException e) {
            log.error("edit sms error:", e);
            return ERROR;
        } finally {
            this.addOperationLog("修改短信提醒信息");
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMESSAGE_MODIFY);
        }
        return SUCCESS;
    }
    
    /**
     * 短信提醒配置信息删除
     * @return
     */
    public String deleteSms() {
        try {
            service.update("SmsManage.deleteSmsByStuId", studentInfo.getStudent_id());
        } catch (BusinessException e) {
            log.error("delete message config error:", e);
            addActionError(e.getMessage());
            return ERROR;
        } finally {
            setMessage("sms.delete.success");
            // 设置操作描述
            this.addOperationLog("删除短信提醒信息");
            // 设置操作类型
            this.setOperationType(Constants.DELETE);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMESSAGE_DELETE);
        }
        
        return SUCCESS;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SmsManageService getSmsManageService() {
        return smsManageService;
    }

    public void setSmsManageService(SmsManageService smsManageService) {
        this.smsManageService = smsManageService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public List < MessageInfo > getParentsList() {
        return parentsList;
    }

    public void setParentsList(List < MessageInfo > parentsList) {
        this.parentsList = parentsList;
    }

    public List < MessageInfo > getOthersList() {
        return othersList;
    }

    public void setOthersList(List < MessageInfo > othersList) {
        this.othersList = othersList;
    }

    public String getDelParentId() {
        return delParentId;
    }

    public void setDelParentId(String delParentId) {
        this.delParentId = delParentId;
    }

    public List < StudentInfo > getMessageBrowseList() {
        return messageBrowseList;
    }

    public void setMessageBrowseList(List < StudentInfo > messageBrowseList) {
        this.messageBrowseList = messageBrowseList;
    }

    public String getOtherPersonId() {
        return otherPersonId;
    }

    public void setOtherPersonId(String otherPersonId) {
        this.otherPersonId = otherPersonId;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
/*******************************************************************************
 * @(#)SmsReminderAction.java 2012-7-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.smsreminder.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.smsreminderservice.SmsReminderService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.smsreminder.domain.MessageInfo;
import com.neusoft.clw.infomanage.smsreminder.domain.SmsReminderInfo;
import com.neusoft.clw.infomanage.studentmanage.action.PasswordConfirmAction;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-4 下午03:22:58
 */
public class SmsReminderAction extends PaginationAction{
    /** service共通类 */
    private transient Service service;
    private transient SmsReminderService smsReminderService;
    
    private static final String FORBID_ACTION = "forbid";
    
    private SmsReminderInfo smsReminderInfo;
    
    /** 学生信息 **/
    private StudentInfo studentInfo;
    
    /** 列表数据存储map **/
    private Map map = new HashMap();
    
    /** 显示消息 **/
    private String message = null;

    /** 短信配置列表list **/
    private List < SmsReminderInfo > messageBrowseList;
    
    /** 家长短信配置list **/
    private List<MessageInfo> parentsList = new ArrayList<MessageInfo>();
    
    public static final Map < String, String > RELATIVE_MAP = new HashMap < String, String >();
    
    static {
        RELATIVE_MAP.put("0", "父亲");
        RELATIVE_MAP.put("1", "母亲");
        RELATIVE_MAP.put("2", "爷爷");
        RELATIVE_MAP.put("3", "奶奶");
        RELATIVE_MAP.put("4", "外公");
        RELATIVE_MAP.put("5", "外婆");
        RELATIVE_MAP.put("6", "其他");
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
            SmsReminderInfo s = (SmsReminderInfo) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getStudentId());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getStudentName(),
                    s.getStudentCode(),
                    s.getStudentSchool(), 
                    s.getStudentClass(),
                    s.getOrderTime(),
                    s.getPhone1(),
                    s.getPhone2(),
                    s.getPhone3()});
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
            if (null == smsReminderInfo) {
                smsReminderInfo = new SmsReminderInfo();
            }
            UserInfo user = getCurrentUser();
            
            if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
                // 未经过认证
                return SUCCESS;
            }
            smsReminderInfo.setEnterpriseId(user.getEntiID());
            smsReminderInfo.setOrganizationId(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            smsReminderInfo.setSortname(sortName);
            smsReminderInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("SmsReminder.getCount", smsReminderInfo);
            messageBrowseList = (List < SmsReminderInfo >) service.getObjectsByPage(
                    "SmsReminder.getInfos", smsReminderInfo, (Integer
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
     * 分析联系人
     */
    private void analyseParents(){
        for(MessageInfo messageInfo : parentsList) {
            char[] eventTypes = messageInfo.getEventType().toCharArray();
            // 初始化默认配置
            messageInfo.setUpNotChargeChoiceFlag(false);
            messageInfo.setDownNotChargeChoiceFlag(false);
            messageInfo.setUpNotSetSiteChoiceFlag(false);
            messageInfo.setDownNotSetSiteChoiceFlag(false);
            messageInfo.setUpNormalChoiceFlag(false);
            messageInfo.setDownNormalChoiceFlag(false);
            messageInfo.setAnXinWarnChoiceFlag(false);
            messageInfo.setRelativeType(RELATIVE_MAP.get(messageInfo.getRelativeType()));
            
            if(null != eventTypes && eventTypes.length >=7) {
                if('1' == eventTypes[0]) {
                    // 未刷卡上车
                    messageInfo.setUpNotChargeChoiceFlag(true);
                }
                if('1' == eventTypes[1]) {
                    // 未刷卡下车
                    messageInfo.setDownNotChargeChoiceFlag(true);
                }
                if('1' == eventTypes[2]) {
                    // 未在规定站点上车
                    messageInfo.setUpNotSetSiteChoiceFlag(true);
                }
                if('1' == eventTypes[3]) {
                    // 未在规定站点下车
                    messageInfo.setDownNotSetSiteChoiceFlag(true);
                }
                if('1' == eventTypes[4]) {
                    // 正常上车
                    messageInfo.setUpNormalChoiceFlag(true);
                }
                if('1' == eventTypes[5]) {
                    // 正常下车
                    messageInfo.setDownNormalChoiceFlag(true);
                }
                if('1' == eventTypes[6]) {
                    // 安芯提醒
                    messageInfo.setAnXinWarnChoiceFlag(true);
                }
            }
        }
    }
    
    /**
     * 配置短信提醒
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
                parentsList = (List<MessageInfo>) service.getObjects("SmsReminder.getSmsInfoById", studentInfo.getStudent_id());
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
     * 设置短信提醒类型
     */
    private void setParentsEventType() {
        for (MessageInfo messageInfo : parentsList) {
            String eventType = String
                    .format("%s%s%s%s%s%s%s%s%s%s", messageInfo
                            .isUpNotChargeChoiceFlag() ? "1" : "0", messageInfo
                            .isDownNotChargeChoiceFlag() ? "1" : "0",
                            messageInfo.isUpNotSetSiteChoiceFlag() ? "1" : "0",
                            messageInfo.isDownNotSetSiteChoiceFlag() ? "1"
                                    : "0",
                            messageInfo.isUpNormalChoiceFlag() ? "1" : "0",
                            messageInfo.isDownNormalChoiceFlag() ? "1" : "0",
                            messageInfo.isAnXinWarnChoiceFlag() ? "1" : "0",
                            "0", "0", "0");
            messageInfo.setEventType(eventType);
        }
    }
    
    /**
     * 添加短信提醒配置信息
     * @return
     */
    public String configSms() {
        try {
            setParentsEventType();
            // 配置短信提醒配置
            smsReminderService.smsReminderConfig(parentsList);
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

    public SmsReminderService getSmsReminderService() {
        return smsReminderService;
    }

    public void setSmsReminderService(SmsReminderService smsReminderService) {
        this.smsReminderService = smsReminderService;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public SmsReminderInfo getSmsReminderInfo() {
        return smsReminderInfo;
    }

    public void setSmsReminderInfo(SmsReminderInfo smsReminderInfo) {
        this.smsReminderInfo = smsReminderInfo;
    }

    public List < SmsReminderInfo > getMessageBrowseList() {
        return messageBrowseList;
    }

    public void setMessageBrowseList(List < SmsReminderInfo > messageBrowseList) {
        this.messageBrowseList = messageBrowseList;
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
}

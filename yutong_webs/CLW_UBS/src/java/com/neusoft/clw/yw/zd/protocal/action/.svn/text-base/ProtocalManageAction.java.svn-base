package com.neusoft.clw.yw.zd.protocal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.zd.protocal.ds.ProtocalInfo;
import com.neusoft.clw.yw.zd.protocal.ds.ValueInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端协议管理
 * @author JinPeng
 */
public class ProtocalManageAction extends PaginationAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;

    /** 终端协议ID **/
    private String protocalId = "";

    /** 终端协议名称（查询条件） **/
    private String protocalName = "";

    /** 终端厂商ID（查询条件） **/
    private String oemId = "";

    /** 终端型号ID（查询条件） **/
    private String typeId = "";

    /** 终端协议信息 **/
    private ProtocalInfo protocalInfo = new ProtocalInfo();

    /** 终端协议列表 **/
    private List < ProtocalInfo > protocalList = new ArrayList < ProtocalInfo >();

    /** 终端厂家信息 **/
    private List < ValueInfo > oemList = new ArrayList < ValueInfo >();

    /** 终端型号信息 **/
    private List < ValueInfo > typeList = new ArrayList < ValueInfo >();

    /** 终端厂家信息 **/
    private Map < String, String > oemInfosMap = new HashMap < String, String >();

    /** 终端型号信息 **/
    private Map < String, String > typeInfosMap = new HashMap < String, String >();

    /**
     * 获取终端厂家列表值
     * @return
     */
    private boolean getOemSelect() {
        if (oemInfosMap.isEmpty()) {
            try {
                oemList = (List < ValueInfo >) service.getObjects(
                        "ProtocalManage.getOemInfos", null);
                for (ValueInfo valueInfo : oemList) {
                    oemInfosMap.put(valueInfo.getSelectKey(), valueInfo
                            .getSelectValue());
                }
            } catch (BusinessException e) {
                log.error("Query oem infos error:" + e.getMessage());
                return false;
            } catch (Exception e) {
                log.error("Query oem infos error:" + e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * 页面初始化/查询操作
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("protocal.manage.location"));

        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemList.size() == 0) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("oemId", oemId);
            map.put("typeId", typeId);
            map.put("protocalName", protocalName);

            int totalCount = 0;
            totalCount = service.getCount("ProtocalManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            // 查询终端协议信息
            protocalList = (List < ProtocalInfo >) service.getObjectsByPage(
                    "ProtocalManage.getProtocalInfos", map, pageObj
                            .getStartOfPage(), pageSize);

            if (oemId != "") {
                // 终端厂商ID不为空时，查询终端型号信息
                typeList = service.getObjects("ProtocalManage.getTypeInfos",
                        oemId);
                for (ValueInfo valueInfo : typeList) {
                    typeInfosMap.put(valueInfo.getSelectKey(), valueInfo
                            .getSelectValue());
                }
            }

            if (protocalList != null && protocalList.size() == 0) {
                // 无终端协议信息
                addActionError(getText("common.no.data"));
                return ERROR;
            } else {
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_ZD_PROTOCAL_QUERY_MID);
            addOperationLog("查询终端协议信息");
        }

        return SUCCESS;
    }

    /**
     * 填加终端协议信息页面初始化
     * @return
     */
    public String addProtocalBefore() {
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemList.size() == 0) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 填加终端协议
     * @return
     */
    public String addProtocal() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (protocalInfo != null) {
            // 设置创建用户
            protocalInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            protocalInfo.setProtocalId(UUIDGenerator.getUUID());
        }

        try {
            // 创建终端协议信息
            service.insert("ProtocalManage.insertProtocal", protocalInfo);
            setMessage("protocal.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert protocal info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert protocal info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_ZD_PROTOCAL_ADD_MID);
            addOperationLog("新建终端协议信息");
        }

        return SUCCESS;
    }

    /**
     * 查询终端协议信息
     * @return
     */
    public String queryProtocalInfo() {
        if (protocalId == "" || protocalId == null) {
            return ERROR;
        } else {
            try {
                // 查询终端厂家信息
                protocalInfo = (ProtocalInfo) service.getObject(
                        "ProtocalManage.getProtocalInfoById", protocalId);

                if (!getOemSelect()) {
                    // 终端厂家列表初始化异常时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

                if (oemList.size() == 0) {
                    // 提示先创建终端厂家信息
                    super.addActionError(getText("device.oem.require"));
                    return ERROR;
                }

                if (protocalInfo.getOemId() != "") {
                    // 终端厂商ID不为空时，查询终端型号信息
                    typeList = service.getObjects(
                            "ProtocalManage.getTypeInfos", protocalInfo
                                    .getOemId());
                    for (ValueInfo valueInfo : typeList) {
                        typeInfosMap.put(valueInfo.getSelectKey(), valueInfo
                                .getSelectValue());
                    }
                }

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query protocal detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query protocal detail error:" + e.getMessage());
                return ERROR;
            }
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 更新终端协议信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (protocalInfo != null) {
            // 设置修改用户
            protocalInfo.setModifier(currentUser.getUserID());
            // 设置终端协议ID值
            protocalInfo.setProtocalId(protocalId);
        }

        try {
            // 修改终端型号信息
            service.update("ProtocalManage.updateProtocalInfo", protocalInfo);
            setMessage("protocal.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update protocal info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update protocal info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_ZD_PROTOCAL_MODIFY_MID);
            addOperationLog("修改终端协议信息");
        }

        return SUCCESS;
    }

    /**
     * 删除终端协议信息
     * @return
     */
    public String delete() {
        if (protocalId == "" || protocalId == null) {
            return ERROR;
        }

        try {
            int countNum = service.getCount(
                    "ProtocalManage.getProtocalTerminalCount", protocalId);

            if (countNum > 0) {
                setMessage("protocal.delete.notpermission");
                log.error("The protocal is using");
                return ERROR;
            }

            // 删除终端协议信息
            service.update("ProtocalManage.updateProtocalValid", protocalId);
            setMessage("protocal.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete protocal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete protocal error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_ZD_PROTOCAL_DELETE_MID);
            addOperationLog("删除终端协议信息");
        }

        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map < String, String > getOemInfosMap() {
        return oemInfosMap;
    }

    public void setOemInfosMap(Map < String, String > oemInfosMap) {
        this.oemInfosMap = oemInfosMap;
    }

    public Map < String, String > getTypeInfosMap() {
        return typeInfosMap;
    }

    public void setTypeInfosMap(Map < String, String > typeInfosMap) {
        this.typeInfosMap = typeInfosMap;
    }

    public String getProtocalId() {
        return protocalId;
    }

    public void setProtocalId(String protocalId) {
        this.protocalId = protocalId;
    }

    public String getProtocalName() {
        return protocalName;
    }

    public void setProtocalName(String protocalName) {
        this.protocalName = protocalName;
    }

    public List < ProtocalInfo > getProtocalList() {
        return protocalList;
    }

    public void setProtocalList(List < ProtocalInfo > protocalList) {
        this.protocalList = protocalList;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getTypeId() {
        return typeId;
    }

    public ProtocalInfo getProtocalInfo() {
        return protocalInfo;
    }

    public void setProtocalInfo(ProtocalInfo protocalInfo) {
        this.protocalInfo = protocalInfo;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List < ValueInfo > getOemList() {
        return oemList;
    }

    public void setOemList(List < ValueInfo > oemList) {
        this.oemList = oemList;
    }

    public List < ValueInfo > getTypeList() {
        return typeList;
    }

    public void setTypeList(List < ValueInfo > typeList) {
        this.typeList = typeList;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }
}

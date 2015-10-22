package com.neusoft.clw.yw.zd.device.action;

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
import com.neusoft.clw.yw.zd.device.ds.DeviceInfo;
import com.neusoft.clw.yw.zd.oem.ds.OemInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端型号管理
 * @author JinPeng
 */
public class DeviceManageAction extends PaginationAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;

    /** 终端型号名称（查询条件） **/
    private String deviceName = "";

    /** 终端厂商ID（查询条件） **/
    private String oemId = "";

    /** 终端型号ID **/
    private String typeId = "";

    /** 终端型号信息 **/
    private DeviceInfo deviceInfo = new DeviceInfo();

    /** 终端厂家列表 **/
    private List < OemInfo > oemList = new ArrayList < OemInfo >();

    /** 终端型号信息列表 **/
    private List < DeviceInfo > deviceList = new ArrayList < DeviceInfo >();

    /**
     * 获取终端厂家列表
     */
    private boolean getOemInfo() {
        if (oemList.size() == 0) {
            try {
                oemList = (List < OemInfo >) service.getObjects(
                        "DeviceManage.getOemInfos", "");
            } catch (BusinessException e) {
                log.error("Get oem infos error:" + e.getMessage());
                return false;
            } catch (Exception e) {
                log.error("Get oem infos error:" + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 终端设备管理页面初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("device.manage.location"));

        if (!getOemInfo()) {
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
            map.put("deviceName", deviceName);
            map.put("oemId", oemId);

            int totalCount = 0;
            totalCount = service.getCount("DeviceManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            // 查询终端型号信息
            deviceList = (List < DeviceInfo >) service.getObjectsByPage(
                    "DeviceManage.getDeviceInfos", map, pageObj
                            .getStartOfPage(), pageSize);

            if (deviceList != null && deviceList.size() == 0) {
                // 无终端型号信息
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
            log.error("Query device infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query device infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_ZD_DEVICE_QUERY_MID);
            addOperationLog("查询终端型号信息");
        }

        return SUCCESS;
    }

    /**
     * 填加终端型号信息页面初始化
     * @return
     */
    public String addDeviceBefore() {
        if (!getOemInfo()) {
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
     * 填加终端型号信息
     * @return
     */
    public String addDevice() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (deviceInfo != null) {
            // 设置创建用户
            deviceInfo.setCreator(currentUser.getUserID());
            // 设置主键值
            deviceInfo.setTypeId(UUIDGenerator.getUUID());
        }

        try {
            // 创建终端型号信息
            service.insert("DeviceManage.insertDevice", deviceInfo);
            setMessage("device.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Inser device error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Inser device error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_ZD_DEVICE_ADD_MID);
            addOperationLog("新建终端型号信息");
        }

        return SUCCESS;
    }

    /**
     * 查询终端型号信息
     * @return
     */
    public String queryDeviceInfo() {
        if (typeId == "" || typeId == null) {
            return ERROR;
        } else {
            try {
                // 查询终端厂家信息
                deviceInfo = (DeviceInfo) service.getObject(
                        "DeviceManage.getDeviceInfoById", typeId);

                if (!getOemInfo()) {
                    // 终端厂家列表初始化异常时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }
            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query device detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query device detail error:" + e.getMessage());
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
     * 更新终端型号信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (deviceInfo != null) {
            // 设置修改用户
            deviceInfo.setModifier(currentUser.getUserID());
            // 设置终端型号ID值
            deviceInfo.setTypeId(typeId);
        }

        try {
            // 修改终端型号信息
            service.update("DeviceManage.updateDeviceInfo", deviceInfo);
            setMessage("device.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update device info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update device info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_ZD_DEVICE_MODIFY_MID);
            addOperationLog("修改终端型号信息");
        }

        return SUCCESS;
    }

    /**
     * 删除终端型号信息
     * @return
     */
    public String delete() {
        if (typeId == "" || typeId == null) {
            return ERROR;
        }

        try {
            int countNum = service.getCount(
                    "DeviceManage.getTypeProtocalCount", typeId);

            if (countNum > 0) {
                setMessage("device.delete.notpermission");
                log.error("The device is using");
                return ERROR;
            }

            // 删除终端型号信息
            service.update("DeviceManage.updateDeviceValid", typeId);
            setMessage("device.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete device info error:" + e.getMessage());
            setTypeId(typeId);
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete device info error:" + e.getMessage());
            setTypeId(typeId);
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_ZD_DEVICE_DELETE_MID);
            addOperationLog("删除终端型号信息");
        }

        return SUCCESS;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public List < DeviceInfo > getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List < DeviceInfo > deviceList) {
        this.deviceList = deviceList;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public List < OemInfo > getOemList() {
        return oemList;
    }

    public void setOemList(List < OemInfo > oemList) {
        this.oemList = oemList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

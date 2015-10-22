package com.neusoft.clw.yw.cl.vehicleRegister.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.BizTypeInfo;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;
import com.neusoft.clw.yw.cl.vehicleRegister.service.VehicleRegisterService;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 车辆注册action
 * @author JinPeng
 */
public class VehicleRegisterAction extends PaginationAction {
    private transient Service service;

    private transient VehicleRegisterService vehicleRegisterService;

    /** 安节通应用编码 **/
    private static final String ANJT_APPLY_ID = "4002";
    
    /** 海南应用编码 **/
    private static final String HAINAN_APPLY_ID = "4003";
    
    /** 北京应用编码 **/
    private static final String BEIJING_APPLY_ID = "4004";
    
    /** 主键ID * */
    private String vehicleRegisterId = "";

    /** 终端ID * */
    private String terminalId = "";

    /** 终端ID ,修改时保存老的ID* */
    private String terminalOldId = "";

    /** 车辆ID **/
    private String vehicleId = "";
    
    /** 车牌号(查询条件) **/
    private String vehicleLn = "";
    
    /** 车辆VIN号(查询条件) **/
    private String vehicleVin = "";

    /** 终端号(查询条件) * */
    private String terminalCode = "";

    /** SIM卡号(查询条件) * */
    private String simCardNumber = "";

    /** 手机号(查询条件) **/
    private String cellPhone = "";
    
    /** 旧SIM卡号 **/
    private String oldSimCardNumber = "";
    
    /** 企业编码（查询条件） **/
    private String enterpriseCode = "";

    /** 旧企业ID **/
    private String oldEnterpriseId = "";
    
    /** 出厂状态(查询条件) * */
    private String deliveryFlag = "";

    /** 安装方式（查询条件） **/
    private String fixType = "";

    /** (查询条件) * */
    private String start_time = "";

    /** (查询条件) * */
    private String end_time = "";

    /** 车辆注册详细信息 * */
    private VehicleRegisterInfo vehicleRegisterInfo = new VehicleRegisterInfo();

    /** 车辆注册信息列表 * */
    private List < VehicleRegisterInfo > vehicleRegisterList = new ArrayList < VehicleRegisterInfo >();

    /** 提示信息 * */
    private String message = null;

    /** 行业应用列表 * */
    private List < BizTypeInfo > bizTypeList = new ArrayList < BizTypeInfo >();

    /** 缴费状态 * */
    private Map < String, String > feeMap = new HashMap < String, String >();

    /** 出厂状态 * */
    private Map < String, String > fixTypeMap = new HashMap < String, String >();

    /** 出厂状态 * */
    private Map < String, String > deliveryFlagMap = new HashMap < String, String >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /** JSON 返回 注册信息MAP **/
    private Map vehicleRegistMap = new HashMap();
    
    /**
     * 初始化安装方式
     */
    private void getFixTypeMapList() {
        if (fixTypeMap != null && fixTypeMap.isEmpty()) {
            fixTypeMap.put("1", "后装");
            fixTypeMap.put("0", "前装");
        }
    }

    /**
     * 初始化出厂状态
     */
    private void getDeliveryFlagMapList() {
        if (deliveryFlagMap != null && deliveryFlagMap.isEmpty()) {
            deliveryFlagMap.put("1", "已出厂");
            deliveryFlagMap.put("0", "未出厂");
        }
    }

    /**
     * 初始化缴费状态
     */
    private void getFeeMapList() {
        if (feeMap != null && feeMap.isEmpty()) {
            feeMap.put("1", "欠费");
            feeMap.put("0", "正常");
        }
    }

    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("vehicle.register.location"));
        
        getDeliveryFlagMapList();
        getFixTypeMapList();
        
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 组装JSON数据
     * @param vehicleRegisterList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List vehicleRegisterList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        getFixTypeMapList();
        getDeliveryFlagMapList();
        
        for (int i = 0; i < vehicleRegisterList.size(); i++) {
            VehicleRegisterInfo vehicleRegisterInfo = (VehicleRegisterInfo) vehicleRegisterList.get(i);

            if(null == vehicleRegisterInfo.getVehicleLn() || vehicleRegisterInfo.getVehicleLn().length() == 0) {
                vehicleRegisterInfo.setVehicleLn("暂无车牌");
            }
            // 设置出厂状态
            vehicleRegisterInfo.setDeliveryFlag(deliveryFlagMap.get(vehicleRegisterInfo.getDeliveryFlag()));
            // 设置安装方式
            vehicleRegisterInfo.setFixType(fixTypeMap.get(vehicleRegisterInfo.getFixType()));
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", vehicleRegisterInfo.getTerminalId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    vehicleRegisterInfo.getVehicleLn(),
                    vehicleRegisterInfo.getVehicleVin(),
                    vehicleRegisterInfo.getTerminalCode(),
                    vehicleRegisterInfo.getSimCardNumber(),
                    vehicleRegisterInfo.getCellPhone(),
                    vehicleRegisterInfo.getEnterpriseCode(),
                    vehicleRegisterInfo.getEntipriseName(),
                    vehicleRegisterInfo.getRegistrant(),
                    vehicleRegisterInfo.getRegistrationTime(),
                    vehicleRegisterInfo.getModifier(),
                    vehicleRegisterInfo.getModifyTime(),
                    vehicleRegisterInfo.getDeliveryFlag(),
                    vehicleRegisterInfo.getFixType(),
                    vehicleRegisterInfo.getLongitude(),
                    vehicleRegisterInfo.getLatitude()
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");
        
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        
        try {
            getDeliveryFlagMapList();
            getFixTypeMapList();

            Map < String, String > map = new HashMap < String, String >();
            map.put("vehicleLn", SearchUtil.formatSpecialChar(vehicleLn));
            map.put("vehicleVin", SearchUtil.formatSpecialChar(vehicleVin));
            map.put("terminalCode", SearchUtil.formatSpecialChar(terminalCode));
            map.put("simCardNumber", SearchUtil.formatSpecialChar(simCardNumber));
            map.put("cellPhone", SearchUtil.formatSpecialChar(cellPhone));
            map.put("deliveryFlag", deliveryFlag);
            map.put("start_time", start_time);
            map.put("end_time", end_time);
            map.put("enterpriseCode", SearchUtil.formatSpecialChar(enterpriseCode));
            map.put("fixType", fixType);
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("VehicleRegister.getCount", map);

            vehicleRegisterList = service.getObjectsByPage(
                    "VehicleRegister.getVehicleRegisterInfos", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (vehicleRegisterList != null && vehicleRegisterList.size() == 0) {
                // 无车辆注册信息
                addActionMessage(getText("common.no.data"));
//                return ERROR;
            }

            // 显示提示信息
//            if (null != message) {
//                addActionMessage(getText(message));
//            }

            this.vehicleRegistMap = getPagination(vehicleRegisterList, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered vehicles error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered vehicles error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CL_VEHICLEREGISTER_QUERY_MID);
            addOperationLog("查询已注册车辆");
        }

        return SUCCESS;
    }

    public String gpsBrowse() {
        log.debug("getLatitude:" + vehicleRegisterInfo.getLatitude());
        log.debug("getLongitude:" + vehicleRegisterInfo.getLongitude());
        log.debug("getVehicleLn:" + vehicleRegisterInfo.getVehicleLn());

        try {
            if (StringUtils.isNotEmpty(vehicleRegisterInfo.getVehicleLn())) {
                BASE64Decoder decoder = new BASE64Decoder();
                String vehicle_ln;

                vehicle_ln = new String(decoder
                        .decodeBuffer(vehicleRegisterInfo.getVehicleLn()),
                        "UTF-8");

                vehicleRegisterInfo.setVehicleLn(vehicle_ln);
                log.debug("getLatitude:" + vehicleRegisterInfo.getLatitude());
                log.debug("getLongitude:" + vehicleRegisterInfo.getLongitude());
                log.debug("getVehicleLn:" + vehicleRegisterInfo.getVehicleLn());

            }
        } catch (UnsupportedEncodingException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
            return null;
        } catch (IOException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
            return null;
        }
        return SUCCESS;
    }

    /**
     * 填加页面初始化
     * @return
     */
    public String addVehicleRegisterBefore() {
        try {
            // 获取行业应用列表
            bizTypeList = service.getObjects("VehicleRegister.getBizTypes",
                    null);

            for (BizTypeInfo tmp : bizTypeList) {
                if (ANJT_APPLY_ID.equals(tmp.getBizId()) || BEIJING_APPLY_ID.equals(tmp.getBizId())) {
                    tmp.setChoiceFlag(true);
                }
            }

            getFeeMapList();
            getDeliveryFlagMapList();
            getFixTypeMapList();
            // 显示提示信息
            if (null != message) {
                addActionError(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get business types error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get business types error:" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 创建车辆注册信息
     * @return
     */
    public String addVehicleRegister() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 设置行业应用相关信息
        for (BizTypeInfo bizTypeInfo : bizTypeList) {
            // 设置主键
            bizTypeInfo.setTbizId(UUIDGenerator.getUUID());
            // 设置终端号
            bizTypeInfo.setTerminalCode(vehicleRegisterInfo.getTerminalCode());
            // 设置车辆VIN号
            bizTypeInfo.setVehicleVin(vehicleRegisterInfo.getVehicleVin());
            // 设置创建用户ID
            bizTypeInfo.setCreater(currentUser.getUserID());
        }

        // 设置行业应用信息
        vehicleRegisterInfo.setBizTypeList(bizTypeList);
        // 设置修改用户ID
        vehicleRegisterInfo.setModifier(currentUser.getUserID());

        try {
            // 创建车辆注册信息
            vehicleRegisterService.insertVehicleRegister(vehicleRegisterInfo);
            setMessage("vehicleregister.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert register info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert register info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CL_VEHICLEREGISTER_ADD_MID);
            addOperationLog("新建车辆注册信息");
        }

        return SUCCESS;
    }

    /**
     * 获取当前行业应用选中状态
     * @param bizTypeAll 全部行业应用
     * @param bizTypeSelect 当前选择的行业应用
     * @param terminalCode 终端号
     * @return
     */
    private List < BizTypeInfo > getBizTypeSelected(
            List < BizTypeInfo > bizTypeAll,
            List < BizTypeInfo > bizTypeSelect, VehicleRegisterInfo vehicleRegisterInfo) {
        // 循环当前选择的行业应用
        for (BizTypeInfo tmpSelect : bizTypeSelect) {
            // 循环全部的行业应用
            for (BizTypeInfo tmpAll : bizTypeAll) {
                if (tmpSelect.getBizId().equals(tmpAll.getBizId())) {
                    // 设置为选中状态
                    tmpAll.setChoiceFlag(true);
                    break;
                }
            }
        }

        for (BizTypeInfo tmp : bizTypeAll) {
            // 设置终端号
            tmp.setTerminalCode(vehicleRegisterInfo.getTerminalCode());
            // 设置车辆VIN号
            tmp.setVehicleVin(vehicleRegisterInfo.getVehicleVin());
        }
        return bizTypeAll;
    }

    /**
     * 查询车辆注册信息
     * @return
     */
    public String queryVehicleRegister() {
        try {
            // 获取车辆注册信息
            vehicleRegisterInfo = (VehicleRegisterInfo) service.getObject(
                    "VehicleRegister.getVehicleRegisterInfoById", terminalId);

            // 获取全部行业应用信息
            bizTypeList = service.getObjects("VehicleRegister.getBizTypes",
                    null);

            // 获取当前选择的行业应用
            List < BizTypeInfo > bizTypeSelect = service.getObjects(
                    "VehicleRegister.getBizTypeInfoById", terminalId);

            // 获取当前行业应用选中状态
            bizTypeList = getBizTypeSelected(bizTypeList, bizTypeSelect,
                    vehicleRegisterInfo);

            terminalOldId = vehicleRegisterInfo.getTerminalId();
            
            oldEnterpriseId = vehicleRegisterInfo.getEntipriseId();
            
            oldSimCardNumber = vehicleRegisterInfo.getSimCardNumber();

            // 获取缴费信息
            getFeeMapList();

            getDeliveryFlagMapList();

            getFixTypeMapList();

            // 设置车辆ID
            setVehicleId(vehicleRegisterInfo.getVehicleId());

            // 显示提示信息
            if (null != message) {
                addActionError(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query register detail error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query register detail error:" + e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 更新车辆注册信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 设置行业应用相关信息
        for (BizTypeInfo bizTypeInfo : bizTypeList) {
            // 设置主键
            bizTypeInfo.setTbizId(UUIDGenerator.getUUID());
            // 设置创建用户ID
            bizTypeInfo.setCreater(currentUser.getUserID());
        }

        // 设置行业应用信息
        vehicleRegisterInfo.setBizTypeList(bizTypeList);
        // 设置修改用户ID
        vehicleRegisterInfo.setModifier(currentUser.getUserID());
        // 设置终端ID
        vehicleRegisterInfo.setTerminalId(terminalId);
        // 设置车辆ID
        vehicleRegisterInfo.setVehicleId(vehicleId);

        vehicleRegisterInfo.setTerminalOldId(terminalOldId);

        vehicleRegisterInfo.setOldEnterpriseId(oldEnterpriseId);
        
        vehicleRegisterInfo.setOldSimCardNumber(oldSimCardNumber);
        
        log.debug("terminalOldId:" + terminalOldId);
        log.debug("terminalId:" + vehicleRegisterInfo.getTerminalId());
        log.debug("terminalCode:" + vehicleRegisterInfo.getTerminalCode());
        log.debug("getTerminalId:" + vehicleRegisterInfo.getTerminalId());
        log.debug("simCardNumber:" + vehicleRegisterInfo.getSimCardNumber());
        log.debug("terminalCode:" + vehicleRegisterInfo.getTerminalCode());
        log.debug("getVehicleVin:" + vehicleRegisterInfo.getVehicleVin());
        log.debug("deliveryFlag:" + vehicleRegisterInfo.getDeliveryFlag());
        log.debug("registrationTime:"
                + vehicleRegisterInfo.getRegistrationTime());

        try {
            // 更新车辆注册信息
            vehicleRegisterService.updateVehicleRegister(vehicleRegisterInfo);
            setMessage("vehicleregister.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update register info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update register info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CL_VEHICLEREGISTER_MODIFY_MID);
            addOperationLog("修改车辆注册信息");
        }

        return SUCCESS;
    }

    /**
     * 删除车辆注册信息
     * @return
     */
    public String delete() {
        // 设置终端ID
        vehicleRegisterInfo.setTerminalId(terminalId);
        // 设置车辆ID
        vehicleRegisterInfo.setVehicleId(vehicleId);

        vehicleRegisterInfo.setOldEnterpriseId(oldEnterpriseId);
        
        try {
            // 删除车辆注册信息
            vehicleRegisterService.deleteVehicleRegister(vehicleRegisterInfo);
            setMessage("vehicleregister.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete register info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete register info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CL_VEHICLEREGISTER_DELETE_MID);
            addOperationLog("删除车辆注册信息");
        }
        return SUCCESS;
    }

    /**
     * 获取行业信息
     */
    private void getBizTypeAttribute() {
        try {
            // 获取行业应用列表
            bizTypeList = service.getObjects("VehicleRegister.getBizTypes",
                    null);
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get business types error:" + e.getMessage());
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get business types error:" + e.getMessage());
        }
    }

    /**
     * 车辆注册信息导入初始化
     * @return
     */
    public String importVehicleRegisterBefore() {
        getBizTypeAttribute();

        for (BizTypeInfo tmp : bizTypeList) {
            if (ANJT_APPLY_ID.equals(tmp.getBizId()) || BEIJING_APPLY_ID.equals(tmp.getBizId()) ) {
                tmp.setChoiceFlag(true);
            }
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 获取文件内容错误
     * @return
     */
    private String getFileContentError(List < IEErrorMessage > list) {
        String errMsg = "";
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), tmp.getMessage());
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }

    /**
     * 获取文件内容列表
     * @return
     */
    private List < VehicleRegisterInfo > formatVehicleRegisterInfos(
            List < VehicleRegisterInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < VehicleRegisterInfo > ret = new ArrayList < VehicleRegisterInfo >();

        for (VehicleRegisterInfo tmp : list) {
            if (tmp.getVehicleVin() != null
                    && tmp.getVehicleVin().length() == 0
                    && tmp.getTerminalCode() != null
                    && tmp.getTerminalCode().length() == 0
                    && tmp.getSimCardNumber() != null
                    && tmp.getSimCardNumber().length() == 0
                    && tmp.getFeeFlag() != null
                    && tmp.getFeeFlag().length() == 0
                    && tmp.getDeliveryFlag() != null
                    && tmp.getDeliveryFlag().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                List < BizTypeInfo > bizList = new ArrayList < BizTypeInfo >();

                // 设置行业应用相关信息
                for (BizTypeInfo bizTypeInfo : bizTypeList) {
                    BizTypeInfo newBizTypeInfo = new BizTypeInfo();

                    // 设置主键
                    newBizTypeInfo.setTbizId(UUIDGenerator.getUUID());
                    // 设置创建用户ID
                    newBizTypeInfo.setCreater(currentUser.getUserID());
                    // 设置终端号
                    newBizTypeInfo.setTerminalCode(tmp.getTerminalCode());
                    // 设置车辆VIN号
                    newBizTypeInfo.setVehicleVin(tmp.getVehicleVin());
                    // 设置业务ID
                    newBizTypeInfo.setBizId(bizTypeInfo.getBizId());
                    // 设置是否选择
                    newBizTypeInfo.setChoiceFlag(bizTypeInfo.isChoiceFlag());

                    bizList.add(newBizTypeInfo);
                }
                // 设置行业应用信息
                tmp.setBizTypeList(bizList);
                // 设置修改用户ID
                tmp.setModifier(currentUser.getUserID());
                // 设置企业ID
                tmp.setEntipriseId(vehicleRegisterInfo.getEntipriseId());
                // 设置车主用户ID
                tmp.setUserId(vehicleRegisterInfo.getUserId());
                // 添加到list中
                ret.add(tmp);
            }
        }

        return ret;
    }

    /**
     * 导入车辆注册信息
     * @return
     */
    public String importVehicleRegister() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            getBizTypeAttribute();
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            getBizTypeAttribute();
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < VehicleRegisterInfo > list = new ArrayList < VehicleRegisterInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                setMessage("file.import.error");
                log.error("Import file error:" + e.getMessage());
            }

            list = excelImplortor.getSheetData("importVehicleRegister", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                getBizTypeAttribute();
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            getBizTypeAttribute();
            addActionError(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 格式化数据
        list = formatVehicleRegisterInfos(list);

        if (list.size() == 0) {
            getBizTypeAttribute();
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                // 导入车辆注册信息
                String ret = (String) vehicleRegisterService
                        .importVehicleRegisterInfos(list);
                if ("import_done".equals(ret)) {
                    setMessage("file.import.success");
                } else {
                    addActionError("由于车、SIM卡、终端数据信息不完全，以下车辆信息的数据未导入成功，请核实数据。VIN号："
                            + ret);
                    return ERROR;
                }

            } catch (BusinessException e) {
                getBizTypeAttribute();
                addActionError(getText("info.db.error"));
                log.error("Import vehicle register error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                getBizTypeAttribute();
                addActionError(getText("info.db.error"));
                log.error("Import vehicle register error:" + e.getMessage());
                return ERROR;
            } finally {
                setOperationType(Constants.IMPORT,
                        ModuleId.CLW_M_CL_VEHICLEREGISTER_IMPORT_MID);
                addOperationLog("导入车辆注册信息");
            }
        }

        return SUCCESS;
    }

    /**
     * 导出车辆注册信息
     * @return
     */
    public String exportVehicleRegister() {
        Map < String, String > map = new HashMap < String, String >();

        map.put("vehicleVin", vehicleVin);
        map.put("terminalCode", terminalCode);
        map.put("simCardNumber", simCardNumber);
        map.put("deliveryFlag", deliveryFlag);
        map.put("start_time", start_time);
        map.put("end_time", end_time);
        map.put("fixType", fixType);
        map.put("enterpriseCode", enterpriseCode);

        log.debug("vehicleVin:" + vehicleVin);
        log.debug("terminalCode:" + terminalCode);
        log.debug("simCardNumber:" + simCardNumber);
        log.debug("deliveryFlag:" + deliveryFlag);
        log.debug("start_time:" + start_time);
        log.debug("end_time:" + end_time);

        List < VehicleRegisterInfo > list = new ArrayList < VehicleRegisterInfo >();
        try {
            list = service.getObjects(
                    "VehicleRegister.getVehicleRegisterInfos", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export vehicle register error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export vehicle register error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID()
                    + "VehicleRegister.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("车辆注册信息");

            excelExporter.putAutoExtendSheets("exportVehicleRegister", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export vehicle register error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export vehicle register error:" + e.getMessage());
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
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-disposition",
                "attachment;filename=VehicleRegister.xls");
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
            log.error("Export vehicle register error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export vehicle register error:" + e.getMessage());
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
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_CL_VEHICLEREGISTER_EXPORT_MID);
            addOperationLog("导出车辆注册信息");
        }
        // 导出文件成功
        return null;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public VehicleRegisterService getVehicleRegisterService() {
        return vehicleRegisterService;
    }

    public void setVehicleRegisterService(
            VehicleRegisterService vehicleRegisterService) {
        this.vehicleRegisterService = vehicleRegisterService;
    }

    public String getVehicleRegisterId() {
        return vehicleRegisterId;
    }

    public void setVehicleRegisterId(String vehicleRegisterId) {
        this.vehicleRegisterId = vehicleRegisterId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public VehicleRegisterInfo getVehicleRegisterInfo() {
        return vehicleRegisterInfo;
    }

    public void setVehicleRegisterInfo(VehicleRegisterInfo vehicleRegisterInfo) {
        this.vehicleRegisterInfo = vehicleRegisterInfo;
    }

    public List < VehicleRegisterInfo > getVehicleRegisterList() {
        return vehicleRegisterList;
    }

    public void setVehicleRegisterList(
            List < VehicleRegisterInfo > vehicleRegisterList) {
        this.vehicleRegisterList = vehicleRegisterList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List < BizTypeInfo > getBizTypeList() {
        return bizTypeList;
    }

    public void setBizTypeList(List < BizTypeInfo > bizTypeList) {
        this.bizTypeList = bizTypeList;
    }

    public Map < String, String > getFeeMap() {
        return feeMap;
    }

    public void setFeeMap(Map < String, String > feeMap) {
        this.feeMap = feeMap;
    }

    public Map < String, String > getFixTypeMap() {
        return fixTypeMap;
    }

    public void setFixTypeMap(Map < String, String > fixTypeMap) {
        this.fixTypeMap = fixTypeMap;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public Map < String, String > getDeliveryFlagMap() {
        return deliveryFlagMap;
    }

    public void setDeliveryFlagMap(Map < String, String > deliveryFlagMap) {
        this.deliveryFlagMap = deliveryFlagMap;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public String getFixType() {
        return fixType;
    }

    public void setFixType(String fixType) {
        this.fixType = fixType;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTerminalOldId() {
        return terminalOldId;
    }

    public void setTerminalOldId(String terminalOldId) {
        this.terminalOldId = terminalOldId;
    }

    public Map getVehicleRegistMap() {
        return vehicleRegistMap;
    }

    public void setVehicleRegistMap(Map vehicleRegistMap) {
        this.vehicleRegistMap = vehicleRegistMap;
    }

    public String getOldEnterpriseId() {
        return oldEnterpriseId;
    }

    public void setOldEnterpriseId(String oldEnterpriseId) {
        this.oldEnterpriseId = oldEnterpriseId;
    }

    public String getOldSimCardNumber() {
        return oldSimCardNumber;
    }

    public void setOldSimCardNumber(String oldSimCardNumber) {
        this.oldSimCardNumber = oldSimCardNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

	public String getVehicleLn() {
		return vehicleLn;
	}

	public void setVehicleLn(String vehicleLn) {
		this.vehicleLn = vehicleLn;
	}
}

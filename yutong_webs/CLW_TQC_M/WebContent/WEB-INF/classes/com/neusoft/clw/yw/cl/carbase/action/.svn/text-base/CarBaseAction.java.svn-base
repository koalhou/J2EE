package com.neusoft.clw.yw.cl.carbase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.cl.carbase.ds.CarBaseInfo;
import com.neusoft.clw.yw.cl.carbase.service.CarBaseService;
import com.neusoft.clw.yw.cl.illdrive.ds.HarmdefDataInfo;
import com.neusoft.clw.yw.common.ds.CommonMapBean;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

public class CarBaseAction extends PaginationAction {
    private transient Service service;

    private transient CarBaseService carBaseService;

    private List < CarBaseInfo > pageList = new ArrayList < CarBaseInfo >();

    private List < BaseInfo > vehicleTypeList = new ArrayList < BaseInfo >();

    private List < BaseInfo > engineTypeList = new ArrayList < BaseInfo >();

    private CarBaseInfo carBaseInfo = new CarBaseInfo();

    /** 车型品牌 **/
    private Map < String, String > carTypeBrandMap = new HashMap < String, String >();

    /** 发动机品牌 **/
    private Map < String, String > carEngineBrandMap = new HashMap < String, String >();

    /** 发动机类型 **/
    private Map < String, String > engineMap = new HashMap < String, String >();

    /** 车型 **/
    private Map < String, String > vehicleTypeMap = new HashMap < String, String >();

    /** 不良驾驶定义 **/
    private Map < String, String > illDriveMap = new HashMap < String, String >();

    /** 车辆用途 **/
    private Map < String, String > businessTypeMap = new HashMap < String, String >();

    private String vehicle_vin;

    // add by jinp start
    private String oldVehicleVin;

    // add by jinp stop

    private String vehicle_ln;

    private String vehicle_id;

    private String message;

    private String popup_code;

    private String popup_name;

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("carbase.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(2);
            map.put("vehicle_vin", SearchUtil.formatSpecialChar(vehicle_vin));
            map.put("vehicle_ln", SearchUtil.formatSpecialChar(vehicle_ln));

            int totalSize = service.getCount("CarBase.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < CarBaseInfo >) service.getObjectsByPage(
                    "CarBase.selectCarBaseInfo", map, pageObj.getStartOfPage(),
                    pageSize));
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("查询车辆信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询车辆信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CL_VEHICLE_QUERY_MID);
            addOperationLog("查询车辆信息");
        }

        return SUCCESS;
    }

    public String getVehicleType() {
        Map < String, Object > map = new HashMap < String, Object >(2);
        map.put("popup_code", SqlStringUtil.getNull(popup_code));
        map.put("popup_name", SqlStringUtil.getNull(popup_name));
        try {
            vehicleTypeList = service.getObjects("CarBase.getVehicleTypeList",
                    map);

        } catch (BusinessException e) {
            log.error("车型列表查询发生异常，异常原因：" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("车型列表查询发生异常，异常原因：" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String getEngineType() {
        Map < String, Object > map = new HashMap < String, Object >(2);
        map.put("popup_code", SqlStringUtil.getNull(popup_code));
        map.put("popup_name", SqlStringUtil.getNull(popup_name));
        try {
            engineTypeList = service.getObjects("CarBase.getEngineTypeList",
                    map);

        } catch (BusinessException e) {
            log.error("车型列表查询发生异常，异常原因：" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("车型列表查询发生异常，异常原因：" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String gotoadd() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        List < HarmdefDataInfo > list2 = new ArrayList < HarmdefDataInfo >();

        try {
            list2 = service.getObjects("CarBase.getIllDriveList", null);
            for (HarmdefDataInfo harmdefDataInfo : list2) {
                illDriveMap.put(harmdefDataInfo.getDef_id(), harmdefDataInfo
                        .getDef_name());
            }
            list = service.getObjects("CarBase.getBusinessTypeList", null);
            for (BaseInfo baseinfo : list) {
                businessTypeMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.getCarTypeBrandList", null);
            for (BaseInfo baseinfo : list) {
                carTypeBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.carEngineBrandList", null);
            for (BaseInfo baseinfo : list) {
                carEngineBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }

        return SUCCESS;
    }

    public String doadd() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        carBaseInfo.setVehicle_id(UUIDGenerator.getUUID());
        carBaseInfo.setCreater(currentUser.getUserID());
        try {

            service.insert("CarBase.insertCarBase", carBaseInfo);

        } catch (BusinessException e) {
            setMessage("carbase.create.error");
            log.error("车辆信息新建:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("carbase.create.error");
            log.error("车辆信息新建:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CL_VEHICLE_ADD_MID);
            addOperationLog("车辆信息新建");
        }
        setMessage("carbase.create.success");
        return SUCCESS;
    }

    public String gotoinfo() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        List < HarmdefDataInfo > list2 = new ArrayList < HarmdefDataInfo >();

        try {
            // list = service.getObjects("CarBase.getEngineList", null);
            // for (BaseInfo baseinfo : list) {
            // engineMap.put(baseinfo.getCodeId(), baseinfo.getCodeName());
            // }
            //
            // list = service.getObjects("CarBase.getVehicleTypeList", null);
            // for (BaseInfo baseinfo : list) {
            // vehicleTypeMap
            // .put(baseinfo.getCodeId(), baseinfo.getCodeName());
            // }
            Map < String, Object > map = new HashMap < String, Object >(1);
            map.put("vehicle_id", vehicle_id);
            carBaseInfo = (CarBaseInfo) service.getObject(
                    "CarBase.getCarBaseInfoById", map);

            // add by jinp start
            setOldVehicleVin(carBaseInfo.getVehicle_vin());
            // add by jinp stop

            list2 = service.getObjects("CarBase.getIllDriveList", null);
            for (HarmdefDataInfo harmdefDataInfo : list2) {
                illDriveMap.put(harmdefDataInfo.getDef_id(), harmdefDataInfo
                        .getDef_name());
            }
            list = service.getObjects("CarBase.getBusinessTypeList", null);
            for (BaseInfo baseinfo : list) {
                businessTypeMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.getCarTypeBrandList", null);
            for (BaseInfo baseinfo : list) {
                carTypeBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            // list = service.getObjects("CarBase.getVehicleTypeList",
            // carBaseInfo
            // .getCar_type_brand());
            // for (BaseInfo baseinfo : list) {
            // vehicleTypeMap
            // .put(baseinfo.getCodeId(), baseinfo.getCodeName());
            // }
            list = service.getObjects("CarBase.carEngineBrandList", null);
            for (BaseInfo baseinfo : list) {
                carEngineBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            // list = service.getObjects("CarBase.getEngineTypeList",
            // carBaseInfo
            // .getBrand());
            // for (BaseInfo baseinfo : list) {
            // engineMap.put(baseinfo.getCodeId(), baseinfo.getCodeName());
            // }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String doedit() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        carBaseInfo.setModifier(currentUser.getUserID());
        vehicle_id = carBaseInfo.getVehicle_id();
        // add by jinp start
        carBaseInfo.setOld_vehicle_vin(oldVehicleVin);
        // add by jinp stop
        try {
            // add by jinp start
            carBaseService.updateCarBaseInfo(carBaseInfo);
            // add by jinp stop
        } catch (BusinessException e) {
            setMessage("carbase.update.error");
            log.error("车辆信息更新:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("carbase.update.error");
            log.error("车辆信息更新:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CL_VEHICLE_MODIFY_MID);
            addOperationLog("车辆信息更新");
        }
        setMessage("carbase.update.success");
        return SUCCESS;
    }

    public String dodel() {

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        Map < String, Object > map2 = new HashMap < String, Object >(2);
        map2.put("vehicle_id", vehicle_id);
        map2.put("vaset_user_id", currentUser.getUserID());
        try {
            int countNum = service.getCount("CarBase.countBeforeDelCarBase",
                    vehicle_id);
            if (countNum > 0) {
                setMessage("carbase.del.cannot");
                log.error("车辆信息删除:不许删除已经分配的车辆");
                return ERROR;
            }
            service.update("CarBase.updateDelCarBaseInfo", map2);

        } catch (BusinessException e) {
            setMessage("carbase.delete.error");
            log.error("车辆信息删除:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("carbase.delete.error");
            log.error("车辆信息删除:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CL_VEHICLE_DELETE_MID);
            addOperationLog("车辆信息删除");
        }
        setMessage("carbase.delete.success");
        return SUCCESS;
    }

    public String goto_import() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        List < HarmdefDataInfo > list2 = new ArrayList < HarmdefDataInfo >();

        try {
            list2 = service.getObjects("CarBase.getIllDriveList", null);
            for (HarmdefDataInfo harmdefDataInfo : list2) {
                illDriveMap.put(harmdefDataInfo.getDef_id(), harmdefDataInfo
                        .getDef_name());
            }
            list = service.getObjects("CarBase.getBusinessTypeList", null);
            for (BaseInfo baseinfo : list) {
                businessTypeMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.getCarTypeBrandList", null);
            for (BaseInfo baseinfo : list) {
                carTypeBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.carEngineBrandList", null);
            for (BaseInfo baseinfo : list) {
                carEngineBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String do_import() {
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        List < HarmdefDataInfo > list2 = new ArrayList < HarmdefDataInfo >();
        try {
            list2 = service.getObjects("CarBase.getIllDriveList", null);
            for (HarmdefDataInfo harmdefDataInfo : list2) {
                illDriveMap.put(harmdefDataInfo.getDef_id(), harmdefDataInfo
                        .getDef_name());
            }
            list = service.getObjects("CarBase.getBusinessTypeList", null);
            for (BaseInfo baseinfo : list) {
                businessTypeMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.getCarTypeBrandList", null);
            for (BaseInfo baseinfo : list) {
                carTypeBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("CarBase.carEngineBrandList", null);
            for (BaseInfo baseinfo : list) {
                carEngineBrandMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
        } catch (BusinessException e) {
            setMessage("file.import.error");
            return ERROR;
        } catch (Exception e) {
            setMessage("file.import.error");
            return ERROR;
        }

        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }
        List < CarBaseInfo > listInfo = new ArrayList < CarBaseInfo >();

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

            listInfo = excelImplortor.getSheetData("importCarBase", 0);
            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            setMessage(getText("file.import.error"));
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
        listInfo = formatCarBaseInfos(listInfo);

        if (listInfo.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        }

        try {
            // 导入车辆信息
            Object obj = carBaseService.importCarBaseInfos(listInfo);
            if ("error".equals(obj)) {
                addActionError(getText("noly.vinln.error"));
                return ERROR;
            }
            setMessage("file.import.success");
        } catch (BusinessException e) {
            addActionError(getText("noly.vinln.error"));
            log.error("Import sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            addActionError(getText("info.db.error"));
            log.error("Import sim error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.IMPORT,
                    ModuleId.CLW_M_CL_VEHICLE_IMPORT_MID);
            addOperationLog("导入车辆信息");
        }

        return SUCCESS;
    }

    public String do_export() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("vehicle_vin", SqlStringUtil.getNull(vehicle_vin));
        map.put("vehicle_ln", SqlStringUtil.getNull(vehicle_ln));
        List < CarBaseInfo > list = new ArrayList < CarBaseInfo >();
        try {
            list = (List < CarBaseInfo >) service.getObjects(
                    "CarBase.selectCarBaseInfoForExport", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "CarBase.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("车辆信息");

            excelExporter.putAutoExtendSheets("exportCarBase", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
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
                "attachment;filename=CarBase.xls");
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
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
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
                    ModuleId.CLW_M_CL_VEHICLE_EXPORT_MID);
            addOperationLog("导出车辆信息");
        }
        // 导出文件成功
        return null;
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
    private List < CarBaseInfo > formatCarBaseInfos(List < CarBaseInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < CarBaseInfo > ret = new ArrayList < CarBaseInfo >();

        for (CarBaseInfo tmp : list) {
            // 主键
            tmp.setVehicle_id(UUIDGenerator.getUUID());
            // 发动机型号
            tmp.setEngine_type(carBaseInfo.getEngine_type());
            // 车辆类型
            tmp.setVehicle_type_id(carBaseInfo.getVehicle_type_id());
            // 不良驾驶定义
            tmp.setCr_config_id(carBaseInfo.getCr_config_id());
            // 创建用户ID
            tmp.setCreater(currentUser.getUserID());
            /*
             * 业务流程变更后增加： 发动机品牌以及车型品牌
             */
            tmp.setBrand(carBaseInfo.getBrand());
            tmp.setCar_type_brand(carBaseInfo.getCar_type_brand());

            // 添加到list中
            ret.add(tmp);
        }

        return ret;
    }

    /*
     * set and get
     */
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List < CarBaseInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < CarBaseInfo > pageList) {
        this.pageList = pageList;
    }

    public CarBaseInfo getCarBaseInfo() {
        return carBaseInfo;
    }

    public void setCarBaseInfo(CarBaseInfo carBaseInfo) {
        this.carBaseInfo = carBaseInfo;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setEngineMap(Map < String, String > engineMap) {
        this.engineMap = engineMap;
    }

    public Map < String, String > getEngineMap() {
        return engineMap;
    }

    public void setVehicleTypeMap(Map < String, String > vehicleTypeMap) {
        this.vehicleTypeMap = vehicleTypeMap;
    }

    public Map < String, String > getVehicleTypeMap() {
        return vehicleTypeMap;
    }

    public void setIllDriveMap(Map < String, String > illDriveMap) {
        this.illDriveMap = illDriveMap;
    }

    public Map < String, String > getIllDriveMap() {
        return illDriveMap;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void setCarBaseService(CarBaseService carBaseService) {
        this.carBaseService = carBaseService;
    }

    public CarBaseService getCarBaseService() {
        return carBaseService;
    }

    public void setBusinessTypeMap(Map < String, String > businessTypeMap) {
        this.businessTypeMap = businessTypeMap;
    }

    public Map < String, String > getBusinessTypeMap() {
        return businessTypeMap;
    }

    public Map < String, String > getCarTypeBrandMap() {
        return carTypeBrandMap;
    }

    public void setCarTypeBrandMap(Map < String, String > carTypeBrandMap) {
        this.carTypeBrandMap = carTypeBrandMap;
    }

    public void setCarEngineBrandMap(Map < String, String > carEngineBrandMap) {
        this.carEngineBrandMap = carEngineBrandMap;
    }

    public Map < String, String > getCarEngineBrandMap() {
        return carEngineBrandMap;
    }

    public void setPopup_code(String popup_code) {
        this.popup_code = popup_code;
    }

    public String getPopup_code() {
        return popup_code;
    }

    public String getPopup_name() {
        return popup_name;
    }

    public void setPopup_name(String popup_name) {
        this.popup_name = popup_name;
    }

    public List < BaseInfo > getVehicleTypeList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List < BaseInfo > vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }

    public List < BaseInfo > getEngineTypeList() {
        return engineTypeList;
    }

    public void setEngineTypeList(List < BaseInfo > engineTypeList) {
        this.engineTypeList = engineTypeList;
    }

    public String getOldVehicleVin() {
        return oldVehicleVin;
    }

    public void setOldVehicleVin(String oldVehicleVin) {
        this.oldVehicleVin = oldVehicleVin;
    }

    /** 品牌信息列表 **/
    private Map < String, String > brandMap = new HashMap < String, String >();
    
    public Map < String, String > getBrandMap() {
        return brandMap;
    }

    public void setBrandMap(Map < String, String > brandMap) {
        this.brandMap = brandMap;
    }

    /** 创建车型信息初始化 **/
    public String vehicleTypeCreate() {
        try {
            String typeQuery = "";
            typeQuery = Constants.VEHICLE_BRAND_CODE;

            List < CommonMapBean > list = service.getObjects(
                    "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
            for (CommonMapBean commonMapBean : list) {
                brandMap.put(commonMapBean.getCodeId(), commonMapBean
                        .getCodeName());
            }
        } catch (BusinessException e) {
            ;
        }
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }
        return SUCCESS;
    }
    
    private String parentId = "";
    
    private String codeId = "";
    
    private String codeName = "";
    
    
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * 创建车型
     */
    public void createVehicleType() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        BaseInfo baseInfo = new BaseInfo();

        // 处理结果
        String ret = "";

        baseInfo.setParentId(parentId);
        baseInfo.setCodeType(Constants.VEHICLE_TYPE_CODE);
        baseInfo.setCodeId(codeId);
        baseInfo.setCodeName(codeName);
        // 设置创建用户
        baseInfo.setCreater(currentUser.getUserID());
        // 设置主键值
        baseInfo.setDefId(UUIDGenerator.getUUID());

        try {
            // 创建基础信息
            service.insert("BaseInfoManage.insertBaseInfo", baseInfo);
            ret = "success";
        } catch (BusinessException e) {
            ret = "error";
            log.error(e.getMessage());
        } catch (Exception e) {
            ret = "error";
            log.error(e.getMessage());
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_ADD_MID);
            addOperationLog("新建基础信息");
            try {
                response.getWriter().write(ret);
            } catch (IOException ioException) {
            }
        }
    }
    
    /** 创建发动机型号信息初始化 **/
    public String engineTypeCreate() {
        try {
            String typeQuery = "";
            typeQuery = Constants.ENGINE_BRAND_CODE;

            List < CommonMapBean > list = service.getObjects(
                    "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
            for (CommonMapBean commonMapBean : list) {
                brandMap.put(commonMapBean.getCodeId(), commonMapBean
                        .getCodeName());
            }
        } catch (BusinessException e) {
            ;
        }
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 创建发动机型号
     */
    public void createEngineType() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        BaseInfo baseInfo = new BaseInfo();

        // 处理结果
        String ret = "";

        baseInfo.setParentId(parentId);
        baseInfo.setCodeType(Constants.ENGINE_TYPE_CODE);
        baseInfo.setCodeId(codeId);
        baseInfo.setCodeName(codeName);
        // 设置创建用户
        baseInfo.setCreater(currentUser.getUserID());
        // 设置主键值
        baseInfo.setDefId(UUIDGenerator.getUUID());

        try {
            // 创建基础信息
            service.insert("BaseInfoManage.insertBaseInfo", baseInfo);
            ret = "success";
        } catch (BusinessException e) {
            ret = "error";
            log.error(e.getMessage());
        } catch (Exception e) {
            ret = "error";
            log.error(e.getMessage());
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_ADD_MID);
            addOperationLog("新建基础信息");
            try {
                response.getWriter().write(ret);
            } catch (IOException ioException) {
            }
        }
    }
}

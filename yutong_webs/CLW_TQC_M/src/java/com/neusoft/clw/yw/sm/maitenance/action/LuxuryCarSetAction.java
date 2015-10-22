package com.neusoft.clw.yw.sm.maitenance.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.sm.maitenance.ds.LuxuryCar;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 高档车设置action
 * @author <a href="mailto:huangmb@neusoft.com">huangmb </a>
 * @version $Revision 1.1 $ 2011-9-6 下午12:39:28
 */
public class LuxuryCarSetAction extends PaginationAction {
    /** 共同service **/
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /** 保存路径 **/
    private String savePath;

    /** 高档车 **/
    private LuxuryCar luxuryCar;

    private Map < String, Object > map = new HashMap < String, Object >();

    /**
     * 转到高档车设置列表页面
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("service.management.luxury.location"));
        return SUCCESS;
    }

    /**
     * 高档车设置列表页面
     * @return
     */
    public String luxuryCarQuery() {
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            String pageIndex = request.getParameter("page");
            String rpNum = request.getParameter("rp");

            // 为null,非数字,则初始化,保证转换成数字是不出异常
            pageIndex = defaultNumber(pageIndex, "1");
            rpNum = defaultNumber(rpNum, "10");

            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            if (this.luxuryCar == null) this.luxuryCar = new LuxuryCar();
            this.luxuryCar.setSortname(sortName);
            this.luxuryCar.setSortorder(sortOrder);

            int totalSize = service.getCount("LuxuryCarSet.getLuxuryCarCount",
                    this.luxuryCar);

            List < LuxuryCar > pageList = (List < LuxuryCar >) service
                    .getObjectsByPage("LuxuryCarSet.getLuxuryCarList",
                            this.luxuryCar, (Integer.parseInt(pageIndex) - 1)
                                    * Integer.parseInt(rpNum), Integer
                                    .parseInt(rpNum));
            if (message != null) {
                super.addActionMessage(getText(message));
            }
            this.map = getPagination(pageList, totalSize, pageIndex);
        } catch (BusinessException be) {
            log.error("高档车设置列表", be);
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_LUXURYCAR_QUERY_MID);
            addOperationLog("高档车设置列表");
        }
        return SUCCESS;
    }

    /**
     * 高档车设置添加页面
     * @return
     */
    public String gotoAdd() {
        return SUCCESS;
    }

    /**
     * 高档车设置添加
     * @return
     */
    public String doAdd() {
        UserInfo user = sessionUser();
        if (user == null) {
            addActionError("获取当前用户信息失败!");
            return ERROR;
        }
        this.luxuryCar.setCreater_id(user.getUserID());
        try {
            // 先查询高档车的车工号是否已经存在,存在则不添加,不存在才添加
            int count = this.service.getCount(
                    "LuxuryCarSet.getLuxuryCarCountByVNumber", this.luxuryCar);
            if (count < 1) {
                List<LuxuryCar> list = (List<LuxuryCar>)this.service.getObjects("LuxuryCarSet.getLuxuryCarByVinNumber", this.luxuryCar);
                if(list != null && list.size() > 0){
                    LuxuryCar lc = list.get(0);
                    this.luxuryCar.setLuxury_car_id(lc.getLuxury_car_id());
                    this.luxuryCar.setVehicle_vin(lc.getVehicle_vin());
                    this.service.insert("LuxuryCarSet.insertLuxuryCar",
                            this.luxuryCar);
                    addActionMessage("添加数据成功!");
                }else{
                    addActionMessage("基本信息表中无该数据!");
                }
            } else {
                addActionMessage("该车工号已经存在!");
                return ERROR;
            }
        } catch (BusinessException e) {
            log.error("添加高档车信息出错!", e);
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_LUXURYCAR_ADD_MID);
            addOperationLog("添加高档车信息");
        }
        return SUCCESS;
    }

    /**
     * 高档车设置修改页面
     * @return
     */
    public String gotoModify() {
        try {
            LuxuryCar lc = (LuxuryCar) service.getObject(
                    "LuxuryCarSet.getLuxuryCar", this.luxuryCar);
            if (lc == null) return ERROR;
            this.luxuryCar.setVehicle_number(lc.getVehicle_number());
            this.luxuryCar.setVehicle_type_name(lc.getVehicle_type_name());
        } catch (BusinessException e) {
            log.error("转到高档车设置修改页面出错!", e);
        }
        return SUCCESS;
    }

    /**
     * 高档车设置修改
     * @return
     */
    public String doModify() {
        UserInfo user = sessionUser();
        if (user == null) {
            addActionError("获取当前用户信息失败!");
            return ERROR;
        }
        this.luxuryCar.setModifier_id(user.getUserID());
        // service方法中的空串要改正为实际的
        try {
            List<LuxuryCar> list = (List<LuxuryCar>)this.service.getObjects("LuxuryCarSet.getLuxuryCarByVinNumber", luxuryCar);
            if(list != null && list.size() > 0){
                LuxuryCar lc = list.get(0);
                luxuryCar.setLuxury_car_id(lc.getLuxury_car_id());
                luxuryCar.setVehicle_vin(lc.getVehicle_vin());
                // 直接根据ID修改
                this.service.update("LuxuryCarSet.updateLuxuryCar", this.luxuryCar);
                addActionMessage("修改数据成功!");                
            }else{
                addActionError("基本信息表中无该数据!");
            }
        } catch (BusinessException e) {
            log.error("修改高档车信息出错!", e);
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_LUXURYCAR_MODIFY_MID);
            addOperationLog("修改高档车信息");
        }
        return SUCCESS;
    }

    /**
     * 高档车设置删除
     * @return
     */
    public String doDelete() {
        UserInfo user = sessionUser();
        if (user == null) {
            addActionError("获取当前用户信息失败!");
            return ERROR;
        }
        try {
            // 直接删除
            this.service.delete("LuxuryCarSet.deleteLuxuryCar", this.luxuryCar);
            addActionMessage("删除数据成功!");
        } catch (BusinessException e) {
            log.error("删除高档车信息出错!", e);
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_LUXURYCAR_DELETE_MID);
            addOperationLog("删除高档车信息");
        }
        return SUCCESS;
    }

    /**
     * 高档车导入页面
     * @return
     */
    public String toImport() {
        return SUCCESS;
    }

    /**
     * 导入高档车设置
     * @return
     */
    public String doImport() {
        UserInfo user = sessionUser();
        if (user == null) {
            addActionError("获取当前用户信息失败!");
            return ERROR;
        }
        FileInputStream fis = null;
        File destFile = null;
        // 获取uploadfile目录所在的路径,作为目标存储路径
        String destPath = ServletActionContext.getServletContext().getRealPath(
                "/tmp" + File.separator + fileFileName);
        try {
            destFile = new File(destPath);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            destFile = new File(destPath + File.separator + fileFileName);
            // 将文件转存到web服务器目录下
            FileUtils.copyFile(file, destFile);
            fis = new FileInputStream(destFile);
            // 调用组件解析excel,其配置见ie-config.xml
            IEExcelImporter excelImplortor = new IEExcelImporter(fis);
            // 系统根据配置转换为List(LuxuryCar),因为在ie-config.xml配置有class="com.neusoft.clw.yw.sm.maitenance.ds.LuxuryCar"
            // 其中importLuxuryCar为工作表名称
            List < LuxuryCar > luxuryList = excelImplortor.getSheetData(
                    "importLuxuryCar", 0);
            // 根据配置中的规则,得到不合法的数据
            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());
            if (errMessage.length() < 0) {
                addActionError(errMessage);
                return ERROR;
            }
            LuxuryCar lc = null;
            int countSuccess = 0;
            // 引用luxuryList时进行安全检查
            if (luxuryList != null && luxuryList.size() > 0)
                for (LuxuryCar luxuryCar : luxuryList) {
                    // 调用service存储数据
                    int count = this.service.getCount(
                            "LuxuryCarSet.getLuxuryCarCountByVNumber",
                            luxuryCar);
                    if (count < 1) {
                        List<LuxuryCar> list = (List<LuxuryCar>)this.service.getObjects("LuxuryCarSet.getLuxuryCarByVinNumber", luxuryCar);
                        if(list != null && list.size() > 0){
                            lc = list.get(0);
                            luxuryCar.setLuxury_car_id(lc.getLuxury_car_id());
                            luxuryCar.setVehicle_vin(lc.getVehicle_vin());
                        }else{
                            continue;
                        }                        
                        luxuryCar.setCreater_id(user.getUserID());
                        this.service.insert("LuxuryCarSet.insertLuxuryCar",
                                luxuryCar);
                        countSuccess++;
                    }
                }
            addActionMessage("本次共添加成功" + countSuccess + "数据!");
        } catch (IOException ioe) {
            setMessage("file.import.error");
            log.error("Import file error:" + ioe.getMessage());
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
                }
            }
            if (null == fis) {
                // 删除存储的文件
                if (null != destFile) {
                    destFile.delete();
                }
            }
        }
        setOperationType(Constants.IMPORT, ModuleId.CLW_M_LUXURYCAR_IMPORT_MID);
        addOperationLog("导入高档车信息");
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
     * 高档车导出
     * @return
     */
    public String doExport() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = request.getSession().getServletContext().getRealPath(File.separator) + "tmp" + File.separator + UUIDGenerator.getUUID() + "LuxuryCarExport.xls";
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file = new File(filePath);
            // 需要换成查询列表
            List < LuxuryCar > pageList = service.getObjects(
                    "LuxuryCarSet.getLuxuryCarList", this.luxuryCar);
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("高档车设置信息");

            excelExporter.putAutoExtendSheets("exportLuxuryCar", 0, pageList);
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
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-disposition",
                "attachment;filename=LuxuryCarExport.xls");
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
                }
            }
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_LUXURYCAR_EXPORT_MID);
            addOperationLog("高档车设置信息表导出");
        }
        // 导出文件成功
        return null;
    }

    /**
     * 获取车辆类型
     * @return
     */
    public String queryCarType() {
        // TODO 有可能在DWR中配置
        return SUCCESS;
    }

    /**
     * 获取车工号
     * @return
     */
    public String queryVehicleNumber() {
        // TODO 有可能在DWR中配置
        return SUCCESS;
    }

    /**
     * 转换为Map对象
     * @param luxuryCarList
     * @param totalCount
     * @param pageIndex
     * @return
     */
    public Map < String, Object > getPagination(
            List < LuxuryCar > luxuryCarList, int totalCount, String pageIndex) {
        List < Map > mapList = new ArrayList < Map >();
        Map < String, Object > mapData = new LinkedHashMap < String, Object >();
        Map < String, Object > cellMap = null;
        if (luxuryCarList != null && luxuryCarList.size() > 0)
            for (LuxuryCar lc : luxuryCarList) {
                cellMap = new LinkedHashMap < String, Object >();
                cellMap.put("id", lc.getVehicle_number());
                cellMap.put("cell", new Object[] {lc.getVehicle_type_name(),
                        lc.getVehicle_number(), lc.getCreate_time(),
                        lc.getCreater_id(), lc.getModifier_id(),
                        "",lc.getLuxury_car_id(),lc.getModify_time() });
                mapList.add(cellMap);
            }
        // 从前台获取当前第page页
        mapData.put("page", pageIndex);
        // 从数据库获取总记录数
        mapData.put("total", totalCount);
        mapData.put("rows", mapList);
        return mapData;
    }

    /**
     * num不是数字,则返回dn,要求dn必须是数字
     * @param num 任一字符串
     * @param dn 必须是数字
     * @return
     */
    private String defaultNumber(String num, String dn) {
        // 为null,非数字,则初始化
        if (num == null || !num.matches("^[0-9]*$")) {
            num = dn;
        }
        return num;
    }

    /**
     * 统一获取取用户信息
     * @return
     */
    private UserInfo sessionUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
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

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public LuxuryCar getLuxuryCar() {
        return luxuryCar;
    }

    public void setLuxuryCar(LuxuryCar luxuryCar) {
        this.luxuryCar = luxuryCar;
    }

    public Map < String, Object > getMap() {
        return map;
    }

    public void setMap(Map < String, Object > map) {
        this.map = map;
    }

}

package com.neusoft.clw.yw.zd.terminal.action;

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
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.zd.protocal.ds.ValueInfo;
import com.neusoft.clw.yw.zd.terminal.ds.ChannelInfo;
import com.neusoft.clw.yw.zd.terminal.ds.TerminalImportInfo;
import com.neusoft.clw.yw.zd.terminal.ds.TerminalInfo;
import com.neusoft.clw.yw.zd.terminal.service.TerminalManageService;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端管理action
 * @author JinPeng
 */
public class TerminalManageAction extends PaginationAction {
    private transient Service service;

    private transient TerminalManageService terminalManageService;

    /** 通道号列表 **/
    private List < ChannelInfo > channelList = new ArrayList < ChannelInfo >();

    /** 主键ID **/
    private String terminalId = "";

    /** 终端号（旧值） **/
    private String terminalOldCode = "";

    /** 页面提示信息 **/
    private String message = null;

    /** 删除通道ID值 **/
    private String delChannelId = "";

    /** 终端信息bean **/
    private TerminalInfo terminalInfo = new TerminalInfo();

    /** 终端信息列表 **/
    private List < TerminalInfo > terminalList = new ArrayList < TerminalInfo >();

    /** 终端厂家信息 **/
    private Map < String, String > oemInfosMap = new HashMap < String, String >();

    /** 终端型号信息 **/
    private Map < String, String > typeInfosMap = new HashMap < String, String >();

    /** 终端协议信息 **/
    private Map < String, String > protocalInfosMap = new HashMap < String, String >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /**
     * 获取终端厂家列表值
     * @return
     */
    private boolean getOemSelect() {
        if (oemInfosMap.isEmpty()) {
            try {
                List < ValueInfo > list = (List < ValueInfo >) service
                        .getObjects("ProtocalManage.getOemInfos", null);
                for (ValueInfo valueInfo : list) {
                    oemInfosMap.put(valueInfo.getSelectKey(), valueInfo
                            .getSelectValue());
                }
            } catch (BusinessException e) {
                log.error("Get oem list error:" + e.getMessage());
                return false;
            } catch (Exception e) {
                log.error("Get oem list error:" + e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * 获取终端属性信息
     */
    private boolean getTerminalAttribute() {
        try {
            if (terminalInfo.getOemId() != "") {
                // 终端厂商ID不为空时，查询终端型号信息
                List < ValueInfo > list = (List < ValueInfo >) service
                        .getObjects("ProtocalManage.getTypeInfos", terminalInfo
                                .getOemId());
                for (ValueInfo valueInfo : list) {
                    typeInfosMap.put(valueInfo.getSelectKey(), valueInfo
                            .getSelectValue());
                }
            }

            if (terminalInfo.getTypeId() != "") {
                // 终端型号ID不为空时，查询终端协议信息
                List < ValueInfo > list = (List < ValueInfo >) service
                        .getObjects("TerminalManage.getProtocalInfos",
                                terminalInfo.getTypeId());
                for (ValueInfo valueInfo : list) {
                    protocalInfosMap.put(valueInfo.getSelectKey(), valueInfo
                            .getSelectValue());
                }
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get terminal base infos error:" + e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get terminal base infos error:" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 页面初始化/查询操作
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("terminal.manage.location"));
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemInfosMap.isEmpty()) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }

        if(null != terminalInfo) {
        	terminalInfo.setTerminalCodeQuery(SearchUtil.formatSpecialChar(terminalInfo.getTerminalCode()));
        	terminalInfo.setVideoServerIpQuery(SearchUtil.formatSpecialChar(terminalInfo.getVideoServerIp()));
        	terminalInfo.setVideoIdQuery(SearchUtil.formatSpecialChar(terminalInfo.getVideoId()));
        }
        
        try {
            int totalCount = 0;
            totalCount = service.getCount("TerminalManage.getCount",
                    terminalInfo);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            // 查询终端协议信息
            terminalList = (List < TerminalInfo >) service.getObjectsByPage(
                    "TerminalManage.getTerminalInfos", terminalInfo, pageObj
                            .getStartOfPage(), pageSize);

            getTerminalAttribute();

            for(TerminalInfo tmp : terminalList) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("HI", "海康");
                map.put("DA", "大华");
                tmp.setVideoFactory(map.get(tmp.getVideoFactory()));
            }
            
            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_ZD_TERMINAL_QUERY_MID);
            addOperationLog("查询终端信息");
        }

        return SUCCESS;
    }

    /**
     * 创建终端信息初始化
     * @return
     */
    public String addTerminalBefore() {
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemInfosMap.isEmpty()) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }

        // 通道1-监控司机
        ChannelInfo channelInfo1 = new ChannelInfo();
        channelInfo1.setChannelId(UUIDGenerator.getUUID());
        channelInfo1.setChannelNumber("1");
        channelInfo1.setChannelName("监控整车");
        channelList.add(channelInfo1);
        // 通道2-监控路控
        ChannelInfo channelInfo2 = new ChannelInfo();
        channelInfo2.setChannelId(UUIDGenerator.getUUID());
        channelInfo2.setChannelNumber("2");
        channelInfo2.setChannelName("监控路况");
        channelList.add(channelInfo2);
        // 通道3-监控中门
        ChannelInfo channelInfo3 = new ChannelInfo();
        channelInfo3.setChannelId(UUIDGenerator.getUUID());
        channelInfo3.setChannelNumber("3");
        channelInfo3.setChannelName("监控中门");
        channelList.add(channelInfo3);
        // 通道4-监控中门
        ChannelInfo channelInfo4 = new ChannelInfo();
        channelInfo4.setChannelId(UUIDGenerator.getUUID());
        channelInfo4.setChannelNumber("4");
        channelInfo4.setChannelName("监控司机");
        channelList.add(channelInfo4);

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 填加通道
     * @return
     */
    public String addChannel() {
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemInfosMap.isEmpty()) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }
        getTerminalAttribute();

        ChannelInfo channelInfo = new ChannelInfo();
        channelInfo.setChannelId(UUIDGenerator.getUUID());
        channelList.add(channelInfo);

        return SUCCESS;
    }

    /**
     * 删除通道
     * @return
     */
    public String deleteChannel() {
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemInfosMap.isEmpty()) {
            // 提示先创建终端厂家信息
            super.addActionError(getText("device.oem.require"));
            return ERROR;
        }

        getTerminalAttribute();

        for (int i = 0; i < channelList.size(); i++) {
            ChannelInfo channelInfo = channelList.get(i);
            if (channelInfo.getChannelId().equals(delChannelId)) {
                channelList.remove(i);
                break;
            }
        }
        return SUCCESS;
    }

    /**
     * 创建终端
     * @return
     */
    public String addTerminal() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 设置终端号
        for (ChannelInfo channelInfo : channelList) {
            channelInfo.setTerminalCode(terminalInfo.getTerminalCode());
        }
        // 设置通道列表到终端信息bean中
        terminalInfo.setChannelList(channelList);
        // 填加创建人ID
        terminalInfo.setCreator(currentUser.getUserID());
        // 生成主键
        terminalInfo.setTerminalId(UUIDGenerator.getUUID());

        try {
            terminalManageService.insertTerminal(terminalInfo);
            setMessage("terminal.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert terminal error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_ZD_TERMINAL_ADD_MID);
            addOperationLog("新建终端信息");
        }
        return SUCCESS;
    }

    /**
     * 查询终端信息
     * @return
     */
    public String queryTerminalInfo() {
        if (terminalId == "" || terminalId == null) {
            return ERROR;
        } else {
            try {
                terminalInfo = (TerminalInfo) service.getObject(
                        "TerminalManage.getTerminalInfoById", terminalId);

                channelList = (List < ChannelInfo >) service.getObjects(
                        "TerminalManage.getChannelInfosById", terminalId);

                // 设置旧终端号
                setTerminalOldCode(terminalInfo.getTerminalCode());

                if (!getOemSelect()) {
                    // 终端厂家列表初始化异常时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

                if (oemInfosMap.isEmpty()) {
                    // 提示先创建终端厂家信息
                    super.addActionError(getText("device.oem.require"));
                    return ERROR;
                }

                getTerminalAttribute();

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query terminal detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query terminal detail error:" + e.getMessage());
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
     * 更新终端信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (terminalInfo != null) {
            // 设置修改用户
            terminalInfo.setModifier(currentUser.getUserID());
            // 设置终端ID值
            terminalInfo.setTerminalId(terminalId);
            // 设置修改前终端号
            terminalInfo.setTerminalOldCode(terminalOldCode);
        }

        // 设置终端号
        for (ChannelInfo channelInfo : channelList) {
            channelInfo.setTerminalCode(terminalInfo.getTerminalCode());
        }
        // 设置通道列表到终端信息bean中
        terminalInfo.setChannelList(channelList);

        try {
            // 修改终端信息
            terminalManageService.updateTerminal(terminalInfo);
            setMessage("terminal.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update terminal error:" + e.getMessage());
            setTerminalId(terminalId);
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update terminal error:" + e.getMessage());
            setTerminalId(terminalId);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_ZD_TERMINAL_MODIFY_MID);
            addOperationLog("修改终端信息");
        }

        return SUCCESS;
    }

    /**
     * 删除终端信息
     * @return
     */
    public String delete() {
        if (terminalId == "" || terminalId == null) {
            return ERROR;
        }

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (terminalInfo != null) {
            // 设置修改用户
            terminalInfo.setModifier(currentUser.getUserID());
            // 设置终端ID值
            terminalInfo.setTerminalId(terminalId);
            // 设置修改前终端号
            terminalInfo.setTerminalOldCode(terminalOldCode);
        }

        try {
            int countNum = service.getCount(
                    "TerminalManage.getTMRegisteredCount", terminalId);

            if (countNum > 0) {
                setMessage("terminal.delete.notpermission");
                log.error("The terminal is registered");
                return ERROR;
            }

            // 删除终端信息
            terminalManageService.deleteTerminal(terminalInfo);
            setMessage("terminal.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete terminal error:" + e.getMessage());
            setTerminalId(terminalId);
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete terminal error:" + e.getMessage());
            setTerminalId(terminalId);
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_ZD_TERMINAL_DELETE_MID);
            addOperationLog("删除终端信息");
        }

        return SUCCESS;
    }

    /**
     * 终端信息导入页面初始化
     * @return
     */
    public String importTerminalBefore() {
        if (!getOemSelect()) {
            // 终端厂家列表初始化异常时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (oemInfosMap.isEmpty()) {
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
    private List < TerminalInfo > formatTerminalInfos(
            List < TerminalImportInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < TerminalInfo > ret = new ArrayList < TerminalInfo >();

        for (TerminalImportInfo tmp : list) {
            if (tmp.getTerminalCode() != null
                    && tmp.getTerminalCode().length() == 0
                    && tmp.getVideoId() != null
                    && tmp.getVideoId().length() == 0
                    && tmp.getChannelInfos() != null
                    && tmp.getChannelInfos().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 终端信息
                TerminalInfo tm = new TerminalInfo();
                // 通道信息
                List < ChannelInfo > channelList = new ArrayList < ChannelInfo >();
                // 主键
                tm.setTerminalId(UUIDGenerator.getUUID());
                // 终端号
                tm.setTerminalCode(tmp.getTerminalCode());
                // 终端厂家ID
                tm.setOemId(terminalInfo.getOemId());
                // 终端型号ID
                tm.setTypeId(terminalInfo.getTypeId());
                // 终端协议ID
                tm.setProtocalId(terminalInfo.getProtocalId());
                // 视频ID
                tm.setVideoId(tmp.getVideoId());
                // 创建人ID
                tm.setCreator(currentUser.getUserID());
                // 修改人ID
                tm.setModifier(currentUser.getUserID());
                // 视频厂家
                tm.setVideoFactory(tmp.getVideoFactory());
                // 视频地址
                tm.setVideoServerIp(tmp.getVideoServerIp());
                // 视频服务器用户名
                tm.setVideoUser(tmp.getVideoUser());
                // 视频服务器密码
                tm.setVideoPassword(tmp.getVideoPassword());
                // 流媒体地址
                tm.setStreamServerIp(tmp.getStreamServerIp());
                
                if (tmp.getChannelInfos() != null
                        && tmp.getChannelInfos().length() > 0) {
                    String[] channels = tmp.getChannelInfos().split(";");
                    for (int i = 0; i < channels.length; i++) {
                        if (channels[i] != null && channels[i].length() > 0) {
                            String[] channelInfo = channels[i].split(",");
                            if (channelInfo.length == 2) {
                                if (channelInfo[0] != null
                                        && channelInfo[0].length() > 0
                                        && channelInfo[1] != null
                                        && channelInfo[1].length() > 0) {
                                    // 判断通道信息是否完全
                                    ChannelInfo cinfo = new ChannelInfo();
                                    // 通道ID
                                    cinfo.setChannelId(UUIDGenerator.getUUID());
                                    // 通道名称
                                    cinfo.setChannelName(channelInfo[1]);
                                    // 通道号
                                    cinfo.setChannelNumber(channelInfo[0]);
                                    // 终端号
                                    cinfo.setTerminalCode(tm.getTerminalCode());
                                    channelList.add(cinfo);
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }

                /**
                 * if(tmp.getChannelOneName() != null &&
                 * tmp.getChannelOneName().length() > 0 &&
                 * tmp.getChannelOneNumber() != null &&
                 * tmp.getChannelOneNumber().length() > 0) { // 判断通道1信息是否完全
                 * ChannelInfo channelInfo = new ChannelInfo(); // 通道ID
                 * channelInfo.setChannelId(UUIDGenerator.getUUID()); // 通道名称
                 * channelInfo.setChannelName(tmp.getChannelOneName()); // 通道号
                 * channelInfo.setChannelNumber(tmp.getChannelOneNumber()); //
                 * 终端号 channelInfo.setTerminalCode(tm.getTerminalCode());
                 * channelList.add(channelInfo); } if(tmp.getChannelTwoName() !=
                 * null && tmp.getChannelTwoName().length() > 0 &&
                 * tmp.getChannelTwoNumber() != null &&
                 * tmp.getChannelTwoNumber().length() > 0) { // 判断通道2信息是否完全
                 * ChannelInfo channelInfo = new ChannelInfo(); // 通道ID
                 * channelInfo.setChannelId(UUIDGenerator.getUUID()); // 通道名称
                 * channelInfo.setChannelName(tmp.getChannelTwoName()); // 通道号
                 * channelInfo.setChannelNumber(tmp.getChannelTwoNumber()); //
                 * 终端号 channelInfo.setTerminalCode(tm.getTerminalCode());
                 * channelList.add(channelInfo); }
                 **/

                // 设置管道list
                tm.setChannelList(channelList);

                // 添加到list中
                ret.add(tm);
            }
        }

        return ret;
    }

    /**
     * 终端信息导入
     * @return
     */
    public String importTerminal() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            getOemSelect();
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            getOemSelect();
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < TerminalImportInfo > list = new ArrayList < TerminalImportInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                getOemSelect();
                addActionError(getText("file.import.error"));
                log.error("Import file error:" + e.getMessage());
                return ERROR;
            }

            list = excelImplortor.getSheetData("importTerminal", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                getOemSelect();
                return ERROR;
            }
        } catch (Exception e) {
            getOemSelect();
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

        // 格式化数据
        List < TerminalInfo > ret = formatTerminalInfos(list);

        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            getOemSelect();
            return ERROR;
        } else {
            try {
                // 导入终端信息
                terminalManageService.importTerminalInfos(ret);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                addActionError(getText("info.db.error"));
                log.error("Import terminal error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                addActionError(getText("info.db.error"));
                log.error("Import terminal error:" + e.getMessage());
                return ERROR;
            } finally {
                setOperationType(Constants.IMPORT,
                        ModuleId.CLW_M_ZD_TERMINAL_IMPORT_MID);
                addOperationLog("导入终端信息");
            }
        }

        return SUCCESS;
    }

    /**
     * 终端信息导出
     * @return
     */
    public String exportTerminal() {
        List < TerminalInfo > list = new ArrayList < TerminalInfo >();
        TerminalInfo tm = terminalInfo;

        try {
            list = (List < TerminalInfo >) service.getObjects(
                    "TerminalManage.getTerminalInfos", tm);
            
            for(TerminalInfo tmp : list) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("HI", "海康");
                map.put("DA", "大华");
                tmp.setVideoFactory(map.get(tmp.getVideoFactory()));
            }
            
        } catch (BusinessException e) {
            setMessage("file.export.error");
            log.error("Export terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export terminal error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Terminal.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("终端信息");

            excelExporter.putAutoExtendSheets("exportTerminal", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export terminal error:" + e.getMessage());
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
                "attachment;filename=Terminal.xls");
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
            log.error("Export terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export terminal error:" + e.getMessage());
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
                    ModuleId.CLW_M_ZD_TERMINAL_EXPORT_MID);
            addOperationLog("导出终端信息");
        }
        // 导出文件成功
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalOldCode() {
        return terminalOldCode;
    }

    public void setTerminalOldCode(String terminalOldCode) {
        this.terminalOldCode = terminalOldCode;
    }

    public String getDelChannelId() {
        return delChannelId;
    }

    public void setDelChannelId(String delChannelId) {
        this.delChannelId = delChannelId;
    }

    public List < ChannelInfo > getChannelList() {
        return channelList;
    }

    public void setChannelList(List < ChannelInfo > channelList) {
        this.channelList = channelList;
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

    public Map < String, String > getProtocalInfosMap() {
        return protocalInfosMap;
    }

    public void setProtocalInfosMap(Map < String, String > protocalInfosMap) {
        this.protocalInfosMap = protocalInfosMap;
    }

    public List < TerminalInfo > getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List < TerminalInfo > terminalList) {
        this.terminalList = terminalList;
    }

    public TerminalInfo getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(TerminalInfo terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TerminalManageService getTerminalManageService() {
        return terminalManageService;
    }

    public void setTerminalManageService(
            TerminalManageService terminalManageService) {
        this.terminalManageService = terminalManageService;
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
}

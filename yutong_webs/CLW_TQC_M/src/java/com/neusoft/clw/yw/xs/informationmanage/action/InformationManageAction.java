package com.neusoft.clw.yw.xs.informationmanage.action;

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
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.informationmanage.ds.IssueInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 信息管理action
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2011-8-20 上午10:03:19
 */
public class InformationManageAction extends PaginationAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 主题(查询条件) **/
    private String theme = "";

    /** 创建时间 **/
    private String createTime = "";

    /** 问题ID **/
    private String issueId = "";
    
    /** 问题状态 **/
    private String issueStatus = "";
    
    /** 问题状态（修改前） **/
    private String oldIssueStatus = "";

    /** 是否回复 **/
    private String replyOrNot = "";
    
    /** 问题信息列表 **/
    private List < IssueInfo > issueList = new ArrayList < IssueInfo >();

    /** 问题信息bean **/
    private IssueInfo issueInfo = new IssueInfo();

    /** JSON 返回 注册信息MAP **/
    private Map informationsMap = new HashMap();

    /** 信息回复状态 **/
    private Map < String, String > infoReplyMap = new HashMap < String, String >();

    /** 信息回复状态 **/
    private Map < String, String > issueStatusMap = new HashMap < String, String >();

    /**
     * 信息回复状态
     */
    private void getInfoReplyMapList() {
        if (infoReplyMap != null && infoReplyMap.isEmpty()) {
            infoReplyMap.put("1", "已回复");
            infoReplyMap.put("0", "未回复");
        }
    }

    /**
     * 初始化出厂状态
     */
    private void getIssueStatusMapList() {
        if (issueStatusMap != null && issueStatusMap.isEmpty()) {
            issueStatusMap.put("2", "已废弃");
            issueStatusMap.put("1", "已发布");
            issueStatusMap.put("0", "未发布");
        }
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
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("informationmanage.info.location"));

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
    public Map getPagination(List issueList, int totalCount, String pageIndex,
            String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        getInfoReplyMapList();
        getIssueStatusMapList();

        for (int i = 0; i < issueList.size(); i++) {
            IssueInfo issueInfo = (IssueInfo) issueList.get(i);

            // 设置回复状态
            issueInfo
                    .setReplyOrNot(infoReplyMap.get(issueInfo.getReplyOrNot()));
            // 设置出厂状态
            issueInfo.setIssueStatus(issueStatusMap.get(issueInfo
                    .getIssueStatus()));

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", issueInfo.getIssueId());

            cellMap.put("cell", new Object[] {
                    (i + 1) + (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum),
                    issueInfo.getIssueTheme(), issueInfo.getCreater(),
                    issueInfo.getPublishTime(), issueInfo.getReplyOrNot(),
                    issueInfo.getIssueStatus() });

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
    public String getInformations() {
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
            Map < String, String > map = new HashMap < String, String >();
            map.put("theme", theme);
            map.put("createTime", createTime);
            map.put("creater", getCurrentUser().getUserID());
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("InformationManage.getCount", map);

            issueList = service.getObjectsByPage(
                    "InformationManage.getIssueInfos", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (issueList != null && issueList.size() == 0) {
                ;
            }

            this.informationsMap = getPagination(issueList, totalCount,
                    pageIndex, rpNum);

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query information error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query information error:" + e.getMessage());
            return ERROR;
        } finally {
             setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XS_INFO_QUERY_MID);
            addOperationLog("查询反馈信息");
        }

        return SUCCESS;
    }

    /**
     * 新建信息初始化
     * @return
     */
    public String addInfoBefore() {
        return SUCCESS;
    }

    /**
     * 新建信息
     * @return
     */
    public String addInfo() {
        try {
            if (null == issueInfo) {
                issueInfo = new IssueInfo();
            }
            UserInfo user = getCurrentUser();

            issueInfo.setIssueId(UUID.randomUUID().toString());
            issueInfo.setIssueStatus(issueStatus);
            if ("1".equals(issueStatus)) {
                // 发布状态
                issueInfo.setPublishUser(user.getUserID());
            }
            issueInfo.setCreater(user.getUserID());

            service.insert("InformationManage.insertInfo", issueInfo);

            if ("1".equals(issueStatus)) {
                // 发布信息
                setMessage("informationmanage.add.success");
            } else {
                // 暂存信息
                setMessage("informationmanage.ts.success");
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("insert information error.");
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_INFO_ADD_MID);
                    addOperationLog("新建反馈信息");
        }
        return SUCCESS;
    }

    /**
     * 根据ID查询信息
     * @return
     */
    public String queryInfoById() {
        try {
            // 查询信息
            issueInfo = (IssueInfo) service.getObject("InformationManage.getInfoById",
                    issueId);
            // 设置修改前问题状态
            setOldIssueStatus(issueInfo.getIssueStatus());
            // 设置问题ID
            setIssueId(issueId);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Query info detail error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Query info detail error:" + e.getMessage());
            return ERROR;
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 更新信息状态
     * @return
     */
    public String updateInfo() {
        UserInfo user = getCurrentUser();
        // 设置信息ID
        issueInfo.setIssueId(issueId);
        // 设置修改人
        issueInfo.setModifier(user.getUserID());
        
        if("0".equals(oldIssueStatus) || "2".equals(oldIssueStatus)) {
            // 暂存或发布信息
            issueInfo.setIssueStatus(issueStatus);
            if ("1".equals(issueStatus)) {
                // 发布信息
                // 设置发布人
                issueInfo.setPublishUser(user.getUserID());
            }
            try {
                service.update("InformationManage.updateInfo", issueInfo);
            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Update info error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Update info error:" + e.getMessage());
                return ERROR;
            } finally {
                if ("1".equals(issueStatus)) {
                    setOperationType(Constants.UPDATE,
                            ModuleId.CLW_M_XS_INFO_PUB_MID);
                    addOperationLog("发布信息");
                } else {
                    setOperationType(Constants.UPDATE,
                            ModuleId.CLW_M_XS_INFO_TS_MID);
                    addOperationLog("暂存信息");
                }

            }
        } else if("1".equals(oldIssueStatus)) {
            // 暂存或发布反馈信息
            issueInfo.setReplyOrNot(replyOrNot);
            if("1".equals(replyOrNot)) {
                // 发布反馈信息
                // 设置反馈人
                issueInfo.setReplyUser(user.getUserID());
            }
            try {
                service.update("InformationManage.updateReplyInfo", issueInfo);
            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Update info error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Update info error:" + e.getMessage());
                return ERROR;
            } finally {
                if ("1".equals(replyOrNot)) {
                    setOperationType(Constants.UPDATE,
                            ModuleId.CLW_M_XS_INFO_PUB_MID);
                    addOperationLog("发布反馈信息");
                } else {
                    setOperationType(Constants.UPDATE,
                            ModuleId.CLW_M_XS_INFO_TS_MID);
                    addOperationLog("暂存反馈信息");
                }
            }
        }
        // 信息修改成功
        setMessage("informationmanage.update.success");
        return SUCCESS;
    }
    
    /**
     * 废弃信息
     * @return
     */
    public String abandonInfo() {
        if (issueInfo == null) {
            issueInfo = new IssueInfo();
        }
        
        issueInfo.setModifier(getCurrentUser().getUserID());
        issueInfo.setIssueId(issueId);
        try {
            // 废弃信息
            service.update("InformationManage.abandonInfo", issueInfo);
            setMessage("informationmanage.abandon.success");
        } catch (BusinessException e) {
            log.error("Abandon info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } catch (Exception e) {
            log.error("Abandon info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_INFO_ABANDON_MID);
            addOperationLog("废弃信息");
        }

        return SUCCESS;
    }
    
    /**
     * 重新发布已废弃信息
     * @return
     */
    public String republishInfo() {
        if (issueInfo == null) {
            issueInfo = new IssueInfo();
        }
        
        issueInfo.setModifier(getCurrentUser().getUserID());
        issueInfo.setIssueId(issueId);
        try {
            // 废弃信息
            service.update("InformationManage.republishInfo", issueInfo);
            setMessage("informationmanage.update.success");
        } catch (BusinessException e) {
            log.error("Republish info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } catch (Exception e) {
            log.error("Republish info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_XS_INFO_PUB_MID);
            addOperationLog("发布信息");
        }

        return SUCCESS;
    }
    
    /**
     * 删除信息
     * @return
     */
    public String deleteInfo() {
        if (issueInfo == null) {
            issueInfo = new IssueInfo();
        }
        
        issueInfo.setModifier(getCurrentUser().getUserID());
        issueInfo.setIssueId(issueId);
        try {
            // 废弃信息
            service.update("InformationManage.deleteInfo", issueInfo);
            setMessage("informationmanage.delete.success");
        } catch (BusinessException e) {
            log.error("Republish info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } catch (Exception e) {
            log.error("Republish info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE, ModuleId.CLW_M_XS_INFO_DELETE_MID);
            addOperationLog("删除信息");
        }

        return SUCCESS;
    }
    
    /**
     * 导出信息
     * @return
     */
    public String exportInfo() {
        Map < String, String > map = new HashMap < String, String >();

        map.put("theme", theme);
        map.put("createTime", createTime);
        map.put("creater", getCurrentUser().getUserID());
        
        List < IssueInfo > list = new ArrayList < IssueInfo >();
        try {
            list = service.getObjects(
                    "InformationManage.getIssueInfos", map);
            getInfoReplyMapList();
            getIssueStatusMapList();
            
            for(IssueInfo issueInfo : list) {
                issueInfo.setIssueStatus(issueStatusMap.get(issueInfo.getIssueStatus()));
                issueInfo.setReplyOrNot(infoReplyMap.get(issueInfo.getReplyOrNot()));
            }
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export info error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID()
                    + "Informations.xls";

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("信息反馈");

            excelExporter.putAutoExtendSheets("exportInformation", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export info error:" + e.getMessage());
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
                "attachment;filename=Informations.xls");
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
            log.error("Export info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export info error:" + e.getMessage());
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
                    ModuleId.CLW_M_XS_INFO_EXPORT_MID);
            addOperationLog("导出信息");
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public List < IssueInfo > getIssueList() {
        return issueList;
    }

    public void setIssueList(List < IssueInfo > issueList) {
        this.issueList = issueList;
    }

    public Map getInformationsMap() {
        return informationsMap;
    }

    public void setInformationsMap(Map informationsMap) {
        this.informationsMap = informationsMap;
    }

    public Map < String, String > getInfoReplyMap() {
        return infoReplyMap;
    }

    public void setInfoReplyMap(Map < String, String > infoReplyMap) {
        this.infoReplyMap = infoReplyMap;
    }

    public Map < String, String > getIssueStatusMap() {
        return issueStatusMap;
    }

    public void setIssueStatusMap(Map < String, String > issueStatusMap) {
        this.issueStatusMap = issueStatusMap;
    }

    public IssueInfo getIssueInfo() {
        return issueInfo;
    }

    public void setIssueInfo(IssueInfo issueInfo) {
        this.issueInfo = issueInfo;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getOldIssueStatus() {
        return oldIssueStatus;
    }

    public void setOldIssueStatus(String oldIssueStatus) {
        this.oldIssueStatus = oldIssueStatus;
    }

    public String getReplyOrNot() {
        return replyOrNot;
    }

    public void setReplyOrNot(String replyOrNot) {
        this.replyOrNot = replyOrNot;
    }
}

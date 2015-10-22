package com.neusoft.clw.yw.xs.noticemanage.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.common.ds.AccessoryInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.noticemanage.domain.AjtNotice;
import com.neusoft.clw.yw.xs.noticemanage.service.AjtNoticeService;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: August 15, 2011 2:48:14 PM
 */
public class AjtNoticeAction extends PaginationAction {

    private List < AjtNotice > noticeList;

    private List < AccessoryInfo > AccessoryList;

    private AjtNotice ajtNotice;

    private AccessoryInfo accessoryinfo;

    private String notice_id;

    private String del_ids = "";

    private String notice_theme;

    private String notice_type;

    private String publish_time;

    private String message = null;

    private String suffix;

    private String accessoryName = "";

    private InputStream targetFile = null;

    private Map map = new HashMap();

    private String accessoryId;

    private transient AjtNoticeService ajtNoticeService;

    private String edit_accessory_ids;

    /** service共通类 */
    private transient Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("ajtnotice.info.location"));
        if (null != message&&"".equals(message)) {
            super.addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 填加企业公告初始化
     * @return
     */
    public String addEnterpriseNotice() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("ajtnotice.addinfo.location"));
        notice_id = UUID.randomUUID().toString();

        return SUCCESS;
    }

    /**
     * 公告列表显示
     * @return
     */
    public String getNoticeList() {
        final String browseTitle = getText("noticemanage.browse.title");
        UserInfo user = getCurrentUser();
        int totalCount = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {

            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            ajtNotice.setSortname(sortName);
            ajtNotice.setSortorder(sortOrder);
            ajtNotice.setPublish_user(user.getUserID());

            if (null != notice_theme && !"".equals(notice_theme)) {
                ajtNotice.setNotice_theme(notice_theme);
            }
            if (null != publish_time && !"".equals(publish_time)) {
                ajtNotice.setPublish_time(publish_time);
            }
            if (null != notice_type && !"".equals(notice_type)) {
                ajtNotice.setNotice_type(notice_type);
            }

            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "1";
            }
            if (StringUtils.isEmpty(rpNum)) {
                rpNum = "10";
            }

            totalCount = service.getCount("NoticeManage.getNoticeCount",
                    ajtNotice);
            noticeList = service.getObjectsByPage(
                    "NoticeManage.getNoticeInfos", ajtNotice, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(noticeList, totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XS_AJTNOTICE_QUERY_MID);
            addOperationLog(formatLog(browseTitle, null));
        }
        return SUCCESS;
    }

    /**
     * 新建页面发布企业公告
     * @return
     */
    public String insertEnterpriseNotice() {

        final String addTitle = getText("noticemanage.add.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            if (notice_id != null) {
                ajtNotice.setNotice_id(notice_id);
            } else {
                ajtNotice.setNotice_id(UUID.randomUUID().toString());
            }

            ajtNotice.setCreater(user.getUserID());
            ajtNotice.setPublish_user(user.getUserID());
            ajtNotice.setDelAccessoryIds(del_ids);

            // service.insert("NoticeManage.saveNoticePublishInfos",
            // enterpriseNotice);

            // 发布公告信息
            ajtNoticeService.insertEnterpriseNotice(
                    "NoticeManage.saveNoticePublishInfos", ajtNotice);
            setMessage("noticemanage.addsuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_AJTNOTICE_ADD_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 新建页面暂存发布企业公告
     * @return
     */
    public String pushEnterpriseNotice() {

        final String addTitle = getText("noticemanage.temp.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            if (notice_id != null) {
                ajtNotice.setNotice_id(notice_id);
            } else {
                ajtNotice.setNotice_id(UUID.randomUUID().toString());
            }

            ajtNotice.setCreater(user.getUserID());
            ajtNotice.setPublish_user(user.getUserID());
            ajtNotice.setDelAccessoryIds(del_ids);

            ajtNoticeService.insertEnterpriseNotice(
                    "NoticeManage.saveNoticeUnPublishInfos", ajtNotice);
            setMessage("noticemanage.tempsuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_AJTNOTICE_TS_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 新建页面企业公告取消操作（删除附件）
     * @return
     */

    public String deleteNoticeAccessory() {

        final String addTitle = getText("noticemanage.cancel.title");
        log.info(addTitle);
        try {
            service.delete("NoticeManage.deleteNoticeAccessoriesByID",
                    notice_id);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 编辑企业公告
     * @return
     */
    public String editEnterpriseNotice() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("ajtnotice.editinfo.location"));
        if (null == ajtNotice) {
            ajtNotice = new AjtNotice();
        }
        notice_id = ajtNotice.getNotice_id();

        if (null == accessoryinfo) {
            accessoryinfo = new AccessoryInfo();
        }
        try {
            ajtNotice = (AjtNotice) service.getObject(
                    "NoticeManage.getNoticeInfosByID", notice_id);

            AccessoryList = service.getObjects(
                    "NoticeManage.getNoticeAccessoriesByID", notice_id);

            edit_accessory_ids = (String) service.getObject(
                    "NoticeManage.getEditAccessoriesByID", notice_id);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 编辑页面发布企业公告
     * @return
     */
    public String insertEditEnterpriseNotice() {

        final String addTitle = getText("noticemanage.add.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            if (notice_id != null) {
                ajtNotice.setNotice_id(notice_id);
            } else {
                ajtNotice.setNotice_id(UUID.randomUUID().toString());
            }

            ajtNotice.setModifier(user.getUserID());
            ajtNotice.setPublish_user(user.getUserID());
            ajtNotice.setDelAccessoryIds(del_ids);

            ajtNoticeService.updateEnterpriseNotice(
                    "NoticeManage.updateNoticePublishInfos", ajtNotice);
            setMessage("noticemanage.addsuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_AJTNOTICE_PUB_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 编辑页面暂存企业公告
     * @return
     */
    public String pushEditEnterpriseNotice() {

        final String addTitle = getText("noticemanage.temp.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            if (notice_id != null) {
                ajtNotice.setNotice_id(notice_id);
            } else {
                ajtNotice.setNotice_id(UUID.randomUUID().toString());
            }

            ajtNotice.setModifier(user.getUserID());
            ajtNotice.setPublish_user(user.getUserID());
            ajtNotice.setDelAccessoryIds(del_ids);

            ajtNoticeService.updateEnterpriseNotice(
                    "NoticeManage.updateNoticeUnPublishInfos", ajtNotice);
            setMessage("noticemanage.tempsuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_AJTNOTICE_TS_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 编辑页面删除企业公告
     * @return
     */
    public String deleteEditEnterpriseNotice() {

        final String addTitle = getText("noticemanage.delete.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            if (notice_id != null) {
                ajtNotice.setNotice_id(notice_id);
            } else {
                ajtNotice.setNotice_id(UUID.randomUUID().toString());
            }

            ajtNotice.setVaset_user_id(user.getUserID());

            service.update("NoticeManage.deleteNoticePublishInfos", ajtNotice);
            setMessage("noticemanage.deletesuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_XS_AJTNOTICE_DELETE_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 编辑页面企业公告取消操作（删除附件）
     * @return
     */

    public String deleteEditNoticeAccessory() {

        final String addTitle = getText("noticemanage.cancel.title");
        log.info(addTitle);
        if (null == ajtNotice) {
            ajtNotice = new AjtNotice();
        }

        ajtNotice.setNotice_id(notice_id);
        ajtNotice.setEdit_accessory_ids(edit_accessory_ids);
        try {
            service.delete("NoticeManage.deleteEditNoticeAccessoriesByID",
                    ajtNotice);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 浏览企业公告
     * @return
     */
    public String viewEnterpriseNotice() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("ajtnotice.viewinfo.location"));
        final String addEditTitle = getText("noticemanage.view.title");
        log.info(addEditTitle);
        if (null == ajtNotice) {
            ajtNotice = new AjtNotice();
        }
        notice_id = ajtNotice.getNotice_id();

        if (null == accessoryinfo) {
            accessoryinfo = new AccessoryInfo();
        }
        try {
            ajtNotice = (AjtNotice) service.getObject(
                    "NoticeManage.getNoticeInfosByID", notice_id);

            AccessoryList = service.getObjects(
                    "NoticeManage.getNoticeAccessoriesByID", notice_id);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addEditTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 浏览页废弃企业公告
     * @return
     */
    public String dropEnterpriseNotice() {

        final String addTitle = getText("noticemanage.drop.title");
        log.info(addTitle);
        try {
            if (null == ajtNotice) {
                ajtNotice = new AjtNotice();
            }
            UserInfo user = getCurrentUser();
            ajtNotice.setNotice_id(notice_id);

            ajtNotice.setModifier(user.getUserID());
            service.update("NoticeManage.validNoticeByID", ajtNotice);
            setMessage("noticemanage.dropsuccess.message");

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_AJTNOTICE_ABANDON_MID);
            addOperationLog(formatLog(addTitle, null));
        }

        return SUCCESS;
    }

    /**
     * 下载附件
     * @return
     */
    public String downAccessory() {
        final String downTitle = getText("navinfo.down.title");
        log.info(downTitle);
        try {
            if (null == accessoryinfo) {
                accessoryinfo = new AccessoryInfo();
            }
            accessoryId = accessoryinfo.getAccessoryId();

            accessoryinfo = (AccessoryInfo) service.getObject(
                    "NoticeManage.getAccessoriesContent", accessoryId);

            accessoryName = accessoryinfo.getAccessoryName();

            suffix = accessoryName.substring(accessoryName.indexOf(".") + 1);

            targetFile = new ByteArrayInputStream(accessoryinfo
                    .getAccessoryContent());
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(downTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 删除附件
     */
    public String deleteAccessory(String accessoryId) {

        String status = "0";
        try {

            service.delete("NoticeManage.deleteAccessoriesByID", accessoryId);

        } catch (BusinessException e1) {
            e1.printStackTrace();
            status = "1";
        }
        return status;
    }

    /**
     * 导出企业公告管理
     * @return
     */
    public String exportEnterpriseNotice() {
        if (null == ajtNotice) {
            ajtNotice = new AjtNotice();
        }

        if (null != notice_theme && !"".equals(notice_theme)) {
            ajtNotice.setNotice_theme(notice_theme);
        }
        if (null != publish_time && !"".equals(publish_time)) {
            ajtNotice.setPublish_time(publish_time);
        }
        if (null != notice_type && !"".equals(notice_type)) {
            ajtNotice.setNotice_type(notice_type);
        }
        UserInfo user = getCurrentUser();

        ajtNotice.setPublish_user(user.getUserID());

        List < AjtNotice > list = new ArrayList < AjtNotice >();

        try {
            list = (List < AjtNotice >) service.getObjects(
                    "NoticeManage.getNoticeInfos", ajtNotice);

            for (int i = 0; i < list.size(); i++) {
            	if (list.get(i).getNotice_type().equals("0")) {
                    list.get(i).setNotice_type(getText("select.type.ajt0"));
                }
                if (list.get(i).getNotice_type().equals("1")) {
                    list.get(i).setNotice_type(getText("select.type.ajt1"));
                }
                if (list.get(i).getNotice_type().equals("2")) {
                    list.get(i).setNotice_type(getText("select.type.ajt2"));
                }
                if (list.get(i).getNotice_status().equals("0")) {
                    list.get(i).setNotice_status(getText("notice.nopublish"));
                }
                if (list.get(i).getNotice_status().equals("1")) {
                    list.get(i).setNotice_status(getText("notice.publish"));
                }
                if (list.get(i).getNotice_status().equals("2")) {
                    list.get(i).setNotice_status(
                            getText("notice.deletepublisth"));
                }

            }
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Notice.xls";

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("安节通公告");

            excelExporter.putAutoExtendSheets("exportAjtNotice", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
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
                "attachment;filename=Notice.xls");
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
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
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
                    ModuleId.CLW_M_XS_AJTNOTICE_EXPORT_MID);
            addOperationLog(formatLog("导出安节通公告", null));

        }
        // 导出文件成
        return null;
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, AjtNotice om) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != om) {
            if (null != om.getNotice_id()) {
                OperateLogFormator.format(sb, "notice_id", om.getNotice_id());
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getPagination(List noticeList, int totalCountDay,
            String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < noticeList.size(); i++) {

            AjtNotice s = (AjtNotice) noticeList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getNotice_id());

            cellMap.put("cell", new Object[] {
                    (i + 1) + (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), s.getNotice_theme(),
                    s.getNotice_type(), s.getPublish_user(),
                    s.getPublish_time(), s.getNotice_status() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public String getAccessoryName() {

        try {
            accessoryName = new String(accessoryName.getBytes("gb2312"),
                    "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("转换中文失败", e);
        }
        return accessoryName;

    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }

    public AccessoryInfo getAccessoryinfo() {
        return accessoryinfo;
    }

    public void setAccessoryinfo(AccessoryInfo accessoryinfo) {
        this.accessoryinfo = accessoryinfo;
    }

    public List < AccessoryInfo > getAccessoryList() {
        return AccessoryList;
    }

    public void setAccessoryList(List < AccessoryInfo > accessoryList) {
        AccessoryList = accessoryList;
    }

    public InputStream getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(InputStream targetFile) {
        this.targetFile = targetFile;
    }

    public String getNotice_theme() {
        return notice_theme;
    }

    public void setNotice_theme(String notice_theme) {
        this.notice_theme = notice_theme;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public void setNotice_type(String notice_type) {
        this.notice_type = notice_type;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public void setNoticeList(List < AjtNotice > noticeList) {
        this.noticeList = noticeList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public String getDel_ids() {
        return del_ids;
    }

    public void setDel_ids(String del_ids) {
        this.del_ids = del_ids;
    }

    public AjtNotice getAjtNotice() {
        return ajtNotice;
    }

    public void setAjtNotice(AjtNotice ajtNotice) {
        this.ajtNotice = ajtNotice;
    }

    public AjtNoticeService getAjtNoticeService() {
        return ajtNoticeService;
    }

    public void setAjtNoticeService(AjtNoticeService ajtNoticeService) {
        this.ajtNoticeService = ajtNoticeService;
    }

    public String getEdit_accessory_ids() {
        return edit_accessory_ids;
    }

    public void setEdit_accessory_ids(String edit_accessory_ids) {
        this.edit_accessory_ids = edit_accessory_ids;
    }
}

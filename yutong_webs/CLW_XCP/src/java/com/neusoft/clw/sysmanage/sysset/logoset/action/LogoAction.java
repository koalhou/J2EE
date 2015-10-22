package com.neusoft.clw.sysmanage.sysset.logoset.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseDataInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class LogoAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    int imageSize;

    String imageTypes;

    int imageWidth;

    int imageHeight;

    private File file = null;

    private String fileFileName;

    private String logoDefaultPath;

    private String videoPath;

    private InputStream targetFile = null;

    private String downloadFileName = "";

    private String no_id = "";

    public String getLogoDefaultPath() {
        return logoDefaultPath;
    }

    public void setLogoDefaultPath(String logoDefaultPath) {
        this.logoDefaultPath = logoDefaultPath;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String logoSetBefore() {
        return SUCCESS;
    }

    public String logoSet() {

        java.io.InputStream is = null;
        java.io.OutputStream os = null;
        try {

            UserInfo userInfo = getCurrentUser();
            String enterprise_id = userInfo.getEntiID();

            if (file == null) {
                addActionError("请选择一个图片");
                return ERROR;
            }

            // 图片小于512k
            if (file.length() > (imageSize)) {
                addActionError("图片过大，已超过" + (imageSize / 1000) + "k,请选择其它较小图片");
                return ERROR;
            }

            log.info("fileFileName:" + fileFileName);
            String exFileName = fileFileName.substring(fileFileName
                    .lastIndexOf(".") + 1);

            List list = Arrays.asList(imageTypes.split(","));

            if (!list.contains(exFileName)) {
                addActionError("图片格式不符合要求,请选择其它格式图片");
                return ERROR;
            }

            if (!imgeCheck(file)) {
                addActionError("图片长宽不符合要求,请选择其它图片");
                return ERROR;
            }

            is = new java.io.FileInputStream(file);

            //String realPath = ServletActionContext.getServletContext()
            //        .getRealPath("/");

            //log.info("realPath:" + realPath);
            exFileName = exFileName.toLowerCase();

            // String logoPath = "/opt/m2mfile/ftp/logo/" + enterprise_id + "."
            // + exFileName;
            String logoPath = logoDefaultPath + enterprise_id + "."
                    + exFileName;

            log.info("logoPath:" + logoPath);
            
            os = new java.io.FileOutputStream(logoPath);
            byte buffer[] = new byte[8192];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
            EnterpriseDataInfo enterpriseDataInfo = new EnterpriseDataInfo();
            enterpriseDataInfo.setEnterprise_id(enterprise_id);
            enterpriseDataInfo.setImg_path(logoPath);
            service.update("EntiManage.updateLogoPath", enterpriseDataInfo);

            addActionMessage(getText("logo.upload_success"));

            // 设置操作描述
            this.addOperationLog("企业LOGO设置");
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_LOGO_UPDATE_ID);

        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        } catch (IOException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        } catch (BusinessException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return SUCCESS;
    }

    /**
     * 设置为默认LOGO
     * @return
     */
    public String setLogoDefault() {

        try {
            UserInfo userInfo = getCurrentUser();
            String enterprise_id = userInfo.getEntiID();

            EnterpriseDataInfo enterpriseDataInfo = new EnterpriseDataInfo();
            enterpriseDataInfo.setEnterprise_id(enterprise_id);
            enterpriseDataInfo.setImg_path("");

            service.update("EntiManage.updateLogoPath", enterpriseDataInfo);
            addActionMessage(getText("logo.default_success"));

            // 设置操作描述
            this.addOperationLog("默认LOGO设置");
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_LOGO_DEFAULT_ID);
        } catch (BusinessException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public boolean imgeCheck(File img) {
        BufferedImage image;
        try {
            image = ImageIO.read(img);

            int srcH = image.getHeight(null);
            int srcW = image.getWidth(null);
            //
            // if (srcH > (imageHeight + 2) || srcH < (imageHeight - 2)) {
            // return false;
            // }
            // if (srcW > (imageWidth + 2) || srcW < (imageWidth - 2)) {
            // return false;
            // }
            if (srcH > imageHeight) {
                return false;
            }
            if (srcW > imageWidth) {
                return false;
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 跳转关于页面
     * @return
     */
    public String about() {
        return SUCCESS;
    }

    /**
     * 下载帮助视频
     * @return
     */
    public String down_video() {
        downloadFileName = no_id + ".wmv";
        targetFile = ServletActionContext.getServletContext()
                .getResourceAsStream(videoPath + downloadFileName);
        if ("01".equals(no_id)) {
            downloadFileName = no_id + "--初次登陆前的准备.wmv";
        } else if ("02".equals(no_id)) {
            downloadFileName = no_id + "--登陆后修改初始密码.wmv";
        } else if ("03".equals(no_id)) {
            downloadFileName = no_id + "--企业LOGO设置.wmv";
        } else if ("04".equals(no_id)) {
            downloadFileName = no_id + "--角色和用户管理.wmv";
        } else if ("05".equals(no_id)) {
            downloadFileName = no_id + "--车队管理.wmv";
        } else if ("06".equals(no_id)) {
            downloadFileName = no_id + "--车辆管理.wmv";
        } else if ("07".equals(no_id)) {
            downloadFileName = no_id + "--线路管理.wmv";
        } else if ("08".equals(no_id)) {
            downloadFileName = no_id + "--驾驶员管理.wmv";
        } else if ("09".equals(no_id)) {
            downloadFileName = no_id + "--加油管理.wmv";
        } else if ("10".equals(no_id)) {
            downloadFileName = no_id + "--短信配置.wmv";
        } else if ("11".equals(no_id)) {
            downloadFileName = no_id + "--车辆分析.wmv";
        } else if ("12".equals(no_id)) {
            downloadFileName = no_id + "--维保信息.wmv";
        } else if ("13".equals(no_id)) {
            downloadFileName = no_id + "--围栏设置.wmv";
        } else if ("14".equals(no_id)) {
            downloadFileName = no_id + "--考核设置.wmv";
        } else if ("15".equals(no_id)) {
            downloadFileName = no_id + "--节油管理.wmv";
        } else if ("16".equals(no_id)) {
            downloadFileName = no_id + "--安全管理.wmv";
        } else if ("17".equals(no_id)) {
            downloadFileName = no_id + "--实时监控.wmv";
        }
        return SUCCESS;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageTypes() {
        return imageTypes;
    }

    public void setImageTypes(String imageTypes) {
        this.imageTypes = imageTypes;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public InputStream getTargetFile() {
        return targetFile;
    }

    public void setTargetFile(InputStream targetFile) {
        this.targetFile = targetFile;
    }

    public String getDownloadFileName() {
        String downFileName = "";
        try {

            downFileName = new String(downloadFileName.getBytes(), "ISO8859-1");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }
        return downFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getNo_id() {
        return no_id;
    }

    public void setNo_id(String no_id) {
        this.no_id = no_id;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}

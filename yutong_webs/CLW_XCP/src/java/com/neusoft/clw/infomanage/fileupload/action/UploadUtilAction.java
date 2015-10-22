/*******************************************************************************
 * @(#)UploadUtilAction.java 2012-5-3
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.fileupload.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.infomanage.fileupload.domain.UploadFileInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-5-3 下午03:50:23
 */
public class UploadUtilAction extends BaseAction {
    /** service共通类 */
    private transient Service service;
    
    private File fileupload; // 和JSP中input标记name同名

    private String imageUrl;

    private String attachmentUrl;

    private String fileRealName;

    private String fileuploadFileName; // 上传来的文件的名字

    /** 截取的图片尺寸 **/
    private String top = "";
    private String left = "";
    private String width = "";
    private String height = "";
    
    /** 数据ID **/
    private String dataId = "";

    /** 放大、缩小 **/
    private String zoomType = "";
    
    /** 检查是否是图片格式 **/
    public static boolean checkIsImage(String imgStr) {
        boolean flag = false;
        if (imgStr != null) {
            if (imgStr.equalsIgnoreCase(".gif")
                    || imgStr.equalsIgnoreCase(".jpg")
                    || imgStr.equalsIgnoreCase(".jpeg")
                    || imgStr.equalsIgnoreCase(".bmp")
                    || imgStr.equalsIgnoreCase(".png")) {
                flag = true;
            }
        }
        return flag;
    }

    public String init() {
        dataId = UUIDGenerator.getUUID32();
        UploadFileInfo uploadFileInfo = new UploadFileInfo();
        uploadFileInfo.setDataId(dataId);
        try {
            service.update("FileUpload.insertPhoto", uploadFileInfo);
        } catch (BusinessException e) {
            ;
        }
        return SUCCESS;
    }
    
    public String uploadFile() {
        // 保存文件拓展名
        String extName = ""; 

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk"); // 务必，防止返回文件名是乱码
        
        // 获取拓展名
        if (fileuploadFileName.lastIndexOf(".") >= 0) {
            extName = fileuploadFileName.substring(fileuploadFileName
                    .lastIndexOf("."));
        }
        try {
            // 检查上传的是否是图片
            if (checkIsImage(extName)) {
                String strFileName = fileupload.getAbsolutePath().toLowerCase();

                BufferedInputStream in = null;
                ByteArrayOutputStream outStream = null;

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    outStream = new ByteArrayOutputStream(1024);

                    while ((size = in.read(temp)) != -1) {
                        outStream.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }

                byte[] content = outStream.toByteArray();

                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(dataId);
                // 创建附件信息bean
                uploadFileInfo.setPhotoContent(content);
                uploadFileInfo.setPhoto_name(fileuploadFileName);
                service.update("FileUpload.updatePhoto", uploadFileInfo);
                response.getWriter()
                        .print("<font color='red'>" + fileuploadFileName
                                + "上传成功!</font>#" + "filePath" + "#"
                                + fileuploadFileName);
            } else {
                response.getWriter()
                        .print("<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>");
            }
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            try {
                response.getWriter().print("上传失败，出错啦!");
            } catch (IOException e1) {
               ;
            }
        } catch (BusinessException e) {
            try {
                response.getWriter().print("上传失败，出错啦!");
            } catch (IOException e1) {
                ;
            }
        }
        return null;
    }

    public String showImage() throws Exception {
        // 根据图片地址构造file对象
        UploadFileInfo uploadFileInfo = new UploadFileInfo();
        uploadFileInfo.setDataId(dataId);
        uploadFileInfo = (UploadFileInfo) service.getObject(
                "FileUpload.getPhoto", uploadFileInfo);
        
        ByteArrayInputStream is = new ByteArrayInputStream(uploadFileInfo.getPhotoContent());
        // 读图片
        Image image = ImageIO.read(is);
        String imageType = "";
        // 获取拓展名
        if (null != uploadFileInfo.getPhoto_name() && uploadFileInfo.getPhoto_name().lastIndexOf(".") >= 0) {
            imageType = uploadFileInfo.getPhoto_name().substring(uploadFileInfo.getPhoto_name()
                    .lastIndexOf(".") + 1);
        }
        
        RenderedImage img = (RenderedImage) image;
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream out = response.getOutputStream();
        ImageIO.write(img, imageType, out);
        out.flush();
        out.close();
        return null;
    }

    
    /**
     * 图片缩放
     */
    public String resizeImage() {
        HttpServletResponse response = ServletActionContext.getResponse(); 
        response.setCharacterEncoding("UTF-8");
        
        try {
            UploadFileInfo uploadFileInfo = new UploadFileInfo();
            uploadFileInfo.setDataId(dataId);
            uploadFileInfo = (UploadFileInfo) service.getObject(
                    "FileUpload.getPhoto", uploadFileInfo);

            ByteArrayInputStream is = new ByteArrayInputStream(uploadFileInfo
                    .getPhotoContent());
            String imageType = "";
            // 获取拓展名
            if (null != uploadFileInfo.getPhoto_name()
                    && uploadFileInfo.getPhoto_name().lastIndexOf(".") >= 0) {
                imageType = uploadFileInfo.getPhoto_name().substring(
                        uploadFileInfo.getPhoto_name().lastIndexOf(".") + 1);
            }

            BufferedImage bi = ImageIO.read(is);
            int height, width;
            if ("bigger".equals(zoomType)) {
                height = (int) (bi.getHeight() * 1.1);
                width = (int) (bi.getWidth() * 1.1);
            } else {
                height = (int) (bi.getHeight() * 0.9);
                width = (int) (bi.getWidth() * 0.9);
            }

            Image itemp = bi.getScaledInstance(width, height,
                    BufferedImage.SCALE_SMOOTH);

            // 计算比例
            /**
             * double ratio = 0; // 缩放比例 if ((bi.getHeight() > height) ||
             * (bi.getWidth() > width)) { if (bi.getHeight() > bi.getWidth()) {
             * ratio = (new Integer(height)).doubleValue() / bi.getHeight(); }
             * else { ratio = (new Integer(width)).doubleValue() /
             * bi.getWidth(); } AffineTransformOp op = new
             * AffineTransformOp(AffineTransform .getScaleInstance(ratio,
             * ratio), null); itemp = op.filter(bi, null); }
             **/

            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);
            if (width == itemp.getWidth(null))
                g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                        itemp.getWidth(null), itemp.getHeight(null),
                        Color.white, null);
            else
                g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp
                        .getWidth(null), itemp.getHeight(null), Color.white,
                        null);
            g.dispose();
            itemp = image;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage) itemp, imageType, outputStream);

            byte[] content = outputStream.toByteArray();

            UploadFileInfo newUploadFileInfo = new UploadFileInfo();
            newUploadFileInfo.setDataId(dataId);
            // 创建附件信息bean
            newUploadFileInfo.setPhotoContent(content);
            newUploadFileInfo.setPhoto_name(uploadFileInfo.getPhoto_name());
            service.update("FileUpload.updatePhoto", newUploadFileInfo);

            response.getWriter().write("success");

        } catch (Exception e) {
            log.info(e.getMessage());
            try {
                response.getWriter().write("error");
            } catch (IOException e1) {
                ;
            }
        }
        return null;
    }
    
    /** 
     * 保存图片 
     * @throws IOException 
     */ 
    public void saveImage() throws IOException {
        String ret = "";
        String ext = "jpg";
        
        UploadFileInfo uploadFileInfo = new UploadFileInfo();
        uploadFileInfo.setDataId(dataId);
        try {
            uploadFileInfo = (UploadFileInfo) service.getObject(
                    "FileUpload.getPhoto", uploadFileInfo);
        } catch (BusinessException e) {
        }
        
        ByteArrayInputStream is = new ByteArrayInputStream(uploadFileInfo.getPhotoContent());
        
        BufferedImage bi = (BufferedImage) ImageIO.read(is);
        
        // 图片转换成JPG - start
        BufferedImage bufferedChange=new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_BGR);
        Graphics2D g = (Graphics2D) bufferedChange.getGraphics();
        g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
        g.dispose();
        bufferedChange.flush();
        ByteArrayOutputStream formattedImg = new ByteArrayOutputStream();
        ImageIO.write(bufferedChange, "JPG", formattedImg);
        ByteArrayInputStream formattedStrem = new ByteArrayInputStream(formattedImg.toByteArray());
        bi = ImageIO.read(formattedStrem);
        // 图片转换成JPG - end
        
        int height_val = 0;
        height_val = Math.min(Integer.valueOf(height), bi.getHeight());
        int width_val = 0;
        width_val = Math.min(Integer.valueOf(width), bi.getWidth());
        if (height_val <= 0) height_val = bi.getHeight();
        if (width_val <= 0) width_val = bi.getWidth();
        int top_val = 0;
        top_val = Math.min(Math.max(0, Integer.valueOf(top)), bi.getHeight()
                - height_val);
        int left_val = 0;
        left_val = Math.min(Math.max(0, Integer.valueOf(left)), bi.getWidth()
                - width_val);

        BufferedImage bi_cropper = bi.getSubimage(left_val, top_val, width_val,
                height_val);
        
        ByteArrayOutputStream picOutputStream = new ByteArrayOutputStream();
        if (ImageIO.write(bi_cropper, ext, picOutputStream)) {
            UploadFileInfo formatFile = new UploadFileInfo();
            formatFile.setDataId(dataId);
            formatFile.setPhoto_name("formatted.jpg");
            formatFile.setPhotoContent(picOutputStream.toByteArray());
            try {
                service.update("FileUpload.updatePhoto", formatFile);
            } catch (BusinessException e) {
                ret = "error";
            }
            ret = "success";
        } else {
            ret = "error";
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(ret);
        } catch (IOException ioException) {
            ;
        }
    } 
    
    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    public File getFileupload() {
        return fileupload;
    }

    public void setFileupload(File fileupload) {
        this.fileupload = fileupload;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public String getFileuploadFileName() {
        return fileuploadFileName;
    }

    public void setFileuploadFileName(String fileuploadFileName) {
        this.fileuploadFileName = fileuploadFileName;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getZoomType() {
        return zoomType;
    }

    public void setZoomType(String zoomType) {
        this.zoomType = zoomType;
    }
}

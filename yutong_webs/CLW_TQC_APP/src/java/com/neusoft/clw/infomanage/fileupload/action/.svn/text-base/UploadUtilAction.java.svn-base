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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.UnicodeConverter;
import com.neusoft.clw.infomanage.fileupload.domain.UploadFileInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-5-3 下午03:50:23
 */
public class UploadUtilAction extends BaseAction {
    /** service共通类 */
    private transient Service service;
    private String errorMessage;
    private String message;
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
    private String filepath;
    /** 数据ID **/
    private String dataId = "";

    /** 放大、缩小 **/
    private String zoomType = "";
    private UploadFileInfo ufi;
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
    /** 检查是否是apk格式 **/
    public static boolean checkIsApk(String imgStr) {
        boolean flag = false;
        if (imgStr != null) {
            if (imgStr.equalsIgnoreCase(".apk")) {
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
    public String apkinit() {
    	if (errorMessage != null) {
            addActionError(UnicodeConverter.fromEncodedUnicode(errorMessage.toCharArray(), 0, errorMessage.length()));//UnicodeConverter.fromEncodedUnicode(errorMessage.toCharArray(), 0, errorMessage.length())
        }
        if(message!=null){
            addActionMessage(UnicodeConverter.fromEncodedUnicode(message.toCharArray(), 0, message.length()));
        }
        
        return SUCCESS;
    }
    public String apkdownloadFile() {
    	HttpServletResponse response = ServletActionContext.getResponse();
        try {
        	this.ufi = (UploadFileInfo) service.getObject("FileUpload.getsys_v_t", null);
        } catch (BusinessException e) {
            ;
        }
        
        response.setHeader("Content-disposition","attachment;filename="+this.ufi.getSoftware_uri());
		//response.setContentType("application/msexcel; charset=\"utf-8\"");
        FileInputStream fileInputStream = null;
		OutputStream out = null;
		try {
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(filepath+this.ufi.getSoftware_uri());
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
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
		}
        return null;
    }
    public String uploadFile() {
        // 保存文件拓展名
        String extName = ""; 
        String errTxt = "";
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
                //BufferedInputStream in = null;
                //ByteArrayOutputStream outStream = null;
                
                
//                File picture = new File(strFileName);  
//                long s = fis.available();
                //byte[] temp = new byte[1024];
                //int size = 0;
                byte[] data = null;
                try {
                    //in = new BufferedInputStream(new FileInputStream(strFileName));
                  //文件大小
                    FileInputStream fis = new FileInputStream(fileupload);
                    long s = fis.available()/1048576;
                    
//                    BufferedImage sourceImg =ImageIO.read(fis);
                    if(s > 1.5){
                    	errTxt = "<font color='red'>上传的文件大小过大,请上传不大于1.5M的图片!</font>";
                    	response.getWriter().print(errTxt);
                    	return null;
                    } 
                    data = new byte[fis.available()];
                    fis.read(data);
                    fis.close();              
//                    if(sourceImg.getWidth() > 1024 && sourceImg.getHeight() > 768){
//                    	errTxt = "<font color='red'>上传的文件尺寸过大,请上传小于1024*768/768*1024尺寸图片!</font>";
//                    	response.getWriter().print(errTxt);
//                    	return null;
//                    } 
                    
                    //outStream = new ByteArrayOutputStream(1024);

                    //while ((size = in.read(temp)) != -1) {
                        //outStream.write(temp, 0, size);
                    //}
                } catch (IOException e) {
                    ;
                } 
//                finally {
//                    if (in != null) {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            ;
//                        }
//                    }
//                } 
                
             // 对字节数组Base64编码 
                BASE64Encoder encoder = new BASE64Encoder();   
                String str = encoder.encode(data);// 返回Base64编码过的字节数组字符串 

                //byte[] content = outStream.toByteArray();
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(dataId);
                // 创建附件信息bean
                
                if (str == null){ // 图像数据为空   
                	return null;
                }   
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] bytes = null;
                try {   
                	// Base64解码   
                	bytes = decoder.decodeBuffer(str);   
                	for (int i = 0; i < bytes.length; ++i) {   
	                	if (bytes[i] < 0) {// 调整异常数据   
	                		bytes[i] += 256;   
	                	}   
                	}   
//                	// 生成jpeg图片   
//                	OutputStream out = new FileOutputStream(imgFilePath);   
//                	out.write(bytes);   
//                	out.flush();   
//                	out.close();   
//                	return true;   
//                	} catch (Exception e) {   
//                	return false;   
                }catch (Exception e) {
				}   
                
                uploadFileInfo.setPhotoContent(bytes);
                uploadFileInfo.setPhoto_name(fileuploadFileName);
                service.update("FileUpload.updatePhoto", uploadFileInfo);
                response.getWriter().print("<font color='green'>" + fileuploadFileName+ "上传成功!</font>#" + "filePath" + "#"+ fileuploadFileName);
//                response.getWriter()
//                .print("<font color='green'>上传成功!</font>");
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
    public String apkuploadFile() {
        // 保存文件拓展名
        String extName = ""; 
//        String errTxt = "";
        //HttpServletResponse response = ServletActionContext.getResponse();
        //response.setCharacterEncoding("gbk"); // 务必，防止返回文件名是乱码
        FileOutputStream fos=null;
        InputStream in = null;
        // 获取拓展名
        if (fileuploadFileName.lastIndexOf(".") >= 0) {
            extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));
        }
        //try {
            // 检查上传的是否是图片
            if (checkIsApk(extName)) {
                String strFileName = fileupload.getAbsolutePath().toLowerCase();

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                	//判断文件是否是空
                	if(fileupload.length()<1) {
                		this.setErrorMessage(UnicodeConverter.toEncodedUnicode("<font color='red'>文件不能为空!</font>", true));
                    	return ERROR;
//                		response.getWriter().print("<font color='red'>文件不能为空!</font>");
                	}
                	//文件大小
                	File dirs=new File(filepath);
                    if(!dirs.exists()){
                 	   if(!dirs.mkdirs()){
//        	            	errTxt = "<font color='red'>文件服务器路径不存在!</font>";
//        	            	response.getWriter().print(errTxt);
        	            	this.setErrorMessage(UnicodeConverter.toEncodedUnicode("<font color='red'>文件服务器路径不存在!</font>", true));
                        	return ERROR;
                       }
                    }
                    filepath = filepath+fileuploadFileName;//DateUtil.getCurrentDayTime()+"#!#"+
                    in = new FileInputStream(strFileName);
                    fos=new FileOutputStream(filepath);
                    while ((size = in.read(temp)) != -1) {
                    	fos.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                    		if (in != null) in.close();
                            if (fos!=null) fos.close();
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }
                ufi.setSoftware_uri(fileuploadFileName);
                try {
                    service.insert("FileUpload.insertsys_a_tApk", ufi);
                } catch (BusinessException e) {
                	this.setErrorMessage("siteareainfo.adderror.message");
                	return ERROR;
                }
                String str = UnicodeConverter.toEncodedUnicode("<font color='green'>" + fileuploadFileName+ "上传成功!</font>", true);
                this.setMessage(str);
                //response.getWriter().print("<font color='green'>" + fileuploadFileName+ "上传成功!</font>");
            } else {
            	this.setErrorMessage(UnicodeConverter.toEncodedUnicode("<font color='red'>上传的文件类型错误，请选择apk格式的文件!</font>", true));
                //response.getWriter().print("<font color='red'>上传的文件类型错误，请选择apk格式的文件!</font>");
                return ERROR;
            }
//            response.getWriter().flush();
//            response.getWriter().close();
        /*} catch (IOException e) {
            try {
            	this.setErrorMessage(UnicodeConverter.toEncodedUnicode("error", true));
                //response.getWriter().print("error");
            } catch (IOException e1) {
               ;
            }
        } */
        return SUCCESS;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public UploadFileInfo getUfi() {
		return ufi;
	}
	public void setUfi(UploadFileInfo ufi) {
		this.ufi = ufi;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}

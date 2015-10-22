/*******************************************************************************
 * @(#)UploadUtilAction.java 2012-5-3
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.newmodel.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.UnicodeConverter;
import com.neusoft.clw.yw.newmodel.domain.UploadFileInfo;

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
    private String filepath = "/opt/m2mfile/ftp/ygbapk/";//"d:/";
    private String imageUrl;

    private String attachmentUrl;

    private String fileRealName;

    private String fileuploadFileName; // 上传来的文件的名字

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

                BufferedInputStream in = null;
                ByteArrayOutputStream outStream = null;
                
                
//                File picture = new File(strFileName);  
//                long s = fis.available();
                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    
                  //文件大小
                    FileInputStream fis = new FileInputStream(strFileName);
                    long s = fis.available()/1048576;
                    
//                    BufferedImage sourceImg =ImageIO.read(fis);
                    if(s > 1.5){
                    	errTxt = "<font color='red'>上传的文件大小过大,请上传不大于1.5M的图片!</font>";
                    	response.getWriter().print(errTxt);
                    	return null;
                    }
//                    if(sourceImg.getWidth() > 1024 && sourceImg.getHeight() > 768){
//                    	errTxt = "<font color='red'>上传的文件尺寸过大,请上传小于1024*768/768*1024尺寸图片!</font>";
//                    	response.getWriter().print(errTxt);
//                    	return null;
//                    } 
                    
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

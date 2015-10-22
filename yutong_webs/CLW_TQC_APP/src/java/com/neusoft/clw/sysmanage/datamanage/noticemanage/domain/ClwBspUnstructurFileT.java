/**
 *    主要功能  : 非结构文件信息表   .
 */
package com.neusoft.clw.sysmanage.datamanage.noticemanage.domain;


import java.io.Serializable;
import java.util.Date;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2013-11-27  15:47:36
 * Created By:
 * 主要功能  : 非结构文件信息表
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class ClwBspUnstructurFileT implements Serializable{
    /**
	 * 公告上传图片信息
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键，文件ID . 
     */
    private Integer fileId;
    /**
     * 文件名 . 
     */
    private String fileName;
    /**
     * 文件描述 . 
     */
    private String fileDesc;
    /**
     * 0：文件存储方式；1：数据库存储方式 . 
     */
    private Integer fileStorage;
    /**
     * 文件服务器路径 . 
     */
    private String fileServer;
    /**
     * 文件本地路径 . 
     */
    private String filePath;
    /**
     * 文件类型 . 
     */
    private Integer fileType;
    /**
     * 文件后缀 . 
     */
    private String fileSuffix;
    /**
     * 文件长度 . 
     */
    private Integer fileLength;
    /**
     *  . 
     */
    private Integer applyId;
    /**
     *  . 
     */
    private Integer moduleId;
    /**
     *  . 
     */
    private Integer fileDlcount;
    /**
     *  . 
     */
    private Integer creater;
    /**
     *  . 
     */
    private Date createTime;
    /**
     *  . 
     */
    private Integer modifier;
    /**
     *  . 
     */
    private Date modifyTime;
    /**
     * 创建时间
     */
    private String screateDate;
    public String getScreateDate() {
		return screateDate;
	}
	public void setScreateDate(String screateDate) {
		this.screateDate = screateDate;
	}
	/**
     * 初始化单元对象 .
     */ 
    public  ClwBspUnstructurFileT(){
    }
    /**
     * 设置主键，文件ID .
     * @param fileId 主键，文件ID
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    /**
     * 获得主键，文件ID .
     * @return 主键，文件ID
     */
    public Integer getFileId() {
        return fileId;
    }
    /**
     * 设置文件名 .
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * 获得文件名 .
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * 设置文件描述 .
     * @param fileDesc 文件描述
     */
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }
    /**
     * 获得文件描述 .
     * @return 文件描述
     */
    public String getFileDesc() {
        return fileDesc;
    }
    /**
     * 设置0：文件存储方式；1：数据库存储方式 .
     * @param fileStorage 0：文件存储方式；1：数据库存储方式
     */
    public void setFileStorage(Integer fileStorage) {
        this.fileStorage = fileStorage;
    }
    /**
     * 获得0：文件存储方式；1：数据库存储方式 .
     * @return 0：文件存储方式；1：数据库存储方式
     */
    public Integer getFileStorage() {
        return fileStorage;
    }
    /**
     * 设置文件服务器路径 .
     * @param fileServer 文件服务器路径
     */
    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }
    /**
     * 获得文件服务器路径 .
     * @return 文件服务器路径
     */
    public String getFileServer() {
        return fileServer;
    }
    /**
     * 设置文件本地路径 .
     * @param filePath 文件本地路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * 获得文件本地路径 .
     * @return 文件本地路径
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * 设置文件类型 .
     * @param fileType 文件类型
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
    /**
     * 获得文件类型 .
     * @return 文件类型
     */
    public Integer getFileType() {
        return fileType;
    }
    /**
     * 设置文件后缀 .
     * @param fileSuffix 文件后缀
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
    /**
     * 获得文件后缀 .
     * @return 文件后缀
     */
    public String getFileSuffix() {
        return fileSuffix;
    }
    /**
     * 设置文件长度 .
     * @param fileLength 文件长度
     */
    public void setFileLength(Integer fileLength) {
        this.fileLength = fileLength;
    }
    /**
     * 获得文件长度 .
     * @return 文件长度
     */
    public Integer getFileLength() {
        return fileLength;
    }
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }
    public Integer getApplyId() {
        return applyId;
    }
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
    public Integer getModuleId() {
        return moduleId;
    }
    public void setFileDlcount(Integer fileDlcount) {
        this.fileDlcount = fileDlcount;
    }
    public Integer getFileDlcount() {
        return fileDlcount;
    }
    public void setCreater(Integer creater) {
        this.creater = creater;
    }
    public Integer getCreater() {
        return creater;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }
    public Integer getModifier() {
        return modifier;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
}

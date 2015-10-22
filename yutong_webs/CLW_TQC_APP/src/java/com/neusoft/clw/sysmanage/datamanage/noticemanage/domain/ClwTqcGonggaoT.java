/**
 *    主要功能  : 公告维护表   .
 */
package com.neusoft.clw.sysmanage.datamanage.noticemanage.domain;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2013-11-27  15:47:44
 * Created By:
 * 主要功能  : 公告维护表
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class ClwTqcGonggaoT implements Serializable{
    /**
	 * 公告信息
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 主键 . 
     */
    private Integer gonggaoId;
    /**
     * 作者 . 
     */
    private String gonggaoAuthor;
    /**
     * 公告标题 . 
     */
    private String gonggaoTitle;
    /**
     * 公告概要信息 . 
     */
    private String gonggaoSummary;
    /**
     * 公告内容 . 
     */
    private String gonggaoContent;
    /**
     * 公告时间 . 
     */
    private Date gonggaoDate;
    /**
     * 公告类型 . 
     */
    private String gonggaoType;
    /**
     * 是否置顶 . 
     */
    private String isTop;
    /**
     * 资源ID1 . 
     */
    private String photo1;
    /**
     * 资源ID2 . 
     */
    private String photo2;
    /**
     * 资源ID3 . 
     */
    private String photo3;
    /**
     * 资源ID4 . 
     */
    private String photo4;
    /**
     * 资源ID5 . 
     */
    private String photo5;
    /**
     * 推送标识 0：未推送，1：已经推送 . 
     */
    private String sendFlag;
    
    private String sortname;
    
    private String sortorder;
    /**
     * 公告日期字符串
     */
    private String sgonggaoDate;
    public String getSgonggaoDate() {
    	if(this.sgonggaoDate!=null && !"".equals(this.sgonggaoDate)){
    		this.sgonggaoDate=this.sgonggaoDate.substring(0,19);
    	}
		return sgonggaoDate;
	}
	public void setSgonggaoDate(String sgonggaoDate) {
		this.sgonggaoDate = sgonggaoDate;
	}
	/**
     * 初始化单元对象 .
     */ 
    public  ClwTqcGonggaoT(){
    }
    /**
     * 设置主键 .
     * @param gonggaoId 主键
     */
    public void setGonggaoId(Integer gonggaoId) {
        this.gonggaoId = gonggaoId;
    }
    /**
     * 获得主键 .
     * @return 主键
     */
    public Integer getGonggaoId() {
        return gonggaoId;
    }
    /**
     * 设置作者 .
     * @param gonggaoAuthor 作者
     */
    public void setGonggaoAuthor(String gonggaoAuthor) {
        this.gonggaoAuthor = gonggaoAuthor;
    }
    /**
     * 获得作者 .
     * @return 作者
     */
    public String getGonggaoAuthor() {
        return gonggaoAuthor;
    }
    /**
     * 设置公告标题 .
     * @param gonggaoTitle 公告标题
     */
    public void setGonggaoTitle(String gonggaoTitle) {
        this.gonggaoTitle = gonggaoTitle;
    }
    /**
     * 获得公告标题 .
     * @return 公告标题
     */
    public String getGonggaoTitle() {
        return gonggaoTitle;
    }
    /**
     * 设置公告概要信息 .
     * @param gonggaoSummary 公告概要信息
     */
    public void setGonggaoSummary(String gonggaoSummary) {
        this.gonggaoSummary = gonggaoSummary;
    }
    /**
     * 获得公告概要信息 .
     * @return 公告概要信息
     */
    public String getGonggaoSummary() {
        return gonggaoSummary;
    }
    /**
     * 设置公告内容 .
     * @param gonggaoContent 公告内容
     */
    public void setGonggaoContent(String gonggaoContent) {
        this.gonggaoContent = gonggaoContent;
    }
    /**
     * 获得公告内容 .
     * @return 公告内容
     */
    public String getGonggaoContent() {
        return gonggaoContent;
    }
    /**
     * 设置公告时间 .
     * @param gonggaoDate 公告时间
     */
    public void setGonggaoDate(Date gonggaoDate) {
        this.gonggaoDate = gonggaoDate;
    }
    /**
     * 获得公告时间 .
     * @return 公告时间
     */
    public Date getGonggaoDate() {
        return gonggaoDate;
    }
    /**
     * 设置公告类型 .
     * @param gonggaoType 公告类型
     */
    public void setGonggaoType(String gonggaoType) {
        this.gonggaoType = gonggaoType;
    }
    /**
     * 获得公告类型 .
     * @return 公告类型
     */
    public String getGonggaoType() {
        return gonggaoType;
    }
    /**
     * 设置是否置顶 .
     * @param isTop 是否置顶
     */
    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }
    /**
     * 获得是否置顶 .
     * @return 是否置顶
     */
    public String getIsTop() {
        return isTop;
    }
    /**
     * 设置资源ID1 .
     * @param photo1 资源ID1
     */
    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }
    /**
     * 获得资源ID1 .
     * @return 资源ID1
     */
    public String getPhoto1() {
        return photo1;
    }
    /**
     * 设置资源ID2 .
     * @param photo2 资源ID2
     */
    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }
    /**
     * 获得资源ID2 .
     * @return 资源ID2
     */
    public String getPhoto2() {
        return photo2;
    }
    /**
     * 设置资源ID3 .
     * @param photo3 资源ID3
     */
    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }
    /**
     * 获得资源ID3 .
     * @return 资源ID3
     */
    public String getPhoto3() {
        return photo3;
    }
    /**
     * 设置资源ID4 .
     * @param photo4 资源ID4
     */
    public void setPhoto4(String photo4) {
        this.photo4 = photo4;
    }
    /**
     * 获得资源ID4 .
     * @return 资源ID4
     */
    public String getPhoto4() {
        return photo4;
    }
    /**
     * 设置资源ID5 .
     * @param photo5 资源ID5
     */
    public void setPhoto5(String photo5) {
        this.photo5 = photo5;
    }
    /**
     * 获得资源ID5 .
     * @return 资源ID5
     */
    public String getPhoto5() {
        return photo5;
    }
    /**
     * 设置推送标识 0：未推送，1：已经推送 .
     * @param sendFlag 推送标识 0：未推送，1：已经推送
     */
    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }
    /**
     * 获得推送标识 0：未推送，1：已经推送 .
     * @return 推送标识 0：未推送，1：已经推送
     */
    public String getSendFlag() {
        return sendFlag;
    }
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public String getSortorder() {
		return sortorder;
	}
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
    
    
}

/**
 *    主要功能  : 意见建议表   .
 */
package com.neusoft.clw.feedback.domain;


import java.util.*;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2013-11-27  15:47:47
 * Created By:
 * 主要功能  : 意见建议表
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class FeedBackMsg {
    /**
     * 主键 . 
     */
    private Integer suggestId;
    /**
     *  . 
     */
    private String content;
    /**
     *  . 
     */
    private String suggestDate;
    /**
     *  . 
     */
    private String owner;
    /**
     *  . 
     */
    private String type;
    
    private String empCode;
    /**
     * 初始化单元对象 .
     */ 
    public  FeedBackMsg(){
    }
    /**
     * 设置主键 .
     * @param suggestId 主键
     */
    public void setSuggestId(Integer suggestId) {
        this.suggestId = suggestId;
    }
    /**
     * 获得主键 .
     * @return 主键
     */
    public Integer getSuggestId() {
        return suggestId;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setSuggestDate(String suggestDate) {
        this.suggestDate = suggestDate;
    }
    public String getSuggestDate() {
        return suggestDate;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
    
}

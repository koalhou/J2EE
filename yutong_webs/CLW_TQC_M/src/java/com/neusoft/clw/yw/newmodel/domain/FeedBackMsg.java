/**
 *    主要功能  : 意见建议表   .
 */
package com.neusoft.clw.yw.newmodel.domain;


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
    
    private String status;
    private String operator;
    private String operate_time;
    private String ygb_tel;
    private String operate_content;
    private String exportRownum;
    private String msg_id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	public String getYgb_tel() {
		return ygb_tel;
	}
	public void setYgb_tel(String ygb_tel) {
		this.ygb_tel = ygb_tel;
	}
	public String getOperate_content() {
		return operate_content;
	}
	public void setOperate_content(String operate_content) {
		this.operate_content = operate_content;
	}
	public String getExportRownum() {
		return exportRownum;
	}
	public void setExportRownum(String exportRownum) {
		this.exportRownum = exportRownum;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	
    
}

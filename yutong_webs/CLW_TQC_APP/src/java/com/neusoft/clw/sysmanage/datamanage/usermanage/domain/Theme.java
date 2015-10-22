/**
 *    主要功能  : 用户界面配置表   .
 */
package com.neusoft.clw.sysmanage.datamanage.usermanage.domain;


import java.util.*;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2014-7-15  8:44:11
 * Created By:
 * 主要功能  : 用户界面配置表
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class Theme {
    /**
     * 主键 . 
     */
    private String id;
    /**
     * 用户主键 . 
     */
    private String userId;
    /**
     * 头部样式 . 
     */
    private String headerclass;
    private byte[] headerclass_p;
    /**
     * 头部背景样式 . 
     */
    private String headbgclass;
    private byte[] headbgclass_p;
    /**
     * 公司logo . 
     */
    private String companylogo;
    private byte[] companylogo_p;
    /**
     * 页脚样式 . 
     */
    private String footerclass;
    /**
     * 地理位置加载 . 
     */
    private String ismapgroundlogo;
    
	private String loginName;
	private String userName;
	private String enterpriseCode;
	private String shortName;
	private String navphotos;
	
	private byte[] mi1_p;
	private byte[] mi2_p;
	private byte[] mi3_p;
	private byte[] mi4_p;
	private byte[] mi5_p;
	
	private byte[] nav1focus;
	private byte[] nav2focus;
	private byte[] nav3focus;
	private byte[] nav4focus;
	private byte[] nav5focus;
	
	private byte[] navphotos_p;
	
    /**
     * 初始化单元对象 .
     */ 
    public  Theme(){
    }
    /**
     * 设置主键 .
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获得主键 .
     * @return 主键
     */
    public String getId() {
        return id;
    }
    /**
     * 设置用户主键 .
     * @param userId 用户主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获得用户主键 .
     * @return 用户主键
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置头部样式 .
     * @param headerclass 头部样式
     */
    public void setHeaderclass(String headerclass) {
        this.headerclass = headerclass;
    }
    /**
     * 获得头部样式 .
     * @return 头部样式
     */
    public String getHeaderclass() {
        return headerclass;
    }
    /**
     * 设置头部背景样式 .
     * @param headbgclass 头部背景样式
     */
    public void setHeadbgclass(String headbgclass) {
        this.headbgclass = headbgclass;
    }
    /**
     * 获得头部背景样式 .
     * @return 头部背景样式
     */
    public String getHeadbgclass() {
        return headbgclass;
    }
    /**
     * 设置公司logo .
     * @param companylogo 公司logo
     */
    public void setCompanylogo(String companylogo) {
        this.companylogo = companylogo;
    }
    /**
     * 获得公司logo .
     * @return 公司logo
     */
    public String getCompanylogo() {
        return companylogo;
    }
    /**
     * 设置页脚样式 .
     * @param footerclass 页脚样式
     */
    public void setFooterclass(String footerclass) {
        this.footerclass = footerclass;
    }
    /**
     * 获得页脚样式 .
     * @return 页脚样式
     */
    public String getFooterclass() {
        return footerclass;
    }
    /**
     * 设置地理位置加载 .
     * @param ismapgroundlogo 地理位置加载
     */
    public void setIsmapgroundlogo(String ismapgroundlogo) {
        this.ismapgroundlogo = ismapgroundlogo;
    }
    /**
     * 获得地理位置加载 .
     * @return 地理位置加载
     */
    public String getIsmapgroundlogo() {
        return ismapgroundlogo;
    }
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getNavphotos() {
		return navphotos;
	}
	public void setNavphotos(String navphotos) {
		this.navphotos = navphotos;
	}
	public byte[] getHeaderclass_p() {
		return headerclass_p;
	}
	public void setHeaderclass_p(byte[] headerclass_p) {
		this.headerclass_p = headerclass_p;
	}
	public byte[] getHeadbgclass_p() {
		return headbgclass_p;
	}
	public void setHeadbgclass_p(byte[] headbgclass_p) {
		this.headbgclass_p = headbgclass_p;
	}
	public byte[] getCompanylogo_p() {
		return companylogo_p;
	}
	public void setCompanylogo_p(byte[] companylogo_p) {
		this.companylogo_p = companylogo_p;
	}
	public byte[] getNav1focus() {
		return nav1focus;
	}
	public void setNav1focus(byte[] nav1focus) {
		this.nav1focus = nav1focus;
	}
	public byte[] getNav2focus() {
		return nav2focus;
	}
	public void setNav2focus(byte[] nav2focus) {
		this.nav2focus = nav2focus;
	}
	public byte[] getNav3focus() {
		return nav3focus;
	}
	public void setNav3focus(byte[] nav3focus) {
		this.nav3focus = nav3focus;
	}
	public byte[] getNav4focus() {
		return nav4focus;
	}
	public void setNav4focus(byte[] nav4focus) {
		this.nav4focus = nav4focus;
	}
	public byte[] getNav5focus() {
		return nav5focus;
	}
	public void setNav5focus(byte[] nav5focus) {
		this.nav5focus = nav5focus;
	}
	public byte[] getNavphotos_p() {
		return navphotos_p;
	}
	public void setNavphotos_p(byte[] navphotos_p) {
		this.navphotos_p = navphotos_p;
	}
	public byte[] getMi1_p() {
		return mi1_p;
	}
	public void setMi1_p(byte[] mi1_p) {
		this.mi1_p = mi1_p;
	}
	public byte[] getMi2_p() {
		return mi2_p;
	}
	public void setMi2_p(byte[] mi2_p) {
		this.mi2_p = mi2_p;
	}
	public byte[] getMi3_p() {
		return mi3_p;
	}
	public void setMi3_p(byte[] mi3_p) {
		this.mi3_p = mi3_p;
	}
	public byte[] getMi4_p() {
		return mi4_p;
	}
	public void setMi4_p(byte[] mi4_p) {
		this.mi4_p = mi4_p;
	}
	public byte[] getMi5_p() {
		return mi5_p;
	}
	public void setMi5_p(byte[] mi5_p) {
		this.mi5_p = mi5_p;
	}
    
}

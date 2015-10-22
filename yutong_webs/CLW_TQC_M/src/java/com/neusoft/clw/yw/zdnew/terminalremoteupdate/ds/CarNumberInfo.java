/*******************************************************************************
 * @(#)CarNumberInfo.java 2012-6-5
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds;

/**
 * 车辆信息bean
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-6-5 上午10:24:37
 */
public class CarNumberInfo {
    /** 车辆个数 **/
    private String carnum = "";
    /** 企业ID **/
    private String enterpriseId = "";
    /** 数据类型 **/
    private String flag = "";
    
    public String getCarnum() {
        return carnum;
    }
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
    public String getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
}

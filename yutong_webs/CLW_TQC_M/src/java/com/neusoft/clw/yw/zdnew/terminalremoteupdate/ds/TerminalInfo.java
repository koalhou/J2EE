package com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds;

/**
 * 终端实时状态信息
 * @author JinPeng
 */
public class TerminalInfo {
    /** 终端ID **/
    private String terminalId = "";

    /** 终端号 **/
    private String terminalCode = "";

    /** 车牌号 **/
    private String vehicleLn = "";

    /** 车辆VIN号 **/
    private String vehicleVin = "";

    /** SIM卡号 **/
    private String simCardNumber = "";

    /** 手机号 **/
    private String cellPhone = "";
    
    /** 企业ID **/
    private String enterpriseId = "";

    /** 企业编码 **/
    private String enterpriseCode = "";
    
    /** 企业名称 **/
    private String enterpriseName = "";

    /** 车主ID **/
    private String userId = "";

    /** 车主名称 **/
    private String userName = "";

    /** 终端厂家ID **/
    private String oemId = "";

    /** 终端厂家名称 **/
    private String oemName = "";

    /** 终端型号ID **/
    private String typeId = "";

    /** 终端型号名称 **/
    private String typeName = "";

    /** 终端协议ID **/
    private String protocalId = "";

    /** 终端协议名称 **/
    private String protocalName = "";

    /** GPS状态 **/
    private String gpsState = "";

    private String host_hard_ver;
    private String host_firm_ver;
    private String xianshi_hard_ver;
    private String xianshi_firm_ver;
    private String DVR_hard_ver;
    private String DVR_firm_ver;
    private String SHEPIN_hard_ver;
    private String SHEPIN_firm_ver;
    private String SIM_SCCID;
    private String UPDATE_VERSION;
    private String OPERATE_TIME;
    private String DEAL_STATE;
    
    public String getUPDATE_VERSION() {
		return UPDATE_VERSION;
	}

	public void setUPDATE_VERSION(String update_version) {
		UPDATE_VERSION = update_version;
	}

	public String getOPERATE_TIME() {
		return OPERATE_TIME;
	}

	public void setOPERATE_TIME(String operate_time) {
		OPERATE_TIME = operate_time;
	}

	public String getDEAL_STATE() {
		return DEAL_STATE;
	}

	public void setDEAL_STATE(String deal_state) {
		DEAL_STATE = deal_state;
	}

	public String getHost_hard_ver() {
		return host_hard_ver;
	}

	public void setHost_hard_ver(String host_hard_ver) {
		this.host_hard_ver = host_hard_ver;
	}

	public String getHost_firm_ver() {
		return host_firm_ver;
	}

	public void setHost_firm_ver(String host_firm_ver) {
		this.host_firm_ver = host_firm_ver;
	}

	public String getXianshi_hard_ver() {
		return xianshi_hard_ver;
	}

	public void setXianshi_hard_ver(String xianshi_hard_ver) {
		this.xianshi_hard_ver = xianshi_hard_ver;
	}

	public String getXianshi_firm_ver() {
		return xianshi_firm_ver;
	}

	public void setXianshi_firm_ver(String xianshi_firm_ver) {
		this.xianshi_firm_ver = xianshi_firm_ver;
	}

	public String getDVR_hard_ver() {
		return DVR_hard_ver;
	}

	public void setDVR_hard_ver(String dvr_hard_ver) {
		DVR_hard_ver = dvr_hard_ver;
	}

	public String getDVR_firm_ver() {
		return DVR_firm_ver;
	}

	public void setDVR_firm_ver(String dvr_firm_ver) {
		DVR_firm_ver = dvr_firm_ver;
	}

	public String getSHEPIN_hard_ver() {
		return SHEPIN_hard_ver;
	}

	public void setSHEPIN_hard_ver(String shepin_hard_ver) {
		SHEPIN_hard_ver = shepin_hard_ver;
	}

	public String getSHEPIN_firm_ver() {
		return SHEPIN_firm_ver;
	}

	public void setSHEPIN_firm_ver(String shepin_firm_ver) {
		SHEPIN_firm_ver = shepin_firm_ver;
	}

	public String getSIM_SCCID() {
		return SIM_SCCID;
	}

	public void setSIM_SCCID(String sim_sccid) {
		SIM_SCCID = sim_sccid;
	}

	public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getVehicleLn() {
        return vehicleLn;
    }

    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getOemName() {
        return oemName;
    }

    public void setOemName(String oemName) {
        this.oemName = oemName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProtocalId() {
        return protocalId;
    }

    public void setProtocalId(String protocalId) {
        this.protocalId = protocalId;
    }

    public String getProtocalName() {
        return protocalName;
    }

    public void setProtocalName(String protocalName) {
        this.protocalName = protocalName;
    }

    public String getGpsState() {
        return gpsState;
    }

    public void setGpsState(String gpsState) {
        this.gpsState = gpsState;
    }
}

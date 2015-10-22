package com.neusoft.clw.info.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 终端信息
 */
public class TerminalRecord  {
	
	private String id;//非业务主键
	private String terminal_id;  //终端硬件编码
	private String enterprise_id;  //终端所属企业id
	private String enterprise_name;  //终端所属企业名
	private String vehicle_vin;  //车辆VIN号
	private String terminal_oem_id;  //终端所属厂商ID
	private String terminal_oem_name;  //终端所属厂商名
	private String terminal_type_name;  //终端型号
	private String terminal_protocol_name;  //终端协议版本
	private String sim_card_number;  //SIM卡号
	private String communicate_id;  //终端通讯码
	private String video_id;  //终端视频ID
	private String valid_flag;  //终端是否有效标记
	private String vaset_time;  //无效时间
	private String modify_time;  //最后修改时间
	private String cellphone; //手机号码

    public String getCellphone() {
		return cellphone;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}



	public String getTerminal_id() {
		return terminal_id;
	}



	public void setTerminal_id(String terminalId) {
		terminal_id = terminalId;
	}



	public String getEnterprise_id() {
		return enterprise_id;
	}



	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}



	public String getEnterprise_name() {
		return enterprise_name;
	}



	public void setEnterprise_name(String enterpriseName) {
		enterprise_name = enterpriseName;
	}



	public String getVehicle_vin() {
		return vehicle_vin;
	}



	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}



	public String getTerminal_oem_id() {
		return terminal_oem_id;
	}



	public void setTerminal_oem_id(String terminalOemId) {
		terminal_oem_id = terminalOemId;
	}



	public String getTerminal_oem_name() {
		return terminal_oem_name;
	}



	public void setTerminal_oem_name(String terminalOemName) {
		terminal_oem_name = terminalOemName;
	}



	public String getTerminal_type_name() {
		return terminal_type_name;
	}



	public void setTerminal_type_name(String terminalTypeName) {
		terminal_type_name = terminalTypeName;
	}



	public String getTerminal_protocol_name() {
		return terminal_protocol_name;
	}



	public void setTerminal_protocol_name(String terminalProtocolName) {
		terminal_protocol_name = terminalProtocolName;
	}



	public String getSim_card_number() {
		return sim_card_number;
	}



	public void setSim_card_number(String simCardNumber) {
		sim_card_number = simCardNumber;
	}



	public String getCommunicate_id() {
		return communicate_id;
	}



	public void setCommunicate_id(String communicateId) {
		communicate_id = communicateId;
	}



	public String getVideo_id() {
		return video_id;
	}



	public void setVideo_id(String videoId) {
		video_id = videoId;
	}



	public String getValid_flag() {
		return valid_flag;
	}



	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}



	public String getVaset_time() {
		return vaset_time;
	}



	public void setVaset_time(String vasetTime) {
		vaset_time = vasetTime;
	}



	public String getModify_time() {
		return modify_time;
	}



	public void setModify_time(String modifyTime) {
		modify_time = modifyTime;
	}

	@Override
	public String toString() {
		return "TerminalRecord [cellphone=" + cellphone + ", communicate_id="
				+ communicate_id + ", enterprise_id=" + enterprise_id
				+ ", enterprise_name=" + enterprise_name + ", modify_time="
				+ modify_time + ", sim_card_number=" + sim_card_number
				+ ", terminal_id=" + terminal_id + ", terminal_oem_id="
				+ terminal_oem_id + ", terminal_oem_name=" + terminal_oem_name
				+ ", terminal_protocol_name=" + terminal_protocol_name
				+ ", terminal_type_name=" + terminal_type_name
				+ ", valid_flag=" + valid_flag + ", vaset_time=" + vaset_time
				+ ", vehicle_vin=" + vehicle_vin + ", video_id=" + video_id
				+ "]";
	}

	public TerminalRecord() {    	 
		terminal_id =null;
		enterprise_id=null;
		enterprise_name=null;
		vehicle_vin=null;
		terminal_oem_id=null;
		terminal_oem_name=null;
		terminal_type_name=null;
		terminal_protocol_name=null;
		sim_card_number=null;
		communicate_id=null;
		video_id=null;
		valid_flag=null;
		vaset_time=null;
		modify_time=null;
		cellphone=null;
    }

   

    public static void main(String[] args) {
        List<TerminalRecord> list = new ArrayList<TerminalRecord>();
        TerminalRecord record = new TerminalRecord();
      
        list.add(record);

        for (int i = 1000; i > 0; i--) {
            record = new TerminalRecord();
            
            list.add(record);
        }

        System.out.println(new Date() + "排序前" + list.toString());
        
        System.out.println(new Date() + "排序后" + list.toString());
    }
}

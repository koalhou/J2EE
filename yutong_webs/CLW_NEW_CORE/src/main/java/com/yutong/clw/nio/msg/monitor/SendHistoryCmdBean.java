package com.yutong.clw.nio.msg.monitor;

public class SendHistoryCmdBean {

	public static final int len00 = 4;
	public static final int len01 = 32;
	public static final int len02 = 12;
	public static final int len03 = 12;
	public static final int len04 = 2;
	//历史命令查询 命令字 2006
	public String cmd;
	//消息id，采用uuid
	public String msg_id;
	//开始时间
	public String start_time;
	//结束时间
	public String end_time;
	//想要查询的历史数据类型
	public String query_field;
	
	public byte[] bytes;
	
	public String systime;
	
	public String vin;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String startTime) {
		start_time = startTime;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

	public String getQuery_field() {
		return query_field;
	}

	public void setQuery_field(String queryField) {
		query_field = queryField;
	}
}

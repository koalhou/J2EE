package com.yutong.clw.service.buffer.sync;

public class SyncClass {
	private String req;
	private String id;
	private String command;
	private String seq;
	//0:学生 1：企业
	private String flag;
	//协议包长度
	private int totallen;
	
	public int getTotallen() {
		return totallen;
	}
	public void setTotallen(int totallen) {
		this.totallen = totallen;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getReq() {
		return req;
	}
	public void setReq(String req) {
		this.req = req;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}

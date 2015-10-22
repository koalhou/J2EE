package com.yutong.clw.nio.msg.monitor;

public class SendBean {
	byte[] sendData;
	CmdBean cmdbean;
	String source;// 管理平台：amin；转发平台：transmit

	public SendBean(CmdBean cmdbean, byte[] sendData, String source) {
		this.cmdbean = cmdbean;
		this.sendData = sendData;
		this.source = source;
	}

	public byte[] getSendData() {
		return sendData;
	}

	public void setSendData(byte[] sendData) {
		this.sendData = sendData;
	}

	public CmdBean getCmdbean() {
		return cmdbean;
	}

	public void setCmdbean(CmdBean cmdbean) {
		this.cmdbean = cmdbean;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}

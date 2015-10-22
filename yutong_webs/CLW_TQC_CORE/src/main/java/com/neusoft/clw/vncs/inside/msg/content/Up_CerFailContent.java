package com.neusoft.clw.vncs.inside.msg.content;

import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;

public class Up_CerFailContent extends UpLoadDataReq{
	
	public String vin;
	public String sim;
	public String terminal_id;
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminalId) {
		terminal_id = terminalId;
	}
	
}

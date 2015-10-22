package com.yutong.clw.nio.msg.value;

import com.yutong.clw.nio.msg.req.UpLoadDataReq;

public class Up_RegionParamContent extends UpLoadDataReq{
	
	public String msg_id;
	public String deal_state;
	public String state;
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}
	public String getDeal_state() {
		return deal_state;
	}
	public void setDeal_state(String dealState) {
		deal_state = dealState;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

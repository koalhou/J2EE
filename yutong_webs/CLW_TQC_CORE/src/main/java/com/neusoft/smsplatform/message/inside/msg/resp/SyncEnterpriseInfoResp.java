package com.neusoft.smsplatform.message.inside.msg.resp;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;


public class SyncEnterpriseInfoResp extends MesssageInsideMsg {

    public static final String COMMAND = "09";
    
    public static final int RESULTSIZE = 1;   
    public static final int ENTERPRISEIDSIZE = 64;
    public String result;
    public String enterprise_id;
    
	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
    
}

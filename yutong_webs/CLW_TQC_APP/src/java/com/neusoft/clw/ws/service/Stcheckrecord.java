package com.neusoft.clw.ws.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.listener.SpringBootStrap;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.ws.bean.ClwXcStCheckRecordT;

public class Stcheckrecord {
	
    private Service service;
    public Object[] getCheckRecordList(int pageIdx,  int sumNumber, String time){
    	Stcheckrecord scr = (Stcheckrecord) SpringBootStrap.getInstance().getBean("clwXcStCheckRecordT");
    	service = scr.getService();
    	try {
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("rowStart", (pageIdx-1)*sumNumber);
    		map.put("rowEnd", (pageIdx)*sumNumber);
    		map.put("lesstime", time);
    		List<ClwXcStCheckRecordT> list = service.getObjects("stcheckrecord.StCheckRecordpaginate", map);
    		return list.toArray();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
}

package com.neusoft.clw.vncs.dao;

import java.util.List;

import com.neusoft.clw.vncs.domain.TerminalBean;

public interface ITerminalDAO {
	
	List<TerminalBean> getAllBaseTerminalInfo();
	
	List<TerminalBean> getBaseTerminalInfo();
	
	String getSysTime();
	
	TerminalBean getTerminalByid(String vin);
}

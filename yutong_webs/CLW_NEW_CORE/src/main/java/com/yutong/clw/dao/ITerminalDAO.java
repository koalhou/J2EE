package com.yutong.clw.dao;

import java.util.List;

import com.yutong.clw.beans.cl.TerminalBean;

public interface ITerminalDAO {
	
	List<TerminalBean> getAllBaseTerminalInfo();
	
	List<TerminalBean> getBaseTerminalInfo();
	
	String getSysTime();
	
	TerminalBean getTerminalByid(String vin);
}

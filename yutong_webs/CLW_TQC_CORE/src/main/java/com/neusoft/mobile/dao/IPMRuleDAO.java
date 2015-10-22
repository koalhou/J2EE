package com.neusoft.mobile.dao;

import java.util.List;

import com.neusoft.clw.vncs.domain.PMRule;

public interface IPMRuleDAO {
	
	List<PMRule> getAllPMRule();
	List<PMRule> getPartPMRule(String time);
	String getSysTime();
}

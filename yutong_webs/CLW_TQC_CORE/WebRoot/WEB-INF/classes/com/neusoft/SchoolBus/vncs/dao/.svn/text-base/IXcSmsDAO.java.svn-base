package com.neusoft.SchoolBus.vncs.dao;

import java.util.List;

import com.neusoft.SchoolBus.vncs.domain.EnterPriseBean;
import com.neusoft.SchoolBus.vncs.domain.RouteSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;

public interface IXcSmsDAO {
	List<XcStuSmsBean> getStuSMSParam();
	List<XcStuSmsBean> getStuSMSParam(String time,String systime);
	List<EnterPriseBean> getEnterPriseParam();
	List<EnterPriseBean> getEnterPriseParam(String systime);
	String getEnterprise_By_Stu(String stucode) throws Exception;
	List<XcStudentBean> getStudentParam();
	String getSysTime();
	List<XcStudentBean> getAllStudentParam(String time);
	List<XcSiteBean> getSiteParam();
	List<XcSiteBean> getSiteParam(String time);
	List<RouteSiteBean> getRouteSiteParam();
	List<XcStuSmsVTBean> getStuSMSParamVT();
	List<XcvsseBean> getVssParam();
	List<XcStuSmsVTBean> getStuSMSParamVTAdd(String lastSyncDate);
	List<XcStuSmsBean> getStuSMSParamByKey(String stu_id,String event_type);
}

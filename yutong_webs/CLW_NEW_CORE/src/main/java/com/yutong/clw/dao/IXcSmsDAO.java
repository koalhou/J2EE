package com.yutong.clw.dao;

import java.util.List;

import com.yutong.clw.beans.xc.EnterPriseBean;
import com.yutong.clw.beans.xc.RouteSiteBean;
import com.yutong.clw.beans.xc.XcSiteBean;
import com.yutong.clw.beans.xc.XcStuSmsBean;
import com.yutong.clw.beans.xc.XcStuSmsVTBean;
import com.yutong.clw.beans.xc.XcStudentBean;
import com.yutong.clw.beans.xc.XcvsseBean;

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

package com.yutongbei.www.zspt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.TqcAlarm;
import com.neusoft.clw.tree.domain.TreeNodeInfo;
import com.neusoft.clw.yw.ftly.ds.ZsptFtlyInfo;
import com.neusoft.clw.yw.sitemanager.ds.SitSet;
import com.neusoft.clw.yw.sitemanager.ds.SiteAddOilConfig;
import com.neusoft.clw.yw.sitemanager.ds.TerminalViBean;
import com.neusoft.clw.yw.sitemanager.service.StationService;

public class BaseSpringTest {
	protected Logger logger = Logger.getLogger(BaseSpringTest.class);

	ApplicationContext ac;
	Service service;

	@Before
	public void before() {

		ac = new ClassPathXmlApplicationContext(new String[] {
				"conf/spring/applicationContext-datasource.xml",
				"conf/spring/applicationContext-dao.xml",
				"conf/spring/applicationContext-web.xml",
				"conf/spring/applicationContext-webaspect.xml",
				"conf/spring/applicationContext-service.xml" });

		service = (Service) ac.getBean("service");
	}

	@Test
	public void testSpring() throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		StationService serv = (StationService) ac.getBean("sitservice");
		List<SitSet> list = serv.selectStationSet(
				"SitManage.selectStatiionSet", "41");
		String vehicleln = "";
		String vehicleVin = "";
		for (SitSet sitset : list) {
			vehicleln = vehicleln + "," + sitset.getVehicle_ln();
			vehicleVin = vehicleVin + "," + sitset.getVehicle_vin();
		}
		logger.info(vehicleln.replaceFirst(",", ""));
		logger.info(vehicleVin.replaceFirst(",", ""));
	}

	@Test
	public void testShowFtlyBigPicAddStore() throws BusinessException {
		//UserInfo user = getCurrentUser();
			TqcAlarm	tqcalarm = new TqcAlarm();
		tqcalarm.setOrganization_id("7287dce9-46b5-4b7c-af89-973f439ab4ed");
//		tqcalarm.setAlarm_type("5,2");
		tqcalarm.setStart_time(DateUtil.getPreNDay(-7));
		tqcalarm.setOperate_flag("0");
		tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
		List tqcAlarmList = service.getObjectsByPage("AlarmManage.getNofullAlarmInfos", tqcalarm, 0, 3);
		logger.info(tqcAlarmList.size());
	}
	
	@Test
	public void testgetFtlyInfoList() throws BusinessException {
		//UserInfo user = getCurrentUser();
			TqcAlarm	tqcalarm = new TqcAlarm();
		tqcalarm.setOrganization_id("7287dce9-46b5-4b7c-af89-973f439ab4ed");
//		tqcalarm.setAlarm_type("5,2");
//		tqcalarm.setStart_time(DateUtil.getPreNDay(-7));
		tqcalarm.setOperate_flag("0");
//		tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("vehicleVin", StringUtil.toStringList("LZYTBTD6991001781, LGC1GJFN464030004, ZZYT1234567899876, LZYT1115222222111, LZYTCTC24F1011699"));
		map.put("ent_id", "7287dce9-46b5-4b7c-af89-973f439ab4ed");
		//map.put("type", getRequest().getParameter("type"));
		map.put("rowEnd", 12);
		map.put("rowStart", 0);
		List tqcAlarmList = service.getObjects("AlarmManage.getLastFtlyInfoGrouped", map);
		logger.info(tqcAlarmList.size());
	}

	

}

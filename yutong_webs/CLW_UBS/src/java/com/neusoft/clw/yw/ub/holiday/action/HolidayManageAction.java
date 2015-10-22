package com.neusoft.clw.yw.ub.holiday.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.ub.holiday.ds.ConditionParameter;
import com.neusoft.clw.yw.ub.holiday.ds.HolidayWorkdayDataInfo;
import com.neusoft.clw.yw.ub.holiday.ds.StaticsDataInfo;
import com.neusoft.clw.yw.ub.holiday.helper.AccountDateUtil;
import com.neusoft.clw.yw.ub.holiday.service.HolidayManageService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 节假日-工作日管理类
 * 
 * @author ZhangYong
 */
public class HolidayManageAction extends PaginationAction {

	/**
	 * 执行结果提示
	 */
	private String message;
	/**
	 * SqlMap共通处理接口服务
	 */
	private transient Service service;
	/**
	 * 选择年份条件数据列表
	 */

	/**
	 * 管理接口
	 */
	private transient HolidayManageService holidayManageService;

	/**
	 * 选择条件：年份
	 */
	private Map<String, String> yearList = new HashMap();
	// private Map<String, String> yearList = new HashMap() {{
	// put( "2012" , "2012" );
	// put( "2013" , "2013" );
	// }};

	/**
	 * 查询条件对象
	 */
	private ConditionParameter searchparam = new ConditionParameter();
	/**
	 * 返回数据列表
	 */
	private List<StaticsDataInfo> staticsList = new ArrayList<StaticsDataInfo>();

	private String groupname = "";
	/**
	 * 选中数据对象
	 */
	private StaticsDataInfo selectObject = new StaticsDataInfo();

	/**
	 * 数据对象
	 */
	private StaticsDataInfo staitcsObject = new StaticsDataInfo();

	/* 节假日管理相关方法 */
	/**
	 * 节假日初始化
	 * 
	 * @return
	 */
	public String init() {
		ActionContext
				.getContext()
				.getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.holiday.location"));

		searchparam.setDay_flag("0");
		searchYearList(searchparam.getDay_flag());

		try {
			Map<String, String> map = new HashMap<String, String>();

			searchparam.setDay_flag(searchparam.getDay_flag());
			searchparam.setYear_select(searchparam.getYear_select());
			map.put("day_flag",
					SearchUtil.formatSpecialChar(searchparam.getDay_flag()));
			map.put("year_select",
					SearchUtil.formatSpecialChar(searchparam.getYear_select()));

			int totalCount = 0;
			totalCount = service.getCount("HolidayMaintain.getCount", map);
			Page pageObj = new Page(page, totalCount, pageSize, url, param);
			this.pageBar = PageHelper.getPageBar(pageObj);

			staticsList = (List<StaticsDataInfo>) service.getObjectsByPage(
					"HolidayMaintain.getStaticsInfos", map,
					pageObj.getStartOfPage(), pageSize);

			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query holiworkday infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query holiworkday infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询节假日信息");
		}

		return SUCCESS;
	}

	public String gotoadd() {

		if (null != message) {
			addActionError(getText(message));
		}

		staitcsObject.setDayflag("0");
		return SUCCESS;
	}

	public String doadd() {
		return addInfo();
	}

	public String gotoedit() {
		return getStaticsInfo();
	}

	public String doedit() {

		return updateInfo();
	}

	public String dodel() {
		return updateDelInfo();
	}

	/* 工作日管理相关方法 */
	/**
	 * 页面初始化
	 * 
	 * @return
	 */
	public String init_workday() {
		ActionContext
				.getContext()
				.getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.holiday.location"));
		searchparam.setDay_flag("1");
		searchYearList(searchparam.getDay_flag());

		try {
			Map<String, String> map = new HashMap<String, String>();

			searchparam.setDay_flag(searchparam.getDay_flag());
			searchparam.setYear_select(searchparam.getYear_select());
			map.put("day_flag",
					SearchUtil.formatSpecialChar(searchparam.getDay_flag()));
			map.put("year_select",
					SearchUtil.formatSpecialChar(searchparam.getYear_select()));

			int totalCount = 0;
			totalCount = service.getCount("HolidayMaintain.getCount", map);
			Page pageObj = new Page(page, totalCount, pageSize, url, param);
			this.pageBar = PageHelper.getPageBar(pageObj);

			staticsList = (List<StaticsDataInfo>) service.getObjectsByPage(
					"HolidayMaintain.getStaticsInfos", map,
					pageObj.getStartOfPage(), pageSize);

			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query holiworkday infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query holiworkday infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询节假日信息");
		}

		return SUCCESS;

	}

	public String gotoadd_workday() {
		if (null != message) {
			addActionError(getText(message));
		}
		staitcsObject.setDayflag("1");
		return SUCCESS;
	}

	public String doadd_workday() {
		return addInfo();
	}

	public String gotoedit_workday() {

		return getStaticsInfo();
	}

	public String doedit_workday() {

		return updateInfo();
	}

	public String dodel_workday() {
		return updateDelInfo();
	}

	/* 私有方法 */

	private String getStaticsInfo() {
		if (null != message) {
			addActionError(getText(message));
		}

		if (groupname == "" || groupname == null) {
			return ERROR;
		} else {
			try {
				Map<String, String> map = new HashMap<String, String>();

				map.put("groupname", SearchUtil.formatSpecialChar(groupname));

				staitcsObject = (StaticsDataInfo) service.getObject(
						"HolidayMaintain.getStaticsInfo", map);

			} catch (BusinessException e) {
				setMessage("info.db.error");
				log.error("Query holiday detail error:" + e.getMessage());
				return ERROR;
			} catch (Exception e) {
				setMessage("info.db.error");
				log.error("Query holiday detail error:" + e.getMessage());
				return ERROR;
			}
		}
		return SUCCESS;
	}

	// 获取查询条件的年列表
	private void searchYearList(String day_flag) {
		yearList.clear();
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("day_flag", SearchUtil.formatSpecialChar(day_flag));

			List list = service.getObjects("HolidayMaintain.getYearList", map);

			for (int i = 0; i < list.size(); i++) {
				Map year_selectmap = (Map) list.get(i);
				String year_select = (String) year_selectmap.get("YEAR_SELECT");

				yearList.put(year_select, year_select);

				if (i == 0) {
					if (searchparam.getYear_select() == null
							|| "".equals(searchparam.getYear_select())) {
						searchparam.setYear_select(year_select);
					}
				}
			}

		} catch (BusinessException e) {
			log.error("Query yearlist list error:" + e.getMessage());
			yearList.clear();
		} catch (Exception e) {
			log.error("Query year yearlist error:" + e.getMessage());
			yearList.clear();
		}
	}

	/**
	 * 转换前台中文编码
	 * 
	 * @author zhangzhia 2013-7-9 上午9:59:23
	 * 
	 * @return
	 */
	private String encoding(String str) {
		try {

			if (str != null && !"".equals(str)) {
				str = new String(str.getBytes("iso-8859-1"), "UTF-8");
				return str;
			} else {
				return str;
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return str;
		}// 解决URL传递过来的中文参数值的乱码问题
	}

	/**
	 * 修改节假日工作日信息
	 * 
	 * @author zhangzhia 2013-7-9 下午2:15:10
	 * 
	 * @return
	 */
	private String updateInfo() {
		try {

			Date str1date = DateUtil.parseStringToDate(
					staitcsObject.getStartDate(), "yyyy-MM-dd");
			Date str2date = DateUtil.parseStringToDate(
					staitcsObject.getEndDate(), "yyyy-MM-dd");

			if (str2date.before(str1date)) {
				setMessage("ub.holiday.dateerror");
				return ERROR;
			}

			List<String> list = AccountDateUtil.getEveryday(
					staitcsObject.getStartDate(), staitcsObject.getEndDate());

			if (staitcsObject.getDayflag().equals("1")) {
				if (!checkWeek(list)) {
					setMessage("ub.workday.checkinfo");
					return ERROR;
				}
			}

			List<HolidayWorkdayDataInfo> dataInfos = parseDataInfoList(list);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("holiworkdays", list);
			map.put("groupname", groupname);

			int infoNum = 0;
			infoNum = service
					.getCount("HolidayMaintain.verifyDateIsExist", map);

			if (infoNum > 0) {
				setMessage("ub.holiday.exist_data");
				return ERROR;
			}

			holidayManageService.updateInfo(groupname, dataInfos);

			setMessage("ub.holiday.success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMessage("info.db.error");
			log.error("Update holiworkdays infos error:" + e.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 更新删除数据 更新删除状态字段值为1
	 * 
	 * @author zhangzhia 2013-7-9 上午10:13:03
	 * 
	 * @return
	 */
	private String updateDelInfo() {
		if (groupname == "" || groupname == null) {
			setMessage("ub.holiday.groupEmpty");
			return ERROR;
		} else {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("groupname", SearchUtil.formatSpecialChar(groupname));

				int result = 0;
				result = service.update("HolidayMaintain.updateDelStatus", map);

			} catch (BusinessException e) {
				setMessage("info.db.error");
				log.error("Query holiday detail error:" + e.getMessage());
				return ERROR;
			} catch (Exception e) {
				setMessage("info.db.error");
				log.error("Query holiday detail error:" + e.getMessage());
				return ERROR;
			}
		}
		return ERROR;
	}

	/**
	 * 新增节假日工作日信息
	 * 
	 * @author zhangzhia 2013-7-9 下午2:15:10
	 * 
	 * @return
	 */
	private String addInfo() {
		try {
			Date str1date = DateUtil.parseStringToDate(
					staitcsObject.getStartDate(), "yyyy-MM-dd");
			Date str2date = DateUtil.parseStringToDate(
					staitcsObject.getEndDate(), "yyyy-MM-dd");

			if (str2date.before(str1date)) {
				setMessage("ub.holiday.dateerror");
				return ERROR;
			}

			List<String> list = AccountDateUtil.getEveryday(
					staitcsObject.getStartDate(), staitcsObject.getEndDate());

			if (staitcsObject.getDayflag().equals("1")) {
				if (!checkWeek(list)) {
					setMessage("ub.workday.checkinfo");
					return ERROR;
				}
			}

			List<HolidayWorkdayDataInfo> dataInfos = parseDataInfoList(list);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("holiworkdays", list);
			int infoNum = 0;
			infoNum = service
					.getCount("HolidayMaintain.verifyDateIsExist", map);

			if (infoNum > 0) {
				setMessage("ub.holiday.exist_data");
				return ERROR;
			}

			holidayManageService.insertInfo(dataInfos);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			setMessage("info.db.error");
			log.error("Add holiworkdays infos error:" + e.getMessage());
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * @author zhangzhia 2013-7-10 下午1:38:34
	 * 
	 * @param list
	 */
	private List<HolidayWorkdayDataInfo> parseDataInfoList(List<String> list) {
		UserInfo currentUser = (UserInfo) ActionContext.getContext()
				.getSession().get(Constants.USER_SESSION_KEY);
		List<HolidayWorkdayDataInfo> dataInfos = new ArrayList<HolidayWorkdayDataInfo>();

		String curTime = DateUtil.formatDateToString(new Date(),
				"yyyy-MM-dd HH:mm:ss");
		String groupname = UUIDGenerator.getUUID();
		for (String datestr : list) {
			HolidayWorkdayDataInfo dataInfo = new HolidayWorkdayDataInfo();
			dataInfo.setDay_id(UUIDGenerator.getUUID());
			dataInfo.setYear_select(datestr);
			dataInfo.setDay_name(staitcsObject.getDay_name());
			dataInfo.setDayflag(staitcsObject.getDayflag());
			dataInfo.setDeleteflag("0");
			dataInfo.setHoliworkday(datestr);
			dataInfo.setOperator(currentUser.getUserID());
			dataInfo.setOperate_time(curTime);
			dataInfo.setGroupname(groupname);

			dataInfos.add(dataInfo);
		}

		return dataInfos;
	}

	/**
	 * 判断时段是否只为周六和周日
	 *@author zhangzhia 2013-7-30 下午7:11:26
	 *
	 * @param dateList
	 * @return
	 */
	private Boolean checkWeek(List<String> dateList) {
		Boolean pass = true;
		for (String datestr : dateList) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parseStringToDate(datestr, "yyyy-MM-dd"));
			int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;

			if (!(week == 6 || week == 0)) {
				pass = false;
				break;
			}
		}
		return pass;
	}

	/* 公开属性 */

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(Service service) {

		this.service = service;
	}

	/**
	 * @return the yearList
	 */
	public Map<String, String> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList
	 *            the yearList to set
	 */
	public void setYearList(Map<String, String> yearList) {
		this.yearList = yearList;
	}

	/**
	 * @return the searchparam
	 */
	public ConditionParameter getsearchparam() {
		return searchparam;
	}

	/**
	 * @param searchparam
	 *            the searchparam to set
	 */
	public void setsearchparam(ConditionParameter searchparam) {
		this.searchparam = searchparam;
	}

	/**
	 * @return the staticsList
	 */
	public List<StaticsDataInfo> getStaticsList() {
		return staticsList;
	}

	/**
	 * @param staticsList
	 *            the staticsList to set
	 */
	public void setStaticsList(List<StaticsDataInfo> staticsList) {
		this.staticsList = staticsList;
	}

	/**
	 * @return the staitcsObject
	 */
	public StaticsDataInfo getstaitcsObject() {
		return staitcsObject;
	}

	/**
	 * @param staitcsObject
	 *            the staitcsObject to set
	 */
	public void setstaitcsObject(StaticsDataInfo staitcsObject) {
		this.staitcsObject = staitcsObject;
	}

	/**
	 * @return the selectObject
	 */
	public StaticsDataInfo getSelectObject() {
		return selectObject;
	}

	/**
	 * @param selectObject
	 *            the selectObject to set
	 */
	public void setSelectObject(StaticsDataInfo selectObject) {
		this.selectObject = selectObject;
	}

	/**
	 * @return the holidayManageService
	 */
	public HolidayManageService getHolidayManageService() {
		return holidayManageService;
	}

	/**
	 * @param holidayManageService
	 *            the holidayManageService to set
	 */
	public void setHolidayManageService(
			HolidayManageService holidayManageService) {
		this.holidayManageService = holidayManageService;
	}

	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}

	/**
	 * @param groupname
	 *            the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}

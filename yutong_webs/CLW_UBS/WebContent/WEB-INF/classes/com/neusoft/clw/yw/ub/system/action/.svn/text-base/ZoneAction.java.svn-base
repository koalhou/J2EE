package com.neusoft.clw.yw.ub.system.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.system.ds.ConditionParameter;
import com.neusoft.clw.yw.ub.system.ds.VisitStaticsDataInfo;
import com.neusoft.clw.yw.ub.system.helper.Helper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 
 * @author ZhangYong
 */
public class ZoneAction extends PaginationAction {

	private String message;

	private transient Service service;

	/**
	 * 查询条件对象
	 */
	private ConditionParameter searchparam = new ConditionParameter();

	/** JSON 返回 Zone 大区统计信息MAP **/
	private Map dataAreaMap = new HashMap();

	/** JSON 返回 Zone 省份统计信息MAP **/
	private Map dataProvinceMap = new HashMap();

	/**
	 * 页面初始化
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.system.location"));

		//getAreaList();
		//getProvinceList();
		return SUCCESS;
	}

	/**
	 * 获取大区图表数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	public String getAreaList() {
		HttpServletRequest request = ServletActionContext.getRequest();

		List<VisitStaticsDataInfo> staticsList = new ArrayList<VisitStaticsDataInfo>();

		if (null != message) {
			addActionMessage(getText(message));
		}

		searchparam.setOrder("visitCount");
		//searchparam.setStartDate("2013-01-01");
		//searchparam.setEndDate("2013-12-01");

		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startDate", searchparam.getStartDate());
			map.put("endDate", searchparam.getEndDate());
			map.put("order", searchparam.getOrder());
			map.put("direction", searchparam.getDirection());

			staticsList = (List<VisitStaticsDataInfo>) service.getObjects(
					"SystemEnvironment.getAreaStatisticsList", map);

			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

			this.dataAreaMap = Helper.getZoneJson(staticsList);
			
		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query ZoneAction infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query ZoneAction infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询Zone大区图表统计信息");
		}

		return SUCCESS;
	}

	/**
	 * 获取省份图表数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getProvinceList() {
		HttpServletRequest request = ServletActionContext.getRequest();

		List<VisitStaticsDataInfo> staticsList = new ArrayList<VisitStaticsDataInfo>();

		if (null != message) {
			addActionMessage(getText(message));
		}

		searchparam.setOrder("visitCount");
		//searchparam.setStartDate("2013-01-01");
		//searchparam.setEndDate("2013-12-01");

		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startDate", searchparam.getStartDate());
			map.put("endDate", searchparam.getEndDate());
			map.put("order", searchparam.getOrder());
			map.put("direction", searchparam.getDirection());

			staticsList = (List<VisitStaticsDataInfo>) service.getObjects(
					"SystemEnvironment.getProvinceStatisticsList", map);

			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

			this.dataProvinceMap = Helper.getZoneJson(staticsList);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query ZoneAction infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query ZoneAction infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询Zone省份图表统计信息");
		}

		return SUCCESS;
	}

	/**
	 * @return Returns the service.
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service
	 *            The service to set.
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @return the searchparam
	 */
	public ConditionParameter getSearchparam() {
		return searchparam;
	}

	/**
	 * @param searchparam
	 *            the searchparam to set
	 */
	public void setSearchparam(ConditionParameter searchparam) {
		this.searchparam = searchparam;
	}

	/**
	 * @return the dataAreaMap
	 */
	public Map getDataAreaMap() {
		return dataAreaMap;
	}

	/**
	 * @param dataAreaMap
	 *            the dataAreaMap to set
	 */
	public void setDataAreaMap(Map dataAreaMap) {
		this.dataAreaMap = dataAreaMap;
	}

	/**
	 * @return the dataProvinceMap
	 */
	public Map getDataProvinceMap() {
		return dataProvinceMap;
	}

	/**
	 * @param dataProvinceMap
	 *            the dataProvinceMap to set
	 */
	public void setDataProvinceMap(Map dataProvinceMap) {
		this.dataProvinceMap = dataProvinceMap;
	}

}

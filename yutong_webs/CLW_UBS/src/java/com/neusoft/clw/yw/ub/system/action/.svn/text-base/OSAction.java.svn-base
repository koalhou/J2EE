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
import com.neusoft.clw.yw.ub.system.ds.StaticsDataInfo;
import com.neusoft.clw.yw.ub.system.helper.Helper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 
 * @author ZhangYong
 */
public class OSAction extends PaginationAction {
	private String message;

	private transient Service service;

	/**
	 * 查询条件对象
	 */
	private ConditionParameter searchparam = new ConditionParameter();

	/** JSON 返回 操作系统统计信息MAP **/
	private Map dataMap = new HashMap();

	
	/** JSON 返回 操作系统统计分页信息MAP **/
	private Map dataPageMap = new HashMap();

	/**
	 * 页面初始化
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.system.location"));
		return SUCCESS;
	}

	/**
	 * 获取图表显示数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	public String getCharsList() {
		
		if (null != message) {
			addActionMessage(getText(message));
		}
		
		List<StaticsDataInfo> staticsList = new ArrayList<StaticsDataInfo>();

		searchparam.setSystemType("1");
		searchparam.setIs_Holiday("0");
		//searchparam.setStartDate("2013-01-01");
		//searchparam.setEndDate("2013-12-01");

		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("p_systemType", searchparam.getSystemType());
			map.put("p_is_Holiday", searchparam.getIs_Holiday());
			map.put("p_startDate", searchparam.getStartDate());
			map.put("p_endDate", searchparam.getEndDate());

			service.getObjects("SystemEnvironment.getStatisticsList", map);

			staticsList = (List<StaticsDataInfo>) map
					.get("p_statisInfo_Cursor");

			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

			this.dataMap = Helper.getPagination(staticsList);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query OSAction infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query OSAction infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询操作系统统计信息");
		}

		return SUCCESS;
	}
	/**
	 * 获取分页数据
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getPageList() {
		HttpServletRequest request = ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}
		List<StaticsDataInfo> staticsList = new ArrayList<StaticsDataInfo>();

		searchparam.setSystemType("1");
		searchparam.setIs_Holiday("0");
		//searchparam.setStartDate("2013-01-01");
		//searchparam.setEndDate("2013-12-01");

		try {

			int pageIndex = Integer.parseInt(request.getParameter("page"));
			int rpNum = Integer.parseInt(request.getParameter("rp"));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("p_systemType", searchparam.getSystemType());
			map.put("p_is_Holiday", searchparam.getIs_Holiday());
			map.put("p_startDate", searchparam.getStartDate());
			map.put("p_endDate", searchparam.getEndDate());

			map.put("p_pageSize", rpNum);
			map.put("p_curPage", pageIndex);

			service.getObjects("SystemEnvironment.getStatisticsPageList", map);

			int totalCount = 0;
			totalCount = (Integer) map.get("p_totalCount");

			staticsList = (List<StaticsDataInfo>) map
					.get("p_statisInfo_Cursor");

			this.dataPageMap = Helper.getPaginationPage(staticsList,
					totalCount, page, pageSize);
			if (staticsList == null || staticsList.size() == 0) {
				// 无数据信息
				setMessage("common.no.data");
				return ERROR;
			}

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query OSAction infos error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query OSAction infos error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询操作系统统计信息");
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
	 * @return the dataPageMap
	 */
	public Map getDataPageMap() {
		return dataPageMap;
	}

	/**
	 * @param dataPageMap
	 *            the dataPageMap to set
	 */
	public void setDataPageMap(Map dataPageMap) {
		this.dataPageMap = dataPageMap;
	}

    public Map getDataMap()
    {
        return dataMap;
    }

    public void setDataMap(Map dataMap)
    {
        this.dataMap = dataMap;
    }
	
}

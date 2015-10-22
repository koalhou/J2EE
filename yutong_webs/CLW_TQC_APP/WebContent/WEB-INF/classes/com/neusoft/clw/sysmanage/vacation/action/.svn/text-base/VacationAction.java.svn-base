package com.neusoft.clw.sysmanage.vacation.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.vacation.domain.Vacation;
import com.neusoft.clw.sysmanage.vacation.service.VacationService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 假期设置： 设置每月的假期
 * 
 * @author wubind
 * 
 */
public class VacationAction extends PaginationAction {
	protected Logger logger = Logger.getLogger(VacationAction.class);
	

	/**
	 * 假期列表
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public String list() throws BusinessException {
		int page = Integer.parseInt(getRequest().getParameter("page"));
		int rp = Integer.parseInt(getRequest().getParameter("rp"));

		this.orgID = getRequest().getParameter("orgID");
		if (!StringUtils.hasText(orgID)) {
			orgID = getCurrentUser().getOrganizationID();
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("orgID", this.orgID);
		paraMap.put("sortname", getRequest().getParameter("sortname"));
		paraMap.put("sortorder", getRequest().getParameter("sortorder"));
		paraMap.put("year", this.year);
		paraMap.put("month", this.month);
		paraMap.put("rowEnd", page * rp);
		paraMap.put("rowStart", (page - 1) * rp);

		int total = service.getCount("vacation.count", paraMap);
		List<Vacation> list = service.getObjects("vacation.list", paraMap);

		this.map = getPagination(list, total, page, rp);

		return "success";
	}

	private Map<String, Object> getPagination(List<Vacation> list,
			int totalCount, int pageIndex, int rpNum) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapData = new LinkedHashMap<String, Object>();
		for (int i = 0; i < list.size(); i++) {
			Vacation s = list.get(i);
			Map<String, Object> cellMap = new LinkedHashMap<String, Object>();
			cellMap.put("id", s.getID());
			cellMap.put(
					"cell",
					new Object[] {
							Integer.valueOf(i + 1 + (pageIndex - 1) * rpNum),
							s.getOrgName(), s.getYear(), s.getMonth(),
							s.getCollection(), s.getOrgID() });
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);
		mapData.put("total", Integer.valueOf(totalCount));
		mapData.put("rows", mapList);

		return mapData;
	}

	public String one() throws BusinessException {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("orgID", this.orgID);
		paraMap.put("year", this.year);
		paraMap.put("month", this.month);
		paraMap.put("rowEnd", 10);
		paraMap.put("rowStart", 0);
		if(!"add".equals(this.action)){
			List<Vacation> list = service.getObjects("vacation.list", paraMap);
			if (list != null & list.size() > 0) {
				this.collection = list.get(0).getCollection();
				this.orgName = list.get(0).getOrgName();
			}
		}

		return "success";
	}
	
	public String getDays() throws BusinessException {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("orgID", this.orgID);
		paraMap.put("year", this.year);
		paraMap.put("month", this.month);
		int  total = service.getCount("vacation.count", paraMap);

		this.result.put("returns", total);

		return "success";
	}

	public String add() throws BusinessException {
		String orgsl = getRequest().getParameter("orgs");
		String[] days = this.collection.split(",");
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("orgID", this.orgID);
		paraMap.put("year", this.year);
		paraMap.put("month", this.month);
		int  total = service.getCount("vacation.count", paraMap);
		if("add".equals(action)&&total>0){
			this.result.put("returns", "error");
			this.result.put("msg", total);
		}else{
			try {
				vacationService.saveVacation(orgsl, days, year, month, getCurrentUser().getUserID());
				this.result.put("returns", "success");
			} catch (DataAccessIntegrityViolationException e) {
				this.result.put("returns", "error");
				logger.error("", e);
			} catch (DataAccessException e) {
				this.result.put("returns", "error");
				logger.error("", e);
			}
		}
		return "success";
	}

	public String del() throws BusinessException {

		Vacation va = new Vacation();
		va.setOrgID(this.orgID);
		va.setYear(this.year);
		va.setMonth(this.month);
		va.setUpdatedBy(getCurrentUser().getUserID());
		int ret = service.update("vacation.del", va);
		if(ret>0){
			this.result.put("returns", "success");
		}else{
			this.result.put("returns", "fail");
		}
		return "success";
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession()
				.get(Constants.USER_SESSION_KEY);
	}

	private transient Service service;
	private VacationService vacationService;
	private int year;
	private String month;
	private String orgID;
	private String orgName;
	private String collection;
	private String action;
	private Map result = new HashMap();
	private Map<String, Object> map;

	public void setService(Service service) {
		this.service = service;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getCollection() {
		return collection;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public void setVacationService(VacationService vacationService) {
		this.vacationService = vacationService;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

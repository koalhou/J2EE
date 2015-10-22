package com.neusoft.clw.yw.ub.login.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.ub.login.ds.ConditionParameter;
import com.neusoft.clw.yw.ub.login.ds.LoginDetailDataInfo;
import com.neusoft.clw.yw.ub.login.ds.LoginStaticsDataInfo;
import com.neusoft.clw.yw.ub.login.helper.ExcelHelper;
import com.neusoft.clw.yw.ub.login.helper.Helper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 登陆次数管理类
 * 
 * @author ZhangYong
 */
public class LoginCountStatAction extends PaginationAction {

	private String message;

	private transient Service service;

	/**
	 * 查询条件对象
	 */
	private ConditionParameter searchparam = new ConditionParameter();

	/** 客户类别月度客户活跃报表 信息 **/
	private List<LoginStaticsDataInfo> staticsList = new ArrayList<LoginStaticsDataInfo>();
	/** 类型A区域月度客户活跃报表 信息 **/
	private List<LoginStaticsDataInfo> staticsA_AreaList = new ArrayList<LoginStaticsDataInfo>();
	/** 类型B区域月度客户活跃报表 信息 **/
	private List<LoginStaticsDataInfo> staticsB_AreaList = new ArrayList<LoginStaticsDataInfo>();
	/** 类型C区域月度客户活跃报表 信息 **/
	private List<LoginStaticsDataInfo> staticsC_AreaList = new ArrayList<LoginStaticsDataInfo>();

	/** JSON 返回 类别月度客户活跃报表 MAP **/
	private Map staticsMap = new HashMap();

	/**
	 * 页面初始化
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String init() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		//searchparam.setStatics_platform("web");
		

		getList();
		getA_AreaList();
		getB_AreaList();
		getC_AreaList();

		return SUCCESS;
	}
	   @SuppressWarnings("unchecked")
	    public String query() {
	        ActionContext.getContext().getSession()
	                .put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
	        
	        
	        //Date dt = new Date();

	        getList();
	        getA_AreaList();
	        getB_AreaList();
	        getC_AreaList();

	        return SUCCESS;
	    }
	

	/**
	 * 首页显示所有登录次数统计map
	 * 
	 * @author zhangzhia 2013-7-29 下午4:52:37
	 * 
	 * @return
	 */
	public String getMainlogincntList() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}

		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statis_month", searchparam.getStatis_month());

			String platform = searchparam.getStatics_platform();

			List<LoginStaticsDataInfo> list = (List<LoginStaticsDataInfo>) service
					.getObjects("LoginActivity.getWebLoginStatics", map);

			Double allActivePercent = null;
			Integer allEpCount = null;
			Integer allActiveCount = null;

			if (list == null || list.size() == 0) {
				LoginStaticsDataInfo logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("1");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				list.add(logindata);
				
				logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("2");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				list.add(logindata);
				
				logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("3");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				list.add(logindata);
			}
			
			for (int i = 0; i < list.size(); i++) {

				LoginStaticsDataInfo data = list.get(i);

				data.setSystemname(data.getSystemname() + '类');
				data.setActivePercentStr(Helper.getPercent(data
						.getActivePercent()));

				if (data.getEpCount() != null) {

					if (allEpCount == null) {
						allEpCount = new Integer(0);
					}
					allEpCount += data.getEpCount();
				}

				if (data.getActiveCount() != null) {
					if (allActiveCount == null) {
						allActiveCount = new Integer(0);
					}
					allActiveCount += data.getActiveCount();
				}
			}

			if (allEpCount != 0) {
				if (allActivePercent == null) {
					allActivePercent = new Double(0d);
				}
				allActivePercent = (double) allActiveCount
						/ (double) allEpCount;
			}
			LoginStaticsDataInfo cell = new LoginStaticsDataInfo();
			cell.setSystemname("合计");
			cell.setEpCount(allEpCount);
			cell.setActiveCount(allActiveCount);
			cell.setActivePercent(allActivePercent);
			cell.setActivePercentStr(Helper.getPercent(allActivePercent));

			list.add(cell);

			this.staticsMap = Helper.getJson(list, true);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction main infos error:"
					+ e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction main infos error:"
					+ e.getMessage());
			return ERROR;
		} finally {
			addOperationLog("查询客户类别月客户活跃报表");
		}

		return SUCCESS;
	}

	/**
	 * 获取客户类别月度All客户活跃报表数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getList() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}

		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statis_month", searchparam.getStatis_month());

			String platform = searchparam.getStatics_platform();
			if (platform.equals("web")) {
				staticsList = (List<LoginStaticsDataInfo>) service.getObjects(
						"LoginActivity.getWebLoginStatics", map);
			} else if (platform.equals("app")) {
				staticsList = (List<LoginStaticsDataInfo>) service.getObjects(
						"LoginActivity.getAppLoginStatics", map);
			} else {
				staticsList = (List<LoginStaticsDataInfo>) service.getObjects(
						"LoginActivity.getAllLoginStatics", map);
			}

			if (staticsList == null || staticsList.size() == 0) {
				LoginStaticsDataInfo logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("A");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				staticsList.add(logindata);
				
				logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("B");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				staticsList.add(logindata);
				
				logindata = new LoginStaticsDataInfo();
				logindata.setSystemname("C");
				logindata.setEpCount(0);
				logindata.setActiveCount(0);
				logindata.setActivePercent(0.0);
				logindata.setActivePercentStr("-");
				staticsList.add(logindata);
			}
			
			Double allActivePercent = null;
			Integer allEpCount = null;
			Integer allActiveCount = null;

			for (int i = 0; i < staticsList.size(); i++) {

				LoginStaticsDataInfo data = staticsList.get(i);
				data.setSystemname(data.getSystemname() + '类');
				data.setActivePercentStr(Helper.getPercent(data
						.getActivePercent()));

				if (data.getEpCount() != null) {

					if (allEpCount == null) {
						allEpCount = new Integer(0);
					}
					allEpCount += data.getEpCount();
				}

				if (data.getActiveCount() != null) {
					if (allActiveCount == null) {
						allActiveCount = new Integer(0);
					}
					allActiveCount += data.getActiveCount();
				}
			}

			if (allEpCount != 0) {
				if (allActivePercent == null) {
					allActivePercent = new Double(0d);
				}
				allActivePercent = (double) allActiveCount
						/ (double) allEpCount;
			}

			LoginStaticsDataInfo cell = new LoginStaticsDataInfo();
			cell.setSystemname("合计");
			cell.setEpCount(allEpCount);
			cell.setActiveCount(allActiveCount);
			cell.setActivePercent(allActivePercent);
			cell.setActivePercentStr(Helper.getPercent(allActivePercent));

			staticsList.add(cell);

			// this.dataAllMap = Helper.getJson(staticsAllList, true);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询客户类别月度客户活跃报表");
		}

		return SUCCESS;
	}

	/**
	 * 获取客户类别A月度客户活跃报表数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getA_AreaList() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}

		try {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("customer_type", "1");
			map.put("statis_month", searchparam.getStatis_month());

			this.staticsA_AreaList = (List<LoginStaticsDataInfo>) service
					.getObjects("LoginActivity.getAreaLoginStatics", map);

			for (int i = 0; i < staticsA_AreaList.size(); i++) {

				LoginStaticsDataInfo data = staticsA_AreaList.get(i);

				data.setActivePercentStr(Helper.getPercent(data
						.getActivePercent()));
			}
			// this.dataA_AreaMap = Helper.getJson(staticsA_AreaList, true);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询类型区域月度客户活跃报表");
		}

		return SUCCESS;
	}

	/**
	 * 获取客户类别B月度客户活跃报表数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getB_AreaList() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}

		try {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("customer_type", "2");
			map.put("statis_month", searchparam.getStatis_month());

			this.staticsB_AreaList = (List<LoginStaticsDataInfo>) service
					.getObjects("LoginActivity.getAreaLoginStatics", map);

			for (int i = 0; i < staticsB_AreaList.size(); i++) {

				LoginStaticsDataInfo data = staticsB_AreaList.get(i);

				data.setActivePercentStr(Helper.getPercent(data
						.getActivePercent()));
			}

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询类型区域月度客户活跃报表");
		}

		return SUCCESS;
	}

	/**
	 * 获取客户类别C月度客户活跃报表数据
	 * 
	 * @author zhangzhia 2013-7-15 上午8:48:11
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String getC_AreaList() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, getText("ub.login.location"));
		ServletActionContext.getRequest();

		if (null != message) {
			addActionMessage(getText(message));
		}

		try {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("customer_type", "3");
			map.put("statis_month", searchparam.getStatis_month());

			this.staticsC_AreaList = (List<LoginStaticsDataInfo>) service
					.getObjects("LoginActivity.getAreaLoginStatics", map);

			for (int i = 0; i < staticsC_AreaList.size(); i++) {

				LoginStaticsDataInfo data = staticsC_AreaList.get(i);

				data.setActivePercentStr(Helper.getPercent(data
						.getActivePercent()));
			}

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Query LoginCountStatAction infos error:"
					+ e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_CS_SIM_QUERY_MID);
			addOperationLog("查询类型区域月度客户活跃报表");
		}

		return SUCCESS;
	}

	/**
	 * 导出统计
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportStat() {

		Map<String, Object> mapExportList = new HashMap<String, Object>();
		List<LoginDetailDataInfo> list = new ArrayList<LoginDetailDataInfo>();
		try {

			getList();
			getA_AreaList();
			getB_AreaList();
			getC_AreaList();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statis_month", searchparam.getStatis_month());

			list = (List<LoginDetailDataInfo>) service.getObjects(
					"LoginActivity.getWebLoginDetails", map);

			mapExportList.put("AllList", staticsList);
			mapExportList.put("A_AreaList", staticsA_AreaList);
			mapExportList.put("B_AreaList", staticsB_AreaList);
			mapExportList.put("C_AreaList", staticsC_AreaList);
			mapExportList.put("DetailList", list);

		} catch (BusinessException e) {
			setMessage("info.db.error");
			log.error("Export LoginCountStat error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			log.error("Export LoginCountStat error:" + e.getMessage());
			return ERROR;
		}

		String filePath = "";
		OutputStream outputStream = null;
		try {
			// String realPath =
			// ServletActionContext.getServletContext().getRealPath("/");
			// realPath = realPath.replace("\\", "/");
			// realPath = realPath + "exportfile/";

			filePath = "/tmp/" + UUIDGenerator.getUUID() + "loginCountStat.xls";

			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			outputStream = new FileOutputStream(filePath);

			ExcelHelper.setStatis_month(searchparam.getStatis_month());
			ExcelHelper.exportExcel(mapExportList, file);

		} catch (FileNotFoundException e) {
			setMessage("file.export.error");
			log.error("Export loginCountStat error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("file.export.error");
			log.error("Export loginCountStat error:" + e.getMessage());
			return ERROR;
		} finally {
			// 关闭流
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					;
				}
			}
		}

		// 设置下载文件属性
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition",
				"attachment;filename=loginCountStat.xls");
		response.setContentType("application/msexcel; charset=\"utf-8\"");

		FileInputStream fileInputStream = null;
		OutputStream out = null;
		try {
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(filePath);
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			setMessage("file.export.error");
			log.error("Export loginCountStat error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("file.export.error");
			log.error("Export loginCountStat error:" + e.getMessage());
			return null;
		} finally {
			// 关闭流
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					;
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					;
				}
			}
			// setOperationType(Constants.EXPORT,
			// ModuleId.CLW_M_CS_SIM_EXPORT_MID);
			addOperationLog("导出登录次数统计信息");
		}

		// 导出文件成功
		return null;

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
	 * @return the staticsList
	 */
	public List<LoginStaticsDataInfo> getStaticsList() {
		return staticsList;
	}

	/**
	 * @param staticsList
	 *            the staticsList to set
	 */
	public void setStaticsList(List<LoginStaticsDataInfo> staticsList) {
		this.staticsList = staticsList;
	}

	/**
	 * @return the staticsA_AreaList
	 */
	public List<LoginStaticsDataInfo> getStaticsA_AreaList() {
		return staticsA_AreaList;
	}

	/**
	 * @param staticsA_AreaList
	 *            the staticsA_AreaList to set
	 */
	public void setStaticsA_AreaList(
			List<LoginStaticsDataInfo> staticsA_AreaList) {
		this.staticsA_AreaList = staticsA_AreaList;
	}

	/**
	 * @return the staticsB_AreaList
	 */
	public List<LoginStaticsDataInfo> getStaticsB_AreaList() {
		return staticsB_AreaList;
	}

	/**
	 * @param staticsB_AreaList
	 *            the staticsB_AreaList to set
	 */
	public void setStaticsB_AreaList(
			List<LoginStaticsDataInfo> staticsB_AreaList) {
		this.staticsB_AreaList = staticsB_AreaList;
	}

	/**
	 * @return the staticsC_AreaList
	 */
	public List<LoginStaticsDataInfo> getStaticsC_AreaList() {
		return staticsC_AreaList;
	}

	/**
	 * @param staticsC_AreaList
	 *            the staticsC_AreaList to set
	 */
	public void setStaticsC_AreaList(
			List<LoginStaticsDataInfo> staticsC_AreaList) {
		this.staticsC_AreaList = staticsC_AreaList;
	}

	/**
	 * @return the staticsMap
	 */
	public Map getStaticsMap() {
		return staticsMap;
	}

	/**
	 * @param staticsMap
	 *            the staticsMap to set
	 */
	public void setStaticsMap(Map staticsMap) {
		this.staticsMap = staticsMap;
	}

}

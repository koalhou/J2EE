package com.neusoft.clw.businessmanage.overload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.neusoft.clw.businessmanage.overload.domain.OverloadInfo;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class OverloadAction extends PaginationAction {
	/** service共通类 */
	private transient Service service;

	private String start_time;

	private String end_time;

	private String vehicle_ln;

	private String operate_user_name;

	private String chooseorgid;

	private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	private OverloadInfo overload;

	private List<OverloadInfo> overloadList;

	private Map map = new HashMap();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<OverloadInfo> getOverloadList() {
		return overloadList;
	}

	public void setOverloadList(List<OverloadInfo> overloadList) {
		this.overloadList = overloadList;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public String getOperate_user_name() {
		return operate_user_name;
	}

	public void setOperate_user_name(String operate_user_name) {
		this.operate_user_name = operate_user_name;
	}

	public OverloadInfo getOverload() {
		return overload;
	}

	public void setOverload(OverloadInfo overload) {
		this.overload = overload;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	/**
	 * 2.0新页面超载查询跳转页面
	 * 
	 * @return
	 */
	public String newoverloadReady() {
		if (start_time == null) {
			start_time = DateUtil.getCurrentBeforeWeek();
		}
		if (StringUtils.isEmpty(end_time)) {
			end_time = DateUtil.getCurrentDay();
		}
		year = DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0新页面超载查询列表页面
	 * 
	 * @return
	 */
	public String newoverloadManage() {
		final String browseTitle = getText("overload.browse.title");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>(4);
		//MDC.put("loginid", getloginuuid());
		//MDC.put("modulename", "[overload]");
		log.info("[vehicle_ln:" + vehicle_ln + ",start_time:" + start_time
				+ ",end_time:" + end_time + ",user_name:" + operate_user_name+"chooseorgid:"+chooseorgid+"]:超载记录查询开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == overload) {
				overload = new OverloadInfo();
			}

			if (null == chooseorgid || "".equals(chooseorgid)) {
				overload.setOrganization_id(user.getOrganizationID());
			} else {
				overload.setOrganization_id(chooseorgid);
			}

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			overload.setSortname(sortName);
			overload.setSortorder(sortOrder);
			overload.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			overload.setStart_time(start_time);
			overload.setUser_name(SearchUtil
					.formatSpecialChar(operate_user_name));
			if (null != end_time && !end_time.equals("")) {
				overload.setEnd_time(end_time + " 23:59:59");
			}
			if (sortName.equals("vehicle_ln") || sortName.equals("USER_NAME")
					|| sortName.equals("PHOTO_DESC")) {
				sortName = "nlssort(" + sortName
						+ ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			totalCount = service
					.getCount("overload.getOverloadCount", overload);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			overloadList = service.getObjectsByPage("overload.getoverloadList",
					overload, (Integer.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getPagination(overloadList, totalCount, pageIndex);

			// 设置操作描述
			this.addOperationLog(browseTitle);
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_OVERLOAD_QUERY_ID);
			log.info("超载记录查询结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("超载记录查询异常", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("超载记录查询异常", e);
			return ERROR;
		}
		return SUCCESS;

	}

	public Map getPagination(List overloadList, int totalCount, String pageIndex) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < overloadList.size(); i++) {

			OverloadInfo s = (OverloadInfo) overloadList.get(i);
			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getPhoto_id());

			cellMap.put("cell", new Object[] { s.getVehicle_vin(),
					s.getVehicle_ln(), "超载", s.getUser_name(),s.getFlag_time(),
					s.getPhoto_time(), s.getPhoto_desc(), s.getPhoto_file() });
			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	public String exportOverload() {
		//MDC.put("modulename", "[exportoverload]");
		log.info("[vehicle_ln:" + vehicle_ln + ",start_time:" + start_time
				+ ",end_time:" + end_time + ",user_name:" + operate_user_name+"chooseorgid:"+chooseorgid+"]:超载记录导出开始");
		String timestr = start_time + "——" + end_time;
		
		try {
			UserInfo user = getCurrentUser();

			if (null == overload) {
				overload = new OverloadInfo();
			}

			if (null == chooseorgid || "".equals(chooseorgid)) {
				overload.setOrganization_id(user.getOrganizationID());
			} else {
				overload.setOrganization_id(chooseorgid);
			}
			// overload.setOrganization_id(user.getOrganizationID());
			// overload.setSortname(sortName);
			// overload.setSortorder(sortOrder);
			overload.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			overload.setStart_time(start_time);
			overload.setUser_name(SearchUtil
					.formatSpecialChar(operate_user_name));
			if (null != end_time && !end_time.equals("")) {
				overload.setEnd_time(end_time + " 23:59:59");
			}

			overloadList = (List<OverloadInfo>) service.getObjects(
					"overload.expoverloadList", overload);
		} catch (BusinessException e) {
			// setMessage("info.db.error");
			log.error("导出超载记录查询列表时出错:", e);
			return ERROR;
		} catch (Exception e) {
			// setMessage("info.db.error");
			log.error("导出超载记录查询列表时出错", e);
			return ERROR;
		}

		String filePath = "";
		OutputStream outputStream = null;
		try {

			filePath = "/tmp/" + UUIDGenerator.getUUID()
					+ "OverLoadRecords.xls";

			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("超载记录(" + timestr + ")");
			if (null == overloadList || overloadList.size() < 1) {
				overloadList.add(new OverloadInfo());
			}
			excelExporter
					.putAutoExtendSheets("exportOverload", 0, overloadList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			// setMessage("file.export.error");
			log.error("导出超载记录形成文件时出错:", e);
			return ERROR;
		} catch (Exception e) {
			// setMessage("file.export.error");
			log.error("导出超载记录形成文件时出错:", e);
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
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename=OverLoadRecords-" + name + ".xls");
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
			// setMessage("file.export.error");
			log.error("导出超载下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			// setMessage("file.export.error");
			log.error("导出超载下载时出错:",e);
			return ERROR;
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
			// 设置操作描述
			this.addOperationLog("导出超载记录");
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.CLW_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_OVERLOAD_EXPORT);
			log.info("超载记录导出结束");
		}
		// 导出文件成功
		return null;
	}

	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}

}

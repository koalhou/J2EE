package com.neusoft.clw.sysmanage.systools.messagesend.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.systools.messagesend.domain.MessageSendInfo;
import com.opensymphony.xwork2.ActionContext;

public class MessageSendAction extends PaginationAction {

	/** service共通类 */
	private transient Service service;

	/** 显示数据list * */
	private List<MessageSendInfo> messageList;

	private String vehicle_ln;
	private String vehicle_code;

	private String start_time;

	private String end_time;

	private String sms_type;

	private String chooseorgid;
	
	private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private Map map = new HashMap();

	MessageSendInfo messageSendInfo;

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public MessageSendInfo getMessageSendInfo() {
		return messageSendInfo;
	}

	public void setMessageSendInfo(MessageSendInfo messageSendInfo) {
		this.messageSendInfo = messageSendInfo;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	/**
	 * 历史消息查询跳转页面
	 * 
	 * @return
	 */
	public String init() {
		if (start_time == null) {
			start_time = DateUtil.getCurrentBeforeWeek();
		}
		if (StringUtils.isEmpty(end_time)) {
			end_time = DateUtil.getCurrentDay();
		}
		year=DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0历史消息浏览页面
	 * 
	 * @return
	 */
	public String newmessageSendList() {
		final String browseTitle = getText("messagesend.browse.title");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		log.debug("vehicle_ln:" + vehicle_ln);
		log.debug("chooseorgid:" + chooseorgid);
		log.debug("start_time:" + start_time);
		log.debug("sms_type:" + sms_type);
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(
							org.apache.struts2.StrutsStatics.HTTP_REQUEST);

			Map<String, String> paramap = new HashMap<String, String>();

			if (null != chooseorgid && !"".equals(chooseorgid)) {
				paramap.put("organization_id", chooseorgid);
			} else {
				paramap.put("organization_id", user.getOrganizationID());
			}

			//paramap.put("organization_id", user.getOrganizationID());

			if (start_time == null) {
				start_time = DateUtil.getCurrentBeforeWeek();
			}
			if (StringUtils.isEmpty(end_time)) {
				end_time = DateUtil.getCurrentDay();
			}
			if ((Integer.parseInt(end_time.replace("-", "")) - Integer
					.parseInt((DateUtil.getCurrentDay()).replace("-", ""))) > 0) {
				end_time = DateUtil.getCurrentDay();
			}
			if ("yutongsuperadmin".equals(user.getUserID())) {
				paramap.put("operate_user_id", null);
			} else {
				paramap.put("operate_user_id", user.getUserID());
			}
			paramap.put("vehicle_ln", SearchUtil.formatSpecialChar(vehicle_ln));
			paramap.put("vehicle_code", SearchUtil.formatSpecialChar(vehicle_code));
			if (!"".equals(start_time.trim())) {
				paramap.put("starttime", start_time + " 00:00:00");
			}
			paramap.put("endtime", end_time + " 23:59:59");
			paramap.put("sms_type", sms_type);
			totalCount = service.getCount("messagesend.getMessageCount",
					paramap);

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			if(sortName.equals("vehicle_ln")||sortName.equals("remark"))
			{
				sortName="nlssort("+sortName+",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			paramap.put("sortname", sortName);
			paramap.put("sortorder", sortOrder);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			messageList = service.getObjectsByPage(
					"messagesend.getMessageList", paramap, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getPagination(messageList, totalCount, pageIndex);

			if (messageList == null || 0 == messageList.size()) {
				this.addActionMessage(getText("nodata.list"));
			}

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_MESSAGELIST_QUERY_ID);
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error(browseTitle, e);
			return ERROR;
		}
		return SUCCESS;

	}

	/**
	 * 格式化日志信息
	 * 
	 * @param desc
	 * @param Object
	 * @return
	 */
	protected String formatLog(String desc, MessageSendInfo msginfo) {
		StringBuffer sb = new StringBuffer();
		if (null != desc) {
			sb.append(desc);
		}
		return sb.toString();
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

	public Map getPagination(List dayList, int totalCountDay, String pageIndex) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < dayList.size(); i++) {

			MessageSendInfo s = (MessageSendInfo) dayList.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getVehicle_ln());

			cellMap.put("cell", new Object[] {s.getVehicle_code(),s.getVehicle_ln(),
					//s.getUser_name(), 
					s.getOperate_time(),
					s.getDeal_state(), s.getUser_name(), s.getSms_type(),
					s.getRemark() });

			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		if (null == messageSendInfo) {
			messageSendInfo = new MessageSendInfo();
		}

		return mapData;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<MessageSendInfo> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageSendInfo> messageList) {
		this.messageList = messageList;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getSms_type() {
		return sms_type;
	}

	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}

	public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}

}

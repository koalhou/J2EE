package com.neusoft.clw.sysmanage.systools.messagesend.action;

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

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.systools.messagesend.domain.MessageSendInfo;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class MessageSendAction extends PaginationAction {

	/** service共通类 */
	private transient Service service;

	/** 显示数据list * */
	private List<MessageSendInfo> messageList;

	private String vehicle_ln;

	private String start_time;

	private String end_time;

	private String sms_type;

	private String chooseorgid;
	
	private String year;
	
	private MessageSendInfo exportObj;

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

	public MessageSendInfo getExportObj() {
		return exportObj;
	}

	public void setExportObj(MessageSendInfo exportObj) {
		this.exportObj = exportObj;
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

			cellMap.put("cell", new Object[] { s.getVehicle_ln(),
					s.getUser_name(), s.getOperate_time(),
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
	
	
	/**
     * 导出记录
     * @return
     */
    public String exportMessage() {
        String exportTitle = getText("消息调度");
        List <MessageSendInfo> list = new ArrayList <MessageSendInfo>();
        try {
        	UserInfo user = getCurrentUser();
        	Map<String, String> paramap = new HashMap<String, String>();
 			if (null != exportObj.getChooseorgid() && !"".equals(exportObj.getChooseorgid())) {
 				paramap.put("organization_id", exportObj.getChooseorgid());
 			} else {
 				paramap.put("organization_id", user.getOrganizationID());
 			}
 			if (exportObj.getStart_time() == null) {
 				exportObj.setStart_time(DateUtil.getCurrentBeforeWeek())  ;
 			}
 			if (StringUtils.isEmpty(exportObj.getEnd_time())) {
 				exportObj.setEnd_time(DateUtil.getCurrentDay());
 			}
 			if ((Integer.parseInt(exportObj.getEnd_time().replace("-", "")) - Integer
 					.parseInt((DateUtil.getCurrentDay()).replace("-", ""))) > 0) {
 				exportObj.setEnd_time(DateUtil.getCurrentDay());
 			}
 			if ("yutongsuperadmin".equals(user.getUserID())) {
 				paramap.put("operate_user_id", null);
 			} else {
 				paramap.put("operate_user_id", user.getUserID());
 			}
 			paramap.put("vehicle_ln", SearchUtil.formatSpecialChar(exportObj.getVehicle_ln()));
 			if (!"".equals(exportObj.getStart_time().trim())) {
 				paramap.put("starttime", exportObj.getStart_time() + " 00:00:00");
 			}
 			paramap.put("endtime", exportObj.getEnd_time() + " 23:59:59");
 			paramap.put("sms_type", exportObj.getSms_type());
 			paramap.put("sortname", "operate_time");
			paramap.put("sortorder", "desc");
            list = (List < MessageSendInfo >) service.getObjects("messagesend.getMessageList", paramap);
//             for (int i = 0; i < list.size(); i++) {
//            	 RouteChart s = (RouteChart) list.get(i);
//            	 if(s.getDriver_name()==null)
//            		 s.setDriver_name("未登录");
//            	 if(s.getSichen_name()==null)
//            		 s.setSichen_name("未登录");
//             }
        } catch (BusinessException e) {
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
        	e.printStackTrace();
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }
        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Message.xls";
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(exportTitle+"("+exportObj.getStart_time().substring(0,10)+"——"+exportObj.getEnd_time().substring(0,10)+")");
            if (null == list || list.size() < 1) {
            	list.add(new MessageSendInfo());
			}
            excelExporter.putAutoExtendSheets("exportMessage", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error(exportTitle+"Export Data error:" + e.getMessage());
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
        response.setHeader("Content-disposition", "attachment;filename=message_send-"+DateUtil.getCurrentDayTime()+".xls");
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
            log.error("Export menssage error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export menssage error:" + e.getMessage());
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
            // 设置操作描述
            this.addOperationLog("消息调度记录导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STU_RIDE_EXP);
        }
        // 导出文件成功
        return null;
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

}

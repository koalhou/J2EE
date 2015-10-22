package com.neusoft.clw.businessmanage.sendcommandhis.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import com.neusoft.clw.businessmanage.sendcommandhis.domain.SendCommandDomain;
import com.neusoft.clw.businessmanage.sendcommandhis.domain.UserListDomain;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class SendCmdHisAction extends PaginationAction {
    /** service共通类 */
    private transient Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getEndime() {
        return endime;
    }

    public void setEndime(String endime) {
        this.endime = endime;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    private String endime;

    private String begintime;
    
    private String chooseorgid;

    public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	private SendCommandDomain sendcommand;

    private List < SendCommandDomain > sendList;

    private UserListDomain userdomain;

    private List < UserListDomain > userList;
    
    private Map sendmap = new HashMap();
    
    private Map usermap=new HashMap();

    public List < SendCommandDomain > getSendList() {
        return sendList;
    }

    public void setSendList(List < SendCommandDomain > sendList) {
        this.sendList = sendList;
    }

    public SendCommandDomain getSendcommand() {
        return sendcommand;
    }

    public void setSendcommand(SendCommandDomain sendcommand) {
        this.sendcommand = sendcommand;
    }

    public Map getSendmap() {
        return sendmap;
    }

    public void setSendmap(Map sendmap) {
        this.sendmap = sendmap;
    }

    public UserListDomain getUserdomain() {
        return userdomain;
    }

    public void setUserdomain(UserListDomain userdomain) {
        this.userdomain = userdomain;
    }

    public Map getUsermap() {
        return usermap;
    }

    public void setUsermap(Map usermap) {
        this.usermap = usermap;
    }
    
    /**
     * 2.0收发记录查询跳转页面
     * @return
     */
    public String newsendcmdReady() {

        if (begintime == null) {
            begintime = DateUtil.getCurrentBeforeWeek();
        }
        if (StringUtils.isEmpty(endime)) {
            endime = DateUtil.getCurrentDay();
        }
        return SUCCESS;
    }
    
    public String newgetSendCmdList(){
        final String browseTitle = getText("sendcomhis.browse.title");
        UserInfo user = getCurrentUser();
        int totalCount = 0;
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {

            if (null == sendcommand) {
                sendcommand = new SendCommandDomain();
            }
            if(null==chooseorgid||"".equals(chooseorgid)){
            	sendcommand.setOrganization_id(user.getOrganizationID());
            }else{
            	sendcommand.setOrganization_id(chooseorgid);
            }
            if("".equals(sendcommand.getSend_type())){
            	sendcommand.setSend_type("''");
            }else if("'2001','2002','2202'".equals(sendcommand.getSend_type())){
            	List<String> str = (List<String>) ActionContext.getContext().getSession().get(Constants.PER_URL_LIST);
            	String type = "";
            	if(str.contains("111_3_1_14_2")){
            		type = "'2001'";
            		if(str.contains("111_3_1_14_1")){
            			type = type + "," + "'2002'";
            		}
            		if(str.contains("111_3_1_14_3")){
            			type = type + "," + "'2202'";
            		}
            	}else {
            		if(str.contains("111_3_1_14_1")){
            			type = "'2002'";
            			if(str.contains("111_3_1_14_3")){
                			type = type + "," + "'2202'";
                		}
            		}else {
            			if(str.contains("111_3_1_14_3")){
                			type = "'2202'";
                		}else {
							type = "'m'";
						}
					}
            		
				}
            	sendcommand.setSend_type(type);
			}
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            if(sortName.equals("vehicle_ln")||sortName.equals("operate_user_name"))
			{
				sortName="nlssort("+sortName+",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
            sendcommand.setSortname(sortName);
            sendcommand.setSortorder(sortOrder);
            if (null != endime&&!"".equals(endime)) {
                sendcommand.setEnd_time(endime + " 23:59:59");
            }

            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "1";
            }
            if (StringUtils.isEmpty(rpNum)) {
                rpNum = "10";
            }

            totalCount = service.getCount("Sendcmdhis.newgetSendcmdCount", sendcommand);
            sendList = service.getObjectsByPage("Sendcmdhis.newgetSendcmdList", sendcommand, (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.sendmap = getMapPagination(sendList, totalCount, pageIndex);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_SENDCMDHIS_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String getSendCmdList() {
        final String browseTitle = getText("sendcomhis.browse.title");
        UserInfo user = getCurrentUser();
        int totalCount = 0;
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {

            if (null == sendcommand) {
                sendcommand = new SendCommandDomain();
            }
            sendcommand.setOrganization_id(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            sendcommand.setSortname(sortName);
            sendcommand.setSortorder(sortOrder);
            if (null != endime&&!"".equals(endime)) {
                sendcommand.setEnd_time(endime + "23:59:59");
            }

            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "1";
            }
            if (StringUtils.isEmpty(rpNum)) {
                rpNum = "10";
            }

            totalCount = service.getCount("Sendcmdhis.getSendcmdCount",
                    sendcommand);
            sendList = service.getObjectsByPage("Sendcmdhis.getSendcmdList",
                    sendcommand, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.sendmap = getMapPagination(sendList, totalCount, pageIndex);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_SENDCMDHIS_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Map getMapPagination(List < SendCommandDomain > sendList,
            int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < sendList.size(); i++) {
            SendCommandDomain s = (SendCommandDomain) sendList.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getId());
            cellMap.put("cell", new Object[] {s.getVehicle_code(),s.getVehicle_ln(),
                    s.getSend_type(), s.getDeal_state(), s.getOperate_time(),
                    s.getOperate_user_name() });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, SendCommandDomain sendObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != sendObj) {
            if (null != sendObj.getId()) {
                OperateLogFormator.format(sb, "vehicleid", sendObj.getId());
            }
        }
        return sb.toString();
    }
}

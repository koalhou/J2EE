package com.neusoft.clw.feedback.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.feedback.domain.FeedBackMsg;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class FeedBackAction extends PaginationAction{

	/** service共通类 */
    private transient Service service;
	
    private FeedBackMsg feedBackMsg;
    
    private String empName;
    
    private String feedBackDate;
    
    private String feedBackStatus;
    
    private String feedBackId;
        
    private Map feedbackMap = new HashMap();
    
    private List< FeedBackMsg > list = new ArrayList< FeedBackMsg >();
    
    public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<FeedBackMsg> getList() {
		return list;
	}

	public void setList(List<FeedBackMsg> list) {
		this.list = list;
	}

	public FeedBackMsg getFeedBackMsg() {
		return feedBackMsg;
	}

	public void setFeedBackMsg(FeedBackMsg feedBackMsg) {
		this.feedBackMsg = feedBackMsg;
	}

	public String getFeedBackId() {
		return feedBackId;
	}

	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFeedBackDate() {
		return feedBackDate;
	}

	public void setFeedBackDate(String feedBackDate) {
		this.feedBackDate = feedBackDate;
	}

	public String getFeedBackStatus() {
		return feedBackStatus;
	}

	public void setFeedBackStatus(String feedBackStatus) {
		this.feedBackStatus = feedBackStatus;
	}

	public Map getFeedbackMap() {
		return feedbackMap;
	}

	public void setFeedbackMap(Map feedbackMap) {
		this.feedbackMap = feedbackMap;
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
     * 进入问题反馈列表页面
     * @return
     */
    public String readyPage(){
    	
    	return SUCCESS;
    }
    
    /**
     * 浏览问题反馈信息
     * @return
     */
    public String feedBackMsgBrowse() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	String rpNum = request.getParameter("rp");
    	String pageIndex = request.getParameter("page");
    	if(null == rpNum || rpNum.length() == 0){
    		rpNum = request.getParameter("pageSize");
    		pageIndex = request.getParameter("page");
    	}
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
//        if(null != feedBackMsg){
        	if(null != empName && empName.length() != 0){
        		map.put("empName", empName);
        	}
        	if(null != feedBackDate && feedBackDate.length() != 0 && !feedBackDate.equals("undefined")){
        		map.put("feedBackDate",feedBackDate);
        	}
        	if(null != feedBackStatus && feedBackStatus.length() != 0){
        		map.put("feedBackStatus",feedBackStatus);
        	}
//        }
        map.put("sortname",sortName);
        map.put("sortorder", sortOrder);
                
        try {
        	int totalCount = service.getCount("feedback.searchFeedBackPageCount", map);
        	list = (List < FeedBackMsg >)service.getObjectsByPage(
                    "feedback.searchFeedBackPage", map, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
        	this.feedbackMap = getPagination(list, totalCount, pageIndex, rpNum);
        	
        	// 设置操作描述
//            this.addOperationLog("浏览问题反馈信息");
            // 设置操作类型
//            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
//            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
//            this.setModuleId(MouldId.XCP_STUDENTMANAGE_QUERY);
        	
        } catch (Exception e) {
        	addActionError(getText(e.getMessage()));
            log.error("浏览问题反馈", e);
            return ERROR;
        }
        
    	return SUCCESS;
    }
    
    public String findFeedBackDetail(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(null != feedBackMsg){
    		if(null != feedBackMsg.getSuggestId() && feedBackMsg.getSuggestId() != 0 && !feedBackMsg.getSuggestId().equals("null")){
    			map.put("feedBackId",feedBackMsg.getSuggestId());
    		}
    		if(null != feedBackMsg.getSuggestDate() && feedBackMsg.getSuggestDate().length() != 0 && !feedBackMsg.getSuggestDate().equals("null")){
    			map.put("feedBackDate",feedBackMsg.getSuggestDate());
    		}
    	}
    	try {
    		List<FeedBackMsg> list = (List<FeedBackMsg>)service.getObjects("feedback.searchFeedById", map);
    		if(null != list && list.size() != 0){
    			feedBackMsg = list.get(0);
    			if(feedBackMsg.getType().equals("0")){
    				feedBackMsg.setType("1");
    			} else {
    				feedBackMsg.setType("0");
    			}
    		}
    	} catch (Exception e) {
    		addActionError(getText(e.getMessage()));
            log.error("浏览问题反馈明细", e);
            return ERROR;
    	}
    	return SUCCESS;
    }
	
    
    public Map getPagination(List list, int totalCount, String pageIndex, String rpNum) {
    	List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
        	FeedBackMsg s = (FeedBackMsg) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getSuggestId());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getContent(),
                    s.getEmpCode(),
                    s.getOwner(),
                    s.getSuggestDate(),
                    s.getType()
                    });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
    	return mapData;
    }
    
}

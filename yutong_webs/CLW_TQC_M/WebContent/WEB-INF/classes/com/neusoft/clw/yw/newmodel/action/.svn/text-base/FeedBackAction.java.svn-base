package com.neusoft.clw.yw.newmodel.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.newmodel.domain.FeedBackMsg;

import com.neusoft.clw.yw.newmodel.domain.BaseNotification;
import com.neusoft.clw.yw.newmodel.domain.FeedBackMsg;
import com.neusoft.clw.yw.newmodel.domain.JacksonUtils;
import com.neusoft.clw.yw.newmodel.domain.PushMessageCls;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.ie.IEExcelExporter;

import com.opensymphony.xwork2.ActionContext;
import com.yutong.axxc.xmpp.AndroidpnPusher;
import com.yutong.axxc.xmpp.IPusher;

public class FeedBackAction extends PaginationAction{

	/** service共通类 */
    private transient Service service;
	
    private FeedBackMsg feedBackMsg;
    
    private String empName;
    
    private String feedBackDate;
    
    private String feedBackStatus;
    
    private String feedBackId;
    
    private String beginDate;
    
    private String endDate;
    
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
	
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
    	 ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                 getText("feedback.location"));
    	this.endDate=DateUtil.getCurrentDay();
    	Calendar cal=Calendar.getInstance();
    	cal.add(Calendar.DATE, -30);
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    	this.beginDate=format.format(cal.getTime());
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
       //新增
        map.put("beginDate", this.beginDate);
        map.put("endDate", this.endDate);
        map.put("TYPE", this.feedBackStatus);
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
    /**
     * 立即回复
     * @return
     */
	public String feedbackOperateDetail(){
		if(this.feedBackId==null || "".equals(this.feedBackId)){
			log.error("问题反馈Id为空");
            return ERROR;
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("feedBackId", this.feedBackId);
			
			List<FeedBackMsg> list = (List<FeedBackMsg>)service.getObjects("feedback.searchFeedById", map);
    		if(null != list && list.size() != 0){
    			feedBackMsg = list.get(0);
    		}
		} catch (BusinessException e) {
			log.error("问题立即回复查询详情出错",e);
		}
		return SUCCESS;
	}
	private String pushServerUrl="http://10.8.2.199:5333/Androidpn-tomcat/pushapi.do?action=send";
	//private IPusher puser=new AndroidpnPusher(Config.props.getProperty("pushServerUrl"));
	private IPusher puser=new AndroidpnPusher(pushServerUrl);
	private String apikey="0123456789";
	private String appid="anxin_tqc"; 
	
	
	public void push2SingleUser(String userid,String message)
	{
		try {
			puser.push2SingleUser(apikey, appid, userid, message);
		} catch (Exception e) {
			log.error("问题反馈推送失败"+e.getMessage());
		}
	}
	
	
	/**
	 * 新闻推送
	 */
	public void pushMsg(FeedBackMsg eachnewsList){       
		PushMessageCls pushMessageBean = new PushMessageCls();

		pushMessageBean.setRule_id("05");    //02厂内推送 03厂外推送 01新闻推送
		pushMessageBean.setUser_code(eachnewsList.getEmpCode());
		
		BaseNotification content = new BaseNotification();
		//content.setNews_type(eachnewsList.getNews_type());
		//content.setNews_id(eachnewsList.getNews_id());
		//content.setNews_title(eachnewsList.getNews_title());
		//content.setNews_summary(eachnewsList.getContent());
		content.setMsg_id(eachnewsList.getMsg_id());
		content.setAdvise(eachnewsList.getContent());
		content.setAdvise_time(eachnewsList.getSuggestDate());
		content.setReply(eachnewsList.getOperate_content());
		content.setReply_time(eachnewsList.getOperate_time());
		//content.setNews_time(DateUtil.date2string(eachnewsList.getOperate_time(),"yyyyMMddHHmmss"));
		//content.setNews_time(eachnewsList.getOperate_time());
		/*try
		{
			String msgJson = JacksonUtils.toJson(content);
			pushMessageBean.setContent(msgJson);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}*/
		pushMessageBean.setContent(content);
		// 将推送内容放入到推送缓冲池中
		//PushMsgBuffer.getInstance().add(pushMessageBean);
		// 将推送信息存储到数据库中
		//DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertPushInfo(pushMessageBean));//liuja先删除掉，之后要加上。推送记录
		try
		{
			log.info("上行推送的json...."+JacksonUtils.toJson(pushMessageBean));
			push2SingleUser(eachnewsList.getEmpCode(),JacksonUtils.toJson(pushMessageBean));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
    public String feedbackUpdate(){
    	try {
    		UserInfo user = getCurrentUser();
    		this.feedBackMsg.setType("1");
    		this.feedBackMsg.setOperator(user.getUserName());
    		this.feedBackMsg.setOperate_time(DateUtil.getThisSecondTime2());
    		pushMsg(this.feedBackMsg);
			this.service.update("feedback.feedbackUpdate", this.feedBackMsg);
		} catch (BusinessException e) {
			log.error("问题反馈状态修改失败", e);
			return ERROR;
		}
		return SUCCESS;
    }
    /**
	 * 根据条件查询导出
	 */
	public String exportSearchList(){
		String filePath = "";
        OutputStream outputStream = null;
        try {
        	Map<String,String> paramMap=new HashMap<String,String>();
        	paramMap.put("beginDate", this.beginDate);
        	paramMap.put("endDate", this.endDate);
        	paramMap.put("TYPE", this.feedBackStatus);
        	paramMap.put("empName", this.empName);
        	List<FeedBackMsg> exportlist=this.service.getObjects("feedback.searchFeedBackPage", paramMap);
        	for(int i=0;i<exportlist.size();i++){
        		FeedBackMsg feed=exportlist.get(i);
        		feed.setExportRownum(String.valueOf(i+1));
        		
        		if("1".equals(feed.getType())){
        			feed.setType("已回复");
        		}else{
        			feed.setType("未回复");
        		}
        	}
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "feedbackList.xls";
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            String title="";
            if(endDate!=null && !"".equals(endDate) && beginDate!=null && !"".equals(beginDate)){
            	title="("+this.beginDate+"至"+this.endDate+")";
            }
            excelExporter.setTitle("问题反馈"+title);

            excelExporter.putAutoExtendSheets("exportFeedBackList", 0, exportlist);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error("Export loginCount error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export loginCount error:" + e.getMessage());
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
        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
        	// 设置下载文件属性
    		String fileName = URLEncoder.encode("问题反馈","UTF8");
    		HttpServletResponse response = ServletActionContext.getResponse();
	        response.setHeader("Content-disposition", "attachment;filename="+fileName+"-" + DateUtil.formatDateToString(new Date(), "yyyyMMddHHmmss") + ".xls");
	        response.setContentType("application/msexcel; charset=\"utf-8\"");
    	        
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export loginCount error:" + e.getMessage());
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
        }
        return null;
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
                    s.getYgb_tel(),
                    s.getSuggestDate(),
                    s.getType(),
                    s.getOperator(),
                    s.getOperate_time(),
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

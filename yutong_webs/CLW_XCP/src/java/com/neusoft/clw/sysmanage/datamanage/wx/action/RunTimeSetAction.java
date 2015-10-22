package com.neusoft.clw.sysmanage.datamanage.wx.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.runtimeset.domain.RunTimeSet;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class RunTimeSetAction extends PaginationAction{
	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private List<RunTimeSet> runTimeSetList;
	
	private RunTimeSet runTimeSet;
	
	private String goStartTime;
	
	private String goEndTime;
	
	private String backStartTime;
	
	private String backEndTime;
	
	private String keyId;
	
    /**
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
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
	
	public String init(){
	
		return SUCCESS;
	}
	
	public String iframeSet(){
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String findRunTimeSetList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 运行时段设置分页处理!");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map<String,Object> mpas = new HashMap<String,Object>();
        try {
			
			// 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
        	
			
			mpas.put("enterpriseId", user.getEntiID());
			
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("runtimeset.findRunTimeSetListCount", mpas);
            
        	runTimeSetList = service.getObjectsByPage(
					"runtimeset.findRunTimeSetList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
        	map = getPagination(runTimeSetList,totalCount,pageIndex,rpNum);
        	
			log.info("logid:" + logid + "," + "运行时段设置分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "运行时段设置分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getPagination(List<RunTimeSet> runtimeList, int totalCountDay, String pageIndex, String rpNum) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < runtimeList.size(); i++) {

        	RunTimeSet s = (RunTimeSet) runtimeList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getRunId());
            cellMap.put("cell", new Object[]{
            		(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
            	s.getRunId(),s.getGoTimeRange(),s.getBackTimeRange(),
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	public String addRunTimeSet(){
		String logid = getlogid();
		UserInfo users = getCurrentUser();
		log.info("logid:" + logid + "," + " 运行时段设置开始!");
		String message = "";
		try {
			if(null == runTimeSet){
				runTimeSet = new RunTimeSet();
			}
			runTimeSet.setCreateBy(users.getUserID());
			runTimeSet.setEnterpriseId(users.getEntiID());
			runTimeSet.setGoStartTime(goStartTime);
			runTimeSet.setGoEndTime(goEndTime);
			runTimeSet.setBackStartTime(backStartTime);
			runTimeSet.setBackEndTime(backEndTime);
			if(null != keyId && !"".equals(keyId))
				runTimeSet.setRunId(keyId);
			int rangeCount = service.getCount("runtimeset.findRangeCount", runTimeSet);
			if(rangeCount == 0){
				if(null != runTimeSet.getRunId() && runTimeSet.getRunId().length() > 0){
					service.delete("runtimeset.deleteID", runTimeSet.getRunId());
				}
				runTimeSet.setRunId(UUIDGenerator.getUUID().replace("-", ""));
				service.insert("runtimeset.addRunTimeSet", runTimeSet);
				message = "success";
			} else {
				message = "与已有时间范围重复！";
			}
			runTimeSet.setRunId(null);
			runTimeSet =null;
			keyId=null;
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 运行时段设置出错!");
			message = "error";
		}
		map.put("message", message);
		log.info("logid:" + logid + "," + " 运行时段设置成功!");
		return SUCCESS;
	}
	
	public String deleteRunTimeSet(){
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 删除运行时段设置开始!");
		String message = "";
		try {
			service.delete("runtimeset.deleteID", keyId);
			message = "success";
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 删除运行时段设置出错!");
			message = "error";
		}
		map.put("message", message);
		log.info("logid:" + logid + "," + " 删除运行时段设置成功!");
		return SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked" })
	public String findSign(){
		String logid = getlogid();
		log.info("logid:" + logid + "," + " ID查找设置开始!");
		String message ="";
		
		try {
			Map<String,Object> mpas = new HashMap<String,Object>();
			mpas.put("runId", keyId);
			runTimeSetList = service.getObjects("runtimeset.findSetSign", keyId);
			message = "success";
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " ID查找设置出错!");
			message = "error";
		}
		map.put("message", message);
		map.put("timelist", runTimeSetList);
		log.info("logid:" + logid + "," + " ID查找设置成功!");
		return SUCCESS;
	}
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<RunTimeSet> getRunTimeSetList() {
		return runTimeSetList;
	}
	public void setRunTimeSetList(List<RunTimeSet> runTimeSetList) {
		this.runTimeSetList = runTimeSetList;
	}
	public RunTimeSet getRunTimeSet() {
		return runTimeSet;
	}
	public void setRunTimeSet(RunTimeSet runTimeSet) {
		this.runTimeSet = runTimeSet;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getGoStartTime() {
		return goStartTime;
	}
	public void setGoStartTime(String goStartTime) {
		this.goStartTime = goStartTime;
	}
	public String getGoEndTime() {
		return goEndTime;
	}
	public void setGoEndTime(String goEndTime) {
		this.goEndTime = goEndTime;
	}
	public String getBackStartTime() {
		return backStartTime;
	}
	public void setBackStartTime(String backStartTime) {
		this.backStartTime = backStartTime;
	}
	public String getBackEndTime() {
		return backEndTime;
	}
	public void setBackEndTime(String backEndTime) {
		this.backEndTime = backEndTime;
	}
	
	

}

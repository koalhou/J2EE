package com.neusoft.clw.sysmanage.datamanage.paramset.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.paramset.domain.ClwJcParamCfgT;

public class ParamSetAction extends PaginationAction {
	//日志信息
	Log log=LogFactory.getLog(ParamSetAction.class);
	private ClwJcParamCfgT paramSet;
	private transient Service service;
	private Map<String,Object> jsonMap;
	
	/**
	 * 进入参数设置管理界面
	 * @return SUCCESS
	 */
	public String init(){
		log.info("进入参数设置管理界面");
		return SUCCESS;
	}
	/**
	 * 参数设置列表
	 * @return SUCCESS
	 */
	@SuppressWarnings("unchecked")
	public String getParamSetList(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			//每页显示多少行
			String rpNum = request.getParameter("rp");
			//当前页码
	        String pageIndex = request.getParameter("page");
	        //得到查询参数
	        paramSet=new ClwJcParamCfgT();
	        String paramName = request.getParameter("paramName");
	        paramSet.setParamName(paramName);
	        int totalCount = 0;
			//得到总行数
			totalCount=service.getCount("paramSet.getParamSetListCount", paramSet);
			//得到当前页的数据集
			List<ClwJcParamCfgT> list=(List<ClwJcParamCfgT>)service.getObjectsByPage("paramSet.getParamSetList", paramSet,(Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//转换成前端页面识别的数据格式
			this.jsonMap = getPagination(list, totalCount, pageIndex, rpNum);
		} catch (NumberFormatException e) {
			log.error("查询参数设置列表错误",e);
			return ERROR;
		} catch (BusinessException e) {
			log.error("查询参数设置列表错误",e);
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 进入参数添加页面,初始化预添加信息
	 */
	public String preAddParamSet(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String paramName=request.getParameter("paramName");
		if(paramName!=null && !"".equals(paramName)){
			this.paramSet=this.getParamSetByName(paramName);
			//存储旧的参数名字
			this.paramSet.setOldParamName(this.paramSet.getParamName());
		}
		if(this.paramSet==null){
			this.paramSet=new ClwJcParamCfgT();
		}
		return SUCCESS;
	}
	/**
	 * 根据参数名称查询一个ClwJcParamCfgT对象
	 * @param  String paramName
	 * @return ClwJcParamCfgT
	 */
	public ClwJcParamCfgT getParamSetByName(String paramName){
		ClwJcParamCfgT paramSet=null;
		try {
			paramSet=(ClwJcParamCfgT)service.getObject("paramSet.getParamSetByName", paramName);
		} catch (Exception e) {
			log.error("根据参数名称查询一个ClwJcParamCfgT对象出错",e);
		}
		
		return paramSet;
	}
	/**
	 * 添加参数设置信息
	 * @return SUCCESS
	 */
	public String addParamSet(){
		try {
			if(this.paramSet==null){
				log.error("添加参数设置信息错误,ClwJcParamCfgT对象为null!");
				return ERROR;
			}
		    service.insert("paramSet.addParamSet", this.paramSet);
		} catch (Exception e) {
			log.error("添加参数设置信息错误",e);
		}
		return SUCCESS;
	}
	/**
	 * 根据参数名称删除一个ClwJcParamCfgT对象
	 * @param  String paramName
	 * @return SUCCESS
	 */
	public void delParamSetByName(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			String paramName=request.getParameter("paramName");
			if(paramName!=null && !"".equals(paramName)){
				service.delete("paramSet.delParamSetByName", paramName);
			}
		} catch (Exception e) {
			log.error("根据参数名称删除一个ClwJcParamCfgT对象出错",e);
		}
	}
	/**
	 * 修改一个ClwJcParamCfgT对象
	 */
	public String updateParamSet(){
		try {
			if(this.paramSet==null){
				log.error("修改参数设置信息错误,ClwJcParamCfgT对象为null!");
				return ERROR;
			}
		    service.update("paramSet.updateParamSetByName", this.paramSet);
		} catch (Exception e) {
			log.error("修改参数设置信息错误",e);
		}
		return SUCCESS;
	}
	/**
     * 转换Map
     * @param list
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map<String,Object> getPagination(List<ClwJcParamCfgT> list, int totalCount, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	ClwJcParamCfgT paramSet = list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id",paramSet.getParamName());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    paramSet.getParamName(),
                    paramSet.getParamValue(),
                    paramSet.getRemark()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
	public ClwJcParamCfgT getParamSet() {
		return paramSet;
	}
	public void setParamSet(ClwJcParamCfgT paramSet) {
		this.paramSet = paramSet;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	} 
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}
	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
}

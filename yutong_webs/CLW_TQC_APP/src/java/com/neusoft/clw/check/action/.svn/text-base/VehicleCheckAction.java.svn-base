package com.neusoft.clw.check.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.check.domain.AlarmEntity;
import com.neusoft.clw.check.domain.VehicleCheckSearch;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class VehicleCheckAction extends PaginationAction{
	/** service共通类 */
    private transient Service service;  
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
  //公车私用巡检查询VO
    private VehicleCheckSearch searchVO=new VehicleCheckSearch();
    private AlarmEntity alarmShow=new AlarmEntity();
    
    
	public AlarmEntity getAlarmShow() {
		return alarmShow;
	}

	public void setAlarmShow(AlarmEntity alarmShow) {
		this.alarmShow = alarmShow;
	}

	public VehicleCheckSearch getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(VehicleCheckSearch searchVO) {
		this.searchVO = searchVO;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
    /**
     * 从菜单进入页面
     */
	public String readyPage(){
		//设置默认查询条件
		searchVO.setBeginTime(DateUtil.getMonthFirstDay1());
		searchVO.setEndTime(DateUtil.getCurrentDay());
		searchVO.setCurr_date(DateUtil.getCurrentDay());
		
		return SUCCESS;
	}
	/**
	 * 公车私用巡检列表,异常记录查询
	 */
	public String illList(){
		//根据传过来的查询参数查询列表数据
		String browseTitle="公车私用巡检列表,异常记录查询";
		//用户信息
		UserInfo userInfo=getCurrentUser();
		HttpServletRequest request = getCurrentRequest();
		try {
			//分页
			String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            searchVO.setSortName(sortName);
            searchVO.setSortOrder(sortOrder);
            
            searchVO.setOrganization_id(userInfo.getOrganizationID());
            int totalCount = 0;
            totalCount=this.service.getCount("VehicleCheckManage.getVehicleCheckCount", searchVO);
			List<AlarmEntity> list=this.service.getObjectsByPage("VehicleCheckManage.getVehicleCheckList", searchVO, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getIllPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
			
		} catch (BusinessException e) {
			this.log.error("公车私用巡检列表,异常记录查询出错", e);
		}
		// 设置操作描述
		this.addOperationLog(browseTitle);
		// 设置操作类型
		this.setOperationType(Constants.SELECT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
		return SUCCESS;
	}
	/**
	 * 异常用车处理后指向下一条记录
	 */
	public void getNextRow(){
		String ret="";
		try {
			List<AlarmEntity> list = this.service.getObjectsByPage("VehicleCheckManage.getVehicleCheckList", searchVO,0, 1);
			if(list.size()>0){
				AlarmEntity info=list.get(0);
				ret=info.getAlarm_id()+","+info.getVehicle_code()+","+info.getVehicle_ln();
			}
		} catch (BusinessException e) {
			ret="";
			this.log.error("异常用车处理后指向下一条记录出错", e);
		}
		this.printWriter(ret);
	}
	/**
	 * 公车私用巡检列表,处理记录查询
	 */
	public String operatedList(){
		//根据传过来的查询参数查询列表数据
		String browseTitle="公车私用巡检列表,处理记录查询";
		//用户信息
		UserInfo userInfo=getCurrentUser();
		HttpServletRequest request = getCurrentRequest();
		
		try {
			//分页
			String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            searchVO.setSortName(sortName);
            searchVO.setSortOrder(sortOrder);
            
            searchVO.setOrganization_id(userInfo.getOrganizationID());
            int totalCount = 0;
            totalCount=this.service.getCount("VehicleCheckManage.getVehicleCheckCount", searchVO);
			List<AlarmEntity> list=this.service.getObjectsByPage("VehicleCheckManage.getVehicleCheckList", searchVO, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getOperatedPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
			
		} catch (BusinessException e) {
			this.log.error("公车私用巡检列表,处理记录查询出错", e);
		}
		// 设置操作描述
		this.addOperationLog(browseTitle);
		// 设置操作类型
		this.setOperationType(Constants.SELECT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
		return SUCCESS;
	}
	/**
	 * 异常用车导出
	 */
	public String exportIllList(){
		List<AlarmEntity> exportlist;
		UserInfo user = getCurrentUser();

		if (searchVO.getOrganization_id() == null
				|| "".equals(searchVO.getOrganization_id())) {
			searchVO.setOrganization_id(user.getOrganizationID());
		}
		try {
			searchVO.setOperate_state("0");//未处理
			exportlist = this.service.getObjects("VehicleCheckManage.getVehicleCheckList", searchVO);
		} catch (BusinessException e) {
			log.error("Export error:" + e.getMessage(),e);
            return ERROR;
		}
		String title="异常用车记录";
    	return exportList(title,"illListExport",exportlist);
    	
	}
	/**
	 * 处理记录导出
	 */
	public String exportOperatedList(){
		List<AlarmEntity> exportlist;
		UserInfo user = getCurrentUser();

		if (searchVO.getOrganization_id() == null
				|| "".equals(searchVO.getOrganization_id())) {
			searchVO.setOrganization_id(user.getOrganizationID());
		}
		try {
			searchVO.setOperate_state("1");//已处理
			exportlist = this.service.getObjects("VehicleCheckManage.getVehicleCheckList", searchVO);
			for(AlarmEntity obj:exportlist){
	    		//处理类型
	    		if(Constants.VECHICLE_ILLEAGAL_USE.equals(obj.getOperate_type())){
	    			obj.setOperate_type("公车私用");
	    		}else{
	    			obj.setOperate_type("正常使用");
	    		}
	    	}
		} catch (BusinessException e) {
			log.error("Export error:" + e.getMessage(),e);
            return ERROR;
		}
		String title="异常用车处理记录";
    	return exportList(title,"operatedExport",exportlist);
	}
	/**
	 * 导出
	 */
	public String exportList(String title,String sheetName,List<AlarmEntity> exportList){
		String filePath = "";
        OutputStream outputStream = null;
        try {
        	
            filePath = "/tmp/" + UUIDGenerator.getUUID() +sheetName+".xls";
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(title+"("+searchVO.getBeginTime()+"~"+searchVO.getEndTime()+")");

            excelExporter.putAutoExtendSheets(sheetName, 0, exportList);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export error:" + e.getMessage());
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
    		String fileName = URLEncoder.encode(title,"UTF8");
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
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export error:" + e.getMessage());
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
	/**
	 * 加载iframe页面（此页面通过iframe.src加载地图轨迹页面）
	 * 通过iframe的目的：完全卸载加载的地图
	 */
	public String loadIframe(){
		HttpServletRequest request=getCurrentRequest();
		request.setAttribute("alarmId",request.getParameter("id"));
		return SUCCESS;
	}
	/**
	 * 显示地图轨迹页面
	 */
	public String showMapPage(){
		HttpServletRequest request = getCurrentRequest();
		//告警id
		String id=request.getParameter("id");
		try {
			//根据告警ID查询一条告警信息
			alarmShow=(AlarmEntity)this.service.getObject("VehicleCheckManage.getAlarmById", id);
			//根据vin和日期范围查询轨迹信息
			
		} catch (BusinessException e) {
			this.log.error("显示地图轨迹页面查询出错", e);
		}
		return SUCCESS;
	}
	/**
	 * 意见批量处理
	 */
	public void batchOperate(){
		//用户信息
		UserInfo userInfo=getCurrentUser();
		HttpServletRequest request=getCurrentRequest();
		String ids=request.getParameter("ids");
		String desc=request.getParameter("desc");
		String type=request.getParameter("type");
		//处理的告警参数
		AlarmEntity alarm=new AlarmEntity();
		alarm.setDeal_flag(Constants.ALARM_E_CODE);//已处理
		alarm.setUser_id(userInfo.getUserID());
		alarm.setConfirm_time(DateUtil.formatDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		alarm.setIds(formatString(ids));
		alarm.setOpeerate_desc(desc);
		alarm.setOperate_type(type);
		try {
			this.service.update("VehicleCheckManage.updateAlarm", alarm);
		} catch (BusinessException e) {
			this.log.error("意见批量处理出错", e);
			this.printWriter("处理失败!");
		}
		this.printWriter("处理成功!");
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
     * 得到当前请求对象
     */
    private HttpServletRequest getCurrentRequest(){
    	return (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    }
    /**
     * 对以逗号分隔的字符串进行格式化
     */
    private String formatString(String value){
    	StringBuffer sb=new StringBuffer();
    	if(value!=null){
    		String[] strs=value.split(",");
    		int len=strs.length;
    		for(int i=0;i<len;i++){
    			if(i==len-1){
    				sb.append("'"+strs[i]+"'");
    			}else{
    				sb.append("'"+strs[i]+"',");
    			}
    		}
    	}
    	return sb.toString();
    }
	/**
     * 转换Map 异常记录
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getIllPagination(List<AlarmEntity> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	AlarmEntity alarm=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", alarm.getAlarm_id());
            cellMap.put("cell", new Object[] {
            		alarm.getAlarm_id(),
                    alarm.getVehicle_code(),
                    alarm.getVehicle_ln(),
                    alarm.getDriver_name(),
                    alarm.getDriver_tel(),
                    alarm.getEffect_time(),
                    alarm.getAlarm_time(),
                    alarm.getAlarm_end_time(),
                    alarm.getUse_time(),
                    alarm.getMileage(),
                    alarm.getAlarm_id()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map 处理记录
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getOperatedPagination(List<AlarmEntity> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	AlarmEntity alarm=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", alarm.getAlarm_id());
            cellMap.put("cell", new Object[] {
            		alarm.getAlarm_id(),
                    alarm.getVehicle_code(),
                    alarm.getVehicle_ln(),
                    alarm.getDriver_name(),
                    alarm.getDriver_tel(),
                    alarm.getEffect_time(),
                    alarm.getAlarm_time(),
                    alarm.getAlarm_end_time(),
                    alarm.getUse_time(),
                    alarm.getMileage(),
                    alarm.getOperate_type(),
                    alarm.getOpeerate_desc(),
                    alarm.getUser_name(),
                    alarm.getConfirm_time(),
                    alarm.getAlarm_id()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
}

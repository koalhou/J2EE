package com.neusoft.clw.trippatch.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
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
import com.neusoft.clw.tree.domain.TreeNodesAttri;
import com.neusoft.clw.trippatch.domain.TripPatch;
import com.neusoft.clw.trippatch.domain.TripSearchVO;
import com.neusoft.clw.trippatch.domain.VehicleTree;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class TripPatchAction extends PaginationAction{
	/** service共通类 */
    private transient Service service;
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
   //临时派车补录查询VO
    private TripSearchVO searchVO=new TripSearchVO();
    private TripPatch tripPatch=new TripPatch();
    
    private List<VehicleTree> treeNodesList;
    
    private String message;
    
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<VehicleTree> getTreeNodesList() {
		return treeNodesList;
	}

	public void setTreeNodesList(List<VehicleTree> treeNodesList) {
		this.treeNodesList = treeNodesList;
	}

	public TripSearchVO getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(TripSearchVO searchVO) {
		this.searchVO = searchVO;
	}

	public TripPatch getTripPatch() {
		return tripPatch;
	}

	public void setTripPatch(TripPatch tripPatch) {
		this.tripPatch = tripPatch;
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
		if(this.message != null){
			try {
				this.message=new String(this.message.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				this.log.error(e.getMessage());
			}
			this.addActionMessage(this.message);
		}
		//设置默认查询条件
		searchVO.setStart_time(DateUtil.getMonthFirstDay1());
		searchVO.setEnd_time(DateUtil.getCurrentDay());
		searchVO.setCur_time(DateUtil.getCurrentDay());
		return SUCCESS;
	}
	/**
	 * 临时派车补录列表
	 */
	public String tripPatchList(){
		//根据传过来的查询参数查询列表数据
		String browseTitle="临时派车补录列表";
		
		HttpServletRequest request = getCurrentRequest();
		
		try {
			//分页
			String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            searchVO.setSortName(sortName);
            searchVO.setSortOrder(sortOrder);
            int totalCount = 0;
            totalCount=this.service.getCount("TripPatchManage.getTripPatchCount", searchVO);
			List<TripPatch> list=this.service.getObjectsByPage("TripPatchManage.getTripPatchList", searchVO, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
			
		} catch (BusinessException e) {
			this.log.error("临时派车补录列表查询出错", e);
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
	 * 临时派车补录列表导出
	 */
	public String exportTripPatchList(){
		String filePath = "";
        OutputStream outputStream = null;
        try {
        	List<TripPatch> exportlist=this.service.getObjects("TripPatchManage.getTripPatchList", searchVO);
        	
        	for(TripPatch obj:exportlist){
        		//行驶线路类别，显示成汉字
        		if(Constants.TRIP_PATCH_ROUTE_MORNING.equals(obj.getRoute_type())){
        			obj.setRoute_type("早班");
        		}else if(Constants.TRIP_PATCH_ROUTE_NORMAL.equals(obj.getRoute_type())){
        			obj.setRoute_type("厂内通勤");
        		}else if(Constants.TRIP_PATCH_ROUTE_NIGHT.equals(obj.getRoute_type())){
        			obj.setRoute_type("晚班");
        		}
        		//使用类别
        		if(Constants.TRIP_PATCH_TYPE_NORMAL.equals(obj.getType())){
        			obj.setType("通勤");
        		}else{
        			obj.setType("公差");
        		}
        	}
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "tripPatchExport.xls";
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("临时派车补录("+searchVO.getStart_time()+"~"+searchVO.getEnd_time()+")");

            excelExporter.putAutoExtendSheets("tripPatchExport", 0, exportlist);
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
    		String fileName = URLEncoder.encode("临时派车补录","UTF8");
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
	/**
	 * 显示地图轨迹页面
	 */
	public String showMapPage(){
		HttpServletRequest request = getCurrentRequest();
		//告警id
		//String id=request.getParameter("id");
		try {
			//根据告警ID查询一条告警信息
			//tripPatch=(TripPatch)this.service.getObject("TripPatchManage.getTripPatchById", id);
			String sstart_time=request.getParameter("start_time");
			String send_time=request.getParameter("end_time");
			//时长
			long dateDiff=0;
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!"".equals(sstart_time) && !"".equals(send_time)){
				Date dstart=sf.parse(sstart_time);
				Date dend=sf.parse(send_time);
				dateDiff=(dend.getTime()-dstart.getTime())/(1000*60);//单位：分钟
			}
			tripPatch.setVehicle_vin(request.getParameter("vin"));
			tripPatch.setStart_time(request.getParameter("start_time"));
			tripPatch.setEnd_time(request.getParameter("end_time"));
			
			tripPatch.setUse_time(String.valueOf(dateDiff));
			String mileage=request.getParameter("mg");
			if("add".equals(mileage)){
				//如果此参数来自添加页面
				//得到人数
				int personNum=this.service.getCount("TripPatchManage.getPersonNum", tripPatch);
				//得到里程
				String smileage=(String)this.service.getObject("TripPatchManage.getMileage", tripPatch);
				tripPatch.setMileage(smileage);
				tripPatch.setCount(String.valueOf(personNum));
			}else{
				tripPatch.setMileage(mileage);
			}
			
		} catch (ParseException e) {
			this.log.error("格式化日期异常", e);
		} catch (BusinessException e) {
			this.log.error("查询里程或人数出现异常", e);
		}
		return SUCCESS;
	}
	/**
	 * 加载iframe页面（此页面通过iframe.src加载地图轨迹页面）
	 * 通过iframe的目的：完全卸载加载的地图
	 */
	public String loadIframe(){
		HttpServletRequest request=getCurrentRequest();
		request.setAttribute("vin",request.getParameter("vin"));
		request.setAttribute("start_time",request.getParameter("start_time"));
		request.setAttribute("end_time",request.getParameter("end_time"));
		String mg=request.getParameter("mg");
		//如果是来至添加页面
		if(mg==null){
			request.setAttribute("mileage","add");
		}else{
			//列表页面
			request.setAttribute("mileage",request.getParameter("mg"));
		}
		
		return SUCCESS;
	}
	/**
	 * 转到添加补录信息页面
	 */
	public String showAddPage(){
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		String yesterday=DateUtil.formatDateToString(c.getTime(), "yyyy-MM-dd");
		tripPatch.setStart_time(yesterday+" 00:00:00");
		tripPatch.setEnd_time(yesterday+" 00:00:00");
		tripPatch.setYesterday(yesterday+" 23:59:59");
		return SUCCESS;
	}
	/**
	 * 转到车辆选择页面
	 */
	public String chooseVehicle(){
		return SUCCESS;
	}
	/**
	 * 转到驾驶员选择页面
	 */
	public String chooseDriver(){
		return SUCCESS;
	}
	/**
	 * 转到线路选择页面
	 */
	public String chooseRoute(){
		return SUCCESS;
	}
	/**
	 * 临时派车补录保存到数据库 
	 */
	public String addTripPatch(){
		try {
			UserInfo currentUser =  getCurrentUser();
			tripPatch.setUpdate_by(currentUser.getUserName());
			this.service.insert("TripPatchManage.addTripPatch", tripPatch);
		} catch (BusinessException e) {
			this.addActionError("新增失败!");
			return ERROR;
		}
		//this.addActionMessage("新增成功!");
		this.message="新增成功!";
		return SUCCESS;
	}
	/**
	 * 删除
	 */
	public String delTripPatch(){
		HttpServletRequest request=getCurrentRequest();
		String ids=formatString(request.getParameter("ids"));
		try {
			this.service.delete("TripPatchManage.delTripPatch", ids);
			this.printWriter("<font color='green'>删除成功!</font>");
		} catch (BusinessException e) {
			this.log.error("删除临时派车补录异常", e);
			this.printWriter("<font color='red'>删除失败!</font>");
		}
		return null;
	}
	/**
	 * 查询车辆选择树
	 */
	public String getTreeNodes(){
		log.info("线路树初始化");
    	UserInfo currentUser = getCurrentUser();
        try {
        	treeNodesList = (List < VehicleTree >) service.getObjects("TripPatchManage.getTreeNodes", currentUser.getOrganizationID());
        	formatEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }
        return SUCCESS;
	}
	 /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfo() {
        try {
        	UserInfo currentUser =  getCurrentUser();
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise", map);
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : carnumbers) {
                // 已分配车辆
            	carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(VehicleTree singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    /**
     * 查询组织机构树
     */
    public String searchTreeNodes() {
    	log.info("组织树查询和刷新");
    	UserInfo currentUser = getCurrentUser();
    	HttpServletRequest request=getCurrentRequest();
    	String sname=request.getParameter("name");
    	if("".equals(sname)){
    		log.error("传入的查询参数为空");
    	}
    	Map<String,String> map = new HashMap<String,String>();
        try {
        	map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(sname, "utf-8")));
        	treeNodesList = (List<VehicleTree>) service.getObjects("TripPatchManage.searchTreeNodesByCode", map);
        	if(null == treeNodesList || treeNodesList.size() == 0) {
	        		// 按车牌查询无数据,查询组织
        		treeNodesList = (List<VehicleTree>) service.getObjects("TripPatchManage.searchTreeNodesByDivisionName", map);
        	}
            formatEnterpriseInfo();
        }catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    /**
     * 根据vin获得驾驶员信息
     */
    public void getDriverByVin(){
    	HttpServletRequest request=getCurrentRequest();
    	String vin=request.getParameter("vin");
    	try {
			String driver=(String)this.service.getObject("TripPatchManage.getDriverByVin", vin);
			if(driver == null){
				driver="";
			}
			this.printWriter(driver);
		} catch (BusinessException e) {
			log.error("根据vin获得驾驶员信息出错:" + e.getMessage());
		}
    }
    /**
     * 根据车辆vin和时间段判断发车行程里里是否存在发车记录
     */
    public void isExistTrip(){
    	HttpServletRequest request=getCurrentRequest();
    	String vin=request.getParameter("vin");
    	String start_time=request.getParameter("start_time");
    	String end_time=request.getParameter("end_time");
    	Map<String,String> param=new HashMap<String,String>();
    	param.put("vehicle_vin", vin);
    	param.put("start_time", start_time);
    	param.put("end_time", end_time);
    	param.put("compareTime", start_time);
    	try {
    		//查询开始时间是否存在发车记录冲突
			int start=this.service.getCount("TripPatchManage.getExistTripCount", param);
			//查询结束时间是否存在发车记录冲突
			param.put("compareTime", end_time);
			int end=this.service.getCount("TripPatchManage.getExistTripCount", param);
			//存在发车记录时间冲突
			if(start>0 || end>0){
				this.printWriter("success");
			}
		} catch (BusinessException e) {
			log.error("根据vin获得发车行程里里是否存在发车记录信息出错:" + e.getMessage());
		}
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
     * 转换Map
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPagination(List<TripPatch> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	TripPatch info=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getPatch_id());
            cellMap.put("cell", new Object[] {
            		info.getPatch_id(),
            		info.getVehicle_code(),
            		info.getVehicle_ln(),
            		info.getDriver_name(),
            		info.getType(),
            		info.getRoute_name(),
            		info.getRoute_type(),
            		info.getStart_time(),
            		info.getEnd_time(),
            		info.getMileage(),
            		info.getCount(),
            		info.getUpdate_by(),
            		info.getOperate_time(),
            		info.getVehicle_vin()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
}

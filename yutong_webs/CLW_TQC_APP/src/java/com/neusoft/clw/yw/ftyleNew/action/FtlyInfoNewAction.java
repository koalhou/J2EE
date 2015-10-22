/**
 * 
 */
package com.neusoft.clw.yw.ftyleNew.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.util.StringUtils;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.action.gpsUtil.SearchGisAreaByCode;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yw.engine.ds.VehicleRegisterInfo;
import com.neusoft.clw.yw.ftly.ds.ComparatorFtlyInfo;
import com.neusoft.clw.yw.ftly.ds.ZsptFtlyInfo;
import com.neusoft.clw.yw.ftly.service.FtlyInfoService;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 *类描述：油量监控类
 * 
 * @author: Chang ZhengWu
 *@date： 日期：2012-6-5 时间：下午01:56:11
 *@version 1.0
 */
public class FtlyInfoNewAction extends PaginationAction {
	protected Logger logger = Logger.getLogger(FtlyInfoNewAction.class);
	private transient Service service;
	private String organizationId;
	private String vehicle_vin;
	private String vehicle_ln;
	private String optpageid;
	private String oilbox_state;
	private String gridflag;
	private String chooseorgid;
	private String cdeal_flag;
	private String xmlDoc;
	private String ftlyInfo;
	private VehicleRegisterInfo vehicleInfo;
	private String bigPicSrc;
	private List <ZsptFtlyInfo> ftlyInfoList =  new ArrayList < ZsptFtlyInfo >();
	private Map ftlyInfoMap = new HashMap();
	private Map mapData = new LinkedHashMap();
	private InputStream excelStream;
	private FtlyInfoService ftlyInfoService;
	
	private String exprotFileName;
	
	private String operateDesc;//解除告警备注内容
    private String isValid;//是否解除告警 0: 未解除、1:解除 
    private String ftlyId;
	private String returnPointStr;
	
    public String getReturnPointStr() {
		return returnPointStr;
	}

	public void setReturnPointStr(String returnPointStr) {
		this.returnPointStr = returnPointStr;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
    
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    
	// 防透漏由左侧树加载
	public String blankAction() {
		ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,"->油箱油量监控->油箱监控");
		ActionContext.getContext().getSession().put("pageLocation","oilMonitor");
		return SUCCESS;
	}
	

	// 仪表盘分页查询，获取全部数据，包含无设备
	public String init() {
		String pageIndex = getRequest().getParameter("page");
		String rpNum = getRequest().getParameter("rp");
		
		int page=Integer.parseInt(pageIndex);
		int rp=Integer.parseInt(rpNum);
		
		String vehicleVinList = getRequest().getParameter("vehicleVinList");
		
		Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat(
        "yyyyMMdd");
		String currTime = inputFormat.format(currentTime);
		System.out.println("FTLY_INFO_"+currTime);
		String partionTime = "FTLY_INFO_"+currTime;
		
		calendar.roll(Calendar.DATE, -3);
		Date dd = calendar.getTime();
		String endTime = inputFormat.format(dd);
		
		if(StringUtils.hasText(vehicleVinList)){
			try {
				Map<String, Object> map = new HashMap<String, Object>();
	
				map.put("vehicleVin", StringUtil.toStringList(vehicleVinList));
//				map.put("ent_id", this.getCurrentUser().getEntiID());
				map.put("ent_id", this.getCurrentUser().getOrganizationID());
				map.put("enterprise_id", this.getCurrentUser().getEntiID());
				map.put("type", getRequest().getParameter("type"));
				map.put("partionTime", partionTime);
//				map.put("start_time", currTime);
//				map.put("end_time", endTime+" 23:59:59");
				int totalCount = 1;
				totalCount = service.getCount("ZsptFtlyinfoNew.getLastFtlyInfoGroupedCount",map);
				int tmpVal = 1;
//				if(totalCount/rp < page*rp){
					tmpVal = totalCount/rp == 0? totalCount/rp: totalCount/rp+1;
//				}
				if(tmpVal < page && tmpVal != 0){
					page = tmpVal;					
				}
				map.put("rowEnd", page*rp);
				map.put("rowStart", (page-1)*rp);
				ftlyInfoList = service.getObjects("ZsptFtlyinfoNew.getLastFtlyInfoGrouped", map );
				
				if (ftlyInfoList == null && ftlyInfoList.size() == 0) {
					addActionMessage(getText("common.no.data"));
				}

				ftlyInfoMap.put("page", page);// 从前台获取当前第page页
				ftlyInfoMap.put("total", totalCount);// 从数据库获取总记录数
				ftlyInfoMap.put("rows", ftlyInfoList);
			} catch (BusinessException e) {
				super.addActionError(getText("info.db.error"));
				log.error("Query registered vehicles error:" + e.getMessage(),e);
				return ERROR;
			} catch (Exception e) {
				super.addActionError(getText("info.db.error"));
				log.error("Query registered vehicles error:" + e.getMessage(),e);
				return ERROR;
			}
		}
		this.organizationId = null;
		return SUCCESS;
	}
/**
 * 获取所有车辆的状态 
 * @return
 */
	public String getAllCarState() {

		String vehicleVinList = getRequest().getParameter("vehicleVinList");
		
//		if(StringUtils.hasText(vehicleVinList)){
			try {
				Map<String, Object> map = new HashMap<String, Object>();
	
				map.put("vehicleVin", StringUtil.toStringList(vehicleVinList));
				map.put("ent_id", this.getCurrentUser().getEntiID());
				ftlyInfoList = service.getObjects("ZsptFtlyinfoNew.getAllCarState", map );
				
				if (ftlyInfoList == null && ftlyInfoList.size() == 0) {
					addActionMessage(getText("common.no.data"));
				}
				 this.ftlyInfoMap.put("rows", ftlyInfoList);
	
			} catch (BusinessException e) {
				super.addActionError(getText("info.db.error"));
				log.error("Query registered vehicles error:" + e.getMessage(),e);
				return ERROR;
			} catch (Exception e) {
				super.addActionError(getText("info.db.error"));
				log.error("Query registered vehicles error:" + e.getMessage(),e);
				return ERROR;
			}
//		}
		return SUCCESS;
	}


	
	public Map getPaginationForAddOil(List<ZsptFtlyInfo> ftlyInfoList2, int totalCount,
			String pageIndex, String rpNum) 
	{
		List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        if(ftlyInfoList2!=null){
	        for (int i = 0; i < ftlyInfoList2.size(); i++) {
	        	
	        	ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo) ftlyInfoList2.get(i);
	        	double oilMass = 0;
				java.text.DecimalFormat   df=new   java.text.DecimalFormat("####.##");
	        	if(ftlyInfo.getOilboxState().equals("001")){
					oilMass = Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass());
				} else if(ftlyInfo.getOilboxState().equals("010")){
					oilMass = Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill());
				}
	        	
	            Map cellMap = new LinkedHashMap();
	
		        	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
		        	cellMap.put("cell", 
		        		new Object[] 
		        		{
		        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
		        			ftlyInfo.getVehicle_code(),
		        			ftlyInfo.getVehicleLn(),
		        			StringUtil.doubleToString2(oilMass),
		        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
		        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
		        			ftlyInfo.getOilboxState().equals("001")?"骤减":"加油",
		        			ftlyInfo.getReportTimeString(),
		        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename()
		        		});
	          
	            mapList.add(cellMap);
	        }
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    
	}
	public Map getPaginationForOilWear(List<ZsptFtlyInfo> ftlyInfoList2, int totalCount,
			String pageIndex, String rpNum) 
	{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		
		if(ftlyInfoList2!=null){
			for (int i = 0; i < ftlyInfoList2.size(); i++) {
				
				ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo) ftlyInfoList2.get(i);
				
				Map cellMap = new LinkedHashMap();

				cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
				cellMap.put("cell", 
						new Object[] 
								{
						(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
						ftlyInfo.getVehicle_code(),
						ftlyInfo.getVehicleLn(),
						ftlyInfo.getUsedOil(),
						ftlyInfo.getMileage(),
						ftlyInfo.getTotalOilPrice(),
						ftlyInfo.getReportTimeString()});
				mapList.add(cellMap);
			}
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		
		return mapData;
		
	}
	/**
	 * 获取某段时间的加油记录，并生成图片生成数据，返回告警的地图数据
	 * 
	 * @return
	 */
	// 生成图片
	public String generateImg() {
		String vehicle_ln = getRequest().getParameter("vehicle_ln");// 车牌号
		String vehicleVin = getRequest().getParameter("vehicle_vin");

		String start_time = getRequest().getParameter("start_time");
		String end_time = getRequest().getParameter("end_time");
		Map map = new HashMap<String, String>();
		map.put("vehicleVin", vehicleVin);
		map.put("vehicleLn", vehicle_ln);
//		map.put("startDate_time", start_time);
//		map.put("endDate_time", end_time.split(" ")[0]+" 23:59:59");
		map.put("oilMassTrue", "true");
		
		String[] startArr = start_time.split(" ")[0].split("-");
		//获取两日期间相差的天数
		long ddb = DateUtil.getQuot(end_time,start_time)+1;		
		map.put("diffDay", ddb);
		//获取两日期之间的日期数组
		List tmp = getDateArr(startArr,ddb);
		if(null != tmp && tmp.size() != 0){
			map.put("dateArray",tmp);
		}
		
		if (organizationId == null || organizationId.length() < 1)
			map.put("organizationId", getCurrentUser().getOrganizationID());
		List<ZsptFtlyInfo> list = new ArrayList<ZsptFtlyInfo>();
		List<ZsptFtlyInfo> listAlarm = new ArrayList<ZsptFtlyInfo>();
		
		String chart = null;
		try {
//			long s = System.currentTimeMillis();
			list = service.getObjects("ZsptFtlyinfoNew.getFtlyInfoList",map);
			ComparatorFtlyInfo comparator=new ComparatorFtlyInfo();
			Collections.sort(list,comparator);
			/* 查询加油、透油告警信息 并经纬度数据根据日期前推6.5分钟 */
//			listAlarm = service.getObjects("ZsptFtlyinfoNew.getFtlyChars", map);
//			list.addAll(listAlarm);
    		if(list!=null&&list.size()>0) {
    			chart = createChart(list);
    		}
		} catch (BusinessException e1) {
			logger.error("", e1);
		}
		
		try {
			if(chart==null)
				xmlDoc = "null";
			else
				xmlDoc = chart;
			
    		StringBuffer bString = new StringBuffer();
    		
    		int i=0;
    		
    		map.put("start_time", start_time);
    		map.put("end_time", end_time);
    		
    		list = service.getObjects("ZsptFtlyinfoNew.getFtlyChars", map);
    		
    		gpsUtil gpsutil = new gpsUtil();
    		
    		list = gpsutil.getpostFtly(list);
    		for(ZsptFtlyInfo ftlyInfo : list) {
    			if(!"0.0".equals(ftlyInfo.getLatitude())&&!"0.0".equals(ftlyInfo.getLongitude())&&!"0".equals(ftlyInfo.getLongitude())&&("001".equals(ftlyInfo.getOilboxState())||"010".equals(ftlyInfo.getOilboxState()))) {
    				if(i<200) {
    					bString.append(ftlyInfo.getLatitude()+","+ftlyInfo.getLongitude()+","+ftlyInfo.getOilboxState()+"-");
    					i++;
    				} else {
    					break;
    				}
    			}
    			//System.out.println("经度："+ ftlyInfo.getOilboxState() + "///" + ftlyInfo.getLongitude() + "  ---- 纬度：" + "////"+ ftlyInfo.getLatitude());
    		}
    		ftlyInfo = bString.append(vehicle_ln).toString();
		} catch (NumberFormatException e) {
			logger.error("", e);
		} 
		catch (BusinessException e1) {
			logger.error("", e1);
		}
		xmlDoc = "[{\"chartData\": \"" + xmlDoc + "\"},{\"ftlyInfo\": \"" + ftlyInfo + "\"}]";
		
		return SUCCESS;
	}

	private CategoryDataset getDataSet(List<String[]> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String[] ss : data) {
			dataset.addValue(Double.valueOf(ss[0]), ss[1], ss[2]);
		}
		return dataset;
	}
	
	
	public List getDateArr(String[] startArr,long ddb){
		List dateArr = new ArrayList();
		Calendar c = Calendar.getInstance();
		//c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY)-2);
		c.set(Calendar.YEAR,Integer.parseInt(startArr[0]));
		c.set(Calendar.MONDAY, Integer.parseInt(startArr[1])-1);
		c.set(Calendar.DATE, Integer.parseInt(startArr[2]));
		for(int i = 1;i <= ddb; i++){
			String year = String.valueOf(c.get(Calendar.YEAR));    //获取年
			String month = String.valueOf(c.get(Calendar.MONTH)+1);   //获取月份，0表示1月份
			String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    //获取当前天数
			if(day.length() == 1){
				day = "0"+day;
			}
			dateArr.add(year+"-"+month+"-"+day);
			c.add(Calendar.DATE, 1);
		}
		
		if(dateArr.size() != 0){
			return dateArr;
		}
		return null;
	}
	
	/**
	 * 生成折线图数据
	 * @TODO when i=1  10/01  
	 * @param dataList
	 * @return
	 */
	//时序图类
	private String createChart(List<ZsptFtlyInfo> dataList) {
		StringBuffer data = new StringBuffer();
		data.append("<chart>");
		
		StringBuffer dataDate = new StringBuffer();
		StringBuffer dataAll = new StringBuffer();
		StringBuffer dataAdd = new StringBuffer();
		StringBuffer dataStore = new StringBuffer();
		int l=0;
		int m=0;
		int n=0;
		int v=0;
		
		dataDate.append("<series>");
		//dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(0).getReportTimeString()+"</value>");
		dataAll.append("<graphs>");
		dataAll.append("<graph gid='0' title='油量消耗' color='#0000ff' line_width='2' >");
//		dataAll.append("<value xid='"+(m++)+"'>"+dataList.get(0).getOilboxMass()+"</value>");
		dataAdd.append("<graph gid='1' title='油量增加' color='#00cc00' line_width='2' >");
//		dataAdd.append("<value xid='"+(n++)+"'></value>");
		dataStore.append("<graph gid='2' title='油量减少' color='#ff0000' line_width='2'>");
//		dataStore.append("<value xid='"+(v++)+"'/>");
		int j = 0;
		for (int i=0;i<dataList.size();i++) {
			//如果是偷油
			if ("001".equals(dataList.get(i).getOilboxState())) {
				if(i>0) {
					dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(i).getReportTimeString()+"</value>");
					dataAll.append("<value xid='"+(m++)+"'>"+(Double.parseDouble(dataList.get(i-1).getOilboxMass())) +"</value>");//+Double.parseDouble(dataList.get(i).getAddOill())
					dataAdd.append("<value xid='"+(n++)+"'></value>");
					dataStore.append("<value xid='"+(v++)+"'>"+(Double.parseDouble(dataList.get(i-1).getOilboxMass())) +"</value>");//+Double.parseDouble(dataList.get(i).getAddOill())
					
					dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(i).getReportTimeString()+"</value>");
					dataAll.append("<value xid='"+(m++)+"'>"+dataList.get(i).getOilboxMass()+"</value>");
					dataAdd.append("<value xid='"+(n++)+"'></value>");
					dataStore.append("<value xid='"+(v++)+"'>"+dataList.get(i).getOilboxMass()+"</value>");
				
				}
			} else if("010".equals(dataList.get(i).getOilboxState())){
				if(i>0) {
					dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(i).getReportTimeString()+"</value>");
					dataAll.append("<value xid='"+(m++)+"'>"+(Double.parseDouble(dataList.get(i-1).getOilboxMass())) +"</value>");//-Double.parseDouble(dataList.get(i).getAddOill())
					dataAdd.append("<value xid='"+(n++)+"'>"+(Double.parseDouble(dataList.get(i-1).getOilboxMass())) +"</value>");//-Double.parseDouble(dataList.get(i).getAddOill())
					dataStore.append("<value xid='"+(v++)+"'></value>");
					
					dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(i).getReportTimeString()+"</value>");
					dataAll.append("<value xid='"+(m++)+"'>"+dataList.get(i).getOilboxMass()+"</value>");
					dataAdd.append("<value xid='"+(n++)+"'>"+dataList.get(i).getOilboxMass()+"</value>");
					dataStore.append("<value xid='"+(v++)+"'></value>");
				}

			} else {
				dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(i).getReportTimeString()+"</value>");
				dataAll.append("<value xid='"+(m++)+"'>"+dataList.get(i).getOilboxMass()+"</value>");
				dataAdd.append("<value xid='"+(n++)+"'></value>");
				dataStore.append("<value xid='"+(v++)+"'></value>");
			}
		}
		dataDate.append("<value xid='"+(l++)+"'>"+dataList.get(dataList.size()-1).getReportTimeString()+"</value>");
		dataDate.append("</series>");
		dataAll.append("<value xid='"+(m++)+"'>"+dataList.get(dataList.size()-1).getOilboxMass()+"</value>");
		dataAll.append("</graph>");
		dataAdd.append("<value value='"+(n++)+"' />");
		dataAdd.append("</graph>");
		dataStore.append("<value value='"+(v++)+"' />");
		dataStore.append("</graph>");
		dataStore.append("</graphs>");
		
		data.append(dataDate.toString());
		data.append(dataAll.toString());
		data.append(dataAdd.toString());
		data.append(dataStore.toString());
		data.append("</chart>");
		//System.out.println(data.toString());
		return data.toString();
	}
	
	/**合并到上面加载图片数据中**/
	public String loadBigMap() {
    	Map map = new HashMap();
    	String vin = getRequest().getParameter("vin");
    	String startDate = null;
    	String endDate = null;
    	try {
    		startDate = java.net.URLDecoder.decode(getRequest().getParameter("startDate"),"utf-8");
    		endDate = java.net.URLDecoder.decode(getRequest().getParameter("endDate"),"utf-8");
    		String vehicleLn = java.net.URLDecoder.decode(getRequest().getParameter("vehicleLn"),"utf-8");
    		map.put("vehicleVin",vin);
    		map.put("start_time",startDate);
    		map.put("end_time",endDate);
    		map.put("oilboxStateValueOr","true");
    		map.put("organizationId", getCurrentUser().getOrganizationID());
    		ftlyInfoList = service.getObjects("ZsptFtlyinfoNew.getFtlyInfoList", map);
    		StringBuffer bString = new StringBuffer();
    		int i=0;
    		for(ZsptFtlyInfo ftlyInfo : ftlyInfoList) {
    			if(!"0.0".equals(ftlyInfo.getLatitude())&&!"0.0".equals(ftlyInfo.getLongitude())&&!"0".equals(ftlyInfo.getLongitude())) {
    				if(i<150) {
    					bString.append(ftlyInfo.getLatitude()+","+ftlyInfo.getLongitude()+","+ftlyInfo.getOilboxState()+"-");
    					i++;
    				} else {
    					break;
    				}
    			}
    		}
    		ftlyInfo = bString.append(vin).toString();//bString.append(vehicleLn).toString();
    		xmlDoc = "[{\"ftlyInfo\": \"" + ftlyInfo + "\"}]";
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		} catch (BusinessException e) {
			logger.error("", e);
		}
			
    	
    	return SUCCESS;
    }
	/**
	 * 将空值转换为空字符串
	 *
	 * @param str
	 *            字符串
	 * @return String 返回处理后的字符串
	 */
	private static String nullToStr(String str) {
		return str == null || str.equals("null") ? "" : str.trim();
	}

	/**
	 * 将空值转换为空字符串
	 *
	 * @param dateStr
	 *            日期字符串
	 * @return Date
	 */
	private static Date getDateFormat(String dateStr) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(dateStr);
		} catch (Exception ex) {
		}
		return date;
	}
	public String showFtlyBigPic() {
		String vehicleVin = getRequest().getParameter("vehicle_vin");
		try {
			String start_time = java.net.URLDecoder.decode(getRequest().getParameter("start_time"),"utf-8");
			String end_time = java.net.URLDecoder.decode(getRequest().getParameter("end_time"),"utf-8");
			Map map = new HashMap<String, String>();
			map.put("vehicleVin", vehicleVin);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("oilMassTrue", "true");
			map.put("pt", "zspt");
//			map.put("pt", getCurrentUser().getPtFlag());
			
			map.put("organizationId", getCurrentUser().getOrganizationID());

			List<ZsptFtlyInfo> list = null;
			list = service.getObjects("ZsptFtlyinfoNew.getFtlyInfoList",map);
			if(list!=null&&list.size()>0) {
				for(int i=0;i<list.size();i++) {
					list.get(i).setElevation(String.valueOf(i));
				}
    			ActionContext.getContext().getSession().put("ftlyList", list);
    		}
		} catch (BusinessException e) {
			logger.error("", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		return SUCCESS;
	}

	public String showFtlyBigPicAddStore() {
		return SUCCESS;
	}
	
	public void showFtlyBigPicAddStoreInfo() {
		String vehicleVin = getRequest().getParameter("vehicle_vin");
		try {
			String start_time = java.net.URLDecoder.decode(getRequest().getParameter("start_time"),"utf-8");
			String end_time = java.net.URLDecoder.decode(getRequest().getParameter("end_time"),"utf-8");
			Map map = new HashMap<String, String>();
			map.put("vehicleVin", vehicleVin);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("oilMassTrue", "true");
			map.put("pt", "zspt");
			map.put("organizationId", getCurrentUser().getOrganizationID());

			List<ZsptFtlyInfo> list = null;
			list = service.getObjects("ZsptFtlyinfoNew.getFtlyInfoList",map);
			if(list!=null&&list.size()>0) {
				String chart = createChart(list);
    			printWriter(chart);
    		}
		} catch (BusinessException e) {
			logger.error("", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
	}
	//查询油量变动列表初始化
	public String beforeOilDetails(){
		vehicleInfo.setVehicleVin(null);
		vehicleInfo.setVehicleLn(null);
		vehicleInfo.setStartDate(null);
		vehicleInfo.setEndDate(null);
		return SUCCESS;
	}
	
	//查询油量变动列表
	public String getStealOilList(){
		String rpNum = "";
		String pageIndex = "";

		pageIndex = getRequest().getParameter("page");
		rpNum = getRequest().getParameter("rp");

		String sortName = getRequest().getParameter("sortname");
		String sortOrder = getRequest().getParameter("sortorder");
		
		String vehicle_vin = getRequest().getParameter("vehicle_vin");
		
		String oilboxState=getRequest().getParameter("oilbox_state");

		String start_time = getRequest().getParameter("start_time");
		String end_time = getRequest().getParameter("end_time");
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start_time", start_time);
		map.put("end_time", end_time);

		map.put("sortname", sortName);
		map.put("sortorder", sortOrder);

		map.put("oilbox_state", StringUtil.toStringList(oilboxState));
		map.put("vehicle_vin", StringUtil.toStringList(vehicle_vin));
		List<ZsptFtlyInfo> list= null;
		
		int total=0;
		if(StringUtil.toStringList(vehicle_vin)!=null){
		try {
			total = service.getCount("ZsptFtlyinfoNew.getOilStateChangeCount",map);
			list = service.getObjectsByPage("ZsptFtlyinfoNew.getOilStateChangeList",map,(Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
		} catch (BusinessException e) {
			logger.error("查询油量告警异常",e);
		}
		}
		mapData = getPaginationForAddOil(list,total,pageIndex,rpNum);
        
        return SUCCESS;
	}
	/**
	 * 导出油量变动记录
	 * @return
	 */
	public String exportAddExcel()throws UnsupportedEncodingException{
		String modelTitle = getText("油量变动记录");
		String vehicle_vin = getRequest().getParameter("vehicle_vin");
		String oilboxState=getRequest().getParameter("oilbox_state");
		String start_time = getRequest().getParameter("start_time")+" 00:00:00";
		String end_time = getRequest().getParameter("end_time")+" 23:59:59";

		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start_time", start_time);
		map.put("end_time", end_time);

		map.put("sortname", "report_time");
		map.put("sortorder", "desc");

		map.put("oilbox_state", StringUtil.toStringList(oilboxState));
		map.put("vehicle_vin", StringUtil.toStringList(vehicle_vin));
		List<ZsptFtlyInfo> list= null;
		
		if(StringUtil.toStringList(vehicle_vin)!=null){
		try {
			list = service.getObjects("ZsptFtlyinfoNew.getOilStateChangeList",map);
			excelStream = genAddExcelInputStream(list);
		} catch (Exception e) {
			logger.error("导出油量告警异常",e);
		}}
		List < ZsptFtlyInfo > exportlist = new ArrayList < ZsptFtlyInfo >();
		double oilMass = 0;
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("####.##");
    	
		 for (int i = 0; i < list.size(); i++) {
			 	ZsptFtlyInfo oilexport = new ZsptFtlyInfo();
			 	ZsptFtlyInfo oilused = list.get(i);
			 	if(oilused.getOilboxState().equals("001")){
			 		oilMass = Double.valueOf(oilused.getAddOill())+Double.valueOf(oilused.getOilboxMass());
			 	} else if(oilused.getOilboxState().equals("010")){
			 		oilMass = Double.valueOf(oilused.getOilboxMass())-Double.valueOf(oilused.getAddOill());
			 	}
	        	oilexport.setVehicle_code(oilused.getVehicle_code());
	            oilexport.setVehicleLn(oilused.getVehicleLn());
	            oilexport.setOilboxLevel(StringUtil.doubleToString2(oilMass));
	            oilexport.setAddOill(oilused.getAddOill());
	            oilexport.setOilboxMass(oilused.getOilboxMass());
	            oilexport.setOilboxState(oilused.getOilboxState().equals("001")?"骤减":"加油");
	            
	            //ftlyInfo.getOilBoxState().equals("001")?"骤减":"加油",
	            
	            		
	            oilexport.setReportTimeString(oilused.getReportTimeString());
	            oilexport.setZonename((oilused.getZonename()==null || "".equals(oilused.getZonename()))?"定位无效":oilused.getZonename());
	            exportlist.add(oilexport);
	        }
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "HumanBadDrive.xls";

			// add by liuja start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by liuja stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("油量变动记录("+start_time+ "~"+end_time+
					//timeStr+
					")");
			if(list == null || list.size()<1){
            	list.add(new ZsptFtlyInfo());
            }

		    excelExporter.putAutoExtendSheets("exportFtlyInfoWear", 0,
		    		exportlist);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
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

		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String datetimestr = pathDf.format(calendar.getTime());
		// 设置下载文件属性
		String fileName = URLEncoder.encode("油量变动记录","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition",
				"attachment;filename="+fileName+"-" +DateUtil.getCurrentDayTime()+ ".xls");
		response.setContentType("application/msexcel; charset=\"utf-8\"");
		
		
		//excelStream = genAddExcelInputStream(list);
		//exprotFileName = fileName+"-"+DateUtil.formatDateToString(new Date(),"yyyyMMddHHmmss")+".xls";

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
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
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
			//this.addOperationLog(formatLog(modelTitle, null));
			 // 设置操作描述
            this.addOperationLog("发车统计导出");
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.CLW_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_EXP);
		}
		log.info(modelTitle + "结束");
		// 导出文件成功
		return "excel";
	}
	
	/**
	 * 油量变动记录导出
	 * @param dataList
	 * @return
	 */
	public InputStream genAddExcelInputStream(List dataList) {
		
		jxl.write.Label label;
		WritableWorkbook workbook;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet("oil-changed", 0);
			
			sheet.setColumnView(0,13); //调整第一列宽度    
			sheet.setColumnView(1,20); //调整第二列宽度    
			sheet.setColumnView(2,18); //调整第三列宽度    
			sheet.setColumnView(3,15); //调整第四列宽度    
			sheet.setColumnView(4,15); //调整第五列宽度    
			sheet.setColumnView(5,15); //调整第六列宽度    
			sheet.setColumnView(6,15); //调整第六列宽度    
			
			label = new jxl.write.Label(0, 0, "班车号");
			sheet.addCell(label);
			
			label = new jxl.write.Label(1, 0, "车牌号");
			sheet.addCell(label);
			
			label = new jxl.write.Label(2, 0, "变动前油量(L)");
			sheet.addCell(label);
			
			label = new jxl.write.Label(3, 0, "变动油量(L)");
			sheet.addCell(label);
			
			label = new jxl.write.Label(4, 0, "变动后油量(L)");
			sheet.addCell(label);
			
			label = new jxl.write.Label(5, 0, "变动形式");
			sheet.addCell(label);
			
			label = new jxl.write.Label(6, 0, "变动时间");
			sheet.addCell(label);
			
			label = new jxl.write.Label(7, 0, "位置");
			sheet.addCell(label);
			
			
			for(int i=0;i<dataList.size();i++){
				
				ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo)dataList.get(i);
					
					jxl.write.WritableFont font = new  jxl.write.WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);    
					jxl.write.WritableCellFormat backgroud = new  jxl.write.WritableCellFormat(font);    
					
					label = new jxl.write.Label(0, i+1, ftlyInfo.getVehicle_code(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(1, i+1, ftlyInfo.getVehicleLn(),backgroud);
					sheet.addCell(label);
					
					double oilMass = 0;
					java.text.DecimalFormat   df=new   java.text.DecimalFormat("####.##");   

					if(ftlyInfo.getOilboxState().equals("001")){
						oilMass = Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass());
					} else {
						oilMass = Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill());
					}
					
					label = new jxl.write.Label(2, i+1, df.format(oilMass),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(3, i+1, Double.valueOf(ftlyInfo.getAddOill()).toString(),backgroud);
					sheet.addCell(label);
					
					
					label = new jxl.write.Label(4, i+1, Double.valueOf(ftlyInfo.getOilboxMass()).toString(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(5, i+1, ftlyInfo.getOilboxState().equals("001")?"骤减":"加油",backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(6, i+1, ftlyInfo.getReportTimeString(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(7, i+1, (ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),backgroud);
					sheet.addCell(label);
					
			}
			
			workbook.write();
			workbook.close();
			
			return new ByteArrayInputStream(out.toByteArray());   
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return null;
	}
	/**
	 * 查询油耗列表初始化
	 * @return
	 */
	public String initOilWearList(){
		vehicleInfo.setVehicleVin(null);
		vehicleInfo.setVehicleLn(null);
		vehicleInfo.setStartDate(DateUtil.getMonthFirstDay1());
		vehicleInfo.setEndDate(DateUtil.getCurrentDay());
		return SUCCESS;
	}
	
	/**
	 * 查询油耗变动列表
	 * @return
	 */
	public String getOilWearList(){
		String rpNum = "";
		String pageIndex = "";
		
		pageIndex = getRequest().getParameter("page");
		rpNum = getRequest().getParameter("rp");
		
		String sortName = getRequest().getParameter("sortname");
		String sortOrder = getRequest().getParameter("sortorder");
		
		String vehicle_vin = getRequest().getParameter("vehicle_vin");
		
		String start_time = getRequest().getParameter("start_time");
		String end_time = getRequest().getParameter("end_time");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		
		map.put("sortname", sortName);
		map.put("sortorder", sortOrder);
		
		map.put("vehicle_vin", StringUtil.toStringList(vehicle_vin));
		List<ZsptFtlyInfo> list= null;
		
		int total=0;
		if(StringUtil.toStringList(vehicle_vin)!=null){
			try {
				total = service.getCount("ZsptFtlyinfoNew.getOilWearCount",map);
				list = service.getObjectsByPage("ZsptFtlyinfoNew.getOilWearList",map,(Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			} catch (BusinessException e) {
				logger.error("查询油耗异常",e);
			}
		}
		mapData = getPaginationForOilWear(list,total,pageIndex,rpNum);
		
		return SUCCESS;
	}
	
	/**
	 * 导出油耗记录
	 * @return
	 */
	public String exportOilWearExcel(){
		String vehicle_vin = getRequest().getParameter("vehicle_vin");
		String start_time = getRequest().getParameter("start_time");
		String end_time = getRequest().getParameter("end_time");
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("sortname", "totalOilPrice");
		map.put("sortorder", "desc");
		map.put("vehicle_vin", StringUtil.toStringList(vehicle_vin));
		List<ZsptFtlyInfo> list= null;
		
		if(StringUtil.toStringList(vehicle_vin)!=null){
		try {
			String fileName = URLEncoder.encode("油耗统计","UTF8");
			list = service.getObjects("ZsptFtlyinfoNew.getOilWearList",map);
			excelStream = genOilWearExcelInputStream(list,start_time,end_time);
			exprotFileName = fileName + "-"+DateUtil.formatDateToString(new Date(),"yyyyMMddHHmmss")+".xls";
		} catch (Exception e) {
			logger.error("导出油耗异常",e);
		}}
		return "excel";
	}
	/**
	 * 油量变动记录导出
	 * @param dataList
	 * @return
	 */
	public InputStream genOilWearExcelInputStream(List dataList,String startTime,String endTime) {
		
		jxl.write.Label label;
		WritableWorkbook workbook;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = workbook.createSheet("油耗统计", 0);
			
			sheet.mergeCells(0,0,4,0);
			
			jxl.write.WritableFont fontt = new  jxl.write.WritableFont(WritableFont.createFont("楷体_GB2312"), 16 ,WritableFont.BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);    
			jxl.write.WritableCellFormat backgroudt = new  jxl.write.WritableCellFormat(fontt);
			backgroudt.setAlignment(jxl.format.Alignment.CENTRE);
			backgroudt.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);



			
			label = new jxl.write.Label(0, 0, "油耗统计信息     ("+startTime + "~" + endTime+")",backgroudt);
			sheet.addCell(label);
			
			sheet.setColumnView(0,13); //调整第一列宽度 
			sheet.setColumnView(1,20); //调整第二列宽度    
			sheet.setColumnView(2,18); //调整第三列宽度    
			sheet.setColumnView(3,15); //调整第四列宽度    
			sheet.setColumnView(4,15); //调整第五列宽度    
			sheet.setColumnView(5,15); //调整第六列宽度    
			sheet.setColumnView(6,15); //调整第六列宽度    
			
			
			label = new jxl.write.Label(0, 1, "班车号");
			sheet.addCell(label);
			
			label = new jxl.write.Label(1, 1, "车牌号");
			sheet.addCell(label);
			
			label = new jxl.write.Label(2, 1, "燃油消耗(L)");
			sheet.addCell(label);
			
			label = new jxl.write.Label(3, 1, "行驶里程(KM)");
			sheet.addCell(label);
			
			label = new jxl.write.Label(4, 1, "燃油价格(元)");
			sheet.addCell(label);
			
			int j = 1;
			for(int i=0;i<dataList.size();i++){
				
				ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo)dataList.get(i);
					
					jxl.write.WritableFont font = new  jxl.write.WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);    
					jxl.write.WritableCellFormat backgroud = new  jxl.write.WritableCellFormat(font);    
					
					label = new jxl.write.Label(0, j+1, ftlyInfo.getVehicle_code(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(1, j+1, ftlyInfo.getVehicleLn(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(2, j+1, ftlyInfo.getUsedOil(),backgroud);
					sheet.addCell(label);
					
					label = new jxl.write.Label(3, j+1, ftlyInfo.getMileage(),backgroud);
					sheet.addCell(label);
					
					
					label = new jxl.write.Label(4, j+1, ftlyInfo.getTotalOilPrice(),backgroud);
					sheet.addCell(label);
					j++;
			}
			
			workbook.write();
			workbook.close();
			
			return new ByteArrayInputStream(out.toByteArray());   
		} catch (Exception e) {
			logger.error("", e);
		}
		
		return null;
	}
	/**
	 * 查询偷油信息(包含驾驶员信息,不分页)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getStealAlarmList(){
		String rpNum = "";
		String pageIndex = "";
		String isPage = "";
		
		isPage = getRequest().getParameter("isPage");
		
		pageIndex = getRequest().getParameter("page");
		rpNum = getRequest().getParameter("rp");

		String sortName = getRequest().getParameter("sortname");
		String sortOrder = getRequest().getParameter("sortorder");
		
		String vehicle_vin = getRequest().getParameter("vehicle_vin");

		String oilbox_state = getRequest().getParameter("oilbox_state");
		
		String ent_id = this.getCurrentUser().getOrganizationID();
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("sortname", sortName);
		map.put("sortorder", sortOrder);
	
		map.put("vehicle_vin", vehicle_vin);
		map.put("oilbox_state", oilbox_state);
		map.put("ent_id", ent_id);
		map.put("enterprise_id", this.getCurrentUser().getEntiID());
		List<ZsptFtlyInfo> list= null;
		int total=0;
		try {
			//total = service.getCount("ZsptFtlyinfoNew.getStealAlarmListCount",map);
			list = service.getObjectsByPage("ZsptFtlyinfoNew.getStealAlarmList",map,(Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			mapData.put("returnStr", "success");
			mapData.put("ftlyInfo", list);
		} catch (BusinessException e) {
			logger.error("", e);
			mapData.put("returnStr", "error");
		}
//		mapData = getPaginationForAlarm(list,total,pageIndex,rpNum,oilbox_state);
        return SUCCESS;
	}
	
	//查询偷油信息(包含驾驶员信息,透油告警与加油地点告警，不分页)
	public String getStealAlarmDisposeList(){
		String rpNum = "";
		String pageIndex = "";
		
		UserInfo user = getCurrentUser();
		
		pageIndex = getRequest().getParameter("page");
		rpNum = getRequest().getParameter("rp");

		String sortName = getRequest().getParameter("sortname");
		String sortOrder = getRequest().getParameter("sortorder");
		
		String vehicle_vin = getRequest().getParameter("vehicle_vin");

		String oilbox_state = getRequest().getParameter("oilbox_state");
		
		String getGridFlag = getRequest().getParameter("gridflag");
		
		String alarmTypeId = getRequest().getParameter("alarm_type_id");
		
		String chooseorgid = getRequest().getParameter("chooseorgid");
		
		String start_time = getRequest().getParameter("start_time");
		
		String end_time = getRequest().getParameter("end_time");
		
		String isValid = getRequest().getParameter("deal_flag");
		
		String isChuli = getRequest().getParameter("isChuli");
		
		String vehicleCode = getRequest().getParameter("vehicleCode");
		
		String vehicleln = getRequest().getParameter("vehicle_ln");
		
		// 增加中文排序
		if (sortName.equals("vehicle_ln")) {
			sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
		}
		
		if (null == chooseorgid || "".equals(chooseorgid)) {
			chooseorgid = user.getOrganizationID();
		}
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("sortname", sortName);
		map.put("sortorder", sortOrder);
	
		map.put("vehicle_vin", vehicle_vin);
		map.put("oilbox_state", oilbox_state);
		map.put("enterprise_id", chooseorgid);
		map.put("entId", user.getEntiID());
		map.put("vehicleln", SearchUtil.formatSpecialChar(vehicleln));
		map.put("vehicleCode", SearchUtil.formatSpecialChar(vehicleCode));
		if(null != start_time && start_time.length() > 0){
			map.put("start_time", start_time+" 00:00:00");
			map.put("end_time", end_time+" 23:59:59");
		}
		map.put("isValid", isValid);
		if("1".equals(isValid)){
			map.put("sortotherconfig", " operate_time desc, ");
		}
		List<ZsptFtlyInfo> list= null;
		int total=0;
		String countSql = "getStealAlarmListPageCount";
		String querySql = "getStealAlarmListPage";
//		if("001".equals(alarmTypeId)){
			countSql = "getStealAlarmListPageCount1";
			querySql = "getStealAlarmListPage1";
//		} else if("12".equals(alarmTypeId)){
//			countSql = "getStealAlarmListPageCount2";
//			querySql = "getStealAlarmListPage2";
//		} else if("010".equals(alarmTypeId)){
//			countSql = "getStealAlarmListPageCount3";
//			querySql = "getStealAlarmListPage3";
//		}
		try {
			total = service.getCount("ZsptFtlyinfoNew."+countSql,map);
			list = service.getObjectsByPage("ZsptFtlyinfoNew."+querySql,map,(Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			if("1".equals(getGridFlag)){
				if("1".equals(isChuli)){
					mapData = getPaginationForMonitorAlarmNew(list,total,pageIndex,rpNum);
				} else {
					mapData = getPaginationForAlarmNew(list,total,pageIndex,rpNum);
				}
			} else {
				mapData = getPaginationForAlarm(list,total,pageIndex,rpNum);
			}
		} catch (BusinessException e) {
			logger.error("", e);
		}
        return SUCCESS;
	}
	
	public String filterState(String oilboxState){
		Set<String> filterSet = new HashSet<String>();
		String[] stateMap = oilboxState.split(",");
		String returnStr = "";
		for(int i = 0; i < stateMap.length; i++){
			filterSet.add(stateMap[i]);
		}
		for(Iterator<String> it = filterSet.iterator(); it.hasNext();){
			String sex = (String)it.next();
			if(returnStr.length() == 0){
				returnStr = returnStr + sex;
			} else {
				returnStr = returnStr + "," + sex;
			}
		}
		return returnStr;
	}
	
	public Map getPaginationForAlarm(List<ZsptFtlyInfo> ftlyInfoList2, int totalCount,
			String pageIndex, String rpNum) 
	{
		List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        	
        for (int i = 0; i < ftlyInfoList2.size(); i++) {
        	
        	ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo) ftlyInfoList2.get(i);
        	
            Map cellMap = new LinkedHashMap();
            ftlyInfo.setOilboxState(filterState(ftlyInfo.getOilboxState()));
            //偏转
    		String lon = ftlyInfo.getLongitude();
    		String lat = ftlyInfo.getLatitude();
    		gpsUtil gpsUtil = new gpsUtil();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			ftlyInfo.setLongitude(p[0].toString());
    			ftlyInfo.setLatitude(p[1].toString());
    		}
    		//偏转结束
       //异动记录
            if("001".equals(ftlyInfo.getOilboxState())) {
	        	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			 "油量骤减告警",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getVehicleLn(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getDirection()
	        		});
            } 
      //加油记录 --异常地点加油
            else if("010".equals(ftlyInfo.getOilboxState())) {
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			 "异常地点加油",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getVehicleLn(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getDirection()
	        		});
	        	
            }
            //加油记录－－异常时间加油
            else if("12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			 "异常时间加油",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getVehicleLn(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getDirection()
	        		});
            } 
          //加油记录－－异常时间加油
            else if("010,12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			 "异常地点/异常时间",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getVehicleLn(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getDirection()
	        		});
            } 
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    
    
	}
	
	/**
	 * 油量告警(告警管理使用)
	 * @param ftlyInfoList2
	 * @param totalCount
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */
	public Map getPaginationForAlarmNew(List<ZsptFtlyInfo> ftlyInfoList2, int totalCount,
			String pageIndex, String rpNum) 
	{
		List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        	
        for (int i = 0; i < ftlyInfoList2.size(); i++) {
        	
        	ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo) ftlyInfoList2.get(i);
        	
            Map cellMap = new LinkedHashMap();
            ftlyInfo.setOilboxState(filterState(ftlyInfo.getOilboxState()));
          //偏转
    		String lon = ftlyInfo.getLongitude();
    		String lat = ftlyInfo.getLatitude();
    		gpsUtil gpsUtil = new gpsUtil();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			ftlyInfo.setLongitude(p[0].toString());
    			ftlyInfo.setLatitude(p[1].toString());
    		}
    		//偏转结束
       //异动记录
            if("001".equals(ftlyInfo.getOilboxState())) {
	        	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"油量骤减告警",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode(),
	        			ftlyInfo.getOperateTimeString()
	        		});
            } 
      //加油记录 --异常地点加油
            else if("010".equals(ftlyInfo.getOilboxState())) {
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常地点加油",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode(),
	        			ftlyInfo.getOperateTimeString()
	        		});
	        	
            }
            //加油记录－－异常时间加油
            else if("12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常时间加油",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode(),
	        			ftlyInfo.getOperateTimeString()
	        		});
            } 
          //加油记录－－异常时间/地点加油
            else if("010,12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常地点/异常时间",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode(),
	        			ftlyInfo.getOperateTimeString()
	        		});
            } 
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    
    
	}
	
	/**
	 * 油量告警(告警管理使用)
	 * @param ftlyInfoList2
	 * @param totalCount
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */
	public Map getPaginationForMonitorAlarmNew(List<ZsptFtlyInfo> ftlyInfoList2, int totalCount,
			String pageIndex, String rpNum) 
	{
		List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        	
        for (int i = 0; i < ftlyInfoList2.size(); i++) {
        	
        	ZsptFtlyInfo ftlyInfo = (ZsptFtlyInfo) ftlyInfoList2.get(i);
        	
            Map cellMap = new LinkedHashMap();
            ftlyInfo.setOilboxState(filterState(ftlyInfo.getOilboxState()));
          //偏转
    		String lon = ftlyInfo.getLongitude();
    		String lat = ftlyInfo.getLatitude();
    		gpsUtil gpsUtil = new gpsUtil();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			ftlyInfo.setLongitude(p[0].toString());
    			ftlyInfo.setLatitude(p[1].toString());
    		}
    		//偏转结束
       //异动记录
            if("001".equals(ftlyInfo.getOilboxState())) {
	        	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"油量骤减告警",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())+Double.valueOf(ftlyInfo.getOilboxMass())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode()
	        		});
            } 
      //加油记录 --异常地点加油
            else if("010".equals(ftlyInfo.getOilboxState())) {
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常地点加油",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode()
	        		});
	        	
            }
            //加油记录－－异常时间加油
            else if("12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常时间加油",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode()
	        		});
            } 
          //加油记录－－异常时间/地点加油
            else if("010,12".equals(ftlyInfo.getOilboxState())){
            	cellMap.put("id", (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
            	
	        	cellMap.put("cell", 
	        		new Object[] 
	        		{
	        			(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), //序号值
	        			ftlyInfo.getVehicle_code(),
	        			ftlyInfo.getVehicleLn(),
	        			"异常地点/异常时间",
	        			"0".equals(ftlyInfo.getIsValid()) ? "未处理" : "已处理",
	        			ftlyInfo.getReportTimeString(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getAddOill())),
	        			(ftlyInfo.getZonename()==null || "".equals(ftlyInfo.getZonename())) ? "定位无效":ftlyInfo.getZonename(),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOperator_name(),
	        			ftlyInfo.getOperateDesc(),
	        			ftlyInfo.getDriverName(),
	        			ftlyInfo.getDriverTel(),
	        			ftlyInfo.getLongitude(),
	        			ftlyInfo.getLatitude(),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())-Double.valueOf(ftlyInfo.getAddOill())),
	        			StringUtil.doubleToString2(Double.valueOf(ftlyInfo.getOilboxMass())),
	        			ftlyInfo.getFtylyIdNum(),
	        			ftlyInfo.getOilboxState(),
	        			ftlyInfo.getVinCode()
	        		});
            } 
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    
    
	}
	
	/**
	 * 滑动窗口异步请求(changePath 判断转向告警处理页面，还是告警时序图页面)
	 * @return
	 */
	public String getOilMessage(){
		try {
			this.vehicle_ln=new String(getRequest().getParameter("vehicle_ln").getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		this.vehicle_vin = getRequest().getParameter("vehicle_vin");
		String changePath = getRequest().getParameter("changePath");
		return changePath;
	}
	
	/**
	 * 解除透油、异常加油地点告警	
	 * @return
	 */
	public String updateAlarmMsg(){
		
		this.mapData.put("operateDesc", this.operateDesc);
		this.mapData.put("ftlyId", this.ftlyId);
		this.mapData.put("isValid", "1");
		this.mapData.put("operator_name", getCurrentUser().getUserID());
		try {
			this.service.update("ZsptFtlyinfoNew.proccessAlarm", mapData);
			this.mapData.put("returns", "success");
		} catch (BusinessException e) {
			logger.error("", e);
			this.mapData.put("returns", "error");
		}
		return "success";
	}
	
	/**
	 * SOS告警数据导出
	 * 
	 * @return
	 */
	public String exportOilalarm() throws Exception{
		//String logid=getlogid();
		String exportTitle = getText("alarmmanage.sos.export");
		//log.info("logid:"+logid+";"+exportTitle+"开始");
		//MDC.put("modulename", "[exportsos]");
		String rpNum = "";
		String pageIndex = "";
		
		UserInfo user = getCurrentUser();
		
		pageIndex = getRequest().getParameter("page");
		rpNum = getRequest().getParameter("rp");

		String sortName = getRequest().getParameter("sortname");
		String sortOrder = getRequest().getParameter("sortorder");
		
		String vehicle_vin = getRequest().getParameter("vehicle_vin");

		String oilbox_state = getRequest().getParameter("oilbox_state");
		
		String getGridFlag = getRequest().getParameter("gridflag");
		
		String alarmTypeId = getRequest().getParameter("alarm_type_id");
		
		String chooseorgid = getRequest().getParameter("chooseorgid");
		
		String start_time = getRequest().getParameter("start_time");
		
		String end_time = getRequest().getParameter("end_time");
		
		String isValid = getRequest().getParameter("deal_flag");
		
		String vehicleCode = getRequest().getParameter("vehicleCode");
		
		String timestr = start_time + "——" + end_time;
		
		// 增加中文排序
//		if (sortName.equals("vehicle_ln")) {
//			sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
//		}
		
		if (null == chooseorgid || "".equals(chooseorgid)) {
			chooseorgid = user.getOrganizationID();
		}
		
		if (org.apache.commons.lang.StringUtils.isEmpty(pageIndex)) {
			pageIndex = "1";
		}
		if (org.apache.commons.lang.StringUtils.isEmpty(rpNum)) {
			rpNum = "10";
		}
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("sortname", sortName);
		map.put("sortorder", sortOrder);
	
		map.put("vehicle_vin", vehicle_vin);
		map.put("oilbox_state", oilbox_state);
		map.put("enterprise_id", chooseorgid);
		if(null != start_time && start_time.length() > 0){
			map.put("start_time", start_time+" 00:00:00");
			map.put("end_time", end_time+" 23:59:59");
		}
		map.put("isValid", isValid);
		map.put("vehicleCode", vehicleCode);
		List<ZsptFtlyInfo> list= null;
		int total=0;
		String countSql = "getStealAlarmListPageCount";
		String querySql = "getStealAlarmListPage";
//		if("001".equals(alarmTypeId)){
			countSql = "getStealAlarmListPageCount1";
			querySql = "getStealAlarmListPage1";
//		} else if("12".equals(alarmTypeId)){
//			countSql = "getStealAlarmListPageCount2";
//			querySql = "getStealAlarmListPage2";
//		} else if("010".equals(alarmTypeId)){
//			countSql = "getStealAlarmListPageCount3";
//			querySql = "getStealAlarmListPage3";
//		}
		try {
			total = service.getCount("ZsptFtlyinfoNew."+countSql,map);
			list = service.getObjectsByPage("ZsptFtlyinfoNew."+querySql,map,0, total);
//			if("1".equals(getGridFlag)){
//				mapData = getPaginationForAlarmNew(list,total,pageIndex,rpNum);
//			} else {
//				mapData = getPaginationForAlarm(list,total,pageIndex,rpNum);
//			}
		} catch (BusinessException e) {
			logger.error("", e);
		}
		
		
		List<ZsptFtlyInfo> oilAlarmList= new ArrayList < ZsptFtlyInfo >();
		for (int i = 0; i < list.size(); i++) {
			ZsptFtlyInfo export=new ZsptFtlyInfo();
			ZsptFtlyInfo s = (ZsptFtlyInfo) list.get(i);
			
			
			export.setVehicle_code(s.getVehicle_code());
			export.setVehicleLn(s.getVehicleLn());
			String state = s.getOilboxState();
			String strState = "";
			if("001".equals(state)){
				strState = "油量骤减";
			} else if("010".equals(state)){
				strState = "异常地点加油";
			} else if("12".equals(state)){
				strState = "异常时间加油";
			} else if("010,12".equals(state)){
				strState = "异常地点/异常时间";
			}
			export.setAlarmTypeName(strState);
			export.setIsValid("0".equals(s.getIsValid())? "未处理" : "已处理");
			export.setReportTimeString(s.getReportTimeString());
			export.setAddOill(s.getAddOill());
			export.setZonename((s.getZonename()==null || "".equals(s.getZonename()))?"定位无效":s.getZonename());
			oilAlarmList.add(export);
			
		}
		
		
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "oilAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("油量告警 ("+timestr+")");
			
			if (null == oilAlarmList || oilAlarmList.size() < 1) {
				oilAlarmList.add(new ZsptFtlyInfo());
			}

			excelExporter.putAutoExtendSheets("exportoilalarm", 0, oilAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出油量告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出油量告警写入Excel时出错:",e);
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
		String fileName = URLEncoder.encode("油量告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName + "-" + name + ".xls");
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
			log.error("导出油量告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出油量告警下载时出错:",e);
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
//			this.addOperationLog(formatLog(exportTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出油量告警结束");
		}
		// 导出文件成功
		return null;
	}
	
	public String findFtlyPoint(){
		String reportTime = getRequest().getParameter("reportTime");
		String vehicleVin = getRequest().getParameter("vehicleVin");
		HashMap maps = new HashMap();
		List pointlist;
		try {
			pointlist = service.getObjects("ZsptFtlyinfoNew.getftlyPoint", maps);
			ZsptFtlyInfo pointInfo = (ZsptFtlyInfo)pointlist.get(0);
			pointInfo.setLongitude(pointInfo.getLongitude());
			pointInfo.setLatitude(pointInfo.getLatitude());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getXmlDoc() {
		return xmlDoc;
	}

	public void setXmlDoc(String xmlDoc) {
		this.xmlDoc = xmlDoc;
	}

	public List<ZsptFtlyInfo> getFtlyInfoList() {
		return ftlyInfoList;
	}

	public void setFtlyInfoList(List<ZsptFtlyInfo> ftlyInfoList) {
		this.ftlyInfoList = ftlyInfoList;
	}

	public Map getFtlyInfoMap() {
		return ftlyInfoMap;
	}

	public void setFtlyInfoMap(Map ftlyInfoMap) {
		this.ftlyInfoMap = ftlyInfoMap;
	}

	public Map getMapData() {
		return mapData;
	}

	public void setMapData(Map mapData) {
		this.mapData = mapData;
	}

	public String getFtlyInfo() {
		return ftlyInfo;
	}

	public void setFtlyInfo(String ftlyInfo) {
		this.ftlyInfo = ftlyInfo;
	}

	public VehicleRegisterInfo getVehicleInfo() {
		return vehicleInfo;
	}

	public void setVehicleInfo(VehicleRegisterInfo vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}

	public String getBigPicSrc() {
		return bigPicSrc;
	}

	public void setBigPicSrc(String bigPicSrc) {
		this.bigPicSrc = bigPicSrc;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	public void setFtlyInfoService(FtlyInfoService ftlyInfoService) {
		this.ftlyInfoService = ftlyInfoService;
	}


	public String getVehicle_vin() {
		return vehicle_vin;
	}


	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}


	public String getVehicle_ln() {
		return vehicle_ln;
	}


	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}


	public String getOptpageid() {
		return optpageid;
	}


	public void setOptpageid(String optpageid) {
		this.optpageid = optpageid;
	}


	public String getOperateDesc() {
		return operateDesc;
	}


	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}


	public String getFtlyId() {
		return ftlyId;
	}


	public void setFtlyId(String ftlyId) {
		this.ftlyId = ftlyId;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getOilbox_state() {
		return oilbox_state;
	}

	public void setOilbox_state(String oilbox_state) {
		this.oilbox_state = oilbox_state;
	}

	public String getGridflag() {
		return gridflag;
	}

	public void setGridflag(String gridflag) {
		this.gridflag = gridflag;
	}

	public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	public String getCdeal_flag() {
		return cdeal_flag;
	}

	public void setCdeal_flag(String cdeal_flag) {
		this.cdeal_flag = cdeal_flag;
	}

	public FtlyInfoService getFtlyInfoService() {
		return ftlyInfoService;
	}

	public String getExprotFileName() {
		return exprotFileName;
	}

	public void setExprotFileName(String exprotFileName) {
		this.exprotFileName = exprotFileName;
	}

	
}

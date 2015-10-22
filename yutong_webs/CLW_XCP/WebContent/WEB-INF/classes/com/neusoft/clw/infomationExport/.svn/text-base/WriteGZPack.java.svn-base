package com.neusoft.clw.infomationExport;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPOutputStream;
import com.neusoft.clw.common.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.writegzpackservice.Writegzpackservice;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 这是个文件操作类，用来写文件并压缩GZIP格式
 * 
 * @author yg
 * @version 2011-12-15
 */
public class WriteGZPack extends PaginationAction {
	private List<Trip> routelist;
	private List<Driver> driverlist;
	private List<Sichen> sichenlist;
	private List<Site> sitelist;
	private List<Student> studentlist;
	/** 车辆列表 **/
	private List<VehcileInfo> vehcList;
	private List<Trip> triplist;
	private Route route;
	private Driver drivers;
	private Sichen sichen;
	private Site site;
	private Student student;
	private String VEHICLE_VIN;
	private String vehicle_vin_pop;
	private String trip_id;

	/** service共通类 */
	private transient Service service;
	
	private Writegzpackservice writegzpackservice;

	/**
	 * 展示已规划车辆列表-跳转页面
	 */
	public String vehicleListSearch() {
		return SUCCESS;
	}

	public void getVinToTrip(){
		try {
			MDC.put("modulename", "[rideplanning]");
			VEHICLE_VIN = vehicle_vin_pop;
			Map < String, Object > resultmap = new HashMap < String, Object >();
			String targetFileName="";
			LOG.info("[VEHICLE_VIN:"+VEHICLE_VIN+"]:乘车规划下载开始");
			try {
				resultmap=writegzpackservice.getPackPath(VEHICLE_VIN,null,Constants.UPLOAD_PATH_BASE,VEHICLE_VIN);
				targetFileName=(String) resultmap.get("filename");
			} catch (DataAccessIntegrityViolationException e) {
				e.printStackTrace();
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			pushPackage(new File(targetFileName));
			//deleteFile(inputFileName);
			//deleteFile(targetFileName);
			LOG.info("乘车规划下载结束");
			
		} catch (BusinessException e) {
			e.printStackTrace();
			LOG.error("乘车规划下载异常",e);
		}
	}
	
		
	public void pushPackage(File srcFile){
		// 设置下载文件属性
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition", "attachment;filename="
				+ (srcFile.getAbsolutePath() + ".gz").substring((srcFile
						.getAbsolutePath() + ".gz").indexOf("\\tmp\\") + 5));
		response.setContentType("application/gzip; charset=\"utf-8\"");

		FileInputStream fileInputStream = null;
		OutputStream outDownload = null;
		try {
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(srcFile.getAbsolutePath()
					+ ".gz");
			outDownload = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				outDownload.write(i);
			}
		} catch (FileNotFoundException e) {
			log.error("下载规划文件异常:" + e.getMessage());
		} catch (Exception e) {
			log.error("下载规划文件异常:" + e.getMessage());
		} finally {
			// 关闭流
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					;
				}
			}
			if (null != outDownload) {
				try {
					outDownload.close();
				} catch (IOException e) {
					;
				}
			}
		}
	}

	/*
	 * 删除文件夹及文件
	 */
	private void deleteFile(String fileStr) {
		File file = new File(fileStr);
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i].getPath());
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}
	
	/**
	 * 展示已规划的车辆列表
	 */
	public String vehicleList() {
		final String vehTitle = getText("oilinfo.veh.title");
		UserInfo user = getCurrentUser();

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		// 每页显示条数
		String rpNum = request.getParameter("rp");
		// 当前页码
		String pageIndex = request.getParameter("page");
		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");

		try {

			VehcileInfo vehinfo = new VehcileInfo();

			vehinfo.setOrganization_id(user.getOrganizationID());
			vehinfo.setSortname(sortName);
			vehinfo.setSortorder(sortOrder);
			int totalCount = 0;
			totalCount = service.getCount("ExportIni.getInfosCount", vehinfo);

			vehcList = (List<VehcileInfo>) service.getObjectsByPage(
					"ExportIni.getInfosVeh", vehinfo, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getPagination(vehcList, totalCount, pageIndex);

		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error(vehTitle, e);
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 转换为Map对象
	 * 
	 * @param dayList
	 * @param totalCountDay
	 * @param pageIndex
	 * @return
	 */
	public Map getPagination(List vehcList, int totalCount, String pageIndex) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < vehcList.size(); i++) {

			VehcileInfo s = (VehcileInfo) vehcList.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getVehicle_vin());

			cellMap.put("cell", new Object[] { s.getVehicle_ln(),
					s.getVehicle_vin(), s.getVehicle_code(),
					s.getShort_allname() });

			mapList.add(cellMap);

		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}
	
	/*
	 * 转UNICODE编码
	 */
	public String getUnicode(String comStrName) {
		String as[] = new String[comStrName.length()];
		String returnStr = "";
		for (int i = 0; i < as.length; i++) {
			as[i] = Integer.toHexString(comStrName.charAt(i) & 0xffff);
			returnStr = returnStr + "\\u" + as[i];
		}
		return returnStr;
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
	
	public List<Trip> getRoutelist() {
		return routelist;
	}

	public void setRoutelist(List<Trip> routelist) {
		this.routelist = routelist;
	}

	public List<Driver> getDriverlist() {
		return driverlist;
	}

	public void setDriverlist(List<Driver> driverlist) {
		this.driverlist = driverlist;
	}

	public List<Sichen> getSichenlist() {
		return sichenlist;
	}

	public void setSichenlist(List<Sichen> sichenlist) {
		this.sichenlist = sichenlist;
	}

	public List<Site> getSitelist() {
		return sitelist;
	}

	public void setSitelist(List<Site> sitelist) {
		this.sitelist = sitelist;
	}

	public List<Student> getStudentlist() {
		return studentlist;
	}

	public void setStudentlist(List<Student> studentlist) {
		this.studentlist = studentlist;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Driver getDrivers() {
		return drivers;
	}

	public void setDrivers(Driver drivers) {
		this.drivers = drivers;
	}

	public Sichen getSichen() {
		return sichen;
	}

	public void setSichen(Sichen sichen) {
		this.sichen = sichen;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getVEHICLE_VIN() {
		return VEHICLE_VIN;
	}

	public void setVEHICLE_VIN(String vehicle_vin) {
		VEHICLE_VIN = vehicle_vin;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getVehicle_vin_pop() {
		return vehicle_vin_pop;
	}

	public void setVehicle_vin_pop(String vehicle_vin_pop) {
		this.vehicle_vin_pop = vehicle_vin_pop;
	}

	public List<VehcileInfo> getVehcList() {
		return vehcList;
	}

	public void setVehcList(List<VehcileInfo> vehcList) {
		this.vehcList = vehcList;
	}

	public List<Trip> getTriplist() {
		return triplist;
	}

	public void setTriplist(List<Trip> triplist) {
		this.triplist = triplist;
	}
	
	private Map map = new HashMap();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public Writegzpackservice getWritegzpackservice() {
		return writegzpackservice;
	}

	public void setWritegzpackservice(Writegzpackservice writegzpackservice) {
		this.writegzpackservice = writegzpackservice;
	}

}
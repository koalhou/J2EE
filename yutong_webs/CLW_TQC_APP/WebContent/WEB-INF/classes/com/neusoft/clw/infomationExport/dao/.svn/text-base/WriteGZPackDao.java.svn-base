package com.neusoft.clw.infomationExport.dao;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

import com.neusoft.clw.common.dao.impl.SqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.infomanage.ridingplan.domain.VehdriverInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;
import com.neusoft.clw.infomationExport.Driver;
import com.neusoft.clw.infomationExport.Route;
import com.neusoft.clw.infomationExport.Sichen;
import com.neusoft.clw.infomationExport.Site;
import com.neusoft.clw.infomationExport.Student;
import com.neusoft.clw.infomationExport.Trip;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 这是个文件操作类，用来写文件并压缩GZIP格式
 * 
 * @author yg
 * @version 2012-07-09
 */
public class WriteGZPackDao extends SqlMapDao {
	private List<Trip> routelist;
	private List<Driver> driverlist;
	private List<Sichen> sichenlist;
	private List<Site> sitelist;
	private List<Student> studentlist;
	private List<Trip> triplist;
	private List<Trip> tripoldlist;
	private Route route;
	private Driver drivers;
	private Sichen sichen;
	private Site site;
	private Student student;

	private static int BUFFER = 1024 * 4; // 缓冲大小
	private static byte[] B_ARRAY = new byte[BUFFER];

	public Map getVinToTrip(String VEHICLE_VIN, String cTrip_ID,
			String path, String VEHICLE_VIN_OLD,String exe_date)
			throws DataAccessIntegrityViolationException, DataAccessException {
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();
			UserInfo user = getCurrentUser();
			String datetimestr = pathDf.format(calendar.getTime());
			// 修改乘车规划车辆时
			/*if (!VEHICLE_VIN.equals(VEHICLE_VIN_OLD)) {
				String tripOldListStr = "";
				String targetDeleteFileName = "";
				String operatetimeold="";
				String triplistoldcrc = "";
				tripoldlist = getSqlMapClientTemplate().queryForList(
						"ExportIni.getVintoTripOld", VEHICLE_VIN_OLD);
				
				//取得行程更新最后时间
				operatetimeold=(String) getSqlMapClientTemplate().queryForObject(
						"ExportIni.getoperatetimeold", VEHICLE_VIN_OLD);
				
				if(operatetimeold == null){
					operatetimeold=datetimestr;
				}
				
				tripOldListStr += "[triplist]" + "\r\n";
				tripOldListStr += "trip_mode=3 \r\n";
				tripOldListStr += "trip_count=" + tripoldlist.size() + " \r\n";
				//edit by susu 20120828 总文件中去掉updatetime字段
				//tripOldListStr += "updatetime=" + operatetimeold + " \r\n";
				String oldpath=path.replace(VEHICLE_VIN, VEHICLE_VIN_OLD);
				deleteFolder(new File(oldpath));
				for (int i = 1; i <= tripoldlist.size(); i++) {
					String fileContent = "";
					String crc = "";
					//组织trip.ini文件字符
					fileContent = getDataToFileString(tripoldlist.get(i - 1).getTrip_id(), VEHICLE_VIN_OLD, oldpath,cTrip_ID);
					crc = writeGZPack(tripoldlist.get(i - 1).getTrip_id(),VEHICLE_VIN_OLD, fileContent, oldpath);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("trip_id", tripoldlist.get(i - 1).getTrip_id());
					map.put("crc", crc);
					getSqlMapClientTemplate().update("RidingPlan.update_xc_trip_exe_crc", map);
					
					tripOldListStr += "[trip_" + i + "]" + "\r\n";
					tripOldListStr += "id=" + tripoldlist.get(i - 1).getTrip_id() + " \r\n";
					tripOldListStr += "crc=" + crc + " \r\n";
				}
				triplistoldcrc=setTripListIni(VEHICLE_VIN_OLD, tripOldListStr, oldpath);
				resultmap.put("oldcrc", triplistoldcrc);
				String inputFileName = oldpath + VEHICLE_VIN_OLD + "/";
				targetDeleteFileName = oldpath + VEHICLE_VIN_OLD + "-"
						+ datetimestr + ".tar";
				execute(inputFileName, targetDeleteFileName);
			}*/

			String tripListStr = "";
			String targetFileName = "";
			String operatetime=null;
			String triplistcrc="";
			if (user.getEn_mould().equals("2")) {
				tripListStr += "trip_mode=2 \r\n";
				// .......核心处增写........
			} else {
				Map<String,Object> mapd = new HashMap<String,Object>();
				mapd.put("vehicle_vin", VEHICLE_VIN);
				mapd.put("exe_date", exe_date);
				triplist = getSqlMapClientTemplate().queryForList("ExportIni.getVintoTripNew_cari", mapd);
				//operatetime=(String) getSqlMapClientTemplate().queryForObject("ExportIni.getoperatetime_cari", cTrip_ID);
				
				if(operatetime == null){
					operatetime=datetimestr;
				}
				deleteFolder(new File(path));
				tripListStr += "[triplist]" + "\r\n";
				tripListStr += "trip_mode=3 \r\n";
				tripListStr += "trip_count="+triplist.size()+" \r\n";
				//edit by susu 20120828 总文件中去掉updatetime字段
				//tripListStr += "updatetime=" + operatetime + " \r\n";
				for (int i = 1; i <= triplist.size(); i++) {
					String fileContent = "",crc = "";
					fileContent = getDataToFileString(triplist.get(i - 1).getTrip_id(), VEHICLE_VIN, path,cTrip_ID);//2589 triplist.get(i - 1).getTrip_id()
					crc = writeGZPack(triplist.get(i - 1).getTrip_id(),VEHICLE_VIN, fileContent, path);
				
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("trip_id", triplist.get(i - 1).getTrip_id());
					map.put("crc", crc);
					if(cTrip_ID.indexOf(triplist.get(i - 1).getTrip_id())>=0)
						getSqlMapClientTemplate().update("RidingPlan.update_xc_trip_exe_crc_time", map);
					else
						getSqlMapClientTemplate().update("RidingPlan.update_xc_trip_exe_crc", map);
				     
					//按照本安的要求，重新定义tripList.ini文件 -----add by ningdonghai 2013-08-03
					String send_condition=triplist.get(i - 1).getSend_condition();
					String route_name=triplist.get(i - 1).getRoute_name();
					String send_time=triplist.get(i - 1).getSend_time();
					//如果是0：坐满发车   1:循环发车
					if(send_condition.equals("0")){						
						route_name=route_name+" 坐满发车";
					}
					//2:按时发车
					else if(send_condition.equals("2")){
						route_name=route_name+" "+send_time+"发车";
					}else if(send_condition.equals("1")){
						//route_name=route_name;
					}	
					
					tripListStr += "[trip_" + i + "]" + "\r\n";
					tripListStr += "id=" + triplist.get(i - 1).getTrip_id() + " \r\n";
					tripListStr += "send_order=" + i + " \r\n";
					tripListStr += "trip_name=" + route_name + " \r\n";
					tripListStr += "crc=" + crc + " \r\n";
				}
				
				triplistcrc=setTripListIni(VEHICLE_VIN, tripListStr, path);
				resultmap.put("crc", triplistcrc);
				
				String inputFileName = path + VEHICLE_VIN + "/";
				targetFileName = path + VEHICLE_VIN + "-" + datetimestr + ".tar";
				execute(inputFileName, targetFileName);
			}
			
			
			resultmap.put("filename",targetFileName);
			return resultmap;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	/**
	 * 删除文件夹及文件
	 */
	public static boolean deleteFolder(File dir) {
		if(dir.exists()){
			File filelist[] = dir.listFiles();
			int listlen = filelist.length;
			for (int i = 0; i < listlen; i++) {
				if (filelist[i].isDirectory()) {
					deleteFolder(filelist[i]);
				} else {
					if (!filelist[i].delete()){
						System.out.println("删除第"+i+"个文件失败");
						return false;
					}
				}
			}
			
		}
		if (!dir.delete()){
			System.out.println("删除当前目录失败");
			return false;// 删除当前目录
		}else{
			return true;
		}
	}

	/**
	 * 创建INI配置文件
	 */
	public String setTripListIni(String vehicle_vin, String tripListStr,
			String path) {
		String filePath = "";
		filePath = path + vehicle_vin + "/triplist.ini";
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			BufferedWriter bufferedWriter;
			bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
			bufferedWriter.write(tripListStr);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doChecksum(filePath);

	}

	public String getDataToFileString(String trip_id, String vehicle_vin,
			String path,String Trip_ID) throws DataAccessIntegrityViolationException,
			DataAccessException {
		String fileContent = "";

		if (null == route) {
			route = new Route();
		}
		if (null == site) {
			site = new Site();
		}
		if (null == student) {
			student = new Student();
		}
		if (null == drivers) {
			drivers = new Driver();
		}
		if (null == sichen) {
			sichen = new Sichen();
		}
		try {
			routelist = getSqlMapClientTemplate().queryForList("ExportIni.getRouteBaseInfo_carinfo", trip_id);//getRouteBaseInfo
			sitelist = getSqlMapClientTemplate().queryForList("ExportIni.getSiteInfo_carinfo", trip_id);
			/*studentlist = getSqlMapClientTemplate().queryForList("ExportIni.getStudentInfo", trip_id);			
			sichenlist = getSqlMapClientTemplate().queryForList("ExportIni.getSichenInfo", trip_id);*/
			driverlist = getSqlMapClientTemplate().queryForList("ExportIni.getDriverInfo", trip_id);
		} catch (Exception e) {
			throw new DataAccessException(e);
		}

		fileContent += "[trip]" + "\r\n";
		//fileContent += "updatetime=" + routelist.get(0).getOperate_time()+ "\r\n";		
		fileContent += "route_id=" + routelist.get(0).getRoute_id() + "\r\n";
		fileContent += "id=" + routelist.get(0).getTrip_id() + "\r\n";
		String typeStr = "";
		if ("0".equals(routelist.get(0).getType())) {
			typeStr = "1";
		} else {
			typeStr = "2";
		}
		fileContent += "type=" + typeStr + "\r\n";
		fileContent += "name=" + StringUtil.nullToStr(routelist.get(0).getRoute_name()) + "\r\n";	
		fileContent += "station_count=" + routelist.get(0).getSitecount() + "\r\n";
		fileContent += "student_count=" + routelist.get(0).getStudentcount() + "\r\n";
		fileContent += "driver_count=" + routelist.get(0).getDrivercount() + "\r\n";
		fileContent += "passenger_count=" + routelist.get(0).getSichencount() + "\r\n";		
		//新增修改项	
		//（行程文件修改）如果是	坐满发车send_condition为1 如果是按时发车为2	循环发车随便写
		String condition = routelist.get(0).getSend_condition().equals("0")?"1":routelist.get(0).getSend_condition().equals("1")?"0":"2";
		String start_time = routelist.get(0).getSend_condition().equals("2")?routelist.get(0).getSend_time():"";
		fileContent += "send_condition=" + condition + "\r\n";//	与下面trip_loop重复
		fileContent += "start_time=" + start_time + "\r\n";
		fileContent += "end_time=" + StringUtil.nullToStr(routelist.get(0).getEnd_time()) + "\r\n";		
		////专为循环发车 trip_loop
		String send_condition = routelist.get(0).getSend_condition().equals("1")?"1":"0";
		fileContent += "trip_loop=" + send_condition + "\r\n";
		//fileContent += "send_order=" + routelist.get(0).getSend_order() + "\r\n";

		if (sitelist.size() > 0) {
			for (int i = 1; i <= sitelist.size(); i++) {
				String name = StringUtil.nullToStr(sitelist.get(i - 1).getSite_name());
				String addr = StringUtil.nullToStr(sitelist.get(i - 1).getSichen_addr());
				String plan_in_time = StringUtil.nullToStr(sitelist.get(i - 1).getPlan_in_time());
				String plan_out_time = StringUtil.nullToStr(sitelist.get(i - 1).getPlan_out_time());
				String custom_voice_content = StringUtil.nullToStr(sitelist.get(i - 1).getCustom_voice_content());

				fileContent += "[station_" + i + "]" + "\r\n";
				//只有循环发车时增加preStationId
				if(i>1&&sitelist.get(i - 1).getSet_perstation().equals("0"))
					fileContent += "preStationId=" + sitelist.get(i - 2).getSite_id() + "\r\n";
				else
					fileContent += "preStationId= \r\n";
				fileContent += "name=" + name + "\r\n";
				fileContent += "id=" + sitelist.get(i - 1).getSite_id() + "\r\n";
				fileContent += "addr=" + addr + "\r\n";
				fileContent += "latitude=" + sitelist.get(i - 1).getSite_latitude() + "\r\n";
				fileContent += "longitude=" + sitelist.get(i - 1).getSite_longitude() + "\r\n";
				fileContent += "arrive_time=" + plan_in_time + "\r\n";
				fileContent += "leave_time=" + plan_out_time + "\r\n";
				fileContent += "broadcast=" + custom_voice_content + "\r\n";
				//if(send_condition.equals("1"))
					fileContent += "range="+sitelist.get(i - 1).getSet_in()+":"+sitelist.get(i - 1).getSet_out()+" \r\n";
				//else
					//fileContent += "range= \r\n";
			}
		}

		/*if (Integer.parseInt(routelist.get(0).getStudentcount()) > 0) {
			for (int i = 1; i <= studentlist.size(); i++) {
				String name = studentlist.get(i - 1).getStu_name() == null ? ""
						: studentlist.get(i - 1).getStu_name();
				String card_number = studentlist.get(i - 1).getStu_card_id() == null ? ""
						: studentlist.get(i - 1).getStu_card_id();
				String school = studentlist.get(i - 1).getStu_school() == null ? ""
						: studentlist.get(i - 1).getStu_school();
				String stu_class = studentlist.get(i - 1).getStu_class() == null ? ""
						: studentlist.get(i - 1).getStu_class();
				String teacher_contact = studentlist.get(i - 1)
						.getTeacher_tel() == null ? "" : studentlist.get(i - 1)
						.getTeacher_tel();
				String parents_contact = studentlist.get(i - 1)
						.getRelative_tel() == null ? "" : studentlist
						.get(i - 1).getRelative_tel();

				String picture_name = studentlist.get(i - 1).getPhoto_name() == null ? ""
						: studentlist.get(i - 1).getPhoto_name();

				fileContent += "[student_" + i + "]" + "\r\n";
				fileContent += "name=" + name + "\r\n";
				fileContent += "id=" + studentlist.get(i - 1).getStu_id()
						+ "\r\n";
				fileContent += "card_number=" + card_number + "\r\n";
				fileContent += "school=" + school + "\r\n";
				fileContent += "class=" + stu_class + "\r\n";
				fileContent += "teacher_contact=" + teacher_contact + "\r\n";
				fileContent += "parents_contact=" + parents_contact + "\r\n";
				fileContent += "picture_name=" + picture_name + "\r\n";

				String up_station_id = studentlist.get(i - 1).getGetonbus() == null ? ""
						: studentlist.get(i - 1).getGetonbus();

				fileContent += "up_station_id=" + up_station_id + "\r\n";

				String down_station_id = studentlist.get(i - 1).getGetdownbus() == null ? ""
						: studentlist.get(i - 1).getGetdownbus();

				fileContent += "down_station_id=" + down_station_id + "\r\n";

				byte[] b = studentlist.get(i - 1).getStu_photo();
				if (studentlist.get(i - 1).getStu_photo() != null) {
					InputStream in = new ByteArrayInputStream(b);
					String sFilef ="";
					if(Trip_ID==null){
						
						sFilef = path + vehicle_vin + "/"
						+ studentlist.get(i - 1).getPhoto_name();
					}else{
						
						sFilef = path + "/"
						+ studentlist.get(i - 1).getPhoto_name();
					}
					
					FileOutputStream files = null;
					try {

						File file = new File(path + vehicle_vin);
						if (!file.exists()) {
							file.mkdirs();
						}
						files = new FileOutputStream(sFilef);
						int len = (int) b.length;
						byte[] buffer = new byte[len]; // 建立缓冲区
						while ((len = in.read(buffer)) != -1) {
							files.write(buffer, 0, len);
						}
						if (files != null) {
							files.close();
						}
						if (in != null) {
							in.close();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}*/		
		/*if (Integer.parseInt(routelist.get(0).getSichencount()) > 0) {
			for (int i = 1; i <= sichenlist.size(); i++) {
				String name = sichenlist.get(i - 1).getSichen_name() == null ? ""
						: sichenlist.get(i - 1).getSichen_name();
				fileContent += "[passenger_" + i + "]" + "\r\n";
				fileContent += "id=" + sichenlist.get(i - 1).getSichen_id()
						+ "\r\n";
				fileContent += "card_number="
						+ sichenlist.get(i - 1).getSichen_card_id() + "\r\n";
				fileContent += "name=" + name + "\r\n";

			}
		}*/
		if (Integer.parseInt(routelist.get(0).getDrivercount()) > 0) {
			for (int i = 1; i <= driverlist.size(); i++) {
				String name = StringUtil.nullToStr(driverlist.get(i - 1).getDriver_name());
				fileContent += "[driver_" + i + "]" + "\r\n";
				fileContent += "id=" + driverlist.get(i - 1).getDriver_id()+ "\r\n";
				fileContent += "card_number="+ driverlist.get(i - 1).getDriver_card_id() + "\r\n";
				fileContent += "name=" + name + "\r\n";	
				fileContent += "driver_tel=" + driverlist.get(i - 1).getDriver_tel() + "\r\n";	
			  }
	    }
		return fileContent;
	}

	/**
	 * 创建INI配置文件及其它文件
	 */
	public String writeGZPack(String trip_id, String vehicle_vin,
			String fileContent, String path) {
		String filePath = "";
		filePath = path + vehicle_vin + "/trip_" + trip_id + ".ini";
		File file = new File(filePath);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			BufferedWriter bufferedWriter;
			bufferedWriter = new BufferedWriter(new FileWriter(filePath, false));
			bufferedWriter.write(fileContent);
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doChecksum(filePath);
	}

	/*
	 * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
	 */
	public void execute(String inputFileName, String targetFileName) {
		File inputFile = new File(inputFileName);
		String base = inputFileName.substring(inputFileName.lastIndexOf("/") + 1);
		
		//TarOutputStream out = getTarOutputStream(targetFileName);
		
		// 如果打包文件没有.tar后缀名，就自动加上
		targetFileName = targetFileName.endsWith(".tar") ? targetFileName : targetFileName + ".tar";
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(targetFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		TarOutputStream out = new TarOutputStream(bufferedOutputStream);

		// 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
		out.setLongFileMode(TarOutputStream.LONGFILE_GNU);
		tarPack(out, inputFile, base);
		try {
			if (null != out) {
				out.close();
			}
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		compress(new File(targetFileName));
	}

	public void pushPackage(File srcFile) {
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
			fileInputStream = new FileInputStream(srcFile.getAbsolutePath() + ".gz");
			outDownload = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				outDownload.write(i);
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
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
	 * 方法功能：打包成tar文件 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
	 */

	private void tarPack(TarOutputStream out, File inputFile, String base) {
		if (inputFile.isDirectory()) { // 打包文件夹
			packFolder(out, inputFile, base);
		} else { // 打包文件
			packFile(out, inputFile, base);
		}
	}

	/*
	 * 方法功能：遍历文件夹下的内容，如果有子文件夹，就调用tarPack方法 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件
	 * base 打包文件中的路径
	 */
	private void packFolder(TarOutputStream out, File inputFile, String base) {
		File[] fileList = inputFile.listFiles();
		try {
			// 在打包文件中添加路径
			out.putNextEntry(new TarEntry(base + "/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		base = base.length() == 0 ? "" : base + "/";
		for (File file : fileList) {
			tarPack(out, file, base + file.getName());
		}
	}

	/*
	 * 方法功能：打包文件 参数：out 压缩后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
	 */
	private void packFile(TarOutputStream out, File inputFile, String base) {
		TarEntry tarEntry = new TarEntry(base);

		// 设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
		tarEntry.setSize(inputFile.length());
		try {
			out.putNextEntry(tarEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int b = 0;

		try {
			while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err
					.println("NullPointerException info ======= [FileInputStream is null]");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.closeEntry();
				}
			} catch (IOException e) {
				System.err
				.println("tar error");
			}
		}
	}

	/*
	 * 方法功能：把打包的tar文件压缩成gz格式 参数：srcFile 要压缩的tar文件路径
	 */
	private void compress(File srcFile) {
		File target = new File(srcFile.getAbsolutePath() + ".gz");
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {

			in = new FileInputStream(srcFile);
			out = new GZIPOutputStream(new FileOutputStream(target));
			int number = 0;
			while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, number);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}

				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 方法功能：获得打包后文件的流 参数：targetFileName 打包后文件的路径
	 */
	private TarOutputStream getTarOutputStream(String targetFileName) {
		// 如果打包文件没有.tar后缀名，就自动加上
		targetFileName = targetFileName.endsWith(".tar") ? targetFileName
				: targetFileName + ".tar";
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(targetFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);
		TarOutputStream out = new TarOutputStream(bufferedOutputStream);

		// 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
		out.setLongFileMode(TarOutputStream.LONGFILE_GNU);

		return out;
	}

	private String doChecksum(String fileName) {
		long checksum = 0;
		try {

			CheckedInputStream cis = null;
			try {
				// Computer CRC32 checksum
				cis = new CheckedInputStream(new FileInputStream(fileName),
						new CRC32());
			} catch (FileNotFoundException e) {
				System.err.println("File not found.");
			}

			byte[] buf = new byte[128];
			while (cis.read(buf) >= 0) {
			}

			checksum = cis.getChecksum().getValue();
			if (cis != null) {
				cis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return String.valueOf(checksum);
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
	
	/**
	 * 泰安乘车规划下发
	 * @param VEHICLE_VIN
	 * @param cTrip_ID
	 * @param path
	 * @param VEHICLE_VIN_OLD
	 * @param exe_date
	 * @return
	 * @throws DataAccessIntegrityViolationException
	 * @throws DataAccessException
	 */
	public Map getTVinToTrip(String VEHICLE_VIN, String cTrip_ID,
			String path, String VEHICLE_VIN_OLD,String exe_date)
			throws DataAccessIntegrityViolationException, DataAccessException {
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar calendar = Calendar.getInstance();
			String datetimestr = pathDf.format(calendar.getTime());
			String tripListStr = "";
			String targetFileName = "";
			String triplistcrc="";
			deleteFolder(new File(path));
			tripListStr += "[triplist]" + "\r\n";
			tripListStr += "trip_mode=4 \r\n";
			tripListStr += "trip_count=1 \r\n";
			//单个ini文件写入
			String fileContent = "";
			String crc = "";					
			fileContent = getTDataToFileString(cTrip_ID, VEHICLE_VIN, path,cTrip_ID);//2589 triplist.get(i - 1).getTrip_id()
			crc = writeGZPack(cTrip_ID,VEHICLE_VIN, fileContent, path);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("trip_id", cTrip_ID);
			map.put("crc", crc);
			tripListStr += "[trip_1]" + "\r\n";
			tripListStr += "id=" + cTrip_ID + " \r\n";
			tripListStr += "send_order=1 \r\n";
			tripListStr += "trip_name= \r\n";
			tripListStr += "crc=" + crc + " \r\n";
			//乘车规划主ini文件写入
			triplistcrc=setTripListIni(VEHICLE_VIN, tripListStr, path);
			resultmap.put("crc", triplistcrc);
			String inputFileName = path + VEHICLE_VIN + "/";
			targetFileName = path + VEHICLE_VIN + "-" + datetimestr + ".tar";
			execute(inputFileName, targetFileName);
			resultmap.put("filename",targetFileName);
			return resultmap;
		} catch (Exception e) {
			throw new DataAccessException(e);
		}
	}
	
	/**
	 * 泰安通勤车单个ini文件生成
	 * @param trip_id
	 * @param vehicle_vin
	 * @param path
	 * @param Trip_ID
	 * @return
	 * @throws DataAccessIntegrityViolationException
	 * @throws DataAccessException
	 */
	public String getTDataToFileString(String trip_id, String vehicle_vin,
			String path,String Trip_ID) throws DataAccessIntegrityViolationException,
			DataAccessException {
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("trip_id", trip_id);
		map1.put("vehicle_vin", vehicle_vin);
		List<VssInfo> vssList = getSqlMapClientTemplate().queryForList("ExportIni.getTVintoTrip", map1);
		List<VehdriverInfo> driverList = getSqlMapClientTemplate().queryForList("ExportIni.getTDriver", map1);
		String fileContent = "";
		fileContent += "[trip]" + "\r\n";
		fileContent += "route_id= \r\n";
		fileContent += "id=" + trip_id + "\r\n";
		String typeStr = "";
		fileContent += "type=" + typeStr + "\r\n";
		fileContent += "student_count =" +vssList.size()+ "\r\n";
		int i = 1;
		for(VssInfo v : vssList){
			fileContent += "[student_" + i + "]" + "\r\n";
			fileContent += "name=" + v.getStudent_name() + "\r\n";
			fileContent += "id=" + v.getStudent_id()+ "\r\n";
			fileContent += "card_number=" + v.getCard_id() + "\r\n";
			i++;
		}
		int j=1;
		for(VehdriverInfo d : driverList){
			fileContent += "[driver_" + j + "]" + "\r\n";
			fileContent += "name=" + d.getDriver_name() + "\r\n";
			fileContent += "id=" + d.getDriver_id()+ "\r\n";
			fileContent += "card_number=" + d.getDriver_card_id() + "\r\n";
			j++;
		}
		return fileContent;
	}
}
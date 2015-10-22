package com.neusoft.SchoolBus.vncs.thread;


import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.MDC;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.neusoft.SchoolBus.vncs.dao.impl.QuartzTripDAO;
import com.neusoft.SchoolBus.vncs.domain.Driver;
import com.neusoft.SchoolBus.vncs.domain.Sichen;
import com.neusoft.SchoolBus.vncs.domain.Site;
import com.neusoft.SchoolBus.vncs.domain.StudentTrip;
import com.neusoft.SchoolBus.vncs.domain.Trip;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.tqcpt.util.CDate;
import com.sun.jmx.snmp.BerException;
public class QuartzTripThread extends Thread implements Runnable {

	private static Logger log = LoggerFactory.getLogger(QuartzThread.class);
	private static int BUFFER = 1024 * 4; // 缓冲大小
//	private static byte[] B_ARRAY = new byte[BUFFER];
	private static final String NAME = "<QuartzTripThread>";
	private static SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
	private Trip trip;
    private List<Trip> triplist;
	private List<Trip> routelist;
	private List<Driver> driverlist;
	private List<Sichen> sichenlist;
	private List<Site> sitelist;
	private List<StudentTrip> studentlist;	
	private String strVin;
	private String strDate;
	private QuartzTripDAO qtdao;
	@SuppressWarnings("unused")
	private QuartzTripThread() {
	}
    //正式使用
	public QuartzTripThread(String date, String vin) {
		strDate = date;
		strVin = vin;
		qtdao = (QuartzTripDAO) SpringBootStrap.getInstance().getBean("qtdao");
	}
	/*//测试使用
	public QuartzTripThread(String date, String vin,ApplicationContext ac) {
		strDate = date;
		strVin = vin;
		qtdao = (QuartzTripDAO) ac.getBean("qtdao");
	}*/

	public void run() {
		try {
			QuartzCountDown.instance().countAdd();
			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[QBuf]");
			log.info(NAME + "通勤车行程文件线程开始======vehicle_vin：" + strVin + ",date:"+ strDate);
			
			//修改原有校车下发原理           --modify by ningdh  2013/07/08
			/*String enmodle = qtdao.getenidList(strVin);
			if(enmodle!=null&&!enmodle.equals("")){
				//如果是模式2
				if("2".equals(enmodle)){
					getmdtVinToTrip(strVin, strDate);
				}
				//如果是模式3
				if("3".equals(enmodle)){
					if(qtdao.gettrip(strVin)>0){
					  getVinToTrip(strVin, strDate);
					}
				}
			}else{
				log.info(NAME + "未找到该企业模式！");
			}*/
			
			//不再受模式的限制，统一模式为3 
			//如果没有行程 生成一个空行程 终端在线更新
			//if(qtdao.gettrip(strVin,strDate)>0){
				  getVinToTrip(strVin, strDate);
		    //}			
			
			QuartzCountDown.instance().countDown();
		} catch (Exception e) {
			QuartzCountDown.instance().countDown();
			e.printStackTrace();
			log.info(NAME + "vehicle_vin：" + strVin + ",date:" + strDate + " 行程文件线程未正常结束，关闭线程！");
		}
		log.info(NAME + "行程文件线程结束======vehicle_vin：" + strVin + ",date:" + strDate);
	}
	/**
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
	 * 生成CRC校验码
	 * 
	 * @throws Exception
	 */

	public static long doChecksum(String fileName) {
		long checksum = 0;
		try {

			CheckedInputStream cis = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(fileName);
				// Computer CRC32 checksum
				cis = new CheckedInputStream(fis,
						new CRC32());
			} catch (FileNotFoundException e) {
				System.err.println("File not found.");
			}

			byte[] buf = new byte[128];
			while (cis.read(buf) >= 0) {
			}

			checksum = cis.getChecksum().getValue();
			
			if(null != fis){
				fis.close();
			}
			if(null != cis){
				cis.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return checksum;
	}
	
	/**
	 * 创建行程INI配置文件及其它文件
	 * 
	 * @throws Exception
	 */
	public String sendTripFile(String tripid,String VEHICLE_VIN) throws Exception {
		String fileContent= "";
		String realPath = Config.props.getProperty("iniPath") + "/"
				+ VEHICLE_VIN + "/"; // 家里測試路徑
		String usedPath = realPath;
		try {
			routelist = qtdao.getRouteBaseInfo(tripid);
			sitelist = qtdao.getSiteInfo(tripid);
			log.debug("sitelist=" + tripid);
			driverlist = qtdao.getDriverInfo(tripid);
			log.debug("driverlist=" + tripid);
			/*studentlist = qtdao.getStudentInfo(tripid);
			log.debug("studentlist=" + tripid);			
			sichenlist = qtdao.getSichenInfo(tripid);
			log.debug("sichenlist=" + tripid);*/
			
		} catch (BerException e3) {
			e3.printStackTrace();
		}
		//行程基本信息
		fileContent += "[trip]" + "\r\n";			
		
		//【执行】修改为  因为每天执行的行程都是最新的，和手工下发不一样，自动下发CRC每天必须得修改，才能保证终端每天早上都更新
		//fileContent += "updatetime=" + StringUtil.nullToStr(CDate.getCurrentDate_trip()) + "\r\n";	
		fileContent += "route_id=" + StringUtil.nullToStr(routelist.get(0).getRoute_id())+ "\r\n";
		fileContent += "id=" + StringUtil.nullToStr(routelist.get(0).getTrip_id()) + "\r\n";
		String typeStr="";
		if("0".equals(routelist.get(0).getType())){
			typeStr="1";
		}else{
			typeStr="2";
		}
		fileContent += "type=" + typeStr + "\r\n";
		fileContent += "name="+ StringUtil.nullToStr(routelist.get(0).getRoute_name())+"\r\n";		
		fileContent += "station_count="	+ StringUtil.nullToStr(routelist.get(0).getSitecount()) + "\r\n";
		fileContent += "student_count="	+ StringUtil.nullToStr(routelist.get(0).getStudentcount()) + "\r\n";
		fileContent += "driver_count="	+ StringUtil.nullToStr(routelist.get(0).getDrivercount()) + "\r\n";
		fileContent += "passenger_count="+ StringUtil.nullToStr(routelist.get(0).getSichencount()) + "\r\n";	
		
		//新增修改项
		String conditionTemp = StringUtil.nullToStr(routelist.get(0).getSend_condition());
		//（行程文件修改）如果是	坐满发车send_condition为1 如果是按时发车为2	循环发车随便写
		String condition = conditionTemp.equals("0")?"1":conditionTemp.equals("1")?"0":"2";
		String start_time = conditionTemp.equals("2")?StringUtil.nullToStr(routelist.get(0).getSend_time()):"";
		fileContent += "send_condition=" + condition + "\r\n";
		fileContent += "start_time=" + start_time + "\r\n";
		fileContent += "end_time=" + StringUtil.nullToStr(routelist.get(0).getEnd_time()) + "\r\n";		
		//专为循环发车 trip_loop
		String trip_loop = conditionTemp.equals("1")?"1":"0";
		fileContent += "trip_loop=" + trip_loop + "\r\n";		
		//fileContent += "send_order=" + StringUtil.nullToStr(routelist.get(0).getSend_order()) + "\r\n";
        
		//站点基本信息
		if(Integer.parseInt(routelist.get(0).getSitecount())>0){ 
			for (int i = 1; i <= sitelist.size(); i++) {
				String name = StringUtil.nullToStr(sitelist.get(i - 1).getSite_name());
				String addr = StringUtil.nullToStr(sitelist.get(i - 1).getSichen_addr());				
				String plan_in_time = StringUtil.nullToStr(sitelist.get(i - 1).getPlan_in_time());				
				String plan_out_time = StringUtil.nullToStr(sitelist.get(i - 1).getPlan_out_time());
				String custom_voice_content = StringUtil.nullToStr(sitelist.get(i - 1).getCustom_voice_content());

				fileContent += "[station_" + i + "]" + "\r\n";
				
				if(i>1&&sitelist.get(i - 1).getSet_perstation().equals("0"))
					fileContent += "preStationId=" + sitelist.get(i - 2).getSite_id() + "\r\n";
				else
					fileContent += "preStationId= \r\n";
				
				fileContent += "name=" + name + "\r\n";
				fileContent += "id="+ sitelist.get(i - 1).getSite_id() + "\r\n";
				fileContent += "addr=" + addr + "\r\n";
				fileContent += "latitude="+ sitelist.get(i - 1).getSite_latitude() + "\r\n";
				fileContent += "longitude="	+ sitelist.get(i - 1).getSite_longitude() + "\r\n";					
                fileContent += "arrive_time=" + plan_in_time + "\r\n";
				fileContent += "leave_time=" + plan_out_time + "\r\n";
				fileContent += "broadcast=" + custom_voice_content + "\r\n";
				//if(trip_loop.equals("1"))
					fileContent += "range="+sitelist.get(i - 1).getSet_in()+":"+sitelist.get(i - 1).getSet_out()+" \r\n";
				//else
					//fileContent += "range= \r\n";
			}
		}
		//司机基本信息
		if(Integer.parseInt(routelist.get(0).getDrivercount())>0){
			for (int i = 1; i <= driverlist.size(); i++) {
				String name = driverlist.get(i - 1).getDriver_name() == null ? ""
						: driverlist.get(i - 1).getDriver_name();
				fileContent += "[driver_" + i + "]" + "\r\n";
				fileContent += "id="+ driverlist.get(i - 1).getDriver_id() + "\r\n";
				fileContent += "card_number="+ driverlist.get(i - 1).getDriver_card_id() + "\r\n";
				fileContent += "name=" + name + "\r\n";
				fileContent += "driver_tel=" + driverlist.get(i - 1).getDriver_tel() + "\r\n";	
			}
		}
        
		/*//上车学生基本信息
		if(Integer.parseInt(routelist.get(0).getStudentcount())>0){
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
				
				String picture_name = studentlist.get(i - 1)
						.getPhotoname() == null ? "" : studentlist
						.get(i - 1).getPhotoname();
				
				fileContent += "[student_" + i + "]" + "\r\n";
				fileContent += "name=" + name + "\r\n";
				fileContent += "id="+ studentlist.get(i - 1).getStu_id() + "\r\n";
				fileContent += "card_number=" + card_number + "\r\n";
				fileContent += "school=" + school + "\r\n";
				fileContent += "class=" + stu_class + "\r\n";
				fileContent += "teacher_contact=" + teacher_contact	+ "\r\n";
				fileContent += "parents_contact=" + parents_contact	+ "\r\n";
				fileContent += "picture_name="	+ picture_name + "\r\n";

				String up_station_id = studentlist.get(i - 1).getGetonbus() == null ? ""
						: studentlist.get(i - 1).getGetonbus();

				fileContent += "up_station_id=" + up_station_id + "\r\n";

				String down_station_id = studentlist.get(i - 1).getGetdownbus() == null ? ""
						: studentlist.get(i - 1).getGetdownbus();
				
				fileContent += "down_station_id="+ down_station_id + "\r\n";

				byte[] b = studentlist.get(i - 1).getStu_photo();
				if (studentlist.get(i - 1).getStu_photo() != null) {
					InputStream in = new ByteArrayInputStream(b);
					String sFilef = usedPath + "/"
							+ studentlist.get(i - 1).getPhotoname();
					FileOutputStream files = null;
					try {

						File file = new File(usedPath + VEHICLE_VIN);
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
		}	
		
		//司乘基本信息
		if(Integer.parseInt(routelist.get(0).getSichencount())>0){
			for (int i = 1; i <= sichenlist.size(); i++) {
				String name = sichenlist.get(i - 1).getSichen_name() == null ? ""
						: sichenlist.get(i - 1).getSichen_name();
				fileContent += "[passenger_" + i + "]" + "\r\n";
				fileContent += "id="+ sichenlist.get(i - 1).getSichen_id() + "\r\n";
				fileContent += "card_number="+ sichenlist.get(i - 1).getSichen_card_id() + "\r\n";
				fileContent += "name=" + name + "\r\n";
			}
		}*/
		
		return fileContent;

	}
	
	public void getVinToTrip(String VEHICLE_VIN, String date) throws Exception {		
		if (null == trip) {
			trip = new Trip();
		}
		log.debug("triplist--->:VEHICLE_VIN=" + VEHICLE_VIN+" DATE="+date);
		//根据车辆VIN、日期查询行程执行表，该表数据是通过数据库存储过程来实现的，是否增加行程，是否取消行程，已经处理好
		//如果查询为空，证明今天没有行程，或者行程取消	    
		triplist = qtdao.getTripBaseInfo(VEHICLE_VIN,date);			
		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String path = Config.props.getProperty("iniPath") + "/"+VEHICLE_VIN+"/"; 
		//String path="C:/tqcfile/"+VEHICLE_VIN+"/"; 
		String tripListStr="";
		
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} else {
			deleteFile(path);
			file.getParentFile().mkdirs();
		}
		tripListStr += "[triplist]" + "\r\n";
		tripListStr += "trip_mode=3 \r\n";
		tripListStr += "trip_count="+triplist.size()+" \r\n";
		for(int i=1;i<=triplist.size();i++){
			String fileContent= "";
			String crc="";
			//生成线路具体内容
			fileContent=sendTripFile(triplist.get(i-1).getTrip_id(),VEHICLE_VIN);
			//生成CRC校验码
			crc=String.valueOf(writeGZPack(triplist.get(i-1).getTrip_id(),VEHICLE_VIN,fileContent,path));
			//更新
			qtdao.updateCrc(triplist.get(i-1).getTrip_id(),crc);
			
			//按照本安的要求，重新定义tripList.ini文件 -----add by ningdonghai 2013-08-03
			String send_condition=triplist.get(i - 1).getSend_condition();
			String route_name=triplist.get(i - 1).getRoute_name();
			String send_time=triplist.get(i - 1).getSend_time();
			//如果是0：坐满发车   1:循环发车
			if(send_condition.equals("0")||send_condition.equals("1")){						
				route_name=route_name+" 坐满发车";
			}
			//2:按时发车
			else if(send_condition.equals("2")){
				route_name=route_name+" "+send_time+"发车";
			}	
			
			tripListStr += "[trip_" + i + "]" + "\r\n";
			tripListStr += "id="+triplist.get(i-1).getTrip_id()+" \r\n";
			tripListStr += "send_order="+i+" \r\n"; //add by ningdh 20130603
			tripListStr += "trip_name=" + route_name + " \r\n";
			tripListStr += "crc="+crc+" \r\n";
		}
		//生成tripList文件
		setTripListIni(VEHICLE_VIN,tripListStr,path);
		String inputFileName = path + VEHICLE_VIN + "/";
		String targetFileName = path + VEHICLE_VIN + "-" + pathDf.format(calendar.getTime()) + ".tar";
		//输出生成压缩包
		execute(inputFileName, targetFileName);
	}
	
	/**
	 * 创建模式2INI文件
	 */	
	
	public void getmdtVinToTrip(String VEHICLE_VIN, String date) throws Exception {
		
		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		if (null == trip) {
			trip = new Trip();
		}			
		String path = Config.props.getProperty("iniPath") + "/" +VEHICLE_VIN+"/"; // 家里測試路徑	
		String tripListStr="";
		tripListStr += "[triplist]" + "\r\n";
		tripListStr += "trip_mode=2 \r\n";
				
		String fileContent= "";
		fileContent=sendTripMTFile(VEHICLE_VIN);
		
		tripListStr = tripListStr+ "\r\n" + fileContent;
		
		setTripListIni(VEHICLE_VIN,tripListStr,path);
		String inputFileName = path + VEHICLE_VIN + "/";
		String targetFileName = path + VEHICLE_VIN + "-"
				+ pathDf.format(calendar.getTime()) + ".tar";
		execute(inputFileName, targetFileName);
//		pushPackage(new File(targetFileName));			

	}	
	/**
	 * 创建行程INI配置文件及其它文件
	 * 
	 * @throws Exception
	 */
	public String sendTripMTFile(String VEHICLE_VIN) throws Exception {
		String fileContent= "";
		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();		
		String date = pathDf.format(calendar.getTime());
		String fileName = VEHICLE_VIN + "-" + date + ".INI";

		String realPath = Config.props.getProperty("iniPath") + "/"
				+ VEHICLE_VIN + "/"; // 家里測試路徑
		String usedPath = realPath;
		File file = new File(usedPath + fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		} else {
			deleteFile(usedPath);
			file.getParentFile().mkdirs();
		}
		try {
			driverlist = qtdao.getDriverModelInfo(VEHICLE_VIN);
			sichenlist = qtdao.getSichenMModelInfo(VEHICLE_VIN);
			
		} catch (BerException e3) {
			e3.printStackTrace();
		}
		fileContent += "driver_count="	+ driverlist.size() + "\r\n";
		fileContent += "passenger_count="+ sichenlist.size() + "\r\n";		

		for (int i = 1; i <= driverlist.size(); i++) {
			String name = driverlist.get(i - 1).getDriver_name() == null ? ""
					: driverlist.get(i - 1).getDriver_name();
			fileContent += "[driver_" + i + "]" + "\r\n";
			fileContent += "id="+ driverlist.get(i - 1).getDriver_id() + "\r\n";
			fileContent += "card_number="+ driverlist.get(i - 1).getDriver_card_id() + "\r\n";
			fileContent += "name=" + name + "\r\n";

		}		
	
		for (int i = 1; i <= sichenlist.size(); i++) {
			String name = sichenlist.get(i - 1).getSichen_name() == null ? ""
					: sichenlist.get(i - 1).getSichen_name();
			fileContent += "[passenger_" + i + "]" + "\r\n";
			fileContent += "id="+ sichenlist.get(i - 1).getSichen_id() + "\r\n";
			fileContent += "card_number="+ sichenlist.get(i - 1).getSichen_card_id() + "\r\n";
			fileContent += "name=" + name + "\r\n";

		}		
		
		return fileContent;

	}	
	/**
	 * 创建INI配置文件
	 */
	public void setTripListIni(String vehicle_vin,String tripListStr,String path) {
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
		
	}
	/*
	 * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
	 */
	public void execute(String inputFileName, String targetFileName) {
		File inputFile = new File(inputFileName);
		String base = inputFileName
				.substring(inputFileName.lastIndexOf("/") + 1);
		TarOutputStream out = getTarOutputStream(targetFileName);
		tarPack(out, inputFile, base);
		try {
			if (null != out) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		compress(new File(targetFileName));
		//deleteFile(inputFileName);
		// deleteFile(targetFileName);
	}
	

	/*
	 * 方法功能：把打包的tar文件压缩成gz格式 参数：srcFile 要压缩的tar文件路径
	 */
	private void compress(File srcFile) {
		byte[] B_ARRAY = new byte[BUFFER];
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
	/*
	 * 方法功能：打包成tar文件 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
	 */

	private void tarPack(TarOutputStream out, File inputFile, String base) {
		if (inputFile.isDirectory()){ // 打包文件夹
			packFolder(out, inputFile, base);
		} else{ // 打包文件
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
		byte[] B_ARRAY = new byte[BUFFER];
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

			}
		}
	}	
	/**
	 * 创建INI配置文件及其它文件
	 */
	public long writeGZPack(String trip_id,String vehicle_vin,String fileContent,String path) {
		String filePath = "";
		filePath = path + vehicle_vin + "/trip_"+trip_id+".ini";
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
}

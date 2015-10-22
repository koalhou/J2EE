package com.neusoft.SchoolBus.vncs.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.impl.QuartzDAO;
import com.neusoft.SchoolBus.vncs.domain.Driver;
import com.neusoft.SchoolBus.vncs.domain.Route;
import com.neusoft.SchoolBus.vncs.domain.Sichen;
import com.neusoft.SchoolBus.vncs.domain.Site;
import com.neusoft.SchoolBus.vncs.domain.Student;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.spring.SpringBootStrap;


/**
 * 线路下发
 * 
 * @author yg
 * @version 2012-03-15
 */
public class SendRouteFile  {
	
	private   Logger log = LoggerFactory.getLogger(SendRouteFile.class);  
	
	private List<Route> routelist;
	private List<Driver> driverlist;
	private List<Sichen> sichenlist;
	private List<Site> sitelist;
	private List<Student> studentlist;
	
	private Route route;
	private Driver drivers;
	private Sichen sichen;
	private Site site;
	private Student student;
	private String VEHICLE_VIN;
	private String iniDefaultPath;
	private String ip;
	private String port;
	private String username;
	private String userpass;
	
	private QuartzDAO qdao;
	
	public SendRouteFile(){
		qdao = (QuartzDAO) SpringBootStrap.getInstance().getBean("qdao");
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getIniDefaultPath() {
		return iniDefaultPath;
	}

	public void setIniDefaultPath(String iniDefaultPath) {
		this.iniDefaultPath = iniDefaultPath;
	}

	public List<Route> getRoutelist() {
		return routelist;
	}

	public void setRoutelist(List<Route> routelist) {
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
	 * 创建INI配置文件及其它文件
	 * @throws Exception 
	 */
	public void sendRouteFile(String VEHICLE_VIN,String date) throws Exception {
		String fileContent;
//		String VEHICLE_VIN_ARRAY = "013774269115,LZYTETC65A1026688";
////		String batch_id = IdCreater.getUUid();
//		//
//		String Arr[]= VEHICLE_VIN_ARRAY.split(",");
//		
//		for(int j=0; j<Arr.length;j++){
//			VEHICLE_VIN=Arr[j];
//			SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
//			Calendar calendar = Calendar.getInstance();
			
			String fileName = VEHICLE_VIN + "-" + date	+ ".INI";

			String realPath = Config.props.getProperty("iniPath")+"/"+VEHICLE_VIN+ "/" ;    //家里測試路徑
			
//	        String pathInfo = iniDefaultPath+VEHICLE_VIN+ "/" ; //線網路徑
	        
			String usedPath=realPath;
	        
			File file = new File(usedPath+ fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}else{
				deleteFile(usedPath);
				file.getParentFile().mkdirs();
			}
			
			
			try {
				BufferedWriter bufferedWriter;
				bufferedWriter = new BufferedWriter(new FileWriter(usedPath+ fileName, false));

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
					routelist = qdao.getRouteBaseInfo(VEHICLE_VIN);
					log.info("routelist="+VEHICLE_VIN);
					sitelist = qdao.getSiteInfo(VEHICLE_VIN);
					log.info("sitelist="+VEHICLE_VIN);
					studentlist = qdao.getStudentInfo(VEHICLE_VIN);
					log.info("studentlist="+VEHICLE_VIN);
					driverlist = qdao.getDriverInfo(VEHICLE_VIN);
					log.info("driverlist="+VEHICLE_VIN);
					sichenlist = qdao.getSichenInfo(VEHICLE_VIN);
					log.info("sichenlist="+VEHICLE_VIN);
				} catch (Exception e3) {
					e3.printStackTrace();
					throw new Exception("查询车辆相关信息时发生异常"+e3.getMessage());
				}
				String nameRoute = (routelist == null|| routelist.size()==0 )?"":((routelist.get(0).getRoute_name() == null) ? ""
						: routelist.get(0).getRoute_name());

				fileContent = "";
				fileContent += "[route]" + "\r\n";
				fileContent += "id=" + routelist.get(0).getRoute_id()
						+ "\r\n";
				fileContent += "name=" + nameRoute + "\r\n";
				// delete by jinp start
//				fileContent += "station_count="
//						+ routelist.get(0).getSitecount() + "\r\n";
//				fileContent += "student_count="
//						+ routelist.get(0).getStudentcount() + "\r\n";
				// delete by jinp stop
				fileContent += "station_count=" + sitelist.size()
						+ "\r\n";
				fileContent += "student_count="
						+ studentlist.size() + "\r\n";
				// add by jinp start
				
				// add by jinp stop
				fileContent += "driver_count="
						+ routelist.get(0).getDrivercount() + "\r\n";
				fileContent += "passenger_count="
						+ routelist.get(0).getSichencount() + "\r\n";
				fileContent += "up_start_time="
						+ routelist.get(0).getUpstarttime() + "\r\n";
				fileContent += "up_end_time="
						+ routelist.get(0).getUpendtime() + "\r\n";
	 			fileContent += "down_start_time="
						+ routelist.get(0).getDownstarttime() + "\r\n";
				fileContent += "down_end_time="
						+ routelist.get(0).getDownendtime() + "\r\n";
				for (int i = 1; i <= sitelist.size(); i++) {

					String name = sitelist.get(i - 1).getSite_name() == null ? ""
							: sitelist.get(i - 1).getSite_name();
					String addr = sitelist.get(i - 1).getSichen_addr() == null ? ""
							: sitelist.get(i - 1).getSichen_addr();
					
					String plan_in_time = sitelist.get(i - 1).getPlan_in_time() == null ? ""
							: sitelist.get(i - 1).getPlan_in_time();
					
					String plan_out_time = sitelist.get(i - 1).getPlan_out_time() == null ? ""
							: sitelist.get(i - 1).getPlan_out_time();

					fileContent += "[station_" + i + "]" + "\r\n";
					fileContent += "name=" + name + "\r\n";
					fileContent += "id="
							+ sitelist.get(i - 1).getSite_id() + "\r\n";
					fileContent += "addr=" + addr + "\r\n";
					fileContent += "latitude="
							+ sitelist.get(i - 1).getSite_latitude() + "\r\n";
					fileContent += "longitude="
							+ sitelist.get(i - 1).getSite_longitude() + "\r\n";
					fileContent += "direction="
							+ sitelist.get(i - 1).getVss_state() + "\r\n";
					fileContent += "arrive_time=" + plan_in_time + "\r\n";
					fileContent += "leave_time=" + plan_out_time + "\r\n";
				}
				log.info("sitelist1:"+VEHICLE_VIN);
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
					fileContent += "[student_" + i + "]" + "\r\n";
					fileContent += "name=" + name + "\r\n";
					fileContent += "id="
							+ studentlist.get(i - 1).getStu_id() + "\r\n";
					fileContent += "card_number=" + card_number + "\r\n";
					fileContent += "school=" + school + "\r\n";
					fileContent += "class=" + stu_class + "\r\n";
					fileContent += "teacher_contact=" + teacher_contact
							+ "\r\n";
					fileContent += "parents_contact=" + parents_contact
							+ "\r\n";
					fileContent += "picture_name="+ (studentlist.get(i - 1).getPhotoname() == null ? "" : studentlist.get(i - 1).getPhotoname()) + "\r\n";

					String up_station_id = studentlist.get(i - 1).getGetonbus() == null ? ""
							: studentlist.get(i - 1).getGetonbus();

					if (studentlist.get(i - 1).getGetonbus() == null) {
						fileContent += "up_station_id=" + up_station_id
								+ "\r\n";
					} else {
						/*if (studentlist.get(i - 1).getGetonbus().indexOf(",") > -1) {
							fileContent += "up_station_id=\""
									+ studentlist.get(i - 1).getGetonbus()
									+ "\"\r\n";
						} else {
						*/
							fileContent += "up_station_id="
									+ studentlist.get(i - 1).getGetonbus() + "\r\n";
						//}
					}

					String down_station_id = studentlist.get(i - 1).getGetdownbus() == null ? ""
							: studentlist.get(i - 1).getGetdownbus();

					if (studentlist.get(i - 1).getGetdownbus() == null) {
						fileContent += "down_station_id="
								+ down_station_id + "\r\n";
					} else {
						/*if (studentlist.get(i - 1).getGetdownbus().indexOf(",") > -1) {
							fileContent += "down_station_id=\""
									+ studentlist.get(i - 1).getGetdownbus()
									+ "\"\r\n";
						} else {*/
							fileContent += "down_station_id="
									+ studentlist.get(i - 1).getGetdownbus()
									+ "\r\n";
						//}
					}

					Blob b = studentlist.get(i - 1).getStu_photo();
					if (studentlist.get(i - 1).getStu_photo() != null) {
//						InputStream in = new ByteArrayInputStream(b);
						String sFilef = usedPath
								+ studentlist.get(i - 1).getPhotoname();
//						FileOutputStream files = new FileOutputStream(sFilef);
//						int len = (int) b.length;
//						byte[] buffer = new byte[len]; // 建立缓冲区
//						while ((len = in.read(buffer)) != -1) {
//							files.write(buffer, 0, len);
//						}
//						files.close();
//						in.close();
						BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(sFilef));
						BufferedInputStream in = new BufferedInputStream(b.getBinaryStream());
						int c;
						while ((c=in.read())!=-1) {
							output.write(c);
						}
						in.close();
						output.close();
					}
				}
				log.info("studentlist1:"+VEHICLE_VIN);
				for (int i = 1; i <= driverlist.size(); i++) {
					String name = driverlist.get(i - 1).getDriver_name() == null ? ""
							: driverlist.get(i - 1).getDriver_name();
					fileContent += "[driver_" + i + "]" + "\r\n";
					fileContent += "id="
							+ driverlist.get(i - 1).getDriver_id() + "\r\n";
					fileContent += "card_number="
							+ driverlist.get(i - 1).getDriver_card_id() + "\r\n";
					fileContent += "name=" + name + "\r\n";

				}
				log.info("driverlist1:"+VEHICLE_VIN);
				for (int i = 1; i <= sichenlist.size(); i++) {
					String name = sichenlist.get(i - 1).getSichen_name() == null ? ""
							: sichenlist.get(i - 1).getSichen_name();
					fileContent += "[passenger_" + i + "]" + "\r\n";
					fileContent += "id="
							+ sichenlist.get(i - 1).getSichen_id() + "\r\n";
					fileContent += "card_number="
							+ sichenlist.get(i - 1).getSichen_card_id() + "\r\n";
					fileContent += "name=" + name + "\r\n";

				}
				log.info("sichenlist1:"+VEHICLE_VIN);
				bufferedWriter.write(fileContent);
				bufferedWriter.flush();
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(e.getMessage());
			}
//		}
		
		
	}	
	
	public static long doChecksum(String fileName) {
		long checksum = 0;
        try {

            CheckedInputStream cis = null;
            try {
                // Computer CRC32 checksum
                cis = new CheckedInputStream(new FileInputStream(fileName), new CRC32());
            } catch (FileNotFoundException e) {
                System.err.println("File not found.");
            }

            byte[] buf = new byte[128];
            while(cis.read(buf) >= 0) {
            }

            checksum = cis.getChecksum().getValue();
            
            if (null != cis) {
				cis.close();
			}

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return checksum;
    }
}
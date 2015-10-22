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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.writegzpackservice.Writegzpackservice;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.infomationExport.WriteGZPack;
import com.opensymphony.xwork2.ActionContext;
/**
 * 线路下发
 * 
 * @author yg
 * @version 2012-03-15
 */
public class SendRouteFile extends PaginationAction {
	private List<Trip> triplist;
	private String VEHICLE_VIN;
	private String iniDefaultPath;
	private String ip;
	private String port;
	private String username;
	private String userpass;
	private String carsVinInfos;
	private String tripidInfos;
	private String trip_ids;
	private String iniDefaultPathFlag;
	private SendCommandClient sendCommandClient;
	/** service共通类 */
	private transient Service service;
	private Writegzpackservice writegzpackservice;

	public void sendRouteFile(){
		try {
			UserInfo user = getCurrentUser();
			String Arr[]= carsVinInfos.split(",");
			
			String ArrTrip[]=tripidInfos.split(",");
			String batch_id = UUIDGenerator.getUUID32();
			Map < String, Object > resultmap = new HashMap < String, Object >();
			
			log.info("carsVinInfos:"+carsVinInfos);
			log.info("tripidInfos:"+tripidInfos);
			
			for(int j=0; j<Arr.length;j++){
				VEHICLE_VIN=Arr[j];
				trip_ids=ArrTrip[j];
				String realPath = ServletActionContext.getServletContext().getRealPath(
		         "/")+iniDefaultPath+VEHICLE_VIN+ "/" ;    //家里測試路徑
				
		        String pathInfo = iniDefaultPath+VEHICLE_VIN+ "/" ; //線網路徑
		        
		        String sendFilePath="";
		        
		        String targetFileName="";
		        
		        if(iniDefaultPathFlag.equals("0")){
		        	sendFilePath="/"+VEHICLE_VIN+"/";
		        }else{
		        	sendFilePath=pathInfo;
		        }
		        
				String usedPath=pathInfo;
				
				try {
					resultmap=writegzpackservice.getPackPath(VEHICLE_VIN,trip_ids,usedPath,VEHICLE_VIN);
					targetFileName=(String) resultmap.get("filename");
				} catch (DataAccessIntegrityViolationException e) {
					e.printStackTrace();
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				targetFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
				
		    	String msgid = UUIDGenerator.getUUID32();
		    	String crc=(String) resultmap.get("crc");
		    	String returnvalue = sendCommandClient.sendRouteNotice(VEHICLE_VIN, user.getUserID(), msgid, batch_id,
		    			ip, port, username, userpass, 
		    			sendFilePath+ targetFileName , 
		    			crc);
		    	//deleteFile(inputFileName);
				//deleteFile(targetFileName);
		    	log.info("returnvalue:" + returnvalue);
			}

		} catch (BusinessException e) {
			log.error("下发异常", e);
		}
	}

	/**
	 * 模式2的线路下发。 
	 * @返回类型：void
	 * @方法描述：TODO
	 */
	public void sendRouteFileM2(){
		try {
			UserInfo user = getCurrentUser();
			String Arr[]= carsVinInfos.split(",");
			
			String batch_id = UUIDGenerator.getUUID32();
			Map < String, Object > resultmap = new HashMap < String, Object >();
			
			log.info("carsVinInfos:"+carsVinInfos);
			
			for(int j=0; j<Arr.length;j++){
				VEHICLE_VIN=Arr[j];
				
		        String pathInfo = iniDefaultPath+VEHICLE_VIN+ "/" ; //線網路徑
		        
		        String sendFilePath="";
		        
		        String targetFileName="";
		        
		        if(iniDefaultPathFlag.equals("0")){
		        	sendFilePath="/"+VEHICLE_VIN+"/";
		        }else{
		        	sendFilePath=pathInfo;
		        }
		        
				String usedPath=pathInfo;
				
				try {
					resultmap=writegzpackservice.getPackPathM2(VEHICLE_VIN,usedPath,VEHICLE_VIN);
					targetFileName=(String) resultmap.get("filename");
				} catch (DataAccessIntegrityViolationException e) {
					e.printStackTrace();
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				targetFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
				
		    	String msgid = UUIDGenerator.getUUID32();
		    	String crc=(String) resultmap.get("crc");
		    	String returnvalue = sendCommandClient.sendRouteNotice(VEHICLE_VIN, user.getUserID(), msgid, batch_id,
		    			ip, port, username, userpass, 
		    			sendFilePath+ targetFileName , 
		    			crc);
		    	//deleteFile(inputFileName);
				//deleteFile(targetFileName);
		    	log.info("returnvalue:" + returnvalue);
			}

		} catch (BusinessException e) {
			log.error("下发异常", e);
		}
	}
	
	private String doChecksum(String fileName) {
		long checksum = 0;
        try {
            CheckedInputStream cis = null;
            try {
                // Computer CRC32 checksum
                cis = new CheckedInputStream(
                        new FileInputStream(fileName), new CRC32());
            } catch (FileNotFoundException e) {
            	log.error("File not found.");
            }

            byte[] buf = new byte[128];
            while(cis.read(buf) >= 0) {
            }
            checksum = cis.getChecksum().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(checksum);
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
			log.error("所删除的文件不存在！");
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
    
    public String getTripidInfos() {
		return tripidInfos;
	}

	public void setTripidInfos(String tripidInfos) {
		this.tripidInfos = tripidInfos;
	}

	public String getTrip_ids() {
		return trip_ids;
	}

	public void setTrip_ids(String trip_ids) {
		this.trip_ids = trip_ids;
	}

	public String getIniDefaultPathFlag() {
		return iniDefaultPathFlag;
	}

	public void setIniDefaultPathFlag(String iniDefaultPathFlag) {
		this.iniDefaultPathFlag = iniDefaultPathFlag;
	}

	public String getCarsVinInfos() {
		return carsVinInfos;
	}

	public void setCarsVinInfos(String carsVinInfos) {
		this.carsVinInfos = carsVinInfos;
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

	public List<Trip> getTriplist() {
		return triplist;
	}

	public void setTriplist(List<Trip> triplist) {
		this.triplist = triplist;
	}

	public String getVEHICLE_VIN() {
		return VEHICLE_VIN;
	}

	public void setVEHICLE_VIN(String vehicle_vin) {
		VEHICLE_VIN = vehicle_vin;
	}

	public SendCommandClient getSendCommandClient() {
		return sendCommandClient;
	}

	public void setSendCommandClient(SendCommandClient sendCommandClient) {
		this.sendCommandClient = sendCommandClient;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Writegzpackservice getWritegzpackservice() {
		return writegzpackservice;
	}

	public void setWritegzpackservice(Writegzpackservice writegzpackservice) {
		this.writegzpackservice = writegzpackservice;
	}
}
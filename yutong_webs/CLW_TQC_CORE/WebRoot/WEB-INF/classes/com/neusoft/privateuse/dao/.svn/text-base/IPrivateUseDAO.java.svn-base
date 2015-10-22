package com.neusoft.privateuse.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.privateuse.bean.LawfulRunTimeInfo;
import com.neusoft.privateuse.bean.RunRecord;
import com.neusoft.privateuse.bean.VehicleInfo;

public interface IPrivateUseDAO {
	
	List<VehicleInfo> getVehicleInfo(int weekDay);
	
	List<VehicleInfo> getVehiclePrivateUseAlarm();
	
	List<VehicleInfo> getVehicleInfoByVin(String vin,String start,String end);
	
	List<VehicleInfo> getVehicleOilByVin(String vin,String start,String end);
	
	List<LawfulRunTimeInfo> getVehicleInfoByOrganization_id(String organization_id);
	
	
	List<RunRecord> getRunRecordInfo(String vin);
	
	int updatePrivateUseAlarm(VehicleInfo vi);

	int updatePrivateUseFlag();

}

package com.neusoft.parents.algorithm.dao;


import java.util.Date;
import java.util.List;

import com.neusoft.parents.algorithm.domain.*;

public interface IAlgorithmDAO {
	/**
	 * 计算上行站点
	 * @param vehicle_vin
	 * @param date 计算哪一天的线路
	 * @param hour 上下行的分割时间，一般是12点，12
	 * @return
	 */
	List<CheckCardRecord> getCheckCardRecordUp(String vehicle_vin, Date date, int hour);
	/**
	 * 计算下行站点
	 * @param vehicle_vin
	 * @param date 计算哪一天的线路
	 * @param hour 上下行的分割时间，一般是12点，12
	 * @return
	 */
	List<CheckCardRecord> getCheckCardRecordDown(String vehicle_vin, Date date, int hour);
	
	List<CheckCardRecord> getCheckCardRecordByUpOrDown(String vehicle_vin, Date date, int upDown, int upDownHour);
	/*
	 * 
	 * @param type 短信版还是全能版
	 * @return
	 */
	List<Vehicle> getVehiclesByModel(int model);
	
	
	List<Date> getCheckRecordSampleDate(String vin, int recordCount);
	
	List<Date> getCheckRecordSampleDateByUpAndDown(String vin, int recordCount, int upDown, int upDownHour);
	
	List<CheckCardRecord> getCheckCardRecordDetailByUpOrDown(String vehicle_vin, Date date, int upOrDown, int upOrDownHour);
	
	List<VehicleTrack> getVehicleTrack(String vin, int type);	
	
	Date getLastCheckRecordSampleDate(String vin, Date date);
	
	List<VehicleTrack> getTerminalRecordDetail(String vin, Date date);
	
	List<VehicleTrack> getTerminalRecordDetail(String vin, Date date, int type, int upDownHour);
	
	List<VehicleTripStation> getVehicleTripStation(String vin);
	
	List<VehicleTripStation> getVehicleTripStationByTripId(int tripId);
	
	List<Trip> getTripByVehicleVin(String vin);
	
	List<Trip> getTripNotInOrbitByVehicleVin(String vin);
	
	Date getLastTerminalRecordDate(String vin, Date date);
	
	void insertTripOrbit(VehicleTrack vt);
	
	void deleteTripOrbitByTripId(int tripId);
	
	List<VehicleTrack> getTripOrbitByTripId(int tripId);
	
	int getTripOrbitCountByTripId(int tripId);
	
	List<Station> getStationByTripId(int tripId);
	
	List<StationGenAlgorithmParam> getStationGenAlgorithmParamByOrganization(String organizationId);
	
	List<StationGenAlgorithmParam> getStationGenAlgorithmParamByDefault();
	
	List<StationGenAlgorithmParam> getStationGenAlgorithmParamByEnterprise(String enterpriseId);
	
	List<StationGenAlgorithmParam> getStationGenAlgorithmParamByVin(String vin);
	
	Vehicle getVehicle(String vin);
	
	StationGenAlgorithmThreshold getStationGenAlgorithmThreshold(String vin);
	
	void insertStationStudent(String vin, int routeId, int tripId, int stationId, int studentId);
	
	int getStationStudentTripSiteCount(int tripId, int stationId);
	
	void insertStationStudentToBackup(int studentId,int stationId);
	
	void deleteStationStudentFromBackup(int stationId);
	
	int getRouteIdByTripId(int tripId);
	   //陈卫峰添加start
    int getJZSiteID();
    int getJZRouteID();
    int getJZRelationID();
    int getXCSiteID();
    int getXCRouteID();
    
    int deleteJzStuSite(String vin);
    int deleteJzRouteCalc(String vin);
    int deleteJzSiteCalc(String vin);
    int deleteJzSiteVehicle(String vin);
    int deleteJzTrip(String vin);
    
    int getSiteListCount(String vin);

    int getRouteListCount(String vin);

    int getRouteSiteListCount(String vin);

    int getTripListCount(String vin);

    int getVssSiteListCount(String vin);

    int getVssListCount(String vin);
  //陈卫峰添加end
    
    void ExecuteSql(String sql);
    
    List<AlgorithmVehicle> getVehiclesForAlgorithm();
    
    void UpdateCalculationBegin(String vin, Date beginDate);
    
    void UpdateCalculationEnd(String vin, Date endDate, int isCompleted);
}

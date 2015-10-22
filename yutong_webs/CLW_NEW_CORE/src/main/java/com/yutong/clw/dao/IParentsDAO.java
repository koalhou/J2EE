package com.yutong.clw.dao;

import java.util.Date;
import java.util.List;

import com.yutong.clw.beans.vehicle_real.News;
import com.yutong.clw.beans.vehicle_real.Rules;
import com.yutong.clw.beans.vehicle_real.Site;
import com.yutong.clw.beans.vehicle_real.StuSiteNote;
import com.yutong.clw.beans.vehicle_real.Vehicle;
import com.yutong.clw.beans.vehicle_real.VehicleReal;

public interface IParentsDAO
{

    List<Rules> getRulesInfo();

    List<VehicleReal> getVehicleRealInfo();

    VehicleReal getVehicleRealByVin(String vin);

    List<StuSiteNote> getStuSiteNote(int weekDay);
    
    List<StuSiteNote> getVinTripList(String vin,String trip,int weekDay);
    
    List<StuSiteNote> delVinTrip();
    
    List<StuSiteNote> getVinTrip(int weekDay);
    
    List<VehicleReal> getVehicleVin();
    
    List<VehicleReal> getTestVin(int flag);
    
    List<News> getNewsList();
    
    List<News> getEmpCode();
    
    List<StuSiteNote> getEmpSiteNote(String routeId,String siteId);

    String getSysTime();
    
    String getVehicleCode(String vehicle_vin);

    int getPushCheckRecordID();
    
 

    List<Rules> getUserPushRule(String userId,String stuId);

    @SuppressWarnings("rawtypes")
    List getSiteNoteLog(String accountId, String noteId,String tripId, Date date);

    Vehicle getVehicleByStuId(int stuId);

    List<Site> getSitesByStuId(int stuId);

    List<Site> getSitesByStuId(int stuId, int controlStation);
    
    int updateAllStuState();
      int updateStuStateById(String stuId,String state);

}

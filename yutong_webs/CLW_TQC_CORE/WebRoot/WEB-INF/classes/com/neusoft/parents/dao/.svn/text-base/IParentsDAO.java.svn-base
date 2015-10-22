package com.neusoft.parents.dao;

import java.util.Date;
import java.util.List;

import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.bean.Parents;
import com.neusoft.parents.bean.Rules;
import com.neusoft.parents.bean.Site;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.Students;
import com.neusoft.parents.bean.Vehicle;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.bean.News;

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
    
    List<NoticeBean> getGroupRouteSite();
    
    List<NoticeBean> getGroupLineByTime(String route_id, String site_id,int time);
    
    //int deletePreLineByRouteAndSite(String route_id,String site_id);
    int deletePreLineByRouteAndSite();
    
    int insertPreLineByRouteAndSite(String route_id, String site_id,float mile);
    //int insertPreLineByTime(String route_id, String site_id,int time);
    
    int insertPreLineByTime(NoticeBean bean);
    
 

    List<Rules> getUserPushRule(String userId,String stuId);

    @SuppressWarnings("rawtypes")
    List getSiteNoteLog(String accountId, String noteId,String tripId, Date date);

    Vehicle getVehicleByStuId(int stuId);

    List<Site> getSitesByStuId(int stuId);

    List<Site> getSitesByStuId(int stuId, int controlStation);
    
    int updateAllStuState();
      int updateStuStateById(String stuId,String state);

}

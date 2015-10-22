package com.yutong.clw.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yutong.clw.beans.vehicle_real.CacheVinTripList;
import com.yutong.clw.beans.vehicle_real.Rules;
import com.yutong.clw.beans.vehicle_real.StuSiteNote;
import com.yutong.clw.beans.vehicle_real.VehicleReal;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.ParentsDAO;
import com.yutong.clw.quartz.managers.RuleCacheManager;
import com.yutong.clw.quartz.managers.VehicleRealCacheManager;
import com.yutong.clw.quartz.managers.VinTripCacheManager;
import com.yutong.clw.sysboot.SpringBootStrap;

public class CacheUtils
{


   

    public static VehicleReal getVehicleRealCache(String vin)
    {
        VehicleReal vr = VehicleRealCacheManager.getInstance().getValue(Constant.VEHICLEREAL + vin);
        if (vr != null)
        {
            return vr;
        }
        else
        {
            ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
            vr = parentsDAO.getVehicleRealByVin(vin);
            VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.VEHICLEREAL + vin, vr);
        }
        return vr;
    }
    
    public static int getDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);  
    	return cal.get(Calendar.DAY_OF_WEEK); 
    }
    
    public static CacheVinTripList getVinTripCache(String vin,String trip_id)
    {
    	CacheVinTripList vr = VinTripCacheManager.getInstance().getValue(Constant.STUSITENOTE + vin + trip_id );
        if (vr != null)
        {
            return vr;
        }
        else
        {
        	vr = new CacheVinTripList();
        	Date now = new Date();
        	int weekDay=getDay(now)-1;//获取当天是星期几，星期一是第一天
            if(weekDay < 1 )
            	weekDay = 7;
            ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
            List<StuSiteNote> ddd = parentsDAO.getVinTripList(vin,trip_id,weekDay);
            vr.setStuSiteNoteList(ddd);
            vr.setVehicle_vin(vin);
            vr.setTrip_id(trip_id);
            VinTripCacheManager.getInstance().SyncVinTripValue(Constant.OFF, Constant.VEHICLEREAL + vin + trip_id, vr);
        }
        return vr;
    }

    public static VehicleReal getVehicleInfoCache(String vin)
    {
        VehicleReal vr = VehicleRealCacheManager.getInstance().getValue(Constant.VEHICLEINFO + vin);
        if (vr != null)
        {
            return vr;
        }
        else
        {
            ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
            vr = parentsDAO.getVehicleRealByVin(vin);
            VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.VEHICLEINFO + vin, vr);
        }
        return vr;
    }

    public static List<Rules> getRuleCache(String userId,String empCode)
    {
        List<Rules> listRule = RuleCacheManager.getInstance().getValue(Constant.PUSHRULE + userId+empCode);
        if (listRule != null && listRule.size() > 0)
        {
            return listRule;
        }
        else
        {
            ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
            listRule = parentsDAO.getUserPushRule(userId,empCode);
            RuleCacheManager.getInstance().SyncRulesInfoValue(Constant.OFF, Constant.PUSHRULE + userId+empCode, listRule);
        }
        return listRule;
    }
}

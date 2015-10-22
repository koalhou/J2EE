package com.neusoft.parents.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.parents.bean.CacheVinTripList;
import com.neusoft.parents.bean.Parents;
import com.neusoft.parents.bean.Rules;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.Students;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.cachemanager.RuleCacheManager;
import com.neusoft.parents.cachemanager.VehicleRealCacheManager;
import com.neusoft.parents.cachemanager.VinTripCacheManager;
import com.neusoft.parents.dao.impl.ParentsDAO;

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
            VinTripCacheManager.getInstance().SyncVinTripValue(Constant.OFF, Constant.STUSITENOTE + vin + trip_id, vr);
        }
        return vr;
    }

    public static VehicleReal getVehicleInfoCache(String vin)
    {

        VehicleReal vr = VehicleRealCacheManager.getInstance().getValue(Constant.LIU + vin);
        if (vr != null)
        {
            return vr;
        }
        else
        {
            ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
            vr = parentsDAO.getVehicleRealByVin(vin);
            VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, Constant.LIU + vin, vr);
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

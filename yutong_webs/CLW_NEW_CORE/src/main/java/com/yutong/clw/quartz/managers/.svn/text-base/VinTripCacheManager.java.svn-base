package com.yutong.clw.quartz.managers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.vehicle_real.CacheVinTripList;
import com.yutong.clw.beans.vehicle_real.StuSiteNote;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.IParentsDAO;
import com.yutong.clw.service.ParentsBuildSQL;
import com.yutong.clw.service.buffer.DBBuffer;

public class VinTripCacheManager {
	
	private Logger log = LoggerFactory.getLogger(VehicleRealCacheManager.class);
	private final static String NAME = "<VinTripCacheManager>";
	
	//拼接sql语句
	public static final VinTripCacheManager vinTripCacheManager = new VinTripCacheManager();
	public static VinTripCacheManager getInstance() {
		return vinTripCacheManager;		
	}

	private IParentsDAO parentsDAO;
	
	public static Map<String, CacheVinTripList> infoMap;
	private VinTripCacheManager()
    {
        infoMap = new HashMap<String,CacheVinTripList>();
        //allInfoMap = new HashMap<String,List<VehicleReal>>();
    }
    public static int getDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);  
    	return cal.get(Calendar.DAY_OF_WEEK); 
    }
    /**
     * 凌晨清除缓存   并且 将昨天的闹钟提醒（设置的重复次数为零的）有效值置为无效，即valid_flag字段由1置为0
     */
    public synchronized void delInit()
    {
    	 List<StuSiteNote> list = parentsDAO.delVinTrip();
    	 if (list == null || list.size() <= 0)
         {
             return;
         }
    	 //CacheVinTripList vr =new CacheVinTripList();
    	 for (StuSiteNote vehiclereal : list)
    	 {
    		 String key =Constant.STUSITENOTE + vehiclereal.getVehicle_vin()+ vehiclereal.getTrip_id();
 	         deleteVehicleRealInfo(key);
    	 }
    	 DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().updateEmpNote());
    	 return;
             
    }
	public synchronized void init()
    {
        //MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        //MDC.put("modulename", "[vehicleRealCacheManager]");

        log.info("<VinTripCacheManager>开始初始化车辆行程信息");
        Date now = new Date();
        int weekDay=getDay(now)-1;//获取当天是星期几，星期一是第一天
        if(weekDay < 1 )
        	weekDay = 7;
        //List<VehicleReal> list = parentsDAO.getVehicleRealInfo();
        List<StuSiteNote> list = parentsDAO.getVinTrip(weekDay);    //需要简化，不需要那么复杂
       /* for (StuSiteNote siteNote : list)
    	{
    		List<StuSiteNote> listNote = parentsDAO.getVinTripList(siteNote.getVehicle_vin(),siteNote.getTrip_id(),weekDay);
    		
    		CacheVinTripList cache = new CacheVinTripList();
           
            cache.setVehicle_vin(siteNote.getVehicle_vin());
            cache.setTrip_id(siteNote.getTrip_id());
            cache.setStuSiteNoteList(listNote);
            //cacheVinTripList.add(cache);
            //StuSiteNoteCacheManager.getInstance().SyncSiteNoteInfoValue(Constant.OFF, Constant.STUSITENOTE+siteNote.getVehicle_vin()+siteNote.getTrip_id(), ci);
    	}*/
        if (list == null || list.size() <= 0)
        {
            return;
        }
        CacheVinTripList vr =new CacheVinTripList();
        if (Constant.isfirstvintriprealload)
        {

            for (StuSiteNote vehiclereal : list)
            {

            		List<StuSiteNote> listNote = parentsDAO.getVinTripList(vehiclereal.getVehicle_vin(),vehiclereal.getTrip_id(),weekDay);
	            	vr.setVehicle_vin(vehiclereal.getVehicle_vin());
	            	vr.setTrip_id(vehiclereal.getTrip_id());
	            	vr.setStuSiteNoteList(listNote);
	                String keyVehicleInfo = Constant.STUSITENOTE + vehiclereal.getVehicle_vin()+ vehiclereal.getTrip_id();
	                addVehicleRealIntoCache(keyVehicleInfo, vr);
            	
            }
            Constant.isfirstvintriprealload =false;
        }
        else
        {

            for (StuSiteNote vehiclereal : list)
            {
                
	                List<StuSiteNote> listNote = parentsDAO.getVinTripList(vehiclereal.getVehicle_vin(),vehiclereal.getTrip_id(),weekDay);
	            	vr.setVehicle_vin(vehiclereal.getVehicle_vin());
	            	vr.setTrip_id(vehiclereal.getTrip_id());
	            	vr.setStuSiteNoteList(listNote);
	                String keyVehicleInfo = Constant.STUSITENOTE + vehiclereal.getVehicle_vin()+ vehiclereal.getTrip_id();
	                
	                SyncVehicleRealInfoValue(Constant.OFF, keyVehicleInfo, vr);
                
            }
            
        }
        //log.info(NAME + "本次启动共加载" + list.size() + "车辆实时信息缓存");
        list.clear();
        list=null;
   
    }
	
	
	 @SuppressWarnings("unchecked")
	    public synchronized CacheVinTripList getValue(String key)
	    {
	        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
	        {
	            Object o = Constant.getMemcachedClient().getObject(key);
	            if (o != null && !o.equals(""))
	            {
	                return (CacheVinTripList) o;
	            }
	            else
	            {
	                return null;
	            }
	        }
	        else
	        {
	            return infoMap.get(key);
	        }
	    }
	 public synchronized CacheVinTripList SyncVinTripValue(String type, String key, CacheVinTripList vehicleReal)
	    {
	        if (type.equals(Constant.OFF))
	        {
	        	CacheVinTripList vr = getValue(key);
	            deleteVehicleRealInfo(key);
	            addVehicleRealIntoCache(key,vehicleReal);
	            return null;
	        }
	        else if (type.equals(Constant.ON))
	        {
	            return getValue(key);
	        }
	        else
	        {
	            log.error(NAME + "SyncVehicleRealInfoValue传入的类型错误");
	            return null;
	        }
	    }
	 /**
	     * 将车辆行程加入缓存
	     * 
	     * @param cidList
	     */
	    public synchronized void addVehicleRealIntoCache(String key,CacheVinTripList vehicleReal)
	    {
	        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
	        {
	            Constant.getMemcachedClient().insert(key, vehicleReal);
	        }
	        infoMap.put(key, vehicleReal);
	    }
	    
	    
	    public synchronized CacheVinTripList SyncVehicleRealInfoValue(String type, String key, CacheVinTripList vehicleReal)
	    {
	        if (type.equals(Constant.OFF))
	        {
	            deleteVehicleRealInfo(key);
	            addVehicleRealIntoCache(key,vehicleReal);
	            return null;
	        }
	        else if (type.equals(Constant.ON))
	        {
	            return getValue(key);
	        }
	        else
	        {
	            log.error(NAME + "SyncVehicleRealInfoValue传入的类型错误");
	            return null;
	        }
	    }
	 public void deleteVehicleRealInfo(String key)
	    {
	        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
	        {
	            Constant.getMemcachedClient().delete(key);
	        }
	        infoMap.remove(key);
	    }
	public void setParentsDAO(IParentsDAO parentsDAO)
    {
        this.parentsDAO = parentsDAO;
    }
}

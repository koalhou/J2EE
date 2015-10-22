package com.neusoft.parents.cachemanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.dao.impl.ParentsDAO;
import com.neusoft.parents.utils.CacheUtils;

public class VehicleRealCacheManager
{
    private Logger log = LoggerFactory.getLogger(VehicleRealCacheManager.class);

    private final static String NAME = "<VehicleRealCacheManager>";

    private static final VehicleRealCacheManager vehicleRealCacheManager = new VehicleRealCacheManager();


    private IParentsDAO parentsDAO;

    public static Map<String, VehicleReal> infoMap;
    
    public static Map<String, List<VehicleReal>> allInfoMap;

    private VehicleRealCacheManager()
    {
        infoMap = new HashMap<String,VehicleReal>();
        allInfoMap = new HashMap<String,List<VehicleReal>>();
    }

    public static VehicleRealCacheManager getInstance()
    {
        return vehicleRealCacheManager;
    }

    public synchronized void init()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[vehicleRealCacheManager]");

        log.info("<vehicleRealCacheManager>开始初始化车辆的信息缓存。。");
   
        List<VehicleReal> list = parentsDAO.getVehicleRealInfo();
        if (list == null || list.size() <= 0)
        {
            log.debug(NAME + "从数据库未查出车辆实时信息。。");
            return;
        }
        if (Constant.isfirstvehiclerealload)
        {
//            addAllVehicleRealIntoCache(Constant.ALLVEHICLEREAL,list);
            for (VehicleReal vehiclereal : list)
            {
                String keyVehicleReal = Constant.VEHICLEREAL + vehiclereal.getVehicle_vin();
                String keyVehicleInfo = Constant.LIU + vehiclereal.getVehicle_vin();
                addVehicleRealIntoCache(keyVehicleReal, vehiclereal);
                addVehicleRealIntoCache(keyVehicleInfo, vehiclereal);
            }
            Constant.isfirstvehiclerealload =false;
        }
        else
        {
//            SyncAllVehicleRealInfoValue(Constant.OFF, Constant.ALLVEHICLEREAL, list);
            for (VehicleReal vehiclereal : list)
            {
                String keyVehicleReal = Constant.VEHICLEREAL + vehiclereal.getVehicle_vin();
                String keyVehicleInfo = Constant.LIU + vehiclereal.getVehicle_vin();
                SyncVehicleRealInfoValue(Constant.OFF, keyVehicleReal, vehiclereal);
                SyncVehicleRealInfoValue(Constant.OFF, keyVehicleInfo, vehiclereal);
            }
            
        }
        log.info(NAME + "本次启动共加载" + list.size() + "车辆实时信息缓存");
        list.clear();
        list=null;
        
        
    }

    /**
     * 将车辆实时信息加入缓存
     * 
     * @param cidList
     */
    public synchronized void addVehicleRealIntoCache(String key,VehicleReal vehicleReal)
    {
        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
        {
            Constant.getMemcachedClient().insert(key, vehicleReal);
        }
        infoMap.put(key, vehicleReal);
        
        VehicleReal vr = getValue(key);
    }
    
    
//    public synchronized void addAllVehicleRealIntoCache(String key,List<VehicleReal> list)
//    {
//        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
//        {
//            Constant.getMemcachedClient().insert(key, list);
//        }
//        allInfoMap.put(key, list);
//    }

    @SuppressWarnings("unchecked")
    public synchronized VehicleReal getValue(String key)
    {  	
        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
        {
            Object o = Constant.getMemcachedClient().getObject(key);
            if (o != null && !o.equals(""))
            {
                return (VehicleReal) o;
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
    
    
    
//    public synchronized VehicleReal getValueByMap(String key)
//    {
//    	return infoMap.get(key);
//    }
    
//    public synchronized List<VehicleReal> getAllValue(String key)
//    {
//        if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
//        {
//            Object o = Constant.getMemcachedClient().getObject(key);
//            if (o != null && !o.equals(""))
//            {
//                return (List<VehicleReal>) o;
//            }
//            else
//            {
//                return null;
//            }
//        }
//        else
//        {
//            return allInfoMap.get(key);
//        }
//    }

 
    public synchronized VehicleReal SyncVehicleRealInfoValue(String type, String key, VehicleReal vehicleReal)
    {
//    	if("liuja_cache11Z1Y111111111111".equals(key))
//    	{
//    		log.info("222222222222222222222222222222222" + vehicleReal.getFlag());  //偏航标识
//    	}
        if (type.equals(Constant.OFF))
        {
        	VehicleReal vr = getValue(key);
        	//VehicleReal vr2 = getValueByMap(key);
        	
//        	if("liuja_cache11Z1Y111111111111".equals(key))
//        	{
//        		log.info("4444444444444444444444444444444444444" + vr.getFlag());
//        		log.info("5555555555555555555555555555555555555" + vr2.getFlag());
//        		log.info("8888888888888888888888888888888888888" + vr.getTerminal_time());
//        		log.info("9999999999999999999999999999999999999" + vr2.getStart_overtime());
//
//        	}
        	
        	//行程不重新覆盖
        	if(vr!=null && vr.getTrip_id()!=null && vehicleReal.getTrip_id()==null){
        		vehicleReal.setTrip_id(vr.getTrip_id());
        		vehicleReal.setRoute_id(vr.getRoute_id());
        	}
        	//标识不重新覆盖
        	if(vr!=null && vr.getFlag()!=null && vehicleReal.getFlag()==null){
        		vehicleReal.setFlag(vr.getFlag());
        	}
        	//开始时间不重新覆盖
        	if(vr!=null && vr.getStart_overtime()!=null && vehicleReal.getStart_overtime()==null){
        		vehicleReal.setStart_overtime(vr.getStart_overtime());
        	}
        	//结束时间不重新覆盖
        	if(vr!=null && vr.getEnd_overtime()!=null && vehicleReal.getEnd_overtime()==null){
        		vehicleReal.setEnd_overtime(vr.getEnd_overtime());
        	}
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
    
//    public synchronized List<VehicleReal> SyncAllVehicleRealInfoValue(String type, String key, List<VehicleReal> vehicleReal)
//    {
//        if (type.equals(Constant.OFF))
//        {
//            deleteVehicleRealInfo(key);
//            addAllVehicleRealIntoCache(key,vehicleReal);
//            return null;
//        }
//        else if (type.equals(Constant.ON))
//        {
//            return getAllValue(key);
//        }
//        else
//        {
//            log.error(NAME + "SyncVehicleRealInfoValue传入的类型错误");
//            return null;
//        }
//    }

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

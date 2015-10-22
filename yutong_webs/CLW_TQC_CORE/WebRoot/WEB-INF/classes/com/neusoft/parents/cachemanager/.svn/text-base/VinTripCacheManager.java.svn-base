package com.neusoft.parents.cachemanager;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.bean.CacheVinTripList;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.service.ParentsBuildSQL;
import com.neusoft.tqcpt.service.FtlyBuildSQL;

public class VinTripCacheManager {
	
	private Logger log = LoggerFactory.getLogger(VinTripCacheManager.class);
	private final static String NAME = "<VinTripCacheManager>";
	
	//拼接sql语句
	public static final VinTripCacheManager vinTripCacheManager = new VinTripCacheManager();
	public static VinTripCacheManager getInstance() {
		return vinTripCacheManager;		
	}

	private IParentsDAO parentsDAO;
	
	private TransactionDao transactionDao;
	
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
		
		String reportServer = null; 
		transactionDao = (TransactionDao) SpringBootStrap.getInstance().getBean("transactionDao");
		boolean b = false;
    	try
        {
    		int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
    		reportServer = transactionDao.getReportServer();    		
    		if (reportServer.equals(Constant.CORE_ID)) {// 是指定的报表计算服务器
				b = true;
			} else {
				// 是否为主核心，为了保证主核心业务的正常，核心数量大于1时主核心不参与报表计算。
				ActiveCoreService acs = ActiveCoreService.getInstance();
				b = acs.getMainCore(Constant.CORE_ID, coreActive);
				// 获取活跃核心数量
				int corenum = transactionDao.queryReportServer(coreActive);
				if (corenum == 1) {
					b = true;
				} else {
					int rcore = transactionDao.queryLiveReportS(coreActive,reportServer);
					if (rcore == 0 && !b) {// 非主核心设置自己为报表服务器
						transactionDao.setReportServer(Constant.CORE_ID);
						b = true;
					}
					b = false;
				}
			}
    		
    		log.info(NAME+"判断是本服务器是否为【员工版】消息推送服务器"+b);
			if (!b) {
				log.info(NAME+"本机不是【员工版】消息推送服务器。");
				return;
			}

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
        StuSiteNote a = new StuSiteNote();
        StuSiteNote b1 = new StuSiteNote();
        if (Constant.isfirstvintriprealload)
        {
        	List<StuSiteNote> listNote = new LinkedList<StuSiteNote>();
            for (StuSiteNote vehiclereal : list)
            {
            	a = vehiclereal;
            	if(b1.getVehicle_vin() == null){
            		b1 = vehiclereal;
            	}
            	if(a != null && b1 != null && a.getVehicle_vin().equals(b1.getVehicle_vin()) && a.getTrip_id().equals(b1.getTrip_id())){
            		StuSiteNote c = new StuSiteNote();
            		c = a;
            		listNote.add(c);
            		
            	}else{
            		vr.setVehicle_vin(listNote.get(listNote.size()-1).getVehicle_vin());
            		vr.setTrip_id(listNote.get(listNote.size()-1).getTrip_id());
            		//vr.setVehicle_vin(vehiclereal.getVehicle_vin());
	            	//vr.setTrip_id(vehiclereal.getTrip_id());
	            	vr.setStuSiteNoteList(listNote);
	                String keyVehicleInfo = Constant.STUSITENOTE + vr.getVehicle_vin()+ vr.getTrip_id();
	                addVehicleRealIntoCache(keyVehicleInfo, vr);
	                listNote.removeAll(listNote);
	                StuSiteNote c = new StuSiteNote();
            		c = a;
            		listNote.add(c);
            	}
            	b1 = a;

            		//List<StuSiteNote> listNote = parentsDAO.getVinTripList(vehiclereal.getVehicle_vin(),vehiclereal.getTrip_id(),weekDay);
	            	
            	
            }
            Constant.isfirstvintriprealload =false;
        }
        else
        {
        	List<StuSiteNote> listNote = new LinkedList<StuSiteNote>();

            for (StuSiteNote vehiclereal : list)
            {
            	
            	
            	a = vehiclereal;
            	if(b1.getVehicle_vin() == null){
            		b1 = vehiclereal;
            	}
            	if(a != null && b1 != null && a.getVehicle_vin().equals(b1.getVehicle_vin()) && a.getTrip_id().equals(b1.getTrip_id())){
            		StuSiteNote c = new StuSiteNote();
            		c = a;
            		listNote.add(c);
            		
            		
            	}else{
            		vr.setVehicle_vin(listNote.get(listNote.size()-1).getVehicle_vin());
            		vr.setTrip_id(listNote.get(listNote.size()-1).getTrip_id());
            		//vr.setVehicle_vin(vehiclereal.getVehicle_vin());
	            	//vr.setTrip_id(vehiclereal.getTrip_id());
	            	vr.setStuSiteNoteList(listNote);
	                String keyVehicleInfo = Constant.STUSITENOTE + vr.getVehicle_vin()+ vr.getTrip_id();
	                
	                SyncVehicleRealInfoValue(Constant.OFF, keyVehicleInfo, vr);
	                listNote.removeAll(listNote);
	                StuSiteNote c = new StuSiteNote();
            		c = a;
            		listNote.add(c);
            	}
            	b1 = a;
                
	                //List<StuSiteNote> listNote = parentsDAO.getVinTripList(vehiclereal.getVehicle_vin(),vehiclereal.getTrip_id(),weekDay);
	            	
                
            }
            
        }
        //log.info(NAME + "本次启动共加载" + list.size() + "车辆实时信息缓存");
        list.clear();
        list=null;
        
        }    	
	    catch (Exception e)
	    {   
	        log.error(NAME+"更新缓存失败：", e);
	    }
   
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
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	
}

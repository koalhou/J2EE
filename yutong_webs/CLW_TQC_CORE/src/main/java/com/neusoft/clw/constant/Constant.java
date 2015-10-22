/*******************************************************************************
 * @(#)Constant.java 2009-2-25
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.constant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.neusoft.SchoolBus.vncs.domain.EnterPriseBean;
import com.neusoft.SchoolBus.vncs.domain.RouteSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.domain.VehicleBean;
import com.neusoft.tlm.memcache.process.MemcacheClientHandler;

public final class Constant {

    private Constant() {

    }

    public static  String space = " ";
    public static  final String shaft = "-";
    public static  long SECOND = 1000;
    public static final String PUSH_OK = "ok";
	public static final String MOBILEINFO = "mobileinfo";
	public static final String TUISONGRULE = "tsrule";
	public static final String PMRULE = "pmrule";
	public static final String MOBILE_CLIENT_VERSION = "mcap-1.0.0";
	public static final String SICHEN = "sichen";
	public static final String DRIVERINFO = "driverinfo";
	public static final String ROUTE = "route";
	public static final String INVALID = "无效";
	public final static String VERSION_ID = "mcap-1.0.0";

//    @SuppressWarnings("unchecked")
//	public static ConcurrentHashMap timeMap = new ConcurrentHashMap();
//    
//    public static  String ENABLE = "enable";
//
//    public static  String VG_PROTOCOL_ID = "vg";
//
//    public static  String GAS_PROTOCOL_ID = "gas";
//
//    public static  int NOMAL_SEND_TYPE = 1;
//
//    public static  int SM_SEND_TYPE = 2;
//    public static  int analyzeThreadPoolSize=50;//线程池数量
//    public static  int ThreadSleep=100;
//
//    public static  int DISCONNECT_STATE = 1;

//    public static  int PLATFORMID = 1;

//    public static  String OPER_ID = "注销";
    
//    public static String isfirstvehicle_regionload = Config.props.getProperty("is.first.vehicle_region.cache.load");
    
    public static String isfirstvihicle_terminalload = Config.props.getProperty("is.first.vehicle_terminal.cache.load");
    
    public static String isfirstvehicleload = Config.props.getProperty("is.first.vehicle.cache.load");

    public static String isfirststudentload = Config.props.getProperty("is.first.student.cache.load");
    
    public static String isfirststudentidload = Config.props.getProperty("is.first.studentid.cache.load");
    
    public static String isfirstmsgnumload = Config.props.getProperty("is.first.msgnum.cache.load");
    
    public static boolean isfirstsiteload =true;
    
    public static boolean isfirstmoshiload =true;
    
    public static boolean isfirstridingplanload = true;
    
    public static String upd_terminal_cache_time = null;
    
    
    public static String upd_pmrule_cache_time = null;
    
    public static String isfirstmobileload = Config.props.getProperty("is.first.mobile.cache.load");
    public static boolean pmRuleload = true;
    
//    public static String upd_vr_cache_time = null;

	public static String upd_vehicle_cache_time = null;
	
	public static String upd_student_cache_time = null;
	
	public static String upd_msg_cache_time = null;
	
	public static String upd_site_cache_time = null;
	
	public static String upd_moshi_cache_time = null;
	
	public static String upd_stusms_cache_time = null;
	
	public static String upd_xd_cache_time = null;
	
	public static String CORE_ID =Config.props.getProperty("core_id");
	
	public static String isstartMemcache = Config.props.getProperty("IsStartMemCache");
	
//	public static boolean iscontinue_startYtb = true;
	
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap<String, Map> cacheMap = new ConcurrentHashMap<String, Map>();
	
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap respMap = new ConcurrentHashMap();
	
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap ytbsendcmdMap = new ConcurrentHashMap();
	
	
	
	
	// liuja增加start
	public static boolean isfirststuparentsload = true;
	public static boolean isfirststustateload = true;
	public static boolean isfirstvehiclerealload = true;
	public static boolean isfirstvehiclerealload1 = true;
	public static boolean isfirstvintriprealload = true;
	public static boolean isfirstruleload = true;
	public static final String STUPARENTSINFO = "app_stuparentsinfo";
	public static final String STUSTATEINFO = "app_stustateinfo";
	public static final String VEHICLEREAL = "app_vehiclereal";
//	public static final String VEHICLEINFO = "app_vehicleinfo";
	public static final String LIU = "liuja_cache";
//	public static final String ALLVEHICLEREAL = "app_allvehiclereal";
	public static final String STUSITENOTE = "app_stusitenote";
	public static final String PUSHSTATION = "app_emppushnote";   //车辆的缓存Key
	public static final String PUSHRULE = "app_PUSHRULES";
	public static int isthread = 0;
	// liuja增加end
	
	// liuja增加start
	public static final String REMINDTYPE_TIME = "0";
	public static final String REMINDTYPE_MILEAGE = "1";
	public static final String REMINDTYPE_SITE = "2";
	// liuja增加end
	
	
//    //司机刷卡记录缓存	
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap drivercmdMap = new ConcurrentHashMap();	
//	
//    //学生刷卡车辆VIN记录缓存	
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap ShuaKaTerminalcmdMap = new ConcurrentHashMap();	
	//车联网急加速缓存
//	public static ConcurrentHashMap clwrapidcmdMap = new ConcurrentHashMap();
	//车联网车辆超速记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwoverspeedcmdMap = new ConcurrentHashMap();
//	//车联网登陆记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwloginrecordcmdMap = new ConcurrentHashMap();
//	//车联网开关量记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwonoffcmdMap = new ConcurrentHashMap();
//	//车联网秒数据记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwseccmdMap = new ConcurrentHashMap();
//	
//	//车联网1分钟数据记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwminute1cmdMap = new ConcurrentHashMap();
//	
//	//车联网5分钟数据记录缓存
//	@SuppressWarnings("unchecked")
//	public static ConcurrentHashMap clwminute5cmdMap = new ConcurrentHashMap();
	//企业短信配额本地缓存
	@SuppressWarnings("unchecked")
	public static ConcurrentHashMap msgMap = new ConcurrentHashMap();
	
	public static  String ON = "1";
	public static  String OFF = "0";
	
	public static  String F2 = "FF";
	public static  String F3 = "FFF";
	public static  String F4 = "FFFF";
	public static  String F5 = "FFFFF";
	public static  String F6 = "FFFFFF";
	public static  String F7 = "FFFFFFF";
	public static  String F8 = "FFFFFFFF";
	public static  String F9 = "FFFFFFFFF";
	public static  String F10 ="FFFFFFFFFF";
	public static  String F12 ="FFFFFFFFFFFF";
	public static  String F16 = "FFFFFFFFFFFFFFFF";
	
	/**************************************** 疲劳驾驶算法 ****************************/
	
	/** 疲劳驾驶报警状态：1-疲劳驾驶预警*/
    public static  String FAG_DRIVE_FOREWARN = "1";
	/** 疲劳驾驶报警状态：2-疲劳驾驶报警*/
	public static  String FAG_DRIVE_ALERT_ON = "2";
    /** 疲劳驾驶报警状态：0-疲劳驾驶报警关*/
    public static  String FAG_DRIVE_ALERT_OFF = "0";
	
    /** 疲劳驾驶算法中，加入第二天几个小时的源数据进行运算*/
    public static  int HOURS_OF_GET_NEXT_SOURCE_DATA = 12;
    
    /** 一次疲劳驾驶的最大判断间隔时间(单位：毫秒)，当前为12小时*/
    public static  int MAX_PERIOD_TIME_OF_FD_CAL = 43200000;
    
    /** 疲劳驾驶时长的初始值(单位：秒),当前为4小时*/
    public static  int INIT_FAG_DRIVE_SEC = 14400;
    /** 疲劳驾驶时长的初始值(单位：小时),当前为4小时*/
    public static  int INIT_FAG_DRIVE_HOURS = 4;
    
    /** 可以取消一次疲劳驾驶的最少停车时间（单位：毫秒），当前为20分钟*/
    public static  int MIN_PARKING_TIME_OF_CAN_FAG = 1200000;
    /** 可以取消一次疲劳驾驶的最少停车时间（单位：分钟），当前为20分钟*/
    public static  int MIN_PARKING_MINUTES_OF_CAN_FAG = 20;
    
    public static  String DRIVER = "driver";
    public static  String ShuaKaTerminal = "ShuaKaTerminal";
    public static  String OVERLOAD = "overload";
    public static String ALARM = "ALARM";
    public static  String ENTERPRISE = "ENTERPRISE";
    public static boolean sms = true;
//    public static  String SEC_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("sec.fileParent");
//    public static  String MUN1TE1_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("munite1.fileParent");
//    public static  String MUN1TE5_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("munite5.fileParent");
//    public static  String LOGIN_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("login.fileParent");
//    public static  String OVERSPEED_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("overspeed.fileParent");
//    public static  String ONOFF_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("onoff.fileParent");
//    public static  String RAPID_FILEPARENT = System.getProperty("user.dir")+System.getProperty("file.separator")+Config.props.getProperty("rapid.fileParent");
//    
//    
//    public static  String datePatten = Config.props.getProperty("clw.filename.DatePattern");
//    public static  int scheduledTime = Integer.parseInt(Config.props.getProperty("clw.scheduledTime"));
//    public static  boolean stdout = Boolean.valueOf(Config.props.getProperty("clw.stdout"));
//    public static  String BACKUP_SUFFIX = Config.props.getProperty("clw.filename.backup.suffix");
//    public static  String BACKUP_DIR = Config.props.getProperty("clw.filename.backup.dir");
//	public static List<String> array = new ArrayList<String>();
	
	private static MemcacheClientHandler memcachedClient;

//	public static String upd_sim_cache_time = null;

	public static boolean studentload = true;

	public static void setMemcachedClient(MemcacheClientHandler memcachedClient) {
		Constant.memcachedClient = memcachedClient;
	}

	public static MemcacheClientHandler getMemcachedClient() {
		return memcachedClient;
	}	
	
	public static  String SITE = "site";
	public static  String STUDENT = "student";
	public static  String errorQueuesize = Config.props.getProperty("error.blockingQueue.size");

	public static boolean vssload = true;
	public static boolean bool = true;
	public static boolean terminalload = true;
	public static boolean vehicleload = true;
	public static boolean siteload = true;
	public static boolean enterprise_smgatewayload = true;
	public static boolean routesiteload = true;
	public static boolean stusmsload = true;
	
	public static ConcurrentHashMap<String, TerminalBean> terminalMap = new ConcurrentHashMap<String, TerminalBean>();

	public static ConcurrentHashMap<String, VehicleBean> vehicleMap = new ConcurrentHashMap<String, VehicleBean>();
	
	public static ConcurrentHashMap<String, XcStudentBean> xcstudentMap = new ConcurrentHashMap<String, XcStudentBean>();
	
	public static ConcurrentHashMap<String, String> xcstudentidMap = new ConcurrentHashMap<String, String>();

	public static ConcurrentHashMap<String, XcSiteBean> xcsiteMap = new ConcurrentHashMap<String, XcSiteBean>();

	public static ConcurrentHashMap<String, EnterPriseBean>  xcenterpriseMap = new ConcurrentHashMap<String, EnterPriseBean>();

	public static ConcurrentHashMap<String, XcvsseBean> xcvssMap = new ConcurrentHashMap<String, XcvsseBean>();

	public static ConcurrentHashMap<String, RouteSiteBean> xcroutesiteMap = new ConcurrentHashMap<String, RouteSiteBean>();

	public static ConcurrentHashMap<String, List<XcStuSmsBean>> xcStuSmsMap = new ConcurrentHashMap<String, List<XcStuSmsBean>>();
}

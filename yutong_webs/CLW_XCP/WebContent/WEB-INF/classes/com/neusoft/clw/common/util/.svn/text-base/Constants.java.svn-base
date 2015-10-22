/**********************************************************************
 * @(#)UserOperateLogVo.java Sep 16, 2008
 * 常量类
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 ********/
package com.neusoft.clw.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.1 $ Sep 16, 2008 3:05:25 PM
 */
public final class Constants {

    // private static final Log log = LogFactory.getLog(Constants.class);
    private static final Logger log = Logger.getLogger(Constants.class);

    /**
     * Utility classes should not have a public or default constructor
     */
    private Constants() {

    }

    /** 上传文件根目录 **/
    public static final String UPLOAD_PATH_BASE = "/tmp/";

    /** map key **/
    public static final String MAP_KEY = "f6c97a7f64063cfee7c2dc2157847204d4"
            + "dbf0938d58820f89940a1a68832c0cdd53109727cfa622";

    /** 登陆用户session key值 **/
    public static final String USER_SESSION_KEY = "adminProfile";

    public static final String PARENT_USER_KEY = "5";

    public static final String ONWER_USER_KEY = "3";

    public static final String ENCODE_USER_KEY = "1";

    public static final String LOG_USE_ID = "logid";

    // 默认起始分页的记录条件数
    public static final int DEFAULT_PAGE_SIZE = 5;

    public static final String LOCKED_USER_STATUS = "1";

    public static final int size = 2 * 1024 * 1024;

    public static final int area = 100;

    public static final String ADMIN_ROLE_ID = "administrator"; // �����û���ɫID

    public static final String ADMIN_USER_ID = "admin"; // �����û�ID

    public static final String PER_URL_LIST = "perUrlList"; // SESSION�е�Ȩ��URL

    public static final String ENCODE = System.getProperty("file.encoding");

    public static final String DELETE = "删除";

    public static final String UPDATE = "更新";

    public static final String SELECT = "查询";

    public static final String INSERT = "新建";

    public static final String IMPORT = "导入";

    public static final String EXPORT = "导出";

    public static final String LOGIN = "登入";

    public static final String LOGOUT = "注销";
    
    public static final String DOWNLOAD = "下载";

    public static String MAIL_SERVER_IP = null;

    public static int MAIL_SERVER_PORT;

    public static String MAIL_SERVER_USER = null;

    public static String MAIL_SERVER_PASSWORD = null;

    public static String MAIL_SENDPASSWROD_SENDER_ADDRESS = null;

    public static String REPORT_ADDRESS = null;

    public static String PARAMETERTYPE = "";

    public static String CONTENTCONVERT = "";

    public static String headBatchId = null;

    public static String MAIL_FILE_PATH = null;

    public static String ISSSL = null;

    public static String M2M_REGISTED_AREA = null;

    // �澯֪ͨ
    public static String NET_SEND_PERIOD = null;

    // �ʼ��澯
    public static String ADDRESSER = null;

    public static String EMAIL = null;

    public static String SERVICEIP = null;

    public static String SERVICEPORT = null;

    public static String SERVICEPASSWORD = null;

    // ���Ÿ澯
    public static String GATEWAYIP = null;

    public static int GATEWAYPORT;

    public static String SPCIALSERNUM = null;

    public static String USERID = null;

    public static String USERNAME = null;

    public static String PASSWORD = null;

    /** 是否编码 **/
    public static final String CON_YES_CODE = "0";

    public static final String CON_YES_NAME = "是";

    public static final String CON_NO_CODE = "1";

    public static final String CON_NO_NAME = "否";

    public static final Map < String, String > CONDITIONS_SYS_MAP = new HashMap < String, String >();
    static {
        CONDITIONS_SYS_MAP.put(CON_NO_CODE, CON_NO_NAME);
        CONDITIONS_SYS_MAP.put(CON_YES_CODE, CON_YES_NAME);

    }

    /** 月份编码 **/
    public static final String MON_January_CODE = "1";

    public static final String MON_January_NAME = "一月份";

    public static final String MON_February_CODE = "2";

    public static final String MON_February_NAME = "二月份";

    public static final String MON_March_CODE = "3";

    public static final String MON_March_NAME = "三月份";

    public static final String MON_April_CODE = "4";

    public static final String MON_April_NAME = "四月份";

    public static final String MON_May_CODE = "5";

    public static final String MON_May_NAME = "五月份";

    public static final String MON_June_CODE = "6";

    public static final String MON_June_NAME = "六月份";

    public static final String MON_July_CODE = "7";

    public static final String MON_July_NAME = "七月份";

    public static final String MON_August_CODE = "8";

    public static final String MON_August_NAME = "八月份";

    public static final String MON_September_CODE = "9";

    public static final String MON_September_NAME = "九月份";

    public static final String MON_October_CODE = "10";

    public static final String MON_October_NAME = "十月份";

    public static final String MON_November_CODE = "11";

    public static final String MON_November_NAME = "十一月份";

    public static final String MON_December_CODE = "12";

    public static final String MON_December_NAME = "十二月份";

    public static final Map < String, String > MONTH_SYS_MAP = new HashMap < String, String >();
    static {
        MONTH_SYS_MAP.put(MON_January_CODE, MON_January_NAME);
        MONTH_SYS_MAP.put(MON_February_CODE, MON_February_NAME);
        MONTH_SYS_MAP.put(MON_March_CODE, MON_March_NAME);
        MONTH_SYS_MAP.put(MON_April_CODE, MON_April_NAME);
        MONTH_SYS_MAP.put(MON_May_CODE, MON_May_NAME);
        MONTH_SYS_MAP.put(MON_June_CODE, MON_June_NAME);
        MONTH_SYS_MAP.put(MON_July_CODE, MON_July_NAME);
        MONTH_SYS_MAP.put(MON_August_CODE, MON_August_NAME);
        MONTH_SYS_MAP.put(MON_September_CODE, MON_September_NAME);
        MONTH_SYS_MAP.put(MON_October_CODE, MON_October_NAME);
        MONTH_SYS_MAP.put(MON_November_CODE, MON_November_NAME);
        MONTH_SYS_MAP.put(MON_December_CODE, MON_December_NAME);

    }

    /** 性别编码 **/
    public static final String SEX_MALE_CODE = "0";

    public static final String SEX_MALE_NAME = "男";

    public static final String SEX_FEMALE_CODE = "1";

    public static final String SEX_FEMALE_NAME = "女";

    /** 车联网系统 **/
    public static final String CLW_P_CODE = "1";

    /** 校车系统 **/
    public static final String XC_P_CODE = "1";

    /** 数据同步 **/
    public static final String CLW_SYN_CODE = "05";

    /** 车联网 **/
    public static final String CLW_P_NAME = "车联网系统";

    /** 用户类型 **/
    public static final String USERTYPE_E_CODE = "1";

    public static final String USERTYPE_E_NAME = "系统用户";

    public static final String USERTYPE_G_CODE = "0";

    public static final String USERTYPE_G_NAME = "管理系统用户";

    public static final String USERTYPE_Y_CODE = "2";

    public static final String USERTYPE_Y_NAME = "宇通杯用户";

    public static final String USERTYPE_V_CODE = "3";

    public static final String USERTYPE_V_NAME = "车主用户";

    public static final String USERTYPE_P_CODE = "5";

    public static final String USERTYPE_P_NAME = "家长用户";

    /** 告警状态 **/
    public static final String ALARM_E_CODE = "1";

    public static final String ALARM_E_NAME = "已处理";

    public static final String ALARM_V_CODE = "0";

    public static final String ALARM_V_NAME = "未处理";

    public static final Map < String, String > SEX_SYS_MAP = new HashMap < String, String >();

    public static final Map < String, String > USERTYPE_SYS_MAP = new HashMap < String, String >();

    public static final Map < String, String > ALARM_SYS_MAP = new HashMap < String, String >();

    public static final Map < String, String > RELATIVE_TYPE_MAP = new HashMap < String, String >();

    // public static final Map < String, String > ALARM_TYPE_MAP = new HashMap <
    // String, String >();

    // 按TAB页类型分MAP
    public static final Map < String, String > ALARM_TAB1_TYPE_MAP = new HashMap < String, String >();

    public static final Map < String, String > ALARM_TAB2_TYPE_MAP = new HashMap < String, String >();

    public static final Map < String, String > ALARM_TAB3_TYPE_MAP = new HashMap < String, String >();

    public static final Map < String, String > ALARM_OFF_MAP = new HashMap < String, String >();

    public static final Map < String, String > STU_ALARM_TYPE_MAP = new HashMap < String, String >();

    public static final Map < String, String > ALARM_DEAL_FLAG_MAP = new HashMap < String, String >();
    
    public static final Map <String,String> ALARM_TAB_MAP=new HashMap < String, String >();
    
    public static final Map <String,String> ALARM_DEAL_ENABLE_MAP=new HashMap < String, String >();

    public static final String VALUE_KEY = "c";

    public static final String IS_ON_LINE = "200";

    public static final String IS_OFF_LINE = "8000";
    
    public static final String IS_OFF_LINE_PHOTO="8001";

    static{
    	ALARM_DEAL_ENABLE_MAP.put("40", "111_3_1_12_5");
    	ALARM_DEAL_ENABLE_MAP.put("72", "111_3_1_12_5");
    	ALARM_DEAL_ENABLE_MAP.put("32", "111_3_1_12_6");
    	ALARM_DEAL_ENABLE_MAP.put("09", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("10", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("13", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("25", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("26", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("64", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("65", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("66", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("67", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("68", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("69", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("70", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("71", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("88", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("89", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("90", "111_3_1_12_7");
    	ALARM_DEAL_ENABLE_MAP.put("73", "111_3_1_12_8");
    	ALARM_DEAL_ENABLE_MAP.put("74", "111_3_1_12_8");
    	ALARM_DEAL_ENABLE_MAP.put("79", "111_3_1_12_8");
    	ALARM_DEAL_ENABLE_MAP.put("80", "111_3_1_12_8");
    }
    
    static {
    	ALARM_TAB_MAP.put("1","紧急告警");
    	ALARM_TAB_MAP.put("2","超速告警");
    	ALARM_TAB_MAP.put("3","违规驾驶");
    	ALARM_TAB_MAP.put("4","异常乘车");
    }
    
    static {
        ALARM_SYS_MAP.put(ALARM_E_CODE, ALARM_E_NAME);
        ALARM_SYS_MAP.put(ALARM_V_CODE, ALARM_V_NAME);

    }

    static {
        USERTYPE_SYS_MAP.put(USERTYPE_E_CODE, USERTYPE_E_NAME);
        USERTYPE_SYS_MAP.put(USERTYPE_P_CODE, USERTYPE_P_NAME);
    }
    static {
        SEX_SYS_MAP.put(SEX_MALE_CODE, SEX_MALE_NAME);
        SEX_SYS_MAP.put(SEX_FEMALE_CODE, SEX_FEMALE_NAME);

        RELATIVE_TYPE_MAP.put("0", "父亲");
        RELATIVE_TYPE_MAP.put("1", "母亲");
        RELATIVE_TYPE_MAP.put("2", "爷爷");
        RELATIVE_TYPE_MAP.put("3", "奶奶");
        RELATIVE_TYPE_MAP.put("4", "外公");
        RELATIVE_TYPE_MAP.put("5", "外婆");
        RELATIVE_TYPE_MAP.put("6", "其他");
        // InputStream is = getResourceAsStream();
        // if (null != is) {
        // Properties prop = new Properties();
        // try {
        // prop.load(is);
        // /*
        // PARAMETERTYPE = prop.getProperty("parameterType").trim();
        // CONTENTCONVERT = prop.getProperty("contentConvert").trim();
        // MAIL_SERVER_IP = prop.getProperty("MAIL_SERVER_IP").trim();
        // MAIL_SERVER_USER = prop.getProperty("MAIL_SERVER_USER").trim();
        // MAIL_SERVER_PASSWORD =
        // prop.getProperty("MAIL_SERVER_PASSWORD").trim();
        // MAIL_SERVER_PORT =
        // Integer.parseInt(prop.getProperty("mail.smtp.port"));
        // MAIL_SENDPASSWROD_SENDER_ADDRESS = prop.getProperty(
        // "MAIL_SENDPASSWROD_SENDER_ADDRESS").trim();
        // MAIL_FILE_PATH = prop.getProperty("mail.file.path").trim();
        // ISSSL = prop.getProperty("isSSL");
        // REPORT_ADDRESS = prop.getProperty("REPORT_ADDRESS").trim();
        // headBatchId = prop.getProperty("BatchId_Add_Type").trim()
        // + prop.getProperty("BatchId_Head").trim();
        // M2M_REGISTED_AREA = prop.getProperty("M2M_REGISTED_AREA").trim();
        // NET_SEND_PERIOD = prop.getProperty("net.send.period").trim();
        // ADDRESSER = prop.getProperty("addresser").trim();
        // EMAIL = prop.getProperty("email").trim();
        // SERVICEIP = prop.getProperty("serviceIp").trim();
        // SERVICEPORT = prop.getProperty("servicePort").trim();
        // SERVICEPASSWORD = prop.getProperty("servicePassword").trim();
        // GATEWAYIP = prop.getProperty("gateWayIp");
        // GATEWAYPORT = Integer.parseInt(prop.getProperty("gateWayPort"));
        // SPCIALSERNUM = prop.getProperty("specialSerNum");
        // USERID = prop.getProperty("userId");
        // USERNAME = prop.getProperty("userName");
        // PASSWORD = prop.getProperty("password");
        // */
        // } catch (IOException e) {
        // log.error(e.getMessage());
        // }
        // }
    }

    static {
        // ALARM_TAB1_TYPE_MAP.put("40,72", "请选择");
        ALARM_TAB1_TYPE_MAP.put("'40'", "SOS告警");
        ALARM_TAB1_TYPE_MAP.put("'72'", "超载告警");
    }

    static {
        // ALARM_TAB2_TYPE_MAP.put("32,33", "违规驾驶");
        ALARM_TAB2_TYPE_MAP.put("'32'", "超速告警");
        //ALARM_TAB2_TYPE_MAP.put("33", "超时驾驶告警");
        //ALARM_TAB2_TYPE_MAP.put("54", "未鉴权驾驶");
    }

    static {
        // ALARM_TAB3_TYPE_MAP
        // .put("9,10,13,25,26,64,65,66,67,68,69,70,71", "车辆故障");
        ALARM_TAB3_TYPE_MAP.put("'09'", "制动气压告警");
        ALARM_TAB3_TYPE_MAP.put("'10'", "油压告警");
        ALARM_TAB3_TYPE_MAP.put("'13'", "制动蹄片磨损告警");
        ALARM_TAB3_TYPE_MAP.put("'25'", "缓速器高温告警");
        ALARM_TAB3_TYPE_MAP.put("'26'", "仓温告警");
        ALARM_TAB3_TYPE_MAP.put("'64'", "摄像头故障");
        ALARM_TAB3_TYPE_MAP.put("'65'", "TTS模块故障");
        ALARM_TAB3_TYPE_MAP.put("'66'", "终端LCD故障");
        ALARM_TAB3_TYPE_MAP.put("'67'", "终端主电源掉电");
        ALARM_TAB3_TYPE_MAP.put("'68'", "终端主电源欠压");
        ALARM_TAB3_TYPE_MAP.put("'69','70','71'", "GPS故障");
        ALARM_TAB3_TYPE_MAP.put("'88'", "dvr故障告警");
        ALARM_TAB3_TYPE_MAP.put("'89'", "sd卡故障告警");
        ALARM_TAB3_TYPE_MAP.put("'90'", "GPS无效告警");
        
    }

    /*
     * static { ALARM_TYPE_MAP.put("40,72", "紧急告警"); ALARM_TYPE_MAP.put("32,33",
     * "违规驾驶"); ALARM_TYPE_MAP.put("9,10,13,25,26,64,65,66,67,68,69,70,71",
     * "车辆故障"); ALARM_TYPE_MAP.put("32", "超速告警"); ALARM_TYPE_MAP.put("33",
     * "超时驾驶告警"); }
     */

    static {
        ALARM_OFF_MAP.put("40", "0");
        ALARM_OFF_MAP.put("60", "2");
        ALARM_OFF_MAP.put("61", "3");
        ALARM_OFF_MAP.put("59", "4");
        ALARM_OFF_MAP.put("54", "5");
        ALARM_OFF_MAP.put("53", "6");
    }

    static {
        STU_ALARM_TYPE_MAP.put("'73'", "未在规定站点上车");
        STU_ALARM_TYPE_MAP.put("'74'", "未在规定站点下车");
        STU_ALARM_TYPE_MAP.put("'79'", "未刷卡上车");
        STU_ALARM_TYPE_MAP.put("'80'", "未刷卡下车");
    }

    static {
        ALARM_DEAL_FLAG_MAP.put("0", "未处理");
        ALARM_DEAL_FLAG_MAP.put("1", "已处理");
        ALARM_DEAL_FLAG_MAP.put("2", "处理中");
    }

    // private static InputStream getResourceAsStream() {
    // URL url = null;
    // url =
    // Thread.currentThread().getClass().getResource(SYS_CONFIG_FILE_PATH);
    // if (null == url) {
    // url = Constants.class.getResource(SYS_CONFIG_FILE_PATH);
    // }
    //
    // try {
    // return (url != null) ? url.openStream() : null;
    // } catch (IOException e) {
    // log.error(e.getMessage());
    // return null;
    // }
    // }

    public static void main(String[] args) {
        // String filePath = "/conf/other/sys_config.properties";
        // File file = new File(filePath);
        InputStream is = Constants.class
                .getResourceAsStream("/conf/other/sys_config_ec_permission.properties");
        Properties prop = new Properties();
        try {
            prop.load(is);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}

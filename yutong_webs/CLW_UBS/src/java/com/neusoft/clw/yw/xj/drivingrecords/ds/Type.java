package com.neusoft.clw.yw.xj.drivingrecords.ds;

import java.util.Map;
import java.util.TreeMap;

public class Type {
    public static Map < String, String > TYPE_ID_MAP = new TreeMap < String, String >();

    public static Map < String, String > TIME_SCALE_MAP = new TreeMap < String, String >();
    static {
        TYPE_ID_MAP.put("t01", "GPS信息");
        TYPE_ID_MAP.put("t02", "速度");
        TYPE_ID_MAP.put("t03", "转速");
        TYPE_ID_MAP.put("t04", "里程信息");
        TYPE_ID_MAP.put("t05", "开关量信息");
        TYPE_ID_MAP.put("t06", "CAN-发动机转速");
        TYPE_ID_MAP.put("t07", "CAN-扭矩");
        TYPE_ID_MAP.put("t08", "CAN-锂电池电压");
        TYPE_ID_MAP.put("t09", "CAN-蓄电池电压");
        TYPE_ID_MAP.put("t10", "CAN-供电状态");
        TYPE_ID_MAP.put("t11", "CAN-发动机油温");
        TYPE_ID_MAP.put("t12", "CAN-发动机水温");
        TYPE_ID_MAP.put("t13", "CAN-机油压力");
        TYPE_ID_MAP.put("t14", "CAN-大气压力");
        TYPE_ID_MAP.put("t15", "CAN-进气温度");
        TYPE_ID_MAP.put("t16", "CAN-车速");
        TYPE_ID_MAP.put("t17", "CAN-冷却剂温度");
        TYPE_ID_MAP.put("t18", "CAN-累计油耗");
        TYPE_ID_MAP.put("t19", "CAN-发动机运行时间");
        TYPE_ID_MAP.put("t20", "CAN-瞬时油耗");
        TYPE_ID_MAP.put("t21", "Extend-急减速");
        TYPE_ID_MAP.put("t22", "Extend-急加速");
        TYPE_ID_MAP.put("t23", "Extend-超速");
        // 时段
        TIME_SCALE_MAP.put("aweek", "本周");
        TIME_SCALE_MAP.put("bmonth", "本月");
        TIME_SCALE_MAP.put("cquarter", "本季度");
        TIME_SCALE_MAP.put("dyear", "本年");
    }
}

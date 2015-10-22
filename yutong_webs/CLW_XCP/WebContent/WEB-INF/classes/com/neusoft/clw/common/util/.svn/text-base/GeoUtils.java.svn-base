package com.neusoft.clw.common.util;

import java.math.BigDecimal;

/**
 * 地理位置信息计算函数类
 * @author jinp 2010-08-21
 */
public class GeoUtils {
    private static final Double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 计算两点之间距离
     * @param lat1 第一点：经度
     * @param lng1 第一点：纬度
     * @param lat2 第二点：经度
     * @param lng2 第二点：纬度
     * @return 两点之间距离
     */
    public static double GetTwoPointsDistance(double lat1, double lng1,
            double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 经纬度转换（度.分.秒->度.度）
     * @param input
     * @return
     */
    public static String formatLatLon(String input) {
        String[] idx = input.split("\\.");

        double ret = 0;
        for (int i = 0; i < idx.length; i++) {
            if (i == 0) {
                ret = Double.valueOf(idx[i]);
            } else if (i == 1) {
                Double tmp = div(Double.valueOf(idx[i]), Double.valueOf("60"),
                        7);
                ret = add(ret, tmp);
            } else if (i == 2) {
                Double tmp = div(Double.valueOf(idx[i]),
                        Double.valueOf("3600"), 7);
                ret = add(ret, tmp);
                ret = round(ret, 6);
                break;
            }
        }

        return String.valueOf(ret);
    }

    public static void main(String[] args) {
        String longlat = "0";
        StringBuffer jwd = new StringBuffer();
        Double jwddb = Double.valueOf(longlat);
        if (jwddb >= 0) {
            // 正数符号设为0
            jwd.append("0");
        } else {
            // 负数符号设为1
            jwd.append("1");
            jwddb = -jwddb;
        }
        int dui = (int) Math.floor(jwddb);
        // 两位度为16进制，不足补0
        String du = Integer.toHexString(dui);
        if (du.length() == 2) {
            jwd.append(du);
        } else {
            jwd.append("0");
            jwd.append(du);
        }

        Double fendb = sub(jwddb, dui) * 60;
        int feni = (int) Math.floor(fendb);
        // 两位分为16进制，不足补0
        String fen = Integer.toHexString(feni);
        if (fen.length() == 2) {
            jwd.append(fen);
        } else {
            jwd.append("0");
            jwd.append(fen);
        }

        Double miaodb = sub(fendb, feni) * 10000;
        int miaoi = (int) Math.floor(miaodb);
        // 四位秒为16进制，不足补0
        String miao = Integer.toHexString(miaoi);
        for (int i = 4; i > miao.length(); i--) {
            jwd.append("0");
        }
        jwd.append(miao);
        System.out.println(jwd.toString());
    }

    // 转换经纬度成度分秒,且按协议进行补零，补正负号
    public static String changeLongLat(String longlat) {
        StringBuffer jwd = new StringBuffer();
        Double jwddb = Double.valueOf(longlat);
        if (jwddb >= 0) {
            // 正数符号设为0
            jwd.append("0");
        } else {
            // 负数符号设为1
            jwd.append("1");
            jwddb = -jwddb;
        }
        int dui = (int) Math.floor(jwddb);
        // 两位度为16进制，不足补0
        String du = Integer.toHexString(dui);
        if (du.length() == 2) {
            jwd.append(du);
        } else {
            jwd.append("0");
            jwd.append(du);
        }

        Double fendb = sub(jwddb, dui) * 60;
        int feni = (int) Math.floor(fendb);
        // 两位分为16进制，不足补0
        String fen = Integer.toHexString(feni);
        if (fen.length() == 2) {
            jwd.append(fen);
        } else {
            jwd.append("0");
            jwd.append(fen);
        }

        Double miaodb = sub(fendb, feni) * 10000;
        int miaoi = (int) Math.floor(miaodb);
        // 四位秒为16进制，不足补0
        String miao = Integer.toHexString(miaoi);
        for (int i = 4; i > miao.length(); i--) {
            jwd.append("0");
        }
        jwd.append(miao);

        return jwd.toString();
    }

}

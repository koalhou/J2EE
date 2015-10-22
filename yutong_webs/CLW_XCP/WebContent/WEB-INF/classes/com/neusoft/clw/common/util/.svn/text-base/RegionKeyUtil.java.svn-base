package com.neusoft.clw.common.util;

public class RegionKeyUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RegionKeyUtil util = new RegionKeyUtil();
        String regid = RegionKeyUtil.getLogKey();
        StringBuffer regidb = new StringBuffer();
        for (int i = 8; i > regid.length(); i--) {
            regidb.append("0");
        }
        regidb.append(regid);
        //System.out.println(regidb.toString());

    }

    /**
     * 私有构造器
     */
    private RegionKeyUtil() {

    }

    /**
     * getPK,获得数据库使用的一个long型唯一主键 16位，同一微秒内3000个并发不会重复
     * @return long
     */
    private static long[] ls = new long[3000];

    private static int li = 0;

    /**
     * @return
     */
    public static synchronized String getLogKey() {
        long lo = getKey();
        for (int i = 0; i < 3000; i++) {
            long lt = ls[i];
            if (lt == lo) {
                lo = getKey();
                break;
            }
        }
        ls[li] = lo;
        li++;
        if (li == 3000) {
            li = 0;
        }
        return Long.toString(lo);
    }

    /**
     * @return
     */
    private static long getKey() {
        String a = (String.valueOf(System.currentTimeMillis()))
                .substring(8, 13);
        //System.out.println(System.currentTimeMillis());
        //System.out.println(a);
        String d = (String.valueOf(Math.random())).substring(5, 8);
        //System.out.println(String.valueOf(Math.random()));
        //System.out.println(d);
        return Long.parseLong(a + d);
    }

}

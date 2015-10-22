package com.neusoft.clw.common.util;

public class SqlStringUtil {
    public static String getNoNull(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getNull(String str) {
        if ("".equals(str)) {
            return null;
        }
        return str;
    }
    
    //拼装SQL使用的字符串-将A,B,C 转换为'A','B','C'
    public static String getSqlA(String str){
        
        String[] ary = str.split(",");
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<ary.length;i++){
            sb.append("'").append(ary[i]).append("',");
        }
        sb.deleteCharAt(sb.length()-1);
        
        return sb.toString();
    }
    
    public static void main(String args[]){
        String str = "ae3af082-0f20-442b-abc1-a35d24a33646,ad176cdd-ddf4-44a9-93fc-2261f8d9e9f9";
        
        String strR = SqlStringUtil.getSqlA(str);
        System.out.println(str);
        System.out.println(strR);
    }
}

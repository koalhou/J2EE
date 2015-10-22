
package com.yutong.axxc.tqc.tools;

import java.awt.List;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.regex.Pattern;

import com.yutong.axxc.tqc.common.NumberConstant;



public final class CheckRequestParam {
	/**
	 * 检查对象是否为空.
	 * 
	 * @param pInput
	 *            被检查对象.
	 * @return 是否为空.
	 */
	public static boolean isEmpty(Object pInput) {
    	// 判断参数是否为空或者''
    	if (pInput == null || "".equals(pInput)) {
    		return true;
    	} else if ("java.lang.String".equals(pInput.getClass().getName())){
    		// 判断传入的参数的String类型的

    		// 替换各种空格
    		String tmpInput = Pattern.compile("//r|//n|//u3000")
    		                         .matcher((String)pInput).replaceAll("");
    		// 匹配空
    		return Pattern.compile("^(//s)*$")
    		              .matcher(tmpInput).matches();
    	} else {
    		// 方法类
    		Method method = null;
    		String newInput = "";
    		try {
    			// 访问传入参数的size方法
    			method = pInput.getClass().getMethod("size");
    			// 判断size大小

    			// 转换为String类型
    			newInput = String.valueOf(method.invoke(pInput));
    			// size为0的场合
    			if (Integer.parseInt(newInput) == 0) {

    				return true;
    			} else {

    				return false;
    			}
    		} catch (Exception e) {
    			// 访问失败
    			try {
    				// 访问传入参数的getItemCount方法
    				method = pInput.getClass().getMethod("getItemCount");
    				// 判断size大小
    				
    				// 转换为String类型
    				newInput = String.valueOf(method.invoke(pInput));
    				
    				// getItemCount为0的场合
    				if (Integer.parseInt(newInput) == 0) {

    					return true;
    				} else {

    					return false;
    				}
    			} catch (Exception ex) {
    				// 访问失败
    				try{
    					// 判断传入参数的长度

    					// 长度为0的场合
    					if (Array.getLength(pInput) == 0) {

    						return true;
    					} else {

    						return false;
    					}
    				} catch (Exception exx) {
    					// 访问失败
    					try{
    						// 访问传入参数的hasNext方法
    						method = Iterator.class.getMethod("hasNext");
    						// 转换String类型
    						newInput = String.valueOf(method.invoke(pInput));
    						
    						// 转换hasNext的值
    						if (!Boolean.valueOf(newInput)) {
    							return true;
    						} else {
    							return false;
    						}
    						
    					} catch (Exception exxx) {
    						// 以上场合不满足
    						
    						return false;
    					}
    				}
    			}
    		}
    	}
	}

	/**
	 * 检查手机号是否合法. 检查原则为:非空11位字符串.
	 * 
	 * @param phone
	 *            被检查手机号参数
	 * @return 手机号参数是否合法
	 */
	public static boolean isPhoneRightful(String phone) {
		if (!CheckRequestParam.isEmpty(phone)
				&& (phone.length() == NumberConstant.NUM_11)) {
			return true;
		} else {
			return false;
		}
	}

    public static void main(String args[]) throws Exception{
        char ch [][] = new char[10][10];
        System.out.println("char ch[]= " + isEmpty(ch));
        
        byte be [] = new byte[10];
        System.out.println("byte be[]= " + isEmpty(be));
        
        float[] ft = new float[10];
        System.out.println("float ft[]= " + isEmpty(ft));
        
        double ad[] = new double[10];
        System.out.println("double ad[]= " + isEmpty(ad));
        
        int ai[][][] = new int [10][10][10];
        System.out.println("int ai[]= " + isEmpty(ai));
        
        Object ob = null;
        System.out.println("Object= " + isEmpty(ob));
        
        String a [] =null;
        System.out.println("String a []= " + isEmpty(a));
        
        String str ="	";
        System.out.println("String str= " + isEmpty(str));
        
        List aa = new List();
        System.out.println("List= " + isEmpty(aa));
        
        ArrayList aaa = new ArrayList();
        aaa.add(null);
        System.out.println("ArrayList= " + isEmpty(aaa));
        System.out.println("ArrayList= " + aaa.isEmpty());
        
        Map map = new HashMap(); 
        System.out.println("HashMap= " + isEmpty(map));
        
        String a2 [][][][] = new String[10][10][10][20];
        System.out.println("String a2 [][][][]= " + isEmpty(a2));
        
        HashMap map2 = new HashMap(); 
        System.out.println("HashMap= " + isEmpty(map2));
        
        Vector keys = new Vector();
        System.out.println("Vector= " + isEmpty(keys));
        
        Hashtable ht = new Hashtable();
        System.out.println("Hashtable= " + isEmpty(ht));
        
        LinkedList lt = new LinkedList();
        System.out.println("LinkedList= " + isEmpty(lt));
        
        TreeSet tt = new TreeSet();
        System.out.println("TreeSet= " + isEmpty(tt));
        
        Set ss = new TreeSet();
        System.out.println("TreeSet= " + isEmpty(ss));
        
        Iterator it = new ArrayList().iterator(); 
        System.out.println("Iterator= " + isEmpty(it));
        
        LinkedHashMap llp = new LinkedHashMap();
        System.out.println("LinkedHashMap= " + isEmpty(llp));
        
        LinkedHashSet llt = new LinkedHashSet();
        System.out.println("LinkedHashSet= " + isEmpty(llt));
        
        WeakHashMap wp =new WeakHashMap();
        System.out.println("WeakHashMap= " + isEmpty(wp));
        
        String[] sra = ",,,".split(",");
        System.out.println("sra= " + isEmpty(sra));
        
        SortedMap m= new TreeMap();
        System.out.println("SortedMap= " + isEmpty(m));
    }

}

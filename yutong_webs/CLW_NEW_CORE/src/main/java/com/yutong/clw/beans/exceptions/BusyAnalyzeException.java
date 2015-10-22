
/**
 * 自定义异常类:正在忙于分析油耗数据,拒绝请求
 */

package com.yutong.clw.beans.exceptions;

 
public class BusyAnalyzeException extends Exception {
	 private static final long serialVersionUID = 1L;
	    
	    private String strExpDescription = "";
	    
	    public BusyAnalyzeException(){
	    }
	    
	    public BusyAnalyzeException(String strExpDescription) {
	        this.strExpDescription = strExpDescription;
	    }
	    
	    @Override
	    public String getMessage() {
	        return String.format(strExpDescription);
	    }
	    
	    @Override
	    public String toString() {
	        return String.format(strExpDescription);
	    }
}

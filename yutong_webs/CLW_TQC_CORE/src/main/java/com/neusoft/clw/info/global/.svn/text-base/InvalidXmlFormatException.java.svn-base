package com.neusoft.clw.info.global;

/**
 * 自定义异常类:无效的请求xml格式 
 */
public class InvalidXmlFormatException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private String strExpDescription = "";
    
    public InvalidXmlFormatException(){
    }
    
    public InvalidXmlFormatException(String strExpDescription) {
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
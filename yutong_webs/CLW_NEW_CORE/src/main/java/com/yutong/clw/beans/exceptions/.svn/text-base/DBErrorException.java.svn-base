package com.yutong.clw.beans.exceptions;

public class DBErrorException extends Exception{
	
	 private static final long serialVersionUID = 1L;
	    
	    private String strExpDescription = "";
	    
	    public DBErrorException(){
	    }
	    
	    public DBErrorException(String strExpDescription) {
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

package com.neusoft.clw.info.exception;

public class SendInfoException extends Exception{
	private static final long serialVersionUID = 1L;
    
    private String strExpDescription = "";
    
    public SendInfoException(){
    }
    
    public SendInfoException(String strExpDescription) {
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

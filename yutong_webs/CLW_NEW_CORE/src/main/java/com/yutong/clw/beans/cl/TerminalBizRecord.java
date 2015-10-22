package com.yutong.clw.beans.cl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 终端业务关系记录
 */
public class TerminalBizRecord  {
	 
	private String terminal_id;  //终端硬件编码
	private String biz_id	;	//业务id						
	private String biz_name;	//业务名称
	private String modify_time;    //最后修改时间
	 

    public String getTerminal_id() {
		return terminal_id;
	}



	public void setTerminal_id(String terminalId) {
		terminal_id = terminalId;
	}

 

 



	public TerminalBizRecord() {    	 
		terminal_id =null;
		biz_id = null;	;	//业务id						
		biz_name = null;;	//业务名称
		modify_time=null;
    }

   

    public String getBiz_id() {
		return biz_id;
	}



	public void setBiz_id(String bizId) {
		biz_id = bizId;
	}



	public String getBiz_name() {
		return biz_name;
	}



	public void setBiz_name(String bizName) {
		biz_name = bizName;
	}



	public String getModify_time() {
		return modify_time;
	}



	public void setModify_time(String modifyTime) {
		modify_time = modifyTime;
	}



	public static void main(String[] args) {
        List<TerminalRecord> list = new ArrayList<TerminalRecord>();
        TerminalRecord record = new TerminalRecord();
      
        list.add(record);

        for (int i = 1000; i > 0; i--) {
            record = new TerminalRecord();
            
            list.add(record);
        }

        System.out.println(new Date() + "排序前" + list.toString());
        
        System.out.println(new Date() + "排序后" + list.toString());
    }
}

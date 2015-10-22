/*******************************************************************************
 * @(#)SendDataReq.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.msg.req;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.nio.msg.monitor.CmdBean;
import com.yutong.clw.nio.msg.monitor.TerminalUtil;
import com.yutong.clw.nio.msg.up.InsideMsg;
import com.yutong.clw.nio.msg.util.InsideMsgUtils;

/**
 * @author <a href="mailto:tianmc@neusoft.com">tianmc 
 * @version $Revision 1.1 $ 2010-5-24 下午06:14:03
 */
public class SendCmdReq extends InsideMsg {

   
    private Logger log = LoggerFactory.getLogger(SendCmdReq.class);
    public static final String COMMAND = "0010";
    public static final String NORMAL_STATUS = "0000";

    public static final String SM_STATUS = "1000";

    public static final int TERMINALIDSIZE = 20;

    public static final int PACKETLENSIZE = 8;

    private String terminalId;

    private CmdBean cmdbean;
    
    private String packetLen;

    private byte[] packetContent;
    private String sendStr;
    public void setTerminalId(String terminalId) {
        this.terminalId = (terminalId == null || terminalId.equals("")) ? null : InsideMsgUtils
                .formatTerminalId(terminalId);
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setPacketLen(String packetLen) {
        this.packetLen = (packetLen == null || packetLen.equals("")) ? null : InsideMsgUtils
                .formatPacketLen(Integer.parseInt(packetLen));
    }

    public String getPacketLen() {
        return packetLen;
    }

    public void setPacketContent(byte[] packetContent) {
        this.packetContent = (packetContent == null || packetContent.length < 0) ? null
                : packetContent;
    }

    public byte[] getPacketContent() {
        return packetContent;
    }

    @Override
    public byte[] getBytes() {
    	byte[] packetContent=getbody();
        int location = 0;
        int packetContentLen = packetContent.length;
        int len = InsideMsg.MSGHEADERSIZE + TERMINALIDSIZE + PACKETLENSIZE + packetContentLen;
        this.setPacketLen(String.valueOf(packetContentLen));
        this.setPacketContent(packetContent);
        this.setMsgLength(String.valueOf(len));
        byte[] buf = new byte[len];
        byte[] header = super.getBytes();
        System.arraycopy(header, 0, buf, location, header.length);
        location += header.length;
        System.arraycopy(this.getTerminalId().getBytes(), 0, buf, location, TERMINALIDSIZE);
        location += TERMINALIDSIZE;
        System.arraycopy(this.getPacketLen().getBytes(), 0, buf, location, PACKETLENSIZE);
        location += PACKETLENSIZE;
        System.arraycopy(this.getPacketContent(), 0, buf, location, packetContentLen);
        return buf;
    }
    public byte[] getbody() { 	
	    byte[] r = null;
//        if(cmdbean.getCMDID().equals("301")){
//        	r= TakePicture(); //拍照
//		}else{
//			r=MakeCmd();
//		}
        r=MakeCmd();
        
	 return r;
    }
	
 
	//拍照指令
//	C M 1 4C54:1001|301|1 3B9
	public byte[] TakePicture() {		
		sendStr=cmdbean.getOEMCODE()+":"+cmdbean.getCOMMADDR()+"|301|1 ";		
		sendStr=sendStr+TerminalUtil.VERIFYCODE(sendStr);
		sendStr="C M "+cmdbean.getCMDSEQ()+" "+sendStr;
	    byte[] rdata =new byte[sendStr.length()+2];
	    rdata[rdata.length-2]=0x0d;
	    rdata[rdata.length-1]=0x0a;
	   	System.arraycopy(sendStr.getBytes(), 0, rdata, 0, sendStr.length());   
	   	return rdata;
	}
	
	//通用生成模块
//		C M SEQ OEMCODE:COMMADDR|CMDID|CMDARGUS VERIFYCODE\r\n
	public byte[] MakeCmd() {
		byte[] rdata = null;
		try{
			sendStr=cmdbean.getOEMCODE()+":"+cmdbean.getCOMMADDR()+"|"+cmdbean.getCMDID()+"|"+cmdbean.getCMDARGUS()+" ";		
			sendStr=sendStr+TerminalUtil.VERIFYCODE(sendStr);
			sendStr="C M "+cmdbean.getCMDSEQ()+" "+sendStr;
		    rdata =new byte[sendStr.length()+2];
		    rdata[rdata.length-2]=0x0d;
		    rdata[rdata.length-1]=0x0a;
		   	System.arraycopy(sendStr.getBytes(), 0, rdata, 0, sendStr.length());   
	   	
		}catch(Exception e){
			
			log.error("SendCmdReq:"+"MakeCmd"+e.getMessage());
		}
		return rdata;
	}

	public CmdBean getCmdbean() {
		return cmdbean;
	}

	public void setCmdbean(CmdBean cmdbean) {
		this.cmdbean = cmdbean;
	}
	
	
}





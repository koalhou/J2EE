package com.neusoft.clw.info.bean;

import org.apache.log4j.Logger;

import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.util.Util;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.util.Converser;


public class SendHolesReq extends InsideMsg {
	private static final Logger LOGGER = Logger.getLogger(SendHolesReq.class); 

    public static final String COMMAND = "0010";

    public static final String STATUS = "0000";

    public static final int TIMIDESIZE = 20;

    public static final int PACKETLENG = 8;
    public static final int TERMINALIDLEN = 20;
    
    public static final String banben = "02";
    
//    public static final int bodylen = 71;
    
    public static final String cmd = "2900";
    
    public static final String leaveid = "86";
    public static final String terminateid = "87";
    
    public static final int len00 = 4;
	public static final int len01 = 32;
	public static final int len02 = 4;
    
    public String begintime;
    public String endtime;
    private String route_id;
    private String stu_id;
    private String msg_id;
    private String send_type;
 
    private String systemId;

    private String terminal_id;
    private String packet_con_length;
    
    private String bytetype;
    
    
    
    public String getBytetype() {
		return bytetype;
	}

	public void setBytetype(String bytetype) {
		this.bytetype = bytetype;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminalId) {
		terminal_id = Util.PostfixS(" ",TIMIDESIZE,terminalId);
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String sendType) {
		send_type = sendType;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String routeId) {
		route_id = InsideMsgUtils.formatRouteid(routeId);
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stuId) {
		stu_id = InsideMsgUtils.formatStuid(stuId);
	}
	
	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	public String getPacket_con_length() {
		return packet_con_length;
	}

	public void setPacket_con_length(String packetConLength) {
		packet_con_length = (packetConLength == null || packetConLength.equals("")) ? null : InsideMsgUtils
                .formatPacketLen(Integer.parseInt(packetConLength));
	}

	public String getPacket_content() {
		return packet_content;
	}

	public void setPacket_content(String packetContent) {
		packet_content = packetContent;
	}
	
	public static String getStatus() {
		return STATUS;
	}

	public static int getTimidesize() {
		return TIMIDESIZE;
	}

	public static int getPacketleng() {
		return PACKETLENG;
	}



	private String packet_content;
    
    public SendHolesReq() {   	
    	     
    }

    public void setSystemId(String systemId) {
        this.systemId = (systemId == null || systemId.equals("")) ? null : InsideMsgUtils
                .formatSystemId(systemId);
    }

    public String getSystemId() {
        return systemId;
    }

    public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	@Override
    public byte[] getBytes() {
        int location = 0;
        byte[] tc = getTc();
        byte[] body = getBody(tc.length);
        
        int msglen = MSGHEADERSIZE+TIMIDESIZE+PACKETLENG+body.length+tc.length;
        this.setMsgLength(String.valueOf(msglen));
        
        byte[] buf = new byte[msglen];
        byte[] header = super.getBytes();
        this.setPacket_con_length(String.valueOf(body.length+tc.length));
        System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
        location += MSGHEADERSIZE;
        System.arraycopy(this.getTerminal_id().getBytes(), 0, buf, location, TIMIDESIZE);
        location += TIMIDESIZE;
        
        System.arraycopy(this.getPacket_con_length().getBytes(), 0, buf, location, PACKETLENG);
        location += PACKETLENG;
        
        System.arraycopy(body, 0, buf, location, body.length);
        location+=body.length;
        System.arraycopy(tc, 0, buf, location, tc.length);
        location+=tc.length;
        LOGGER.info("下行透传命令："+new String(header)+new String(this.getTerminal_id().getBytes())+new String(this.getPacket_con_length().getBytes())+new String(body)+Converser.bytesToHexString(tc));
        return buf;
    }
	
	public String getTcToString(){
		byte[] tc = getTc();
//		byte[] body = getBody(tc.length);
		return Converser.bytesToHexString(tc);
	}

	private byte[] getBody(int length) {
		int location = 0;
		byte[] body = new byte[50];
		StringBuffer sendStr = new StringBuffer();
		sendStr.append(ContentParamName.packet_content00+IdCreater.converToString(Integer.toHexString(len00).toUpperCase(),2)+ cmd);	
		sendStr.append(ContentParamName.packet_content01+IdCreater.converToString(Integer.toHexString(len01).toUpperCase(),2)+ this.getMsg_id());
		sendStr.append(ContentParamName.packet_content02+IdCreater.converToString(Integer.toHexString(length).toUpperCase(),4));
		System.arraycopy(sendStr.toString().getBytes(), 0, body, location, sendStr.toString().length());
		location+=sendStr.toString().length();
//		System.out.println(new String(body));
		return body;
	}
	
	private byte[] getTc(){
		byte[] body = null;
		int location = 0;
		int bodylen = 0;
		if(this.getSend_type().equals("87")){
//			bodylen = 11;
			bodylen = 7;
			body = new byte[bodylen];
			
			byte[] msgidbyte = Converser.hexStringToBytes(InsideMsgUtils.formatCmdid(terminateid));
//			byte[] routeidbyte = Converser.hexStringToBytes(this.getRoute_id());
			byte[] stuidbyte = Converser.hexStringToBytes(this.getStu_id());
			System.arraycopy(Converser.hexStringToBytes(this.getBytetype()), 0, body, location, Converser.hexStringToBytes(getBytetype()).length);
			location+=Converser.hexStringToBytes(this.getBytetype()).length;
			System.arraycopy(Converser.hexStringToBytes(banben), 0, body, location, Converser.hexStringToBytes(banben).length);
			location+=Converser.hexStringToBytes(banben).length;
			System.arraycopy(msgidbyte, 0, body, location, msgidbyte.length);
			location+=msgidbyte.length;
//			System.arraycopy(routeidbyte, 0, body, location, routeidbyte.length);
//			location+=routeidbyte.length;
			System.arraycopy(stuidbyte, 0, body, location, stuidbyte.length);
			location+=stuidbyte.length;
		}else if(this.getSend_type().equals("86")){
//			bodylen = 23;
			bodylen = 19;
			body = new byte[bodylen];
			byte[] msgidbyte = Converser.hexStringToBytes(InsideMsgUtils.formatCmdid(leaveid));
			byte[] begintimebyte = Converser.strToBcd(this.getBegintime());
			byte[] endtimebyte = Converser.strToBcd(this.getEndtime());
//			System.out.println(Converser.bcdToStr(begintimebyte,0,begintimebyte.length));
//			System.out.println(Converser.bcdToStr(endtimebyte,0,endtimebyte.length));
//			byte[] routeidbyte = Converser.hexStringToBytes(this.getRoute_id());
			byte[] stuidbyte = Converser.hexStringToBytes(this.getStu_id());
			System.arraycopy(Converser.hexStringToBytes(this.getBytetype()), 0, body, location, Converser.hexStringToBytes(getBytetype()).length);
			location+=Converser.hexStringToBytes(this.getBytetype()).length;
			System.arraycopy(Converser.hexStringToBytes(banben), 0, body, location, Converser.hexStringToBytes(banben).length);
			location+=Converser.hexStringToBytes(banben).length;
			System.arraycopy(msgidbyte, 0, body, location, msgidbyte.length);
			location+=msgidbyte.length;
//			System.arraycopy(routeidbyte, 0, body, location, routeidbyte.length);
//			location+=routeidbyte.length;
			System.arraycopy(stuidbyte, 0, body, location, stuidbyte.length);
			location+=stuidbyte.length;
			System.arraycopy(begintimebyte, 0, body, location, begintimebyte.length);
			location+=begintimebyte.length;
			System.arraycopy(endtimebyte, 0, body, location, endtimebyte.length);
			location+=endtimebyte.length;
		}
//		System.out.println(Converser.bytesToHexString(body));
		return body;
	}
}

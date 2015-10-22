package com.neusoft.clw.info.bean;

import java.io.UnsupportedEncodingException;

import com.neusoft.clw.util.Util;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;


public class SendReq extends InsideMsg {

    public static final String COMMAND = "0010";

    public static final String STATUS = "0000";

    public static final int TIMIDESIZE = 20;

    public static final int PACKETLENG = 8;
    //public static final int TERMINALIDLEN = 20;

 
    private String systemId;

    private String terminal_id;
    private String packet_con_length;
    public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminalId) {
		terminal_id = Util.PostfixS(" ",TIMIDESIZE,terminalId);
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
    
    public SendReq() {   	
    	     
    }

    public void setSystemId(String systemId) {
        this.systemId = (systemId == null || systemId.equals("")) ? null : InsideMsgUtils
                .formatSystemId(systemId);
    }

    public String getSystemId() {
        return systemId;
    }
    
    private byte[] packet_content2;

   	public byte[] getPacket_content2() {
   		return packet_content2;
   	}

   	public void setPacket_content2(byte[] packet_content2) {
   		this.packet_content2 = packet_content2;
   	}

    // 透传指令获取字节数
    public byte[] getBytesTc() {
        int location = 0;
        byte[] buf = new byte[Integer.parseInt(this.getMsgLength())];
        byte[] header = super.getBytes();
        System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
        location += MSGHEADERSIZE;
        System.arraycopy(this.getTerminal_id().getBytes(), 0, buf, location, TIMIDESIZE);
        location += TIMIDESIZE;
        System.arraycopy(this.getPacket_con_length().getBytes(), 0, buf, location, PACKETLENG);
        location += PACKETLENG;
        System.arraycopy(this.getPacket_content2(), 0, buf, location, this.getPacket_content2().length);
        return buf;
    }

    @Override
    public byte[] getBytes(){
        int location = 0;
        byte[] buf = new byte[Integer.parseInt(this.getMsgLength())];
        byte[] header = super.getBytes();
        System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
        location += MSGHEADERSIZE;
        System.arraycopy(this.getTerminal_id().getBytes(), 0, buf, location, TIMIDESIZE);
        location += TIMIDESIZE;
        try {
	        System.arraycopy(this.getPacket_con_length().getBytes(), 0, buf, location, PACKETLENG);
	        location += PACKETLENG;
			System.arraycopy(this.getPacket_content().getBytes("GBK"), 0, buf, location, this.getPacket_content().getBytes("GBK").length);
			location+=this.getPacket_content().getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//        System.out.println(new String(buf));
        return buf;
    }
}

package com.neusoft.clw.info.bean;


import org.apache.log4j.Logger;

import com.neusoft.clw.util.Util;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;

public class SendLockReq extends InsideMsg {
	private static final Logger log = Logger.getLogger(SendLockReq.class);

	public static final String COMMAND = "0010";

	public static final String STATUS = "0000";

	public static final int TIMIDESIZE = 20;

	public static final int PACKETLENG = 8;
	public static final int TERMINALIDLEN = 20;

	public static final String cmd = "2F16";

	public static final String terminateid = "88";

	public static final int len00 = 4;
	public static final int len01 = 32;
	public static final int len02 = 4;
	
	private String terminal_id;
	private String packet_con_length;
	private String lockMsg;

	private String bytetype;

	private String msg_id;

	public String getLockMsg() {
		return lockMsg;
	}

	public void setLockMsg(String lockMsg) {
		this.lockMsg = lockMsg;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

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
		terminal_id = Util.PostfixS(" ", TIMIDESIZE, terminalId);
	}

	public String getPacket_con_length() {
		return packet_con_length;
	}

	public void setPacket_con_length(String packetConLength) {
		packet_con_length = (packetConLength == null || packetConLength
				.equals("")) ? null : InsideMsgUtils.formatPacketLen(Integer
				.parseInt(packetConLength));
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

	public SendLockReq() {

	}

	@Override
	public byte[] getBytes() {
		int location = 0;
		byte[] tc= getTc();
		
		int msglen = MSGHEADERSIZE + TIMIDESIZE+PACKETLENG + tc.length;
		this.setMsgLength(String.valueOf(msglen));

		byte[] buf = new byte[msglen];
		byte[] header = super.getBytes();
		this.setPacket_con_length(String.valueOf(tc.length));
		System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
		location += MSGHEADERSIZE;
		System.arraycopy(this.getTerminal_id().getBytes(), 0, buf, location, TIMIDESIZE);
		location += TIMIDESIZE;
		
		System.arraycopy(InsideMsgUtils.formatPacketLen(tc.length).getBytes(), 0, buf, location, PACKETLENG);
		location += PACKETLENG;

		System.arraycopy(tc, 0, buf, location, tc.length);
		location += tc.length;
		log.error("下行透传命令：" + new String(header)
				+ new String(this.getTerminal_id().getBytes())
				+ new String(this.getPacket_con_length().getBytes())
				+ this.getPacket_content() + this.getBytetype()+InsideMsgUtils
				.formatCmdid(terminateid));
		return buf;
	}

	private byte[] getTc(){
		byte[] b = this.getPacket_content().getBytes();
		byte[] body = new byte[b.length];
		System.arraycopy(b, 0, body,0 , b.length);
		return body;
	}
	//消息长度:8	消息指令:8	消息id:36	消息参数加长度:4
	public String getSend_type(){
		int start = 8+36+4;
		return this.getPacket_content().substring(start, start+4);
	}
	//消息长度:8	消息指令:8	消息id:36	消息参数加长度:4
	public String getLock_control(){
		int start = 8+36+4 +4 +4;
		return this.getPacket_content().substring(start, start+2);
	}
	//消息长度:8	消息指令:8	消息id:36	消息参数加长度:4
	public String getLock_type(){
		int start = 8+36+4 +4 +4 +2 +4;
		return this.getPacket_content().substring(start, start+2);
	}
	//消息长度:8	消息指令:8	消息id:36	消息参数加长度:4
	public String getMax_route_spd(){
		int start = 8+36+4 +4 +4 +2 +4+2 +4;
		return this.getPacket_content().substring(start, start+4);
	}
	
	public String getLocked_time(){
		return this.getPacket_content().substring(this.getPacket_content().length()-28, this.getPacket_content().length()-16);
	}
	
	public String getLock_nock_time(){
		return this.getPacket_content().substring(this.getPacket_content().length()-12, this.getPacket_content().length());
	}
}

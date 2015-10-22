package com.neusoft.clw.info.bean;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.util.Util;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.util.Converser;

public class SendExceptionSettingReq extends InsideMsg {
	private static final Logger log = Logger.getLogger(SendExceptionSettingReq.class);

	public static final String COMMAND = "0010";

	public static final String STATUS = "0000";

	public static final int TIMIDESIZE = 20;

	public static final int PACKETLENG = 8;
	public static final int TERMINALIDLEN = 20;

	public static final String banben = "01";

	// public static final int bodylen = 71;

	public static final String cmd = "2900";

//	public static final String leaveid = "86";
	public static final String terminateid = "02";

	public static final int len00 = 4;
	public static final int len01 = 32;
	public static final int len02 = 4;
	
	public static final int switchlen = 1;

	private String terminal_id;
	private String packet_con_length;
	private String exceptionswitch;

	private String bytetype;

	private String msg_id;

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msgId) {
		msg_id = msgId;
	}

	
	
	public String getExceptionswitch() {
		return exceptionswitch;
	}

	public void setExceptionswitch(String exceptionswitch) {
		this.exceptionswitch = exceptionswitch;
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

	public SendExceptionSettingReq() {

	}

	@Override
	public byte[] getBytes() {
		try {
			int location = 0;
			byte[] tc= getTc();
			
			byte[] body = getBody(tc.length);
	
			int msglen = MSGHEADERSIZE + TIMIDESIZE + PACKETLENG + body.length
					+ tc.length;
			this.setMsgLength(String.valueOf(msglen));
	
			byte[] buf = new byte[msglen];
			byte[] header = super.getBytes();
			this.setPacket_con_length(String.valueOf(body.length + tc.length));
			System.arraycopy(header, 0, buf, location, MSGHEADERSIZE);
			location += MSGHEADERSIZE;
			System.arraycopy(this.getTerminal_id().getBytes(), 0, buf, location,
					TIMIDESIZE);
			location += TIMIDESIZE;
	
			System.arraycopy(this.getPacket_con_length().getBytes(), 0, buf,
					location, PACKETLENG);
			location += PACKETLENG;
	
			System.arraycopy(body, 0, buf, location, body.length);
			location += body.length;
			System.arraycopy(tc, 0, buf, location, tc.length);
			location += tc.length;
			return buf;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getTcToString() throws UnsupportedEncodingException {
		byte[] tc = getTc();
		// byte[] body = getBody(tc.length);
		return Converser.bytesToHexString(tc);
	}

	private byte[] getBody(int length) {
		int location = 0;
		byte[] body = new byte[50];
		StringBuffer sendStr = new StringBuffer();
		sendStr.append(ContentParamName.packet_content00
				+ IdCreater.converToString(Integer.toHexString(len00)
						.toUpperCase(), 2) + cmd);
		sendStr.append(ContentParamName.packet_content01
				+ IdCreater.converToString(Integer.toHexString(len01)
						.toUpperCase(), 2) + this.getMsg_id());
		sendStr.append(ContentParamName.packet_content02
				+ IdCreater.converToString(Integer.toHexString(length)
						.toUpperCase(), 4));
		System.arraycopy(sendStr.toString().getBytes(), 0, body, location,
				sendStr.toString().length());
		location += sendStr.toString().length();
		// System.out.println(new String(body));
		return body;
	}

	private byte[] getTc() throws UnsupportedEncodingException {

		int location = 0;
		byte[] banbenbyte = Converser.hexStringToBytes(banben);
		byte[] typebyte = Converser.hexStringToBytes(getBytetype());
		byte[] switchbyte = Converser.hexStringToBytes(this.getExceptionswitch());
		byte[] msgidbyte = Converser.hexStringToBytes(InsideMsgUtils
				.formatCmdid(terminateid));
		int bodylen = typebyte.length+banbenbyte.length+msgidbyte.length+switchbyte.length;
		byte[] body = new byte[bodylen];
		
		System.arraycopy(typebyte, 0,
				body, location,	typebyte.length);
		location += typebyte.length;
		System.arraycopy(banbenbyte, 0, body, location,
				banbenbyte.length);
		location += banbenbyte.length;
		System.arraycopy(msgidbyte, 0, body, location, msgidbyte.length);
		location += msgidbyte.length;
		System.arraycopy(switchbyte, 0, body, location, switchbyte.length);
		location+=switchbyte.length;
		return body;
	}
}

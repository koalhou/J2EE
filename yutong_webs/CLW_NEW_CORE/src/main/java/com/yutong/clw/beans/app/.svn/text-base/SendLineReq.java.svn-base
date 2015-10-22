package com.yutong.clw.beans.app;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.yutong.clw.config.ContentParamName;
import com.yutong.clw.nio.msg.up.InsideMsg;
import com.yutong.clw.nio.msg.util.IdCreater;
import com.yutong.clw.nio.msg.util.InsideMsgUtils;
import com.yutong.clw.utils.Converser;
import com.yutong.clw.utils.Util;

public class SendLineReq extends InsideMsg {
	private static final Logger log = Logger.getLogger(SendLineReq.class);

	public static final String COMMAND = "0010";

	public static final String STATUS = "0000";

	public static final int TIMIDESIZE = 20;

	public static final int PACKETLENG = 8;
	public static final int TERMINALIDLEN = 20;

	public static final String banben = "02";

	// public static final int bodylen = 71;

	public static final String cmd = "2900";

//	public static final String leaveid = "86";
	public static final String terminateid = "88";

	public static final int len00 = 4;
	public static final int len01 = 32;
	public static final int len02 = 4;

	public static final int typelen = 1;
	public static final int bblen = 1;
	public static final int msglen = 1;
	public static final int iplen = 1;
	public static final int portlen = 2;
	public static final int userlen = 1;
	public static final int passlen = 1;
	public static final int filelen = 1;
	public static final int crclen = 4;

	public String ip;
	public String iplength;
	private String port;
	private String userlength;
	private String username;
	private String passlength;
	private String filelength;
	private String filename;
	private String userpass;
	private String crc;
	private String terminal_id;
	private String packet_con_length;

	private String bytetype;

	private String msg_id;

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

	public SendLineReq() {

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIplength() {
		return iplength;
	}

	public void setIplength(String iplength) {
		this.iplength = InsideMsgUtils.formatIpLength(iplength);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = InsideMsgUtils.formatPort(port);
	}

	public String getUserlength() {
		return userlength;
	}

	public void setUserlength(String userlength) {
		this.userlength = InsideMsgUtils.formatUserLength(userlength);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasslength() {
		return passlength;
	}

	public void setPasslength(String passlength) {
		this.passlength = InsideMsgUtils.formatPassLength(passlength);
	}

	public String getFilelength() {
		return filelength;
	}

	public void setFilelength(String filelength) {
		this.filelength = InsideMsgUtils.formatFileLength(filelength);
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getCrc() {
		return crc;
	}

	public void setCrc(String crc) {
		this.crc = InsideMsgUtils.formatCrc(crc);
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
			log.info("下行透传命令：" + new String(header)
					+ new String(this.getTerminal_id().getBytes())
					+ new String(this.getPacket_con_length().getBytes())
					+ new String(body) + this.getBytetype()+banben+InsideMsgUtils
					.formatCmdid(terminateid)+getIplength()+getIp()+getPort()+getUserlength()+getUsername()
					+getPasslength()+getUserpass()+getFilelength()+getFilename()+getCrc());
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
		int ipl = Integer.parseInt(getIplength(),16);
		int userl = Integer.parseInt(getUserlength(),16);
		int passl = Integer.parseInt(getPasslength(),16);
		int filel = Integer.parseInt(getFilelength(),16);
		int bodylen = typelen+bblen+msglen+iplen + ipl + portlen + userlen + userl + passlen + passl + filelen + filel + crclen;
		byte[] body = new byte[bodylen];

		byte[] msgidbyte = Converser.hexStringToBytes(InsideMsgUtils
				.formatCmdid(terminateid));
		
		System.arraycopy(Converser.hexStringToBytes(this.getBytetype()), 0,
				body, location,	Converser.hexStringToBytes(getBytetype()).length);
		location += Converser.hexStringToBytes(this.getBytetype()).length;
		System.arraycopy(Converser.hexStringToBytes(banben), 0, body, location,
				Converser.hexStringToBytes(banben).length);
		location += Converser.hexStringToBytes(banben).length;
		System.arraycopy(msgidbyte, 0, body, location, msgidbyte.length);
		location += msgidbyte.length;
		System.arraycopy(Converser.hexStringToBytes(getIplength()), 0, body, location, Converser.hexStringToBytes(getIplength()).length);
		location += Converser.hexStringToBytes(getIplength()).length;
		System.arraycopy(getIp().getBytes("GBK"), 0, body, location, ipl);
		location += ipl;
		System.arraycopy(Converser.hexStringToBytes(getPort()), 0, body, location, Converser.hexStringToBytes(getPort()).length);
		location += Converser.hexStringToBytes(getPort()).length;
		System.arraycopy(Converser.hexStringToBytes(getUserlength()), 0, body, location, Converser.hexStringToBytes(getUserlength()).length);
		location += Converser.hexStringToBytes(getUserlength()).length;
		System.arraycopy(getUsername().getBytes("GBK"), 0, body, location, userl);
		location += userl;
		System.arraycopy(Converser.hexStringToBytes(getPasslength()), 0, body, location, Converser.hexStringToBytes(getPasslength()).length);
		location += Converser.hexStringToBytes(getPasslength()).length;
		System.arraycopy(getUserpass().getBytes("GBK"), 0, body, location, passl);
		location += passl;
		System.arraycopy(Converser.hexStringToBytes(getFilelength()), 0, body, location, Converser.hexStringToBytes(getFilelength()).length);
		location += Converser.hexStringToBytes(getFilelength()).length;
		System.arraycopy(getFilename().getBytes("GBK"), 0, body, location, filel);
		location += filel;
		System.arraycopy(Converser.hexStringToBytes(getCrc()), 0, body, location, crclen);
		// System.out.println(Converser.bytesToHexString(body));
		location += crclen;
		return body;
	}
}

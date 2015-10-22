package com.neusoft.clw.vncs.inside.msg.req;

import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.monitor.SendHistoryCmdBean;

public class SendHistoryCmdReq extends InsideMsg {

//	private Logger log = LoggerFactory.getLogger(SendCmdReq.class);

	public static final String COMMAND = "0010";

	public static final String NORMAL_STATUS = "0000";

//	public static final String SM_STATUS = "1000";

	public static final int TERMINALIDSIZE = 20;

	public static final int PACKETLENSIZE = 8;

	private String terminalId;

	private String packetLen;

	private byte[] packetContent;
	
	public SendHistoryCmdBean cmdbean;

	public SendHistoryCmdBean getCmdbean() {
		return cmdbean;
	}

	public void setCmdbean(SendHistoryCmdBean cmdbean) {
		this.cmdbean = cmdbean;
	}

	private StringBuffer sendStr;
	
	public void setTerminalId(String terminalId) {
		this.terminalId = (terminalId == null || terminalId.equals("")) ? null
				: InsideMsgUtils.formatTerminalId(terminalId);
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setPacketLen(String packetLen) {
		this.packetLen = (packetLen == null || packetLen.equals("")) ? null
				: InsideMsgUtils.formatPacketLen(Integer.parseInt(packetLen));
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
		byte[] packetContent = getbody();
		int location = 0;
		int packetContentLen = packetContent.length;
		int len = InsideMsg.MSGHEADERSIZE + TERMINALIDSIZE + PACKETLENSIZE
				+ packetContentLen;
		this.setPacketLen(String.valueOf(packetContentLen));
		this.setPacketContent(packetContent);
		this.setMsgLength(String.valueOf(len));
		this.setCommand(COMMAND);
		this.setStatusCode(NORMAL_STATUS);
		this.setSeq(InsideMsgUtils.getSeq());
		byte[] buf = new byte[len];
		byte[] header = super.getBytes();
		System.arraycopy(header, 0, buf, location, header.length);
		location += header.length;
		System.arraycopy(this.getTerminalId().getBytes(), 0, buf, location,
				TERMINALIDSIZE);
		location += TERMINALIDSIZE;
		System.arraycopy(this.getPacketLen().getBytes(), 0, buf, location,
				PACKETLENSIZE);
		location += PACKETLENSIZE;
		System.arraycopy(this.getPacketContent(), 0, buf, location,
				packetContentLen);
		return buf;
	}

	private byte[] getbody() {
		sendStr = new StringBuffer();
		sendStr.append(ContentParamName.packet_content00+IdCreater.converToString(Integer.toHexString(SendHistoryCmdBean.len00).toUpperCase(),2)+ cmdbean.getCmd());	
		sendStr.append(ContentParamName.packet_content01+IdCreater.converToString(Integer.toHexString(SendHistoryCmdBean.len01).toUpperCase(),2)+ cmdbean.getMsg_id());
		sendStr.append(ContentParamName.packet_content02+IdCreater.converToString(Integer.toHexString(SendHistoryCmdBean.len02).toUpperCase(),2)+cmdbean.getStart_time());
		sendStr.append(ContentParamName.packet_content03+IdCreater.converToString(Integer.toHexString(SendHistoryCmdBean.len03).toUpperCase(),2)+cmdbean.getEnd_time());
		sendStr.append(ContentParamName.packet_content04+IdCreater.converToString(Integer.toHexString(SendHistoryCmdBean.len04).toUpperCase(),2)+cmdbean.getQuery_field());
//		log.info(LogFormatter.formatMsg("SendHistoryCmdReq",sendStr.toString()));
		return sendStr.toString().getBytes();
	}
}

package com.neusoft.smsplatform.message.inside.msg;

import com.neusoft.clw.vncs.util.Converser;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;


/***
 * 
 * @author chenqiong
 *
 */
public class MesssageInsideMsg {

	/***
	 * 消息长度所占字节
	 */
    public static final int MSGLENSIZE = 4;

    /**
     * 操作码所占字节
     */
    public static final int COMMANDSIZE = 2;

    /**
     * 序列号所占字节
     */
    public static final int SEQUENCESIZE = 8;

    /**
     * message header length(byte)
     */
    public static final int MSGHEADERSIZE = 14;    
    
    
    /**
     * the whole message length
     */
    private String msgLength;
    private String seqLength;

    /**
     * message command
     */
    private String command;
    
    /**
     * @param msgLength The msgLength to set.
     */
    public void setMsgLength(String msgLength) {
        this.msgLength = (msgLength == null || msgLength.equals("")) ? null : SmsCommonMsgUtils
                .formatMsgLen(Integer.parseInt(msgLength));
    }
    
    public int getWhole_length(byte[] msgbyte){
    	return Converser.bytes2Short(msgbyte, 0);
    }

	public String getSeqLength() {
		return seqLength;
	}

	public void setSeqLength(String seqLength) {
		this.seqLength = (seqLength == null || seqLength.equals("")) ? null : SmsCommonMsgUtils
                .formatSeqLen(Integer.parseInt(seqLength));
	}

	/**
     * @return Returns the msgLength.
     */
    public String getMsgLength() {
        return msgLength;
    }

    /**
     * @param command The command to set.
     */
    public void setCommand(String command) {
        this.command = command ;
    }

    /**
     * @return Returns the command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return the msg header byte array
     */
    public byte[] getBytes() {
        int location = 0;
        byte[] buf = new byte[MSGHEADERSIZE];
        System.arraycopy(this.getMsgLength().getBytes(), 0, buf, location, MSGLENSIZE);
        location += MSGLENSIZE;
        System.arraycopy(this.getCommand().getBytes(), 0, buf, location, COMMANDSIZE);
        location += COMMANDSIZE;
        System.arraycopy(this.getSeqLength().getBytes(), 0, buf, location, SEQUENCESIZE);
        location+=SEQUENCESIZE;
        return buf;
    }

	public static void main(String[] args) {
    	
    }
    
    
}

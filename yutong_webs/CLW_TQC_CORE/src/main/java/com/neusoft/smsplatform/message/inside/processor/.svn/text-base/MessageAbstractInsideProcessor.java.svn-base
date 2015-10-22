package com.neusoft.smsplatform.message.inside.processor;

import java.io.UnsupportedEncodingException;

import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsInsideMsgUtils;
import com.neusoft.smsplatform.nio.client.ISmsCommunicateService;




public abstract class MessageAbstractInsideProcessor<E extends MesssageInsideMsg, V extends ISmsCommunicateService>
        implements IMessageInsideProcessor<E, V> {
	
    public static int parseHeader(byte[] buf, MesssageInsideMsg msg) throws ParseException {
        try {
            int location = 0;
            byte[] msglengthbyte = new byte[MesssageInsideMsg.MSGLENSIZE];
            System.arraycopy(buf, location, msglengthbyte, 0, MesssageInsideMsg.MSGLENSIZE);
            msg.setMsgLength(new String(msglengthbyte));
            location += MesssageInsideMsg.MSGLENSIZE;
            
            msg.setCommand(new String(buf,location,MesssageInsideMsg.COMMANDSIZE));
            location += MesssageInsideMsg.COMMANDSIZE;
            
            msg.setSeqLength(new String(buf,location,MesssageInsideMsg.SEQUENCESIZE));
            location+=MesssageInsideMsg.SEQUENCESIZE;
            return location;
        } catch (Throwable t) {
            throw new ParseException("parse message header failed.", t);
        }

    }

    public void validHeader(E msg) throws InvalidMessageException {
//    	log.info("####################"+msg.getCommand());
//    	log.info("********************"+InsideMsgUtils.checkMsgCommand(msg.getCommand()));
        if (msg.getMsgLength() == null) {
            throw new InvalidMessageException("msgLength is invalid.");
        } else if (msg.getCommand() == null || !SmsInsideMsgUtils.checkMsgCommand(msg.getCommand())) {
            throw new InvalidMessageException("command is invalid.");
        } else {
            return;
        }
    }
    
    public static void main(String[] args) throws ParseException, UnsupportedEncodingException, InvalidMessageException{
//    	byte[] buf = HexDumper.hexStringToByte("7E00189004560003C7ED4339304635330000020000000623");
    }
}

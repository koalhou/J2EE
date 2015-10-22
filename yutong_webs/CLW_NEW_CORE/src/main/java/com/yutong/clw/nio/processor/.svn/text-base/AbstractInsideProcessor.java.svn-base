package com.yutong.clw.nio.processor;

//import com.neusoft.communicate.CommunicateService;
//import com.neusoft.tag.app.inside.msg.InsideMsg;
//import com.neusoft.tag.inside.utils.InsideMsgUtils;
import com.yutong.clw.beans.exceptions.InvalidMessageException;
import com.yutong.clw.beans.exceptions.ParseException;
import com.yutong.clw.nio.mina.impl.CommunicateService;
import com.yutong.clw.nio.msg.up.InsideMsg;
import com.yutong.clw.nio.msg.util.InsideMsgUtils;


public abstract class AbstractInsideProcessor<E extends InsideMsg, V extends CommunicateService>
        implements IInsideProcessor<E, V> {

    public int parseHeader(byte[] buf, InsideMsg msg) throws ParseException {
        try {
            int location = 0;
            msg.setMsgLength(new String(buf,location,InsideMsg.MSGLENSIZE));
            location += InsideMsg.MSGLENSIZE;
            msg.setCommand(new String(buf,location,InsideMsg.COMMANDSIZE));
            location += InsideMsg.COMMANDSIZE;
            msg.setStatusCode(new String(buf,location,InsideMsg.STATUSCODESIZE));
            location += InsideMsg.STATUSCODESIZE;
            msg.setSeq(new String(buf,location,InsideMsg.SEQSIZE));
            location += InsideMsg.SEQSIZE;
//            System.out.println(msg.getMsgLength()+","+msg.getCommand()+","+msg.getStatusCode()+","+msg.getSeq());
            return location;
        } catch (Throwable t) {
            throw new ParseException("parse message header failed.", t);
        }

    }

    public void validHeader(E msg) throws InvalidMessageException {
        if (msg.getMsgLength() == null) {
            throw new InvalidMessageException("msgLength is invalid.");
        } else if (msg.getCommand() == null || !InsideMsgUtils.checkMsgCommand(msg.getCommand())) {
            throw new InvalidMessageException("command is invalid.");
        } else if (msg.getStatusCode() == null
                || !InsideMsgUtils.checkStatusCode(msg.getStatusCode())) {
            throw new InvalidMessageException("statusCode is invalid.");
        } else if (msg.getSeq() == null || !InsideMsgUtils.checkSeq(Integer.parseInt(msg.getSeq()))) {
            throw new InvalidMessageException("sequence is invalid.");
        } else {
            return;
        }
    }
}

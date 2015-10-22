package com.yutong.clw.nio.mina.coding;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.nio.msg.up.InsideMsg;

public class ClwDecoder implements ProtocolDecoder {
	
	private static final Logger LOG = LoggerFactory.getLogger(ClwDecoder.class);
	private static final String KEY = "key";
    
	@Override
	public void decode(IoSession session, IoBuffer buf, ProtocolDecoderOutput out) throws Exception {
        int limit = buf.limit();

        
        byte[] bytes = new byte[limit];
        buf.get(bytes,0,limit);
        buf.position(0);
        
        String sbuf=new String(bytes);
        LOG.debug("::::"+sbuf);
      
        
        
        if (session.getAttribute(KEY) == null) {
            Data data = new Data();
            session.setAttribute(KEY, data);
        }
        Data data = (Data) session.getAttribute(KEY);
        if (data.getStatus().equals(Data.RIGHT)) {
            doMsg(buf, out, limit, data);
        } else if (data.getStatus().equals(Data.LESS)) {
            assemble4Less(buf, out, limit, data);
        } else if (data.getStatus().equals(Data.LENGTH_NOT_ENOUGH)) {
            doMsgLen(buf, out, limit, data);
        } else {
            LOG.error("CLW ", "the data's status is invalid.");
        }
    }

    private void doMsgLen(IoBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
        if (data.getLeftLen() == 0) {
            if (limit < InsideMsg.MSGLENSIZE) {
                byte[] bytes = new byte[limit];
                buf.get(bytes);
                data.setBuf(bytes);
                data.setLeftLen(InsideMsg.MSGLENSIZE - limit);
                data.setStatus(Data.LENGTH_NOT_ENOUGH);
            }
        } else {
            if (limit < data.getLeftLen()) {
                byte[] bytes = new byte[limit];
                buf.get(bytes);
                data.setBuf(bytes);
                data.setLeftLen(data.getLeftLen() - limit);
                data.setStatus(Data.LENGTH_NOT_ENOUGH);
            } else if (limit > data.getLeftLen()) {
                byte[] bytes = new byte[data.getLeftLen()];
                buf.get(bytes);
                data.setBuf(bytes);
                byte[] msgLen = new byte[InsideMsg.MSGLENSIZE];
                data.getBuf().get(msgLen);
                int len = Integer.parseInt(new String(msgLen));
                int moreLen = limit - data.getLeftLen();
                data.setLeftLen(len - InsideMsg.MSGLENSIZE);
                data.setStatus(Data.LESS);
                assemble4Less(buf, out, moreLen, data);
            } else {
                byte[] bytes = new byte[limit];
                buf.get(bytes);
                data.setBuf(bytes);
                byte[] msgLen = new byte[InsideMsg.MSGLENSIZE];
                data.getBuf().get(msgLen);
                int len = Integer.parseInt(new String(msgLen));
                data.setLeftLen(len - InsideMsg.MSGLENSIZE);
                data.setStatus(Data.LESS);
            }
        }
    }

    private void doMsg(IoBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
        if (data.getLeftLen() == 0 && limit < InsideMsg.MSGLENSIZE) {
            doMsgLen(buf, out, limit, data);
            return;
        }
        byte[] len = new byte[InsideMsg.MSGLENSIZE];
        buf.get(len);
        data.setBuf(len);
        int msgLength = Integer.parseInt(new String(len));
        int logicalLeftLen = msgLength - InsideMsg.MSGLENSIZE;
        int actualLeftLen = limit - InsideMsg.MSGLENSIZE;
        if (actualLeftLen < logicalLeftLen) {
            byte[] bytes = new byte[actualLeftLen];
            buf.get(bytes);
            data.setBuf(bytes);
            data.setLeftLen(logicalLeftLen - actualLeftLen);
            data.setStatus(Data.LESS);
        } else if (actualLeftLen > logicalLeftLen) {
            byte[] bytes = new byte[logicalLeftLen];
            buf.get(bytes);
            data.setBuf(bytes);
            out.write(data.getBytes());
            data.clean();
            int moreLen = actualLeftLen - logicalLeftLen;
            doMsg(buf, out, moreLen, data);
        } else {
            byte[] bytes = new byte[logicalLeftLen];
            buf.get(bytes);
            data.setBuf(bytes);
            out.write(data.getBytes());
            data.clean();
        }

    }

    private void assemble4Less(IoBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
        if (limit < data.getLeftLen()) {
            byte[] bytes = new byte[limit];
            buf.get(bytes);
            data.setBuf(bytes);
            data.setLeftLen(data.getLeftLen() - limit);
            data.setStatus(Data.LESS);
        } else if (limit > data.getLeftLen()) {
            byte[] bytes = new byte[data.getLeftLen()];
            buf.get(bytes);
            data.setBuf(bytes);
            out.write(data.getBytes());
            int moreLen = limit - data.getLeftLen();
            data.clean();
            doMsg(buf, out, moreLen, data);
        } else {
            byte[] bytes = new byte[data.getLeftLen()];
            buf.get(bytes);
            data.setBuf(bytes);
            out.write(data.getBytes());
            data.clean();
        }
    }

    public void dispose(IoSession arg0) throws Exception {
    }

    public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1) throws Exception {
    }
}
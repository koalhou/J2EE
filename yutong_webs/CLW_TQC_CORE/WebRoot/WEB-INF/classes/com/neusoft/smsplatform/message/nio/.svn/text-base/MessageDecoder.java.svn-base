/*******************************************************************************
 * @(#)Decode.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.nio;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.nio.client.SmsDecoder;

/**
 * 
 * @author chenqiong
 *
 */
public final class MessageDecoder extends SmsDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(MessageDecoder.class);

    private ProtocolDecoder decoder;

    private static final String KEY = "key";

    private static final MessageDecoder OTADECODER = new MessageDecoder();

    private MessageDecoder() {
        decoder = new Decoder();
    }

    public static MessageDecoder getInstance() {
        return OTADECODER;
    }

    static class Decoder implements ProtocolDecoder {

        public void decode(IoSession session, ByteBuffer buf, ProtocolDecoderOutput out)
                throws Exception {
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

        private void doMsgLen(ByteBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
            if (data.getLeftLen() == 0) {
                if (limit < MesssageInsideMsg.MSGLENSIZE) {
                    byte[] bytes = new byte[limit];
                    buf.get(bytes);
                    data.setBuf(bytes);
                    data.setLeftLen(MesssageInsideMsg.MSGLENSIZE - limit);
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
                    byte[] msgLen = new byte[MesssageInsideMsg.MSGLENSIZE];
                    data.getBuf().get(msgLen);
                    int len = Integer.parseInt(new String(msgLen));
                    int moreLen = limit - data.getLeftLen();
                    data.setLeftLen(len - MesssageInsideMsg.MSGLENSIZE);
                    data.setStatus(Data.LESS);
                    assemble4Less(buf, out, moreLen, data);
                } else {
                    byte[] bytes = new byte[limit];
                    buf.get(bytes);
                    data.setBuf(bytes);
                    byte[] msgLen = new byte[MesssageInsideMsg.MSGLENSIZE];
                    data.getBuf().get(msgLen);
                    int len = Integer.parseInt(new String(msgLen));
                    data.setLeftLen(len - MesssageInsideMsg.MSGLENSIZE);
                    data.setStatus(Data.LESS);
                }
            }
        }

        private void doMsg(ByteBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
            if (data.getLeftLen() == 0 && limit < MesssageInsideMsg.MSGLENSIZE) {
                doMsgLen(buf, out, limit, data);
                return;
            }
            byte[] len = new byte[MesssageInsideMsg.MSGLENSIZE];
            buf.get(len);
            data.setBuf(len);
            int msgLength = Integer.parseInt(new String(len));
            int logicalLeftLen = msgLength - MesssageInsideMsg.MSGLENSIZE;
            int actualLeftLen = limit - MesssageInsideMsg.MSGLENSIZE;
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

        private void assemble4Less(ByteBuffer buf, ProtocolDecoderOutput out, int limit, Data data) {
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

    static class Data {

        private static final int BLOCKSIZE = 512;

        private ByteBuffer buf = ByteBuffer.allocate(BLOCKSIZE).setAutoExpand(true);

        private int leftLen = 0;

        private String status = RIGHT;

        public static final String LESS = "LESS";

        public static final String RIGHT = "RIGHT";

        public static final String LENGTH_NOT_ENOUGH = "LENGTH_NOT_ENOUGH";

        public Data() {
            clean();
        }

        public void clean() {
            buf.limit(0);
            buf.position(0);
            leftLen = 0;
            status = RIGHT;
        }

        private byte[] getBytes() {
            byte[] bytes = new byte[getBuf().limit()];
            buf.get(bytes);
            return bytes;
        }

        public void setBuf(byte[] buf) {
            this.buf.put(buf);
        }

        public void setBuf(byte[] data, int offset, int length) {
            this.buf.put(data, offset, length);
        }

        public ByteBuffer getBuf() {
            this.buf.flip();
            return this.buf;
        }

        public void setLeftLen(int leftLen) {
            this.leftLen = leftLen;
        }

        public int getLeftLen() {
            return leftLen;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    public ProtocolDecoder getDecoder() throws Exception {
        return decoder;
    }
}

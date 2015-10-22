/*******************************************************************************
 * @(#)Vg3.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.vg.msg;

import java.nio.ByteBuffer;
import com.neusoft.clw.util.mfqueue.IQueueObject;

// import com.neusoft.tag.vg.utils.Symbol;
// import com.neusoft.tag.vg.utils.VgMsgUtils;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:04:47
 */
public class VgMsg extends MessageParser implements IQueueObject{
    
    private int blockSize;
    
    private ByteBuffer byteBufferMessage = null;
    
    private String seq;

    private String header;

    private String oemcode;

    private String terminalId;
    
    private String[] body;

    private String verifyCode;
    
    private int bodyLen;
    
    private String timeStamp;

    public static final String NAME = "Vg";
    
    public VgMsg(int blockSize) {
        reset();
        this.blockSize = blockSize;
    }

    public int getBlockSize() {
        return blockSize;
    }
    
    public String toBodyString() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<bodyLen; i++) {
            sb.append(body[i] + "|");
        }
        
        return sb.toString();
    }

    public void reset() {
        header = "";
        oemcode = "";
        terminalId = "";
        body = null;
        verifyCode = "";  
        bodyLen = 0;
    }

    public ByteBuffer toByteBuffer() {
        byteBufferMessage = ByteBuffer.allocate(blockSize);
        byteBufferMessage.position(0);
        
/*        System.out.println("=================blockSize" +  blockSize);
        System.out.println("=================getBlockSize" +  Queue.getInstance().getBlockSize());*/
        byteBufferMessage.put(stringToNullTerminateByteArray(seq));
        byteBufferMessage.put(stringToNullTerminateByteArray(header));
        byteBufferMessage.put(stringToNullTerminateByteArray(oemcode));
        byteBufferMessage.put(stringToNullTerminateByteArray(terminalId));
        byteBufferMessage.putInt(bodyLen);
        for (int i = 0; i < bodyLen; i++) {
            byteBufferMessage.put(stringToNullTerminateByteArray(body[i]));
        }
        
        byteBufferMessage.put(stringToNullTerminateByteArray(verifyCode));
        byteBufferMessage.put(stringToNullTerminateByteArray(timeStamp));
        
        return byteBufferMessage;
    }

    public void toObject(byte[] objByteArray) {
        reset();
        int i = 0;
        seq = byteArrayToNullTerminateString(objByteArray, i);
        i = nextStringPosition(i, seq);
        header = byteArrayToNullTerminateString(objByteArray, i);
        i = nextStringPosition(i, header);
        oemcode = byteArrayToNullTerminateString(objByteArray, i);
        i = nextStringPosition(i, oemcode);
        terminalId = byteArrayToNullTerminateString(objByteArray, i);
        i = nextStringPosition(i, terminalId);
        bodyLen = byteArrayToInteger(objByteArray, i);
        i = nextIntegerPosition(i);
        
        body = new String[bodyLen];
        for (int j = 0 ; j < bodyLen; j++) {
            body[j] = byteArrayToNullTerminateString(objByteArray, i);
            i = nextStringPosition(i, body[j]);
        }
        
        verifyCode = byteArrayToNullTerminateString(objByteArray, i); 
        i = nextStringPosition(i, verifyCode);
        timeStamp =  byteArrayToNullTerminateString(objByteArray, i);     
    }
    

    public String[] getBody() {
        return body;
    }

    public void setBody(String[] body) {
        this.body = (body.length <= 0) ? null : body;       
        this.bodyLen = (body.length <= 0) ? 0 : body.length;;
    }

    /**
     * @return Returns the oemcode.
     */
    public final String getOemcode() {
        return oemcode;
    }

    /**
     * @param oemcode The oemcode to set.
     */
    public final void setOemcode(String oemcode) {
        this.oemcode = (oemcode == null || oemcode.equals("")) ? null : oemcode;
    }

    /**
     * @return Returns the terminalId.
     */
    public final String getTerminalId() {
        return terminalId;
    }

    /**
     * @param terminalId The terminalId to set.
     */
    public final void setTerminalId(String terminalId) {
        this.terminalId = (terminalId == null || terminalId.equals("")) ? null : terminalId;
    }

    /**
     * @return Returns the verifyCode.
     */
    public final String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode The verifyCode to set.
     */
    public final void setVerifyCode(String verifyCode) {
        this.verifyCode = (verifyCode == null || verifyCode.equals("")) ? null : verifyCode;
    }

    /**
     * @param header The header to set.
     */
    public void setHeader(String header) {
        this.header = (header == null || header.equals("")) ? null : header;
    }

    /**
     * @return Returns the header.
     */
    public String getHeader() {
        return header;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    

    // protected byte[] getArgsBuf() {
    // StringBuffer sb = new StringBuffer();
    // for (String arg : getArgs()) {
    // sb.append(arg);
    // sb.append(Symbol.SEMICOLON);
    // }
    // String args = sb.toString();
    // return args.substring(0, args.length() - 1).getBytes();
    // }

//    public byte[] getBytes() {
//        int location = 0;
//        byte[] headerBuf = getHeader().getBytes();
//        byte[] space1Buf = Symbol.SPACE.getBytes();
//        byte[] seqBuf = getSeq().getBytes();
//        byte[] space2Buf = Symbol.SPACE.getBytes();
//        byte[] oemcodeBuf = getOemcode().getBytes();
//        byte[] colonBuf = Symbol.COLON.getBytes();
//        byte[] terminalIdBuf = getTerminalId().getBytes();
//        byte[] verticalBar1Buf = Symbol.VERTICALBAR.getBytes();
//        byte[] commandBuf = getCommand().getBytes();
//        byte[] verticalBar2Buf = Symbol.VERTICALBAR.getBytes();
//        byte[] argsBuf = getArgs();// getArgsBuf();
//        byte[] space3Buf = Symbol.SPACE.getBytes();
//        this.setVerifyCode(calcuteVerifyCode(oemcodeBuf, colonBuf, terminalIdBuf, verticalBar1Buf,
//                commandBuf, verticalBar2Buf, argsBuf, space3Buf));
//        byte[] verifyCodeBuf = getVerifyCode().getBytes();
//        byte[] tailBuf = Symbol.TAIL.getBytes();
//        byte[] msg = new byte[headerBuf.length + space1Buf.length + seqBuf.length
//                + space2Buf.length + oemcodeBuf.length + colonBuf.length + terminalIdBuf.length
//                + verticalBar1Buf.length + commandBuf.length + verticalBar2Buf.length
//                + argsBuf.length + space3Buf.length + verifyCodeBuf.length + tailBuf.length];
//        System.arraycopy(headerBuf, 0, msg, location, headerBuf.length);
//        location += headerBuf.length;
//        System.arraycopy(space1Buf, 0, msg, location, space1Buf.length);
//        location += space1Buf.length;
//        System.arraycopy(seqBuf, 0, msg, location, seqBuf.length);
//        location += seqBuf.length;
//        System.arraycopy(space2Buf, 0, msg, location, space2Buf.length);
//        location += space2Buf.length;
//        System.arraycopy(oemcodeBuf, 0, msg, location, oemcodeBuf.length);
//        location += oemcodeBuf.length;
//        System.arraycopy(colonBuf, 0, msg, location, colonBuf.length);
//        location += colonBuf.length;
//        System.arraycopy(terminalIdBuf, 0, msg, location, terminalIdBuf.length);
//        location += terminalIdBuf.length;
//        System.arraycopy(verticalBar1Buf, 0, msg, location, verticalBar1Buf.length);
//        location += verticalBar1Buf.length;
//        System.arraycopy(commandBuf, 0, msg, location, commandBuf.length);
//        location += commandBuf.length;
//        System.arraycopy(verticalBar2Buf, 0, msg, location, verticalBar2Buf.length);
//        location += verticalBar2Buf.length;
//        System.arraycopy(argsBuf, 0, msg, location, argsBuf.length);
//        location += argsBuf.length;
//        System.arraycopy(space3Buf, 0, msg, location, space3Buf.length);
//        location += space3Buf.length;
//        System.arraycopy(verifyCodeBuf, 0, msg, location, verifyCodeBuf.length);
//        location += verifyCodeBuf.length;
//        System.arraycopy(tailBuf, 0, msg, location, tailBuf.length);
//        location += tailBuf.length;
//        return msg;
//    }

//    private String calcuteVerifyCode(byte[] oemcodeBuf, byte[] colonBuf, byte[] terminalIdBuf,
//            byte[] verticalBar1Buf, byte[] commandBuf, byte[] verticalBar2Buf, byte[] argsBuf,
//            byte[] space3Buf) {
//        int count = 0;
//        int location = 0;
//        final int verifyCodeLen = 4;
//        byte[] buf = new byte[oemcodeBuf.length + colonBuf.length + terminalIdBuf.length
//                + verticalBar1Buf.length + commandBuf.length + verticalBar2Buf.length
//                + argsBuf.length + space3Buf.length];
//        System.arraycopy(oemcodeBuf, 0, buf, location, oemcodeBuf.length);
//        location += oemcodeBuf.length;
//        System.arraycopy(colonBuf, 0, buf, location, colonBuf.length);
//        location += colonBuf.length;
//        System.arraycopy(terminalIdBuf, 0, buf, location, terminalIdBuf.length);
//        location += terminalIdBuf.length;
//        System.arraycopy(verticalBar1Buf, 0, buf, location, verticalBar1Buf.length);
//        location += verticalBar1Buf.length;
//        System.arraycopy(commandBuf, 0, buf, location, commandBuf.length);
//        location += commandBuf.length;
//        System.arraycopy(verticalBar2Buf, 0, buf, location, verticalBar2Buf.length);
//        location += verticalBar2Buf.length;
//        System.arraycopy(argsBuf, 0, buf, location, argsBuf.length);
//        location += argsBuf.length;
//        System.arraycopy(space3Buf, 0, buf, location, space3Buf.length);
//        location += space3Buf.length;
//        for (int i = 0; i < buf.length; i++) {
//            if (buf[i] < 0) {
//                count += 256 + buf[i];
//            } else {
//                count += buf[i];
//            }
//
//        }
//        String hexString = Integer.toHexString(count);
//        if (hexString.length() > verifyCodeLen) {
//            hexString = hexString.substring(hexString.length() - verifyCodeLen, hexString.length());
//        }
//        return hexString;
//    }

//    public String toString() {
//        return new String(getBytes()).trim();
//    }
}

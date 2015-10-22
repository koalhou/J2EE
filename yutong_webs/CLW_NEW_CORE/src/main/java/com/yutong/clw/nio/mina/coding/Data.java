package com.yutong.clw.nio.mina.coding;

import org.apache.mina.core.buffer.IoBuffer;

public class Data {

    private static final int BLOCKSIZE = 512;

    private IoBuffer buf = IoBuffer.allocate(BLOCKSIZE).setAutoExpand(true);

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

    public byte[] getBytes() {
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

    public IoBuffer getBuf() {
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

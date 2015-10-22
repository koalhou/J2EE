package com.neusoft.clw.yw.zd.terminal.ds;

/**
 * 终端通道信息bean
 * @author JinPeng
 */
public class ChannelInfo {
    /** 通道ID **/
    private String channelId = "";

    /** 通道名称 **/
    private String channelName = "";

    /** 通道号 **/
    private String channelNumber = "";

    /** 终端号 **/
    private String terminalCode = "";

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}

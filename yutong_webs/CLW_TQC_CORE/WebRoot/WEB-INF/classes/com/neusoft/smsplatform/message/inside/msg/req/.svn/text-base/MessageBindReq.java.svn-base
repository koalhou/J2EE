package com.neusoft.smsplatform.message.inside.msg.req;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;

public class MessageBindReq extends MesssageInsideMsg {

    public static final String COMMAND = "01";

    public static final int USERIDSIZE = 10;

    public static final int PASSWORDSIZE = 10;

    public static final int MSGSIZE = 34;

    private String password;

    private String userId;

    private String randomSerial;

    public void setPassword(String password) {
        this.password = (password==null||password.equals(""))?null:SmsCommonMsgUtils.rightspaceformat(password, 10);
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = (userId==null||userId.equals(""))?null:SmsCommonMsgUtils.rightspaceformat(userId, 10);
    }

    public String getRandomSerial() {
        return randomSerial;
    }

    public void setRandomSerial(String randomSerial) {
        this.randomSerial = randomSerial;
    }

    @Override
    public byte[] getBytes() {
        int location = 0;
        byte[] buf = new byte[MSGSIZE];
//        System.out.println("BindReq:len="+this.getMsgLength());
//        System.out.println("BindReq:command="+this.getCommand());
//        System.out.println("BindReq:seq="+this.getSeqLength());
//        System.out.println("BindReq:id="+this.getUserId());
//        System.out.println("BindReq:pass="+this.getPassword());
        System.arraycopy(this.getMsgLength().getBytes(), 0, buf, location,MSGLENSIZE);
        location += MSGLENSIZE;
        System.arraycopy(this.getCommand().getBytes(), 0, buf, location, COMMANDSIZE);
        location += COMMANDSIZE;
        System.arraycopy(this.getSeqLength().getBytes(), 0, buf, location, SEQUENCESIZE);
        location+=SEQUENCESIZE;
        System.arraycopy(this.getUserId().getBytes(), 0, buf, location, USERIDSIZE);
        location += USERIDSIZE;
        System.arraycopy(this.getPassword().getBytes(), 0, buf, location, PASSWORDSIZE);
        location += PASSWORDSIZE;
        System.out.println("BindReq:"+new String(buf));
        return buf;
    }
}

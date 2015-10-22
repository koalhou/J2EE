/*******************************************************************************
 * @(#)MessageFactory.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.inside.msg;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.vncs.inside.msg.req.ActiveTestReq;
import com.neusoft.clw.vncs.inside.msg.req.BindReq;
import com.neusoft.clw.vncs.inside.msg.req.QueryTerminalReq;
import com.neusoft.clw.vncs.inside.msg.req.SendCmdReq;
import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;
import com.neusoft.clw.vncs.inside.msg.resp.ActiveTestResp;
import com.neusoft.clw.vncs.inside.msg.resp.UploadDataResp;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.inside.msg.utils.StringUtils;
import com.neusoft.clw.vncs.monitor.CmdBean;


public final class InsideMsgFactory {

    private InsideMsgFactory() {
    }

    public static InsideMsg createActiveTestReq() {
        ActiveTestReq activeTest = new ActiveTestReq();
        activeTest.setMsgLength(String.valueOf(ActiveTestReq.MSGHEADERSIZE));
        activeTest.setCommand(ActiveTestReq.COMMAND);
        activeTest.setStatusCode(ActiveTestReq.STATUS);
        activeTest.setSeq(InsideMsgUtils.getSeq());
        return activeTest;
    }

    public static InsideMsg createActiveTestResp(String seq) {
        ActiveTestResp resp = new ActiveTestResp();
        resp.setMsgLength(String.valueOf(ActiveTestResp.MSGHEADERSIZE));
        resp.setCommand(ActiveTestResp.COMMAND);
        resp.setStatusCode(ActiveTestResp.STATUS);
        resp.setSeq(seq);
        return resp;
    }

    public static InsideMsg createBindReq() {
        BindReq req = new BindReq();
        req.setMsgLength(String.valueOf(BindReq.MSGHEADERSIZE + BindReq.TIMESIZE
                + BindReq.SYSTEMIDSIZE + BindReq.MD5CODESIZE));
        req.setCommand(BindReq.COMMAND);
        req.setStatusCode(BindReq.STATUS);
        req.setSeq(InsideMsgUtils.getSeq());
        req.setTime(InsideMsgUtils.getCurrentTime());
        req.setSystemId(Config.props.getProperty("username"));
//        req.setPassword(SUNBASE64.decodeString(Config.props
//                .getProperty("password").trim()));
        req.setPassword(Config.props.getProperty("password"));
        System.out.println("bindreq:"+req.toString());
        return req;
    }

    public static InsideMsg createUpLoadDataReq() {
        return new UpLoadDataReq();
    }

    public static InsideMsg createUpLoadDataResp(String seq) {
        UploadDataResp resp = new UploadDataResp();
        resp.setMsgLength(String.valueOf(UploadDataResp.MSGHEADERSIZE));
        resp.setCommand(UploadDataResp.COMMAND);
        resp.setStatusCode(UploadDataResp.STATUS);
        resp.setSeq(seq);
        return resp;
    }
    
    public static InsideMsg createUpLoadDataResp(String status,String seq) {
        UploadDataResp resp = new UploadDataResp();
        resp.setMsgLength(String.valueOf(UploadDataResp.MSGHEADERSIZE));
        resp.setCommand(UploadDataResp.COMMAND);
        resp.setStatusCode(status);
        resp.setSeq(seq);
        return resp;
    }
    
	 public static InsideMsg createSendCmd(CmdBean terminal) {
	  SendCmdReq req = new SendCmdReq();
	  req.setCmdbean(terminal);
	  req.setCommand(SendCmdReq.COMMAND);
	  req.setStatusCode(SendCmdReq.NORMAL_STATUS);
	  req.setSeq(terminal.getVGSEQ());
	  req.setTerminalId(terminal.getTERMINAL_ID());
	  //req.setPacketLen(String.valueOf(packetContent.length));
	  //req.setPacketContent(packetContent);
	  return req;
	}
	 
	 public static InsideMsg createQueryTerminalReq(String[] terminal) {
	        QueryTerminalReq req = new QueryTerminalReq();
	        req.setCommand(QueryTerminalReq.COMMAND);
	        req.setStatusCode(QueryTerminalReq.STATUS);
	        req.setSeq(InsideMsgUtils.getSeq());
	        StringBuffer terminalBuffer = new StringBuffer();
	        for (int i = 0; i < terminal.length; i++) {
	            String temp = StringUtils.addPostposition(terminal[i], " ",
	                    QueryTerminalReq.TERMINALIDSIZE);

	            terminalBuffer.append(temp);
	        }
	        String count = StringUtils.addPrefix(String.valueOf(terminal.length), "0",
	                QueryTerminalReq.COUNTSIZE);
	        req.setTerminalCount(count);
	        req.setTerminalId(terminalBuffer.toString());
	        req.setMsgLength(String.valueOf(QueryTerminalReq.MSGHEADERSIZE + QueryTerminalReq.COUNTSIZE
	                + (20 * terminal.length)));
	        return req;
	    }
}

/*******************************************************************************
 * @(#)TcpHandler.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.nio.client.AbstractNioHandler;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.ActiveCoreDAO;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;
import com.neusoft.clw.vncs.inside.msg.InsideMsgStatusCode;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.resp.ActiveTestResp;
import com.neusoft.clw.vncs.inside.msg.resp.BindResp;
import com.neusoft.clw.vncs.inside.processor.IInsideProcessor;
import com.neusoft.clw.vncs.inside.processor.InsideProcessorMap;
import com.neusoft.clw.vncs.util.Converser;

public class ClwCommunicateHandler extends AbstractNioHandler<CommunicateService> {

    private Logger log = LoggerFactory.getLogger(ClwCommunicateHandler.class);

    private static final String NAME = "<ClwCommunicateHandler>";
    
    private boolean isLogined;

    private ActiveTest activeTest;
//    private VgSendDataService vgSendDataService; 
    public ClwCommunicateHandler(CommunicateService nioService) {
        super(nioService);
    }

    public void connectionClosed(CommunicateService nioService) throws Exception {
        log.info(NAME+ "the session between ota and " + nioService.getRemoteAddress() + " is closed.");
        isLogined = false;
        cancelActiveTest();
//        for(int i=0;i<Constant.array.size();i++){
//        	if(nioService.getRemoteAddress().equals(Constant.array.get(i))){
//        		Constant.array.remove(i);
//        	}
//        }
//        System.out.println("array:"+Constant.array.size());
        nioService.reconnect();
    }

    public void connectionOpen(CommunicateService nioService) throws Exception {
    	
        byte[] bytes = InsideMsgFactory.createBindReq().getBytes();
        nioService.send(bytes);
        StringBuffer sb = new StringBuffer();
        sb.append("send a bind request message.");
        if (log.isDebugEnabled()) {
            sb.append(new String(bytes));
        }
        log.info(NAME+ sb.toString());
    }

    @SuppressWarnings("unchecked")
    public void doMsg(CommunicateService nioService, byte[] buf) throws Exception {
        try {
        	while (!SpringBootStrap.getInstance().isInit()) {
//        		System.out.println(SpringBootStrap.getInstance().isInit());
        		Thread.sleep(1000);
//        		System.out.println("-----------");
        	}
 	
//        	byte[] hh = "00000065001100001588VS-VIN00000000001   0000001700041900010003".getBytes();
//        	byte[] bb = Converser.hexStringToBytes("80020C");
//        	buf = new byte[hh.length+bb.length];
//        	int location = 0;
//        	System.arraycopy(hh, 0, buf, location, hh.length);
//        	location+=hh.length;
//        	System.arraycopy(bb, 0, buf, location, bb.length);
//        	buf  = "00000391001100008304LZYTGG55555667788   0000034300041000350800000003020111301114010030902229034704090712901B70505030d43304007206046aa4010c1212181807311E08000074cc36050000039080000000007020009100000000000000000100850d040631208000002b6200c00000d3bc1a046080050000A290400d22A0300a31030004708FFFFFFFF4808FFFFFFFF4902000E08FFFFFFFF5008FFFFFFFF5102005208FFFFFFFF530201540200550800000847450800000000".getBytes();
        	String xmlStr= null;
        	if(buf.length>62&&getdelivercmd(buf).equals("1900")){
            	log.info(NAME+"hand:::"+new String(buf,0,62)+getTcContent(buf));
        	}else{
        		xmlStr = new String(buf,0,buf.length);
        		log.info(NAME+"hand:::"+ xmlStr);
	        	buf = xmlStr.getBytes();
        	}
            String command = getCommand(buf);
            if (!isLogined) {
                if (command.equals(BindResp.COMMAND)) {
                    doLogin(nioService, buf, command);
                }
            } else {
                activeTest.clear();
                if (command.equals(ActiveTestResp.COMMAND)) {
                    activeTest.doActiveTestResp();
                    ActiveCoreDAO activeCoreDAO = (ActiveCoreDAO) SpringBootStrap.getInstance().getBean("activeCoreDAO"); 
                    activeCoreDAO.operateParamTable();
                } else {
                    IInsideProcessor processor = InsideProcessorMap.getInstance().getProcessor(command);
                    if (processor != null) {
                        InsideMsg msg = processor.parse(buf);
                        processor.valid(msg);
                        processor.handle(msg, nioService);
                    } else {
                        log.error(NAME + "there is no processor for command:" + command);
                    }
//                    System.out.println(VehicleCacheManager.vehicleMap.size());
//                    System.out.println(VehicleCacheManager.getInstance().getValue("SUSUCTD60A1026622").getModify_time());
                }
            }
            buf = null;
            command = null;
        } catch (Throwable t) {
        	if(buf.length>62&&getdelivercmd(buf).equals("1900")){
            	log.error(NAME+"there is a exception when deal with the message:" + new String(buf,0,62)+getTcContent(buf), t);
        	}else{
        		log.error(NAME+"there is a exception when deal with the message:" + new String(buf), t);
        	}     
        	buf = null;
        }
    }

    @SuppressWarnings("unchecked")
    private void doLogin(CommunicateService nioService, byte[] buf, String command) throws Exception {
    	
    	while (!SpringBootStrap.getInstance().isInit()) {
    		Thread.sleep(5000);		
//    		System.out.println("-----------");
    	}
    	
        IInsideProcessor processor = InsideProcessorMap.getInstance().getProcessor(command);
        if (processor != null) {
            InsideMsg msg = processor.parse(buf);
            processor.valid(msg);
            if (InsideMsgStatusCode.STATUS_CODE_SUCCESS.equals(msg.getStatusCode())) {
                isLogined = true;
                log.info(NAME+ "receive bind response." + " ------Login " + nioService.getRemoteAddress() + " successfully------");
                startActiveTest(nioService);
//                startVgSendDataService(nioService);
            } else if (InsideMsgStatusCode.STATUS_COMMAND_WRONG.equals(msg.getStatusCode())) {
                log.error(NAME+"username or password in the login message is wrong.Login Failed!!!");
            } else {
                log.error(NAME+"status in the login response is " + msg.getStatusCode() + ".Login Failed!!!");
            }
        } else {
            log.error(NAME+"there is no processor for command:" + command);
        }
    }

    private void startActiveTest(CommunicateService nioService) {
        activeTest = new ActiveTest(nioService);
        activeTest.start();
    }
//	private void startVgSendDataService(CommunicateService nioService) {
//    	vgSendDataService = new VgSendDataService(nioService, InsideMsgFactory.createActiveTestReq().getBytes());
//    	vgSendDataService.start();
//    }

    private void cancelActiveTest() {
        if (activeTest != null) {
            activeTest.cancel();
        }
    }

    private String getCommand(byte[] buf) {
        byte[] commandBuf = new byte[InsideMsg.COMMANDSIZE];
        System.arraycopy(buf, InsideMsg.MSGLENSIZE, commandBuf, 0, InsideMsg.COMMANDSIZE);
        return new String(commandBuf);
    }
    
    private String getTcContent(byte[] buf){
    	int len = ContentParamName.getWhole_len(buf);
    	byte[] tccontent = new byte[len - 62];
    	System.arraycopy(buf, 62, tccontent, 0, tccontent.length);
    	return Converser.bytesToHexString(tccontent);
    }
    
    private String getdelivercmd(byte[] buf){
    	byte[] cmd = new byte[4];
    	System.arraycopy(buf, 52, cmd, 0, 4);
    	return new String(cmd);
    }
}

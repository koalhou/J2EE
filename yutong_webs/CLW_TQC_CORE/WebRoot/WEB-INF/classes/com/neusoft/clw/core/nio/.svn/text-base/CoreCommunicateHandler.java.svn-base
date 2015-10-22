/*******************************************************************************
 * @(#)TcpHandler.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.core.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.core.processor.IProcessor;
import com.neusoft.clw.core.processor.ProcessorMap;
import com.neusoft.clw.core.processor.take.LoginProcessor;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.nio.client.AbstractNioHandler;
import com.neusoft.clw.spring.SpringBootStrap;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午01:23:32
 */
public class CoreCommunicateHandler extends AbstractNioHandler<NioCommunicateService> {

    private Logger log = LoggerFactory.getLogger(CoreCommunicateHandler.class);

    @SuppressWarnings("unused")
	private boolean isLogined;

    private ActiveTest activeTest;
    private CoreBeatInfo coreInfo;

    public CoreCommunicateHandler(NioCommunicateService nioService) {
        super(nioService);
    }

    public void connectionClosed(NioCommunicateService nioService) throws Exception {
        log.info(LogFormatter.formatMsg("TagCommunicateHandler", "the session between ota and "
                + nioService.getRemoteAddress() + " is closed."));
        isLogined = false;
        cancelActiveTest();
        nioService.reconnect();
    }

    public void connectionOpen(NioCommunicateService nioService) throws Exception {
    	
    	
    	LoginProcessor lp=LoginProcessor.getInstance();
    	lp.GetUpXml().getBytes();
        byte[] bytes = "open".getBytes();
        nioService.send(lp.GetUpXml().getBytes());
        StringBuffer sb = new StringBuffer();
        sb.append("send a bind request message.");
        if (log.isDebugEnabled()) {
            sb.append(new String(bytes));
        }
        log.info(LogFormatter.formatMsg("CoreCommunicateHandler", sb.toString()));
        
        coreInfo = new CoreBeatInfo(nioService, "test".getBytes());
        coreInfo.start();
    }

    @SuppressWarnings("unchecked")
    public void doMsg(NioCommunicateService nioService, byte[] buf) throws Exception {
        try {	
        	String xmlStr=new String(buf,0,buf.length-1);
        	System.out.println("ssssssssss:::"+ xmlStr);
			OlxDocument doc = OlxDocument.Factory.parse(xmlStr);
			
			Olx olx=doc.getOlx();
			@SuppressWarnings("unused")
			String olxdir=olx.getDir();
			Function function=olx.getFunction();
        	String action=function.getName();

            IProcessor processor = ProcessorMap.getInstance().getProcessor(
            		action);
            if (processor != null) {
              
                processor.valid(doc);
                processor.handle(doc,nioService);
            } else {
                log.error(LogFormatter.formatMsg("TagCommunicateHandler",
                        "there is no processor for command:" + action));
            }
        } catch (Throwable t) {
            log.error(LogFormatter.formatMsg("TagCommunicateHandler",
                    "there is a exception when deal with the message:" + new String(buf)), t);
        }
    }

    @SuppressWarnings({"unused" })
    private void doLogin(NioCommunicateService nioService, byte[] buf, String command)
            throws Exception {
    	
    	while (!SpringBootStrap.getInstance().isInit()) {
    		Thread.sleep(1000);
    		
    		System.out.println("-----------");
    	}
    	
        
    }

    @SuppressWarnings("unused")
	private void startActiveTest(NioCommunicateService nioService) {
        activeTest = new ActiveTest(nioService, "test".getBytes());
        activeTest.start();
    }

    private void cancelActiveTest() {
        if (activeTest != null) {
            activeTest.cancel();
        }
    }

    @SuppressWarnings("unused")
	private String getCommand(byte[] buf) {
        byte[] commandBuf = new byte[11];
           return new String(commandBuf);
    }
}

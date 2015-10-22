/*******************************************************************************
 * @(#)TcpHandler.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.MessageInsideMsgFactory;
import com.neusoft.smsplatform.message.inside.msg.MessageInsideMsgStatusCode;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageActiveTestResp;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageBindResp;
import com.neusoft.smsplatform.message.inside.processor.IMessageInsideProcessor;
import com.neusoft.smsplatform.message.inside.processor.MessageInsideProcessorMap;
import com.neusoft.smsplatform.nio.client.SmsAbstractNioHandler;

public class MessageCommunicateHandler extends
		SmsAbstractNioHandler<SmsCommunicateService> {

	private Logger log = LoggerFactory
			.getLogger(MessageCommunicateHandler.class);

	private static final String NAME = "<MessageCommunicateHandler>";

	private boolean isLogined;

	private MessageActiveTest activeTest;

	// private VgSendDataService vgSendDataService;
	public MessageCommunicateHandler(SmsCommunicateService nioService) {
		super(nioService);
	}

	public void connectionClosed(SmsCommunicateService nioService)
			throws Exception {
		log.info(NAME + "the session between ota and "
				+ nioService.getRemoteAddress() + " is closed.");
		isLogined = false;
		cancelActiveTest();
		// for(int i=0;i<Constant.array.size();i++){
		// if(nioService.getRemoteAddress().equals(Constant.array.get(i))){
		// Constant.array.remove(i);
		// }
		// }
		// System.out.println("array:"+Constant.array.size());
		nioService.reconnect();
	}

	public void connectionOpen(SmsCommunicateService nioService) throws Exception {
		byte[] bytes = MessageInsideMsgFactory.createBindReq().getBytes();
        nioService.send(bytes);
        StringBuffer sb = new StringBuffer();
        sb.append("send a bind request message.");
        if (log.isDebugEnabled()) {
            sb.append(new String(bytes));
        }
        log.info(NAME+ sb.toString());
	}

	@SuppressWarnings("unchecked")
	public void doMsg(SmsCommunicateService nioService, byte[] buf)
			throws Exception {
		try {
			while (!SpringBootStrap.getInstance().isInit()) {
//				System.out.println(SpringBootStrap.getInstance().isInit());
				Thread.sleep(1000);
				// System.out.println("-----------");
			}
			// byte[] b = new byte[buf.length-InsideMsg.HEADER];
			// System.arraycopy(b, buf.length-InsideMsg.HEADER, buf,
			// InsideMsg.HEADER, buf.length);
			String xmlStr = new String(buf);
			log.info(NAME + "hand:::" + xmlStr);
			// buf = xmlStr.getBytes();
			String command = getCommand(buf);

			if (!isLogined) {
				if (command.equals(MessageBindResp.COMMAND)) {
					doLogin(nioService, buf, command);
				}
			} else {
				if (command.equals(MessageActiveTestResp.COMMAND)) {
					activeTest.doActiveTestResp(buf);
				} else {
					IMessageInsideProcessor processor = MessageInsideProcessorMap
							.getInstance().getProcessor(command);
					if (processor != null) {
						MesssageInsideMsg msg = processor.parse(buf);
						processor.valid(msg);
						processor.handle(msg, nioService);
					} else {
						log.error(NAME + "there is no processor for command:"
								+ command);
					}
					// System.out.println(VehicleCacheManager.vehicleMap.size());
					// System.out.println(VehicleCacheManager.getInstance().getValue("SUSUCTD60A1026622").getModify_time());
				}
			}
		} catch (Throwable t) {
			log.error(NAME + "there is a exception when deal with the message:"
					+ new String(buf), t);
		}
	}

	@SuppressWarnings( { "unchecked" })
	private void doLogin(SmsCommunicateService nioService, byte[] buf,
			String command) throws Exception {

		while (!SpringBootStrap.getInstance().isInit()) {
			Thread.sleep(5000);
			// System.out.println("-----------");
		}

		IMessageInsideProcessor processor = MessageInsideProcessorMap.getInstance()
				.getProcessor(command);
		if (processor != null) {
			// InsideMsg msg = (InsideMsg) processor.parse(buf);
			// processor.valid(msg);
			String status = getResult(buf);
			log.info(NAME + ":" + status);
			if (MessageInsideMsgStatusCode.STATUS_CODE_SUCCESS.equals(status)) {
				isLogined = true;
				log
						.info(NAME + "receive bind response." + " ------Login "
								+ nioService.getRemoteAddress()
								+ " successfully------");
				startActiveTest(nioService);
			} else {
				log.error(NAME + "status in the login response is "
						+ getResult(buf) + ".Login Failed!!!");
			}
		} else {
			log.error(NAME + "there is no processor for command:" + command);
		}
	}

	private void startActiveTest(SmsCommunicateService nioService) {
		activeTest = new MessageActiveTest(nioService, MessageInsideMsgFactory
				.createActiveTestReq().getBytes());
		activeTest.start();
	}

	private void cancelActiveTest() {
		if (activeTest != null) {
			activeTest.cancel();
		}
	}

	/**
	 * parse the msg , get the command field
	 * 
	 * @param msg
	 * @return command
	 */
	private String getCommand(byte[] msg) {
		byte[] commandBuf = new byte[MesssageInsideMsg.COMMANDSIZE];
		System.arraycopy(msg, MesssageInsideMsg.MSGLENSIZE,
				commandBuf, 0, MesssageInsideMsg.COMMANDSIZE);
		return new String(commandBuf);
	}

	private String getResult(byte[] msg) {
		byte[] resultBuf = new byte[MessageBindResp.RESULTSIZE];
		System.arraycopy(msg, MesssageInsideMsg.MSGHEADERSIZE, resultBuf, 0,
				MessageBindResp.RESULTSIZE);
		return new String(resultBuf);
	}
}

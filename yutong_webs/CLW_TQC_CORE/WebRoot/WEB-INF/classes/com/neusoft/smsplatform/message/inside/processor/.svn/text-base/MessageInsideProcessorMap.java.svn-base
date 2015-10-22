package com.neusoft.smsplatform.message.inside.processor;

import java.util.concurrent.ConcurrentHashMap;

import com.neusoft.smsplatform.message.inside.msg.req.MessageActiveTestReq;
import com.neusoft.smsplatform.message.inside.msg.req.ChangePhoneReq;
import com.neusoft.smsplatform.message.inside.msg.req.OrderRelationsReq;
import com.neusoft.smsplatform.message.inside.msg.req.ReceiveStuInfoReq;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageBindResp;
import com.neusoft.smsplatform.message.inside.msg.resp.SendMessageResp;
import com.neusoft.smsplatform.message.inside.msg.resp.SyncEnterpriseInfoResp;
import com.neusoft.smsplatform.message.inside.msg.resp.SyncStuInfoResp;
import com.neusoft.smsplatform.message.inside.processor.req.MessageActiveTestReqProcessor;
import com.neusoft.smsplatform.message.inside.processor.req.ChangePhoneReqProcessor;
import com.neusoft.smsplatform.message.inside.processor.req.OrderRelationsReqProcessor;
import com.neusoft.smsplatform.message.inside.processor.req.ReceiveStuInfoReqProcessor;
import com.neusoft.smsplatform.message.inside.processor.resp.MessageBindRespProcessor;
import com.neusoft.smsplatform.message.inside.processor.resp.SendMessageRespProcessor;
import com.neusoft.smsplatform.message.inside.processor.resp.SyncEnterpriseInfoRespProcessor;
import com.neusoft.smsplatform.message.inside.processor.resp.SyncStuInfoRespProcessor;


public final class MessageInsideProcessorMap {

    private static final MessageInsideProcessorMap PROCESSOR_MAP = new MessageInsideProcessorMap();

    @SuppressWarnings("unchecked")
    private ConcurrentHashMap<String, IMessageInsideProcessor> map = new ConcurrentHashMap<String, IMessageInsideProcessor>();

    private MessageInsideProcessorMap() {
        map.put(MessageBindResp.COMMAND, MessageBindRespProcessor.getInstance());
        map.put(MessageActiveTestReq.COMMAND, MessageActiveTestReqProcessor.getInstance());
        map.put(SyncEnterpriseInfoResp.COMMAND, SyncEnterpriseInfoRespProcessor.getInstance());
        map.put(SyncStuInfoResp.COMMAND, SyncStuInfoRespProcessor.getInstance());
        map.put(OrderRelationsReq.COMMAND, OrderRelationsReqProcessor.getInstance());
        map.put(SendMessageResp.COMMAND, SendMessageRespProcessor.getInstance());
        map.put(ChangePhoneReq.COMMAND, ChangePhoneReqProcessor.getInstance());
        map.put(ReceiveStuInfoReq.COMMAND, ReceiveStuInfoReqProcessor.getInstance());
//        map.put(SendDataResp.COMMAND, SendDataRespProcessor.getInstance());
    }

    public static MessageInsideProcessorMap getInstance() {
        return PROCESSOR_MAP;
    }

    @SuppressWarnings("unchecked")
    public IMessageInsideProcessor getProcessor(String command) throws Exception {
        if (!map.containsKey(command)) {
            throw new Exception("processor is not exist.");
        }
        return map.get(command);
    }

	public boolean containsKey(String trim) {
		return map.containsKey(trim);
	}

}

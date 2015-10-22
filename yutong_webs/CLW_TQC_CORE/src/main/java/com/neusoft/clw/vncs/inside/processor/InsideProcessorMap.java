package com.neusoft.clw.vncs.inside.processor;

import java.util.concurrent.ConcurrentHashMap;

import com.neusoft.clw.vncs.inside.msg.req.ActiveTestReq;
import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;
import com.neusoft.clw.vncs.inside.msg.resp.BindResp;
import com.neusoft.clw.vncs.inside.msg.resp.SendDataResp;
import com.neusoft.clw.vncs.inside.processor.req.ActiveTestReqProcessor;
import com.neusoft.clw.vncs.inside.processor.req.UploadDataReqProcessor;
import com.neusoft.clw.vncs.inside.processor.resp.BindRespProcessor;
import com.neusoft.clw.vncs.inside.processor.resp.SendDataRespProcessor;

//import com.neusoft.tag.app.inside.msg.req.ActiveTestReq;
//import com.neusoft.tag.app.inside.msg.req.UpLoadDataReq;
//import com.neusoft.tag.app.inside.msg.resp.BindResp;
//import com.neusoft.tag.app.inside.msg.resp.SendDataResp;
//import com.neusoft.tag.app.inside.processor.req.ActiveTestReqProcessor;
//import com.neusoft.tag.app.inside.processor.resp.BindRespProcessor;
//import com.neusoft.tag.app.inside.processor.resp.SendDataRespProcessor;
//import com.neusoft.tag.app.inside.processor.resp.UpLoadDataReqProcessor;


public final class InsideProcessorMap {

    private static final InsideProcessorMap PROCESSOR_MAP = new InsideProcessorMap();

    @SuppressWarnings("unchecked")
    private ConcurrentHashMap<String, IInsideProcessor> map = new ConcurrentHashMap<String, IInsideProcessor>();

    private InsideProcessorMap() {
        map.put(BindResp.COMMAND, BindRespProcessor.getInstance());
        map.put(ActiveTestReq.COMMAND, ActiveTestReqProcessor.getInstance());
//        map.put(UpLoadDataReq.COMMAND, UploadDataProcessor.getInstance());
        map.put(UpLoadDataReq.COMMAND, UploadDataReqProcessor.getInstance());
        map.put(SendDataResp.COMMAND, SendDataRespProcessor.getInstance());
       // map.put(UpLoadDataReq.COMMAND, UpLoadDataReqProcessor.getInstance());
    }

    public static InsideProcessorMap getInstance() {
        return PROCESSOR_MAP;
    }

    @SuppressWarnings("unchecked")
    public IInsideProcessor getProcessor(String command) throws Exception {
        if (!map.containsKey(command)) {
            throw new Exception("processor is not exist.");
        }
        return map.get(command);
    }

}

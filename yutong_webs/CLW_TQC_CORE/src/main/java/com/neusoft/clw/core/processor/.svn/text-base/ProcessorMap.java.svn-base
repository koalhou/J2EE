package com.neusoft.clw.core.processor;

import java.util.concurrent.ConcurrentHashMap;
import com.neusoft.clw.core.processor.take.*;

/**
 * @author pudong
 */
public final class ProcessorMap {

    private static final ProcessorMap PROCESSOR_MAP = new ProcessorMap();

    @SuppressWarnings("unchecked")
    private ConcurrentHashMap<String, IProcessor> map = new ConcurrentHashMap<String, IProcessor>();

    private ProcessorMap() {
        map.put(LoginProcessor.COMMAND, LoginProcessor.getInstance());//注册登录
        map.put(KeepAliveProcessor.COMMAND, KeepAliveProcessor.getInstance());//心跳
        map.put(CmdTstBatchProcessor.COMMAND, CmdTstBatchProcessor.getInstance());//设置限拨号码
        map.put(SubscribeRuleSetProcessor.COMMAND, SubscribeRuleSetProcessor.getInstance());//订阅规则设置
        map.put(QueryTargetListProcessor.COMMAND, QueryTargetListProcessor.getInstance());//车辆信息管理列表
        map.put(CmdMsBatchProcessor.COMMAND, CmdMsBatchProcessor.getInstance());//消息下发接口
        map.put(CmdEcBatchProcessor.COMMAND, CmdEcBatchProcessor.getInstance());//拍照
        map.put(StatPhotoListProcessor.COMMAND, StatPhotoListProcessor.getInstance());//获取照片列表
        
        
//        map.put(SendDataResp.COMMAND, SendDataRespProcessor.getInstance());
    }

    public static ProcessorMap getInstance() {
        return PROCESSOR_MAP;
    }

    @SuppressWarnings("unchecked")
    public IProcessor getProcessor(String command) throws Exception {
        if (!map.containsKey(command)) {
            throw new Exception("processor is not exist.");
        }
        return map.get(command);
    }

}

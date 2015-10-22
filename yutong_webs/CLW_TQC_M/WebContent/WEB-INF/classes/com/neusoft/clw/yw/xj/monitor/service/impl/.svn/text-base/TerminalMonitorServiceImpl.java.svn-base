package com.neusoft.clw.yw.xj.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.servlet.ds.MemData;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;
import com.neusoft.clw.yw.xj.monitor.service.TerminalMonitorService;

/**
 * 终端监控service实现类
 * @author JinPeng
 */
public class TerminalMonitorServiceImpl extends ServiceImpl implements
        TerminalMonitorService {
    private static final String TERMINAL_PARAMS_TYPE_VAL = "2007";

    private static final String INDEX_PROPERTY_TYPE_VAL = "2009";

    /**
     * 获取设置特征系数命令
     * @param map
     * @return
     */
    public SendCommandInfo getIndexPropertyCmd(Map < String, String > map) {
        SendCommandInfo sendCommandInfo = new SendCommandInfo();

        // 设置主键
        sendCommandInfo.setCommandId(UUIDGenerator.getUUID32());
        // 设置SIM卡号
        sendCommandInfo.setSimCardNumber(map.get("simCardNumber"));
        // 设置车辆VIN号
        sendCommandInfo.setVehicleVin(map.get("vehicleVin"));
        // 消息ID
        String messageId = UUIDGenerator.getUUID32();
        // 设置消息ID
        sendCommandInfo.setMessageId(messageId);
        // 设置下发子类型
        sendCommandInfo.setSendType(INDEX_PROPERTY_TYPE_VAL);
        // 设置备注
        sendCommandInfo.setRemark("设置特征系数");
        // 设置核心ID
        if (null != MemData.getAppcfgList()) {
            sendCommandInfo.setCoreId(MemData.getAppcfgList().get(0)
                    .getCoreId());
        }
        // 设置用户ID
        sendCommandInfo.setOperateUserId(map.get("userId"));
        // 数据包封装
        StringBuffer packetContent = new StringBuffer();
        packetContent.append("000420090120");
        packetContent.append(messageId);
        packetContent.append("0204");

        int idx = Integer.parseInt(map.get("indexProperty"));
        String idxHex = Integer.toHexString(idx);

        // 长度不足四位补0
        for (int i = 4; i > idxHex.length(); i--) {
            packetContent.append("0");
        }

        // 设置特征系数值
        packetContent.append(idxHex);
        // 设置数据包内容
        sendCommandInfo.setPacketContent(packetContent.toString());

        return sendCommandInfo;
    }

    /**
     * 获取查询终端参数命令
     */
    private List < SendCommandInfo > getQueryTerminalParamsCmd(
            Map < String, String > map) {
        List < SendCommandInfo > list = new ArrayList < SendCommandInfo >();
        for (int i = 1; i < 5; i++) {
            SendCommandInfo sendCommandInfo = new SendCommandInfo();
            // 设置主键
            sendCommandInfo.setCommandId(UUIDGenerator.getUUID32());
            // 设置SIM卡号
            sendCommandInfo.setSimCardNumber(map.get("simCardNumber"));
            // 设置车辆VIN号
            sendCommandInfo.setVehicleVin(map.get("vehicleVin"));
            // 消息ID
            String messageId = UUIDGenerator.getUUID32();
            // 设置消息ID
            sendCommandInfo.setMessageId(messageId);
            // 设置下发子类型
            sendCommandInfo.setSendType(TERMINAL_PARAMS_TYPE_VAL);
            // 设置备注
            sendCommandInfo.setRemark("查询终端基本参数");
            // 设置核心ID
            if (null != MemData.getAppcfgList()) {
                sendCommandInfo.setCoreId(MemData.getAppcfgList().get(0)
                        .getCoreId());
            }
            // 设置用户ID
            sendCommandInfo.setOperateUserId(map.get("userId"));
            // 数据包封装
            StringBuffer packetContent = new StringBuffer();
            packetContent.append("000420070120");
            packetContent.append(messageId);
            packetContent.append("0201");
            packetContent.append(String.valueOf(i));
            // 设置数据包内容
            sendCommandInfo.setPacketContent(packetContent.toString());
            list.add(sendCommandInfo);
        }

        return list;
    }

    /**
     * 查询终端参数信息
     */
    public void getTerminalParams(Map < String, String > map)
            throws BusinessException {
        List < SendCommandInfo > list = getQueryTerminalParamsCmd(map);
        // 下发命令
        insert("", list);
    }

}

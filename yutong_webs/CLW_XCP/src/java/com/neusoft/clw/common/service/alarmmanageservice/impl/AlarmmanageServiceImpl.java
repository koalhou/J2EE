package com.neusoft.clw.common.service.alarmmanageservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.alarmmanageservice.AlarmmanageService;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

public class AlarmmanageServiceImpl extends ServiceImpl implements
        AlarmmanageService {
    public String updateAlarm(AlarmManage alarmmanage,
            SendCommandClient sendcommandclient) throws Exception {
        String msgid = UUIDGenerator.getUUID32();
        LOG.info("msgid:" + msgid + ";vin:" + alarmmanage.getVehicle_vin()
                + ";userid:" + alarmmanage.getUser_id());
        String returnvalue = sendcommandclient.sendAlaramOffCommand(alarmmanage
                .getVehicle_vin(), alarmmanage.getUser_id(),
                Constants.ALARM_OFF_MAP.get(alarmmanage.getAlarm_type_id()),
                msgid, alarmmanage.getAlarm_id(), alarmmanage
                        .getOpeerate_desc(), msgid);
        LOG.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
        if ("0".equals(returnvalue)) {
            update("AlarmManage.updateVehAlarmFlag", alarmmanage);
            returnvalue = "2";
        }
        return returnvalue;
    }

    public void updateList(List < AlarmManage > list) throws BusinessException {
        updateBatch("AlarmManage.batchupdatelist", list);
    }
    
    public void updateStuList(List < StuAlarm > list) throws BusinessException{
        updateStuBatch("AlarmManage.batchupdatestulist", list);
    }
}

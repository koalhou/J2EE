package com.neusoft.clw.common.service.alarmmanageservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.alarmmanageservice.AlarmmanageService;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.TqcAlarm;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;

public class AlarmmanageServiceImpl extends ServiceImpl implements  AlarmmanageService {
	
    public String updateAlarm(AlarmManage alarmmanage, SendCommandClient sendcommandclient) throws Exception {
        String msgid = UUIDGenerator.getUUID32();
        LOG.info("msgid:" + msgid + ";vin:" + alarmmanage.getVehicle_vin() + ";userid:" + alarmmanage.getUser_id());
        String returnvalue = sendcommandclient.sendAlaramOffCommand(	alarmmanage.getVehicle_vin(), alarmmanage.getUser_id(), 
        		Constants.ALARM_OFF_MAP.get(alarmmanage.getAlarm_type_id()), 
        		msgid, alarmmanage.getAlarm_id(), alarmmanage.getOpeerate_desc(), msgid);
        LOG.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
        if ("0".equals(returnvalue)) {
            update("AlarmManage.updateVehAlarmFlagSOS", alarmmanage);
            update("AlarmManage.updateVehAlarmFlagSOSOthers", alarmmanage);
            returnvalue = "2";
        }
        return returnvalue;
    }

    public void updateList(List < AlarmManage > list) throws BusinessException {
    	AlarmManage alarmManage = (AlarmManage)list.get(0);
    	if(alarmManage.getAlarm_type_id().equals("010") || alarmManage.getAlarm_type_id().equals("001") || alarmManage.getAlarm_type_id().equals("12") || alarmManage.getAlarm_type_id().equals("010,12")){
    		updateBatch("AlarmManage.batchOilupdatelist", list);
    	} else {
    		updateBatch("AlarmManage.batchupdatelist", list);
    	}
    }
    public void updateotherAlarmList(List < AlarmManage > list) throws BusinessException {
    	updateBatch("AlarmManage.batchupdateotheralarmlist", list);
    }
    
    public void updateStuList(List < StuAlarm > list) throws BusinessException{
        updateStuBatch("AlarmManage.batchupdatestulist", list);
    }

	public void updateTqcList(List<TqcAlarm> list) throws BusinessException {
		updateTqcBatch("AlarmManage.batchupdatetqclist", list);
	}
}

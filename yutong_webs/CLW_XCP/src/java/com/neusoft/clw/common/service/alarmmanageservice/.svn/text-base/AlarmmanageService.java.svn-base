package com.neusoft.clw.common.service.alarmmanageservice;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;

public interface AlarmmanageService extends Service {
    String updateAlarm(AlarmManage alarmmanage,SendCommandClient sendcommandclient) throws Exception;
    void updateList(List < AlarmManage > list) throws BusinessException;
    void updateStuList(List < StuAlarm > list) throws BusinessException;
}

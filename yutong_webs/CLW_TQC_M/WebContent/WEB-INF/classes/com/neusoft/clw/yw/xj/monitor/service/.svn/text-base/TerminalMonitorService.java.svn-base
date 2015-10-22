package com.neusoft.clw.yw.xj.monitor.service;

import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;

/**
 * 终端监控service接口
 * @author JinPeng
 */
public interface TerminalMonitorService extends Service {
    /** 查询终端参数信息 **/
    void getTerminalParams(Map < String, String > map) throws BusinessException;

    /** 获取特征系数命令 **/
    SendCommandInfo getIndexPropertyCmd(Map < String, String > map);
}

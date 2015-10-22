package com.neusoft.clw.yw.zd.terminal.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.zd.terminal.ds.TerminalInfo;

/**
 * 终端管理service接口
 * @author JinPeng
 */
public interface TerminalManageService extends Service {
    /** 创建终端 **/
    void insertTerminal(TerminalInfo terminalInfo) throws BusinessException;

    /** 修改终端 **/
    void updateTerminal(TerminalInfo terminalInfo) throws BusinessException;

    /** 删除终端 **/
    void deleteTerminal(TerminalInfo terminalInfo) throws BusinessException;

    /** 导入终端 **/
    void importTerminalInfos(List < TerminalInfo > list)
            throws BusinessException;
}

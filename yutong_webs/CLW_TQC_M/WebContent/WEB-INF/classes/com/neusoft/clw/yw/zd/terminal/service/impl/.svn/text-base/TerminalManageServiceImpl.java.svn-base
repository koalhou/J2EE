package com.neusoft.clw.yw.zd.terminal.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.zd.terminal.ds.TerminalInfo;
import com.neusoft.clw.yw.zd.terminal.service.TerminalManageService;

/**
 * 终端管理service实现类
 * @author JinPeng
 */
public class TerminalManageServiceImpl extends ServiceImpl implements
        TerminalManageService {

    /**
     * 创建终端
     */
    public void insertTerminal(TerminalInfo terminalInfo)
            throws BusinessException {
        // 创建终端
        insert("", terminalInfo);
    }

    /**
     * 修改终端
     */
    public void updateTerminal(TerminalInfo terminalInfo)
            throws BusinessException {
        // 修改终端
        update("", terminalInfo);
    }

    /**
     * 删除终端
     */
    public void deleteTerminal(TerminalInfo terminalInfo)
            throws BusinessException {
        // 删除终端
        delete("", terminalInfo);
    }

    /**
     * 导入终端
     */
    public void importTerminalInfos(List < TerminalInfo > list)
            throws BusinessException {
        // 导入终端
        insert(TerminalManageServiceImpl.class, list);
    }

}

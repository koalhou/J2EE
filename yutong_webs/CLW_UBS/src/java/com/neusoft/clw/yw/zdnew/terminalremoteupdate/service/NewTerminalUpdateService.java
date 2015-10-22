package com.neusoft.clw.yw.zdnew.terminalremoteupdate.service;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.UpdateUserInfo;

public interface NewTerminalUpdateService extends Service{
	void insertUpdateInfo(UpdateUserInfo updateuserinfo)
    throws BusinessException;
}
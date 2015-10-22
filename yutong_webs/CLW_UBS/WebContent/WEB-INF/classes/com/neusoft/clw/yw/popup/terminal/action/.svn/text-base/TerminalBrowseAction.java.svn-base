package com.neusoft.clw.yw.popup.terminal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.popup.terminal.ds.TerminalBrowseInfo;

/**
 * 终端选择popup子页面action
 * @author JinPeng
 */
public class TerminalBrowseAction extends BaseAction {
    private transient Service service;

    /** 终端号 **/
    private String terminalCode = "";

    /** 提示信息 **/
    private String message = null;

    /** 终端信息列表 **/
    private List < TerminalBrowseInfo > terminalList = new ArrayList < TerminalBrowseInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("terminalCode", terminalCode);

            terminalList = (List < TerminalBrowseInfo >) service.getObjects(
                    "TerminalBrowse.getTerminalInfos", map);

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List < TerminalBrowseInfo > getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List < TerminalBrowseInfo > terminalList) {
        this.terminalList = terminalList;
    }

}

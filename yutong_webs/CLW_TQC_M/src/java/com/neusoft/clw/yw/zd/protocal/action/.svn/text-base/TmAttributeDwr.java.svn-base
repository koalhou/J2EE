package com.neusoft.clw.yw.zd.protocal.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.zd.protocal.ds.ValueInfo;

/**
 * 终端属性信息DWR
 * @author JinPeng
 */
public class TmAttributeDwr extends BaseAction {
    private transient Service service;

    /** 终端型号列表 **/
    private List < ValueInfo > list = new ArrayList < ValueInfo >();

    /**
     * 根据终端厂家ID获取终端型号信息
     * @param oemId 终端厂家ID
     * @return 终端型号列表
     */
    public Map < String, String > getTmTypeByOemId(String oemId) {
        Map < String, String > map = new HashMap < String, String >();
        try {
            list = service.getObjects("ProtocalManage.getTypeInfos", oemId);
            for (ValueInfo valueInfo : list) {
                map.put(valueInfo.getSelectKey(), valueInfo.getSelectValue());
            }
        } catch (BusinessException e) {
            log.error("终端型号查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return map;
    }

    /**
     * 根据终端型号ID获取终端协议信息
     * @param typeId 终端型号ID
     * @return 终端协议列表
     */
    public Map < String, String > getProtocalByTypeId(String typeId) {
        Map < String, String > map = new HashMap < String, String >();

        try {
            list = service
                    .getObjects("TerminalManage.getProtocalInfos", typeId);
            for (ValueInfo valueInfo : list) {
                map.put(valueInfo.getSelectKey(), valueInfo.getSelectValue());
            }
        } catch (BusinessException e) {
            log.error("终端协议查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return map;
    }

    public List < ValueInfo > getList() {
        return list;
    }

    public void setList(List < ValueInfo > list) {
        this.list = list;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}

package com.neusoft.clw.yw.sm.maitenance.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.sm.maitenance.ds.LuxuryCar;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;

public class MaintenanceDWR extends BaseAction {
    private transient Service service;

    public boolean checkVNumber(String number) {
        try {
            LuxuryCar luxuryCar = new LuxuryCar();
            luxuryCar.setVehicle_number(number.trim());
            int num = this.service.getCount("LuxuryCarSet.getLuxuryCarCountByVNumber", luxuryCar);
            if (num == 0) {
                return true;

            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean checkVin(String vin) {
        try {
            int num = service.getCount("CarBase.getVinCount", vin.trim());
            if (num == 0) {
                return true;

            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkLn(String ln) {
        try {
            int num = service.getCount("CarBase.getLnCount", ln.trim());
            if (num == 0) {
                return true;

            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Map < String, String > getVehicleType(String code) {
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        Map < String, String > map = new HashMap < String, String >();
        try {

            list = service.getObjects("CarBase.getVehicleTypeList", code);
            for (BaseInfo baseinfo : list) {
                map.put(baseinfo.getCodeId(), baseinfo.getCodeName());
            }
            return map;
        } catch (BusinessException e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return map;
        } catch (Exception e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return map;
        }
    }

    public String getVehicleInfo(String code) {
        String re = "";
        try {

            re = (String) service.getObject("CarBase.getVehicleInfo", code);
            if(re==null){
                re="";
            }
            return re;
        } catch (BusinessException e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return re;
        } catch (Exception e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return re;
        }
    }
    public String getEngineInfo(String code) {
        String re = "";
        try {

            re = (String) service.getObject("CarBase.getEngineInfo", code);
            if(re==null){
                re="";
            }
            return re;
        } catch (BusinessException e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return re;
        } catch (Exception e) {
            log.error("车型列表查询DWR异常发生，异常原因：" + e.getMessage());
            return re;
        }
    }
    public Map < String, String > getEngineType(String code) {
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        Map < String, String > map = new HashMap < String, String >();
        try {
            list = service.getObjects("CarBase.getEngineTypeList", code);
            for (BaseInfo baseinfo : list) {
                map.put(baseinfo.getCodeId(), baseinfo.getCodeName());
            }
        } catch (BusinessException e) {
            log.error("省市信息查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return map;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }
}

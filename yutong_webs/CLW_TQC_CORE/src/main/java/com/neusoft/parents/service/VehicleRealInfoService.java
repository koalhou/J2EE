package com.neusoft.parents.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.bean.ResponseBean;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.cachemanager.VehicleRealCacheManager;
import com.neusoft.parents.utils.CacheUtils;

public class VehicleRealInfoService
{
    private Logger log = LoggerFactory.getLogger(VehicleRealInfoService.class);

    private static final String NAME = "<VehicleRealInfoService>";

    private static final VehicleRealInfoService vehicleRealInfoService = new VehicleRealInfoService();

    public static VehicleRealInfoService getInstance()
    {
        return vehicleRealInfoService;

    }
 
/**
 * 
  * 函数介绍：手机服务端调用的接口方法
  * 参数：
  * 返回值：respBean
 */
    public void sendVehicleRealInfo(ResponseBean respBean, RequestBean reqBean)
    {
          String key=Constant.VEHICLEREAL+reqBean.getVin();
          VehicleReal vr = CacheUtils.getVehicleRealCache(reqBean.getVin());
          respBean.setLongitude(vr.getLongitude());
          respBean.setLatitude(vr.getLatitude());
          respBean.setDirection(vr.getDirection());
          respBean.setSpeed(vr.getSpeed());
    }
    
    /**
     * 
     * 暂时没有地方调用
     * 函数介绍：在上行的gps位置信息的分支里面，加入此段的业务逻辑处理。目的是实时更新缓存中的gps位置信息。
     * 参数：
     * 返回值：
    */
    public void CallVehicleLocationInterface(Up_InfoContent uhc) throws java.text.ParseException
    {
        String key=Constant.VEHICLEREAL+uhc.getTerminalId();
        VehicleReal vehicleReal=new VehicleReal();
        vehicleReal.setVehicle_vin(uhc.getTerminalId());
        vehicleReal.setLongitude(uhc.getLongitude());
        vehicleReal.setLatitude(uhc.getLatitude());
        vehicleReal.setDirection(uhc.getDirection());
        vehicleReal.setStat_info(uhc.getState());
        vehicleReal.setVehicle_ln(uhc.getVehicle_ln());
        vehicleReal.setSpeed(uhc.getGps_speeding());
        vehicleReal.setTerminal_time(DateUtil.string2date("yyMMddHHmmss", uhc.getTerminal_time()));
        log.info("key::"+key+"   "+uhc.getTerminalId()+" getGps_speeding  "+uhc.getGps_speeding()+" getSpeed  " +uhc.getSpeed()+"   ");
        VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, key, vehicleReal);
    }
}

package com.neusoft.parents.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.impl.ParentsDAO;
import com.neusoft.parents.pushmessage.domain.BaseNotification;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.pushmessage.manager.PushMsgBuffer;
import com.neusoft.parents.sitenotemanager.SiteNoteManager;
import com.neusoft.parents.utils.CacheUtils;
import com.neusoft.parents.utils.JacksonUtils;

public class PushMsgCommand {
	private Logger log = LoggerFactory.getLogger(SiteNoteManager.class);
	
	/**
	 * 厂外推送    将满足条件的推送     推送出去
	 * @param stuSiteNote
	 * @param vr
	 */
    public void pushMsg(StuSiteNote stuSiteNote,VehicleReal vr)
    {

        PushMessageCls pushMessageBean = new PushMessageCls();
        ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
        int id = parentsDAO.getPushCheckRecordID();    
        pushMessageBean.setId(id);
        pushMessageBean.setRule_id("03");           //02厂内推送 03厂外推送 01新闻推送
        pushMessageBean.setUser_code(stuSiteNote.getPar_id());    //员工号
        //pushMessageBean.setCld_id(stuSiteNote.getEmp_code());
        pushMessageBean.setEvent_time(DateUtil.date2string(vr.getTerminal_time(), "yyyyMMddHHmmss"));
        
        BaseNotification content = new BaseNotification();
        //content.setCld_alias(stuSiteNote.getStu_alias());
        
        content.setRemind_id(stuSiteNote.getNote_id());
        content.setRemind_range(stuSiteNote.getRemind_range());    //提醒范围方式，0：仅站点提醒，1：站点与车辆提醒                之前全部将站点和车关联在一起，所以此处直接设置为1
        content.setRemind_type(stuSiteNote.getRemind_type());  //推送提醒类型   0：按时间  1：按距离
        if("0".equals(stuSiteNote.getRemind_type())){
        	stuSiteNote.setRemind_value(stuSiteNote.getAhead_note_time());
        }
        else{
        	stuSiteNote.setRemind_value(stuSiteNote.getAhead_note_mileage());
        }
        content.setRemind_value(stuSiteNote.getRemind_value());  //设置的具体数值
        content.setArea_type(stuSiteNote.getBelong_area());//设置厂区  1：一厂    2：二厂
        content.setStation_id(stuSiteNote.getSite_id());
        content.setStation_name(stuSiteNote.getSite_name());
        content.setStatus_range(stuSiteNote.getStatus_range());   //0：早班     1：晚班
        content.setVehicle_vin(stuSiteNote.getVehicle_vin());
        content.setLine_id(stuSiteNote.getRoute_id());
        content.setLine_name(stuSiteNote.getRoute_name());
        //content.setVehicle_plate(vr.getVehicle_ln());
        if(null == vr.getVehicle_code() || "".equals(vr.getVehicle_code())){
        	vr.setVehicle_code(parentsDAO.getVehicleCode(stuSiteNote.getVehicle_vin()));
        }
        content.setVehicle_number(vr.getVehicle_code());
        content.setVehicle_lon(vr.getLongitude());
        content.setVehicle_lat(vr.getLatitude());

        pushMessageBean.setContent(content);
        // 将推送内容放入到推送缓冲池中
        PushMsgBuffer.getInstance().add(pushMessageBean);
        // 将推送信息存储到数据库中
        DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(vr, stuSiteNote));
        //add by liuja 先不注释掉，后期为了加快推送速度，应注释掉不打印日志
        try
        {
            log.info("上行推送的json...."+JacksonUtils.toJson(pushMessageBean));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
      

    }
    
    /**
     * 厂内推送：站点推送
     * @param stuSiteNote
     */
    public void pushMsg1(StuSiteNote stuSiteNote)
    {
        VehicleReal vr = CacheUtils.getVehicleRealCache(stuSiteNote.getVehicle_vin());

        PushMessageCls pushMessageBean = new PushMessageCls();
        ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
        int id = parentsDAO.getPushCheckRecordID();
        //int id=1111;
        pushMessageBean.setId(id);
        pushMessageBean.setRule_id("02");    //02厂内推送 03厂外推送 01新闻推送
        pushMessageBean.setUser_code(stuSiteNote.getPar_id());    //员工号
        //pushMessageBean.setCld_id(stuSiteNote.getEmp_code());
        pushMessageBean.setEvent_time(DateUtil.date2string(vr.getTerminal_time(), "yyyyMMddHHmmss"));
        
        BaseNotification content = new BaseNotification();
        //content.setCld_alias(stuSiteNote.getStu_alias());
        //content.setRemind_range("1");    //提醒范围方式，0：仅站点提醒，1：站点与车辆提醒
        //content.setRemind_type(stuSiteNote.getRemind_type());  //推送提醒类型   0：按时间  1：按距离
        //content.setRemind_value(stuSiteNote.getRemind_value());  //设置的具体数值
        content.setRemind_id(stuSiteNote.getNote_id());
        content.setArea_type(stuSiteNote.getBelong_area());//设置厂区  1：一厂    2：二厂
        content.setStation_id(stuSiteNote.getSite_id());
        content.setStation_name(stuSiteNote.getSite_name());
        content.setStation_alias(stuSiteNote.getStu_alias());
        content.setVehicle_vin(stuSiteNote.getVehicle_vin());
        content.setLine_id(stuSiteNote.getRoute_id());
        content.setLine_name(stuSiteNote.getRoute_name());
        //content.setVehicle_plate(vr.getVehicle_ln());
        content.setVehicle_number(vr.getVehicle_code());
        content.setVehicle_lon(vr.getLongitude());
        content.setVehicle_lat(vr.getLatitude());

        pushMessageBean.setContent(content);
        // 将推送内容放入到推送缓冲池中
        PushMsgBuffer.getInstance().add(pushMessageBean);
        //add by liuja 先不注释掉，后期为了加快推送速度，应注释掉不打印日志
        try
        {
            log.info("上行推送的json...."+JacksonUtils.toJson(pushMessageBean));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
               

    }

}

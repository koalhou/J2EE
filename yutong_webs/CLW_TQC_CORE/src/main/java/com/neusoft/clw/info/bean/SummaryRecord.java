package com.neusoft.clw.info.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.neusoft.clw.info.exception.VspeOilBusinessException;
import com.neusoft.clw.info.util.tool.CommonDataUtil;

/**
 * 油耗汇总记录
 */
@SuppressWarnings("unchecked")
public class SummaryRecord implements Comparable {
    private String vehicleId; // 车辆编号

    private String simNo; // SIM卡号
 
    private int ao; // 当天加油总量

    private int uo; // 当天耗油总量

    private int eo; // 当天异常油量之和

    private String businessid; // 集团计费号

    private String corpname; // 集团名称

    private String statTime; // 统计时间

    private String vehicleName; // 车辆名称

    private String groupId; // 分组id

    private String orderFieldName; // 排序依据字段名称

    public static final String strFields = "targetId,ao,uo,eo,name";

    public SummaryRecord() {
        vehicleId = null;
        simNo = null;
        ao = 0;
        uo = 0;
        eo = 0;
    }

    @Override
    public String toString() {
        return String.format("groupid=%s \tvehicleID=%s\tsimNo=%s\tao=%d\tuo=%d\teo=%d", groupId,
                vehicleId, simNo, ao, uo, eo);
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSimNo() {
        return simNo;
    }

    public void setSimNo(String simNo) {
        this.simNo = simNo;
    }

    public int getAo() {
        return ao;
    }

    public void setAo(int ao) {
        this.ao = ao;
    }

    public int getUo() {
        return uo;
    }

    public void setUo(int uo) {
        this.uo = uo;
    }

    public int getEo() {
        return eo;
    }

    public void setEo(int eo) {
        this.eo = eo;
    }

    public static String getStrFields() {
        return strFields;
    }

    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname;
    }

    public String getStatTime() {
        return statTime;
    }

    public void setStatTime(String statTime) {
        this.statTime = statTime;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

  
    public int compareTo(Object obj) {
        try {
            SummaryRecord record = (SummaryRecord) obj;
            Object valueOfThis = CommonDataUtil.getFieldValueOfObj(this, orderFieldName);
            Object valueOfNew = CommonDataUtil.getFieldValueOfObj(record, orderFieldName);
            if (valueOfThis instanceof String) {
                String strValueOfThis = (String) valueOfThis;
                return strValueOfThis.compareTo((String) valueOfNew);
            } else if (valueOfThis instanceof Integer) {
                int intValueOfThis = (Integer) valueOfThis;
                int intValueOfNew = (Integer) valueOfNew;

                if (intValueOfThis < intValueOfNew) {
                    return -1;
                } else if (intValueOfThis == intValueOfNew) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                // 其他类型比较
            }
            return 0;
        } catch (Exception e) {
            throw new VspeOilBusinessException("对象数据比较时出现错误", e);
        }
    }

    public String getOrderFieldName() {
        return orderFieldName;
    }

    public void setOrderFieldName(String orderFieldName) {
        this.orderFieldName = orderFieldName;
    }

    public static void main(String[] args) {
        List<SummaryRecord> list = new ArrayList<SummaryRecord>();
        SummaryRecord record = new SummaryRecord();
        record.setGroupId("g1");
        record.setAo(10);
        record.setUo(20);
        record.setEo(30);
        record.setOrderFieldName("ao");
        list.add(record);

        for (int i = 1000; i > 0; i--) {
            record = new SummaryRecord();
            record.setGroupId("g" + String.valueOf(i));
            record.setAo(i);
            record.setUo(i);
            record.setEo(i);
            record.setOrderFieldName("ao");
            list.add(record);
        }

        System.out.println(new Date() + "排序前" + list.toString());
        Collections.sort(list);
        System.out.println(new Date() + "排序后" + list.toString());
    }
}

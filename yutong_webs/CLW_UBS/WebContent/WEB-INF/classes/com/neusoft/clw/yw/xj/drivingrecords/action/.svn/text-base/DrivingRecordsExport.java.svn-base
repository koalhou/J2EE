package com.neusoft.clw.yw.xj.drivingrecords.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.yw.xj.drivingrecords.ds.DrivingRecords;
import com.neusoft.clw.yw.xj.drivingrecords.ds.On_off;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2011-9-15 下午02:08:58
 */
public class DrivingRecordsExport extends BaseAction {
    private transient Service service;

    private DrivingRecords drivingRecords;

    private List < DrivingRecords > drivingRecordsList;

    private List < On_off > onff1;

    private On_off onff;

    private String message;

    private String yourTimeScale;

    public On_off getOnff() {
        return onff;
    }

    public void setOnff(On_off onff) {
        this.onff = onff;
    }

    public List < On_off > getOnff1() {
        return onff1;
    }

    public void setOnff1(List < On_off > onff1) {
        this.onff1 = onff1;
    }

    public String getYourTimeScale() {
        return yourTimeScale;
    }

    public void setYourTimeScale(String yourTimeScale) {
        this.yourTimeScale = yourTimeScale;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DrivingRecords getDrivingRecords() {
        return drivingRecords;
    }

    public void setDrivingRecords(DrivingRecords drivingRecords) {
        this.drivingRecords = drivingRecords;
    }

    public List < DrivingRecords > getDrivingRecordsList() {
        return drivingRecordsList;
    }

    public void setDrivingRecordsList(List < DrivingRecords > drivingRecordsList) {
        this.drivingRecordsList = drivingRecordsList;
    }

    public Service getService() {
        return service;
    }

    // 注入service层
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * 导出Execl表
     * @param
     * @return
     */
    public String exportRecords() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == drivingRecords) {
                drivingRecords = new DrivingRecords();
            } else if (drivingRecords != null
                    && drivingRecords.getTypeId() != null
                    && !"".equals(drivingRecords.getTypeId())) {
                String getListStr = "";
                String typeId = drivingRecords.getTypeId();
                if ("t01".equalsIgnoreCase(typeId)) {
                    drivingRecords.setTypeId(typeId);
                    getListStr = "drivingRecords.getGpsInfoList";
                }else if("t02".equalsIgnoreCase(typeId)){
                	drivingRecords.setTypeId(typeId);
                	getListStr = "drivingRecords.getTerminalListForSpeed";
                } else if ("t03".equalsIgnoreCase(typeId)
                        || "t04".equalsIgnoreCase(typeId)
                        || "t05".equalsIgnoreCase(typeId)
                        || "t08".equalsIgnoreCase(typeId)
                        || "t09".equalsIgnoreCase(typeId)
                        || "t10".equalsIgnoreCase(typeId)
                        || "t12".equalsIgnoreCase(typeId)
                        || "t13".equalsIgnoreCase(typeId)) {
                    drivingRecords.setTypeId(typeId);
                    getListStr = "drivingRecords.getTerminalList";
                } else if ("t06".equalsIgnoreCase(typeId)
                        || "t07".equalsIgnoreCase(typeId)
                        || "t16".equalsIgnoreCase(typeId)
                        || "t20".equalsIgnoreCase(typeId)) {
                    drivingRecords.setTypeId(typeId);
                    getListStr = "drivingRecords.getSecDataList"; //            
                } else if ("t11".equalsIgnoreCase(typeId)
                        || "t17".equalsIgnoreCase(typeId)
                        || "t18".equalsIgnoreCase(typeId)) {
                    drivingRecords.setTypeId(typeId);
                    getListStr = "drivingRecords.getMinute1DataList";
                } else if ("t14".equalsIgnoreCase(typeId)
                        || "t15".equalsIgnoreCase(typeId)
                        || "t19".equalsIgnoreCase(typeId)) {
                    getListStr = "drivingRecords.getMinute5DataList";
                } else if ("t21".equalsIgnoreCase(typeId)
                        || "t22".equalsIgnoreCase(typeId)
                        || "t23".equalsIgnoreCase(typeId)) {
                    drivingRecords.setTypeId(typeId);
                    if("t21".equalsIgnoreCase(typeId))
                    {
                        drivingRecords.setAlarm_type_id("48");
                    }
                    else if("t22".equalsIgnoreCase(typeId)){
                        drivingRecords.setAlarm_type_id("47");
                    }
                    else if("t23".equalsIgnoreCase(typeId)){
                        drivingRecords.setAlarm_type_id("32");
                    }
                    getListStr = "drivingRecords.getMalarmdList";
                }
                if(StringUtils.isNotEmpty(drivingRecords.getStart_time())){
                	drivingRecords.setPartition_time(drivingRecords.getStart_time().substring(0, 10).replaceAll("-", ""));
                }  
                // 查询时段处理:按时段查询,本周,本月,本季度,本年
                processDate(yourTimeScale, request);
                // 时间设置完毕
                drivingRecordsList = new ArrayList < DrivingRecords >();

                // 对前台页面所要传过来的 是否为空
                drivingRecordsList = service.getObjects(getListStr,
                        drivingRecords);
            }
        } catch (BusinessException e) {
            log.error("Export Records error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export Records error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "RecordExport.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("行车记录");
            
            String typeId1 = drivingRecords.getTypeId();
            if ("t01".equalsIgnoreCase(typeId1)) {
                // 对无效数值进行处理
                /**
                 *t01参数 utc_time create_time longitude latitude direction
                 * gps_speeding
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords.setLongitude(ffffToTemp(drivingRecordsList
                            .get(i).getLongitude()));
                    drivingRecords.setLatitude(ffffToTemp(drivingRecordsList
                            .get(i).getLatitude()));
                    drivingRecords.setDirection(ffffToTemp(drivingRecordsList
                            .get(i).getDirection()));
                    drivingRecords.setDirection_str(diretionToStr(drivingRecords.getDirection()));
                    drivingRecords.setVehicle_vin(drivingRecordsList.get(i).getVehicle_vin());
                    drivingRecords
                            .setGps_speeding(subNumberString(ffffToTemp(drivingRecordsList.get(
                                    i).getGps_speeding())));
                    // drivingRecordsList.add(drivingRecords);
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t01", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t02".equalsIgnoreCase(typeId1)
                    || "t03".equalsIgnoreCase(typeId1)
                    || "t04".equalsIgnoreCase(typeId1)) {
                /**
                 *t020304参数 terminal_time create_time speeding
                 * engine_rotate_speed mileage
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords.setSpeeding(subNumberString(ffffToTemp(drivingRecordsList
                            .get(i).getSpeeding())));
                    drivingRecords
                            .setEngine_rotate_speed(ffffToTemp(drivingRecordsList
                                    .get(i).getEngine_rotate_speed()));
                    drivingRecords.setMileage(ffffToTemp(drivingRecordsList
                            .get(i).getMileage()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t0234", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t05".equalsIgnoreCase(typeId1)) {
                onff1 = getList(drivingRecordsList);
                excelExporter.putAutoExtendSheets("export_t05", 0, onff1);
                // 将Excel写入到指定的流中
                excelExporter.write();

            } else if ("t06".equalsIgnoreCase(typeId1)) {
                /**
                 *t06参数 terminal_time write_time engine_rotate_speed
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setEngine_rotate_speed(ffffToTemp(drivingRecordsList
                                    .get(i).getEngine_rotate_speed()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t06", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t07".equalsIgnoreCase(typeId1)) {
                /**
                 *t07参数 terminal_time write_time torque
                 */
                double torque1 = 0;
                DecimalFormat df = new DecimalFormat();
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setTorque(ffffToTemp(drivingRecordsList.get(
                            i).getTorque()));
                    String s = drivingRecordsList.get(i).getTorque();
                    if (s != null && !"".equals(s)
                            && s.matches("^(-?\\d+)(\\.\\d+)?")) {
                        try {
                            torque1 = Double.valueOf(s);
                            torque1=Double.parseDouble(df.format(torque1 * 100));
                        } catch (NumberFormatException nfe) {
                            log.error("转换CAN-扭矩为百分比出错");
                        }
                    }
                    drivingRecords.setTorque1(String.valueOf(torque1));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t07", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t08".equalsIgnoreCase(typeId1)) {
                /**
                 *t08参数 terminal_time create_time battery_voltage
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords
                            .setBattery_voltage(ffffToTemp(drivingRecordsList
                                    .get(i).getBattery_voltage()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t08", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t09".equalsIgnoreCase(typeId1)) {
                /**
                 *t09参数 terminal_time create_time ext_voltage
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords.setExt_voltage(ffffToTemp(drivingRecordsList
                            .get(i).getExt_voltage()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t09", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t10".equalsIgnoreCase(typeId1)) {
                /**
                 *t10参数 terminal_time create_time power_state
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords.setPower_state(ffffToTemp(drivingRecordsList
                            .get(i).getPower_state()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t010", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t11".equalsIgnoreCase(typeId1)) {
                /**
                 *t11参数 terminal_time write_time oil_temperature
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setOil_temperature(ffffToTemp(drivingRecordsList
                                    .get(i).getOil_temperature()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t011", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t12".equalsIgnoreCase(typeId1)) {
                /**
                 *t12参数 terminal_time create_time e_water_temp
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords
                            .setE_water_temp(ffffToTemp(drivingRecordsList.get(
                                    i).getE_water_temp()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t012", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t13".equalsIgnoreCase(typeId1)) {
                /**
                 *t13参数 terminal_time create_time oil_pressure
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setCreate_time(drivingRecordsList.get(i)
                            .getCreate_time());
                    drivingRecords
                            .setOil_pressure(ffffToTemp(drivingRecordsList.get(
                                    i).getOil_pressure()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t013", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t14".equalsIgnoreCase(typeId1)) {
                /**
                 *t14参数 terminal_time write_time air_pressure
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setAir_pressure(ffffToTemp(drivingRecordsList.get(
                                    i).getAir_pressure()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t014", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            }

            else if ("t15".equalsIgnoreCase(typeId1)) {
                /**
                 *t15参数 terminal_time write_time air_inflow_tpr
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setAir_inflow_tpr(ffffToTemp(drivingRecordsList
                                    .get(i).getAir_inflow_tpr()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t015", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t16".equalsIgnoreCase(typeId1)) {
                /**
                 *t16参数 terminal_time write_time vehicle_speed
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setVehicle_speed(subNumberString(ffffToTemp(drivingRecordsList
                                    .get(i).getVehicle_speed())));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t016", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t17".equalsIgnoreCase(typeId1)) {
                /**
                 *t17参数 terminal_time write_time colder_temperature
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setColder_temperature(ffffToTemp(drivingRecordsList
                                    .get(i).getColder_temperature()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t017", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            }

            else if ("t18".equalsIgnoreCase(typeId1)) {
                /**
                 *t18参数 terminal_time write_time oil_total
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setOil_total(ffffToTemp(drivingRecordsList
                            .get(i).getOil_total()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t018", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t19".equalsIgnoreCase(typeId1)) {
                /**
                 *t19参数 terminal_time write_time enghrrev_t_e_h
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords
                            .setEnghrrev_t_e_h(ffffToTemp(drivingRecordsList
                                    .get(i).getEnghrrev_t_e_h()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t019", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t20".equalsIgnoreCase(typeId1)) {
                /**
                 *t20参数 terminal_time write_time oil_instant
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setOil_instant(ffffToTemp(drivingRecordsList
                            .get(i).getOil_instant()));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t020", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t21".equalsIgnoreCase(typeId1)) {
                /**
                 *t21参数 terminal_time write_time alarm_time
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setAlarm_time(drivingRecordsList.get(i)
                            .getAlarm_time());
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t021", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t22".equalsIgnoreCase(typeId1)) {
                /**
                 *t22参数 terminal_time write_time alarm_time
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setAlarm_time(drivingRecordsList.get(i)
                            .getAlarm_time());
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t022", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            } else if ("t23".equalsIgnoreCase(typeId1)) {
                /**
                 *t23参数 terminal_time write_time alarm_time maxspeed
                 */
                for (int i = 0; i < drivingRecordsList.size(); i++) {
                    drivingRecords=new DrivingRecords();
                    drivingRecords.setTerminal_time(drivingRecordsList.get(i)
                            .getTerminal_time());
                    drivingRecords.setWrite_time(drivingRecordsList.get(i)
                            .getWrite_time());
                    drivingRecords.setAlarm_time(drivingRecordsList.get(i)
                            .getAlarm_time());
                    drivingRecords.setMaxspeed(subNumberString(ffffToTemp(drivingRecordsList
                            .get(i).getMaxspeed())));
                    drivingRecordsList.set(i, drivingRecords);
                }
                excelExporter.putAutoExtendSheets("export_t023", 0,
                        drivingRecordsList);
                // 将Excel写入到指定的流中
                excelExporter.write();
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-disposition",
                "attachment;filename=RecordExport.xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export driving records error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export driving records error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_XJ_DRIVING_RECORDS_EXPORT_MID);
            addOperationLog("行车记录导出");
        }
        // 导出文件成功
        return null;
    }

    /**
     * on_off字符串分解字段
     * @param drivingRecordsList
     * @return l
     */
    private List < On_off > getList(List < DrivingRecords > drivingRecordsList) {
        List < On_off > l = null;
        l = new ArrayList < On_off >();
        for (int i = 0; i < drivingRecordsList.size(); i++) {

            onff = new On_off();
            onff.setTerminal_time(drivingRecordsList.get(i).getTerminal_time());
            onff.setCreate_time(drivingRecordsList.get(i).getCreate_time());
            String s = ffffToTemp(drivingRecordsList.get(i).getOn_off());
            // 对开关量进行判断
            if (s != null || !"FFFF".equals(s)) {
                String s1[] = new String[s.length()];
                for (int j = 0; j < s1.length; j++) {
                    s1[j] = s.substring(j, j + 1);
                }
                onff.setOn_off1(s1[0]);
                onff.setOn_off2(s1[1]);
                onff.setOn_off3(s1[2]);
                onff.setOn_off4(s1[3]);
                onff.setOn_off5(s1[4]);
                onff.setOn_off6(s1[5]);
                onff.setOn_off7(s1[6]);
                onff.setOn_off8(s1[7]);
                onff.setOn_off9(s1[8]);
                onff.setOn_off10(s1[9]);
                onff.setOn_off11(s1[10]);
                onff.setOn_off12(s1[11]);
                onff.setOn_off13(s1[12]);
                onff.setOn_off14(s1[13]);
                onff.setOn_off15(s1[14]);
                onff.setOn_off16(s1[15]);
                onff.setOn_off17(s1[16]);
                onff.setOn_off18(s1[17]);
                onff.setOn_off19(s1[18]);
                onff.setOn_off20(s1[19]);
                onff.setOn_off21(s1[20]);
                onff.setOn_off22(s1[21]);
                onff.setOn_off23(s1[22]);
                onff.setOn_off24(s1[23]);
                onff.setOn_off25(s1[24]);
                onff.setOn_off26(s1[25]);
                onff.setOn_off27(s1[26]);
                onff.setOn_off28(s1[27]);
                onff.setOn_off29(s1[28]);
                onff.setOn_off30(s1[29]);
                onff.setOn_off31(s1[30]);
                onff.setOn_off32(s1[31]);
                l.add(onff);
            } else {
                onff.setOn_off1("");
                onff.setOn_off2("");
                onff.setOn_off3("");
                onff.setOn_off4("");
                onff.setOn_off5("");
                onff.setOn_off6("");
                onff.setOn_off7("");
                onff.setOn_off8("");
                onff.setOn_off9("");
                onff.setOn_off10("");
                onff.setOn_off11("");
                onff.setOn_off12("");
                onff.setOn_off13("");
                onff.setOn_off14("");
                onff.setOn_off15("");
                onff.setOn_off16("");
                onff.setOn_off17("");
                onff.setOn_off18("");
                onff.setOn_off19("");
                onff.setOn_off20("");
                onff.setOn_off21("");
                onff.setOn_off22("");
                onff.setOn_off23("");
                onff.setOn_off24("");
                onff.setOn_off25("");
                onff.setOn_off26("");
                onff.setOn_off27("");
                onff.setOn_off28("");
                onff.setOn_off29("");
                onff.setOn_off30("");
                onff.setOn_off31("");
                onff.setOn_off32("");
                l.add(onff);
            }
        }

        return l;

    }

    /**
     * 日期信息
     * @param yourTimeScale
     * @param req
     * @return
     */
    private void processDate(String yourTimeScale, HttpServletRequest req) {
        if (this.drivingRecords == null) {
            this.drivingRecords = new DrivingRecords();
        }
        // 时段
        if (yourTimeScale != null
                && ("".equals(yourTimeScale) || "0".equals(yourTimeScale))) {
            // week //month //quarter //year
            if ("aweek".equalsIgnoreCase(this.drivingRecords.getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getCurrentWeekFirst());
                this.drivingRecords.setEnd_time(DateUtil.getCurrentWeekLast());

            } else if ("bmonth".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil.getMonthFirstDay());
                this.drivingRecords.setEnd_time(DateUtil.getMonthLastDay());

            } else if ("cquarter".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getSeasonFirstDay(DateUtil.getSeason()));
                this.drivingRecords.setEnd_time(DateUtil
                        .getSeasonLastDay(DateUtil.getSeason()));

            } else if ("dyear".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getCurrentYearFirst());
                this.drivingRecords.setEnd_time(DateUtil.getCurrentYearLast());
            }
            // 自定义时段
        } else if (yourTimeScale != null && "1".equals(yourTimeScale)) {
            String sdate = this.drivingRecords.getStart_time();
            String edate = this.drivingRecords.getEnd_time();
            if (sdate != null && !"".equals(sdate) && sdate.indexOf(":") == -1) {
                this.drivingRecords.setStart_time(sdate + " 00:00:00");
            }
            if (edate != null && !"".equals(edate) && edate.indexOf(":") == -1) {
                this.drivingRecords.setEnd_time(sdate + " 23:59:59");
            }
        }
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, DrivingRecords dr) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != dr) {
            if (null != dr.getVehicle_ln()) {
                OperateLogFormator.format(sb, "Vehicle ln", dr.getVehicle_ln());
            }
        }
        return sb.toString();
    }

    /**
     * FFFF转字空符串
     * @param s
     * @return
     */
    private String ffffToTemp(String s) {
        if (s == null || "FFFF".equalsIgnoreCase(s)) {
            return "";
        } else {
            return s;
        }
    }
    /**
     * 方向转换
     * @param direction
     * @return
     */
    private String diretionToStr(String direction) {
        if (direction == null || "".equals(direction)
                || !direction.matches("^(-?\\d+)(\\.\\d+)?")) {
            return "";
        }
        // 转换成数值
        double d = 0.0;
        try {
            d = Double.valueOf(direction);
        } catch (NumberFormatException nfe) {
            log.error("转换方向时出错,", nfe);
        }
        if ((d >= 0 && d < 10) || (d >= 350 && d <= 360)) {
            return "北";
        } else if (d >= 10 && d < 80) {
            return "东北";
        } else if (d >= 80 && d < 100) {
            return "东";
        } else if (d >= 100 && d < 170) {
            return "东南";
        } else if (d >= 170 && d < 190) {
            return "南";
        } else if (d >= 190 && d < 260) {
            return "西南";
        } else if (d >= 260 && d < 280) {
            return "西";
        } else if (d >= 280 && d < 350) {
            return "西北";
        } else {
            return "北";
        }
    }
    /**
     * 截取小数点后6位
     * @param s
     * @return
     */
    private String subNumberString(String s) {
    	String sub ="";
        if(StringUtils.isNotEmpty(s)){
    	DecimalFormat df1 = new DecimalFormat("0.00");
    	sub = df1.format(Double.parseDouble(s));
        }
    	return sub;
    }
}

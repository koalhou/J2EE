package com.neusoft.clw.sysmanage.datamanage.sendcommand.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Base64;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MemData;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.sysmanage.datamanage.appconfig.domain.Appcfg;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.domain.SendCommand;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

/**
 * 下发消息
 */
public class SendCommandClient {

    /** service共通类 */
    private transient static Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    protected final Logger log = Logger.getLogger(getClass());

    /**
     * 下发拍照指令
     * @param vehicle_vin
     * @param channel_number 通道号
     * @param pixel 像素
     * @param image_quality 图片质量
     * @param userid 用户ID
     * @param brightness 亮度
     * @param contrast 对比度
     * @param saturation 饱和度
     * @param color 色度
     * @param msgid 唯一标识
     * @return
     */
    public String sendPhotoCommand(String vehicle_vin, String channel_number,
            String pixel, String image_quality, String userid,
            String brightness, String contrast, String saturation,
            String color, String msgid, String batch_id) {
        SendCommand sendcommand = new SendCommand();
        // String msgid = UUIDGenerator.getUUID32();
        sendcommand.setSendcommandid(msgid);

        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn("MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn("MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn("MemData.appcfgList为空!");
                return recode;
            }
        }

        // List<VehcileInfo> vehlist = service.getObjects(
        // "RegionGroupManage.getSIMCardNumber", vehicle_vin);
        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error("拍照下发查询SIM卡出错:", e);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist == null || vehlist.size() == 0) {
            log.warn("不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn("不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2001");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);

        StringBuffer packet = new StringBuffer();
        packet.append("00042001");
        packet.append("0120");
        packet.append(msgid);
        packet.append("02");
        packet.append(format(Integer.toHexString(12), 2));
        packet.append(DateUtil.getThisSecondTime());
        // packet.append("000000000000");
        packet.append("0302");

        if (pixel == null || pixel == "") {
            pixel = "01";
        }
        // packet.append(String.format("%-2s",
        // Integer.toHexString(Integer.parseInt(pixel))));
        packet.append(format(Integer.toHexString(Integer.parseInt(pixel)), 2));

        packet.append("0402");
        if (image_quality == null || image_quality == "") {
            image_quality = "1";
        }
        // packet.append(String.format("%-2s",
        // Integer.toHexString(Integer.parseInt(image_quality))));
        packet.append(format(Integer.toHexString(Integer
                .parseInt(image_quality)), 2));
        packet.append("0502");
        // packet.append(String.format("%-2s",
        // Integer.toHexString(Integer.parseInt(channel_number))));
        packet.append(format(Integer.toHexString(Integer
                .parseInt(channel_number)), 2));
        /*
         * 新增参数报文
         */
        packet.append("0604");
        packet.append("0001");
        packet.append("0703");
        packet.append("000");
        packet.append("0801");
        packet.append("0");
        packet.append("0903");
        packet.append(format(Integer.toHexString(Integer.parseInt(brightness)),
                3));
        packet.append("0A03");
        packet
                .append(format(Integer.toHexString(Integer.parseInt(contrast)),
                        3));
        packet.append("0B03");
        packet.append(format(Integer.toHexString(Integer.parseInt(saturation)),
                3));
        packet.append("0C03");
        packet.append(format(Integer.toHexString(Integer.parseInt(color)), 3));

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setRemark("下发拍照命令");
        sendcommand.setChanle_code(channel_number);
        sendcommand.setBatch_id(batch_id);

        // service.insert("RegionGroupManage.insertsmsphotocmd", sendcommand);

        String reqXml = getSendHttpPhotoCommandXML(sendcommand);

        // log.info("reqXml:" + reqXml);

        try {
            log.info(msgid + ",下发拍照命令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发拍照命令结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发拍照命令异常:", e);
            return recode;
        }

    }

    /**
     * 下发消息指令
     * @param vehicle_vin 车辆VIN
     * @param message 消息内容
     * @param userid 用户ID
     * @param msgtype 消息类型
     * @param msgid 唯一标识
     * @return
     */
    public String sendSMSCommand(String vehicle_vin, String message,
            String userid, String msgtype, String msgid, String batch_id) {

        SendCommand sendcommand = new SendCommand();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + "MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + "MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + "MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn(msgid + "MemData.appcfgList为空!");
                return recode;
            }
        }

        // List<VehcileInfo> vehlist = service.getObjects(
        // "RegionGroupManage.getSIMCardNumber", vehicle_vin);
        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",消息下发查询SIM卡NUMBER出错:", e);
            return recode;
        }
        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2002");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setRemark(message);

        StringBuffer packet = new StringBuffer();
        packet.append("00042002");
        packet.append("0120");
        packet.append(msgid);
        packet.append("0202");
        packet.append(format(Integer.toHexString(Integer.parseInt(msgtype, 2)),
                2));
        packet.append("03");
        try {
            packet.append(format(Integer
                    .toHexString(message.getBytes("GBK").length), 4));
            packet.append(message);
        } catch (Exception e) {
            log.error(msgid + ",消息内容拼串出错:", e);
            return recode;
        }

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setBatch_id(batch_id);

        String reqXml = getSendHttpXML(sendcommand);

        try {
            log.info(msgid + "下发文本消息指令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + "下发文本消息指令结束");
            return recode;
        } catch (Exception e) {
            log.info(msgid + ",下发文本消息异常：" + e.getMessage());
            return recode;
        }
        // service.insert("RegionGroupManage.insertsmsphotocmd", sendcommand);
    }

    /**
     * 下发监听指令
     * @param phone 电话号码
     * @param vehicle_vin 车辆VIN
     * @param userid 用户ID
     * @param msgid 唯一标识
     * @return
     */
    public String sendPhoneCommand(String phone, String vehicle_vin,
            String userid, String msgid) {
        SendCommand sendcommand = new SendCommand();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + ",MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + ",MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + ",MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn(msgid + ",MemData.appcfgList为空!");
                return recode;
            }
        }

        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",监听指令下发查询SIM卡出错:", e);
            return recode;
        }

        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2400");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setRemark("下发监听指令,回拨电话:" + phone);

        StringBuffer packet = new StringBuffer();
        packet.append("00042400");
        packet.append("0120");
        packet.append(msgid);
        packet.append("0202");
        packet.append("01");
        packet.append("03");
        packet.append(format(Integer.toHexString(phone.length()), 4));
        packet.append(phone);

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setBatch_id(msgid);

        String reqXml = getSendHttpXML(sendcommand);

        try {
            log.info(msgid + ",下发监听指令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发监听指令结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发监听指令异常:", e);
            return recode;
        }
    }

    /**
     * 重点监控指令下发
     * @param vehicle_vin 车辆VIN
     * @param userid 用户ID
     * @param interval 上报间隔
     * @param duration 持续时间秒
     * @param msgid 唯一标识
     * @return
     */
    public String sendKeyMonitorCommand(String vehicle_vin, String userid,
            String interval, String duration, String msgid, String batch_id) {
        SendCommand sendcommand = new SendCommand();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + ",MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + ",MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + ",MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn(msgid + ",MemData.appcfgList为空!");
                return recode;
            }
        }

        // List<VehcileInfo> vehlist = service.getObjects(
        // "RegionGroupManage.getSIMCardNumber", vehicle_vin);
        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",监听指令下发查询SIM卡出错:", e);
            return recode;
        }

        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2202");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setRemark("下发重点监控指令");

        StringBuffer packet = new StringBuffer();
        packet.append("00042202");
        packet.append("0120");
        packet.append(msgid);
        packet.append("0204");

        if ("0".equals(interval)) {
            packet.append(format(Integer
                    .toHexString(Integer.parseInt(interval)), 4));

        } else {
            packet.append(format(Integer
                    .toHexString(Integer.parseInt(interval)), 4));
            packet.append("0308");
            // int i = Integer.parseInt(interval);
            // int j = Integer.parseInt(duration);
            // int k = i * j;
            // packet.append(format(Integer.toHexString(k), 8));
            packet
                    .append(format(Long.toHexString(Long.parseLong(duration)),
                            8));
        }

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setBatch_id(batch_id);

        String reqXml = getSendHttpXML(sendcommand);

        try {
            log.info(msgid + ",下发重点监控指令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发重点监控指令结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发重点监控指令:", e);
            return recode;
        }
    }

    /**
     * 下发关闭告警指令
     * @param vehicle_vin 车辆VIN
     * @param userid 用户ID
     * @param alarmtype 告警类型
     * @param msgid 唯一标识
     * @return
     */
    public String sendAlaramOffCommand(String vehicle_vin, String userid,
            String alarmtype, String msgid, String alarm_id, String remark,String batch_id)
            throws Exception {
        SendCommand sendcommand = new SendCommand();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + ",MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + ",MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + ",MemData.getAppcfgList().get(0)为空!");
                    throw new Exception(msgid + "下发关闭告警指令出错");
                    // return recode;
                }
            } else {
                log.warn(msgid + ",MemData.appcfgList为空!");
                throw new Exception(msgid + "下发关闭告警指令出错");
                // return recode;
            }
        }

        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",下发取消告警指令查询SIM卡出错:", e);
            throw new Exception(msgid + "下发关闭告警指令出错");
            // return recode;
        }

        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            throw new Exception(msgid + "下发关闭告警指令出错");
            // return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            throw new Exception(msgid + "下发关闭告警指令出错");
            // return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2B00");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setRemark(remark);
        sendcommand.setReggrpid(alarm_id);

        StringBuffer packet = new StringBuffer();
        packet.append("00042B00");
        packet.append("0120");
        packet.append(msgid);
        packet.append("0201");
        packet.append(alarmtype);

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setBatch_id(batch_id);

        String reqXml = getSendHttpXML(sendcommand);

        try {
            log.info(msgid + ",下发告警取消指令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发告警取消指令结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发告警取消指令:", e);
            throw new Exception(msgid + "下发关闭告警指令出错");
            // return recode;
        }

    }

    /**
     * 设置车牌和颜色参数
     * @param vehicle_vin
     * @param userid
     * @param vehln
     * @param color
     * @param parmcount
     * @param msgid
     * @return
     */
    public String sendVehParm(String vehicle_vin, String userid, String vehln,
            String color, String parmcount, String msgid) {
        SendCommand sendcommand = new SendCommand();
        // String msgid = UUIDGenerator.getUUID32();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;

        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + ",MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + ",MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + ",MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn(msgid + ",MemData.appcfgList为空!");
                return recode;
            }
        }

        // List<VehcileInfo> vehlist = service.getObjects(
        // "RegionGroupManage.getSIMCardNumber", vehicle_vin);
        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",车牌和颜色下发查询SIM卡出错:", e);
            return recode;
        }

        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("2103");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setRemark("下发车牌和颜色设置指令");

        StringBuffer packet = new StringBuffer();
        packet.append("00042103");
        packet.append("FF20");
        packet.append(msgid);
        packet.append("FE02");
        packet.append(format(Integer.toHexString(Integer.parseInt(parmcount)),
                2));
        packet.append("00000083");
        try {
            packet.append(format(Integer
                    .toHexString(vehln.getBytes("GBK").length), 2));
            packet.append(vehln);
        } catch (Exception e) {
            log.error(msgid + "车牌拼串出错:", e);
            return recode;
        }
        packet.append("00000084");
        packet.append("02");
        packet.append(format(Integer.toHexString(Integer.parseInt(color)), 2));

        // 设置下发报文
        sendcommand.setPacket_content(packet.toString());
        sendcommand.setBatch_id(msgid);

        String reqXml = getSendHttpXML(sendcommand);

        try {
            log.info(msgid + ",车牌和颜色参数设置指令");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",车牌参数设置指令结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",车牌参数设置指令:", e);
            return recode;
        }
    }


    /**
     * 下发线路更新通知
     * @param vehicle_vin
     * @param userid
     * @param msgid
     * @param batch_id
     * @param ip
     * @param port
     * @param username
     * @param userpass
     * @param filename
     * @param CRC
     * @return
     */
    public String sendRouteNotice(String vehicle_vin, String userid,
            String msgid, String batch_id, String ip, String port,
            String username, String userpass, String filename, String crc) {
        SendCommand sendcommand = new SendCommand();
        sendcommand.setSendcommandid(msgid);
        String recode = "7001";
        boolean setCore_id_b = false;
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) != null) {
                sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                        .getCore_id());
                setCore_id_b = true;
            } else {
                log.warn(msgid
                        + ",MemData.getAppcfgList().get(0)为空,reload config!");
                initParameter();
            }
        } else {
            log.warn(msgid + ",MemData.appcfgList为空,reload config!");
            initParameter();
        }

        if (!setCore_id_b) {
            if (MemData.getAppcfgList() != null) {
                if (MemData.getAppcfgList().get(0) != null) {
                    sendcommand.setCore_id(MemData.getAppcfgList().get(0)
                            .getCore_id());
                } else {
                    log.warn(msgid + ",MemData.getAppcfgList().get(0)为空!");
                    return recode;
                }
            } else {
                log.warn(msgid + ",MemData.appcfgList为空!");
                return recode;
            }
        }

        List < VehcileInfo > vehlist = null;
        try {
            vehlist = service.getObjects("SendCommand.getSIMCardNumber",
                    vehicle_vin);
        } catch (Exception e) {
            log.error(msgid + ",监听指令下发查询SIM卡出错:", e);
            return recode;
        }

        if (vehlist == null || vehlist.size() == 0) {
            log.warn(msgid + ",不存在对应车辆VIN信息:" + vehicle_vin);
            return recode;
        }
        String sim_card_number = "";
        if (vehlist.get(0).getSim_card_number() != null
                && vehlist.get(0).getSim_card_number() != "") {
            sim_card_number = vehlist.get(0).getSim_card_number();
        } else {
            log.warn(msgid + ",不存在对应Sim_card_number信息!");
            return recode;
        }

        sendcommand.setSim_card_number(sim_card_number);
        sendcommand.setVehicle_vin(vehicle_vin);
        sendcommand.setMsg_id(msgid);
        sendcommand.setSend_command("0010");
        sendcommand.setSend_type("88");
        sendcommand.setDeal_state("0");
        sendcommand.setOperate_user_id(userid);
        sendcommand.setIp(ip);
        sendcommand.setPort(port);
        sendcommand.setUsername(username);
        sendcommand.setUserpass(userpass);
        //filename=filename.substring(24, filename.length());
        sendcommand.setFilename(filename);
        sendcommand.setCrc(crc);
        sendcommand.setBatch_id(batch_id);
        String reqXml = getNoticeHttpXml(sendcommand);

        try {
            log.info(msgid + ",下发线路文件更新通知");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发线路文件更新通知");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发线路文件更新通知:", e);
            return recode;
        }
    }

    /**
     * 标记超载告警
     * @param msgid(消息ID)
     * @param enterprise_id(企业ID)
     * @param terminal_id(车辆VIN)
     * @param photo_id(照片ID)
     * @return
     */
    public String sendHttpPhotoFlagXML(String msgid,String enterprise_id,String terminal_id,String photo_id) {
    	
    	String recode = "7001";
        String reqXml = getSendHttpPhotoFlagXML(enterprise_id,terminal_id,photo_id);
        
        log.info("reqXml:" + reqXml);

        try {
            log.info(msgid + ",下发照片标记超载开始");
            recode = sendHttpCommand(reqXml, msgid);
            log.info(msgid + ",下发照片标记超载结束");
            return recode;
        } catch (Exception e) {
            log.error(msgid + ",下发照片标记超载异常:", e);
            return recode;
        }

    }
    
    /**
     * 线路文件下发XML
     * @param sendcommand
     * @return
     */
    private String getNoticeHttpXml(SendCommand sendcommand) {
        String str = "str";
        String bstr = "bstr";
        OlxDocument doc = OlxDocument.Factory.newInstance();
        Olx x = doc.addNewOlx();
        x.setDir("up");
        x.setVersion("0.0.1");
        Function f = x.addNewFunction();
        f.setName("vncs.app.sendline");
        f.setTimeout("60");
        f.setSeq("3001");
        Param p = f.addNewParam();
        TreeObject tree = p.addNewTreeObject();

        setNewValue(tree.addNewValue(), "AppId", str, MemData.getAppcfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCore_pass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, sendcommand
                .getSend_command());
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, sendcommand
                .getSend_type());
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, sendcommand
                .getVehicle_vin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, sendcommand
                .getSim_card_number());
        setNewValue(tree.addNewValue(), "MSG_ID", str, sendcommand.getMsg_id());
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, sendcommand
                .getOperate_user_id());
        setNewValue(tree.addNewValue(), "REMARK", bstr, Base64.encode(
                sendcommand.getRemark(), "UTF-8"));
        setNewValue(tree.addNewValue(), "IPLENGTH", str, sendcommand.getIp()
                .length());
        setNewValue(tree.addNewValue(), "IP", str, sendcommand.getIp());
        setNewValue(tree.addNewValue(), "PORT", str, sendcommand.getPort());
        setNewValue(tree.addNewValue(), "USERLENGTH", str, sendcommand
                .getUsername().length());
        setNewValue(tree.addNewValue(), "USERNAME", str, sendcommand
                .getUsername());
        setNewValue(tree.addNewValue(), "PASSLENGTH", str, sendcommand
                .getUserpass().length());
        setNewValue(tree.addNewValue(), "USERPASS", bstr, Base64.encode(
                sendcommand.getUserpass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "FILELENGTH", str, sendcommand
                .getFilename().length());
        setNewValue(tree.addNewValue(), "FILENAME", str, sendcommand
                .getFilename());
        setNewValue(tree.addNewValue(), "CRC", str, sendcommand.getCrc());
        setNewValue(tree.addNewValue(), "BATCH_ID", str, sendcommand
                .getBatch_id());
        return doc.xmlText();
    }

    private String getSendHttpXML(SendCommand sendcommand) {
        String str = "str";
        String bstr = "bstr";
        OlxDocument doc = OlxDocument.Factory.newInstance();
        Olx x = doc.addNewOlx();
        x.setDir("up");
        x.setVersion("0.0.1");
        Function f = x.addNewFunction();
        f.setName("vncs.app.sendcommand");
        f.setTimeout("60");
        f.setSeq("3001");
        Param p = f.addNewParam();
        TreeObject tree = p.addNewTreeObject();

        setNewValue(tree.addNewValue(), "AppId", str, MemData.getAppcfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCore_pass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, sendcommand
                .getSend_command());
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, sendcommand
                .getSend_type());
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, sendcommand
                .getVehicle_vin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, sendcommand
                .getSim_card_number());
        setNewValue(tree.addNewValue(), "MSG_ID", str, sendcommand.getMsg_id());
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, sendcommand
                .getOperate_user_id());
        setNewValue(tree.addNewValue(), "REMARK", bstr, Base64.encode(
                sendcommand.getRemark(), "UTF-8"));
        setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(
                sendcommand.getPacket_content(), "UTF-8"));
        // setNewValue(tree.addNewValue(), "CHANLE_CODE", str, sendcommand
        // .getChanle_code());
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        setNewValue(tree.addNewValue(), "REGGRPID", str, sendcommand
                .getReggrpid());
        setNewValue(tree.addNewValue(), "BATCH_ID", str, sendcommand
                .getBatch_id());
        return doc.xmlText();
    }

    private String getSendHttpPhotoCommandXML(SendCommand sendcommand) {
        String str = "str";
        String bstr = "bstr";

        OlxDocument doc = OlxDocument.Factory.newInstance();
        Olx x = doc.addNewOlx();
        x.setDir("up");
        x.setVersion("0.0.1");
        Function f = x.addNewFunction();
        f.setName("vncs.app.sendcommand");
        f.setTimeout("60");
        f.setSeq("3001");
        Param p = f.addNewParam();
        TreeObject tree = p.addNewTreeObject();

        setNewValue(tree.addNewValue(), "AppId", str, MemData.getAppcfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCore_pass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, sendcommand
                .getSend_command());
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, sendcommand
                .getSend_type());
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, sendcommand
                .getVehicle_vin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, sendcommand
                .getSim_card_number());
        setNewValue(tree.addNewValue(), "MSG_ID", str, sendcommand.getMsg_id());
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, sendcommand
                .getOperate_user_id());
        setNewValue(tree.addNewValue(), "REMARK", bstr, Base64.encode(
                sendcommand.getRemark(), "UTF-8"));
        setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(
                sendcommand.getPacket_content(), "UTF-8"));
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, sendcommand
                .getChanle_code());
        // setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        setNewValue(tree.addNewValue(), "REGGRPID", str, null);
        setNewValue(tree.addNewValue(), "BATCH_ID", str, sendcommand
                .getBatch_id());
        return doc.xmlText();
    }
    
    /**
     * 标记超载告警
     * @param enterprise_id(企业id)
     * @param terminal_id(目的终端号码 ：车辆vin号)
     * @param photo_id(照片ID)
     * @return
     */
    private String getSendHttpPhotoFlagXML(String enterprise_id,String terminal_id,String photo_id) {
        String str = "str";
        String bstr = "bstr";

        OlxDocument doc = OlxDocument.Factory.newInstance();
        Olx x = doc.addNewOlx();
        x.setDir("up");
        x.setVersion("0.0.1");
        Function f = x.addNewFunction();
        f.setName("vncs.app.markalarm");
        f.setTimeout("60");
        f.setSeq("3001");
        Param p = f.addNewParam();
        TreeObject tree = p.addNewTreeObject();
        tree.setVersion("0.0.1");
        //添加参数
        setNewValue(tree.addNewValue(), "AppId", str, MemData.getAppcfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCore_pass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str,terminal_id);
        setNewValue(tree.addNewValue(), "ALARM_TYPE_ID", str,"87");//87-超载
        setNewValue(tree.addNewValue(), "PHOTO_ID", str,photo_id);
        setNewValue(tree.addNewValue(), "ENTERPRISE_ID", str,enterprise_id);
        return doc.xmlText();
    }

    private String sendHttpCommand(String sendXml, String msgid)
            throws MalformedURLException {
        return doPost(sendXml, msgid);
    }

    // 设置一个value标签的三个属性值
    private static void setNewValue(Value value, String strKey, String strClas,
            Object objValue) {
        value.setKey(strKey);
        value.setClass1(Value.Class.Enum.forString(strClas));
        String strValue = String.valueOf(objValue);
        value.setValue(strValue);
    }

    public String doPost(String strSend, String msgid)
            throws MalformedURLException {
        URL serurl = new URL(MemData.getAppcfgList().get(0).getSend_path());
        //
        // URL serurl = new URL("http://10.10.124.100:8080/vncs/app");

        HttpURLConnection connection = null;
        // 自定义异常返回码
        String recode = "7002";
        try {
            log.info(msgid
                    + ",请求xml内容:"
                    + OlxDocument.Factory.parse(StringUtils.trim(strSend))
                            .xmlText());
            connection = initeConnection(serurl);
            sendXml(connection, strSend);
            log.info(msgid + ",等待" + connection.getURL() + "响应消息...");

            StringBuffer strReceive = new StringBuffer();
            int nStatusCode = receiveXml(connection, strReceive);
            log.info(msgid + ",HTTP响应码: " + nStatusCode + "  "
                    + connection.getResponseMessage());
            if (200 == nStatusCode) {
                // log.info("x-status-code = " +
                // connection.getHeaderField("x-status-code"));
                // LOGGER.info("x-status-desc = " +
                // Base64.decode(connection.getHeaderField("x-status-desc"),
                // "UTF-8"));
                // LOGGER.info("strReceive.length = " + strReceive.length());
                // LOGGER.info("strReceive:content = " + strReceive);
                log.info(OlxDocument.Factory.parse(strReceive.toString())
                        .toString());
                // 解析响应
                OlxDocument doc = OlxDocument.Factory.parse(strReceive
                        .toString());
                Olx olx = doc.getOlx();
                Function function = olx.getFunction();
                Result result = function.getResult();
                TreeObject treeObject = result.getTreeObject();
                Value[] value = treeObject.getValueArray();
                for (int i = 0; i < value.length; i++) {
                    if (Constants.VALUE_KEY.equals(value[i].getKey())) {
                        recode = value[i].getValue();
                        break;
                    }
                }

            } else {
                log.info(msgid + "返回码:" + nStatusCode + ",消息:"
                        + connection.getResponseMessage() + ",ContentLength:"
                        + connection.getContentLength());
                recode = nStatusCode + "";
            }

        } catch (Exception e) {
            log.error(msgid + ",发送指令异常:" + e.getMessage() + "\r\n"
                    + e.getCause());

        } finally {
            if (null != connection) {
                connection.disconnect();
                connection = null;
            }
        }
        return recode;
    }

    private HttpURLConnection initeConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(30);
        connection.setReadTimeout(6000);
        return connection;
    }

    private void sendXml(HttpURLConnection connection, String strXml)
            throws IOException {
        // 命令行
        connection.setRequestProperty("POST", "/ HTTP/1.1");
        // 头部参数
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("x-protocol-version", "0.0.1");
        connection.setRequestProperty("x-service-type", "vspe:01/1.0");
        // 内容
        OutputStreamWriter out = new OutputStreamWriter(connection
                .getOutputStream());
        out.write(strXml);
        //
        out.flush();
        out.close();
    }

    private int receiveXml(HttpURLConnection connection, StringBuffer strXml)
            throws IOException {
        int nStatusCode = 0;
        try {
            nStatusCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String strCurrentLine = null;
            while ((strCurrentLine = reader.readLine()) != null) {
                strXml.append(strCurrentLine + "\n");
            }
            return nStatusCode;
        } catch (Exception e) {
            log.info("receiveXml()中出现异常:" + e.getMessage());
        }
        return nStatusCode;
    }

    public static String format(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }

    public void initParameter() {
        log.info("start reload config");
        try {
            // 初始化参数表
            List < Appcfg > appcfglist = service.getObjects("Appcfg.getInfos",
                    Constants.CLW_P_CODE);
            MemData.setAppcfgList(appcfglist);
            log.info("start reload end success");
        } catch (BusinessException e) {
            log.error("参数初始化出错:", e);
        } catch (Exception e2) {
            log.error("参数初始化出错:", e2);
        }
    }

}

package com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.AppContextHelper;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Base64;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MemData;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.sysmanage.datamanage.appconfig.domain.Appcfg;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.Bit32ValueInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.XCTerminalParamsInfo;

/**
 * 发送消息类
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2012-6-7 下午16:57:34
 */
public class SendCommandUtils {
    protected static final Logger log = Logger.getLogger(SendCommandUtils.class);
    
    private int paramsCount = 0;
    
    /**
     * 设置报文值
     * @param value
     * @param strKey
     * @param strClas
     * @param objValue
     */
    private static void setNewValue(Value value, String strKey, String strClas,
            Object objValue) {
        value.setKey(strKey);
        value.setClass1(Value.Class.Enum.forString(strClas));
        String strValue = String.valueOf(objValue);
        value.setValue(strValue);
    }
    
    /**
     * 判断字符是否为空 true：非空 false：空【终端参数设置专用】
     * @param str
     * @return
     */
    private boolean checkValue(String str) {
        if(str == null || str.length() == 0 ) {
            return false;
        }
        paramsCount++;
        return true;
    }
    
    /**
     * 判断bit类数据是否为空
     * @param bit32ValueInfo
     * @return
     */
    private boolean checkBitValue(Bit32ValueInfo bit32ValueInfo) {
        if(bit32ValueInfo == null) {
            return false;
        }
        paramsCount++;
        return true;
    }
    
    /**
     * 判断复选框状态
     * @param flag
     * @return
     */
    private boolean checkCheckBoxStatus(boolean flag) {
        if(!flag) {
            // 未选中时
            return false;
        }
        paramsCount++;
        return true;
    }
    
    /**
     * 位选中状态为0
     * @param paramid
     * @param setValue
     * @param paramLength
     * @return
     */
    private String getBinary0ToHex(String paramid, Bit32ValueInfo bit32ValueInfo, int paramLength) {
        String setValue = String
                .format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        bit32ValueInfo.isBit32() ? "0" : "1", 
                        bit32ValueInfo.isBit31() ? "0" : "1", 
                        bit32ValueInfo.isBit30() ? "0" : "1", 
                        bit32ValueInfo.isBit29() ? "0" : "1", 
                        bit32ValueInfo.isBit28() ? "0" : "1", 
                        bit32ValueInfo.isBit27() ? "0" : "1", 
                        bit32ValueInfo.isBit26() ? "0" : "1", 
                        bit32ValueInfo.isBit25() ? "0" : "1", 
                        bit32ValueInfo.isBit24() ? "0" : "1", 
                        bit32ValueInfo.isBit23() ? "0" : "1", 
                        bit32ValueInfo.isBit22() ? "0" : "1", 
                        bit32ValueInfo.isBit21() ? "0" : "1", 
                        bit32ValueInfo.isBit20() ? "0" : "1", 
                        bit32ValueInfo.isBit19() ? "0" : "1", 
                        bit32ValueInfo.isBit18() ? "0" : "1", 
                        bit32ValueInfo.isBit17() ? "0" : "1", 
                        bit32ValueInfo.isBit16() ? "0" : "1", 
                        bit32ValueInfo.isBit15() ? "0" : "1", 
                        bit32ValueInfo.isBit14() ? "0" : "1", 
                        bit32ValueInfo.isBit13() ? "0" : "1", 
                        bit32ValueInfo.isBit12() ? "0" : "1", 
                        bit32ValueInfo.isBit11() ? "0" : "1", 
                        bit32ValueInfo.isBit10() ? "0" : "1", 
                        bit32ValueInfo.isBit9() ? "0" : "1", 
                        bit32ValueInfo.isBit8() ? "0" : "1", 
                        bit32ValueInfo.isBit7() ? "0" : "1", 
                        bit32ValueInfo.isBit6() ? "0" : "1", 
                        bit32ValueInfo.isBit5() ? "0" : "1", 
                        bit32ValueInfo.isBit4() ? "0" : "1", 
                        bit32ValueInfo.isBit3() ? "0" : "1", 
                        bit32ValueInfo.isBit2() ? "0" : "1", 
                        bit32ValueInfo.isBit1() ? "0" : "1");
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        if(4 == paramLength) {
            sb.append("04");
        } else if(8 == paramLength) {
            sb.append("08");
        }
        // 转成16进制
        sb.append(format(Long.toHexString(Long.parseLong(setValue, 2)),
                paramLength));
        return sb.toString();
    }
    
    /**
     * 位选中状态为1
     * @param paramid
     * @param setValue
     * @param paramLength
     * @return
     */
    private String getBinary1ToHex(String paramid, Bit32ValueInfo bit32ValueInfo, int paramLength) {
        String setValue = String
                .format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        bit32ValueInfo.isBit32() ? "1" : "0", 
                        bit32ValueInfo.isBit31() ? "1" : "0", 
                        bit32ValueInfo.isBit30() ? "1" : "0", 
                        bit32ValueInfo.isBit29() ? "1" : "0", 
                        bit32ValueInfo.isBit28() ? "1" : "0", 
                        bit32ValueInfo.isBit27() ? "1" : "0", 
                        bit32ValueInfo.isBit26() ? "1" : "0", 
                        bit32ValueInfo.isBit25() ? "1" : "0", 
                        bit32ValueInfo.isBit24() ? "1" : "0", 
                        bit32ValueInfo.isBit23() ? "1" : "0", 
                        bit32ValueInfo.isBit22() ? "1" : "0", 
                        bit32ValueInfo.isBit21() ? "1" : "0", 
                        bit32ValueInfo.isBit20() ? "1" : "0", 
                        bit32ValueInfo.isBit19() ? "1" : "0", 
                        bit32ValueInfo.isBit18() ? "1" : "0", 
                        bit32ValueInfo.isBit17() ? "1" : "0", 
                        bit32ValueInfo.isBit16() ? "1" : "0", 
                        bit32ValueInfo.isBit15() ? "1" : "0", 
                        bit32ValueInfo.isBit14() ? "1" : "0", 
                        bit32ValueInfo.isBit13() ? "1" : "0", 
                        bit32ValueInfo.isBit12() ? "1" : "0", 
                        bit32ValueInfo.isBit11() ? "1" : "0", 
                        bit32ValueInfo.isBit10() ? "1" : "0", 
                        bit32ValueInfo.isBit9() ? "1" : "0", 
                        bit32ValueInfo.isBit8() ? "1" : "0", 
                        bit32ValueInfo.isBit7() ? "1" : "0", 
                        bit32ValueInfo.isBit6() ? "1" : "0", 
                        bit32ValueInfo.isBit5() ? "1" : "0", 
                        bit32ValueInfo.isBit4() ? "1" : "0", 
                        bit32ValueInfo.isBit3() ? "1" : "0", 
                        bit32ValueInfo.isBit2() ? "1" : "0", 
                        bit32ValueInfo.isBit1() ? "1" : "0");
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        if(4 == paramLength) {
            sb.append("04");
        } else if(8 == paramLength) {
            sb.append("08");
        }
        // 转成16进制
        sb.append(format(Long.toHexString(Long.parseLong(setValue, 2)),
                paramLength));
        return sb.toString();
    }
    
    
    /**
     * 定时\定距拍照控制
     * @param paramid
     * @param setValue
     * @param paramLength
     * @return
     */
    private String formatScheduleData(String paramid, Bit32ValueInfo bit32ValueInfo, String paramValue) {
        // 各位开启状态
        String lowerVal = String
                .format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                        bit32ValueInfo.isBit16() ? "1" : "0", 
                        bit32ValueInfo.isBit15() ? "1" : "0", 
                        bit32ValueInfo.isBit14() ? "1" : "0", 
                        bit32ValueInfo.isBit13() ? "1" : "0", 
                        bit32ValueInfo.isBit12() ? "1" : "0", 
                        bit32ValueInfo.isBit11() ? "1" : "0", 
                        bit32ValueInfo.isBit10() ? "1" : "0", 
                        bit32ValueInfo.isBit9() ? "1" : "0", 
                        bit32ValueInfo.isBit8() ? "1" : "0", 
                        bit32ValueInfo.isBit7() ? "1" : "0", 
                        bit32ValueInfo.isBit6() ? "1" : "0", 
                        bit32ValueInfo.isBit5() ? "1" : "0", 
                        bit32ValueInfo.isBit4() ? "1" : "0", 
                        bit32ValueInfo.isBit3() ? "1" : "0", 
                        bit32ValueInfo.isBit2() ? "1" : "0", 
                        bit32ValueInfo.isBit1() ? "1" : "0");
        
        // 定时/定距设置值
        String higherVal = format(Integer.toBinaryString(Integer.valueOf(paramValue)),16);
        String binaryStr = higherVal + lowerVal;
        
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        sb.append("08");
        // 转成16进制
        sb.append(format(Long.toHexString(Long.parseLong(binaryStr, 2)),
                8));
        return sb.toString();
    }
    
    /**
     * 获取16进制内容
     * @param paramid
     * @param valueLen
     * @param setValue
     * @return
     */
    private String getParamHexPacketContent(String paramid, String setValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        sb.append("08");
        sb.append(format(Long.toHexString(Long.parseLong(setValue)),8));
        return sb.toString();
    }
    
    /**
     * 获取16进制内容4
     * @param paramid
     * @param valueLen
     * @param setValue
     * @return
     */
    private String getParamHex4PacketContent(String paramid, String setValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        sb.append("04");
        sb.append(format(Integer.toHexString(Integer.parseInt(setValue)), 
                         4));
        return sb.toString();
    }
    
    /**
     * 获取16进制内容2
     * @param paramid
     * @param valueLen
     * @param setValue
     * @return
     */
    private String getVoiceOutputDataContent(String paramid, String setValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        sb.append("02");
        sb.append(format(Integer.toHexString(Integer.parseInt(setValue, 2)),2));
        return sb.toString();
    }
    
    /**
     * 语音输出通道数据拼装
     * @param paramid
     * @param setValue
     * @return
     */
    private String getParamHex2PacketContent(String paramid, String setValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        sb.append("02");
        sb.append(format(Integer.toHexString(Integer.parseInt(setValue)), 
                         2));
        return sb.toString();
    }
    
    /**
     * 获取ASCII内容
     * @param paramid
     * @param setValue
     * @return
     */
    private String getASCIIPacketContent(String paramid, String setValue) {
        StringBuffer sb = new StringBuffer();
        sb.append(paramid);
        try {
            sb.append(format(Integer.toHexString(setValue.getBytes("GBK").length), 
                             2));
        } catch (UnsupportedEncodingException e) {
            log.error("getASCIIPacketContent error, value is" + setValue);
        }
        sb.append(setValue);
        return sb.toString();
    }
    
    /**
     * 分析设置的参数
     * @param bean
     * @return
     */
    private String analyseTerminalParams(XCTerminalParamsInfo bean) {
StringBuffer sb = new StringBuffer();
        
        // 01-终端心跳发送时间间隔
        if(checkValue(bean.getKeepAliveTime())) {
            sb.append(getParamHexPacketContent("00000001", bean.getKeepAliveTime()));
        }
        // 02-TCP消息应答超时时间
        if(checkValue(bean.getTcpOverTime())) {
            sb.append(getParamHexPacketContent("00000002", bean.getTcpOverTime()));
        }
        // 03-TCP消息重传次数
        if(checkValue(bean.getTcpRetransTime())) {
            sb.append(getParamHexPacketContent("00000003", bean.getTcpRetransTime()));
        }
        // 04-UDP消息应答超时时间
        if(checkValue(bean.getUdpOverTime())) {
            sb.append(getParamHexPacketContent("00000004", bean.getUdpOverTime()));
        }
        // 05-UDP消息重传次数
        if(checkValue(bean.getUdpRetransTime())) {
            sb.append(getParamHexPacketContent("00000005", bean.getUdpRetransTime()));
        }
        // 08-核载人数
        if(checkValue(bean.getStandardCnt())){
        	sb.append(getParamHex2PacketContent("0000F100",bean.getStandardCnt()));
        }
        // 10-主服务器APN
        if(checkValue(bean.getMainApn())) {
            sb.append(getASCIIPacketContent("00000010", bean.getMainApn()));
        }
        // 11-主服务器无线通讯拨号用户名
        if(checkValue(bean.getMainUser())) {
            sb.append(getASCIIPacketContent("00000011", bean.getMainUser()));
        }
        // 12-主服务器无线通讯拨号密码
        if(checkValue(bean.getMainPass())) {
            sb.append(getASCIIPacketContent("00000012", bean.getMainPass()));
        }
        // 13-主服务器地址
        if(checkValue(bean.getMainIp())) {
            sb.append(getASCIIPacketContent("00000013", bean.getMainIp()));
        }
        // 14-备份服务器APN
        if(checkValue(bean.getStandbyApn())) {
            sb.append(getASCIIPacketContent("00000014", bean.getStandbyApn()));
        }
        // 15-备份服务器无线通讯拨号用户名
        if(checkValue(bean.getStandbyUser())) {
            sb.append(getASCIIPacketContent("00000015", bean.getStandbyUser()));
        }
        // 16-备份服务器无线通讯拨号密码
        if(checkValue(bean.getStandbyPass())) {
            sb.append(getASCIIPacketContent("00000016", bean.getStandbyPass()));
        }
        // 17-备份服务器地址
        if(checkValue(bean.getStandbyIp())) {
            sb.append(getASCIIPacketContent("00000017", bean.getStandbyIp()));
        }
        // 18-服务器TCP端口
        if(checkValue(bean.getTcpPort())) {
            sb.append(getParamHexPacketContent("00000018", bean.getTcpPort()));
        }
        // 19-服务器UDP端口
        if(checkValue(bean.getUdpPort())) {
            sb.append(getParamHexPacketContent("00000019", bean.getUdpPort()));
        }
        // 20-位置汇报策略
        if(checkValue(bean.getPositionUpType())) {
            sb.append(getParamHexPacketContent("00000020", bean.getPositionUpType()));
        }
        // 21-位置汇报方案
        if(checkValue(bean.getPositionUpSchema())) {
            sb.append(getParamHexPacketContent("00000021", bean.getPositionUpSchema()));
        }
        // 22-驾驶员未登录汇报时间间隔
        if(checkValue(bean.getDriverOverDateTime())) {
            sb.append(getParamHexPacketContent("00000022", bean.getDriverOverDateTime()));
        }
        // 27-休眠时汇报时间间隔
        if(checkValue(bean.getSleepDateTime())) {
            sb.append(getParamHexPacketContent("00000027", bean.getSleepDateTime()));
        }
        // 28-紧急报警时汇报时间间隔
        if(checkValue(bean.getSosTime())) {
            sb.append(getParamHexPacketContent("00000028", bean.getSosTime()));
        }
        // 29-缺省时间汇报间隔
        if(checkValue(bean.getDefaultDateTime())) {
            sb.append(getParamHexPacketContent("00000029", bean.getDefaultDateTime()));
        }
        // 2c-缺省距离汇报间隔
        if(checkValue(bean.getDefaultSpaceTime())) {
            sb.append(getParamHexPacketContent("0000002c", bean.getDefaultSpaceTime()));
        }
        // 2d-驾驶员未登录汇报距离间隔
        if(checkValue(bean.getDriverOverSpaceTime())) {
            sb.append(getParamHexPacketContent("0000002d", bean.getDriverOverSpaceTime()));
        } 
        // 2e-休眠时汇报距离间隔
        if(checkValue(bean.getSleepSpaceTime())) {
            sb.append(getParamHexPacketContent("0000002e", bean.getSleepSpaceTime()));
        }
        // 2f-紧急报警时汇报距离间隔
        if(checkValue(bean.getSosSpaceTime())) {
            sb.append(getParamHexPacketContent("0000002f", bean.getSosSpaceTime()));
        }
        // 30-拐点补传角度
        if(checkValue(bean.getMakeUpAngle())) {
            sb.append(getParamHexPacketContent("00000030", bean.getMakeUpAngle()));
        }
        // 48-监听电话号码
        if(checkValue(bean.getListenPhone())) {
            sb.append(getASCIIPacketContent("00000048", bean.getListenPhone()));
        }
        // 50-报警屏蔽字
        if(checkCheckBoxStatus(bean.isAlarmShieldFlag())) {
            sb.append(getBinary0ToHex("00000050", bean.getAlarmShield(), 8));
        }
        // 52-报警拍摄开关
        if(checkCheckBoxStatus(bean.isAlarmShootFlag())) {
            sb.append(getBinary1ToHex("00000052", bean.getAlarmShootSwitch(), 8));
        }
        // 53-报警拍摄存储标志
        if(checkCheckBoxStatus(bean.isAlarmShootSave())) {
            sb.append(getBinary1ToHex("00000053", bean.getAlarmShootSaveFlag(), 8));
        }
        // 55-最高速度
        if(checkValue(bean.getTopSpeed())) {
            sb.append(getParamHexPacketContent("00000055", bean.getTopSpeed()));
        }
        // 56-超速持续时间
        if(checkValue(bean.getOverspeedTime())) {
            sb.append(getParamHexPacketContent("00000056", bean.getOverspeedTime()));
        }
        // 5b-超速报警预警差值
        if(checkValue(bean.getOverspeedAlarmDifference())) {
            sb.append(getParamHex4PacketContent("0000005b", bean.getOverspeedAlarmDifference()));
        }
        // 5d-特征系数
        if(checkValue(bean.getCharacteristicOefficient())) {
            sb.append(getParamHex4PacketContent("0000005d", bean.getCharacteristicOefficient()));
        }
        // 5e-车轮每转脉冲数
        if(checkValue(bean.getWheelPulseCount())) {
            sb.append(getParamHex4PacketContent("0000005e", bean.getWheelPulseCount()));
        }
        // 5f-油箱容量
        if(checkValue(bean.getFuelCapacity())) {
            sb.append(getParamHex4PacketContent("0000005f", bean.getFuelCapacity()));
        }
        // 61-门开关拍照控制
        if(checkCheckBoxStatus(bean.isCarDoorFlag())) {
            sb.append(getBinary1ToHex("00000061", bean.getCarDoorControl(), 8));
        }
        // 62-终端外设安装配置
        if(checkCheckBoxStatus(bean.isTerminalOuterDeviceFlag())) {
            sb.append(getBinary1ToHex("00000062", bean.getTerminalOuterDevice(), 8));
        }
        // 64-定时拍照控制
        /*if(checkCheckBoxStatus(bean.isRegularCameraFlag())) {
        	String regulartime = bean.getRegularTime()==null||bean.getRegularTime().equals("")?"0":bean.getRegularTime();
            sb.append(formatScheduleData("00000064", bean.getRegularCameraControl(), String.valueOf(Integer.parseInt(regulartime)*60)));
        } else {
        	sb.append(formatScheduleData("00000064", bean.getRegularCameraControl(), "0"));
        }*/
        if(checkValue(bean.getRegularTime())) {
        	String regulartime = bean.getRegularTime()==null||bean.getRegularTime().equals("")?"0":bean.getRegularTime();
            sb.append(formatScheduleData("00000064", bean.getRegularCameraControl(), String.valueOf(Integer.parseInt(regulartime)*60)));
        } else {
        	sb.append(formatScheduleData("00000064", bean.getRegularCameraControl(), "0"));
        }
        
        // 65-定距拍照控制
        /*if(checkCheckBoxStatus(bean.isFixDistanceFlag())) {
        	String fixDistance = bean.getFixDistance()==null||bean.getFixDistance().equals("")?"0":bean.getFixDistance();
            sb.append(formatScheduleData("00000065", bean.getFixDistanceCameraControl(), String.valueOf(Integer.parseInt(fixDistance)*1000)));
        } else {
        	sb.append(formatScheduleData("00000065", bean.getFixDistanceCameraControl(), "0"));
        }*/
        if(checkValue(bean.getFixDistance())) {
        	String fixDistance = bean.getFixDistance()==null||bean.getFixDistance().equals("")?"0":bean.getFixDistance();
            sb.append(formatScheduleData("00000065", bean.getFixDistanceCameraControl(), String.valueOf(Integer.parseInt(fixDistance)*1000)));
        } else {
        	sb.append(formatScheduleData("00000065", bean.getFixDistanceCameraControl(), "0"));
        }
        // 66-速度来源设置
        if(checkValue(bean.getSpeedSourceSetting())) {
            sb.append(getParamHex2PacketContent("00000066", bean.getSpeedSourceSetting()));
        }
        // 80-车辆里程表读数 
        if(checkValue(bean.getOdometer())) {
            sb.append(getParamHexPacketContent("00000080", bean.getOdometer()));
        }
        // 83-车牌号
        if(checkValue(bean.getVehicleLn())) {
            sb.append(getASCIIPacketContent("00000083", bean.getVehicleLn()));
        }
        // 84-车牌颜色
        if(checkValue(bean.getVehicleLnColor())) {
            sb.append(getParamHex2PacketContent("00000084", bean.getVehicleLnColor()));
        }
        // f0-语音输出通道控制
        if(checkValue(bean.getVoiceOutputControlType0())) {
            sb.append(getVoiceOutputDataContent("000000f0", 
                                                String.format(
                                                        "%s%s%s%s%s%s%s%s", 
                                                        "0", 
                                                        "0", 
                                                        bean.getVoiceOutputControlType5(),
                                                        bean.getVoiceOutputControlType4(),
                                                        bean.getVoiceOutputControlType3(),
                                                        bean.getVoiceOutputControlType2(),
                                                        bean.getVoiceOutputControlType1(),
                                                        bean.getVoiceOutputControlType0())
                                                )
                                               );
        }
        // f1-学生刷卡自动切换行程控制
        if(checkValue(bean.getAutoSwitchTrip())) {
            sb.append(getParamHex2PacketContent("000000f1", bean.getAutoSwitchTrip()));
        }
        return sb.toString();
    }
    
    /**
     * 格式化String
     * @param str
     * @param len
     * @return
     */
    private String format(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
    
    /**
     * 拼状报文
     * @return
     */
    private String getSendUpdateCommandXML(XCTerminalParamsInfo bean, String sendType) {
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

        String messageId = UUIDGenerator.getUUID32();
        setNewValue(tree.addNewValue(), "appid", str, MemData.getAppcfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCore_pass(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, "0010");
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, sendType);
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, bean.getVehicleVin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, bean.getSimCardNumber());
        setNewValue(tree.addNewValue(), "MSG_ID", str, messageId);
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, bean.getUserId());
       // setNewValue(tree.addNewValue(), "REMARK", bstr, null);
        
        if("2007".equals(sendType)) {
            // 终端参数查询
            StringBuffer sb = new StringBuffer();
            sb.append("000420070120");
            sb.append(messageId);
            setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(sb.toString(), "UTF-8"));
        } else if("2103".equals(sendType)) {
            // 终端参数设置
            StringBuffer sb = new StringBuffer();
            sb.append("00042103FF20");
            sb.append(messageId);
            sb.append("FE02");
            
            //开始组装参数  -----------------add by ningdonghai   20130605
            String packentContent = analyseTerminalParams(bean);
            sb.append(format(Integer.toHexString(paramsCount), 2));
            sb.append(packentContent);
            System.out.println("packet_content:" + sb.toString());
            log.info("packet_content:" + sb.toString());
            setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(sb.toString(), "UTF-8"));
        }
        
        setNewValue(tree.addNewValue(), "REGGRPID", str, null);
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        
        return doc.xmlText();
    }
    
    
    
    /**
     * 初始化核心参数
     */
    public void initParameter() {
    	log.info("start reload config");
        try {
            Service service = (Service) AppContextHelper.getBean("service");
            // 初始化参数表
            List < Appcfg > appcfglist = service.getObjects(
                    "AppConfig.getAppConfigInfos", Constants.CLW_M_CODE);
            MemData.setAppcfgList(appcfglist);
            
            log.info("start reload end success");
        } catch (BusinessException e) {
            log.error("参数初始化出错:", e);
        } catch (Exception e2) {
            log.error("参数初始化出错:", e2);
        }
    }
    
    /**
     * 初始化HTTP连接
     * @param url
     * @return
     * @throws IOException
     */
    private HttpURLConnection initConnection(URL url) throws IOException {
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(30);
        return connection;
    }
    
    /**
     * 发送HTTP请求
     * @param connection
     * @param strXml
     * @throws IOException
     */
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
    
    /**
     * 获取返回值
     * @param connection
     * @param strXml
     * @return
     * @throws IOException
     */
    private int receiveXml(HttpURLConnection connection, StringBuffer strXml)
            throws IOException {
    	try {
            int nStatusCode = connection.getResponseCode();
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
        return 0;
    }
    
    /**
     * 发送通知
     * @param sendXml
     * @return
     * @throws MalformedURLException
     */
    private String sendHttpCommand(String sendXml) throws MalformedURLException {
    	URL serurl = new URL(MemData.getAppcfgList().get(0).getSend_path());
        //
        HttpURLConnection connection = null;
        // 自定义异常返回码
        String recode = "";
        try {
            connection = initConnection(serurl);
            sendXml(connection, sendXml);
            log.info("请求xml内容:"
                    + OlxDocument.Factory.parse(StringUtils.trim(sendXml))
                            .xmlText());
            log.info("等待" + connection.getURL() + "响应消息...");

            StringBuffer strReceive = new StringBuffer();
            int nStatusCode = receiveXml(connection, strReceive);
            log.info("HTTP响应码: " + nStatusCode + "  "
                    + connection.getResponseMessage());
            
            if (200 == nStatusCode) {
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
                    if ("c".equals(value[i].getKey())) {
                        recode = value[i].getValue();
                        break;
                    }
                }
            } else {
                log.info("消息:" + connection.getResponseMessage());
                log.info("ContentLength:" + connection.getContentLength());
                recode = nStatusCode + "";
            }

        } catch (Exception e) {
            log.info(e.getMessage() + "\r\n" + e.getCause());

        } finally {
            if (null != connection) {
                connection.disconnect();
                connection = null;
            }
        }
        return recode;
    }
    
    /**
     * 查询终端参数
     * @return
     */
    public String queryTerminalParams(XCTerminalParamsInfo bean) {
String ret = "";
        
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        
        String reqXml = getSendUpdateCommandXML(bean, "2007");
        
        try {
            log.info("下发终端参数查询命令");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发终端参数查询命令异常：" + e.getMessage());
            return ret;
        }
    }
    
    /**
     * 设置终端参数
     * @param bean
     * @return
     */
    public String setTerminalParams(XCTerminalParamsInfo bean) {
String ret = "";
        
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        
        paramsCount = 0;
        
        String reqXml = getSendUpdateCommandXML(bean, "2103");
        
        try {
            log.info("下发终端参数设置命令");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发终端参数设置异常：" + e.getMessage());
            return ret;
        }
    }
}

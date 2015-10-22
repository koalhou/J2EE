package com.neusoft.clw.yw.xj.terminalparam.action;

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
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.servlet.ds.Appcfg;
import com.neusoft.clw.common.util.servlet.ds.MemData;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.xj.terminalparam.ds.XCTerminalParamsInfo;
import com.neusoft.clw.yw.zdnew.speedmonitoring.ds.SpeedMonitoringInfo;

/**
 * 发送消息类
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2011-9-1 下午12:57:34
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
     * 判断字符是否为空 true：非空 false：空
     * @param str
     * @return
     */
    private boolean checkEmpty(String str) {
        if(str == null || str.length() == 0 ) {
            return false;
        }
        return true;
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
        sb.append(format(Integer.toHexString(Integer.parseInt(setValue)), 
                         8));
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
            sb.append(getParamHexPacketContent("01", bean.getKeepAliveTime()));
        }
        // 02-TCP消息应答超时时间
        if(checkValue(bean.getTcpOverTime())) {
            sb.append(getParamHexPacketContent("02", bean.getTcpOverTime()));
        }
        // 03-TCP消息重传次数
        if(checkValue(bean.getTcpRetransTime())) {
            sb.append(getParamHexPacketContent("03", bean.getTcpRetransTime()));
        }
        // 04-UDP消息应答超时时间
        if(checkValue(bean.getUdpOverTime())) {
            sb.append(getParamHexPacketContent("04", bean.getUdpOverTime()));
        }
        // 05-UDP消息重传次数
        if(checkValue(bean.getUdpRetransTime())) {
            sb.append(getParamHexPacketContent("05", bean.getUdpRetransTime()));
        }
        // 20-位置汇报策略
        if(checkValue(bean.getPositionUpType())) {
            sb.append(getParamHexPacketContent("20", bean.getPositionUpType()));
        }
        // 21-位置汇报方案
        if(checkValue(bean.getPositionUpSchema())) {
            sb.append(getParamHexPacketContent("21", bean.getPositionUpSchema()));
        }
        // 27-休眠时汇报时间间隔
        if(checkValue(bean.getSleepDateTime())) {
            sb.append(getParamHexPacketContent("27", bean.getSleepDateTime()));
        }
        // 28-紧急报警时汇报时间间隔
        if(checkValue(bean.getSosTime())) {
            sb.append(getParamHexPacketContent("28", bean.getSosTime()));
        }
        // 29-缺省时间汇报间隔
        if(checkValue(bean.getDefaultDateTime())) {
            sb.append(getParamHexPacketContent("29", bean.getDefaultDateTime()));
        }
        // 2c-缺省距离汇报间隔
        if(checkValue(bean.getDefaultSpaceTime())) {
            sb.append(getParamHexPacketContent("2c", bean.getDefaultSpaceTime()));
        }
        // 2d-驾驶员未登录汇报距离间隔
        if(checkValue(bean.getDriverOverSpaceTime())) {
            sb.append(getParamHexPacketContent("2d", bean.getDriverOverSpaceTime()));
        } 
        // 2e-休眠时汇报距离间隔
        if(checkValue(bean.getSleepSpaceTime())) {
            sb.append(getParamHexPacketContent("2e", bean.getSleepSpaceTime()));
        }
        // 2f-紧急报警时汇报距离间隔
        if(checkValue(bean.getSosSpaceTime())) {
            sb.append(getParamHexPacketContent("2f", bean.getSosSpaceTime()));
        }
        // 30-拐点补传角度
        if(checkValue(bean.getMakeUpAngle())) {
            sb.append(getParamHexPacketContent("30", bean.getMakeUpAngle()));
        }
        // 10-主服务器APN
        if(checkValue(bean.getMainApn())) {
            sb.append(getASCIIPacketContent("10", bean.getMainApn()));
        }
        // 11-主服务器无线通讯拨号用户名
        if(checkValue(bean.getMainUser())) {
            sb.append(getASCIIPacketContent("11", bean.getMainUser()));
        }
        // 12-主服务器无线通讯拨号密码
        if(checkValue(bean.getMainPass())) {
            sb.append(getASCIIPacketContent("12", bean.getMainPass()));
        }
        // 14-备份服务器APN
        if(checkValue(bean.getStandbyApn())) {
            sb.append(getASCIIPacketContent("14", bean.getStandbyApn()));
        }
        // 15-备份服务器无线通讯拨号用户名
        if(checkValue(bean.getStandbyUser())) {
            sb.append(getASCIIPacketContent("15", bean.getStandbyUser()));
        }
        // 16-备份服务器无线通讯拨号密码
        if(checkValue(bean.getStandbyPass())) {
            sb.append(getASCIIPacketContent("16", bean.getStandbyPass()));
        }
        // 13-主服务器地址
        if(checkValue(bean.getMainIp())) {
            sb.append(getASCIIPacketContent("13", bean.getMainIp()));
        }
        // 18-服务器TCP端口
        if(checkValue(bean.getTcpPort())) {
            sb.append(getParamHexPacketContent("18", bean.getTcpPort()));
        }
        // 19-服务器UDP端口
        if(checkValue(bean.getUdpPort())) {
            sb.append(getParamHexPacketContent("19", bean.getUdpPort()));
        }
        // 40-监控平台电话号码
        if(checkValue(bean.getMonitorPhone())) {
            sb.append(getASCIIPacketContent("40", bean.getMonitorPhone()));
        }
        // 41-复位电话号码
        if(checkValue(bean.getResetPhone())) {
            sb.append(getASCIIPacketContent("41", bean.getResetPhone()));
        }
        // 42-恢复出厂设置电话号码
        if(checkValue(bean.getResetFactory())) {
            sb.append(getASCIIPacketContent("42", bean.getResetFactory()));
        }
        // 43-监管平台SMS电话号码
        if(checkValue(bean.getMonitorSmsPhone())) {
            sb.append(getASCIIPacketContent("43", bean.getMonitorSmsPhone()));
        }
        // 44-接收终端SMS文本报警号码
        if(checkValue(bean.getTerminalSmsPhone())) {
            sb.append(getASCIIPacketContent("44", bean.getTerminalSmsPhone()));
        }
        // 45-终端电话接听策略
        if(checkValue(bean.getTerminalPhoneTactic())) {
            sb.append(getParamHexPacketContent("45", bean.getTerminalPhoneTactic()));
        }
        // 46-每次最长通话时间
        if(checkValue(bean.getCallTimePer())) {
            sb.append(getParamHexPacketContent("46", bean.getCallTimePer()));
        }
        // 47-当月最长通话时间
        if(checkValue(bean.getCallTimeMonth())) {
            sb.append(getParamHexPacketContent("47", bean.getCallTimeMonth()));
        }
        // 80-车辆里程表读数 
        if(checkValue(bean.getOdometer())) {
            sb.append(getParamHexPacketContent("80", bean.getOdometer()));
        }
        // 70-图像/视频质量
        if(checkValue(bean.getMediaQuality())) {
            sb.append(getParamHexPacketContent("70", bean.getMediaQuality()));
        }
        // 71-亮度
        if(checkValue(bean.getLuminance())) {
            sb.append(getParamHexPacketContent("71", bean.getLuminance()));
        }
        // 72-对比度
        if(checkValue(bean.getContrast())) {
            sb.append(getParamHexPacketContent("72", bean.getContrast()));
        }
        // 73-饱和度
        if(checkValue(bean.getSaturation())) {
            sb.append(getParamHexPacketContent("73", bean.getSaturation()));
        }
        // 74-色度
        if(checkValue(bean.getChroma())) {
            sb.append(getParamHexPacketContent("74", bean.getChroma()));
        }
        // 50-报警屏蔽字
        if(checkValue(bean.getAlarmShield())) {
            sb.append(getParamHexPacketContent("50", bean.getAlarmShield()));
        }
        // 51-报警发送文本SMS开关
        if(checkValue(bean.getAlarmSmsSwitch())) {
            sb.append(getParamHexPacketContent("51", bean.getAlarmSmsSwitch()));
        }
        // 52-报警拍摄开关
        if(checkValue(bean.getAlarmShootSwitch())) {
            sb.append(getParamHexPacketContent("52", bean.getAlarmShootSwitch()));
        }
        // 53-报警拍摄存储标志
        if(checkValue(bean.getAlarmShootSaveFlag())) {
            sb.append(getParamHexPacketContent("53", bean.getAlarmShootSaveFlag()));
        }
        // 54-关键标志
        if(checkValue(bean.getKeyFlag())) {
            sb.append(getParamHexPacketContent("54", bean.getKeyFlag()));
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
                .get(0).getAppId());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCorePass(), "UTF-8"));
        
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
     * 拼状终端远程升级报文
     * @return
     */
    private String getTerminalUpdateCommandXML(TerminalParamsInfo bean) {
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
                .get(0).getAppId());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCorePass(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, "0010");
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, "2105");
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, bean.getVehicleVin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, bean.getSimCardNumber());
        setNewValue(tree.addNewValue(), "MSG_ID", str, messageId);
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, bean.getUserId());
        
        // 拼装数据包
        StringBuffer sb = new StringBuffer();
        sb.append("000421050120");
        sb.append(messageId);
        sb.append("020201");
        
        StringBuffer updateParams = new StringBuffer();

        // 拨号点名称
        if(checkEmpty(bean.getMainapn())) {
            updateParams.append(getASCIIPacketContent("05", bean.getMainapn()));
            //updateParams.append(";");
        }
        
        // 拨号点用户名【必填项】
        if(checkEmpty(bean.getMainuser())) {
            updateParams.append(getASCIIPacketContent("06", bean.getMainuser()));
            //updateParams.append(";");
        }
        
        // 拨号密码【必填项】
        if(checkEmpty(bean.getMainpass())) {
            updateParams.append(getASCIIPacketContent("07", bean.getMainpass()));
            //updateParams.append(";");
        }
        
        // IP地址【必填项】
        if(checkEmpty(bean.getIp())) {
            updateParams.append(getASCIIPacketContent("08", bean.getIp()));
            //updateParams.append(";");
        }
        
        // TCP端口【必填项】
        if(checkEmpty(bean.getTcpport())) {
            updateParams.append(getParamHexPacketContent("09", bean.getTcpport()));
            //updateParams.append(";");
        }
        
        // UDP端口
        if(checkEmpty(bean.getUdpport())) {
            updateParams.append(getParamHexPacketContent("0A", bean.getUdpport()));
            //updateParams.append(";");
        }
        
        // 硬件版本【必填项】
        if(checkEmpty(bean.getHardver())) {
            updateParams.append(getASCIIPacketContent("0D", bean.getHardver()));
            //updateParams.append(";");
        }
        
        // 固件版本【必填项】
        if(checkEmpty(bean.getFirmver())) {
            updateParams.append(getASCIIPacketContent("0E", bean.getFirmver()));
            //updateParams.append(";");
        }
        
        // URL地址
        if(checkEmpty(bean.getUrl())) {
            updateParams.append(getASCIIPacketContent("0F", bean.getUrl()));
            //updateParams.append(";");
        }
        
        // 连接到指定服务器时限
        if(checkEmpty(bean.getTimePer())) {
            updateParams.append(getParamHexPacketContent("10", bean.getTimePer()));
            //updateParams.append(";");
        }
        
        // 升级信息拼装完成
        // sb.append(getASCIIPacketContent("03", updateParams.toString()));
        sb.append(updateParams.toString());
        log.info("packet_content:" + sb.toString());
        setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(sb.toString(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "REGGRPID", str, null);
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        
        return doc.xmlText();
    }
    
    /**
     * 拼状新终端远程升级报文
     * @return
     */
    private String getTerminalUpdateNewCommandXML(TerminalParamsInfo bean,String messageId) {
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
        
        setNewValue(tree.addNewValue(), "appid", str, MemData.getAppcfgList()
                .get(0).getAppId());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCorePass(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, "0010");
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, "2105");
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, bean.getVehicleVin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, bean.getSimCardNumber());
        setNewValue(tree.addNewValue(), "MSG_ID", str, messageId);
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, bean.getUserId());
        
        // 拼装数据包
        StringBuffer sb = new StringBuffer();
        sb.append("000421050120");
        sb.append(messageId);
        sb.append("020201");
        
        StringBuffer updateParams = new StringBuffer();

        // 拨号点名称
        if(checkEmpty(bean.getMainapn())) {
            updateParams.append(getASCIIPacketContent("05", bean.getMainapn()));
            //updateParams.append(";");
        }
        
        // 拨号点用户名【必填项】
        if(checkEmpty(bean.getMainuser())) {
            updateParams.append(getASCIIPacketContent("06", bean.getMainuser()));
            //updateParams.append(";");
        }
        
        // 拨号密码【必填项】
        if(checkEmpty(bean.getMainpass())) {
            updateParams.append(getASCIIPacketContent("07", bean.getMainpass()));
            //updateParams.append(";");
        }
        
        // IP地址【必填项】
        if(checkEmpty(bean.getIp())) {
            updateParams.append(getASCIIPacketContent("08", bean.getIp()));
            //updateParams.append(";");
        }
        
        // TCP端口【必填项】
        if(checkEmpty(bean.getTcpport())) {
            updateParams.append(getParamHexPacketContent("09", bean.getTcpport()));
            //updateParams.append(";");
        }
        
        // UDP端口
        if(checkEmpty(bean.getUdpport())) {
            updateParams.append(getParamHexPacketContent("0A", bean.getUdpport()));
            //updateParams.append(";");
        }
        
        // 硬件版本【必填项】
        if(checkEmpty(bean.getHardver())) {
            updateParams.append(getASCIIPacketContent("0D", bean.getHardver()));
            //updateParams.append(";");
        }
        
        // 固件版本【必填项】
        if(checkEmpty(bean.getFirmver())) {
            updateParams.append(getASCIIPacketContent("0E", bean.getFirmver()));
            //updateParams.append(";");
        }
        
        // URL地址
        if(checkEmpty(bean.getUrl())) {
            updateParams.append(getASCIIPacketContent("0F", bean.getUrl()));
            //updateParams.append(";");
        }
        
        // 连接到指定服务器时限
        if(checkEmpty(bean.getTimePer())) {
            updateParams.append(getParamHexPacketContent("10", bean.getTimePer()));
            //updateParams.append(";");
        }
        
        // 升级信息拼装完成
        // sb.append(getASCIIPacketContent("03", updateParams.toString()));
        sb.append(updateParams.toString());
        log.info("packet_content:" + sb.toString());
        setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(sb.toString(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "REGGRPID", str, null);
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        
        return doc.xmlText();
    }
    
    /**
     * 拼装开启/关闭VSS矫正
     * @param bean
     * @param configType 00:启动 01:关闭
     * @return
     */
    private String getStartAdjustXML(SpeedMonitoringInfo bean, String configType) {
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
                .get(0).getAppId());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getAppcfgList().get(0).getCorePass(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "SEND_COMMAND", str, "0010");
        setNewValue(tree.addNewValue(), "SEND_TYPE", str, "2C00");
        setNewValue(tree.addNewValue(), "TERMINAL_ID", str, bean.getVehicleVin());
        setNewValue(tree.addNewValue(), "SIM_CARD_NUMBER", str, bean.getSimCardNo());
        setNewValue(tree.addNewValue(), "MSG_ID", str, messageId);
        setNewValue(tree.addNewValue(), "OPERATE_USER_ID", str, bean.getUserId());
        
        // 拼装数据包
        StringBuffer sb = new StringBuffer();
        sb.append("00042C000120");
        sb.append(messageId);
        sb.append("0202");
        sb.append(configType);
        log.info("packet_content:" + sb.toString());
        
        setNewValue(tree.addNewValue(), "PACKET_CONTENT", bstr, Base64.encode(sb.toString(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "REGGRPID", str, null);
        setNewValue(tree.addNewValue(), "CHANLE_CODE", str, null);
        setNewValue(tree.addNewValue(), "BATCH_ID", str, UUIDGenerator.getUUID32());
        
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
        URL serurl = new URL(MemData.getAppcfgList().get(0).getSendPath());
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
    
    /**
     * 终端远程升级
     * @return
     */
    public String updateTerminalVersion(TerminalParamsInfo bean) {
        String ret = "";
        
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        
        String reqXml = getTerminalUpdateCommandXML(bean);
        
        try {
            log.info("下发终端远程升级命令");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发终端远程升级命令异常：" + e.getMessage());
            return ret;
        } 
        
    }
    
    /**
     * 新终端远程升级
     * @return
     */
    public String updateTerminalNewVersion(TerminalParamsInfo bean,String messageId) {
        String ret = "";
        
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        String reqXml = getTerminalUpdateNewCommandXML(bean,messageId);
        
        try {
            log.info("下发终端远程升级命令");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发终端远程升级命令异常：" + e.getMessage());
            return ret;
        } 
    }
    
    /**
     * 下发开启/关闭VSS矫正
     * @param bean
     * @return
     */
    public String configAdjust(SpeedMonitoringInfo bean, String configType) {
        String ret = "";
        
        if (MemData.getAppcfgList() != null) {
            if (MemData.getAppcfgList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        String reqXml = getStartAdjustXML(bean, configType);
        
        try {
            log.info("下发开启/关闭VSS矫正");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发开启/关闭VSS矫正异常：" + e.getMessage());
            return ret;
        } 
    }
}

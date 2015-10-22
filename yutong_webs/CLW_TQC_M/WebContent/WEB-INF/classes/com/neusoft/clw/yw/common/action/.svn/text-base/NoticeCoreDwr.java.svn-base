package com.neusoft.clw.yw.common.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Base64;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.servlet.ds.Appcfg;
import com.neusoft.clw.common.util.servlet.ds.MemData;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;

/**
 * 实时通知类
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2011-9-1 下午12:57:34
 */
public class NoticeCoreDwr extends BaseAction {
    private transient Service service;

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
     * 拼状报文
     * @param vehicleFlag
     * @param terminalFlag
     * @return
     */
    private String getSendUpdateCommandXML(String vehicleFlag, String terminalFlag) {
        String str = "str";
        String bstr = "bstr";

        OlxDocument doc = OlxDocument.Factory.newInstance();
        Olx x = doc.addNewOlx();
        x.setDir("up");
        x.setVersion("0.0.1");
        Function f = x.addNewFunction();
        f.setName("vncs.app.sendupdate");
        f.setTimeout("60");
        f.setSeq("3001");
        Param p = f.addNewParam();
        TreeObject tree = p.addNewTreeObject();

        setNewValue(tree.addNewValue(), "appid", str, MemData
                .getRealNoticList().get(0).getAppId());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getRealNoticList().get(0).getCorePass(), "UTF-8"));
        
        setNewValue(tree.addNewValue(), "vehicle", str, vehicleFlag.trim());
        setNewValue(tree.addNewValue(), "terminal", str, terminalFlag.trim());
        return doc.xmlText();
    }
    
    /**
     * 初始化核心参数
     */
    public void initParameter() {
        log.info("start reload config");
        try {
            // 初始化参数表
            List < Appcfg > appcfglist = service.getObjects(
                    "AppConfig.getAppConfigInfos", Constants.CLW_M_REAL_CODE);
            MemData.setRealNoticList(appcfglist);
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
        URL serurl = new URL(MemData.getRealNoticList().get(0).getSendPath());
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
     * 通知核心同步注册关系
     * @param vehicleFlag 1:需要同步 0:不需要同步
     * @param terminalFlag 1:需要同步 0:不需要同步
     * @return
     */
    public String noticeCore(String vehicleFlag, String terminalFlag) {
        String ret = "";
        
        if (MemData.getRealNoticList() != null) {
            if (MemData.getRealNoticList().get(0) == null) {
                initParameter();
            }
        } else {
            log.warn("MemData.appcfgList为空,reload config!");
            initParameter();
        }
        
        String reqXml = getSendUpdateCommandXML(vehicleFlag, terminalFlag);
        
        try {
            log.info("下发数据同步命令");
            System.out.println(reqXml);
            ret = sendHttpCommand(reqXml);
            
            return ret;
        } catch (Exception e) {
            log.info("下发数据同步命令异常：" + e.getMessage());
            return ret;
        }
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}

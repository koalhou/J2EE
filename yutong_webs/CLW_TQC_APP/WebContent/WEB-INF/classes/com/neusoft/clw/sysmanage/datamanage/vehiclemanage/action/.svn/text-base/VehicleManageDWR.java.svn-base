package com.neusoft.clw.sysmanage.datamanage.vehiclemanage.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Base64;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MemData;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.sysmanage.datamanage.appconfig.domain.Appcfg;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 车辆管理DWR
 * @author JinPeng
 */
public class VehicleManageDWR extends BaseAction {

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    private transient Service service;
    
    private SendCommandClient sendCommandClient;
    

    public SendCommandClient getSendCommandClient() {
		return sendCommandClient;
	}

	public void setSendCommandClient(SendCommandClient sendCommandClient) {
		this.sendCommandClient = sendCommandClient;
	}

	/**
     * 判断车牌号唯一性
     * @param terminalCode
     * @return
     */
    public boolean checkVehiclelnUnique(String velnCode) {
        boolean ret = false;

        try {
            int cnt = service.getCount("VehicleManage.getCountforln", velnCode);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("终端号查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }

    public boolean checkVehileCodeUnique(String vehcode, String enid) {
        boolean ret = false;
        try {
            VehcileInfo veh = new VehcileInfo();
            veh.setVehicle_code(vehcode);
            veh.setEnterprise_id(enid);
            int cnt = service.getCount("VehicleManage.getCountforvehcode", veh);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("车辆编码查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }

    /**
     * 通知核心车牌号变化
     */
    public void postVehiclelnadvice(String vin,String ln,String color) {
    	WebContext ctx = WebContextFactory.get();
        HttpServletRequest request = ctx.getHttpServletRequest();
        // 取得session对象实例
        UserInfo user = (UserInfo) request.getSession().getAttribute(
                Constants.USER_SESSION_KEY);
        
        if (null != MemData.getSyncfgList()) {
            if (null != MemData.getSyncfgList().get(0)) {
            } else {
                log.warn("MemData.getSyncfgList().get(0)为空,reload config!");
                initsynParameter();
            }
        } else {
            log.warn("MemData.syncfgList为空,reload config!");
            initsynParameter();
        }
        String advicexml = getSendAdviceXML();
        try {
            sendHttpadvice(advicexml);
            
            log.info("vin:" + vin + ";ln:" + ln+ ";color:" + color
    				+ ";userid:" + user.getUserID());
            String msgid = UUIDGenerator.getUUID32();
    		String returnvalue = sendCommandClient.sendVehParm(vin, user.getUserID(),ln,
    				color,"2",msgid);
    		log.info("returnvalue:" + returnvalue);
    		
        } catch (Exception e) {
            log.info("通知核心异常：" + e.getMessage());
        }

    }

    private String getSendAdviceXML() {
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

        setNewValue(tree.addNewValue(), "AppId", str, MemData.getSyncfgList()
                .get(0).getApp_id());
        setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(MemData
                .getSyncfgList().get(0).getCore_pass(), "UTF-8"));
        setNewValue(tree.addNewValue(), "vehicle", str, "1");
        setNewValue(tree.addNewValue(), "terminal", str, "0");
        return doc.xmlText();
    }

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

    private void sendHttpadvice(String sendXml) throws MalformedURLException {
        doPostadvice(sendXml);
    }

    public void doPostadvice(String strSend) throws MalformedURLException {
        URL serurl = new URL(MemData.getSyncfgList().get(0).getSend_path());
        HttpURLConnection connection = null;
        // 自定义异常返回码
        try {
            log.info("同步车牌请求xml内容:"
                    + OlxDocument.Factory.parse(StringUtils.trim(strSend))
                            .xmlText());
            connection = initeConnection(serurl);
            sendXml(connection, strSend);
            StringBuffer strReceive = new StringBuffer();
            int nStatusCode = receiveXml(connection, strReceive);
            log.info("同步车牌HTTP响应码: " + nStatusCode + "  "
                    + connection.getResponseMessage());

        } catch (Exception e) {
            log.info("同步车牌异常:"+e.getMessage() + "\r\n" + e.getCause());

        } finally {
            if (null != connection) {
                connection.disconnect();
                connection = null;
            }
        }
    }

    public void initsynParameter() {
        log.info("start reload syn parameter begin");
        try {
            // 初始化参数表
            List < Appcfg > syncfglist = service.getObjects("Appcfg.getInfos",
                    Constants.CLW_SYN_CODE);
            MemData.setSyncfgList(syncfglist);
            log.info("start reload syn parameter end success");
        } catch (BusinessException e) {
            log.error("参数初始化出错:", e);
        } catch (Exception e2) {
            log.error("参数初始化出错:", e2);
        }
    }

    private HttpURLConnection initeConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setConnectTimeout(30);
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

    // 设置一个value标签的三个属性值
    private static void setNewValue(Value value, String strKey, String strClas,
            String objValue) {
        value.setKey(strKey);
        value.setClass1(Value.Class.Enum.forString(strClas));
        // String strValue = String.valueOf(objValue);
        value.setValue(objValue);
    }

}

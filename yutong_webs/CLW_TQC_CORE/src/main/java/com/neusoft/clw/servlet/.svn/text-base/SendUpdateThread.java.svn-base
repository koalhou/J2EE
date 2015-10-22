package com.neusoft.clw.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.core.xmlbean.FunctionDocument.Function;
import com.neusoft.clw.core.xmlbean.OlxDocument.Olx;
import com.neusoft.clw.core.xmlbean.ParamDocument.Param;
import com.neusoft.clw.core.xmlbean.ResultDocument.Result;
import com.neusoft.clw.core.xmlbean.TreeObjectDocument.TreeObject;
import com.neusoft.clw.core.xmlbean.ValueDocument.Value;
import com.neusoft.clw.info.bean.AppBean;
import com.neusoft.clw.info.bean.RequestBean;
import com.neusoft.clw.info.business.SetResponseBean;
import com.neusoft.clw.info.dao.AppDAO;
import com.neusoft.clw.info.exception.InvalidXmlFormatException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.util.Base64;

public class SendUpdateThread implements Runnable {
	private static final Logger log = Logger.getLogger(ClwServlet.class);
	private RequestBean reqBean;

	public SendUpdateThread(RequestBean reqBean) {
		this.reqBean = reqBean;
	}

	public void run() {
		AppBean appbean = getAppBean();
		int terminal;
		int vehicle;
		long l = Long.parseLong((String) Config.props.get("sleepNoticeTime")) * 1000;
		int i=0;
		while (reqBean.getTerminal() != 0 || reqBean.getVehicle() != 0) {
			terminal = reqBean.getTerminal();
			vehicle = reqBean.getVehicle();
			if(sendXml(appbean, terminal, vehicle)){
				log.info("SendUpdateThread::通知平台成功");
			}else{
				try {
					Thread.sleep(1);
					log.info("SendUpdateThread::通知平台失败，等待"+l+"秒继续进行通知");
					if(i>=3){
						break;
					}else{
						i++;
					}
				} catch (InterruptedException e) {
					if(i>=3){
						break;
					}else{
						i++;
						continue;
					}
				}
			}
		}
		SetResponseBean.FLAG = false;
//		if (reqBean.getTerminal() == 0 && reqBean.getVehicle() == 0) {
//			SetResponseBean.FLAG = false;
//			while (reqBean.getTerminal() != 0 || reqBean.getVehicle() != 0) {
//				terminal = reqBean.getTerminal();
//				vehicle = reqBean.getVehicle();
//				if(sendXml(appbean, terminal, vehicle)){
//					log.info("SendUpdateThread::通知平台成功");
//				}else{
//					try {
//						Thread.sleep(1);
//						log.info("SendUpdateThread:::通知平台失败，等待"+l+"秒继续进行通知");
//					} catch (InterruptedException e) {
//						continue;
//					}
//				}	
//			}
//		}
	}

	public boolean sendXml(AppBean appbean, int terminal, int vehicle) {
		try {
			URL url = new URL(appbean.getSend_path());
			HttpURLConnection con = initeConnection(url);
			sendXml(con, AddXml(appbean, terminal, vehicle));
			StringBuffer strReceive = new StringBuffer();
			return receiveXml(con, strReceive, terminal, vehicle);
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}

	private AppBean getAppBean() {
		AppDAO appDAO = (AppDAO) SpringBootStrap.getInstance()
				.getBean("appDAO");
		return appDAO.getAppCfg("20");
	}

	private boolean receiveXml(HttpURLConnection con, StringBuffer strReceive,
			int terminal, int vehicle) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));

			String strCurrentLine;
			OlxDocument doc;
			while ((strCurrentLine = reader.readLine()) != null) {
				strReceive.append(strCurrentLine + "\n");
			}
			doc = OlxDocument.Factory.parse(strReceive.toString());
			if (parseResponse(reqBean, doc)) {
				reqBean.setTerminalAndVehicle("terminal", -terminal);
				reqBean.setTerminalAndVehicle("vehicle", -vehicle);
			}
			return true;
		} catch (Exception e) {
			log.error("receiveXml()产生异常" + e.getMessage());
			return false;
		}
	}

	public static boolean parseResponse(final RequestBean reqBean,
			final OlxDocument xmlDoc) throws InvalidXmlFormatException,
			Exception {

		// 1. olx节点, Dir和Version是必选属性
		Olx olx = xmlDoc.getOlx();
		if (null == olx || null == olx.getDir() || null == olx.getVersion()) {
			throw new InvalidXmlFormatException("olx节点为空或其属性值错误");
		}
		// 2. function节点, name和seq是必选属性
		Function function = olx.getFunction();
		Result result = function.getResult();
		TreeObject treeobject = result.getTreeObject();
		if (treeobject == null || treeobject.getVersion() == null) {
			throw new InvalidXmlFormatException("tree-object节点为空或其属性值错误");
		}
		Value[] valueArray = treeobject.getValueArray();
		for (Value value : valueArray) {
			if (value.getKey().equalsIgnoreCase("c")
					&& value.getClass1().toString().toLowerCase().equals("int")) {
				if (value.getValue().equals("0")) {
					return true;
				}
			}
		}
		return false;
	}

	private String AddXml(AppBean appbean, int terminal, int vehicle) {
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
		setNewValue(tree.addNewValue(), "AppId", str, appbean.getApp_id());
		setNewValue(tree.addNewValue(), "PASS", bstr, Base64.encode(appbean
				.getCore_pass(), "UTF-8"));
		if (vehicle != 0) {
			setNewValue(tree.addNewValue(), "vehicle", str, "1");
		} else {
			setNewValue(tree.addNewValue(), "vehicle", str, "0");
		}
		if (terminal != 0) {
			setNewValue(tree.addNewValue(), "terminal", str, "1");
		} else {
			setNewValue(tree.addNewValue(), "terminal", str, "0");
		}
//		System.out.println(doc.xmlText());
		return doc.xmlText();
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
		connection.setRequestProperty("POST", "/ HTTP/1.1");
		connection.setRequestProperty("Cache-Control", "no-cache");
		connection.setRequestProperty("x-protocol-version", "0.0.1");
		connection.setRequestProperty("x-service-type", "clw:01/1.0");
		OutputStreamWriter out = new OutputStreamWriter(connection
				.getOutputStream());
		out.write(strXml);
		out.flush();
		out.close();
	}

	private void setNewValue(Value value, String strKey, String strClas,
			String objValue) {
		value.setKey(strKey);
		value.setClass1(Value.Class.Enum.forString(strClas));
		// String strValue = String.valueOf(objValue);
		value.setValue(objValue);
	}

}

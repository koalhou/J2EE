package com.yutong.axxc.tqc.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.HttpConstant;
import com.yutong.axxc.tqc.entity.homePage.WeatherInfo;
import com.yutong.axxc.tqc.entity.homePage.WeatherInfoResp;
import com.yutong.axxc.tqc.exception.common.ApplicationException;

public class WeatherReport {
	/**
	 * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 客户将要发送给服务器的SOAP请求
	 */
	private static String getSoapRequest(String city) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
				+ "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
				+ "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "<soap:Body>    <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">"
				+ "<theCityName>" + city
				+ "</theCityName>    </getWeatherbyCityName>"
				+ "</soap:Body></soap:Envelope>");
		return sb.toString();
	}

	/**
	 * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
	 * 
	 
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 服务器端返回的输入流，供客户端读取
	 * @throws Exception
	 */
	private static InputStream getSoapInputStream(String city) throws Exception {
		String soap = getSoapRequest(city);
		if (soap == null) {
			return null;
		}

		URL url = new URL(
				"http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
		URLConnection conn = url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);

		conn.setRequestProperty("Content-Length",
				Integer.toString(soap.length()));
		conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		conn.setRequestProperty("SOAPAction",
				"http://WebXml.com.cn/getWeatherbyCityName");

		conn.setReadTimeout(1500);

		OutputStream os = conn.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
		osw.write(soap);
		osw.flush();
		osw.close();

		InputStream is = conn.getInputStream();
		return is;
	}

	/**
	 * 对服务器端返回的XML进行解析
	 * 
	 
	 * 
	 * @param city
	 *            用户输入的城市名称
	 * @return 字符串 用,分割
	 * @throws Exception
	 */
	public static String getWeather(String city) throws Exception {
		Document doc;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputStream is = getSoapInputStream(city);
		doc = db.parse(is);
		NodeList nl = doc.getElementsByTagName("string");
		StringBuffer sb = new StringBuffer();
		for (int count = 0; count < nl.getLength(); count++) {
			Node n = nl.item(count);
			if (n.getFirstChild().getNodeValue().equals("访问被限制！")) {
				sb = new StringBuffer("访问被限制！");
				break;
			} else if (n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
				sb = new StringBuffer("查询结果为空！");
				break;
			} else {
				sb.append(n.getFirstChild().getNodeValue() + "#\n");
			}
		}
		is.close();
		return sb.toString();
	}

	private static Logger logger = LoggerFactory.getLogger(WeatherReport.class);

	/**
	 * 获取天气信息
	 * 
	 * @param region
	 *            城市
	 * @return
	 */
	public static WeatherInfoResp getWeatherInfo(String region) {
		logger.debug("获取{}的天气",region);
		WeatherInfoResp iWeatherInfoResp = new WeatherInfoResp();
		List<WeatherInfo> list = new ArrayList<WeatherInfo>();

		String w = null;
		try {
			w = WeatherReport.getWeather(region);
		} catch (Exception e) {
			logger.error("获取天气信息时发生异常" + e.getMessage());
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		}

		if (StringUtils.isEmpty(w)) {
			logger.error("获取到的天气信息为空");
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		} else if (StringUtils.equals(w, "访问被限制！")) {
			logger.error("连接到气象信息服务的请求被限制，可能的原因为IP请求的数量24小时内超过50次或600ms内发送多次请求");
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		} else if (StringUtils.equals(w, "查询结果为空！")) {
			logger.error("连接到气象信息服务后返回的应答为查询结果为空");
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		}

		String[] weather = StringUtils.split(w, '#');

		if (weather.length != 24) {
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		}

		for (int i = 0; i < weather.length; i++) {
			weather[i] = StringUtils.strip(weather[i]);
		}
		weather[8] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[8]);
		weather[9] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[9]);
		weather[15] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[15]);
		weather[16] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[16]);
		weather[20] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[20]);
		weather[21] = HttpConstant.WEATHER_IMG_PATH + '/'
				+ StringUtils.strip(weather[21]);

		logger.debug("本次气象更新时间：" + weather[4]);
		try {
			Date weatherTime = TimeUtil.parseStringToDate(weather[4],
					HttpConstant.WEATHER_TIME_FORMAT);

			iWeatherInfoResp.setTime(TimeUtil.formatDateToString(weatherTime,
					HttpConstant.TIME_FORMAT));
		} catch (ParseException e) {
			logger.error("气象信息更新时间格式化异常" + e.getMessage());
			throw new ApplicationException(ErrorConstant.ERROR10011,
					Response.Status.FORBIDDEN);
		}
		String[] ss = (String[]) ArrayUtils.subarray(weather, 5, 10);

		WeatherInfo iWeatherInfo = new WeatherInfo();
		iWeatherInfo.setTemper(ss[0]);
		iWeatherInfo.setDate(ss[1].split(" ")[0]);
		iWeatherInfo.setDesc(ss[1].split(" ")[1]);
		iWeatherInfo.setWind(ss[2]);
		iWeatherInfo.setImgOne(ss[3]);
		iWeatherInfo.setImgTwo(ss[4]);
		list.add(iWeatherInfo);

//		ss = (String[]) ArrayUtils.subarray(weather, 12, 17);
//
//		iWeatherInfo = new WeatherInfo();
//		iWeatherInfo.setTemper(ss[0]);
//		iWeatherInfo.setDate(ss[1].split(" ")[0]);
//		iWeatherInfo.setDesc(ss[1].split(" ")[1]);
//		iWeatherInfo.setWind(ss[2]);
//		iWeatherInfo.setImgOne(ss[3]);
//		iWeatherInfo.setImgTwo(ss[4]);
//		list.add(iWeatherInfo);
//
//		ss = (String[]) ArrayUtils.subarray(weather, 17, 22);
//
//		iWeatherInfo = new WeatherInfo();
//		iWeatherInfo.setTemper(ss[0]);
//		iWeatherInfo.setDate(ss[1].split(" ")[0]);
//		iWeatherInfo.setDesc(ss[1].split(" ")[1]);
//		iWeatherInfo.setWind(ss[2]);
//		iWeatherInfo.setImgOne(ss[3]);
//		iWeatherInfo.setImgTwo(ss[4]);
//		list.add(iWeatherInfo);

		iWeatherInfoResp.setInfo(list);
		return iWeatherInfoResp;
	}

	/**
	 * 测试用
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(getWeather("沈阳"));
	}
}

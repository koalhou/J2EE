package com.yutong.clw.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.utils.XMLConfig;

public class SearchGisAreaByCode {

	private static String url = "http://search1.mapabc.com/sisserver";
	private static String key = "1c1f088154552328dd9927181a2616b6a62971c7a9db6d6a36bea17f5adf2413fc7c61ccabe6a62f";
	private static final Logger log = LoggerFactory.getLogger(SearchGisAreaByCode.class);
	public static String getAddressInfoFrPoint(String lng, String lat, int range,int poiNumber, int pattern) {
		String result = "";
		try {
			String spatialXml = "<?xml version=\"1.0\" encoding=\"GB2312\"?><spatial_request method=\"searchPoint\">"
					+ "<x>"
					+ lng
					+ "</x> <y>"
					+ lat
					+ "</y><poiNumber>"
					+ poiNumber
					+ "</poiNumber>"
					+ "<range>"
					+ range
					+ "</range><pattern>"
					+ pattern
					+ "</pattern>"
					+ "<roadlevel>1</roadlevel>" + "</spatial_request>";
			spatialXml = URLEncoder.encode(spatialXml, "utf-8");
			String param = "&config=SPAS&enc=gb2312&resType=xml&spatialXml="
					+ spatialXml + "&a_k=" + key;
			result = getDataFrUrlGet(param);
			System.out.println(result);
		} catch (IOException oe) {
			oe.printStackTrace();
			return "";
		}
		return result;
	}
	public static String getZoneNameByPosition(String longitude, String latidude)
	{
		String xmlString = getAddressInfoFrPoint(longitude,latidude, 0, 3000, 10);
		if(null == xmlString || "".equals(xmlString)){
			return "定位无效";
		}
		XMLConfig cfg = new XMLConfig();
		StringBuffer sb=new StringBuffer();
		if (cfg.loadConfigByXmlString(xmlString)) {
			String province=(cfg.getValueMap("/spatial_response/SpatialBean/Province")).get("name").toString().trim();
			String city=(cfg.getValueMap("/spatial_response/SpatialBean/City")).get("name").toString().trim();
			String district=(cfg.getValueMap("/spatial_response/SpatialBean/District")).get("name").toString().trim();
			if("".equals(province)||"".equals(city)||"".equals(district)){
				return "";
			}
			String road="",poiName="",poiDirection="",poiSum;
			int poiDistance=0;
			List<Map> listRoad = (cfg.getMapList("/spatial_response/SpatialBean/roadList/Road"));
			/*for(Map m:listRoad)
			{
				road+=m.get("name");
			}*/
			if(listRoad!=null&&listRoad.size()>0){
				road=((Map)listRoad.get(0)).get("name").toString();
			}
			//不显示具体实物地点
			List<Map> listPoi = (cfg.getMapList("/spatial_response/SpatialBean/poiList/poi"));
			/*for(Map m:listPoi)
			{
				poiName+=m.get("name");
			}*/
			if(listPoi!=null&&listPoi.size()>0){
				poiName=((Map)listPoi.get(0)).get("name").toString();
				poiDirection=((Map)listPoi.get(0)).get("direction").toString().trim();
				poiDistance=(int)(Math.floor(Double.valueOf(((Map)listPoi.get(0)).get("distance").toString())));				
			}
			poiSum="（"+getChineseDirection(poiDirection)+poiDistance+"米处"+poiName+"）";
			return sb.append(province).append(city).append(district).append(road).append(poiSum).toString();
		}
		return "";
	}
	private static String getDataFrUrlGet(String param) {
		try {
			URL u = new URL(url + "?" + param);
			URLConnection urlc = u.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) urlc;
			httpConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					httpConnection.getInputStream(), "gb2312"));
			String line = null;
			StringBuffer sb = new StringBuffer("");
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return "";
	}

	public String getZoneNameByXmlString(String xmlString)
	{
		XMLConfig cfg = new XMLConfig();
		StringBuffer sb=new StringBuffer();
		if (cfg.loadConfigByXmlString(xmlString)) {
			String province=(cfg.getValueMap("/spatial_response/SpatialBean/Province")).get("name").toString().trim();
			String city=(cfg.getValueMap("/spatial_response/SpatialBean/City")).get("name").toString().trim();
			String district=(cfg.getValueMap("/spatial_response/SpatialBean/District")).get("name").toString().trim();
			String road="",poiName="",poiDirection="",poiSum;
			int poiDistance=0;
			List<Map> listRoad = (cfg.getMapList("/spatial_response/SpatialBean/roadList/Road"));
			/*for(Map m:listRoad)
			{
				road+=m.get("name");
			}*/
			if(listRoad!=null&&listRoad.size()>0){
				road=((Map)listRoad.get(0)).get("name").toString();
			}
			//不显示具体实物地点
			List<Map> listPoi = (cfg.getMapList("/spatial_response/SpatialBean/poiList/poi"));
			/*for(Map m:listPoi)
			{
				poiName+=m.get("name");
			}*/
			if(listPoi!=null&&listPoi.size()>0){
				poiName=((Map)listPoi.get(0)).get("name").toString();
				poiDirection=((Map)listPoi.get(0)).get("direction").toString().trim();
				poiDistance=(int)(Math.floor(Double.valueOf(((Map)listPoi.get(0)).get("distance").toString())));				
			}
			poiSum="（"+getChineseDirection(poiDirection)+poiDistance+"米处"+poiName+"）";		
			if(poiDistance==0){				
				poiSum="";	
			}
			return sb.append(province).append(city).append(district).append(road).append(poiSum).toString();
		}
		return "";
	}
	
	public String reflectGetZoneNameByXmlString(SearchGisAreaByCode sgabc,String lon,String lat)
	{
		String xmlString=sgabc.getAddressInfoFrPoint(lon, lat, 0, 3000, 10);
		XMLConfig cfg = new XMLConfig();
		StringBuffer sb=new StringBuffer();
		if (cfg.loadConfigByXmlString(xmlString)) {
			String province=(cfg.getValueMap("/spatial_response/SpatialBean/Province")).get("name").toString().trim();
			String city=(cfg.getValueMap("/spatial_response/SpatialBean/City")).get("name").toString().trim();
			String district=(cfg.getValueMap("/spatial_response/SpatialBean/District")).get("name").toString().trim();
			String road=(cfg.getValueMap("/spatial_response/SpatialBean/roadList/Road")).get("name").toString().trim();
			String poi=(cfg.getValueMap("/spatial_response/SpatialBean/poiList/poi")).get("address").toString().trim();
			 return sb.append(province).append(city).append(district).append(road).append(poi).toString();
		}
		return "";
	}
	
	//根据英文方向获得方向中文方向名称
	public static String getChineseDirection(String engDirection){
		String chinaDirection="";
		//东南方
		if("SouthEast".equals(engDirection)){
			chinaDirection="东南方";			
		}
		//西南方
		else if("SouthWest".equals(engDirection)){
			chinaDirection="西南方";	
		}
		//西北方
		else if("NorthWest".equals(engDirection)){
			chinaDirection="西北方";
		}
		//东北方
		else if("NorthEast".equals(engDirection)){
			chinaDirection="东北方";
		}
		//正东方
		if("East".equals(engDirection)){
			chinaDirection="正东方";			
		}		
		//正西方
		else if("West".equals(engDirection)){
			chinaDirection="正西方";	
		}
		//正南方
		else if("South".equals(engDirection)){
			chinaDirection="正南方";
		}
		//正北方
		else if("North".equals(engDirection)){
			chinaDirection="正北方";
		}		
		return chinaDirection;
	}
	
	public static void main(String[] args) {
		
		SearchGisAreaByCode sgabc=	new SearchGisAreaByCode();
		//String xmlString = sgabc.getAddressInfoFrPoint("", uhc.getLati, 0, 3000, 10);
		String xmlString = sgabc.getAddressInfoFrPoint("113.68695","34.690745", 0, 3000, 10);
		if(!xmlString.equals("")){
			String zoneName=sgabc.getZoneNameByXmlString(xmlString);
			//uhc.setZonename(zoneName);
			System.out.println("---------->>:"+zoneName);
		}
	}
	
}

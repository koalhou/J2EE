package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.ClwTqcEmpPreline;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.check.domain.SimpleRecord;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.areamanage.domain.AreaInfo;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.yw.ftly.ds.ZsptFtlyInfo;
import com.neusoft.item.RgcResultDocument;
import com.neusoft.item.RgcResultDocument.RgcResult;
import com.neusoft.item.RgcResultDocument.RgcResult.Item;
import com.neusoft.yt.nidizhi.SearchresultDocument;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial;
import com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial.Roadlist.Road;

public class gpsUtil extends PaginationAction {
    /**
     * 日志
     */
    protected final Logger log = Logger.getLogger(getClass());

    public List < TerminalViBean > getpost(List < TerminalViBean > list) {

        List < TerminalViBean > linshilist = new ArrayList();

        StringBuilder sb = new StringBuilder();
        // Map map = new HashMap();
        // sb.append(str);
        if (list != null && list.size() > 0) {

            // if(list.get(list.size()-1).getID() != null &&
            // !list.get(list.size()-1).getID().equals("")){

            for (int i = 0; i < list.size(); i++) {
                TerminalViBean TerminalViBean = list.get(i);
                TerminalViBean.getID();
                String lon = TerminalViBean.getLONGITUDE();
                String lat = TerminalViBean.getLATITUDE();

                if (lon != null && lon != "" && lat != null && lat != ""
                        && !lon.equals("FFFF") && !lat.equals("FFFF")) {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");

                    }
                    linshilist.add(TerminalViBean);
                    // map.put(i, TerminalViBean);
                }
            }
            log.info("请求的GPS数据：" + sb.toString());
            if (sb != null && !sb.toString().equals("")) {

                String[] str = getXml(sb.toString());
                if (str != null && str.length > 0) {

                    for (int i = 0; i < str.length; i++) {
                        String x = str[i].split(",")[0];
                        String y = str[i].split(",")[1];

                        linshilist.get(i).setLONGITUDE(x);
                        linshilist.get(i).setLATITUDE(y);

                        for (int j = 0; j < list.size(); j++) {

                            if (list.get(j).getID() != null
                                    && list.get(j).getID().equals(
                                            linshilist.get(i).getID())) {
                                list.get(j).setLONGITUDE(
                                        linshilist.get(i).getLONGITUDE());
                                list.get(j).setLATITUDE(
                                        linshilist.get(i).getLATITUDE());
                                list.get(j).setGpsIsExc(true);

                            }
                        }
                    }
                    list.get(0).setGpsIsExc(true);
                } else {
                    log.info("偏移后的GPS数据数组长度为0");
                    list.get(0).setGpsIsExc(false);
                }
            } else {
                log.info("无合法GPS数据进行请求");
                list.get(0).setGpsIsExc(true);
            }
            // }

            return list;

        } else {
            log.info(sb.toString());
            return list;

        }

    }
    public List<ClwTqcEmpPreline> getpostEmpPre(List<ClwTqcEmpPreline> list) {
        StringBuilder sb = new StringBuilder();
        // Map map = new HashMap();
        // sb.append(str);
        if (list != null && list.size() > 0) {
            // if(list.get(list.size()-1).getID() != null &&
            // !list.get(list.size()-1).getID().equals("")){

            for (int i = 0; i < list.size(); i++) {
            	ClwTqcEmpPreline TerminalViBean = list.get(i);
                String lon = TerminalViBean.getLongitude();
                String lat = TerminalViBean.getLatitude();

                if (lon != null && lon != "" && lat != null && lat != "" && !lon.equals("FFFF") && !lat.equals("FFFF")) {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");

                    }
                    // map.put(i, TerminalViBean);
                }
            }
            log.info("请求的GPS数据：" + sb.toString());
            if (sb != null && !sb.toString().equals("")) {

                String[] str = getXml(sb.toString());
                if (str != null && str.length > 0) {
                    for (int i = 0; i < str.length; i++) {
                        String x = str[i].split(",")[0];
                        String y = str[i].split(",")[1];
                        list.get(i).setLongitude(x);
                        list.get(i).setLatitude(y);
                        list.get(i).setGpsIsExc(true);
                    }
                    list.get(0).setGpsIsExc(true);
                } else {
                    log.info("偏移后的GPS数据数组长度为0");
                    list.get(0).setGpsIsExc(false);
                }
            } else {
                log.info("无合法GPS数据进行请求");
                list.get(0).setGpsIsExc(true);
            }
            return list;
        } else {
            return list;
        }
    }
    
    public List < ZsptFtlyInfo > getpostFtly(List < ZsptFtlyInfo > list) {
    	//List newlist = new ArrayList();
        List < ZsptFtlyInfo > linshilist = new ArrayList();

        StringBuilder sb = new StringBuilder();
        // Map map = new HashMap();
        // sb.append(str);
        if (list != null && list.size() > 0) {

            // if(list.get(list.size()-1).getID() != null &&
            // !list.get(list.size()-1).getID().equals("")){

            for (int i = 0; i < list.size(); i++) {
            	ZsptFtlyInfo asptFtlyInfo = list.get(i);
            	asptFtlyInfo.getFtylyIdNum();
                String lon = asptFtlyInfo.getLongitude();
                String lat = asptFtlyInfo.getLatitude();
                String oilBox = asptFtlyInfo.getOilboxState();

                if (lon != null && lon != "" && lat != null && lat != ""
                        && !lon.equals("FFFF") && !lat.equals("FFFF")) {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");

                    }
                    linshilist.add(asptFtlyInfo);
                    // map.put(i, TerminalViBean);
                }
            }
            log.info("请求的GPS数据：" + sb.toString());
            if (sb != null && !sb.toString().equals("")) {

                String[] str = getXml(sb.toString());
                if (str != null && str.length > 0) {

                    for (int i = 0; i < str.length; i++) {
                        String x = str[i].split(",")[0];
                        String y = str[i].split(",")[1];

                        linshilist.get(i).setLongitude(x);
                        linshilist.get(i).setLatitude(y);

                        for (int j = 0; j < list.size(); j++) {

                            if (list.get(j).getFtylyIdNum() != null
                                    && list.get(j).getFtylyIdNum().equals(
                                            linshilist.get(i).getFtylyIdNum())) {
                                list.get(j).setLongitude(
                                        linshilist.get(i).getLongitude());
                                list.get(j).setLatitude(
                                        linshilist.get(i).getLatitude());
//                                list.get(j).setGpsIsExc(true);
                            }
                        }
                    }
                    //list.get(0).setGpsIsExc(true);
                } else {
                    log.info("偏移后的GPS数据数组长度为0");
                    //list.get(0).setGpsIsExc(false);
                }
            } else {
                log.info("无合法GPS数据进行请求");
                //list.get(0).setGpsIsExc(true);
            }
            // }
            
            return list;

        } else {
            log.info(sb.toString());
            return list;

        }

    }
    
    
    /*
     * 获得一个点的偏移
     */
    public String getOneXY(String lon, String lat) {
        StringBuilder sb = new StringBuilder();

        if (lon != null && lon != "" && lat != null && lat != ""
                && !lon.equals("FFFF") && !lat.equals("FFFF")) {
            sb.append(lon);
            sb.append(",");
            sb.append(lat);

            if (sb != null && !sb.toString().equals("")) {

                String[] str = getXml(sb.toString());

                if (str != null && str.length > 0) {

                    return str[0].toString();
                }
            }

        }
        return null;

    }
    public static void main(String[] args) {
		gpsUtil saa = new gpsUtil();
		String str = saa.getOneXY("120.638691","31.178836");
		System.out.println(str);
		
//		String str1 = saa.getOneXY("113.719649","34.739033");
//		System.out.println(str1);
	}
    public List<AreaInfo> checkLonLat(List<AreaInfo> titleList) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < titleList.size(); i++) {
    		AreaInfo ai = titleList.get(i);
            String lon = ai.getSitelon();
            String lat = ai.getSitelat();

            sb.append(lon);
            sb.append(",");
            sb.append(lat);
            if (i != titleList.size() - 1) {
                sb.append(";");
            }
        }
    	
    	String[] str = getXml(sb.toString());
    	for (int i = 0; i < titleList.size(); i++) {
    		AreaInfo ai = titleList.get(i);
            ai.setSitelon(str[i].split(",")[0]);
            ai.setSitelat(str[i].split(",")[1]);
        }
    	return titleList;
    }
    private String[] getXml(String points) {

        try {
            PostMethod postMethodProxyRequest = new PostMethod(
                    "http://search1.mapabc.com/sisserver");

            // Header header3 = new Header("Proxy-Authorization",
            // "Basic d196aGU6MXEydzNlNHJAd2FuZ1pIRQ==");// 上线删除
            // Basic Y2hlbmcuajpKSU4uY2hlbmcyMDE4

            // postMethodProxyRequest.setRequestHeader(header3);// 上线删除

            postMethodProxyRequest.addParameter("config", "RGC");
            postMethodProxyRequest.addParameter("resType", "xml");
            postMethodProxyRequest.addParameter("coors", points);
            postMethodProxyRequest.addParameter("cr", "0");
            // postMethodProxyRequest.addParameter("ver", "2.0");
            postMethodProxyRequest.addParameter("flag", "true");
            postMethodProxyRequest.addParameter("a_k", getText("map.key"));
            // postMethodProxyRequest.addParameter("a_k",
            // "f6c97a7f64063cfee7c2dc2157847204d4dbf0938d58820f89940a1a68832c0cdd53109727cfa622");
            log.info("map key is ::" + getText("map.key"));
            HttpClient httpClient = new HttpClient();

            // httpClient.getHostConfiguration().setProxy("proxy.neusoft.com",
            // 8080);// 上线删除
            httpClient.getParams().setContentCharset("UTF-8");

            int app = httpClient.executeMethod(postMethodProxyRequest);
            // System.out.println(postMethodProxyRequest
            // .getResponseHeader("x-res-code"));
            String s = postMethodProxyRequest.getResponseBodyAsString();
            log.info("偏移应答码：" + app);
            //log.info("偏移后的应答信息：" + s);

            if (app == 200) {

                if (s != null && !s.equals("")) {

                    RgcResultDocument td = RgcResultDocument.Factory.parse(s);
                    RgcResult rr = td.getRgcResult();

                    Item[] items = rr.getItemArray();

                    if (items != null && items.length > 0) {

                        String[] str = new String[items.length];

                        for (int i = 0; i < items.length; i++) {

                            str[i] = items[i].getX() + "," + items[i].getY();

                        }
                        return str;
                    } else {
                        return null;
                    }

                } else {
                    return null;
                }

            } else {
                return null;

            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            log.info("GPS偏移异常数据：" + e.getMessage());
            return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.info("GPS偏移异常数据：" + e.getMessage());
            return null;
        }

    }

    /**
     * 地理位置坐标点偏转
     * @param list
     * @return
     */
    public List < AlarmManage > getAlarmPosition(List < AlarmManage > list) {
        List < AlarmManage > linshilist = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                AlarmManage alarmmanage = list.get(i);
                String lon = alarmmanage.getLongitude();
                String lat = alarmmanage.getLatitude();
                if (lon != null && lon != "" && lat != null && lat != ""
                        && !lon.equals("FFFF") && !lat.equals("FFFF")) {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");
                    }
                    linshilist.add(alarmmanage);
                }
            }
            log.info("告警坐标点:" + sb.toString());
            if (sb != null && !sb.toString().equals("")) {
                String[] str = getXml(sb.toString());
                if (str != null && str.length > 0) {
                    for (int i = 0; i < str.length; i++) {
                        String x = str[i].split(",")[0];
                        String y = str[i].split(",")[1];
                        linshilist.get(i).setLongitude(x);
                        linshilist.get(i).setLatitude(y);
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j).getAlarm_id() != null
                                    && list.get(j).getAlarm_id().equals(
                                            linshilist.get(i).getAlarm_id())) {
                                list.get(j).setLongitude(
                                        linshilist.get(i).getLongitude());
                                list.get(j).setLatitude(
                                        linshilist.get(i).getLatitude());
                            }
                        }
                    }
                } else {
                    log.info("偏移后的告警坐标数据数组长度为0");
                }
            } else {
                log.info("无合法告警坐标数据进行请求");
            }
            return list;
        } else {
            log.info(sb.toString());
            return list;
        }
    }
    
    /**
     * 根据坐标返回地理信息
     * @param points
     * @return
     */
    private String getAddressinfo(String points){
    	String result="";
    	
    	try {
			PostMethod postMethodProxyRequest = new PostMethod(
					"http://apis.mapabc.com/rgeocode/simple");

//			Header header3 = new Header("Proxy-Authorization",
//					"Basic Y2hlbmcuajpHWm5ldS4yMDE1");//上线屏蔽
//			postMethodProxyRequest.setRequestHeader(header3);//上线屏蔽

			postMethodProxyRequest.addParameter("sid", "7001");
			postMethodProxyRequest.addParameter("resType", "xml");
			postMethodProxyRequest.addParameter("encode", "UTF-8");
			postMethodProxyRequest.addParameter("key", getText("map.key"));
			postMethodProxyRequest.addParameter("region", points);
			//postMethodProxyRequest.addParameter("range", "50");
			postMethodProxyRequest.addParameter("roadnum", "1");
			postMethodProxyRequest.addParameter("crossnum", "0");
			postMethodProxyRequest.addParameter("poinum", "0");
			postMethodProxyRequest.addParameter("show_near_districts", "false");
			
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);
//			httpClient.getHostConfiguration().setProxy("proxy.neusoft.com",
//					8080);//上线屏蔽
			httpClient.getParams().setContentCharset("UTF-8");

			int app = httpClient.executeMethod(postMethodProxyRequest);
			
			String s = postMethodProxyRequest.getResponseBodyAsString();
			log.info("逆地址查询应答码：" + app+";逆地址查询结果:"+s);
	        if(app==200){
	        	SearchresultDocument srdoc=SearchresultDocument.Factory.parse(s);
				Searchresult seares=srdoc.getSearchresult();
				if(null!=seares.getList()){
					if(null!=seares.getList().getSpatialArray()){
						for (int i=0;i<seares.getList().getSpatialArray().length;i++){
							Spatial sp=seares.getList().getSpatialArray(i);
							if(null!=sp.getRoadlist()){
								if (null!=sp.getRoadlist().getRoadArray() && sp.getRoadlist().getRoadArray().length>0){
									Road rd=sp.getRoadlist().getRoadArray(0);
									if(null!=rd){
										result=result+rd.getName()+"附近,";
									}else{
										result=result+" ,";
									}
								}else{
									result=result+" "+",";
								}
							}else{
								result=result+" "+",";
							}
							
						}
					}
					
				}else{
					
				}
	        }

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			log.error("获取逆地址查询时出错", e);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("获取逆地址查询时出错", e);

		}
		return result;
    }
    
    /**
     * 告警地理位置批量获取
     * @param list
     * @return
     */
    public List < AlarmManage > getAlarmAddress(List < AlarmManage > list) {
    	log.info("批量获取地理位置开始");
        List < AlarmManage > linshilist = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                AlarmManage alarmmanage = list.get(i);
                String lon = alarmmanage.getLongitude();
                String lat = alarmmanage.getLatitude();
                if (lon != null && lon != "" && lat != null && lat != ""
                        && !lon.equals("FFFF") && !lat.equals("FFFF")) {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");
                    }
                    linshilist.add(alarmmanage);
                }
            }
            log.info("告警坐标点:" + sb.toString());
            if (sb != null && !sb.toString().equals("")) {
                String backresult = getAddressinfo(sb.toString());
                if (null!=backresult && !backresult.equals("")) {
                	String[] items=backresult.split(",");
                    for (int i = 0; i < items.length; i++) {
                        String x = items[i];
                        linshilist.get(i).setAlarm_address(x);
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j).getAlarm_id() != null
                                    && list.get(j).getAlarm_id().equals(
                                            linshilist.get(i).getAlarm_id())) {
                                list.get(j).setAlarm_address(linshilist.get(i).getAlarm_address());
                            }
                        }
                    }
                } else {
                    log.info("无返回地址");
                }
            } else {
                log.info("无合法告警坐标数据进行请求");
            }
            log.info("批量获取地理位置结束");
            return list;
        } else {
        	log.info("批量获取地理位置结束");
            return list;
        }
    }
    /**
     * 偏移计算（本偏移针对简单的轨迹回放）
     */
    public void getMapLonLat(List<SimpleRecord> list){
    	StringBuffer sb=new StringBuffer();
    	for(SimpleRecord r:list){
    		String lon=r.getLongitude();
    		String lat=r.getLatitude();
    		if(lon!=null && !"".equals(lon) && !"FFFF".equals(lon) && lat!=null && !"".equals(lat) && !"FFFF".equals(lat)){
    			sb.append(r.getLongitude()+","+r.getLatitude()+";");
    		}
    	}
    	String[] str = getXml(sb.toString());
    	if(str==null){
    		return;
    	}
    	for(int i=0;i<str.length;i++){
    		String lonlat=str[i];
    		String[] ll=lonlat.split(",");
    		String lon=ll[0];
    		String lat=ll[1];
    		if(lon!=null && !"".equals(lon) && !"FFFF".equals(lon) && lat!=null && !"".equals(lat) && !"FFFF".equals(lat)){
        		SimpleRecord r=list.get(i);
        		r.setLongitude(lon);
        		r.setLatitude(lat);
        		r.setGpsIsExc(true);
    		}
    		
    	}
    }
}

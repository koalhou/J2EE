package com.neusoft.clw.yw.sitemanager.action;

//import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.sitemanager.ds.TerminalViBean;
import com.neusoft.item.RgcResultDocument;

public class gpsUtil extends PaginationAction {

	public gpsUtil() {
	}

	public List getpost(List list) {
		List linshilist = new ArrayList();
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				TerminalViBean TerminalViBean = (TerminalViBean) list.get(i);
				TerminalViBean.getID();
				String lon = TerminalViBean.getLONGITUDE();
				String lat = TerminalViBean.getLATITUDE();
				if (lon != null && lon != "" && lat != null && lat != ""
						&& !lon.equals("FFFF") && !lat.equals("FFFF")) {
					sb.append(lon);
					sb.append(",");
					sb.append(lat);
					if (i != list.size() - 1)
						sb.append(";");
					linshilist.add(TerminalViBean);
				}
			}

			log.info((new StringBuilder("请求的GPS数据：")).append(sb.toString())
					.toString());
			if (sb != null && !sb.toString().equals("")) {
				String str[] = getXml(sb.toString());
				if (str != null && str.length > 0) {
					for (int i = 0; i < str.length; i++) {
						String x = str[i].split(",")[0];
						String y = str[i].split(",")[1];
						((TerminalViBean) linshilist.get(i)).setLONGITUDE(x);
						((TerminalViBean) linshilist.get(i)).setLATITUDE(y);
						for (int j = 0; j < list.size(); j++)
							if (((TerminalViBean) list.get(j)).getID() != null
									&& ((TerminalViBean) list.get(j))
											.getID()
											.equals(((TerminalViBean) linshilist
													.get(i)).getID())) {
								((TerminalViBean) list.get(j))
										.setLONGITUDE(((TerminalViBean) linshilist
												.get(i)).getLONGITUDE());
								((TerminalViBean) list.get(j))
										.setLATITUDE(((TerminalViBean) linshilist
												.get(i)).getLATITUDE());
								((TerminalViBean) list.get(j))
										.setGpsIsExc(true);
							}

					}

					((TerminalViBean) list.get(0)).setGpsIsExc(true);
				} else {
					log.info("偏移后的GPS数据数组长度为0");
					((TerminalViBean) list.get(0)).setGpsIsExc(false);
				}
			} else {
				log.info("无合法GPS数据进行请求");
				((TerminalViBean) list.get(0)).setGpsIsExc(true);
			}
			return list;
		} else {
			log.info(sb.toString());
			return list;
		}
	}

	public String getOneXY(String lon, String lat) {
		StringBuilder sb = new StringBuilder();
		if (lon != null && lon != "" && lat != null && lat != ""
				&& !lon.equals("FFFF") && !lat.equals("FFFF")) {
			sb.append(lon);
			sb.append(",");
			sb.append(lat);
			if (sb != null && !sb.toString().equals("")) {
				String str[] = getXml(sb.toString());
				if (str != null && str.length > 0)
					return str[0].toString();
			}
		}
		return null;
	}

	private String[] getXml(String points) {
		label0: {
			label1: {
				try {
					PostMethod postMethodProxyRequest = new PostMethod(
							"http://search1.mapabc.com/sisserver");
					postMethodProxyRequest.addParameter("config", "RGC");
					postMethodProxyRequest.addParameter("resType", "xml");
					postMethodProxyRequest.addParameter("coors", points);
					postMethodProxyRequest.addParameter("cr", "0");
					postMethodProxyRequest.addParameter("flag", "true");
					postMethodProxyRequest.addParameter("a_k",
							getText("map.key"));
					log.info((new StringBuilder("map key is ::")).append(
							getText("map.key")).toString());
					HttpClient httpClient = new HttpClient();
					httpClient.getParams().setContentCharset("UTF-8");
					int app = httpClient.executeMethod(postMethodProxyRequest);
					String s = postMethodProxyRequest.getResponseBodyAsString();
					log.info((new StringBuilder("偏移应答码：")).append(app)
							.toString());
					log.info((new StringBuilder("偏移后的应答信息：")).append(s)
							.toString());
					if (app != 200)
						break label0;
					if (s == null || s.equals(""))
						break label1;
					RgcResultDocument td = com.neusoft.item.RgcResultDocument.Factory
							.parse(s);
					com.neusoft.item.RgcResultDocument.RgcResult rr = td
							.getRgcResult();
					com.neusoft.item.RgcResultDocument.RgcResult.Item items[] = rr
							.getItemArray();
					if (items != null && items.length > 0) {
						String str[] = new String[items.length];
						for (int i = 0; i < items.length; i++)
							str[i] = (new StringBuilder(String.valueOf(items[i]
									.getX()))).append(",")
									.append(items[i].getY()).toString();

						return str;
					}
				} catch (HttpException e) {
					log.info((new StringBuilder("GPS偏移异常数据：")).append(
							e.getMessage()).toString());
					return null;
				} catch (Exception e) {
					log.info((new StringBuilder("GPS偏移异常数据：")).append(
							e.getMessage()).toString());
					return null;
				}
				return null;
			}
			return null;
		}
		return null;
	}

//	public List getAlarmPosition(List list) {
//		List linshilist = new ArrayList();
//		StringBuilder sb = new StringBuilder();
//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				AlarmManage alarmmanage = (AlarmManage) list.get(i);
//				String lon = alarmmanage.getLongitude();
//				String lat = alarmmanage.getLatitude();
//				if (lon != null && lon != "" && lat != null && lat != ""
//						&& !lon.equals("FFFF") && !lat.equals("FFFF")) {
//					sb.append(lon);
//					sb.append(",");
//					sb.append(lat);
//					if (i != list.size() - 1)
//						sb.append(";");
//					linshilist.add(alarmmanage);
//				}
//			}
//
//			log.info((new StringBuilder("告警坐标点:")).append(sb.toString())
//					.toString());
//			if (sb != null && !sb.toString().equals("")) {
//				String str[] = getXml(sb.toString());
//				if (str != null && str.length > 0) {
//					for (int i = 0; i < str.length; i++) {
//						String x = str[i].split(",")[0];
//						String y = str[i].split(",")[1];
//						((AlarmManage) linshilist.get(i)).setLongitude(x);
//						((AlarmManage) linshilist.get(i)).setLatitude(y);
//						for (int j = 0; j < list.size(); j++)
//							if (((AlarmManage) list.get(j)).getAlarm_id() != null
//									&& ((AlarmManage) list.get(j))
//											.getAlarm_id().equals(
//													((AlarmManage) linshilist
//															.get(i))
//															.getAlarm_id())) {
//								((AlarmManage) list.get(j))
//										.setLongitude(((AlarmManage) linshilist
//												.get(i)).getLongitude());
//								((AlarmManage) list.get(j))
//										.setLatitude(((AlarmManage) linshilist
//												.get(i)).getLatitude());
//							}
//
//					}
//
//				} else {
//					log.info("偏移后的告警坐标数据数组长度为0");
//				}
//			} else {
//				log.info("无合法告警坐标数据进行请求");
//			}
//			return list;
//		} else {
//			log.info(sb.toString());
//			return list;
//		}
//	}
//
//	private String getAddressinfo(String points) {
//		String result = "";
//		try {
//			PostMethod postMethodProxyRequest = new PostMethod(
//					"http://apis.mapabc.com/rgeocode/simple");
//			postMethodProxyRequest.addParameter("sid", "7001");
//			postMethodProxyRequest.addParameter("resType", "xml");
//			postMethodProxyRequest.addParameter("encode", "UTF-8");
//			postMethodProxyRequest.addParameter("key", getText("map.key"));
//			postMethodProxyRequest.addParameter("region", points);
//			postMethodProxyRequest.addParameter("roadnum", "1");
//			postMethodProxyRequest.addParameter("crossnum", "0");
//			postMethodProxyRequest.addParameter("poinum", "0");
//			postMethodProxyRequest.addParameter("show_near_districts", "false");
//			HttpClient httpClient = new HttpClient();
//			httpClient.getHttpConnectionManager().getParams()
//					.setConnectionTimeout(3000);
//			httpClient.getHttpConnectionManager().getParams()
//					.setSoTimeout(3000);
//			httpClient.getParams().setContentCharset("UTF-8");
//			int app = httpClient.executeMethod(postMethodProxyRequest);
//			String s = postMethodProxyRequest.getResponseBodyAsString();
//			log.info((new StringBuilder("逆地址查询应答码：")).append(app)
//					.append(";逆地址查询结果:").append(s).toString());
//			if (app == 200) {
//				SearchresultDocument srdoc = com.neusoft.yt.nidizhi.SearchresultDocument.Factory
//						.parse(s);
//				com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult seares = srdoc
//						.getSearchresult();
//				if (seares.getList() != null
//						&& seares.getList().getSpatialArray() != null) {
//					for (int i = 0; i < seares.getList().getSpatialArray().length; i++) {
//						com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial sp = seares
//								.getList().getSpatialArray(i);
//						if (sp.getRoadlist() != null) {
//							if (sp.getRoadlist().getRoadArray() != null
//									&& sp.getRoadlist().getRoadArray().length > 0) {
//								com.neusoft.yt.nidizhi.SearchresultDocument.Searchresult.List.Spatial.Roadlist.Road rd = sp
//										.getRoadlist().getRoadArray(0);
//								if (rd != null)
//									result = (new StringBuilder(
//											String.valueOf(result)))
//											.append(rd.getName()).append("附近,")
//											.toString();
//								else
//									result = (new StringBuilder(
//											String.valueOf(result))).append(
//											" ,").toString();
//							} else {
//								result = (new StringBuilder(
//										String.valueOf(result))).append(" ")
//										.append(",").toString();
//							}
//						} else {
//							result = (new StringBuilder(String.valueOf(result)))
//									.append(" ").append(",").toString();
//						}
//					}
//
//				}
//			}
//		} catch (HttpException e) {
//			log.error("获取逆地址查询时出错", e);
//		} catch (Exception e) {
//			log.error("获取逆地址查询时出错", e);
//		}
//		return result;
//	}
//
//	public List getAlarmAddress(List list) {
//		log.info("批量获取地理位置开始");
//		List linshilist = new ArrayList();
//		StringBuilder sb = new StringBuilder();
//		if (list != null && list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				AlarmManage alarmmanage = (AlarmManage) list.get(i);
//				String lon = alarmmanage.getLongitude();
//				String lat = alarmmanage.getLatitude();
//				if (lon != null && lon != "" && lat != null && lat != ""
//						&& !lon.equals("FFFF") && !lat.equals("FFFF")) {
//					sb.append(lon);
//					sb.append(",");
//					sb.append(lat);
//					if (i != list.size() - 1)
//						sb.append(";");
//					linshilist.add(alarmmanage);
//				}
//			}
//
//			log.info((new StringBuilder("告警坐标点:")).append(sb.toString())
//					.toString());
//			if (sb != null && !sb.toString().equals("")) {
//				String backresult = getAddressinfo(sb.toString());
//				if (backresult != null && !backresult.equals("")) {
//					String items[] = backresult.split(",");
//					for (int i = 0; i < items.length; i++) {
//						String x = items[i];
//						((AlarmManage) linshilist.get(i)).setAlarm_address(x);
//						for (int j = 0; j < list.size(); j++)
//							if (((AlarmManage) list.get(j)).getAlarm_id() != null
//									&& ((AlarmManage) list.get(j))
//											.getAlarm_id().equals(
//													((AlarmManage) linshilist
//															.get(i))
//															.getAlarm_id()))
//								((AlarmManage) list.get(j))
//										.setAlarm_address(((AlarmManage) linshilist
//												.get(i)).getAlarm_address());
//
//					}
//
//				} else {
//					log.info("无返回地址");
//				}
//			} else {
//				log.info("无合法告警坐标数据进行请求");
//			}
//			log.info("批量获取地理位置结束");
//			return list;
//		} else {
//			log.info("批量获取地理位置结束");
//			return list;
//		}
//	}

	protected final Logger log = Logger.getLogger(getClass());
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\aaaa.jar
	Total time: 15 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/
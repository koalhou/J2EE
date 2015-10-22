package com.neusoft.clw.yw.xs.poimanage.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.yw.xs.poimanage.ds.PoiInfo;
import com.neusoft.item.RgcResultDocument;
import com.neusoft.item.RgcResultDocument.RgcResult;
import com.neusoft.item.RgcResultDocument.RgcResult.Item;

/**
 * 坐标偏转类
 * @author JinPeng
 */
public class GPSUtil extends BaseAction {
    /**
     * 日志
     */
    protected final Logger log = Logger.getLogger(getClass());

    public List < PoiInfo > getpost(List < PoiInfo > list) {

        List < PoiInfo > linshilist = new ArrayList < PoiInfo >();

        StringBuilder sb = new StringBuilder();

        if (list != null && list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                PoiInfo poiInfo = list.get(i);

                String lon = poiInfo.getPoiLon();
                String lat = poiInfo.getPoiLat();

                if (lon != null && lon != "" && lat != null && lat != "") {
                    sb.append(lon);
                    sb.append(",");
                    sb.append(lat);
                    if (i != list.size() - 1) {
                        sb.append(";");
                    }
                    linshilist.add(poiInfo);
                }
            }

            // log.info("请求的GPS数据：" + sb.toString());

            if (sb != null && !sb.toString().equals("")) {
                String[] str = getXml(sb.toString());
                if (str != null && str.length > 0) {
                    for (int i = 0; i < str.length; i++) {
                        String x = str[i].split(",")[0];
                        String y = str[i].split(",")[1];

                        linshilist.get(i).setPoiLon(x);
                        linshilist.get(i).setPoiLat(y);

                        for (int j = 0; j < list.size(); j++) {

                            if (list.get(j).getPoiId() != null
                                    && list.get(j).getPoiId().equals(
                                            linshilist.get(i).getPoiId())) {
                                list.get(j).setPoiLon(
                                        linshilist.get(i).getPoiLon());
                                list.get(j).setPoiLat(
                                        linshilist.get(i).getPoiLat());
                            }
                        }
                    }
                } else {
                    log.info("偏移后的GPS数据数组长度为0");
                }
            } else {
                log.info("无合法GPS数据进行请求");
            }

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

    private String[] getXml(String points) {
        try {

            PostMethod postMethodProxyRequest = new PostMethod(
                    "http://search1.mapabc.com/sisserver");

//            Header header3 = new Header("Proxy-Authorization",
//                    "Basic d196aGU6MTAwMjAwQFdBTkd6aGU=");// 上线删除

//            postMethodProxyRequest.setRequestHeader(header3);// 上线删除

            postMethodProxyRequest.addParameter("config", "RGC");
            postMethodProxyRequest.addParameter("resType", "xml");
            postMethodProxyRequest.addParameter("coors", points);
            postMethodProxyRequest.addParameter("cr", "0");
            postMethodProxyRequest.addParameter("flag", "true");
            postMethodProxyRequest.addParameter("a_k", getText("map.key"));

            HttpClient httpClient = new HttpClient();

//            httpClient.getHostConfiguration().setProxy("proxy.neusoft.com",
//                    8080);// 上线删除
            httpClient.getParams().setContentCharset("UTF-8");

            int app = httpClient.executeMethod(postMethodProxyRequest);

            String s = postMethodProxyRequest.getResponseBodyAsString();
            // log.info("偏移应答码：" + app);
            // log.info("偏移后的应答信息：" + s);

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
            log.info("GPS偏移异常数据：" + e.getMessage());
            return null;
        } catch (Exception e) {
            log.info("GPS偏移异常数据：" + e.getMessage());
            return null;
        }
    }
}

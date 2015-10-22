/*******************************************************************************
 * @(#)AlarmCountAction.java Sep 6, 2008
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.page.ds;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <p>
 * Description:
 * </p>
 * @author lihaiyan <a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.0 $ Sep 6, 2008 1:24:29 PM
 */
@SuppressWarnings("unchecked")
public final class PageHelper {

    /**
     * Utility classes should not have a public or default constructor
     */
    private PageHelper() {

    }

    // private static Log log = LogFactory.getLog(PageHelper.class);
    private static Logger log = Logger.getLogger(PageHelper.class);

    private static final int PAGEINCREAMENT = 10;

    /**
     * 生成页面分页条
     * @param page
     * @return
     */
    public static String getPageBar(Page page) {
        String param = page.getParam();
        boolean isHasParam = true;

        if (null == param || "".equals(param)) {
            isHasParam = false;
        } else {
            param = paseParameter(param);
        }

        Map paramMap = saveParam(page, param, isHasParam);

        Configuration config = new Configuration();
        config.setClassForTemplateLoading(PageHelper.class, "");
        config.setEncoding(Locale.getDefault(), "utf-8");

        StringWriter out = new StringWriter();

        // 按照FreeMarker模板生成
        Template template;
        try {
            template = config.getTemplate(page.getPageTemplate());
            template.setEncoding("utf-8");
            template.process(paramMap, out);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (TemplateException e) {
            log.error(e.getMessage());
        }
        return out.toString();
    }

    /**
     * 生成左侧树页面分页条
     * @param page
     * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
     * @return
     */
    public static String getLeftPageBar(Page page) {
        String param = page.getParam();
        boolean isHasParam = true;

        if (null == param || "".equals(param)) {
            isHasParam = false;
        } else {
            param = paseParameter(param);
        }

        Map paramMap = saveParam(page, param, isHasParam);

        Configuration config = new Configuration();
        config.setClassForTemplateLoading(PageHelper.class, "");
        config.setEncoding(Locale.getDefault(), "utf-8");

        StringWriter out = new StringWriter();

        // 按照FreeMarker模板生成
        Template leftTemplate;
        try {
            leftTemplate = config.getTemplate(page.getLeftpageTemplate());
            leftTemplate.setEncoding("utf-8");
            leftTemplate.process(paramMap, out);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (TemplateException e) {
            log.error(e.getMessage());
        }
        return out.toString();
    }

    /**
     * 保存生成的相关分页信息
     * @param page
     * @param param
     * @param isHasParam
     * @return
     */
    private static Map saveParam(Page page, String param, boolean isHasParam) {

        Map paramMap = new HashMap();

        paramMap.put("start", Integer.valueOf(page.getStartOfPage()));
        paramMap.put("pageSize", Integer.valueOf(page.getPageSize()));
        paramMap.put("total", Integer.valueOf(page.getTotalCount()));
        paramMap.put("page", Integer.valueOf(page.getCurPage()));
        paramMap.put("totalPageCount", Integer
                .valueOf(page.getTotalPageCount()));
        paramMap.put("url", page.getUrl());
        paramMap.put("param", param);
        paramMap.put("firstUrl", getFirstURL(page, param, isHasParam));
        paramMap.put("prevUrl", getPrevURL(page, param, isHasParam));
        paramMap.put("nextUrl", getNextURL(page, param, isHasParam));
        paramMap.put("lastUrl", getLastURL(page, param, isHasParam));
        paramMap.put("numPage", getNumPage(page.getPageSize(), page.getCNum()));
        paramMap.put("jumpPage", getJumpPage(page.getTotalPageCount(), page
                .getCurPage()));
        paramMap.put("numParam", page.parseParam(param, Page.PAGE_SIZE_PREFIX));

        return paramMap;

    }

    /**
     * 获得首页URL
     * @param page
     * @param param
     * @param isHasParam
     * @return
     */
    private static String getFirstURL(Page page, String param,
            boolean isHasParam) {
        String firstUrl = "";
        if (page.getTotalPageCount() > 1 && page.getCurPage() != 1) {
            firstUrl = page.getUrl() + "?page=1";
            if (isHasParam) {
                firstUrl += "&" + param;
            }
        }
        return firstUrl;
    }

    /**
     * 取得上一页URL
     * @param page
     * @param param
     * @param isHasParam
     * @return
     */
    private static String getPrevURL(Page page, String param, boolean isHasParam) {
        String prevUrl = "";
        if (page.hasPreviousPage()) {
            prevUrl = page.getUrl() + "?page=" + (page.getCurPage() - 1);
            if (isHasParam) {
                prevUrl += "&" + param;
            }
        }
        return prevUrl;
    }

    /**
     * 取得下一页URL
     * @param page
     * @param param
     * @param isHasParam
     * @return
     */
    private static String getNextURL(Page page, String param, boolean isHasParam) {
        String nextUrl = "";
        if (page.hasNextPage()) {
            nextUrl = page.getUrl() + "?page=" + (page.getCurPage() + 1);
            if (isHasParam) {
                nextUrl += "&" + param;
            }
        }
        return nextUrl;
    }

    /**
     * 取得末页URL
     * @param page
     * @param param
     * @param isHasParam
     * @return
     */
    private static String getLastURL(Page page, String param, boolean isHasParam) {
        String lastUrl = "";
        if (page.getTotalPageCount() > 1
                && page.getCurPage() < page.getTotalPageCount()) {
            lastUrl = page.getUrl() + "?page=" + page.getTotalPageCount();
            if (isHasParam) {
                lastUrl += "&" + param;
            }
        }
        return lastUrl;
    }

    /**
     * 对参数进行编码转换
     * @param param
     * @return
     */
    private static String paseParameter(String param) {
        String[] params = param.split("&");
        param = "";
        for (String tempParam : params) {
            int index = tempParam.indexOf("=");
            try {
                tempParam = tempParam.substring(0, index)
                        + "="
                        + URLEncoder.encode(tempParam.substring(index + 1),
                                "utf-8");
                param = param + tempParam + "&";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (param.endsWith("&")) {
            param = param.substring(0, param.length() - 1);
        }
        return param;
    }

    /**
     * 取得页数
     * @param pageSize
     * @param cNum
     * @return
     */
    private static String getNumPage(int pageSize, int cNum) {
        StringBuffer sb = new StringBuffer();
        int tmp;
        for (int i = 1; i <= cNum; i++) {
            tmp = i * PAGEINCREAMENT;
            sb.append("<option value='").append(tmp).append("'");
            if (tmp == pageSize) {
                sb.append(" selected ");
            }
            sb.append(">").append(tmp).append("</option>");

        }
        return sb.toString();
    }

    /**
     * 取得跳转页
     * @param totalPageCount
     * @param curPage
     * @return
     */
    private static String getJumpPage(long totalPageCount, int curPage) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= totalPageCount; i++) {
            sb.append("<option value='").append(i).append("'");
            if (i == curPage) {
                sb.append(" selected ");
            }
            sb.append(">").append(i).append("</option>");
        }
        return sb.toString();
    }

}

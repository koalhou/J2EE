/*******************************************************************************
 * @(#)Page.java Oct 26, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.page.ds;

import com.neusoft.clw.common.util.Constants;

/**
 * @author <a href="mailto:hegq@neusoft.com">puras.he </a>
 * @version $Revision 1.1 $ Oct 26, 2007 11:14:31 AM
 */
public class DetailPage {
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String PAGE_PREFIX = "detailPage=";

    public static final String PAGE_SIZE_PREFIX = "detailPageSize=";

    private int detailCurPage = 1;

    private int detailPageSize = DEFAULT_PAGE_SIZE;

    private int detailTotalCount;

    // 以10为单位，递增初始化页面上的分页所能选择的页面记录数
    private int cNum = Constants.DEFAULT_PAGE_SIZE;

    private String url = "";

    private String param = "";

    private String pageTemplate = "detailPage.ftl";

    public DetailPage() {
        this(1, 0, DEFAULT_PAGE_SIZE, "");
    }

    public DetailPage(int curPage, int totalSize, String queryString) {
        this(curPage, totalSize, DEFAULT_PAGE_SIZE, queryString);
    }

    public DetailPage(int curPage, int totalSize, int pageSize,
            String queryString) {
        this.detailPageSize = pageSize;
        this.detailTotalCount = totalSize;
        this.detailCurPage = setCurrentPage(curPage);
        parseQueryString(queryString);
    }

    public DetailPage(int curPage, int totalSize, int pageSize, String url,
            String param) {
        this.detailPageSize = pageSize;
        this.detailTotalCount = totalSize;
        this.detailCurPage = setCurrentPage(curPage);
        this.url = url;
        this.param = parseParam(param, PAGE_PREFIX);
    }

    private int setCurrentPage(int currentPage) {
        if (currentPage > this.getTotalPageCount()) {
            return this.getTotalPageCount();
        } else {
            return currentPage;
        }
    }

    public String parseParam(String curParam, String filter) {
        StringBuffer sb = new StringBuffer();
        if (null != curParam) {
            String[] params = curParam.split("&");
            if (null != params) {
                for (int i = 0, len = params.length; i < len; i++) {
                    if (params[i].indexOf(filter) != -1) {
                        continue;
                    }
                    if (params[i].length() > 0) {
                        sb.append(params[i]);
                        if (i < len - 1) {
                            sb.append("&");
                        }
                    }
                }
            }
        }
        String paramTmp = sb.toString();
        if (paramTmp.endsWith("&")) {
            paramTmp = paramTmp.substring(0, paramTmp.length() - 1);
        }

        return paramTmp;
    }

    private void parseQueryString(String queryString) {
        if (queryString.indexOf("?") != -1) {
            String[] tmps = queryString.split("\\?");
            url = tmps[0];
            param = tmps[1];
        } else {
            url = queryString;
        }
    }

    public int getTotalPageCount() {
        if (detailTotalCount % detailPageSize == 0) {
            return detailTotalCount / detailPageSize;
        } else {
            return detailTotalCount / detailPageSize + 1;
        }
    }

    public boolean hasNextPage() {
        return this.getDetailCurPage() < this.getTotalPageCount();
    }

    public boolean hasPreviousPage() {
        return this.getDetailCurPage() > 1;
    }

    public int getDetailStartOfPage() {
        return getDetailStartOfPage(this.detailCurPage, this.detailPageSize);
    }

    public int getDetailStartOfPage(int pageNo) {
        return getDetailStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public int getDetailStartOfPage(int pageNo, int tempPageSize) {
        return (pageNo - 1) * tempPageSize;
    }

    /**
     * @return Returns the cNum.
     */
    public int getCNum() {
        return cNum;
    }

    /**
     * @param num The cNum to set.
     */
    public void setCNum(int num) {
        cNum = num;
    }

    /**
     * @return Returns the pageTemplate.
     */
    public String getPageTemplate() {
        return pageTemplate;
    }

    /**
     * @param pageTemplate The pageTemplate to set.
     */
    public void setPageTemplate(String pageTemplate) {
        this.pageTemplate = pageTemplate;
    }

    public void setQueryString(String queryString) {
        parseQueryString(queryString);
    }

    public int getDetailCurPage() {
        return detailCurPage;
    }

    public void setDetailCurPage(int detailCurPage) {
        this.detailCurPage = detailCurPage;
    }

    public int getDetailPageSize() {
        return detailPageSize;
    }

    public void setDetailPageSize(int detailPageSize) {
        this.detailPageSize = detailPageSize;
    }

    public int getDetailTotalCount() {
        return detailTotalCount;
    }

    public void setDetailTotalCount(int detailTotalCount) {
        this.detailTotalCount = detailTotalCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}

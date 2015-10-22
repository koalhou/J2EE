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
public class SelectPage {
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String PAGE_PREFIX = "selectPage=";

    public static final String PAGE_SIZE_PREFIX = "selectPageSize=";

    private int selectCurPage = 1;

    private int selectPageSize = DEFAULT_PAGE_SIZE;

    private int selectTotalCount;

    private int cNum = Constants.DEFAULT_PAGE_SIZE;

    private String url = "";

    private String param = "";

    private String pageTemplate = "selectPage.ftl";

    public SelectPage() {
        this(1, 0, DEFAULT_PAGE_SIZE, "");
    }

    public SelectPage(int curPage, int totalSize, String queryString) {
        this(curPage, totalSize, DEFAULT_PAGE_SIZE, queryString);
    }

    public SelectPage(int curPage, int totalSize, int pageSize,
            String queryString) {
        this.selectPageSize = pageSize;
        this.selectTotalCount = totalSize;
        this.selectCurPage = setCurrentPage(curPage);
        parseQueryString(queryString);
    }

    public SelectPage(int curPage, int totalSize, int pageSize, String url,
            String param) {
        this.selectPageSize = pageSize;
        this.selectTotalCount = totalSize;
        this.selectCurPage = setCurrentPage(curPage);
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
        if (selectTotalCount % selectPageSize == 0) {
            return selectTotalCount / selectPageSize;
        } else {
            return selectTotalCount / selectPageSize + 1;
        }
    }

    public boolean hasNextPage() {
        return this.getSelectCurPage() < this.getTotalPageCount();
    }

    public boolean hasPreviousPage() {
        return this.getSelectCurPage() > 1;
    }

    public int getSelectStartOfPage() {
        return getSelectStartOfPage(this.selectCurPage, this.selectPageSize);
    }

    public int getSelectStartOfPage(int pageNo) {
        return getSelectStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public int getSelectStartOfPage(int pageNo, int tempPageSize) {
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

    public int getSelectCurPage() {
        return selectCurPage;
    }

    public void setSelectCurPage(int selectCurPage) {
        this.selectCurPage = selectCurPage;
    }

    public int getSelectPageSize() {
        return selectPageSize;
    }

    public void setSelectPageSize(int selectPageSize) {
        this.selectPageSize = selectPageSize;
    }

    public int getSelectTotalCount() {
        return selectTotalCount;
    }

    public void setSelectTotalCount(int selectTotalCount) {
        this.selectTotalCount = selectTotalCount;
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

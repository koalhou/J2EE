/*******************************************************************************
 * @(#)PaginationAction.java 2008-4-8
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.page.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;

/**
 * @author <a href="mailto:hanbj@neusoft.com">baojun.han </a>
 * @version $Revision 1.1 $ 2008-4-8 02:57:46
 */
@SuppressWarnings("unchecked")
public class PaginationAction extends BaseAction {

    /**
     * 生成序列号
     */
    private static final long serialVersionUID = -1601199179831404521L;

    /** 分页信息 **/
    protected int page = 1;

    protected int pageSize = Page.DEFAULT_PAGE_SIZE;

    protected String url;

    protected String param;

    protected String pageBar;

    protected int getStartPage(Service service, Class clazz)
            throws BusinessException {
        int totalCount = service.getCount(clazz, null);
        Page pageObj = new Page(page, totalCount, pageSize, url, param);
        this.pageBar = PageHelper.getPageBar(pageObj);
        return pageObj.getStartOfPage();
    }

    protected int getStartPage(Service service, Class clazz, Object object)
            throws BusinessException {
        int totalCount = service.getCount(clazz, object);
        Page pageObj = new Page(page, totalCount, pageSize, url, param);
        this.pageBar = PageHelper.getPageBar(pageObj);
        return pageObj.getStartOfPage();
    }

    /**
     * @return Returns the page.
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page The page to set.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return Returns the pageSize.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize The pageSize to set.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return Returns the param.
     */
    public String getParam() {
        return param;
    }

    /**
     * @param param The param to set.
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * @return Returns the pageBar.
     */
    public String getPageBar() {
        return pageBar;
    }

    /**
     * @param pageBar The pageBar to set.
     */
    public void setPageBar(String pageBar) {
        this.pageBar = pageBar;
    }
}

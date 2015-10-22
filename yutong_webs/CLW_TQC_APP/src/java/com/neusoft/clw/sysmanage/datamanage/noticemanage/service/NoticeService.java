package com.neusoft.clw.sysmanage.datamanage.noticemanage.service;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwBspUnstructurFileT;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwTqcGonggaoT;

public interface NoticeService extends Service{
	/**
	 * 插入公告图片信息
	 */
	public Integer insertImage(String sqlId,ClwBspUnstructurFileT obj) throws BusinessException;
	/**
	 * 置顶
	 */
	public void updateTop(String sqlId,ClwTqcGonggaoT obj) throws BusinessException;
	/**
	 * 公告信息插入
	 */
	public void insertNotice(String sqlId,ClwTqcGonggaoT obj) throws BusinessException;
	/**
	 * 公告信息修改
	 */
	public void updateNotice(String sqlId,ClwTqcGonggaoT obj) throws BusinessException;
}

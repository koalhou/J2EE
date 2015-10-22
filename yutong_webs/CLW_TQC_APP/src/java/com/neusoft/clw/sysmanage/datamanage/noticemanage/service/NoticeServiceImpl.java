package com.neusoft.clw.sysmanage.datamanage.noticemanage.service;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwBspUnstructurFileT;
import com.neusoft.clw.sysmanage.datamanage.noticemanage.domain.ClwTqcGonggaoT;

public class NoticeServiceImpl extends ServiceImpl implements NoticeService {
	/**
	 * 插入公告图片信息
	 */
	 
	public Integer insertImage(String sqlId,ClwBspUnstructurFileT obj) throws BusinessException {
		Integer fileId=null;
		try{
			fileId =(Integer)this.dao.insert(sqlId, obj);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return fileId;
	}
	/**
	 * 置顶
	 */
	public void updateTop(String sqlId,ClwTqcGonggaoT obj) throws BusinessException{
		try {
			//把已置顶的公告改为未置顶
			this.dao.update("NoticeManage.updateTop", null);
			//把当前的公告置顶
			this.dao.update(sqlId, obj);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	/**
	 * 公告信息插入
	 */
	public void insertNotice(String sqlId,ClwTqcGonggaoT obj) throws BusinessException{
		try {
			//判断是否选择置顶 1:置顶
			if(!"".equals(obj.getIsTop()) && "1".equals(obj.getIsTop())){
				//把已置顶的公告改为未置顶
				this.dao.update("NoticeManage.updateTop", null);
			}
			//公告信息插入
			this.dao.insert(sqlId, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
	}
	/**
	 * 公告信息修改
	 */
	public void updateNotice(String sqlId,ClwTqcGonggaoT obj) throws BusinessException{
		try {
			//判断是否选择置顶 1:置顶
			if(!"".equals(obj.getIsTop()) && "1".equals(obj.getIsTop())){
				//把已置顶的公告改为未置顶
				this.dao.update("NoticeManage.updateTop", null);
			}
			//公告信息修改
			this.dao.update(sqlId, obj);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}

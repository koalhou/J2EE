package com.neusoft.clw.yw.ub.holiday.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.dao.Dao;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.yw.ub.holiday.ds.HolidayWorkdayDataInfo;
import com.neusoft.clw.yw.ub.holiday.service.HolidayManageService;

public class HolidayManageServiceImpl extends ServiceImpl implements
HolidayManageService {

	@Override
	public void insertInfo(List<HolidayWorkdayDataInfo> list)
			throws BusinessException {
		// TODO Auto-generated method stub
		Map<String, List<HolidayWorkdayDataInfo>> map = new HashMap<String, List<HolidayWorkdayDataInfo>>();
        map.put("holiworkdays", list);

		try {
			dao.insert("HolidayMaintain.insertDayInfo", map);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
		
	}

	/* (non-Javadoc)
	 * @see com.neusoft.clw.yw.ub.holiday.service.HolidayManageService#updateInfo(java.lang.String, java.util.List)
	 */
	@Override
	public void updateInfo(String groupname, List<HolidayWorkdayDataInfo> list)
			throws BusinessException {
		// TODO Auto-generated method stub
		Map<String, String> delmap = new HashMap<String, String>();
		delmap.put("groupname", SearchUtil.formatSpecialChar(groupname));
    	
		Map<String, List<HolidayWorkdayDataInfo>> map = new HashMap<String, List<HolidayWorkdayDataInfo>>();
        map.put("holiworkdays", list);

		try {
			
			//先更新这组数据的删除状态为1
			dao.update("HolidayMaintain.updateDelStatus", delmap);
			//再继续增加操作
			dao.insert("HolidayMaintain.insertDayInfo", map);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
		
	}

}

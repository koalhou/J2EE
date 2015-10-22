package com.neusoft.clw.common.service.employeeManageService.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.studentManageService.StudentManageService;
import com.neusoft.clw.common.service.employeeManageService.EmployeeManageService;
import com.neusoft.clw.sysmanage.datamanage.employeemanage.domain.EmployeeInfo;

public class EmployeeManageServiceImpl extends ServiceImpl implements EmployeeManageService{
	 public void importEmployeeInfos(List < EmployeeInfo > list)
     throws BusinessException {
		 insert(EmployeeInfo.class, list);
	 }
}

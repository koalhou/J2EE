package com.neusoft.clw.common.service.studentManageService.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.studentManageService.StudentManageService;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;

public class StudentManageServiceImpl extends ServiceImpl implements
        StudentManageService {
    public void importStudentInfos(List < StudentInfo > list)
            throws BusinessException {
        insert(StudentInfo.class, list);
    }
}

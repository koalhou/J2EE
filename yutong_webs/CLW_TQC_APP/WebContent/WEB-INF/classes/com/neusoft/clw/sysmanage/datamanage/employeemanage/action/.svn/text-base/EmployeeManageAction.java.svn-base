package com.neusoft.clw.sysmanage.datamanage.employeemanage.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.studentManageService.StudentManageService;
import com.neusoft.clw.common.service.employeeManageService.EmployeeManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.ExcelParser;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.fileupload.domain.UploadFileInfo;
import com.neusoft.clw.infomanage.studentmanage.action.DateFormatUtil;
import com.neusoft.clw.infomanage.studentmanage.domain.CommonMapBean;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.employeemanage.domain.EmployeeInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.neusoft.ie.xml.IEXmlReader;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeManageAction extends PaginationAction{
	/** service共通类 */
    private transient Service service;
    
    private transient StudentManageService studentManageService;
    private transient EmployeeManageService employeeManageService;
    
    private static final String FORBID_ACTION = "forbid";

    /** 显示数据list **/
    private List < StudentInfo > studentList;

    /** 修改数据用 **/
    private StudentInfo studentInfo;
    
    private EmployeeInfo employeeInfo;

    /** 显示消息 **/
    private String message = null;

    /** 性别编码 **/
    private Map < String, String > sexSysMap = new HashMap < String, String >();

    /** 所属关系编码 **/
    private Map < String, String > relativeTypeMap = new HashMap < String, String >();
    
    /** 基础消息类型列表List **/
    private List < CommonMapBean > relativeTypeList = new ArrayList < CommonMapBean >();
    
    private final String FORBID = "forbid";

    private Map map = new HashMap();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;
    
    private ByteArrayInputStream inputStream;
    
    /** 保存的照片ID **/
    private String picId = "";
    
    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    /** 省/直辖市信息 **/
    private List < ZoneXsInfo > provinceInfosList = new ArrayList < ZoneXsInfo >();

    /** 市/县信息 **/
    private List < ZoneXsInfo > cityInfosList = new ArrayList < ZoneXsInfo >();
    
    /** 县信息 **/
    private List < ZoneXsInfo > countryInfosList = new ArrayList < ZoneXsInfo >();
   
    public List < ZoneXsInfo > getProvinceInfosList() {
        return provinceInfosList;
    }

    public void setProvinceInfosList(List < ZoneXsInfo > provinceInfosList) {
        this.provinceInfosList = provinceInfosList;
    }

    public List < ZoneXsInfo > getCityInfosList() {
        return cityInfosList;
    }

    public void setCityInfosList(List < ZoneXsInfo > cityInfosList) {
        this.cityInfosList = cityInfosList;
    }

    public List < ZoneXsInfo > getCountryInfosList() {
        return countryInfosList;
    }

    public void setCountryInfosList(List < ZoneXsInfo > countryInfosList) {
        this.countryInfosList = countryInfosList;
    }

    /**
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos() {
        // 地理信息列表
        Map < String, Object > mapPar = new HashMap < String, Object >(1);

        try {
            // 省/直辖市
            mapPar.put("parentId", null);
            provinceInfosList = service.getObjects("ZoneManage.getGeographyList", mapPar);

            // 市
            if (studentInfo != null && studentInfo.getStudentProvince() != ""
                    && studentInfo.getStudentProvince() != null) {
                mapPar.put("parentId", studentInfo.getStudentProvince());

                cityInfosList = service.getObjects("ZoneManage.getGeographyList", mapPar);
            }
            // 县
            if (studentInfo != null && studentInfo.getStudentCity() != ""
                    && studentInfo.getStudentCity() != null) {
                mapPar.put("parentId", studentInfo.getStudentCity());
                countryInfosList = service.getObjects("ZoneManage.getGeographyList", mapPar);
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        }

        return true;
    }
    
    /**
     * 初始化所属关系
     */
    private void initRelativeTypeList() {
    	CommonMapBean father = new CommonMapBean();
    	father.setCodeId("0");
    	father.setCodeName("父亲");
    	relativeTypeList.add(father);
    	
    	CommonMapBean mother = new CommonMapBean();
    	mother.setCodeId("1");
    	mother.setCodeName("母亲");
    	relativeTypeList.add(mother);
    	
    	CommonMapBean grandpa = new CommonMapBean();
    	grandpa.setCodeId("2");
    	grandpa.setCodeName("爷爷");
    	relativeTypeList.add(grandpa);
    	
    	CommonMapBean grandmother = new CommonMapBean();
    	grandmother.setCodeId("3");
    	grandmother.setCodeName("奶奶");
    	relativeTypeList.add(grandmother);
    	
    	CommonMapBean grandpa1 = new CommonMapBean();
    	grandpa1.setCodeId("4");
    	grandpa1.setCodeName("外公");
    	relativeTypeList.add(grandpa1);
    	
    	CommonMapBean grandmother1 = new CommonMapBean();
    	grandmother1.setCodeId("5");
    	grandmother1.setCodeName("外婆");
    	relativeTypeList.add(grandmother1);
    	
    	CommonMapBean others = new CommonMapBean();
    	others.setCodeId("6");
    	others.setCodeName("其他");
    	relativeTypeList.add(others);
    	
    }
    
    public StudentManageService getStudentManageService() {
        return studentManageService;
    }

    public void setStudentManageService(StudentManageService studentManageService) {
        this.studentManageService = studentManageService;
    }

    
    public EmployeeManageService getEmployeeManageService() {
		return employeeManageService;
	}

	public void setEmployeeManageService(EmployeeManageService employeeManageService) {
		this.employeeManageService = employeeManageService;
	}

	public List<CommonMapBean> getRelativeTypeList() {
		return relativeTypeList;
	}

	public void setRelativeTypeList(List<CommonMapBean> relativeTypeList) {
		this.relativeTypeList = relativeTypeList;
	}

	/**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    public Map < String, String > getSexSysMap() {
        return sexSysMap;
    }

    public void setSexSysMap(Map < String, String > sexSysMap) {
        this.sexSysMap = sexSysMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }
    
    public List < StudentInfo > getStudentList() {
        return studentList;
    }

    public void setStudentList(List < StudentInfo > studentList) {
        this.studentList = studentList;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    } 

    public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public Map < String, String > getRelativeTypeMap() {
        return relativeTypeMap;
    }

    public void setRelativeTypeMap(Map < String, String > relativeTypeMap) {
        this.relativeTypeMap = relativeTypeMap;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        UserInfo user = getCurrentUser();
//        if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
//            setMessage("student.getin.nopermission");
//            addActionMessage(getText(message));
//            return FORBID_ACTION;
//        }
        if (null != message) {
        	if("1".equals(message)){
        		addActionMessage(getText("添加员工信息成功"));
        	}
        	else if("2".equals(message)){
        		addActionMessage(getText("修改员工信息成功"));
        	}
        	else if("3".equals(message)){
        		addActionMessage(getText("删除员工信息成功"));
        	}
        	else{
        		addActionMessage(getText(message));
        	}
        }
        return SUCCESS;
    }

    /**
     * 浏览员工信息
     * @return
     */
    public String employeeBrowse() {
        final String browseTitle = "浏览员工信息";
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            UserInfo user = getCurrentUser();

            studentInfo.setEnterprise_id(user.getEntiID());
            //studentInfo.setOrganization_id(user.getOrganizationID());从前台已经传进来组织id，此处不能要
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            if("STU_NAME".equals(sortName)){
            	sortName="NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')";
            }
            String sortOrder = request.getParameter("sortorder");
    
            studentInfo.setSortname(sortName);
            studentInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("StudentManage.getCount", studentInfo);
            studentList = (List < StudentInfo >) service.getObjectsByPage(
                    "StudentManage.getInfos", studentInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(studentList, totalCount, pageIndex, rpNum);// 转换map

            if (studentList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMANAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    /**
     * 浏览员工信息---泰安版
     * @return
     */
    public String employeeBrowseta() {
        final String browseTitle = "浏览员工信息";
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            UserInfo user = getCurrentUser();

            studentInfo.setEnterprise_id(user.getEntiID());
            //studentInfo.setOrganization_id(user.getOrganizationID());从前台已经传进来组织id，此处不能要
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            if("STU_NAME".equals(sortName)){
            	sortName="NLSSORT(STU_NAME,'NLS_SORT = SCHINESE_PINYIN_M')";
            }
            String sortOrder = request.getParameter("sortorder");
    
            studentInfo.setSortname(sortName);
            studentInfo.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("StudentManage.getCount", studentInfo);
            studentList = (List < StudentInfo >) service.getObjectsByPage(
                    "StudentManage.getInfos", studentInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(studentList, totalCount, pageIndex, rpNum);// 转换map

            if (studentList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMANAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    public String addBefore() {
        UserInfo user = getCurrentUser();
        
        if (null != message) {
            addActionMessage("员工信息添加失败！");
        }

        // 性别
        if (sexSysMap != null && sexSysMap.size() == 0) {
            sexSysMap = Constants.SEX_SYS_MAP;
        }
        
        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }
        
        if (relativeTypeList == null || relativeTypeList.size() == 0) {
            initRelativeTypeList();
        }
        studentInfo= new StudentInfo();
        studentInfo.setEnterprise_id(user.getOrganizationID());
        return SUCCESS;
    }

    /**
     * 添加员工
     */
    public String add() {
        if (null == studentInfo) {
            return addBefore();
        }
        final String addTitle = "添加员工信息";
        log.info(addTitle);
        try {
            UserInfo user = getCurrentUser();
            studentInfo.setCreater(user.getUserID());
            studentInfo.setModifier(user.getUserID());
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setValid_flag("0");
            
            if ("710000".equals(studentInfo.getStudentProvince()) ||
                "810000".equals(studentInfo.getStudentProvince()) ||
                "820000".equals(studentInfo.getStudentProvince())) {
                // 港、澳、台地区市县信息填写
                studentInfo.setStudentCity(studentInfo.getStudentProvince());
                studentInfo.setStudentDistrict(studentInfo.getStudentProvince());
            }
            
            /**
            if (null != file) {
                // 判断是否包含相片文件
                String strFileName = file.getAbsolutePath().toLowerCase();

                BufferedInputStream in = null;
                ByteArrayOutputStream out = null;

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    out = new ByteArrayOutputStream(1024);

                    while ((size = in.read(temp)) != -1) {
                        out.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }

                byte[] content = out.toByteArray();

                studentInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                // 创建附件信息bean
                studentInfo.setPhotoContent(content);
            }**/
            if(null != picId && picId.length() > 0) {
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(picId);
                uploadFileInfo = (UploadFileInfo) service.getObject(
                        "FileUpload.getPhoto", uploadFileInfo);
                studentInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                // 创建附件信息bean
                studentInfo.setPhotoContent(uploadFileInfo.getPhotoContent());
            }
            service.insert("StudentManage.insertStudentInfo", studentInfo);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            setMessage("creat student error:" + e.getMessage());
            return ERROR;
        }
        //String fileName=URLEncoder.encode("添加员工信息成功","GBK");
        setMessage("1");
        
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_ADD);
        return SUCCESS;
    }
    private String trip_idupdate;
    
    public String getTrip_idupdate() {
		return trip_idupdate;
	}

	public void setTrip_idupdate(String trip_idupdate) {
		this.trip_idupdate = trip_idupdate;
	}

	/**
     * 修改员工页面
     */
    public String editBefore() {
        final String editBefTitle = getText("修改员工");
        UserInfo user = getCurrentUser();
        String ChooseEnterID_tree = "";
        
        if (null != message) {
            addActionMessage("员工息修改失败");
        }

        StudentInfo student= new StudentInfo();

        if(null!=employeeInfo){
        	student.setStudent_id(employeeInfo.getEmployee_id());
        }
        log.info(editBefTitle);
        try {
            if (null != student) {
              
                studentInfo = (StudentInfo) service.getObject("StudentManage.getStudentInfo", student);
                if (null == studentInfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
                studentInfo.setOld_student_code(studentInfo.getStudent_code());
                studentInfo.setOld_student_card(studentInfo.getStudent_card());
                studentInfo.setOld_organization_id(studentInfo.getOrganization_id());
                studentInfo.setEnterprise_id(user.getOrganizationID());
                ChooseEnterID_tree = studentInfo.getOrganization_id();
                if (!getGeoInfos()) {
                    // 地理信息初始化失败时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }
                
            } else {
                setMessage("info.data.notexsist");
                return ERROR;
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        } finally {
        	ActionContext.getContext().getSession().put("ChooseEnterID_tree",ChooseEnterID_tree);
        }
        return SUCCESS;
    }
    
    /**
     * 显示员工相片
     * @return
     */
    public String showStudentPhoto() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String studentId = request.getParameter("student_id");
            
            StudentInfo tmp = new StudentInfo();
            tmp.setStudent_id(studentId);
            tmp = (StudentInfo) service.getObject(
                    "StudentManage.getStudentInfo", tmp);
            
            ByteArrayInputStream input = null;
            try {
                input = new ByteArrayInputStream(tmp.getPhotoContent());
            } catch (Exception e) {
                log.debug("显示员工相片出现错误：" + e.toString());
                return ERROR;
            }
            this.setInputStream(input);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 修改员工
     */
    public String updateEmployee() {
        if (null == studentInfo) {
        	return editBefore();
        }
        
//        studentInfo
        
        final String editTitle = getText("修改员工");
        try {
        	UserInfo user = getCurrentUser();
            studentInfo.setModifier(user.getUserID());
            String updateStr = "";
            
            if(null != picId && picId.length() > 0) {
                UploadFileInfo uploadFileInfo = new UploadFileInfo();
                uploadFileInfo.setDataId(picId);
                uploadFileInfo = (UploadFileInfo) service.getObject(
                        "FileUpload.getPhoto", uploadFileInfo);
                studentInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                // 创建附件信息bean
                studentInfo.setPhotoContent(uploadFileInfo.getPhotoContent());
                updateStr = "StudentManage.updateInfoAndPhotobyStudentId";
            } else if("del".equals(studentInfo.getPhotoDelFlag())) {
                // 删除相片并更新信息
                updateStr = "StudentManage.updateInfoDelPhotobyStudentId";
            } else {
                updateStr = "StudentManage.updateInfoOnlybyStudentId";
            }
            
            if ("710000".equals(studentInfo.getStudentProvince())
                    || "810000".equals(studentInfo.getStudentProvince())
                    || "820000".equals(studentInfo.getStudentProvince())) {
                // 港、澳、台地区市县信息填写
                studentInfo.setStudentCity(studentInfo.getStudentProvince());
                studentInfo.setStudentDistrict(studentInfo.getStudentProvince());
            }
            
            service.update(updateStr, studentInfo);
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("2");
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_MODIFY);
        return SUCCESS;
    }
    private String formatIds(String regions) {
        String ret = "";
        String[] strs = regions.split(",");
        for (int i = 0; i < strs.length; i++) {
            String tmp = strs[i];
            if (null != tmp && tmp.length() > 0) {
                tmp = tmp.substring(0, tmp.length());
                strs[i] = tmp;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            if (ret == "") {
                ret = ret.concat(strs[i]);
            } else {
                ret = ret.concat(",").concat(strs[i]);
            }
        }
        return ret;
    }
    /**
     * 删除员工
     */
    public String deleteEmployee() {
        if (null == studentInfo) {
            return editBefore();
        }
        final String cancleTitle = getText("student.delete");
        
        String student = studentInfo.getStudent_id();
        
     // 所选去除分配车辆VIN确认
        if (student != null && student != ""
                && !student.equals("")) {
        	
    		//vehcileinfo.setOrganization_id(user.getOrganizationID());
        	//student.setCancleids(carsVinInfos);
    		
			
            // 拆分车辆VIN
            String student_id[] = formatIds(student).split(",");

            //List < StudentInfo > vInfos = new ArrayList < StudentInfo >();

            // 去除分配车辆VIN进行集合统计
            for (int i = 0; i < student_id.length; i++) {

            	StudentInfo stu = new StudentInfo();

                stu.setStudent_id(student_id[i]);
                // 设置修改人-当前操作者
                //stu.setModifier(user.getUserID());

                // vi.setOrganization_id(organization_id);

                //vInfos.add(stu);
            

        try {
            UserInfo user = getCurrentUser();
            studentInfo.setVaset_user_id(user.getUserID());
            //int m = service.getCount("StudentManage.getInUseCount", stu);  //判断员工或是学生是否分配了线路
            //if (m > 0) {
               // setMessage("student.delete.nopermission");
                //return FORBID;
            //} else {
                service.update("StudentManage.deletebyStudentId", stu);
            //}
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }}}
        else {
            // 没有选择车辆返回提示，重新操作
            setMessage("choose.canle.car");
            return ERROR;
        }
        setMessage("3");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_DELETE);

        return SUCCESS;

    }
    /**
     * 删除员工
     */
    public void deleteEmployee2() {
        final String cancleTitle = getText("student.delete");
        
        String student = studentInfo.getStudent_id();
        
     // 所选去除分配车辆VIN确认
        if (student != null && student != "" && !student.equals("")) {
        	
    		//vehcileinfo.setOrganization_id(user.getOrganizationID());
        	//student.setCancleids(carsVinInfos);
			
            // 拆分车辆VIN
            String student_id[] = formatIds(student).split(",");

            //List < StudentInfo > vInfos = new ArrayList < StudentInfo >();

            // 去除分配车辆VIN进行集合统计
            for (int i = 0; i < student_id.length; i++) {
            	StudentInfo stu = new StudentInfo();
                stu.setStudent_id(student_id[i]);
                // 设置修改人-当前操作者
                //stu.setModifier(user.getUserID());

                // vi.setOrganization_id(organization_id);

                //vInfos.add(stu);
        try {
            UserInfo user = getCurrentUser();
            studentInfo.setVaset_user_id(user.getUserID());
            //int m = service.getCount("StudentManage.getInUseCount", stu);  //判断员工或是学生是否分配了线路
            //if (m > 0) {
               // setMessage("student.delete.nopermission");
                //return FORBID;
            //} else {
                service.update("StudentManage.deletebyStudentId", stu);
            //}
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            printWriter("error");
        }}}
        else {
            // 没有选择车辆返回提示，重新操作
            setMessage("choose.canle.car");
            printWriter("error");
        }
        setMessage("3");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_DELETE);
        printWriter("success");
    }
    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, StudentInfo studentObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != studentObj) {
            if (null != studentObj.getStudent_id()) {
                OperateLogFormator.format(sb, "studentid", studentObj
                        .getStudent_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            StudentInfo s = (StudentInfo) list.get(i);
            if(null == s.getPhoto_name() || s.getPhoto_name().length() == 0) {
            	s.setPhoto_name("1");
            }
            Map cellMap = new LinkedHashMap();
            s.setRelative_type(Constants.RELATIVE_TYPE_MAP.get(s.getRelative_type()));
            cellMap.put("id", s.getStudent_id());
            cellMap.put("cell", new Object[] {
                    //(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
            		s.getTrip_id()==null||s.getTrip_id().equals("")?s.getStudent_id():"",
                    s.getStudent_name(),
                    s.getStudent_code(),
                    "<span id='"+ s.getStudent_id() +"'>"+ s.getStudent_card() +"</span>",
                    //s.getStudent_school(),
                    s.getShort_name(),
                    s.getRemarks(),
                    s.getTrip_id()==null||s.getTrip_id().equals("")?s.getStudent_id():"",
                    //s.getStudent_class()
                    //s.getStudent_sex(),
                    //s.getStudent_birth(),
                    //s.getStudent_address(),
                    //s.getTeacher_name(),
                    //s.getTeacher_tel(),
                    //s.getRelative_name(),
                    //s.getRelative_type(),
                    //s.getRelative_tel(),
                    //s.getPhoto_name()
                    });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /**
     * 导入页面初始化
     * @return
     */
    public String importEmployeeBefore() {
        UserInfo user = getCurrentUser();
        
        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }
        
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }
    
    /**
     * 获取文件内容错误
     * @return
     */
    private String getFileContentError(List < IEErrorMessage > list) {
        String errMsg = "";
        String strSplit = "";
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
        	if(tmp.getMessage().indexOf("employee_name")>=0)
        		strSplit = tmp.getMessage().replace("employee_name", "");
        	else if(tmp.getMessage().indexOf("employee_code")>=0)
        		strSplit = tmp.getMessage().replace("employee_code", "");
        	else if(tmp.getMessage().indexOf("employee_card")>=0)
        		strSplit = tmp.getMessage().replace("employee_card", "");
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), strSplit);
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }
    /**
     * 获取文件内容错误
     * @return
     */
    private String getError(List<EmployeeInfo> list) {
    	//手工进行判断	如果错误返回list=0	errormsg=""
    	List<String> codelist = new ArrayList<String>();
    	List<String> cardlist = new ArrayList<String>();
    	StringBuffer errorMsg = new StringBuffer();
        for(int i=0;i<list.size();i++) {
        	EmployeeInfo tmp = list.get(i);
        	if(tmp.getEmployee_code() == null || tmp.getEmployee_card() == null || tmp.getEmployee_name() == null) {
        		if(tmp.getEmployee_code() == null && tmp.getEmployee_card() == null && tmp.getEmployee_name() == null) {
        			continue;
        		} else {
        			if(tmp.getEmployee_code() == null)
        				errorMsg.append("行").append(i+3).append("列B").append("不能为空.");
        			else if(tmp.getEmployee_card() == null)
        				errorMsg.append("行").append(i+3).append("列C").append("不能为空.");
        			else if(tmp.getEmployee_name() == null)
        				errorMsg.append("行").append(i+3).append("列A").append("不能为空.");
        		}
        	} else {
	        	if (tmp.getEmployee_code() != null && tmp.getEmployee_code().length() >14) {
	        		errorMsg.append("员工号").append(tmp.getEmployee_code()).append("不能超过14位.");
	            } else if(tmp.getEmployee_card() != null && tmp.getEmployee_card().length() >14) {
	            	errorMsg.append("卡号").append(tmp.getEmployee_code()).append("不能超过14位.");
	            } else if(tmp.getEmployee_name() != null && tmp.getEmployee_name().length()>16) {
	            	errorMsg.append("姓名").append(tmp.getEmployee_code()).append("不能超过16位.");
	            }
	        	if(errorMsg.length() > 0)
	        		return errorMsg.toString();
        	}
        	if(!codelist.contains(tmp.getEmployee_code())) {
        		codelist.add(tmp.getEmployee_code());
        	}else {
        		errorMsg.append("员工号").append(tmp.getEmployee_code()).append("不能重复.");
        	}
        		
        	if(!cardlist.contains(tmp.getEmployee_card()))
        		cardlist.add(tmp.getEmployee_card()); 
        	else {
        		errorMsg.append("卡号").append(tmp.getEmployee_card()).append("不能重复.");
        	}
        }
        return errorMsg.toString();
    }
    /**
     * 获取文件内容列表
     * @return
     */
    private List < EmployeeInfo > formatSimInfos(List < EmployeeInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
        List < EmployeeInfo > ret = new ArrayList < EmployeeInfo >();
        for (EmployeeInfo tmp : list) {
            if (tmp.getEmployee_code() != null
                    && tmp.getEmployee_code().length() == 0
                    && tmp.getEmployee_card() != null
                    && tmp.getEmployee_card().length() == 0
                    && tmp.getEmployee_name() != null
                    && tmp.getEmployee_name().length() == 0) {
                // 文件行数据为空
                continue;
            } else if(tmp.getEmployee_code() == null
                    && tmp.getEmployee_card() == null
                    && tmp.getEmployee_name() == null){
            	 continue;
            }else {
                // 创建用户ID
                tmp.setCreater(currentUser.getUserID());
                // 修改用户ID
                tmp.setModifier(currentUser.getUserID());
                tmp.setEnterprise_id(currentUser.getEntiID());
                //tmp.setOrganization_id(currentUser.getOrganizationID());
                
                // 设置省市县ID信息
                //tmp.setStudentProvince(studentInfo.getStudentProvince());
                //tmp.setStudentCity(studentInfo.getStudentCity());
                //tmp.setStudentDistrict(studentInfo.getStudentDistrict());
                tmp.setOrganization_id(studentInfo.getOrganization_id());
                
                // 添加到list中
                ret.add(tmp);
            }
        }

        return ret;
    }
   
    /**
     * 导入员工信息
     * @return
     * @throws BusinessException 
     */
    public String importEmployee() throws BusinessException {
        /*if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            getGeoInfos();
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))&&!"xlsx".equals(fileFileName.substring(
                        fileFileName.length() - 4, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            getGeoInfos();
            return ERROR;
        }*/
        List < EmployeeInfo > list = new ArrayList < EmployeeInfo >();
        //List < StudentInfo > list = new ArrayList < StudentInfo >();

        //FileInputStream fis = null;
        try {
            ///fis = new FileInputStream(file);
            
    		ExcelParser parser = new ExcelParser(file);
    		List<List<Object>> datas = parser.getDatasInSheet(0);
    		List<Object> title = datas.get(1);
    		if(!title.get(1).toString().equals("员工号")||!title.get(2).toString().equals("卡号")||!title.get(0).toString().equals("姓名")||!title.get(3).toString().equals("备注")) {
            	addActionError("导入表格表头格式有误，请您使用模板格式！");
                return ERROR;
            }
    		
    		for (int i = 2; i < datas.size(); i++) {
    			List<Object> row = datas.get(i);
    			//if(row.get(0)!=null&&row.get(1)!=null&&row.get(2)!=null) {
	    			EmployeeInfo emp = new EmployeeInfo();
	    			if(row.size()>0)
	    			emp.setEmployee_name(row.get(0)==null?null:row.get(0).toString());
	    			if(row.size()>1)
	    			emp.setEmployee_code(row.get(1)==null?null:row.get(1).toString());
	    			if(row.size()>2)
	    			emp.setEmployee_card(row.get(2)==null?null:row.get(2).toString());
	    			if(row.size()>3)
	    			emp.setRemarks(row.get(3)==null?null:row.get(3).toString());
	    			list.add(emp);
    			//}
    		}
            
            /*List<EmployeeInfo> list22 = excelImplortor.getInvalideDataList();
            String errMessage = getFileContentError(excelImplortor.getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                getGeoInfos();
                return ERROR;
            }*/
            String errorStr = getError(list);
            if(errorStr.length()>0) {
            	addActionError(errorStr);
                return ERROR;
            }
        } catch (Exception e) {
        	addActionError(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            getGeoInfos();
            return ERROR;
        } finally {
            // 关闭流
            /*if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }*/
        }
        // 格式化数据
        list = formatSimInfos(list);
        int errcount = 0;
        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            getGeoInfos();
            return ERROR;
        } else {
        	StringBuffer errMsg = new StringBuffer();
        	for (EmployeeInfo tmp : list) {
        		int img = service.getCount("StudentManage.checkStudentCode1", tmp);
        		int cnt = service.getCount("StudentManage.checkStudentCard1", tmp);
        		if(!check_code(tmp.getEmployee_card())) {
        			errcount++;
        			errMsg.append("卡号为").append(tmp.getEmployee_card()).append(",只能为字母或数字.");
        		}
//        		else if(!check_code(tmp.getEmployee_code())) {
//        			errcount++;
//        			String msg = String.format("员工号%s,只能为字符或数字.", tmp.getEmployee_code());
//      		      	errMsg = errMsg.concat(msg);
//        		} 
        		else {
	                if (img > 0&&cnt > 0) {
	      		      errMsg.append("员工号为").append(tmp.getEmployee_code()).append(",卡号为").append(tmp.getEmployee_card()).append("的员工已存在.");
	      		      errcount++;
	                } 
	                else if(img > 0){
	      		      errMsg.append("员工号为").append(tmp.getEmployee_code()).append("的员工已存在.");
	      		      errcount++;
	                }
	                else if (cnt > 0) {
	      		      errMsg.append("卡号为").append(tmp.getEmployee_code()).append("的员工已存在.");
	      		      errcount++;
	                } 
        		}
                if (errcount > 3) {
                    addActionError(errMsg.toString());
                    //getGeoInfos();
                    return ERROR;
                }
  		  }
        	if (errMsg.length() > 0) {
                addActionError(errMsg.toString());
                //getGeoInfos();
                return ERROR;
            }
        	
        	
        	
            try {
                // 导入SIM卡信息
                employeeManageService.importEmployeeInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                getGeoInfos();
                return ERROR;
            } catch (Exception e) {
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                getGeoInfos();
                return ERROR;
            } finally {
                // 设置操作描述
                this.addOperationLog(formatLog("导入员工信息", null));
                // 设置操作类型
                this.setOperationType(Constants.IMPORT);
                // 设置所属应用系统
                this.setApplyId(Constants.CLW_P_CODE);
                // 设置所属模块
                this.setModuleId(MouldId.XCP_STUDENTMANAGE_IMPORT);
            }
        }

        return SUCCESS;
    }
    private Boolean check_code(String aaa){
    	for(int i=0;i<aaa.length();i++) {
    		int ls = aaa.codePointAt(i);
    		if((ls<48)||((ls>57)&(ls<65))||(ls>122)||((ls>90)&(ls<97))) {
    			return false;}
    		
    	}
    	return true;
    }
    
    /**
     * 导出员工信息
     * @return
     */
    public String exportEmployee() throws UnsupportedEncodingException {
        List < StudentInfo > list = new ArrayList < StudentInfo >();
        try {
            UserInfo user = getCurrentUser();
            
            if(null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            
            studentInfo.setEnterprise_id(user.getEntiID());
            //studentInfo.setOrganization_id(user.getOrganizationID());
            
            studentInfo.setStudent_code(SearchUtil.formatSpecialChar(studentInfo.getStudent_code()));
            studentInfo.setOrganization_id(SearchUtil.formatSpecialChar(studentInfo.getOrganization_id()));
            studentInfo.setStudent_class(SearchUtil.formatSpecialChar(studentInfo.getStudent_class()));
            studentInfo.setStudent_name(SearchUtil.formatSpecialChar(studentInfo.getStudent_name()));

            list = (List < StudentInfo >) service.getObjects("StudentManage.getInfos", studentInfo);
                    //"StudentManage.exportStudentInfos", studentInfo);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Student.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("员工信息");

            excelExporter.putAutoExtendSheets("exportEmployee", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
		String fileName=URLEncoder.encode("员工信息","UTF-8");
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename="+fileName+"-"+ DateUtil.getCurrentDayTime() + ".xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return null;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            this.addOperationLog(formatLog("导出员工信息", null));
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUDENTMANAGE_EXPORT);
        }
        // 导出文件成功
        return null;
    }

    /**
     * 验证员工号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStudentCode(String studentCode,String enid) {
        boolean ret = false;
        //UserInfo user=new UserInfo ();
        //user = getCurrentUser();
        StudentInfo stu=new StudentInfo();
        stu.setStudent_code(studentCode);
        //stu.setOrganization_id(user.getOrganizationID());
        stu.setOrganization_id(enid);
        try {
          
            int cnt = service.getCount("StudentManage.checkStudentCode", stu);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证员工号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    /**
     * 修改验证员工号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStudentCode1(String studentCode,String enid) {
        boolean ret = false;
        //UserInfo user=new UserInfo ();
        //user = getCurrentUser();
        StudentInfo stu=new StudentInfo();
        stu.setStudent_code(studentCode);
        //stu.setOrganization_id(user.getOrganizationID());
        stu.setOrganization_id(enid);
        try {
          
            int cnt = service.getCount("StudentManage.checkStudentCode", stu);
            if (cnt <= 1) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证员工号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    
    /**
     * 添加验证员工卡号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStudentCard(String studentCard,String enid) {
        boolean ret = false;
        //UserInfo user=new UserInfo ();
        //user = getCurrentUser();
        StudentInfo stu=new StudentInfo();
        stu.setStudent_card(studentCard);
        //stu.setOrganization_id(user.getOrganizationID());
        stu.setOrganization_id(enid);
        try {
        	
            int cnt = service.getCount("StudentManage.checkStudentCard", stu);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证卡号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    /**
     * 修改验证员工卡号唯一性
     * @param studentCode
     * @return
     */
    public boolean checkStudentCard1(String studentCard,String enid) {
        boolean ret = false;
        //UserInfo user=new UserInfo ();
        //user = getCurrentUser();
        StudentInfo stu=new StudentInfo();
        stu.setStudent_card(studentCard);
        //stu.setOrganization_id(user.getOrganizationID());
        stu.setOrganization_id(enid);
        try {
        	
            int cnt = service.getCount("StudentManage.checkStudentCard", stu);
            if (cnt<= 1) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("验证卡号唯一性DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
}

package com.neusoft.clw.infomanage.studentmanage.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.fileupload.domain.UploadFileInfo;
import com.neusoft.clw.infomanage.studentmanage.domain.CommonMapBean;
import com.neusoft.clw.infomanage.studentmanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

public class StudentManageAction extends PaginationAction{
    /** service共通类 */
    private transient Service service;
    
    private transient StudentManageService studentManageService;
    
    private static final String FORBID_ACTION = "forbid";

    /** 显示数据list **/
    private List < StudentInfo > studentList;

    /** 修改数据用 **/
    private StudentInfo studentInfo;

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
        if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
//            setMessage("student.getin.nopermission");
//            addActionMessage(getText(message));
            return FORBID_ACTION;
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 浏览学生信息
     * @return
     */
    public String studentBrowse() {
        final String browseTitle = "浏览学生信息";
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            UserInfo user = getCurrentUser();
            
            if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
                // 未经过认证
                return SUCCESS;
            }
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setOrganization_id(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
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
        
        if (!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return ERROR;
        }
        
        if (null != message) {
            addActionMessage("学生信息填加失败！");
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
        return SUCCESS;
    }

    /**
     * 添加学生
     */
    public String add() {
        if (null == studentInfo) {
            return addBefore();
        }
        final String addTitle = "填加学生信息";
        log.info(addTitle);
        try {
            UserInfo user = getCurrentUser();
            studentInfo.setCreater(user.getUserID());
            studentInfo.setModifier(user.getUserID());
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setOrganization_id(user.getOrganizationID());
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
        setMessage("student.addsuccess.message");
        
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_ADD);
        return SUCCESS;
    }

    /**
     * 修改学生页面
     */
    public String editBefore() {
        final String editBefTitle = getText("student.editbefore.title");
        UserInfo user = getCurrentUser();
        
        if (!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return ERROR;
        }
        
        if (null != message) {
            addActionMessage("学生信息修改失败");
        }
        
        log.info(editBefTitle);
        try {
            if (null != studentInfo) {
                if (sexSysMap != null && sexSysMap.size() == 0) {
                    sexSysMap = Constants.SEX_SYS_MAP;
                }
                if (relativeTypeList == null || relativeTypeList.size() == 0) {
                    initRelativeTypeList();
                }
                
                studentInfo = (StudentInfo) service.getObject("StudentManage.getStudentInfo", studentInfo);
                if (null == studentInfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
                studentInfo.setOld_student_card(studentInfo.getStudent_card());
                
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
        }
        return SUCCESS;
    }
    
    /**
     * 显示学生相片
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
                log.debug("显示学生相片出现错误：" + e.toString());
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
     * 修改学生
     */
    public String updateStudent() {
        if (null == studentInfo) {
            return editBefore();
        }
        final String editTitle = getText("student.update");
        try {
            UserInfo user = getCurrentUser();
            studentInfo.setModifier(user.getUserID());
            String updateStr = "";
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

                // 创建附件信息bean
                studentInfo.setPhotoContent(content);
                //studentInfo.setPhoto_name(fileFileName);
                studentInfo.setPhoto_name(DateFormatUtil.getYYYYMMDDHHMISS()+ ".jpg");
                updateStr = "StudentManage.updateInfoAndPhotobyStudentId";
            **/
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
        setMessage("student.editsuccess.message");
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_STUDENTMANAGE_MODIFY);
        return SUCCESS;
    }

    /**
     * 删除学生
     */
    public String deleteStudent() {
        if (null == studentInfo) {
            return editBefore();
        }
        final String cancleTitle = getText("student.delete");
        try {
            UserInfo user = getCurrentUser();
            studentInfo.setVaset_user_id(user.getUserID());
            int i = service.getCount("StudentManage.getInUseCount", studentInfo);
            if (i > 0) {
                setMessage("student.delete.nopermission");
                return FORBID;
            } else {
                service.update("StudentManage.deletebyStudentId", studentInfo);
            }
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("student.delete.success");
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
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getStudent_name(),
                    s.getStudent_code(),
                    "<span id='"+ s.getStudent_id() +"'>"+ s.getStudent_card() +"</span>",
                    s.getStudent_school(), 
                    s.getStudent_class(),
                    s.getStudent_sex(),
                    s.getStudent_birth(),
                    s.getStudent_address(),
                    s.getTeacher_name(),
                    s.getTeacher_tel(),
                    s.getRelative_name(),
                    s.getRelative_type(),
                    s.getRelative_tel(),
                    s.getPhoto_name()});
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
    public String importStudentBefore() {
        UserInfo user = getCurrentUser();
        
        if (!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
            return ERROR;
        }
        
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
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), tmp.getMessage());
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }

    /**
     * 获取文件内容列表
     * @return
     */
    private List < StudentInfo > formatSimInfos(List < StudentInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        
        if ("710000".equals(studentInfo.getStudentProvince())
                || "810000".equals(studentInfo.getStudentProvince())
                || "820000".equals(studentInfo.getStudentProvince())) {
            // 港、澳、台地区市县信息填写
            studentInfo.setStudentCity(studentInfo.getStudentProvince());
            studentInfo.setStudentDistrict(studentInfo.getStudentProvince());
        }
        
        List < StudentInfo > ret = new ArrayList < StudentInfo >();

        for (StudentInfo tmp : list) {
            if (tmp.getStudent_code() != null
                    && tmp.getStudent_code().length() == 0
                    && tmp.getStudent_card() != null
                    && tmp.getStudent_card().length() == 0
                    && tmp.getStudent_name() != null
                    && tmp.getStudent_name().length() == 0
                    && tmp.getStudent_sex() != null
                    && tmp.getStudent_sex().length() == 0
                    && tmp.getTeacher_name() != null
                    && tmp.getTeacher_name().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 创建用户ID
                tmp.setCreater(currentUser.getUserID());
                // 修改用户ID
                tmp.setModifier(currentUser.getUserID());
                tmp.setEnterprise_id(currentUser.getEntiID());
                tmp.setOrganization_id(currentUser.getOrganizationID());
                
                // 设置省市县ID信息
                tmp.setStudentProvince(studentInfo.getStudentProvince());
                tmp.setStudentCity(studentInfo.getStudentCity());
                tmp.setStudentDistrict(studentInfo.getStudentDistrict());
                
                // 添加到list中
                ret.add(tmp);
            }
        }

        return ret;
    }

    /**
     * 导入学生信息
     * @return
     */
    public String importStudent() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            getGeoInfos();
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            getGeoInfos();
            return ERROR;
        }

        List < StudentInfo > list = new ArrayList < StudentInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                setMessage("file.import.error");
                log.error("Import file error:" + e.getMessage());
                getGeoInfos();
                return ERROR;
            }

            list = excelImplortor.getSheetData("importStudent", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                getGeoInfos();
                return ERROR;
            }
        } catch (Exception e) {
            setMessage(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            getGeoInfos();
            return ERROR;
        } finally {
            // 关闭流
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 格式化数据
        list = formatSimInfos(list);

        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            getGeoInfos();
            return ERROR;
        } else {
            try {
                // 导入SIM卡信息
                studentManageService.importStudentInfos(list);
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
                this.addOperationLog(formatLog("导入学生信息", null));
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
    
    /**
     * 导出学生信息
     * @return
     */
    public String exportStudent() {
        List < StudentInfo > list = new ArrayList < StudentInfo >();
        try {
            UserInfo user = getCurrentUser();
            
            if(!PasswordConfirmAction.USER_IS_CHECK.equals(user.getUserIsCheck())) {
                // 未经过认证
                return SUCCESS;
            }
            
            if(null == studentInfo) {
                studentInfo = new StudentInfo();
            }
            
            studentInfo.setEnterprise_id(user.getEntiID());
            studentInfo.setOrganization_id(user.getOrganizationID());
            
            studentInfo.setStudent_code(SearchUtil.formatSpecialChar(studentInfo.getStudent_code()));
            studentInfo.setStudent_school(SearchUtil.formatSpecialChar(studentInfo.getStudent_school()));
            studentInfo.setStudent_class(SearchUtil.formatSpecialChar(studentInfo.getStudent_class()));
            studentInfo.setStudent_name(SearchUtil.formatSpecialChar(studentInfo.getStudent_name()));
            
            list = (List < StudentInfo >) service.getObjects(
                    "StudentManage.exportStudentInfos", studentInfo);
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
            excelExporter.setTitle("学生信息");

            excelExporter.putAutoExtendSheets("exportStudent", 0, list);
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
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename=StudentsInfo-"+ DateUtil.getCurrentDayTime() + ".xls");
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
            this.addOperationLog(formatLog("导出学生信息", null));
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

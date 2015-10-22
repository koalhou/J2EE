package com.neusoft.clw.sysmanage.datamanage.usermanage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */

public class studentAction extends PaginationAction {

	private transient Service service;
	
    private StudentInfo info;    
    
    private String stu_id;
      
    private String checkBoxHtml;
    
    private String stu_name;
    
    private String stu_code;
    
    private String stu_school;
    
    private String stu_class;
    
    private String site_desc;
    
    private String flag;
    
    private String upnotexist;
    
    private String downnotexist;
    
    //上行删除已选的学生
    private String delupexistdata;
    //下行删除已选的学生
    private String deldownexistdata;
    
    //待选查询列表中，去除被选中的学生
    private String searchparam;
    
    private String route_id;
    
    private String trip_id;
    
    private String upstudentids;
    private String existdata;//2013-10-16添加查询字段 所属组织
    private String stu_org;//2013-10-16添加查询字段 所属组织
    private String stu_remark;//2013-10-16添加查询字段 学生备注
    
	public String getUpstudentids() {
		return upstudentids;
	}

	public void setUpstudentids(String upstudentids) {
		this.upstudentids = upstudentids;
	}

	public String getDelupexistdata() {
		return delupexistdata;
	}

	public void setDelupexistdata(String delupexistdata) {
		this.delupexistdata = delupexistdata;
	}

	public String getDeldownexistdata() {
		return deldownexistdata;
	}

	public void setDeldownexistdata(String deldownexistdata) {
		this.deldownexistdata = deldownexistdata;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getUpnotexist() {
		return upnotexist;
	}

	public void setUpnotexist(String upnotexist) {
		this.upnotexist = upnotexist;
	}

	public String getDownnotexist() {
		return downnotexist;
	}

	public void setDownnotexist(String downnotexist) {
		this.downnotexist = downnotexist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSite_desc() {
		return site_desc;
	}

	public void setSite_desc(String site_desc) {
		this.site_desc = site_desc;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_code() {
		return stu_code;
	}

	public void setStu_code(String stu_code) {
		this.stu_code = stu_code;
	}

	public String getStu_school() {
		return stu_school;
	}

	public void setStu_school(String stu_school) {
		this.stu_school = stu_school;
	}

	public String getStu_class() {
		return stu_class;
	}

	public void setStu_class(String stu_class) {
		this.stu_class = stu_class;
	}

	public String getCheckBoxHtml() {
		return checkBoxHtml;
	}

	public void setCheckBoxHtml(String checkBoxHtml) {
		this.checkBoxHtml = checkBoxHtml;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	/** 车辆列表 **/
    private List < StudentInfo > studentList;
    
    private Map map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

	
    public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public StudentInfo getInfo() {
		return info;
	}

	public void setInfo(StudentInfo info) {
		this.info = info;
	}

	public List<StudentInfo> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentInfo> studentList) {
		this.studentList = studentList;
	}
	
	public String getSearchparam() {
		return searchparam;
	}

	public void setSearchparam(String searchparam) {
		this.searchparam = searchparam;
	}

	public String getExistdata() {
		return existdata;
	}

	public void setExistdata(String existdata) {
		this.existdata = existdata;
	}

	public String getStu_org() {
		return stu_org;
	}

	public void setStu_org(String stu_org) {
		this.stu_org = stu_org;
	}

	public String getStu_remark() {
		return stu_remark;
	}

	public void setStu_remark(String stu_remark) {
		this.stu_remark = stu_remark;
	}

	/**
     * 展示关联学生列表-跳转页面
     */
    public String studentListSearch() {
        return SUCCESS;
    }
    
    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {

        try {
        	UserInfo user = getCurrentUser();
        	if(info == null){
    	    	info = new StudentInfo();
            }
        	  info.setOrganization_id(user.getOrganizationID());
              info.setEnterprise_id(user.getEntiID());
        	studentList = (List < StudentInfo >) service.getObjects(
                    "User.getStudentList", info);

            if (studentList != null && studentList.size() == 0) {
                // 无用户信息
                addActionError("无任何记录信息！");
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError("数据库操作失败！");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError("数据库操作失败！");
            log.error(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 展示关联车辆列表
     */
    public String vehList() {
        final String vehTitle = "学生选择列表";
        int totalCount = 0;
        UserInfo user = getCurrentUser();

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        // 每页显示条数
        String rpNum = request.getParameter("rp");
        // 当前页码
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        try {  
        	    if(info == null){
        	    	info = new StudentInfo();
                }
        	    info.setOrganization_id(user.getOrganizationID());
                info.setEnterprise_id(user.getEntiID());
                totalCount = service.getCount("User.getCountST", info);

                studentList = (List < StudentInfo >) service.getObjectsByPage(
                        "User.getStudentList", info, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));


        this.map = getPagination(studentList, totalCount, pageIndex);

            // 设置操作描述
            //this.addOperationLog(formatLog(vehTitle, null));
            // 设置操作类型
            //this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            //this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            //this.setModuleId(MouldId.YTP_OILMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 为了行车规划做的学生列表
     */
    public String stList() {
        final String vehTitle = "学生选择列表";
        int totalCount = 0;
        UserInfo user = getCurrentUser();

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        // 每页显示条数
        String rpNum = request.getParameter("rp");
        // 当前页码
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        try {  
        	    if(info == null){
        	    	info = new StudentInfo();
                }
                info.setOrganization_id(user.getOrganizationID());
                info.setEnterprise_id(user.getEntiID());
                info.setSortname(sortName);
                info.setSortorder(sortOrder);
                //info.setRp(rpNum);
                //info.setPage(pageIndex);
                totalCount = service.getCount("RidingPlan.getCountST", info);

                studentList = (List < StudentInfo >) service.getObjectsByPage(
                        "RidingPlan.getStudentList", info, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));


        this.map = getPagination(studentList, totalCount, pageIndex);

            // 设置操作描述
            //this.addOperationLog(formatLog(vehTitle, null));
            // 设置操作类型
            //this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            //this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            //this.setModuleId(MouldId.YTP_OILMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 为了行车规划做的新学生列表
     */
    public String stList2() {
    	 final String vehTitle = "行程规划人员选择列表";
         UserInfo user = getCurrentUser();
         try {  
         	    if(info == null)
         	    	info = new StudentInfo();
                 info.setOrganization_id(user.getOrganizationID());
                 info.setEnterprise_id(user.getEntiID());
                 info.setStu_name(stu_name);
                 info.setStu_code(stu_code);
                 info.setOrganization_id_s(stu_org);//2013-10-16添加查询字段
                 info.setStu_remark(stu_remark);//2013-10-16添加查询字段
                 info.setExistdata(existdata);//右边已选人员过滤
                 studentList = (List < StudentInfo >) service.getObjects("RidingPlan.getStudentList", info);
         } catch (BusinessException e) {
             addActionError(getText(e.getMessage()));
             log.error(vehTitle, e);
             return ERROR;
         }
        return SUCCESS;
    }
    
    public void getupstudentname() {
        final String vehTitle = "取得学生姓名";
        String studentname="";
        try {  
        	    if(info == null){
        	    	info = new StudentInfo();
                }
                info.setUpstudentids(upstudentids);
                studentname = (String) service.getObject("RidingPlan.getupstudentname", info);
                
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setCharacterEncoding("UTF-8");

                try {
                    response.getWriter().write(studentname);
                } catch (IOException ioException) {
                    ;
                }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
        }

    }
    
    /**
     * 展示详细
     */
    public String stShow() {
    	final String vehTitle = "学生选择列表";
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");// 每页显示条数
        String pageIndex = request.getParameter("page");// 当前页码
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        try {  
        	    if(info == null)
        	    	info = new StudentInfo();
                info.setOrganization_id(user.getOrganizationID());
                info.setEnterprise_id(user.getEntiID());
                info.setSortname(sortName);
                info.setSortorder(sortOrder);
                if(info.getStu_id()==null||"none".equals(info.getStu_id())||"".equals(info.getStu_id())){
                	info.setStu_id("-1");
                }
                totalCount = service.getCount("RidingPlan.getCountSTShow", info);

                studentList = (List < StudentInfo >) service.getObjectsByPage(
                        "RidingPlan.getStudentShow", info, (Integer
                                .parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));


        this.map = getPagination2(studentList, totalCount, pageIndex);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }
    /**
     * 新选学生专用
     */
    public String stShow2() {
    	final String vehTitle = "学生选择列表";
        try {  
        	    if(info == null){
        	    	info = new StudentInfo();
                }
        	    info.setSortname("STU_CODE");
                info.setSortorder("asc");
                studentList = (List < StudentInfo >) service.getObjects(
                        "RidingPlan.getStudentShow", info);     
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List stList, int totalCount, String pageIndex) {
    	
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < stList.size(); i++) {

            StudentInfo s = (StudentInfo) stList.get(i);

            Map cellMap = new LinkedHashMap();
            
            String flag = findStudent(s.getStu_id());
            String html = initcheckBoxHtml(s.getStu_id(),s.getStu_name(),flag);

            cellMap.put("id", s.getStu_id());

            cellMap.put("cell", new Object[] {
            		html,
            		s.getStu_code(),
            		s.getStu_name(), 
                    s.getStu_school(),
                    s.getStu_class(),
                    flag});
            mapList.add(cellMap);
        }
        
        // if (null == oilManage) {
        // oilManage = new OilManage();
        // }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination2(List stList, int totalCount, String pageIndex) {
    	
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < stList.size(); i++) {

            StudentInfo s = (StudentInfo) stList.get(i);

            Map cellMap = new LinkedHashMap();
                       
            cellMap.put("id", s.getStu_id());

            cellMap.put("cell", new Object[] {
            		null,
            		i+1,
            		s.getStu_name(), 
            		s.getStu_code(),
                    s.getOrganization_name(),
                    s.getStu_remark()});
            mapList.add(cellMap);
        }
        
        // if (null == oilManage) {
        // oilManage = new OilManage();
        // }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 判断学生是否选中
     * @return
     */
    private String findStudent(String my_stu_id) {

		String flag = "0";
		if (StringUtils.isNotEmpty(my_stu_id)) {
			String[] stu_ids = stu_id.split(",");
			for (int i = 0; i < stu_ids.length; i++) {
               if(stu_ids[i].equals(my_stu_id)){
            	   flag="1";
            	   break;
               }
			}
		}
		return flag;
	}
    private String initcheckBoxHtml(String stu_id,String stu_name,String flag){    	
    	//checkBoxHtml=<input type="checkbox" name="checkstudent"  onclick="operate2()"
        String html = checkBoxHtml;
    	String value = " value=\""+stu_id+","+stu_name+"\" ";
    	String checked = "1".equals(flag)?" checked =\"checked\" />":" />";
    	return html+value+checked;
    }
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

}

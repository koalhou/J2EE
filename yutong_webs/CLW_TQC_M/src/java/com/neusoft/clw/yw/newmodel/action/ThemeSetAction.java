package com.neusoft.clw.yw.newmodel.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.newmodel.domain.ClwJcParamCfgT;
import com.neusoft.clw.yw.newmodel.domain.Theme;
import com.opensymphony.xwork2.ActionContext;

public class ThemeSetAction extends PaginationAction {
	// 日志信息
	Log log = LogFactory.getLog(ThemeSetAction.class);
	private ClwJcParamCfgT paramSet;
	private String message;
	private String errorMessage;
	private transient Service service;
	private Map<String, Object> jsonMap;
	private String filepath = "c:/image/";// "/opt/m2mfile/ftp/ygbapk/";
	private static String linux_path = "/opt/m2mfile/ftp/themephoto/";
	
	private ByteArrayInputStream inputStream;

	private List<Theme> userList = new ArrayList<Theme>();
	private Map userMap = new HashMap();
	private Theme theme;

	private List<File> file;
	private File fileheader;
	private File fileheadbg;
	private File filelogo;
	private String fileheaderFileName;
	private String fileheadbgFileName;
	private String filelogoFileName;

	// 导航图标文件
	private File jkheader;
	private File ylheader;
	private File ddheader;
	private File tjheader;
	private File szheader;
	private File jkactiveheader;
	private File ylactiveheader;
	private File ddactiveheader;
	private File tjactiveheader;
	private File szactiveheader;

	private String jkheaderFileName;
	private String ylheaderFileName;
	private String ddheaderFileName;
	private String tjheaderFileName;
	private String szheaderFileName;
	private String jkactiveheaderFileName;
	private String ylactiveheaderFileName;
	private String ddactiveheaderFileName;
	private String tjactiveheaderFileName;
	private String szactiveheaderFileName;

	private List<String> fileFileName;
	private List<String> fileContentType;

	private String userId;
	private String id;
	private String userIdStr;

	private String footcolor;
	private String footbgcolor;
	private String theme_lon;
	private String theme_lat;
	/**
     * 显示相片
     * @return
     */
    public String showPhoto() {
        try {
        	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext()
			.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            
            Map<String, String> map = new HashMap<String, String>();
			map.put("id", request.getParameter("id"));
			
			this.theme = (Theme) service.getObject("ThemeManage.getThemeDefault", map);
            
            ByteArrayInputStream input = null;
            try {
            	if(request.getParameter("type").equals("headerclass")) {
            		input = new ByteArrayInputStream(this.theme.getHeaderclass_p());
            	}else if(request.getParameter("type").equals("headbgclass")) {
            		input = new ByteArrayInputStream(this.theme.getHeadbgclass_p());
            	}else if(request.getParameter("type").equals("companylogo")) {
            		input = new ByteArrayInputStream(this.theme.getCompanylogo_p());
            	}else if(request.getParameter("type").equals("mi1")) {
            		input = new ByteArrayInputStream(this.theme.getMi1_p());
            	}else if(request.getParameter("type").equals("mi2")) {
            		input = new ByteArrayInputStream(this.theme.getMi2_p());
            	}else if(request.getParameter("type").equals("mi3")) {
            		input = new ByteArrayInputStream(this.theme.getMi3_p());
            	}else if(request.getParameter("type").equals("mi4")) {
            		input = new ByteArrayInputStream(this.theme.getMi4_p());
            	}else if(request.getParameter("type").equals("mi5")) {
            		input = new ByteArrayInputStream(this.theme.getMi5_p());
            	}else if(request.getParameter("type").equals("nav1focus")) {
            		input = new ByteArrayInputStream(this.theme.getNav1focus());
            	}else if(request.getParameter("type").equals("nav2focus")) {
            		input = new ByteArrayInputStream(this.theme.getNav2focus());
            	}else if(request.getParameter("type").equals("nav3focus")) {
            		input = new ByteArrayInputStream(this.theme.getNav3focus());
            	}else if(request.getParameter("type").equals("nav4focus")) {
            		input = new ByteArrayInputStream(this.theme.getNav4focus());
            	}else if(request.getParameter("type").equals("nav5focus")) {
            		input = new ByteArrayInputStream(this.theme.getNav5focus());
            	}
            } catch (Exception e) {
                log.debug("显示驾驶员相片出现错误：" + e.toString());
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
	 * 进入参数设置管理界面
	 * 
	 * @return SUCCESS
	 */
	public String init() {
		ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION, "-> 系统设置 -> 用户界面设置");
		log.info("进入用户界面设置管理界面");
		return SUCCESS;
	}

	/**
	 * 参数设置列表
	 * 
	 * @return SUCCESS
	 */
	@SuppressWarnings("unchecked")
	public String getThemeSetList() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

		String rpNum = "";
		String pageIndex = "";

		pageIndex = request.getParameter("page");
		rpNum = request.getParameter("rp");

		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		// 查询管理系统用户信息
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("loginName", request.getParameter("loginName"));
			map.put("entipriseId", request.getParameter("entipriseId"));
			map.put("sortname", sortName);
			map.put("sortorder", sortOrder);

			int totalCount = 0;

			totalCount = service.getCount("ThemeManage.getCount", map);

			userList = (List<Theme>) service
					.getObjectsByPage(
							"ThemeManage.getThemeDetails",
							map,
							(Integer.parseInt(pageIndex) - 1)
									* Integer.parseInt(rpNum),
							Integer.parseInt(rpNum));

			if (userList != null && userList.size() == 0) {
				addActionError(getText("common.no.data"));
			} else {
			}
			// 显示操作成功信息
			if (null != "") {
				addActionMessage(getText(""));
			}
			this.userMap = getPagination(userList, totalCount, pageIndex, rpNum);

		} catch (BusinessException e) {
			super.addActionError(getText("info.db.error"));
			log.error("Query user error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			super.addActionError(getText("info.db.error"));
			log.error("Query user error:" + e.getMessage());
			return ERROR;
		} finally {
			// setOperationType(Constants.SELECT,
			// ModuleId.CLW_M_QX_USER_QUERY_MID);
			// addOperationLog("查询用户");
		}

		return SUCCESS;
	}

	public Map getPagination(List userList, int totalCount, String pageIndex,
			String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();

		for (int i = 0; i < userList.size(); i++) {

			Theme user = (Theme) userList.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", user.getUserId());

			cellMap.put(
					"cell",
					new Object[] {
							(i + 1) + (Integer.parseInt(pageIndex) - 1)
									* Integer.parseInt(rpNum),
							user.getUserName(), user.getLoginName(),
							user.getShortName(), user.getEnterpriseCode(),
							user.getId(), user.getId() });

			mapList.add(cellMap);

		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	/**
	 * 修改一个用户对象
	 */
	public String updatethemeBefore() {
		ActionContext.getContext().getSession()
				.put(Constants.CURRENT_LOCATION, "-> 系统设置 -> 修改用户界面");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", request.getParameter("id"));
		map.put("nodefault", "true");
		try {
			this.theme = (Theme) service.getObject("ThemeManage.getThemeDefault", map);

		} catch (Exception e) {
			log.error("修改参数设置信息错误", e);
		}
		return SUCCESS;
	}

	/**
	 * 修改一个用户对象
	 */
	public String addthemeBefore() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "default");
		map.put("default", "true");
		try {
			this.theme = (Theme) service.getObject(
					"ThemeManage.getThemeDefault", map);

		} catch (Exception e) {
			log.error("修改参数设置信息错误", e);
		}
		return SUCCESS;
	}

	public String delthemeset() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String id = request.getParameter("id");
		try {
			if (id != null)
				service.delete("ThemeManage.delThemeDetails", id);
		} catch (Exception e) {
			log.error("删除参数设置信息错误", e);
		}
		return SUCCESS;
	}

	public String mapGroundUpdate() {
		Theme theme = new Theme();
		theme.setId(id);
		theme.setIsmapgroundlogo(theme_lon + "," + theme_lat);
		try {
			service.update("ThemeManage.updateThemeDetails", theme);

			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("nodefault", "true");
			this.theme = (Theme) service.getObject(
					"ThemeManage.getThemeDefault", map);
		} catch (Exception e) {
			log.error("修改参数设置信息错误", e);
		}
		return SUCCESS;
	}

	public String imageuploadFile() {
		//得到工程保存图片的路径
		String root=null;
		if(getSystem().contains("windows")) 
			root = ServletActionContext.getRequest().getRealPath("/upload/");
		else
			root = linux_path;
        String extName;
        //循环上传的文件
        try {
        	Theme theme = new Theme();
        	if (fileheaderFileName!=null&&fileheaderFileName.lastIndexOf(".") >= 0) {
                extName = fileheaderFileName.substring(fileheaderFileName.lastIndexOf("."));
                	theme.setHeaderclass("background: url(../upload/"+userId+"/"+fileheaderFileName+") repeat-x left top; height: 80px;  min-width: 1256px; width:100%;");
                	InputStream is = new FileInputStream(fileheader);
				if (is != null) {
					int len = is.available();
					byte[] xml = new byte[len];
					is.read(xml);
					theme.setHeaderclass_p(xml);
					// stream(fileheader,fileheaderFileName,root,userId);
				}
        	}else {
            	extName = "";
            }
        	if (!checkIsImage(extName)&&extName!="") {
        		this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
        		return "error";
        	}
        	
        	if (fileheadbgFileName!=null&&fileheadbgFileName.lastIndexOf(".") >= 0) {
                extName = fileheadbgFileName.substring(fileheadbgFileName.lastIndexOf("."));
                theme.setHeadbgclass("background: url(../upload/"+userId+"/"+fileheadbgFileName+") no-repeat left top; float: left; height: 80px;width:100%;");
                InputStream is = new FileInputStream(fileheadbg);
				if (is != null) {
					int len = is.available();
					byte[] xml = new byte[len];
					is.read(xml);
					theme.setHeadbgclass_p(xml);
				}
                //stream(fileheadbg,fileheadbgFileName,root,userId);
            } else {
            	extName = "";
            }
        	if (!checkIsImage(extName)&&extName!="") {
        		this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
        		return "error";
        	}
        	
        	if (filelogoFileName!=null&&filelogoFileName.lastIndexOf(".") >= 0) {
                extName = filelogoFileName.substring(filelogoFileName.lastIndexOf("."));
                	theme.setCompanylogo("../upload/"+userId+"/"+filelogoFileName);
                	InputStream is = new FileInputStream(filelogo);
    				if (is != null) {
    					int len = is.available();
    					byte[] xml = new byte[len];
    					is.read(xml);
    					theme.setCompanylogo_p(xml);
    				}
                	//stream(filelogo,filelogoFileName,root,userId);
            } else {
            	extName = "";
            }
        	if (!checkIsImage(extName)&&extName!="") {
        		this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
        		return "error";
        	}
	            
	        theme.setUserId(userId);
	        theme.setId(id);
	        theme.setFooterclass("background: "+footbgcolor+"; color: "+footcolor+"; line-height: 25px; text-align: center; width:100%;min-width: 1256px;");
	        this.theme = theme;
	        service.update("ThemeManage.updateThemeDetails", theme);
	        
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS;
    }

	public String getSystem() {
		return System.getProperties().getProperty("os.name").toLowerCase();
	}

	public String imageuploadFileUsers() {
		// 得到工程保存图片的路径

		String root = null;
		if (getSystem().contains("windows"))
			root = ServletActionContext.getRequest().getRealPath("/upload/");
		else
			root = linux_path;
		String extName;
		// 循环上传的文件
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("default", "true");
			Theme defaultTheme = (Theme) service.getObject("ThemeManage.getThemeDefault", map);
			
			String[] str = userIdStr.split(",");
			for (String s : str) {
				Theme theme = new Theme();
				if (fileheaderFileName != null && fileheaderFileName.lastIndexOf(".") >= 0) {
					extName = fileheaderFileName.substring(fileheaderFileName.lastIndexOf("."));
					theme.setHeaderclass("background: url(../upload/"+userId+"/"+fileheaderFileName+ ") repeat-x left top; height: 80px;  min-width: 1256px; width:100%;");

					InputStream is = new FileInputStream(fileheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setHeaderclass_p(xml);
					}
					//stream(fileheader, fileheaderFileName, root, s);
				} else {
					extName = "";
					theme.setHeaderclass_p(defaultTheme.getHeaderclass_p());
					//theme.setHeaderclass("background: url(../images/theme/header_bg.png) repeat-x left top; height: 80px;  min-width: 1256px; width:100%;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				if (fileheadbgFileName != null && fileheadbgFileName.lastIndexOf(".") >= 0) {
					extName = fileheadbgFileName.substring(fileheadbgFileName.lastIndexOf("."));
					theme.setHeadbgclass("background: url(../upload/"+userId+"/"+fileheadbgFileName+ ") no-repeat left top; float: left; height: 80px;width:100%;");
					
					InputStream is = new FileInputStream(fileheadbg);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setHeadbgclass_p(xml);
					}
					//stream(fileheadbg, fileheadbgFileName, root, s);
				} else {
					extName = "";
					theme.setHeadbgclass_p(defaultTheme.getHeadbgclass_p());
					//theme.setHeadbgclass("background: url(../images/theme/header_bg2.png) no-repeat left top; float: left; height: 80px;width:100%;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				if (filelogoFileName != null && filelogoFileName.lastIndexOf(".") >= 0) {
					extName = filelogoFileName.substring(filelogoFileName.lastIndexOf("."));
					theme.setCompanylogo("../upload/" + s + "/" + filelogoFileName);
					
					InputStream is = new FileInputStream(filelogo);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setCompanylogo_p(xml);
					}
					//stream(filelogo, filelogoFileName, root, s);
				} else {
					extName = "";
					theme.setCompanylogo_p(defaultTheme.getCompanylogo_p());
					//theme.setCompanylogo("../images/theme/yutong_logo.png");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				JSONObject jsono = new JSONObject();
				// 车辆监控
				if (jkheaderFileName != null && jkheaderFileName.lastIndexOf(".") >= 0) {
					extName = jkheaderFileName.substring(jkheaderFileName.lastIndexOf("."));
					jsono.put("mi1", "background: url(../upload/" + s + "/" + jkheaderFileName + ") no-repeat center top;");
					
					InputStream is = new FileInputStream(jkheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setMi1_p(xml);
					}
					//stream(jkheader, jkheaderFileName, root, s);
				} else {
					extName = "";
					theme.setMi1_p(defaultTheme.getMi1_p());
					//jsono.put("mi1", "background: url(../images/theme/menu1.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}
				// 车辆监控活动图标
				if (jkactiveheaderFileName != null && jkactiveheaderFileName.lastIndexOf(".") >= 0) {
					extName = jkactiveheaderFileName.substring(jkactiveheaderFileName.lastIndexOf("."));
					jsono.put("nav1focus", "background: url(../upload/" + s+ "/" + jkactiveheaderFileName+ ") no-repeat center top;");
					
					InputStream is = new FileInputStream(jkactiveheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setNav1focus(xml);
					}
					//stream(jkactiveheader, jkactiveheaderFileName, root, s);
				} else {
					extName = "";
					theme.setNav1focus(defaultTheme.getNav1focus());
					//jsono.put("nav1focus", "background: url(../images/theme/menu1_focus.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				// 油量监控
				if (ylheaderFileName != null && ylheaderFileName.lastIndexOf(".") >= 0) {
					extName = ylheaderFileName.substring(ylheaderFileName.lastIndexOf("."));
					jsono.put("mi2", "background: url(../upload/" + s + "/" + ylheaderFileName + ") no-repeat center top;");
					
					InputStream is = new FileInputStream(ylheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setMi2_p(xml);
					}
					//stream(ylheader, ylheaderFileName, root, s);
				} else {
					extName = "";
					theme.setMi2_p(defaultTheme.getMi2_p());
					//jsono.put("mi2", "background: url(../images/theme/menu2.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}
				// 油量监控活动图标
				if (ylactiveheaderFileName != null && ylactiveheaderFileName.lastIndexOf(".") >= 0) {
					extName = ylactiveheaderFileName.substring(ylactiveheaderFileName.lastIndexOf("."));
					jsono.put("nav2focus", "background: url(../upload/" + s+ "/" + ylactiveheaderFileName+ ") no-repeat center top;");

					InputStream is = new FileInputStream(ylactiveheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setNav2focus(xml);
					}
					//stream(ylactiveheader, ylactiveheaderFileName, root, s);
				} else {
					extName = "";
					theme.setNav2focus(defaultTheme.getNav2focus());
					//jsono.put("nav2focus", "background: url(../images/theme/menu2_focus.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				// 调度中心
				if (ddheaderFileName != null && ddheaderFileName.lastIndexOf(".") >= 0) {
					extName = ddheaderFileName.substring(ddheaderFileName.lastIndexOf("."));
					jsono.put("mi3", "background: url(../upload/" + s + "/" + ddheaderFileName + ") no-repeat center top;");

					InputStream is = new FileInputStream(ddheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setMi3_p(xml);
					}
					//stream(ddheader, ddheaderFileName, root, s);
				} else {
					extName = "";
					theme.setMi3_p(defaultTheme.getMi3_p());
					//jsono.put("mi3", "background: url(../images/theme/menu8.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}
				// 调度中心活动图标
				if (ddactiveheaderFileName != null && ddactiveheaderFileName.lastIndexOf(".") >= 0) {
					extName = ddactiveheaderFileName.substring(ddactiveheaderFileName.lastIndexOf("."));
					jsono.put("nav3focus", "background: url(../upload/" + s+ "/" + ddactiveheaderFileName+ ") no-repeat center top;");

					InputStream is = new FileInputStream(ddactiveheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setNav3focus(xml);
					}
					//stream(ddactiveheader, ddactiveheaderFileName, root, s);
				} else {
					extName = "";
					theme.setNav3focus(defaultTheme.getNav3focus());
					//jsono.put("nav3focus", "background: url(../images/theme/menu8_focus.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				// 统计中心
				if (tjheaderFileName != null && tjheaderFileName.lastIndexOf(".") >= 0) {
					extName = tjheaderFileName.substring(tjheaderFileName.lastIndexOf("."));
					jsono.put("mi4", "background: url(../upload/" + s + "/"+ tjheaderFileName + ") no-repeat center top;");
					
					InputStream is = new FileInputStream(tjheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setMi4_p(xml);
					}
					//stream(tjheader, tjheaderFileName, root, s);
				} else {
					extName = "";
					theme.setMi4_p(defaultTheme.getMi4_p());
					//jsono.put("mi4", "background: url(../images/theme/menu3.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}
				// 统计中心活动图标
				if (tjactiveheaderFileName != null && tjactiveheaderFileName.lastIndexOf(".") >= 0) {
					extName = tjactiveheaderFileName.substring(tjactiveheaderFileName.lastIndexOf("."));
					jsono.put("nav4focus", "background: url(../upload/" + s+ "/" + tjactiveheaderFileName+ ") no-repeat center top;");

					InputStream is = new FileInputStream(tjactiveheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setNav4focus(xml);
					}
					//stream(tjactiveheader, tjactiveheaderFileName, root, s);
				} else {
					extName = "";
					theme.setNav4focus(defaultTheme.getNav4focus());
					//jsono.put("nav4focus", "background: url(../images/theme/menu3_focus.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				// 设置中心
				if (szheaderFileName != null && szheaderFileName.lastIndexOf(".") >= 0) {
					extName = szheaderFileName.substring(szheaderFileName.lastIndexOf("."));
					jsono.put("mi5", "background: url(../upload/" + s + "/" + szheaderFileName + ") no-repeat center top;");

					InputStream is = new FileInputStream(szheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setMi5_p(xml);
					}
					//stream(szheader, szheaderFileName, root, s);
				} else {
					extName = "";
					theme.setMi5_p(defaultTheme.getMi5_p());
					//jsono.put("mi5", "background: url(../images/theme/menu9.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}
				// 设置中心活动图标
				if (szactiveheaderFileName != null && szactiveheaderFileName.lastIndexOf(".") >= 0) {
					extName = szactiveheaderFileName.substring(szactiveheaderFileName.lastIndexOf("."));
					jsono.put("nav5focus", "background: url(../upload/" + s+ "/" + szactiveheaderFileName+ ") no-repeat center top;");

					InputStream is = new FileInputStream(szheader);
					byte[] xml = null;
					if (is != null) {
						int len = is.available();
						xml = new byte[len];
						is.read(xml);
						theme.setNav5focus(xml);
					}
					//stream(szactiveheader, szactiveheaderFileName, root, s);
				} else {
					extName = "";
					theme.setNav5focus(defaultTheme.getNav5focus());
					//jsono.put("nav5focus", "background: url(../images/theme/menu9_focus.png) no-repeat center top;");
				}
				if (!checkIsImage(extName) && extName != "") {
					this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
					return "error";
				}

				theme.setFooterclass("background: "+ footbgcolor+ "; color: "+ footcolor+ "; line-height: 25px; text-align: center; width:100%;min-width: 1256px;");
				theme.setNavphotos(jsono.toJSONString());
				theme.setId(UUIDGenerator.getUUID32());
				theme.setUserId(s);
				theme.setIsmapgroundlogo(theme_lon + "," + theme_lat);

				int count = service.getCount("ThemeManage.countThemeDetails", s);
				if (count == 0)
					service.update("ThemeManage.insertThemeDetails", theme);
			}
			this.message = "<font color='green'>添加用户主题成功！</font>";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void stream(File file, String fileName, String root, String userId) {
		try {
			InputStream is;

			is = new FileInputStream(file);

			// 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
			isExist(root + "/" + userId);
			File destFile = new File(root + "/" + userId, fileName);

			// 把图片写入到上面设置的路径里
			OutputStream os = new FileOutputStream(destFile);
			byte[] buffer = new byte[400];
			int length = 0;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 检查是否是图片格式 **/
	public static boolean checkIsImage(String imgStr) {
		boolean flag = false;
		if (imgStr != null) {
			if (imgStr.equalsIgnoreCase(".jpg")) {
				flag = true;
			} else if (imgStr.equalsIgnoreCase(".jpeg")) {
				flag = true;
			} else if (imgStr.equalsIgnoreCase(".bmp")) {
				flag = true;
			} else if (imgStr.equalsIgnoreCase(".png")) {
				flag = true;
			} else if (imgStr.equalsIgnoreCase(".gif")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param path
	 *            文件夹路径
	 */
	public static void isExist(String path) {
		File file = new File(path);
		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdirs();

		}

	}

	// 导航图标上传方法
	public String navigationFile() {
		// 得到工程保存图片的路径
		String root = null;
		if (getSystem().contains("windows"))
			root = ServletActionContext.getRequest().getRealPath("/upload/");
		else
			root = linux_path;
		String extName;
		// 循环上传的文件
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			map.put("nodefault", "true");
			Theme theme = (Theme) service.getObject("ThemeManage.getThemeDefault", map);
			String nav_value = theme.getNavphotos();
			JSONObject jsono = JSON.parseObject(nav_value);
			// 车辆监控
			if (jkheaderFileName != null && jkheaderFileName.lastIndexOf(".") >= 0) {
				extName = jkheaderFileName.substring(jkheaderFileName.lastIndexOf("."));
				jsono.put("mi1", "background: url(../upload/" + userId + "/" + jkheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(jkheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setMi1_p(xml);
				}
				//jsono.put("mi1", xml.length);
				//stream(jkheader, jkheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}
			// 车辆监控活动图标
			if (jkactiveheaderFileName != null && jkactiveheaderFileName.lastIndexOf(".") >= 0) {
				extName = jkactiveheaderFileName.substring(jkactiveheaderFileName.lastIndexOf("."));
				jsono.put("nav1focus", "background: url(../upload/" + userId + "/" + jkactiveheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(jkactiveheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setNav1focus(xml);
				}
				//jsono.put("nav1focus", xml.length);
				//stream(jkactiveheader, jkactiveheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}

			// 油量监控
			if (ylheaderFileName != null && ylheaderFileName.lastIndexOf(".") >= 0) {
				extName = ylheaderFileName.substring(ylheaderFileName.lastIndexOf("."));
				jsono.put("mi2", "background: url(../upload/" + userId + "/" + ylheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(ylheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setMi2_p(xml);
				}
				//jsono.put("mi2", xml.length);
				//stream(ylheader, ylheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}
			// 油量监控活动图标
			if (ylactiveheaderFileName != null && ylactiveheaderFileName.lastIndexOf(".") >= 0) {
				extName = ylactiveheaderFileName.substring(ylactiveheaderFileName.lastIndexOf("."));
				jsono.put("nav2focus", "background: url(../upload/" + userId + "/" + ylactiveheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(ylactiveheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setNav2focus(xml);
				}
				//jsono.put("nav2focus", xml.length);
				//stream(ylactiveheader, ylactiveheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}

			// 调度中心
			if (ddheaderFileName != null && ddheaderFileName.lastIndexOf(".") >= 0) {
				extName = ddheaderFileName.substring(ddheaderFileName.lastIndexOf("."));
				jsono.put("mi3", "background: url(../upload/" + userId + "/" + ddheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(ddheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setMi3_p(xml);
				}
				//jsono.put("mi3", xml.length);
				//stream(ddheader, ddheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}
			// 调度中心活动图标
			if (ddactiveheaderFileName != null && ddactiveheaderFileName.lastIndexOf(".") >= 0) {
				extName = ddactiveheaderFileName.substring(ddactiveheaderFileName.lastIndexOf("."));
				jsono.put("nav3focus", "background: url(../upload/" + userId + "/" + ddactiveheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(ddactiveheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setNav3focus(xml);
				}
				//jsono.put("nav3focus", xml.length);
				//stream(ddactiveheader, ddactiveheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}

			// 统计中心
			if (tjheaderFileName != null && tjheaderFileName.lastIndexOf(".") >= 0) {
				extName = tjheaderFileName.substring(tjheaderFileName.lastIndexOf("."));
				jsono.put("mi4", "background: url(../upload/" + userId + "/" + tjheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(tjheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setMi4_p(xml);
				}
				//jsono.put("mi4", xml.length);
				//stream(tjheader, tjheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}
			// 统计中心活动图标
			if (tjactiveheaderFileName != null && tjactiveheaderFileName.lastIndexOf(".") >= 0) {
				extName = tjactiveheaderFileName.substring(tjactiveheaderFileName.lastIndexOf("."));
				jsono.put("nav4focus", "background: url(../upload/" + userId + "/" + tjactiveheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(tjactiveheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setNav4focus(xml);
				}
				//jsono.put("nav4focus", xml.length);
				//stream(tjactiveheader, tjactiveheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}

			// 设置中心
			if (szheaderFileName != null && szheaderFileName.lastIndexOf(".") >= 0) {
				extName = szheaderFileName.substring(szheaderFileName.lastIndexOf("."));
				jsono.put("mi5", "background: url(../upload/" + userId + "/" + szheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(szheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setMi5_p(xml);
				}
				//jsono.put("mi5", xml.length);
				//stream(szheader, szheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}
			// 设置中心活动图标
			if (szactiveheaderFileName != null && szactiveheaderFileName.lastIndexOf(".") >= 0) {
				extName = szactiveheaderFileName.substring(szactiveheaderFileName.lastIndexOf("."));
				jsono.put("nav5focus", "background: url(../upload/" + userId + "/" + szactiveheaderFileName + ") no-repeat center top;");
				
				InputStream is = new FileInputStream(szactiveheader);
				byte[] xml = null;
				if (is != null) {
					int len = is.available();
					xml = new byte[len];
					is.read(xml);
					theme.setNav5focus(xml);
				}
				//jsono.put("nav5focus", xml.length);
				//stream(szactiveheader, szactiveheaderFileName, root, userId);
			} else {
				extName = "";
			}
			if (!checkIsImage(extName) && extName != "") {
				this.errorMessage = "<font color='red'>上传的文件类型错误，请选择jpg,jpeg,bmp,png和gif格式的图片!</font>";
				return "error";
			}

			theme.setUserId(userId);
			theme.setId(id);
			theme.setNavphotos(jsono.toJSONString());
			theme.setFooterclass("background: " + footbgcolor + "; color: " + footcolor + "; line-height: 25px; text-align: center; width:100%;min-width: 1256px;");
			
			this.theme = theme;
			service.update("ThemeManage.updateThemeDetails", theme);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public ClwJcParamCfgT getParamSet() {
		return paramSet;
	}

	public void setParamSet(ClwJcParamCfgT paramSet) {
		this.paramSet = paramSet;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public Map getUserMap() {
		return userMap;
	}

	public void setUserMap(Map userMap) {
		this.userMap = userMap;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public File getFileheader() {
		return fileheader;
	}

	public void setFileheader(File fileheader) {
		this.fileheader = fileheader;
	}

	public File getFileheadbg() {
		return fileheadbg;
	}

	public void setFileheadbg(File fileheadbg) {
		this.fileheadbg = fileheadbg;
	}

	public File getFilelogo() {
		return filelogo;
	}

	public void setFilelogo(File filelogo) {
		this.filelogo = filelogo;
	}

	public String getFileheaderFileName() {
		return fileheaderFileName;
	}

	public void setFileheaderFileName(String fileheaderFileName) {
		this.fileheaderFileName = fileheaderFileName;
	}

	public String getFileheadbgFileName() {
		return fileheadbgFileName;
	}

	public void setFileheadbgFileName(String fileheadbgFileName) {
		this.fileheadbgFileName = fileheadbgFileName;
	}

	public String getFilelogoFileName() {
		return filelogoFileName;
	}

	public void setFilelogoFileName(String filelogoFileName) {
		this.filelogoFileName = filelogoFileName;
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public String getFootcolor() {
		return footcolor;
	}

	public void setFootcolor(String footcolor) {
		this.footcolor = footcolor;
	}

	public String getFootbgcolor() {
		return footbgcolor;
	}

	public void setFootbgcolor(String footbgcolor) {
		this.footbgcolor = footbgcolor;
	}

	public File getJkheader() {
		return jkheader;
	}

	public void setJkheader(File jkheader) {
		this.jkheader = jkheader;
	}

	public File getYlheader() {
		return ylheader;
	}

	public void setYlheader(File ylheader) {
		this.ylheader = ylheader;
	}

	public File getDdheader() {
		return ddheader;
	}

	public void setDdheader(File ddheader) {
		this.ddheader = ddheader;
	}

	public File getTjheader() {
		return tjheader;
	}

	public void setTjheader(File tjheader) {
		this.tjheader = tjheader;
	}

	public File getSzheader() {
		return szheader;
	}

	public void setSzheader(File szheader) {
		this.szheader = szheader;
	}

	public File getJkactiveheader() {
		return jkactiveheader;
	}

	public void setJkactiveheader(File jkactiveheader) {
		this.jkactiveheader = jkactiveheader;
	}

	public File getYlactiveheader() {
		return ylactiveheader;
	}

	public void setYlactiveheader(File ylactiveheader) {
		this.ylactiveheader = ylactiveheader;
	}

	public File getDdactiveheader() {
		return ddactiveheader;
	}

	public void setDdactiveheader(File ddactiveheader) {
		this.ddactiveheader = ddactiveheader;
	}

	public File getTjactiveheader() {
		return tjactiveheader;
	}

	public void setTjactiveheader(File tjactiveheader) {
		this.tjactiveheader = tjactiveheader;
	}

	public File getSzactiveheader() {
		return szactiveheader;
	}

	public void setSzactiveheader(File szactiveheader) {
		this.szactiveheader = szactiveheader;
	}

	public String getJkheaderFileName() {
		return jkheaderFileName;
	}

	public void setJkheaderFileName(String jkheaderFileName) {
		this.jkheaderFileName = jkheaderFileName;
	}

	public String getYlheaderFileName() {
		return ylheaderFileName;
	}

	public void setYlheaderFileName(String ylheaderFileName) {
		this.ylheaderFileName = ylheaderFileName;
	}

	public String getDdheaderFileName() {
		return ddheaderFileName;
	}

	public void setDdheaderFileName(String ddheaderFileName) {
		this.ddheaderFileName = ddheaderFileName;
	}

	public String getTjheaderFileName() {
		return tjheaderFileName;
	}

	public void setTjheaderFileName(String tjheaderFileName) {
		this.tjheaderFileName = tjheaderFileName;
	}

	public String getSzheaderFileName() {
		return szheaderFileName;
	}

	public void setSzheaderFileName(String szheaderFileName) {
		this.szheaderFileName = szheaderFileName;
	}

	public String getJkactiveheaderFileName() {
		return jkactiveheaderFileName;
	}

	public void setJkactiveheaderFileName(String jkactiveheaderFileName) {
		this.jkactiveheaderFileName = jkactiveheaderFileName;
	}

	public String getYlactiveheaderFileName() {
		return ylactiveheaderFileName;
	}

	public void setYlactiveheaderFileName(String ylactiveheaderFileName) {
		this.ylactiveheaderFileName = ylactiveheaderFileName;
	}

	public String getDdactiveheaderFileName() {
		return ddactiveheaderFileName;
	}

	public void setDdactiveheaderFileName(String ddactiveheaderFileName) {
		this.ddactiveheaderFileName = ddactiveheaderFileName;
	}

	public String getTjactiveheaderFileName() {
		return tjactiveheaderFileName;
	}

	public void setTjactiveheaderFileName(String tjactiveheaderFileName) {
		this.tjactiveheaderFileName = tjactiveheaderFileName;
	}

	public String getSzactiveheaderFileName() {
		return szactiveheaderFileName;
	}

	public void setSzactiveheaderFileName(String szactiveheaderFileName) {
		this.szactiveheaderFileName = szactiveheaderFileName;
	}

	public String getTheme_lon() {
		return theme_lon;
	}

	public void setTheme_lon(String theme_lon) {
		this.theme_lon = theme_lon;
	}

	public String getTheme_lat() {
		return theme_lat;
	}

	public void setTheme_lat(String theme_lat) {
		this.theme_lat = theme_lat;
	}
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

}

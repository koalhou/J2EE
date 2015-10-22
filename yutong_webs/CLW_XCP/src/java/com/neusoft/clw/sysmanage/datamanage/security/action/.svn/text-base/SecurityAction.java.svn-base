package com.neusoft.clw.sysmanage.datamanage.security.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.AppContextHelper;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserPermissionInfo;
import com.opensymphony.xwork2.ActionContext;

public class SecurityAction extends BaseAction implements SessionAware {

	/**
	 * 生成序列号
	 */
	private static final long serialVersionUID = -7994936652239224404L;

	private transient Service service;

	private Map session;

	private String username;

	private String password;

	private String encode;

	private String hidden;
	
	private String resolution;
	
	private String operatesys;
	
	private String browser;
	
	private String flashver;
	
	List<HashMap<String, String>> isDisplayNewIcon;
	Map<String, Object> fileNames;

	public List<HashMap<String, String>> getIsDisplayNewIcon() {
		return isDisplayNewIcon;
	}

	public void setIsDisplayNewIcon(List<HashMap<String, String>> isDisplayNewIcon) {
		this.isDisplayNewIcon = isDisplayNewIcon;
	}

	public Map<String, Object> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Map<String, Object> fileNames) {
		this.fileNames = fileNames;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOperatesys() {
		return operatesys;
	}

	public void setOperatesys(String operatesys) {
		this.operatesys = operatesys;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getFlashver() {
		return flashver;
	}

	public void setFlashver(String flashver) {
		this.flashver = flashver;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	private String validateCode = "";

	public SecurityAction() {

	}

	/**
	 * 登录action
	 * 
	 * @return
	 */
	public void login() {
		BasicDataSource bddatasource=(BasicDataSource)AppContextHelper.getBean("dataSource");
		// 设定当前系统的编码集为UTF-8
		System.setProperty("file.encoding", "UTF-8");
		
		if (log.isDebugEnabled()) {
			log.info("username is " + username + ", Password is " + password
					+ "encode:" + encode);
		}
		// 判断从哪个页面登录，从车主页面登录错误时返回ERROR2
		/*
		 * if (null == encode || "".equals(encode)) { if (null == username ||
		 * null == password) { return ERROR2; } if (null == session) { return
		 * ERROR2; } // 获取随机生成的验证码 String validateCodeCrt = (String)
		 * session.get("randCheckCode"); // 核对验证码 if (null != validateCode &&
		 * !validateCode.equals(validateCodeCrt)) {
		 * super.addActionError(getText("login.randCheckCode.invalid")); return
		 * ERROR2; } UserInfo admin = new UserInfo(); try {
		 * admin.setLoginName(username);
		 * admin.setLoginPwd(MD5digest.generatePassword(password));
		 * admin.setUserType(Constants.ONWER_USER_KEY); admin = (UserInfo)
		 * service.getObject("login.getUserbyPasswd", admin); if (null == admin)
		 * { super.addActionError(getText("login.invalid")); return ERROR2; }
		 * List<UserPermissionInfo> perPermissList = (List<UserPermissionInfo>)
		 * service .getObjects("login.getUserPermissList", admin); if
		 * (!perPermissList.isEmpty()) { List perPermissionList = new
		 * ArrayList(); for (UserPermissionInfo upi : perPermissList) {
		 * perPermissionList.add(upi.getModuleId()); }
		 * session.put(Constants.PER_URL_LIST, perPermissionList);
		 * session.put(Constants.USER_SESSION_KEY, admin); } else {
		 * super.addActionError("没有权限登录"); return ERROR2; } } catch
		 * (BusinessException e) { // TODO Auto-generated catch block
		 * super.addActionError(getText("login.db.error"));
		 * log.error(e.getMessage()); return ERROR2; } }
		 */

		String logid = UUIDGenerator.getUUID();
		String loguseid="["+username+"]["+logid+"]";
		session.put(Constants.LOG_USE_ID, loguseid);
		MDC.put("loginid", getloginuuid());
		MDC.put("modulename", "[login]");
		log.info("[loginname:" + username + ",encode:"
				+ encode+"]登录开始");
		if(null!=bddatasource){
			log.info("activenum:"+bddatasource.getNumActive()+";idlenum:"+bddatasource.getNumIdle());
		}
		
		if (null == username || null == password) {
			//return ERROR;
		}
		if (null == session) {
			//return ERROR;
		}
		String validateCodeCrt = (String) session.get("randCheckCode");
		// 核对验证码

		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
		if (null != validateCode
				&& validateCode.equals(getText("login.test.validcode"))) {
			
		} else if (null != validateCode
				&& !validateCode.equals(validateCodeCrt)) {
			super.addActionError(getText("login.randCheckCode.invalid"));
			log.info("[validateCode:"+validateCode+";validateCodeCrt:"+validateCodeCrt+"]验证码错误!");
			//return ERROR;
			 try {
		            response.getWriter().write(getText("login.randCheckCode.invalid"));
		        } catch (IOException ioException) {
		            log.error("写回错误信息出错",ioException);
		        }
			return ;
		}
		try {
			UserInfo admin = new UserInfo();
			admin.setLoginName(username);
			admin.setLoginPwd(MD5digest.generatePassword(password));
			String tempencode = EncodeOption(encode);
			// 判断是否为超级用户
			if (username.equals("yutongsuperadmin")) {
				admin = (UserInfo) service.getObject("login.getUserforsuper",
						admin);
				if (null != admin) {
					// 获取相关的企业信息
					UserInfo eninfo = (UserInfo) service.getObject(
							"login.getEnByEncode", tempencode);
					if (null == eninfo) {
						log.info("encode错误!");
						//super.addActionError(getText("login.invalid"));
						//return ERROR;
						try {
				            response.getWriter().write(getText("login.invalid"));
				        } catch (IOException ioException) {
				            log.error("写回错误信息出错",ioException);
				        }
				        return;
					} else {
						admin.setEntiID(eninfo.getEntiID());
						admin.setOrganizationID(eninfo.getEntiID());
						admin.setImg_path(eninfo.getImg_path());
						admin.setEn_mould(eninfo.getEn_mould());
					}
				} else {
					log.info("superadmin密码错误!");
					//super.addActionError(getText("login.invalid"));
					try {
			            response.getWriter().write(getText("login.invalid"));
			        } catch (IOException ioException) {
			            log.error("写回错误信息出错",ioException);
			        }
			        return;
					//return ERROR;
				}
			} else {
				// admin.setEn_code(EncodeOption(encode));
				admin.setEn_code(tempencode);
				// 新增区分是否为禁用用户，为对应垃圾用户采用返回LIST的查询方法
				List<UserInfo> usinfo = (List<UserInfo>) service.getObjects(
						"login.getUserForforbid", admin);
				if (usinfo.size() > 0) {
					admin = usinfo.get(0);
					if (!"0".equals(admin.getValideFlg())) {
						log.info("用户被禁用");
						//super.addActionError(getText("login.forbid"));
						try {
				            response.getWriter().write(getText("login.forbid"));
				        } catch (IOException ioException) {
				            log.error("写回错误信息出错",ioException);
				        }
				        return;
						//return ERROR;
					}
				} else {
					log.info("无该登录用户");
					//super.addActionError(getText("login.invalid"));
					try {
			            response.getWriter().write(getText("login.invalid"));
			        } catch (IOException ioException) {
			            log.error("写回错误信息出错",ioException);
			        }
			        return;
					//return ERROR;
				}

				// admin = (UserInfo) service
				// .getObject("login.getUserbyPasswd", admin);
				// if (null == admin) {
				// log.info("logid:" + logid + ",admin对象为空!");
				// super.addActionError(getText("login.invalid"));
				// return ERROR;
				// }
			}
			List<UserPermissionInfo> perPermissList = (List<UserPermissionInfo>) service
					.getObjects("login.getUserPermissList", admin);
			if (!perPermissList.isEmpty()) {
				admin.setResolution(resolution);
				admin.setOperatesys(operatesys);
				admin.setBrowser(browser);
				admin.setFlashver(flashver);
				List perPermissionList = new ArrayList();
				for (UserPermissionInfo upi : perPermissList) {
					perPermissionList.add(upi.getModuleId());
				}
				session.put(Constants.PER_URL_LIST, perPermissionList);
				session.put(Constants.USER_SESSION_KEY, admin);
				//session.put(Constants.LOG_USE_ID, logid);
				// 增加登录入库日志
				// 设置操作类型
				this.setOperationType(Constants.LOGIN);
				// 设置所属应用系统
				this.setApplyId(Constants.XC_P_CODE);
				// 设置所属模块
				this.setModuleId(MouldId.XCP_LOGIN_ID);
				// 设置操作描述
				this.addOperationLog("登入车联网应用系统");
			} else {
				log.info("没有登录权限!");
				//super.addActionError("没有权限登录");
				try {
		            response.getWriter().write("没有权限登录");
		        } catch (IOException ioException) {
		            log.error("写回错误信息出错",ioException);
		        }
		        return;
				//return ERROR;
			}

		} catch (BusinessException e) {
			//super.addActionError(getText("login.db.error"));
			 ((HttpServletResponse)ActionContext
	     				.getContext()
	     				.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE)).setHeader("x-error-code", "6000");
			log.error("登录报错:", e);
			try {
	            response.getWriter().write(getText("login.db.error"));
	        } catch (IOException ioException) {
	            log.error("写回错误信息出错",ioException);
	        }
			//return ERROR;
			return;
		} catch (Exception e) {
			//super.addActionError(getText("login.db.error"));
			 ((HttpServletResponse)ActionContext
	     				.getContext()
	     				.get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE)).setHeader("x-error-code", "6000");
			log.error("登录报错:", e);
			try {
	            response.getWriter().write(getText("login.db.error"));
	        } catch (IOException ioException) {
	            log.error("写回错误信息出错",ioException);
	        }
			//return ERROR;
			return;
		}
		log.info("登录成功结束");
		try {
            response.getWriter().write("success");
        } catch (IOException ioException) {
            log.error("写回错误信息出错",ioException);
        }
		//return SUCCESS;
        return;

	}

	/** 企业编码补零 **/
	private String EncodeOption(String str) {
		int length = str.length();

		if (length < 10) {
			int sub = 10 - length;

			for (int i = 0; i < sub; i++) {
				str = "0" + str;
			}
			return str;
		}
		return str;

	}

	/***********************************************************************************************
	 * 获取用户基本信息
	 * 
	 * @throws BusinessException
	 **********************************************************************************************/
	private UserInfo getloginUserInfo(String usertype) throws BusinessException {
		Map<String, String> map = new HashMap<String, String>(2);
		map.put("loginname", username);
		map.put("loginpwd", MD5digest.generatePassword(password));
		map.put("userType", usertype);
		// map.put("loginencode", encode);
		UserInfo admin = new UserInfo();
		admin = (UserInfo) service.getObject("login.getUserInfo", map);

		return admin;
	}

	/***********************************************************************************************
	 * 根据企业编码获得企业ID
	 * 
	 * @throws BusinessException
	 **********************************************************************************************/
	private UserInfo getentId(String encode) throws BusinessException {

		return (UserInfo) service.getObject("login.getentiInfo", encode);

	}

	/** 根据用户ID获取对应角色的权限ID集合 * */
	private List<HashMap> getuserRoleModule(String userid)
			throws BusinessException {

		List<HashMap> li = service
				.getObjects("login.getUserRoleModule", userid);

		return li;
	}

	/** 根据企业ID获取LOGO路径 * */
	private String getLogoPath(UserInfo admin) throws BusinessException {

		String logoPath = (String) service
				.getObject("login.getLogoPath", admin);

		return logoPath;
	}

	@SuppressWarnings("unchecked")
	private boolean setPermissionList() throws BusinessException {

		return true;
	}

	public String logout() {
		try {
			MDC.put("modulename", "[logout]");
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession httpSession = request.getSession();
			if (null != httpSession) {
				// setOperationType(Constants.LOGOUT);
				// addOperationLog(getText("log.logout.title"));
				UserInfo user = getCurrentUser();
				if (null != user) {
					log.info("[loginname:" + user.getLoginName()
							+ ",encode:" + user.getEn_code()+"]用户注销");
				}
				// 设置操作类型
				this.setOperationType(Constants.LOGOUT);
				// 设置所属应用系统
				this.setApplyId(Constants.XC_P_CODE);
				// 设置所属模块
				this.setModuleId(MouldId.XCP_LOGOUT_ID);
				// 设置操作描述
				this.addOperationLog("退出车联网应用系统");
				httpSession.invalidate();
			}
		} catch (Exception e) {
			addActionError(getText(e.getMessage()));
			log.error("注销", e);
			return ERROR;
		}

		return SUCCESS;
	}
	
	//首页new图标显示
	public String getDisplayNewIcon() throws IOException {
		try {
	        isDisplayNewIcon = service.getObjects("login.getDisplayNewIcon", "");
			session.put("isDisplayNewIcon", isDisplayNewIcon);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String getHomeShow() throws IOException {
		fileNames = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userID = request.getParameter("userID");
			String publishDate = (String)service.getObject("login.getMaxPublishDate", "");
			List<Map<String, String>> isDisplayBlockUI = service.getObjects("login.getHomeShow", userID);
			if(isDisplayBlockUI.isEmpty() || isDisplayBlockUI.size() == 0) {
				Map<String, String> m = new HashMap<String, String>();
				m.put("userID", userID);
				m.put("id", UUID.randomUUID().toString());
				service.insert("login.insertHomeShow", m);
				
				Properties prop = new Properties();
				prop.load(SecurityAction.class.getClassLoader().getResourceAsStream("config.properties"));
				String address = prop.getProperty("version.default.path");
				address = new String(address.getBytes("ISO8859-1"), "GBK");
				File file = new File(address + "/" + publishDate);
				File[] filelist = file.listFiles();
				List<String> names = new ArrayList<String>();
				if(filelist.length > 0) {
					for (int i = 0; i < filelist.length; i++) {
						names.add(publishDate + "/" + filelist[i].getName());
					}
				}
				fileNames.put("fileNames", names);
				session.put("isHomeShow", "1");
				session.put("fileNames", names);
			} else {
				session.put("isHomeShow", "0");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * @return Returns the service.
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service
	 *            The service to set.
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return Returns the session.
	 */
	public Map getSession() {
		return session;
	}

	/**
	 * @param session
	 *            The session to set.
	 */
	public void setSession(Map session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取验证码
	 * 
	 * @return
	 */
	public String getValidateCode() {
		return validateCode;
	}

	/**
	 * 设定验证码
	 * 
	 * @param validateCode
	 *            验证码
	 */
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}
}

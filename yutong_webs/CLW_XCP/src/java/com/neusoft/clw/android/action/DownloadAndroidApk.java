package com.neusoft.clw.android.action;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.clw.android.domain.VersionDomain;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.interceptor.AuthenticationInterceptor;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.BaseServlet;
import com.neusoft.clw.common.util.BatchIdHelper;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.FileUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.log.ds.UserOperateLog;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;

public class DownloadAndroidApk extends BaseServlet{
	private static final long serialVersionUID = -5466808608791362243L;
	private Service service;
	private String apkPath;
	private VersionDomain apk;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext sc = config.getServletContext();
	}
	//get方式获取请求
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		
		String type=request.getParameter("type");
		type = type!="0"?"1":"0";
		//updateUserOpera(type,request,response,session);
		getAndroidAPK(type,request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		doGet(request,response);
	}
	/**
	 * 提交用户消息
	 * 
	 */
	public void updateUserOpera(String type, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
        
//		try{
//    		response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out=response.getWriter();
//			out.print("<script type='text/javascript'>window.location='./'</script>");
//    	}catch(IOException e){
//    		
//    	}
       
	}
	
	/**
	 * 获取最新 apk信息并下载
	 * @param type 下载类型
	 * @param request
	 * @param response
	 */
	private void getAndroidAPK(String type, HttpServletRequest request, HttpServletResponse response){
	    
        try{
        	HttpSession session = request.getSession(false);
			apk=new VersionDomain();
			String realPath=request.getSession().getServletContext().getRealPath(""); 
			//Service service = (Service) AppContextHelper.getBean("service");
			apk=(VersionDomain)service.getObject("AndroidDownload.getAPK",type);
			String filePath=StringUtil.toRealPath(apkPath+apk.getSoftware_uri());
			realPath=realPath+filePath;
			UserInfo user = null;
			if(null != session) {
			    user = (UserInfo)session.getAttribute(AuthenticationInterceptor.USER_SESSION_KEY);
			}
			UserOperateLog userOperateLog = new UserOperateLog();
			// 设置操作日志对象日志ID
	        userOperateLog.setLogId(UUIDGenerator.getUUID());
			if (null != user) {
				 // 设置操作日志对象企业ID
		        userOperateLog.setEnterpriseId(user.getEntiID());
		        // 设置操作日志对象属性企业名称
		        userOperateLog.setEnterpriseName(user.getFullName());
		        // 设置操作日志对象用户ID
		        userOperateLog.setUserId(user.getUserID());
		        // 设置操作日志对象用户名称
		        userOperateLog.setUserName(user.getLoginName());
				
			}else{
				// 设置操作日志对象企业ID
		        userOperateLog.setEnterpriseId(" ");
		        // 设置操作日志对象属性企业名称
		        userOperateLog.setEnterpriseName(" ");
		        // 设置操作日志对象用户ID
		        userOperateLog.setUserId(" ");
		        // 设置操作日志对象用户名称
		        userOperateLog.setUserName(" ");
			}
			 // 设置操作日志对象操作时间
	        userOperateLog.setOperateTime(BatchIdHelper.getInstance().getBatchId());
	        userOperateLog.setOperateIp(request.getRemoteAddr());
	        userOperateLog.setOperateType(Constants.DOWNLOAD);
	        userOperateLog.setApplyId(Constants.CLW_P_CODE);
	        userOperateLog.setModuleId("0".equals(type) ? MouldId.XCP_APK_MOBILE_DOWNLOAD_ID : MouldId.XCP_APK_WEB_DOWNLOAD_ID);
	        //userOperateLog.setOperateDesc("0".equals(downloadType) ? "web下载":"手机客户端下载");
	        userOperateLog.setOperateDesc(apk.getVersion_name());
	        
	        try {
	            service.insert(UserOperateLog.class, userOperateLog);
	        } catch (BusinessException e) {
	           
	        }			
			FileUtil.downloadFile(realPath,request,response);
		}catch(BusinessException eb){

		}
	}
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getApkPath() {
		return apkPath;
	}
	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}
}

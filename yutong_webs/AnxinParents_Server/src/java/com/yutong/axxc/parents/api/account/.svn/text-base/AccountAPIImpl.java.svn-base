package com.yutong.axxc.parents.api.account;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.UserSeesion;
import com.yutong.axxc.parents.entity.account.Account;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.entity.common.Verify;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;
import com.yutong.axxc.parents.tools.PropertiesTools;
import com.yutong.axxc.parents.tools.ValidateUtil;

public class AccountAPIImpl implements AccountAPI {
	private static Logger logger = LoggerFactory
			.getLogger(AccountAPIImpl.class);
	// access_token超时时间设置,单位:分钟.
	public static int ACCESS_TOKEN_EXPIRE_TIME = 10080;
	// 配置文件中存放access_token超时时间配置的key值.
	public static String ACCESS_TOKEN_EXPIRE_TIME_KEY = "token.expire.interval";
	@Context
	private MessageContext context;

	@Autowired
	protected BaseService baseService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EtagService etagService;
	
	@Autowired
	private SessionService sessionService;

	@Override
	public Response verifyPhone(String phone) {
		if(!ValidateUtil.isPhone(phone)){
			logger.error("手机号{}错误",phone);
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		Integer i = baseService.get("Account.verifyPhone", phone);

		Verify ver = new Verify();
		ver.setVerify_result(i);
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(ver))
				.build();
	}

	@Override
	public Response verifyEmail(String email) {
		return Response.ok().status(501).build();
	}

	/**
	 * 注册
	 */
	@Override
	public Response register(String registerInfo) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(registerInfo);
		Map userMap = (Map) map.get("usr_info");
		Map clientInfo=(Map)map.get("client_info");
		
		if(userMap==null){
			logger.error("参数{}缺失",userMap);
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		String name=(String)userMap.get("usr_login_name");
		String pwd=(String)userMap.get("usr_pwd");
		String phone=(String)userMap.get("usr_phone");
		
		UserInfo user = new UserInfo();
		
		if(StringUtils.hasText(phone)&&StringUtils.hasText(pwd)&&StringUtils.hasText(name)){
			//检查、绑定、注册
			user.setPhone(phone);
			user.setName(name.trim());
			user.setPwd(pwd);
			user = accountService.regist(user);

			//登陆
			int expireIn = AccountAPIImpl.getTokenExpireIntervall();
			//生成session，并保存
			user = accountService.genSession(user, expireIn);
			//是否有学生
			int cnt=baseService.get("Child.countStudentByParentID", user.getId());
			user.setBound(String.valueOf(cnt>0?1:0));
			
			//设置授权用户
			if(!"1".equals(user.getBound())){
				Account account=baseService.get("Account.getMainPareant", user.getId());
				if(account!=null){
				user.setAuthedAccountCode(account.getCode());
				user.setAuthedAccountName(account.getName());
				}
			}
			
			String etag=CachedCommon.CACHED_ACCOUNT+user.getId();
			etagService.put(etag, CachedCommon.CACHED_MINTER_7D, user);
			

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("access_token", user.getAccessToken());
			resultMap.put("expires_in", String.valueOf(expireIn));
			resultMap.put("refresh_token", user.getRefreshToken());
			resultMap.put("usr_info", user);

			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
			
		}else{
			throw new ApplicationException(ErrorConstant.ERROR10001,
					Response.Status.BAD_REQUEST);
		}
		
	}

	/**
	 * 重置密码
	 */
	@Override
	public Response forgetPWD(String req) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(req);
		String usr_account=(String)map.get("usr_account");
		String reset_pwd=(String)map.get("reset_pwd");
		String usr_type=(String)map.get("account_type");
		
		if(!StringUtils.hasText(usr_account)||!StringUtils.hasText(reset_pwd)||!StringUtils.hasText(usr_type)){
			logger.error("请求参数错误",map.toString());
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		UserInfo user=new UserInfo();
		if(StringUtils.hasText(reset_pwd)){
			user.setPwd(reset_pwd);
			logger.debug("{}",usr_type);
			logger.debug("{}-{}","0".equals(usr_type),ValidateUtil.isPhone(usr_account));
			if("0".equals(usr_type)&&ValidateUtil.isPhone(usr_account)){//手机
				user.setPhone(usr_account);
				Integer i = baseService.get("Account.verifyPhone", usr_account); 
				if(i==0){
					throw new ApplicationException(ErrorConstant.ERROR10027,
							Response.Status.BAD_REQUEST);
				}
			}else if(ValidateUtil.isEmail(usr_account)){//
				user.setEmail(usr_account);
				Integer i = baseService.get("Account.verifyEmail", usr_account);
				if(i==0){
					throw new ApplicationException(ErrorConstant.ERROR10027,
							Response.Status.BAD_REQUEST);
				}
			}
			int ret=accountService.resetPwd(user);
			logger.info("用户{}重置密码[{}]",user,ret);
			if(ret==1){
				return Response.ok().build();
			}
		}
		throw new ApplicationException(ErrorConstant.ERROR10024,
				Response.Status.BAD_REQUEST);
	}
	
	/**
	 * 终端用户登录操作.
	 * 
	 * @param loginInfo
	 *            终端用户登录信息.
	 * @return 服务端登录结果信息或新版终端软件信息.
	 */
	@Override
	public Response login(String loginInfo) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(loginInfo);
		String usr_account=(String)map.get("usr_account");
		String reset_pwd=(String)map.get("usr_pwd");
		
		//TODO 检查版本是否最新
		if(StringUtils.hasText(usr_account)&&StringUtils.hasText(reset_pwd)){
			UserInfo loginUser=new UserInfo();
			loginUser.setAlias(usr_account);
			loginUser.setPwd(reset_pwd);
			logger.error("用户{}login", loginUser);
			
			List<UserInfo>  usrInfoList= accountService.getUserByNamePwd(loginUser);
			UserInfo user = null;
			int expireIn = getTokenExpireIntervall();
			if (null == usrInfoList || usrInfoList.size() == 0) {
				logger.error("用户或密码{}不正确", usr_account);
				throw new ApplicationException(ErrorConstant.ERROR_LOGIN_10102,
						Response.Status.BAD_REQUEST);
			} else {
				//清除以前的session
				Object obj=etagService.get(CachedCommon.CACHED_ACCOUNT+usrInfoList.get(0).getId());
				if(obj!=null){
					logger.info("清除缓存{}",obj);
					UserInfo tmp=(UserInfo)obj;
					sessionService.clear(tmp.getAccessToken());
				}
				
				
				//生成session，并保存
				user = accountService.genSession(usrInfoList.get(0), expireIn);
				//是否有学生
				int cnt=baseService.get("Child.countStudentByParentID", user.getId());
				user.setBound(String.valueOf(cnt>0?1:0));
				//设置授权用户
				
				if(!"1".equals(user.getBound())){
					Account account=baseService.get("Account.getMainPareant", user.getId());
					if(account!=null){
						user.setAuthedAccountCode(account.getCode());
						user.setAuthedAccountName(account.getName());
					}
				}
				
				String etag=CachedCommon.CACHED_ACCOUNT+user.getId();
				etagService.put(etag, CachedCommon.CACHED_MINTER_7D, user);
				
				Map<String, Object> resultMap=new HashMap<String, Object>();
				resultMap.put("access_token", user.getAccessToken());
				resultMap.put("expires_in", String.valueOf(expireIn));
				resultMap.put("refresh_token", user.getRefreshToken());
				resultMap.put("usr_info", user);
				
				return Response
						.ok()
						.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
			}

		}else{
				logger.error("登录请求参数非法");
				throw new ApplicationException(ErrorConstant.ERROR10001,
						Response.Status.BAD_REQUEST);
		}
		
	}



	/**
	 * 从配置文件中获取token更新时间间隔.
	 * 
	 * @return token更新时间间隔.
	 */
	public static int getTokenExpireIntervall() {
		int expireTime = ACCESS_TOKEN_EXPIRE_TIME;
		try {
			// 从系统配置文件中获取token超时时间配置.
			expireTime = Integer.parseInt(PropertiesTools.readValue(
					ModCommonConstant.SYS_CONF_FILE,
					ACCESS_TOKEN_EXPIRE_TIME_KEY));
			logger.debug("配置文件中时间间隔为:" + expireTime + "分");
		} catch (IOException e) {
			logger.error("从配置文件中获取token更新时间间隔时出错", e);
			// 使用默认超时时间配置
			logger.info("使用默认时间间隔" + expireTime + "分");
		}
		return expireTime;
	}
}

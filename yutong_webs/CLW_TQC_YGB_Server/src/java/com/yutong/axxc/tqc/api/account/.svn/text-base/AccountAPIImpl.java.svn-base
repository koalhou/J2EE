package com.yutong.axxc.tqc.api.account;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.emay.sdk.client.api.Client;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.HttpConstant;
import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.entity.UserSeesion;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.entity.common.Verify;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.AccountService;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.service.SessionService;
import com.yutong.axxc.tqc.tools.JacksonUtils;
import com.yutong.axxc.tqc.tools.PropertiesTools;
import com.yutong.axxc.tqc.tools.ValidateUtil;

public class AccountAPIImpl implements AccountAPI {
	private static Logger logger = LoggerFactory.getLogger(AccountAPIImpl.class);
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
	private static String softwareSerialNo;
	private static String password;

	private static final String EMAY_SERIALNO="emay.softwareSerialNo";
	private static final String EMAY_KEY="emay.key";
	private static Client smsClient;
	static{
		try {
			softwareSerialNo = PropertiesTools.readValue(ModCommonConstant.SYS_CONF_FILE, EMAY_SERIALNO);
			password = PropertiesTools.readValue(ModCommonConstant.SYS_CONF_FILE, EMAY_KEY);
			smsClient = new Client(softwareSerialNo,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String phone_bind(String token, String bind_info) {
		
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(bind_info);
		UserSeesion session = baseService.get("Account.getIDByToken", token);
		Object phone = map.get("phone");
		if(null == session){
			throw new ApplicationException(ErrorConstant.ERROR20001,Response.Status.BAD_REQUEST);
		}
		if(null == phone || "".equals(String.valueOf(phone)) || String.valueOf(phone).length()!=11){
			logger.info("AccountAPI ： phone_bind，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001,Response.Status.BAD_REQUEST);
		}

		logger.info("持有{}用户绑定手机号" + phone);
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("phone", String.valueOf(phone));
		conditions.put("account_id", session.getId());
		
		List<UserInfo>  usrInfoList= accountService.getUserByNamePhone(conditions);
		int expireIn = getTokenExpireIntervall();
		if(usrInfoList.size() > 0){
			logger.info("AccountAPI ： phone_bind，该手机号已经被其他账户绑定！");
			throw new ApplicationException(ErrorConstant.ERROR10021,Response.Status.BAD_REQUEST);
		}else{
			accountService.bindPhone(conditions, expireIn);
		}
		String userEtag = CachedCommon.CACHED_ACCOUNT + token;
		//删除缓存
		etagService.del(userEtag);
		return HttpConstant.RESP_200;
	}

	@Override
	public Response send(String req) {
		Map<String,String> map = (Map<String,String>)JacksonUtils.jsonToMapRuntimeException(req);
		String phone=map.get("phone") == null ? "" : (String)map.get("phone");
		String code = map.get("code") == null ? "" : (String)map.get("code");
		String type=String.valueOf(map.get("type"));
		
		logger.debug("请求:{},", phone,type);
		if(!StringUtils.hasText(phone)||!StringUtils.hasText(type)){
			logger.info("AccountAPI ：getcode，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		String content="";
		if("".equals(code) || null == (code)){
			map.put("code", genRandomNum());
		}
		if("1".equals(type)){
			//绑定手机
			content="【宇通客车】安芯验证码："+map.get("code")+"您正在进行安芯通勤车手机绑定，请在1小时内完成验证，祝您使用安芯通勤车手机版愉快！";
		}else if("2".equals(type)){
			//更换绑定手机
			content="【宇通客车】安芯验证码："+map.get("code")+"您正在进行安芯通勤车更换绑定手机号码，请在1小时内完成验证，祝您使用安芯通勤车手机版愉快！";
		}else if("3".equals(type)){
			//新密码
			content="【宇通客车】安芯验证码："+map.get("code")+"您正在进行安芯通勤车修改密码操作，请在1小时内完成验证，祝您使用安芯通勤车手机版愉快！";
		}
		try {
			logger.debug("发送请求{}", phone);
			
			int num = smsClient.sendSMS(new String[] { phone },	content, 5);
			logger.info("短信服务器返回：{}",num);
			Map<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("verify_code", map.get("code"));
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(map)).build();
		} catch (Exception e) {
			logger.error("发送短信异常",e);
			throw new ApplicationException(ErrorConstant.ERROR90000,Response.Status.BAD_REQUEST);
		}

	}

	private String genRandomNum(){
		String s = "";
		 while(s.length()<6)
		  s+=(int)(Math.random()*10);
		 return s;
	}
	@Override
	public Response verifyPhone(String phone) {
		if(!ValidateUtil.isPhone(phone)){
			logger.error("手机号{}错误",phone);
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		Integer i = baseService.get("Account.verifyPhone", phone);

		Verify ver = new Verify();
		ver.setVerify_result(i);
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(ver)).build();
	}
	
	@Override
	public Response verifyAccount(String req) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object account = map.get("account");
//		Object type = map.get("type");
		if(null == account || "".equals(String.valueOf(account))){
			logger.info("AccountAPI ： accountcheck，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001,Response.Status.BAD_REQUEST);
		}
		Map<String, String> check_conditions = new HashMap<String, String>();
		check_conditions.put("account", String.valueOf(account));
//		if("0".equals(type)){
//		}else if("1".equals(type)){
//			checkUser.setEmp_code(usr_account);
//		}else if("2".equals(type)){
//			checkUser.setYgb_tel(usr_account);
//		}
		List<UserInfo>  usrInfoList= accountService.getUserByAccount(check_conditions);
		
		if(usrInfoList.size() < 1){
			logger.error("AccountAPI ： accountcheck ：账号{}错误" , account);
			throw new ApplicationException(ErrorConstant.ERROR10012, Response.Status.BAD_REQUEST);
		}
		
		UserInfo rst = usrInfoList.get(0);
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(rst)).build();
	}

	/**
	 * 用户修改密码操作
	 * 
	 * @param 终端用户账号
	 * 
	 * @param 终端用户密码
	 * 
	 * @return 修改结果
	 */
	@Override
	public Response changePWD(String req) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object account = map.get("emp_code");
		Object reset_pwd = map.get("password");
		
		if(reset_pwd != null && account != null){
			Map<String, String> pwd_conditions = new HashMap<String, String>();
			pwd_conditions.put("reset_pwd", String.valueOf(reset_pwd));
			pwd_conditions.put("account", String.valueOf(account));
			logger.debug("{员工号：}+ " + account + "修改密码操作！");
			int ret = accountService.resetPwd(pwd_conditions);
			logger.info("用户{}重置密码" , account , ret);
			if(ret==1){
				return Response.ok().build();
			}
			throw new ApplicationException(ErrorConstant.ERROR10024,Response.Status.BAD_REQUEST);
		}else{
			logger.info("AccountAPI ： pwd，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001,Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * 终端用户登录操作.
	 * 
	 * @param loginInfo 终端用户登录信息.
	 * 
	 * @return 服务端登录结果信息或新版终端软件信息.
	 */
	@Override
	public Response login(String loginInfo) {
		Map<?, ?> map = JacksonUtils.jsonToMapRuntimeException(loginInfo);
		Object account = map.get("account");
		Object password = map.get("password");

		if(null == account || null == password){
			logger.info("AccountAPI ： login，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		
		Map<String, String> login_conditions = new HashMap<String, String>();
		login_conditions.put("account", String.valueOf(account));
		login_conditions.put("password", String.valueOf(password));
		
		logger.error("用户{}login,Emp_code : " + account + ", password : " + password );
		List<UserInfo>  usrInfoList= accountService.getUserByNamePwd(login_conditions);
		accountService.insertLoginLog(usrInfoList);
		UserInfo user = null;
		int expireIn = getTokenExpireIntervall();
		if (null == usrInfoList || usrInfoList.size() == 0) {
			logger.error("用户或密码{}不正确", String.valueOf(account));
			throw new ApplicationException(ErrorConstant.ERROR_LOGIN_10102,	Response.Status.BAD_REQUEST);
		} else {
			//生成session，并保存
			user = accountService.genSession(usrInfoList.get(0), expireIn, String.valueOf(password));
//				//是否有学生
//				int cnt=baseService.get("Child.countStudentByParentID", user.getId());
//				user.setBound(String.valueOf(cnt>0?1:0));
//				//设置授权用户
//				if(!"1".equals(user.getBound())){
//					Account account=baseService.get("Account.getMainPareant", user.getId());
//					if(account!=null){
//						user.setAuthedAccountCode(account.getCode());
//						user.setAuthedAccountName(account.getName());
//					}
//				}
			
			String etag = CachedCommon.CACHED_ACCOUNT + user.getAccessToken();
			etagService.put(etag, CachedCommon.CACHED_MINTER_7D, user);
			
			Map<String, Object> resultMap=new HashMap<String, Object>();
			resultMap.put("access_token", user.getAccessToken());
			resultMap.put("expires_in", String.valueOf(expireIn));
			resultMap.put("refresh_token", user.getRefreshToken());
			resultMap.put("emp_info", user);
			
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(etag).build();
		}

	}

	
	@Override
	public Response autologin(String token) {
		String etag = CachedCommon.CACHED_TOKEN_KEY + token;
		Object o = etagService.get(etag);
		if(null == o){
			o = baseService.get("Account.getIDByToken", token);
		}
		if (o == null) {
			logger.warn("用户access_token[{}]信息已过期,需重新登录", token);
			return Response.status(ErrorConstant.HTTP_ERROR_SESSION_EXPIRE).build();
		}
		UserSeesion session = (UserSeesion)o;
		session.calExpire();
		
		etag = CachedCommon.CACHED_ACCOUNT + token;
		UserInfo user = (UserInfo) etagService.get(etag);
		if (user == null) {
			// String userId = "191";
			user = baseService.get("Account.getLoginByID", session.getId());
			user.setExpire(session.getExpire());
			user.setRefreshToken(session.getRefreshToken());
			logger.info("用户{}重登陆", user);
			user.setAccessToken(session.getAccessToken());
			user.setRefreshToken(session.getRefreshToken());
			// 缓存session
			etag = CachedCommon.CACHED_TOKEN_KEY + session.getAccessToken();
			if (!etagService.put(etag, session.getExpire(), session)) {
				logger.error("缓存失败:{}", etag);
			}
		}
		accountService.insertAutoLoginLog(user);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("expires_in", String.valueOf(session.getExpire()));
		resultMap.put("emp_info", user);
		resultMap.put("access_token", token);
		resultMap.put("refresh_token", session.getRefreshToken());
		
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(etag).build();
		
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
			expireTime = Integer.parseInt(PropertiesTools.readValue(ModCommonConstant.SYS_CONF_FILE,ACCESS_TOKEN_EXPIRE_TIME_KEY));
			logger.debug("配置文件中时间间隔为:" + expireTime + "分");
		} catch (IOException e) {
			logger.error("从配置文件中获取token更新时间间隔时出错", e);
			// 使用默认超时时间配置
			logger.info("使用默认时间间隔" + expireTime + "分");
		}
		return expireTime;
	}

	@Override
	public Response tokenRefesh(String token, String req) {
		String userId = context.getHttpHeaders().getHeaderString("usr_id");

		Map map = JacksonUtils.jsonToMapRuntimeException(req);
		String refreshToken = (String) map.get("refresh_token");
		int expireIn = AccountAPIImpl.getTokenExpireIntervall();
		UserInfo usrInfo = new UserInfo();
		usrInfo.setRefreshToken(refreshToken);
		usrInfo.setAccessToken(token);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, expireIn);
		usrInfo.setExpireTime(cal.getTime());
		int ret = accountService.refreshToken(usrInfo, expireIn);

		logger.info("用户{}更新token", usrInfo);
		if (ret == 1) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("expires_in", expireIn);
			// 更新session
			String etag = CachedCommon.CACHED_TOKEN_KEY + token;
			UserSeesion session = baseService.get("Account.getIDByToken", token);
			etagService.put(etag, CachedCommon.CACHED_MINTER_7D, session);

			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
		} else {
			throw new ApplicationException(ErrorConstant.ERROR10012,Response.Status.BAD_REQUEST);
		}
	}


	@Override
	public Response logout(String token) {
		UserSeesion session = baseService.get("Account.getIDByToken", token);
		if (session == null) {
			logger.warn("用户access_token[{}]信息已过期,需重新登录", token);
			return Response.status(ErrorConstant.HTTP_ERROR_SESSION_EXPIRE).build();
		}

		logger.info("持有{}的用户申请登出系统", token);
		accountService.logout(token);
		etagService.del(CachedCommon.CACHED_ACCOUNT + token);
		// 情况相关缓存并删除session
		sessionService.clear(token);
		return Response.ok().build();
	}

	/**
	 * 用户旧密码验证.
	 * 
	 * @param token 访问令牌信息.
	 *            
	 * @param req  用户旧密码.
	 *           
	 * @return 密码验证结果.
	 */
	@Override
	public Response checkOldPwd(String oldPwd) {
		logger.info("处理客户端发起的用户原密码验证请求");
		if (!StringUtils.hasText(oldPwd)) {
			throw new ApplicationException(ErrorConstant.ERROR10002,Response.Status.BAD_REQUEST);
		}
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
		// String userId = "43";

		UserInfo user = new UserInfo();
		user.setEmp_id(userId);
//		user.setEmp_pwd(oldPwd);
		int userCount = baseService.get("Account.countByPwd", user);

		if (1 == userCount) {
			logger.info("用户ID:" + userId + "的原密码" + oldPwd + "正确");
			return Response.ok().header(HttpHeaders.CACHE_CONTROL, "no-store").header("Pragma", "no-cache").build();
		} else {
			logger.error("用户ID:" + userId + "的原密码" + oldPwd + "不正确");
			return Response.status(Response.Status.NO_CONTENT).header(HttpHeaders.CACHE_CONTROL, "no-store").header("Pragma", "no-cache").build();
		}
	}

	@Override
	public String saveAccount(String userInfo) {
//		UserInfo user = JacksonUtils.fromJsonRuntimeException(userInfo,
//				UserInfo.class);
//		String userId = context.getHttpHeaders().getHeaderString("usr_id");
////		String userId = "43";
//		user.setId(userId);
//
//		// 不检查空 可设置空值
//		logger.info("用户{}修改个人资料", user);
//		int ret = accountService.saveParents(user);
//		logger.info("用户{}修改密码[{}]", user, ret);
//		if (ret == 0) {
//			throw new ApplicationException(ErrorConstant.ERROR10024,
//					Response.Status.BAD_REQUEST);
//		}
//
//		 etagService.del(CachedCommon.CACHED_ACCOUNT+ user.getId());

		return HttpConstant.RESP_200;

	}

}

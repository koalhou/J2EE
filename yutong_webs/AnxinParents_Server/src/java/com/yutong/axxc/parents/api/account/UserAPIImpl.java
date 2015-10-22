package com.yutong.axxc.parents.api.account;

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

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.HttpConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.Child;
import com.yutong.axxc.parents.entity.UserSeesion;
import com.yutong.axxc.parents.entity.account.Account;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;

public class UserAPIImpl implements UserAPI {
	private static Logger logger = LoggerFactory
			.getLogger(UserAPIImpl.class);
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
			UserSeesion session = baseService
					.get("Account.getIDByToken", token);
			etagService.put(etag, CachedCommon.CACHED_MINTER_7D, session);

			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap))
					.build();
		} else {
			throw new ApplicationException(ErrorConstant.ERROR10012,
					Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public Response autologin(String token) {
		UserSeesion session = baseService.get("Account.getIDByToken", token);
		if (session == null) {
			logger.warn("用户access_token[{}]信息已过期,需重新登录", token);
			return Response.status(ErrorConstant.HTTP_ERROR_SESSION_EXPIRE)
					.build();
		}
		session.calExpire();

		String etag = CachedCommon.CACHED_ACCOUNT + session.getId();
		UserInfo user = (UserInfo) etagService.get(etag);
		if (user == null) {
			String userId = context.getHttpHeaders().getHeaderString("usr_id");
			user = baseService.get("Account.getLoginByID", userId);
			user.setExpire(session.getExpire());
			user.setRefreshToken(session.getRefreshToken());
			logger.info("用户{}重登陆", user);
			// 是否有学生
			int cnt = baseService.get("Child.countStudentByParentID",
					user.getId());
			user.setBound(String.valueOf(cnt > 0 ? 1 : 0));
			// 设置授权用户
			if (!"1".equals(user.getBound())) {
				Account account = baseService.get("Account.getMainPareant",
						user.getId());
				if (account != null) {
					user.setAuthedAccountCode(account.getCode());
					user.setAuthedAccountName(account.getName());
				}
			}
			user.setAccessToken(session.getAccessToken());
			user.setRefreshToken(session.getRefreshToken());
			// 缓存session
			etag = CachedCommon.CACHED_TOKEN_KEY + session.getAccessToken();
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_7D, session)) {
				logger.error("缓存失败:{}", etag);
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("expires_in", String.valueOf(session.getExpire()));
		resultMap.put("usr_info", user);
		resultMap.put("access_token", token);
		resultMap.put("refresh_token", session.getRefreshToken());

		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();

	}

	@Override
	public String logout(String token) {
		String userId = context.getHttpHeaders().getHeaderString("usr_id");

		logger.info("持有{}的用户申请登出系统", token);
		accountService.logout(userId);
		etagService.del(CachedCommon.CACHED_ACCOUNT + userId);
		// 情况相关缓存并删除session
		sessionService.clear(token);
		return HttpConstant.RESP_200;
	}

	/**
	 * 用户旧密码验证.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param req
	 *            用户旧密码.
	 * @return 密码验证结果.
	 */
	@Override
	public Response checkOldPwd(String oldPwd) {
		logger.info("处理客户端发起的用户原密码验证请求");
		if (!StringUtils.hasText(oldPwd)) {
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		String userId = context.getHttpHeaders().getHeaderString("usr_id");

		UserInfo user = new UserInfo();
		user.setId(userId);
		user.setPwd(oldPwd);
		int userCount = baseService.get("Account.countByPwd", user);

		if (1 == userCount) {
			logger.info("用户ID:" + userId + "的原密码" + oldPwd + "正确");
			return Response.ok().header(HttpHeaders.CACHE_CONTROL, "no-store")
					.header("Pragma", "no-cache").build();
		} else {
			logger.error("用户ID:" + userId + "的原密码" + oldPwd + "不正确");
			return Response.status(Response.Status.NO_CONTENT)
					.header(HttpHeaders.CACHE_CONTROL, "no-store")
					.header("Pragma", "no-cache").build();
		}
	}

	/**
	 * 终端用户修改密码处理.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param modifyPwdInfo
	 *            密码修改信息.
	 * @return 密码修改结果.
	 */
	@Override
	public Response modifyPwd(String req) {
		logger.debug("处理客户端发起的用户密码修改请求");

		// 用户密码修改请求信息反序列化
		Map map = JacksonUtils.jsonToMapRuntimeException(req);
		String oldPwd = (String) map.get("old_pwd");
		String newPwd = (String) map.get("new_pwd");

		if (StringUtils.hasText(oldPwd) && StringUtils.hasText(newPwd)) {
			String userId = context.getHttpHeaders().getHeaderString("usr_id");
			UserInfo user = new UserInfo();
			user.setId(userId);
			user.setPwd(newPwd);
			user.setAlias(oldPwd);
			logger.debug("用户{}修改密码", user);
			int ret = accountService.updatePwd(user);
			logger.info("用户{}修改密码[{}]", user, ret);
			if (ret == 1) {
				return Response.ok()
						.header(HttpHeaders.CACHE_CONTROL, "no-store")
						.header("Pragma", "no-cache").build();
			} else {
				logger.error("用户密码修改请求信息错误");
				throw new ApplicationException(ErrorConstant.ERROR10002,
						Response.Status.BAD_REQUEST);
			}
		} else {
			logger.error("用户密码修改请求信息存在非法[参数内容为空]请求参数");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
	}

	/**
	 * 保存用户个性信息
	 */
	@Override
	public String saveAccount(String userInfo) {
		UserInfo user = JacksonUtils.fromJsonRuntimeException(userInfo,
				UserInfo.class);
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
		user.setId(userId);

		// 不检查空 可设置空值
		if(StringUtils.hasText(user.getXinming())&&user.getXinming().trim().length()>30){
			logger.error("真实姓名参数错误");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		if(StringUtils.hasText(user.getAddr())&&user.getAddr().trim().length()>99){
			logger.error("地址参数错误");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		logger.info("用户{}修改个人资料", user);
		int ret = accountService.saveParents(user);
		logger.info("用户{}修改密码[{}]", user, ret);
		if (ret == 0) {
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}

		 etagService.del(CachedCommon.CACHED_ACCOUNT+ user.getId());
		 List<Child> list= baseService.getList("Child.getChildren", userId);
		 if(list!=null&&list.size()>0){
			 for (Child child : list) {
			 etagService.del(CachedCommon.CACHED_AUTH+user.getId()+"_"+child.getId());
			}
		 }

		return HttpConstant.RESP_200;

	}
}

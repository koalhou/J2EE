
package com.yutong.axxc.parents.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.common.UserInfoKey;
import com.yutong.axxc.parents.entity.UserSeesion;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;

/**
 * 用户token信息拦截器. 所有需进行token验证的接口服务均需通过该拦截器进行token信息获取及验证.
 * 如token信息有效,系统将与该token信息绑定的终端用户信息添加到本次交易中HTTP协议的Header中供后续服务使用;
 * 如请求中不包含token信息,则向客户端返回状态码为400的HTTP应答信息;
 * 如请求中包含的token消息服务端侧无对应的有效终端用户信息,则向客户端返回状态码为401的HTTP应答消息;
 * 如token信息正确但有效时长已过期,则向客户端返回状态为298的HTTP应答消息.
 * 
 
 * @version $Revision 1.0 $ 2013-4-2 下午4:25:18
 */
public class OauthTokenCheckInterceptor extends
		AbstractPhaseInterceptor<Message> {

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

	private static final String ACCESS_TOKEN = "access_token";
	private static final String IF_NONE_MATCH = "If-None-Match";
	private static final String CLIENT_INFO = "client_info";
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private EtagService etagService;
	

	/**
	 * @param phase
	 */
	public OauthTokenCheckInterceptor(final String phase) {
		super(phase);
	}

	public OauthTokenCheckInterceptor() {
		this(Phase.UNMARSHAL);
	}

	/**
	 * 终端请求中token验证.
	 * 
	 * @param message
	 */
	@Override
	public void handleMessage(Message message) throws Fault {
		logger.info("用户访问的服务需进行授权认证");
		String token = "";
		String etag = "";
		String clientInfo = "";
		boolean isExistToken = false;
		boolean isExistEtag = false;
		Response response = null;
		Map<String, List<String>> reqHeaders = CastUtils
				.cast((Map<?, ?>) message.get(Message.PROTOCOL_HEADERS));
		if (reqHeaders != null) {
			for (Map.Entry<String, List<String>> e : reqHeaders.entrySet()) {
				if (ACCESS_TOKEN.equalsIgnoreCase(e.getKey())) {
					token = e.getValue().get(0);
					isExistToken = true;
				}
				if (IF_NONE_MATCH.equalsIgnoreCase(e.getKey())) {
					etag = e.getValue().get(0);
					isExistEtag = true;
				}
				if (CLIENT_INFO.equalsIgnoreCase(e.getKey())) {
					clientInfo = e.getValue().get(0);
					logger.debug("ClientInfo:{}",clientInfo);
				}
			}
		} else {
			logger.error("接收到的HTTP请求信息不包含HEADER信息");
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(ErrorConstant.ERROR10001.toJson())
					.header("Content-Type", "application/json;charset=UTF-8")
					.build();
		}
		if (!isExistToken || !StringUtils.hasText(token)) {
			logger.error("接收到的HTTP请求信息HEADER中不包含\"access_token\"信息");
			response = Response.status(Response.Status.BAD_REQUEST)
					.entity(ErrorConstant.ERROR20000.toJson())
					.header("Content-Type", "application/json;charset=UTF-8")
					.build();
		} else {
			logger.info("请求中包含的access_token={}" ,token);
			if (checkToken(token)) {
				try {
					//先从缓存获取，没有再查询数据库
					String key=CachedCommon.CACHED_TOKEN_KEY+token;
					UserSeesion session=(UserSeesion)etagService.get(key);
					if(session==null){
						session = baseService.get("Account.getIDByToken", token);
					}
					
						if (session==null||session.calExpire()<=0){
							logger.warn("用户access_token信息已过期,需重新登录");
							response = Response.status(ErrorConstant.HTTP_ERROR_SESSION_EXPIRE).build();
						}else{
							if(isExistEtag&&StringUtils.hasText(etag)){
								logger.debug("请求中包含的ETage:{}" , etag);
								etag=etag.replaceAll("\"", "");
								Object obj=etagService.get(etag);
								if(obj!=null){
									logger.info("用户 {} 已请求过 ETag:{}" ,session.getId(), etag);
									response = Response.status(Response.Status.NOT_MODIFIED)
											.header("Content-Type", "application/json;charset=UTF-8")
											.build();
								}
							}
							addUserInfo2HttpHeaderInfo(reqHeaders,session.getId());
						}
				} catch (Exception e) {
					logger.error("根据用户token获取其基本信息时出错", e);
					response = Response
							.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(ErrorConstant.ERROR90000.toJson())
							.header("Content-Type",
									"application/json;charset=UTF-8").build();
				}
			} else {
				logger.error("接收到的HTTP请求信息HEADER中\"access_token\"信息不符合规范");
				response = Response
						.status(Response.Status.BAD_REQUEST)
						.entity(ErrorConstant.ERROR10002.toJson())
						.header("Content-Type",
								"application/json;charset=UTF-8").build();
			}
		}
		message.getExchange().put(Response.class, response);
	}

	/**
	 * 将与token匹配的终端用户信息添加到本次交易的HTTP请求Header中.
	 * 
	 * @param reqHeaders
	 *            HTTP请求Header信息.
	 * @param userID
	 *            与token匹配的终端用户信息
	 * @return 添加有终端用户信息的HTTP请求Header信息.
	 */
	private Map<String, List<String>> addUserInfo2HttpHeaderInfo(
			Map<String, List<String>> reqHeaders, String userID) {
		reqHeaders.put(UserInfoKey.USR_ID, Arrays.asList(userID));
		logger.info("向HTTP Header中添加usr_id:" + userID);

		
		return reqHeaders;
	}

	/**
	 * 检查token信息是否合法
	 * 
	 * @param token
	 * @return
	 */
	private boolean checkToken(String token) {
		if (token.length() != 16) {
			logger.error("access_token信息长度为:" + token.length() + "非法");
			return false;
		} else {
			try {
				byte[] tokenByteArray = token.getBytes("UTF-8");
				// Validate the key
				for (byte b : tokenByteArray) {
					if (b == ' ' || b == '\n' || b == '\r' || b == 0) {
						logger.info("access_token信息中包含非法字符");
						return false;
					}
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("将token进行UTF-8转码时出错", e);
				return false;
			}
		}
		return true;
	}

}

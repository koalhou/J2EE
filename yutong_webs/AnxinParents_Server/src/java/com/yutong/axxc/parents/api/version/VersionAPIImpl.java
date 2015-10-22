package com.yutong.axxc.parents.api.version;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.oauth.SoftwareVersion;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.tools.JacksonUtils;
import com.yutong.axxc.parents.tools.PropertiesTools;

public class VersionAPIImpl implements VersionAPI {
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);
	private static String NEW_VERSION_SOFTWARE_ADD = "new.version.software.host";
	@Autowired
	private BaseService baseService;
	@Autowired
	protected EtagService etagService;
	@Context 
	private MessageContext context;

	@Override
	public Response checkSoftwareVersion(String req) {
		// 检查客户端版本
		Map reqMap=JacksonUtils.jsonToMapRuntimeException(req);
		String version=(String)reqMap.get("version");
		if (!StringUtils.hasText(version)) {
			logger.error("请求参数非法");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		} else {
			logger.debug("用户当前使用的软件版本为:" +version);
			//从缓存中获取最新的版本信息，没有取数据库并保存30分钟
			String key=CachedCommon.CACHED_VERSION;
			SoftwareVersion newVersion=(SoftwareVersion)etagService.get(key);
			logger.debug("缓存数据：{}", newVersion);
			if(newVersion==null){
				newVersion=baseService.get("Log.getMaxVersion", version);
				if(newVersion!=null){
					//存储在缓存
					try {
						String host = PropertiesTools.readValue(
								ModCommonConstant.SYS_CONF_FILE,
								NEW_VERSION_SOFTWARE_ADD);
						
						newVersion.setUri(host + newVersion.getUri());
						etagService.put(key, CachedCommon.CACHED_MINTER_30M, newVersion);
					} catch (IOException e) {
						logger.error("从配置文件" + ModCommonConstant.SYS_CONF_FILE_PATH
								+ "获取新版软件所在主机信息时出错", e);
						throw new ApplicationException(ErrorConstant.ERROR90000,
								Response.Status.INTERNAL_SERVER_ERROR);
					}
				}
			}
			//比较版本是否最新
			if ( newVersion==null || newVersion.getTargetVersion().compareToIgnoreCase(version.trim())<=0) {
					logger.info("未检索到高版本客户端软件信息");
					return Response.status(Status.NO_CONTENT).build();
			} else {
					return Response.ok(JacksonUtils.toJsonRuntimeException(newVersion)).build();
					
			}
			
		}
	}

}

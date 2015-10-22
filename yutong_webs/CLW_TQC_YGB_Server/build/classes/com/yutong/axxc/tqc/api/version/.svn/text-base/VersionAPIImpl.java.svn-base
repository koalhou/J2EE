package com.yutong.axxc.tqc.api.version;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.entity.oauth.SoftwareVersion;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.tools.JacksonUtils;
import com.yutong.axxc.tqc.tools.PropertiesTools;

public class VersionAPIImpl implements VersionAPI {
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory.getLogger(ModCommonConstant.LOGGER_NAME);
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
		String version_name =(String)reqMap.get("version_name");
		String version_code =(String)reqMap.get("version_code");
		if (!StringUtils.hasText(version_code) || !StringUtils.hasText(version_name)) {
			logger.info("VersionAPI ：version，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		} else {
			logger.debug("用户当前使用的软件版本为:" +version_name);
			
			SoftwareVersion newVersion= null ;//(SoftwareVersion)etagService.get(CachedCommon.CACHED_VERSION);
			if(newVersion==null){
				List<SoftwareVersion> softversionList =baseService.getList("Log.getMaxVersion", version_code);
				if (null == softversionList || softversionList.size() == 0) {
					logger.debug("未检索到高版本客户端软件信息");
					return Response.status(Status.NO_CONTENT).build();
				} else {
					newVersion = softversionList.get(0);
					try {
						String host = PropertiesTools.readValue(ModCommonConstant.SYS_CONF_FILE, NEW_VERSION_SOFTWARE_ADD);
						newVersion.setUri(host + newVersion.getUri());
						etagService.put(CachedCommon.CACHED_VERSION, CachedCommon.CACHED_MINTER_1D, newVersion);
						
					} catch (IOException e) {
						logger.error("从配置文件" + ModCommonConstant.SYS_CONF_FILE_PATH + "获取新版软件所在主机信息时出错", e);
						throw new ApplicationException(ErrorConstant.ERROR90000, Response.Status.INTERNAL_SERVER_ERROR);
					}
					
				}
			}
			String key=CachedCommon.CACHED_VERSION + version_code;
			etagService.put(key, CachedCommon.CACHED_MINTER_7D, key);
			return Response.ok(JacksonUtils.toJsonRuntimeException(newVersion)).tag(key).build();
		}
	}

	@Override
	public Response downloadrecord() {
		baseService.update("Log.insertDownloadRecord",new HashMap<String, String>());
		return Response.ok().build();
	}

}

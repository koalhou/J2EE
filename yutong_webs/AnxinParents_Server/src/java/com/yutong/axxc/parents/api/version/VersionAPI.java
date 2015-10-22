package com.yutong.axxc.parents.api.version;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;


public interface VersionAPI {
	/**
	 * 客户端软件新版本检查接口.
	 * 
	 * @param version
	 *            客户端当前软件版本号.
	 * @return 客户端软件新版本检查结果消息.
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response checkSoftwareVersion(String version);
}

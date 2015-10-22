package com.yutong.axxc.tqc.api.version;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;


public interface VersionAPI {
	/**
	 * 客户端软件新版本检查接口.
	 * 
	 * @param version 客户端当前软件版本号.
	 * @return 客户端软件新版本检查结果消息.
	 */
	
	@POST
	@Path(value = "version")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response checkSoftwareVersion(String version);
	
	/**
	 * 客户端软件新版本检查接口.
	 * 
	 * @param version 客户端当前软件版本号.
	 * @return 客户端软件新版本检查结果消息.
	 */
	
	@GET
	@Path(value = "downloadrec")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response downloadrecord();	
}

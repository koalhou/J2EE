package com.yutong.axxc.parents.api.log;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface BehaviorAPI {
	
	/**
	 * 用户行为信息上报接口.
	 * 
	 * @param behaviorInfo
	 *            终端用户行为信息.
	 * @return 行为结果上报结果.
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response behavior(@HeaderParam("access_token") String token,String behaviorInfo);
	

}

/**
 * @(#)PushRuleService.java 2013-4-8 Copyright 2013 Neusoft Group Ltd. All
 *                          rights reserved. Neusoft PROPRIETARY/CONFIDENTIAL.
 *                          Use is subject to license terms.
 */
package com.yutong.axxc.parents.api.pushmsg.rule;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface PushRulesAPI
{

    /**
     * 获取个人推送规则服务
     * 
     * @param token 用户令牌
     * @return 用户所有的推送，包括系统和学生的
     */
    @GET
    @Path(value = "rule")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getRule(@HeaderParam("access_token") String token);

    /**
     * 设置个人推送规则服务
     * 
     * @param content 多条个人推送规则
     */
    @PUT
    @Path(value = "rule")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response setRule( String content);

}

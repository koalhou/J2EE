package com.yutong.axxc.parents.api.pushmsg.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.entity.pushRule.PushRuleInfo;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.RuleService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;

public class PushRulesAPIImpl implements PushRulesAPI
{

    @Context
    private MessageContext context;

    @Autowired
    private BaseService baseService;
    @Autowired
    private RuleService ruleService;
	@Autowired
	protected EtagService etagService;
	@Autowired
	private SessionService sessionService;
    private Logger logger = LoggerFactory.getLogger(PushRulesAPIImpl.class);

    /**
     * 获取个人推送规则服务
     * 
     */
    @Override
    public Response getRule(String token)
    {
        String usrId = context.getHttpHeaders().getHeaderString("usr_id");
     
        logger.debug("获取用户推送规则列表");
        List<PushRuleInfo> list = baseService.getList("PushRules.getPushRules", usrId);

        if (list != null && list.size()> 0)
        {
        	Map resultMap=new HashMap();
        	resultMap.put("rule_contents", list);
        	
        	String key=CachedCommon.CACHED_RULE+usrId;
			etagService.put(key, CachedCommon.CACHED_MINTER_1D, key);
			sessionService.updateSession(token, key);
            return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
        }
        return Response.noContent().build();
    }


    /**
     * 设置个人推送规则服务
     */
    @Override
    public Response setRule(String content)
    {
    	String usrId = context.getHttpHeaders().getHeaderString("usr_id");
        logger.debug("处理设置个人推送规则请求");
        @SuppressWarnings("unchecked")
        Map<String, List<Map<String, String>>> tm = (Map<String, List<Map<String, String>>>) JacksonUtils.jsonToMapRuntimeException(content);
        List<Map<String, String>> list = tm.get("rule_contents");
        
        int ret=ruleService.updateRule(usrId, list);
        logger.info("更新规则{},[{}]",content,ret);
        
        String key=CachedCommon.CACHED_RULE+usrId;
		etagService.del(key);

        return  Response.ok().build();
    }
}

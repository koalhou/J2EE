package com.yutong.axxc.tqc.boot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;

public class QuartzJob {
	
	@Autowired
	protected BaseService baseService;

	@Autowired
	protected EtagService etagService;
	
	/**
	 * 推荐站点缓存更新    
	 */
	public void work(){  
		System.out.println("更新推荐站点缓存开始！");
		//BaseService bs = (BaseService)webApplicationContext.getBean(BaseService.class);
		//EtagService es = (EtagService)webApplicationContext.getBean(EtagService.class);
		List<Map<String, String>> configData = baseService.getList("Station.getSystemConfig",null);
		for(Map<String, String> map : configData){
			etagService.put(map.get("PARAM_NAME"), 0, map.get("PARAM_VALUE"));
		}
	} 

}

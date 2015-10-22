package com.yutong.axxc.parents.api.resources;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.entity.StorageFile;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.ResourceService;
import com.yutong.axxc.parents.tools.FileUtil;
import com.yutong.axxc.parents.tools.JacksonUtils;


public class ResourcesAPIImpl implements ResourcesAPI {
	private Logger logger = LoggerFactory.getLogger(ResourcesAPIImpl.class);
	
	@Context
	private MessageContext context;
	
	@Autowired
	protected BaseService baseService;
	
	@Autowired
	protected ResourceService resourceService;
	@Autowired
	protected EtagService etagService;
	@Override
	public Response getResource(String resId) {
		logger.debug("处理终端获取资源请求");

		// 判断是否为空
		if ( !StringUtils.hasText(resId)) {
			logger.error("终端参数不符合要求");
			throw new ApplicationException(ErrorConstant.ERROR10001,
					Response.Status.BAD_REQUEST);
		}

		StorageFile file=baseService.get("File.get", resId);

		if (file == null) {
			logger.error("未获取到对应的资源");
			return Response.noContent().build();
		}
		//计数
		baseService.update("File.setCount", resId);

		String s=FileUtil.readFile(file.getPath()+file.getName());
		if(!StringUtils.hasText(s)){
			throw new ApplicationException(ErrorConstant.ERROR10029,
					Response.Status.BAD_REQUEST);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("suffix", file.getSuf());
		resultMap.put("resource", s);
		String key=CachedCommon.CACHED_FILE+file.getId();
		boolean b=etagService.put(key, CachedCommon.CACHED_MINTER_EVER, key);
		
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
	}

	@Override
	public Response setResource(String req) {
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		
		Map desc=(Map) map.get("res_desc");
		String ext=(String) desc.get("suffix");
		String platform=(String) desc.get("platform");
		String size=(String) desc.get("size");
		String resource=(String) map.get("resource");
		
		if (!StringUtils.hasText(platform)||!StringUtils.hasText(ext)||!StringUtils.hasText(resource)) {
			logger.error("终端参数不符合要求");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		StorageFile file=new StorageFile();
		file.setCreater(userId);
		file.setSuf(ext);
		file.setOwner(userId);
		file.setApplyId(platform);
		file.setModuleId("");
		file.setDescription("");
		int fileID=resourceService.setResource(file, resource);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("res_id", fileID);
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

}

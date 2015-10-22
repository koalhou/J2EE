/**
 * @(#)ResourceServiceImpl.java 2013-4-11
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.entity.StorageFile;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.mapper.MybatisDAO;
import com.yutong.axxc.tqc.tools.FileUtil;

/**
 * @author wyg
 */
@Service
public class ResourceService {

	@Context
	private MessageContext context;
	@Autowired
	protected MybatisDAO dao;
	@Autowired
	private EtagService etagService;
	
	private final String UPLOAD_PATH = "upload.path";
	private Logger logger = LoggerFactory.getLogger(ResourceService.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 终端获取资源API
	 * 
	 * @param token 用户token
	 *            
	 * @param type 资源类型 0- 家长头像图片 1- 学生头像图片 2- 学生背景图片 3- 学生个性声音
	 *            
	 * @param resId 资源ID
	 *            
	 * @return
	 */

	public int setResource(Map<String, String> file_temp, String res) {
		int fileID = -1;
		try {
			String path = String.valueOf(etagService.get("YGB_UPLOAD_PHOTO_PATH") + sdf.format(new Date()) + "/" + file_temp.get("owner") + "/");
			fileID = dao.get("File.getSeq", "SEQ_FILE");
			String fileName = path + fileID + "." + file_temp.get("suf");
			logger.info("file name:{}", fileName);
			File folder = new File(path);
			if(!(folder).exists()){
				folder.mkdirs();
			}
			int len = FileUtil.writeFile(res, path + fileID + "." + file_temp.get("suf"));
			file_temp.put("id", String.valueOf(fileID));
			file_temp.put("len", String.valueOf(len));
			file_temp.put("path", path);
			file_temp.put("name", fileID + "." + file_temp.get("suf"));
			file_temp.put("server", "");
			file_temp.put("file_dlcount", "0");
			int ret = save(file_temp);
			logger.info("upload result:", ret);
		} catch (Exception e) {
			logger.error("上传文件异常异常", e);
			throw new ApplicationException(ErrorConstant.ERROR10024, Response.Status.BAD_REQUEST);
		}

		return fileID;
	}

	@Transactional
	private int save(Map<String, String> file) {
		return dao.save("File.add", file);
	}

}

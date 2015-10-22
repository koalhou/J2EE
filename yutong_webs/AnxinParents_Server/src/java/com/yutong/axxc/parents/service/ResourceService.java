/**
 * @(#)ResourceServiceImpl.java 2013-4-11
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.service;

import java.io.File;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.Child;
import com.yutong.axxc.parents.entity.StorageFile;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.mapper.MybatisDAO;
import com.yutong.axxc.parents.tools.FileUtil;
import com.yutong.axxc.parents.tools.PropertiesTools;

/**
 * @author wyg
 */
@Service
public class ResourceService {

	@Context
	private MessageContext context;
	@Autowired
	protected MybatisDAO dao;

	private final String UPLOAD_PATH = "upload.path";
	private Logger logger = LoggerFactory.getLogger(ResourceService.class);

	/**
	 * 终端获取资源API
	 * 
	 * @param token
	 *            用户token
	 * @param type
	 *            资源类型 0- 家长头像图片 1- 学生头像图片 2- 学生背景图片 3- 学生个性声音
	 * @param resId
	 *            资源ID
	 * @return
	 */

	public int setResource(StorageFile file, String res) {
		int fileID = -1;
		try {
			String path = PropertiesTools.readValue(
					ModCommonConstant.SYS_CONF_FILE, UPLOAD_PATH);
			fileID = dao.get("File.getSeq", "seq_clw_bsp_unstructur_file_id");
			String fileName = path + fileID + "." + file.getSuf();
			logger.info("file name:{}", fileName);
			int len = FileUtil.writeFile(res,
					path + fileID + "." + file.getSuf());
			file.setId(String.valueOf(fileID));
			file.setLen(len);
			file.setPath(path);
			file.setName(fileID + "." + file.getSuf());
			file.setServer("");
			int ret = save(file);
			logger.info("upload result:", ret);
		} catch (Exception e) {
			logger.error("上传文件异常异常", e);
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}

		return fileID;
	}

	@Transactional
	private int save(StorageFile file) {
		return dao.save("File.add", file);
	}

}

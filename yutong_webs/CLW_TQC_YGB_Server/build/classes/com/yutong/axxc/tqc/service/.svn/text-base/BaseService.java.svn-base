package com.yutong.axxc.tqc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yutong.axxc.tqc.mapper.MybatisDAO;

@Service
public class BaseService {

	@Autowired
	protected MybatisDAO dao;
	
	public int save(String key, Object object) {
		return dao.save(key, object);
	}

	public int delete(String key, Object object) {
		return dao.delete(key, object);
	}

	public int update(String key, Object object) {
		return dao.update(key, object);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object object) {
		return (T) dao.get(key, object);
	}

	public <T> List<T> getList(String key) {
		return dao.getList(key);
	}

	public <T> List<T> getList(String key, Object object) {
		return dao.getList(key, object);
	}
	
}

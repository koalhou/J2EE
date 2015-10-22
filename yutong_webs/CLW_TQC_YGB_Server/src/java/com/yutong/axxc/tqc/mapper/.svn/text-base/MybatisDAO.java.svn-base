package com.yutong.axxc.tqc.mapper;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;


public class MybatisDAO extends SqlSessionDaoSupport {
    
	public int save(String key, Object object) {
		return getSqlSession().insert(key, object);
	}

	public int delete(String key, Object object) {
		return getSqlSession().delete(key, object);
	}

	public int update(String key, Object object) {
		 return getSqlSession().update(key, object);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Object object) {
		return (T) getSqlSession().selectOne(key, object);
	}

	public <T> List<T> getList(String key) {
		return getSqlSession().selectList(key);
	}

	public <T> List<T> getList(String key, Object object) {
		return getSqlSession().selectList(key, object);
	}

}

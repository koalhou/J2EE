/**
 * @author haoxy
 * @createdate 2013年9月16日 下午12:33:20
 * @description 
 */
package com.yutong.axxc.parents.entity.distance;

/**
 * @author haoxy
 * 
 */
public class SQLAdapter
{
	String sql;

	public SQLAdapter(String sql)
	{
		this.sql = sql;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}
}

package com.yutong.axxc.parents.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UserSeesion implements Serializable {

	private static final long serialVersionUID = 2449148321494700841L;

	private String id;
	private String accessToken;
	private String refreshToken;
	private Date expireTime;
	private List<String> keys=new ArrayList<String>() ;

	private int expire;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getExpire() {
		return expire;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public void addKey(String key) {
		this.keys.add(key);
	}

	public List<String> getKeys() {
		return keys;
	}

	public long calExpire(){
		Calendar cal = Calendar.getInstance();
		long diff = this.expireTime.getTime() - cal.getTime().getTime();
		this.expire= Long.valueOf(diff / (1000 * 60 )).intValue();
		return this.expire;
	}

	@Override
	public String toString() {
		return "UserSeesion [id=" + id + ", accessToken=" + accessToken
				+ "]";
	}

}

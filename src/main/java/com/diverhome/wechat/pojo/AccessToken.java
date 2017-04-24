package com.diverhome.wechat.pojo;

/**
 * 微信通用接口凭证
 * 
 * @author anchao
 * 
 */
public class AccessToken {

	private static AccessToken accessToken;
	
	// 获取到的凭证
	private String token;
	// 凭证有效时间，单位：秒
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	private AccessToken() {
	}

	public static AccessToken getInstance() {
		if (accessToken == null) {
			accessToken = new AccessToken();
			return accessToken;
		}
		return accessToken;
	}

}

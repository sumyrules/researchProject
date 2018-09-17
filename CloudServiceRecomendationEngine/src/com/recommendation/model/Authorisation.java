package com.recommendation.model;

public class Authorisation {
	private String accessKeyId;
	private String accessSecretKey;
	private String iamRole;
	
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessSecretKey() {
		return accessSecretKey;
	}
	public void setAccessSecretKey(String accessSecretKey) {
		this.accessSecretKey = accessSecretKey;
	}
	public String getIamRole() {
		return iamRole;
	}
	public void setIamRole(String iamRole) {
		this.iamRole = iamRole;
	}

}

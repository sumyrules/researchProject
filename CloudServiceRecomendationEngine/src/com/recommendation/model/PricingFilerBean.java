package com.recommendation.model;

public class PricingFilerBean {
	private String serviceProvder;
	private String serviceCode;
	private String region;
	private String databaseEngine;
	private String location;
	private String instanceCode;
	
	/**
	 * Java/ Mysql
	 */
	private String serviceType;
	
	public String getServiceProvder() {
		return serviceProvder;
	}
	public void setServiceProvder(String serviceProvder) {
		this.serviceProvder = serviceProvder;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDatabaseEngine() {
		return databaseEngine;
	}
	public void setDatabaseEngine(String databaseEngine) {
		this.databaseEngine = databaseEngine;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInstanceCode() {
		return instanceCode;
	}
	public void setInstanceCode(String instanceCode) {
		this.instanceCode = instanceCode;
	}
}

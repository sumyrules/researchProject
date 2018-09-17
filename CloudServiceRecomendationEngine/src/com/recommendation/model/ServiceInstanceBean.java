package com.recommendation.model;

public class ServiceInstanceBean {

	private String instanceType;
	private String intanceName;
	private String region;
	private SecurtiyGroup securtiyGroup;
	private String operatingSystem;
	private String numberOfInstances;
	private Authorisation authorisation;
	private NetworkInfo vpc;
	private Storage storage;
	
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getIntanceName() {
		return intanceName;
	}
	public void setIntanceName(String intanceName) {
		this.intanceName = intanceName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public SecurtiyGroup getSecurtiyGroup() {
		return securtiyGroup;
	}
	public void setSecurtiyGroup(SecurtiyGroup securtiyGroup) {
		this.securtiyGroup = securtiyGroup;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getNumberOfInstances() {
		return numberOfInstances;
	}
	public void setNumberOfInstances(String numberOfInstances) {
		this.numberOfInstances = numberOfInstances;
	}
	public Authorisation getAuthorisation() {
		return authorisation;
	}
	public void setAuthorisation(Authorisation authorisation) {
		this.authorisation = authorisation;
	}
	public NetworkInfo getVpc() {
		return vpc;
	}
	public void setVpc(NetworkInfo vpc) {
		this.vpc = vpc;
	}
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}

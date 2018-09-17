package com.recommendation.model;

public class NetworkInfo {

	private String ipAddress;
	private String vpcName;
	private String vpcId;
	private String routeTable;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getVpcName() {
		return vpcName;
	}
	public void setVpcName(String vpcName) {
		this.vpcName = vpcName;
	}
	public String getVpcId() {
		return vpcId;
	}
	public void setVpcId(String vpcId) {
		this.vpcId = vpcId;
	}
	public String getRouteTable() {
		return routeTable;
	}
	public void setRouteTable(String routeTable) {
		this.routeTable = routeTable;
	}
}

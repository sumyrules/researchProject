package com.tool.recommendation;

public class ServiceData {
	
	public static final String PRICE ="CostPerYear";
	public static final String INSTANCE_CODE="instanceCode";
	public static final String PERFORMANCE_PER_HOUR="perfPerHour";
	public static final String PERFORMANCE_BY_COST="performanceByCost";
	
	private String ServiceName;
	private String benchMarkType;
	private String instanceCode;
	private String costPerHour;
	private String perfPerHour;
	private String performanceByCost;
	private String CostPerYear;
	
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		this.ServiceName = serviceName;
	}
	public String getBenchMarkType() {
		return benchMarkType;
	}
	public void setBenchMarkType(String benchMarkType) {
		this.benchMarkType = benchMarkType;
	}
	public String getInstanceCode() {
		return instanceCode;
	}
	public void setInstanceCode(String instanceCode) {
		this.instanceCode = instanceCode;
	}
	
	public String getCostPerHour() {
		return costPerHour;
	}
	public void setCostPerHour(String costPerHour) {
		this.costPerHour = costPerHour;
	}
	public String getPerfPerHour() {
		return perfPerHour;
	}
	public void setPerfPerHour(String perfPerHour) {
		this.perfPerHour = perfPerHour;
	}
	public String getPerformanceByCost() {
		return performanceByCost;
	}
	public void setPerformanceByCost(String performanceByCost) {
		this.performanceByCost = performanceByCost;
	}
	public String getCostPerYear() {
		return CostPerYear;
	}
	public void setCostPerYear(String costPerYear) {
		this.CostPerYear = costPerYear;
	}
}

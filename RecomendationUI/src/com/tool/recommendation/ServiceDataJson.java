package com.tool.recommendation;

import java.util.HashMap;
import java.util.Map;

public class ServiceDataJson {
	
	private String serviceName;
	private String benchmarkType;
	private Map<String, Map<String,String>> results = new HashMap<>();
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getBenchmarkType() {
		return benchmarkType;
	}
	public void setBenchmarkType(String benchmarkType) {
		this.benchmarkType = benchmarkType;
	}
	public Map<String, Map<String, String>> getResults() {
		return results;
	}
	public void setResults(Map<String, Map<String, String>> results) {
		this.results = results;
	}
	

}

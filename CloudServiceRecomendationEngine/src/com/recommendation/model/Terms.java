package com.recommendation.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Terms {
	@SerializedName("OnDemand")
	@Expose
	Map<String, Map<String,TermDetails>> onDemand = new HashMap<>();
	@SerializedName("Reserved")
	@Expose
	Map<String, Map<String,TermDetails>> reserved = new HashMap<>();
	
	public Map<String, Map<String, TermDetails>> getOnDemand() {
		return onDemand;
	}
	public void setOnDemand(Map<String, Map<String, TermDetails>> onDemand) {
		this.onDemand = onDemand;
	}
	public Map<String, Map<String, TermDetails>> getReserved() {
		return reserved;
	}
	public void setReserved(Map<String, Map<String, TermDetails>> reserved) {
		this.reserved = reserved;
	}
}

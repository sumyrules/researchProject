package com.recommendation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PricePerUnit {
	@SerializedName("USD")
	@Expose
	private String uSD;

	public String getUSD() {
	return uSD;
	}

	public void setUSD(String uSD) {
	this.uSD = uSD;
	}

}

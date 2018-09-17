package com.recommendation.model;

import java.util.List;

public class PriceDimensions {
	private String rateCode;
	private String description;
	private String beginRange;
	private String endRange;
	private String unit; 
	private PricePerUnit pricePerUnit;
	private List<Object> appliesTo = null;

	public String getRateCode() {
	return rateCode;
	}

	public void setRateCode(String rateCode) {
	this.rateCode = rateCode;
	}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public String getBeginRange() {
	return beginRange;
	}

	public void setBeginRange(String beginRange) {
	this.beginRange = beginRange;
	}

	public String getEndRange() {
	return endRange;
	}

	public void setEndRange(String endRange) {
	this.endRange = endRange;
	}

	public String getUnit() {
	return unit;
	}

	public void setUnit(String unit) {
	this.unit = unit;
	}

	public PricePerUnit getPricePerUnit() {
	return pricePerUnit;
	}

	public void setPricePerUnit(PricePerUnit pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
	}

	public List<Object> getAppliesTo() {
	return appliesTo;
	}

	public void setAppliesTo(List<Object> appliesTo) {
	this.appliesTo = appliesTo;
	}
}

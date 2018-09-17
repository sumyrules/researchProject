package com.recommendation.model;

import java.util.Map;

public class TermDetails {
	private String offerTermCode;
	private String sku;
	private String effectiveDate;
	private Map<String , PriceDimensions> priceDimensions;
	private Map<String,String> termAttributes; 

	public String getOfferTermCode() {
	return offerTermCode;
	}

	public void setOfferTermCode(String offerTermCode) {
	this.offerTermCode = offerTermCode;
	}

	public String getSku() {
	return sku;
	}

	public void setSku(String sku) {
	this.sku = sku;
	}

	public String getEffectiveDate() {
	return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
	this.effectiveDate = effectiveDate;
	}

	public Map<String, PriceDimensions> getPriceDimensions() {
		return priceDimensions;
	}

	public void setPriceDimensions(Map<String, PriceDimensions> priceDimensions) {
		this.priceDimensions = priceDimensions;
	}

	public Map<String, String> getTermAttributes() {
		return termAttributes;
	}

	public void setTermAttributes(Map<String, String> termAttributes) {
		this.termAttributes = termAttributes;
	}
}

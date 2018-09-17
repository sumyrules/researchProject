package com.recommendation.model;
import java.util.Map;

public class AwsService {
	private String formatVersion;
	private String disclaimer;
	private String offerCode;
	private String version;
	private String publicationDate;
	private Map<String , Product> products;
	private Terms terms;
	public String getFormatVersion() {
	return formatVersion;
	}

	public void setFormatVersion(String formatVersion) {
	this.formatVersion = formatVersion;
	}

	public String getDisclaimer() {
	return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
	this.disclaimer = disclaimer;
	}

	public String getOfferCode() {
	return offerCode;
	}

	public void setOfferCode(String offerCode) {
	this.offerCode = offerCode;
	}

	public String getVersion() {
	return version;
	}

	public void setVersion(String version) {
	this.version = version;
	}

	public String getPublicationDate() {
	return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
	this.publicationDate = publicationDate;
	}

	public Map<String, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product> products) {
		this.products = products;
	}

	public void setTerms(Terms terms) {
		this.terms = terms;
	}

	public Terms getTerms() {
		return terms;
	}


}

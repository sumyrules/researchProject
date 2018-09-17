package com.tool.recommendation;

import java.text.DecimalFormat;

public class RecommendationBean {
private  ServiceData serviceData1;
private ServiceData serviceData2;
private boolean isRecommended; 

public RecommendationBean() {
}
public RecommendationBean(ServiceData servicedata1, ServiceData serviceData2){
	this.serviceData1 =servicedata1;
	this.serviceData2 =serviceData2;
}

public ServiceData getServiceData1() {
	return serviceData1;
}
public void setServiceData1(ServiceData serviceData1) {
	this.serviceData1 = serviceData1;
}
public ServiceData getServiceData2() {
	return serviceData2;
}
public void setServiceData2(ServiceData serviceData2) {
	this.serviceData2 = serviceData2;
}

public String getServiceName1() {
	return this.serviceData1 != null ?  this.serviceData1.getServiceName(): "";
}

public String getServiceName2() {
	return this.serviceData2 != null ?  this.serviceData2.getServiceName() : "";
}

public double getPrice() {
	double price = 0;
	if(serviceData1 != null) {
		price = price + DataUtils.convertToPriceDouble(serviceData1.getCostPerYear());
	}
	if(serviceData2 != null) {
		price = price + DataUtils.convertToPriceDouble(serviceData2.getCostPerYear());
	}
	price = Double.parseDouble(new DecimalFormat("##.###").format(price));
	return price;
}

public boolean isRecommended() {
	return isRecommended;
}
public void setRecommended(boolean isRecommended) {
	this.isRecommended = isRecommended;
}


}

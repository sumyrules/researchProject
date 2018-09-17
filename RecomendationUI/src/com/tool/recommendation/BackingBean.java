package com.tool.recommendation;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class BackingBean {
	private String priceLimit = "400";
	boolean ishighPerformance;
	List<RecommendationBean> recommendationList;
	private String application;
	private double mySqlPricePercentage =50;

	public String getSomeProperty() {
		return (priceLimit);
	}

	public boolean isRecommendationTableRendered() {
		return recommendationList != null && recommendationList.size() > 0;
	}

	public void setSomeProperty(String priceLimit) {
		this.priceLimit = priceLimit;
	}

	public String getCloudRecommendations() {
		return ("pricing_recomendation");
	}

	public String getApplicationRecommendations() {
		return ("application_recomendation");
	}

	public String getPriceLimit() {
		return priceLimit;
	}

	public void setPriceLimit(String priceLimit) {
		this.priceLimit = priceLimit;
	}

	public List<RecommendationBean> getData() {
		ServiceRecomendationFilter awsFilter = new ServiceRecomendationFilter();
		recommendationList = awsFilter.getAllCombinationsForPrice(priceLimit, ishighPerformance, 50);
		return recommendationList;
	}

	public boolean isIshighPerformance() {
		return ishighPerformance;
	}

	public void setIshighPerformance(boolean ishighPerformance) {
		this.ishighPerformance = ishighPerformance;
	}

	public String priceBasedActionControllerMethod() {
		return "pricing_recomendation";
	}

	public RecommendationBean getRecomendedService() {
		if (priceLimit == null ) {
			return null;
		}
		return findRecommendation();
		/*
		 * if (this.recommendationList != null && this.recommendationList.size() > 0 ) {
		 * return recommendationList.get(0); } return null;
		 */
	}
	
	public RecommendationBean getApplicationBasedRecommendation() {
		if (priceLimit == null ) {
			return null;
		}
		setMySqlPricePercentage();
		ServiceRecomendationFilter awsFilter = new ServiceRecomendationFilter();
		ishighPerformance = true;
		recommendationList = awsFilter.getAllCombinationsForPrice(priceLimit, ishighPerformance, mySqlPricePercentage);
		return findRecommendation();
	}

	private void setMySqlPricePercentage() {
		if(getApplication() == null ) {
			return;
		}
		if (getApplication().equalsIgnoreCase("a")){
			mySqlPricePercentage =88;
		}else if (getApplication().equalsIgnoreCase("b")){
			mySqlPricePercentage=60;
		}else if (getApplication().equalsIgnoreCase("c")){
			mySqlPricePercentage =40;
		}
	}

	private RecommendationBean findRecommendation() {
		System.out.println("Mysql percentage "+ mySqlPricePercentage + "priceLimit = " + priceLimit);
		List<RecommendationBean> data = this.recommendationList;
		if (data == null || data.size() < 1) {
			return null;
		} else if (ishighPerformance) {
			double priceLimitDouble = DataUtils.convertToPriceDouble(priceLimit);
			ServiceData highestPerformingMySql = null;
			ServiceData highestPerformingJava = null;
			double maxPerfMySql = 0;
			double maxPerfJava = 0;
			for (RecommendationBean rb : data) {
				ServiceData mySqlServiceData = rb.getServiceData2();
				double perfMySql = DataUtils.convertToPriceDouble(mySqlServiceData.getPerfPerHour());
				if (perfMySql > maxPerfMySql
						&& DataUtils.convertToPriceDouble(mySqlServiceData.getCostPerYear()) <= priceLimitDouble*(mySqlPricePercentage/100)) {
					maxPerfMySql = perfMySql;
					highestPerformingMySql = mySqlServiceData;
				}
			}

			double availablePrice = priceLimitDouble
					- DataUtils.convertToPriceDouble(highestPerformingMySql.getCostPerYear());
			for (RecommendationBean rb : data) {
				ServiceData javaServiceData = rb.getServiceData1();
				double perfJava = DataUtils.convertToPriceDouble(javaServiceData.getPerformanceByCost());
				if (perfJava > maxPerfJava
						&& DataUtils.convertToPriceDouble(javaServiceData.getCostPerYear()) < availablePrice) {
					maxPerfJava = perfJava;
					highestPerformingJava = javaServiceData;
				}
			}
			return new RecommendationBean(highestPerformingJava, highestPerformingMySql);
		} else {
			double priceLimitDouble = DataUtils.convertToPriceDouble(priceLimit);
			ServiceData mostEfficientMySql = null;
			double maxEfficiencyMySql = 0;

			ServiceData mostEfficientJava = null;
			double maxEfficiencyJava = 0;

			for (RecommendationBean rb : data) {

				ServiceData mySqlServiceData = rb.getServiceData2();
				double efficiencyMySql = DataUtils.convertToPriceDouble(mySqlServiceData.getPerformanceByCost());

				if (efficiencyMySql > maxEfficiencyMySql
						&& DataUtils.convertToPriceDouble(mySqlServiceData.getCostPerYear()) <= priceLimitDouble / 2) {
					maxEfficiencyMySql = efficiencyMySql;
					mostEfficientMySql = mySqlServiceData;
				}
			}

			double availablePrice = priceLimitDouble
					- DataUtils.convertToPriceDouble(mostEfficientMySql.getCostPerYear());
			for (RecommendationBean rb : data) {
				ServiceData javaServiceData = rb.getServiceData1();
				double efficiencyJava = DataUtils.convertToPriceDouble(javaServiceData.getPerformanceByCost());
				if (efficiencyJava > maxEfficiencyJava
						&& DataUtils.convertToPriceDouble(javaServiceData.getCostPerYear()) < availablePrice) {
					maxEfficiencyJava = efficiencyJava;
					mostEfficientJava = javaServiceData;
				}
			}
			return new RecommendationBean(mostEfficientJava, mostEfficientMySql);
		}
	}

	public static void main(String[] args) {
		System.out.println(String.valueOf(Double.MAX_VALUE));
//		BackingBean b = new BackingBean();
	}

	protected static void print(List<ServiceData> dataList) {
		for (ServiceData d : dataList) {
			System.out.println(d.getCostPerYear());
		}
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
}

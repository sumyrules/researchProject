package com.tool.recommendation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ServiceRecomendationFilter {

	private static final String FILES_DIRECTORY = "/home/ubuntu/tomcat/GeneratedJSON/";
	//	private static final String FILES_DIRECTORY = "D:\\Study\\Sem 3\\Research Project\\Demo\\GeneratedJSON";
	public List<RecommendationBean> getAllCombinationsForPrice(String priceLimit, boolean isHighPerformance , double javaPricePercentage){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<String> javadataFiles = getJavaDataFiles();
		List<String> mySqlDataFiles = getMySqlDataFiles();
		List<ServiceData> serviceDataList1 = getServiceDataList(priceLimit, isHighPerformance,  gson, javadataFiles);
		List<ServiceData> serviceDataList2 = getServiceDataList(priceLimit, isHighPerformance,  gson, mySqlDataFiles);
		return getAllRecommendationsForPrice(serviceDataList1,serviceDataList2,priceLimit);
	}

	private List<RecommendationBean> getAllRecommendationsForPrice(List<ServiceData> serviceDataList1, List<ServiceData> serviceDataList2, String priceLimit) {
		List<RecommendationBean> recommendationList = new ArrayList<>();
		for( int i  = 0 ; i < serviceDataList1.size() ; i++) {
			for (int j = 0 ; j < serviceDataList2.size() ; j++) {
				try {
					ServiceData serviceDataJava = serviceDataList1.get(i);
					ServiceData serviceDataMySql = serviceDataList2.get(j);
					RecommendationBean recommendationBean = new RecommendationBean(serviceDataJava, serviceDataMySql);
					if(recommendationBean.getPrice() > DataUtils.convertToPriceDouble(priceLimit)) continue;
					recommendationList.add(recommendationBean);
				}catch(IndexOutOfBoundsException ie) {
					System.out.println("There less than 2 recommendations. Count on java " + i + " Count of mysql " + j);
				}
			}
		}
		return recommendationList;
	}

	/*private List<RecommendationBean> filterTopRecommendationsByPrice(List<ServiceData> serviceDataList1, List<ServiceData> serviceDataList2) {
	}*/

	private List<ServiceData> getServiceDataList(String priceLimit, boolean isHighPerformance, Gson gson,
			List<String> javadataFiles) {
		List<ServiceData> serviceDataList1 = new ArrayList<>();
		for(String fileLocation : javadataFiles) {
			List<ServiceData> serviceDatafromFile = createServiceDatafromFile(priceLimit, isHighPerformance, gson, fileLocation);
			if(serviceDatafromFile != null) {
				serviceDataList1.addAll(serviceDatafromFile);
			}
		}
		return serviceDataList1;
	}

	private List<String> getJavaDataFiles() {
		List<String> dataFiles = new ArrayList<String>();
		String ec2JvmFile = FILES_DIRECTORY+"AWS_EC2_JVM_Ireland.json";
		dataFiles.add(ec2JvmFile);
		String gcpJvm = FILES_DIRECTORY+"GCP_JVM_London.json";
		dataFiles.add(gcpJvm);
		for(String s : dataFiles) System.out.println(s);
		return dataFiles;
	}

	private List<String> getMySqlDataFiles() {
		List<String> dataFiles = new ArrayList<>();
		String ec2MySqlFile = FILES_DIRECTORY+"AWS_EC2_MySQL_Ireland.json";
		dataFiles.add(ec2MySqlFile);
		String rdsMySql = FILES_DIRECTORY+"AWS_RDS_MySQL_Ireland.json";
		dataFiles.add(rdsMySql);
		String gcpMySql = FILES_DIRECTORY+"GCP_MySQL_London.json";
		dataFiles.add(gcpMySql);
		return dataFiles;
	}

	private List<ServiceData> createServiceDatafromFile(String priceLimit, boolean isHighPerformance,Gson gson, String fileLocation) {
		String dataString = "";
		try {
			dataString = new String(Files.readAllBytes(Paths.get(fileLocation)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
//		System.out.println("JSON DATA \n"+dataString);
		ServiceDataJson service = gson.fromJson(dataString, ServiceDataJson.class);
		String benchMarkType = service.getBenchmarkType();
		List<ServiceData> dataList = new ArrayList<ServiceData>();
		createDataListForJson(priceLimit, service, benchMarkType, dataList);
		sortDataList(isHighPerformance, dataList);
		return dataList;
	}

	private void createDataListForJson(String priceLimit, ServiceDataJson service, String benchMarkType,
			List<ServiceData> dataList) {
		String serviceName = service.getServiceName();
		Map<String, Map<String, String>> results = service.getResults();
		Set<String> keySetResult = results.keySet();
		for(String key : keySetResult) {
			Map<String, String> attributes = results.get(key);
			String price = attributes.get(ServiceData.PRICE);
			double priceD = DataUtils.convertToPriceDouble(price);
			double priceLimitD = DataUtils.convertToPriceDouble(priceLimit);
			if(priceD > priceLimitD) {
				continue;
			}
			ServiceData data = createDataBean(benchMarkType, serviceName, attributes, price);
			dataList.add(data);
		}
	}

	

	private ServiceData createDataBean(String benchMarkType, String serviceName, Map<String, String> attributes, String price) {
		ServiceData data = new ServiceData();
		data.setCostPerYear(price);
		data.setBenchMarkType(benchMarkType);
		data.setServiceName(serviceName);
		data.setInstanceCode(attributes.get(ServiceData.INSTANCE_CODE));
		data.setPerfPerHour(attributes.get(ServiceData.PERFORMANCE_PER_HOUR));
		data.setPerformanceByCost(attributes.get(ServiceData.PERFORMANCE_BY_COST));
		return data;
	}

	private void sortDataList(boolean isHighPerformance, List<ServiceData> dataList) {
		if (dataList != null && !dataList.isEmpty()) {
			if(isHighPerformance){
				Collections.sort(dataList, new PerformanceDescendingComparator());
			}else {
				Collections.sort(dataList,new EfficiencyDescendingComparator());
			}
		}
	}
}

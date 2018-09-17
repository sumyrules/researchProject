package aws.service.pricing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.pricing.AWSPricing;
import com.amazonaws.services.pricing.AWSPricingClientBuilder;
import com.amazonaws.services.pricing.model.AttributeValue;
import com.amazonaws.services.pricing.model.DescribeServicesRequest;
import com.amazonaws.services.pricing.model.DescribeServicesResult;
import com.amazonaws.services.pricing.model.Filter;
import com.amazonaws.services.pricing.model.GetAttributeValuesRequest;
import com.amazonaws.services.pricing.model.GetAttributeValuesResult;
import com.amazonaws.services.pricing.model.GetProductsRequest;
import com.amazonaws.services.pricing.model.GetProductsResult;
import com.amazonaws.services.pricing.model.Service;
import com.amazonaws.util.FileUtils;

public class AwsPricingUtils {

	private static final String DATABASE_ENGINE = "databaseEngine";
	private static final String TERM_MATCH = "TERM_MATCH";
	private static final String INSTANCE_TYPE = "instanceType";
	private static final String LOCATION = "location";

	/**
	 * Fetches Json from the Google Billing Service and persists in the File system
	 * 
	 * @param zone
	 * @param serviceId
	 */
	public void persistAwsServiceJson(Regions region, String serviceCode, String dbEngine, String instanceCode,
			String location) {
		if (serviceCode == null) {
			throw new IllegalArgumentException("Mandatory Field service code is null");
		}
		if (region == null) {
			throw new IllegalArgumentException("Mandatory Field region is null");
		}

		setCredentials();
		AWSPricing awsPricing = AWSPricingClientBuilder.standard().withRegion(region).build();
		Filter instanceFilter = null;
		if (instanceCode != null) {
			instanceFilter = new Filter();
			;
			instanceFilter.withValue(instanceCode);
			instanceFilter.setType(TERM_MATCH);
			instanceFilter.setField(INSTANCE_TYPE);
		}

		Filter locationFilter = null;
		if (location != null) {
			locationFilter = new Filter();
			;
			locationFilter.setType(TERM_MATCH);
			locationFilter.setField(LOCATION);
			locationFilter.withValue(location);
		}

		Filter dbEngineFilter = null;
		if (dbEngine != null) {
			dbEngineFilter = new Filter();
			dbEngineFilter.setType(TERM_MATCH);
			dbEngineFilter.setField(DATABASE_ENGINE);
			dbEngineFilter.withValue(dbEngine);
		}
		Filter deploymentOptionFilter = null;
		deploymentOptionFilter = new Filter();
		;
		deploymentOptionFilter.setType(TERM_MATCH);
		deploymentOptionFilter.setField("deploymentOption");
		deploymentOptionFilter.withValue("Single-AZ");

		GetProductsRequest getProductRequest = new GetProductsRequest().withServiceCode(serviceCode)
				.withFilters(instanceFilter, locationFilter, dbEngineFilter, deploymentOptionFilter);

		GetProductsResult res2 = awsPricing.getProducts(getProductRequest);
		File file = new File("D:\\Study\\Sem 3\\Research Project\\Demo\\CatalogJSON\\RDS-output.json");
		try {
			FileUtils.appendDataToTempFile(file, res2.getPriceList().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String json : res2.getPriceList()) {
			System.out.println(json);
		}
	}

	private static void setCredentials() {
		System.getProperties().put("aws.accessKeyId", "<your_AWS_ACCESS_KEY_ID>");
		System.getProperties().put("aws.secretKey", "<YOU_AWS_SECRET_KEY>");
	}

	private static void getAllAttributeValues(AWSPricing awsPricing, Service serviceResult) {
		Map<String, List<AttributeValue>> attributeMap = new HashMap<>();
		for (String attribute : serviceResult.getAttributeNames()) {
			GetAttributeValuesRequest attributeValuesRequest = new GetAttributeValuesRequest()
					.withServiceCode(serviceResult.getServiceCode()).withAttributeName(attribute);
			GetAttributeValuesResult attributeValuesResult = awsPricing.getAttributeValues(attributeValuesRequest);
			attributeMap.put(attribute, attributeValuesResult.getAttributeValues());
		}
		System.out.println(attributeMap);
	}

	private Service findService(AWSPricing awsPricing, String serviceCode) {
		DescribeServicesResult describeServices = awsPricing.describeServices(new DescribeServicesRequest());
		String serviceString = serviceCode;
		Service desiredService = null;
		for (Service service : describeServices.getServices()) {
			if (service.getServiceCode().toLowerCase().contains(serviceString.toLowerCase())) {
				desiredService = service;
				System.out.println(desiredService);
				return desiredService;
			}

		}
		System.out.println("service Not found with Name " + serviceCode);
		return null;

	}

	public static void main(String[] args) throws IOException {
		AwsPricingUtils pricingUtils = new AwsPricingUtils();
		pricingUtils.persistAwsServiceJson(Regions.US_EAST_1, "AmazonRDS", "MySQL", "db.t2.micro", "EU (Ireland)");
	}

	private static void testCode() throws IOException {
		setCredentials();
		// BasicAWSCredentials awsCreds = new
		// BasicAWSCredentials("",
		// "+ajsgkahdlljdh8hpy+2r");
		GetProductsResult productList = new GetProductsResult();
		productList.setFormatVersion("v1.0");
		productList.getPriceList();
		List<String> priceList = new ArrayList<String>();
		// System.out.println(productList);
		AWSPricing awsPricing = AWSPricingClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		// GetProductsRequest request = new
		// GetProductsRequest().withFormatVersion("aws_v1").withServiceCode("EC2");
		// GetProductsResult result = awsPricing.getProducts(request);
		// System.out.println(result.toString());
		/*
		 * DescribeServicesRequest req = new
		 * DescribeServicesRequest().withServiceCode("AmazonEC2");
		 * DescribeServicesResult res = awsPricing.describeServices(req);
		 */
		// GetAttributeValuesRequest req1 = new
		// GetAttributeValuesRequest().withServiceCode("AmazonRDS");
		Filter filters1 = new Filter();
		;
		filters1.withValue("db.t2.micro");
		filters1.setType(TERM_MATCH);
		filters1.setField(INSTANCE_TYPE);
		Filter filters2 = new Filter();
		;
		filters2.setType(TERM_MATCH);
		filters2.setField(LOCATION);
		filters2.withValue("EU (Ireland)");
		Filter filters3 = new Filter();
		;
		filters3.setType(TERM_MATCH);
		filters3.setField(DATABASE_ENGINE);
		filters3.withValue("MySQL");
		System.out.println(filters1);
		System.out.println(filters2);
		// System.out.println(res1);
		// Service serviceResult = findService(awsPricing,"EC2");//takes too long
		// preferably use exact name
		// getAllAttributeValues(awsPricing, serviceResult);
		GetProductsRequest req2 = new GetProductsRequest().withServiceCode("AmazonRDS").withFilters(filters1, filters2,
				filters3);
		// GetProductsRequest req2 = new
		// GetProductsRequest().withServiceCode("AmazonEC2");
		GetProductsResult res2 = awsPricing.getProducts(req2);
		File file = new File("D:\\Study\\Sem 3\\Research Project\\Demo\\CatalogJSON\\RDS-output.json");
		FileUtils.appendDataToTempFile(file, res2.getPriceList().toString());
		System.out.println(res2.getPriceList());
		// priceList.addAll(res2.getPriceList());
		// System.out.println(res2.getPriceList().get(0));
	}

}
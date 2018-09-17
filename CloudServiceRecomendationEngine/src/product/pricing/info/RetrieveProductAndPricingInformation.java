package product.pricing.info;

import com.amazonaws.regions.Regions;
import com.recommendation.model.PricingFilerBean;

import aws.service.pricing.AwsPricingUtils;
import gcp.billing.GcpBillingInformationUtils;

public class RetrieveProductAndPricingInformation {

 public void persistServicePricing(PricingFilerBean pricingFilerBean) {
	 if (pricingFilerBean.getServiceProvder() != null && pricingFilerBean.getServiceProvder().equalsIgnoreCase("aws")) {
		 AwsPricingUtils awsPricingUtils = new AwsPricingUtils();
		 awsPricingUtils.persistAwsServiceJson(Regions.valueOf(pricingFilerBean.getRegion()), pricingFilerBean.getServiceCode(), pricingFilerBean.getDatabaseEngine(),
				 pricingFilerBean.getInstanceCode(), pricingFilerBean.getLocation());
	 }
	 if (pricingFilerBean.getServiceProvder() != null && pricingFilerBean.getServiceProvder().equalsIgnoreCase("gcp")) {
	 GcpBillingInformationUtils gcpUtils = new GcpBillingInformationUtils();
		gcpUtils.persistComputeEngine(pricingFilerBean.getRegion(), pricingFilerBean.getServiceCode());
	 }
 }
}

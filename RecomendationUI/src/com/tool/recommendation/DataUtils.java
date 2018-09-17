package com.tool.recommendation;

public class DataUtils {

	public static double convertToPriceDouble(String price) {
		return Double.parseDouble(price.trim().replace(",", "."));
	}
}

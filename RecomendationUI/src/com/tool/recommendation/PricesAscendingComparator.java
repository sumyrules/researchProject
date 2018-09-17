package com.tool.recommendation;

import java.util.Comparator;

public class PricesAscendingComparator  implements Comparator<ServiceData>{

	@Override
	public int compare(ServiceData d1, ServiceData d2) {
		Double dd1 = Double.parseDouble(d1.getCostPerYear().replaceAll(",", "."));
		Double dd2 = Double.parseDouble(d2.getCostPerYear().replaceAll(",", "."));
		return dd1.compareTo(dd2);
	}

}

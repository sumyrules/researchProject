package com.tool.recommendation;

import java.util.Comparator;

public class EfficiencyDescendingComparator implements Comparator<ServiceData>{

	@Override
	public int compare(ServiceData d1, ServiceData d2) {
		Double dd1 = Double.valueOf(d1.getPerformanceByCost());
		Double dd2 = Double.valueOf(d2.getPerformanceByCost());
		return dd2.compareTo(dd1);
	}

}

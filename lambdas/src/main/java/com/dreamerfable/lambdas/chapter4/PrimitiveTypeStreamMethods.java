package com.dreamerfable.lambdas.chapter4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dreamerfable.lambdas.domain.Planet;

/**
 * 常用的基本类型流方法联系
 * 
 * @author dreamerfable
 */
public class PrimitiveTypeStreamMethods {

	Planet earth = new Planet("earth", 12756);
	Planet mars = new Planet("mars", 6794);
	Planet saturn = new Planet("saturn", 120540);

	List<Planet> source = Arrays.asList(earth, mars, saturn);

	@Test
	@DisplayName("测试基本流特殊的计算方法")
	void testSum() {
		long expectedSum = earth.getSize() + mars.getSize() + saturn.getSize();
		long expectedMax = saturn.getSize();
		long expectedMin = mars.getSize();
		double expectedAvg = (double) expectedSum / 3;
		long expectedCount = 3;
		LongSummaryStatistics lss = source.stream().mapToLong(planet -> planet.getSize()).summaryStatistics();
		assertEquals(expectedSum, lss.getSum());
		assertEquals(expectedMax, lss.getMax());
		assertEquals(expectedMin, lss.getMin());
		assertEquals(expectedAvg, lss.getAverage());
		assertEquals(expectedCount, lss.getCount());
	}

}

package com.dreamerfable.lambdas.chapter5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dreamerfable.lambdas.domain.Planet;
import com.dreamerfable.lambdas.domain.PlanetFunctionalInterface;

/**
 * 方法引用练习
 * 
 * @author dreamerfable
 */
public class MethodReference {

	Planet earth = new Planet("earth", 12756);
	Planet mars = new Planet("mars", 6794);
	Planet saturn = new Planet("saturn", 120540);

	List<Planet> source = Arrays.asList(earth, mars, saturn);

	@Test
	@DisplayName("测试方法引用")
	void testMethodReference() {
		long expectedSum = earth.getSize() + mars.getSize() + saturn.getSize();
		LongSummaryStatistics lss = source.stream().mapToLong(Planet::getSize).summaryStatistics();
		assertEquals(expectedSum, lss.getSum());
	}

	@Test
	@DisplayName("测试构造方法的方法引用,这感觉太奇怪了")
	void testMethodReferenceOfConstructor() {
		Planet expectedMoon = new Planet("moon", 3476);
		PlanetFunctionalInterface pfi = Planet::new;
		Planet actualMoon = pfi.create("moon", 3476);
		assertEquals(expectedMoon, actualMoon);
	}

	@Test
	@DisplayName("测试数组的方法引用")
	void testMethodReferenceOfArray() {
		List<Integer> lengths = Arrays.asList(1, 2, 3, 4);
		List<String[]> strArrays = lengths.stream().map(String[]::new).collect(Collectors.toList());
		assertEquals(strArrays.get(0).length, 1);
		assertEquals(strArrays.get(1).length, 2);
		assertEquals(strArrays.get(2).length, 3);
		assertEquals(strArrays.get(3).length, 4);
	}

}

package com.dreamerfable.lambdas.chapter5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dreamerfable.lambdas.domain.Planet;

public class BuiltInCollectors {

	Planet earth = new Planet("earth", 12756);
	Planet mars = new Planet("mars", 6794);
	Planet saturn = new Planet("saturn", 120540);
	Planet mercury = new Planet("mercury", 4878);

	List<Planet> source = Arrays.asList(earth, mars, saturn);

	@Test
	@DisplayName("测试转换成指定的其他集合")
	void testChangeToOtherCollection() {
		List<Integer> source = Arrays.asList(1, 2, 3, 4);
		Object obj = source.stream().collect(Collectors.toCollection(TreeSet::new));
		assertTrue(obj instanceof TreeSet);
	}

	@Test
	@DisplayName("测试最大值收集器")
	void testMaxByCollector() {
		Planet expectedMax = saturn;
		Optional<Planet> actualMax = source.stream().collect(Collectors.maxBy(Comparator.comparing(Planet::getSize)));
		assertEquals(expectedMax, actualMax.get());
	}

	@Test
	@DisplayName("测试求平均值收集器")
	void testAvgCollector() {
		double expetedAvg = (double) (earth.getSize() + mars.getSize() + saturn.getSize()) / 3;
		double actualAvg = source.stream().collect(Collectors.averagingLong(Planet::getSize));
		assertEquals(expetedAvg, actualAvg);
	}

	@Test
	@DisplayName("测试partitioningBy方法")
	void testPartitioningBy() {
		List<Planet> expectedBiggerThanEarth = Arrays.asList(saturn);
		List<Planet> expectedNotBiggerThanEarth = Arrays.asList(earth, mars);
		Map<Boolean, List<Planet>> planetsCompareToEarth = source.stream()
				.collect(Collectors.partitioningBy(planet -> planet.getSize() > earth.getSize()));
		assertEquals(expectedBiggerThanEarth, planetsCompareToEarth.get(true));
		assertEquals(expectedNotBiggerThanEarth, planetsCompareToEarth.get(false));
	}

	@Test
	@DisplayName("测试groupingBy方法")
	void testGroupingBy() {
		Map<String, List<Planet>> planets = source.stream()
				.collect(Collectors.groupingBy(planet -> sizeCompareToEarth(planet.getSize())));
		assertEquals(Arrays.asList(mars), planets.get("smaller than earth"));
		assertEquals(Arrays.asList(earth), planets.get("equals with earth"));
		assertEquals(Arrays.asList(saturn), planets.get("bigger than earth"));
	}

	String sizeCompareToEarth(long size) {
		if (size > earth.getSize()) {
			return "bigger than earth";
		} else if (size == earth.getSize()) {
			return "equals with earth";
		} else {
			return "smaller than earth";
		}
	}

	@Test
	@DisplayName("测试joining方法")
	void testJoining() {
		String expected = String.join(",", earth.getName(), mars.getName(), saturn.getName());
		String actual = source.stream().map(Planet::getName).collect(Collectors.joining(","));
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("测试组合收集器，分组计数")
	void testGroupingCounting() {
		List<Planet> source = new ArrayList<>(this.source);
		source.add(mercury);
		Map<String, Long> planets = source.stream().collect(
				Collectors.groupingBy(planet -> sizeCompareToEarth(planet.getSize()), Collectors.counting()));
		assertEquals(Long.valueOf(2), planets.get("smaller than earth"));
		assertEquals(Long.valueOf(1), planets.get("equals with earth"));
		assertEquals(Long.valueOf(1), planets.get("bigger than earth"));
	}

	@Test
	@DisplayName("测试组合收集器，分组操作")
	void TestGroupingList() {
		List<Planet> source = new ArrayList<>(this.source);
		source.add(mercury);
		Map<String, String> planets = source.stream().collect(
				Collectors.groupingBy(planet -> sizeCompareToEarth(planet.getSize()),
						Collectors.mapping(Planet::getName, Collectors.joining(";"))));
		String expectedBigger = saturn.getName();
		String expectedEquals = earth.getName();
		String expectedSmaller = String.join(";", mars.getName(), mercury.getName());
		assertEquals(expectedSmaller, planets.get("smaller than earth"));
		assertEquals(expectedEquals, planets.get("equals with earth"));
		assertEquals(expectedBigger, planets.get("bigger than earth"));
		
	}

	@Test
	@DisplayName("测试computeIfAbsent方法")
	void TestComputeIfAbsent() {
		Map<String, Planet> planets = new HashMap<>();
		planets.put("earth", earth);
		planets.put("saturn", saturn);

		planets.computeIfAbsent("mars", mars -> this.mars);
		assertEquals(mars, planets.get("mars"));

		Map<String, Planet> planetsInvolvedMars = new HashMap<>(planets);
		planetsInvolvedMars.computeIfAbsent("mars", mercury -> this.mercury);
		assertEquals(mars, planets.get("mars"));
	}

}

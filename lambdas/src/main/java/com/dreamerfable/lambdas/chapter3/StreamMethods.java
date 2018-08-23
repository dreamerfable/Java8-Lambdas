package com.dreamerfable.lambdas.chapter3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dreamerfable.lambdas.domain.Planet;

/**
 * 书中提到的常用的Stream方法练习
 * 
 * @author dreamerfable
 */
public class StreamMethods {

	@Test
	@DisplayName("测试collect方法")
	void testCollect() {
		List<String> source = Arrays.asList("1", "2", "3");
		List<String> collected = Stream.of("1", "2", "3").collect(Collectors.toList());
		assertEquals(source, collected);
	}

	@Test
	@DisplayName("测试map方法进行类型转换")
	void testMap1() {
		List<String> source = Arrays.asList("1", "2", "3");
		List<Integer> expected = Arrays.asList(1, 2, 3);
		List<Integer> actual = source.stream().map((str) -> Integer.valueOf(str)).collect(Collectors.toList());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("测试map方法进行同类型运算")
	void testMap2() {
		List<Integer> source = Arrays.asList(1, 2, 3);
		List<Integer> expected = Arrays.asList(1 * 1, 2 * 2, 3 * 3);
		List<Integer> actual = source.stream().map((value) -> value * value).collect(Collectors.toList());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("测试filter方法进行列表过滤")
	void testFilter() {
		List<String> source = Arrays.asList("a1", "b1", "a2", "b2", "c1");
		List<String> expected = Arrays.asList("a1", "a2");
		List<String> actual = source.stream().filter(str -> str.startsWith("a")).collect(Collectors.toList());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("测试flatMap方法进行流合并")
	void testFlatMap() {
		List<Integer> source1 = Arrays.asList(1, 2, 3);
		List<Integer> source2 = Arrays.asList(4, 5);
		List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> actual = Stream.of(source1, source2).flatMap(numbers -> numbers.stream())
				.collect(Collectors.toList());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("测试max和min方法取最大最小值")
	void testMaxAndMin() {
		Planet earth = new Planet("earth", 12756);
		Planet mars = new Planet("mars", 6794);
		Planet saturn = new Planet("saturn", 120540);

		List<Planet> source = Arrays.asList(earth, mars, saturn);
		Planet actualMax = source.stream().max(Comparator.comparing(planet -> planet.getSize())).get();
		assertEquals(saturn, actualMax);
		Planet actualMin = source.stream().min(Comparator.comparing(planet -> planet.getSize())).get();
		assertEquals(mars, actualMin);

	}

	@Test
	@DisplayName("测试使用reduce方法求和")
	void testReduceSum() {
		List<Integer> source = Arrays.asList(1, 2, 3, 4);
		int expected = 1 + 2 + 3 + 4;
		int actual = source.stream().reduce(0, (acc, element) -> acc + element);
		assertEquals(actual, expected);
	}

	@Test
	@DisplayName("测试使用reduce方法求最大值")
	void testReduceMax() {
		List<Integer> source = Arrays.asList(1,2,3,4);
		int expected = 4;
		int actual = source.stream().reduce(0, (acc, element) -> acc > element ? acc : element);
		assertEquals(actual, expected);
	}

}

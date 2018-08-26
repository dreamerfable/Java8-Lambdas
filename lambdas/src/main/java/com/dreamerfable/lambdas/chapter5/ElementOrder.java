package com.dreamerfable.lambdas.chapter5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 元素顺序测试
 * 
 * @author dreamerfable
 */
public class ElementOrder {

	@Test
	@DisplayName("出现顺序测试")
	void appearanceOrder() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
		List<Integer> sameOrder = numbers.stream().collect(Collectors.toList());
		assertEquals(numbers, sameOrder);
	}

}

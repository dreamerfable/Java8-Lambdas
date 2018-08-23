package com.dreamerfable.lambdas.domain;

/**
 * 星球
 * 
 * @author dreamerfable
 */
public class Planet {

	String name;

	long size;

	public Planet(String name, long size) {
		super();
		this.name = name;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "planet [name=" + name + ", size=" + size + "]";
	}

}

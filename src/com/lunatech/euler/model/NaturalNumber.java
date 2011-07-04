package com.lunatech.euler.model;

public class NaturalNumber {
	private final int value;

	public NaturalNumber(final int value) {
		if (value < 1) {
			throw new IllegalArgumentException("Not a natural number: " + value);
		}
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

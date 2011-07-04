package com.lunatech.euler.model;

public class NaturalNumber {
	private final long value;

	public NaturalNumber(final long value) {
		if (value < 1) {
			throw new IllegalArgumentException("Not a natural number: " + value);
		}
		this.value = value;
	}

	public long getValue() {
		return value;
	}
}

package com.lunatech.euler.model;

public class PrimeNumber {

	private final long index;
	private final long value;

	public PrimeNumber(final long index, final long value) {
		if (value < 1) {
			throw new IllegalArgumentException("Not a natural number: " + value);
		}
		this.value = value;
		this.index = index;
	}

	public long getIndex() {
		return index;
	}

	public long getValue() {
		return value;
	}
}

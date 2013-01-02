package com.lunatech.euler.model;

public class PrimeNumber extends BaseNaturalNumber {

	private final long index;

	public PrimeNumber(final long index, final long value) {
        super(value);
		this.index = index;
	}

	public long getIndex() {
		return index;
	}
}

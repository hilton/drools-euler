package com.lunatech.euler.model;

public class SquareNumber extends BaseNaturalNumber {

    private final Long root;

    public SquareNumber(final Long value, final Long root) {
        super(value);
        if (value != root * root) {
            throw new IllegalArgumentException(String.format("%d is not a square number with root %d", value, root));
        }
        this.root = root;
    }

    public Long getRoot() {
        return root;
    }
}

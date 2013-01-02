package com.lunatech.euler.model;

/**
 * Base class for natural number (positive whole number) types. From an OO perspective, it makes sense for SquareNumber to subclass
 * NaturalNumber, because a square number is a kind of natural number. However, in this rules model, we use a fact of this type to
 * mean that a particular number is a square number for easy matching, independently of the NaturalNumber facts. This means that
 * when there are SquareNumber facts, these will also match NaturalNumber patterns (because it's a subclass), and appear to be duplicates.
 *
 * A conceptually-cleaner solution, that would require a little more DRL code, would be for SquareNumber to subclass NaturalNumber,
 * and to retract the corresponding NaturalNumber when inserting a SquareNumber. However, without multiple inheritance, a number
 * could only have one type.
 *
 * An alternative approach is probably to use ‘traits’, introduced in Drools 5.3.
 */
public abstract class BaseNaturalNumber {

    private final long value;

    public BaseNaturalNumber(final long value) {
        if (value < 1) {
            throw new IllegalArgumentException("Not a natural number: " + value);
        }
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}

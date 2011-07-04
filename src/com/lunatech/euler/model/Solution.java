package com.lunatech.euler.model;

public class Solution {

	private final int problem;
	private final long solution;

	public Solution(final int problem, final long solution) {
		this.problem = problem;
		this.solution = solution;
	}

	public int getProblem() {
		return problem;
	}

	public long getSolution() {
		return solution;
	}
}

package com.lunatech.euler.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lunatech.euler.RulesService;

public class ProblemsTest {

	private static RulesService service;

	@BeforeClass
	public static void setUp() {
		service = new RulesService();
		service.solveProblems();
	}

	private void assertSolution(final int problem, final long value) {
		final Long solution = service.getSolution(problem);
		assertNotNull("No solution found", solution);
		assertEquals(value, solution.longValue());
	}

	@Test
	public void problem1() {
		assertSolution(1, 233168L);
	}

	@Test
	public void problem2() {
		assertSolution(2, 4613732L);
	}

	@Test
	public void problem3() {
		assertSolution(3, 6857L);
	}

	@Test
	public void problem4() {
		assertSolution(4, 906609L);
	}
}

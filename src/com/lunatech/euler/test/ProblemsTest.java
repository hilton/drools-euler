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

	@Test
	public void problem5() {
		assertSolution(5, 232792560L);
	}

	@Test
	public void problem6() {
		assertSolution(6, 25164150L);
	}

	@Test
	public void problem7() {
		assertSolution(7, 104743L);
	}

	@Test
	public void problem8() {
		assertSolution(8, 40824L);
	}

	@Test
	public void problem9() {
		assertSolution(9, 31875000L);
	}

//	@Test
//	public void problem10() {
//		assertSolution(10, 142913828922L);
//	}
}

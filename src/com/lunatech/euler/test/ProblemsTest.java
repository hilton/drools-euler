package com.lunatech.euler.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.lunatech.euler.RulesService;

public class ProblemsTest {

	private RulesService service;

	@Before
	public void setUp() {
		service = new RulesService();
	}

	@Test
	public void problem1() {
		service.solveProblems();
		final Long solution = service.getSolution(1);
		assertNotNull("No solution found", solution);
		assertEquals(233168L, solution.longValue());
	}
}

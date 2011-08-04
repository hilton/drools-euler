package com.lunatech.euler;

import com.lunatech.euler.model.NaturalNumber;
import com.lunatech.euler.model.Solution;
import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.ExecutionResults;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Service facade for running the rules engine.
 */
public class RulesService {

	private static final String QUERY_ROW_VALUE = "value";
	private final static String RESULTS_QUERY = "results";
	private final static String PROBLEM_1 = "problem-1.drl";
	private final static String PROBLEM_2 = "problem-2.drl";
	private final static String PROBLEM_3 = "problem-3.drl";
	private final static String PROBLEM_4 = "problem-4.drl";
	private final static String PROBLEM_5 = "problem-5.drl";
	private final static String PROBLEM_6 = "problem-6.drl";
	private final static String PROBLEM_7 = "problem-7.drl";
	private final static String PROBLEM_8 = "problem-8.drl";
	private final static Logger log = Logger.getLogger(RulesService.class);

	private static KnowledgeBase knowledgeBase;

	/** Map of problem solution values, keyed on problem number. */
	private Map<Integer, Long> solutions;

	/**
	 * Utility method to construct and cache the {@link KnowledgeBase} instance.
	 */
	private KnowledgeBase getKnowledgeBase() {
		log.info("getKnowledgeBase");
		if (knowledgeBase == null) {
			log.debug("Load rules");
			final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			try {
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_1, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_2, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_3, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_4, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_5, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_6, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_7, RulesService.class), ResourceType.DRL);
				builder.add(ResourceFactory.newClassPathResource(PROBLEM_8, RulesService.class), ResourceType.DRL);
			}
			catch (final Exception e) {
				log.error("Could not load rules file : " + e.getMessage());
				return null;
			}

			log.debug("Compile rules");
			final Collection<KnowledgePackage> packages = builder.getKnowledgePackages();

			log.debug("Add packages");
			if (builder.hasErrors()) {
				log.error("Rules compilation failed: " + builder.getErrors());
			}
			else {
				final Properties properties = new Properties();
				properties.setProperty("org.drools.sequential", "true");
				final ClassLoader classLoader = this.getClass().getClassLoader();
				final KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration(properties, classLoader);
				knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(kbConf);
				knowledgeBase.addKnowledgePackages(packages);
			}
		}
		return knowledgeBase;
	}

	/**
	 * Convenience interface to the results of the rules session.
	 */
	public Long getSolution(final int problem) {
		return solutions.get(problem);
	}

	/**
	 * Executes the rules session, in order to calculate results.
	 */
	public void solveProblems() {

		// Get the compiled rules.
		final KnowledgeBase knowledgeBase = getKnowledgeBase();

		log.info("Rule session");
		if (knowledgeBase != null) {
			final StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
			session.addEventListener(new EventListener());

			final long start = System.currentTimeMillis();
			final List<Command> commands = new ArrayList<Command>();
			commands.add(CommandFactory.newInsertElements(numbers()));
			commands.add(CommandFactory.newFireAllRules());
			commands.add(CommandFactory.newQuery(RESULTS_QUERY, "Problem solutions"));
			final ExecutionResults executionResults = session.execute(CommandFactory.newBatchExecution(commands));

			solutions = new HashMap<Integer, Long>();
			final QueryResults queryResults = (QueryResults) executionResults.getValue(RESULTS_QUERY);
			final double duration = (System.currentTimeMillis() - start) / 1000;
			log.info(String.format("%d solutions found in %d ms", queryResults.size(), System.currentTimeMillis() - start));
			for (final QueryResultsRow row : queryResults) {
				final Solution solution = (Solution) row.get(QUERY_ROW_VALUE);
				solutions.put(solution.getProblem(), solution.getSolution());
				log.info(String.format("Solution %d = %d", solution.getProblem(), solution.getSolution()));
			}
		}
	}

	/**
	 * Generate a set of number facts for problems to work on.
	 */
	private Set<NaturalNumber> numbers() {
		final Set<NaturalNumber> numbers = new HashSet<NaturalNumber>();
		for (long i = 1; i <= 1000; i++) {
			numbers.add(new NaturalNumber(i));
		}
		return numbers;
	}
}

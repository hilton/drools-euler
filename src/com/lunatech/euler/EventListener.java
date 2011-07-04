package com.lunatech.euler;

import org.apache.log4j.Logger;
import org.drools.definition.rule.Rule;
import org.drools.event.rule.BeforeActivationFiredEvent;
import org.drools.event.rule.DefaultAgendaEventListener;

/**
 * Event listener that logs the name of each rule that is activated, which means that its condition is true.
 */
public class EventListener extends DefaultAgendaEventListener {

	@Override
	public void beforeActivationFired(final BeforeActivationFiredEvent event) {
		final Rule rule = event.getActivation().getRule();
		final Logger log = Logger.getLogger(rule.getPackageName() + "." + rule.getName());
		log.trace("Before activation");
	}
}

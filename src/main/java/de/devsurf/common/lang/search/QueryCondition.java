package de.devsurf.common.lang.search;

import de.devsurf.common.lang.values.Valueable;

public enum QueryCondition implements Valueable<String> {
	LIKE("like"),
	IN("in"),
	EQUAL("="),
	NOT_EQUAL("!="),
	GREATER(">"),
	GREATER_OR_EQUAL(">="),
	LESS("<"),
	LESS_OR_EQUAL("<=");
	
	private String op;

	QueryCondition(String op){
		this.op = op;
	}
	
	@Override
	public String value() {
		return op;
	}
}

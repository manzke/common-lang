package de.devsurf.common.lang.search;

import de.devsurf.common.lang.values.Valueable;

public enum QueryOperator implements Valueable<String> {
	
	AND("AND"),
	OR("OR");
	
	private String op;

	QueryOperator(String op){
		this.op = op;
	}
	
	@Override
	public String value(){
		return op;
	}
}
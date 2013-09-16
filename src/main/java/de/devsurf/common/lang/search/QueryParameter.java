package de.devsurf.common.lang.search;

public class QueryParameter {
	private String key;
	private String value;
	private QueryCondition condition;

	public QueryParameter() {
	}

	public QueryParameter(String key, String value, QueryCondition condition) {
		this.key = key;
		this.value = value;
		this.condition = condition;
	}

	public QueryCondition getCondition() {
		return condition;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setCondition(QueryCondition condition) {
		this.condition = condition;
	}
}

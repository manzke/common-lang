package de.devsurf.common.lang.search;

import java.util.Date;

public enum DescriptorFieldType {
	INTEGER(Integer.class),
	DOUBLE(Double.class),
	STRING(String.class),
	DATE(Date.class),
	BOOLEAN(Boolean.class),
	UNKNOWN(Void.class);

	private Class<?> type;

	private DescriptorFieldType(Class<?> type) {
		this.type = type;
	}

	/**
	 * @return the type of the field
	 */
	public Class<?> type() {
		return type;
	}
}
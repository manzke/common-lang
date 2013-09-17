package de.devsurf.common.lang.formatter;

import java.util.ArrayList;
import java.util.List;

import de.devsurf.common.lang.build.Builder;
import de.devsurf.common.lang.encapsulation.Pair;


/**
 * Utility class for parameter formatting.
 */
public class ParameterMessage implements Builder<String> {

	private static final String NULL = "null";	
	private static final String NAME_VALUE_SEPARATOR = "=";
	private static final String PARAMETER_SEPARATOR = ", ";
	
	private List<Pair<String, Object>> parameters;
	private String parameterSeparator = PARAMETER_SEPARATOR;
	private String nameValueSeparator = NAME_VALUE_SEPARATOR;

	protected ParameterMessage() {
		this.parameters = new ArrayList<>();
	}
	
	public static ParameterMessage format() {
		return new ParameterMessage();
	}
	
	public ParameterMessage addParameter(String name, Object value) {
		this.parameters.add(Pair.paired(name, value));
		return this;
	}
	
	public ParameterMessage setParameterSeparator(String separator) {
		if (separator == null) {
			throw new IllegalArgumentException(ExceptionMessage
					.format("The specified parameter separator is null.").build());
		}
		this.parameterSeparator = separator;
		return this;
	}
	
	public ParameterMessage setNameValueSeparator(String separator) {
		if (separator == null) {
			throw new IllegalArgumentException(ExceptionMessage
					.format("The specified name/value separator is null.").build());
		}
		this.nameValueSeparator = separator;
		return this;
	}
	
	@Override
	public String build() {
		if (parameters == null || parameters.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(parameters.size() * 50);
		for (Pair<String, Object> pair : parameters) {
			sb.append(pair.key);
			sb.append(nameValueSeparator);
			String strValue = NULL;
			if (pair.value != null) {
				strValue = pair.value.toString();
			}
			sb.append(strValue);
			sb.append(parameterSeparator);
		}

		sb.setLength(sb.length() - parameterSeparator.length());

		return sb.toString();
	}
}

package de.devsurf.common.lang.converter;

public interface TwoWayConverter<From, To> extends Converter<From, To> {
	From convertFrom(To source);
}

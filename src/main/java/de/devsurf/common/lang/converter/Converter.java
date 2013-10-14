package de.devsurf.common.lang.converter;




public interface Converter<From, To> {
	To convertTo(From source);
}

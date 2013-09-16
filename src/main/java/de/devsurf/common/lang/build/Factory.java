package de.devsurf.common.lang.build;

public interface Factory<Type, ExceptionType extends Exception> {
	Type build() throws ExceptionType;
}
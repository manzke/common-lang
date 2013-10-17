/*
Copyright 2013 Daniel Manzke (devsurf)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package de.devsurf.common.lang.formatter;

/**
 * <p>
 * The standard formatter for {@link RuntimeException}s.
 * </p>
 * <p>
 * The format-method is overloaded multiple times to accept the exception message and different numbers of parameter
 * informations as name-value pairs.
 * </p>
 * <p>
 * The message and all parameter names must not be {@code null} (but may be empty strings). The parameter values may be
 * {@code null}.
 * </p>
 */
public class ExceptionMessage
    extends ParameterMessage {

    private static final String HEADER = System.getProperty( "line.separator" );

    private static final String MESSAGE_PARAMETER_SEPARATOR = System.getProperty( "line.separator" );

    private static final String TRAILER = System.getProperty( "line.separator" );

    private String message;

    private ExceptionMessage( String message ) {
        super();
        this.message = message;
    }

    public static ExceptionMessage format( String message ) {
        return new ExceptionMessage( message );
    }

    public ExceptionMessage addParameter( String name, Object value ) {
        super.addParameter( name, value );
        return this;
    }

    public String build() {
        String parameters = super.build();
        StringBuilder sb = new StringBuilder( 1000 );

        sb.append( HEADER );
        sb.append( message );
        sb.append( MESSAGE_PARAMETER_SEPARATOR );
        sb.append( parameters );
        sb.append( TRAILER );

        return sb.toString();
    }
}

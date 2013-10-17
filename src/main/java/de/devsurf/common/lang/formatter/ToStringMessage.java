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

public final class ToStringMessage
    extends ParameterMessage {

    private static final char PARAMETER_START = '[';

    private static final char PARAMETER_END = ']';

    private static final String PARAMETER_SEPARATOR = ", ";

    private static final String PARAMETER_NAME_OF_UNKNOWN_SUPER_TO_STRING = "super";

    private String parentMessage;

    private Class<?> clazz;

    private ToStringMessage( Class<?> clazz ) {
        super();
        this.clazz = clazz;
    }

    public static ToStringMessage format( Class<?> clazz ) {
        return new ToStringMessage( clazz );
    }

    public ToStringMessage setParentMessage( String parentToString ) {
        this.parentMessage = parentToString;
        return this;
    }

    public ToStringMessage addParameter( String name, Object value ) {
        super.addParameter( name, value );
        return this;
    }

    public String build() {
        if ( clazz == null ) {
            throw new IllegalArgumentException( ExceptionMessage.format( "The specified class is null." ).build() );
        }

        String className = clazz.getSimpleName();

        String parametersOfSuperToString = null;
        if ( parentMessage != null ) {
            int startIndex = parentMessage.indexOf( PARAMETER_START );
            int endIndex = parentMessage.lastIndexOf( PARAMETER_END );

            if ( ( startIndex != -1 ) && ( endIndex != -1 ) ) {
                startIndex++;
                if ( startIndex < endIndex ) {
                    parametersOfSuperToString = parentMessage.substring( startIndex, endIndex );
                }
            } else {
                parametersOfSuperToString =
                    ParameterMessage.format().addParameter( PARAMETER_NAME_OF_UNKNOWN_SUPER_TO_STRING, parentMessage ).build();
            }
        }

        String parametersOfThis = super.build();

        int sizeGuess =
            className.length()
                + 2
                + ( ( parametersOfSuperToString == null ) ? 0 : parametersOfSuperToString.length()
                    + PARAMETER_SEPARATOR.length() ) + parametersOfThis.length();

        StringBuilder sb = new StringBuilder( sizeGuess );

        sb.append( className );
        sb.append( PARAMETER_START );
        if ( parametersOfSuperToString != null ) {
            sb.append( parametersOfSuperToString );
            if ( !parametersOfThis.isEmpty() ) {
                sb.append( PARAMETER_SEPARATOR );
            }
        }
        sb.append( parametersOfThis );
        sb.append( PARAMETER_END );

        return sb.toString();
    }
}

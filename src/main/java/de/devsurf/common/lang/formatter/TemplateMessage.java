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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TODO - add optional filling of missing parameter <br>
 * TODO - add optional failing on missing parameter <br>
 * TODO - add optional failing on brackets errors
 */
public class TemplateMessage {
    public static final String LEFT_DELIMITER = "${";

    public static final String RIGHT_DELIMITER = "}";

    private Map<String, String> replacements;

    private Map<Integer, String> parameters = new LinkedHashMap<>();

    public static String format( String message, Map<String, String> parameters ) {
        TemplateMessage formatter = new TemplateMessage();
        formatter.replacements = parameters;
        return formatter.format( message );
    }

    private TemplateMessage() {};

    public String format( String message ) {
        StringBuilder result = new StringBuilder( message.length() );
        String pattern = analyze( message );
        int lastOffset = 0;

        for ( Entry<Integer, String> entry : parameters.entrySet() ) {
            int index = entry.getKey();
            result.append( pattern.substring( lastOffset, index ) );
            lastOffset = index;
            String key = entry.getValue();
            if ( replacements.containsKey( key ) ) {
                String value = replacements.get( key );
                if ( value != null ) {
                    result.append( value );
                    continue;
                }
            }

            result.append( LEFT_DELIMITER ).append( RIGHT_DELIMITER );
        }

        result.append( pattern.substring( lastOffset, pattern.length() ) );

        return result.toString();
    }

    public String analyze( String message )
        throws IllegalArgumentException {
        StringBuilder outputBuilder = new StringBuilder( message.length() );
        int index = 0;
        for ( int leftIndex = 0; leftIndex > -1; ) {
            leftIndex = message.indexOf( LEFT_DELIMITER, index );
            int rightIndex;
            if ( leftIndex >= 0 ) {
                rightIndex = message.indexOf( RIGHT_DELIMITER, leftIndex + LEFT_DELIMITER.length() );
                // sanity check
                int newLextIndex = message.indexOf( LEFT_DELIMITER, leftIndex + LEFT_DELIMITER.length() );
                if ( rightIndex < 0 ) {
                    throw new IllegalArgumentException( String.format( "Right Bracket is missing near position %s",
                                                                       leftIndex ) );
                }
                if ( rightIndex > newLextIndex && newLextIndex > -1 ) {
                    throw new IllegalArgumentException( String.format( "Right Bracket is missing near position %s",
                                                                       leftIndex ) );
                }

                outputBuilder.append( message.substring( index, leftIndex ) );
                parameters.put( outputBuilder.length(),
                                message.substring( leftIndex + LEFT_DELIMITER.length(), rightIndex ) );
                index = rightIndex + RIGHT_DELIMITER.length();
            }
        }

        outputBuilder.append( message.substring( index ) );

        return outputBuilder.toString();
    }

    public static void main( String[] args ) {
        System.out.println( TemplateMessage.format( "send an email from ${sender} to ${recipient}",
                                                    Collections.singletonMap( "sender", "d@d.de" ) ) );
        try {
            System.out.println( TemplateMessage.format( "send an email from ${sender to ${recipient}",
                                                        Collections.singletonMap( "sender", "d@d.de" ) ) );
        }
        catch ( Throwable e ) {
            e.printStackTrace();
        }
        try {
            System.out.println( TemplateMessage.format( "send an email from ${sender} to ${recipient",
                                                        Collections.singletonMap( "sender", "d@d.de" ) ) );
        }
        catch ( Throwable e ) {
            e.printStackTrace();
        }
        System.out.println( TemplateMessage.format( "send an email from ${sender} to ${recipient}",
                                                    Collections.singletonMap( "bla", "d@d.de" ) ) );
        System.out.println( TemplateMessage.format( "A class to format message which contain named parameters like \"send an email from ${sender} to ${recipient}\".",
                                                    Collections.singletonMap( "sender", "d@d.de" ) ) );

    }
}

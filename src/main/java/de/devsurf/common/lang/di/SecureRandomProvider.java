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
package de.devsurf.common.lang.di;

import java.security.NoSuchAlgorithmException;

import javax.inject.Provider;
import javax.xml.bind.DatatypeConverter;

import de.devsurf.common.lang.formatter.ExceptionMessage;

public class SecureRandomProvider
    implements Provider<String> {
    private final static java.security.SecureRandom INSTANCE;

    static {
        try {
            INSTANCE = java.security.SecureRandom.getInstance( "SHA1PRNG" );
        }
        catch ( NoSuchAlgorithmException e ) {
            throw new IllegalStateException(
                                             ExceptionMessage.format( "Algorithm doesn't exists." ).addParameter( "algo",
                                                                                                                  "SHA1PRNG" ).build() );
        }
    }

    @Override
    public String get() {
        byte bytes[] = new byte[20];
        INSTANCE.nextBytes( bytes );
        // TODO should we switch to google guava
        return DatatypeConverter.printBase64Binary( bytes );
    }
}

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
package de.devsurf.common.lang.secret;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public final class SecretGenerator {

    public static KeyPair generateKeyPair( String algorithm, int keysize )
        throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance( algorithm );
        kpg.initialize( keysize );
        return kpg.generateKeyPair();
    }

    public static SecretKey generateKey( String algorithm, int keysize )
        throws NoSuchAlgorithmException {
        KeyGenerator gen = KeyGenerator.getInstance( algorithm );
        gen.init( keysize );
        return gen.generateKey();
    }

}
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

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Ciphers {
    public final static String AES_ENCRYPT_ALGORITHM = "AES";

    public static Cipher aes256( byte[] key, boolean encrypt )
        throws InvalidKeyException {
        try {
            SecretKeySpec spec = new SecretKeySpec( key, AES_ENCRYPT_ALGORITHM );
            Cipher cipher = Cipher.getInstance( AES_ENCRYPT_ALGORITHM );
            cipher.init( ( encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE ), spec );
            return cipher;
        }
        catch ( InvalidKeyException e ) {
            throw e;
        }
        catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
    }

    public static Cipher cipher( String algorithm, Key key, boolean encrypt ) {
        try {
            Cipher cipher = Cipher.getInstance( algorithm );
            cipher.init( ( encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE ), key );
            return cipher;
        }
        catch ( Exception e ) {
            throw new IllegalStateException( e );
        }
    }
}

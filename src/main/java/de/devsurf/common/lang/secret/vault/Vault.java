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
package de.devsurf.common.lang.secret.vault;

import java.security.SignatureException;

import de.devsurf.common.lang.secret.vault.exceptions.LoadException;
import de.devsurf.common.lang.secret.vault.exceptions.NotExistingException;
import de.devsurf.common.lang.secret.vault.exceptions.StoreException;
import de.devsurf.common.lang.secret.vault.exceptions.VaultException;

public interface Vault {
    <ValueType, GenericExceptionType extends Throwable, WriterType extends VaultWriter<ValueType, GenericExceptionType>> void store( String key,
                                                                                                                                     ValueType value,
                                                                                                                                     WriterType writer )
        throws StoreException, VaultException, GenericExceptionType;

    <ValueType, GenericExceptionType extends Throwable, LoaderType extends VaultReader<ValueType, GenericExceptionType>> ValueType load( String key,
                                                                                                                                         LoaderType loader )
        throws GenericExceptionType, LoadException, NotExistingException, VaultException;

    boolean contains( String key )
        throws VaultException;

    void delete( String key )
        throws NotExistingException, VaultException;

    String sign( String toSign )
        throws SignatureException, VaultException;

    boolean verify( String signed, String hash )
        throws SignatureException, VaultException;

    String encrypt( String toEncrypt )
        throws VaultException;

    String decrypt( String toDecrypt )
        throws VaultException;
}

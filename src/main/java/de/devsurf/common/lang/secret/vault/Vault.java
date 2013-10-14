package de.devsurf.common.lang.secret.vault;

import java.security.SignatureException;

import de.devsurf.common.lang.secret.vault.exceptions.LoadException;
import de.devsurf.common.lang.secret.vault.exceptions.NotExistingException;
import de.devsurf.common.lang.secret.vault.exceptions.StoreException;
import de.devsurf.common.lang.secret.vault.exceptions.VaultException;

public interface Vault {
	<ValueType, GenericExceptionType extends Throwable, WriterType extends VaultWriter<ValueType, GenericExceptionType>> void store(
			String key, ValueType value, WriterType writer)
			throws StoreException, VaultException, GenericExceptionType;

	<ValueType, GenericExceptionType extends Throwable, LoaderType extends VaultReader<ValueType, GenericExceptionType>> ValueType load(
			String key, LoaderType loader) throws GenericExceptionType,
			LoadException, NotExistingException, VaultException;

	boolean contains(String key) throws VaultException;

	void delete(String key) throws NotExistingException, VaultException;

	String sign(String toSign) throws SignatureException, VaultException;

	boolean verify(String signed, String hash) throws SignatureException,
			VaultException;

	String encrypt(String toEncrypt) throws VaultException;

	String decrypt(String toDecrypt) throws VaultException;
}

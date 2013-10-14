package de.devsurf.common.lang.secret.vault;

import javax.crypto.Cipher;


public interface VaultWriter<ValueType, GenericException extends Throwable>{
	String write(ValueType value) throws GenericException;
	void init(Cipher cipher);
}
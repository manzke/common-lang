package de.devsurf.common.lang.secret.vault;

import javax.crypto.Cipher;


public interface VaultReader<Type, GenericException extends Throwable>{
	Type read(String in) throws GenericException;
	void init(Cipher cipher);
}
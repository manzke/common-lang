package de.devsurf.common.lang.encapsulation;

public final class Pair<KeyType, ValueType> {
	public final KeyType key;
	public final ValueType value;
	
	private Pair(KeyType name, ValueType value) {
		this.key = name;
		this.value = value;
	}

	public static <KeyType, ValueType> Pair<KeyType, ValueType> paired(KeyType key, ValueType value) {
		return new Pair<>(key, value);
	}
}
package de.devsurf.common.lang;


public final class Void {
	public static final Void INSTANCE = new Void();
	
	private Void() {}
	
	public static final Void create(){
		return INSTANCE;
	}
}

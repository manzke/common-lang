package de.devsurf.common.lang.search;

import java.util.Collection;

public interface TypedResult<Type> {

	public Collection<Type> list();
	
	public boolean isEmpty();

	public int count();

	public Type first();
}

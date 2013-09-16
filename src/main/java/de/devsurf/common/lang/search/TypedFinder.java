package de.devsurf.common.lang.search;

import java.util.Map;

public interface TypedFinder<Type> {
	TypedFinder<Type> with(Map<DescriptorField, Object> keyValues) throws SearchException;
	TypedFinder<Type> with(DescriptorField field, Object value) throws SearchException;
	TypedFinder<Type> combinedBy(QueryOperator op);
	TypedResult<Type> result() throws SearchException;
	
	
}

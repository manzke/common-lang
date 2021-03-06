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
package de.devsurf.common.lang.search;

import de.devsurf.common.lang.values.Valueable;

public enum QueryCondition
    implements Valueable<String> {
    LIKE( "like" ),
    IN( "in" ),
    EQUAL( "=" ),
    NOT_EQUAL( "!=" ),
    GREATER( ">" ),
    GREATER_OR_EQUAL( ">=" ),
    LESS( "<" ),
    LESS_OR_EQUAL( "<=" );

    private String op;

    QueryCondition( String op ) {
        this.op = op;
    }

    @Override
    public String value() {
        return op;
    }
}

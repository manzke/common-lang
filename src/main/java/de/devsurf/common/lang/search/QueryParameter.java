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

public class QueryParameter {
    private String key;

    private String value;

    private QueryCondition condition;

    public QueryParameter() {}

    public QueryParameter( String key, String value, QueryCondition condition ) {
        this.key = key;
        this.value = value;
        this.condition = condition;
    }

    public QueryCondition getCondition() {
        return condition;
    }

    public String getKey() {
        return key;
    }

    public void setKey( String key ) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    public void setCondition( QueryCondition condition ) {
        this.condition = condition;
    }
}

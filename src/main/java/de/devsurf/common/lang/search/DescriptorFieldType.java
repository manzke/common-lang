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

import java.util.Date;

public enum DescriptorFieldType {
    INTEGER( Integer.class ), DOUBLE( Double.class ), STRING( String.class ), DATE( Date.class ), BOOLEAN(
        Boolean.class ), UNKNOWN( Void.class );

    private Class<?> type;

    private DescriptorFieldType( Class<?> type ) {
        this.type = type;
    }

    /**
     * @return the type of the field
     */
    public Class<?> type() {
        return type;
    }
}
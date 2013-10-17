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

import java.io.Serializable;

import de.devsurf.common.lang.formatter.ToStringMessage;

public interface DescriptorField
    extends Serializable {
    String name();

    DescriptorFieldType type();

    public static class DefaultDescriptorField
        implements DescriptorField, Serializable {
        private static final long serialVersionUID = 242505283964621680L;

        private final String name;

        private final DescriptorFieldType type;

        protected DefaultDescriptorField( String name, DescriptorFieldType type ) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public DescriptorFieldType type() {
            return type;
        }

        @Override
        public String toString() {
            return ToStringMessage.format( getClass() ).addParameter( "Name", name ).addParameter( "Type", type ).build();
        }
    }
}

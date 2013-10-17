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
package de.devsurf.common.lang.secret.vault.exceptions;

public class NotExistingException
    extends VaultException {

    private static final long serialVersionUID = 3160439247856402985L;

    public NotExistingException() {
        super();
    }

    public NotExistingException( String message, Throwable cause ) {
        super( message, cause );
    }

    public NotExistingException( String message ) {
        super( message );
    }

    public NotExistingException( Throwable cause ) {
        super( cause );
    }

}

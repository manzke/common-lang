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
package de.devsurf.common.lang;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import de.devsurf.common.lang.formatter.ExceptionMessage;

/**
 * Utility class for thread treatment.
 */
public final class Threads {

    private Threads() {}

    /**
     * Creates a new {@link ThreadFactory} which only creates daemon {@link Threads}. So the JVM will shutdown nether
     * the less if there are submitted jobs.
     * 
     * @return new {@link ThreadFactory} for creating daemon {@link Threads}
     */
    public static ThreadFactory newDaemonThreadFactory() {
        return new DaemonThreadFactory( "threads" );
    }

    /**
     * Creates a new named {@link ThreadFactory} which only creates daemon {@link Threads}. So the JVM will shutdown
     * nether the less if there are submitted jobs.
     * 
     * @param named name of the {@link ThreadFactory}
     * @return new {@link ThreadFactory} for creating daemon {@link Threads}
     */
    public static ThreadFactory newDaemonThreadFactory( String named ) {
        if ( named != null && named.length() > 0 ) {
            return new DaemonThreadFactory( named );
        }
        throw new IllegalStateException(
                                         ExceptionMessage.format( "ThreadFactory needs a name." ).addParameter( "named",
                                                                                                                named ).build() );
    }

    private static final class DaemonThreadFactory
        implements ThreadFactory {
        private static final AtomicInteger GLOBAL_FACTORY_NUMBER = new AtomicInteger( 1 );

        private final ThreadGroup group;

        private final AtomicInteger threadNumber = new AtomicInteger( 1 );

        private final String namePrefix;

        DaemonThreadFactory( String name ) {
            SecurityManager s = System.getSecurityManager();
            this.group = ( s != null ) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = name + "-" + GLOBAL_FACTORY_NUMBER.getAndIncrement() + "-thread-";
        }

        public Thread newThread( Runnable r ) {
            Thread t = new Thread( this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0 );
            if ( !t.isDaemon() ) {
                t.setDaemon( true );
            }
            return t;
        }
    }
}

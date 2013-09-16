package de.devsurf.common.lang;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import de.devsurf.common.lang.formatter.ExceptionMessage;

/**
 * Utility class for thread treatment.
 */
public final class Threads {

	private Threads() {
	}

	/**
	 * Creates a new {@link ThreadFactory} which only creates daemon
	 * {@link Threads}. So the JVM will shutdown nether the less if there are
	 * submitted jobs.
	 * 
	 * @return new {@link ThreadFactory} for creating daemon {@link Threads}
	 */
	public static ThreadFactory newDaemonThreadFactory() {
		return new DaemonThreadFactory("threads");
	}

	public static ThreadFactory newDaemonThreadFactory(String named) {
		if (named != null && named.length() > 0) {
			return new DaemonThreadFactory(named);
		}
		throw new IllegalStateException(ExceptionMessage
				.format("ThreadFactory needs a name.")
				.addParameter("named", named).build());
	}

	private static final class DaemonThreadFactory implements ThreadFactory {
		static final AtomicInteger factoryNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		DaemonThreadFactory(String name) {
			SecurityManager s = System.getSecurityManager();
			this.group = (s != null) ? s.getThreadGroup() : Thread
					.currentThread().getThreadGroup();
			this.namePrefix = name + "-" + factoryNumber.getAndIncrement()
					+ "-thread-";
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(this.group, r, this.namePrefix
					+ this.threadNumber.getAndIncrement(), 0);
			if (!t.isDaemon()) {
				t.setDaemon(true);
			}
//			if (t.getPriority() != Thread.MIN_PRIORITY) {
//				t.setPriority(Thread.MIN_PRIORITY);
//			}
			return t;
		}
	}
}

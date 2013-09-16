package de.devsurf.common.lang.search;

import java.io.Serializable;

import de.devsurf.common.lang.formatter.ToStringMessage;

public interface DescriptorField extends Serializable {
	String name();

	DescriptorFieldType type();

	public static class DefaultDescriptorField implements DescriptorField,
			Serializable {
		private static final long serialVersionUID = 242505283964621680L;
		private final String name;
		private final DescriptorFieldType type;

		protected DefaultDescriptorField(String name, DescriptorFieldType type) {
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
			return ToStringMessage.format(getClass())
					.addParameter("Name", name).addParameter("Type", type)
					.build();
		}
	}
}

package com.legendshop.event;

public abstract class SystemEvent<T> {
	private final BaseEventId baseEventId;
	protected T source;

	public SystemEvent(T paramT, BaseEventId eventId) {
		if (paramT == null) {
			throw new IllegalArgumentException("null source");
		}
		this.source = paramT;
		if (eventId == null) {
			throw new IllegalArgumentException("null eventId");
		}
		this.baseEventId = eventId;
	}

	public SystemEvent(BaseEventId paramBaseEventId) {
		if (paramBaseEventId == null) {
			throw new IllegalArgumentException("null eventId");
		}
		this.baseEventId = paramBaseEventId;
	}

	public BaseEventId getEventId() {
		return this.baseEventId;
	}

	public T getSource() {
		return this.source;
	}

	public void setSource(T paramT) {
		if (paramT == null)
			throw new IllegalArgumentException("null source");
		this.source = paramT;
	}
}
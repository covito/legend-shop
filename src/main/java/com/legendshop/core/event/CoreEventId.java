package com.legendshop.core.event;

import com.legendshop.event.BaseEventId;

public enum CoreEventId implements BaseEventId {
	LICENSE_STATUS_CHECK_EVENT, LICENSE_UPGRADE_CHECK_EVENT, FUNCTION_CHECK_EVENT, SYS_INIT_EVENT, SYS_DESTORY_EVENT, FIRE_EVENT;

	private final String eventId=name().substring(0, name().length()-6);

	public String getEventId() {
		return this.eventId;
	}

	public boolean instance(String param) {
		return false;
	}
}
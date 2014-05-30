package com.legendshop.spi.event;

import com.legendshop.event.BaseEventId;

public enum EventId implements BaseEventId {
	USER_REG_EVENT, SEND_MAIL_EVENT, SEND_SMS_EVENT, LOGIN_EVENT, PROD_CHANGE_EVENT, VISIT_LOG_EVENT, CAN_ADD_SHOPDETAIL_EVENT, ORDER_SAVE_EVENT, ORDER_UPDATE_EVENT, SYS_PARAM_EVENT, SHOPDETAIL_DELETE, SHOPDETAIL_SAVE, SHOPDETAIL_UPDATE, PRODUCT_DELETE, PRODUCT_SAVE, PRODUCT_UPDATE;

	private final String value = name();

	public String getEventId() {
		return this.value;
	}

	public boolean instance(String name) {
		EventId[] arrayOfEventId1;
		EventId[] eventIds = values();
		int j = (arrayOfEventId1 = eventIds).length;
		for (int i = 0; i < j; ++i) {
			EventId eventId = arrayOfEventId1[i];
			if (eventId.name().equals(name))
				return true;
		}

		return false;
	}
}
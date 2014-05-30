package com.legendshop.event;

import java.util.Comparator;

class EventListenerComparator implements Comparator<BaseEventListener> {
	
	public int compare(BaseEventListener paramBaseEventListener1,
			BaseEventListener paramBaseEventListener2) {
		if ((paramBaseEventListener1 == null)
				|| (paramBaseEventListener2 == null)
				|| (paramBaseEventListener1.getOrder() == null)
				|| (paramBaseEventListener2.getOrder() == null))
			return -1;
		if (paramBaseEventListener1.getOrder().intValue() < paramBaseEventListener2
				.getOrder().intValue())
			return -1;
		if (paramBaseEventListener1.getOrder() == paramBaseEventListener2
				.getOrder())
			return 0;
		return 1;
	}
}
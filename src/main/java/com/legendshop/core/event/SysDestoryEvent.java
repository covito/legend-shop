package com.legendshop.core.event;

import com.legendshop.event.SystemEvent;
import javax.servlet.ServletContextEvent;

public class SysDestoryEvent extends SystemEvent<ServletContextEvent> {
	public SysDestoryEvent(ServletContextEvent paramServletContextEvent) {
		super(paramServletContextEvent, CoreEventId.SYS_DESTORY_EVENT);
	}
}
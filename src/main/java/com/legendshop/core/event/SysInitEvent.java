package com.legendshop.core.event;

import com.legendshop.event.SystemEvent;
import javax.servlet.ServletContextEvent;

public class SysInitEvent extends SystemEvent<ServletContextEvent> {
	public SysInitEvent(ServletContextEvent paramServletContextEvent) {
		super(paramServletContextEvent, CoreEventId.SYS_INIT_EVENT);
	}
}
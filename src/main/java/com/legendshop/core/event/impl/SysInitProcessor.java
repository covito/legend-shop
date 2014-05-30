package com.legendshop.core.event.impl;

import com.legendshop.core.StartupService;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.BaseProcessor;
import javax.servlet.ServletContextEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysInitProcessor extends BaseProcessor<ServletContextEvent> {
	
	private static Logger _$2 = LoggerFactory.getLogger(SysInitProcessor.class);
	private StartupService _$1;

	public void process(ServletContextEvent paramServletContextEvent) {
		if (PropertiesUtil.isSystemInstalled()) {
			_$2.info("*********startup service *************");
			this._$1.startup(paramServletContextEvent.getServletContext());
		}
	}

	public void setStartupService(StartupService paramStartupService) {
		this._$1 = paramStartupService;
	}
}
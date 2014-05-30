package com.legendshop.business;

import com.legendshop.core.tag.TableCache;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.spi.service.SystemParameterService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class SysInitProcessor extends BaseProcessor<ServletContextEvent> {
	private SystemParameterService systemParameterService;
	private TableCache codeTablesCache;

	public void process(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		this.systemParameterService.initSystemParameter();

		this.codeTablesCache.initCodeTablesCache();

		servletContext.setAttribute("LEGENDSHOP_DOMAIN_NAME",
				"http://www.legendesign.net");
	}

	public void setSystemParameterService(
			SystemParameterService systemParameterService) {
		this.systemParameterService = systemParameterService;
	}

	public void setCodeTablesCache(TableCache codeTablesCache) {
		this.codeTablesCache = codeTablesCache;
	}
}
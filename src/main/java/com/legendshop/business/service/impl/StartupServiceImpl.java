package com.legendshop.business.service.impl;

import com.legendshop.core.StartupService;
import com.legendshop.core.helper.SpringBeanManager;
import com.legendshop.page.TemplatePage;
import com.legendshop.page.TemplatePageManager;
import com.legendshop.util.AppUtils;
import com.legendshop.util.handler.PluginRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class StartupServiceImpl implements StartupService {
	private TemplatePageManager templatePageManager;
	private SpringBeanManager springBeanManager;
	private Boolean isInited = Boolean.valueOf(false);

	public void startup(ServletContext servletContext) {
		synchronized (this.isInited) {
			if (!(this.isInited.booleanValue())) {
				PluginRepository.getInstance().startPlugins(servletContext);

				Map templatePages = ContextLoader
						.getCurrentWebApplicationContext().getBeansOfType(
								TemplatePage.class);

				if (AppUtils.isNotBlank(templatePages)) {
					List templatePageList = createTemplatePageList(templatePages
							.values());
					Collections.sort(templatePageList);
					for (Iterator localIterator = templatePageList.iterator(); localIterator
							.hasNext();) {
						TemplatePage tp = (TemplatePage) localIterator.next();
						this.templatePageManager.registerTemplatePage(tp);
					}

					this.springBeanManager.removeBean(servletContext,
							templatePages.keySet());
				}

				this.isInited = Boolean.valueOf(true);
			}
		}
	}

	private List<TemplatePage> createTemplatePageList(
			Collection<TemplatePage> templatePages) {
		if (templatePages == null)
			return null;

		List templatePageList = new ArrayList();
		for (Iterator localIterator = templatePages.iterator(); localIterator
				.hasNext();) {
			TemplatePage templatePage = (TemplatePage) localIterator.next();
			templatePageList.add(templatePage);
		}
		return templatePageList;
	}

	public void destory(ServletContext servletContext) {
		synchronized (this.isInited) {
			if (this.isInited.booleanValue()){
				PluginRepository.getInstance().stopPlugins(servletContext);
			}
			return;

			
		}
	}

	public void setTemplatePageManager(TemplatePageManager templatePageManager) {
		this.templatePageManager = templatePageManager;
	}

	public void setSpringBeanManager(SpringBeanManager springBeanManager) {
		this.springBeanManager = springBeanManager;
	}
}
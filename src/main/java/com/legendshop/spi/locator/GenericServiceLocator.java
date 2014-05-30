package com.legendshop.spi.locator;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.helper.ThreadLocalContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenericServiceLocator<T> implements ServiceLocator<T> {
	protected Map<String, T> serviceMap;

	public T getConcreteService(HttpServletRequest request,
			HttpServletResponse response, PageDefinition page) {
		String template = ThreadLocalContext.getFrontType(request, response,
				page.getNativeValue(), page);
		T service = this.serviceMap.get(template);
		if (service == null)
			service = this.serviceMap.get("default");

		return service;
	}

	public void setServiceMap(Map<String, T> serviceMap) {
		this.serviceMap = serviceMap;
	}
}
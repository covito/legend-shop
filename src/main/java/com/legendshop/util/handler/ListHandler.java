package com.legendshop.util.handler;

import java.util.List;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

class ListHandler extends AbstractSingleBeanDefinitionParser {
	protected Class<ListFactoryBean> getBeanClass(Element paramElement) {
		return ListFactoryBean.class;
	}

	protected void doParse(Element paramElement,
			ParserContext paramParserContext,
			BeanDefinitionBuilder paramBeanDefinitionBuilder) {
		String str1 = paramElement.getAttribute("list-class");
		List localList = paramParserContext.getDelegate()
				.parseListElement(paramElement,
						paramBeanDefinitionBuilder.getRawBeanDefinition());
		String str2 = paramElement.getAttribute("id");
		if (paramParserContext.getRegistry().isBeanNameInUse(str2)) {
			PropertyValue localObject = paramParserContext.getRegistry()
					.getBeanDefinition(str2).getPropertyValues()
					.getPropertyValue("sourceList");
			ManagedList localManagedList = (ManagedList) ((PropertyValue) localObject)
					.getValue();
			localList.addAll(localManagedList);
		}
		paramBeanDefinitionBuilder.addPropertyValue("sourceList", localList);
		if (StringUtils.hasText(str1))
			paramBeanDefinitionBuilder
					.addPropertyValue("targetListClass", str1);
		Object localObject = paramElement.getAttribute("scope");
		if (StringUtils.hasLength((String) localObject))
			paramBeanDefinitionBuilder.setScope((String) localObject);
	}
}
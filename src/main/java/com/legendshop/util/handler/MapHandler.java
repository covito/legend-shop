package com.legendshop.util.handler;

import java.util.Map;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

class MapHandler extends AbstractSingleBeanDefinitionParser {
	public static final String DEFAULT_KEY = "default-key";
	public static final String MAP_CLASS_NAME = "com.legendshop.util.handler.DefaultKeyMap";

	protected Class<DefaultMapFactoryBean> getBeanClass(Element paramElement) {
		return DefaultMapFactoryBean.class;
	}

	protected void doParse(Element paramElement,
			ParserContext paramParserContext,
			BeanDefinitionBuilder paramBeanDefinitionBuilder) {
		Map localMap = paramParserContext.getDelegate()
				.parseMapElement(paramElement,
						paramBeanDefinitionBuilder.getRawBeanDefinition());
		String id = paramElement.getAttribute("id");
		String defaultkey = paramElement.getAttribute("default-key");
		String str2 = null;
		if (paramParserContext.getRegistry().isBeanNameInUse(id)) {
			PropertyValue sourceMap = paramParserContext.getRegistry()
					.getBeanDefinition(id).getPropertyValues()
					.getPropertyValue("sourceMap");
			PropertyValue dkey = paramParserContext.getRegistry()
					.getBeanDefinition(id).getPropertyValues()
					.getPropertyValue("defaultKey");
			ManagedMap localManagedMap = (ManagedMap) ((PropertyValue) sourceMap)
					.getValue();
			str2 = (String) dkey.getValue();
			localMap.putAll(localManagedMap);
		}
		paramBeanDefinitionBuilder.addPropertyValue("sourceMap", localMap);
		paramBeanDefinitionBuilder.addPropertyValue("targetMapClass",
				"com.legendshop.util.handler.DefaultKeyMap");
		if (StringUtils.hasText((String) defaultkey)) {
			if ((!(StringUtils.hasText(str2))) || (str2.equals(defaultkey)))
				paramBeanDefinitionBuilder.addPropertyValue("defaultKey",
						defaultkey);
			// throw new
			// RuntimeException("Default key must be unique in one map!");
		}
		if (StringUtils.hasText(str2))
			defaultkey = str2;
		Object localObject2 = paramElement.getAttribute("scope");
		if (StringUtils.hasLength((String) localObject2))
			paramBeanDefinitionBuilder.setScope((String) localObject2);
	}
}
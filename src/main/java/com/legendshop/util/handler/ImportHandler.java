package com.legendshop.util.handler;

import com.legendshop.util.AppUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;

class ImportHandler implements BeanDefinitionParser {
	public static final String RESOURCE_ATTRIBUTE = "resource";
	public static final String MATCHER_CLASS = "matcher-class";
	public static final String TYPE_ATTR = "type";
	public static final String VALUE_ATTR = "value";

	public BeanDefinition parse(Element paramElement,
			ParserContext paramParserContext) {
		String str1 = paramElement.getAttribute("resource");
		String str2 = paramElement.getAttribute("type");
		String str3 = paramElement.getAttribute("value");
		String str4 = paramElement.getAttribute("matcher-class");
		AbstractPluginMatcher pluginMatcher = null;
		try {
			if (AppUtils.isNotBlank(str4)){
				pluginMatcher = (AbstractPluginMatcher) ClassUtils.forName(str4).newInstance();
			}else{
				pluginMatcher = new ImportMatcher();
			}
			pluginMatcher.setResource(str1);
			pluginMatcher.setType(str2);
			pluginMatcher.setValue(str3);
		} catch (Exception localException) {
			throw new IllegalStateException("Unable to load class [" + str4
					+ "]", localException);
		}
		if (!pluginMatcher.isMatch()){
			return null;
		}
		String str5 = pluginMatcher.getParsedResource();
		NamespaceHandler.parse( paramElement, paramParserContext, str5);
		return  null;
	}
}
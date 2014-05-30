package com.legendshop.util.handler;

import com.legendshop.plugins.Plugin;
import com.legendshop.plugins.PluginConfig;
import com.legendshop.plugins.PluginStatusEnum;
import org.slf4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;

class PluginHandler implements BeanDefinitionParser {
	public static final String PULGINID_ATTR = "pulginId";
	public static final String PULGINVERSION_ATTR = "pulginVersion";
	public static final String STATUS_ATTR = "status";
	public static final String REQUIRED_ATTR = "required";
	public static final String SPRING_CONFIGURATION_ATTR = "springConfiguration";
	public static final String I18N_ATTR = "i18n";
	public static final String CLASS_ATTR = "class";
	public static final String DESCRIPTION_ATTR = "description";
	public static final String PROVIDER = "provider";

	public BeanDefinition parse(Element paramElement,
			ParserContext paramParserContext) {
		String str1 = paramElement.getAttribute("pulginId");
		String str2 = paramElement.getAttribute("pulginVersion");
		String str3 = paramElement.getAttribute("status");
		Boolean localBoolean = Boolean.valueOf(false);
		if (paramElement.getAttribute("required") != null)
			try {
				localBoolean = Boolean.valueOf(paramElement
						.getAttribute("required"));
			} catch (Exception localException1) {
			}
		String str4 = paramElement.getAttribute("springConfiguration");
		String str5 = paramElement.getAttribute("i18n");
		String str6 = paramElement.getAttribute("class");
		String str7 = paramElement.getAttribute("description");
		String str8 = paramElement.getAttribute("provider");
		PluginConfig localPluginConfig = new PluginConfig();
		localPluginConfig.setDescription(str7);
		localPluginConfig.setPulginId(str1);
		localPluginConfig.setPulginVersion(str2);
		localPluginConfig.setProvider(str8);
		localPluginConfig.setRequired(localBoolean.booleanValue());
		localPluginConfig.setSpringConfiguration(str4);
		try {
			PluginStatusEnum localPluginStatusEnum = PluginStatusEnum
					.valueOf(str3);
			localPluginConfig.setStatus(localPluginStatusEnum);
		} catch (Exception localException2) {
			localPluginConfig.setStatus(PluginStatusEnum.N);
		}
		localPluginConfig.setI18n(str5);
		Plugin localPlugin = null;
		PluginImportServiceMatcher localPluginImportServiceMatcher = new PluginImportServiceMatcher();
		try {
			localPlugin = (Plugin) ClassUtils.forName(str6).newInstance();
			localPlugin.setPluginConfig(localPluginConfig);
		} catch (Exception localException3) {
//			new NamespaceHandler()._$1(this._$1).error(
//					"can not instantiate class {}", str6);
		}
		localPluginImportServiceMatcher.setPlugin(localPlugin);
		localPluginImportServiceMatcher.setValue(str1);
		if (!(localPluginImportServiceMatcher.isMatch()))
			return null;
		if (PluginStatusEnum.Y.equals(localPluginConfig.getStatus()))
			new NamespaceHandler().parse(paramElement, paramParserContext,
					localPluginConfig.getSpringConfiguration());
		return null;
	}

	private void _$1(Element paramElement, ParserContext paramParserContext,
			String paramString, PluginConfig paramPluginConfig) {
		String str = paramString + "_" + paramPluginConfig.getPulginId();
		if (!(paramParserContext.getRegistry().containsBeanDefinition(str))) {
			RootBeanDefinition localRootBeanDefinition = new RootBeanDefinition();
			localRootBeanDefinition.setBeanClassName(paramString);
			_$1(paramElement, localRootBeanDefinition, paramPluginConfig);
			paramParserContext
					.registerBeanComponent(new BeanComponentDefinition(
							localRootBeanDefinition, str));
		}
	}

	private void _$1(Element paramElement, BeanDefinition paramBeanDefinition,
			PluginConfig paramPluginConfig) {
		paramBeanDefinition.getPropertyValues().add("pluginConfig",
				paramPluginConfig);
	}
}
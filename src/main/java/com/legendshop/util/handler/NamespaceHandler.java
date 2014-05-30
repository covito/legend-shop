package com.legendshop.util.handler;

import java.io.IOException;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class NamespaceHandler extends NamespaceHandlerSupport {
	private static final Logger logger = LoggerFactory.getLogger(NamespaceHandler.class);
	private static final String scope = "scope";

	public void init() {
		registerBeanDefinitionParser("import", new ImportHandler());
		registerBeanDefinitionParser("plugin", new PluginHandler());
		registerBeanDefinitionParser("map", new MapHandler());
		registerBeanDefinitionParser("list", new ListHandler());
	}

	public static void parse(Element paramElement, ParserContext paramParserContext,
			String paramString) {
		int i;
		XmlReaderContext localXmlReaderContext = paramParserContext
				.getReaderContext();
		if (!(StringUtils.hasText(paramString)))
			return;
		if (ResourcePatternUtils.isUrl(paramString))
			try {
				LinkedHashSet localLinkedHashSet = new LinkedHashSet(4);
				i = localXmlReaderContext.getReader().loadBeanDefinitions(
						paramString, localLinkedHashSet);
				if (logger.isDebugEnabled())
					logger.debug("Imported " + i
							+ " bean definitions from URL location ["
							+ paramString + "]");
				Resource[] arrayOfResource = (Resource[]) localLinkedHashSet
						.toArray(new Resource[localLinkedHashSet.size()]);
				localXmlReaderContext.fireImportProcessed(paramString,
						arrayOfResource,
						localXmlReaderContext.extractSource(paramElement));
			} catch (BeanDefinitionStoreException localBeanDefinitionStoreException1) {
				localXmlReaderContext.error(
						"Failed to import bean definitions from URL location ["
								+ paramString + "]", paramElement,
						localBeanDefinitionStoreException1);
			}
		else
			try {
				Resource localResource = localXmlReaderContext.getResource()
						.createRelative(paramString);
				i = localXmlReaderContext.getReader().loadBeanDefinitions(
						localResource);
				if (logger.isDebugEnabled())
					logger.debug("Imported " + i
							+ " bean definitions from relative location ["
							+ paramString + "]");
				localXmlReaderContext.fireImportProcessed(paramString,
						new Resource[] { localResource },
						localXmlReaderContext.extractSource(paramElement));
			} catch (IOException localIOException) {
				localXmlReaderContext.error(
						"Invalid relative resource location [" + paramString
								+ "] to import bean definitions from",
						paramElement, localIOException);
			} catch (BeanDefinitionStoreException localBeanDefinitionStoreException2) {
				localXmlReaderContext.error(
						"Failed to import bean definitions from relative location ["
								+ paramString + "]", paramElement,
						localBeanDefinitionStoreException2);
			}
	}
}
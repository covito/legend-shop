package com.legendshop.util.handler;

import com.legendshop.util.AppUtils;
import com.legendshop.util.EnvironmentConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImportMatcher extends AbstractPluginMatcher {
	protected static final Log logger = LogFactory.getLog(ImportMatcher.class);
	public static final String PLACEHOLDER_PREFIX = "${";
	public static final String PLACEHOLDER_SUFFIX = "}";

	public String getParsedResource() {
		return resolvePath(this.resource);
	}

	protected String resolvePath(String paramString) {
		StringBuilder localStringBuilder = new StringBuilder(paramString);
		int i = paramString.indexOf("${");
		while (i != -1) {
			int j = localStringBuilder.toString().indexOf("}",
					i + "${".length());
			if (j != -1) {
				String str1 = localStringBuilder
						.substring(i + "${".length(), j);
				String str2 = System.getProperty(str1);
				if (str2 == null) {
					str2 = EnvironmentConfig.getInstance().getPropertyValue(
							"config/global.properties", str1);
					if (AppUtils.isBlank(str2))
						str2 = EnvironmentConfig.getInstance()
								.getPropertyValue("config/common.properties",
										str1);
				}
				if (str2 != null) {
					localStringBuilder.replace(i, j + "}".length(), str2);
					i = localStringBuilder.toString().indexOf("${",
							i + str2.length());
				} else {
					logger.warn("Could not resolve placeholder '" + str1
							+ "' in resource path [" + paramString
							+ "] as system property");
					i = localStringBuilder.toString().indexOf("${",
							j + "}".length());
				}
			} else {
				i = -1;
			}
		}
		return localStringBuilder.toString();
	}

	public boolean isMatch() {
		return true;
	}
}
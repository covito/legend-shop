package com.legendshop.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class EnvironmentConfig {
	private static Logger _$4 = Logger.getLogger(EnvironmentConfig.class);
	static EnvironmentConfig _$3;
	private static Map<String, Properties> _$2 = new HashMap();
	private String[] _$1 = { "" };

	public static EnvironmentConfig getInstance() {
		if (_$3 == null)
			_$3 = new EnvironmentConfig();
		return _$3;
	}

	private Properties _$2(String paramString) {
		Properties localProperties = (Properties) _$2.get(paramString);
		if (localProperties == null)
			try {
				Object localObject = null;
				try {
					localObject = new FileInputStream(paramString);
				} catch (Exception localException2) {
					if (paramString.startsWith("/"))
						localObject = EnvironmentConfig.class
								.getResourceAsStream(paramString);
					else
						localObject = EnvironmentConfig.class
								.getResourceAsStream("/" + paramString);
				}
				localProperties = new Properties();
				localProperties.load((InputStream) localObject);
				_$2.put(paramString, localProperties);
				((InputStream) localObject).close();
			} catch (Exception localException1) {
				_$4.error("getProperties: ", localException1);
			}
		return ((Properties) localProperties);
	}

	public String getPropertyValue(String paramString1, String paramString2) {
		Properties localProperties = _$2(paramString1);
		try {
			return localProperties.getProperty(paramString2);
		} catch (Exception localException) {
			localException.printStackTrace(System.out);
		}
		return null;
	}

	private void _$1(String paramString1, String paramString2,
			String paramString3) {
		if (paramString3 == null)
			paramString3 = "";
		Properties localProperties = _$2(paramString1);
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					paramString1);
			localProperties.setProperty(paramString2, paramString3);
			localProperties.store(localFileOutputStream, "set");
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
	}

	public synchronized void writeProperties(String paramString,
			Map<String, String> paramMap) {
		Properties localProperties = _$2(paramString);
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					paramString);
			Iterator localIterator = paramMap.keySet().iterator();
			while (localIterator.hasNext()) {
				String str1 = (String) localIterator.next();
				String str2 = (String) paramMap.get(str1);
				if (str2 == null)
					str2 = "";
				localProperties.setProperty(str1, str2);
			}
			localProperties.store(localFileOutputStream, "set");
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		_$1(paramString);
	}

	private void _$1(String paramString) {
		_$4.info("reload configuration file " + paramString);
		Properties localProperties = null;
		try {
			Object localObject = null;
			try {
				localObject = new FileInputStream(paramString);
			} catch (Exception localException2) {
				_$4.warn("initProperties, error message: "
						+ localException2.getLocalizedMessage());
				if (paramString.startsWith("/"))
					localObject = EnvironmentConfig.class
							.getResourceAsStream(paramString);
				else
					localObject = EnvironmentConfig.class
							.getResourceAsStream("/" + paramString);
			}
			localProperties = new Properties();
			localProperties.load((InputStream) localObject);
			_$2.put(paramString, localProperties);
			((InputStream) localObject).close();
		} catch (Exception localException1) {
			_$4.error("initProperties: ", localException1);
		}
	}
}
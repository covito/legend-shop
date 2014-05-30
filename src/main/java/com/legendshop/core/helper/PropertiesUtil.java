package com.legendshop.core.helper;

import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.constant.SysParameterTypeEnum;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.util.AppUtils;
import com.legendshop.util.EnvironmentConfig;
import com.legendshop.util.SystemUtil;
import com.legendshop.util.TimerUtil;
import com.legendshop.util.converter.ByteConverter;
import com.legendshop.util.des.DES2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertiesUtil extends SystemUtil {
	
	private static Map<String, Object> configMap = new HashMap<String, Object>();

	public static String getSmallFilesAbsolutePath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.SMALL_PIC_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "smallImage";
		return str;
	}

	public static String getBigFilesAbsolutePath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.BIG_PIC_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "bigImage";
		return str;
	}

	public static String getBackupFilesAbsolutePath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.BACKUP_PIC_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "backupImage";
		return str;
	}

	public static String getHtmlPath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.HTML_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "html/";
		return str;
	}

	public static String getSmallImagePathPrefix() {
		return EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties", "IMAGES_PATH_PREFIX");
	}

	public static String getPhotoPathPrefix() {
		return EnvironmentConfig.getInstance().getPropertyValue(
				"fckeditor.properties", "connector.userFilesPath");
	}

	public static String getDomainName() {
		return EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties", "DOMAIN_NAME");
	}

	public static String getDownloadFilePath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.DOWNLOAD_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "WEB-INF/download";
		return str;
	}

	public static String getLucenePath() {
		String str = EnvironmentConfig.getInstance().getPropertyValue(
				"config/common.properties",
				ConfigPropertiesEnum.LUCENE_PATH.name());
		if (AppUtils.isBlank(str))
			str = getSystemRealPath() + "WEB-INF/luceneIndex";
		return str;
	}

	public static String getProperties(String paramString1, String paramString2) {
		return EnvironmentConfig.getInstance().getPropertyValue(paramString1,
				paramString2);
	}

	public static String getGlobalProperties(String paramString) {
		return EnvironmentConfig.getInstance().getPropertyValue(
				"config/global.properties", paramString);
	}

	public static boolean isSystemInstalled() {
		return "INSTALLED".equals(getProperties("config/common.properties",
				ConfigPropertiesEnum.INSTALLED.name()));
	}

	public static boolean isSystemInDebugMode() {
		return "true".equals(getProperties("config/global.properties",
				ConfigPropertiesEnum.SQL_DEBUG_MODE.name()));
	}

	public static String getCurrencyPattern() {
		return EnvironmentConfig.getInstance().getPropertyValue(
				"config/global.properties",
				ConfigPropertiesEnum.CURRENCY_PATTERN.name());
	}

	public static void writeProperties(String paramString,
			Map<String, String> paramMap) {
		EnvironmentConfig.getInstance().writeProperties(paramString, paramMap);
	}

	public static <T> T getObject(SysParameterEnum paramSysParameterEnum,
			Class<T> paramClass) {
		return (T)configMap.get(paramSysParameterEnum.name());
	}

	public static Boolean getBooleanObject(String paramString) {
		return ((Boolean) configMap.get(paramString));
	}

	public static void setObject(SysParameterEnum paramSysParameterEnum,
			Object paramObject) {
		if (paramObject.getClass() != paramSysParameterEnum.getClazz())
			throw new RuntimeException("Required Type = "
					+ paramSysParameterEnum.getClazz() + ", but Actual Type = "
					+ paramObject.getClass());
		configMap.put(paramSysParameterEnum.name(), paramObject);
	}

	public static void setParameter(SystemParameter parameter) {
		String str1 = parameter.getType();
		if (SysParameterTypeEnum.Integer.name().equalsIgnoreCase(str1)) {
			try {
				configMap.put(parameter.getName(),
						Integer.valueOf(parameter.getValue()));
			} catch (Exception localException1) {
				configMap.put(parameter.getName(),
						Integer.valueOf(parameter.getOptional()));
			}
		} else if (SysParameterTypeEnum.Boolean.name().equalsIgnoreCase(str1)) {
			configMap.put(parameter.getName(),
					Boolean.valueOf(parameter.getValue()));
		} else if (SysParameterTypeEnum.Long.name().equalsIgnoreCase(str1)) {
			try {
				configMap.put(parameter.getName(),
						Long.valueOf(parameter.getValue()));
			} catch (Exception localException2) {
				configMap.put(parameter.getName(),
						Integer.valueOf(parameter.getOptional()));
			}
		} else if (SysParameterTypeEnum.List.name().equalsIgnoreCase(str1)) {
			ArrayList localArrayList = new ArrayList();
			if (AppUtils.isNotBlank(parameter.getValue())) {
				String[] arrayOfString1 = parameter.getValue()
						.split(",");
				String[] arrayOfString2 = arrayOfString1;
				int i = arrayOfString2.length;
				for (int j = 0; j < i; ++j) {
					String str2 = arrayOfString2[j];
					localArrayList.add(str2);
				}
			}
			configMap.put(parameter.getName(), localArrayList);
		} else {
			configMap.put(parameter.getName(),
					parameter.getValue());
		}
	}

	public static String getDefaultShopName() {
		return ((String) getObject(SysParameterEnum.DEFAULT_SHOP, String.class));
	}

	public static boolean isDefaultShopName(String paramString) {
		return ((String) getObject(SysParameterEnum.DEFAULT_SHOP, String.class))
				.equals(paramString);
	}

	public static boolean isInDefaultShop(String paramString) {
		return ((String) getObject(SysParameterEnum.DEFAULT_SHOP, String.class))
				.equals(paramString);
	}

	public static String getLegendShopSystemId() {
		return getProperties("config/common.properties",
				ConfigPropertiesEnum.LEGENDSHOP_SYSTEM_ID.name());
	}

	public static void changeLegendShopSystemId() {
		DES2 localDES2 = new DES2();
		HashMap localHashMap = new HashMap();
		String str1 = TimerUtil.getStrDate();
		String str2 = ByteConverter.encode(localDES2.byteToString(localDES2
				.createEncryptor(str1)));
		localHashMap
				.put(ConfigPropertiesEnum.LEGENDSHOP_SYSTEM_ID.name(), str2);
		String str3 = getSystemRealPath()
				+ "WEB-INF/classes/config/common.properties";
		writeProperties(str3, localHashMap);
	}

	public static boolean sendMail() {
		return ((Boolean) getObject(SysParameterEnum.SEND_MAIL, Boolean.class))
				.booleanValue();
	}
}
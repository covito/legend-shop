package com.legendshop.central.license;

import com.legendshop.core.constant.ConfigPropertiesEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import com.legendshop.util.TimerUtil;
import com.legendshop.util.converter.ByteConverter;
import com.legendshop.util.des.DES2;
import com.legendshop.util.ip.LocalAddress;
import com.legendshop.util.ip.LocalAddressUtil;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;

public class LicenseHelper {
	private static HttpClientLicenseHelper _$2 = new HttpClientLicenseHelper();
	private static String _$1 = PropertiesUtil.getSystemRealPath()
			+ "WEB-INF/license.out";

	public static String getLicense(ServletContext paramServletContext) {
		LSResponse localLSResponse = (LSResponse) paramServletContext
				.getAttribute("LEGENSHOP_LICENSE");
		if (LicenseEnum.UN_AUTH.name().equals(localLSResponse.getLicense()))
			return LicenseEnum.getValue(localLSResponse.getLicense())
					+ LicenseWarnMessage.EXPIRED_WARNING.value();
		return null;
	}

	public static LSResponse checkLicense(ServletContext paramServletContext)
			throws Exception {
		String str = _$1(paramServletContext, "checkLicense", null);
		LSResponse localLSResponse = null;
		if (AppUtils.isNotBlank(str)) {
			localLSResponse = (LSResponse) JSONUtil.getObject(str,
					LSResponse.class);
			persistResopnse(localLSResponse);
			_$1(paramServletContext, localLSResponse);
		} else {
			_$1(paramServletContext, getPersistedResopnse());
		}
		return localLSResponse;
	}

	private static void _$1(ServletContext paramServletContext,
			LSResponse paramLSResponse) {
		if (paramLSResponse != null) {
			paramServletContext.setAttribute("LEGENSHOP_LICENSE",
					paramLSResponse);
			if (LicenseEnum.instance(paramLSResponse.getLicense()))
				paramServletContext.setAttribute("licenseDesc", LicenseEnum
						.valueOf(paramLSResponse.getLicense()).value());
		}
	}

	public static void persistResopnse(LSResponse paramLSResponse)
			throws Exception {
		HashMap localHashMap = new HashMap();
		localHashMap.put("resopnse", paramLSResponse);
		localHashMap.put("validate",
				AppUtils.getCRC32(paramLSResponse.toString()));
		FileOutputStream localFileOutputStream = null;
		ObjectOutputStream localObjectOutputStream = null;
		try {
			localFileOutputStream = new FileOutputStream(_$1);
			localObjectOutputStream = new ObjectOutputStream(
					localFileOutputStream);
			localObjectOutputStream.writeObject(localHashMap);
		} catch (Exception localException) {
		} finally {
			if (localObjectOutputStream != null)
				localObjectOutputStream.flush();
			if (localFileOutputStream != null)
				localFileOutputStream.close();
		}
	}

	public static LSResponse getPersistedResopnse() throws Exception {
		FileInputStream localFileInputStream = null;
		ObjectInputStream localObjectInputStream = null;
		try {
			localFileInputStream = new FileInputStream(_$1);
			localObjectInputStream = new ObjectInputStream(localFileInputStream);
			Map localMap = (Map) localObjectInputStream.readObject();
			Long localLong1 = (Long) localMap.get("validate");
			LSResponse localLSResponse1 = (LSResponse) localMap.get("resopnse");
			Long localLong2 = AppUtils.getCRC32(localLSResponse1.toString());
			if ((localLong1 != null) && (localLong1.equals(localLong2))) {
				LSResponse localLSResponse2 = localLSResponse1;
				return localLSResponse2;
			}
		} catch (Exception localException) {
		} finally {
			if (localObjectInputStream != null)
				localObjectInputStream.close();
			if (localFileInputStream != null)
				localFileInputStream.close();
		}
		return null;
	}

	public static LSResponse updateLicense(ServletContext paramServletContext,
			String paramString) {
		LSResponse localLSResponse = null;
		try {
			String str = _$1(paramServletContext, "updateLicense", paramString);
			if (AppUtils.isNotBlank(str)) {
				localLSResponse = (LSResponse) JSONUtil.getObject(str,
						LSResponse.class);
				_$1(paramServletContext, localLSResponse);
			}
		} catch (Exception localException) {
			return null;
		}
		return localLSResponse;
	}

	private static String _$1(ServletContext paramServletContext,
			String paramString1, String paramString2) {
		String str1;
		try {
			str1 = PropertiesUtil.getLegendShopSystemId();
			if (str1 == null) {
				DES2 localDES2 = new DES2();
				str1 = ByteConverter.encode(localDES2.byteToString(localDES2
						.createEncryptor(TimerUtil.getStrDate())));
			}
			DES2 localDES2 = new DES2();
			String str2 = new String(localDES2.createDecryptor(localDES2
					.stringToByte(ByteConverter.decode(str1))));
			LocalAddress localLocalAddress = LocalAddressUtil.getLocalAddress();
			LSRequest localLSRequest = new LSRequest();
			localLSRequest.setAction(paramString1);
			localLSRequest.setBusinessMode((String) paramServletContext
					.getAttribute("BUSINESS_MODE"));
			localLSRequest.setDomainName((String) paramServletContext
					.getAttribute("DOMAIN_NAME"));
			localLSRequest.setLanguage((String) paramServletContext
					.getAttribute("LANGUAGE_MODE"));
			localLSRequest.setHostname(localLocalAddress.getHostName());
			localLSRequest.setIp(localLocalAddress.getIp());
			localLSRequest.setDate(str2);
			localLSRequest.setVersion((String) paramServletContext
					.getAttribute(ConfigPropertiesEnum.LEGENDSHOP_VERSION
							.name()));
			if (paramString2 != null)
				localLSRequest.setLicenseKey(paramString2);
			String str3 = JSONUtil.getJson(localLSRequest);
			return _$2.postMethod(str3);
		} catch (Exception localException) {
		}
		return null;
	}
}
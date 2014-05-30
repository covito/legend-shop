package com.legendshop.util;

import java.io.File;
import java.io.FileInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DownloadFileUtil {
	private static DownloadFileUtil _$2;
	private static Logger _$1 = Logger.getLogger(DownloadFileUtil.class
			.getName());

	public static DownloadFileUtil getInstance() {
		if (_$2 == null)
			_$2 = new DownloadFileUtil();
		return _$2;
	}

	public static String toUtf8String(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramString.length(); ++i) {
			char c = paramString.charAt(i);
			if ((c >= 0) && (c <= 255)) {
				localStringBuffer.append(c);
			} else {
				byte[] arrayOfByte;
				try {
					arrayOfByte = String.valueOf(c).getBytes("utf-8");
				} catch (Exception localException) {
					localException.printStackTrace();
					arrayOfByte = new byte[0];
				}
				for (int j = 0; j < arrayOfByte.length; ++j) {
					int k = arrayOfByte[j];
					if (k < 0)
						k += 256;
					localStringBuffer.append("%"
							+ Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return localStringBuffer.toString();
	}

	public static String getEncodingFileName(String paramString) {
		_$1.debug("EncodingFileName: " + paramString);
		int i = paramString.lastIndexOf(".");
		String str1 = paramString;
		String str2 = "";
		int j = 56;
		if (i > -1) {
			str1 = paramString.substring(0, i);
			str2 = paramString.substring(i);
			j = j - str2.length() - 4;
		}
		String str3 = "";
		try {
			int k = str1.getBytes("GBK").length;
			if (k > j) {
				k = j;
				str3 = "....";
			}
			String str4 = new String(str1.getBytes("GBK"), 0, k, "GBK");
			if ("".equals(str4))
				str4 = new String(str1.getBytes("GBK"), 0, k + 1, "GBK");
			_$1.debug("Encode File Name: " + str4 + str3 + str2);
			return str4 + str3 + str2;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;
	}

	public void downloadFile(HttpServletResponse paramHttpServletResponse,
			String paramString1, String paramString2, boolean paramBoolean) {
		File localFile;
		try {
			Object localObject;
			localFile = new File(paramString1);
			if ((!(localFile.exists())) || (!(localFile.isFile()))) {
				_$1.debug("File: " + paramString1 + " Not Exists");
				paramHttpServletResponse.setHeader("Content-Type",
						"text/html; charset=GBK");
				localObject = paramHttpServletResponse.getOutputStream();
				((ServletOutputStream) localObject).println("文件不存在！联系管理员！");
			} else {
				if (paramBoolean) {
					paramHttpServletResponse.setHeader("Content-Type",
							"application/octet-stream; charset=GBK");
					paramHttpServletResponse.setHeader("Content-Disposition",
							"attachment; filename="
									+ toUtf8String(paramString2));
				} else {
					localObject = "application/pdf; charset=GBK";
					if ((paramString2 != null)
							&& (paramString2.endsWith(".doc"))) {
						localObject = "application/msword; charset=GBK";
						paramHttpServletResponse.setHeader("Content-Type",
								(String) localObject);
					} else if ((paramString2 != null)
							&& (paramString2.endsWith(".pdf"))) {
						localObject = "application/pdf; charset=GBK";
						paramHttpServletResponse.setHeader("Content-Type",
								(String) localObject);
					} else {
						localObject = "application/force-download";
						paramHttpServletResponse.setHeader("Content-Type",
								(String) localObject);
					}
					paramHttpServletResponse.setHeader("Content-Disposition",
							"filename=" + toUtf8String(paramString2));
				}
				localObject = new FileInputStream(paramString1);
				byte[] arrayOfByte = new byte[8192];
				ServletOutputStream localServletOutputStream = paramHttpServletResponse
						.getOutputStream();
				int i;
				while ((i = ((FileInputStream) localObject).read(arrayOfByte)) != -1) {
					localServletOutputStream.write(arrayOfByte, 0, i);
				}
				localServletOutputStream.flush();
				((FileInputStream) localObject).close();
				localServletOutputStream.close();
				_$1.debug("Download File: " + paramString1 + " Finished");
			}
		} catch (Exception localException) {
			_$1.error("DownloadFile: " + paramString1 + " Error",
					localException);
		}
	}
}
package com.legendshop.core.helper;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.InvalidFormatException;
import com.legendshop.util.AppUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileProcessor {
	private static Logger logger = LoggerFactory.getLogger(FileProcessor.class);

	public static void main(String[] paramArrayOfString) {
	}

	public static int deleteFile(String paramString) {
		return deleteFile(paramString, true);
	}

	public static int deleteFile(String paramString, boolean paramBoolean) {
		File localFile1;
		try {
			localFile1 = new File(paramString);
			if (localFile1.exists()) {
				if (paramBoolean) {
					String str1 = PropertiesUtil.getBackupFilesAbsolutePath();
					if (AppUtils.isNotBlank(str1)) {
						String str2 = PropertiesUtil.getBigFilesAbsolutePath();
						if ((AppUtils.isNotBlank(str2))
								&& (paramString.indexOf(str2) > -1)) {
							String str3 = paramString;
							str3 = str3.replace(str2, str1);
							File localFile2 = new File(str3);
							FileUtils.copyFile(localFile1, localFile2);
						}
					}
				}
				boolean bool = localFile1.delete();
				if (bool)
					return 0;
				return -1;
			}
			logger.warn("没有该文件：{}", paramString);
			return 1;
		} catch (Exception localException) {
			logger.warn("删除文件 {} 失败", paramString);
		}
		return 2;
	}

	public static void deleteDirectory(File paramFile) {
		if (!(paramFile.exists()))
			return;
		if (paramFile.isFile()) {
			paramFile.delete();
			return;
		}
		File[] arrayOfFile = paramFile.listFiles();
		for (int i = 0; i < arrayOfFile.length; ++i)
			deleteDirectory(arrayOfFile[i]);
		paramFile.delete();
	}

	public static String uploadFile(InputStream paramInputStream,
			String paramString1, String paramString2, String paramString3,
			boolean paramBoolean1, boolean paramBoolean2) {
		File localFile1;
		FileOutputStream localFileOutputStream = null;
		try {
			String str1 = getFileExtName(paramString3);
			if (paramBoolean1)
				paramString3 = paramString2 + System.currentTimeMillis()
						+ randomNumeric(new Random(), 6) + str1;
			localFile1 = new File(paramString1);
			if (!(localFile1.exists()))
				localFile1.mkdirs();
			File localFile2 = new File(paramString1 + "/" + paramString3);
			if ((localFile2.exists()) && (paramBoolean1)) {
				paramString3 = System.currentTimeMillis() + "_" + paramString3;
				localFile2 = new File(paramString1 + "/" + paramString3);
			} else if ((localFile2.exists()) && (!(paramBoolean2))) {
				String str2 = paramString3;
				return str2;
			}
			localFileOutputStream = new FileOutputStream(localFile2);
			int i = 0;
			byte[] arrayOfByte = new byte[8192];
			while ((i = paramInputStream.read(arrayOfByte)) > 0)
				localFileOutputStream.write(arrayOfByte, 0, i);
		} catch (Exception localException3) {
			return null;
		} finally {
			try {
				localFileOutputStream.close();
			} catch (Exception localException8) {
			}
			try {
				paramInputStream.close();
			} catch (Exception localException9) {
			}
		}
		return paramString3;
	}

	public static String randomNumeric(Random paramRandom, int paramInt) {
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < paramInt; ++i) {
			int j = paramRandom.nextInt(10);
			localStringBuilder.append(String.valueOf(j));
		}
		return localStringBuilder.toString();
	}

	public static boolean copyFile(File paramFile1, File paramFile2) {
		FileInputStream localFileInputStream;
		try {
			localFileInputStream = new FileInputStream(paramFile1);
			FileOutputStream localFileOutputStream = new FileOutputStream(
					paramFile2);
			int i = 0;
			byte[] arrayOfByte = new byte[8192];
			while ((i = localFileInputStream.read(arrayOfByte)) > 0)
				localFileOutputStream.write(arrayOfByte, 0, i);
			localFileOutputStream.close();
			localFileInputStream.close();
		} catch (Exception localException) {
			return false;
		}
		return true;
	}

	public static String writeFile(String paramString1, String paramString2,
			String paramString3) throws IOException {
		return writeFile(paramString1, paramString2, paramString3, false, true,
				false);
	}

	public static String writeFile(String paramString1, String paramString2,
			String paramString3, boolean paramBoolean1, boolean paramBoolean2,
			boolean paramBoolean3) throws IOException {
		File localFile1 = null;
		File localFile2 = null;
		FileWriter localFileWriter = null;
		OutputStreamWriter localOutputStreamWriter = null;
		try {
			Object localObject1;
			localFile1 = new File(paramString2);
			if (!(localFile1.exists()))
				localFile1.mkdirs();
			localFile2 = new File(paramString2 + "/" + paramString3);
			if ((localFile2.exists()) && (paramBoolean1)) {
				paramString3 = System.currentTimeMillis() + "_" + paramString3;
				localFile2 = new File(paramString2 + "/" + paramString3);
			} else if ((localFile2.exists()) && (!(paramBoolean2))) {
				return paramString3;
			}
			localFileWriter = new FileWriter(localFile2);
			localOutputStreamWriter = new OutputStreamWriter(
					new FileOutputStream(localFile2), "UTF-8");
			if (paramBoolean3) {
				localObject1 = new StringBuffer();
				((StringBuffer) localObject1).append(" <P> ");
				for (int i = 0; i < paramString1.length(); ++i)
					if (new String(new char[] { paramString1.charAt(i) })
							.equals(" "))
						((StringBuffer) localObject1).append(" &nbsp;&nbsp; ");
					else if (new String(new char[] { paramString1.charAt(i) })
							.equals("\n"))
						((StringBuffer) localObject1).append(" <br> ");
					else if (new String(new char[] { paramString1.charAt(i) })
							.equals("\n"))
						if ((new String(
								new char[] { paramString1.charAt(i + 1) })
								.equals("\n"))
								&& (i + 1 != paramString1.length())) {
							((StringBuffer) localObject1)
									.append(" </P><br><P> ");
							++i;
						} else
							((StringBuffer) localObject1).append(new String(
									new char[] { paramString1.charAt(i) }));
				((StringBuffer) localObject1).append("</P>");
				paramString1 = ((StringBuffer) localObject1).toString();
			}
			localOutputStreamWriter.write(paramString1);
			localFileWriter.close();
		} catch (Exception localException) {
			localException.printStackTrace();
			return null;
		} finally {
			if (localOutputStreamWriter != null)
				localOutputStreamWriter.close();
			if (localFileWriter != null)
				localFileWriter.close();
			localOutputStreamWriter = null;
			localFileWriter = null;
		}
		return ((String) paramString3);
	}

	public static String readFile(File paramFile) throws IOException {
		StringBuffer localStringBuffer = new StringBuffer();
		BufferedReader localBufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(paramFile), "UTF-8"));
		String str = "";
		while ((str = localBufferedReader.readLine()) != null)
			localStringBuffer.append(str);
		return localStringBuffer.toString();
	}

	public static String readFile(File paramFile, boolean paramBoolean)
			throws IOException {
		StringBuffer localStringBuffer = new StringBuffer();
		BufferedReader localBufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(paramFile), "UTF-8"));
		String str = "";
		while ((str = localBufferedReader.readLine()) != null)
			localStringBuffer.append(str).append("\n");
		return localStringBuffer.toString();
	}

	public static String uploadFileAndCallback(
			MultipartFile paramMultipartFile, String paramString1,
			String paramString2) {
		if (_$1(paramMultipartFile)) {
			String str1 = paramMultipartFile.getOriginalFilename();
			String str2 = getFileExtName(str1);
			if (paramString2 == null)
				paramString2 = "";
			String str3 = paramString2 + System.currentTimeMillis()
					+ randomNumeric(new Random(), 6) + str2;
			String str4 = paramString1 + str3;
			File localFile1 = new File(RealPathUtil.getBigPicRealPath() + "/"
					+ paramString1);
			if (!(localFile1.exists()))
				localFile1.mkdirs();
			String str5 = RealPathUtil.getBigPicRealPath() + "/" + str4;
			File localFile2 = new File(str5);
			try {
				FileCopyUtils.copy(paramMultipartFile.getBytes(), localFile2);
			} catch (Exception localException) {
				logger.error("upload file:", localException);
			}
			return str4;
		}
		String str1 = null;
		if (paramMultipartFile != null)
			str1 = paramMultipartFile.getName();
		throw new InvalidFormatException(ResourceBundleHelper.getString(
				"invalid.file.format", "invalid.file.format") + str1);
	}

	private static boolean _$1(MultipartFile paramMultipartFile) {
		if ((paramMultipartFile.getSize() < 5439830840664129536L)
				|| (paramMultipartFile.getSize() > ((Long) PropertiesUtil
						.getObject(SysParameterEnum.MAX_FILE_SIZE, Long.class))
						.longValue() * 1048576L))
			return false;
		String str1 = paramMultipartFile.getOriginalFilename();
		String str2 = null;
		try {
			str2 = getFileExtName(str1);
		} catch (Exception localException) {
			logger.warn("can not get file extName,maybe there is not a file!");
			return false;
		}
		List localList = (List) PropertiesUtil.getObject(
				SysParameterEnum.ALLOWED_UPLOAD_FILE_TPYE, List.class);
		return (localList.contains(str2));
	}

	public static String getFileExtName(String paramString) {
		if (AppUtils.isBlank(paramString))
			return "";
		return paramString.substring(paramString.lastIndexOf("."))
				.toLowerCase();
	}
}
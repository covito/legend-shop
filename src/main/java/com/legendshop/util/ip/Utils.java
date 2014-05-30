package com.legendshop.util.ip;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class Utils {
	public static byte[] getIpByteArrayFromString(String paramString) {
		byte[] arrayOfByte = new byte[4];
		StringTokenizer localStringTokenizer = new StringTokenizer(paramString,
				".");
		try {
			arrayOfByte[0] = (byte) (Integer.parseInt(localStringTokenizer
					.nextToken()) & 0xFF);
			arrayOfByte[1] = (byte) (Integer.parseInt(localStringTokenizer
					.nextToken()) & 0xFF);
			arrayOfByte[2] = (byte) (Integer.parseInt(localStringTokenizer
					.nextToken()) & 0xFF);
			arrayOfByte[3] = (byte) (Integer.parseInt(localStringTokenizer
					.nextToken()) & 0xFF);
		} catch (Exception localException) {
			System.out.println(localException.getMessage());
		}
		return arrayOfByte;
	}

	public static void main(String[] paramArrayOfString) {
		byte[] arrayOfByte = getIpByteArrayFromString(paramArrayOfString[0]);
		for (int i = 0; i < arrayOfByte.length; ++i)
			System.out.println(arrayOfByte[i]);
		System.out.println(getIpStringFromBytes(arrayOfByte));
	}

	public static String getString(String paramString1, String paramString2,
			String paramString3) {
		try {
			return new String(paramString1.getBytes(paramString2), paramString3);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return paramString1;
	}

	public static String getString(byte[] paramArrayOfByte, String paramString) {
		try {
			return new String(paramArrayOfByte, paramString);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return new String(paramArrayOfByte);
	}

	public static String getString(byte[] paramArrayOfByte, int paramInt1,
			int paramInt2, String paramString) {
		try {
			return new String(paramArrayOfByte, paramInt1, paramInt2,
					paramString);
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		return new String(paramArrayOfByte, paramInt1, paramInt2);
	}

	public static String getIpStringFromBytes(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append(paramArrayOfByte[0] & 0xFF);
		localStringBuffer.append('.');
		localStringBuffer.append(paramArrayOfByte[1] & 0xFF);
		localStringBuffer.append('.');
		localStringBuffer.append(paramArrayOfByte[2] & 0xFF);
		localStringBuffer.append('.');
		localStringBuffer.append(paramArrayOfByte[3] & 0xFF);
		return localStringBuffer.toString();
	}
}
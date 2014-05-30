package com.legendshop.util.converter;

import com.legendshop.util.TimerUtil;
import com.legendshop.util.des.DES2;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class ByteConverter {
	private static String _$1 = "0123456789ABCDEF";

	public static String stringToHexString(String paramString) {
		String str1 = "";
		for (int i = 0; i < paramString.length(); ++i) {
			int j = paramString.charAt(i);
			String str2 = Integer.toHexString(j);
			str1 = str1 + str2;
		}
		return str1;
	}

	public static String encode(String paramString) {
		byte[] arrayOfByte = paramString.getBytes();
		StringBuilder localStringBuilder = new StringBuilder(
				arrayOfByte.length * 2);
		for (int i = 0; i < arrayOfByte.length; ++i) {
			localStringBuilder.append(_$1.charAt((arrayOfByte[i] & 0xF0) >> 4));
			localStringBuilder.append(_$1.charAt((arrayOfByte[i] & 0xF) >> 0));
		}
		return localStringBuilder.toString();
	}

	public static String decode(String paramString) {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(
				paramString.length() / 2);
		for (int i = 0; i < paramString.length(); i += 2)
			localByteArrayOutputStream
					.write(_$1.indexOf(paramString.charAt(i)) << 4
							| _$1.indexOf(paramString.charAt(i + 1)));
		return new String(localByteArrayOutputStream.toByteArray());
	}

	public static String encode(String paramString, int paramInt) {
		byte[] arrayOfByte = paramString.getBytes();
		StringBuilder localStringBuilder = new StringBuilder(
				arrayOfByte.length * 2);
		for (int i = 0; i < arrayOfByte.length; ++i) {
			localStringBuilder.append(_$1
					.charAt((arrayOfByte[i] & 0xF0) >> paramInt));
			localStringBuilder.append(_$1.charAt((arrayOfByte[i] & 0xF) >> 0));
		}
		return localStringBuilder.toString();
	}

	public static String decode(String paramString, int paramInt) {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(
				paramString.length() / 2);
		for (int i = 0; i < paramString.length(); i += 2)
			localByteArrayOutputStream
					.write(_$1.indexOf(paramString.charAt(i)) << paramInt
							| _$1.indexOf(paramString.charAt(i + 1)));
		return new String(localByteArrayOutputStream.toByteArray());
	}

	private static byte _$1(byte paramByte1, byte paramByte2) {
		int i = Byte.decode("0x" + new String(new byte[] { paramByte1 }))
				.byteValue();
		i = (byte) (i << 4);
		int j = Byte.decode("0x" + new String(new byte[] { paramByte2 }))
				.byteValue();
		byte k = (byte) (i | j);
		return k;
	}

	public static byte[] HexString2Bytes(String paramString) {
		byte[] arrayOfByte1 = new byte[6];
		byte[] arrayOfByte2 = paramString.getBytes();
		for (int i = 0; i < 6; ++i)
			arrayOfByte1[i] = _$1(arrayOfByte2[(i * 2)],
					arrayOfByte2[(i * 2 + 1)]);
		return arrayOfByte1;
	}

	public static byte[] hexStringToBytes(String paramString)
			throws IllegalArgumentException {
		int i = paramString.length();
		if (i % 2 > 0) {
			paramString = "0" + paramString;
			++i;
		}
		byte[] arrayOfByte = new byte[i / 2];
		char[] arrayOfChar = paramString.toUpperCase().toCharArray();
		int j = 0;
		int k = 0;
		while (j < arrayOfByte.length) {
			int l = _$1.indexOf(arrayOfChar[(k++)]);
			if (l < 0)
				throw new IllegalArgumentException(arrayOfChar[(k - 1)]
						+ " is not a hex char");
			int i1 = _$1.indexOf(arrayOfChar[(k++)]);
			if (i1 < 0)
				throw new IllegalArgumentException(arrayOfChar[(k - 1)]
						+ " is not a hex char");
			arrayOfByte[j] = (byte) (l << 4 | i1);
			++j;
		}
		return arrayOfByte;
	}

	public static void main(String[] paramArrayOfString) {
		String str1 = "413779696E723974526D3944524A585A613348736165596331796D7A2B377967";
		DES2 localDES2 = new DES2();
		String str2 = new String(localDES2.createDecryptor(localDES2
				.stringToByte(decode(str1))));
		Date localDate = TimerUtil.strToDate(str2);
		System.out.println("date = " + localDate);
		System.out.println("s1 = " + str2);
		System.out.println(encode("AB"));
		System.out.println(decode(str1));
		byte[] arrayOfByte = hexStringToBytes("4142");
		System.out.println("length = " + arrayOfByte.length);
		for (int i = 0; i < arrayOfByte.length; ++i) {
			int j = arrayOfByte[i];
			System.out.println(j);
		}
		String str3 = "1234";
		String str4 = encode(encode(str3, 2), 6);
		System.out.println(str4);
		System.out.println(decode(decode(str4, 2), 6));
	}
}
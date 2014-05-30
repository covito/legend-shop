package com.legendshop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

import sun.misc.BASE64Decoder;

public class StringUtil extends StringUtils {
	public static int intToString(String paramString) {
		return Integer.parseInt(paramString);
	}

	public static String stringToInt(int paramInt) {
		return String.valueOf(paramInt);
	}

	public static String stringToInt(Integer paramInteger) {
		return String.valueOf(paramInteger.intValue());
	}

	public static String arrayToString(String[] paramArrayOfString,
			String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramArrayOfString.length; ++i)
			localStringBuffer.append(paramArrayOfString[i]).append(paramString);
		return localStringBuffer.toString().substring(0,
				localStringBuffer.length() - 1);
	}

	public static String[] stringtoArray(String paramString1,
			String paramString2) {
		if (paramString1 == null) {
			String[] arrayOfString = new String[1];
			arrayOfString[0] = paramString1;
			return arrayOfString;
		}
		if (paramString2 == null)
			paramString2 = ",";
		StringTokenizer localStringTokenizer = new StringTokenizer(
				paramString1, paramString2);
		int i = localStringTokenizer.countTokens();
		String[] arrayOfString = new String[i];
		for (int j = 0; j < i; ++j)
			arrayOfString[j] = localStringTokenizer.nextToken();
		return arrayOfString;
	}

	public static String[] stringtoArray(String paramString, char paramChar) {
		return stringtoArray(paramString, String.valueOf(paramChar));
	}

	public static String[] stringtoArray(String paramString) {
		return stringtoArray(paramString, ",");
	}

	public static void printStrings(String[] paramArrayOfString,
			String paramString, OutputStream paramOutputStream) {
		try {
			if (paramArrayOfString != null) {
				int i = paramArrayOfString.length - 1;
				for (int j = 0; j < i; ++j)
					if (paramArrayOfString[j] != null)
						if (paramArrayOfString[j].indexOf(paramString) > -1)
							paramOutputStream.write(("\""
									+ paramArrayOfString[j] + "\""
									+ paramString).getBytes());
						else
							paramOutputStream.write((paramArrayOfString[j]
									+ paramString).getBytes());
					else
						paramOutputStream.write("null".getBytes());
				if (paramArrayOfString[i] != null)
					if (paramArrayOfString[i].indexOf(paramString) > -1)
						paramOutputStream.write(("\"" + paramArrayOfString[i]
								+ "\"").getBytes());
					else
						paramOutputStream.write(paramArrayOfString[i]
								.getBytes());
				else
					paramOutputStream.write("null".getBytes());
			} else {
				paramOutputStream.write("null".getBytes());
			}
			paramOutputStream.write("\n".getBytes());
		} catch (IOException localIOException) {
		}
	}

	public static void printStrings(String[] paramArrayOfString,
			String paramString) {
		printStrings(paramArrayOfString, paramString, System.out);
	}

	public static void printStrings(String[] paramArrayOfString,
			OutputStream paramOutputStream) {
		printStrings(paramArrayOfString, ",", paramOutputStream);
	}

	public static void printStrings(String[] paramArrayOfString) {
		printStrings(paramArrayOfString, ",", System.out);
	}

	public static String getReplaceString(String paramString1,
			String paramString2, String[] paramArrayOfString) {
		Object localObject = paramString2;
		if ((paramString2 == null) || (paramArrayOfString == null)
				|| (paramArrayOfString.length < 1))
			return paramString2;
		if (paramString1 == null)
			paramString1 = "%";
		for (int i = 0; i < paramArrayOfString.length; ++i) {
			String str1 = paramString1 + Integer.toString(i + 1);
			int j = ((String) localObject).indexOf(str1);
			if (j != -1) {
				String str2 = ((String) localObject).substring(0, j);
				if (i < paramArrayOfString.length)
					str2 = str2 + paramArrayOfString[i];
				else
					str2 = str2
							+ paramArrayOfString[(paramArrayOfString.length - 1)];
				str2 = str2 + ((String) localObject).substring(j + 2);
				localObject = str2;
			}
		}
		return ((String) localObject);
	}

	public static String getReplaceString(String paramString,
			String[] paramArrayOfString) {
		return getReplaceString("%", paramString, paramArrayOfString);
	}

	public static boolean contains(String[] paramArrayOfString,
			String paramString, boolean paramBoolean) {
		for (int i = 0; i < paramArrayOfString.length; ++i) {
			if (paramBoolean == true) {
				if (!(paramArrayOfString[i].equals(paramString))){
					if (paramArrayOfString[i].equalsIgnoreCase(paramString)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean contains(String[] paramArrayOfString,
			String paramString) {
		return contains(paramArrayOfString, paramString, true);
	}

	public static boolean containsIgnoreCase(String[] paramArrayOfString,
			String paramString) {
		return contains(paramArrayOfString, paramString, false);
	}

	public static String combineStringArray(String[] paramArrayOfString,
			String paramString) {
		int i = paramArrayOfString.length - 1;
		if (paramString == null)
			paramString = "";
		StringBuffer localStringBuffer = new StringBuffer(i * 8);
		for (int j = 0; j < i; ++j) {
			localStringBuffer.append(paramArrayOfString[j]);
			localStringBuffer.append(paramString);
		}
		localStringBuffer.append(paramArrayOfString[i]);
		return localStringBuffer.toString();
	}

	public static String fillString(char paramChar, int paramInt) {
		String str = "";
		for (int i = 0; i < paramInt; ++i)
			str = str + paramChar;
		return str;
	}

	public static String trimLeft(String paramString) {
		String str = paramString;
		if (str == null)
			return str;
		char[] arrayOfChar = str.toCharArray();
		int i = -1;
		for (int j = 0; (j < arrayOfChar.length)
				&& (Character.isWhitespace(arrayOfChar[j])); ++j)
			i = j;
		if (i != -1)
			str = str.substring(i + 1);
		return str;
	}

	public static String trimRight(String paramString) {
		String str = paramString;
		if (str == null)
			return str;
		char[] arrayOfChar = str.toCharArray();
		int i = -1;
		for (int j = arrayOfChar.length - 1; (j > -1)
				&& (Character.isWhitespace(arrayOfChar[j])); --j)
			i = j;
		if (i != -1)
			str = str.substring(0, i);
		return str;
	}

	public static String escapeCharacter(String paramString,
			HashMap paramHashMap) {
		if ((paramString == null) || (paramString.length() == 0))
			return paramString;
		if (paramHashMap.size() == 0)
			return paramString;
		StringBuffer localStringBuffer = new StringBuffer();
		StringCharacterIterator localStringCharacterIterator = new StringCharacterIterator(
				paramString);
		int i = localStringCharacterIterator.first();
		while (i != 65535) {
			String str = String.valueOf(i);
			if (paramHashMap.containsKey(str))
				str = (String) paramHashMap.get(str);
			localStringBuffer.append(str);
			int j = localStringCharacterIterator.next();
		}
		return localStringBuffer.toString();
	}

	public static int getByteLength(String paramString) {
		int i = 0;
		for (int j = 0; j < paramString.length(); ++j) {
			int k = paramString.charAt(j);
			int l = k >>> 8;
			i += ((l == 0) ? 1 : 2);
		}
		return i;
	}

	public static int getSubtringCount(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString1.length() == 0))
			return 0;
		int i = 0;
		for (int j = paramString1.indexOf(paramString2); j >= 0; j = paramString1
				.indexOf(paramString2, j + 1))
			++i;
		return i;
	}

	public static String encodePassword(String paramString1, String paramString2) {
		byte[] arrayOfByte1 = paramString1.getBytes();
		MessageDigest localMessageDigest = null;
		try {
			localMessageDigest = MessageDigest.getInstance(paramString2);
		} catch (Exception localException) {
			localException.printStackTrace();
			return paramString1;
		}
		localMessageDigest.reset();
		localMessageDigest.update(arrayOfByte1);
		byte[] arrayOfByte2 = localMessageDigest.digest();
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < arrayOfByte2.length; ++i) {
			if ((arrayOfByte2[i] & 0xFF) < 16)
				localStringBuffer.append("0");
			localStringBuffer.append(Long.toString(arrayOfByte2[i] & 0xFF, 16));
		}
		return localStringBuffer.toString();
	}

	public static String decodeString(String paramString) {
		BASE64Decoder localBASE64Decoder = new BASE64Decoder();
		try {
			return new String(localBASE64Decoder.decodeBuffer(paramString));
		} catch (IOException localIOException) {
			throw new RuntimeException(localIOException.getMessage(),
					localIOException.getCause());
		}
	}

	public static String convert(String paramString1, String paramString2,
			Map paramMap) throws MalformedPatternException {
		StringBuffer localStringBuffer = new StringBuffer(paramString1);
		Perl5Compiler localPerl5Compiler = new Perl5Compiler();
		Pattern localPattern = localPerl5Compiler.compile(paramString2);
		Perl5Matcher localPerl5Matcher = new Perl5Matcher();
		PatternMatcherInput localPatternMatcherInput = new PatternMatcherInput(
				paramString1.toString());
		int i = 0;
		String str1 = null;
		try {
			MatchResult localMatchResult=null;
			do {
				if (!(localPerl5Matcher.contains(localPatternMatcherInput,
						localPattern)))
					break;
				localMatchResult = localPerl5Matcher.getMatch();
			} while (!(AppUtils.isNotBlank(localMatchResult)));
			int j = localMatchResult.length();
			str1 = localMatchResult.toString();
			String str2 = (String) paramMap.get(str1);
			localStringBuffer.replace(localMatchResult.beginOffset(0) + i,
					localMatchResult.endOffset(0) + i, str2);
			i += str2.length() - j;
		} catch (Exception localException) {
			throw new RuntimeException(
					"can not replace key  " + str1, localException);
		}
		return localStringBuffer.toString();
	}

	public static String convert(String paramString, String[] paramArrayOfString) {
		StringBuffer localStringBuffer = new StringBuffer(paramString);
		int i = 0;
		do {
			if (localStringBuffer.indexOf("?") == -1)
				break;
			localStringBuffer.replace(localStringBuffer.indexOf("?"),
					localStringBuffer.indexOf("?") + 1, paramArrayOfString[i]);
		} while (++i <= paramArrayOfString.length - 1);
		return localStringBuffer.toString();
	}

	public static void main(String[] paramArrayOfString) {
		String[] arrayOfString = stringtoArray("test1/test2/test3", "/");
		String str1 = arrayToString(arrayOfString, " ");
		System.out.println("ss=" + str1);
		printStrings(arrayOfString, "\\");
		System.out.println("ssssssss==" + "1111abc1".indexOf("1abc"));
		str1 = "test1test2test3";
		boolean bool = contains(arrayOfString, "test1");
		System.out.println("result=" + contains(arrayOfString, "test3"));
		char c = 'c';
		String str2 = fillString(c, 2);
		System.out.println("cc=" + str2);
		String str3 = "4ccc!_#$";
		System.out.println("getByteLength==" + getByteLength(str3));
	}
}
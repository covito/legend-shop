package com.legendshop.util;

import java.io.PrintStream;

public class CodeFilter {
	public static String toHtml(String paramString) {
		if (paramString == null) {
			paramString = "";
			return paramString;
		}
		paramString = Replace(paramString.trim(), "&", "&amp;");
		paramString = Replace(paramString.trim(), "<", "&lt;");
		paramString = Replace(paramString.trim(), ">", "&gt;");
		paramString = Replace(paramString.trim(), "\t", "    ");
		paramString = Replace(paramString.trim(), "\r\n", "\n");
		paramString = Replace(paramString.trim(), "\n", "<br>");
		paramString = Replace(paramString.trim(), "  ", " &nbsp;");
		paramString = Replace(paramString.trim(), "'", "&#39;");
		paramString = Replace(paramString.trim(), "\\", "&#92;");
		return paramString;
	}

	public static String unHtml(String paramString) {
		paramString = Replace(paramString, "<br>", "\n");
		paramString = Replace(paramString, "</br>", "");
		paramString = Replace(paramString, "<p>", "");
		paramString = Replace(paramString, "</p>", "");
		paramString = Replace(paramString, "&nbsp;", "");
		paramString = Replace(paramString, "<strong>", "");
		paramString = Replace(paramString, "<div>", "");
		paramString = Replace(paramString, "</div>", "");
		paramString = Replace(paramString, "<span>", "");
		paramString = Replace(paramString, "</span>", "");
		paramString = Replace(paramString, "<", "");
		paramString = Replace(paramString, "/", "");
		paramString = Replace(paramString, ">", "");
		return paramString;
	}

	public static String htmlEncode(String paramString) {
		if (paramString == null) {
			paramString = "";
			return paramString;
		}
		paramString = paramString.trim();
		paramString = Replace(paramString, "\t", "    ");
		paramString = Replace(paramString, "\r\n", "\n");
		paramString = Replace(paramString, "\n", "<br>");
		paramString = Replace(paramString, "  ", " &nbsp;&nbsp;");
		paramString = Replace(paramString, "\"", "&#34;");
		return paramString;
	}

	public static String strEncode(String paramString) {
		if (paramString == null) {
			paramString = "";
			return paramString;
		}
		paramString = paramString.trim();
		paramString = Replace(paramString, "<", "&#60;");
		paramString = Replace(paramString, ">", "&#62;");
		paramString = Replace(paramString, "\t", "    ");
		paramString = Replace(paramString, "\r\n", "\n");
		paramString = Replace(paramString, "\n", "<br>");
		paramString = Replace(paramString, "  ", " &nbsp;&nbsp;");
		paramString = Replace(paramString, "'", "&#39;");
		paramString = Replace(paramString, "\\", "/");
		paramString = Replace(paramString, "\"", "&#34;");
		return paramString;
	}

	public static String Replace(String paramString1, String paramString2,
			String paramString3) {
		StringBuffer localStringBuffer = new StringBuffer();
		int i = paramString1.length();
		int j = paramString2.length();
		int l;
		int k;
		for (k = 0; (l = paramString1.indexOf(paramString2, k)) >= 0; k = l + j) {
			localStringBuffer.append(paramString1.substring(k, l));
			localStringBuffer.append(paramString3);
		}
		if (k < i)
			localStringBuffer.append(paramString1.substring(k));
		return localStringBuffer.toString();
	}

	public static void main(String[] paramArrayOfString) {
		CodeFilter localCodeFilter = new CodeFilter();
		String str1 = "<p>ddd</p> <br>nsss</br>";
		String str2 = unHtml(str1);
		System.out.println(str2);
	}
}
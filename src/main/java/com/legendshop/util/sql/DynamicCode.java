package com.legendshop.util.sql;

import com.legendshop.util.AppUtils;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class DynamicCode {
	Logger _$9 = Logger.getLogger(DynamicCode.class);
	private static final String _$8 = "\\$.*?\\$";
	private static final String _$7 = "\\#.*?\\#";
	private static final String _$6 = "{.*?}";
	private static final String _$5 = "{!";
	private static final String _$4 = "{?";
	private static final String _$3 = "{";
	private static final String _$2 = "}";
	private static final String _$1 = "||";

	public String convert(String paramString1, String paramString2,
			Map<String, Object> paramMap) throws MalformedPatternException {
		StringBuffer localStringBuffer = new StringBuffer(paramString1);
		Perl5Compiler localPerl5Compiler = new Perl5Compiler();
		Pattern localPattern = localPerl5Compiler.compile(paramString2);
		Perl5Matcher localPerl5Matcher = new Perl5Matcher();
		PatternMatcherInput localPatternMatcherInput = new PatternMatcherInput(
				paramString1.toString());
		int i = 0;
		while (localPerl5Matcher.contains(localPatternMatcherInput,
				localPattern)) {
			MatchResult localMatchResult = localPerl5Matcher.getMatch();
			int j = localMatchResult.length();
			String str1 = localMatchResult.toString();
			String str2 = _$1(str1, paramMap);
			localStringBuffer.replace(localMatchResult.beginOffset(0) + i,
					localMatchResult.endOffset(0) + i, str2);
			i += str2.length() - j;
		}
		return localStringBuffer.toString();
	}

	public void fillPlaceHolder(String paramString,
			Map<String, String> paramMap, Map<String, Boolean> paramMap1)
			throws MalformedPatternException {
		String str1 = (String) paramMap.get(paramString);
		StringBuffer localStringBuffer = new StringBuffer(str1);
		Perl5Compiler localPerl5Compiler = new Perl5Compiler();
		Pattern localPattern = localPerl5Compiler.compile("\\#.*?\\#");
		Perl5Matcher localPerl5Matcher = new Perl5Matcher();
		PatternMatcherInput localPatternMatcherInput = new PatternMatcherInput(
				str1);
		int i = 0;
		while (localPerl5Matcher.contains(localPatternMatcherInput,
				localPattern)) {
			MatchResult localMatchResult = localPerl5Matcher.getMatch();
			int j = localMatchResult.length();
			String str2 = localMatchResult.toString();
			String str3 = str2.replace("#", "").trim();
			String str4 = (String) paramMap.get(str3);
			if (!(paramMap1.containsKey(str3)))
				paramMap1.put(str3, Boolean.valueOf(true));
			else
				throw new RuntimeException(
						"ConfigCode recursion detected, codeKey = "
								+ paramString + ",  key = " + str3
								+ ", value = " + str4);
			if (AppUtils.isNotBlank(str4))
				fillPlaceHolder(str3, paramMap, paramMap1);
			str4 = (String) paramMap.get(str3);
			if (AppUtils.isBlank(str4))
				throw new RuntimeException("can not get value from key = "
						+ str3);
			localStringBuffer.replace(localMatchResult.beginOffset(0) + i,
					localMatchResult.endOffset(0) + i, str4);
			paramMap.put(paramString, localStringBuffer.toString());
			i += str4.length() - j;
		}
	}

	public String convert(String paramString, Map<String, Object> paramMap) {
		try {
			if (AppUtils.isBlank(paramString))
				return null;
			return convert(paramString, "{.*?}", paramMap);
		} catch (Exception localException) {
			this._$9.error("获取动态SQL出错" + localException);
		}
		return null;
	}

	private String _$1(String paramString, Map<String, Object> paramMap)
			throws MalformedPatternException {
		boolean bool1 = paramString.startsWith("{!");
		boolean bool2 = paramString.startsWith("{?");
		StringBuffer localStringBuffer = null;
		String str1 = null;
		if (bool1) {
			int i = paramString.indexOf("||");
			if (i != -1)
				str1 = paramString.substring(0, paramString.indexOf("||") + 2);
			else
				str1 = "{!";
			localStringBuffer = new StringBuffer(_$1(paramString, str1, "}"));
		} else if (bool2) {
			localStringBuffer = new StringBuffer(_$1(paramString, "{?", "}"));
		} else {
			localStringBuffer = new StringBuffer(_$1(paramString, "{", "}"));
		}
		Perl5Compiler localPerl5Compiler = new Perl5Compiler();
		Pattern localPattern = localPerl5Compiler.compile("\\$.*?\\$");
		Perl5Matcher localPerl5Matcher = new Perl5Matcher();
		PatternMatcherInput localPatternMatcherInput = new PatternMatcherInput(
				localStringBuffer.toString());
		int i = 0;
		while (localPerl5Matcher.contains(localPatternMatcherInput,
				localPattern)) {
			MatchResult localMatchResult = localPerl5Matcher.getMatch();
			int j = localMatchResult.length();
			String str2 = localMatchResult.toString();
			String str3 = null;
			Object localObject = paramMap.get(str2.substring(1,
					str2.length() - 1));
			if (localObject != null)
				str3 = localObject.toString();
			if ((bool1) && (str3 == null)) {
				String str4 = _$1(str1, "{!", "||");
				if ((!(str4.startsWith("{!"))) && (!(str4.endsWith("||"))))
					str3 = str4.trim();
				else
					str3 = "";
			}
			if (str3 != null)
				if (!(bool2)) {
					localStringBuffer.replace(localMatchResult.beginOffset(0)
							+ i, localMatchResult.endOffset(0) + i, str3);
					i += str3.length() - j;
				} else {
					localStringBuffer.replace(localMatchResult.beginOffset(0)
							+ i, localMatchResult.endOffset(0) + i, "?");
					i += str3.length() - j;
				}
			else
				return "";
		}
		return localStringBuffer.toString();
	}

	private String _$1(String paramString1, String paramString2,
			String paramString3) {
		if ((paramString1 == null) || (paramString1 == paramString2)
				|| (paramString1 == paramString3))
			return paramString1;
		if ((!(paramString1.startsWith(paramString2)))
				|| (!(paramString1.endsWith(paramString3))))
			return paramString1;
		return paramString1.substring(paramString2.length(),
				paramString1.length() - paramString3.length());
	}

	public static void main(String[] paramArrayOfString)
			throws MalformedPatternException {
		DynamicCode localDynamicCode = new DynamicCode();
		long l1 = System.currentTimeMillis();
		for (int i = 0; i < 1; ++i)
			localDynamicCode._$1(localDynamicCode);
		long l2 = System.currentTimeMillis();
		System.out.println("total time ：" + (l2 - l1));
		System.out.println("avage time ：" + ((float) (l2 - l1) / 100.0F));
	}

	private void _$1(DynamicCode paramDynamicCode)
			throws MalformedPatternException {
		HashMap localHashMap = new HashMap();
		localHashMap.put("id", "1");
		localHashMap.put("id1", "2");
		localHashMap.put("name", "hewq");
		localHashMap.put("condition", "and moc = 1");
		localHashMap.put("memo2", "df");
		String str1 = " select * from t_scheme where 1==1 \n { and id = $id$   id1 = $id$ } \n { and name = $name$ } \n  {$condition$} \n {!     default value   || and  memo1 = $memo1$} \n { and memo2 = $memo2$}  \n order by id ";
		String str2 = paramDynamicCode.convert(str1, "{.*?}", localHashMap);
		System.out.println(str2);
	}
}
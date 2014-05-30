package com.legendshop.util;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;

public class SafeHtml {
	private static Set _$3 = new HashSet();
	private static Set _$2 = new HashSet();
	private static Set _$1 = new HashSet();

	private static void _$1(String paramString, Set paramSet) {
		if (paramString == null)
			return;
		String[] arrayOfString = paramString.toUpperCase().split(",");
		for (int i = 0; i < arrayOfString.length; ++i)
			paramSet.add(arrayOfString[i].trim());
	}

	public String ensureAllAttributesAreSafe(String paramString) {
		StringBuffer sb = new StringBuffer(paramString.length());
		try {
			Lexer localLexer = new Lexer(paramString);
			Node localNode;
			while (true) {
				if ((localNode = localLexer.nextNode()) == null) {
					break;
				}
				if (!(localNode instanceof Tag)) {
					continue;
				}
				Tag localTag = (Tag) localNode;
				_$1(localTag, false);
				sb.append(localTag.toHtml());
			}
		} catch (Exception localException) {
			throw new RuntimeException("Problems while parsing HTML",
					localException);
		}
		return sb.toString();
	}

	public String makeSafe(String paramString) {
		if ((paramString == null) || (paramString.length() == 0))
			return paramString;
		StringBuffer localStringBuffer1 = new StringBuffer(paramString.length());
		try {
			Lexer localLexer = new Lexer(paramString);
			Node localNode;
			while ((localNode = localLexer.nextNode()) != null) {
				Object localObject;
				boolean bool = localNode instanceof TextNode;
				if (bool) {
					localObject = localNode.toHtml();
					if ((((String) localObject).indexOf(62) > -1)
							|| (((String) localObject).indexOf(60) > -1)) {
						StringBuffer localStringBuffer2 = new StringBuffer(
								(String) localObject);
						replaceAll(localStringBuffer2, "<", "&lt;");
						replaceAll(localStringBuffer2, ">", "&gt;");
						replaceAll(localStringBuffer2, "\"", "&quot;");
						localNode.setText(localStringBuffer2.toString());
					}
				}
				if ((bool) || ((localNode instanceof Tag) && (_$1(localNode)))) {
					localStringBuffer1.append(localNode.toHtml());
				} else {
					localObject = new StringBuffer(localNode.toHtml());
					replaceAll((StringBuffer) localObject, "<", "&lt;");
					replaceAll((StringBuffer) localObject, ">", "&gt;");
					localStringBuffer1.append(((StringBuffer) localObject)
							.toString());
				}
			}
		} catch (Exception localException) {
			throw new RuntimeException("Error while parsing HTML",
					localException);
		}
		return ((String) localStringBuffer1.toString());
	}

	private boolean _$1(Node paramNode) {
		Tag localTag = (Tag) paramNode;
		if (!(_$3.contains(localTag.getTagName())))
			return false;
		_$1(localTag, true);
		return true;
	}

	private void _$1(Tag paramTag, boolean paramBoolean) {
		Vector localVector = new Vector();
		Iterator localIterator = paramTag.getAttributesEx().iterator();
		while (true) {
			Attribute localAttribute;
			while (true) {
				String str1;
				String str2;
				while (true) {
					while (true) {
						if (!(localIterator.hasNext()))
							paramTag.setAttributesEx(localVector);
						localAttribute = (Attribute) localIterator.next();
						str1 = localAttribute.getName();
						if (str1 == null) {
							localVector.add(localAttribute);
						}
						str1 = str1.toUpperCase();
						if (localAttribute.getValue() != null)
							break;
						localVector.add(localAttribute);
					}
					str2 = localAttribute.getValue().toLowerCase();
					if ((!(paramBoolean)) || (_$2(str1)))
						break;
				}
				if (_$1(str1, str2))
					break;
			}
			if (localAttribute.getValue().indexOf("&#") > -1)
				localAttribute.setValue(localAttribute.getValue().replaceAll(
						"&#", "&amp;#"));
			localVector.add(localAttribute);
		}

	}

	private boolean _$2(String paramString) {
		return _$2.contains(paramString);
	}

	private boolean _$1(String paramString1, String paramString2) {
		if ((paramString1.length() >= 2) && (paramString1.charAt(0) == 'O')
				&& (paramString1.charAt(1) == 'N'))
			return false;
		if ((paramString2.indexOf(10) > -1) || (paramString2.indexOf(13) > -1)
				|| (paramString2.indexOf(0) > -1))
			return false;
		if (("HREF".equals(paramString1)) || ("SRC".equals(paramString1))) {
			if (_$1(paramString2)) {
				return false;
			}
			return false;
		}
		return ((!("STYLE".equals(paramString1))) || (paramString2.indexOf(40) <= -1));
	}

	private boolean _$1(String paramString) {
		int i = 0;
		if ((i != 0) && (paramString.length() > 0)
				&& (paramString.charAt(0) == '/'))
			return true;
		Iterator localIterator = _$1.iterator();
		while (localIterator.hasNext()) {
			String str = localIterator.next().toString().toLowerCase();
			if (paramString.startsWith(str))
				return true;
		}
		return false;
	}

	public static String replaceAll(StringBuffer paramStringBuffer,
			String paramString1, String paramString2) {
		for (int i = paramStringBuffer.indexOf(paramString1); i > -1; i = paramStringBuffer
				.indexOf(paramString1))
			paramStringBuffer.replace(i, i + paramString1.length(),
					paramString2);
		return paramStringBuffer.toString();
	}

	public static void main(String[] paramArrayOfString) {
		SafeHtml localSafeHtml = new SafeHtml();
		String str1 = "<html><table><tr><td>aaa<script type=\"text/javascript\">function b(){alert(21);}b();</script><p>测试</p></td></tr></table></html>";
		String str2 = localSafeHtml.makeSafe(str1);
		String str3 = localSafeHtml.ensureAllAttributesAreSafe(str1);
		System.out.println(str2);
		System.out.println(str3);
	}

	static {
		_$1("u, a, img, i, u, li, ul, font, br, p, b, hr,pre", _$3);
		_$1("src, href, size, face, color, target, rel", _$2);
		_$1("http://, https://, mailto:, ftp://", _$1);
	}
}
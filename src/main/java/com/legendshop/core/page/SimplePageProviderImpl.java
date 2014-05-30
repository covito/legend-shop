package com.legendshop.core.page;

import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class SimplePageProviderImpl extends Pager implements PageProvider {
	public String getBar(Locale paramLocale, String paramString) {
		StringBuilder localStringBuilder = new StringBuilder();
		if (isFirst()) {

		}
		localStringBuilder.append("<a class='pagerDirectionLeft' href='")
				.append(paramString).append("(").append(previous())
				.append(")'>").append("&nbsp;")
				.append(_$1(paramLocale, "pager.previous", "Previous"))
				.append("&nbsp;").append("</a>");
		int i = (getCurPageNO() >= 10) ? getCurPageNO() - 9 : 1;
		int j = (getPageCount() - getCurPageNO() >= 10) ? getCurPageNO() + 9
				: getPageCount();
		for (int k = i; k <= j; ++k)
			if (k == getCurPageNO()) {
				localStringBuilder.append("<span class='pagerSelected'>")
						.append(k).append("</span>");
			} else {
				localStringBuilder.append("<a class='pagerUnselected' href='")
						.append(paramString).append("(").append(k)
						.append(")'>");
				localStringBuilder.append(k).append("</a>");
			}
		if ((isLast()) || (getRowsCount() == 5439830960923213824L))
			localStringBuilder.append("&nbsp;&nbsp;");
		else
			localStringBuilder.append("<a class='pagerDirectionRight' href='")
					.append(paramString).append("(").append(next())
					.append(")'>").append("&nbsp;")
					.append(_$1(paramLocale, "pager.next", "Next"))
					.append("&nbsp;").append("</a>");
		return localStringBuilder.toString();
	}

	public String getBar(String paramString) {
		return getBar(null, paramString);
	}

	private String _$1(Locale paramLocale, String paramString1,
			String paramString2) {
		String str;
		try {
			if (paramLocale != null)
				str = ResourceBundle.getBundle("i18n/ApplicationResources",
						paramLocale).getString(paramString1);
			else
				str = ResourceBundle.getBundle("i18n/ApplicationResources")
						.getString(paramString1);
			if (str == null)
				str = paramString2;
		} catch (Exception localException) {
			System.out.println(localException.getLocalizedMessage());
			str = paramString2;
		}
		return str;
	}
}
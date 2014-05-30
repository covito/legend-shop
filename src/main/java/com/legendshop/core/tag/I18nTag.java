package com.legendshop.core.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.springframework.web.servlet.LocaleResolver;

import com.legendshop.core.helper.ResourceBundleHelper;

public class I18nTag extends LegendShopTag implements DynamicAttributes {
	private static LocaleResolver _$3;
	private String _$2;
	private final List<Object> _$1 = new ArrayList();

	public I18nTag() {
		if (_$3 == null)
			_$3 = (LocaleResolver) getBean("localeResolver");
	}

	public void doTag() throws JspException, IOException {
		Locale localLocale = _$3.resolveLocale(request());
		if (this._$1.size() == 0) {
			String localObject = ResourceBundleHelper.getString(localLocale,
					this._$2);
			write((String) localObject);
		} else {
			String[] localObject = new String[this._$1.size()];
			for (int i = 0; i < this._$1.size(); ++i) {
				String str2 = (String) this._$1.get(i);
				if ((str2 != null) && (str2.startsWith("message:")))
					localObject[i] = ResourceBundleHelper.getString(
							localLocale, str2.substring("message:".length()));
				else
					localObject[i] = str2;
			}
			String str1 = ResourceBundleHelper.getString(localLocale, this._$2,
					localObject);
			write(str1);
		}
	}

	public void setKey(String paramString) {
		this._$2 = paramString;
	}

	public void setDynamicAttribute(String paramString1, String paramString2,
			Object paramObject) throws JspException {
		this._$1.add(paramObject);
	}
}
package com.legendshop.core.tag;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public class TemplateResourceTag extends LegendShopTag {
	private String _$1;

	public void doTag() throws IOException {
		String str = request().getContextPath() + this._$1;
		write(str);
	}

	public void setItem(String paramString) {
		this._$1 = paramString;
	}
}
package com.legendshop.core.tag;

import com.legendshop.core.helper.PropertiesUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public class ImagesTag extends LegendShopTag {
	private String _$3;
	private String _$2 = PropertiesUtil.getSmallImagePathPrefix();
	private int _$1;

	public void doTag() throws IOException {
		String str = request().getContextPath() + this._$2 + this._$1 + "/"
				+ this._$3;
		write(str);
	}

	public void setItem(String paramString) {
		this._$3 = paramString;
	}

	public void setScale(int paramInt) {
		this._$1 = paramInt;
	}
}
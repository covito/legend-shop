package com.legendshop.core.tag;

import com.legendshop.core.helper.PropertiesUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public class PhotoTag extends LegendShopTag {
	private String item;
	private String prefix = PropertiesUtil.getPhotoPathPrefix();

	public void doTag() throws IOException {
		String str =request().getContextPath() + this.prefix + this.item;
		write(str);
	}

	public void setItem(String item) {
		this.item = item;
	}
}
package com.legendshop.spi.page;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum FrontPage implements PageDefinition {
	VARIABLE, ERROR_PAGE, INSTALL, ALL_PAGE, FAIL, TOPALL, TOP, TOP_USER_INFO, TOP_SORT, TOP_SORT_NEWS, TOP_NEWS, COPY, HOT_ON, HOT_SALE, FRIEND_LINK, HOT_VIEW, HOT_COMMENT, HOT_RECOMMEND, ALL, PROD_PIC_GALLERY, IPSEARCH, BOUGHT, CASH_SAVE, RESETPASSWORD, HOME_TOP, BOTTOM, VISITED_PROD, VISITED_SHOP, ORDER, AJAX_SEARCH, PRODUCT_SORT_LIST, SORT_LIST, PRODUCT_COMMENTS, PRODUCT_COMMENT_LIST, PRODUCT_CONSULTS, PRODUCT_CONSULTS_LIST;

	private final String value = name();

	public String getValue(HttpServletRequest request,
			HttpServletResponse response) {
		return getValue(request, response, this.value, this);
	}

	public String getValue(HttpServletRequest request,
			HttpServletResponse response, String path,
			PageDefinition pageDefinition) {
		return PagePathCalculator.calculateFronendPath(request, response, path,
				pageDefinition);
	}

	public String getNativeValue() {
		return this.value;
	}
}
package com.legendshop.spi.page;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum TilesPage implements PageDefinition {
	VARIABLE, INDEX_PAGE, NO_LOGIN, AFTER_OPERATION, NSORT, LOGIN, LEAVEWORD, NEWS, PUB, ALL_NEWS, PAGE_CASH, REG, LEAGUE, PRODUCT_SORT, SEARCHALL, PAGE_SUB, MYACCOUNT, SHOPCONTACT, BUY, ORDER, OPEN_SHOP, VIEWS, PRODUCT_COMMENT, HOME, ALL_SORTS, ALL_PRODS, ALL_BRAND, LEAVE_MSG, SHOP_SEARCH, WARNING;

	private final String value=name().toLowerCase().replace("_", "");

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
package com.legendshop.spi.page;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum FowardPage implements PageDefinition {
	VARIABLE, INDEX_QUERY, HOME_QUERY, VIEWS, ADV_LIST_QUERY, BRAND_LIST_QUERY, LINK_LIST_QUERY, HOT_LIST_QUERY, IMG_LIST_QUERY, IJPG_LIST_QUERY, LOGO_LIST_QUERY, LEAGUE_LIST_QUERY, NEWS_LIST_QUERY, NEWSCAT_LIST_QUERY, NSORT_LIST_QUERY, PAY_TYPE_LIST_QUERY, PROD_LIST_QUERY, SHOP_DETAIL_LIST_QUERY, SHOP_DETAIL_LOAD, SORT_LIST_QUERY, GSORT_LIST_QUERY, PARAM_LIST_QUERY, PROD_COMM_LIST_QUERY, USER_COMM_LIST_QUERY, VLOG_LIST_QUERY, USER_DETAIL_LIST_QUERY, PUB_LIST_QUERY, DYNAMIC_QUERY, DELIVERYCORP_LIST_QUERY, DELIVERYTYPE_LIST_QUERY, PARTNER_LIST_QUERY, TAG_QUERY, EVENT_QUERY, PROD_SPEC_LIST_QUERY, PROD_CONSULT_LIST_QUERY, PRODUCTPROPERTY_LIST_QUERY, NAVIGATION_LIST_QUERY, SENSITIVEWORD_LIST_QUERY, DISTRICT_LIST_PAGE;

	private final String value = name();

	public String getValue(HttpServletRequest request,
			HttpServletResponse response) {
		return getValue(request, response, this.value, null);
	}

	public String getValue(HttpServletRequest request,
			HttpServletResponse response, String path,
			PageDefinition pageDefinition) {
		return PagePathCalculator.calculateActionPath("forward:", path);
	}

	public String getNativeValue() {
		return this.value;
	}
}
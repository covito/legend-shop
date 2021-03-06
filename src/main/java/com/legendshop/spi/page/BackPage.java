package com.legendshop.spi.page;

import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.PagePathCalculator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public enum BackPage implements PageDefinition {
	VARIABLE, BACK_ERROR_PAGE, ORDERDETAIL, ADV_LIST_PAGE, ADV_EDIT_PAGE, BRAND_LIST_PAGE, BRAND_EDIT_PAGE, LINK_LIST_PAGE, LINK_EDIT_PAGE, HOT_LIST_PAGE, HOT_EDIT_PAGE, IMG_LIST_PAGE, IMG_EDIT_PAGE, DASH_BOARD, ADMIN_HOME, ADMIN_TOP, IJPG_LIST_PAGE, IJPG_EDIT_PAGE, UPGRADE_PAGE, LOGIN_HIST_LIST_PAGE, LOGIN_HIST_SUM_PAGE, LOGO_LIST_PAGE, LOGO_EDIT_PAGE, LUCENE_PAGE, LEAGUE_LIST_PAGE, LEAGUE_EDIT_PAGE, NEWS_LIST_PAGE, NEWS_EDIT_PAGE, NEWSCAT_LIST_PAGE, NEWSCAT_EDIT_PAGE, NSORT_LIST_PAGE, NSORT_EDIT_PAGE, NSORT_APPENDBRAND_PAGE, PROCESSING_ORDER_LIST_PAGE, PROCESSED_ORDER_LIST_PAGE, PAY_PAGE, PAY_TYPE_LIST_PAGE, PAY_TYPE_EDIT_PAGE, PROD_LIST_PAGE, PROD_CONTENT_LIST_PAGE, PROD_LIST_PAGE_TEMP, PROD_EDIT_PAGE, PROD_EDIT_NEW_PAGE, PROD_PUBLISH_PAGE, PUBLISH_SORT_QUERY_PAGE, PUBLISH_NSORT_QUERY_PAGE, PUBLISH_SUBSORT_QUERY_PAGE, PUBLISH_BRAND_QUERY_PAGE, PROD_PUBLISH2_PAGE, PROD_PUBLISH3_PAGE, PROD_PUBLISH4_PAGE, PROD_CATEGORY_2_PAGE, PROD_CATEGORY_3_PAGE, PROD_EDIT_PROPERTY_PAGE, PROD_EDIT_DETAILS_PAGE, PROD_EDIT_SALE_PROP_PAGE, APPEND_PRODUCT, CREATEP_RODUCT_STEP, PROD_COMM_LIST_PAGE, PROD_COMM_EDIT_PAGE, PUB_LIST_PAGE, PUB_EDIT_PAGE, SHOP_DETAIL_LIST_PAGE, SHOP_DETAIL_EDIT_PAGE, SORT_LIST_PAGE, SORT_EDIT_PAGE, PARAM_LIST_PAGE, CACHE_LIST_PAGE, HTML_LIST_PAGE, PLUGIN_LIST_PAGE, PARAM_EDIT_PAGE, USER_COMM_LIST_PAGE, USER_COMM_EDIT_PAGE, USER_DETAIL_LIST_PAGE, VLOG_LIST_PAGE, VLOG_EDIT_PAGE, SHOW_DYNAMIC_ATTRIBUTE, SHOW_DYNAMIC, DYNAMIC_ATTRIBUTE, MODIFYPRICE, DELIVERYCORP_LIST_PAGE, DELIVERYCORP_EDIT_PAGE, DELIVERYTYPE_LIST_PAGE, DELIVERYTYPE_EDIT_PAGE, PARTNER_LIST_PAGE, PARTNER_EDIT_PAGE, PARTNER_CHANGE_PASSWORD_PAGE, TAG_LIST, TAG, EVENT_LIST_PAGE, EVENT_EDIT_PAGE, PRODUCTSPEC_LIST_PAGE, PRODUCTSPEC_EDIT_PAGE, FILE_EDIT_PAGE, PROD_CONSULT_LIST_PAGE, PROD_CONSULT_EDIT_PAGE, PRODUCTPROPERTY_LIST_PAGE, PRODUCTPROPERTY_EDIT_PAGE, MENU, NAVIGATION_LIST_PAGE, NAVIGATION_EDIT_PAGE, NAVIGATIONITEM_EDIT_PAGE, SENSITIVEWORD_LIST_PAGE, SENSITIVEWORD_EDIT_PAGE, DISTRICT_LIST_PAGE, RPOVINCE_PAGE, CITY_PAGE, AREA_PAGE, PROVINCE_CONTENT_LIST_PAGE, CITY_CONTENT_LIST_PAGE, AREA_CONTENT_LIST_PAGE;

	private final String value = name();

	public String getValue(HttpServletRequest request,
			HttpServletResponse response) {
		return getValue(request, response, this.value, this);
	}

	public String getValue(HttpServletRequest request,
			HttpServletResponse response, String path,
			PageDefinition pageDefinition) {
		return PagePathCalculator.calculateBackendPath(request, response, path,
				pageDefinition);
	}

	public String getNativeValue() {
		return this.value;
	}
}
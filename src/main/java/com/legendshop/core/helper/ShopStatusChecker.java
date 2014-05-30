package com.legendshop.core.helper;

import com.legendshop.core.model.UserMessages;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.util.constant.ShopStatusEnum;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

public class ShopStatusChecker implements Checker<ShopDetailView> {
	private static Logger logger = LoggerFactory
			.getLogger(ShopStatusChecker.class);
	private LocaleResolver localeResolver;

	public boolean check(ShopDetailView paramShopDetailView,
			HttpServletRequest paramHttpServletRequest) {
		boolean i = false;
		if (PropertiesUtil.isDefaultShopName(paramShopDetailView.getUserName())) {
			return i;
		}
		Integer localInteger = paramShopDetailView.getStatus();
		if (!(ShopStatusEnum.ONLINE.value().equals(localInteger))) {
			if (ShopStatusEnum.AUDITING.value().equals(localInteger)) {
				String str = paramShopDetailView.getUserName();
				Locale localLocale = this.localeResolver
						.resolveLocale(paramHttpServletRequest);
				logger.warn("shop {} auditing ", str);
				UserMessages localUserMessages = new UserMessages();
				localUserMessages.setTitle(ResourceBundleHelper.getString(
						localLocale, "shop.name")
						+ str
						+ ResourceBundleHelper.getString(localLocale,
								"shop.is.auditing"));
				localUserMessages.setDesc("Shop " + str
						+ " is Auditing, Please wait.");
				localUserMessages.setCode("502");
				localUserMessages.addCallBackList(ResourceBundleHelper
						.getString(localLocale, "shop.my.shop"),
						ResourceBundleHelper.getString(localLocale,
								"lookup.shop.status"), "myaccount.do");
				paramHttpServletRequest.setAttribute(UserMessages.MESSAGE_KEY,
						localUserMessages);
			}
			i = true;
		}
		return i;
	}

	public void setLocaleResolver(LocaleResolver paramLocaleResolver) {
		this.localeResolver = paramLocaleResolver;
	}
}
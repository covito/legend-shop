package com.legendshop.core.helper;

import com.legendshop.core.AttributeKeys;
import com.legendshop.core.constant.PageDefinition;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.service.ShopService;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.visit.VisitHistory;
import com.legendshop.page.TemplatePageManager;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.CookieUtil;
import com.legendshop.util.ServiceLocatorIF;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

public class ThreadLocalContext {
	private static final Logger logger = LoggerFactory
			.getLogger(ThreadLocalContext.class);
	private static ShopService shopService;
	private static TemplatePageManager templateManager;
	private static boolean _$6 = false;
	private static LocaleResolver localeRes;
	private static String REQ_SHOPDETAIL = "REQ_SHOPDETAIL_VIEW";
	private static String REQ_FRONT = "REQ_FRONTTYPE";
	private static String REQ_END = "REQ_ENDTYPE";
	private static ThreadLocal<HttpServletRequest> current = new ThreadLocal<HttpServletRequest>();

	public static ShopDetailView getShopDetailView(HttpServletRequest request,
			HttpServletResponse response, String shopName) {
		if (AppUtils.isBlank(shopName)) {
			return null;
		}
		ShopDetailView shopView = (ShopDetailView) request
				.getAttribute(REQ_SHOPDETAIL);
		if (shopView == null) {
			shopView = shopService.getShopDetailView(shopName);
			if (shopView == null) {
				String str = (String) PropertiesUtil.getObject(
						SysParameterEnum.DEFAULT_SHOP, String.class);
				if (AppUtils.isBlank(str))
					throw new RuntimeException("Can't find default shop name");
				shopName = str;
				shopView = shopService.getShopDetailView(shopName);
			}
			if (shopView != null)
				request.setAttribute(REQ_SHOPDETAIL, shopView);
		}
		return shopView;
	}

	public static ShopDetailView getShopDetailView(String paramString) {
		ShopDetailView localShopDetailView = shopService
				.getShopDetailView(paramString);
		return localShopDetailView;
	}

	private static void _$2(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			ShopDetailView paramShopDetailView) {
		String str1 = paramShopDetailView.getUserName();
		String str2 = (String) paramHttpServletRequest.getSession()
				.getAttribute("shopName");
		if (str1 == null) {
			logger.debug("shopName can not both NULL");
			return;
		}
		if ((!(str1.equals(str2))) || (AppUtils.isBlank(str2))) {
			logger.debug("从商城  {} 进入另外一个商城  {}", str2, str1);
			str2 = str1;
			_$1(paramHttpServletRequest, paramHttpServletResponse, str2);
			VisitHistoryHelper.visit(paramShopDetailView,
					paramHttpServletRequest, paramHttpServletResponse);
		}
		_$1(paramHttpServletRequest, paramHttpServletResponse,
				paramShopDetailView);
	}

	private static void _$1(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse,
			ShopDetailView paramShopDetailView) {
		String str2;
		String[] arrayOfString;
		String str1 = paramShopDetailView.getFrontEndLanguage();
		if ((AppUtils.isBlank(str1))
				|| ("userChoice".equals(str1))
				|| (!("userChoice".equals(paramHttpServletRequest.getSession()
						.getServletContext().getAttribute("LANGUAGE_MODE")))))
			return;
		Locale localLocale = null;
		if (_$6) {
			str2 = (String) paramHttpServletRequest.getSession().getAttribute(
					"LegendShopLanguage");
			if ((str1 != null) && (str1.equals(str2)))
				return;
			paramHttpServletRequest.getSession().setAttribute(
					"LegendShopLanguage", str1);
			arrayOfString = paramShopDetailView.getFrontEndLanguage()
					.split("_");
			localLocale = new Locale(arrayOfString[0], arrayOfString[1]);
		} else {
			str2 = CookieUtil.getCookieValue(paramHttpServletRequest,
					"LegendShopLanguage");
			if ((str1 != null) && (str1.equals(str2)))
				return;
			arrayOfString = paramShopDetailView.getFrontEndLanguage()
					.split("_");
			localLocale = new Locale(arrayOfString[0], arrayOfString[1]);
		}
		logger.debug("{} setLocalByShopDetail with langStyle {}",
				paramShopDetailView.getSiteName(), str1);
		localeRes.setLocale(paramHttpServletRequest, paramHttpServletResponse,
				localLocale);
	}

	public static String getCurrentShopName(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) {
		String str = (String) paramHttpServletRequest.getAttribute("shopName");
		if (AppUtils.isBlank(str)) {
			str = (String) paramHttpServletRequest.getSession().getAttribute(
					"shopName");
			if (AppUtils.isBlank(str)) {
				ShopDetailView localShopDetailView = getShopDetailView(
						paramHttpServletRequest, paramHttpServletResponse, str);
				if (localShopDetailView == null) {
					str = (String) PropertiesUtil.getObject(
							SysParameterEnum.DEFAULT_SHOP, String.class);
				} else{
					str = localShopDetailView.getUserName();
				}
				paramHttpServletRequest.getSession().setAttribute("shopName",
						str);
			}
			paramHttpServletRequest.setAttribute("shopName", str);
		}
		return str;
	}

	public static void setCurrentShopName(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString) {
		String str = (String) paramHttpServletRequest.getAttribute("shopName");
		if (AppUtils.isNotBlank(str)) {
			logger.debug("currentShopName  {}  have been set in this Thread",
					str);
			return;
		}
		ShopDetailView localShopDetailView = getShopDetailView(
				paramHttpServletRequest, paramHttpServletResponse, paramString);
		if (!(localShopDetailView.getStatus().equals(AttributeKeys.ONLINE))) {
			paramString = PropertiesUtil.getDefaultShopName();
			localShopDetailView = getShopDetailView(paramHttpServletRequest,
					paramHttpServletResponse, paramString);
		} else {
			paramString = localShopDetailView.getUserName();
		}
		_$2(paramHttpServletRequest, paramHttpServletResponse,
				localShopDetailView);
	}

	private static void _$1(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString) {
		paramHttpServletRequest.getSession().setAttribute("shopName",
				paramString);
	}

	public static String getFrontType(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String showName,
			PageDefinition paramPageDefinition) {
		String str = (String) paramHttpServletRequest.getAttribute(REQ_FRONT);
		if (str == null) {
			ShopDetailView localShopDetailView = getShopDetailView(
					paramHttpServletRequest,
					paramHttpServletResponse,
					getCurrentShopName(paramHttpServletRequest,
							paramHttpServletResponse));
			if ((localShopDetailView != null)
					&& (localShopDetailView.getFrontEndTemplet() != null)) {
				str = localShopDetailView.getFrontEndTemplet();
			}
			if (paramPageDefinition != null) {
				List localList = templateManager.getTemplateByPage(showName);
				if ((AppUtils.isNotBlank(localList))
						&& (!(localList.contains(str))))
					str = (String) localList.iterator().next();
			}
			if (str == null) {
				str = "default";
			}
			paramHttpServletRequest.setAttribute(REQ_FRONT, str);
		}
		return str;
	}

	public static String getBackEndType(
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse, String paramString,
			PageDefinition paramPageDefinition) {
		String str = (String) paramHttpServletRequest.getAttribute(REQ_END);
		if (str == null) {
			ShopDetailView localShopDetailView = getShopDetailView(
					paramHttpServletRequest,
					paramHttpServletResponse,
					getCurrentShopName(paramHttpServletRequest,
							paramHttpServletResponse));
			if ((localShopDetailView != null)
					&& (localShopDetailView.getBackEndTemplet() != null))
				str = localShopDetailView.getBackEndTemplet();
			if (paramPageDefinition != null) {
				List localList = templateManager.getTemplateByPage(paramString);
				if ((AppUtils.isNotBlank(localList))
						&& (!(localList.contains(str))))
					str = (String) localList.iterator().next();
			}
			if (str == null)
				str = "default";
			paramHttpServletRequest.setAttribute(REQ_END, str);
		}
		return str;
	}

	private static final void _$1() {
		if (PropertiesUtil.isSystemInstalled()) {
			logger.info("start to init ThreadLocalContext");
			if ((shopService == null)
					&& (ContextServiceLocator.getInstance()
							.containsBean("shopDetailService")))
				shopService = (ShopService) ContextServiceLocator.getInstance()
						.getBean("shopDetailService");
			if ((localeRes == null)
					&& (ContextServiceLocator.getInstance()
							.containsBean("localeResolver")))
				localeRes = (LocaleResolver) ContextServiceLocator
						.getInstance().getBean("localeResolver");
			if ((templateManager == null)
					&& (ContextServiceLocator.getInstance()
							.containsBean("templatePageManager")))
				templateManager = (TemplatePageManager) ContextServiceLocator
						.getInstance().getBean("templatePageManager");
		}
	}

	public static void clean() {
		if (current.get() != null)
			current.remove();
	}

	public static boolean requestStarted() {
		return (current.get() != null);
	}

	public static Locale getLocale() {
		HttpServletRequest localHttpServletRequest = (HttpServletRequest) current
				.get();
		if (localHttpServletRequest == null)
			return null;
		return localeRes.resolveLocale(localHttpServletRequest);
	}

	public static HttpServletRequest getRequest() {
		return ((HttpServletRequest) current.get());
	}

	public static void setRequest(HttpServletRequest paramHttpServletRequest) {
		current.set(paramHttpServletRequest);
	}

	static {
		_$1();
	}
}
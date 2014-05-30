package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.BasketService;
import com.legendshop.util.AppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/basket" })
public class BasketController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(BasketController.class);

	@Autowired
	private BasketService basketService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request, HttpServletResponse response) {
		String prodId = request.getParameter("prodId");
		if (AppUtils.isNotBlank(prodId))
			return getBasket(request, response,
					Long.valueOf(Long.parseLong(prodId)));

		throw new BusinessException("product id can not be null, prodId =  "
				+ prodId);
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request, HttpServletResponse response) {
		String prodId = request.getParameter("prodId");

		String userName = UserManager.getUserName(request);
		if (prodId == null)
			throw new BusinessException(
					"product id can not be null, userName =  " + userName);

		String count = request.getParameter("count");
		if (count == null)
			count = "1";

		HttpSession session = request.getSession();
		session.setAttribute("BASKET_HW_COUNT", count);
		String prodattr = request.getParameter("prodattr");
		request.getSession().setAttribute("BASKET_HW_ATTR", prodattr);
		if (userName == null) {
			String destView = "/basket/load/";
			request.setAttribute("returnUrl", PropertiesUtil.getDomainName()
					+ destView + prodId);
			return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);
		}
		String shopName = ThreadLocalContext.getCurrentShopName(request,
				response);
		if (prodId != null) {
			this.basketService.saveToCart(Long.valueOf(Long.parseLong(prodId)),
					shopName, prodattr, userName, Integer.valueOf(count));

			CommonServiceUtil.calBasketTotalCount(session, -1);
		}

		return PathResolver.getPath(request, response, TilesPage.PAGE_CASH);
	}

	private String getBasket(HttpServletRequest request,
			HttpServletResponse response, Long prodId) {
		String userName = UserManager.getUserName(request);
		if (userName == null) {
			String destView = "/basket/load/";
			request.setAttribute("returnUrl", PropertiesUtil.getDomainName()
					+ destView + prodId);
			return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);
		}
		if (prodId == null)
			throw new BusinessException(
					"product id can not be null, userName =  " + userName);

		String count = request.getParameter("count");
		if (count == null)
			count = "1";

		request.getSession().setAttribute("BASKET_HW_COUNT", count);
		String prodattr = request.getParameter("prodattr");
		request.getSession().setAttribute("BASKET_HW_ATTR", prodattr);

		return PathResolver.getPath(request, response, TilesPage.BUY);
	}

	@RequestMapping({ "/load/{prodId}" })
	public String load(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId) {
		return getBasket(request, response, prodId);
	}
}
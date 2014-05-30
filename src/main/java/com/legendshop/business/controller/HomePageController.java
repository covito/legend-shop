package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.locator.GenericServiceLocator;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.HomeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController extends BaseController {
	private final Logger logger = LoggerFactory
			.getLogger(HomePageController.class);

	@Autowired
	private GenericServiceLocator<HomeService> homeServiceLocator;

	@RequestMapping({ "/home" })
	public String home(HttpServletRequest request, HttpServletResponse response) {
		this.logger.debug("Start to call home...");
		try {
			String shopName = PropertiesUtil.getDefaultShopName();
			ThreadLocalContext.setCurrentShopName(request, response, shopName);
			ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(
					request, response, shopName);
			return ((HomeService) this.homeServiceLocator.getConcreteService(
					request, response, TilesPage.HOME)).getHome(request,
					response, shopDetail);
		} catch (Exception e) {
			this.logger.error("invoking index", e);
			if (!(PropertiesUtil.isSystemInstalled())) {
				redirectToInstallPage(request);
			}
			throw new BusinessException(e, "Visit home error", "998");
		}
	}

	public void setHomeServiceLocator(
			GenericServiceLocator<HomeService> homeServiceLocator) {
		this.homeServiceLocator = homeServiceLocator;
	}
}
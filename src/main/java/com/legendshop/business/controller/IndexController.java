package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.IndexService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private IndexService indexService;

	@RequestMapping({ "/index" })
	public String index(HttpServletRequest request, HttpServletResponse response) {
		this.log.debug("Index starting calling");
		try {
			String shopName = getShopName(request, response);
			if (PropertiesUtil.getDefaultShopName().equals(shopName))
				return PathResolver.getPath(request, response,
						FowardPage.HOME_QUERY);

			ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(
					request, response, shopName);
			this.indexService.getIndex(request, response, shopDetail);
			return PathResolver
					.getPath(request, response, TilesPage.INDEX_PAGE);
		} catch (Exception e) {
			this.log.error("invoking index", e);
			if (!(PropertiesUtil.isSystemInstalled())) {
				redirectToInstallPage(request);
			}
			throw new BusinessException("Index page error");
		}
	}

	@RequestMapping({ "/shop/{newShopName}" })
	public String shop(HttpServletRequest request,
			HttpServletResponse response, String curPageNO,
			@PathVariable String newShopName) {
		ThreadLocalContext.setCurrentShopName(request, response, newShopName);
		return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
	}

	private String getShopName(HttpServletRequest request,
			HttpServletResponse response) {
		return ThreadLocalContext.getCurrentShopName(request, response);
	}
}
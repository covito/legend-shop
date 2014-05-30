package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Logo;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.LogoService;
import com.legendshop.spi.service.ShopDetailService;
import com.legendshop.util.AppUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping({ "/admin/logo" })
public class LogoAdminController extends BaseController {
	private final Logger log = LoggerFactory
			.getLogger(LogoAdminController.class);

	@Autowired
	private LogoService logoService;

	@Autowired
	private ShopDetailService shopDetailService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Logo logo) {
		SimpleHqlQuery hql = new SimpleHqlQuery(curPageNO);

		hql.hasAllDataFunction(request, logo.getUserName());

		hql.fillPageSize(request);
		PageSupport ps = this.logoService.getLogo(hql);
		ps.savePage(request);
		request.setAttribute("logo", logo);
		return PathResolver.getPath(request, response, BackPage.LOGO_LIST_PAGE);
	}

	@RequestMapping({ "/save" })
	public String save(MultipartHttpServletRequest request,
			HttpServletResponse response, Logo logo) {
		String logoPic = null;
		String name = UserManager.getUserName(request.getSession());
		String subPath = name + "/logo/";
		String userId = logo.getId();
		ShopDetail shopDetail = null;
		if (AppUtils.isBlank(userId)) {
			userId = UserManager.getUserId(request);
			shopDetail = this.shopDetailService.getShopDetailByUserId(userId);
			if (shopDetail != null) {
				String originlogoPic = shopDetail.getLogoPic();
				if (logo.getFile().getSize() > -4648542910910824448L) {
					logoPic = FileProcessor.uploadFileAndCallback(
							logo.getFile(), subPath, "");
					shopDetail.setLogoPic(logoPic);
					this.logoService.updateLogo(shopDetail);
					if (AppUtils.isNotBlank(originlogoPic)) {
						String url = RealPathUtil.getBigPicRealPath() + "/"
								+ originlogoPic;
						FileProcessor.deleteFile(url);
					}
				}
			}
			throw new NotFoundException("ShopDetail is empty");
		}

		shopDetail = this.shopDetailService.getShopDetailByUserId(userId);
		if (shopDetail == null)
			throw new NotFoundException("ShopDetail is empty");

		if ((!(CommonServiceUtil.haveViewAllDataFunction(request)))
				&& (!(name.equals(shopDetail.getUserName())))) {
			throw new PermissionException(name
					+ " can't edit Logo does not own to you!");
		}

		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.LOGO_LIST_QUERY);
	}

	@RequestMapping({ "/delete/{userId}" })
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String userId) {
		ShopDetail shopDetail = this.shopDetailService
				.getShopDetailByUserId(userId);
		if (shopDetail == null) {
			throw new NotFoundException("ShopDetail is empty");
		}

		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()),
				shopDetail.getUserName());
		if (result != null) {
			return result;
		}

		this.log.info("{}, delete Logo Url {}", shopDetail.getUserName(),
				shopDetail.getLogoPic());
		this.logoService.deleteLogo(shopDetail);
		if (shopDetail.getLogoPic() != null) {
			String url = RealPathUtil.getBigPicRealPath() + "/"
					+ shopDetail.getLogoPic();
			FileProcessor.deleteFile(url);
		}

		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, response,
				FowardPage.LOGO_LIST_QUERY);
	}

	@RequestMapping({ "/load/{userId}" })
	public String load(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String userId) {
		ShopDetail shopDetail = this.shopDetailService
				.getShopDetailByUserId(userId);
		if (shopDetail == null)
			throw new NotFoundException("ShopDetail is empty");

		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()),
				shopDetail.getUserName());
		if (result != null)
			return result;

		request.setAttribute("bean", shopDetail);
		return PathResolver.getPath(request, response, BackPage.LOGO_EDIT_PAGE);
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, BackPage.LOGO_EDIT_PAGE);
	}
}
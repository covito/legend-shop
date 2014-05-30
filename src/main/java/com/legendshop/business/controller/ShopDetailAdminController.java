package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.page.TempletManager;
import com.legendshop.model.StatusKeyValueEntity;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.model.template.Templet;
import com.legendshop.model.template.TempletEntity;
import com.legendshop.spi.constants.ShopTypeEnum;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ShopDetailService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.ContextServiceLocator;
import com.legendshop.util.JSONUtil;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.ServiceLocatorIF;
import com.legendshop.util.constant.ShopStatusEnum;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({ "/admin/shopDetail" })
public class ShopDetailAdminController extends BaseController {

	@Autowired
	private ShopDetailService shopDetailService;

	@Autowired
	private TempletManager templetManager;

	@RequestMapping({ "query" })
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO,
			ShopDetail shopDetail) throws Exception {
		if (FoundationUtil.haveViewAllDataFunction(request)) {
			CriteriaQuery cq = new CriteriaQuery(ShopDetail.class, curPageNO,
					"javascript:pager");
			hasAllDataFunction(cq, request, "userName",
					StringUtils.trim(shopDetail.getUserName()));

			if (!(CommonServiceUtil.isDataForExport(cq, request)))
				cq.setPageSize(((Integer) PropertiesUtil.getObject(
						SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

			if (!(CommonServiceUtil.isDataSortByExternal(cq, request)))
				cq.addOrder("desc", "modifyDate");

			cq.eq("type", shopDetail.getType());
			cq.eq("status", shopDetail.getStatus());

			PageSupport ps = this.shopDetailService.getShopDetail(cq);
			ps.savePage(request);
			request.setAttribute("shopDetail", shopDetail);
			return PathResolver.getPath(request, response,
					BackPage.SHOP_DETAIL_LIST_PAGE);
		}

		String userName = UserManager.getUserName(request);
		Long shopId = this.shopDetailService.getShopIdByName(userName);
		return PathResolver.getPath(request, response,
				FowardPage.SHOP_DETAIL_LOAD) + shopId;
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request,
			HttpServletResponse response, ShopDetail shopDetail) {
		if ((shopDetail != null)
				&& (!(AppUtils.isBlank(shopDetail.getShopId())))) {
			return update(request, response, shopDetail);
		}

		String userName = shopDetail.getUserName();

		String shopPic = null;
		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()), userName);
		if (result != null)
			return result;

		if (AppUtils.isBlank(userName))
			throw new NotFoundException("user name can not be empty");

		UserDetail userDetail = this.shopDetailService
				.getShopDetailByName(userName);
		if (AppUtils.isBlank(userDetail))
			throw new NotFoundException("userDetail can not beempty");

		try {
			shopDetail.setUserId(userDetail.getUserId());
			Date date = new Date();
			shopDetail.setModifyDate(date);
			shopDetail.setRecDate(date);
			shopDetail.setVisitTimes(Integer.valueOf(0));
			shopDetail.setOffProductNum(Integer.valueOf(0));
			shopDetail.setCommNum(Integer.valueOf(0));
			shopDetail.setProductNum(Integer.valueOf(0));
			shopDetail.setType(ShopTypeEnum.BUSINESS.value());
			shopDetail.setStatus(ShopStatusEnum.ONLINE.value());
			shopDetail.setCapital(Double.valueOf(0D));
			shopDetail.setCredit(Integer.valueOf(0));
			String subPath = userName + "/shop/";
			if (shopDetail.getFile().getSize() > -4648542240895926272L) {
				shopPic = FileProcessor.uploadFileAndCallback(
						shopDetail.getFile(), subPath, "");
				shopDetail.setShopPic(shopPic);
			}
			this.shopDetailService.save(shopDetail);
			saveMessage(request, ResourceBundleHelper.getSucessfulString());
		} catch (Exception e) {
			if (shopPic != null)
				FileProcessor.deleteFile(shopPic);

			throw new BusinessException(e, "Error happened when save shop",
					"601");
		}

		return PathResolver.getPath(request, response,
				FowardPage.SHOP_DETAIL_LIST_QUERY);
	}

	@RequestMapping({ "/delete/{id}" })
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		ShopDetail shopDetail = this.shopDetailService.getShopDetailById(id);
		if (shopDetail != null) {
			this.shopDetailService.delete(shopDetail);
		}

		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, response,
				FowardPage.SHOP_DETAIL_LIST_QUERY);
	}

	@RequestMapping({ "/load/{id}" })
	public String load(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		ShopDetail shopDetail = this.shopDetailService.getShopDetailById(id);
		if (shopDetail != null) {
			String result = checkPrivilege(request,
					UserManager.getUserName(request), shopDetail.getUserName());
			if (result != null)
				return result;

		}

		request.setAttribute("shopDetail", shopDetail);
		request.setAttribute("id", request.getParameter("userId"));
		request.setAttribute("templetEntity",
				JSONUtil.getJson(getTempletsEntity(shopDetail)));
		return PathResolver.getPath(request, response,
				BackPage.SHOP_DETAIL_EDIT_PAGE);
	}

	public TempletEntity getTempletsEntity(ShopDetail shopDetail) {
		TempletEntity templet = new TempletEntity();
		templet.setFrontEndTempletList(CommonServiceUtil.convertKeyValueEntity(
				this.templetManager.getFrontEndTempletList(),
				shopDetail.getFrontEndTemplet()));
		templet.setBackEndTempletList(CommonServiceUtil.convertKeyValueEntity(
				this.templetManager.getBackEndTempletList(),
				shopDetail.getBackEndTemplet()));

		Map frontEndTempletMap = this.templetManager.getFrontEndtTempletMap();
		for (Iterator localIterator = frontEndTempletMap.keySet().iterator(); localIterator
				.hasNext();) {
			String templetId = (String) localIterator.next();
			Templet newTemplet = getFrontEndTemplet(templetId);
			convertTemplet(templetId, newTemplet,
					shopDetail.getFrontEndLanguage(),
					shopDetail.getFrontEndStyle());
			templet.addFrontEndTempletMap(newTemplet);
		}

		Map backEndTempletMap = this.templetManager.getBackEndTempletMap();
		for (Iterator localIterator = backEndTempletMap.keySet().iterator(); localIterator
				.hasNext();) {
			String templetId = (String) localIterator.next();
			Templet newTemplet = getBackEndTemplet(templetId);
			convertTemplet(templetId, newTemplet,
					shopDetail.getBackEndLanguage(),
					shopDetail.getBackEndStyle());
			templet.addBackEndTempletMap(newTemplet);
		}
		return templet;
	}

	private void convertTemplet(String templetId, Templet newTemplet,
			String selectLanague, String selectStyle) {
		newTemplet.setId(templetId);
		newTemplet.setLanguages(CommonServiceUtil.convertKeyValueEntity(
				newTemplet.getLanguages(), selectLanague));
		newTemplet.setStyles(CommonServiceUtil.convertKeyValueEntity(
				newTemplet.getStyles(), selectStyle));
	}

	private Templet getFrontEndTemplet(String templetId) {
		return ((Templet) ContextServiceLocator.getInstance().getBean(
				this.templetManager.getFrontEndTempletById(templetId)));
	}

	private Templet getBackEndTemplet(String templetId) {
		return ((Templet) ContextServiceLocator.getInstance().getBean(
				this.templetManager.getBackEndTempletById(templetId)));
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("templetEntity",
				JSONUtil.getJson(getTempletsEntity(new ShopDetail())));
		return PathResolver.getPath(request, response,
				BackPage.SHOP_DETAIL_EDIT_PAGE);
	}

	private String update(HttpServletRequest request,
			HttpServletResponse response, ShopDetail shopDetail) {
		ShopDetail shop = this.shopDetailService.getShopDetailById(shopDetail
				.getShopId());
		if (shop == null)
			throw new NotFoundException("shop can not be null, getShopId = "
					+ shopDetail.getShopId());

		String originShopPic = shop.getShopPic();
		String shopPic = null;
		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()),
				shopDetail.getUserName());
		if (result != null)
			return result;
		try {
			String subPath = shopDetail.getUserName() + "/shop/";
			SafeHtml safeHtml = new SafeHtml();
			shop.setSiteName(safeHtml.makeSafe(shopDetail.getSiteName()));
			shop.setShopAddr(safeHtml.makeSafe(shopDetail.getShopAddr()));
			shop.setBankCard(safeHtml.makeSafe(shopDetail.getBankCard()));
			shop.setPayee(safeHtml.makeSafe(shopDetail.getPayee()));
			shop.setCode(safeHtml.makeSafe(shopDetail.getCode()));
			shop.setPostAddr(safeHtml.makeSafe(shopDetail.getPostAddr()));
			shop.setRecipient(safeHtml.makeSafe(shopDetail.getRecipient()));
			shop.setBriefDesc(safeHtml.makeSafe(shopDetail.getBriefDesc()));
			shop.setDetailDesc(safeHtml.makeSafe(shopDetail.getDetailDesc()));
			shop.setModifyDate(new Date());
			shop.setProvinceid(shopDetail.getProvinceid());
			shop.setCityid(shopDetail.getCityid());
			shop.setAreaid(shopDetail.getAreaid());

			shop.setFrontEndTemplet(shopDetail.getFrontEndTemplet());
			shop.setBackEndTemplet(shopDetail.getBackEndTemplet());

			Templet frontEndTemplet = getFrontEndTemplet(shopDetail
					.getFrontEndTemplet());
			Templet backEndTemplet = getBackEndTemplet(shopDetail
					.getBackEndTemplet());
			shop.setFrontEndStyle(checkTemplet(frontEndTemplet.getStyles(),
					shopDetail.getFrontEndStyle()));
			shop.setBackEndStyle(checkTemplet(backEndTemplet.getStyles(),
					shopDetail.getBackEndStyle()));
			shop.setFrontEndLanguage(checkTemplet(
					frontEndTemplet.getLanguages(),
					shopDetail.getFrontEndLanguage()));
			shop.setBackEndLanguage(checkTemplet(
					frontEndTemplet.getLanguages(),
					shopDetail.getBackEndLanguage()));

			if ((((CommonServiceUtil.haveViewAllDataFunction(request)) || (!(ShopStatusEnum.AUDITING
					.value().equals(shop.getStatus())))))
					&& (shopDetail.getStatus() != null)) {
				shop.setStatus(shopDetail.getStatus());
			}

			if (shopDetail.getFile().getSize() > -4648542240895926272L) {
				shopPic = FileProcessor.uploadFileAndCallback(
						shopDetail.getFile(), subPath, "");
				shop.setShopPic(shopPic);
			}
			if (FoundationUtil.haveViewAllDataFunction(request)) {
				shop.setDomainName(shopDetail.getDomainName());
				shop.setIcpInfo(shopDetail.getIcpInfo());
			}

			this.shopDetailService.update(shop);
		} catch (Exception e) {
			if (shopPic != null)
				FileProcessor.deleteFile(shopPic);

			throw new BusinessException(e, "Error happened when update shop",
					"602");
		} finally {
			if ((shopPic != null) && (originShopPic != null))
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/"
						+ originShopPic);
		}

		return PathResolver.getPath(request, response,
				FowardPage.SHOP_DETAIL_LIST_QUERY);
	}

	private String checkTemplet(List<StatusKeyValueEntity> entityList,
			String keyValue) {
		boolean matched = false;
		
		//FIXME modify
		if (entityList != null){
			for (Iterator localIterator = entityList.iterator(); localIterator
					.hasNext();) {
				StatusKeyValueEntity entity = (StatusKeyValueEntity) localIterator
						.next();
				if (entity.getKey().equals(keyValue)) {
					matched = true;
					break;
				}
			}
		}
		if (matched) {
			return keyValue;
		}

		return ((StatusKeyValueEntity) entityList.get(0)).getKey();
	}

	@RequestMapping({ "audit" })
	@ResponseBody
	public String auditShop(HttpServletRequest request, String userId,
			Long shopId, Integer status) {
		if ((userId == null) || (shopId == null)) {
			return "fail";
		}

		ShopDetail shopDetail = this.shopDetailService
				.getShopDetailByShopId(shopId);
		if (shopDetail != null) {
			String loginName = UserManager.getUserName();
			checkPrivilege(request, loginName, shopDetail.getUserName());
			boolean result = this.shopDetailService.updateShop(userId,
					shopDetail, status);
			if (result)
				return null;

			return "fail";
		}

		return "fail";
	}
}
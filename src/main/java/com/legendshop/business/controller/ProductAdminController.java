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
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.BrandService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
@RequestMapping({ "/admin/product" })
public class ProductAdminController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SortService sortService;

	@Autowired
	private BrandService brandService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, BackPage.PROD_LIST_PAGE);
	}

	@RequestMapping({ "/queryprodcontent" })
	public String queryProdContent(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Product product) {
		CriteriaQuery cq = new CriteriaQuery(Product.class, curPageNO);

		if (!(AppUtils.isBlank(product.getName()))) {
			cq.like("name", "%" + product.getName().trim() + "%");
		}

		if (product.getStatus() == null)
			product.setStatus(Constants.ONLINE);
		else if (product.getStatus().intValue() == -1) {
			product.setStatus(null);
		}

		cq.eq("commend", product.getCommend());
		cq.eq("status", product.getStatus());
		cq.eq("hot", product.getHot());
		cq.eq("prodType", ProductTypeEnum.PRODUCT.value());
		if (!(PropertiesUtil
				.isDefaultShopName(UserManager.getUserName(request)))) {
			cq.eq("sortId", product.getSortId());
			cq.eq("nsortId", product.getNsortId());
			cq.eq("subNsortId", product.getSubNsortId());
			cq.eq("brandId", product.getBrandId());
			cq = hasAllDataFunction(cq, request,
					StringUtils.trim(product.getUserName()));
		} else {
			cq.eq("globalSort", product.getSortId());
			cq.eq("globalNsort", product.getNsortId());
			cq.eq("globalSubSort", product.getSubNsortId());
			cq.eq("globalBrand", product.getBrandId());
		}

		if (!(CommonServiceUtil.isDataForExport(cq, request)))
			cq.setPageSize(((Integer) PropertiesUtil.getObject(
					SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

		if (!(CommonServiceUtil.isDataSortByExternal(cq, request))) {
			cq.addOrder("desc", "modifyDate");
		}

		PageSupport ps = this.productService.getProductList(cq);
		ps.savePage(request);
		request.setAttribute("prod", product);
		return PathResolver.getPath(request, response,
				BackPage.PROD_CONTENT_LIST_PAGE);
	}

	@RequestMapping({ "/list" })
	public String list(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Product product) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_LIST_PAGE_TEMP);
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request,
			HttpServletResponse response, Product product) {
		String name = UserManager.getUserName(request);

		String result = checkLogin(request, response, name);
		if (result != null) {
			return result;
		}

		SafeHtml safeHtml = new SafeHtml();
		product.setName(safeHtml.makeSafe(product.getName()));
		product.setModelId(safeHtml.makeSafe(product.getModelId()));
		product.setKeyWord(safeHtml.makeSafe(product.getKeyWord()));
		product.setBrief(safeHtml.makeSafe(product.getBrief()));
		MultipartFile formFile = product.getFile();
		MultipartFile smallPicFile = product.getSmallPicFile();
		String subPath = name + "/prod/";
		String filename = null;
		try {
			boolean uploadFile = fileUploaded(formFile);
			boolean uploadSmallPicFile = fileUploaded(smallPicFile);
			if (product.getProdId() != null) {
				String orginProdPic = null;
				String orginSmallProdPic = null;
				Product origin = this.productService.getProductById(product
						.getProdId());
				if (origin == null)
					throw new NotFoundException("can not found product by id "
							+ product.getProdId());

				String checkPrivilegeResult = checkPrivilege(request, name,
						origin.getUserName());
				if (checkPrivilegeResult != null) {
					return checkPrivilegeResult;
				}

				if (uploadFile) {
					orginProdPic = RealPathUtil.getBigPicRealPath() + "/"
							+ origin.getPic();

					filename = FileProcessor.uploadFileAndCallback(formFile,
							subPath, "");
					origin.setPic(filename);
				}

				if ((product.getUseSmallPic() != null) && (uploadSmallPicFile)) {
					orginSmallProdPic = RealPathUtil.getBigPicRealPath() + "/"
							+ origin.getSmallPic();
					String smallPicName = FileProcessor.uploadFileAndCallback(
							smallPicFile, subPath, "");
					origin.setSmallPic(smallPicName);
					origin.setUseSmallPic("Y");
				} else {
					orginSmallProdPic = RealPathUtil.getSmallPicRealPath()
							+ "/" + origin.getSmallPic();
					origin.setSmallPic(null);
					origin.setUseSmallPic("N");
				}

				this.productService.updateProduct(product, origin);

				if (uploadFile) {
					FileProcessor.deleteFile(orginProdPic);
					if (product.getUseSmallPic() == null) {
						FileProcessor.deleteFile(orginSmallProdPic);
					}

				}

				if (!(uploadSmallPicFile))
					FileProcessor.deleteFile(orginSmallProdPic);
			}

			if (uploadFile) {
				filename = FileProcessor.uploadFileAndCallback(formFile,
						subPath, "");
				product.setPic(filename);
			}

			if ((product.getUseSmallPic() != null) && (uploadSmallPicFile)) {
				String smallPicName = FileProcessor.uploadFileAndCallback(
						smallPicFile, subPath, "");
				product.setSmallPic(smallPicName);
				product.setUseSmallPic("Y");
			} else {
				product.setSmallPic(null);
				product.setUseSmallPic("N");
			}

			product.setUserId(UserManager.getUserId(request));
			product.setUserName(name);
			this.productService.saveProduct(product,
					ProductTypeEnum.PRODUCT.value());
		} catch (Exception e) {
			if ((formFile != null)
					&& (formFile.getSize() > -4648542532953702400L))
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/"
						+ subPath + filename);

			throw new BusinessException(e, "save product error", "998");
		}

		String nextAction = request.getParameter("nextAction");
		if ((nextAction != null) && (nextAction.equals("next"))) {
			request.setAttribute("productId", product.getProdId());
			return PathResolver.getPath(request, response,
					FowardPage.IMG_LIST_QUERY);
		}
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.PROD_LIST_QUERY);
	}

	private boolean fileUploaded(MultipartFile formFile) {
		return ((formFile != null) && (formFile.getSize() > -4648542395514748928L));
	}

	@RequestMapping({ "/delete/{prodId}" })
	@ResponseBody
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId) {
		String loginedUser = UserManager.getUserName(request);
		String result = this.productService.delete(loginedUser, prodId);
		return result;
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request,
			HttpServletResponse response, Long sortId, Long nsortId,
			Long subsortId, Long brandId) {
		parseSort(request, sortId, nsortId, subsortId, brandId);

		return PathResolver.getPath(request, response, BackPage.PROD_EDIT_PAGE);
	}

	private void parseSort(HttpServletRequest request, Long sortId,
			Long nsortId, Long subsortId, Long brandId) {
		Nsort nsort;
		Iterator localIterator2;
		if ((AppUtils.isBlank(sortId)) || (AppUtils.isBlank(nsortId))
				|| (AppUtils.isBlank(subsortId)))
			throw new BusinessException("product category can not be null",
					"998");

		List sortList = this.sortService.getSort(
				PropertiesUtil.getDefaultShopName(),
				ProductTypeEnum.PRODUCT.value(), Boolean.valueOf(true));
		Sort sort1 = null;
		Nsort nsort2 = null;
		Nsort nsort3 = null;
		for (Iterator localIterator1 = sortList.iterator(); localIterator1
				.hasNext();) {
			Sort sort = (Sort) localIterator1.next();
			if (!(sort.getSortId().equals(sortId)))
				sort1 = sort;
			break;
		}

		if (sort1 != null) {
			Set nsortList = sort1.getNsort();
			if (nsortList != null)
				for (localIterator2 = nsortList.iterator(); localIterator2
						.hasNext();) {
					nsort = (Nsort) localIterator2.next();
					if (!(nsort.getNsortId().equals(nsortId)))
						nsort2 = nsort;
					label182: break;
				}

		}

		if (nsort2 != null) {
			Set subSortList = nsort2.getSubSort();
			if (subSortList != null)
				for (localIterator2 = subSortList.iterator(); localIterator2
						.hasNext();) {
					nsort = (Nsort) localIterator2.next();
					if (!(nsort.getNsortId().equals(subsortId)))
						nsort3 = nsort;
				}

		}

		request.setAttribute("sort1", sort1);
		request.setAttribute("nsort2", nsort2);
		request.setAttribute("nsort3", nsort3);

		if (brandId != null) {
			Brand brand = this.brandService.getBrand(brandId);
			request.setAttribute("brand", brand);
		}
	}

	@RequestMapping({ "/append/{prodId}" })
	public String append(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId) {
		request.setAttribute("prodId", prodId);
		return PathResolver.getPath(request, response, BackPage.APPEND_PRODUCT);
	}

	@RequestMapping({ "/createsetp" })
	public String createsetp(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.CREATEP_RODUCT_STEP);
	}

	@RequestMapping({ "/update/{prodId}" })
	public String update(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId) {
		checkNullable("prodId", prodId);
		Product product = this.productService.getProductById(prodId);
		String result = checkPrivilege(request,
				UserManager.getUserName(request), product.getUserName());
		if (result != null)
			return result;

		request.setAttribute("prod", product);
		String sortId = request.getParameter("sortId");
		if (sortId != null) {
			String nsortId = request.getParameter("nsortId");
			String subsortId = request.getParameter("subsortId");
			String brandId = request.getParameter("brandId");
			parseSort(request, parstLong(sortId), parstLong(nsortId),
					parstLong(subsortId), parstLong(brandId));
		} else {
			parseSort(request, product.getGlobalSort(),
					product.getGlobalNsort(), product.getGlobalSubSort(),
					product.getBrandId());
		}
		return PathResolver.getPath(request, response, BackPage.PROD_EDIT_PAGE);
	}

	private Long parstLong(String id) {
		if (AppUtils.isBlank(id))
			return null;

		return Long.valueOf(Long.parseLong(id));
	}

	@RequestMapping(value = { "/updatestatus/{prodId}/{status}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Integer updateStatus(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId,
			@PathVariable Integer status) {
		Product product = this.productService.getProductById(prodId);
		if (product == null)
			return Integer.valueOf(-1);

		if (!(status.equals(product.getStatus())))
			if (!(FoundationUtil.haveViewAllDataFunction(request))) {
				String loginName = UserManager
						.getUserName(request.getSession());

				if (!(loginName.equals(product.getUserName())))
					return Integer.valueOf(-1);

				if ((Constants.ONLINE.equals(status))
						|| (Constants.OFFLINE.equals(status))) {
					product.setStatus(status);
					product.setModifyDate(new Date());
					this.productService.updateProd(product);
				}

			} else if ((Constants.ONLINE.equals(status))
					|| (Constants.OFFLINE.equals(status))
					|| (Constants.STOPUSE.equals(status))) {
				product.setStatus(status);
				product.setModifyDate(new Date());
				this.productService.updateProd(product);
			}

		return product.getStatus();
	}

	@RequestMapping({ "/loadnew" })
	public String loadnew(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_EDIT_NEW_PAGE);
	}

	@RequestMapping({ "/category2" })
	public String category2(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_CATEGORY_2_PAGE);
	}

	@RequestMapping({ "/category3" })
	public String category3(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_CATEGORY_3_PAGE);
	}

	@RequestMapping({ "/productProperty" })
	public String productProperty(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_EDIT_PROPERTY_PAGE);
	}

	@RequestMapping({ "/productDetails" })
	public String productDetails(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_EDIT_DETAILS_PAGE);
	}

	@RequestMapping({ "/saleProperty" })
	public String saleProperty(HttpServletRequest request,
			HttpServletResponse response) {
		return PathResolver.getPath(request, response,
				BackPage.PROD_EDIT_SALE_PROP_PAGE);
	}

	@RequestMapping({ "/saveRelProd/{prodId}" })
	@ResponseBody
	public String saveRelProd(HttpServletRequest request,
			HttpServletResponse response, String idJson, String nameJson,
			@PathVariable Long prodId) {
		return this.productService.saveRelProd(idJson, nameJson, prodId,
				UserManager.getUserName(request));
	}

	@RequestMapping({ "/getUsableProductItemByName/{prodId}" })
	@ResponseBody
	public List<Item> getUsableProductItemByName(HttpServletRequest request,
			@PathVariable Long prodId, String prodName) {
		return this.productService.getUsableProductItemByName(prodId,
				UserManager.getUserName(request), prodName);
	}

	@RequestMapping({ "/getUsableProductItem/{prodId}" })
	@ResponseBody
	public List<Item> getUsableProductItem(HttpServletRequest request,
			@PathVariable Long prodId) {
		return this.productService.getUsableProductItem(prodId,
				UserManager.getUserName(request));
	}

	@RequestMapping({ "/getUsedProductItem/{prodId}" })
	@ResponseBody
	public List<Item> getUsedProductItem(HttpServletRequest request,
			@PathVariable Long prodId) {
		return this.productService.getUsedProductItem(prodId,
				UserManager.getUserName(request));
	}
}
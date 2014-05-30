package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.controller.AbstractSortController;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NsortService;
import com.legendshop.spi.service.SortService;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/admin/sort" })
public class SortAdminController extends AbstractSortController implements
		AdminController<Sort, Long> {

	@Autowired
	private NsortService nsortService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Sort sort) {
		sort.setSortType(ProductTypeEnum.PRODUCT.value());
		PageSupport ps = querySort(request, response, curPageNO, sort);
		ps.savePage(request);
		request.setAttribute("sort", sort);
		return PathResolver.getPath(request, response, BackPage.SORT_LIST_PAGE);
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request,
			HttpServletResponse response, Sort entity) {
		entity.setSortType(ProductTypeEnum.PRODUCT.value());
		parseSort(request, response, entity);
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.SORT_LIST_QUERY);
	}

	@RequestMapping({ "/delete/{id}" })
	@ResponseBody
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		deleteSort(request, response, id);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, response,
				FowardPage.SORT_LIST_QUERY);
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, BackPage.SORT_EDIT_PAGE);
	}

	@RequestMapping({ "/update/{id}" })
	public String update(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		checkNullable("sortId", id);
		Sort sort = this.sortService.getSortAndBrand(id);
		String result = checkPrivilege(request,
				UserManager.getUserName(request), sort.getUserName());
		if (result != null)
			return result;

		request.setAttribute("sort", sort);
		return PathResolver.getPath(request, response, BackPage.SORT_EDIT_PAGE);
	}

	@RequestMapping(value = { "/updatestatus/{sortId}/{status}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Integer updateStatus(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long sortId,
			@PathVariable Integer status) {
		Sort sort = this.sortService.getSortById(sortId);
		if (sort == null)
			return Integer.valueOf(-1);

		if (!(status.equals(sort.getStatus())))
			if (!(FoundationUtil.haveViewAllDataFunction(request))) {
				String loginName = UserManager
						.getUserName(request.getSession());

				if (!(loginName.equals(sort.getUserName())))
					return Integer.valueOf(-1);

				if ((Constants.ONLINE.equals(status))
						|| (Constants.OFFLINE.equals(status))) {
					sort.setStatus(status);
					this.sortService.updateSort(sort);
				}

			} else if ((Constants.ONLINE.equals(status))
					|| (Constants.OFFLINE.equals(status))
					|| (Constants.STOPUSE.equals(status))) {
				sort.setStatus(status);
				this.sortService.updateSort(sort);
			}

		return sort.getStatus();
	}

	@RequestMapping({ "/publish" })
	public String publish(HttpServletRequest request,
			HttpServletResponse response) {
		List sortList = this.sortService.getSort(
				PropertiesUtil.getDefaultShopName(), Boolean.valueOf(true));
		request.setAttribute("sortList", sortList);
		return PathResolver.getPath(request, response,
				BackPage.PROD_PUBLISH_PAGE);
	}

	@RequestMapping({ "/publish/{prodId}" })
	public String publishProduct(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long prodId) {
		List sortList = this.sortService.getSort(
				PropertiesUtil.getDefaultShopName(), Boolean.valueOf(true));
		request.setAttribute("sortList", sortList);
		request.setAttribute("prodId", prodId);
		return PathResolver.getPath(request, response,
				BackPage.PROD_PUBLISH_PAGE);
	}

	@RequestMapping({ "/loadsort" })
	public String loadsort(HttpServletRequest request,
			HttpServletResponse response, String sortName) {
		List sortList = this.sortService.getSort(
				PropertiesUtil.getDefaultShopName(),
				ProductTypeEnum.PRODUCT.value(), sortName);
		request.setAttribute("sortList", sortList);
		return PathResolver.getPath(request, response,
				BackPage.PUBLISH_SORT_QUERY_PAGE);
	}

	@RequestMapping({ "/loadnsort" })
	public String loadnsort(HttpServletRequest request,
			HttpServletResponse response, Long sortId, String nsortName) {
		List nsortList = this.sortService.getNsortBySortId(sortId, nsortName);
		request.setAttribute("nSort2List", nsortList);
		return PathResolver.getPath(request, response,
				BackPage.PUBLISH_NSORT_QUERY_PAGE);
	}

	@RequestMapping({ "/loadsubsort" })
	public String loadsubsort(HttpServletRequest request,
			HttpServletResponse response, Long nsortId, String nsortName) {
		List nsortList = this.sortService.getSubNsortBySortId(nsortId,
				nsortName);
		request.setAttribute("nSort3List", nsortList);
		return PathResolver.getPath(request, response,
				BackPage.PUBLISH_SUBSORT_QUERY_PAGE);
	}

	@RequestMapping({ "/publish2/{sortId}" })
	public String publish2(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long sortId) {
		List sortList = this.sortService.getSort(
				PropertiesUtil.getDefaultShopName(), Boolean.valueOf(true));
		Set nSort2List = null;
		if (sortList != null)
			for (Iterator localIterator = sortList.iterator(); localIterator
					.hasNext();) {
				Sort sort = (Sort) localIterator.next();
				if (!(sort.getSortId().equals(sortId))){
					
				}
				label69: nSort2List = sort.getNsort();
			}

		request.setAttribute("nSort2List", nSort2List);
		return PathResolver.getPath(request, response,
				BackPage.PROD_PUBLISH2_PAGE);
	}

	@RequestMapping({ "/publish3/{nsortId}" })
	public String publish3(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long nsortId) {
		request.setAttribute("nSort3List",
				this.nsortService.getNSort3ByNSort2(nsortId));
		return PathResolver.getPath(request, response,
				BackPage.PROD_PUBLISH3_PAGE);
	}
}
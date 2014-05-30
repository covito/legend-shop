package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Pub;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.PubService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.TimerUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/admin/pub" })
public class PubAdminController extends BaseController {
	private final Logger log = LoggerFactory
			.getLogger(PubAdminController.class);

	@Autowired
	private PubService pubService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Pub pub) {
		CriteriaQuery cq = new CriteriaQuery(Pub.class, curPageNO,
				"javascript:pager");
		cq.setPageSize(((Integer) PropertiesUtil.getObject(
				SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
		cq = hasAllDataFunction(cq, request,
				StringUtils.trim(pub.getUserName()));
		cq.addOrder("desc", "recDate");

		PageSupport ps = this.pubService.getPubList(cq);
		ps.setResultList(checkValidPub(ps.getResultList()));
		ps.savePage(request);
		request.setAttribute("bean", pub);
		return PathResolver.getPath(request, response, BackPage.PUB_LIST_PAGE);
	}

	private List<Pub> checkValidPub(List<Pub> list) {
		if (AppUtils.isBlank(list))
			return null;

		Date today = TimerUtil.getNowDateShort();
		for (Iterator localIterator = list.iterator(); localIterator.hasNext();) {
			Pub pub = (Pub) localIterator.next();
			if ((((pub.getStartDate() == null) || (!(pub.getStartDate()
					.after(today)))))
					&& (((pub.getEndDate() == null) || (!(pub.getEndDate()
							.before(today)))))) {
				pub.setValid(false);
			}
		}

		return list;
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request,
			HttpServletResponse response, Pub pub) {
		String name = UserManager.getUserName(request.getSession());
		pub.setRecDate(new Date());
		pub.setUserId(UserManager.getUserId(request.getSession()));
		pub.setUserName(name);
		if ((AppUtils.isNotBlank(pub.getStartDate()))
				&& (AppUtils.isNotBlank(pub.getEndDate()))
				&& (pub.getStartDate().after(pub.getEndDate()))) {
			throw new ConflictException(
					"Date range error, Start date can not late then end date");
		}

		this.pubService.save(pub, name,
				CommonServiceUtil.haveViewAllDataFunction(request));
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.PUB_LIST_QUERY);
	}

	@RequestMapping({ "/delete/{id}" })
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		Pub pub = this.pubService.getPubById(id);
		if (pub == null)
			throw new NotFoundException("Pub does not exists");

		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()),
				pub.getUserName());
		if (result != null)
			return result;

		this.log.info("{} delete Pub Title {}", pub.getUserName(),
				pub.getTitle());
		this.pubService.deletePub(pub);
		saveMessage(request, ResourceBundleHelper.getDeleteString());
		return PathResolver.getPath(request, response,
				FowardPage.PUB_LIST_QUERY);
	}

	@RequestMapping({ "/load/{id}" })
	public String load(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		Pub pub = this.pubService.getPubById(id);
		String result = checkPrivilege(request,
				UserManager.getUserName(request.getSession()),
				pub.getUserName());
		if (result != null)
			return result;

		request.setAttribute("bean", pub);
		return PathResolver.getPath(request, response, BackPage.PUB_EDIT_PAGE);
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, BackPage.PUB_EDIT_PAGE);
	}

	@RequestMapping(value = { "/updatestatus/{pubId}/{status}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Integer updateStatus(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long pubId,
			@PathVariable Integer status) {
		Pub pub = this.pubService.getPubById(pubId);
		if (pub == null)
			return Integer.valueOf(-1);

		if (!(status.equals(pub.getStatus())))
			if (!(FoundationUtil.haveViewAllDataFunction(request))) {
				String loginName = UserManager
						.getUserName(request.getSession());

				if (!(loginName.equals(pub.getUserName())))
					return Integer.valueOf(-1);

				if ((Constants.ONLINE.equals(status))
						|| (Constants.OFFLINE.equals(status))) {
					pub.setStatus(status);
					pub.setRecDate(new Date());
					this.pubService.update(pub);
				}

			} else if ((Constants.ONLINE.equals(status))
					|| (Constants.OFFLINE.equals(status))
					|| (Constants.STOPUSE.equals(status))) {
				pub.setStatus(status);
				pub.setRecDate(new Date());
				this.pubService.update(pub);
			}

		return pub.getStatus();
	}
}
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
import com.legendshop.core.model.UserMessages;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.IndexJpgService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({ "/admin/indexjpg" })
public class IndexJpgAdminController extends BaseController {
	private final Logger log = LoggerFactory
			.getLogger(IndexJpgAdminController.class);

	@Autowired
	private IndexJpgService indexJpgService;

	@RequestMapping({ "/query" })
	public String query(HttpServletRequest request,
			HttpServletResponse response, String curPageNO, Indexjpg indexjpg) {
		CriteriaQuery cq = new CriteriaQuery(Indexjpg.class, curPageNO);

		if (CommonServiceUtil.haveViewAllDataFunction(request))
			if (!(AppUtils.isBlank(indexjpg.getUserName())))
				cq.like("userName", "%" + indexjpg.getUserName() + "%");

			else
				cq.eq("userName", UserManager.getUserName(request));

		if (!(CommonServiceUtil.isDataForExport(cq, request)))
			cq.setPageSize(((Integer) PropertiesUtil.getObject(
					SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

		if (!(CommonServiceUtil.isDataSortByExternal(cq, request))) {
			cq.addOrder("asc", "seq");
		}

		PageSupport ps = this.indexJpgService.getIndexJpg(cq);
		ps.savePage(request);
		request.setAttribute("indexJpg", indexjpg);
		return PathResolver.getPath(request, response, BackPage.IJPG_LIST_PAGE);
	}

	@RequestMapping({ "/save" })
	public String save(HttpServletRequest request,
			HttpServletResponse response, Indexjpg indexjpg) {
		String name = UserManager.getUserName(request);
		Long num = this.indexJpgService.getIndexJpgNum(name);
		String result = checkMaxJpgNum(request, response, num);
		if (result != null)
			return result;

		String subPath = name + "/indexjpg/";
		String filename = null;
		MultipartFile formFile = indexjpg.getFile();
		try {
			if (indexjpg.getId() != null) {
				String orginImg = null;
				Indexjpg origin = this.indexJpgService.getIndexJpgById(indexjpg
						.getId());
				String checkPrivilegeResult = checkPrivilege(request, name,
						origin.getUserName());
				if (checkPrivilegeResult != null)
					return checkPrivilegeResult;

				if ((formFile != null)
						&& (formFile.getSize() > -4648541931658280960L)) {
					orginImg = RealPathUtil.getBigPicRealPath() + "/"
							+ origin.getImg();

					filename = FileProcessor.uploadFileAndCallback(formFile,
							subPath, "");
					origin.setImg(filename);
				}
				updateIndexjpg(request, response, indexjpg, origin);

				if ((formFile == null)
						|| (formFile.getSize() <= -4648542189356318720L)
						|| (orginImg == null)) {

				} else {
					FileProcessor.deleteFile(orginImg);
				}
			}

			indexjpg.setUserId(UserManager.getUserId(request));
			indexjpg.setUserName(name);

			if ((formFile != null)
					&& (formFile.getSize() > -4648542670392655872L)) {
				filename = FileProcessor.uploadFileAndCallback(formFile,
						subPath, "");
				indexjpg.setImg(filename);
			}

			saveIndexjpg(request, response, indexjpg);
		} catch (Exception e) {
			if ((formFile != null)
					&& (formFile.getSize() > -4648542670392655872L))
				FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/"
						+ subPath + filename);

			throw new BusinessException(e, "Save Indexjpg error", "998");
		}
		label347: saveMessage(request,
				ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.IJPG_LIST_QUERY);
	}

	private void saveIndexjpg(HttpServletRequest request,
			HttpServletResponse response, Indexjpg indexjpg) {
		this.indexJpgService.saveIndexjpg(indexjpg);
	}

	private void updateIndexjpg(HttpServletRequest request,
			HttpServletResponse response, Indexjpg indexjpg, Indexjpg origin) {
		origin.setAlt(indexjpg.getAlt());
		origin.setHref(indexjpg.getHref());
		origin.setId(indexjpg.getId());
		origin.setLink(indexjpg.getLink());
		origin.setStitle(indexjpg.getStitle());
		origin.setTitle(indexjpg.getTitle());
		origin.setTitleLink(indexjpg.getTitleLink());
		origin.setUploadTime(new Date());
		origin.setSeq(indexjpg.getSeq());
		this.indexJpgService.updateIndexjpg(origin);
	}

	private String checkMaxJpgNum(HttpServletRequest request,
			HttpServletResponse response, Long num) {
		String result = null;
		Integer maxNum = (Integer) PropertiesUtil.getObject(
				SysParameterEnum.MAX_INDEX_JPG, Integer.class);
		if ((num != null) && (num.longValue() >= maxNum.intValue())) {
			UserMessages uem = new UserMessages();
			uem.setTitle("系统设置不能上传多于" + maxNum + "张图片");
			uem.setCode("416");
			uem.addCallBackList("重新上传", "", PathResolver.getPath(request,
					response, BackPage.IJPG_EDIT_PAGE));
			request.setAttribute(UserMessages.MESSAGE_KEY, uem);
			result = handleException(request, uem);
		}
		return result;
	}

	@RequestMapping({ "/delete/{id}" })
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		Indexjpg indexjpg = this.indexJpgService.getIndexJpgById(id);
		checkNullable("indexjpg", indexjpg);
		String userName = UserManager.getUserName(request);
		String result = checkPrivilege(request, userName,
				indexjpg.getUserName());
		if (result != null)
			return result;

		this.log.debug("{} delete indexjpg {}", userName, id);
		this.indexJpgService.deleteIndexJpg(indexjpg);
		FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/"
				+ indexjpg.getImg());
		saveMessage(request, ResourceBundleHelper.getSucessfulString());
		return PathResolver.getPath(request, response,
				FowardPage.IJPG_LIST_QUERY);
	}

	@RequestMapping({ "/load" })
	public String load(HttpServletRequest request, HttpServletResponse response) {
		return PathResolver.getPath(request, response, BackPage.IJPG_EDIT_PAGE);
	}

	@RequestMapping({ "/update/{id}" })
	public String update(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		if (AppUtils.isBlank(id))
			throw new NotFoundException("indexjpg Id is non nullable", "405");

		Indexjpg indexjpg = this.indexJpgService.getIndexJpgById(id);
		String result = checkPrivilege(request,
				UserManager.getUserName(request), indexjpg.getUserName());
		if (result != null)
			return result;

		request.setAttribute("index", indexjpg);
		return PathResolver.getPath(request, response, BackPage.IJPG_EDIT_PAGE);
	}

	@RequestMapping(value = { "/updatestatus/{id}/{status}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Integer updateStatus(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id,
			@PathVariable Integer status) {
		Indexjpg indexjpg = this.indexJpgService.getIndexJpgById(id);
		if (indexjpg == null)
			return Integer.valueOf(-1);

		if (!(status.equals(Integer.valueOf(indexjpg.getStatus()))))
			if (!(FoundationUtil.haveViewAllDataFunction(request))) {
				String loginName = UserManager
						.getUserName(request.getSession());

				if (!(loginName.equals(indexjpg.getUserName())))
					return Integer.valueOf(-1);

				if ((Constants.ONLINE.equals(status))
						|| (Constants.OFFLINE.equals(status))) {
					indexjpg.setStatus(status.intValue());
					indexjpg.setUploadTime(new Date());
					this.indexJpgService.updateIndexjpg(indexjpg);
				}

			} else if ((Constants.ONLINE.equals(status))
					|| (Constants.OFFLINE.equals(status))
					|| (Constants.STOPUSE.equals(status))) {
				indexjpg.setStatus(status.intValue());
				indexjpg.setUploadTime(new Date());
				this.indexJpgService.updateIndexjpg(indexjpg);
			}

		return Integer.valueOf(indexjpg.getStatus());
	}
}
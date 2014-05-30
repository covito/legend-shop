package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.ExternalLink;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ExternalLinkService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/externallink"})
public class ExternalLinkAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ExternalLinkAdminController.class);

  @Autowired
  private ExternalLinkService externalLinkService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ExternalLink externalLink)
  {
    CriteriaQuery cq = new CriteriaQuery(ExternalLink.class, curPageNO, "javascript:pager");
    cq = hasAllDataFunction(cq, request, StringUtils.trim(externalLink.getUserName()));
    if (!(CommonServiceUtil.isDataForExport(cq, request)))
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    if (!(CommonServiceUtil.isDataSortByExternal(cq, request))) {
      cq.addOrder("desc", "bs");
    }

    PageSupport ps = this.externalLinkService.getDataByCriteriaQuery(cq);
    ps.savePage(request);
    request.setAttribute("bean", externalLink);
    return PathResolver.getPath(request, response, BackPage.LINK_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ExternalLink externalLink)
  {
    ExternalLink origin = null;
    String picUrl = null;
    externalLink.setUserId(UserManager.getUserId(request.getSession()));
    String userName = UserManager.getUserName(request.getSession());
    String subPath = userName + "/frendlink/";
    externalLink.setUserName(userName);

    if ((externalLink != null) && (externalLink.getId() != null)) {
      origin = this.externalLinkService.getExternalLinkById(externalLink.getId());
      if (origin == null) {
        throw new NotFoundException("Origin ExternalLink is empty");
      }

      if ((!(CommonServiceUtil.haveViewAllDataFunction(request))) && 
        (!(userName.equals(origin.getUserName())))) {
        throw new PermissionException("Can't edit ExternalLink does not own to you!");
      }

      String originPicUrl = origin.getPicture();
      origin.setUrl(externalLink.getUrl());
      origin.setWordlink(externalLink.getWordlink());
      origin.setContent(externalLink.getContent());
      origin.setBs(externalLink.getBs());
      if ((externalLink.getFile() != null) && (externalLink.getFile().getSize() > -4648542618853048320L)) {
        picUrl = FileProcessor.uploadFileAndCallback(externalLink.getFile(), subPath, "");
        origin.setPicture(picUrl);
        String url = RealPathUtil.getBigPicRealPath() + "/" + originPicUrl;
        FileProcessor.deleteFile(url);
      }
      this.externalLinkService.update(origin);
    } else {
      if ((externalLink.getFile() != null) && (externalLink.getFile().getSize() > -4648542618853048320L)) {
        picUrl = FileProcessor.uploadFileAndCallback(externalLink.getFile(), subPath, "");
        externalLink.setPicture(picUrl);
      }
      this.log.info("{} save ExternalLink Url {} ", userName, externalLink.getUrl());
      this.externalLinkService.save(externalLink);
    }

    saveMessage(request, ResourceBundleHelper.getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.LINK_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ExternalLink externalLink = this.externalLinkService.getExternalLinkById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), externalLink.getUserName());
    if (result != null)
      return result;

    if (externalLink != null) {
      this.log.info("{} delete ExternalLink Url{}", externalLink.getUserName(), externalLink.getUrl());
      this.externalLinkService.delete(id);
      String picUrl = RealPathUtil.getBigPicRealPath() + "/" + externalLink.getPicture();

      this.log.debug("delete ExternalLink Image file {}", picUrl);
      FileProcessor.deleteFile(picUrl);
    }

    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.LINK_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ExternalLink externalLink = this.externalLinkService.getExternalLinkById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), externalLink.getUserName());
    if (result != null)
      return result;

    request.setAttribute("bean", externalLink);
    return PathResolver.getPath(request, response, BackPage.LINK_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.LINK_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable ExternalLink externalLink)
  {
    ExternalLink origin = this.externalLinkService.getExternalLinkById(externalLink.getId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserName());
    if (result != null)
      return result;

    externalLink.setUserId(origin.getUserId());
    externalLink.setUserName(origin.getUserName());
    this.log.info("{} update ExternalLink Url{}", origin.getUserName(), externalLink.getUrl());
    this.externalLinkService.update(externalLink);
    return PathResolver.getPath(request, response, FowardPage.LINK_LIST_QUERY);
  }
}
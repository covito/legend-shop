package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.LimitationException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.Advertisement;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.AdvertisementService;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/advertisement"})
public class AdvertisementAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(AdvertisementAdminController.class);

  @Autowired
  private AdvertisementService advertisementService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Advertisement advertisement)
  {
    CriteriaQuery cq = new CriteriaQuery(Advertisement.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(advertisement.getUserName()));

    cq.addOrder("asc", "type");

    PageSupport ps = this.advertisementService.getDataByCriteriaQuery(cq);
    ps.savePage(request);
    request.setAttribute("bean", advertisement);
    return PathResolver.getPath(request, response, BackPage.ADV_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Advertisement advertisement)
  {
    Advertisement origin = null;
    String picUrl = null;
    String name = UserManager.getUserName(request.getSession());
    String subPath = name + "/advertisement/";
    if ((advertisement != null) && (advertisement.getId() != null)) {
      origin = this.advertisementService.getAdvertisement(advertisement.getId());
      if (origin == null)
        throw new NotFoundException("Origin Advertisement is NULL");

      String originPicUrl = origin.getPicUrl();
      if ((!(CommonServiceUtil.haveViewAllDataFunction(request))) && 
        (!(name.equals(origin.getUserName())))) {
        throw new PermissionException("Can't edit Advertisement does not own to you!");
      }

      origin.setLinkUrl(advertisement.getLinkUrl());
      origin.setType(advertisement.getType());
      origin.setSourceInput(advertisement.getSourceInput());
      origin.setEnabled(advertisement.getEnabled());
      origin.setTitle(advertisement.getTitle());
      if (advertisement.getFile().getSize() > -4648542464234225664L) {
        picUrl = FileProcessor.uploadFileAndCallback(advertisement.getFile(), subPath, "");
        origin.setPicUrl(picUrl);
        String url = RealPathUtil.getBigPicRealPath() + "/" + originPicUrl;
        FileProcessor.deleteFile(url);
      }
      this.advertisementService.update(origin);
    }
    else
    {
      if (!(this.advertisementService.isMaxNum(name, advertisement.getType())))
        throw new LimitationException("您已经达到广告上限，不能再增加");

      picUrl = FileProcessor.uploadFileAndCallback(advertisement.getFile(), subPath, "adv" + name);
      advertisement.setPicUrl(picUrl);
      advertisement.setUserId(UserManager.getUserId(request.getSession()));
      advertisement.setUserName(UserManager.getUserName(request.getSession()));
      this.advertisementService.save(advertisement);
    }
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.ADV_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Advertisement advertisement = this.advertisementService.getAdvertisement(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), advertisement.getUserName());
    if (result != null)
      return result;

    this.log.info("{}, delete Advertisement Url {}", advertisement.getUserName(), advertisement.getLinkUrl());
    this.advertisementService.delete(id);
    if (advertisement.getPicUrl() != null) {
      String url = RealPathUtil.getBigPicRealPath() + "/" + advertisement.getPicUrl();
      FileProcessor.deleteFile(url);
    }
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.ADV_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Advertisement advertisement = this.advertisementService.getAdvertisement(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), advertisement.getUserName());
    if (result != null)
      return result;

    request.setAttribute("bean", advertisement);
    return PathResolver.getPath(request, response, BackPage.ADV_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.ADV_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Advertisement advertisement)
  {
    Advertisement origin = this.advertisementService.getAdvertisement(advertisement.getId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserName());
    if (result != null)
      return result;

    advertisement.setUserId(origin.getUserId());
    advertisement.setUserName(origin.getUserName());
    this.advertisementService.update(advertisement);
    return PathResolver.getPath(request, response, FowardPage.ADV_LIST_QUERY);
  }

  @RequestMapping(value={"/updatestatus/{id}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, @PathVariable Integer status)
  {
    Advertisement advertisement = this.advertisementService.getAdvertisement(id);
    if (advertisement == null)
      return Integer.valueOf(-1);

    if (!(status.equals(advertisement.getEnabled())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(advertisement.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          advertisement.setEnabled(status);
          this.advertisementService.update(advertisement);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        advertisement.setEnabled(status);
        this.advertisementService.update(advertisement);
      }


    return advertisement.getEnabled();
  }
}
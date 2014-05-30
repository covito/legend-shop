package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.model.entity.Partner;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.PartnerService;
import com.legendshop.spi.service.ShopDetailService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/partner"})
public class PartnerController extends BaseController
  implements AdminController<Partner, Long>
{

  @Autowired
  private PartnerService partnerService;

  @Autowired
  private ShopDetailService shopDetailService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Partner partner)
  {
    CriteriaQuery cq = new CriteriaQuery(Partner.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(partner.getUserName()));

    PageSupport ps = this.partnerService.getPartner(cq);
    ps.savePage(request);
    request.setAttribute("partner", partner);
    return PathResolver.getPath(request, response, BackPage.PARTNER_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Partner partner) {
    Partner p = null;

    String username = UserManager.getUserName(request.getSession());
    String subPath = username + "/partner/";
    if (AppUtils.isNotBlank(partner.getPartnerId())) {
      p = this.partnerService.getPartner(partner.getPartnerId());

      partner.setCreateTime(p.getCreateTime());
      partner.setPassword(p.getPassword());
      partner.setShopId(p.getShopId());

      partner.setCommentGood(p.getCommentGood());
      partner.setCommentNone(p.getCommentNone());
      partner.setCommentBad(p.getCommentBad());

      if (partner.getImageFile().getSize() > -4648542601673179136L) {
        String image = FileProcessor.uploadFileAndCallback(partner.getImageFile(), subPath, "");
        String originImage = p.getImage();
        if (StringUtils.isNotEmpty(originImage))
          FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + originImage);

        partner.setImage(image);
      }
      if (partner.getImageFile1().getSize() > -4648542601673179136L) {
        String image1 = FileProcessor.uploadFileAndCallback(partner.getImageFile1(), subPath, "");
        String originImage1 = p.getImage1();
        if (StringUtils.isNotEmpty(originImage1))
          FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + originImage1);

        partner.setImage1(image1);
      }
      if (partner.getImageFile2().getSize() > -4648542601673179136L) {
        String image2 = FileProcessor.uploadFileAndCallback(partner.getImageFile2(), subPath, "");
        String originImage2 = p.getImage2();
        if (StringUtils.isNotEmpty(originImage2))
          FileProcessor.deleteFile(RealPathUtil.getBigPicRealPath() + "/" + originImage2);

        partner.setImage2(image2);
      }

      p = partner;
    } else {
      p = partner;
      p.setCreateTime(new Date());
      p.setCommentBad(Integer.valueOf(0));
      p.setCommentGood(Integer.valueOf(0));
      p.setCommentNone(Integer.valueOf(0));

      ShopDetail shopDetail = this.shopDetailService.getShopDetailByUserId(UserManager.getUserId(request));

      if (shopDetail == null)
        throw new NotFoundException("Can't find shopDetail by user id");

      p.setShopId(shopDetail.getShopId());

      if (partner.getImageFile().getSize() > -4648542601673179136L) {
        String image = FileProcessor.uploadFileAndCallback(partner.getImageFile(), subPath, "");
        partner.setImage(image);
      }

      if (partner.getImageFile1().getSize() > -4648542601673179136L) {
        String image1 = FileProcessor.uploadFileAndCallback(partner.getImageFile1(), subPath, "");
        partner.setImage1(image1);
      }
      if (partner.getImageFile2().getSize() > -4648542601673179136L) {
        String image2 = FileProcessor.uploadFileAndCallback(partner.getImageFile2(), subPath, "");
        partner.setImage2(image2);
      }

    }

    p.setUserId(UserManager.getUserId(request));
    p.setUserName(username);
    p.setModifyTime(new Date());

    this.partnerService.savePartner(p);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.PARTNER_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Partner partner = this.partnerService.getPartner(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), partner.getUserName());
    if (result != null)
      return result;

    this.partnerService.deletePartner(partner);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.PARTNER_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Partner partner = this.partnerService.getPartner(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), partner.getUserName());
    if (result != null)
      return result;

    request.setAttribute("partner", partner);
    return PathResolver.getPath(request, response, BackPage.PARTNER_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response) {
    return PathResolver.getPath(request, response, BackPage.PARTNER_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Partner partner = this.partnerService.getPartner(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), partner.getUserName());
    if (result != null)
      return result;

    request.setAttribute("partner", partner);
    return PathResolver.getPath(request, response, FowardPage.PARTNER_LIST_QUERY);
  }

  @RequestMapping({"/changePassword/{id}"})
  public String changePassword(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    Partner partner = this.partnerService.getPartner(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), partner.getUserName());
    if (result != null)
      return result;

    request.setAttribute("partner", partner);
    return PathResolver.getPath(request, response, BackPage.PARTNER_CHANGE_PASSWORD_PAGE);
  }

  @RequestMapping({"/savePassword"})
  public String savePassword(HttpServletRequest request, HttpServletResponse response, Partner partner) {
    Partner p = null;
    if ((AppUtils.isNotBlank(partner.getPartnerId())) && (AppUtils.isNotBlank(partner.getPassword()))) {
      p = this.partnerService.getPartner(partner.getPartnerId());
      p.setPassword(partner.getPassword());
    }

    p.setModifyTime(new Date());

    this.partnerService.savePartner(p);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.PARTNER_LIST_QUERY);
  }
}
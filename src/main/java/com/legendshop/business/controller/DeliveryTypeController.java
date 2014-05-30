package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.DeliveryType;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.DeliveryTypeService;
import com.legendshop.util.AppUtils;
import java.io.PrintStream;
import java.util.Date;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/deliveryType"})
public class DeliveryTypeController extends BaseController
  implements AdminController<DeliveryType, Long>
{

  @Autowired
  private DeliveryTypeService deliveryTypeService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, DeliveryType deliveryType)
  {
    CriteriaQuery cq = new CriteriaQuery(DeliveryType.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(deliveryType.getUserName()));

    if (!(AppUtils.isBlank(deliveryType.getName()))) {
      cq.like("name", "%" + deliveryType.getName() + "%");
    }

    PageSupport ps = this.deliveryTypeService.getDeliveryType(cq);
    ps.savePage(request);
    request.setAttribute("deliveryType", deliveryType);
    return PathResolver.getPath(request, response, BackPage.DELIVERYTYPE_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, DeliveryType deliveryType)
  {
    DeliveryType dt = null;
    if (AppUtils.isNotBlank(deliveryType.getDvyTypeId())) {
      dt = this.deliveryTypeService.getDeliveryType(deliveryType.getDvyTypeId());
      dt.setName(deliveryType.getName());
      dt.setDvyId(deliveryType.getDvyId());
      dt.setNotes(deliveryType.getNotes());
    } else {
      dt = deliveryType;
      dt.setCreateTime(new Date());
    }
    System.out.println("---------" + dt.getDvyId());
    dt.setUserId(UserManager.getUserId(request));
    dt.setUserName(UserManager.getUserName(request));
    dt.setModifyTime(new Date());

    this.deliveryTypeService.saveDeliveryType(dt);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.DELIVERYTYPE_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    DeliveryType deliveryType = this.deliveryTypeService.getDeliveryType(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryType.getUserName());
    if (result != null)
      return result;

    this.deliveryTypeService.deleteDeliveryType(deliveryType);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.DELIVERYTYPE_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    DeliveryType deliveryType = this.deliveryTypeService.getDeliveryType(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryType.getUserName());
    if (result != null)
      return result;

    request.setAttribute("deliveryType", deliveryType);
    return PathResolver.getPath(request, response, BackPage.DELIVERYTYPE_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.DELIVERYTYPE_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    DeliveryType deliveryType = this.deliveryTypeService.getDeliveryType(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryType.getUserName());
    if (result != null)
      return result;

    request.setAttribute("deliveryType", deliveryType);
    return PathResolver.getPath(request, response, FowardPage.DELIVERYTYPE_LIST_QUERY);
  }
}
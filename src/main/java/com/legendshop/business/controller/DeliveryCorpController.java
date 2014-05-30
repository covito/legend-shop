package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.DeliveryCorp;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.DeliveryCorpService;
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

@Controller
@RequestMapping({"/admin/deliveryCorp"})
public class DeliveryCorpController extends BaseController
  implements AdminController<DeliveryCorp, Long>
{

  @Autowired
  private DeliveryCorpService deliveryCorpService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, DeliveryCorp deliveryCorp)
  {
    CriteriaQuery cq = new CriteriaQuery(DeliveryCorp.class, curPageNO);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq = hasAllDataFunction(cq, request, StringUtils.trim(deliveryCorp.getUserName()));

    if (!(AppUtils.isBlank(deliveryCorp.getName()))) {
      cq.like("name", "%" + deliveryCorp.getName() + "%");
    }

    PageSupport ps = this.deliveryCorpService.getDeliveryCorp(cq);
    ps.savePage(request);
    request.setAttribute("deliveryCorp", deliveryCorp);
    return PathResolver.getPath(request, response, BackPage.DELIVERYCORP_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, DeliveryCorp deliveryCorp)
  {
    DeliveryCorp dc = null;
    if (AppUtils.isNotBlank(deliveryCorp.getDvyId())) {
      dc = this.deliveryCorpService.getDeliveryCorp(deliveryCorp.getDvyId());
      dc.setName(deliveryCorp.getName());
      dc.setUrl(deliveryCorp.getUrl());
    } else {
      dc = deliveryCorp;
      deliveryCorp.setCreateTime(new Date());
    }
    dc.setUserId(UserManager.getUserId(request));
    dc.setUserName(UserManager.getUserName(request));
    dc.setModifyTime(new Date());

    this.deliveryCorpService.saveDeliveryCorp(dc);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.DELIVERYCORP_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    DeliveryCorp deliveryCorp = this.deliveryCorpService.getDeliveryCorp(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryCorp.getUserName());
    if (result != null)
      return result;

    this.deliveryCorpService.deleteDeliveryCorp(deliveryCorp);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.DELIVERYCORP_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    DeliveryCorp deliveryCorp = this.deliveryCorpService.getDeliveryCorp(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryCorp.getUserName());
    if (result != null)
      return result;

    request.setAttribute("deliveryCorp", deliveryCorp);
    return PathResolver.getPath(request, response, BackPage.DELIVERYCORP_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.DELIVERYCORP_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    DeliveryCorp deliveryCorp = this.deliveryCorpService.getDeliveryCorp(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), deliveryCorp.getUserName());
    if (result != null)
      return result;

    request.setAttribute("deliveryCorp", deliveryCorp);
    return PathResolver.getPath(request, response, FowardPage.DELIVERYCORP_LIST_QUERY);
  }
}
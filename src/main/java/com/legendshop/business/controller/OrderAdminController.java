package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/order"})
public class OrderAdminController extends BaseController
  implements AdminController<Sub, Long>
{
  private final Logger log = LoggerFactory.getLogger(OrderAdminController.class);

  @Autowired
  private SubService subService;

  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sub entity)
  {
    this.log.debug("query sub by SubNumber {}, ShopName {}" + entity.getSubNumber(), entity.getShopName());
    String loginName = UserManager.getUserName(request);
    String subNumber = entity.getSubNumber();
    if (!(AppUtils.isBlank(subNumber))) {
      subNumber = subNumber.trim();
    }

    CriteriaQuery cq = new CriteriaQuery(Sub.class, curPageNO);
    if (CommonServiceUtil.haveViewAllDataFunction(request))
      if (!(AppUtils.isBlank(entity.getShopName())))
        cq.eq("shopName", entity.getShopName());

    else {
      cq.eq("shopName", loginName);
    }

    if (AppUtils.isNotBlank(subNumber)) {
      cq.like("subNumber", subNumber + "%");
    }

    if (AppUtils.isNotBlank(entity.getUserName()))
      cq.like("userName", entity.getUserName() + "%");

    cq.eq("status", entity.getStatus());
    cq.eq("subCheck", entity.getSubCheck());
    if (!(CommonServiceUtil.isDataForExport(cq, request)))
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue());

    if (!(CommonServiceUtil.isDataSortByExternal(cq, request))) {
      cq.addOrder("desc", "subDate");
    }

    PageSupport ps = this.subService.getOrderList(cq);
    ps.savePage(request);
    request.setAttribute("subForm", entity);
    return null;
  }

  @RequestMapping({"/processing"})
  public String queryProcessingOrder(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sub entity)
  {
    entity.setSubCheck("N");
    query(request, response, curPageNO, entity);
    return PathResolver.getPath(request, response, BackPage.PROCESSING_ORDER_LIST_PAGE);
  }

  @RequestMapping({"/processed"})
  public String queryProcessedOrder(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sub entity)
  {
    entity.setSubCheck("Y");
    query(request, response, curPageNO, entity);
    return PathResolver.getPath(request, response, BackPage.PROCESSED_ORDER_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Sub entity)
  {
    return null;
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, Long id)
  {
    return null;
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return null;
  }

  @RequestMapping({"/loadBySubnumber/{subNumber}"})
  public String loadBySubNember(HttpServletRequest request, HttpServletResponse response, @PathVariable String subNumber)
  {
    List baskets = this.subService.getBasketBySubNumber(subNumber);
    if (!(AppUtils.isBlank(baskets))) {
      Double totalcash = CommonServiceUtil.calculateTotalCash(baskets);
      Sub sub = this.subService.getSubBySubNumber(subNumber);
      String loginName = UserManager.getUserName(request);
      if ((!(CommonServiceUtil.haveViewAllDataFunction(request))) && (!(sub.getShopName().equals(loginName))))
        throw new PermissionException(loginName + " cann't view Sub id is " + sub.getSubId());

      request.setAttribute("sub", sub);
      request.setAttribute("baskets", baskets);
      request.setAttribute("totalcash", totalcash);
    }
    return PathResolver.getPath(request, response, BackPage.ORDERDETAIL);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, Long id)
  {
    return null;
  }

  @RequestMapping({"/modifyPrice"})
  public String modifyPrice(HttpServletRequest request, HttpServletResponse response, Long id)
  {
    return PathResolver.getPath(request, response, BackPage.MODIFYPRICE);
  }

  @RequestMapping({"/adminChangeSubPrice"})
  @ResponseBody
  public String adminChangeSubPrice(HttpServletRequest request, HttpServletResponse response, Long subId, String totalPrice)
  {
    if ((subId == null) || (totalPrice == null))
      return "fail";

    Double price = null;
    try {
      price = Double.valueOf(totalPrice.trim());
    } catch (Exception e) {
      return "fail";
    }
    if ((price == null) || (price.doubleValue() < 0D) || (price.doubleValue() > 9999999999999.0D))
      return "fail";

    Sub sub = this.subService.getSubById(subId);

    String userName = UserManager.getUserName(request);
    if ((!(sub.getUserName().equals(userName))) && (!(sub.getShopName().equals(userName)))) {
      this.log.warn("can not change sub {} does not belongs to you", subId);
      return "fail";
    }
    boolean result = false;
    try {
      result = this.subService.updateSubPrice(sub, userName, price);
    } catch (Exception e) {
      throw new BusinessException("update price failed");
    }

    if (result)
      return "OK";

    return "fail";
  }
}
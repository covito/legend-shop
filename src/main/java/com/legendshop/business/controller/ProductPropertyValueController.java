package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductPropertyValue;
import com.legendshop.spi.service.ProductPropertyValueService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/productPropertyValue"})
public class ProductPropertyValueController extends BaseController
  implements AdminController<ProductPropertyValue, Long>
{

  @Autowired
  private ProductPropertyValueService productPropertyValueService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductPropertyValue productPropertyValue)
  {
    CriteriaQuery cq = new CriteriaQuery(ProductPropertyValue.class, curPageNO);

    cq.add();
    PageSupport ps = this.productPropertyValueService.getProductPropertyValue(cq);
    ps.savePage(request);
    request.setAttribute("productPropertyValue", productPropertyValue);

    return "/productPropertyValue/productPropertyValueList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductPropertyValue productPropertyValue)
  {
    this.productPropertyValueService.saveProductPropertyValue(productPropertyValue);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/productPropertyValue/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductPropertyValue productPropertyValue = this.productPropertyValueService.getProductPropertyValue(id);

    this.productPropertyValueService.deleteProductPropertyValue(productPropertyValue);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/productPropertyValue/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductPropertyValue productPropertyValue = this.productPropertyValueService.getProductPropertyValue(id);

    request.setAttribute("productPropertyValue", productPropertyValue);
    return "/productPropertyValue/productPropertyValue";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return "/productPropertyValue/productPropertyValue";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductPropertyValue productPropertyValue = this.productPropertyValueService.getProductPropertyValue(id);

    request.setAttribute("productPropertyValue", productPropertyValue);
    return "forward:/admin/productPropertyValue/query.htm";
  }
}
package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductProperty;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ProductPropertyService;
import java.io.PrintStream;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/productProperty"})
public class ProductPropertyController extends BaseController
  implements AdminController<ProductProperty, Long>
{

  @Autowired
  private ProductPropertyService productPropertyService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductProperty productProperty)
  {
    CriteriaQuery cq = new CriteriaQuery(ProductProperty.class, curPageNO);

    cq.add();
    PageSupport ps = this.productPropertyService.getProductProperty(cq);
    ps.savePage(request);
    request.setAttribute("productProperty", productProperty);

    return PathResolver.getPath(request, response, BackPage.PRODUCTPROPERTY_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductProperty productProperty) {
    System.out.println("productProperty = " + ToStringBuilder.reflectionToString(productProperty));
    this.productPropertyService.saveProductProperty(productProperty);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));

    return PathResolver.getPath(request, response, FowardPage.PRODUCTPROPERTY_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductProperty productProperty = this.productPropertyService.getProductProperty(id);

    this.productPropertyService.deleteProductProperty(productProperty);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));

    return PathResolver.getPath(request, response, FowardPage.PRODUCTPROPERTY_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductProperty productProperty = this.productPropertyService.getProductProperty(id);

    request.setAttribute("productProperty", productProperty);

    return PathResolver.getPath(request, response, BackPage.PRODUCTPROPERTY_EDIT_PAGE);
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, BackPage.PRODUCTPROPERTY_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
    ProductProperty productProperty = this.productPropertyService.getProductProperty(id);

    request.setAttribute("productProperty", productProperty);

    return PathResolver.getPath(request, response, BackPage.PRODUCTPROPERTY_EDIT_PAGE);
  }
}
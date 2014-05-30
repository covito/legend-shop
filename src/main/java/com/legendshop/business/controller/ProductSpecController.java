package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductSpec;
import com.legendshop.spi.service.ProductSpecService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/productSpec"})
public class ProductSpecController extends BaseController
  implements AdminController<ProductSpec, Long>
{

  @Autowired
  private ProductSpecService productSpecService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductSpec productSpec)
  {
    CriteriaQuery cq = new CriteriaQuery(ProductSpec.class, curPageNO);

    cq.add();
    PageSupport ps = this.productSpecService.getProductSpec(cq);
    ps.savePage(request);
    request.setAttribute("productSpec", productSpec);

    return "/productSpec/productSpecList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductSpec productSpec)
  {
    this.productSpecService.saveProductSpec(productSpec);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/productSpec/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductSpec productSpec = this.productSpecService.getProductSpec(id);

    this.productSpecService.deleteProductSpec(productSpec);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/productSpec/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductSpec productSpec = this.productSpecService.getProductSpec(id);

    request.setAttribute("productSpec", productSpec);
    return "/productSpec/productSpec";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return "/productSpec/productSpec";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductSpec productSpec = this.productSpecService.getProductSpec(id);

    request.setAttribute("productSpec", productSpec);
    return "forward:/admin/productSpec/query.htm";
  }
}
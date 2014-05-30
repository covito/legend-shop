package com.legendshop.business.controller;

import com.legendshop.core.base.AdminController;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Sku;
import com.legendshop.spi.service.SkuService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/sku"})
public class SkuController extends BaseController
  implements AdminController<Sku, Long>
{

  @Autowired
  private SkuService skuService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sku sku)
  {
    CriteriaQuery cq = new CriteriaQuery(Sku.class, curPageNO);

    cq.add();
    PageSupport ps = this.skuService.getSku(cq);
    ps.savePage(request);
    request.setAttribute("sku", sku);

    return "/sku/skuList";
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, Sku sku)
  {
    this.skuService.saveSku(sku);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return "forward:/admin/sku/query.htm";
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Sku sku = this.skuService.getSku(id);

    this.skuService.deleteSku(sku);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return "forward:/admin/sku/query.htm";
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Sku sku = this.skuService.getSku(id);

    request.setAttribute("sku", sku);
    return "/sku/sku";
  }

  @RequestMapping({"/load"})
  public String load(HttpServletRequest request, HttpServletResponse response)
  {
    return "/sku/sku";
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    Sku sku = this.skuService.getSku(id);

    request.setAttribute("sku", sku);
    return "forward:/admin/sku/query.htm";
  }
}
package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductConsult;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ProductConsultService;
import com.legendshop.spi.service.ProductService;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/productConsult"})
public class ProductConsultAdminController extends BaseController
{

  @Autowired
  private ProductConsultService productConsultService;

  @Autowired
  private ProductService productService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductConsult productConsult)
  {
    PageSupport ps = this.productConsultService.getProductConsult(curPageNO, productConsult);
    ps.savePage(request);
    request.setAttribute("productConsult", productConsult);
    return PathResolver.getPath(request, response, BackPage.PROD_CONSULT_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductConsult productConsult)
  {
    String result = this.productConsultService.updateProductConsult(request, productConsult);
    if (result != null)
      return result;

    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.PROD_CONSULT_LIST_QUERY);
  }

  @RequestMapping({"/delete/{consId}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long consId)
  {
    String result = this.productConsultService.deleteProductConsult(request, consId);
    if (result != null)
      return result;

    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.PROD_CONSULT_LIST_QUERY);
  }

  @RequestMapping({"/load/{consId}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long consId)
  {
    ProductConsult productConsult = this.productConsultService.getProductConsult(consId);
    if (productConsult == null)
      throw new NotFoundException("productConsult not found with Id " + consId);

    Product product = this.productService.getProductById(productConsult.getProdId());
    productConsult.setProdName(product.getName());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), product.getUserName());
    if (result != null)
      return result;

    request.setAttribute("productConsult", productConsult);
    return PathResolver.getPath(request, response, BackPage.PROD_CONSULT_EDIT_PAGE);
  }
}
package com.legendshop.business.controller;

import com.legendshop.business.common.DynamicPropertiesHelper;
import com.legendshop.business.helper.PageGengrator;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/dynamic"})
public class ProductDynParamController extends BaseController
{

  @Autowired
  private ProductService productService;

  @RequestMapping({"/attribute/{prodId}"})
  public String queryAttribute(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
  {
    ProductDetail prod = (ProductDetail)request.getAttribute("prod");
    String attribute = null;
    if (prod != null)
      attribute = prod.getAttribute();
    else {
      attribute = this.productService.getProdDetail(prodId).getAttribute();
    }

    if (AppUtils.isNotBlank(attribute)) {
      List modelList = JSONUtil.getArray(attribute, Model.class);

      request.setAttribute("list", modelList);
      request.setAttribute("attribute", attribute);
    }
    return PathResolver.getPath(request, response, BackPage.SHOW_DYNAMIC_ATTRIBUTE);
  }

  @RequestMapping({"/parameter/{prodId}"})
  public String queryParameter(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
  {
    ProductDetail prod = (ProductDetail)request.getAttribute("prod");
    String parameter = null;
    if (prod != null)
      parameter = prod.getParameter();
    else {
      parameter = this.productService.getProdDetail(prodId).getParameter();
    }

    if (AppUtils.isNotBlank(parameter)) {
      List modelList = JSONUtil.getArray(parameter, Model.class);
      DynamicPropertiesHelper helper = new DynamicPropertiesHelper();
      request.setAttribute("dynamicProperties", "<table class='goodsAttributeTable'>" + helper.gerenateHTML(modelList) + 
        "</table>");
    }
    return PathResolver.getPath(request, response, BackPage.SHOW_DYNAMIC);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response)
  {
    return PathResolver.getPath(request, response, FowardPage.DYNAMIC_QUERY);
  }

  @RequestMapping({"/queryDynamicParameter"})
  @ResponseBody
  public String queryDynamicParameter(HttpServletRequest request, HttpServletResponse response, Long prodId)
  {
    if (AppUtils.isBlank(prodId))
      return "";

    ProductDetail prod = (ProductDetail)request.getAttribute("prod");
    String parameter = null;
    if (prod != null)
      parameter = prod.getParameter();
    else
      parameter = this.productService.getProdParameter(prodId);

    Map map = new HashMap();
    if (AppUtils.isNotBlank(parameter)) {
      List modelList = JSONUtil.getArray(parameter, Model.class);
      DynamicPropertiesHelper helper = new DynamicPropertiesHelper();
      map.put("dynamicProperties", "<table class='goodsAttributeTable'>" + helper.gerenateHTML(modelList) + "</table>");
      String result = PageGengrator.getInstance().crateHTML(request.getSession().getServletContext(), "showdynamic.ftl", map, ThreadLocalContext.getLocale());
      return result;
    }
    return "";
  }
}
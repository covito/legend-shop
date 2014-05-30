package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.model.dynamic.DynamicModel;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin"})
public class ProductDynParamAdminController extends BaseController
{

  @Autowired
  private ProductService productService;

  @RequestMapping({"/prodspec/query/{type}"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, DynamicTemp dynamicTemp, @PathVariable Integer type)
  {
    SimpleHqlQuery hql = new SimpleHqlQuery(curPageNO);

    hql.fillLikeParameter("name", dynamicTemp.getName());
    hql.fillParameter("status", dynamicTemp.getStatus());
    hql.fillParameter("sortId", dynamicTemp.getSortId());
    hql.fillParameter("type", type);
    hql.hasAllDataFunction(request, dynamicTemp.getUserName());

    hql.fillPageSize(request);
    PageSupport ps = this.productService.getDynamicTemp(hql);
    ps.savePage(request);
    dynamicTemp.setUserName(hql.getUserName());
    return PathResolver.getPath(request, response, BackPage.PRODUCTSPEC_LIST_PAGE);
  }

  @RequestMapping({"/prodspec/delete/{tempId}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long tempId)
  {
    String userName = UserManager.getUserName(request.getSession());
    int specType = this.productService.deleteDynamicTemp(tempId, userName);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    String path = PathResolver.getPath(request, response, FowardPage.PROD_SPEC_LIST_QUERY) + "/" + specType;
    return path;
  }

  @RequestMapping({"/prodspec/load/{tempId}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long tempId)
  {
    DynamicTemp dynamicTemp = this.productService.getDynamicTemp(tempId);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), dynamicTemp.getUserName());
    if (result != null)
      return result;

    request.setAttribute("dynamicTemp", dynamicTemp);
    return "/ask/ask";
  }

  @RequestMapping({"/dynamic/loadAttribute/{prodId}"})
  public String loadAttribute(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
  {
    Product product = this.productService.getProductById(prodId);
    if (AppUtils.isNotBlank(product)) {
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), product.getUserName());
      if (result != null)
        return result;

      request.setAttribute("prod", product);
      if (AppUtils.isNotBlank(product.getAttribute()))
        request.setAttribute("dynTempJson", product.getAttribute());

    }

    request.setAttribute("DYNAMIC_TYPE", Integer.valueOf(1));

    return PathResolver.getPath(request, response, BackPage.DYNAMIC_ATTRIBUTE);
  }

  @RequestMapping({"/prodspec/attribute/{tempId}"})
  public String attribute(HttpServletRequest request, HttpServletResponse response, @PathVariable Long tempId)
  {
    String userName = UserManager.getUserName(request.getSession());
    DynamicTemp dynamicTemp = this.productService.getDynamicTemp(tempId);
    if (AppUtils.isNotBlank(dynamicTemp)) {
      String result = checkPrivilege(request, userName, dynamicTemp.getUserName());
      if (result != null)
        return result;

      request.setAttribute("dynamicTemp", dynamicTemp);

      request.setAttribute("dynTempJson", dynamicTemp.getContent());
      request.setAttribute("DYNAMIC_TYPE", dynamicTemp.getType());
    }

    return PathResolver.getPath(request, response, BackPage.PRODUCTSPEC_EDIT_PAGE);
  }

  @RequestMapping({"/prodspec/createattribute/{type}"})
  public String createattribute(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer type) {
    DynamicTemp dynamicTemp = new DynamicTemp();
    dynamicTemp.setType(type);
    request.setAttribute("dynamicTemp", dynamicTemp);
    request.setAttribute("DYNAMIC_TYPE", type);
    return PathResolver.getPath(request, response, BackPage.PRODUCTSPEC_EDIT_PAGE);
  }

  @RequestMapping({"/dynamic/loadParameter/{prodId}"})
  public String loadParameter(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
  {
    Product product = this.productService.getProductById(prodId);
    if (AppUtils.isNotBlank(product)) {
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), product.getUserName());
      if (result != null)
        return result;

      request.setAttribute("prod", product);
      if (AppUtils.isNotBlank(product.getParameter()))
      {
        request.setAttribute("dynTempJson", product.getParameter());
      }
    }
    request.setAttribute("DYNAMIC_TYPE", Integer.valueOf(2));
    return PathResolver.getPath(request, response, BackPage.DYNAMIC_ATTRIBUTE);
  }

  @RequestMapping(value={"/prodspec/updatestatus/{tempId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long tempId, @PathVariable Integer status)
  {
    String loginName = UserManager.getUserName(request.getSession());
    DynamicTemp dynamicTemp = this.productService.getDynamicTemp(tempId);
    if (dynamicTemp == null)
      return Integer.valueOf(-1);

    String result = checkPrivilege(request, loginName, dynamicTemp.getUserName());
    if (result != null)
      return Integer.valueOf(-1);

    if (!(status.equals(dynamicTemp.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request)))
      {
        if (!(loginName.equals(dynamicTemp.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          dynamicTemp.setStatus(status);
          this.productService.updateDynamicTemp(dynamicTemp);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        dynamicTemp.setStatus(status);
        this.productService.updateDynamicTemp(dynamicTemp);
      }


    return dynamicTemp.getStatus();
  }

  @RequestMapping(value={"/dynamic/update"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public boolean updateDynamicTemp(@RequestBody DynamicModel dmodel) {
    Long tempId = dmodel.getTempId();
    Integer type = dmodel.getType();
    Long sortId = dmodel.getSortId();
    Model[] model = dmodel.getModel();

    boolean result = true;
    if (model != null) {
      DynamicTemp dynamicTemp = this.productService.getDynamicTemp(tempId);
      if (dynamicTemp != null) {
        String userName = UserManager.getUserName();
        if (dynamicTemp.getUserName().equals(userName)) {
          dynamicTemp.setType(type);
          dynamicTemp.setContent(JSONUtil.getJson(model));
          dynamicTemp.setSortId(sortId);
          result = this.productService.updateDynamicTemp(dynamicTemp);
        }
      }
    }
    return result;
  }

  @RequestMapping(value={"/dynamic/save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public long saveDynamicTemp(@RequestBody DynamicModel dmodel) {
    Integer type = dmodel.getType();
    Long sortId = dmodel.getSortId();
    String tempName = dmodel.getTempName();
    Model[] model = dmodel.getModel();
    if (model != null) {
      String userName = UserManager.getUserName();
      DynamicTemp dynamicTemp = new DynamicTemp();
      dynamicTemp.setName(tempName);
      dynamicTemp.setUserName(userName);
      dynamicTemp.setType(type);
      dynamicTemp.setContent(JSONUtil.getJson(model));
      dynamicTemp.setSortId(sortId);
      dynamicTemp.setStatus(Constants.ONLINE);
      return this.productService.saveDynamicTemp(dynamicTemp).longValue();
    }

    return -1L;
  }

  @RequestMapping(value={"/dynamic/delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public boolean deleteDynamicTemp(HttpServletRequest request, HttpServletResponse response, Long tempId)
  {
    String userName = UserManager.getUserName(request);
    if (AppUtils.isNotBlank(userName)) {
      DynamicTemp temp = this.productService.getDynamicTemp(tempId);
      if ((temp != null) && 
        (userName.equals(temp.getSortName())))
        return this.productService.deleteDynamicTemp(temp);

    }

    return false;
  }

  @RequestMapping(value={"/dynamic/loadspec"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public DynamicTemp loadDynamicAttributeFromTemp(Long tempId) {
    if (AppUtils.isBlank(tempId))
      return null;

    DynamicTemp temp = this.productService.getDynamicTemp(tempId);
    if ((temp != null) && 
      (AppUtils.isNotBlank(temp.getContent()))) {
      temp.setModelList(JSONUtil.getArray(temp.getContent(), Model.class));
    }

    return temp;
  }

  @RequestMapping(value={"/dynamic/savetoprod"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public boolean saveDynamic(@RequestBody DynamicModel dmodel) {
    Model[] model = dmodel.getModel();
    Long prodId = dmodel.getProdId();
    Integer type = dmodel.getType();

    if (model != null) {
      String userName = UserManager.getUserName();
      String result = JSONUtil.getJson(model);
      if (AppUtils.isNotBlank(result)) {
        Product product = this.productService.getProd(prodId, userName);
        if (product != null) {
          if (Constants.TEMPLATE_ATTRIBUTE.equals(type))
            product.setAttribute(result);
          else
            product.setParameter(result);

          this.productService.updateProd(product);
        }
      }
    }

    return true;
  }
}
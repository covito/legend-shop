package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.ProductCommentService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.sql.ConfigCode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/admin/productcomment"})
public class ProductCommentAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ProductCommentAdminController.class);

  @Autowired
  private ProductCommentService productCommentService;

  @Autowired
  private ProductService productService;

  @RequestMapping({"/query"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, ProductComment productComment)
  {
    String userName = UserManager.getUserName(request.getSession());
    Map map = new HashMap();
    HqlQuery hql = new HqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue(), curPageNO);
    if (!(CommonServiceUtil.haveViewAllDataFunction(request))) {
      map.put("ownerName", userName);
      hql.addParams(userName);
    }
    else if (AppUtils.isNotBlank(productComment.getOwnerName())) {
      map.put("ownerName", productComment.getOwnerName());
      hql.addParams(productComment.getOwnerName());
    }

    if (AppUtils.isNotBlank(productComment.getUserName())) {
      map.put("userName", productComment.getUserName());
      hql.addParams("%" + productComment.getUserName() + "%");
    }

    if (AppUtils.isNotBlank(productComment.getProdName())) {
      map.put("name", productComment.getProdName());
      hql.addParams("%" + productComment.getProdName() + "%");
    }

    if (!(CommonServiceUtil.isDataForExport(hql, request)))
      hql.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());

    if (!(CommonServiceUtil.isDataSortByExternal(hql, request, map)))
      map.put("orderIndicator", "order by hc.addtime desc");

    hql.setAllCountString(ConfigCode.getInstance().getCode("prod.getProductCommentCount", map));
    hql.setQueryString(ConfigCode.getInstance().getCode("prod.getProductComment", map));
    PageSupport ps = this.productCommentService.getProductCommentList(hql);
    ps.savePage(request);
    request.setAttribute("bean", productComment);
    return PathResolver.getPath(request, response, BackPage.PROD_COMM_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductComment productComment)
  {
    ProductComment comment = this.productCommentService.getProductCommentById(productComment.getId());
    if (comment == null)
      throw new NotFoundException("ProductComment Not Found");

    String username = UserManager.getUserName(request.getSession());
    String result = checkPrivilege(request, username, comment.getOwnerName());
    if (result != null)
      return result;

    SafeHtml safe = new SafeHtml();
    comment.setReplyContent(safe.makeSafe(productComment.getReplyContent()));
    comment.setReplyName(username);
    comment.setReplyTime(new Date());
    this.productCommentService.update(comment);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("operation.successful"));
    return PathResolver.getPath(request, response, FowardPage.PROD_COMM_LIST_QUERY);
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    String username = UserManager.getUserName(request.getSession());
    ProductComment productComment = this.productCommentService.getProductCommentById(id);
    String result = checkPrivilege(request, username, productComment.getOwnerName());
    if (result != null)
      return result;

    this.log.info("{}, delete ProductComment Addtime {}, delete person", 
      new Object[] { productComment.getUserName(), productComment.getAddtime(), username });
    this.productCommentService.delete(id);
    saveMessage(request, ResourceBundle.getBundle("i18n/ApplicationResources").getString("entity.deleted"));
    return PathResolver.getPath(request, response, FowardPage.PROD_COMM_LIST_QUERY);
  }

  @RequestMapping({"/load/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    ProductComment productComment = this.productCommentService.getProductCommentById(id);
    if (productComment == null)
      throw new NotFoundException("ProductComment not found with Id " + id);

    Product product = this.productService.getProductById(productComment.getProdId());
    productComment.setProdName(product.getName());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), productComment.getOwnerName());
    if (result != null)
      return result;

    request.setAttribute("bean", productComment);
    return PathResolver.getPath(request, response, BackPage.PROD_COMM_EDIT_PAGE);
  }

  @RequestMapping({"/update"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable ProductComment productComment)
  {
    this.productCommentService.update(productComment);
    return PathResolver.getPath(request, response, FowardPage.PROD_COMM_LIST_QUERY);
  }
}
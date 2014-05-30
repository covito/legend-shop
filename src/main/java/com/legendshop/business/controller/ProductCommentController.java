package com.legendshop.business.controller;

import com.legendshop.business.helper.PageGengrator;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductCommentCategory;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.RedirectPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.ProductCommentService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.SafeHtml;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/productcomment"})
public class ProductCommentController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ProductCommentController.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private ProductCommentService productCommentService;

  @Autowired
  private SubService subService;

  @RequestMapping({"/update/{prodId}"})
  public String update(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
    throws Exception
  {
    ProductDetail prod = this.productService.getProdDetail(prodId);
    request.setAttribute("prod", prod);
    return PathResolver.getPath(request, response, TilesPage.PRODUCT_COMMENT);
  }

  @RequestMapping({"/list/{prodId}"})
  public String list(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer score, @PathVariable Long prodId)
    throws Exception
  {
    parseCommentList(request, response, curPageNO, score, prodId);
    return PathResolver.getPath(request, response, FrontPage.PRODUCT_COMMENTS);
  }

  @RequestMapping({"/listcontent/{prodId}"})
  public String listcontent(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer score, @PathVariable Long prodId) throws Exception
  {
    parseCommentList(request, response, curPageNO, score, prodId);
    return PathResolver.getPath(request, response, FrontPage.PRODUCT_COMMENT_LIST);
  }

  private void parseCommentList(HttpServletRequest request, HttpServletResponse response, String curPageNO, Integer score, Long prodId)
  {
    CriteriaQuery cq = new CriteriaQuery(ProductComment.class, curPageNO, "javascript:commentPager", 
      PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    cq.setPageSize(10);
    cq.eq("prodId", prodId);
    cq.addOrder("desc", "addtime");
    if ((score != null) && (score.intValue() != 0))
      if (score.intValue() == 3)
        cq.ge("score", Integer.valueOf(4));
      else if (score.intValue() == 2)
      {
        cq.or(Restrictions.eq("score", Integer.valueOf(3)), Restrictions.isNull("score"));
      } else if (score.intValue() == 1)
        cq.lt("score", Integer.valueOf(3));


    PageSupport ps = this.productCommentService.getProductCommentList(cq);
    ps.savePage(request);
    ProductCommentCategory pcc = this.productCommentService.initProductCommentCategory(prodId);
    request.setAttribute("prodCommCategory", pcc);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, ProductComment productComment)
    throws Exception
  {
    String userName = UserManager.getUserName(request.getSession());
    String validation = this.productCommentService.validateComment(productComment.getProdId(), userName);
    if (validation != null)
      throw new BusinessException("Validate Product Comment Failed with Id " + productComment.getProdId());

    SafeHtml safe = new SafeHtml();
    productComment.setUserName((userName == null) ? "anonymous" : userName);
    productComment.setAddtime(new Date());
    Product product = this.productService.getProductById(productComment.getProdId());
    if (product == null)
      throw new NotFoundException("Can not found Product with Id " + productComment.getProdId());

    productComment.setContent(safe.makeSafe(productComment.getContent()));
    productComment.setReplyContent(safe.makeSafe(productComment.getReplyContent()));
    productComment.setOwnerName(product.getUserName());
    productComment.setPostip(request.getRemoteAddr());
    productComment.validate();
    this.productCommentService.save(productComment);
    return PathResolver.getPath(request, response, RedirectPage.VIEWS) + "/" + product.getProdId();
  }

  @RequestMapping({"/load"})
  @ResponseBody
  public String load(HttpServletRequest request, HttpServletResponse response, Long prodId, String curPageNO)
  {
    if (AppUtils.isBlank(prodId))
      return null;

    return genProdCommentHtml(request, prodId, curPageNO);
  }

  @RequestMapping({"/addProdcutComment"})
  @ResponseBody
  public String addProdcutComment(HttpServletRequest request, HttpServletResponse response, Long prodId, String content)
  {
    if (AppUtils.isBlank(prodId))
      return null;

    String userName = UserManager.getUserName(request);
    String validation = this.productCommentService.validateComment(prodId, userName);
    if (validation != null)
      return validation;

    ProductComment productComment = new ProductComment();
    productComment.setUserName((userName == null) ? "anonymous" : userName);
    productComment.setAddtime(new Date());
    productComment.setContent(content);
    productComment.setProdId(prodId);
    Product product = this.productService.getProductById(prodId);
    productComment.setOwnerName(product.getUserName());
    productComment.setPostip(request.getRemoteAddr());
    this.productCommentService.save(productComment);

    String curPageNO = request.getParameter("curPageNO");
    return genProdCommentHtml(request, prodId, curPageNO);
  }

  private String genProdCommentHtml(HttpServletRequest request, Long prodId, String curPageNO)
  {
    Locale locale = ThreadLocalContext.getLocale();
    if (locale == null)
      locale = Locale.CHINA;
    try
    {
      CriteriaQuery cq = new CriteriaQuery(ProductComment.class, curPageNO, PageProviderEnum.SIMPLE_PAGE_PROVIDER);
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue());
      cq.addOrder("desc", "addtime");
      cq.eq("prodId", prodId);

      PageSupport ps = this.productService.getProductList(cq);
      request.setAttribute("curPageNO", new Integer(ps.getCurPageNO()));
      Map map = new HashMap();
      List list = ps.getResultList();
      map.put("productCommentList", list);
      if (AppUtils.isNotBlank(list)) {
        String userName = UserManager.getUserName(request);
        if (((ProductComment)list.get(0)).getOwnerName().equals(userName))
          map.put("owner", Boolean.valueOf(true));
      }

      map.put("totalProductComment", Long.valueOf(ps.getTotal()));
      map.put("toolBar", ps.getToolBar());
      String result = PageGengrator.getInstance().crateHTML(request.getSession().getServletContext(), "productComment.ftl", map, locale);
      return result;
    } catch (Exception e) {
      this.log.error("getProductComment", e); }
    return "FAIL";
  }
}
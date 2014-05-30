package com.legendshop.business.controller;

import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.helper.VisitHistoryHelper;
import com.legendshop.core.model.UserMessages;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.visit.VisitHistory;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.PageADV;
import com.legendshop.spi.constants.VisitTypeEnum;
import com.legendshop.spi.event.VisitLogEvent;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.ImgFileService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private ImgFileService imgFileService;

  @RequestMapping({"/views/{prodId}"})
  public String views(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
  {
    if (prodId == null)
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);

    ProductDetail prod = this.productService.getProdDetail(prodId);

    if (prod != null) {
      if (ProductTypeEnum.GROUP.value().equals(prod.getProdType()))
        return PathResolver.getPath(request, response, "/group/view/" + prodId, FowardPage.VARIABLE);

      if (!(Constants.ONLINE.equals(prod.getStatus()))) {
        throw new NotFoundException("产品 " + prod.getName() + " 已经下线.");
      }

      List prodPics = this.imgFileService.getProductPics(prod.getUserName(), prodId);
      if (AppUtils.isNotBlank(prodPics)) {
        request.setAttribute("prodPics", prodPics);
      }

      ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(request, response, prod.getUserName());
      if (shopDetail == null) {
        return PathResolver.getPath(request, response, FrontPage.TOPALL);
      }

      List releationProds = this.productService.getReleationProd(prod.getUserName(), prod.getProdId(), 30);
      if (!(AppUtils.isBlank(releationProds))) {
        request.setAttribute("productList", releationProds);
      }

      request.setAttribute("prod", prod);

      this.productService.getAndSetAdvertisement(request, response, prod.getUserName(), PageADV.PROD.name());

      if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.VISIT_HW_LOG_ENABLE, Boolean.class)).booleanValue()) {
        this.productService.updateProdViews(prodId);
      }

      String userName = UserManager.getUserName(request.getSession());

      if (this.log.isInfoEnabled()) {
        this.log.info("{},{},{},{},viewsprod", new Object[] { request.getRemoteAddr(), (userName == null) ? "" : userName, 
          ThreadLocalContext.getCurrentShopName(request, response), prod.getName() });
      }

      VisitHistoryHelper.visit(prod, request, response);

      List recommendProds = this.productService.getRecommendProd(prod.getProdId());
      if (AppUtils.isNotBlank(recommendProds)) {
        request.setAttribute("recommendProds", recommendProds);
      }

      if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.VISIT_LOG_ENABLE, Boolean.class)).booleanValue())
        EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(), prod.getUserName(), userName, prod.getProdId(), 
          prod.getName(), VisitTypeEnum.PROD.value()));

      return PathResolver.getPath(request, response, TilesPage.VIEWS);
    }
    UserMessages uem = new UserMessages();
    uem.setTitle(ResourceBundleHelper.getString("product.not.found"));
    uem.setDesc(ResourceBundleHelper.getString("product.status.check"));
    uem.setCode("404");
    request.setAttribute(UserMessages.MESSAGE_KEY, uem);
    return PathResolver.getPath(request, response, FrontPage.FAIL);
  }

  @Deprecated
  private void visit(ProductDetail prod, HttpServletRequest request)
  {
    VisitHistory visitHistory = (VisitHistory)request.getSession().getAttribute("VisitHistory");
    if (visitHistory == null)
      visitHistory = new VisitHistory();

    visitHistory.visit(prod);
    request.getSession().setAttribute("VisitHistory", visitHistory);
  }

  @RequestMapping({"/hotsale"})
  public String hotsale(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    List hotsaleList = this.productService.getHotSale(shopName);
    if (AppUtils.isNotBlank(hotsaleList))
      request.setAttribute("hotsaleList", hotsaleList);

    return PathResolver.getPath(request, response, FrontPage.HOT_SALE);
  }

  @RequestMapping({"/hoton/{sortId}"})
  public String hoton(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    List hotonList = this.productService.getHotOn(ThreadLocalContext.getCurrentShopName(request, response), sortId);
    if (AppUtils.isNotBlank(hotonList)) {
      request.setAttribute("hotonList", hotonList);
    }

    return PathResolver.getPath(request, response, FrontPage.HOT_ON);
  }

  @RequestMapping({"/hotview"})
  public String hotView(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    List hotViewList = this.productService.getHotViewProd(shopName, 10);
    if (AppUtils.isNotBlank(hotViewList))
      request.setAttribute("hotViewList", hotViewList);

    return PathResolver.getPath(request, response, FrontPage.HOT_VIEW);
  }

  @RequestMapping({"/hotcomments/{sortId}"})
  public String hotComments(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    List hotCommentList = this.productService.getHotComment(shopName, sortId, 10);
    if (AppUtils.isNotBlank(hotCommentList))
      request.setAttribute("hotCommentList", hotCommentList);

    return PathResolver.getPath(request, response, FrontPage.HOT_COMMENT);
  }

  @RequestMapping({"/hotrecommends/{sortId}"})
  public String hotRecommend(HttpServletRequest request, HttpServletResponse response, @PathVariable Long sortId)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    List hotRecommendList = this.productService.getHotRecommend(shopName, sortId, 3);
    if (AppUtils.isNotBlank(hotRecommendList))
      request.setAttribute("hotRecommendList", hotRecommendList);

    return PathResolver.getPath(request, response, FrontPage.HOT_RECOMMEND);
  }

  @RequestMapping({"/productGallery/{prodId}"})
  public String productGallery(HttpServletRequest request, HttpServletResponse response, @PathVariable Long prodId)
    throws Exception
  {
    if (AppUtils.isBlank(prodId))
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);

    return this.productService.getProductGallery(request, response, prodId);
  }

  @RequestMapping({"/allprods"})
  public String allProds(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    return PathResolver.getPath(request, response, TilesPage.ALL_PRODS);
  }

  @RequestMapping({"/visitedprod"})
  public String visitedProd(HttpServletRequest request, HttpServletResponse response)
  {
    List products = this.productService.getVisitedProd(request);
    request.setAttribute("visitedProd", products);
    return PathResolver.getPath(request, response, FrontPage.VISITED_PROD);
  }
}
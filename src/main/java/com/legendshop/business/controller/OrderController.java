package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.service.locator.SubServiceLocator;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.exception.BusinessException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.exception.PermissionException;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.SubQueryForm;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.PayType;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.form.BasketForm;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.AdvertisementService;
import com.legendshop.spi.service.BasketService;
import com.legendshop.spi.service.PayTypeService;
import com.legendshop.spi.service.ScoreService;
import com.legendshop.spi.service.UserDetailService;
import com.legendshop.spi.service.timer.SubService;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/p"})
public class OrderController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(OrderController.class);
  private final String defaultValue = "0";

  @Autowired
  private AdvertisementService advertisementService;

  @Autowired
  private SubServiceLocator subServiceLocator;

  @Autowired
  private BasketService basketService;

  @Autowired
  private UserDetailService userDetailService;

  @Autowired
  private PayTypeService payTypeService;

  @Autowired
  private ScoreService scoreService;

  @RequestMapping({"/order"})
  public String order(HttpServletRequest request, HttpServletResponse response, String curPageNO, Sub entity, SubQueryForm subQueryForm)
  {
    String userName = UserManager.getUserName(request);

    if (userName == null) {
      request.setAttribute("returnUrl", PropertiesUtil.getDomainName() + "/p/order");
      return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);
    }
    if ((entity != null) && (entity.getSubCheck() == null))
      entity.setSubCheck("N");

    SubService subService = (SubService)this.subServiceLocator.getConcreteService(request, response, TilesPage.ORDER);

    String result = subService.findOrder(request, response, curPageNO, entity, userName, subQueryForm);
    return result;
  }

  @RequestMapping({"/buy"})
  public String update(HttpServletRequest request, HttpServletResponse response, BasketForm basket)
  {
    Integer count = basket.getCount();
    if (count == null)
      count = Integer.valueOf(1);

    this.advertisementService.getAndSetOneAdvertisement(request, response, ThreadLocalContext.getCurrentShopName(request, response), 
      "USER_REG_ADV_950");
    return PathResolver.getPath(request, response, TilesPage.BUY);
  }

  @RequestMapping({"/clear"})
  public String clear(HttpServletRequest request, HttpServletResponse response)
  {
    String userName = UserManager.getUserName(request);
    if (AppUtils.isBlank(userName))
      return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);

    String basketId = request.getParameter("basketId");
    if (basketId == null) {
      this.basketService.deleteBasketByUserName(userName);
      CommonServiceUtil.setBasketTotalCount(request.getSession(), 0);
    } else {
      try {
        Long id = Long.valueOf(basketId);
        this.basketService.deleteBasketById(id);
        CommonServiceUtil.calBasketTotalCount(request.getSession(), -1);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return PathResolver.getPath(request, response, TilesPage.BUY);
  }

  @RequestMapping({"/bought"})
  public String bought(HttpServletRequest request, HttpServletResponse response)
  {
    List baskets = this.basketService.getBasketByUserName(UserManager.getUserName(request));
    if (!(AppUtils.isBlank(baskets))) {
      Double totalcash = CommonServiceUtil.calculateTotalCash(baskets);
      request.setAttribute("baskets", baskets);
      request.setAttribute("totalcash", totalcash);
    }

    return PathResolver.getPath(request, response, FrontPage.BOUGHT);
  }

  @RequestMapping({"/cashsave"})
  public String cashsave(HttpServletRequest request, HttpServletResponse response)
  {
    String total = request.getParameter("total");
    if (total != null)
      request.setAttribute("total", total);
    else
      total = "0";

    String userName = UserManager.getUserName(request);
    if (AppUtils.isBlank(userName)) {
      return PathResolver.getPath(request, response, TilesPage.NO_LOGIN);
    }

    UserDetail member = this.userDetailService.getUserDetail(userName);
    if (!(AppUtils.isBlank(member)))
      request.setAttribute("member", member);

    return PathResolver.getPath(request, response, FrontPage.CASH_SAVE);
  }

  @RequestMapping({"/orderDetail/{subNumber}"})
  public String orderDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable String subNumber)
  {
    String userName = UserManager.getUserName(request);
    if (AppUtils.isBlank(userName)) {
      return PathResolver.getPath(request, response, TilesPage.LOGIN);
    }

    Sub sub = this.subServiceLocator.getSubService().getSubBySubNumber(subNumber);
    if (sub == null) {
      throw new NotFoundException("sub not found with userName = " + userName);
    }

    if ((!(userName.equals(sub.getUserName()))) && (!(userName.equals(sub.getShopName()))) && 
      (!(CommonServiceUtil.haveViewAllDataFunction(request)))) {
      throw new PermissionException("can not modify others order detail");
    }

    return this.subServiceLocator.getSubService().getOrderDetail(request, response, sub, userName, subNumber);
  }

  @RequestMapping({"/cash"})
  public String cash(HttpServletRequest request, HttpServletResponse response)
  {
    String[] basketIdList = request.getParameterValues("strArray");
    if (AppUtils.isBlank(basketIdList))
      throw new BusinessException("Basket at least choose one");

    Long[] basketId = new Long[basketIdList.length];
    for (int i = 0; i < basketId.length; ++i)
      basketId[i] = Long.valueOf(Long.parseLong(basketIdList[i]));

    List basketList = this.basketService.getBasketListById(basketId);
    if (AppUtils.isNotBlank(basketList))
      for (int i = 0; i < basketList.size(); ++i) {
        Basket basket = (Basket)basketList.get(i);
        Integer bc = Integer.valueOf(Integer.parseInt(request.getParameter("basketCount" + basket.getBasketId())));
        if (!(basket.getBasketCount().equals(bc))) {
          basket.setBasketCount(bc);
          this.basketService.updateBasket(basket);
        }
      }


    Double totalcash = CommonServiceUtil.calculateTotalCash(basketList);
    request.setAttribute("baskets", basketList);
    request.setAttribute("totalcash", totalcash);

    request.getSession().setAttribute("SESSION_TOKEN", CommonServiceUtil.generateRandom());
    return PathResolver.getPath(request, response, TilesPage.PAGE_CASH);
  }

  /**
   * @deprecated
   */
  @RequestMapping({"/payToOrder"})
  public void payToOrder(HttpServletRequest request, HttpServletResponse response, Long subId, String shopName, String payTypeId)
  {
    if (AppUtils.isNotBlank(subId)) {
      Sub sub = this.subServiceLocator.getSubService().getSubById(subId);
      PayType payType = this.payTypeService.getPayTypeByIdAndName(shopName, payTypeId);
      if (payType != null) {
        sub.setPayTypeName(payType.getPayTypeName());
        sub.setPayTypeId(payType.getPayTypeId());
        sub.setPayId(payType.getPayId());
        sub.setPayDate(new Date());
        this.subServiceLocator.getSubService().updateSub(sub);
      }
    }
  }

  @RequestMapping({"/updateSubStatus"})
  @ResponseBody
  public String updateSubStatus(HttpServletRequest request, HttpServletResponse response, String subNumber, Integer status, String payTypeId)
  {
    String userName = UserManager.getUserName(request);
    if (userName == null)
      return "fail";

    Sub sub = this.subServiceLocator.getSubService().getSubBySubNumber(subNumber);
    if (sub == null)
      return "fail";

    if ((!(sub.getUserName().equals(userName))) && (!(sub.getShopName().equals(userName))) && 
      (!(CommonServiceUtil.haveViewAllDataFunction(request)))) {
      this.log.warn("updateSub:userName {} or shopName {} not equal userName {}", 
        new Object[] { sub.getUserName(), sub.getShopName(), userName });
      return "fail";
    }

    boolean result = this.subServiceLocator.getSubService().updateSub(sub, status, userName, payTypeId);
    if (result)
      return null;

    return "fail";
  }

  @RequestMapping({"/deleteSub"})
  @ResponseBody
  public String deleteSub(HttpServletRequest request, HttpServletResponse response, Long subId)
  {
    if (subId == null)
      return "fail";

    Sub sub = this.subServiceLocator.getSubService().getSubById(subId);

    String hasAccessRight = checkPrivilege(request, UserManager.getUserName(request.getSession()), sub.getUserName());
    if (hasAccessRight != null) {
      return "fail";
    }

    boolean result = this.subServiceLocator.getSubService().deleteSub(sub);
    if (result)
      return null;

    return "fail";
  }

  @RequestMapping({"/calMoneySocre"})
  @ResponseBody
  public Double calMoneySocre(Long score)
  {
    return this.scoreService.calMoney(score);
  }

  @RequestMapping({"/userScore"})
  @ResponseBody
  public Map<String, Object> userScore(Long subId, Long score)
  {
    Sub sub = this.subServiceLocator.getSubService().getSubById(subId);
    if (sub == null)
      return null;

    return this.scoreService.useScore(sub, score);
  }
}
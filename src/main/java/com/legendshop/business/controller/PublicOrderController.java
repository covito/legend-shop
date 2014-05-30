package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.SaveToCartStatusEnum;
import com.legendshop.spi.service.BasketService;
import com.legendshop.spi.service.ProductService;
import com.legendshop.spi.service.StockService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.Arith;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicOrderController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(PublicOrderController.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private BasketService basketService;

  @Autowired
  private StockService stockService;

  @RequestMapping({"/addtocart"})
  @ResponseBody
  public Map<String, Object> addtocart(HttpServletRequest request, HttpServletResponse response, Long prodId, Integer count, String attribute)
  {
    String userName = UserManager.getUserName(request);
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    if (this.log.isDebugEnabled())
      this.log.debug("userName {} order product with id {} in shop {}, quantity is {}", new Object[] { userName, prodId, shopName, count });

    Product product = this.productService.getProductById(prodId);

    Map map = new HashMap();
    if (product == null)
      return map;

    if (attribute == null)
      attribute = "";

    if (count == null) {
      count = Integer.valueOf(1);
    }

    SaveToCartStatusEnum saveToCartStatus = SaveToCartStatusEnum.OK;
    HttpSession session = request.getSession();
    if (AppUtils.isBlank(userName))
    {
      if (!(this.stockService.canOrder(product, count)))
      {
        saveToCartStatus = SaveToCartStatusEnum.LESS;
      } else if (product.getUserName().equals(userName))
      {
        saveToCartStatus = SaveToCartStatusEnum.OWNER;
      }

      Map basketMap = (Map)session.getAttribute("BASKET_KEY");
      if (basketMap == null)
      {
        if (saveToCartStatus.equals(SaveToCartStatusEnum.OK)) {
          basketMap = new HashMap();
          Basket b = new Basket();
          b.setProdId(prodId);
          b.setBasketCount(count);
          b.setAttribute(attribute);
          b.setCash(product.getCash());
          b.setCarriage(product.getCarriage());
          b.setLastUpdateDate(new Date());

          saveToCartStatus = addProductToCart(basketMap, CommonServiceUtil.getBasketKey(shopName, prodId, attribute), b);
          session.setAttribute("BASKET_KEY", basketMap);
          map.put("BASKET_COUNT", Integer.valueOf(1));
          Double totalCash = Double.valueOf(Arith.mul(count.intValue(), product.getCash().doubleValue()));
          if (product.getCarriage() != null)
            totalCash = Double.valueOf(Arith.add(totalCash.doubleValue(), product.getCarriage().doubleValue()));

          map.put("BASKET_TOTAL_CASH", totalCash);
        } else {
          map.put("BASKET_COUNT", Integer.valueOf(0));
          map.put("BASKET_TOTAL_CASH", Double.valueOf(0D));
        }
      }
      else
      {
        if (saveToCartStatus.equals(SaveToCartStatusEnum.OK)) {
          String basketKey = CommonServiceUtil.getBasketKey(shopName, prodId, attribute);
          Basket b = (Basket)basketMap.get(basketKey);
          if (b == null)
          {
            b = new Basket();
            b.setProdId(prodId);
            b.setBasketCount(count);
            b.setAttribute(attribute);
            b.setCash(product.getCash());
            b.setCarriage(product.getCarriage());
            b.setLastUpdateDate(new Date());

            saveToCartStatus = addProductToCart(basketMap, basketKey, b);
            session.setAttribute("BASKET_KEY", basketMap);
          }
          else if (!(this.stockService.canOrder(product, Integer.valueOf(b.getBasketCount().intValue() + count.intValue()))))
          {
            saveToCartStatus = SaveToCartStatusEnum.LESS;
          }
          else {
            b.setBasketCount(Integer.valueOf(b.getBasketCount().intValue() + count.intValue()));
            saveToCartStatus = addProductToCart(basketMap, basketKey, b);
            session.setAttribute("BASKET_KEY", basketMap);
          }
        }

        map.put("BASKET_COUNT", Integer.valueOf(basketMap.size()));
        map.put("BASKET_TOTAL_CASH", CommonServiceUtil.calculateTotalCash(basketMap));
      }
    }
    else
    {
      saveToCartStatus = this.basketService.saveToCart(userName, prodId, count, attribute);

      List baskets = this.basketService.getBasketByUserName(userName);

      Double totalcash = CommonServiceUtil.calculateTotalCash(baskets);

      map.put("BASKET_COUNT", Integer.valueOf(baskets.size()));
      map.put("BASKET_TOTAL_CASH", totalcash);
    }

    CommonServiceUtil.setBasketTotalCount(session, ((Integer)map.get("BASKET_COUNT")).intValue());
    map.put("status", saveToCartStatus.value());
    return map;
  }

  private SaveToCartStatusEnum addProductToCart(Map<String, Basket> basketMap, String key, Basket basket) {
    if (basketMap.size() < 50) {
      basketMap.put(key, basket);
      return SaveToCartStatusEnum.OK;
    }
    return SaveToCartStatusEnum.MAX;
  }

  public void setStockService(StockService stockService)
  {
    this.stockService = stockService;
  }
}
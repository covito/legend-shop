package com.legendshop.business.service.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.NavigationDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ProductTypeEnum;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefaultCommonPageServiceImpl extends AbstractCommonPageService
{
  public String getTop(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    ShopDetailView shopDetail = ThreadLocalContext.getShopDetailView(request, response, shopName);
    if (shopDetail == null) {
      return PathResolver.getPath(request, response, FrontPage.TOPALL);
    }

    request.setAttribute("sortList", this.sortDao.getSort(shopName, ProductTypeEnum.PRODUCT.value(), null, null, Boolean.valueOf(true)));

    request.setAttribute("newsSortList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_SORT, Integer.valueOf(8)));

    request.setAttribute("navigationList", this.navigationDao.getNavigationList());

    return PathResolver.getPath(request, response, FrontPage.TOP);
  }

  public String getTopUserInfo(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    String userName = UserManager.getUserName(request.getSession());

    request.setAttribute("newsTopList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_TOP, Integer.valueOf(8)));

    boolean shopExists = this.shopDetailDao.isShopExists(userName).booleanValue();

    request.setAttribute("canbeLeagueShop", this.shopDetailDao.isBeLeagueShop(shopExists, userName, shopName));

    HttpSession session = request.getSession();
    Integer baskettotalCount = (Integer)session.getAttribute("BASKET_TOTAL_COUNT");
    if (baskettotalCount == null) {
      Set basketKeySet = new HashSet();
      List basketList = this.basketDao.getBasketByUserName(userName);
      if (AppUtils.isNotBlank(basketList))
        for (Iterator localIterator1 = basketList.iterator(); localIterator1.hasNext(); ) { Basket basket = (Basket)localIterator1.next();
          basketKeySet.add(CommonServiceUtil.getBasketKey(basket.getShopName(), basket.getProdId(), basket.getAttribute()));
        }


      Map basketMap = (Map)session.getAttribute("BASKET_KEY");
      if (AppUtils.isNotBlank(basketMap))
        for (Iterator localIterator2 = basketMap.values().iterator(); localIterator2.hasNext(); ) { Basket basket = (Basket)localIterator2.next();
          basketKeySet.add(CommonServiceUtil.getBasketKey(basket.getShopName(), basket.getProdId(), basket.getAttribute()));
        }

      session.setAttribute("BASKET_TOTAL_COUNT", Integer.valueOf(basketKeySet.size()));
    }

    return PathResolver.getPath(request, response, FrontPage.TOP_USER_INFO);
  }
}
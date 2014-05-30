package com.legendshop.business.service.impl;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.business.dao.LogoDao;
import com.legendshop.business.dao.NavigationDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.service.CommonPageService;
import com.legendshop.spi.service.impl.AbstractService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommonPageService extends AbstractService
  implements CommonPageService
{
  protected NewsDao newsDao;
  protected SortDao sortDao;
  protected LogoDao logoDao;
  protected BasketDao basketDao;
  protected NavigationDao navigationDao;

  public String getCopy(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = PropertiesUtil.getDefaultShopName();
    request.setAttribute("newsBottomList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_BOTTOM, Integer.valueOf(8)));
    return PathResolver.getPath(request, response, FrontPage.COPY);
  }

  public void setNewsDao(NewsDao newsDao)
  {
    this.newsDao = newsDao;
  }

  public void setSortDao(SortDao sortDao)
  {
    this.sortDao = sortDao;
  }

  public void setLogoDao(LogoDao logoDao)
  {
    this.logoDao = logoDao;
  }

  public void setBasketDao(BasketDao basketDao) {
    this.basketDao = basketDao;
  }

  public void setNavigationDao(NavigationDao navigationDao) {
    this.navigationDao = navigationDao;
  }
}
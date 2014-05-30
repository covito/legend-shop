package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.core.helper.VisitHistoryHelper;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.dao.NsortDao;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.spi.form.SearchForm;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.BusinessService;
import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class BusinessServiceImpl extends BaseServiceImpl
  implements BusinessService
{
  private static Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);
  private NewsDao newsDao;
  private ExternalLinkDao externalLinkDao;
  private ProductDao productDao;
  private MyleagueDao myleagueDao;
  private final Long defaultInt = Long.valueOf(-4648543787084152832L);
  private SortDao sortDao;
  private NsortDao nsortDao;

  public String getFriendlink(HttpServletRequest request, HttpServletResponse response)
  {
    String name = ThreadLocalContext.getCurrentShopName(request, response);
    List adList = this.externalLinkDao.getExternalLink(name);
    if (!(AppUtils.isBlank(adList)))
      request.setAttribute("adList", adList);

    return PathResolver.getPath(request, response, FrontPage.FRIEND_LINK);
  }

  public String getLeague(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = ThreadLocalContext.getCurrentShopName(request, response);
    String curPageNO = AppUtils.getDefaultValue(request.getParameter("curPageNO"), "1");
    PageSupport ps = this.myleagueDao.getLeague(shopName, curPageNO);
    ps.savePage(request);
    getAndSetOneAdvertisement(request, response, ThreadLocalContext.getCurrentShopName(request, response), 
      "USER_REG_ADV_740");
    return PathResolver.getPath(request, response, TilesPage.LEAGUE);
  }

  public void getVisitedShop(HttpServletRequest request)
  {
    List shopIds = VisitHistoryHelper.getVisitedShopDetail(request);
    List shopDetails = new ArrayList();
    for (Iterator localIterator = shopIds.iterator(); localIterator.hasNext(); ) { Object userName = localIterator.next();
      shopDetails.add(this.shopDetailDao.getShopDetail((String)userName));
    }
    request.setAttribute("visitedShop", shopDetails);

    List prodIds = VisitHistoryHelper.getVisitedProd(request);
    List products = this.productDao.getProdDetail(prodIds);
    request.setAttribute("visitedProd", products);
  }

  @Required
  public void setShopDetailDao(ShopDetailDao shopDetailDao)
  {
    this.shopDetailDao = shopDetailDao;
  }

  @Required
  public void setNewsDao(NewsDao newsDao)
  {
    this.newsDao = newsDao;
  }

  @Required
  public void setExternalLinkDao(ExternalLinkDao externalLinkDao)
  {
    this.externalLinkDao = externalLinkDao;
  }

  public String getNewsforCommon(HttpServletRequest request, HttpServletResponse response)
  {
    String shopName = PropertiesUtil.getDefaultShopName();
    request.setAttribute("newsBottomList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_BOTTOM, Integer.valueOf(8)));
    return PathResolver.getPath(request, response, FrontPage.COPY);
  }

  @Required
  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  @Required
  public void setMyleagueDao(MyleagueDao myleagueDao)
  {
    this.myleagueDao = myleagueDao;
  }

  public String search(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm)
  {
    Sort sort = null;
    List nsortList = null;

    if (AppUtils.isBlank(searchForm.getKeyword())) {
      log.error("search keyword can't be null!");
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    }

    if ((!(AppUtils.isBlank(searchForm.getSortId()))) && (!(this.defaultInt.equals(searchForm.getSortId())))) {
      sort = this.sortDao.getSort(searchForm.getSortId());
      if (sort != null)
      {
        request.setAttribute("sort", sort);
        request.setAttribute("CurrentSortId", sort.getSortId());
        nsortList = this.nsortDao.getNsortBySortId(searchForm.getSortId());
        request.setAttribute("nsortList", nsortList);
      }
    }

    try
    {
      CriteriaQuery cq = new CriteriaQuery(Product.class, searchForm.getCurPageNOTop(), PageProviderEnum.SIMPLE_PAGE_PROVIDER);
      cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)).intValue() * 2);
      String shopName = ThreadLocalContext.getCurrentShopName(request, response);
      if (!(PropertiesUtil.getDefaultShopName().equals(shopName))) {
        cq.eq("userName", ThreadLocalContext.getCurrentShopName(request, response));
      }

      Criterion c = null;
      if (!(AppUtils.isBlank(searchForm.getKeyword()))) {
        String[] arrayOfString1;
        String[] keywords = AppUtils.searchByKeyword(searchForm.getKeyword());
        int j = (arrayOfString1 = keywords).length; for (int i = 0; i < j; ++i) { String word = arrayOfString1[i];
          Criterion temp = Restrictions.like("name", "%" + word + "%");
          if (c == null)
            c = temp;
          else
            c = Restrictions.or(c, temp);
        }
      }

      if ((!(AppUtils.isBlank(searchForm.getSortId()))) && (!(this.defaultInt.equals(searchForm.getSortId())))) {
        if (c == null)
          c = Restrictions.eq("sortId", searchForm.getSortId());
        else
          c = Restrictions.and(c, Restrictions.eq("sortId", searchForm.getSortId()));

      }

      if (c != null)
        cq.add(c);

      cq.addOrder("desc", "buys");
      cq.addOrder("desc", "views");
      cq.addOrder("desc", "modifyDate");

      PageSupport ps = this.productDao.getProdDetail(cq);
      ps.savePage(request);
      request.setAttribute("searchForm", searchForm);
    }
    catch (Exception e) {
      log.error("getProdDetail", e);
      return PathResolver.getPath(request, response, FowardPage.INDEX_QUERY);
    }
    return PathResolver.getPath(request, response, TilesPage.PRODUCT_SORT);
  }

  public void setSortDao(SortDao sortDao)
  {
    this.sortDao = sortDao;
  }

  public void setNsortDao(NsortDao nsortDao)
  {
    this.nsortDao = nsortDao;
  }
}
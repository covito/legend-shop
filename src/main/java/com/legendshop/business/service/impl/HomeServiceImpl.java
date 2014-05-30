package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.TagDao;
import com.legendshop.core.UserManager;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.constants.PageADV;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.SortDao;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.HomeService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeServiceImpl extends BaseServiceImpl
  implements HomeService
{
  private static Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
  private ProductDao productDao;
  private ImgFileDao imgFileDao;
  private SortDao sortDao;
  private PubDao pubDao;
  private TagDao tagDao;
  private NewsDao newsDao;
  private ExternalLinkDao externalLinkDao;

  public String getHome(HttpServletRequest request, HttpServletResponse response, ShopDetailView shopDetail)
  {
    String shopName = shopDetail.getUserName();

    request.setAttribute("commendProdList", this.productDao.getGlobalCommendProd(40));

    request.setAttribute("newestProdList", this.productDao.getGlobalNewestProd(11));

    request.setAttribute("pubList", this.pubDao.getPub(shopName));
    getAndSetAdvertisement(request, response, shopName, PageADV.INDEX.name());

    request.setAttribute("newList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_NEWS, Integer.valueOf(6)));

    List indexJpgList = this.imgFileDao.getIndexJpeg(shopName);
    request.setAttribute("indexJpgList", indexJpgList);
    if (!(AppUtils.isBlank(indexJpgList)))
    {
      request.setAttribute("maxScreen", Integer.valueOf(indexJpgList.size()));
      request.setAttribute("indexJSON", JSONUtil.getJson(indexJpgList));
    } else {
      request.setAttribute("maxScreen", Integer.valueOf(0));
    }

    request.setAttribute("externalLinkList", this.externalLinkDao.getExternalLink(shopName));

    String userName = UserManager.getUserName(request.getSession());

    request.setAttribute("tagList", this.tagDao.getPageTag(shopName, PageADV.INDEX.name()));

    request.setAttribute("showMenu", Boolean.valueOf(true));
    logUserAccess(request, shopName, userName);
    return PathResolver.getPath(request, response, TilesPage.HOME);
  }

  public void setProductDao(ProductDao productDao) {
    this.productDao = productDao;
  }

  public void setImgFileDao(ImgFileDao imgFileDao) {
    this.imgFileDao = imgFileDao;
  }

  public void setSortDao(SortDao sortDao) {
    this.sortDao = sortDao;
  }

  public void setPubDao(PubDao pubDao) {
    this.pubDao = pubDao;
  }

  public void setTagDao(TagDao tagDao) {
    this.tagDao = tagDao;
  }

  public void setNewsDao(NewsDao newsDao) {
    this.newsDao = newsDao;
  }

  public void setExternalLinkDao(ExternalLinkDao externalLinkDao) {
    this.externalLinkDao = externalLinkDao;
  }
}
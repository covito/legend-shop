package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ExternalLinkDao;
import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.business.dao.NewsDao;
import com.legendshop.business.dao.PubDao;
import com.legendshop.business.dao.SubDao;
import com.legendshop.business.dao.TagDao;
import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.UserManager;
import com.legendshop.model.UserInfo;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.constants.PageADV;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.service.IndexService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.JSONUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class IndexServiceImpl extends BaseServiceImpl
  implements IndexService
{
  private static Logger log = LoggerFactory.getLogger(IndexServiceImpl.class);
  private UserCommentDao userCommentDao;
  private UserDetailDao userDetailDao;
  private NewsDao newsDao;
  private ExternalLinkDao externalLinkDao;
  private ProductDao productDao;
  private ImgFileDao imgFileDao;
  private SubDao subDao;
  private PubDao pubDao;
  private TagDao tagDao;

  public void getIndex(HttpServletRequest request, HttpServletResponse response, ShopDetailView shopDetail)
  {
    String shopName = shopDetail.getUserName();
    List commendProdList = this.productDao.getCommendProd(shopName, 40);
    request.setAttribute("commendProdList", commendProdList);

    request.setAttribute("newestProdList", this.productDao.getNewestProd(shopName, 11));

    request.setAttribute("pubList", this.pubDao.getPub(shopName));
    getAndSetAdvertisement(request, response, shopName, PageADV.INDEX.name());

    request.setAttribute("newList", this.newsDao.getNews(shopName, NewsPositionEnum.NEWS_NEWS, Integer.valueOf(6)));

    List indexJpgList = this.imgFileDao.getIndexJpeg(shopName);
    if (!(AppUtils.isBlank(indexJpgList)))
    {
      request.setAttribute("maxScreen", Integer.valueOf(indexJpgList.size()));
      request.setAttribute("indexJSON", JSONUtil.getJson(indexJpgList));
    } else {
      request.setAttribute("maxScreen", Integer.valueOf(0));
    }

    request.setAttribute("externalLinkList", this.externalLinkDao.getExternalLink(shopName));

    String userName = UserManager.getUserName(request.getSession());

    if (!("default".equals(shopDetail.getFrontEndStyle())))
      request.setAttribute("tagList", this.tagDao.getPageTag(shopName, PageADV.INDEX.name()));

    request.setAttribute("showMenu", Boolean.valueOf(true));
    logUserAccess(request, shopName, userName);
  }

  public UserInfo getAdminIndex(String userName, ShopDetailView shopDetail)
  {
    UserInfo userInfo = new UserInfo();

    if (shopDetail != null)
    {
      userInfo.setArticleNum(this.newsDao.getAllNews(userName));

      userInfo.setProcessingOrderNum(this.subDao.getTotalProcessingOrder(userName));
      userInfo.setUnReadMessageNum(this.userCommentDao.getTotalUnReadMessage(userName));

      userInfo.setShopDetail(shopDetail);
    } else {
      userInfo.setUserTotalNum(this.userDetailDao.getAllUserCount());

      userInfo.setShopTotalNum(this.shopDetailDao.getAllShopCount());
    }

    return userInfo;
  }

  @Required
  public void setNewsDao(NewsDao newsDao)
  {
    this.newsDao = newsDao;
  }

  @Required
  public void setSubDao(SubDao subDao)
  {
    this.subDao = subDao;
  }

  @Required
  public void setUserCommentDao(UserCommentDao userCommentDao)
  {
    this.userCommentDao = userCommentDao;
  }

  @Required
  public void setUserDetailDao(UserDetailDao userDetailDao)
  {
    this.userDetailDao = userDetailDao;
  }

  public UserCommentDao getUserCommentDao()
  {
    return this.userCommentDao;
  }

  public void setExternalLinkDao(ExternalLinkDao externalLinkDao)
  {
    this.externalLinkDao = externalLinkDao;
  }

  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  public void setImgFileDao(ImgFileDao imgFileDao)
  {
    this.imgFileDao = imgFileDao;
  }

  public void setPubDao(PubDao pubDao)
  {
    this.pubDao = pubDao;
  }

  public void setTagDao(TagDao tagDao)
  {
    this.tagDao = tagDao;
  }
}
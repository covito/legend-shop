package com.legendshop.business.controller;

import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.News;
import com.legendshop.model.entity.NewsCategory;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.spi.constants.PageADV;
import com.legendshop.spi.page.FrontPage;
import com.legendshop.spi.page.TilesPage;
import com.legendshop.spi.service.NewsCategoryService;
import com.legendshop.spi.service.NewsService;
import com.legendshop.util.AppUtils;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController extends BaseController
{

  @Autowired
  private NewsService newsService;

  @Autowired
  private NewsCategoryService newsCategoryService;

  @RequestMapping({"/topnews"})
  public String topnews(HttpServletRequest request, HttpServletResponse response)
  {
    String name = ThreadLocalContext.getCurrentShopName(request, response);

    String topsortnews = request.getParameter("topsortnews");
    if (topsortnews != null) {
      request.setAttribute(
        "newList", 
        this.newsService.getNews(name, NewsPositionEnum.NEWS_NEWS, 
        (Integer)PropertiesUtil.getObject(SysParameterEnum.FRONT_PAGE_SIZE, Integer.class)));
      return PathResolver.getPath(request, response, FrontPage.TOP_SORT_NEWS);
    }
    request.setAttribute("newList", this.newsService.getNews(name, NewsPositionEnum.NEWS_NEWS, Integer.valueOf(6)));
    return PathResolver.getPath(request, response, FrontPage.TOP_NEWS);
  }

  @RequestMapping({"/news/{newsId}"})
  public String news(HttpServletRequest request, HttpServletResponse response, @PathVariable Long newsId)
  {
    if (newsId != null) {
      News news = this.newsService.getNewsById(newsId);
      if (news != null)
      {
        this.newsService.getAndSetAdvertisement(request, response, news.getUserName(), PageADV.NEWS.name());
        request.setAttribute("news", news);

        Map newsCatList = this.newsService.getNewsByCategory(news.getUserName());
        request.setAttribute("newsCatList", newsCatList);
      }
    }

    this.newsService.getAndSetOneAdvertisement(request, response, ThreadLocalContext.getCurrentShopName(request, response), 
      "USER_REG_ADV_740");

    return PathResolver.getPath(request, response, TilesPage.NEWS);
  }

  @RequestMapping({"/allnews"})
  public String allNews(HttpServletRequest request, HttpServletResponse response, String curPageNO, Long newsCategoryId)
  {
    if (AppUtils.isBlank(curPageNO))
      curPageNO = "1";

    String shopName = ThreadLocalContext.getCurrentShopName(request, response);

    PageSupport ps = this.newsService.getNews(curPageNO, shopName, newsCategoryId);
    ps.savePage(request);
    request.setAttribute("newsCategoryId", newsCategoryId);
    this.newsService.getAndSetOneAdvertisement(request, response, shopName, "USER_REG_ADV_740");
    if (newsCategoryId != null) {
      NewsCategory newsCategory = this.newsCategoryService.getNewsCategoryById(newsCategoryId);
      if (newsCategory != null)
        request.setAttribute("newsCategoryName", newsCategory.getNewsCategoryName());

    }

    Map newsCatList = this.newsService.getNewsByCategory(shopName);
    request.setAttribute("newsCatList", newsCatList);
    return PathResolver.getPath(request, response, TilesPage.ALL_NEWS);
  }
}
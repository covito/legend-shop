package com.legendshop.business.controller;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.core.UserManager;
import com.legendshop.core.base.BaseController;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.PathResolver;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.core.helper.ResourceBundleHelper;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.page.BackPage;
import com.legendshop.spi.page.FowardPage;
import com.legendshop.spi.service.NewsService;
import com.legendshop.util.AppUtils;
import com.legendshop.util.CodeFilter;
import com.legendshop.util.SafeHtml;
import com.legendshop.util.sql.ConfigCode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin/news"})
public class NewsAdminController extends BaseController
{
  private final Logger log = LoggerFactory.getLogger(NewsAdminController.class);

  @Autowired
  private NewsService newsService;

  @RequestMapping({"/query/{position}"})
  public String query(HttpServletRequest request, HttpServletResponse response, String curPageNO, News news, @PathVariable Integer position)
  {
    String userName = UserManager.getUserName(request.getSession());
    Map map = new HashMap();
    HqlQuery hql = new HqlQuery(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue(), curPageNO, PageProviderEnum.PAGE_PROVIDER);

    if (!(CommonServiceUtil.haveViewAllDataFunction(request))) {
      map.put("userName", userName);
      hql.addParams(userName);
    }
    else if (AppUtils.isNotBlank(news.getUserName())) {
      map.put("userName", news.getUserName());
      hql.addParams(news.getUserName());
    }

    if (!(AppUtils.isBlank(news.getNewsCategoryId()))) {
      map.put("newsCategoryId", String.valueOf(news.getNewsCategoryId()));
      hql.addParams(news.getNewsCategoryId());
    }

    if (!(AppUtils.isBlank(news.getSortId()))) {
      map.put("sortId", String.valueOf(news.getSortId()));
      hql.addParams(news.getSortId());
    }

    if (!(AppUtils.isBlank(news.getNewsTitle()))) {
      map.put("newsTitle", news.getNewsTitle());
      hql.addParams("%" + news.getNewsTitle() + "%");
    }
    if (!(AppUtils.isBlank(news.getStatus()))) {
      map.put("status", String.valueOf(news.getStatus()));
      hql.addParams(news.getStatus());
    }

    map.put("position", position.toString());
    hql.addParams(position);

    if (!(CommonServiceUtil.isDataForExport(hql, request)))
    {
      hql.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    }
    if (!(CommonServiceUtil.isDataSortByExternal(hql, request, map))) {
      map.put("orderIndicator", "order by n.newsDate desc");
    }

    String QueryNsortCount = ConfigCode.getInstance().getCode("biz.QueryNewsCount", map);
    String QueryNsort = ConfigCode.getInstance().getCode("biz.QueryNews", map);
    hql.setAllCountString(QueryNsortCount);
    hql.setQueryString(QueryNsort);

    PageSupport ps = this.newsService.getNewsList(hql);
    ps.savePage(request);
    news.setPosition(position);
    request.setAttribute("news", news);
    return PathResolver.getPath(request, response, BackPage.NEWS_LIST_PAGE);
  }

  @RequestMapping({"/save"})
  public String save(HttpServletRequest request, HttpServletResponse response, News news)
  {
    if (news.getNewsId() != null) {
      return update(request, response, news);
    }

    news.setNewsDate(new Date());
    news.setUserId(UserManager.getUserId(request.getSession()));
    news.setUserName(UserManager.getUserName(request.getSession()));
    SafeHtml safe = new SafeHtml();
    news.setNewsBrief(safe.makeSafe(news.getNewsBrief()));
    news.setNewsTitle(safe.makeSafe(news.getNewsTitle()));
    this.newsService.save(news);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    String result = PathResolver.getPath(request, response, 
      FowardPage.NEWS_LIST_QUERY.getNativeValue() + "/" + news.getPosition(), FowardPage.VARIABLE);
    return result;
  }

  private void setNewsBrief(News news)
  {
    String newsContent = news.getNewsContent();
    if ((newsContent != null) && (newsContent.length() > 0)) {
      Integer len = Integer.valueOf(newsContent.length());
      int maxLength = 100;
      boolean max = len.intValue() > maxLength;
      if (max)
        news.setNewsBrief(CodeFilter.unHtml(news.getNewsContent().substring(0, maxLength)) + "...");
      else
        news.setNewsBrief(CodeFilter.unHtml(newsContent));
    }
  }

  @RequestMapping({"/delete/{id}"})
  public String delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id)
  {
    News news = this.newsService.getNewsById(id);
    if (news != null) {
      String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), news.getUserName());
      if (result != null)
        return result;

      this.log.info("{},delete News Title{}", news.getUserName(), news.getNewsTitle());
      this.newsService.delete(id);
    }
    saveMessage(request, ResourceBundleHelper.getDeleteString());
    return PathResolver.getPath(request, response, FowardPage.NEWS_LIST_QUERY.getNativeValue() + "/" + news.getPosition(), 
      FowardPage.VARIABLE);
  }

  @RequestMapping({"/load/{position}/{id}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer position, @PathVariable Long id)
  {
    News news = this.newsService.getNewsById(id);
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), news.getUserName());
    if (result != null)
      return result;

    news.setPosition(position);
    request.setAttribute("news", news);
    return PathResolver.getPath(request, response, BackPage.NEWS_EDIT_PAGE);
  }

  @RequestMapping({"/load/{position}"})
  public String load(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer position) {
    request.setAttribute("position", position);
    return PathResolver.getPath(request, response, BackPage.NEWS_EDIT_PAGE);
  }

  public String update(HttpServletRequest request, HttpServletResponse response, News news)
  {
    News origin = this.newsService.getNewsById(news.getNewsId());
    String result = checkPrivilege(request, UserManager.getUserName(request.getSession()), origin.getUserName());
    if (result != null)
      return result;

    this.log.info("{} update News Title{}", origin.getUserName(), origin.getNewsTitle());

    SafeHtml safe = new SafeHtml();
    origin.setHighLine(news.getHighLine());
    origin.setNewsCategoryId(news.getNewsCategoryId());
    origin.setNewsDate(new Date());
    origin.setStatus(news.getStatus());
    origin.setSortId(news.getSortId());

    origin.setNewsBrief(safe.makeSafe(news.getNewsBrief()));
    origin.setNewsTitle(safe.makeSafe(news.getNewsTitle()));

    this.newsService.update(origin);
    saveMessage(request, ResourceBundleHelper.getSucessfulString());
    return PathResolver.getPath(request, response, FowardPage.NEWS_LIST_QUERY.getNativeValue() + "/" + news.getPosition(), 
      FowardPage.VARIABLE);
  }

  @RequestMapping(value={"/updatestatus/{newsId}/{status}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Integer updateStatus(HttpServletRequest request, HttpServletResponse response, @PathVariable Long newsId, @PathVariable Integer status)
  {
    News news = this.newsService.getNewsById(newsId);
    if (news == null)
      return Integer.valueOf(-1);

    if (!(status.equals(news.getStatus())))
      if (!(FoundationUtil.haveViewAllDataFunction(request))) {
        String loginName = UserManager.getUserName(request.getSession());

        if (!(loginName.equals(news.getUserName())))
          return Integer.valueOf(-1);

        if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status))) {
          news.setStatus(status);
          news.setNewsDate(new Date());
          this.newsService.update(news);
        }

      }
      else if ((Constants.ONLINE.equals(status)) || (Constants.OFFLINE.equals(status)) || (Constants.STOPUSE.equals(status))) {
        news.setStatus(status);
        news.setNewsDate(new Date());
        this.newsService.update(news);
      }


    return news.getStatus();
  }
}
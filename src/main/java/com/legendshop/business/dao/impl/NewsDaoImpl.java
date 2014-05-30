package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.NewsDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.event.FireEvent;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.entity.News;
import com.legendshop.spi.constants.Constants;
import com.legendshop.spi.constants.NewsPositionEnum;
import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class NewsDaoImpl extends BaseDaoImpl
  implements NewsDao
{
  private static Logger log = LoggerFactory.getLogger(NewsDaoImpl.class);

  @Cacheable({"NewsList"})
  public List<News> getNews(String shopName, NewsPositionEnum newsPositionEnum, Integer num)
  {
    if (shopName == null) {
      log.warn("shopName is null");
      return null;
    }
    List list = parseNews(shopName, newsPositionEnum, num);
    if (AppUtils.isBlank(list))
      list = parseNews(PropertiesUtil.getDefaultShopName(), newsPositionEnum, num);

    return list;
  }

  private List<News> parseNews(String shopName, NewsPositionEnum newsPositionEnum, Integer num)
  {
    CriteriaQuery cq = new CriteriaQuery(News.class);
    cq = new CriteriaQuery(News.class);
    cq.eq("userName", shopName);
    cq.eq("position", newsPositionEnum.value());
    cq.eq("status", Constants.ONLINE);
    cq.addOrder("desc", "newsDate");
    List list = findListByCriteria(cq, 0, num.intValue());
    return list;
  }

  public News getNewsById(Long newsId)
  {
    return ((News)get(News.class, newsId));
  }

  @Cacheable(value={"NewsList"}, condition="T(Integer).parseInt(#curPageNO) < 3")
  public PageSupport getNews(String curPageNO, String userName, Long newsCategoryId)
  {
    CriteriaQuery cq = new CriteriaQuery(News.class, curPageNO, PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    cq.setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
    cq.eq("status", Constants.ONLINE);
    cq.eq("position", NewsPositionEnum.NEWS_NEWS.value());
    cq.eq("userName", userName);
    cq.eq("newsCategory.newsCategoryId", newsCategoryId);
    cq.addOrder("desc", "highLine");
    cq.addOrder("desc", "newsDate");
    PageSupport ps = find(cq);
    return ps;
  }

  @CacheEvict(value={"News"}, key="#news.newsId")
  public void updateNews(News news)
  {
    EventHome.publishEvent(new FireEvent(news, OperationTypeEnum.UPDATE));
    update(news);
  }

  @CacheEvict(value={"News"}, key="#id")
  public void deleteNewsById(Long id)
  {
    News news = getNewsById(id);
    if (news != null) {
      EventHome.publishEvent(new FireEvent(news, OperationTypeEnum.DELETE));
      delete(news);
    }
  }

  public Long getAllNews(String userName)
  {
    return ((Long)findUniqueBy("select count(*) from News where userName = ?", Long.class, new Object[] { userName }));
  }

  @Cacheable({"NewsList"})
  public Map<KeyValueEntity, List<News>> getNewsByCategory(String userName)
  {
    String sql = "select new News(n.newsId, c.newsCategoryId,c.newsCategoryName, n.newsTitle) from News n,NewsCategory c where c.newsCategoryId = n.newsCategory.newsCategoryId and n.status = 1 and c.status = 1 and  n.position = 1 and n. userName =?  order by c.newsCategoryId,n.highLine, n.newsDate desc";
    List newsList = findByHQLLimit(sql, 0, 50, new Object[] { userName });
    Map newsMap = null;
    if (AppUtils.isNotBlank(newsList)) {
      newsMap = new HashMap();

      for (Iterator localIterator = newsList.iterator(); localIterator.hasNext(); ) { News news = (News)localIterator.next();
        KeyValueEntity keyValueEntity = new KeyValueEntity(news.getNewsCategoryId().toString(), news.getNewsCategoryName());
        List newsCatList = (List)newsMap.get(keyValueEntity);
        if (newsCatList == null)
          newsCatList = new ArrayList();

        newsCatList.add(news);
        newsMap.put(keyValueEntity, newsCatList);
      }
    }
    return newsMap;
  }
}
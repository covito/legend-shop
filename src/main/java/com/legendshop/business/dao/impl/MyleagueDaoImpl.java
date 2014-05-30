package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.MyleagueDao;
import com.legendshop.core.constant.PageProviderEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Myleague;
import com.legendshop.util.sql.ConfigCode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class MyleagueDaoImpl extends BaseDaoImpl
  implements MyleagueDao
{
  private BaseJdbcDao baseJdbcDao;

  @Cacheable(value={"MyleagueList"}, condition="T(Integer).parseInt(#curPageNO) < 3")
  public PageSupport getLeague(String shopName, String curPageNO)
  {
    Map map = new HashMap();
    String queryAllSQL = null;
    String querySQL = null;
    if (!(PropertiesUtil.isInDefaultShop(shopName))) {
      map.put("shopName", shopName);
      queryAllSQL = ConfigCode.getInstance().getCode("biz.QueryLeagueCount", map);
      querySQL = ConfigCode.getInstance().getCode("biz.QueryLeague", map);
    } else {
      queryAllSQL = ConfigCode.getInstance().getCode("biz.QueryAllLeagueCount", map);
      querySQL = ConfigCode.getInstance().getCode("biz.QueryAllLeague", map);
    }
    if (map.size() > 0);
    SimpleSqlQuery query = new SimpleSqlQuery(Myleague.class, querySQL, queryAllSQL, 
      null);

    query.setPageSize(15);
    query.setCurPage(curPageNO);
    query.setPageProvider(PageProviderEnum.SIMPLE_PAGE_PROVIDER);
    return this.baseJdbcDao.find(query);
  }

  @CacheEvict(value={"Myleague"}, key="#id")
  public void deleteMyleagueById(Long id)
  {
    deleteById(Myleague.class, id);
  }

  @CacheEvict(value={"Myleague"}, key="#myleague.id")
  public void updateMyleague(Myleague myleague)
  {
    update(myleague);
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao)
  {
    this.baseJdbcDao = baseJdbcDao;
  }

  public Myleague getMyleague(String userName, String shopName)
  {
    return ((Myleague)findUniqueBy("from Myleague where userId = ? and friendId = ?", Myleague.class, new Object[] { userName, shopName }));
  }
}
package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.LogoDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.entity.ShopDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogoDaoImpl extends BaseDaoImpl
  implements LogoDao
{
  private static Logger log = LoggerFactory.getLogger(LogoDaoImpl.class);
  private JdbcTemplate jdbcTemplate;

  public void deleteLogo(ShopDetail shopDetail)
  {
    updateLogo(shopDetail.getUserId(), shopDetail.getUserName(), null);
  }

  public void updateLogo(ShopDetail shopDetail)
  {
    updateLogo(shopDetail.getUserId(), shopDetail.getUserName(), shopDetail.getLogoPic());
  }

  private void updateLogo(String userId, String userName, String logoPic) {
    this.jdbcTemplate.update("update ls_shop_detail set logo_pic = ? where user_id = ?", new Object[] { logoPic, userId });
  }

  public PageSupport getLogo(SimpleHqlQuery hql)
  {
    hql.initSQL("biz.QueryLogo", "biz.QueryLogoCount");
    return find(hql);
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }
}
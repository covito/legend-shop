package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Myleague;

public abstract interface MyleagueDao extends BaseDao
{
  public abstract PageSupport getLeague(String paramString1, String paramString2);

  public abstract void deleteMyleagueById(Long paramLong);

  public abstract void updateMyleague(Myleague paramMyleague);

  public abstract Myleague getMyleague(String paramString1, String paramString2);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Myleague;
import java.util.List;

public abstract interface MyleagueService
{
  public abstract List<Myleague> getMyleagueList(String paramString);

  public abstract Myleague getMyleagueById(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(Myleague paramMyleague);

  public abstract void update(Myleague paramMyleague);

  public abstract PageSupport getMyleagueList(CriteriaQuery paramCriteriaQuery);

  public abstract Myleague getMyleague(String paramString1, String paramString2);
}
package com.legendshop.business.dao;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Sub;
import java.util.Date;
import java.util.List;

public abstract interface SubDao extends SubCommonDao
{
  public abstract void saveSub(Sub paramSub);

  public abstract boolean deleteSub(Sub paramSub);

  public abstract void deleteSub(String paramString);

  public abstract boolean updateSubPrice(Sub paramSub, String paramString, Double paramDouble);

  public abstract Sub getSubById(Long paramLong);

  public abstract Sub getSubBySubNumber(String paramString);

  public abstract boolean updateSub(Sub paramSub, Integer paramInteger, String paramString1, String paramString2);

  public abstract List<Basket> getBasketBySubNumber(String paramString);

  public abstract List<Sub> getFinishUnPay(int paramInt, Date paramDate);

  public abstract List<Sub> getUnAcklodgeSub(int paramInt, Date paramDate);

  public abstract void deleteOverTimeBasket(Date paramDate);

  public abstract void updateSub(Sub paramSub);

  public abstract PageSupport getOrder(CriteriaQuery paramCriteriaQuery);

  public abstract Long getTotalProcessingOrder(String paramString);

  public abstract boolean isUserOrderProduct(Long paramLong, String paramString);

  public abstract PageSupport getOrder(SimpleSqlQuery paramSimpleSqlQuery);
}
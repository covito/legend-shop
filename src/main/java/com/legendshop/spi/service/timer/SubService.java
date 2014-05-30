package com.legendshop.spi.service.timer;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.SubForm;
import com.legendshop.model.SubQueryForm;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Sub;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface SubService
{
  public abstract void finishUnPay();

  public abstract void finishUnAcklodge();

  public abstract void removeOverTimeBasket();

  public abstract List<Sub> saveSub(SubForm paramSubForm);

  public abstract List<Basket> getBasketBySubNumber(String paramString);

  public abstract Sub getSubBySubNumber(String paramString);

  public abstract void updateSub(Sub paramSub);

  public abstract PageSupport getOrderList(CriteriaQuery paramCriteriaQuery);

  public abstract String getOrderDetail(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Sub paramSub, String paramString1, String paramString2);

  public abstract Long getTotalProcessingOrder(String paramString);

  public abstract Sub getSubById(Long paramLong);

  public abstract boolean updateSubPrice(Sub paramSub, String paramString, Double paramDouble);

  public abstract boolean updateSub(Sub paramSub, Integer paramInteger, String paramString1, String paramString2);

  public abstract boolean deleteSub(Sub paramSub);

  public abstract void saveSubHistory(Sub paramSub, String paramString);

  public abstract String findOrder(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, Sub paramSub, String paramString2, SubQueryForm paramSubQueryForm);
}
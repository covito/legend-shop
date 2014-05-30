package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Basket;
import com.legendshop.spi.constants.SaveToCartStatusEnum;
import java.util.List;
import java.util.Map;

public abstract interface BasketDao extends BaseDao
{
  public abstract void deleteBasketById(Long paramLong);

  public abstract List<Basket> getBasketByUserName(String paramString);

  public abstract Long getTotalBasketByUserName(String paramString);

  public abstract Map<String, List<Basket>> getBasketGroupByName(String paramString);

  public abstract Map<String, List<Basket>> getBasketGroupById(Long[] paramArrayOfLong);

  public abstract Basket getBasketById(Long paramLong);

  public abstract Basket getBasketByIdName(Long paramLong, String paramString1, String paramString2, String paramString3);

  public abstract Long getBasketByUserName(String paramString1, String paramString2);

  public abstract Long saveBasket(Basket paramBasket);

  public abstract void updateBasket(Basket paramBasket);

  public abstract List<Basket> getBasket(String paramString1, String paramString2);

  public abstract void deleteBasketByUserName(String paramString);

  public abstract void deleteBasketBySubNumber(String paramString);

  public abstract SaveToCartStatusEnum saveToCart(String paramString1, Long paramLong, Integer paramInteger, String paramString2);

  public abstract void deleteBasket(String paramString);

  public abstract void deleteUserBasket(String paramString);

  public abstract List<Basket> getBasketListById(Long[] paramArrayOfLong);
}
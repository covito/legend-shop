package com.legendshop.spi.service;

import com.legendshop.model.entity.Basket;
import com.legendshop.spi.constants.SaveToCartStatusEnum;
import java.util.List;

public abstract interface BasketService
{
  public abstract void saveToCart(Long paramLong, String paramString1, String paramString2, String paramString3, Integer paramInteger);

  public abstract void deleteBasketByUserName(String paramString);

  public abstract void deleteBasketById(Long paramLong);

  public abstract List<Basket> getBasketByUserName(String paramString);

  public abstract Long getTotalBasketByUserName(String paramString);

  public abstract SaveToCartStatusEnum saveToCart(String paramString1, Long paramLong, Integer paramInteger, String paramString2);

  public abstract List<Basket> getBasketListById(Long[] paramArrayOfLong);

  public abstract void updateBasket(Basket paramBasket);
}
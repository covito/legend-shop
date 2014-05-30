package com.legendshop.spi.service;

import com.legendshop.model.entity.Product;

public abstract interface StockService
{
  public abstract boolean addHold(Long paramLong, Integer paramInteger);

  public abstract void releaseHold(Long paramLong, Integer paramInteger);

  public abstract void addStock(Long paramLong, Integer paramInteger);

  public abstract void reduceStock(Long paramLong, Integer paramInteger);

  public abstract boolean canOrder(Product paramProduct, Integer paramInteger);

  public abstract void addBuys(Long paramLong, Integer paramInteger);
}
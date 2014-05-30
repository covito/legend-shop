package com.legendshop.spi.service;

import com.legendshop.model.entity.Sub;
import java.util.Map;

public abstract interface ScoreService
{
  public abstract void addScore(Sub paramSub);

  public abstract Map<String, Object> useScore(Sub paramSub, Long paramLong);

  public abstract Long calScore(Double paramDouble, String paramString);

  public abstract Double calMoney(Long paramLong);
}
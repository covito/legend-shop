package com.legendshop.business.dao;

import com.legendshop.model.entity.Sub;
import java.util.Map;

public abstract interface ScoreDao extends SubCommonDao
{
  public abstract void saveScore(Sub paramSub);

  public abstract Map<String, Object> deleteScore(Sub paramSub, Long paramLong);

  public abstract Long calScore(Double paramDouble, String paramString);

  public abstract Double calMoney(Long paramLong);
}
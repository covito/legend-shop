package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ScoreDao;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.service.ScoreService;
import java.util.Map;

public class ScoreServiceImpl
  implements ScoreService
{
  private ScoreDao scoreDao;

  public void addScore(Sub sub)
  {
    this.scoreDao.saveScore(sub);
  }

  public Map<String, Object> useScore(Sub sub, Long avaibleScore)
  {
    return this.scoreDao.deleteScore(sub, avaibleScore);
  }

  public Long calScore(Double total, String scoreType)
  {
    return this.scoreDao.calScore(total, scoreType);
  }

  public Double calMoney(Long score)
  {
    return this.scoreDao.calMoney(score);
  }

  public void setScoreDao(ScoreDao scoreDao)
  {
    this.scoreDao = scoreDao;
  }
}
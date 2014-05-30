package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SensitiveWord;
import java.util.List;

public abstract interface SensitiveWordService
{
  public abstract List<SensitiveWord> getSensitiveWord(String paramString);

  public abstract SensitiveWord getSensitiveWord(Long paramLong);

  public abstract void deleteSensitiveWord(SensitiveWord paramSensitiveWord);

  public abstract Long saveSensitiveWord(SensitiveWord paramSensitiveWord);

  public abstract void updateSensitiveWord(SensitiveWord paramSensitiveWord);

  public abstract PageSupport getSensitiveWord(CriteriaQuery paramCriteriaQuery);

  public abstract String newcontainSensitiveWords(String paramString, Long paramLong1, Long paramLong2, Long paramLong3);
}
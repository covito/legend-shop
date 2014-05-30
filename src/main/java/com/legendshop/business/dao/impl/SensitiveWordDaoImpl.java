package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.SensitiveWordDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.SensitiveWord;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SensitiveWordDaoImpl extends BaseDaoImpl
  implements SensitiveWordDao
{
  private static Logger log = LoggerFactory.getLogger(SensitiveWordDaoImpl.class);

  public List<SensitiveWord> getSensitiveWord(String userName)
  {
    return findByHQL("from SensitiveWord where userName = ?", new Object[] { userName });
  }

  public SensitiveWord getSensitiveWord(Long id) {
    return ((SensitiveWord)get(SensitiveWord.class, id));
  }

  public void deleteSensitiveWord(SensitiveWord sensitiveWord) {
    delete(sensitiveWord);
  }

  public Long saveSensitiveWord(SensitiveWord sensitiveWord) {
    return ((Long)save(sensitiveWord));
  }

  public void updateSensitiveWord(SensitiveWord sensitiveWord) {
    update(sensitiveWord);
  }

  public PageSupport getSensitiveWord(CriteriaQuery cq) {
    return find(cq);
  }

  public List<String> getWords(Long sortId, Long nsortId, Long subNsortId)
  {
    List list = findByHQL("select words from SensitiveWord where sortId = ? and nsortId = ? and subNsortId = ?", new Object[] { sortId, nsortId, subNsortId });
    return list;
  }
}
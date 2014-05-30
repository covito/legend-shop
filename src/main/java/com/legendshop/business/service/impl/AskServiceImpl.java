package com.legendshop.business.service.impl;

import com.legendshop.business.dao.AskDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.Ask;
import com.legendshop.spi.service.AskService;
import com.legendshop.util.AppUtils;
import java.util.List;

public class AskServiceImpl
  implements AskService
{
  private AskDao askDao;

  public void setAskDao(AskDao askDao)
  {
    this.askDao = askDao;
  }

  public List<Ask> getAsk(String userName) {
    return this.askDao.getAsk(userName);
  }

  public Ask getAsk(Long id) {
    return this.askDao.getAsk(id);
  }

  public void deleteAsk(Ask ask) {
    this.askDao.deleteAsk(ask);
  }

  public Long saveAsk(Ask ask) {
    if (!(AppUtils.isBlank(ask.getAskId()))) {
      updateAsk(ask);
      return ask.getAskId();
    }
    return ((Long)this.askDao.save(ask));
  }

  public void updateAsk(Ask ask) {
    this.askDao.updateAsk(ask);
  }

  public PageSupport getAsk(CriteriaQuery cq) {
    return this.askDao.find(cq);
  }
}
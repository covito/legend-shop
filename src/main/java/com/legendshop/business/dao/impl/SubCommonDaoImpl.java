package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.SubCommonDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.Sub;
import com.legendshop.model.entity.SubHistory;
import com.legendshop.util.BeanHelper;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubCommonDaoImpl extends BaseDaoImpl
  implements SubCommonDao
{
  private static Logger log = LoggerFactory.getLogger(SubCommonDaoImpl.class);

  public void saveSubHistory(Sub sub, String subAction)
  {
    SubHistory history = new SubHistory();
    try {
      BeanHelper.copyProperties(history, sub);
    } catch (Exception e) {
      log.error("saveSubHistory", e);
    }
    history.setUpdateTime(new Date());
    history.setSubAction(subAction);
    save(history);
  }
}
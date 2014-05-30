package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.VisitLogDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisitLogDaoImpl extends BaseDaoImpl
  implements VisitLogDao
{
  private static Logger log = LoggerFactory.getLogger(VisitLogDaoImpl.class);

  public void updateVisitLog(VisitLog visitLog)
  {
    update(visitLog);
  }

  public void deleteVisitLogById(Long id)
  {
    deleteById(VisitLog.class, id);
  }

  public VisitLog getVisitedIndexLog(VisitLog visitLog)
  {
    String sql;
    List list = null;
    if (visitLog.getUserName() != null)
    {
      sql = "select v from VisitLog v where v.ip = ? and v.shopName = ? and v.userName = ?";
      list = findByHQLLimit(sql, 0, 1, new Object[] { visitLog.getIp(), visitLog.getShopName(), visitLog.getUserName() });
    }
    else {
      sql = "select v from VisitLog v where v.userName is null and v.ip = ? and v.shopName = ? ";
      list = findByHQLLimit(sql, 0, 1, new Object[] { visitLog.getIp(), visitLog.getShopName() });
    }

    if (AppUtils.isNotBlank(list))
      return ((VisitLog)list.get(0));

    return null;
  }

  public VisitLog getVisitedProdLog(VisitLog visitLog)
  {
    String sql;
    List list = null;
    if (visitLog.getUserName() != null)
    {
      sql = "select v from VisitLog v where v.ip = ? and v.userName = ? and v.productId = ? ";
      list = findByHQLLimit(sql, 0, 1, new Object[] { visitLog.getIp(), visitLog.getUserName(), visitLog.getProductId() });
    }
    else {
      sql = "select v from VisitLog v where  v.userName is null and v.ip = ? and v.productId = ? ";
      list = findByHQLLimit(sql, 0, 1, new Object[] { visitLog.getIp(), visitLog.getProductId() });
    }

    if (AppUtils.isNotBlank(list))
      return ((VisitLog)list.get(0));

    return null;
  }
}
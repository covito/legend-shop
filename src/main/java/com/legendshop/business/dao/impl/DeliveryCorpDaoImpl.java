package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.DeliveryCorpDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.DeliveryCorp;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryCorpDaoImpl extends BaseDaoImpl
  implements DeliveryCorpDao
{
  private BaseJdbcDao baseJdbcDao;
  private static Logger log = LoggerFactory.getLogger(DeliveryCorpDaoImpl.class);

  public List<DeliveryCorp> getDeliveryCorp(String userName)
  {
    return findByHQL("from DeliveryCorp where userName = ?", new Object[] { userName });
  }

  public DeliveryCorp getDeliveryCorp(Long id)
  {
    return ((DeliveryCorp)get(DeliveryCorp.class, id));
  }

  public void deleteDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    delete(deliveryCorp);
  }

  public Long saveDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    return ((Long)save(deliveryCorp));
  }

  public void updateDeliveryCorp(DeliveryCorp deliveryCorp)
  {
    update(deliveryCorp);
  }

  public PageSupport getDeliveryCorp(CriteriaQuery cq)
  {
    return find(cq);
  }

  public void deleteDeliveryCorp(String userName)
  {
    this.baseJdbcDao.deleteUserItem("ls_delivery", userName);
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao)
  {
    this.baseJdbcDao = baseJdbcDao;
  }
}
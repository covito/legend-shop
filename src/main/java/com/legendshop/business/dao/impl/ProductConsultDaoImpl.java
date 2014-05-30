package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ProductConsultDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.model.entity.ProductConsult;
import com.legendshop.util.DateUtil;
import java.util.Date;
import java.util.List;

public class ProductConsultDaoImpl extends BaseDaoImpl
  implements ProductConsultDao
{
  private BaseJdbcDao baseJdbcDao;
  private int interval = -5;

  public List<ProductConsult> getProductConsultList(Long prodId)
  {
    return findByHQL("from ProductConsult where id = ?", new Object[] { prodId });
  }

  public ProductConsult getProductConsult(Long id)
  {
    return ((ProductConsult)get(ProductConsult.class, id));
  }

  public void deleteProductConsult(Long id)
  {
    ProductConsult pc = getProductConsult(id);
    if (pc != null)
      delete(pc);
  }

  public Long saveProductConsult(ProductConsult productConsult)
  {
    return ((Long)save(productConsult));
  }

  public void updateProductConsult(ProductConsult productConsult)
  {
    update(productConsult);
  }

  public PageSupport getProductConsult(CriteriaQuery cq)
  {
    return find(cq);
  }

  public PageSupport getProductConsult(SimpleSqlQuery query)
  {
    return this.baseJdbcDao.find(query);
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao)
  {
    this.baseJdbcDao = baseJdbcDao;
  }

  public void deleteProductConsult(ProductConsult consult)
  {
    delete(consult);
  }

  public long checkFrequency(ProductConsult consult)
  {
    Date date = DateUtil.add(new Date(), 12, this.interval);
    String sql = "select count(*) from ls_prod_cons where point_type = ? and prod_id = ? and ask_user_name = ? and rec_date > ?";
    return this.baseJdbcDao.stat(sql, new Object[] { consult.getPointType(), consult.getProdId(), consult.getAskUserName(), date });
  }

  public void setInterval(int interval)
  {
    this.interval = interval;
  }
}
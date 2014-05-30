package com.legendshop.business.service.impl;

import com.legendshop.model.entity.Product;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.service.StockService;
import org.hibernate.LockMode;

public class StockServiceImpl
  implements StockService
{
  private ProductDao productDao;

  public boolean addHold(Long prodId, Integer basketCount)
  {
    Product product = (Product)this.productDao.getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
    if (product != null)
    {
      Integer stocks = product.getStocks();
      if (stocks == null)
        stocks = Integer.valueOf(0);

      if (stocks.intValue() - basketCount.intValue() < 0)
        return false;

      product.setStocks(Integer.valueOf(stocks.intValue() - basketCount.intValue()));
      this.productDao.updateOnly(product);
      return true;
    }

    return false;
  }

  public void releaseHold(Long prodId, Integer count)
  {
    Product product = (Product)this.productDao.getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
    if (product != null)
    {
      Integer stocks = product.getStocks();
      if (stocks == null)
        stocks = Integer.valueOf(0);

      product.setStocks(Integer.valueOf(stocks.intValue() + count.intValue()));
      this.productDao.updateOnly(product);
    }
  }

  public void addStock(Long prodId, Integer count)
  {
    Product product = (Product)this.productDao.getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
    if (product != null)
    {
      Integer stocks = product.getStocks();
      if (stocks == null)
        stocks = Integer.valueOf(0);

      Integer actualStocks = product.getActualStocks();
      if (actualStocks == null)
        actualStocks = Integer.valueOf(0);

      product.setStocks(Integer.valueOf(stocks.intValue() + count.intValue()));
      product.setActualStocks(Integer.valueOf(actualStocks.intValue() + count.intValue()));
      this.productDao.updateOnly(product);
    }
  }

  public void reduceStock(Long prodId, Integer count)
  {
    Product product = (Product)this.productDao.getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
    if (product != null)
    {
      Integer actualStocks = product.getActualStocks();
      if (actualStocks == null)
        actualStocks = product.getStocks();

      product.setActualStocks(Integer.valueOf(actualStocks.intValue() - count.intValue()));
      product.setBuys(Integer.valueOf(product.getBuys().intValue() + count.intValue()));
      this.productDao.updateOnly(product);
    }
  }

  public boolean canOrder(Product product, Integer count)
  {
    return ((product != null) && (((product.getStocks() == null) || (product.getStocks().intValue() >= count.intValue()))));
  }

  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  public void addBuys(Long prodId, Integer basketCount)
  {
    Product product = (Product)this.productDao.getByLockMode(Product.class, prodId, LockMode.UPGRADE_NOWAIT);
    if (product != null)
    {
      Integer buys = product.getBuys();
      if (buys == null)
        buys = Integer.valueOf(0);

      product.setBuys(Integer.valueOf(buys.intValue() + basketCount.intValue()));
      this.productDao.updateOnly(product);
    }
  }
}
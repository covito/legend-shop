package com.legendshop.business.dao.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.BasketDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.model.entity.Basket;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.constants.SaveToCartStatusEnum;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.service.StockService;
import com.legendshop.util.AppUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class BasketDaoImpl extends BaseDaoImpl
  implements BasketDao
{
  private static Logger log = LoggerFactory.getLogger(BasketDaoImpl.class);
  private ProductDao productDao;
  private BaseJdbcDao baseJdbcDao;
  private StockService stockService;

  public void deleteBasketById(Long basketId)
  {
    log.debug("deleteBasketById, basketId = {}", basketId);
    exeByHQL("delete from Basket where basketId=?", new Object[] { basketId });
  }

  public List<Basket> getBasketByUserName(String userName)
  {
    if (AppUtils.isBlank(userName))
      return null;

    log.debug("getBasketByuserName,userName = {}", userName);
    return findByHQL("from Basket where userName=? and basket_check=? order by basketDate desc", new Object[] { userName, 
      "N" });
  }

  public Long getTotalBasketByUserName(String userName)
  {
    return ((Long)findUniqueBy("select count(*) from Basket where userName=? and basket_check=?", Long.class, new Object[] { userName, 
      "N" }));
  }

  public Map<String, List<Basket>> getBasketGroupByName(String userName)
  {
    List list = findByHQL("from Basket where userName=? and basket_check=? order by basketDate desc", new Object[] { userName, 
      "N" });
    return convertBasketMap(list);
  }

  public Map<String, List<Basket>> getBasketGroupById(Long[] basketIdList)
  {
    List list = getBasketListById(basketIdList);
    return convertBasketMap(list);
  }

  public Basket getBasketById(Long id)
  {
    return ((Basket)get(Basket.class, id));
  }

  public List<Basket> getBasketListById(Long[] basketIdList)
  {
    StringBuffer whereCause = new StringBuffer("?");
    for (int i = 0; i < basketIdList.length - 1; ++i) {
      whereCause.append(",?");
    }

    whereCause.append(")");
    return findByHQL("from Basket where basketId in( " + whereCause.toString(), basketIdList);
  }

  public Basket getBasketByIdName(Long prodId, String userName, String shopName, String attribute)
  {
    return ((Basket)findUniqueBy("from Basket where prodId=? and userName=? and basket_check=? and shopName=? and attribute = ?", 
      Basket.class, new Object[] { prodId, userName, "N", shopName, attribute }));
  }

  public Long getBasketByUserName(String userName, String shopName)
  {
    return ((Long)findUniqueBy("select count(*) from Basket where userName=? and basket_check=? and shopName=?", Long.class, new Object[] { userName, 
      "N", shopName }));
  }

  public Long saveBasket(Basket basket)
  {
    return ((Long)save(basket));
  }

  public void updateBasket(Basket basket)
  {
    update(basket);
  }

  public List<Basket> getBasket(String prodId, String userName)
  {
    return findByHQL("from Basket where prodId = ? and userName = ? and basketCheck=?", new Object[] { prodId, userName, 
      "N" });
  }

  public void deleteBasketByUserName(String userName)
  {
    List list = findByHQL("from Basket where userName=? and basketCheck=?", new Object[] { userName, 
      "N" });
    if (!(AppUtils.isBlank(list)))
      deleteAll(list);
  }

  public void deleteBasketBySubNumber(String subNumber)
  {
    List list = findByHQL("from Basket where subNumber=?", new Object[] { subNumber });

    if (!(AppUtils.isBlank(list))) {
      for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Basket basket = (Basket)localIterator.next();
        this.stockService.releaseHold(basket.getProdId(), basket.getBasketCount());
      }
      deleteAll(list);
    }
  }

  public SaveToCartStatusEnum saveToCart(String userName, Long prodId, Integer count, String attribute)
  {
    Product product = this.productDao.getProduct(prodId);
    if (product == null) {
      log.error("saveToCart failed for prod does not exist");
      return SaveToCartStatusEnum.ERR;
    }
    if (product.getUserName().equals(userName)) {
      return SaveToCartStatusEnum.OWNER;
    }

    Long totalBasket = getTotalBasketByUserName(userName);
    if (totalBasket.longValue() >= 50L) {
      return SaveToCartStatusEnum.MAX;
    }

    Basket basket = getBasketByIdName(product.getProdId(), userName, product.getUserName(), attribute);
    if (basket == null)
    {
      if (!(this.stockService.canOrder(product, count)))
        return SaveToCartStatusEnum.LESS;

      Basket b = new Basket();
      b.setProdId(product.getProdId());
      b.setPic(product.getPic());
      b.setUserName(userName);
      b.setBasketCount(count);
      b.setProdName(product.getName());
      b.setCash(product.getCash());
      b.setAttribute(attribute);
      b.setCarriage(product.getCarriage());
      b.setBasketDate(new Date());
      b.setLastUpdateDate(b.getBasketDate());
      b.setBasketCheck("N");
      b.setShopName(product.getUserName());
      saveBasket(b);
      CommonServiceUtil.calBasketTotalCount(ThreadLocalContext.getRequest().getSession(), -1);
    }
    else {
      if (!(this.stockService.canOrder(product, Integer.valueOf(basket.getBasketCount().intValue() + count.intValue()))))
        return SaveToCartStatusEnum.LESS;

      if (count.intValue() > 0) {
        basket.setBasketCount(Integer.valueOf(basket.getBasketCount().intValue() + count.intValue()));
        updateBasket(basket);
      }
    }

    return SaveToCartStatusEnum.OK;
  }

  @Required
  public void setProductDao(ProductDao productDaoImpl)
  {
    this.productDao = productDaoImpl;
  }

  public void deleteBasket(String userName)
  {
    this.baseJdbcDao.update("delete from ls_basket where shop_name = ?", new Object[] { userName });
  }

  public void setBaseJdbcDao(BaseJdbcDao baseJdbcDao)
  {
    this.baseJdbcDao = baseJdbcDao;
  }

  private Map<String, List<Basket>> convertBasketMap(List<Basket> list)
  {
    if (AppUtils.isBlank(list))
      return null;

    Map map = new HashMap();
    for (Iterator localIterator = list.iterator(); localIterator.hasNext(); ) { Basket basket = (Basket)localIterator.next();
      List baskets = (List)map.get(basket.getShopName());
      if (baskets == null)
        baskets = new ArrayList();

      baskets.add(basket);
      map.put(basket.getShopName(), baskets);
    }
    return map;
  }

  public void deleteUserBasket(String userName)
  {
    this.baseJdbcDao.deleteUserItem("ls_basket", userName);
  }

  public void setStockService(StockService stockService) {
    this.stockService = stockService;
  }
}
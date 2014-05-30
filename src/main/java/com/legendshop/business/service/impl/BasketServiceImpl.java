package com.legendshop.business.service.impl;

import com.legendshop.business.dao.BasketDao;
import com.legendshop.model.entity.Basket;
import com.legendshop.spi.constants.SaveToCartStatusEnum;
import com.legendshop.spi.dao.ProductDao;
import com.legendshop.spi.service.BasketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class BasketServiceImpl
  implements BasketService
{
  private BasketDao basketDao;
  private ProductDao productDao;

  public void saveToCart(Long prodId, String shopName, String prodattr, String userName, Integer count)
  {
    String attribute = (prodattr == null) ? "" : prodattr;
    Basket basket = this.basketDao.getBasketByIdName(prodId, userName, shopName, attribute);
    if (basket == null)
      this.basketDao.saveToCart(userName, prodId, count, attribute);
  }

  @Required
  public void setProductDao(ProductDao productDao)
  {
    this.productDao = productDao;
  }

  @Required
  public void setBasketDao(BasketDao basketDao)
  {
    this.basketDao = basketDao;
  }

  public void deleteBasketByUserName(String userName)
  {
    this.basketDao.deleteBasketByUserName(userName);
  }

  public void deleteBasketById(Long id)
  {
    this.basketDao.deleteBasketById(id);
  }

  public List<Basket> getBasketByUserName(String userName)
  {
    return this.basketDao.getBasketByUserName(userName);
  }

  public Long getTotalBasketByUserName(String userName)
  {
    return this.basketDao.getTotalBasketByUserName(userName);
  }

  public SaveToCartStatusEnum saveToCart(String userName, Long prodId, Integer count, String attribute)
  {
    return this.basketDao.saveToCart(userName, prodId, count, attribute);
  }

  public List<Basket> getBasketListById(Long[] basketIdList)
  {
    return this.basketDao.getBasketListById(basketIdList);
  }

  public void updateBasket(Basket basket)
  {
    this.basketDao.updateBasket(basket);
  }
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;
import javax.servlet.http.HttpServletRequest;

public abstract interface GroupProductService
{
  public abstract PageSupport getGroupProductList(HqlQuery paramHqlQuery);

  public abstract void updateGroupProduct(GroupProduct paramGroupProduct);

  public abstract String saveGroupProduct(HttpServletRequest paramHttpServletRequest, GroupProduct paramGroupProduct, Product paramProduct, String paramString);

  public abstract Product getProductById(Long paramLong);

  public abstract void updateProduct(Product paramProduct1, Product paramProduct2, GroupProduct paramGroupProduct);

  public abstract void saveProduct(Product paramProduct, GroupProduct paramGroupProduct);

  public abstract String deleteProduct(String paramString, Long paramLong);

  public abstract Product getGroupProduct(Long paramLong);
}
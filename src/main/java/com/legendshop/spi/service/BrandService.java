package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import java.util.List;

public abstract interface BrandService
{
  public abstract List<Brand> getBrand(String paramString);

  public abstract Brand getBrand(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(Brand paramBrand);

  public abstract void update(Brand paramBrand);

  public abstract PageSupport getDataByCriteriaQuery(CriteriaQuery paramCriteriaQuery);

  public abstract String saveBrandItem(List<String> paramList, Long paramLong, String paramString);

  public abstract String saveBrandItem(String paramString1, String paramString2, Long paramLong, String paramString3);

  public abstract List<Item> getUsableBrandByName(Long paramLong, String paramString1, String paramString2);

  public abstract List<Item> getUsableBrand(Long paramLong, String paramString);

  public abstract List<Item> getUsedBrand(Long paramLong, String paramString);

  public abstract List<KeyValueEntity> loadBrandEntityBySubSortId(Long paramLong, String paramString);

  public abstract List<Brand> loadBrandBySubSortId(Long paramLong, String paramString);

  public abstract List<Brand> loadBrandByName(Long paramLong, String paramString1, String paramString2);

  public abstract boolean hasChildProduct(String paramString, Long paramLong);
}
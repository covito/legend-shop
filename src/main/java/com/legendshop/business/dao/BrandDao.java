package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.Brand;
import java.util.List;

public abstract interface BrandDao extends BaseDao
{
  public abstract List<Item> getUsableBrand(Long paramLong, String paramString);

  public abstract List<Item> getUsableBrandByName(Long paramLong, String paramString1, String paramString2);

  public abstract List<Item> getUsedBrand(Long paramLong, String paramString);

  public abstract String saveBrandItem(List<String> paramList, Long paramLong, String paramString);

  public abstract void deleteBrandById(Long paramLong);

  public abstract void updateBrand(Brand paramBrand);

  public abstract List<Brand> loadBrandBySubSortId(Long paramLong, String paramString);

  public abstract List<Brand> loadBrandByName(Long paramLong, String paramString1, String paramString2);

  public abstract boolean hasChildProduct(String paramString, Long paramLong);
}
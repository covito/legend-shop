package com.legendshop.spi.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Brand;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.Sort;
import java.util.List;

public abstract interface SortDao extends BaseDao
{
  public abstract Sort getSort(Long paramLong);

  public abstract void deleteSortById(Long paramLong);

  public abstract void updateSort(Sort paramSort);

  public abstract Long saveSort(Sort paramSort);

  public abstract List<Product> getProductBySortId(Long paramLong);

  public abstract List<Nsort> getNsortBySortId(Long paramLong);

  public abstract List<Nsort> getNsortBySortId(Long paramLong, String paramString);

  public abstract void deleteSort(Sort paramSort);

  public abstract List<Sort> getSort(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean);

  public abstract List<Sort> getSort(String paramString1, String paramString2, String paramString3);

  public abstract List<Brand> getBrandList(Long paramLong);

  public abstract boolean hasChildNsort(Long paramLong);

  public abstract boolean hasChildProduct(String paramString, Long paramLong);
}
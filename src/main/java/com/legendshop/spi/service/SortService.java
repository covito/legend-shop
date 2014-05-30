package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.KeyValueEntity;
import com.legendshop.model.ProdSearchArgs;
import com.legendshop.model.entity.Nsort;
import com.legendshop.model.entity.Sort;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface SortService extends BaseService
{
  public abstract Sort getSortById(Long paramLong);

  public abstract void deleteSort(Long paramLong);

  public abstract Long save(Sort paramSort);

  public abstract void updateSort(Sort paramSort);

  public abstract PageSupport getSortList(CriteriaQuery paramCriteriaQuery);

  public abstract List<Sort> getSort(String paramString, Boolean paramBoolean);

  public abstract void delete(Sort paramSort);

  public abstract List<Sort> getSort(String paramString1, String paramString2, Boolean paramBoolean);

  public abstract List<Sort> getSort(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2, Boolean paramBoolean);

  public abstract String getSecSort(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Sort paramSort, Long paramLong1, Long paramLong2);

  public abstract String parseSort(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Sort paramSort);

  public abstract String parseSort(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Long paramLong, ProdSearchArgs paramProdSearchArgs);

  public abstract String parseSecSort(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString, Long paramLong1, Long paramLong2, Long paramLong3, ProdSearchArgs paramProdSearchArgs);

  public abstract Sort getSortAndBrand(Long paramLong);

  public abstract String sortList(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Sort paramSort);

  public abstract List<KeyValueEntity> loadSorts(String paramString1, String paramString2);

  public abstract List<KeyValueEntity> loadNSorts(Long paramLong);

  public abstract List<KeyValueEntity> loadSubNSorts(Long paramLong);

  public abstract List<Sort> getSort(String paramString1, String paramString2, String paramString3);

  public abstract List<Nsort> getNsortBySortId(Long paramLong);

  public abstract List<Nsort> getNsortBySortId(Long paramLong, String paramString);

  public abstract List<Nsort> getSubNsortBySortId(Long paramLong, String paramString);
}
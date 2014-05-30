package com.legendshop.spi.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.ProdSearchArgs;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;
import java.util.List;

public abstract interface ProductDao extends BaseDao
{
  public abstract List<Product> getCommendProd(String paramString, int paramInt);

  public abstract List<Product> getGlobalCommendProd(int paramInt);

  public abstract List<Product> getCommendProdBySort(String paramString, Long paramLong, int paramInt);

  public abstract List<Product> getReleationProd(String paramString, Long paramLong, int paramInt);

  public abstract List<RelProduct> getReleationProd(Long paramLong, String paramString);

  public abstract List<Product> getNewestProd(String paramString, int paramInt);

  public abstract List<Product> getGlobalNewestProd(int paramInt);

  public abstract List<Product> gethotsale(String paramString);

  public abstract PageSupport getProdDetail(String paramString, Long paramLong1, Long paramLong2, Long paramLong3, Boolean paramBoolean);

  public abstract PageSupport getProdDetail(String paramString, Long paramLong);

  public abstract PageSupport getProdDetail(String paramString, Long paramLong1, Long paramLong2, Long paramLong3, ProdSearchArgs paramProdSearchArgs);

  public abstract ProductDetail getProdDetail(Long paramLong);

  public abstract List<ProductDetail> getProdDetail(List<Object> paramList);

  public abstract PageSupport getProdDetail(CriteriaQuery paramCriteriaQuery);

  public abstract List<ProductDetail> getProdDetail(Long[] paramArrayOfLong);

  public abstract List<Product> getHotOn(String paramString, Long paramLong);

  public abstract List<Product> getHotViewProd(String paramString, Integer paramInteger);

  public abstract Product getProduct(Long paramLong);

  public abstract void updateProdViews(Long paramLong);

  public abstract void updateProduct(Product paramProduct);

  public abstract void updateOnly(Product paramProduct);

  public abstract Long saveProduct(Product paramProduct);

  public abstract String deleteProd(String paramString, Long paramLong);

  public abstract void deleteProd(Product paramProduct);

  public abstract List<Model> loadDynamicAttribute(Long paramLong, String paramString);

  public abstract Product getProd(Long paramLong, String paramString);

  public abstract DynamicTemp getDynamicTemp(Long paramLong);

  public abstract PageSupport getDynamicTemp(CriteriaQuery paramCriteriaQuery);

  public abstract Long saveDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract boolean updateDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract boolean deleteDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract String saveProdItem(List<String> paramList1, List<String> paramList2, Long paramLong, String paramString);

  public abstract String getProdParameter(Long paramLong);

  public abstract List<Product> getHotComment(String paramString, Long paramLong, int paramInt);

  public abstract List<Product> getHotRecommend(String paramString, Long paramLong, int paramInt);

  public abstract List<DynamicTemp> getDynamicTemp(Integer paramInteger, String paramString);

  public abstract PageSupport getDynamicTemp(SimpleHqlQuery paramSimpleHqlQuery);

  public abstract List<DynamicTemp> getDynamicTemp(Integer paramInteger, Long paramLong, String paramString);

  public abstract List<Item> getUsableProductItemByName(Long paramLong, String paramString1, String paramString2);

  public abstract List<Item> getUsableProductItem(Long paramLong, String paramString);

  public abstract List<Item> getUsedProd(Long paramLong, String paramString);

  public abstract PageSupport getGlobalProdDetail(String paramString, Long paramLong);

  public abstract boolean hasOrder(Long paramLong);
}
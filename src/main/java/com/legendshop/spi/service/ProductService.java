package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleHqlQuery;
import com.legendshop.model.dynamic.Item;
import com.legendshop.model.entity.DynamicTemp;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ProductDetail;
import com.legendshop.model.entity.RelProduct;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface ProductService extends BaseService
{
  public abstract PageSupport getProductList(HqlQuery paramHqlQuery);

  public abstract PageSupport getProductList(CriteriaQuery paramCriteriaQuery);

  public abstract Product getProductById(Long paramLong);

  public abstract void updateProduct(Product paramProduct1, Product paramProduct2);

  public abstract Long saveProduct(Product paramProduct, String paramString);

  public abstract String getAttributeprodAttribute(Long paramLong);

  public abstract Product getProd(Long paramLong, String paramString);

  public abstract String getProdParameter(Long paramLong);

  public abstract Long saveDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract boolean updateDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract DynamicTemp getDynamicTemp(Long paramLong);

  public abstract List<DynamicTemp> getDynamicTemp(Integer paramInteger, String paramString);

  public abstract List<DynamicTemp> getDynamicTemp(Integer paramInteger, Long paramLong, String paramString);

  public abstract int deleteDynamicTemp(Long paramLong, String paramString);

  public abstract String saveProdItem(List<String> paramList1, List<String> paramList2, Long paramLong, String paramString);

  public abstract void updateProd(Product paramProduct);

  public abstract ProductDetail getProdDetail(Long paramLong);

  public abstract List<Product> getReleationProd(String paramString, Long paramLong, int paramInt);

  public abstract List<RelProduct> getReleationProd(Long paramLong, String paramString);

  public abstract void updateProdViews(Long paramLong);

  public abstract List<Product> getHotSale(String paramString);

  public abstract List<Product> getHotOn(String paramString, Long paramLong);

  public abstract List<Product> getHotViewProd(String paramString, int paramInt);

  public abstract PageSupport getProdDetail(String paramString, Long paramLong);

  public abstract String getProductGallery(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Long paramLong);

  public abstract List<Product> getHotComment(String paramString, Long paramLong, int paramInt);

  public abstract List<Product> getHotRecommend(String paramString, Long paramLong, int paramInt);

  public abstract PageSupport getDynamicTemp(CriteriaQuery paramCriteriaQuery);

  public abstract PageSupport getDynamicTemp(SimpleHqlQuery paramSimpleHqlQuery);

  public abstract List<ProductDetail> getVisitedProd(HttpServletRequest paramHttpServletRequest);

  public abstract List<ProductDetail> getRecommendProd(Long paramLong);

  public abstract boolean deleteDynamicTemp(DynamicTemp paramDynamicTemp);

  public abstract String delete(String paramString, Long paramLong);

  public abstract String saveRelProd(String paramString1, String paramString2, Long paramLong, String paramString3);

  public abstract List<Item> getUsableProductItemByName(Long paramLong, String paramString1, String paramString2);

  public abstract List<Item> getUsableProductItem(Long paramLong, String paramString);

  public abstract List<Item> getUsedProductItem(Long paramLong, String paramString);
}
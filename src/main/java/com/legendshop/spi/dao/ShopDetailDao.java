package com.legendshop.spi.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.ShopDetailView;
import java.util.List;

public abstract interface ShopDetailDao extends BaseDao
{
  public abstract Boolean isShopExists(String paramString);

  public abstract ShopDetail getShopDetailForUpdate(String paramString);

  public abstract ShopDetailView getShopDetailView(String paramString);

  public abstract ShopDetail getShopDetail(String paramString);

  public abstract List<ShopDetailView> getShopDetail(Long[] paramArrayOfLong);

  public abstract Boolean isLeagueShopExists(String paramString);

  public abstract Boolean isBeLeagueShop(boolean paramBoolean, String paramString1, String paramString2);

  public abstract Integer getProductNum(String paramString);

  public abstract Integer getOffProductNum(String paramString);

  public abstract void updateShopDetail(ShopDetail paramShopDetail);

  public abstract void updateShopDetailWhenProductChange(Product paramProduct);

  public abstract ShopDetail getShopDetailByUserId(String paramString);

  public abstract void updateShopDetail(String paramString);

  public abstract boolean updateShop(String paramString, ShopDetail paramShopDetail, Integer paramInteger);

  public abstract void saveShopDetail(ShopDetail paramShopDetail);

  public abstract ShopDetail getShopDetailByShopId(Long paramLong);

  public abstract void deleteShopDetail(ShopDetail paramShopDetail);

  public abstract Long getAllShopCount();

  public abstract String getShopNameByDomain(String paramString);

  public abstract Long getShopIdByName(String paramString);
}
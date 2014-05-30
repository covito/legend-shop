package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.UserDetailDao;
import com.legendshop.core.OperationTypeEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.event.FireEvent;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.event.EventHome;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.spi.cache.ShopDetailUpdate;
import com.legendshop.spi.dao.ShopDetailDao;
import com.legendshop.spi.event.ShopDetailDeleteEvent;
import com.legendshop.spi.event.ShopDetailSaveEvent;
import com.legendshop.spi.event.ShopDetailUpdateEvent;
import com.legendshop.spi.service.CommonUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.constant.ShopStatusEnum;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

public abstract class ShopDetailDaoImpl extends BaseDaoImpl
  implements ShopDetailDao
{
  private static Logger log = LoggerFactory.getLogger(ShopDetailDaoImpl.class);
  private CommonUtil commonUtil;
  private UserDetailDao userDetailDao;

  @Cacheable({"ShopDetailView"})
  public Boolean isShopExists(String userName)
  {
    if (AppUtils.isBlank(userName))
      return Boolean.valueOf(false);

    List list = findByHQL("select userName from ShopDetail where  status = 1 and userName = ?", new Object[] { userName });
    if ((list != null) && (list.size() > 0)) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  public ShopDetail getShopDetailForUpdate(String userName)
  {
    ShopDetail shopDetail = (ShopDetail)findUniqueBy("from ShopDetail sd where sd.userName = ?", ShopDetail.class, new Object[] { userName });
    return shopDetail;
  }

  public ShopDetail getShopDetail(String userName)
  {
    return ((ShopDetail)findUniqueBy("from ShopDetail sd where sd.userName = ?", ShopDetail.class, new Object[] { userName }));
  }

  @Cacheable({"ShopDetailView"})
  public Boolean isLeagueShopExists(String userName)
  {
    if (userName == null)
      return Boolean.valueOf(false);
    Long num = (Long)findUniqueBy("select count(*) from Myleague where userId = ? ", Long.class, new Object[] { userName });
    if (num.longValue() > -4648542910910824448L) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  @Cacheable({"ShopDetailView"})
  public Boolean isBeLeagueShop(boolean isShopExists, String userName, String friendName)
  {
    if ((!(isShopExists)) || (AppUtils.isBlank(userName)) || (userName.equals(friendName)))
      return Boolean.valueOf(false);

    Long result = (Long)findUniqueBy("select count(*) from Myleague where userId = ? and friendId = ?", Long.class, new Object[] { userName, 
      friendName });
    if (result.longValue() <= -4648542910910824448L) return Boolean.valueOf(true); return Boolean.valueOf(false);
  }

  public Integer getProductNum(String userName)
  {
    String sql = "select count(*) from Product prod where prod.status = 1 and prod.userName = ?";
    return Integer.valueOf(((Long)findUniqueBy(sql, Long.class, new Object[] { userName })).intValue());
  }

  public Integer getOffProductNum(String userName)
  {
    String sql = "select count(*) from Product prod where prod.status = 0 and prod.userName = ?";
    return Integer.valueOf(((Long)findUniqueBy(sql, Long.class, new Object[] { userName })).intValue());
  }

  @ShopDetailUpdate
  public void updateShopDetail(ShopDetail shopDetail)
  {
    EventHome.publishEvent(new ShopDetailUpdateEvent(shopDetail));
    update(shopDetail);
  }

  @ShopDetailUpdate
  private void saveOrUpdateShopDetail(ShopDetail shopDetail)
  {
    saveOrUpdate(shopDetail);
  }

  @Caching(evict={@org.springframework.cache.annotation.CacheEvict(value={"ShopDetailView"}, key="#product.userName")})
  public void updateShopDetailWhenProductChange(Product product)
  {
    ShopDetail shopdetail = getShopDetailForUpdate(product.getUserName());
    if (shopdetail == null)
      throw new NotFoundException("ShopDetail is null, UserName = " + product.getUserName());

    shopdetail.setProductNum(getProductNum(product.getUserName()));
    shopdetail.setOffProductNum(getOffProductNum(product.getUserName()));
    updateShopDetail(shopdetail);
  }

  public ShopDetail getShopDetailByUserId(String userId)
  {
    return ((ShopDetail)findUniqueBy("from ShopDetail sd where sd.userId = ?", ShopDetail.class, new Object[] { userId }));
  }

  @Caching(evict={@org.springframework.cache.annotation.CacheEvict(value={"ShopDetailView"}, key="#userName")})
  public void updateShopDetail(String userName)
  {
    ShopDetail shopdetail = getShopDetailForUpdate(userName);
    if (shopdetail == null)
      throw new NotFoundException("ShopDetail is null, UserName = " + userName);

    shopdetail.setProductNum(getProductNum(userName));
    shopdetail.setOffProductNum(getOffProductNum(userName));
    updateShopDetail(shopdetail);
  }

  @Caching(evict={@org.springframework.cache.annotation.CacheEvict(value={"ShopDetailView"}, key="#shopDetail.userName")})
  public boolean updateShop(String userId, ShopDetail shopDetail, Integer status)
  {
    boolean result = true;
    try {
      if ((ShopStatusEnum.REJECT.value().equals(status)) || 
        (ShopStatusEnum.CLOSE.value().equals(status)))
      {
        this.commonUtil.removeAdminRight(userId);
      } else if (ShopStatusEnum.ONLINE.value().equals(status)) {
        this.commonUtil.saveAdminRight(userId);
      }

      shopDetail.setStatus(status);
      shopDetail.setModifyDate(new Date());
      EventHome.publishEvent(new FireEvent(shopDetail, OperationTypeEnum.UPDATE_STATUS));
      saveOrUpdateShopDetail(shopDetail);
    }
    catch (Exception e) {
      log.error("auditShop ", e);
      result = false;
    }
    return result;
  }

  public void saveShopDetail(ShopDetail shopDetail)
  {
    EventHome.publishEvent(new ShopDetailSaveEvent(shopDetail));
    save(shopDetail);

    this.commonUtil.saveAdminRight(shopDetail.getUserId());
  }

  @ShopDetailUpdate
  public void deleteShopDetail(ShopDetail shopDetail)
  {
    this.userDetailDao.deleteShopDetail(shopDetail.getUserId(), shopDetail.getUserName(), true);
    EventHome.publishEvent(new ShopDetailDeleteEvent(shopDetail));
  }

  public ShopDetail getShopDetailByShopId(Long shopId)
  {
    return ((ShopDetail)get(ShopDetail.class, shopId));
  }

  public Long getAllShopCount()
  {
    return ((Long)findUniqueBy("select count(*) from ShopDetail", Long.class, new Object[0]));
  }

  @Required
  public void setCommonUtil(CommonUtil commonUtil)
  {
    this.commonUtil = commonUtil;
  }

  public void setUserDetailDao(UserDetailDao userDetailDao) {
    this.userDetailDao = userDetailDao;
  }
}
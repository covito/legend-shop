package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.constants.RegisterEnum;
import javax.mail.MessagingException;
import org.apache.oro.text.regex.MalformedPatternException;

public abstract interface UserDetailDao extends BaseDao
{
  public abstract String saveUser(User paramUser, UserDetail paramUserDetail, ShopDetail paramShopDetail, boolean paramBoolean);

  public abstract void saveUerDetail(UserDetail paramUserDetail, ShopDetail paramShopDetail, boolean paramBoolean);

  public abstract Integer saveShopDetailAndRole(UserDetail paramUserDetail, ShopDetail paramShopDetail);

  public abstract void updateShopDetail(UserDetail paramUserDetail, ShopDetail paramShopDetail, boolean paramBoolean);

  public abstract Integer saveShopDetail(UserDetail paramUserDetail, ShopDetail paramShopDetail);

  public abstract void updateUser(UserDetail paramUserDetail);

  public abstract void updatePassword(UserDetail paramUserDetail);

  public abstract boolean isUserExist(String paramString);

  public abstract boolean isEmailExist(String paramString);

  public abstract boolean isShopExist(String paramString);

  public abstract User getUser(String paramString);

  public abstract User getUserByName(String paramString);

  public abstract UserDetail getUserDetailByName(String paramString);

  public abstract RegisterEnum getUserRegStatus(String paramString1, String paramString2);

  public abstract UserDetail getUserDetail(String paramString);

  public abstract Long getUserScore(String paramString);

  public abstract PageSupport getUserDetailList(SqlQuery paramSqlQuery);

  public abstract PageSupport getUserDetailList(HqlQuery paramHqlQuery);

  public abstract String deleteUserDetail(String paramString1, String paramString2);

  public abstract void deleteShopDetail(String paramString1, String paramString2, boolean paramBoolean);

  public abstract boolean updatePassword(String paramString1, String paramString2, String paramString3)
    throws MalformedPatternException, MessagingException;

  public abstract Long getAllUserCount();

  public abstract void updateUser(User paramUser);
}
package com.legendshop.spi.service;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.model.entity.ShopDetail;
import com.legendshop.model.entity.User;
import com.legendshop.model.entity.UserDetail;
import com.legendshop.spi.form.UserForm;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.oro.text.regex.MalformedPatternException;

public abstract interface UserDetailService extends BaseService
{
  public abstract Long getScore(String paramString);

  public abstract PageSupport getUserDetailList(HqlQuery paramHqlQuery);

  public abstract PageSupport getUserDetailList(SqlQuery paramSqlQuery);

  public abstract UserDetail getUserDetail(String paramString);

  public abstract String deleteUserDetail(String paramString1, String paramString2);

  public abstract boolean updatePassword(String paramString1, String paramString2, String paramString3)
    throws MalformedPatternException, MessagingException;

  public abstract String updateAccount(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserForm paramUserForm);

  public abstract String saveUserReg(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserForm paramUserForm);

  public abstract String saveShop(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, ShopDetail paramShopDetail);

  public abstract String updateUserReg(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString1, String paramString2);

  public abstract boolean isUserExist(String paramString);

  public abstract boolean isEmailExist(String paramString);

  public abstract boolean isShopExist(String paramString);

  public abstract User getUser(String paramString);

  public abstract void uppdateUser(User paramUser);
}
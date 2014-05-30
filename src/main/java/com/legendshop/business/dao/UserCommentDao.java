package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;

public abstract interface UserCommentDao extends BaseDao
{
  public abstract PageSupport getUserCommentByCriteria(CriteriaQuery paramCriteriaQuery);

  public abstract UserComment getUserComment(Long paramLong);

  public abstract void deleteUserComment(UserComment paramUserComment);

  public abstract void updateUserComment(UserComment paramUserComment);

  public abstract void saveOrUpdateUserComment(UserComment paramUserComment);

  public abstract boolean updateUserComment(Long paramLong, String paramString1, String paramString2);

  public abstract Long getTotalUnReadMessage(String paramString);
}
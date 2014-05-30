package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;

public abstract interface UserCommentService
{
  public abstract PageSupport getUserCommentList(CriteriaQuery paramCriteriaQuery);

  public abstract UserComment getUserComment(Long paramLong);

  public abstract void delete(UserComment paramUserComment);

  public abstract void updateUserCommentToReaded(UserComment paramUserComment);

  public abstract void saveOrUpdateUserComment(UserComment paramUserComment);

  public abstract boolean updateUserComment(Long paramLong, String paramString1, String paramString2);
}
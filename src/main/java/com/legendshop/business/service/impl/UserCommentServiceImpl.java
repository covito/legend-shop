package com.legendshop.business.service.impl;

import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;
import com.legendshop.spi.constants.CommentTypeEnum;
import com.legendshop.spi.service.UserCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class UserCommentServiceImpl
  implements UserCommentService
{
  Logger log = LoggerFactory.getLogger(UserCommentServiceImpl.class);
  private UserCommentDao userCommentDao;

  @Required
  public void setUserCommentDao(UserCommentDao userCommentDao)
  {
    this.userCommentDao = userCommentDao;
  }

  public PageSupport getUserCommentList(CriteriaQuery cq)
  {
    return this.userCommentDao.getUserCommentByCriteria(cq);
  }

  public UserComment getUserComment(Long id)
  {
    return this.userCommentDao.getUserComment(id);
  }

  public void delete(UserComment userComment)
  {
    this.userCommentDao.deleteUserComment(userComment);
  }

  public void updateUserCommentToReaded(UserComment comment)
  {
    if (!(comment.getStatus().equals(CommentTypeEnum.COMMENT_READED.value()))) {
      comment.setStatus(CommentTypeEnum.COMMENT_READED.value());
      this.userCommentDao.updateUserComment(comment);
    }
  }

  public void saveOrUpdateUserComment(UserComment comment)
  {
    this.userCommentDao.saveOrUpdateUserComment(comment);
  }

  public boolean updateUserComment(Long id, String answer, String loginName)
  {
    return this.userCommentDao.updateUserComment(id, answer, loginName);
  }
}
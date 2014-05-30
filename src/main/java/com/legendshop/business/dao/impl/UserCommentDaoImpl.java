package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.UserCommentDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.UserComment;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCommentDaoImpl extends BaseDaoImpl
  implements UserCommentDao
{
  private static Logger log = LoggerFactory.getLogger(UserCommentDaoImpl.class);

  public PageSupport getUserCommentByCriteria(CriteriaQuery cq)
  {
    return find(cq);
  }

  public UserComment getUserComment(Long id)
  {
    return ((UserComment)get(UserComment.class, id));
  }

  public void deleteUserComment(UserComment userComment)
  {
    delete(userComment);
  }

  public void updateUserComment(UserComment comment)
  {
    update(comment);
  }

  public void saveOrUpdateUserComment(UserComment comment)
  {
    saveOrUpdate(comment);
  }

  public boolean updateUserComment(Long id, String answer, String toUserName)
  {
    UserComment comment = (UserComment)get(UserComment.class, id);
    if (comment != null) {
      if (!(comment.getToUserName().equals(toUserName))) {
        log.debug("toUserName try to answer comments own to " + comment.getToUserName() + " ,but fail");
        return false;
      }
      comment.setAnswer(answer);
      comment.setAnswertime(new Date());
      update(comment);
      return true;
    }
    return false;
  }

  public Long getTotalUnReadMessage(String userName)
  {
    return ((Long)findUniqueBy("select count(*) from UserComment where status = ? and toUserName = ?", Long.class, new Object[] { Integer.valueOf(0), userName }));
  }
}
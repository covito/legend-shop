package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class NewsCategory extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = -8019865349891607453L;
  private Long newsCategoryId;
  private String newsCategoryName;
  private Short status;
  private Integer seq;
  private Date newsCategoryDate;
  private String userId;
  private String userName;

  public Long getNewsCategoryId()
  {
    return this.newsCategoryId;
  }

  public void setNewsCategoryId(Long newsCategoryId)
  {
    this.newsCategoryId = newsCategoryId;
  }

  public String getNewsCategoryName()
  {
    return this.newsCategoryName;
  }

  public void setNewsCategoryName(String newsCategoryName)
  {
    this.newsCategoryName = newsCategoryName;
  }

  public Short getStatus()
  {
    return this.status;
  }

  public void setStatus(Short status)
  {
    this.status = status;
  }

  public Date getNewsCategoryDate()
  {
    return this.newsCategoryDate;
  }

  public void setNewsCategoryDate(Date newsCategoryDate)
  {
    this.newsCategoryDate = newsCategoryDate;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Serializable getId()
  {
    return this.newsCategoryId;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }
}
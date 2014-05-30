package com.legendshop.model.entity;

import java.util.Date;

public class Pub
  implements BaseEntity
{
  private static final long serialVersionUID = 4935608546923180664L;
  private Long id;
  private String title;
  private String msg;
  private Date recDate;
  private Date startDate;
  private Date endDate;
  private String userId;
  private String userName;
  private Integer status;
  private boolean isValid = true;

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getMsg()
  {
    return this.msg;
  }

  public void setMsg(String msg)
  {
    this.msg = msg;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate)
  {
    this.recDate = recDate;
  }

  public Date getStartDate()
  {
    return this.startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public Date getEndDate()
  {
    return this.endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
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

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Long getId()
  {
    return this.id;
  }

  public boolean isValid() {
    return this.isValid;
  }

  public void setValid(boolean isValid) {
    this.isValid = isValid;
  }
}
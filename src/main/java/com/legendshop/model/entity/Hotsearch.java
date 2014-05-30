package com.legendshop.model.entity;

import java.util.Date;

public class Hotsearch
  implements BaseEntity
{
  private static final long serialVersionUID = -1349792154724940069L;
  private Long id;
  private String title;
  private Long sort;
  private String sortName;
  private String msg;
  private Date date;
  private String userId;
  private String userName;
  protected Integer status;
  private Integer seq;

  public Hotsearch()
  {
  }

  public Hotsearch(Long id, String title, String msg, Date date)
  {
    this.id = id;
    this.title = title;
    this.msg = msg;
    this.date = date;
  }

  public Hotsearch(Long id, String title, String msg, Date date, String userId, String userName)
  {
    this.id = id;
    this.title = title;
    this.msg = msg;
    this.date = date;
    this.userId = userId;
    this.userName = userName;
  }

  public Long getId()
  {
    return this.id;
  }

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

  public Date getDate()
  {
    return this.date;
  }

  public void setDate(Date date)
  {
    this.date = date;
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

  public Long getSort()
  {
    return this.sort;
  }

  public void setSort(Long sort)
  {
    this.sort = sort;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }

  public String getSortName() {
    return this.sortName;
  }

  public void setSortName(String sortName) {
    this.sortName = sortName;
  }
}
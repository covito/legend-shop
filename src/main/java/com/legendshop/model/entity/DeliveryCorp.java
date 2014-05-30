package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DeliveryCorp
  implements BaseEntity
{
  private Long dvyId;
  private String userId;
  private String userName;
  private String name;
  private String url;
  private Date createTime;
  private Date modifyTime;

  public Long getDvyId()
  {
    return this.dvyId;
  }

  public void setDvyId(Long dvyId)
  {
    this.dvyId = dvyId;
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

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Date getModifyTime()
  {
    return this.modifyTime;
  }

  public void setModifyTime(Date modifyTime)
  {
    this.modifyTime = modifyTime;
  }

  public Serializable getId() {
    return this.dvyId;
  }
}
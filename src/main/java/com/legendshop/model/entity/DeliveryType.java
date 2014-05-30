package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class DeliveryType
  implements BaseEntity
{
  private Long dvyTypeId;
  private String userId;
  private String userName;
  private Long dvyId;
  private String type;
  private String name;
  private String notes;
  private Date createTime;
  private Date modifyTime;

  public Long getDvyTypeId()
  {
    return this.dvyTypeId;
  }

  public void setDvyTypeId(Long dvyTypeId)
  {
    this.dvyTypeId = dvyTypeId;
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

  public Long getDvyId()
  {
    return this.dvyId;
  }

  public void setDvyId(Long dvyId)
  {
    this.dvyId = dvyId;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getNotes()
  {
    return this.notes;
  }

  public void setNotes(String notes)
  {
    this.notes = notes;
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
    return this.dvyTypeId;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserEvent
  implements BaseEntity
{
  private static final long serialVersionUID = 1477990911266692837L;
  private Long eventId;
  private String userId;
  private String userName;
  private String type;
  private String operation;
  private String relateData;
  private String relateId;
  private Date createTime;
  private Date startTime;
  private Date endTime;
  private String modifyUser;
  private Object entity;

  public Long getEventId()
  {
    return this.eventId;
  }

  public void setEventId(Long eventId)
  {
    this.eventId = eventId;
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

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getOperation()
  {
    return this.operation;
  }

  public void setOperation(String operation)
  {
    this.operation = operation;
  }

  public String getRelateData()
  {
    return this.relateData;
  }

  public void setRelateData(String relateData)
  {
    this.relateData = relateData;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Serializable getId()
  {
    return this.eventId;
  }

  public String getRelateId()
  {
    return this.relateId;
  }

  public void setRelateId(String relateId)
  {
    this.relateId = relateId;
  }

  public Date getStartTime()
  {
    return this.startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Date getEndTime()
  {
    return this.endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public String getModifyUser()
  {
    return this.modifyUser;
  }

  public void setModifyUser(String modifyUser)
  {
    this.modifyUser = modifyUser;
  }

  public Object getEntity()
  {
    return this.entity;
  }

  public void setEntity(Object entity)
  {
    this.entity = entity;
  }
}
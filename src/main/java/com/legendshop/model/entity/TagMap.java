package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class TagMap
  implements BaseEntity
{
  private static final long serialVersionUID = 1611929959622783915L;
  private Long tagMapId;
  private Long tagId;
  private Long referId;
  private String type;
  private Date startTime;
  private Date endTime;

  public Long getTagMapId()
  {
    return this.tagMapId;
  }

  public void setTagMapId(Long tagMapId)
  {
    this.tagMapId = tagMapId;
  }

  public Long getTagId()
  {
    return this.tagId;
  }

  public void setTagId(Long tagId)
  {
    this.tagId = tagId;
  }

  public Long getReferId()
  {
    return this.referId;
  }

  public void setReferId(Long referId)
  {
    this.referId = referId;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
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

  public Serializable getId()
  {
    return this.tagMapId;
  }
}
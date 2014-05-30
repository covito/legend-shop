package com.legendshop.model.entity;

import java.util.Date;

public class ProductPropertyValue extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = 7248859099398637969L;
  private Long valueId;
  private Long propId;
  private String name;
  private Short status;
  private String pic;
  private Long sequence;
  private Date modifyDate;
  private Date recDate;

  public Long getValueId()
  {
    return this.valueId;
  }

  public void setValueId(Long valueId) {
    this.valueId = valueId;
  }

  public Long getPropId()
  {
    return this.propId;
  }

  public void setPropId(Long propId) {
    this.propId = propId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Short getStatus()
  {
    return this.status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }

  public String getPic()
  {
    return this.pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public Long getSequence()
  {
    return this.sequence;
  }

  public void setSequence(Long sequence) {
    this.sequence = sequence;
  }

  public Date getModifyDate()
  {
    return this.modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate) {
    this.recDate = recDate;
  }

  public Long getId()
  {
    return this.valueId;
  }

  public String getUserName()
  {
    return null;
  }
}
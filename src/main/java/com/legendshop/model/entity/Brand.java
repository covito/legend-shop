package com.legendshop.model.entity;

import java.io.Serializable;

public class Brand extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = 3941969699979401870L;
  private Long brandId;
  private String brandName;
  protected Integer status;
  private Integer seq;
  private String userId;
  private String userName;
  private String brandPic;
  private String memo;
  private Long sortId;

  public Brand()
  {
  }

  public Brand(Long brandId, String brandName)
  {
    this.brandId = brandId;
    this.brandName = brandName;
  }

  public Long getBrandId()
  {
    return this.brandId;
  }

  public void setBrandId(Long brandId)
  {
    this.brandId = brandId;
  }

  public String getBrandName()
  {
    return this.brandName;
  }

  public void setBrandName(String brandName)
  {
    this.brandName = brandName;
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

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo)
  {
    this.memo = memo;
  }

  public String getBrandPic()
  {
    return this.brandPic;
  }

  public void setBrandPic(String brandPic)
  {
    this.brandPic = brandPic;
  }

  public Serializable getId()
  {
    return this.brandId;
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

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }
}
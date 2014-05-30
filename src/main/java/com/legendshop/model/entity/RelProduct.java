package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class RelProduct
  implements BaseEntity
{
  private static final long serialVersionUID = -8175142186764722214L;
  private Long relId;
  private Long prodId;
  private Long relProdId;
  private String relProdName;
  private Date recDate;
  private String userName;

  public Long getRelId()
  {
    return this.relId;
  }

  public void setRelId(Long relId)
  {
    this.relId = relId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public Long getRelProdId()
  {
    return this.relProdId;
  }

  public void setRelProdId(Long relProdId)
  {
    this.relProdId = relProdId;
  }

  public String getRelProdName()
  {
    return this.relProdName;
  }

  public void setRelProdName(String relProdName)
  {
    this.relProdName = relProdName;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate)
  {
    this.recDate = recDate;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Serializable getId() {
    return this.relId;
  }
}
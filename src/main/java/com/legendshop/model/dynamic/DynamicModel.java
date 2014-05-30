package com.legendshop.model.dynamic;

import java.io.Serializable;

public class DynamicModel
  implements Serializable
{
  private Long tempId;
  private Long prodId;
  private String tempName;
  private Integer type;
  private Long sortId;
  private Model[] model;

  public Long getTempId()
  {
    return this.tempId;
  }

  public void setTempId(Long tempId)
  {
    this.tempId = tempId;
  }

  public Integer getType()
  {
    return this.type;
  }

  public void setType(Integer type)
  {
    this.type = type;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }

  public Model[] getModel()
  {
    return this.model;
  }

  public void setModel(Model[] model)
  {
    this.model = model;
  }

  public String getTempName()
  {
    return this.tempName;
  }

  public void setTempName(String tempName)
  {
    this.tempName = tempName;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;

public class ShopGrade
  implements BaseEntity
{
  private static final long serialVersionUID = -2994159243674997206L;
  private Integer gradeId;
  private String name;
  private Integer maxSortSize;
  private Integer maxNsortSize;
  private Integer maxProd;

  public ShopGrade()
  {
  }

  public ShopGrade(Integer gradeId)
  {
    this.gradeId = gradeId;
  }

  public ShopGrade(Integer gradeId, String name, Integer maxSortSize, Integer maxNsortSize, Integer maxProd)
  {
    this.gradeId = gradeId;
    this.name = name;
    this.maxSortSize = maxSortSize;
    this.maxNsortSize = maxNsortSize;
    this.maxProd = maxProd;
  }

  public Integer getGradeId()
  {
    return this.gradeId;
  }

  public void setGradeId(Integer gradeId)
  {
    this.gradeId = gradeId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Integer getMaxSortSize()
  {
    return this.maxSortSize;
  }

  public void setMaxSortSize(Integer maxSortSize)
  {
    this.maxSortSize = maxSortSize;
  }

  public Integer getMaxNsortSize()
  {
    return this.maxNsortSize;
  }

  public void setMaxNsortSize(Integer maxNsortSize)
  {
    this.maxNsortSize = maxNsortSize;
  }

  public Integer getMaxProd()
  {
    return this.maxProd;
  }

  public void setMaxProd(Integer maxProd)
  {
    this.maxProd = maxProd;
  }

  public Serializable getId() {
    return this.gradeId;
  }
}
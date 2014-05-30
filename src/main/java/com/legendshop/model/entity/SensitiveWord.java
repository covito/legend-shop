package com.legendshop.model.entity;

public class SensitiveWord
  implements BaseEntity
{
  private static final long serialVersionUID = -8021084501779040179L;
  private Long sensId;
  private Long sortId;
  private Long nsortId;
  private Long subNsortId;
  private String words;
  private Integer isGlobal;

  public Long getSensId()
  {
    return this.sensId;
  }

  public void setSensId(Long sensId) {
    this.sensId = sensId;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId) {
    this.sortId = sortId;
  }

  public Long getNsortId() {
    return this.nsortId;
  }

  public void setNsortId(Long nsortId) {
    this.nsortId = nsortId;
  }

  public Long getSubNsortId() {
    return this.subNsortId;
  }

  public void setSubNsortId(Long subNsortId) {
    this.subNsortId = subNsortId;
  }

  public String getWords() {
    return this.words;
  }

  public void setWords(String words) {
    this.words = words;
  }

  public Integer getIsGlobal()
  {
    return this.isGlobal;
  }

  public void setIsGlobal(Integer isGlobal) {
    this.isGlobal = isGlobal;
  }

  public Long getId()
  {
    return this.sensId;
  }
}
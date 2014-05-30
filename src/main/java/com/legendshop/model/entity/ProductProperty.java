package com.legendshop.model.entity;

import java.util.Date;
import java.util.List;

public class ProductProperty
  implements BaseEntity
{
  private static final long serialVersionUID = 7157140918610933422L;
  private Long propId;
  private String propName;
  private Long sortId;
  private String memo;
  private Short isRequired;
  private Short isMulti;
  private Long sequence;
  private Short status;
  private Short type;
  private Date modifyDate;
  private Date recDate;
  private Short isKeyProp;
  private Short isParamProp;
  private Short isSaleProp;
  private Short isForSearch;
  private Short isInputProp;
  private List<ProductPropertyValue> productPropertyValueList;

  public Long getPropId()
  {
    return this.propId;
  }

  public void setPropId(Long propId) {
    this.propId = propId;
  }

  public String getPropName()
  {
    return this.propName;
  }

  public void setPropName(String propName) {
    this.propName = propName;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId) {
    this.sortId = sortId;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Short getIsRequired()
  {
    return this.isRequired;
  }

  public void setIsRequired(Short isRequired) {
    this.isRequired = isRequired;
  }

  public Short getIsMulti()
  {
    return this.isMulti;
  }

  public void setIsMulti(Short isMulti) {
    this.isMulti = isMulti;
  }

  public Long getSequence()
  {
    return this.sequence;
  }

  public void setSequence(Long sequence) {
    this.sequence = sequence;
  }

  public Short getStatus()
  {
    return this.status;
  }

  public void setStatus(Short status) {
    this.status = status;
  }

  public Short getType()
  {
    return this.type;
  }

  public void setType(Short type) {
    this.type = type;
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

  public Short getIsKeyProp()
  {
    return this.isKeyProp;
  }

  public void setIsKeyProp(Short isKeyProp) {
    this.isKeyProp = isKeyProp;
  }

  public Short getIsParamProp()
  {
    return this.isParamProp;
  }

  public void setIsParamProp(Short isParamProp) {
    this.isParamProp = isParamProp;
  }

  public Short getIsSaleProp()
  {
    return this.isSaleProp;
  }

  public void setIsSaleProp(Short isSaleProp) {
    this.isSaleProp = isSaleProp;
  }

  public Short getIsForSearch()
  {
    return this.isForSearch;
  }

  public void setIsForSearch(Short isForSearch) {
    this.isForSearch = isForSearch;
  }

  public Short getIsInputProp()
  {
    return this.isInputProp;
  }

  public void setIsInputProp(Short isInputProp) {
    this.isInputProp = isInputProp;
  }

  public Long getId()
  {
    return this.propId;
  }

  public List<ProductPropertyValue> getProductPropertyValueList()
  {
    return this.productPropertyValueList;
  }

  public void setProductPropertyValueList(List<ProductPropertyValue> productPropertyValueList)
  {
    this.productPropertyValueList = productPropertyValueList;
  }
}
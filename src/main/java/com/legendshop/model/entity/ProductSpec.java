package com.legendshop.model.entity;

import java.util.Date;

public class ProductSpec
  implements BaseEntity
{
  private static final long serialVersionUID = -6327498161416369071L;
  private Long prodSpecId;
  private Long productId;
  private Long propId;
  private Long valueId;
  private Short isSku;
  private Long skuId;
  private Date modifyDate;
  private Date recDate;
  private String pic;

  public Long getProdSpecId()
  {
    return this.prodSpecId;
  }

  public void setProdSpecId(Long prodSpecId) {
    this.prodSpecId = prodSpecId;
  }

  public Long getProductId()
  {
    return this.productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getPropId()
  {
    return this.propId;
  }

  public void setPropId(Long propId) {
    this.propId = propId;
  }

  public Long getValueId()
  {
    return this.valueId;
  }

  public void setValueId(Long valueId) {
    this.valueId = valueId;
  }

  public Short getIsSku()
  {
    return this.isSku;
  }

  public void setIsSku(Short isSku) {
    this.isSku = isSku;
  }

  public Long getSkuId()
  {
    return this.skuId;
  }

  public void setSkuId(Long skuId) {
    this.skuId = skuId;
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
    return this.prodSpecId;
  }

  public String getPic()
  {
    return this.pic;
  }

  public void setPic(String pic)
  {
    this.pic = pic;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;

public class NsortBrandId
  implements Serializable
{
  private static final long serialVersionUID = 8233057942953041238L;
  private Long brandId;
  private Long nsortId;

  public NsortBrandId()
  {
  }

  public NsortBrandId(Long brandId, Long nsortId)
  {
    this.brandId = brandId;
    this.nsortId = nsortId;
  }

  public Long getBrandId()
  {
    return this.brandId;
  }

  public void setBrandId(Long brandId)
  {
    this.brandId = brandId;
  }

  public Long getNsortId()
  {
    return this.nsortId;
  }

  public void setNsortId(Long nsortId)
  {
    this.nsortId = nsortId;
  }

  public boolean equals(Object other)
  {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (!(other instanceof NsortBrandId))
      return false;
    NsortBrandId castOther = (NsortBrandId)other;

    return ((((getBrandId() == castOther.getBrandId()) || (
      (getBrandId() != null) && 
      (castOther.getBrandId() != null) && (getBrandId().equals(
      castOther.getBrandId()))))) && ((
      (getNsortId() == castOther.getNsortId()) || (
      (getNsortId() != null) && 
      (castOther.getNsortId() != null) && 
      (getNsortId().equals
      (castOther.getNsortId()))))));
  }

  public int hashCode()
  {
    int result = 17;

    result = 37 * result + 
      ((getBrandId() == null) ? 0 : getBrandId().hashCode());
    result = 37 * result + 
      ((getNsortId() == null) ? 0 : getNsortId().hashCode());
    return result;
  }
}
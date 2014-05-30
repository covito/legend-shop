package com.legendshop.model.entity;

public class NsortBrand
  implements BaseEntity
{
  private static final long serialVersionUID = -213312180753350928L;
  private NsortBrandId id;
  private String userName;

  public NsortBrand()
  {
  }

  public NsortBrand(NsortBrandId id, String userName)
  {
    this.id = id;
    this.userName = userName;
  }

  public NsortBrandId getId()
  {
    return this.id;
  }

  public void setId(NsortBrandId id)
  {
    this.id = id;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}
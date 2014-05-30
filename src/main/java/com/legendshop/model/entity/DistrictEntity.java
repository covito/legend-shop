package com.legendshop.model.entity;

import java.io.Serializable;

public class DistrictEntity
  implements Serializable
{
  private static final long serialVersionUID = -3334792853263711936L;
  private Integer provinceid;
  private Integer cityid;
  private Integer areaid;

  public Integer getProvinceid()
  {
    return this.provinceid;
  }

  public void setProvinceid(Integer provinceid) {
    this.provinceid = provinceid;
  }

  public Integer getCityid() {
    return this.cityid;
  }

  public void setCityid(Integer cityid) {
    this.cityid = cityid;
  }

  public Integer getAreaid() {
    return this.areaid;
  }

  public void setAreaid(Integer areaid) {
    this.areaid = areaid;
  }
}
package com.legendshop.model.entity;

public class Area
  implements BaseEntity
{
  private static final long serialVersionUID = -4386671445232853240L;
  private Integer id;
  private String areaid;
  private Integer cityid;
  private Integer code;
  private String area;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCityid() {
    return this.cityid;
  }

  public void setCityid(Integer cityid) {
    this.cityid = cityid;
  }

  public String getAreaid() {
    return this.areaid;
  }

  public void setAreaid(String areaid) {
    this.areaid = areaid;
  }

  public String getArea() {
    return this.area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Integer getCode() {
    return this.code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
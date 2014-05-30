package com.legendshop.model.entity;

import java.util.List;

public class City
  implements BaseEntity
{
  private static final long serialVersionUID = -4386671445232853240L;
  private Integer id;
  private Integer provinceid;
  private String cityid;
  private String city;
  private List<Area> area;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProvinceid() {
    return this.provinceid;
  }

  public void setProvinceid(Integer provinceid) {
    this.provinceid = provinceid;
  }

  public String getCityid() {
    return this.cityid;
  }

  public void setCityid(String cityid) {
    this.cityid = cityid;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public List<Area> getArea() {
    return this.area;
  }

  public void setArea(List<Area> area) {
    this.area = area;
  }
}
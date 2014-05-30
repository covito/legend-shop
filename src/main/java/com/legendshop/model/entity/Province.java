package com.legendshop.model.entity;

import java.util.List;

public class Province
  implements BaseEntity
{
  private static final long serialVersionUID = -4386671445232853240L;
  private Integer id;
  private String provinceid;
  private String province;
  private List<City> cities;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getProvinceid() {
    return this.provinceid;
  }

  public void setProvinceid(String provinceid) {
    this.provinceid = provinceid;
  }

  public String getProvince() {
    return this.province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public List<City> getCities() {
    return this.cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
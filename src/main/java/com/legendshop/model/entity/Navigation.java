package com.legendshop.model.entity;

import java.util.Set;
import java.util.TreeSet;

public class Navigation
  implements BaseEntity
{
  private static final long serialVersionUID = -4558504540386164776L;
  private Long naviId;
  private String name;
  private Integer seq;
  private Integer status;
  Set<NavigationItem> subItems = new TreeSet(new NavigationItemComparator());

  public Long getNaviId()
  {
    return this.naviId;
  }

  public void setNaviId(Long naviId) {
    this.naviId = naviId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<NavigationItem> getSubItems() {
    return this.subItems;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq) {
    this.seq = seq;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getId() {
    return this.naviId;
  }

  public void setSubItems(Set<NavigationItem> subItems)
  {
    this.subItems = subItems;
  }

  public void addSubItems(NavigationItem item)
  {
    if (getNaviId().equals(item.getNaviId()))
      this.subItems.add(item);
  }
}
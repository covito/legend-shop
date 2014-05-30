package com.legendshop.model.entity;

public class NavigationItem
  implements BaseEntity
{
  private static final long serialVersionUID = 6385487397211680962L;
  private Long itemId;
  private Long naviId;
  private String name;
  private String link;
  private Long seq;
  private Integer status;

  public Long getItemId()
  {
    return this.itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public Long getNaviId()
  {
    return this.naviId;
  }

  public void setNaviId(Long naviId) {
    this.naviId = naviId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink()
  {
    return this.link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Long getSeq()
  {
    return this.seq;
  }

  public void setSeq(Long seq) {
    this.seq = seq;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getId() {
    return this.itemId;
  }
}
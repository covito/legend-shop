package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Sort extends UploadFile
  implements NamedEntity
{
  private static final long serialVersionUID = -6590849925688867352L;
  private Long sortId;
  private String sortName;
  private String picture;
  private String userId;
  private String sortType;
  private String userName;
  private Integer seq;
  protected Integer status;
  private Integer headerMenu;
  private Integer navigationMenu;
  private List<Brand> brandList;
  Set<Nsort> nsort = new TreeSet(new NsortComparator());

  public Sort()
  {
  }

  public Sort(Long sortId)
  {
    this.sortId = sortId;
  }

  public Sort(Long sortId, String sortName, String picture, String userId, String userName)
  {
    this.sortId = sortId;
    this.sortName = sortName;
    this.picture = picture;
    this.userId = userId;
    this.userName = userName;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }

  public String getSortName()
  {
    return this.sortName;
  }

  public void setSortName(String sortName)
  {
    this.sortName = sortName;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public void setPicture(String picture)
  {
    this.picture = picture;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Set<Nsort> getNsort()
  {
    return this.nsort;
  }

  public void setNsort(Set<Nsort> nsort)
  {
    this.nsort = nsort;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }

  public void addSubSort(Nsort n)
  {
    if ((getSortId().equals(n.getSortId())) && 
      (n.getParentNsortId() == null))
      this.nsort.add(n);
  }

  public String getSortType()
  {
    return this.sortType;
  }

  public void setSortType(String sortType)
  {
    this.sortType = sortType;
  }

  public Integer getHeaderMenu()
  {
    return this.headerMenu;
  }

  public void setHeaderMenu(Integer headerMenu)
  {
    this.headerMenu = headerMenu;
  }

  public Integer getNavigationMenu()
  {
    return this.navigationMenu;
  }

  public void setNavigationMenu(Integer navigationMenu)
  {
    this.navigationMenu = navigationMenu;
  }

  public Serializable getId()
  {
    return this.sortId;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public String getName()
  {
    return this.sortName;
  }

  public List<Brand> getBrandList()
  {
    return this.brandList;
  }

  public void setBrandList(List<Brand> brandList)
  {
    this.brandList = brandList;
  }
}
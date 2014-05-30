package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Nsort
  implements BaseEntity
{
  private static final long serialVersionUID = 2161524693470603026L;
  private Long nsortId;
  private String nsortName;
  private Long sortId;
  private String sortName;
  private Long parentNsortId;
  private Nsort parent;
  private Integer seq;
  protected Integer status;
  private Integer sortDeputy;
  Set<Nsort> subSort = new TreeSet(new NsortComparator());

  public Nsort()
  {
  }

  public Nsort(Long nsortId)
  {
    this.nsortId = nsortId;
  }

  public Nsort(Long nsortId, String nsortName, Long sortId, Integer sortDeputy)
  {
    this.nsortId = nsortId;
    this.nsortName = nsortName;
    this.sortId = sortId;
    this.sortDeputy = sortDeputy;
  }

  public Long getNsortId()
  {
    return this.nsortId;
  }

  public void setNsortId(Long nsortId)
  {
    this.nsortId = nsortId;
  }

  public String getNsortName()
  {
    return this.nsortName;
  }

  public void setNsortName(String nsortName)
  {
    this.nsortName = nsortName;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }

  public Long getParentNsortId()
  {
    return this.parentNsortId;
  }

  public void setParentNsortId(Long parentNsortId)
  {
    this.parentNsortId = parentNsortId;
  }

  public Set<Nsort> getSubSort()
  {
    return this.subSort;
  }

  public void setSubSort(Set<Nsort> subSort)
  {
    this.subSort = subSort;
  }

  public void addSubSort(Nsort nsort)
  {
    if (getNsortId().equals(nsort.getParentNsortId()))
      this.subSort.add(nsort);
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }

  public Nsort getParent()
  {
    return this.parent;
  }

  public void setParent(Nsort parent)
  {
    this.parent = parent;
  }

  public Integer getSortDeputy()
  {
    return this.sortDeputy;
  }

  public void setSortDeputy(Integer sortDeputy)
  {
    this.sortDeputy = sortDeputy;
  }

  public Serializable getId()
  {
    return this.nsortId;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public String getSortName()
  {
    return this.sortName;
  }

  public void setSortName(String sortName)
  {
    this.sortName = sortName;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tag
  implements BaseEntity
{
  private static final long serialVersionUID = -8233024988103164755L;
  private Long tagId;
  private String name;
  private Long sortId;
  private Sort sort;
  private String sortName;
  private Long newsCategoryId;
  private String newsCategoryName;
  private List<NewsTitle> newsTitles;
  private String type;
  private Integer status;
  private Date createTime;
  private String userId;
  private String userName;
  private List<Advertisement> advertisementList;
  private List<Product> commendProdList;

  public Tag()
  {
  }

  public Tag(Long tagId, String name, String sortName, String newsCategoryName, String type, Integer status, Date createTime, String userId, String userName)
  {
    this.tagId = tagId;
    this.name = name;
    this.sortName = sortName;
    this.newsCategoryName = newsCategoryName;
    this.type = type;
    this.status = status;
    this.createTime = createTime;
    this.userId = userId;
    this.userName = userName;
  }

  public Long getTagId()
  {
    return this.tagId;
  }

  public void setTagId(Long tagId)
  {
    this.tagId = tagId;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
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

  public Long getNewsCategoryId()
  {
    return this.newsCategoryId;
  }

  public void setNewsCategoryId(Long newsCategoryId)
  {
    this.newsCategoryId = newsCategoryId;
  }

  public String getNewsCategoryName()
  {
    return this.newsCategoryName;
  }

  public void setNewsCategoryName(String newsCategoryName)
  {
    this.newsCategoryName = newsCategoryName;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
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

  public Serializable getId()
  {
    return this.tagId;
  }

  public Sort getSort()
  {
    return this.sort;
  }

  public void setSort(Sort sort)
  {
    this.sort = sort;
  }

  public List<NewsTitle> getNewsTitles()
  {
    return this.newsTitles;
  }

  public void setNewsTitles(List<NewsTitle> newsTitles)
  {
    this.newsTitles = newsTitles;
  }

  public List<Advertisement> getAdvertisementList()
  {
    return this.advertisementList;
  }

  public void setAdvertisementList(List<Advertisement> advertisementList)
  {
    this.advertisementList = advertisementList;
  }

  public List<Product> getCommendProdList()
  {
    return this.commendProdList;
  }

  public void setCommendProdList(List<Product> commendProdList)
  {
    this.commendProdList = commendProdList;
  }
}
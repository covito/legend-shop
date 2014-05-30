package com.legendshop.model.entity;

import java.util.Date;

public class Myleague
  implements BaseEntity
{
  private static final long serialVersionUID = -7191239531193869658L;
  private Long id;
  private String userId;
  private String friendId;
  private String friendName;
  private Integer displayOrder;
  private String banner;
  private String province;
  private String city;
  private String area;
  private Integer status;
  private Date addtime;

  public Myleague(String friendId, String friendName, String banner)
  {
    this.friendId = friendId;
    this.friendName = friendName;
    this.banner = banner;
  }

  public Myleague(String friendId, String friendName, String banner, String province, String city, String area)
  {
    this.friendId = friendId;
    this.friendName = friendName;
    this.banner = banner;
    this.province = province;
    this.city = city;
    this.area = area;
  }

  public String getBanner()
  {
    return this.banner;
  }

  public void setBanner(String banner)
  {
    this.banner = banner;
  }

  public Integer getDisplayOrder()
  {
    return this.displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder)
  {
    this.displayOrder = displayOrder;
  }

  public Myleague()
  {
  }

  public Myleague(Long id)
  {
    this.id = id;
  }

  public Myleague(Long id, String userId, String friendId, String friendName, Integer status, Date addtime)
  {
    this.id = id;
    this.userId = userId;
    this.friendId = friendId;
    this.friendName = friendName;
    this.status = status;
    this.addtime = addtime;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public String getFriendId()
  {
    return this.friendId;
  }

  public void setFriendId(String friendId)
  {
    this.friendId = friendId;
  }

  public String getFriendName()
  {
    return this.friendName;
  }

  public void setFriendName(String friendName)
  {
    this.friendName = friendName;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Date getAddtime()
  {
    return this.addtime;
  }

  public void setAddtime(Date addtime)
  {
    this.addtime = addtime;
  }

  public String getProvince()
  {
    return this.province;
  }

  public void setProvince(String province)
  {
    this.province = province;
  }

  public String getCity()
  {
    return this.city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getArea()
  {
    return this.area;
  }

  public void setArea(String area)
  {
    this.area = area;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginHistory
  implements Serializable
{
  private static final long serialVersionUID = 8643893857459673801L;
  private Long id;
  private String userName;
  private String ip;
  private Date time;
  private Date startTime;
  private Date endTime;
  private Integer loginTimes;
  private String country;
  private String area;

  public LoginHistory()
  {
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getIp()
  {
    return this.ip;
  }

  public void setIp(String ip)
  {
    this.ip = ip;
  }

  public Date getTime()
  {
    return this.time;
  }

  public void setTime(Date time)
  {
    this.time = time;
  }

  public Date getEndTime()
  {
    return this.endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public Date getStartTime()
  {
    return this.startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Integer getLoginTimes()
  {
    return this.loginTimes;
  }

  public void setLoginTimes(Integer loginTimes)
  {
    this.loginTimes = loginTimes;
  }

  public LoginHistory(String userName, Integer loginTimes)
  {
    this.userName = userName;
    this.loginTimes = loginTimes;
  }

  public String getCountry()
  {
    return this.country;
  }

  public void setCountry(String country)
  {
    this.country = country;
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
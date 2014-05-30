package com.legendshop.model.visit;

import java.io.Serializable;
import java.util.Date;

public class VisitItem
  implements Serializable
{
  private static final long serialVersionUID = -6147205011493182266L;
  private String id;
  private String name;
  private String title;
  private Date date;
  private String pic;

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public Date getDate()
  {
    return this.date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public String getPic()
  {
    return this.pic;
  }

  public void setPic(String pic)
  {
    this.pic = pic;
  }
}
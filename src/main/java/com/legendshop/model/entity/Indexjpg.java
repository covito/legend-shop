package com.legendshop.model.entity;

import java.util.Date;

public class Indexjpg extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = 1888032358468727806L;
  private Long id;
  private String href;
  private String img;
  private String alt;
  private String title;
  private String stitle;
  private String link;
  private String titleLink;
  private String userId;
  private String userName;
  private int status;
  private Integer seq;
  private Date uploadTime;

  public Indexjpg()
  {
  }

  public Indexjpg(Long id, String href, String img, String alt, String title, String stitle, String link, String titleLink, String userName)
  {
    this.id = id;
    this.href = href;
    this.img = img;
    this.alt = alt;
    this.title = title;
    this.stitle = stitle;
    this.link = link;
    this.titleLink = titleLink;
    this.userName = userName;
  }

  public Indexjpg(Long id, String href, String img, String alt, String title, String stitle, String link, String titleLink, String userId, String userName)
  {
    this.id = id;
    this.href = href;
    this.img = img;
    this.alt = alt;
    this.title = title;
    this.stitle = stitle;
    this.link = link;
    this.titleLink = titleLink;
    this.userId = userId;
    this.userName = userName;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getHref()
  {
    return this.href;
  }

  public void setHref(String href)
  {
    this.href = href;
  }

  public String getImg()
  {
    return this.img;
  }

  public void setImg(String img)
  {
    this.img = img;
  }

  public String getAlt()
  {
    return this.alt;
  }

  public void setAlt(String alt)
  {
    this.alt = alt;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getStitle()
  {
    return this.stitle;
  }

  public void setStitle(String stitle)
  {
    this.stitle = stitle;
  }

  public String getLink()
  {
    return this.link;
  }

  public void setLink(String link)
  {
    this.link = link;
  }

  public String getTitleLink()
  {
    return this.titleLink;
  }

  public void setTitleLink(String titleLink)
  {
    this.titleLink = titleLink;
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

  public int getStatus()
  {
    return this.status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }

  public Date getUploadTime()
  {
    return this.uploadTime;
  }

  public void setUploadTime(Date uploadTime)
  {
    this.uploadTime = uploadTime;
  }
}
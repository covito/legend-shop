package com.legendshop.model.entity;

public class ExternalLink extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = 5467294318649826405L;
  private Long id;
  private String url;
  private String wordlink;
  private String content;
  private Integer bs;
  private String userId;
  private String userName;
  private String picture;

  public ExternalLink()
  {
  }

  public ExternalLink(Long id)
  {
    this.id = id;
  }

  public ExternalLink(Long id, String url, String wordlink, String content, Integer bs, String userId, String userName)
  {
    this.id = id;
    this.url = url;
    this.wordlink = wordlink;
    this.content = content;
    this.bs = bs;
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

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getWordlink()
  {
    return this.wordlink;
  }

  public void setWordlink(String wordlink)
  {
    this.wordlink = wordlink;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public Integer getBs()
  {
    return this.bs;
  }

  public void setBs(Integer bs)
  {
    this.bs = bs;
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

  public String getPicture()
  {
    return this.picture;
  }

  public void setPicture(String picture)
  {
    this.picture = picture;
  }
}
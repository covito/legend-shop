package com.legendshop.model.entity;

public class Advertisement extends UploadFile
  implements BaseEntity
{
  private static final long serialVersionUID = 3118175679673767409L;
  private Long id;
  private String picUrl;
  private String linkUrl;
  private String userId;
  private String userName;
  private Integer enabled;
  private String type;
  private String sourceInput;
  private String title;

  public String getSourceInput()
  {
    return this.sourceInput;
  }

  public void setSourceInput(String sourceInput)
  {
    this.sourceInput = sourceInput;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getPicUrl()
  {
    return this.picUrl;
  }

  public void setPicUrl(String picUrl)
  {
    this.picUrl = picUrl;
  }

  public String getLinkUrl()
  {
    return this.linkUrl;
  }

  public void setLinkUrl(String linkUrl)
  {
    this.linkUrl = linkUrl;
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

  public Integer getEnabled()
  {
    return this.enabled;
  }

  public void setEnabled(Integer enabled)
  {
    this.enabled = enabled;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }
}
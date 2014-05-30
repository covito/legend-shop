package com.legendshop.model.entity;

public class Logo extends UploadFile
{
  private static final long serialVersionUID = -8146764684614656689L;
  private String logoPic;
  private String userId;
  private String userName;

  public String getId()
  {
    return this.userId;
  }

  public String getLogoPic() {
    return this.logoPic;
  }

  public void setLogoPic(String logoPic)
  {
    this.logoPic = logoPic;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getUserName()
  {
    return this.userName;
  }
}
package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Ask
  implements BaseEntity
{
  private Long askId;
  private String userId;
  private String userName;
  private Long prodId;
  private Long shopId;
  private String type;
  private String content;
  private String comment;
  private Date createTime;
  private Date replyTime;

  public Long getAskId()
  {
    return this.askId;
  }

  public void setAskId(Long askId)
  {
    this.askId = askId;
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

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public Long getShopId()
  {
    return this.shopId;
  }

  public void setShopId(Long shopId)
  {
    this.shopId = shopId;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getComment()
  {
    return this.comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Date getReplyTime()
  {
    return this.replyTime;
  }

  public void setReplyTime(Date replyTime)
  {
    this.replyTime = replyTime;
  }

  public Serializable getId() {
    return this.askId;
  }
}
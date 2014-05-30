package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Message
  implements BaseEntity
{
  private String msgId;
  private String content;
  private Integer delBySender;
  private Integer delByReceiver;
  private Integer isread;
  private Integer isdraft;
  private String title;
  private String sender;
  private String receiver;
  private Date createDate;
  private Date modifyDate;

  public String getMsgId()
  {
    return this.msgId;
  }

  public void setMsgId(String msgId)
  {
    this.msgId = msgId;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public Integer getDelBySender()
  {
    return this.delBySender;
  }

  public void setDelBySender(Integer delBySender)
  {
    this.delBySender = delBySender;
  }

  public Integer getDelByReceiver()
  {
    return this.delByReceiver;
  }

  public void setDelByReceiver(Integer delByReceiver)
  {
    this.delByReceiver = delByReceiver;
  }

  public Integer getIsread()
  {
    return this.isread;
  }

  public void setIsread(Integer isread)
  {
    this.isread = isread;
  }

  public Integer getIsdraft()
  {
    return this.isdraft;
  }

  public void setIsdraft(Integer isdraft)
  {
    this.isdraft = isdraft;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getSender()
  {
    return this.sender;
  }

  public void setSender(String sender)
  {
    this.sender = sender;
  }

  public String getReceiver()
  {
    return this.receiver;
  }

  public void setReceiver(String receiver)
  {
    this.receiver = receiver;
  }

  public Date getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }

  public Date getModifyDate()
  {
    return this.modifyDate;
  }

  public void setModifyDate(Date modifyDate)
  {
    this.modifyDate = modifyDate;
  }

  public Serializable getId()
  {
    return this.msgId;
  }
}
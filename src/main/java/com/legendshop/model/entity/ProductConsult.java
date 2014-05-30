package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductConsult
  implements BaseEntity
{
  private static final long serialVersionUID = 2645969133712434418L;
  private Long consId;
  private Integer pointType;
  private Long prodId;
  private String prodName;
  private String userId;
  private String userName;
  private String content;
  private String answer;
  private Date recDate;
  private String postip;
  private Date answertime;
  private String askUserName;
  private String ansUserName;
  private Date startTime;
  private Date endTime;
  private String gradeName;
  private Integer replyed;

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

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getAnswer()
  {
    return this.answer;
  }

  public void setAnswer(String answer)
  {
    this.answer = answer;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate)
  {
    this.recDate = recDate;
  }

  public String getPostip()
  {
    return this.postip;
  }

  public void setPostip(String postip)
  {
    this.postip = postip;
  }

  public Date getAnswertime()
  {
    return this.answertime;
  }

  public void setAnswertime(Date answertime)
  {
    this.answertime = answertime;
  }

  public Serializable getId()
  {
    return this.consId;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }

  public String getAskUserName()
  {
    return this.askUserName;
  }

  public void setAskUserName(String askUserName)
  {
    this.askUserName = askUserName;
  }

  public String getAnsUserName()
  {
    return this.ansUserName;
  }

  public void setAnsUserName(String ansUserName)
  {
    this.ansUserName = ansUserName;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public void setProdName(String prodName)
  {
    this.prodName = prodName;
  }

  public Date getStartTime()
  {
    return this.startTime;
  }

  public void setStartTime(Date startTime)
  {
    this.startTime = startTime;
  }

  public Date getEndTime()
  {
    return this.endTime;
  }

  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }

  public Long getConsId()
  {
    return this.consId;
  }

  public void setConsId(Long consId)
  {
    this.consId = consId;
  }

  public String getGradeName()
  {
    return this.gradeName;
  }

  public void setGradeName(String gradeName)
  {
    this.gradeName = gradeName;
  }

  public Integer getPointType()
  {
    return this.pointType;
  }

  public void setPointType(Integer pointType)
  {
    this.pointType = pointType;
  }

  public Integer getReplyed()
  {
    return this.replyed;
  }

  public void setReplyed(Integer replyed)
  {
    this.replyed = replyed;
  }
}
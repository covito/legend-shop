package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Score
  implements BaseEntity
{
  private static final long serialVersionUID = 7442720488698346761L;
  private Long scoreId;
  private Long subId;
  private Long score;
  private String scoreType;
  private String userName;
  private Date recDate;

  public Long getScoreId()
  {
    return this.scoreId;
  }

  public void setScoreId(Long scoreId)
  {
    this.scoreId = scoreId;
  }

  public Long getSubId()
  {
    return this.subId;
  }

  public void setSubId(Long subId)
  {
    this.subId = subId;
  }

  public Long getScore()
  {
    return this.score;
  }

  public void setScore(Long score)
  {
    this.score = score;
  }

  public String getScoreType()
  {
    return this.scoreType;
  }

  public void setScoreType(String scoreType)
  {
    this.scoreType = scoreType;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate)
  {
    this.recDate = recDate;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public Serializable getId() {
    return this.scoreId;
  }
}
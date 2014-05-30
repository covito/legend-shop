package com.legendshop.model.entity;

import java.util.Date;

public class UserComment
  implements BaseEntity
{
  private static final long serialVersionUID = 6496927494248199158L;
  private Long id;
  private Integer commentType;
  private String userId;
  private String userName;
  private String yourName;
  private String toUserName;
  private String content;
  private String answer;
  private Date recDate;
  private Date answertime;
  private String postip;
  private Integer status;

  public String getAnswer()
  {
    return this.answer;
  }

  public void setAnswer(String answer)
  {
    this.answer = answer;
  }

  public Date getAnswertime()
  {
    return this.answertime;
  }

  public void setAnswertime(Date answertime)
  {
    this.answertime = answertime;
  }

  public UserComment()
  {
  }

  public UserComment(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Integer getCommentType()
  {
    return this.commentType;
  }

  public void setCommentType(Integer commentType)
  {
    this.commentType = commentType;
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

  public String getYourName()
  {
    return this.yourName;
  }

  public void setYourName(String yourName)
  {
    this.yourName = yourName;
  }

  public String getToUserName()
  {
    return this.toUserName;
  }

  public void setToUserName(String toUserName)
  {
    this.toUserName = toUserName;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public String getPostip()
  {
    return this.postip;
  }

  public void setPostip(String postip)
  {
    this.postip = postip;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Date getRecDate()
  {
    return this.recDate;
  }

  public void setRecDate(Date recDate)
  {
    this.recDate = recDate;
  }
}
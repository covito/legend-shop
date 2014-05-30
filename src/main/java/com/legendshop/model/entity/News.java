package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class News extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = 5866824730655175491L;
  private Long newsId;
  private Long newsCategoryId;
  private NewsCategory newsCategory;
  private String newsCategoryName;
  private Sort sort;
  private Long sortId;
  private String sortName;
  private String newsTitle;
  private String newsContent;
  private Date newsDate;
  private Integer status;
  private Integer position;
  private Integer highLine;
  private String userId;
  private String userName;
  private String newsBrief;

  public News(Long newsId, Long newsCategoryId, String newsCategoryName, Long sortId, String sortName, String newsTitle, String newsContent, Date newsDate, Integer status, Integer position, String userId, String userName, Integer highLine)
  {
    this.newsId = newsId;
    this.newsCategoryId = newsCategoryId;
    this.newsCategoryName = newsCategoryName;
    this.sortId = sortId;
    this.sortName = sortName;
    this.newsTitle = newsTitle;
    this.newsContent = newsContent;
    this.newsDate = newsDate;
    this.status = status;
    this.position = position;
    this.userId = userId;
    this.userName = userName;
    this.highLine = highLine;
  }

  public News()
  {
  }

  public News(Long newsId)
  {
    this.newsId = newsId;
  }

  public News(Long newsId, Long newsCategoryId, String newsCategoryName, String newsTitle) {
    this.newsId = newsId;
    this.newsCategoryId = newsCategoryId;
    this.newsCategoryName = newsCategoryName;
    this.newsTitle = newsTitle;
  }

  public Long getNewsId()
  {
    return this.newsId;
  }

  public void setNewsId(Long newsId)
  {
    this.newsId = newsId;
  }

  public Long getNewsCategoryId()
  {
    return this.newsCategoryId;
  }

  public void setNewsCategoryId(Long newsCategoryId)
  {
    this.newsCategoryId = newsCategoryId;
  }

  public String getNewsTitle()
  {
    return this.newsTitle;
  }

  public void setNewsTitle(String newsTitle)
  {
    this.newsTitle = newsTitle;
  }

  public String getNewsContent()
  {
    return this.newsContent;
  }

  public void setNewsContent(String newsContent)
  {
    this.newsContent = newsContent;
  }

  public Date getNewsDate()
  {
    return this.newsDate;
  }

  public void setNewsDate(Date newsDate)
  {
    this.newsDate = newsDate;
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

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }

  public String getSortName()
  {
    return this.sortName;
  }

  public void setSortName(String sortName)
  {
    this.sortName = sortName;
  }

  public Sort getSort()
  {
    return this.sort;
  }

  public void setSort(Sort sort)
  {
    this.sort = sort;
  }

  public NewsCategory getNewsCategory()
  {
    return this.newsCategory;
  }

  public void setNewsCategory(NewsCategory newsCategory)
  {
    this.newsCategory = newsCategory;
  }

  public String getNewsCategoryName()
  {
    return this.newsCategoryName;
  }

  public void setNewsCategoryName(String newsCategoryName)
  {
    this.newsCategoryName = newsCategoryName;
  }

  public Integer getHighLine()
  {
    return this.highLine;
  }

  public void setHighLine(Integer highLine)
  {
    this.highLine = highLine;
  }

  public String getNewsBrief()
  {
    return this.newsBrief;
  }

  public void setNewsBrief(String newsBrief)
  {
    this.newsBrief = newsBrief;
  }

  public Serializable getId()
  {
    return this.newsId;
  }

  public Integer getPosition()
  {
    return this.position;
  }

  public void setPosition(Integer position)
  {
    this.position = position;
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(" [newsId=").append(this.newsId).append(", sortId=").append(this.sortId).append(", sortName=").append(this.sortName).append(", newsTitle=").append(this.newsTitle).append
      (", userName=").append(this.userName).append(", status=").append(this.status).append(", position=").append(this.position).append("] ");
    return sb.toString();
  }
}
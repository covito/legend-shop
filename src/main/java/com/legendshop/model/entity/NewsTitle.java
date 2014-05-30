package com.legendshop.model.entity;

import java.io.Serializable;

public class NewsTitle
  implements Serializable
{
  private static final long serialVersionUID = 5834756315130185151L;
  private Long newsId;
  private String newsTitle;

  public Long getNewsId()
  {
    return this.newsId;
  }

  public void setNewsId(Long newsId)
  {
    this.newsId = newsId;
  }

  public String getNewsTitle()
  {
    return this.newsTitle;
  }

  public void setNewsTitle(String newsTitle)
  {
    this.newsTitle = newsTitle;
  }
}
package com.legendshop.spi.form;

public class SearchForm
{
  private static final long serialVersionUID = 2489827230218323847L;
  private String curPageNOTop = "1";
  private String keyword;
  private Long sortId;

  public String getCurPageNOTop()
  {
    return this.curPageNOTop;
  }

  public void setCurPageNOTop(String curPageNOTop)
  {
    this.curPageNOTop = curPageNOTop;
  }

  public String getKeyword()
  {
    return this.keyword;
  }

  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }
}
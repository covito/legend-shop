package com.legendshop.model.entity;

import com.legendshop.model.ModelUtil;
import java.util.Date;

public class ProductComment extends AbstractEntity
  implements BaseEntity
{
  private static final long serialVersionUID = 3118765177322131488L;
  private Long id;
  private Long prodId;
  private String ownerName;
  private String userName;
  private String title;
  private String content;
  private Date addtime;
  private String postip;
  private Integer score;
  private String replyContent;
  private String replyName;
  private Date replyTime;
  private String prodName;
  private String advtge;
  private String defect;
  private Date buyTime;

  public ProductComment()
  {
  }

  public ProductComment(Long id, Long prodId, String ownerName, String userName, String content, Date addtime, String postip, Integer score, String replyContent, String replyName, Date replyTime, String prodName)
  {
    this.id = id;
    this.prodId = prodId;
    this.ownerName = ownerName;
    this.userName = userName;
    this.content = content;
    this.addtime = addtime;
    this.postip = postip;
    this.score = score;
    this.replyContent = replyContent;
    this.replyName = replyName;
    this.replyTime = replyTime;
    this.prodName = prodName;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getProdId()
  {
    return this.prodId;
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
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

  public Date getAddtime()
  {
    return this.addtime;
  }

  public void setAddtime(Date addtime)
  {
    this.addtime = addtime;
  }

  public String getPostip()
  {
    return this.postip;
  }

  public void setPostip(String postip)
  {
    this.postip = postip;
  }

  public Integer getScore()
  {
    return this.score;
  }

  public void setScore(Integer score)
  {
    this.score = score;
  }

  public String getReplyContent()
  {
    return this.replyContent;
  }

  public void setReplyContent(String replyContent)
  {
    this.replyContent = replyContent;
  }

  public String getReplyName()
  {
    return this.replyName;
  }

  public void setReplyName(String replyName)
  {
    this.replyName = replyName;
  }

  public Date getReplyTime()
  {
    return this.replyTime;
  }

  public void setReplyTime(Date replyTime)
  {
    this.replyTime = replyTime;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public void setProdName(String prodName)
  {
    this.prodName = prodName;
  }

  public String getOwnerName()
  {
    return this.ownerName;
  }

  public void setOwnerName(String ownerName)
  {
    this.ownerName = ownerName;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getAdvtge()
  {
    return this.advtge;
  }

  public void setAdvtge(String advtge)
  {
    this.advtge = advtge;
  }

  public String getDefect()
  {
    return this.defect;
  }

  public void setDefect(String defect)
  {
    this.defect = defect;
  }

  public void validate()
  {
    ModelUtil util = new ModelUtil();
    util.isNotNull(getTitle(), "Title");
    util.range(getTitle(), 4, 20, "Title");
    util.range(getContent(), 5, 100, "Content");
    util.range(getAdvtge(), 5, 100, "Advtge");
    util.range(getDefect(), 5, 100, "Defect");
    util.isNotNull(getProdId(), "ProdId");
    util.isNotNull(getUserName(), "UserName");
  }

  public Date getBuyTime()
  {
    return this.buyTime;
  }

  public void setBuyTime(Date buyTime)
  {
    this.buyTime = buyTime;
  }
}
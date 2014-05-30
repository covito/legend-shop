package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Date;

public class CashFlow
  implements BaseEntity
{
  private static final long serialVersionUID = -2251274445168293446L;
  private Long flowId;
  private String userId;
  private String userName;
  private String operatorId;
  private String detailId;
  private String detail;
  private String direction;
  private Double money;
  private String action;
  private Date createTime;

  public Long getFlowId()
  {
    return this.flowId;
  }

  public void setFlowId(Long flowId)
  {
    this.flowId = flowId;
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

  public String getDetailId()
  {
    return this.detailId;
  }

  public void setDetailId(String detailId)
  {
    this.detailId = detailId;
  }

  public String getDetail()
  {
    return this.detail;
  }

  public void setDetail(String detail)
  {
    this.detail = detail;
  }

  public String getDirection()
  {
    return this.direction;
  }

  public void setDirection(String direction)
  {
    this.direction = direction;
  }

  public Double getMoney()
  {
    return this.money;
  }

  public void setMoney(Double money)
  {
    this.money = money;
  }

  public String getAction()
  {
    return this.action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public Date getCreateTime()
  {
    return this.createTime;
  }

  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }

  public Serializable getId() {
    return this.flowId;
  }

  public String getOperatorId() {
    return this.operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }
}
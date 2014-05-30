package com.legendshop.model.entity;

import com.legendshop.model.dynamic.Model;
import java.util.List;

public class DynamicTemp
  implements NamedEntity
{
  private static final long serialVersionUID = 8083024182117947985L;
  private Long id;
  private String name;
  private String content;
  private Integer type;
  private String userName;
  private Long sortId;
  private String sortName;
  private Integer status;
  private List<Model> modelList;

  public DynamicTemp()
  {
  }

  public DynamicTemp(Long id, String name, String content, Integer type, String userName, Long sortId, Integer status, String sortName)
  {
    this.id = id;
    this.name = name;
    this.content = content;
    this.type = type;
    this.userName = userName;
    this.sortId = sortId;
    this.status = status;
    this.sortName = sortName;
  }

  public DynamicTemp(String content, Integer type, String userName)
  {
    this.content = content;
    this.type = type;
    this.userName = userName;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public Integer getType()
  {
    return this.type;
  }

  public void setType(Integer type)
  {
    this.type = type;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }

  public Integer getStatus()
  {
    return this.status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public String getSortName()
  {
    return this.sortName;
  }

  public void setSortName(String sortName)
  {
    this.sortName = sortName;
  }

  public List<Model> getModelList()
  {
    return this.modelList;
  }

  public void setModelList(List<Model> modelList)
  {
    this.modelList = modelList;
  }
}
package com.legendshop.model.template;

import com.legendshop.model.StatusKeyValueEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempletEntity
  implements Serializable
{
  private static final long serialVersionUID = 4076388169340185759L;
  private List<StatusKeyValueEntity> frontEndTempletList;
  private List<StatusKeyValueEntity> backEndTempletList;
  private Map<String, Templet> frontEndTempletMap;
  private Map<String, Templet> backEndTempletMap;

  public List<StatusKeyValueEntity> getFrontEndTempletList()
  {
    return this.frontEndTempletList;
  }

  public void setFrontEndTempletList(List<StatusKeyValueEntity> frontEndTempletList)
  {
    this.frontEndTempletList = frontEndTempletList;
  }

  public List<StatusKeyValueEntity> getBackEndTempletList()
  {
    return this.backEndTempletList;
  }

  public void setBackEndTempletList(List<StatusKeyValueEntity> backEndTempletList)
  {
    this.backEndTempletList = backEndTempletList;
  }

  public Map<String, Templet> getFrontEndTempletMap()
  {
    return this.frontEndTempletMap;
  }

  public void addFrontEndTempletMap(Templet templet)
  {
    if (this.frontEndTempletMap == null)
      this.frontEndTempletMap = new HashMap();

    this.frontEndTempletMap.put(templet.getId(), templet);
  }

  public Map<String, Templet> getBackEndTempletMap()
  {
    return this.backEndTempletMap;
  }

  public void addBackEndTempletMap(Templet templet)
  {
    if (this.backEndTempletMap == null)
      this.backEndTempletMap = new HashMap();

    this.backEndTempletMap.put(templet.getId(), templet);
  }
}
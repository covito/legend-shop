package com.legendshop.core.page.impl;

import com.legendshop.model.StatusKeyValueEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TempletSetting
  implements Serializable
{
  private static final long serialVersionUID = 5161478210297452879L;
  private Map<String, String> _$2;
  private List<StatusKeyValueEntity> _$1;

  public Map<String, String> getTempletMap()
  {
    return this._$2;
  }

  public void setTempletMap(Map<String, String> paramMap)
  {
    this._$2 = paramMap;
  }

  public List<StatusKeyValueEntity> getTempletList()
  {
    return this._$1;
  }

  public void addTempletList(StatusKeyValueEntity paramStatusKeyValueEntity)
  {
    if (this._$1 == null)
      this._$1 = new ArrayList();
    this._$1.add(paramStatusKeyValueEntity);
  }
}
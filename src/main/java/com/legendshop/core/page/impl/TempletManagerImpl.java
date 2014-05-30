package com.legendshop.core.page.impl;

import com.legendshop.core.page.TempletManager;
import com.legendshop.model.StatusKeyValueEntity;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TempletManagerImpl
  implements TempletManager
{
  private final Logger _$3 = LoggerFactory.getLogger(TempletManagerImpl.class);
  private TempletSetting _$2;
  private TempletSetting _$1;

  public void init()
  {
    this._$3.debug("TempletManager initializing");
    init(this._$2);
    init(this._$1);
  }

  public void init(TempletSetting paramTempletSetting)
  {
    Set localSet = paramTempletSetting.getTempletMap().keySet();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      StatusKeyValueEntity localStatusKeyValueEntity = new StatusKeyValueEntity();
      localStatusKeyValueEntity.setKey(str);
      localStatusKeyValueEntity.setValue("templet_" + str);
      paramTempletSetting.addTempletList(localStatusKeyValueEntity);
    }
  }

  public TempletSetting getFrontEnd()
  {
    return this._$2;
  }

  public void setFrontEnd(TempletSetting paramTempletSetting)
  {
    this._$2 = paramTempletSetting;
  }

  public TempletSetting getBackEnd()
  {
    return this._$1;
  }

  public void setBackEnd(TempletSetting paramTempletSetting)
  {
    this._$1 = paramTempletSetting;
  }

  public List<StatusKeyValueEntity> getFrontEndTempletList()
  {
    return this._$2.getTempletList();
  }

  public List<StatusKeyValueEntity> getBackEndTempletList()
  {
    return this._$1.getTempletList();
  }

  public String getFrontEndTempletById(String paramString)
  {
    return ((String)this._$2.getTempletMap().get(paramString));
  }

  public String getBackEndTempletById(String paramString)
  {
    return ((String)this._$1.getTempletMap().get(paramString));
  }

  public Map<String, String> getFrontEndtTempletMap()
  {
    return this._$2.getTempletMap();
  }

  public Map<String, String> getBackEndTempletMap()
  {
    return this._$1.getTempletMap();
  }
}
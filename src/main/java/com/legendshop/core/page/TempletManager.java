package com.legendshop.core.page;

import com.legendshop.model.StatusKeyValueEntity;
import java.util.List;
import java.util.Map;

public abstract interface TempletManager
{
  public abstract List<StatusKeyValueEntity> getFrontEndTempletList();

  public abstract List<StatusKeyValueEntity> getBackEndTempletList();

  public abstract String getFrontEndTempletById(String paramString);

  public abstract String getBackEndTempletById(String paramString);

  public abstract Map<String, String> getFrontEndtTempletMap();

  public abstract Map<String, String> getBackEndTempletMap();
}
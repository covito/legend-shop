package com.legendshop.core.tag;

import java.util.Map;

public abstract interface TableCache
{
  public abstract Map<String, String> getCodeTable(String paramString);

  public abstract void initCodeTablesCache();
}
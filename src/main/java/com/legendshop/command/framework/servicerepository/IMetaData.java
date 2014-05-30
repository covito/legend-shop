package com.legendshop.command.framework.servicerepository;

import java.util.Map;

public abstract interface IMetaData
{
  public abstract Object get(String paramString);

  public abstract Object getOne();

  public abstract Map get();
}
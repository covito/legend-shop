package com.legendshop.model.dynamic;

import java.io.Serializable;

public class Item
  implements Serializable
{
  private static final long serialVersionUID = -7793296752968339250L;
  private String key = null;
  private String value = null;

  public Item()
  {
  }

  public Item(String key, String value)
  {
    this.key = key;
    this.value = value;
  }

  public Item(Long key, String value)
  {
    this.key = String.valueOf(key);
    this.value = value;
  }

  public String getKey()
  {
    return this.key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }
}
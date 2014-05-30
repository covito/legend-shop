package com.legendshop.model.dynamic;

import java.io.Serializable;

public class Model
  implements Serializable
{
  private static final long serialVersionUID = -2267776997265702880L;
  private String id;
  private String type;
  private Item[] items;

  public Item[] getItems()
  {
    return this.items;
  }

  public void setItems(Item[] items)
  {
    this.items = items;
  }

  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public Model clone()
  {
    Model model = new Model();
    model.setId(getId());
    model.setItems(getItems());
    model.setType(getType());
    return model;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }
}
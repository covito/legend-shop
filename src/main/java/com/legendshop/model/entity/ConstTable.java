package com.legendshop.model.entity;

import java.io.Serializable;

public class ConstTable
  implements Serializable
{
  private static final long serialVersionUID = 8272145517063923481L;
  private ConstTableId id;
  private String value;
  private Integer seq;

  public Integer getSeq()
  {
    return this.seq;
  }

  public void setSeq(Integer seq)
  {
    this.seq = seq;
  }

  public ConstTable()
  {
  }

  public ConstTable(ConstTableId id)
  {
    this.id = id;
  }

  public ConstTable(ConstTableId id, String value)
  {
    this.id = id;
    this.value = value;
  }

  public ConstTableId getId()
  {
    return this.id;
  }

  public void setId(ConstTableId id)
  {
    this.id = id;
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
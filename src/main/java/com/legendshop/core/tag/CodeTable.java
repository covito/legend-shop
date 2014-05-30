package com.legendshop.core.tag;

import java.io.Serializable;

public class CodeTable
  implements Serializable
{
  private String _$2;
  private String _$1;

  public CodeTable()
  {
  }

  public CodeTable(String paramString)
  {
    this._$2 = paramString;
  }

  public String getId()
  {
    return this._$2;
  }

  public void setId(String paramString)
  {
    this._$2 = paramString;
  }

  public String getName()
  {
    return this._$1;
  }

  public void setName(String paramString)
  {
    this._$1 = paramString;
  }
}
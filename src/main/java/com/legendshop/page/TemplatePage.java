package com.legendshop.page;

import java.io.Serializable;
import java.util.Set;

public class TemplatePage
  implements Serializable, Comparable<TemplatePage>
{
  private static final long serialVersionUID = 8014143912442929809L;
  private String _$3;
  private int _$2 = 1;
  private Set<String> _$1;

  public String getTemplate()
  {
    return this._$3;
  }

  public void setTemplate(String paramString)
  {
    this._$3 = paramString;
  }

  public Set<String> getPageNames()
  {
    return this._$1;
  }

  public void setPageNames(Set<String> paramSet)
  {
    this._$1 = paramSet;
  }

  public int getPriority()
  {
    return this._$2;
  }

  public void setPriority(int paramInt)
  {
    this._$2 = paramInt;
  }

  public int compareTo(TemplatePage paramTemplatePage)
  {
    if (paramTemplatePage == null)
      return -1;
    if (paramTemplatePage.getPriority() > getPriority())
      return 1;
    if (paramTemplatePage.getPriority() == getPriority())
      return 0;
    return -1;
  }
}
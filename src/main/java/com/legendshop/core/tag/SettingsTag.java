package com.legendshop.core.tag;

import com.legendshop.core.helper.PropertiesUtil;
import javax.servlet.jsp.tagext.TagSupport;

public class SettingsTag extends TagSupport
{
  private static final long serialVersionUID = -8943927608529578818L;
  private String _$1;

  public int doStartTag()
  {
    Boolean localBoolean = PropertiesUtil.getBooleanObject(this._$1);
    if ((localBoolean != null) && (localBoolean.booleanValue()))
      return 1;
    return 0;
  }

  public void setKey(String paramString)
  {
    this._$1 = paramString;
  }
}
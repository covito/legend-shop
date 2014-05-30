package com.legendshop.core.tag;

import com.legendshop.plugins.PluginManager;
import com.legendshop.util.handler.PluginRepository;
import javax.servlet.jsp.tagext.TagSupport;

public class PluginTag extends TagSupport
{
  private static final long serialVersionUID = -8943927608529578818L;
  private String _$2;
  private PluginManager _$1 = PluginRepository.getInstance();

  public int doStartTag()
  {
    if (this._$1.isPluginRunning(this._$2))
      return 1;
    return 0;
  }

  public void setPluginId(String paramString)
  {
    this._$2 = paramString;
  }
}
package com.legendshop.core.tag;

import com.legendshop.core.helper.FoundationUtil;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class UserValidationImageTag extends TagSupport
{
  private static final long serialVersionUID = -8943927608529578818L;

  public int doStartTag()
  {
    Boolean localBoolean = Boolean.valueOf(FoundationUtil.needToValidation(this.pageContext.getSession()));
    if (localBoolean.booleanValue())
      return 1;
    return 0;
  }
}
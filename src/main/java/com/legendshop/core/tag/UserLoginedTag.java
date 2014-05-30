package com.legendshop.core.tag;

import com.legendshop.core.UserManager;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class UserLoginedTag extends TagSupport
{
  private static final long serialVersionUID = -779804105023929973L;

  public int doStartTag()
  {
    String str = UserManager.getUserName(this.pageContext.getSession());
    if (str != null)
      return 1;
    return 0;
  }
}
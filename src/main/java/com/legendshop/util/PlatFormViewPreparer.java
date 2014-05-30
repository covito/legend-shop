package com.legendshop.util;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

public class PlatFormViewPreparer
  implements ViewPreparer
{
  public void execute(TilesRequestContext paramTilesRequestContext, AttributeContext paramAttributeContext)
  {
    String str = null;
    if (str == null)
      str = "";
    paramAttributeContext.setTemplateAttribute(new Attribute("/WEB-INF/jsp/common/" + str + "header.jsp"));
  }
}
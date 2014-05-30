package com.legendshop.business.common;

import com.legendshop.model.dynamic.Item;
import com.legendshop.model.dynamic.Model;

public class SimpleDynamicPropertiesHelper extends DynamicPropertiesHelper
{
  public StringBuffer generateSelect(Model model, StringBuffer sb)
  {
    Item[] arrayOfItem;
    sb.append(model.getId()).append("&nbsp;<select id='").append(model.getId()).append("' class='attrselect'").append
      (" name='").append(model.getId()).append("'>");
    sb.append("<option value=''>请选择</option>");
    int j = (arrayOfItem = model.getItems()).length; for (int i = 0; i < j; ++i) { Item item = arrayOfItem[i];
      sb.append("<option value='").append(item.getKey()).append("'>").append(item.getKey()).append("</option>");
    }
    sb.append("</select><br>");
    return sb;
  }
}
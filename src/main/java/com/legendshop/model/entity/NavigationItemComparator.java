package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Comparator;

public class NavigationItemComparator
  implements Comparator<NavigationItem>, Serializable
{
  private static final long serialVersionUID = 4230413111485447011L;

  public int compare(NavigationItem o1, NavigationItem o2)
  {
    if ((o1 == null) || (o2 == null) || (o1.getSeq() == null) || (o2.getSeq() == null))
      return -1;
    if (o1.getSeq().longValue() < o2.getSeq().longValue())
      return -1;

    return 1;
  }
}
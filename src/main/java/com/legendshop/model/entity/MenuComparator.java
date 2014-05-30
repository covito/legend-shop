package com.legendshop.model.entity;

import java.io.Serializable;
import java.util.Comparator;

public class MenuComparator
  implements Comparator<Menu>, Serializable
{
  private static final long serialVersionUID = 7171985873166798645L;

  public int compare(Menu o1, Menu o2)
  {
    if ((o1 == null) || (o2 == null) || (o1.getSeq() == null) || (o2.getSeq() == null))
      return -1;
    if (o1.getSeq().intValue() < o2.getSeq().intValue())
      return -1;

    return 1;
  }
}
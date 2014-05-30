package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum SortEnum
  implements IntegerEnum
{
  NORMAL, SHOW_IN_INDEX_MENU;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }
}
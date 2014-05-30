package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum NewsPositionEnum
  implements IntegerEnum
{
  NEWS_NEWS, NEWS_TOP, NEWS_SORT, NEWS_BOTTOM, NEWS_GROUP_TOP, NEWS_GROUP_BOTTOM;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }
}
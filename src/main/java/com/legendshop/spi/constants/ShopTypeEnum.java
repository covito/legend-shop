package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum ShopTypeEnum
  implements IntegerEnum
{
  PERSONAL, BUSINESS;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }
}
package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum NsortEnum
  implements IntegerEnum
{
  NO, YES;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }
}
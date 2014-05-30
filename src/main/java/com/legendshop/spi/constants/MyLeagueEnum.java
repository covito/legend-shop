package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum MyLeagueEnum
  implements IntegerEnum
{
  ONGOING, AGREE, DENY, NONE, DONE, THESAME, ERROR;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }
}
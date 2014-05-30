package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum ConsultTypeEnum
  implements IntegerEnum
{
  PROD, STOCK, WARRANT;

  private Integer type;

  public Integer value()
  {
    return this.type;
  }
}
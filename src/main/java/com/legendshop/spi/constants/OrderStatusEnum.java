package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;
import java.io.PrintStream;

public enum OrderStatusEnum
  implements IntegerEnum
{
  UNPAY, PADYED, CONSIGNMENT, SUCCESS, CLOSE, REFUNDMENT, PAY_AT_GOODS_ARRIVED;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }

  public static void main(String[] args)
  {
    System.out.println(SUCCESS.value());
    System.out.println(SUCCESS);
  }
}
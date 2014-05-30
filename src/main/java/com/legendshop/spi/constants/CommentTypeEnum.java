package com.legendshop.spi.constants;

import com.legendshop.util.constant.IntegerEnum;

public enum CommentTypeEnum
  implements IntegerEnum
{
  COMMENT_UN_READ, COMMENT_READED, COMMONTALK;

  private Integer num;

  public Integer value()
  {
    return this.num;
  }

  public static void main(String[] args)
  {
  }
}
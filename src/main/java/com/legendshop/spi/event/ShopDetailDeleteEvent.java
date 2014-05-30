package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.ShopDetail;

public class ShopDetailDeleteEvent extends SystemEvent<ShopDetail>
{
  public ShopDetailDeleteEvent(ShopDetail shopDetail)
  {
    super(EventId.SHOPDETAIL_DELETE);
    setSource(shopDetail);
  }
}
package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.ShopDetail;

public class ShopDetailUpdateEvent extends SystemEvent<ShopDetail>
{
  public ShopDetailUpdateEvent(ShopDetail shopDetail)
  {
    super(EventId.SHOPDETAIL_UPDATE);
    setSource(shopDetail);
  }
}
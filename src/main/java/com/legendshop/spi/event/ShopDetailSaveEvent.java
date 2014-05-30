package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.ShopDetail;

public class ShopDetailSaveEvent extends SystemEvent<ShopDetail>
{
  public ShopDetailSaveEvent(ShopDetail shopDetail)
  {
    super(EventId.SHOPDETAIL_SAVE);
    setSource(shopDetail);
  }
}
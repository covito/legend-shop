package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Product;

public class ProductUpdateEvent extends SystemEvent<Product>
{
  public ProductUpdateEvent(Product prod)
  {
    super(EventId.PRODUCT_UPDATE);
    setSource(prod);
  }
}
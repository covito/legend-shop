package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Product;

public class ProductDeleteEvent extends SystemEvent<Product>
{
  public ProductDeleteEvent(Product prod)
  {
    super(EventId.PRODUCT_DELETE);
    setSource(prod);
  }
}
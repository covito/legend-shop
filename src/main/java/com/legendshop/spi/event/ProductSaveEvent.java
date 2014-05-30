package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.Product;

public class ProductSaveEvent extends SystemEvent<Product>
{
  public ProductSaveEvent(Product prod)
  {
    super(EventId.PRODUCT_SAVE);
    setSource(prod);
  }
}
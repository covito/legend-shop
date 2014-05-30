package com.legendshop.business.tag;

import com.legendshop.core.tag.LegendShopTag;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.service.ProductService;
import java.io.IOException;

public class ProductTag extends LegendShopTag
{
  private String var;
  private Long prodId;
  private ProductService productService;

  public ProductTag()
  {
    if (this.productService == null)
      this.productService = ((ProductService)getBean("productService"));
  }

  public void doTag()
    throws IOException
  {
    Product product = this.productService.getProductById(this.prodId);
    if (product != null)
      setAttribute(this.var, product);
  }

  public void setProdId(Long prodId)
  {
    this.prodId = prodId;
  }
}
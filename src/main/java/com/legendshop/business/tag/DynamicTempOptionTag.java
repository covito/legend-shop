package com.legendshop.business.tag;

import com.legendshop.core.tag.OptionTag;
import com.legendshop.spi.service.ProductService;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DynamicTempOptionTag extends OptionTag
{
  private static Log log = LogFactory.getLog(DynamicTempOptionTag.class);
  private Long sortId;
  private ProductService productService;

  public DynamicTempOptionTag()
  {
    if (this.productService == null)
      this.productService = ((ProductService)getBean("productService"));
  }

  public List<?> retrieveData(String type, String userName)
  {
    if (AppUtils.isBlank(type))
      return null;

    Integer dynTempType = Integer.valueOf(type);
    if (AppUtils.isBlank(this.sortId))
      return this.productService.getDynamicTemp(dynTempType, this.shopName);

    return this.productService.getDynamicTemp(dynTempType, this.sortId, userName);
  }

  public Long getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Long sortId)
  {
    this.sortId = sortId;
  }
}
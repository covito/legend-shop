package com.legendshop.business.tag;

import com.legendshop.core.tag.OptionTag;
import com.legendshop.spi.service.SortService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SortOptionTag extends OptionTag
{
  private static Log log = LogFactory.getLog(SortOptionTag.class);
  private SortService sortService;

  public SortOptionTag()
  {
    if (this.sortService == null)
      this.sortService = ((SortService)getBean("sortService"));
  }

  public List<?> retrieveData(String type, String userName)
  {
    return this.sortService.getSort(this.shopName, type, Boolean.valueOf(false));
  }
}
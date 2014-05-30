package com.legendshop.spi.event;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.LegendFilter;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.Product;
import com.legendshop.util.AppUtils;

public abstract class ProductThreadEventProcessor<T> extends ThreadEventProcessor<T>
{
  private String realPath = LegendFilter.HTML_PATH;

  public void clearProductStaticPage(Product product)
  {
    if (AppUtils.isBlank(UserManager.getUserName()))
      return;

    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.STATIC_PAGE_SUPPORT, Boolean.class)).booleanValue()) {
      FileProcessor.deleteFile(this.realPath + product.getUserName() + "/views/" + product.getId() + ".html", false);
      FileProcessor.deleteFile(this.realPath + product.getUserName() + "/group/views/" + product.getId() + ".html", false);
    }
  }
}
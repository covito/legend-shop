package com.legendshop.business.process.event;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.LegendFilter;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.GroupProduct;
import com.legendshop.model.entity.Product;
import com.legendshop.model.entity.UserEvent;
import com.legendshop.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
public class ProductChangeProcessor extends BaseProcessor<UserEvent>
{
  private final Logger log = LoggerFactory.getLogger(ProductChangeProcessor.class);

  public void process(UserEvent userEvent)
  {
    Object obj = userEvent.getEntity();
    String realPath = LegendFilter.HTML_PATH;
    if (AppUtils.isBlank(UserManager.getUserName()))
      return;

    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.STATIC_PAGE_SUPPORT, Boolean.class)).booleanValue()) {
      if (obj instanceof Product) {
        Product product = (Product)obj;
        FileProcessor.deleteFile(realPath + product.getUserName() + "/views/" + product.getId() + ".html", false);
      }
      if (obj instanceof GroupProduct) {
        GroupProduct product = (GroupProduct)obj;
        FileProcessor.deleteFile(realPath + product.getUserName() + "/views/" + product.getId() + ".html", false);
        FileProcessor.deleteFile(realPath + product.getUserName() + "group/views/" + product.getId() + ".html", false);
      }
    }
  }
}
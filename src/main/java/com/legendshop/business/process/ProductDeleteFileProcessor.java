package com.legendshop.business.process;

import com.legendshop.core.helper.FileProcessor;
import com.legendshop.core.helper.RealPathUtil;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.event.ProductThreadEventProcessor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDeleteFileProcessor extends ProductThreadEventProcessor<Product>
{
  private final Logger log = LoggerFactory.getLogger(ProductDeleteFileProcessor.class);
  private Map<String, List<Integer>> scaleList;

  public void process(Product product)
  {
    this.log.debug("ProductDeleteFileProcessor performed");
    String picUrl = RealPathUtil.getBigPicRealPath() + "/" + product.getPic();

    this.log.debug("delete Big Image file {}", picUrl);
    FileProcessor.deleteFile(picUrl);

    for (Iterator localIterator = this.scaleList.keySet().iterator(); localIterator.hasNext(); ) { String scaleValue = (String)localIterator.next();
      String smallPicUrl = RealPathUtil.getSmallPicRealPath() + "/" + scaleValue + "/" + product.getPic();
      this.log.debug("delete small Image file {}", smallPicUrl);
      FileProcessor.deleteFile(smallPicUrl);
    }
  }

  public void setScaleList(Map<String, List<Integer>> scaleList)
  {
    this.scaleList = scaleList;
  }
}
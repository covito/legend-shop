package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ProductPropertyValueDao;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductPropertyValue;
import com.legendshop.util.AppUtils;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ProductPropertyValueDaoImpl extends BaseDaoImpl
  implements ProductPropertyValueDao
{
  private static Logger log = LoggerFactory.getLogger(ProductPropertyValueDaoImpl.class);

  public List<ProductPropertyValue> getProductPropertyValue(String userName)
  {
    return findByHQL("from ProductPropertyValue where userName = ?", new Object[] { userName });
  }

  public ProductPropertyValue getProductPropertyValue(Long id) {
    return ((ProductPropertyValue)get(ProductPropertyValue.class, id));
  }

  public void deleteProductPropertyValue(ProductPropertyValue productPropertyValue) {
    delete(productPropertyValue);
  }

  public void saveProductPropertyValue(ProductPropertyValue productPropertyValue) {
    MultipartFile formFile = productPropertyValue.getFile();
    boolean uploadFile = fileUploaded(formFile);
    String orginPic = null;
    if ((AppUtils.isNotBlank(productPropertyValue.getName())) && (AppUtils.isNotBlank(productPropertyValue.getFile())))
      if (AppUtils.isNotBlank(productPropertyValue.getId())) {
        ProductPropertyValue origin = (ProductPropertyValue)get(ProductPropertyValue.class, productPropertyValue.getId());
        if (origin != null) {
          origin.setModifyDate(new Date());
          origin.setName(productPropertyValue.getName());
          origin.setPic(productPropertyValue.getPic());
          origin.setSequence(productPropertyValue.getSequence());
          update(origin);
        }
      } else {
        Date date = new Date();
        productPropertyValue.setModifyDate(date);
        productPropertyValue.setRecDate(date);
        save(productPropertyValue);
      }
  }

  public void updateProductPropertyValue(ProductPropertyValue productPropertyValue)
  {
    update(productPropertyValue);
  }

  public PageSupport getProductPropertyValue(CriteriaQuery cq) {
    return find(cq);
  }

  private boolean fileUploaded(MultipartFile formFile)
  {
    return ((formFile != null) && (formFile.getSize() > -4648542532953702400L));
  }
}
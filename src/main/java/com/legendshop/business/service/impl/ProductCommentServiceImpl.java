package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ProductCommentDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ProductComment;
import com.legendshop.model.entity.ProductCommentCategory;
import com.legendshop.spi.service.ProductCommentService;
import com.legendshop.util.AppUtils;
import org.springframework.beans.factory.annotation.Required;

public class ProductCommentServiceImpl
  implements ProductCommentService
{
  private ProductCommentDao productCommentDao;

  public ProductComment getProductCommentById(Long id)
  {
    return ((ProductComment)this.productCommentDao.get(ProductComment.class, id));
  }

  public void delete(Long id)
  {
    this.productCommentDao.deleteProductCommentById(id);
  }

  public Long save(ProductComment productComment)
  {
    if (!(AppUtils.isBlank(productComment.getId()))) {
      update(productComment);
      return productComment.getId();
    }
    return ((Long)this.productCommentDao.save(productComment));
  }

  public void update(ProductComment productComment)
  {
    this.productCommentDao.updateProductComment(productComment);
  }

  public String validateComment(Long prodId, String userName)
  {
    return this.productCommentDao.validateComment(prodId, userName);
  }

  public PageSupport getProductCommentList(CriteriaQuery cq)
  {
    return this.productCommentDao.find(cq);
  }

  public PageSupport getProductCommentList(HqlQuery hql)
  {
    return this.productCommentDao.find(hql);
  }

  @Required
  public void setProductCommentDao(ProductCommentDao productCommentDao)
  {
    this.productCommentDao = productCommentDao;
  }

  public ProductCommentCategory initProductCommentCategory(Long prodId)
  {
    return this.productCommentDao.initProductCommentCategory(prodId);
  }
}
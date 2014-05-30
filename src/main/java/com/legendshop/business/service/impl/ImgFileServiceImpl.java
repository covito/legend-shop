package com.legendshop.business.service.impl;

import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import com.legendshop.spi.service.ImgFileService;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

public class ImgFileServiceImpl
  implements ImgFileService
{
  private ImgFileDao imgFileDao;

  public List<ImgFile> getImgFile(String userName)
  {
    return this.imgFileDao.findByHQL("from ImgFile where userName = ?", new Object[] { userName });
  }

  public ImgFile getImgFileById(Long id)
  {
    return ((ImgFile)this.imgFileDao.get(ImgFile.class, id));
  }

  public Product getProd(Long id)
  {
    return ((Product)this.imgFileDao.get(Product.class, id));
  }

  public void delete(Long id)
  {
    this.imgFileDao.deleteImgFileById(id);
  }

  public Long save(ImgFile imgFile)
  {
    if (!(AppUtils.isBlank(imgFile.getFileId()))) {
      update(imgFile);
      return imgFile.getFileId();
    }
    return ((Long)this.imgFileDao.save(imgFile));
  }

  public void update(ImgFile imgFile)
  {
    this.imgFileDao.updateImgFile(imgFile);
  }

  public PageSupport getImgFileList(CriteriaQuery cq)
  {
    return this.imgFileDao.find(cq);
  }

  @Required
  public void setImgFileDao(ImgFileDao imgFileDao)
  {
    this.imgFileDao = imgFileDao;
  }

  public List<ImgFile> getProductPics(String userName, Long prodId)
  {
    return this.imgFileDao.getProductPics(userName, prodId);
  }
}
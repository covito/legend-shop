package com.legendshop.spi.service;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Product;
import java.util.List;

public abstract interface ImgFileService
{
  public abstract List<ImgFile> getImgFile(String paramString);

  public abstract ImgFile getImgFileById(Long paramLong);

  public abstract Product getProd(Long paramLong);

  public abstract void delete(Long paramLong);

  public abstract Long save(ImgFile paramImgFile);

  public abstract void update(ImgFile paramImgFile);

  public abstract PageSupport getImgFileList(CriteriaQuery paramCriteriaQuery);

  public abstract List<ImgFile> getProductPics(String paramString, Long paramLong);
}
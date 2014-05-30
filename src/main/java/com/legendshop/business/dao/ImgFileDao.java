package com.legendshop.business.dao;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Indexjpg;
import java.util.List;

public abstract interface ImgFileDao extends BaseDao
{
  public abstract List<Indexjpg> getIndexJpeg(String paramString);

  public abstract List<ImgFile> getProductPics(String paramString, Long paramLong);

  public abstract List<ImgFile> getAllProductPics(Long paramLong);

  public abstract void deleteImgFileById(Long paramLong);

  public abstract void updateImgFile(ImgFile paramImgFile);

  public abstract void deleteImgFile(ImgFile paramImgFile);
}
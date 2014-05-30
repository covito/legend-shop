package com.legendshop.business.dao.impl;

import com.legendshop.business.dao.ImgFileDao;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.dao.impl.BaseDaoImpl;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.model.entity.ImgFile;
import com.legendshop.model.entity.Indexjpg;
import com.legendshop.util.AppUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public class ImgFileDaoImpl extends BaseDaoImpl
  implements ImgFileDao
{
  private static Logger log = LoggerFactory.getLogger(ImgFileDaoImpl.class);

  @Cacheable(value={"IndexjpgList"}, key="#userName")
  public List<Indexjpg> getIndexJpeg(String userName)
  {
    log.debug("getIndexJpeg, userName = {}", userName);
    String defaultShopName = (String)PropertiesUtil.getObject(SysParameterEnum.DEFAULT_SHOP, String.class);
    if (AppUtils.isBlank(userName))
      return findByHQLLimit("from Indexjpg where status = 1 and  userName = ? order by seq asc", 0, 7, new Object[] { defaultShopName });

    return findByHQLLimit("from Indexjpg where status = 1 and userName = ? OR userName = ? order by seq asc", 0, 7, new Object[] { userName, defaultShopName });
  }

  @Cacheable(value={"ImgFileList"}, key="#userName + #prodId")
  public List<ImgFile> getProductPics(String userName, Long prodId)
  {
    return findByHQL("from ImgFile where productType = 1 and status = 1 and  userName = ? and productId = ?", new Object[] { userName, prodId });
  }

  @CacheEvict(value={"ImgFile"}, key="#fileId")
  public void deleteImgFileById(Long fileId)
  {
    deleteById(ImgFile.class, fileId);
  }

  @CacheEvict(value={"ImgFile"}, key="#imgFile.fileId")
  public void updateImgFile(ImgFile imgFile)
  {
    update(imgFile);
  }

  @CacheEvict(value={"ImgFile"}, key="#imgFile.fileId")
  public void deleteImgFile(ImgFile imgFile)
  {
    delete(imgFile);
  }

  public List<ImgFile> getAllProductPics(Long prodId)
  {
    return findByHQL("from ImgFile where productType = 1 and  productId = ?", new Object[] { prodId });
  }
}
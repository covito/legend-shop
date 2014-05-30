package com.legendshop.business.service.impl;

import com.legendshop.business.common.CommonServiceUtil;
import com.legendshop.business.dao.CommonUtilDao;
import com.legendshop.spi.service.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

public class CommonUtilImpl
  implements CommonUtil
{
  private static Logger log = LoggerFactory.getLogger(CommonServiceUtil.class);
  private CommonUtilDao commonUtilDao;

  public void saveAdminRight(String userId)
  {
    this.commonUtilDao.saveAdminRight(userId);
  }

  public void saveUserRight(String userId)
  {
    this.commonUtilDao.saveUserRight(userId);
  }

  public void removeAdminRight(String userId)
  {
    this.commonUtilDao.removeAdminRight(userId);
  }

  public void removeUserRight(String userId)
  {
    this.commonUtilDao.removeUserRight(userId);
  }

  @Required
  public void setCommonUtilDao(CommonUtilDao commonUtilDao)
  {
    this.commonUtilDao = commonUtilDao;
  }
}
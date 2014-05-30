package com.legendshop.business.service.locator;

import com.legendshop.core.exception.NotFoundException;
import com.legendshop.model.entity.Sub;
import com.legendshop.spi.locator.GenericServiceLocator;
import com.legendshop.spi.service.timer.SubService;

public class SubServiceLocator extends GenericServiceLocator<SubService>
{
  private SubService subService;

  public Sub getSub(Long subId)
  {
    Sub sub = this.subService.getSubById(subId);
    if (sub == null)
      throw new NotFoundException("Sub can not be empty", "404");

    return sub;
  }

  public SubService getSubService()
  {
    return this.subService;
  }

  public void setSubService(SubService subService)
  {
    this.subService = subService;
  }
}
package com.legendshop.business.process.event;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.exception.ConflictException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.core.service.ShopService;
import com.legendshop.event.processor.BaseProcessor;
import com.legendshop.model.entity.ShopDetailView;
import com.legendshop.model.entity.SystemParameter;
import com.legendshop.spi.constants.Constants;

public class DefaultShopPropertiesUpdatedProcessor extends BaseProcessor<SystemParameter>
{
  private ShopService shopService;

  public boolean isSupport(SystemParameter systemParameter)
  {
    boolean result = (systemParameter != null) && (SysParameterEnum.DEFAULT_SHOP.name().equals(systemParameter.getName()));
    return result;
  }

  public void process(SystemParameter systemParameter)
  {
    ShopDetailView shopDetail = this.shopService.getShopDetailView(systemParameter.getValue());
    if (shopDetail == null)
      throw new NotFoundException("找不到默认商城  " + systemParameter.getValue());

    if (!(Constants.ONLINE.equals(shopDetail.getStatus())))
      throw new ConflictException("商城 '" + systemParameter.getValue() + "'处于下线状态，不能作为默认商城");
  }

  public void setShopService(ShopService shopService)
  {
    this.shopService = shopService;
  }
}
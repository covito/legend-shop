package com.legendshop.business.service.impl;

import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.event.EventHome;
import com.legendshop.spi.event.VisitLogEvent;
import com.legendshop.spi.service.impl.AbstractService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseServiceImpl extends AbstractService
{
  private final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

  protected void logUserAccess(HttpServletRequest request, String shopName, String userName)
  {
    if (((Boolean)PropertiesUtil.getObject(SysParameterEnum.VISIT_LOG_INDEX_ENABLE, Boolean.class)).booleanValue()) {
      EventHome.publishEvent(new VisitLogEvent(request.getRemoteAddr(), shopName, userName));
    }
    else if (this.log.isInfoEnabled())
      this.log.info("[{}],{} visit index {}", new Object[] { request.getRemoteAddr(), userName, shopName });
  }
}
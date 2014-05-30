package com.legendshop.spi.event;

import com.legendshop.event.SystemEvent;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.constants.VisitTypeEnum;
import java.util.Date;

public class VisitLogEvent extends SystemEvent<VisitLog>
{
  public VisitLogEvent(String ip, String shopName, String userName, Long prodId, String prodName, String page)
  {
    super(EventId.VISIT_LOG_EVENT);
    VisitLog visitLog = new VisitLog();
    visitLog.setDate(new Date());
    visitLog.setIp(ip);
    visitLog.setShopName(shopName);
    visitLog.setProductName(prodName);
    visitLog.setUserName(userName);
    if (prodId != null)
      visitLog.setProductId(String.valueOf(prodId));

    visitLog.setPage(page);
    setSource(visitLog);
  }

  public VisitLogEvent(String ip, String shopName, String userName)
  {
    this(ip, shopName, userName, null, null, VisitTypeEnum.INDEX.value());
  }
}
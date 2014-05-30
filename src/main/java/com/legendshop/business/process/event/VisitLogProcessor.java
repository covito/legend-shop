package com.legendshop.business.process.event;

import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.model.entity.VisitLog;
import com.legendshop.spi.service.VisitLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisitLogProcessor extends ThreadProcessor<VisitLog>
{
  private final Logger log = LoggerFactory.getLogger(VisitLogProcessor.class);
  private VisitLogService visitLogService;

  public boolean isSupport(VisitLog visitLog)
  {
    return true;
  }

  public void process(VisitLog visitLog)
  {
    if (this.log.isDebugEnabled())
      this.log.debug("[{}],{} visit index {}, this {}", 
        new Object[] { visitLog.getIp(), visitLog.getUserName(), visitLog.getShopName(), this });

    this.visitLogService.process(visitLog);
  }

  public void setVisitLogService(VisitLogService visitLogService)
  {
    this.visitLogService = visitLogService;
  }
}
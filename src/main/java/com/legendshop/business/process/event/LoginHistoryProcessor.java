package com.legendshop.business.process.event;

import com.legendshop.core.security.model.UserDetail;
import com.legendshop.event.processor.ThreadProcessor;
import com.legendshop.spi.service.LoginHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginHistoryProcessor extends ThreadProcessor<UserDetail>
{
  private final Logger log = LoggerFactory.getLogger(LoginHistoryProcessor.class);
  private LoginHistoryService loginHistoryService;

  public boolean isSupport(UserDetail userDetail)
  {
    return true;
  }

  public void process(UserDetail userDetail)
  {
    this.log.debug("LoginHistoryProcessor calling : " + this);
    this.loginHistoryService.saveLoginHistory(userDetail.getUsername(), userDetail.getIpAddress());
  }

  public void setLoginHistoryService(LoginHistoryService loginHistoryService) {
    this.loginHistoryService = loginHistoryService;
  }
}
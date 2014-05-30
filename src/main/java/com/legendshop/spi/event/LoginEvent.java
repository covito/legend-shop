package com.legendshop.spi.event;

import com.legendshop.core.security.model.UserDetail;
import com.legendshop.event.SystemEvent;

public class LoginEvent extends SystemEvent<UserDetail>
{
  public LoginEvent(UserDetail userDetail, String ipAddress)
  {
    super(EventId.LOGIN_EVENT);
    userDetail.setIpAddress(ipAddress);
    setSource(userDetail);
  }
}
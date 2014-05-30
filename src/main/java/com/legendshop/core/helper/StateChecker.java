package com.legendshop.core.helper;

import com.legendshop.command.framework.State;
import com.legendshop.core.exception.InternalException;
import com.legendshop.core.model.UserMessages;
import javax.servlet.http.HttpServletRequest;

public class StateChecker
  implements Checker<State>
{
  public boolean check(State paramState, HttpServletRequest paramHttpServletRequest)
  {
    if (!(paramState.isOK()))
    {
      UserMessages localUserMessages = new UserMessages();
      localUserMessages.setTitle("系统状态异常");
      if (paramState.getThrowable() != null)
        localUserMessages.setDesc(paramState.getThrowable().getMessage());
      paramHttpServletRequest.setAttribute(UserMessages.MESSAGE_KEY, localUserMessages);
      throw new InternalException("State Check Fail", paramState.getErrCode());
    }
    return true;
  }
}
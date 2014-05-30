package com.legendshop.business.helper;

import com.legendshop.command.framework.State;
import com.legendshop.core.exception.InternalException;
import com.legendshop.core.helper.Checker;
import com.legendshop.core.model.UserMessages;
import javax.servlet.http.HttpServletRequest;

public class UserNotExistChecker
  implements Checker<State>
{
  public boolean check(State state, HttpServletRequest request)
  {
    if (!(state.isOK())) {
      UserMessages uem = new UserMessages();
      uem.setTitle("系统状态异常");
      if (state.getThrowable() != null)
        uem.setDesc(state.getThrowable().getMessage());

      request.setAttribute(UserMessages.MESSAGE_KEY, uem);
      throw new InternalException("State Check Fail", state.getErrCode());
    }

    return true;
  }
}
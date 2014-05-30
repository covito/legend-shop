package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import com.legendshop.command.framework.State;
import java.io.Serializable;

public abstract interface DelegateType extends Serializable
{
  public abstract boolean init(String paramString);

  public abstract Response execute(Request paramRequest)
    throws Exception;

  public abstract Object execute(String paramString1, Object paramObject, String paramString2, String paramString3, State paramState)
    throws Exception;

  public abstract Object execute(String paramString1, String paramString2, State paramState)
    throws Exception;

  public abstract void execute(String paramString1, Object paramObject, String paramString2, State paramState)
    throws Exception;
}
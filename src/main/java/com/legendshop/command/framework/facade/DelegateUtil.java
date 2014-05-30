package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.JCFException;
import com.legendshop.command.framework.Response;
import com.legendshop.command.framework.State;
import com.legendshop.util.AppUtils;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import org.apache.log4j.Logger;

public class DelegateUtil
{
  private static Logger _$1 = Logger.getLogger(DelegateUtil.class);

  public static boolean isNullParam(Object paramObject, String paramString, State paramState)
  {
    if (paramObject == null)
    {
      _$1.error("Parameter " + paramString + " is null;");
      paramState.setErrCode("PARAMETER_ERROR");
      return true;
    }
    return false;
  }

  public static boolean isNullOrEmptyString(String paramString1, String paramString2, State paramState)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
    {
      _$1.error("String Parameter " + paramString2 + " is null or empty;");
      paramState.setErrCode("PARAMETER_ERROR");
      return true;
    }
    return false;
  }

  public int getNextCommand(String paramString, String[] paramArrayOfString)
  {
    int i = -1;
    for (int j = 0; j < paramArrayOfString.length; ++j)
      if (paramString.equals(paramArrayOfString[j]))
      {
        i = j;
        break;
      }
    if (!(checkRange(i, paramArrayOfString)))
      i = -1;
    return i;
  }

  public boolean checkRange(int paramInt, String[] paramArrayOfString)
  {
    return ((paramInt <= paramArrayOfString.length) && (paramInt >= 0));
  }

  public static void checkNull(Object paramObject, String paramString)
    throws JCFException
  {
    if (AppUtils.isBlank(paramObject))
    {
      _$1.error(paramString);
      throw new JCFException("PARAMETER_ERROR", paramString);
    }
  }

  public static void checkNull(Object[] paramArrayOfObject, String paramString)
    throws JCFException
  {
    if (!(AppUtils.isBlank(paramArrayOfObject)))
    {
      for (int i = 0; i < paramArrayOfObject.length; ++i)
        if (AppUtils.isBlank(paramArrayOfObject[i]))
        {
          _$1.error(paramArrayOfObject[i] + "    " + paramString);
          throw new JCFException("PARAMETER_ERROR", paramString);
        }
    }
    else
    {
      _$1.error(paramArrayOfObject + "    " + paramString);
      throw new JCFException("PARAMETER_ERROR", paramString);
    }
  }

  public static void handleException(Exception paramException, String paramString, State paramState)
  {
    String str;
    if (paramException instanceof RemoteException)
    {
      str = "RemoteException happened when " + paramString + " delegate called:";
      _$1.error(str, paramException);
      if (paramState != null)
      {
        paramState.setThrowable(paramException);
        paramState.setErrCode("REMOTE_ERROR");
      }
    }
    else if (paramException instanceof CreateException)
    {
      str = "CreateException(create ejb fail) happened when " + paramString + " delegate called:";
      _$1.error(str, paramException);
      if (paramState != null)
      {
        paramState.setThrowable(paramException);
        paramState.setErrCode("CALL_EJB_ERROR");
      }
    }
    else
    {
      str = "Exception happened when " + paramString + " delegate called:";
      _$1.error(str, paramException);
      if (paramState != null)
      {
        paramState.setThrowable(paramException);
        paramState.setErrCode("SYSTEM_ERROR");
      }
    }
  }

  public static void setState(State paramState, Response paramResponse)
  {
    paramState.setErrCode(paramResponse.getState().getErrCode());
    paramState.setThrowable(paramResponse.getState().getThrowable());
  }
}
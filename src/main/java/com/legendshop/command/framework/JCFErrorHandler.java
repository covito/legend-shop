package com.legendshop.command.framework;

import java.rmi.RemoteException;
import org.apache.log4j.Logger;

public class JCFErrorHandler
  implements ErrorHandler
{
  private String _$2;
  private static Logger _$1 = Logger.getLogger(JCFErrorHandler.class);

  public void handleError(Response paramResponse, Throwable paramThrowable)
    throws Exception
  {
    if (paramThrowable instanceof ClientException)
    {
      _$1.error("JCFErrorHandler is dealing with ClientException errorCode is " + ((ClientException)paramThrowable).getErrorCode(), paramThrowable);
      paramResponse.setReturnCode(-2);
      paramResponse.getState().setErrCode((((ClientException)paramThrowable).getErrorCode() == null) ? "BUSINESS_ERROR" : ((ClientException)paramThrowable).getErrorCode());
      throw ((ClientException)paramThrowable);
    }
    if (paramThrowable instanceof GoOnException)
    {
      _$1.error("JCFErrorHandler is dealing with GoOnException", paramThrowable);
      paramResponse.setReturnCode(-1);
      paramResponse.getState().setErrCode((((GoOnException)paramThrowable).getErrorCode() == null) ? "BUSINESS_ERROR" : ((GoOnException)paramThrowable).getErrorCode());
    }
    else
    {
      if (paramThrowable instanceof JCFException)
      {
        _$1.error("JCFErrorHandler is dealing with JCFException errorCode is " + ((JCFException)paramThrowable).getErrorCode(), paramThrowable);
        paramResponse.setReturnCode(-2);
        paramResponse.getState().setErrCode((((JCFException)paramThrowable).getErrorCode() == null) ? "JCF_ERROR" : ((JCFException)paramThrowable).getErrorCode());
        throw ((JCFException)paramThrowable);
      }
      if (paramThrowable instanceof RemoteException)
      {
        _$1.error("JCFErrorHandler is dealing with RemoteException", paramThrowable);
        paramResponse.setReturnCode(-3);
        paramResponse.getState().setErrCode("REMOTE_ERROR");
        throw ((RemoteException)paramThrowable);
      }
      if (paramThrowable instanceof NullPointerException)
      {
        _$1.error("JCFErrorHandler is dealing with NullPointerException", paramThrowable);
        paramResponse.setReturnCode(-2);
        paramResponse.getState().setErrCode("NULLPOINT_ERROR");
        throw ((NullPointerException)paramThrowable);
      }
      _$1.error("JCFErrorHandler is dealing with Exception", paramThrowable);
      paramResponse.setReturnCode(-2);
      paramResponse.getState().setErrCode("SYSTEM_ERROR");
      if (paramThrowable instanceof Exception)
        throw ((Exception)paramThrowable);
    }
  }

  public void setBeanName(String paramString)
  {
    this._$2 = paramString;
  }
}
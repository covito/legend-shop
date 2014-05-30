package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import com.legendshop.command.framework.State;
import org.apache.log4j.Logger;

public class POJODelegate
  implements DelegateType
{
  private static Logger _$3 = Logger.getLogger(POJODelegate.class);
  private static final long serialVersionUID = -3352641969938792968L;
  private PojoSessionFacade _$2;
  private static boolean _$1 = false;

  public boolean init(String paramString)
  {
    if (_$1)
      return _$1;
    _$1 = true;
    return _$1;
  }

  public boolean getState()
  {
    return _$1;
  }

  public Object execute(String paramString1, Object paramObject, String paramString2, String paramString3, State paramState)
    throws Exception
  {
    Object localObject = null;
    if (!(DelegateUtil.isNullParam(paramObject, paramString1, paramState)))
    {
      Request localRequest = new Request();
      localRequest.setValue(paramString1, paramObject);
      localRequest.setServiceName(paramString2);
      Response localResponse = execute(localRequest);
      DelegateUtil.setState(paramState, localResponse);
      localObject = localResponse.getValue(paramString3);
    }
    return localObject;
  }

  public Object execute(String paramString1, String paramString2, State paramState)
    throws Exception
  {
    Object localObject = null;
    Request localRequest = new Request();
    localRequest.setServiceName(paramString1);
    Response localResponse = execute(localRequest);
    DelegateUtil.setState(paramState, localResponse);
    localObject = localResponse.getValue(paramString2);
    return localObject;
  }

  public void execute(String paramString1, Object paramObject, String paramString2, State paramState)
    throws Exception
  {
    if (!(DelegateUtil.isNullParam(paramObject, paramString1, paramState)))
    {
      Request localRequest = new Request();
      localRequest.setValue(paramString1, paramObject);
      localRequest.setServiceName(paramString2);
      Response localResponse = execute(localRequest);
      DelegateUtil.setState(paramState, localResponse);
    }
  }

  public Response execute(Request paramRequest)
  {
    _$3.debug("POJODelegate executing");
    Response localResponse = new Response();
    if (paramRequest == null)
    {
      localResponse.setReturnCode(-2);
      localResponse.getState().setErrCode("REQUEST_IS_NULL");
      return localResponse;
    }
    String str = paramRequest.getServiceName();
    if (str == null)
    {
      localResponse.setReturnCode(-2);
      localResponse.getState().setErrCode("SERVICENAME_IS_NULL");
      return localResponse;
    }
    try
    {
      getFacade().doActivities(paramRequest, localResponse, str);
    }
    catch (Exception localException)
    {
      _$3.error("POJODelegate 异常 " + localException.getMessage());
      if (localResponse.getState().isOK())
      {
        localResponse.setReturnCode(-2);
        localResponse.getState().setErrCode("DB_TRANSACTION_ERROR");
      }
    }
    return localResponse;
  }

  public void setFacade(PojoSessionFacade paramPojoSessionFacade)
  {
    this._$2 = paramPojoSessionFacade;
  }

  public PojoSessionFacade getFacade()
  {
    return this._$2;
  }
}
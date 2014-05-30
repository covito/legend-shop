package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import com.legendshop.command.framework.State;
import com.legendshop.command.framework.servicerepository.ServiceLocator;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import org.apache.log4j.Logger;

public class EjbDelegate
  implements DelegateType
{
  private static final long serialVersionUID = -3352641969938792969L;
  private static Logger _$3 = Logger.getLogger(EjbDelegate.class);
  private static BizFacadeHome _$2;
  private static boolean _$1 = false;

  public BizFacadeRemote getSessionFacade()
    throws RemoteException, CreateException
  {
    BizFacadeRemote localBizFacadeRemote = _$2.create();
    return localBizFacadeRemote;
  }

  public boolean init(String paramString)
  {
    if (_$1)
      return _$1;
    _$2 = (BizFacadeHome)ServiceLocator.getInstance().getOne(paramString);
    _$1 = true;
    if (_$2 == null)
    {
      _$3.error("Delegate get Home Interface fail,jndi = " + paramString);
      _$1 = false;
    }
    return _$1;
  }

  public boolean getState()
  {
    return _$1;
  }

  public java.lang.Object execute(String paramString1, java.lang.Object paramObject, String paramString2, String paramString3, State paramState)
    throws Exception
  {
    java.lang.Object localObject = null;
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

  public void execute(String paramString1, java.lang.Object paramObject, String paramString2, State paramState)
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

  public java.lang.Object execute(String paramString1, String paramString2, State paramState)
    throws Exception
  {
    java.lang.Object localObject = null;
    Request localRequest = new Request();
    localRequest.setServiceName(paramString1);
    Response localResponse = execute(localRequest);
    DelegateUtil.setState(paramState, localResponse);
    localObject = localResponse.getValue(paramString2);
    return localObject;
  }

  public Response execute(Request paramRequest)
    throws Exception
  {
    return getSessionFacade().execute(paramRequest);
  }
}
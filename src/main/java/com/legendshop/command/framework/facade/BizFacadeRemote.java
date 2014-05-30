package com.legendshop.command.framework.facade;

import com.legendshop.command.framework.Request;
import com.legendshop.command.framework.Response;
import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public abstract interface BizFacadeRemote extends EJBObject
{
  public abstract Response execute(Request paramRequest)
    throws RemoteException;
}
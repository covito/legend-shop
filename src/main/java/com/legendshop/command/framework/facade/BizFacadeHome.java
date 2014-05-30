package com.legendshop.command.framework.facade;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public abstract interface BizFacadeHome extends EJBHome
{
  public static final String COMP_NAME = "java:comp/env/ejb/BizFacade";
  public static final String JNDI_NAME = "BizFacade";

  public abstract BizFacadeRemote create()
    throws CreateException, RemoteException;
}
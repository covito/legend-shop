package com.legendshop.command.framework.servicerepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import org.apache.log4j.Logger;

public class EJBMetaData
  implements IMetaData
{
  private static Logger _$4 = Logger.getLogger(EJBMetaData.class);
  private String _$3;
  private String _$2;
  private Map _$1;

  public EJBMetaData(String paramString1, String paramString2, List paramList)
  {
    this._$3 = paramString1;
    this._$2 = paramString2;
    this._$1 = new HashMap();
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        this._$1.put(localIterator.next(), null);
    }
  }

  public Object get(String paramString)
  {
    Object localObject = this._$1.get(paramString);
    if (localObject != null)
      return localObject;
    if (!(this._$1.containsKey(paramString)))
    {
      _$4.info("no specified url " + paramString);
      return null;
    }
    localObject = _$1(paramString);
    if (localObject != null)
      this._$1.put(paramString, localObject);
    else
      _$4.info("create ejb home with jndi name[" + this._$3 + "] url name[" + paramString + "] fail");
    return localObject;
  }

  public Object getOne()
  {
    Object localObject = null;
    Iterator localIterator = this._$1.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localEntry.getValue() == null)
      {
        String str = (String)localEntry.getKey();
        localObject = _$1(str);
        if (localObject != null)
          localEntry.setValue(localObject);
        else
          _$4.info("create ejb home with jndi name[" + this._$3 + "] url name[" + str + "] fail");
      }
      else
      {
        localObject = localEntry.getValue();
      }
    }
    return localObject;
  }

  public Map get()
  {
    Iterator localIterator = this._$1.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localEntry.getValue() == null)
      {
        String str = (String)localEntry.getKey();
        Object localObject = _$1(str);
        if (localObject != null)
          localEntry.setValue(localObject);
        else
          _$4.info("create ejb home with jndi name[" + this._$3 + "] url name[" + str + "] fail");
      }
    }
    return this._$1;
  }

  private Object _$1(String paramString)
  {
    Context localContext;
    try
    {
      localContext = ServiceLocator.getInstance().getContext(paramString);
      if (localContext == null)
      {
        _$4.info("can't find the context by specified url " + paramString);
        return null;
      }
      Object localObject1 = localContext.lookup(this._$3);
      Class localClass = Class.forName(this._$2);
      Object localObject2 = PortableRemoteObject.narrow(localObject1, localClass);
      return localObject2;
    }
    catch (Exception localException)
    {
      _$4.debug("create home obj fail", localException);
    }
    return null;
  }
}
package com.legendshop.command.framework.servicerepository;

import java.util.Map;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import org.apache.log4j.Logger;

public class JMSQueueMetaData
  implements IMetaData
{
  private static Logger _$8 = Logger.getLogger(JMSQueueMetaData.class);
  private String _$7;
  private String _$6;
  private String _$5;
  private String _$4;
  private QueueConnectionFactory _$3;
  private Queue _$2;
  private boolean _$1;

  public JMSQueueMetaData(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this._$7 = paramString1;
    this._$6 = paramString2;
    this._$5 = paramString3;
    this._$4 = paramString4;
  }

  public Object get(String paramString)
  {
    throw new UnsupportedOperationException("[Object JMSTopicMetaData.get(String)] is not supported.This error may be caused by incorrected ServiceConfig.xml settings");
  }

  public Map get()
  {
    throw new UnsupportedOperationException("[Map JMSTopicMetaData.get()] is not supported.This error may be caused by incorrected ServiceConfig.xml settings");
  }

  public Object getOne()
  {
    throw new UnsupportedOperationException("[Map JMSTopicMetaData.getOne()] is not supported.This error may be caused by incorrected ServiceConfig.xml settings");
  }

  public QueueConnectionFactory getQueueConnectionFactory()
    throws Exception
  {
    try
    {
      _$1();
    }
    catch (Exception localException)
    {
      _$8.debug("create Queue connection factory fail", localException);
      throw localException;
    }
    return this._$3;
  }

  public Queue getQueue()
    throws Exception
  {
    try
    {
      _$1();
    }
    catch (Exception localException)
    {
      _$8.debug("create Queue fail", localException);
      throw localException;
    }
    return this._$2;
  }

  private synchronized void _$1()
    throws Exception
  {
    if (this._$1)
      return;
    Context localContext = ServiceLocator.getInstance().getContext(this._$4);
    if (localContext == null)
      throw new Exception("can't find the context by specified url " + this._$4);
    this._$3 = ((QueueConnectionFactory)PortableRemoteObject.narrow(localContext.lookup(this._$6), QueueConnectionFactory.class));
    this._$2 = ((Queue)PortableRemoteObject.narrow(localContext.lookup(this._$5), Queue.class));
    this._$1 = true;
  }
}
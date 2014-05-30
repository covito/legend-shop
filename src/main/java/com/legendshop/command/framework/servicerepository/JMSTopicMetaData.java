package com.legendshop.command.framework.servicerepository;

import java.util.Map;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.rmi.PortableRemoteObject;
import org.apache.log4j.Logger;

public class JMSTopicMetaData
  implements IMetaData
{
  private static Logger _$8 = Logger.getLogger(JMSTopicMetaData.class);
  private String _$7;
  private String _$6;
  private String _$5;
  private String _$4;
  private TopicConnectionFactory _$3;
  private Topic _$2;
  private boolean _$1;

  public JMSTopicMetaData(String paramString1, String paramString2, String paramString3, String paramString4)
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

  public TopicConnectionFactory getTopicConnectionFactory()
    throws Exception
  {
    try
    {
      _$1();
    }
    catch (Exception localException)
    {
      _$8.debug("create topic connection factory fail", localException);
      throw localException;
    }
    return this._$3;
  }

  public Topic getTopic()
    throws Exception
  {
    try
    {
      _$1();
    }
    catch (Exception localException)
    {
      _$8.debug("create topic fail", localException);
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
    this._$3 = ((TopicConnectionFactory)PortableRemoteObject.narrow(localContext.lookup(this._$6), TopicConnectionFactory.class));
    this._$2 = ((Topic)PortableRemoteObject.narrow(localContext.lookup(this._$5), Topic.class));
    this._$1 = true;
  }
}
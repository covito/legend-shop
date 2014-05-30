package com.legendshop.util.sql;

import com.legendshop.util.xml.ConfigException;
import com.legendshop.util.xml.Configure;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class CodeItem
{
  private static Logger _$3 = Logger.getLogger(CodeItem.class);
  private File _$2 = null;
  private long _$1;

  public synchronized Map<String, String> init(String paramString)
  {
    Configure localConfigure = new Configure();
    this._$2 = new File(paramString);
    this._$1 = this._$2.lastModified();
    try
    {
      localConfigure.parse(paramString);
      return _$1(localConfigure);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public boolean isModified()
  {
    if (this._$2 == null)
      return false;
    return (this._$2.lastModified() > this._$1);
  }

  public Map<String, String> init(InputStream paramInputStream)
  {
    Configure localConfigure = new Configure();
    try
    {
      localConfigure.parse(paramInputStream);
      return _$1(localConfigure);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private Map<String, String> _$1(Configure paramConfigure)
    throws ConfigException
  {
    HashMap localHashMap = new HashMap();
    int i = paramConfigure.getItemCount("/DataAccessLayer/BusinessObjects");
    for (int j = 1; j <= i; ++j)
    {
      String str1 = paramConfigure.getItemProp("/DataAccessLayer/BusinessObjects/Object[" + j + "]", "objectName");
      int k = paramConfigure.getItemCount("/DataAccessLayer/BusinessObjects/Object[" + j + "]");
      for (int l = 0; l < k; ++l)
      {
        String str2 = "/DataAccessLayer/BusinessObjects/Object[" + j + "]";
        String str3 = paramConfigure.getItemProp(str2 + "/Method[" + (l + 1) + "]", "name");
        String str4 = paramConfigure.getItemValue(str2, "/Method[" + (l + 1) + "]");
        String str5 = ObjectSignature.toSignature(str1, str3);
        if (localHashMap.containsKey(str5))
          _$3.warn(" unique constraint violated ,key = " + str5);
        else
          localHashMap.put(str5, (str4 == null) ? "" : str4.trim());
      }
    }
    return localHashMap;
  }
}
package com.legendshop.core.dao.support;

import com.legendshop.core.UserManager;
import com.legendshop.core.exception.AuthorizationException;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.ThreadLocalContext;
import com.legendshop.util.AppUtils;
import java.util.Collection;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class QueryMap extends LinkedHashMap<String, Object>
{
  private static final long serialVersionUID = -5165708075543969338L;

  public Object put(String paramString, Object paramObject)
  {
    if ((paramObject != null) && (!("".equals(paramObject))))
      super.put(paramString, paramObject);
    return paramObject;
  }

  public Object like(String paramString, Object paramObject)
  {
    if ((paramObject != null) && (!("".equals(paramObject))))
      super.put(paramString, paramObject + "%");
    return paramObject;
  }

  public Object[] toArray()
  {
    return values().toArray();
  }

  public void hasAllDataFunction(String paramString1, String paramString2)
  {
    HttpServletRequest localHttpServletRequest = ThreadLocalContext.getRequest();
    if (FoundationUtil.haveViewAllDataFunction(localHttpServletRequest))
    {
      if (AppUtils.isNotBlank(paramString2))
        like(paramString1, StringUtils.trim(paramString2) + "%");
    }
    else
    {
      String str = UserManager.getUserName(localHttpServletRequest.getSession());
      if (str == null)
        throw new AuthorizationException(paramString2 + " did not logon yet!");
      put(paramString1, str);
    }
  }
}
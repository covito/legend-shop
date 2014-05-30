package com.legendshop.core.dao.support;

import com.legendshop.core.UserManager;
import com.legendshop.core.constant.SysParameterEnum;
import com.legendshop.core.helper.FoundationUtil;
import com.legendshop.core.helper.PropertiesUtil;
import com.legendshop.util.AppUtils;
import com.legendshop.util.sql.ConfigCode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class SimpleHqlQuery extends HqlQuery
{
  private static final long serialVersionUID = -4278947596603935645L;
  private Map<String, Object> _$1 = new HashMap();

  public SimpleHqlQuery()
  {
  }

  public SimpleHqlQuery(String paramString)
  {
    this.curPage = paramString;
  }

  public void fillParameter(String paramString, Object paramObject)
  {
    if (AppUtils.isNotBlank(paramObject))
    {
      this._$1.put(paramString, paramObject);
      this.params.add(paramObject);
    }
  }

  public void fillOrder(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2)
  {
    if (!(FoundationUtil.isDataSortByExternal(this, paramHttpServletRequest, this._$1)))
      this._$1.put(paramString1, paramString2);
  }

  public void fillOrder(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    if (!(FoundationUtil.isDataSortByExternal(this, paramHttpServletRequest, this._$1)))
      this._$1.put("orderIndicator", paramString);
  }

  public void fillPageSize(HttpServletRequest paramHttpServletRequest)
  {
    if (!(FoundationUtil.isDataForExport(this, paramHttpServletRequest)))
      setPageSize(((Integer)PropertiesUtil.getObject(SysParameterEnum.PAGE_SIZE, Integer.class)).intValue());
  }

  public void fillLikeParameter(String paramString, Object paramObject)
  {
    if (AppUtils.isNotBlank(paramObject))
    {
      String str = paramObject.toString().trim() + "%";
      this._$1.put(paramString, str);
      this.params.add(str);
    }
  }

  public void initSQL(String paramString1, String paramString2)
  {
    setQueryString(ConfigCode.getInstance().getCode(paramString1, this._$1));
    setAllCountString(ConfigCode.getInstance().getCode(paramString2, this._$1));
  }

  public String hasAllDataFunction(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    String str = UserManager.getUserName(paramHttpServletRequest);
    if (!(FoundationUtil.haveViewAllDataFunction(paramHttpServletRequest)))
    {
      this._$1.put("userName", str);
      addParams(str);
    }
    else if (AppUtils.isNotBlank(paramString))
    {
      this._$1.put("userName", paramString);
      addParams(paramString);
    }
    return str;
  }

  public String getUserName()
  {
    return ((String)this._$1.get("userName"));
  }
}
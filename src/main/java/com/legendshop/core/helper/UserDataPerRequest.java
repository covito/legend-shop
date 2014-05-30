package com.legendshop.core.helper;

import com.legendshop.model.entity.ShopDetailView;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDataPerRequest
  implements Serializable
{
  private static final long serialVersionUID = -858303753566417577L;
  private ShopDetailView _$5;
  private String _$4;
  private String _$3;
  private final HttpServletRequest _$2;
  private final HttpServletResponse _$1;

  public UserDataPerRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
  {
    this._$2 = paramHttpServletRequest;
    this._$1 = paramHttpServletResponse;
  }

  public ShopDetailView getShopDetail()
  {
    return this._$5;
  }

  public void setShopDetail(ShopDetailView paramShopDetailView)
  {
    this._$5 = paramShopDetailView;
  }

  public String getFrontType()
  {
    return this._$4;
  }

  public void setFrontType(String paramString)
  {
    this._$4 = paramString;
  }

  public String getEndType()
  {
    return this._$3;
  }

  public void setEndType(String paramString)
  {
    this._$3 = paramString;
  }

  public HttpServletRequest getRequest()
  {
    return this._$2;
  }

  public HttpServletResponse getResponse()
  {
    return this._$1;
  }
}
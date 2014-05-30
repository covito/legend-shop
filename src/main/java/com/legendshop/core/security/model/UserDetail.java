package com.legendshop.core.security.model;

import com.legendshop.core.security.GrantedFunction;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetail extends User
{
  private static final long serialVersionUID = -5898134878028341628L;
  private final Collection<GrantedFunction> _$3;
  private final String _$2;
  private String _$1;

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof UserDetail) && (this._$2.equals(((UserDetail)paramObject).getUserId())));
  }

  public int hashCode()
  {
    return this._$2.hashCode();
  }

  public UserDetail(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, Collection<GrantedAuthority> paramCollection, Collection<GrantedFunction> paramCollection1, String paramString3)
    throws IllegalArgumentException
  {
    super(paramString1, paramString2, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4, paramCollection);
    this._$3 = paramCollection1;
    this._$2 = paramString3;
  }

  public Collection<GrantedFunction> getFunctions()
  {
    return this._$3;
  }

  public String getUserId()
  {
    return this._$2;
  }

  public String getIpAddress()
  {
    return this._$1;
  }

  public void setIpAddress(String paramString)
  {
    this._$1 = paramString;
  }
}
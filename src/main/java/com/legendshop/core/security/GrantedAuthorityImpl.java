package com.legendshop.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

public final class GrantedAuthorityImpl
  implements GrantedAuthority, Comparable<GrantedAuthority>
{
  private static final long serialVersionUID = 310L;
  private final String _$1;

  public GrantedAuthorityImpl(String paramString)
  {
    Assert.hasText(paramString, "A granted authority textual representation is required");
    this._$1 = paramString;
  }

  public String getAuthority()
  {
    return this._$1;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (paramObject instanceof GrantedAuthorityImpl)
      return this._$1.equals(((GrantedAuthorityImpl)paramObject)._$1);
    return false;
  }

  public int hashCode()
  {
    return this._$1.hashCode();
  }

  public String toString()
  {
    return this._$1;
  }

  public int compareTo(GrantedAuthority paramGrantedAuthority)
  {
    return paramGrantedAuthority.getAuthority().compareTo(this._$1);
  }
}
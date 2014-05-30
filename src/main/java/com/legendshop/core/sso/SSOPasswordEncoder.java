package com.legendshop.core.sso;

import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;

public class SSOPasswordEncoder extends PlaintextPasswordEncoder
{
  public boolean isPasswordValid(String paramString1, String paramString2, Object paramObject)
  {
    return true;
  }
}
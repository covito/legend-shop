package com.legendshop.core.security;

import java.io.Serializable;

public abstract interface GrantedFunction extends Serializable
{
  public abstract String getFunction();
}
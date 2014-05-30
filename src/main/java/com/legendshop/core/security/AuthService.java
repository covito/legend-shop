package com.legendshop.core.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public abstract interface AuthService extends UserDetailsService
{
  public abstract Collection<GrantedFunction> getFunctionsByRoles(Collection<? extends GrantedAuthority> paramCollection);
}
package com.legendshop.central.install;

import javax.servlet.http.HttpServletRequest;

public abstract interface Setup
{
  public abstract void startSetup(HttpServletRequest paramHttpServletRequest);
}
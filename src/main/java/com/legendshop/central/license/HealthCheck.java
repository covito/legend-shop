package com.legendshop.central.license;

public abstract interface HealthCheck extends Runnable
{
  public abstract String check();
}
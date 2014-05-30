package com.legendshop.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class SystemArchitecture
{
  private final Logger _$1 = LoggerFactory.getLogger(SystemArchitecture.class);

  @Pointcut("execution(* com.legendshop.business.dao.*.*(..))")
  public void inDataAccessLayer()
  {
  }

  @Pointcut("within(com.legendshop.model.entity..*)")
  public void inDomainModel()
  {
  }

  @Pointcut("execution(* *..service.*.*(..))")
  public void inServiceLayer()
  {
  }

  @Pointcut("execution(* *..dao.*.*(..))")
  public void daoOperation()
  {
    this._$1.debug("dao operation");
  }

  @Pointcut("execution(**...service.impl.*.*(..))")
  public void businessService()
  {
    this._$1.debug("service layer operation");
  }

  @Before("inDataAccessLayer()")
  public void printDaoLayer()
  {
    this._$1.debug("In DAO layer");
  }

  @Before("inServiceLayer()")
  public void printServiceLayer()
  {
    this._$1.debug("In Service layer");
  }
}
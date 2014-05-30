package com.legendshop.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StringType;

public class SqlServerDialect extends SQLServerDialect
{
  public SqlServerDialect()
  {
    registerHibernateType(12, Hibernate.STRING.getName());
  }
}
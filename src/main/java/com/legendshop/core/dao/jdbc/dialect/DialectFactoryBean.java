package com.legendshop.core.dao.jdbc.dialect;

import com.legendshop.core.exception.ApplicationException;
import com.legendshop.core.exception.NotFoundException;
import com.legendshop.util.AppUtils;
import org.springframework.beans.factory.FactoryBean;

public class DialectFactoryBean
  implements FactoryBean<Dialect>
{
  private String _$1;

  public Dialect getObject()
    throws Exception
  {
    if (AppUtils.isBlank(this._$1))
      throw new NotFoundException("dialect can not be empty");
    Object localObject = null;
    if (this._$1.equalsIgnoreCase("org.hibernate.dialect.MySQLDialect"))
      localObject = new MySQLDialect();
    else if (this._$1.equalsIgnoreCase("org.hibernate.dialect.OracleDialect"))
      localObject = new OracleDialect();
    else if (this._$1.equalsIgnoreCase("com.legendshop.util.SqlServerDialect"))
      localObject = new MsSQLDialect();
    else
      throw new ApplicationException("Can not support this dialect ");
    return ((Dialect)localObject);
  }

  public Class<?> getObjectType()
  {
    return Dialect.class;
  }

  public boolean isSingleton()
  {
    return true;
  }

  public void setDialect(String paramString)
  {
    this._$1 = paramString;
  }
}
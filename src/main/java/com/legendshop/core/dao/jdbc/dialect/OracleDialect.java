package com.legendshop.core.dao.jdbc.dialect;

public class OracleDialect
  implements Dialect
{
  public String getLimitString(String paramString, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 100);
    localStringBuilder.append("SELECT * FROM (SELECT ROW_.*, ROWNUM RN FROM (");
    localStringBuilder.append(paramString);
    localStringBuilder.append(") ROW_ WHERE ROWNUM <=");
    localStringBuilder.append(paramInt1 + paramInt2);
    localStringBuilder.append(") WHERE RN>=");
    localStringBuilder.append(paramInt1);
    return localStringBuilder.toString();
  }

  public String getLimitString(String paramString, boolean paramBoolean)
  {
    paramString = paramString.trim();
    int i = 0;
    if (paramString.toLowerCase().endsWith(" for update"))
    {
      paramString = paramString.substring(0, paramString.length() - 11);
      i = 1;
    }
    StringBuffer localStringBuffer = new StringBuffer(paramString.length() + 100);
    if (paramBoolean)
      localStringBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
    else
      localStringBuffer.append("select * from ( ");
    localStringBuffer.append(paramString);
    if (paramBoolean)
      localStringBuffer.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
    else
      localStringBuffer.append(" ) where rownum <= ?");
    if (i != 0)
      localStringBuffer.append(" for update");
    return localStringBuffer.toString();
  }
}
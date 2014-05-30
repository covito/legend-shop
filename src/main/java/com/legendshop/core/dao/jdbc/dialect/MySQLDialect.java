package com.legendshop.core.dao.jdbc.dialect;

public class MySQLDialect
  implements Dialect
{
  public String getLimitString(String paramString, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 20).append(paramString);
    if (paramInt1 > 0)
      localStringBuilder.append(" limit ").append(paramInt1).append(", ").append(paramInt2);
    else
      localStringBuilder.append(" limit ").append(paramInt2);
    return localStringBuilder.toString();
  }

  public String getLimitString(String paramString, boolean paramBoolean)
  {
    return (paramString.length() + 20) + paramString + ((paramBoolean) ? " limit ?, ?" : " limit ?");
  }
}
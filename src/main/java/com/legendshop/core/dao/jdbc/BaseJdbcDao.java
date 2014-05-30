package com.legendshop.core.dao.jdbc;

import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract interface BaseJdbcDao
{
  public abstract JdbcTemplate getJdbcTemplate();

  public abstract int update(String paramString, Object[] paramArrayOfObject);

  public abstract long stat(String paramString, Object[] paramArrayOfObject);

  public abstract <T> T get(String paramString, Class<T> paramClass, Object[] paramArrayOfObject);

  public abstract <T> List<T> query(String paramString, Class<T> paramClass, Object[] paramArrayOfObject);

  public abstract <T> int updateNamed(String paramString, T paramT);

  public abstract <T> int[] updateNamed(String paramString, List<T> paramList);

  public abstract int updateNamedMap(String paramString, Map<String, Object> paramMap);

  public abstract int[] updateNamedMap(String paramString, List<Map<String, Object>> paramList);

  public abstract PageSupport find(SimpleSqlQuery paramSimpleSqlQuery);

  public abstract void deleteUserItem(String paramString1, String paramString2);
}
package com.legendshop.core.dao.jdbc.impl;

import com.legendshop.core.dao.jdbc.BaseJdbcDao;
import com.legendshop.core.dao.jdbc.dialect.Dialect;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SimpleSqlQuery;
import com.legendshop.core.page.PagerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

public class BaseJdbcDaoImpl
  implements BaseJdbcDao
{
  private final Logger _$1 = LoggerFactory.getLogger(BaseJdbcDaoImpl.class);
  protected JdbcTemplate jdbcTemplate;
  protected Dialect dialect;

  public int update(String paramString, Object[] paramArrayOfObject)
  {
    return this.jdbcTemplate.update(paramString, paramArrayOfObject);
  }

  public long stat(String paramString, Object[] paramArrayOfObject)
  {
    return this.jdbcTemplate.queryForLong(paramString, paramArrayOfObject);
  }

  public <T> T get(String paramString, Class<T> paramClass, Object[] paramArrayOfObject)
  {
    try
    {
      return this.jdbcTemplate.queryForObject(paramString, BeanPropertyRowMapper.newInstance(paramClass), paramArrayOfObject);
    }
    catch (EmptyResultDataAccessException localEmptyResultDataAccessException)
    {
    }
    return null;
  }

  public <T> List<T> query(String paramString, Class<T> paramClass, Object[] paramArrayOfObject)
  {
    return this.jdbcTemplate.query(paramString, BeanPropertyRowMapper.newInstance(paramClass), paramArrayOfObject);
  }

  public <T> int updateNamed(String paramString, T paramT)
  {
    String str = NamedParameterUtils.parseSqlStatementIntoString(paramString);
    ParsedSql localParsedSql = NamedParameterUtils.parseSqlStatement(paramString);
    BeanPropertySqlParameterSource localBeanPropertySqlParameterSource = new BeanPropertySqlParameterSource(paramT);
    List localList = NamedParameterUtils.buildSqlParameterList(localParsedSql, localBeanPropertySqlParameterSource);
    Object[] arrayOfObject = NamedParameterUtils.buildValueArray(localParsedSql, localBeanPropertySqlParameterSource, localList);
    return this.jdbcTemplate.update(str, arrayOfObject);
  }

  public <T> int[] updateNamed(String paramString, List<T> paramList)
  {
    String str = NamedParameterUtils.parseSqlStatementIntoString(paramString);
    ParsedSql localParsedSql = NamedParameterUtils.parseSqlStatement(paramString);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      BeanPropertySqlParameterSource localBeanPropertySqlParameterSource = new BeanPropertySqlParameterSource(localObject);
      List localList = NamedParameterUtils.buildSqlParameterList(localParsedSql, localBeanPropertySqlParameterSource);
      Object[] arrayOfObject = NamedParameterUtils.buildValueArray(localParsedSql, localBeanPropertySqlParameterSource, localList);
      localArrayList.add(arrayOfObject);
    }
    return this.jdbcTemplate.batchUpdate(str, localArrayList);
  }

  public int updateNamedMap(String paramString, Map<String, Object> paramMap)
  {
    String str = NamedParameterUtils.parseSqlStatementIntoString(paramString);
    Object[] arrayOfObject = NamedParameterUtils.buildValueArray(paramString, paramMap);
    return this.jdbcTemplate.update(str, arrayOfObject);
  }

  public int[] updateNamedMap(String paramString, List<Map<String, Object>> paramList)
  {
    String str = NamedParameterUtils.parseSqlStatementIntoString(paramString);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Map localMap = (Map)localIterator.next();
      Object[] arrayOfObject = NamedParameterUtils.buildValueArray(paramString, localMap);
      localArrayList.add(arrayOfObject);
    }
    return this.jdbcTemplate.batchUpdate(str, localArrayList);
  }

  public void setJdbcTemplate(JdbcTemplate paramJdbcTemplate)
  {
    this.jdbcTemplate = paramJdbcTemplate;
  }

  public PageSupport find(SimpleSqlQuery paramSimpleSqlQuery)
  {
    int i = PagerUtil.getCurPageNO(paramSimpleSqlQuery.getCurPage());
    if (this._$1.isInfoEnabled())
      if (paramSimpleSqlQuery.getParam() != null)
        this._$1.info("Running SQL:\n" + paramSimpleSqlQuery.getAllCountString().replace("?", "{}"), paramSimpleSqlQuery.getParam());
      else
        this._$1.info("Running SQL:\n" + paramSimpleSqlQuery.getAllCountString());
    long l = stat(paramSimpleSqlQuery.getAllCountString(), paramSimpleSqlQuery.getParam());
    int j = PagerUtil.getOffset(l, i, paramSimpleSqlQuery.getPageSize());
    Object[] arrayOfObject = null;
    if (paramSimpleSqlQuery.getParam() != null)
    {
      int k = paramSimpleSqlQuery.getParam().length;
      if (j > 0)
      {
        arrayOfObject = Arrays.copyOf(paramSimpleSqlQuery.getParam(), k + 2);
        arrayOfObject[k] = Integer.valueOf(j);
        arrayOfObject[(k + 1)] = Integer.valueOf(paramSimpleSqlQuery.getPageSize());
      }
      else
      {
        arrayOfObject = Arrays.copyOf(paramSimpleSqlQuery.getParam(), k + 1);
        arrayOfObject[k] = Integer.valueOf(paramSimpleSqlQuery.getPageSize());
      }
    }
    else if (j > 0)
    {
      arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(j);
      arrayOfObject[1] = Integer.valueOf(paramSimpleSqlQuery.getPageSize());
    }
    else
    {
      arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramSimpleSqlQuery.getPageSize());
    }
    String str = this.dialect.getLimitString(paramSimpleSqlQuery.getQueryString(), j > 0);
    if (this._$1.isInfoEnabled())
      if ((arrayOfObject != null) && (arrayOfObject.length > 0))
        this._$1.info("Running SQL:\n" + str.replace("?", "{}"), arrayOfObject);
      else
        this._$1.info("Running SQL:\n" + str);
    List localList = this.jdbcTemplate.query(str, BeanPropertyRowMapper.newInstance(paramSimpleSqlQuery.getEntityClass()), arrayOfObject);
    return new PageSupport(localList, paramSimpleSqlQuery.getMyaction(), j, i, l, paramSimpleSqlQuery.getPageSize(), paramSimpleSqlQuery.getPageProvider());
  }

  public JdbcTemplate getJdbcTemplate()
  {
    return this.jdbcTemplate;
  }

  public void setDialect(Dialect paramDialect)
  {
    this.dialect = paramDialect;
  }

  public void deleteUserItem(String paramString1, String paramString2)
  {
    update("delete from " + paramString1 + " where user_name = ?", new Object[] { paramString2 });
  }
}
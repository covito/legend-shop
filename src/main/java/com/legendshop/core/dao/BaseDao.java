package com.legendshop.core.dao;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract interface BaseDao
{
  public abstract <T> T get(Class<T> paramClass, Serializable paramSerializable);

  public abstract <T> T getByLockMode(Class<T> paramClass, Serializable paramSerializable, LockMode paramLockMode);

  public abstract <T> T load(Class<T> paramClass, Serializable paramSerializable);

  public abstract <T> T loadByLockMode(Class<T> paramClass, Serializable paramSerializable, LockMode paramLockMode);

  public abstract <T> List<T> getAll(Class<T> paramClass);

  public abstract <T> List<T> getAll(Class<T> paramClass, String paramString, boolean paramBoolean);

  public abstract Serializable save(Object paramObject);

  public abstract void deleteAll(Collection paramCollection);

  public abstract void flush();

  public abstract void clear();

  public abstract List findByHQL(String paramString, Object[] paramArrayOfObject);

  public abstract <T> T findUniqueBy(String paramString, Class<T> paramClass, Object[] paramArrayOfObject);

  public abstract List findByHQLLimit(String paramString, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract <T> T findUniqueByHQLLimit(String paramString, Class<T> paramClass, int paramInt1, int paramInt2, Object[] paramArrayOfObject);

  public abstract List findBySQL(String paramString);

  public abstract Object merge(Object paramObject);

  public abstract void executeBySql(String paramString);

  public abstract String executeByProcedure(String paramString, String[] paramArrayOfString);

  public abstract Integer exeByHQL(String paramString, Object[] paramArrayOfObject);

  public abstract HibernateTemplate getHibernateTemplate();

  public abstract PageSupport find(HqlQuery paramHqlQuery);

  public abstract PageSupport find(SqlQuery paramSqlQuery);

  public abstract PageSupport find(CriteriaQuery paramCriteriaQuery, boolean paramBoolean);

  public abstract PageSupport find(CriteriaQuery paramCriteriaQuery);

  public abstract List findListByCriteria(CriteriaQuery paramCriteriaQuery, int paramInt1, int paramInt2);
}
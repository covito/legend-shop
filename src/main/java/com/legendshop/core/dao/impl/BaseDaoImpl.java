package com.legendshop.core.dao.impl;

import com.legendshop.core.dao.BaseDao;
import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.util.AppUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

public abstract class BaseDaoImpl implements BaseDao {
	private HibernateTemplate template;

	public <T> T get(Class<T> paramClass, Serializable paramSerializable) {
		return getHibernateTemplate().get(paramClass, paramSerializable);
	}

	public <T> T getByLockMode(Class<T> paramClass,
			Serializable paramSerializable, LockMode paramLockMode) {
		return getHibernateTemplate().get(paramClass, paramSerializable,
				paramLockMode);
	}

	public <T> T load(Class<T> paramClass, Serializable paramSerializable) {
		return getHibernateTemplate().load(paramClass, paramSerializable);
	}

	public <T> T loadByLockMode(Class<T> paramClass,
			Serializable paramSerializable, LockMode paramLockMode) {
		return getHibernateTemplate().load(paramClass, paramSerializable,
				paramLockMode);
	}

	public <T> List<T> getAll(Class<T> paramClass) {
		return getHibernateTemplate().loadAll(paramClass);
	}

	public <T> List<T> getAll(Class<T> paramClass, String paramString,
			boolean paramBoolean) {
		Assert.hasText(paramString);
		if (paramBoolean)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(paramClass).addOrder(
							Order.asc(paramString)));
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(paramClass).addOrder(
						Order.desc(paramString)));
	}

	public Serializable save(Object paramObject) {
		return getHibernateTemplate().save(paramObject);
	}

	public void saveOrUpdate(Object paramObject) {
		getHibernateTemplate().saveOrUpdate(paramObject);
	}

	public void saveOrUpdateAll(Collection paramCollection) {
		getHibernateTemplate().saveOrUpdateAll(paramCollection);
	}

	protected void update(Object paramObject) {
		getHibernateTemplate().update(paramObject);
	}

	protected void delete(Object paramObject) {
		getHibernateTemplate().delete(paramObject);
	}

	protected boolean delete(Class paramClass, Serializable paramSerializable) {
		Object localObject = getHibernateTemplate().get(paramClass,
				paramSerializable);
		if (localObject != null) {
			delete(localObject);
			return true;
		}
		return false;
	}

	protected <T> void deleteById(Class<T> paramClass,
			Serializable paramSerializable) {
		Object localObject = get(paramClass, paramSerializable);
		if (localObject != null)
			delete(localObject);
	}

	public void deleteAll(Collection paramCollection) {
		getHibernateTemplate().deleteAll(paramCollection);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public List findByHQL(String paramString, Object[] paramArrayOfObject) {
		return getHibernateTemplate().find(paramString, paramArrayOfObject);
	}

	public <T> T findUniqueBy(String paramString, Class<T> paramClass,
			Object[] paramArrayOfObject) {
		return (T) getHibernateTemplate().execute(
				new IIlIIIllIllIIIII(paramString, paramArrayOfObject));
	}

	public List findByHQLLimit(String paramString, int paramInt1,
			int paramInt2, Object[] paramArrayOfObject) {
		return ((List) getHibernateTemplate().execute(
				new lllIIIllIllIIIII(paramString, paramArrayOfObject,
						paramInt1, paramInt2)));
	}

	public static Query _$1(String paramString, Session paramSession,
			Object[] paramArrayOfObject) {
		Query localQuery = paramSession.createQuery(paramString);
		if (AppUtils.isNotBlank(paramArrayOfObject))
			for (int i = 0; i < paramArrayOfObject.length; ++i)
				localQuery.setParameter(i, paramArrayOfObject[i]);
		return localQuery;
	}

	public <T> T findUniqueByHQLLimit(String paramString, Class<T> paramClass,
			int paramInt1, int paramInt2, Object[] paramArrayOfObject) {
		return (T) getHibernateTemplate().execute(
				new IllIIIllIllIIIII(paramString, paramArrayOfObject,
						paramInt1, paramInt2));
	}

	public List findBySQL(String paramString) {
		return ((List) getHibernateTemplate().execute(
				new lIIllIllIllIIIII(paramString)));
	}

	public Object merge(Object paramObject) {
		return getHibernateTemplate().merge(paramObject);
	}

	public void executeBySql(String paramString) {
		getHibernateTemplate().execute(new IIIllIllIllIIIII(paramString));
	}

	public String executeByProcedure(String paramString,
			String[] paramArrayOfString) {
		return ((String) getHibernateTemplate().execute(
				new llIllIllIllIIIII(paramString, paramArrayOfString)));
	}

	public Integer exeByHQL(String paramString, Object[] paramArrayOfObject) {
		return ((Integer) getHibernateTemplate().execute(
				new IlIllIllIllIIIII(paramString, paramArrayOfObject)));
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.template;
	}

	public void setHibernateTemplate(HibernateTemplate paramHibernateTemplate) {
		this.template = paramHibernateTemplate;
	}

	public PageSupport find(HqlQuery paramHqlQuery) {
		return ((PageSupport) getHibernateTemplate().execute(
				new lIlllIllIllIIIII(paramHqlQuery)));
	}

	public static void _$1(Query paramQuery1, Query paramQuery2,
			List paramList, Object[] paramArrayOfObject, Type[] paramArrayOfType) {
		int i;
		if (AppUtils.isNotBlank(paramList))
			for (i = 0; i < paramList.size(); ++i) {
				paramQuery1.setParameter(i, paramList.get(i));
				paramQuery2.setParameter(i, paramList.get(i));
			}
		else if (AppUtils.isNotBlank(paramArrayOfObject))
			if (AppUtils.isNotBlank(paramArrayOfType)) {
				paramQuery1.setParameters(paramArrayOfObject, paramArrayOfType);
				paramQuery2.setParameters(paramArrayOfObject, paramArrayOfType);
			} else {
				for (i = 0; i < paramArrayOfObject.length; ++i) {
					paramQuery1.setParameter(i, paramArrayOfObject[i]);
					paramQuery2.setParameter(i, paramArrayOfObject[i]);
				}
			}
	}

	public PageSupport find(SqlQuery paramSqlQuery) {
		return ((PageSupport) getHibernateTemplate().execute(
				new IIlllIllIllIIIII(paramSqlQuery)));
	}

	public PageSupport find(CriteriaQuery paramCriteriaQuery,
			boolean paramBoolean) {
		return ((PageSupport) getHibernateTemplate().execute(
				new IllllIIIIlIIlIlI(paramCriteriaQuery, paramBoolean)));
	}

	public PageSupport find(CriteriaQuery paramCriteriaQuery) {
		return find(paramCriteriaQuery, true);
	}

	public List findListByCriteria(CriteriaQuery paramCriteriaQuery,
			int paramInt1, int paramInt2) {
		return ((List) getHibernateTemplate().execute(
				new lIIllIIIIlIIlIlI(paramCriteriaQuery, paramInt1, paramInt2)));
	}
}
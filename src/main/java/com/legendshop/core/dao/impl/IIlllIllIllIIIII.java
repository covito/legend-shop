package com.legendshop.core.dao.impl;

import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.dao.support.SqlQuery;
import com.legendshop.core.page.PagerUtil;
import com.legendshop.util.AppUtils;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

class IIlllIllIllIIIII implements HibernateCallback {
	private SqlQuery sqlQuery;

	public IIlllIllIllIIIII(SqlQuery paramSqlQuery) {
		this.sqlQuery = paramSqlQuery;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		SQLQuery localSQLQuery = paramSession.createSQLQuery(this.sqlQuery
				.getQueryString());
		if (AppUtils.isNotBlank(this.sqlQuery.getEntityClass())) {
			Iterator localObject = this.sqlQuery.getEntityClass().keySet()
					.iterator();
			while (((Iterator) localObject).hasNext()) {
				String str = (String) ((Iterator) localObject).next();
				localSQLQuery.setResultTransformer(Transformers
						.aliasToBean((Class) this.sqlQuery.getEntityClass()
								.get(str)));
			}
		}
		Object localObject = paramSession.createSQLQuery(this.sqlQuery
				.getAllCountString());
		BaseDaoImpl._$1(localSQLQuery, (Query) localObject,
				this.sqlQuery.getParams(), this.sqlQuery.getParam(),
				this.sqlQuery.getTypes());
		long l = ((Number) ((SQLQuery) localObject).uniqueResult()).longValue();
		int i = PagerUtil.getCurPageNO(this.sqlQuery.getCurPage());
		int j = PagerUtil.getOffset(l, i, this.sqlQuery.getPageSize());
		localSQLQuery.setFirstResult(j);
		localSQLQuery.setMaxResults(this.sqlQuery.getPageSize());
		return new PageSupport(localSQLQuery.list(),
				this.sqlQuery.getMyaction(), j, i, l,
				this.sqlQuery.getPageSize(), this.sqlQuery.getPageProvider());
	}
}
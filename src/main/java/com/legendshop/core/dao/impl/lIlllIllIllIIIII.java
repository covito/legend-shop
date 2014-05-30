package com.legendshop.core.dao.impl;

import com.legendshop.core.dao.support.HqlQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.page.PagerUtil;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class lIlllIllIllIIIII implements HibernateCallback {
	private HqlQuery hql;

	public lIlllIllIllIIIII(HqlQuery hql) {
		this.hql = hql;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		Query localQuery1 = paramSession.createQuery(this.hql.getQueryString());
		Query localQuery2 = paramSession.createQuery(this.hql
				.getAllCountString());
		BaseDaoImpl._$1(localQuery1, localQuery2, this.hql.getParams(),
				this.hql.getParam(), this.hql.getTypes());
		long l = ((Number) localQuery2.uniqueResult()).longValue();
		int i = PagerUtil.getCurPageNO(this.hql.getCurPage());
		int j = PagerUtil.getOffset(l, i, this.hql.getPageSize());
		localQuery1.setFirstResult(j);
		localQuery1.setMaxResults(this.hql.getPageSize());
		return new PageSupport(localQuery1.list(), this.hql.getMyaction(), j,
				i, l, this.hql.getPageSize(), this.hql.getPageProvider());
	}
}
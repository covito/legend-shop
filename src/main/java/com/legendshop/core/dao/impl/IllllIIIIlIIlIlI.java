package com.legendshop.core.dao.impl;

import com.legendshop.core.dao.support.CriteriaQuery;
import com.legendshop.core.dao.support.PageSupport;
import com.legendshop.core.page.PagerUtil;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;

class IllllIIIIlIIlIlI implements HibernateCallback {
	private CriteriaQuery query;

	private boolean page;

	public IllllIIIIlIIlIlI(CriteriaQuery query, boolean page) {
		this.query = query;
		this.page = page;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		this.query.add();
		Criteria localCriteria = this.query.getDetachedCriteria()
				.getExecutableCriteria(paramSession);
		Projection localProjection = Projections.rowCount();
		Object localObject = localCriteria.setProjection(localProjection)
				.uniqueResult();
		long l = ((Number) localObject).longValue();
		localCriteria.setProjection(null);

		int i = PagerUtil.getCurPageNO(this.query.getCurPage());
		int j = PagerUtil.getOffset(l, i, this.query.getPageSize());
		if (this.query.getOrderList() != null) {
			Iterator localIterator = this.query.getOrderList().iterator();
			while (localIterator.hasNext()) {
				Order localOrder = (Order) localIterator.next();
				localCriteria.addOrder(localOrder);
			}
		}
		if (this.page) {
			localCriteria.setFirstResult(j);
			localCriteria.setMaxResults(this.query.getPageSize());
		}
		return new PageSupport(localCriteria.list(), this.query.getMyaction(),
				j, i, l, this.query.getPageSize(), this.query.getPageProvider());
	}
}
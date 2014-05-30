package com.legendshop.core.dao.impl;

import com.legendshop.core.dao.support.CriteriaQuery;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;

class lIIllIIIIlIIlIlI implements HibernateCallback {

	private CriteriaQuery query;
	private int startrow;
	private int limit;

	public lIIllIIIIlIIlIlI(CriteriaQuery paramCriteriaQuery, int paramInt1,
			int paramInt2) {
		this.query = paramCriteriaQuery;
		this.startrow = paramInt1;
		this.limit = paramInt2;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		this.query.add();
		Criteria localCriteria = this.query.getDetachedCriteria()
				.getExecutableCriteria(paramSession);
		if (this.query.getOrderList() != null) {
			Iterator localIterator = this.query.getOrderList().iterator();
			while (localIterator.hasNext()) {
				Order localOrder = (Order) localIterator.next();
				localCriteria.addOrder(localOrder);
			}
		}
		localCriteria.setFirstResult(this.startrow);
		localCriteria.setMaxResults(this.limit);
		return localCriteria.list();
	}
}
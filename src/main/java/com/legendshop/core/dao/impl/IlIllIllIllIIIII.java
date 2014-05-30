package com.legendshop.core.dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class IlIllIllIllIIIII implements HibernateCallback {

	private String sql;

	private Object[] param;

	public IlIllIllIllIIIII(String sql, Object[] param) {
		this.sql = sql;
		this.param = param;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		Query localQuery = BaseDaoImpl._$1(this.sql, paramSession, this.param);
		return Integer.valueOf(localQuery.executeUpdate());
	}
}
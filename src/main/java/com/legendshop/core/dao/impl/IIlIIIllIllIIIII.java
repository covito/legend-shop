package com.legendshop.core.dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class IIlIIIllIllIIIII implements HibernateCallback {

	private String sql;

	private Session session;

	private Object[] param;
	
	public IIlIIIllIllIIIII(String sql, Session session, Object[] param) {
		super();
		this.sql = sql;
		this.session = session;
		this.param = param;
	}
	
	public IIlIIIllIllIIIII(String sql,  Object[] param) {
		super();
		this.sql = sql;
		this.param = param;
	}



	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		Query localQuery = BaseDaoImpl._$1(this.sql, session, param);
		return localQuery.uniqueResult();
	}
}
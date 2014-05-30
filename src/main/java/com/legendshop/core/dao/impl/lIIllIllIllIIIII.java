package com.legendshop.core.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class lIIllIllIllIIIII implements HibernateCallback<List> {
	
	private String sql;
	
	public lIIllIllIllIIIII(String sql) {
		this.sql=sql;
	}
	
	
	public List doInHibernate(Session paramSession) throws HibernateException,
			SQLException {
		SQLQuery query = paramSession.createSQLQuery(this.sql);
		return query.list();
	}
}
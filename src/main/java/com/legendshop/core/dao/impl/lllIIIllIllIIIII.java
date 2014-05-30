package com.legendshop.core.dao.impl;

import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class lllIIIllIllIIIII implements HibernateCallback {

	private String sql;

	private Session session;

	private Object[] param;

	private int starRow;

	private int limet;
	
	

	public lllIIIllIllIIIII(String sql, Object[] param,
			int starRow, int limet) {
		this.sql = sql;
		this.param = param;
		this.starRow = starRow;
		this.limet = limet;
	}



	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		Query localQuery = BaseDaoImpl._$1(this.sql, paramSession, this.param);
		localQuery.setFirstResult(this.starRow);
		localQuery.setMaxResults(this.limet);
		return localQuery.list();
	}
}
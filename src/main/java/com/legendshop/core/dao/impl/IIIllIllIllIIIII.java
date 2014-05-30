package com.legendshop.core.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class IIIllIllIllIIIII  implements HibernateCallback {

	private String sql;
	
	public IIIllIllIllIIIII(String sql) {
		this.sql=sql;
	}
	
	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		try {
			Connection con = paramSession.connection();
			PreparedStatement prestate = con
					.prepareStatement(this.sql);
			prestate.execute();
			prestate.close();
			paramSession.flush();
			paramSession.clear();
			return null;
		} catch (RuntimeException localRuntimeException) {
			localRuntimeException.printStackTrace();
		}
		return null;
	}
}
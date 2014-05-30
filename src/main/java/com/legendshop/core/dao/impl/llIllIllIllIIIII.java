package com.legendshop.core.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

class llIllIllIllIIIII implements HibernateCallback {

	String sql;

	String[] param;

	public llIllIllIllIIIII(String sql, String[] param) {
		this.sql = sql;
		this.param = param;
	}

	public Object doInHibernate(Session paramSession)
			throws HibernateException, SQLException {
		Connection localConnection;
		try {
			localConnection = paramSession.connection();
			CallableStatement localCallableStatement = localConnection
					.prepareCall(this.sql);
			localCallableStatement.registerOutParameter(1, 12);
			int i = 2;
			int j = param.length;
			for (int k = 0; k < j; ++k) {
				String str = param[k];
				localCallableStatement.setString(i, str);
				++i;
			}
			localCallableStatement.executeUpdate();
			String localObject = localCallableStatement.getString(1);
			if (localCallableStatement != null)
				localCallableStatement.close();
			return localObject;
		} catch (RuntimeException localRuntimeException) {
			localRuntimeException.printStackTrace();
		}
		return null;
	}
}